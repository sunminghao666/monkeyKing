package com.monkey.gzpt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.monkey.gzpt.pojo.GzptSubscribeInfo;

public interface GzptSubscribeInfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(GzptSubscribeInfo record);

	int insertSelective(GzptSubscribeInfo record);

	GzptSubscribeInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(GzptSubscribeInfo record);

	int updateByPrimaryKey(GzptSubscribeInfo record);

	GzptSubscribeInfo selectByOpenId(@Param("openId") String openId);

	List<GzptSubscribeInfo> selectByCustomerId(
			@Param("customerId") Long customerId);

	GzptSubscribeInfo selectByCustomerIdAndSubscribe(
			@Param("customerId") Long customerId);

	/**
	 * 根据用户ID与openId查询
	 * 
	 * @param userName
	 * @return
	 * @version：1.0
	 * @author：zq
	 * @date：2018年3月27日 上午9:12:07
	 */
	GzptSubscribeInfo selectByCustomerIdAndOpenId(
			@Param("customerId") Long customerId, @Param("openId") String openId);

	/**
	 * 
	 * 【方法功能描述】根据用户id跟新信息
	 * 
	 * @param record
	 * @return 【修改描述】
	 * @version：1.0
	 * @author：liyaming
	 * @date：2018年3月29日 下午3:05:11
	 */
	int updateByCustomerId(GzptSubscribeInfo record);

	GzptSubscribeInfo selectByUserName(@Param("phone") String phone);
}