package com.monkey.taf.web.util;

import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.web.util
 * @类名称： BasicDto
 * @类描述：【类描述】统一参数类型
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年9月19日上午9:39:38
 */
public class BasicDto {
    private Map < String, Object > head;

    private Map < String, Object > body;

    private JSONObject bodyJSONObject;

    private JSONObject headJSONObject;

    public Map < String, Object > getHead() {
        return head;
    }

    public void setHead(Map < String, Object > head) {
        this.head = head;
    }

    public Map < String, Object > getBody() {
        return body;
    }

    public void setBody(Map < String, Object > body) {
        this.body = body;
    }

    public JSONObject getBodyJSONObject() {
        return bodyJSONObject;
    }

    public void setBodyJSONObject(JSONObject bodyJSONObject) {
        this.bodyJSONObject = bodyJSONObject;
    }

    public JSONObject getHeadJSONObject() {
        return headJSONObject;
    }

    public void setHeadJSONObject(JSONObject headJSONObject) {
        this.headJSONObject = headJSONObject;
    }

    public BasicDto() {
        super();
    }
}
