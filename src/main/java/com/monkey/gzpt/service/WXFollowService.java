/**
 * @模块名称：微信公众号绑定与解绑接口
 * @Description
 * @version：1.0
 * @author:zq
 * @date：2018年3月26日 上午10:15:05
 */
package com.monkey.gzpt.service;

import com.monkey.taf.web.util.BasicDto;
import com.monkey.taf.web.util.Status;

/**
 * @模块名称：微信公众号绑定与解绑接口
 * @Description
 * @version：1.0
 * @author：zq
 * @date：2018年3月26日 上午10:15:05
 */
public interface WXFollowService {

    /**
     * @模块名称：用户公众号绑定查询
     * @Description
     * @version：1.0
     * @author：zq
     * @date：2018年3月26日 上午10:15:05
     */
    void selectAttention(Status status, BasicDto basicDto);
    
    /**
     * @模块名称：用户公众号绑定
     * @Description
     * @version：1.0
     * @author：zq
     * @date：2018年3月26日 下午4:15:05
     */
    void attention(Status status, BasicDto basicDto);
    
    /**
     * @模块名称：用户公众号解绑
     * @Description
     * @version：1.0
     * @author：zq
     * @date：2018年3月27日 上午9:15:05
     */
    void relieveAttention(Status status, BasicDto basicDto);
}
