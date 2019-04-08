package com.monkey.taf.encryption;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.encryption
 * @类名称： Base64Util
 * @类描述：【类描述】base64编码及解码
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年7月27日下午3:31:25
 */
public class Base64Util {
    /**
     * 编码UTF_8
     */
    public static final String CODING_UTF_8 = "UTF-8";

    /**
     * 编码GB2312
     */
    public static final String CODING_GB2312 = "GB2312";

    /**
     * 
     * @方法名：encode
     * @方法描述【方法功能描述】base64编码并指定文本编码
     * @param s 待编码字符串
     * @param charset 字符串编码
     * @return 编码后字符串
     * @throws UnsupportedEncodingException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午3:36:45
     * @修改人：cc
     * @修改时间：2018年7月27日 下午3:36:45
     */
    public static String encode(String s, String charset) throws UnsupportedEncodingException {
        return new String(Base64.encodeBase64(s.getBytes(charset)));
    }

    /**
     * 
     * @方法名：encode
     * @方法描述【方法功能描述】base64编码
     * @param s 待编码字符串
     * @return 编码后字符串
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午3:39:16
     * @修改人：cc
     * @修改时间：2018年7月27日 下午3:39:16
     */
    public static String encode(String s) {
        return new String(Base64.encodeBase64(s.getBytes()));
    }

    /**
     * 
     * @方法名：encode
     * @方法描述【方法功能描述】
     * @param bytes
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年11月1日 上午10:05:09
     * @修改人：cc
     * @修改时间：2018年11月1日 上午10:05:09
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * 
     * @方法名：decode
     * @方法描述【方法功能描述】base64解码
     * @param s 待解码字符串
     * @return 解码后字符串
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午3:39:46
     * @修改人：cc
     * @修改时间：2018年7月27日 下午3:39:46
     */
    public static String decode(String s) {
        return new String(Base64.decodeBase64(s.getBytes()));
    }

    /**
     * 
     * @方法名：decode
     * @方法描述【方法功能描述】 base64解码
     * @param bytes 字符编码数组
     * @return 解码后字符串
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年11月1日 上午9:59:44
     * @修改人：cc
     * @修改时间：2018年11月1日 上午9:59:44
     */
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    public static void main(String[] args) {
        try {
            System.out.println(Base64Util.encode("cc", Base64Util.CODING_UTF_8));
            System.out.println(Base64Util.decode("Y2M="));
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
}
