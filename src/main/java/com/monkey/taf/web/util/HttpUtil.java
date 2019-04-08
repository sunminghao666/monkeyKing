package com.monkey.taf.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import com.monkey.taf.log.TAFLog;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.web.util
 * @类名称： HttpUtil
 * @类描述：【类描述】http请求封装类
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年10月25日上午9:50:39
 */
public class HttpUtil {
    /**
     * 
     * @方法名：http
     * @方法描述【方法功能描述】http请求
     * @param url 请求地址
     * @param params 参数
     * @return 返回类型
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年10月25日 上午9:50:57
     * @修改人：cc
     * @修改时间：2018年10月25日 上午9:50:57
     */
    public static String http(String url, Map < String, String > params) throws Exception {
        URL u = null;
        HttpURLConnection con = null;

        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Entry < String, String > e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(e.getValue(), "utf-8"));
                }
                catch (UnsupportedEncodingException e1) {
                    TAFLog.error("拼装ejx参数太多", e1);
                }
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (con != null) {
                con.disconnect();
            }
        }

        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        return buffer.toString();
    }

    /**
     * 
     * @方法名：http
     * @方法描述【方法功能描述】http请求
     * @param url 请求地址
     * @param params 请求参数
     * @param end1 请求参数编码
     * @param end2 返回参数编码
     * @return 返回结果
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年10月25日 上午9:53:19
     * @修改人：cc
     * @修改时间：2018年10月25日 上午9:53:19
     */
    public static String http(String url, Map < String, String > params, String end1, String end2) throws Exception {
        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Entry < String, String > e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setConnectTimeout(30000);
            con.setReadTimeout(120000);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), end1);
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (con != null) {
                con.disconnect();
            }
        }

        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), end2));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        return buffer.toString();
    }

    /**
     * 
     * @方法名：http
     * @方法描述【方法功能描述】http请求
     * @param url 请求地址
     * @param params 请求参数
     * @return 返回结果
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年10月25日 上午9:54:49
     * @修改人：cc
     * @修改时间：2018年10月25日 上午9:54:49
     */
    public static String http(String url, String params) throws Exception {
        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setConnectTimeout(30000);
            con.setReadTimeout(120000);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (con != null) {
                con.disconnect();
            }
        }

        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        return buffer.toString();
    }

    /**
     * 
     * @方法名：http
     * @方法描述【方法功能描述】http请求
     * @param url 请求地址
     * @param postType 请求类型
     * @param params 请求参数
     * @return 返回结果
     * @throws Exception 
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年10月25日 上午9:55:55
     * @修改人：cc
     * @修改时间：2018年10月25日 上午9:55:55
     */
    public static String http(String url, String postType, Map < String, String > params) throws Exception {
        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Entry < String, String > e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod(postType);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (con != null) {
                con.disconnect();
            }
        }

        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        return buffer.toString();
    }
}
