package com.goopal.mapper;

import java.util.List;
import java.util.Map;

import com.goopal.entity.TblUserInfo;

public interface TblUserInfoMapper {
	
    int deleteByPrimaryKey(String address);

    int insert(TblUserInfo record);

    int insertSelective(TblUserInfo record);

    TblUserInfo selectByPrimaryKey(String address);

    int updateByPrimaryKeySelective(TblUserInfo record);

    int updateByPrimaryKey(TblUserInfo record);
    
    /**
     * 
    * @Title: selectByPhone 
    * @Description:查询未使用的区快信息
    * @author David
    * @param 
    * @return List<Map<String,Object>> 
    * @throws
     */
    List<TblUserInfo> selectByStatus();
    
    /**
     * 
    * @Title: selectByPhone 
    * @Description:查询未使用的区快信息
    * @author David
    * @param 
    * @return List<Map<String,Object>> 
    * @throws
     */
    List<TblUserInfo> selectFirstData();
    
    /**
     * 
    * @Title: selectByPhone 
    * @Description:根据手机号码查询用户信息
    * @author David
    * @param 
    * @return TblUserInfo 
    * @throws
     */
    TblUserInfo selectByPhone(TblUserInfo user);
    
    /**
     * 
    * @Title: updateByPrivate 
    * @Description:用户绑定成功后绑定私钥更新账号状态位启用
    * @author David
    * @param 
    * @return int 
    * @throws
     */
    int updateByPrivate(TblUserInfo user);
    
    /**
     * 
    * @Title: queryByPhone 
    * @Description:根据手机号查询用户信息
    * @author David
    * @param 
    * @return TblUserInfo 
    * @throws
     */
    TblUserInfo queryByPhone(String phoneNum);
    
    /**
     * 
    * @Title: selectCountUser 
    * @Description:统计参与人员
    * @author David
    * @param 
    * @return int 
    * @throws
     */
    int selectCountUser();
    
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
    * @Title: clearUserInfo 
    * @Description: 清空用户表 
    * @author David
    * @param 
    * @return TblUserInfo 
    * @throws
     */
    void clearUserInfo(String phone_num);
}