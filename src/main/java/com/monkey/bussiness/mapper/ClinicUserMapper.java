package com.monkey.bussiness.mapper;

import com.monkey.bussiness.dto.ClinicUser;
import com.monkey.bussiness.dto.ClinicUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClinicUserMapper {
    long countByExample(ClinicUserExample example);

    int deleteByExample(ClinicUserExample example);

    int insert(ClinicUser record);

    int insertSelective(ClinicUser record);

    List<ClinicUser> selectByExample(ClinicUserExample example);

    int updateByExampleSelective(@Param("record") ClinicUser record, @Param("example") ClinicUserExample example);

    int updateByExample(@Param("record") ClinicUser record, @Param("example") ClinicUserExample example);
}