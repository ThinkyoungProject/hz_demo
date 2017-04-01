package com.goopal.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goopal.entity.TblConfigure;
import com.goopal.entity.TblUserInfo;
import com.goopal.entity.TblUserTransaction;
import com.goopal.mapper.TblConfigureMapper;
import com.goopal.mapper.TblUserInfoMapper;
import com.goopal.mapper.TblUserTransactionMapper;
import com.goopal.service.SocketService;
import com.goopal.tools.common.StringUtil;

public class ContractTask {

	@Autowired
	private SocketService socketService;

	@Autowired
	private TblUserTransactionMapper tblUserTransactionMapper;
	
	@Autowired
	private TblConfigureMapper tblConfigureMapper;
	
	@Autowired
	private TblUserInfoMapper tblUserInfoMapper;

	public void insuredCon() {
		try {
			List<TblUserTransaction> insurd = tblUserTransactionMapper.selectByInsured();
			if (insurd != null && insurd.size() > 0) {
				for (TblUserTransaction tbl : insurd) {
					List<Object> params = new ArrayList<>();
					params.add(tbl.getTrxId());
					String message = socketService.send("get_result_trx_id", params);
					if (!"".equals(message) && !"[]".equals(message) && message != null) {
						tbl.setExtTrxId(message);
						List<Object> cd = new ArrayList<Object>();
						cd.add(message);
						String tran = socketService.send("blockchain_get_transaction", cd);
						Map<String, Object> resulTran = StringUtil.insuredInfo(tran);
						if (null != resulTran) {
							String blockNum = resulTran.get("block_num").toString();
							String trxNum = resulTran.get("trx_num").toString();
							List<Object> td = new ArrayList<Object>();
							td.add(blockNum);
							String blockId = socketService.send("blockchain_get_block", td);
							JSONObject blockJson = JSONArray.parseObject(blockId);
							tbl.setTrxState(Byte.decode("1"));
							tbl.setBlockNum(Integer.valueOf(blockNum));
							tbl.setBlockTrxNum(Integer.valueOf(trxNum));
							tbl.setBlockId(blockJson.getString("id"));
							Map<String,Object> chainLocation = new HashMap<String, Object>();
							chainLocation.put("block_num", resulTran.get("block_num"));
							chainLocation.put("block_trx_num", resulTran.get("block_trx_num"));
							List<TblUserTransaction> tmpUserTranList = tblUserTransactionMapper.selectBychainlocation(chainLocation);
							if (tmpUserTranList.size() == 0)
							{//此交易为最新奖池状态更新，那么需要更新奖池余额
								TblConfigure conf = tblConfigureMapper.selectByType("contract_balance");
								conf.setConfValue(resulTran.get("total_balance").toString());
								tblConfigureMapper.updateByPrimaryKey(conf);
							}
							TblUserInfo user = new TblUserInfo();
							user.setPhoneNum(String.valueOf(resulTran.get("user_phone")));
							TblUserInfo userInfo = tblUserInfoMapper.selectByPhone(user);
							userInfo.setBalance(Double.valueOf(resulTran.get("user_balance").toString()));
							tblUserInfoMapper.updateByPrimaryKey(userInfo);
						} else {
							tbl.setTrxState(Byte.decode("-1"));
						}
					}
					tblUserTransactionMapper.updateByPrimaryKey(tbl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paymentCon() {
		List<TblUserTransaction> payment = tblUserTransactionMapper.selectByPayment();
		if (payment != null && payment.size() > 0) {
			for (TblUserTransaction tbl : payment) {
				List<Object> params = new ArrayList<>();
				params.add(tbl.getTrxId());
				String message = socketService.send("get_result_trx_id", params);
				if (!"".equals(message) && !"[]".equals(message) && message != null) {
					tbl.setExtTrxId(message);
					List<Object> cd = new ArrayList<Object>();
					cd.add(message);
					String tran = socketService.send("blockchain_get_transaction", cd);
					Map<String, Object> resulTran = StringUtil.paymentInfo(tran);
					if (null != resulTran) {
						String blockNum = resulTran.get("block_num").toString();
						String trxNum = resulTran.get("trx_num").toString();
						List<Object> td = new ArrayList<Object>();
						td.add(blockNum);
						String blockId = socketService.send("blockchain_get_block", td);
						JSONObject blockJson = JSONArray.parseObject(blockId);
						tbl.setTrxState(Byte.decode("1"));
						tbl.setBlockNum(Integer.valueOf(blockNum));
						tbl.setBlockTrxNum(Integer.valueOf(trxNum));
						tbl.setBlockId(blockJson.getString("id"));
						if (resulTran.containsKey("payment")) {
							tbl.setTransferAmount(Double.valueOf(resulTran.get("payment").toString()));
						}
						Map<String,Object> chainLocation = new HashMap<String, Object>();
						chainLocation.put("block_num", resulTran.get("block_num"));
						chainLocation.put("block_trx_num", resulTran.get("block_trx_num"));
						List<TblUserTransaction> tmpUserTranList = tblUserTransactionMapper.selectBychainlocation(chainLocation);
						if (tmpUserTranList.size() == 0)
						{//此交易为最新奖池状态更新，那么需要更新奖池余额
							TblConfigure conf = tblConfigureMapper.selectByType("contract_balance");
							conf.setConfValue(resulTran.get("total_balance").toString());
							tblConfigureMapper.updateByPrimaryKey(conf);
						}
						TblUserInfo user = new TblUserInfo();
						user.setPhoneNum(String.valueOf(resulTran.get("user_phone")));
						TblUserInfo userInfo = tblUserInfoMapper.selectByPhone(user);
						if ("-1".equals(resulTran.get("user_balance").toString()))
						{
							userInfo.setBalance(0.0);
							tbl.setTrxState(Byte.decode("-1"));
						}
						else
						{
							userInfo.setBalance(Double.valueOf(resulTran.get("user_balance").toString()));
						}
						
						tblUserInfoMapper.updateByPrimaryKey(userInfo);
					} else {
						tbl.setTrxState(Byte.decode("-1"));
					}
				}
				tblUserTransactionMapper.updateByPrimaryKey(tbl);
			}
		}

	}
	public void newIssue(){
		TblConfigure configurce = tblConfigureMapper.selectByType("contract_id");
		TblConfigure phoneNum = tblConfigureMapper.selectByType("contract_owner_phone_num");
		List<Object> message = new ArrayList<Object>();
		message.add(configurce.getConfValue());
		message.add("t"+phoneNum.getConfValue());
		message.add("re_init");
		message.add("");
		message.add("ALP");
		message.add("1");
		String resultMesg = socketService.send("call_contract", message);
		if (!"null".equals(resultMesg)  && null != resultMesg) {
			System.out.println("合约奖池余额变化："+resultMesg);
			String trxId = StringUtil.transferNum(resultMesg);

			while(true)
			{
				List<Object> params = new ArrayList<>();
				params.add(trxId);
				String resMessage = socketService.send("get_result_trx_id", params);
				if (!"".equals(resMessage) && !"[]".equals(resMessage) && resMessage != null) {
					tblUserTransactionMapper.deleteAllTransaction();
					TblConfigure issue = tblConfigureMapper.selectByType("contract_issue");
					TblConfigure balance = tblConfigureMapper.selectByType("contract_balance");
					issue.setConfValue(String.valueOf(Integer.valueOf(issue.getConfValue().toString())+1));
					balance.setConfValue("0");
					tblConfigureMapper.updateByPrimaryKey(issue);
					tblConfigureMapper.updateByPrimaryKey(balance);
					break;
				}
				else
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};

				}
			}
		}
		
	}
	
	public void newVersionData(){
		TblConfigure configurce = tblConfigureMapper.selectByType("contract_id");
		TblConfigure phoneNum = tblConfigureMapper.selectByType("contract_owner_phone_num");
		List<Object> message = new ArrayList<Object>();
		message.add(configurce.getConfValue());
		message.add("t"+phoneNum.getConfValue());
		message.add("re_init");
		message.add("true");
		message.add("ALP");
		message.add("10");
		String resultMesg = socketService.send("call_contract", message);
		if (!"null".equals(resultMesg)  && null != resultMesg) {
			System.out.println("合约全清变化："+resultMesg);
			String trxId = StringUtil.transferNum(resultMesg);

			while(true)
			{
				List<Object> params = new ArrayList<>();
				params.add(trxId);
				String resMessage = socketService.send("get_result_trx_id", params);
				if (!"".equals(resMessage) && !"[]".equals(resMessage) && resMessage != null) {
					tblUserTransactionMapper.deleteAllTransaction();
					tblUserInfoMapper.clearUserInfo(phoneNum.getConfValue().toString());
					TblConfigure issue = tblConfigureMapper.selectByType("contract_issue");
					TblConfigure balance = tblConfigureMapper.selectByType("contract_balance");
					issue.setConfValue("1");
					balance.setConfValue("0");
					tblConfigureMapper.updateByPrimaryKey(issue);
					tblConfigureMapper.updateByPrimaryKey(balance);
					break;
				}
				else
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};

				}
			}
		}
	}
	public void autoSelectPayment(){
		List<Map<String,Object>> waitPayList = this.tblUserTransactionMapper.selectByExamine();
		if(waitPayList.size() == 0 )
		{
			return;
		}
		Random r1 = new Random();
		int payNum = r1.nextInt(waitPayList.size());
		String address = waitPayList.get(payNum).get("trxFromAddress").toString();
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
					
				} else {
					System.out.println("自动理赔插入数据库失败!");
				}
			}
		}
	}
	
}
