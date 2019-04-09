package com.monkey.gzpt.service;

import com.monkey.taf.web.util.BasicDto;
import com.monkey.taf.web.util.Status;

public interface WXBasicService {
    /**
     * 
     * 【方法功能描述】进行静默授权
     * 
     * @param status
     * @throws Exception 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年3月26日 下午3:02:45
     */
    void authorize(Status status) throws Exception;

    /**
     * 
     * 【方法功能描述】进行模板推送
     * 
     * @param basicDto
     * @param status
     * @throws Exception 【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年3月26日 下午3:04:30
     */
    void templateSend(BasicDto basicDto, Status status) throws Exception;
    
    /**
     * 
     * 【方法功能描述】微信临时素材下载
     * @param basicDto
     * @param status
     * @throws Exception
     * 【修改描述】
     * @version：1.0
     * @author：liyaming
     * @date：2018年3月27日 下午3:10:33
     */
    void WXTemporaryMaterialDownload(BasicDto basicDto, Status status) throws Exception;
}
