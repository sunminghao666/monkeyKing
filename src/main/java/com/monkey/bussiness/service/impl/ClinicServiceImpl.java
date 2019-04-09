/********************************************************
 * <p>Description: 优悦口腔_诊所模块Service实现层</p>
 * <p>Project: ClinicServiceImpl.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.basic.common.ResponseResult;
import com.monkey.bussiness.bean.respBean.CureProjectBean;
import com.monkey.bussiness.bean.respBean.SearchClinicBean;
import com.monkey.bussiness.convert.ClinicConvert;
import com.monkey.bussiness.dao.ClinicDao;
import com.monkey.bussiness.dao.CureProjectDao;
import com.monkey.bussiness.dto.Clinic;
import com.monkey.bussiness.dto.CureProject;
import com.monkey.bussiness.enums.MonkeyKingReturnEnum;
import com.monkey.bussiness.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService {

	/** log */
	private static final Logger logger = LoggerFactory.getLogger(ClinicServiceImpl.class);

	@Autowired
	private ClinicDao clinicDao;

	@Autowired
	private CureProjectDao cureProjectDao;

	/**
	  * 查询诊所列表
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public ResponseResult searchClinic(Long clinicId, String clinicName, Integer effectiveFlag) {

		ResponseResult respResult = new ResponseResult();

		// 查询诊所用map
		Map<Object, Object> returnsMap = new HashMap<Object, Object>();
		List<Clinic> list = null;

		// 返回标签用List
		List<SearchClinicBean> returnList = new ArrayList<SearchClinicBean>();

		try {

			// 查询诊所列表
			list = clinicDao.searchClinicForList(clinicId, clinicName, effectiveFlag);

			if (list != null) {

				for (Clinic dto : list) {

					SearchClinicBean bean = null;
					bean = ClinicConvert.clinicConvert(dto);
					returnList.add(bean);
				}
			}

			returnsMap.put("clinic", returnList);
			respResult.setReturns(returnsMap);

		}  catch (Exception e) {
			logger.error("查询诊所列表异常", "ClinicServiceImpl.searchClinic()", e);
			respResult.setRespCode(MonkeyKingReturnEnum.FAILED.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.FAILED.getMsg());
		}

		return respResult;
	}

	/**
	  * 查询治疗项目
	 * 
	 * @param
	 * @since 2019-04-07 10:00:00
	 * @author SunMinghao
	 * @return
	 */
	@Override
	public ResponseResult searchCureProject(Long cureProjectId, String cureProjectName, Integer effectiveFlag) {

		ResponseResult respResult = new ResponseResult();

		// 查询诊所用map
		Map<Object, Object> returnsMap = new HashMap<Object, Object>();
		List<CureProject> list = null;

		// 返回标签用List
		List<CureProjectBean> returnList = new ArrayList<CureProjectBean>();

		try {

			// 查询诊所列表
			list = cureProjectDao.searchCureProjectForList(cureProjectId, cureProjectName, effectiveFlag);

			if (list != null) {

				for (CureProject dto : list) {

					CureProjectBean bean = null;
					bean = ClinicConvert.cureProjectConvert(dto);
					returnList.add(bean);
				}
			}

			returnsMap.put("cureProject", returnList);
			respResult.setReturns(returnsMap);

		}  catch (Exception e) {
			logger.error("查询治疗项目异常", "ClinicServiceImpl.searchCureProject()", e);
			respResult.setRespCode(MonkeyKingReturnEnum.FAILED.getCode());
			respResult.setRespMsg(MonkeyKingReturnEnum.FAILED.getMsg());
		}

		return respResult;
	}

}

