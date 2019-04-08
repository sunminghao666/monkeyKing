package com.monkey.taf.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.jni.Status;

import com.monkey.taf.log.TAFLog;
import com.monkey.taf.web.util.BasicDto;
import com.monkey.taf.web.util.Pager;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.common
 * @类名称： Tools
 * @类描述：【类描述】常用工具类
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年9月19日上午9:05:47
 */
public class Tools {

    /**
     * 默认时间格式<br/>
     * yyyy-MM-dd
     */
    public final static String DEFAULTFORMAT = "yyyy-MM-dd";

    /**
     * 完整日期格式
     */
    public final static String FULLFORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认的小数格式
     */
    public final static String DECIMALFORMAT = "0.00";

    /**
     * 
     * @方法名：isBlank
     * @方法描述【方法功能描述】校验对象是否为空
     * @param str 判断对象
     * @return 为空返回true；不为空返回false
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:06:09
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:06:09
     */
    public static boolean isBlank(Object str) {
        if (str == null || "".equals(str.toString().trim()))
            return true;
        else
            return false;
    }

    /**
     * 
     * @方法名：isNotBlank
     * @方法描述【方法功能描述】校验对象是否不为空
     * @param str 判断对象
     * @return 为空返回false；不为空返回true
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:06:53
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:06:53
     */
    public static boolean isNotBlank(Object str) {
        if (str == null || "".equals(str.toString().trim()))
            return false;
        else
            return true;
    }

    /**
     * 
     * @方法名：isBlank
     * @方法描述【方法功能描述】判断字符串是否为空
     * @param str 字符串
     * @return 为空返回true；不为空返回false
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:09:09
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:09:09
     */
    public static boolean isBlank(final String str) {
        return (str == null) || (str.trim().length() <= 0);
    }

    /**
     * 
     * @方法名：isBlank
     * @方法描述【方法功能描述】判断数组是否为空
     * @param objs 数组
     * @return 为空返回true；不为空返回false
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:09:45
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:09:45
     */
    public static boolean isBlank(final Object[] objs) {
        return (objs == null) || (objs.length <= 0);
    }

