package com.monkey.gzpt.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.monkey.taf.log.TAFLog;

public class WXmutual {

	/**
	 * 访问微信公众号https
	 * 
	 * @param method
	 * @param url
	 * @param params
	 * @return
	 */
	public static String http(String method, String url,
			Map<String, String> params) {
		URL u = null;
		HttpURLConnection con = null;
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				try {
					sb.append(URLEncoder.encode(e.getValue(), "utf-8"));
				} catch (Exception e1) {
					TAFLog.error("访问微信异常", e1);
				}
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod(method);
			con.setConnectTimeout(120 * 1000);
			con.setReadTimeout(120 * 1000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 访问微信公众号https
	 * 
	 * @param method
	 * @param url
	 * @param params
	 * @return
	 */
	public static InputStream http_pic(String method, String url, Map<String, String> params) {
		URL u = null;
		HttpURLConnection con = null;
		// 图片输入流
		InputStream inStream = null;
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				try {
					sb.append(URLEncoder.encode(e.getValue(), "utf-8"));
				} catch (Exception e1) {
					TAFLog.error("访问微信异常", e1);
				}
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod(method);
			con.setConnectTimeout(120 * 1000);
			con.setReadTimeout(120 * 1000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
			inStream = con.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return inStream;
	}

	/**
	 * 访问微信公众号https
	 * 
	 * @param method
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpWXPost(String url, String params) {
		URL u = null;
		HttpURLConnection con = null;
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(120 * 1000);
			con.setReadTimeout(120 * 1000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(params);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * parseJSON2Map
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<Object, Object> parseJSON2Map(String jsonStr) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * 
	 * 计算两个日期之间相差的小时
	 * 
	 * @param
	 * @since 2016-05-3 上午11:04:50
	 * @author zhangqiang
	 * @return
	 */
	public static int daysBetween(Date beginDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_time = (time2 - time1) / (1000 * 3600);
		return Math.abs(Integer.parseInt(String.valueOf(between_time)));
	}
}
