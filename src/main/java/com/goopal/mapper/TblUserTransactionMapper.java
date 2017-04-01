package com.goopal.mapper;

import java.util.List;
import java.util.Map;

import com.goopal.entity.TblUserTransaction;

public interface TblUserTransactionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblUserTransaction record);

    int insertSelective(TblUserTransaction record);

    TblUserTransaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblUserTransaction record);

    int updateByPrimaryKey(TblUserTransaction record);
    
    /**
     * 
    * @Title: selectByTransferPhone 
    * @Description:根据手机号码查询交易记录
    * @author David
    * @param 
    * @return TblUserTransaction 
    * @throws
     */
    List<TblUserTransaction> selectByTransferPhone(Map<String,Object> params);
    
    /**
     * 
    * @Title: selectByTrxState 
    * @Description:查询赔付数据信息
    * @author David
    * @param 
    * @return List<TblUserTransaction> 
    * @throws
     */
    List<Map<String,Object>> selectByTrxState();
    
    /**
     * 
    * @Title: selectByTypePhone 
    * @Description: 根据手机号码,参保状态查询交易记录
    * @author David
    * @param 
    * @return TblUserTransaction 
    * @throws
     */
    TblUserTransaction selectByTypePhone(Map<String,Object> params);
    
    /**
     * 
    * @Title: selectBychainlocation 
    * @Description: 根据交易所在位置查询是否有较新的交易被确认
    * @author David
    * @param 
    * @return List<TblUserTransaction> 
    * @throws
     */
    List<TblUserTransaction> selectBychainlocation(Map<String,Object> params);
    
    /**
     * 
    * @Title: selectAllTrance 
    * @Description:查询所有的交易信息并关联手机号
    * @author David
    * @param 
    * @return List<Map<String,Object>> 
    * @throws
     */
    List<Map<String,Object>> selectAllTrance();
    
    /**
     * 
    * @Title: selectBytranToPhone 
    * @Description:根据手机号码查询该用户的赔付信息
    * @author David
    * @param 
    * @return List<Map<String,Object>> 
    * @throws
     */
    List<Map<String,Object>> selectBytranToPhone(String phone);
    
    /**
     * 
    * @Title: updateByTrxState 
    * @Description:根据Id更新trxState状态值
    * @author David
    * @param 
    * @return int 
    * @throws
     */
    int updateByTrxState(Map<String,Object> params);
    
    /**
     * 
    * @Title: selectByTrxStateByPhone 
    * @Description:根据手机号码,订单确认状态查询用户参保记录
    * @author David
    * @param 
    * @return List<Map<String,Object>> 
    * @throws
     */
    List<Map<String,Object>> selectByTrxStateByPhone(Map<String,Object> params);
    
    /**
     * 
    * @Title: selectByExamine 
    * @Description:审核数据展示
    * @author David
    * @param 
    * @return List<Map<String,Object>> 
    * @throws
     */
    List<Map<String,Object>> selectByExamine();
    
    /**
     * 
    * @Title: selectNumTrans 
    * @Description:统计赔偿信息
    * @author David
    * @param 
    * @return Map<String,Object> 
    * @throws
     */
    Map<String,Object> selectNumTrans();
    
    /**
     * 
     * 
    * @Title: updateByFromAddress 
    * @Description:更新状态为3的数据，已获赔
    * @author David
    * @param 
    * @return int 
    * @throws
     */
    int updateByFromAddress(String address);
    
    /**
     * 
    * @Title: selectByInsured 
    * @Description:查询参保数据,用户状态是未确认
    * @author David
    * @param 
    * @return List<TblUserTransaction> 
    * @throws
     */
    List<TblUserTransaction> selectByInsured();
    
    /**
     * 
    * @Title: selectByPayment 
    * @Description:查询赔付数据,用户状态是未确认
    * @author David
    * @param 
    * @return List<TblUserTransaction> 
    * @throws
     */
    List<TblUserTransaction> selectByPayment();
    
    /**
     * 
    * @Title: selectByPayment 
    * @Description:删除交易表所有期号
    * @author David
    * @param 
    * @return List<TblUserTransaction> 
    * @throws
     */
    void deleteAllTransaction();
 }