    /**
     * 
     * @方法名：isBlank
     * @方法描述【方法功能描述】判断集合是否为空
     * @param obj Collection
     * @return 为空返回true；不为空返回false
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:10:11
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:10:11
     */
    public static boolean isBlank(final Collection < ? > obj) {
        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * 
     * @方法名：isBlank
     * @方法描述【方法功能描述】判断Set是否为空
     * @param obj Set
     * @return 为空返回true；不为空返回false
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:10:11
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:10:11
     */
    public static boolean isBlank(final Set < ? > obj) {
        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * 
     * @方法名：isBlank
     * @方法描述【方法功能描述】判断Serializable是否为空
     * @param obj Serializable
     * @return 为空返回true；不为空返回false
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:10:11
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:10:11
     */
    public static boolean isBlank(final Serializable obj) {
        return obj == null;
    }

    /**
     * 
     * @方法名：isBlank
     * @方法描述【方法功能描述】判断map是否为空
     * @param obj map
     * @return 为空返回true；不为空返回false
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:10:11
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:10:11
     */
    public static boolean isBlank(final Map < ?, ? > obj) {
        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * 
     * @方法名：toStr
     * @方法描述【方法功能描述】将对象转换为字符串
     * @param obj 对象
     * @return 字符串
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:12:28
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:12:28
     */
    public static String toStr(Object obj) {
        if (obj == null) {
            return null;
        }
        else {
            return obj.toString();
        }
    }

    /*******************************************************************************************************************
     * 日期类方法
     */
    /**
     * 
     * @方法名：isNumDate
     * @方法描述【方法功能描述】判断输入是否是数字 日期格式为：2011-02-14
     * @param str 字符串
     * @return boolean
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:13:17
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:13:17
     */
    public static boolean isNumDate(String str) {
        boolean flag = false;
        Pattern pattern = Pattern.compile("^\\d+$");
        if (isBlank(str)) {
            return flag;
        }
        else {
            Matcher m = pattern.matcher(str);
            if (m.matches()) {
                flag = true;
            }
            else {
                flag = false;
            }
            return flag;
        }
    }

    /**
     * 
     * @方法名：converDate
     * @方法描述【方法功能描述】将指定格式yyyy-MM-dd的字符串转换为date
     * @param destStr yyyy-MM-dd格式字符串
     * @return 转换后日期
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月19日 上午9:14:58
     * @修改人：cc
     * @修改时间：2018年9月19日 上午9:14:58
     */
    public static Date converDate(String destStr) throws Exception {
        if (Tools.isBlank(destStr)) {
            return null;
        }
        SimpleDateFormat dFormat = new SimpleDateFormat(Tools.DEFAULTFORMAT);
        return dFormat.parse(destStr);
    }

    /**
     * 描述：获取指定日期一个月后的日期
     * <p>
     * 日期：2011-02-12
     */
    public static Date getAddOneMonthDate(String destStr) throws Exception {
        try {
            if (Tools.isBlank(destStr)) {
                return null;
            }
            SimpleDateFormat dFormat = new SimpleDateFormat(Tools.DEFAULTFORMAT);
            Date date = dFormat.parse(destStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MONTH, 1); // 将当前日期加一个月
            // String validityDate = dFormat.format(c.getTime()); //返回String型的时间
            return c.getTime();
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 描述：获取指定日期前一个月的日期
     * <p>
     * 日期：2011-02-12
     */
    public static Date getLastMonthDate(String destStr) throws Exception {
        try {
            if (Tools.isBlank(destStr)) {
                return null;
            }
            SimpleDateFormat dFormat = new SimpleDateFormat(Tools.DEFAULTFORMAT);
            Date date = dFormat.parse(destStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MONTH, -1); // 将当前日期减去一个月
            // String validityDate = dFormat.format(c.getTime()); //返回String型的时间
            return c.getTime();
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 描述：把字符串转换成指定格式的日期
     * <p>
     * 日期：2011-02-12
     */
    public static Date converDate(String destStr, String format) throws Exception {
        try {
            if (Tools.isBlank(destStr)) {
                return null;
            }
            SimpleDateFormat dFormat = new SimpleDateFormat(format);
            return dFormat.parse(destStr);
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * 
     * @param timestampString 时间戳 如："1500998399";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2017-07-25 23:59:59";
     */
    public static String TimeStamp2Date(String timestampString) {
        if (timestampString == null || timestampString.equals("0")) {
            return null;
        }
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(FULLFORMAT, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 描述：把日期转换成指定格式的日期
     * <p>
     * 日期：2011-02-12
     */
    public static Date converDate(Date destDate, String format) throws Exception {
        try {
            if (destDate == null) {
                return null;
            }
            SimpleDateFormat dFormat = new SimpleDateFormat(format);
            String tmp = dFormat.format(destDate);
            return Tools.converDate(tmp, format);
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 描述：把日期转换成指定格式的日期
     * <p>
     * 日期：2011-02-12
     */
    public static String converToString(Date destDate, String format) throws Exception {
        try {
            if (destDate == null) {
                return null;
            }
            SimpleDateFormat dFormat = new SimpleDateFormat(format);
            String tmp = dFormat.format(destDate);
            return tmp;
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 描述：把日期格式字符串转换成指定格式的字符串
     * <p>
     * 日期：2011-02-12
     * 
     * @param destStr 需要转换的日期格式的字符串
     * @param destStrFormat 需要转换的字符串的格式
     * @param toFormat 要转换成的格式
     */
    public static String converToString(String destStr, String destStrFormat, String toFormat) throws Exception {
        try {
            if (isBlank(destStr)) {
                return null;
            }
            SimpleDateFormat dFormat = new SimpleDateFormat(destStrFormat);
            Date tmp = dFormat.parse(destStr);
            dFormat = new SimpleDateFormat(toFormat);
            return dFormat.format(tmp);
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 描述：把字符串转换成指定编码格式
     * <p>
     * 日期：2011-02-21
     * 
     * @return
     */
    public static String encodStr(String srcStr, String encoder) throws Exception {
        try {
            if (Tools.isBlank(srcStr)) {
                return srcStr;
            }
            return new String(srcStr.getBytes("iso-8859-1"), encoder);
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 描述：计算两个时间相差的月数
     * <p>
     * 参数：返回date1-date2相差的月数
     */
    public static int dateMonthDvalue(Date date1, Date date2) throws Exception {
        try {
            int n = 0;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(date1);
            c2.setTime(date2);
            while (c1.after(c2) || c1.equals(c2)) { // 循环对比，直到相等，n 就是所要的结果
                n++;
                c2.add(Calendar.MONTH, 1); // 比较月份，月份+1
            }

            n = n - 1;
            return n;
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
     * @param date2 被比较的时间 为空(null)则为当前时间
     * @param stype 返回值类型 0为多少天，1为多少个月，2为多少年
     * @return
     */

    public static int compareDate(String date1, String date2, int stype) {
        int n = 0;

        String[] u = { "天", "月", "年" };
        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

        try {
            date2 = date2 == null ? Tools.converToString(new Date(), "yyyy-MM-dd") : date2;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        }
        catch (Exception e3) {
            System.out.println("wrong occured");
        }
        // List list = new ArrayList();
        while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
            // list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
            }
            else {
                c1.add(Calendar.DATE, 1); // 比较天数，日期+1
            }
        }

        n = n - 1;

        if (stype == 2) {
            n = (int) n / 365;
        }

        System.out.println(date1 + " -- " + date2 + " 相差多少" + u[stype] + ":" + n);
        return n;
    }

    /**
     * 描述：计算两个时间相差的天数
     * <p>
     * 参数：返回date1-date2相差的天数
     */
    public static Long dateDvalue(Date date1, Date date2) throws Exception {
        try {
            Long result = converDate(date1, "yyyy-MM-dd").getTime() - converDate(date2, "yyyy-MM-dd").getTime();
            if (result < 0) {
                return 0L;
            }
            else {
                return result / 86400000;
            }
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 描述：计算两个时间相差的毫秒数
     * <p>
     * 参数：返回date1-date2相差的毫秒数
     */
    public static Long dateMillisecond(Date date1, Date date2) throws Exception {
        try {
            Long result = date1.getTime() - date2.getTime();
            return result;
        }
        catch (Exception e) {
            throw e;
        }
    }

    /*****************************************************************************************************
     * 数据结构操作类
     */
    /**
     * 描述：把数组转换成字符串，使用指定符号分隔
     * 
     * @param src 要转换的数组
     * @param arg 拼接数组元素的分隔符
     * @return
     */
    public static String arrayToString(String[] src, String arg) {
        if (src == null || src.length <= 0)
            return null;
        StringBuilder builder = new StringBuilder();
        for (String str : src) {
            if (isBlank(str))
                continue;
            builder.append("'" + str + "'").append(arg);
        }
        if (builder != null && builder.length() > 0)
            builder = builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * 描述：把数组转换，使用指定符号分隔
     * 
     * @param src 要转换的数组
     * @param arg 拼接数组元素的分隔符
     * @return
     */
    public static String arrayToNumber(Object[] src, String arg) {
        if (src == null || src.length <= 0)
            return null;
        StringBuilder builder = new StringBuilder();
        for (Object str : src) {
            if (isBlank(str))
                continue;
            builder.append(str).append(arg);
        }
        if (builder != null && builder.length() > 0)
            builder = builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * 描述：把集合转换成字符串，使用指定符号分隔
     * 
     * @param src 要转换的数组
     * @param arg 拼接数组元素的分隔符
     * @return
     */
    public static String arrayToString(List < ? > src, String arg) {
        if (src == null || src.size() <= 0)
            return null;
        StringBuilder builder = new StringBuilder();
        for (Object str : src) {
            if (isBlank(str))
                continue;
            builder.append("'" + str.toString() + "'").append(arg);
        }
        if (builder != null && builder.length() > 0)
            builder = builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * 根据输入的格式返回当前时间的字符串格式
     * 
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat dFormat = new SimpleDateFormat(format);
        return dFormat.format(new Date());
    }

    public static Calendar getCurrentDate() {
        return Calendar.getInstance();
    }

    public static Calendar getCurrentCalendar(String format) throws Exception {
        Calendar nowdate = Calendar.getInstance();
        try {
            nowdate.setTime(Tools.converDate(new Date(), format));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return nowdate;

    }

    public static String getRandomStr() {
        try {
            Thread.sleep(40);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long times = getCurrentDate().getTimeInMillis();
        return times + "";
    }

    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     * 
     * @param s 原文件名
     * @return 重新编码后的文件名
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            }
            else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                }
                catch (Exception ex) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    // private final static byte[] buffer = new byte[1024];
    //
    // /**
    // * 向客户端输出文件流数据
    // *
    // * @param response 客户端响应
    // * @param inputStream 待输出到客户端的输入流
    // * @param contentType 输出内容mime类型
    // * @param filename 输出文件名称
    // * @since 2013-10-22
    // */
    // public static void downLoadFile(HttpServletResponse response, InputStream inputStream, String contentType,
    // String filename) {
    // if (inputStream == null)
    // return;
    // try {
    // if (!StringUtils.isBlank(contentType)) {
    // response.setContentType(contentType);
    // }
    // else {
    // response.setContentType("application");
    // }
    // response.setHeader("Content-Disposition", "attachment; filename="
    // + new String(filename.getBytes("GBK"), "ISO-8859-1"));
    //
    // int read = 0;
    // while ((read = inputStream.read(buffer)) > 0) {
    // response.getOutputStream().write(buffer, 0, read);
    // }
    // response.flushBuffer();
    // }
    // catch (Exception e) {
    // TAFLog.error("文件下载异常", e);
    // }
    // finally {
    // TAFIOUtils.closeStream(inputStream);
    // }
    // }
    //
    // /**
    // * 向客户端输出文件流数据
    // *
    // * @param response 客户端响应
    // * @param inputStream 待输出到客户端的输入流
    // * @param contentType 输出内容mime类型
    // * @param filename 输出文件名称
    // * @since 2013-10-22
    // */
    // public static void downLoadFile(HttpServletResponse response, File file, String filename) {
    // if (file == null)
    // return;
    // InputStream inputStream = null;
    // try {
    // FileDataSource ds = new FileDataSource(file);
    // response.setContentType(ds.getContentType());
    //
    // String outName = StringUtils.isBlank(filename) ? file.getName() : filename;
    //
    // response.setHeader("Content-Disposition", "attachment; filename="
    // + new String(outName.getBytes("GBK"), "ISO-8859-1"));
    //
    // inputStream = new FileInputStream(file);
    // int read = 0;
    // while ((read = inputStream.read(buffer)) > 0) {
    // response.getOutputStream().write(buffer, 0, read);
    // }
    // response.flushBuffer();
    // }
    // catch (Exception e) {
    // TAFLog.error("文件下载异常", e);
    // }
    // finally {
    // TAFIOUtils.closeStream(inputStream);
    // }
    // }

    // /**
    // * 向客户端输出流数据
    // *
    // * @param response 客户端响应
    // * @param file 待输出内容到客户端的文件
    // * @since 2013-10-22
    // */
    // public static void sendResourcesToClient(HttpServletResponse response, File file, String charset) {
    // if (file == null)
    // return;
    // InputStream inputStream = null;
    // try {
    // FileDataSource ds = new FileDataSource(file);
    // if (StringUtils.isNotBlank(charset))
    // response.setContentType(ds.getContentType() + ";charset=" + charset);
    // else
    // response.setContentType(ds.getContentType() + ";charset=UTF-8");
    //
    // inputStream = new FileInputStream(file);
    // int read = 0;
    // while ((read = inputStream.read(buffer)) > 0) {
    // response.getOutputStream().write(buffer, 0, read);
    // }
    // response.flushBuffer();
    // }
    // catch (Exception e) {
    // TAFLog.error("输出文件内容异常", e);
    // }
    // finally {
    // TAFIOUtils.closeStream(inputStream);
    // }
    // }

    public static void setHead(HttpServletResponse response, String contenttype, String filename) {
        response.setContentType(contenttype);
        String externalName = "";
        try {
            externalName = URLEncoder.encode(filename, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            TAFLog.error("", e);
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + externalName);
    }

    public static void setHead(HttpServletResponse response, String contenttype, String filename, String encoding) {
        response.setContentType(contenttype);
        String externalName = "";
        try {
            externalName = URLEncoder.encode(filename, encoding);
        }
        catch (UnsupportedEncodingException e) {
            TAFLog.error("", e);
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + externalName);
    }

    /**
     * 删除文件
     * 
     * @param localUrl
     * @param delParent 是否删除所有上级目录（直到上级目录下有文件为止则不删除）
     */
    public static void delFile(String localUrl, boolean delParent) {
        File file = new File(localUrl);
        if (!file.exists()) {
            return;
        }

        file.delete();

        file = file.getParentFile();
        if (file.isDirectory() && (file.list() == null || file.list().length <= 0)) {
            if (delParent) {
                delFile(file.getAbsolutePath(), delParent);
            }
        }
    }

    /**
     * 删除文件
     * 
     * @param localUrl
     * @param deep 如果上级目录为空则删除上级目录，deep表示往上删除几层
     */
    public static void delFile(String localUrl, int deep) {
        File file = new File(localUrl);
        if (!file.exists()) {
            return;
        }

        file.delete();
        if (deep <= 0)
            return;

        file = file.getParentFile();
        if (file.isDirectory() && (file.list() == null || file.list().length <= 0)) {
            delFile(file.getAbsolutePath(), deep - 1);
        }
    }

    public static double formatDecimat(Double value, String fromat) {
        if (value == null) {
            value = 0.0;
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern(fromat);
        return Double.valueOf(decimalFormat.format(value));
    }

    public static String formatDecimatToStr(Double value, String fromat) {
        if (value == null) {
            value = 0.0;
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern(fromat);
        return decimalFormat.format(value);
    }

    /**
     * 获取指定日期的1日0时0分0秒
     * 
     * @param date 需要处理的日期
     * @return 当月的1月1日
     * @since 2013-10-18
     */
    public static Date toFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取指定日期的1月1日0时0分0秒
     * 
     * @param date 需要处理的日期
     * @return 当月的1月1日
     * @since 2013-10-18
     */
    public static Date toFirstDateOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, 1);
        return c.getTime();
    }

    /**
     * 获取指定日期的00:00:00时间点
     * 
     * @param date 待获取时间点的时间
     * @return 某日期的00:00:00
     * @since 2013-11-11
     */
    public static Date startOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定日期的23:59:59时间点
     * 
     * @param date 待获取时间点的时间
     * @return 某日期的23:59:59
     * @since 2013-11-11
     */
    public static Date endOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 向集合中连续添加指定对象n次
     * 
     * @param c 待添加对象的集合
     * @param num 添加次数
     * @param obj 添加的对象
     * @since 2013-10-23
     */
    public static < T > void fillList(Collection < T > c, int num, T obj) {
        if (c == null)
            return;
        for (int x = 0; x < num; x++)
            c.add(obj);
    }

    /**
     * 向集合中连续添加指定类型的对象n次
     * 
     * @param c 待添加对象的集合
     * @param num 添加次数
     * @param clazz 对象类型
     * @since 2013-10-23
     */
    public static < T > void fillList(Collection < T > c, int num, Class < T > clazz) {
        if (c == null)
            return;
        for (int x = 0; x < num; x++) {
            try {
                c.add(clazz.newInstance());
            }
            catch (Exception e) {
                TAFLog.error("实例化对象失败", e);
                c.add(null);
            }
        }
    }

    /**
     * 获取Map中的Values，并存如Collection中
     * 
     * @param maps 待处理的map
     * @return map中value集合
     * @since 2013-10-24
     */
    public static < T > Collection < T > mapToCollection(Map < ?, T > maps) {
        if (maps == null || maps.size() == 0)
            return null;
        Collection < T > ts = new ArrayList < T >();
        for (T t : maps.values()) {
            ts.add(t);
        }
        return ts;
    }

    /**
     * 获取Map中的Values，并存如List中
     * 
     * @param maps 待处理的map
     * @return map中value集合
     * @since 2013-10-24
     */
    public static < T > List < T > mapToList(Map < ?, T > maps) {
        if (maps == null || maps.size() == 0)
            return null;
        List < T > ts = new ArrayList < T >();
        for (T t : maps.values()) {
            ts.add(t);
        }
        return ts;
    }

    /**
     * 传入一个object类型的参数，如果该参数不为null则该参数被转换成string输出，如果该参数为 null则返回空字符
     * 
     * @param str
     * @return String字符串
     */
    public static String cutNull(Object str) {
        if (str == null) {
            return "";
        }
        return String.valueOf(str);
    }

    // 前补补长度
    public static String makeLength(String value, int len) {
        String returnStr = value.trim();
        try {
            int i = returnStr.length();
            while (i < len) {
                returnStr = "0" + returnStr;
                i++;
            }
            return returnStr;
        }
        catch (Exception e) {
            return null;
        }
    }

    // 前补补长度
    public static String makeLengthSq(String value, int len) {
        String returnStr = value.trim();
        try {
            int i = returnStr.length();
            while (i < len) {
                returnStr = " " + returnStr;
                i++;
            }
            return returnStr;
        }
        catch (Exception e) {
            return null;
        }
    }

    // 前补补长度
    public static String makeLengthNull(String value, int len) {
        if (value == null) {
            value = "";
        }
        String returnStr = value.trim();
        try {
            int i = returnStr.length();
            while (i < len) {
                returnStr = "0" + returnStr;
                i++;
            }
            return returnStr;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 去除左边多余的空格。
     * 
     * @param value 待去左边空格的字符串
     * @return 去掉左边空格后的字符串
     * @since 0.6
     */
    public static String trimLeft(String value) {
        String result = value;
        if (result == null)
            return result;
        char ch[] = result.toCharArray();
        int index = -1;
        for (int i = 0; i < ch.length; i++) {
            if (Character.isWhitespace(ch[i])) {
                index = i;
            }
            else {
                break;
            }
        }
        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;
    }

    /**
     * 去除右边多余的空格。
     * 
     * @param value 待去右边空格的字符串
     * @return 去掉右边空格后的字符串
     * @since 0.6
     */
    public static String trimRight(String value) {
        String result = value;
        if (result == null)
            return result;
        char ch[] = result.toCharArray();
        int endIndex = -1;
        for (int i = ch.length - 1; i > -1; i--) {
            if (Character.isWhitespace(ch[i])) {
                endIndex = i;
            }
            else {
                break;
            }
        }
        if (endIndex != -1) {
            result = result.substring(0, endIndex);
        }
        return result;
    }

    public static Double converDouble(String str) throws Exception {
        if (!isBlank(str)) {
            return Double.parseDouble(str);
        }
        else {
            return null;
        }
    }

    /**
     * 
     * 生成随机数字字符串
     * 
     * @param len 字符串长度
     * @since 2015-2-26 下午2:16:21
     * @author ChenYing
     * @return
     */
    public static String createRandomCode(int len) {
        String code = "";
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            code += r.nextInt(10);
        }
        return code;
    }

    /**
     * 生成随机数字字母组合
     * 
     * @param len
     * @since 2016-10-10 下午2:16:21
     * @author ZhouKaiLei
     * @return
     */
    public static String getStringRandom(int len) {

        String val = "";
        Random random = new Random();

        // 参数length，表示生成几位随机数
        for (int i = 0; i < len; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                val += (char) (random.nextInt(26) + 97);
            }
            else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 
     * 从18位身份证中获取生日(yyyy-MM-dd)
     * 
     * @param
     * @since 2015年3月3日 下午8:05:37
     * @author panaidong
     * @return
     */
    public static String getbrithDayByIdCard(String IdCard) {
        String yue = "";
        String ri = "";
        String year = "";
        String brithday = null;
        try {
            if (IdCard.length() == 18) {
                year = IdCard.substring(6, 10);
                yue = IdCard.substring(10, 12);
                ri = IdCard.substring(12, 14);
            }
            else if (IdCard.length() == 15) {
                year = IdCard.substring(6, 10);
                yue = IdCard.substring(8, 10);
                ri = IdCard.substring(10, 12);
            }
            brithday = year + "-" + yue + "-" + ri;
        }
        catch (NumberFormatException e) {
            brithday = null;
        }

        return brithday;
    }

    /**
     * 
     * 从18位身份证中获取生日(yyyy-MM-dd)
     * 
     * @param
     * @since 2015年3月3日 下午8:05:37
     * @author panaidong
     * @return
     */
    public static String getSexByIdCard(String IdCard) {
        String sex = "";
        try {
            if (IdCard.length() == 18) {
                if (Integer.parseInt(IdCard.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
                    sex = "02";// 女
                }
                else {
                    sex = "01";// 男
                }
            }
            else if (IdCard.length() == 15) {
                String usex = IdCard.substring(14, 15);// 用户的性别
                if (Integer.parseInt(usex) % 2 == 0) {
                    sex = "02";// 女
                }
                else {
                    sex = "01";// 男
                }
            }
        }
        catch (NumberFormatException e) {
            sex = null;
        }

        return sex;
    }

    /**
     * 
     * 获取指定数量的不重复的随机整数
     * 
     * @param max 最大值（不包含）
     * @param size 随机数数量
     * @since 2015-3-12 下午8:02:08
     * @author ChenYing
     * @return
     */
    public static Set < Integer > getRandomInteger(int max, int size) {
        Set < Integer > set = new HashSet < Integer >();
        Random r = new Random();
        while (set.size() != size) {
            set.add(r.nextInt(max));
        }
        return set;
    }

    /**
     * 
     * 获取系统时间戳
     * 
     * @param
     * @since 2015-4-11 下午2:54:25
     * @author chenbaofeng
     * @return
     */
    public static String getCurrentTimeMillis() {
        String timeMillis = Long.toString(System.currentTimeMillis());
        return timeMillis;
    }

    /**
     * 
     * 返回17位的字符串
     * 
     * @param
     * @since 2015-4-27 下午4:44:12
     * @author ChengCheng
     * @return
     */
    public static String seveningDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = df.format(new Date());
        return time;
    }

    // public static Map<String, String> convertDicToMap(List<Dictionary> ls) {
    // Map<String, String> map = new HashMap<String, String>();
    // for (int i = 0; i < ls.size(); i++) {
    // Dictionary d = ls.get(i);
    // map.put(d.getDicCode(), d.getDicValue());
    // }
    // return map;
    // }

    public static String productPremium(String premium) {
        if (premium == null) {
            return null;
        }
        StringBuffer temp = new StringBuffer(premium);
        if (temp.toString().startsWith("￥")) {
            temp.deleteCharAt(0);
        }
        // if (temp.toString().endsWith("起")) {
        // temp.deleteCharAt(temp.length() - 1);
        // }
        return temp.toString();
    }

    /**
     * 
     * 计算据近多长时间
     * 
     * @param
     * @since 2015-8-3 下午3:43:24
     * @author cc
     * @return
     */
    public static String ckeckTimes(Date date) {
        Date now = new Date();
        long diff = now.getTime() - date.getTime();
        long days = diff / (1000 * 60);
        String flag = "";
        if (days <= 0) {
            flag = "";
        }
        else if (days < 1) {
            flag = "刚刚";
        }
        else if (days > 1 * 30) {
            flag = "半小时前";
        }
        else if (days > 1 * 60) {
            flag = days / 60 + "小时前";
        }
        else if (days > 1 * 60 * 24) {
            flag = days / (1 * 60 * 24) + "一天前";
        }
        else if (days > 1 * 60 * 24 * 30) {
            flag = days / (1 * 60 * 24 * 30) + "个月前";
        }
        else if (days > 1 * 60 * 24 * 365) {
            flag = days / (60 * 24 * 365) + "年前前";
        }
        else {
            flag = days / (60 * 24 * 365) + "年前前";
        }
        return flag;
    }

    /**
     * 
     * 实现输入一个string表达式然后输出计算的结果
     * 
     * @param
     * @since 2015-9-1 下午3:43:24
     * @author gouxiaojuan
     * @return
     */
    // public static int sizeyunsuan(String s) {
    // while (true) {
    // int first = 0;
    // int last = 0;
    // for (int i = 0; i < s.length(); i++) {
    // if (s.charAt(i) == '(')
    // first = i;
    // if (s.charAt(i) == ')') {
    // last = i;
    // break;
    // }
    // }
    // if (last == 0) {
    // System.out.println(yunsuanjibie(s));
    // return yunsuanjibie(s);
    // } else {
    // String s1 = s.substring(0, first);
    // String s2 = s.substring(first + 1, last);
    // String s3 = s.substring(last + 1, s.length());
    // s = s1 + yunsuanjibie(s2) + s3;
    // }
    // }
    // }
    //
    // public static int yunsuanjibie(String s) {
    // int r = 0;
    // int p = 0;
    // for (int i = 0; i < s.length(); i++) {
    // if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
    // || s.charAt(i) == '/') {
    // p++;
    // }
    // }
    // String k[] = new String[2 * p + 1];
    // int k1 = 0;
    // int first = 0;
    // for (int i = 0; i < s.length(); i++) {
    // if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
    // || s.charAt(i) == '/') {
    // k[k1] = s.substring(first, i);
    // k1++;
    // k[k1] = "" + s.charAt(i);
    // k1++;
    // first = i + 1;
    // }
    // }
    // k[k1] = s.substring(first, s.length());
    // int kp = p;
    // while (kp > 0) {
    // for (int i = 0; i < k.length; i++) {
    // if (k[i].equals("*") || k[i].equals("/")) {
    // int l;
    // for (l = i - 1; l > -1; l--) {
    // if (!(k[l].equals("p")))
    // break;
    // }
    // int q;
    // for (q = i + 1; q < k.length; q++) {
    // if (!(k[l].equals("p")))
    // break;
    // }
    // if (k[i].equals("*")) {
    // k[i] = ""
    // + (Integer.parseInt(k[l]) * Integer
    // .parseInt(k[q]));
    // k[l] = "p";
    // k[q] = "p";
    // kp--;
    // } else {
    // k[i] = ""
    // + (Integer.parseInt(k[l]) / Integer
    // .parseInt(k[q]));
    // k[l] = "p";
    // k[q] = "p";
    // kp--;
    // }
    // break;
    // }
    // }
    // for (int i = 0; i < 2 * p + 1; i++) {
    // if (k[i].equals("+") || k[i].equals("-")) {
    // int l;
    // for (l = i - 1; l > -1; l--) {
    // if (!(k[l].equals("p")))
    // break;
    // }
    // int q;
    // for (q = i + 1; q < k.length; q++) {
    // if (!(k[q].equals("p")))
    // break;
    // }
    // if (k[i].equals("+")) {
    // k[i] = ""
    // + (Integer.parseInt(k[l]) + Integer
    // .parseInt(k[q]));
    // k[l] = "p";
    // k[q] = "p";
    // kp--;
    // } else {
    // k[i] = ""
    // + (Integer.parseInt(k[l]) - Integer
    // .parseInt(k[q]));
    // k[l] = "p";
    // k[q] = "p";
    // kp--;
    // }
    // break;
    // }
    // }
    // for (int i = 0; i < k.length; i++) {
    // if (!(k[i].equals("p"))) {
    // r = Integer.parseInt(k[i]);
    // break;
    // }
    // }
    // }
    // return r;
    // }

    public static Double sizeyunsuan(String s) {
        while (true) {
            int first = 0;
            int last = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(')
                    first = i;
                if (s.charAt(i) == ')') {
                    last = i;
                    break;
                }
            }
            if (last == 0) {
                Double result = yunsuanjibie(s);
                System.out.println(result);
                return result;
            }
            else {
                String s1 = s.substring(0, first);
                String s2 = s.substring(first + 1, last);
                String s3 = s.substring(last + 1, s.length());
                s = s1 + yunsuanjibie(s2) + s3;
            }
        }
    }

    public static Double yunsuanjibie(String s) {
        Double r = 0.00;
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                p++;
            }
        }
        String k[] = new String[2 * p + 1];
        int k1 = 0;
        int first = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                k[k1] = s.substring(first, i);
                k1++;
                k[k1] = "" + s.charAt(i);
                k1++;
                first = i + 1;
            }
        }
        k[k1] = s.substring(first, s.length());
        int kp = p;
        while (kp > 0) {
            for (int i = 0; i < k.length; i++) {
                if (k[i].equals("*") || k[i].equals("/")) {
                    int l;
                    for (l = i - 1; l > -1; l--) {
                        if (!(k[l].equals("p")))
                            break;
                    }
                    int q;
                    for (q = i + 1; q < k.length; q++) {
                        if (!(k[l].equals("p")))
                            break;
                    }
                    if (k[i].equals("*")) {
                        k[i] = "" + (Double.valueOf(k[l]) * Double.valueOf(k[q]));
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    }
                    else {
                        k[i] = "" + (Double.valueOf(k[l]) / Double.valueOf(k[q]));
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    }
                    break;
                }
            }
            for (int i = 0; i < 2 * p + 1; i++) {
                if (k[i].equals("+") || k[i].equals("-")) {
                    int l;
                    for (l = i - 1; l > -1; l--) {
                        if (!(k[l].equals("p")))
                            break;
                    }
                    int q;
                    for (q = i + 1; q < k.length; q++) {
                        if (!(k[q].equals("p")))
                            break;
                    }
                    if (k[i].equals("+")) {
                        k[i] = "" + (Double.valueOf(k[l]) + Double.valueOf(k[q]));
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    }
                    else {
                        k[i] = "" + (Double.valueOf(k[l]) - Double.valueOf(k[q]));
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    }
                    break;
                }
            }
            for (int i = 0; i < k.length; i++) {
                if (!(k[i].equals("p"))) {
                    r = Double.valueOf(k[i]);
                    break;
                }
            }
        }
        return r;
    }

    /**
     * 把字符串的后n位用“*”号代替
     * 
     * @param str 要代替的字符串
     * @param n 代替的位数
     * @return
     */

    public static String replaceLastString(String str, int n) {
        String sub = "";
        sub = str.substring(0, str.length() - n);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb = sb.append("*");
        }
        sub += sb.toString();
        return sub;
    }

    /**
     * 把字符串指定位置字符用“*”号代替
     * 
     * @param str 要代替的字符串
     * @param startIndex 开始位置
     * @param n 代替的位数
     * @return
     */

    public static String replaceSubString(String str, int startIndex, int n) {
        String sub = "";
        String sub1 = str.substring(0, startIndex);
        String sub2 = str.substring(startIndex + n, str.length());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb = sb.append("*");
        }
        sub = sub1 + sb.toString() + sub2;
        return sub;
    }

    /**
     * 描述：把日期转换成指定格式的日期，并减去n年
     * <p>
     * param years 要减去的年数
     * 
     */
    public static String lastNyearString(int years) throws Exception {
        try {
            // 日历取得
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            // 当前年份取得
            int currentYear = cal.get(Calendar.YEAR);
            // 当前月份取得
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            // 当前日期取得
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);

            int newYear = currentYear - Integer.valueOf(years);
            String newMonth = String.valueOf(currentMonth);
            String newDay = String.valueOf(currentDay);
            if (newMonth.length() < 2) {
                newMonth = "0" + newMonth;
            }
            if (newDay.length() < 2) {
                newDay = "0" + newDay;
            }
            String dateStr = String.valueOf(newYear) + newMonth + newDay;
            return dateStr;
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 方法名：parseGender 详述：根据所传身份证号解析其性别 zhangqiang
     */
    public static String parseGender(String cid) {
        String gender = null;
        char c = cid.charAt(cid.length() - 2);
        int sex = Integer.parseInt(String.valueOf(c));
        if (sex % 2 == 0) {
            gender = "女";
        }
        else {
            gender = "男";
        }
        return gender;
    }

    /**
     * 校验规则： 1、将前面的身份证号码17位数分别乘以不同的系数。第i位对应的数为[2^(18-i)]mod11。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 ；
     * 2、将这17位数字和系数相乘的结果相加； 3、用加出来和除以11，看余数是多少？； 4、余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4
     * 3 2；
     * 
     * @return 返回false说明，身份证号码不符合规则，返回true说明身份证号码符合规则 zhangqinag
     */
    public static boolean checkCardId(String cid) {
        boolean flag = false;
        int len = cid.length();
        int kx = 0;
        for (int i = 0; i < len - 1; i++) {
            int x = Integer.parseInt(String.valueOf(cid.charAt(i)));
            int k = 1;
            for (int j = 1; j < 18 - i; j++) {
                k *= 2;
            }
            kx += k * x;
        }
        int mod = kx % 11;
        int[] mods = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Character[] checkMods = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        for (int i = 0; i < 11; i++) {
            if (mod == mods[i]) {
                Character lastCode = cid.charAt(len - 1);
                if (checkMods[i].equals(lastCode)) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 方法名：parseAge 详述：根据身份证号码，返回年龄 zhangqinag
     */
    public static int parseAge(String cid) {
        int age = 0;
        String birthDayStr = cid.substring(6, 14);
        Date birthDay = null;
        try {
            birthDay = new SimpleDateFormat("yyyyMMdd").parse(birthDayStr);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("您还没有出生么？");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth && dayNow < dayBirth) {
                age--;
            }
        }
        else {
            age--;
        }
        return age;
    }

    /**
     * 方法名：parseBirthday 详述：根据身份证号截取出生日期 zhangqinag
     */
    public static String parseBirthday(String cid) {
        // 通过身份证号来读取出生日期
        String birthday = "";
        // 如果没有身份证，那么不进行字符串截取工作。
        if (checkCardId(cid)) {
            String year = cid.substring(6, 10);
            String month = cid.substring(10, 12);
            String day = cid.substring(12, 14);
            birthday = year + "-" + month + "-" + day;
        }
        return birthday;
    }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z" };

    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    /**
     * 获取传入时间的整点
     * 
     * @param date
     * @return
     */
    public static Date getHourTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取传入时间的上一整点
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getLastHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, date.getHours() - 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取传入时间的业务起始时间
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getLastDayStartHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (date.getHours() <= 15) {
            c.set(Calendar.DATE, date.getDate() - 1);
        }
        c.set(Calendar.HOUR_OF_DAY, 15);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获得统计的业务时间
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getYWDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (date.getHours() > 15) {
            c.set(Calendar.DATE, date.getDate() + 1);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获得当前日期的前一天日期
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getYesterday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, date.getDate() - 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获得当前日期的后一天日期
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getNextday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, date.getDate() + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获得当前日期的后一天日期
     * 
     * @param date
     * @return
     */
    public static Date getNextYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return Tools.endOfDay(c.getTime());
    }

    /**
     * 根据保险起期 获得 1年期产品 保险止期
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getEndDateByOne(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, date.getDate() - 1);
        c.add(Calendar.YEAR, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return Tools.endOfDay(c.getTime());
    }

    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            }
            else {
                age--;
            }
        }
        return age;
    }

    /**
     * 
     * 判断手机号格式
     * 
     * @param
     * @since 2017-11-21 下午4:44:12
     * @author zq
     * @return
     */
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^\\d{11}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 
     * 判断登录密码格式
     * 
     * @param
     * @since 2017-11-29 下午4:44:12
     * @author zq
     * @return
     */
    public static boolean isPassWordNO(String Password) {

        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");

        Matcher m = p.matcher(Password);

        return m.matches();
    }

    /**
     * 
     * 判断交易密码格式
     * 
     * @param
     * @since 2017-11-21 下午4:44:12
     * @author zq
     * @return
     */
    public static boolean isPayPassWordNO(String payPassword) {

        Pattern p = Pattern.compile("^([0-9]){6}$");

        Matcher m = p.matcher(payPassword);

        return m.matches();
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     * 
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i = 0; i < cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    /**
     * 
     * 判断邮箱格式
     * 
     * @param
     * @since 2017-11-28 下午4:44:12
     * @author zq
     * @return
     */
    public static boolean checkEmaile(String email) {

        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 判断是否为正整数
     * 
     * @param bot
     * @return 【修改描述】
     * @version：1.0
     * @author：Hades
     * @date：2017年11月28日 下午2:50:53
     */
    public static boolean isMatches(String bot) {
        boolean flag = false;
        try {
            String regex = "^[1-9]+[0-9]*$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(bot);
            if (m.find()) {
                flag = true;
            }
            else {
                flag = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String valueOf(Object object) {
        if (object == null) {
            return null;
        }
        else {
            return object.toString() + "";
        }
    }

    public static boolean isBlanks(Object str) {
        if (str == null || "".equals(str.toString().trim()) || "null".equals(str.toString().trim()))
            return true;
        else
            return false;
    }

    public static void way(String content, String projectId) throws Exception {
        InputStream inputStream;// 接收字节输入流
        InputStreamReader inputStreamReader;// 将字节输入流转换成字符输入流
        BufferedReader bufferedReader;// 为字符输入流加缓冲
        FileOutputStream fileOutputStream;// 字节输出流
        OutputStreamWriter outputStreamWriter;// 将字节输出流转换成字符输出流
        inputStream = getStringStream(content);
        inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        bufferedReader = new BufferedReader(inputStreamReader);
        String s;
        File dest = new File("D://kf/zs/url/" + "App_" + projectId + ".html");
        System.out.println(dest);
        fileOutputStream = new FileOutputStream(dest);
        outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
        while ((s = bufferedReader.readLine()) != null) {
            outputStreamWriter.write(s);
        }
        outputStreamWriter.close();
        fileOutputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }

    public static InputStream getStringStream(String sInputString) {
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 得到输入日的前几天的时间
     * 
     * @param d
     * @param day
     * @return 【修改描述】
     * @version：1.0
     * @author：Hades
     * @date：2018年1月9日 上午11:03:57
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到输入日的后几天的时间
     * 
     * @param d
     * @param day
     * @return 【修改描述】
     * @version：1.0
     * @author：Hades
     * @date：2018年1月9日 上午11:02:56
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static String getLocalHostIP() {
        try {
            for (Enumeration < NetworkInterface > nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements();) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isLoopback() || !ni.isUp())
                    continue;
                for (Enumeration < InetAddress > ias = ni.getInetAddresses(); ias.hasMoreElements();) {
                    InetAddress ia = ias.nextElement();
                    if (ia instanceof Inet6Address)
                        continue;
                    return ia.getHostAddress();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获得主机IP
     * 
     * @return String
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机ip地址，并自动区分Windows还是linux操作系统
     * 
     * @return String
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }
            // 如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration < NetworkInterface > netInterfaces = (Enumeration < NetworkInterface >) NetworkInterface
                        .getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    // 遍历所有ip
                    Enumeration < InetAddress > ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = (InetAddress) ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                                && ip.getHostAddress().indexOf(":") == -1) {
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }

    /**
     * 小赢理财订单状态转换
     * 
     * @param orderstat
     * @return 【修改描述】
     * @version：1.0
     * @author：hades
     * @date：2017年11月27日 下午6:39:35
     */
    public static String OrderStatusToBaoying(String orderstat, String subOrderStatus) {
        String orderStatus = null;
        if (orderstat.equals("49")) { // 投资处理中
            if (Tools.isNotBlank(subOrderStatus)) {
                if ("71".equals(subOrderStatus)) {
                    orderStatus = "03"; // 投资中
                }
                else {
                    orderStatus = "01";// 正在支付
                }
            }
            else {
                orderStatus = "01"; // 正在支付
            }
        }
        else if (orderstat.equals("72")) { // 投资失败
            orderStatus = "06"; // 投资失败
        }
        else if (orderstat.equals("100")) { // 投资成功，回款中
            if ("123".equals(subOrderStatus)) {
                orderStatus = "09"; // 提前还款
            }
            else {
                orderStatus = "07"; // 计息中
            }
        }
        else if (orderstat.equals("120")) { // 投资成功，回款中
            orderStatus = "05"; // 回款结算中
        }
        else if (orderstat.equals("121")) { // 正常逾期，待理赔
            orderStatus = "07"; // 计息中
        }
        else if (orderstat.equals("122")) { // 逾期，待补充身份证照片以完成理赔
            orderStatus = "07"; // 计息中
        }
        else if (orderstat.equals("123")) { // 加速到期
            orderStatus = "09"; // 提前还款
        }
        else if (orderstat.equals("130")) { // 回款到账(小赢账户)
            orderStatus = "02"; // 已结清
        }
        else if (orderstat.equals("131")) { // 回款到账（银行卡）
            if ("133".equals(subOrderStatus) || "135".equals(subOrderStatus)) {
                orderStatus = "09"; // 提前还款
            }
            else {
                orderStatus = "02"; // 已结清
            }
        }
        else if (orderstat.equals("132")) { // 回款提现失败
            orderStatus = "08"; // 回款提现失败
        }
        else if (orderstat.equals("133")) { // 提前还款
            orderStatus = "09"; // 提前还款
        }
        else if (orderstat.equals("134")) { // 理赔回款
            orderStatus = "09"; // 提前还款
        }
        else if (orderstat.equals("135")) { // 加速到期理赔回款||提前到期
            orderStatus = "09"; // 提前还款
        }
        else if (orderstat.equals("140")) { // 消费转让
            orderStatus = "02"; // 已结清
        }
        return orderStatus;
    }

    public static String repayPalnStatus(String xyReplanStatus) {
        String repayPalnStatus = null;
        if (Tools.isNotBlank(xyReplanStatus)) {
            if (xyReplanStatus.equals("100")) { // 投资成功，回款中
                repayPalnStatus = "01"; // 回款中
            }
            else if (xyReplanStatus.equals("120")) { // 投资成功，回款中
                repayPalnStatus = "01"; // 回款中
            }
            else if (xyReplanStatus.equals("121")) { // 逾期，待补充身份证照片以完成理赔
                repayPalnStatus = "05"; // 逾期，待补充身份证照片以完成理赔
            }
            else if (xyReplanStatus.equals("122")) { // 正常逾期，待理赔
                repayPalnStatus = "04"; // 正常逾期，待理赔
            }
            else if (xyReplanStatus.equals("123")) { // 加速到期
                repayPalnStatus = "01"; // 回款中
            }
            else if (xyReplanStatus.equals("130")) { // 回款到账(小赢账户)
                repayPalnStatus = "02"; // 回款到帐
            }
            else if (xyReplanStatus.equals("131")) { // 回款到账（银行卡）
                repayPalnStatus = "02"; // 回款到帐
            }
            else if (xyReplanStatus.equals("132")) { // 回款提现失败
                repayPalnStatus = "03"; // 回款失败
            }
            else if (xyReplanStatus.equals("133")) { // 提前还款
                repayPalnStatus = "02"; // 回款中
            }
            else if (xyReplanStatus.equals("134")) { // 理赔回款
                repayPalnStatus = "02"; // 回款到帐
            }
            else if (xyReplanStatus.equals("135")) { // 加速到期理赔回款||提前到期
                repayPalnStatus = "02"; // 回款到帐
            }
        }
        else {
            repayPalnStatus = "00"; // 未知状态
        }
        return repayPalnStatus;
    }

    /**
     * 理财订单状态
     * 
     * @param orderCode
     * @return 【修改描述】
     * @version：1.0
     * @author：Hades
     * @date：2017年12月1日 上午8:41:38
     */
    public static String orderConvert(String orderCode) {
        String remark = null;
        if (orderCode.equals("01")) {
            remark = "正在支付";
        }
        else if (orderCode.equals("02")) {
            remark = "已结清";
        }
        else if (orderCode.equals("03")) {
            remark = "投资中";
        }
        else if (orderCode.equals("04")) {
            remark = "支付失败";
        }
        else if (orderCode.equals("05")) {
            remark = "回款结算中";
        }
        else if (orderCode.equals("06")) {
            remark = "投资失败";
        }
        else if (orderCode.equals("07")) {
            remark = "计息中";
        }
        else if (orderCode.equals("08")) {
            remark = "回款提现失败";
        }
        else if (orderCode.equals("09")) {
            remark = "提前还款";
        }
        else {
            remark = "未知状态，请联系相关人员";
        }
        return remark;
    }

    public static String getWeek(Date date) {
        String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     * @throws IOException
     */
    public static String getImageStr(String imgFile) throws IOException {
        InputStream inputStream = null;
        byte[] data = null;
        inputStream = new FileInputStream(imgFile);
        data = new byte[inputStream.available()];
        inputStream.read(data);
        inputStream.close();
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 
     * 【方法功能描述】清除空及空字符串
     * 
     * @param map
     * @return
     * 
     *         【修改描述】
     * @version：1.0
     * @author：cc
     * @date：2018年7月23日 下午4:29:57
     */
    public static Map < String, Object > removeNullEntry(Map < String, Object > map) {
        if (map.isEmpty()) {
        }
        else {
            Set < String > set = map.keySet();
            Iterator < String > it = set.iterator();
            while (it.hasNext()) {
                Object key = it.next();
                if (Tools.isBlank(map.get(key))) {
                    map.remove(key);
                    set = map.keySet();
                    it = set.iterator();
                }
            }
        }

        return map;
    }

    /**
     * 
     * @方法名：pageParams
     * @方法描述【方法功能描述】初始化分页实体及参数
     * @param status
     * @param basicDto
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月26日 下午3:27:19
     * @修改人：cc
     * @修改时间：2018年7月26日 下午3:27:19
     */
    public static Pager < Object > pageParams(Status status, BasicDto basicDto) {
        if (Tools.isBlank(basicDto.getBody().get("pageSize")) || Tools.isBlank(basicDto.getBody().get("pageNo"))) {
            return null;
        }
        else if (Integer.parseInt(basicDto.getBody().get("pageSize") + "") <= 0) {
            return null;
        }
        else if (Integer.parseInt(basicDto.getBody().get("pageNo") + "") <= 0) {
            return null;
        }
        else {
            Pager < Object > pager = new Pager < Object >(Integer.parseInt(basicDto.getBody().get("pageSize") + ""),
                    Integer.parseInt(basicDto.getBody().get("pageNo") + ""));
            basicDto.getBody().put("firstEntityIndex", pager.getFirstEntityIndex());
            basicDto.getBody().put("lastEntityIndex", pager.getLastEntityIndex());
            return pager;
        }
    }

    public static void main(String[] args) {
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("a", 12);
        map.put("cc", null);
        map.put("ss", "");
        System.out.println(Tools.removeNullEntry(map));
    }
    // /**
    // *
    // * 汉字转换位汉语拼音首字母，英文字符不变
    // *
    // * @param chines 汉字
    // * @since 2015年3月2日 下午3:51:07
    // * @author panaidong
    // * @return 拼音
    // */
    // public static String converterToFirstSpell(String chines) {
    // String pinyinName = "";
    // char[] nameChar = chines.toCharArray();
    // HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    // defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    // defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    // for (int i = 0; i < nameChar.length; i++) {
    // if (nameChar[i] > 128) {
    // try {
    // pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
    // }
    // catch (BadHanyuPinyinOutputFormatCombination e) {
    // e.printStackTrace();
    // }
    // }
    // else {
    // pinyinName += nameChar[i];
    // }
    // }
    // return pinyinName;
    // }
    //
    // /**
    // *
    // * 汉字转换位汉语拼音，英文字符不变
    // *
    // * @param
    // * @since 2015年3月2日 下午3:53:07
    // * @author panaidong
    // * @return
    // */
    // public static String converterToSpell(String chines) {
    // String pinyinName = "";
    // char[] nameChar = chines.toCharArray();
    // HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    // defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    // defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    // for (int i = 0; i < nameChar.length; i++) {
    // if (nameChar[i] > 128) {
    // try {
    // if (PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat) != null) {
    // pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
    // }
    // else {
    // pinyinName += nameChar[i];
    // }
    // }
    // catch (BadHanyuPinyinOutputFormatCombination e) {
    // e.printStackTrace();
    // }
    // }
    // else {
    // pinyinName += nameChar[i];
    // }
    // }
    // return pinyinName;
    // }
    // /**
    // *
    // * 将imageData字符串保存成图像
    // *
    // * @param
    // * @since 2015-3-23 上午11:03:53
    // * @author ChenYing
    // * @return
    // */
    // public static void saveImg(String img, String path) throws IOException {
    // // String base64 = img;
    // img = img.replace("data:image/png;base64,", "");
    // img = img.replace("data:image/jpg;base64,", "");
    // img = img.replace("data:image/jpeg;base64,", "");
    // BASE64Decoder decoder = new BASE64Decoder();
    // byte[] imgData = decoder.decodeBuffer(img);
    // File file = new File(path);
    // // TAFLog.info("图片上传" + file.getName() + "路径" + path);
    // File parentDir = file.getParentFile();
    // if (!parentDir.exists()) {
    // parentDir.mkdirs();
    // }
    // if (!file.exists()) {
    // file.createNewFile();
    // }
    // FileOutputStream outputStream = new FileOutputStream(file);
    // outputStream.write(imgData);
    // outputStream.close();
    // }
}
