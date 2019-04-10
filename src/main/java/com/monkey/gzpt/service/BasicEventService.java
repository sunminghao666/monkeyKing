package com.monkey.gzpt.service;

import net.sf.json.JSONObject;

/**
 * 针对微信事件推送进行不同的处理
 * 
 * @author cc
 * 
 */
public interface BasicEventService {

	/**
	 * 
	 * 客户点击“接入客服”按钮
	 * 
	 * @param
	 * @since 2016-12-21 下午17:34:50
	 * @author SunMinghao
	 * @return
	 */
	String clickToCustomerService(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 转客服
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String gotoKefu(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 关注
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String subscribe(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 取消关注
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String unsubscribe(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 扫描带参数二维码事件
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String scan(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 上报地理位置事件
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String location(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 自定义菜单事件
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String click(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 点击菜单跳转链接时的事件推送
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String view(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 扫码推事件的事件推送
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String scancodePush(JSONObject jsonObject, String backBaowen)
			throws Exception;

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件推送
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String scancodeWaitmsg(JSONObject jsonObject, String backBaowen)
			throws Exception;

	/**
	 * 弹出系统拍照发图的事件推送
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String picSysphoto(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * 弹出拍照或者相册发图的事件推送
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String picPhotoOrAlbum(JSONObject jsonObject, String backBaowen)
			throws Exception;

	/**
	 * 弹出微信相册发图器的事件推送
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String picWeixin(JSONObject jsonObject, String backBaowen) throws Exception;

	/**
	 * ：弹出地理位置选择器的事件推送
	 * 
	 * @param jsonObject
	 * @param backBaowen
	 * @throws Exception
	 */
	String location_select(JSONObject jsonObject, String backBaowen)
			throws Exception;

}
