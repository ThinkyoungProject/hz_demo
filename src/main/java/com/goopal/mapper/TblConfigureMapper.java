package com.goopal.mapper;

import com.goopal.entity.TblConfigure;

public interface TblConfigureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblConfigure record);

    int insertSelective(TblConfigure record);

    TblConfigure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblConfigure record);

    int updateByPrimaryKey(TblConfigure record);
    
    /**
     * 
    * @Title: selectByType 
    * @Description:根据配置表的类型查询配置表的值
    * @author David
    * @param 
    * @return TblConfigure 
    * @throws
     */
    TblConfigure selectByType(String confType);
}