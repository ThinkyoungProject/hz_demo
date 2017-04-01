package com.goopal.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.goopal.entity.TblConfigure;
import com.goopal.entity.TblUserInfo;
import com.goopal.entity.TblUserTransaction;
import com.goopal.mapper.TblConfigureMapper;
import com.goopal.mapper.TblUserInfoMapper;
import com.goopal.mapper.TblUserTransactionMapper;
import com.goopal.service.IndexService;
import com.goopal.service.SocketService;
import com.goopal.tools.common.StringUtil;
import com.goopal.tools.common.NumberConvertUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class IndesServiceImpl implements IndexService {

	private Logger logger = LoggerFactory.getLogger(IndesServiceImpl.class);
	private ConcurrentLinkedQueue<TblUserInfo> preLoadUser;
	private Lock preLoadUsersLock = new ReentrantLock();
	private Lock readLoadedUserLock = new ReentrantLock();

	@Autowired
	private TblConfigureMapper tblConfigureMapper;

	@Autowired
	private TblUserInfoMapper tblUserInfoMapper;

	@Autowired
	private TblUserTransactionMapper tblUserTransactionMapper;

	@Autowired
	private SocketService socketService;

	@PostConstruct
	public void postConstruct(){
		System.out.println("first load");
		preLoadUser = new ConcurrentLinkedQueue<TblUserInfo>();
		List<TblUserInfo> userList = tblUserInfoMapper.selectFirstData();
		preLoadUser.addAll(userList);
	}

	@Override
	public String main(Map<String, Object> params, HttpServletRequest request) {
		String result = "fail";
		//判断用户手机是否已经注册过
		String phones = String.valueOf(params.get("phone"));
		TblUserInfo userInfo = null;
		if (!"".equals(phones) && !"null".equals(phones)) {
			TblUserInfo userT = new TblUserInfo();
			userT.setPhoneNum(String.valueOf(params.get("phone")));
			userInfo = tblUserInfoMapper.selectByPhone(userT);
		} else {
			TblUserInfo userT =(TblUserInfo)request.getSession().getAttribute("user");
			userInfo = tblUserInfoMapper.selectByPhone(userT);
		}
		if (null == userInfo && !"null".equals(phones)) {
			//将用户数据记录到数据库中
			TblUserInfo user;


      readLoadedUserLock.lock();
				try {
          if(preLoadUser.size()==0)
          {
            preLoadUsersLock.lock();
            try {
              if(preLoadUser.size()==0) {
                List<TblUserInfo> userList = tblUserInfoMapper.selectFirstData();
                preLoadUser.addAll(userList);
              }
            } finally {
              preLoadUsersLock.unlock();
            }
          }
          user = preLoadUser.poll();
        } finally {
          readLoadedUserLock.unlock();
        }


      user.setPhoneNum(String.valueOf(params.get("phone")));
			user.setBalance(100.0);
			// 更新账号信息
			user.setState((byte) 1);
			int nu = updateByPrivate(user);
			if (nu > 0) {
				request.getSession().setAttribute("user", user);
				result = "success";
			}





		}
		else {
			request.getSession().setAttribute("user", userInfo);
			result = "success";
		}

		return result;


	}

	@Override
	public TblUserInfo join(Map<String, Object> params, TblUserInfo user) {
		TblUserInfo userInfo = tblUserInfoMapper.selectByPhone(user);
		TblConfigure config = tblConfigureMapper.selectByType("contract_id");
		userInfo.setPrivateKey(config.getConfValue());
		return userInfo;
	}

	@Override
	public String confirm(Map<String, Object> params, TblUserInfo user) {
		Map<String,Object> result = new HashMap<String,Object>();
		TblConfigure configurce = tblConfigureMapper.selectByType("contract_id");
		TblConfigure owner_phone = tblConfigureMapper.selectByType("contract_owner_phone_num");
		String balance = String.valueOf(params.get("balance"));
		System.out.println("balance: "+ balance);
		List<Object> message = new ArrayList<Object>();
		message.add(configurce.getConfValue());
		message.add("t" + owner_phone.getConfValue());
		message.add("join_cooperation");
		message.add(user.getPhoneNum());
		message.add("ALP");
		message.add("10");
		String resultMesg = socketService.send("call_contract", message);
		if (!"null".equals(resultMesg) && null != resultMesg) {
			System.out.println("交易信息返回结果：" + resultMesg);
			TblUserTransaction transcation = new TblUserTransaction();
			transcation.setTrxFromAddress(user.getAddress());
			transcation.setTransferFromAddress(user.getAddress());
			transcation.setTransferToAddress(configurce.getConfValue());
			transcation.setTransferAmount(new BigDecimal(balance).doubleValue());
			transcation.setTrxState((byte)0);
			transcation.setTrxType((byte)0);
			transcation.setTrxId(StringUtil.transferNum(resultMesg));
			transcation.setTrxTime(new Date());
			int in = tblUserTransactionMapper.insertSelective(transcation);
			if (in > 0) {
				result.put("status", "success") ;
				user.setBalance(user.getBalance()-10);
				System.out.println("user data:" + user);
				user.setPrivateKey(null);
				this.tblUserInfoMapper.updateByPrimaryKeySelective(user);
			} else {
				result.put("status", "fail") ;
			}
		} else {
			result.put("status", "rpcError") ;
		}
		return JSONObject.toJSONString(result);
	}

	@Override
	public String checkPhone(Map<String,Object> params) {
		Map<String,Object> result = new HashMap<String, Object>();
		TblUserInfo user = new TblUserInfo();
		user.setPhoneNum(String.valueOf(params.get("phone")));
		TblUserInfo userInfo = tblUserInfoMapper.selectByPhone(user);
		if (null != userInfo) {
			result.put("status", "success");
		} else {
			result.put("status", "fail");
		}
		return JSONObject.toJSONString(result);
	}

	@Override
	public List<TblUserTransaction> selectByTransacPhone(Map<String,Object> params) {
		return tblUserTransactionMapper.selectByTransferPhone(params);
	}

	@Override
	public List<Map<String,Object>> selectByTrxStateByPhone(Map<String,Object> params) {
		return tblUserTransactionMapper.selectByTrxStateByPhone(params);
	}

	@Override
	public List<Map<String,Object>> selectByTrxState() {
		return tblUserTransactionMapper.selectByTrxState();
	}

	@Override
	public String selectPayment(Map<String,Object> params) {
		Map<String,Object> result = new HashMap<String,Object>();
		String address = String.valueOf(params.get("address"));
		if (!"".equals(address) && !"null".equals(address)) {
			TblUserInfo user = tblUserInfoMapper.selectByPrimaryKey(address);
			TblConfigure configurce = tblConfigureMapper.selectByType("contract_id");
			TblConfigure config = tblConfigureMapper.selectByType("contract_owner_address");
			TblConfigure phoneNum = tblConfigureMapper.selectByType("contract_owner_phone_num");
			List<Object> message = new ArrayList<Object>();
			message.add(configurce.getConfValue());
			message.add("t"+phoneNum.getConfValue());
			message.add("handle_claim");
			message.add(user.getPhoneNum());
			message.add("ALP");
			message.add("1");
			String resultMesg = socketService.send("call_contract", message);
			if (!"null".equals(resultMesg)  && null != resultMesg) {
				System.out.println("用户赔付返回信息："+resultMesg);
				TblUserTransaction transcation = new TblUserTransaction();
				transcation.setTrxFromAddress(config.getConfValue());
				transcation.setTransferFromAddress(configurce.getConfValue());
				transcation.setTransferToAddress(user.getAddress());
				transcation.setTrxState((byte)0);
				transcation.setTrxType((byte)1);
				transcation.setTrxId(StringUtil.transferNum(resultMesg));
				transcation.setTrxTime(new Date());
				int in = tblUserTransactionMapper.insertSelective(transcation);
				if (in > 0) {
					tblUserTransactionMapper.updateByFromAddress(address);
					result.put("status", "success") ;
				} else {
					result.put("status", "fail") ;
				}
			}
		}
		return JSONObject.toJSONString(result);
	}

	@Override
	public String checkByJoin(TblUserInfo user) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("phone", user.getPhoneNum());
		List<TblUserTransaction> list = tblUserTransactionMapper.selectByTransferPhone(params);
		TblUserInfo userInfo = this.tblUserInfoMapper.selectByPhone(user);
		if (null != list && list.size() > 0) {
			params.put("status", "success");
		} else if(userInfo.getBalance() < 10.0)
		{
			params.put("status", "nobalance");
		}
		else
		{
			params.put("status", "fail");
		}
		return JSONObject.toJSONString(params);
	}

	@Override
	public Map<String,Object> getBalance(TblUserInfo user) {
		Map<String,Object> result = new HashMap<String,Object>();
		TblUserInfo userInfo = tblUserInfoMapper.selectByPhone(user);
		result.put("balance", userInfo.getBalance());
		result.put("block", 0.01);

		return result;
	}

	@Override
	public TblUserInfo queryByPhone(String phone) {
		return this.tblUserInfoMapper.queryByPhone(phone);
	}

	@Override
	public TblUserInfo selectNoState(String phone) {
		return this.tblUserInfoMapper.selectNoState(phone);
	}

	@Override
	public TblConfigure audphone() {
		return tblConfigureMapper.selectByType("contract_owner_phone_num");
	}

	@Override
	public TblConfigure audAddress(){
		return tblConfigureMapper.selectByType("contract_id");
	}
	@Override
	public TblConfigure Issue(){
		return tblConfigureMapper.selectByType("contract_issue");
	}

	@Override
	public Map<String,Object> mutualInfo() {
		Map<String,Object> result = new HashMap<String,Object>();
		int num = this.tblUserInfoMapper.selectCountUser();
		String address = audAddress().getConfValue();
		String issue = Issue().getConfValue();
		int issueNum = Integer.parseInt(issue);
		String issueStr = NumberConvertUtil.foematInteger(issueNum);
		String ban = this.tblConfigureMapper.selectByType("contract_balance").getConfValue();
		issueStr = issueStr + "期";
		result.put("num", num);
		result.put("issue",issueStr);
		result.put("balance", ban);

		result.put("address", address);
		return result;
	}

	@Override
	public List<Map<String,Object>> selectAllTrance() {
		return this.tblUserTransactionMapper.selectAllTrance();
	}

	@Override
	public List<Map<String,Object>> selectBytranToPhone(String phone) {
		return this.tblUserTransactionMapper.selectBytranToPhone(phone);
	}

	@Override
	public int updateByTrxState(Map<String,Object> params) {
		return this.tblUserTransactionMapper.updateByTrxState(params);
	}

	@Override
	public List<Map<String, Object>> selectByExamine() {
		return this.tblUserTransactionMapper.selectByExamine();
	}

	@Override
	public Map<String,Object> selectNumTrans() {
		return this.tblUserTransactionMapper.selectNumTrans();
	}

	public int updateByPrivate(TblUserInfo user) {

		return tblUserInfoMapper.updateByPrivate(user);
	}


}
