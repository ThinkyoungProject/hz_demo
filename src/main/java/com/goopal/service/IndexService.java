package com.goopal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goopal.entity.TblConfigure;
import com.goopal.entity.TblUserInfo;
import com.goopal.entity.TblUserTransaction;
import java.util.concurrent.BlockingQueue;

public interface IndexService {
	
	
	/**
	 * 
	* @Title: in 
	* @Description: 进入互助计划首页
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	String main(Map<String,Object> params,HttpServletRequest request);
	
	/**
	 * 
	* @Title: join 
	* @Description:加入计划
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	TblUserInfo join(Map<String,Object> params,TblUserInfo user);

	/**
	 * 
	* @Title: confirm 
	* @Description:确认购买
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	String confirm(Map<String, Object> params,TblUserInfo user);
	
	/**
	 * 
	* @Title: checkPhone 
	* @Description:判断手机号是否已经注册过了
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	String checkPhone(Map<String,Object> params);
	
	/**
	 * 
	* @Title: selectByTransacPhone 
	* @Description:根据手机号查询交易记录
	* @author David
	* @param 
	* @return List<TblUserTransaction> 
	* @throws
	 */
	List<TblUserTransaction> selectByTransacPhone(Map<String,Object> params);
	
	/**
	 * 
	* @Title: selectByTrxState 
	* @Description:爱心数据展示
	* @author David
	* @param 
	* @return List<TblUserTransaction> 
	* @throws
	 */
	List<Map<String,Object>> selectByTrxState();
	
	/**
	 * 
	* @Title: selectByExamine 
	* @Description:查询审核赔付数据
	* @author David
	* @param 
	* @return List<Map<String,Object>> 
	* @throws
	 */
	List<Map<String,Object>> selectByExamine();
	
	/**
	 * 
	* @Title: selectPayment 
	* @Description:审核赔付
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	String selectPayment(Map<String, Object> params);
	
	/**
	 * 
	* @Title: checkByJoin 
	* @Description:校验用户是否已经参加了互动
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	String checkByJoin(TblUserInfo user);
	
	/**
	 * 
	* @Title: getBalance 
	* @Description:根据手机号码查询余额和区块链费用
	* @author David
	* @param 
	* @return Map<String,Object> 
	* @throws
	 */
	Map<String, Object> getBalance(TblUserInfo user);
	
	/**
	 * 
	* @Title: queryByPhone 
	* @Description:根据手机号码查询用户信息
	* @author David
	* @param 
	* @return TblUserInfo 
	* @throws
	 */
	TblUserInfo queryByPhone(String phone);
	
	/**
	 * 
	* @Title: audphone 
	* @Description:判断是否是审核人员
	* @author David
	* @param 
	* @return TblConfigure 
	* @throws
	 */
	TblConfigure audphone();
	
	/**
	 * 
	* @Title: audAddress 
	* @Description:获取合约地址
	* @author David
	* @param 
	* @return TblConfigure 
	* @throws
	 */
	TblConfigure audAddress();
	
	/**
	 * 
	* @Title: Issue 
	* @Description:获取合约期号
	* @author David
	* @param 
	* @return TblConfigure 
	* @throws
	 */
	TblConfigure Issue();
	
	/**
	 * 
	* @Title: mutualInfo 
	* @Description:展示互助信息统计信息
	* @author David
	* @param 
	* @return Map<String,Object> 
	* @throws
	 */
	Map<String, Object> mutualInfo();
	
	/**
	 * 
	* @Title: selectAllTrance 
	* @Description:查询所有的交易信息并关联手机号
	* @author David
	* @param 
	* @return List<Map<String,Object>> 
	* @throws
	 */
	List<Map<String, Object>> selectAllTrance();
	
	/**
	 * 
	* @Title: selectNoState 
	* @Description:查询合约审核人信息
	* @author David
	* @param 
	* @return TblUserInfo 
	* @throws
	 */
	TblUserInfo selectNoState(String phone);
	
	/**
	 * 
	* @Title: selectBytranToPhone 
	* @Description:根据手机号码查询该用户的赔付信息
	* @author David
	* @param 
	* @return List<Map<String,Object>> 
	* @throws
	 */
	List<Map<String, Object>> selectBytranToPhone(String phone);
	
	/**
	 * 
	* @Title: updateByTrxState 
	* @Description:根据Id更新trxState状态值
	* @author David
	* @param 
	* @return int 
	* @throws
	 */
	int updateByTrxState(Map<String, Object> params);
	
	/**
	 * 
	* @Title: selectByTrxStateByPhone 
	* @Description:
	* @author David
	* @param 
	* @return List<TblUserTransaction> 
	* @throws
	 */
	List<Map<String,Object>> selectByTrxStateByPhone(Map<String, Object> params);
	
	/**
	 * 
	* @Title: selectNumTrans 
	* @Description:统计赔付金额和赔付人数
	* @author David
	* @param 
	* @return Map<String,Object> 
	* @throws
	 */
	Map<String, Object> selectNumTrans();
	
}
