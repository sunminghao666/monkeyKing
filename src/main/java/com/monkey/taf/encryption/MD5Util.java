package com.monkey.taf.encryption;

import java.security.MessageDigest;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.encryption
 * @类名称： MD5Util
 * @类描述：【类描述】MD5加密包括32大小写、16位大小写
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年7月27日下午2:11:44
 */
public class MD5Util {
    private static String m_algorithm = "MD5";

    /**
     * 
     * @方法名：encryption
     * @方法描述【方法功能描述】MD5加密32位小写
     * @param encryptStr 明文
     * @return 32位小写密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:15:35
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:15:35
     */
    public static String encryption(String encryptStr) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance(m_algorithm);
            md.update(encryptStr.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuilder builder = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    builder.append("0");
                builder.append(Integer.toHexString(i));
            }
            re_md5 = builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    /**
     * 
     * @方法名：ENCRYPTION
     * @方法描述【方法功能描述】MD5加密32位大写
     * @param encryptStr 明文
     * @return 32位大写密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:17:46
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:17:46
     */
    public static String ENCRYPTION(String encryptStr) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = encryptStr.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @方法名：encryption16
     * @方法描述【方法功能描述】MD5加密16位小写
     * @param encryptStr 明文
     * @return 16位小写密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:18:26
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:18:26
     */
    public static String encryption16(String encryptStr) {
        return encryption(encryptStr).substring(8, 24);
    }

    /**
     * 
     * @方法名：ENCRYPTION16
     * @方法描述【方法功能描述】 MD5加密16位大写
     * @param encryptStr 明文
     * @return 16位大写密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:19:17
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:19:17
     */
    public static String ENCRYPTION16(String encryptStr) {
        return ENCRYPTION(encryptStr).substring(8, 24);
    }

    public static void main(String[] args) {
        try {
            System.out.println(MD5Util.encryption("cc"));// e0323a9039add2978bf5b49550572c7c
            System.out.println(MD5Util.ENCRYPTION("cc"));// E0323A9039ADD2978BF5B49550572C7C
            System.out.println(MD5Util.encryption16("cc"));// 39add2978bf5b495
            System.out.println(MD5Util.ENCRYPTION16("cc"));// 39ADD2978BF5B495
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
