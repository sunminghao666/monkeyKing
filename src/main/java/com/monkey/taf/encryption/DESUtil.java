package com.monkey.taf.encryption;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.encryption
 * @类名称： DESUtil
 * @类描述：【类描述】 DES是对称性加密里面常见一种，全称为Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法。密钥长度是64位(bit)，超过位数密钥被忽略。
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年7月27日下午3:47:46
 */
public class DESUtil {

    /**
     * 算法/模式/补码方式
     */
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    /**
     * 
     * @方法名：encode
     * @方法描述【方法功能描述】DES算法，加密
     * @param key data 待加密字符串
     * @param data 加密私钥，长度不能够小于8位
     * @param ivKey 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午3:58:47
     * @修改人：cc
     * @修改时间：2018年7月27日 下午3:58:47
     */
    public static String encode(String key, String data, String ivKey) throws Exception {
        return encode(key, data.getBytes(), ivKey);
    }

    /**
     * 
     * @方法名：decodeValue
     * @方法描述【方法功能描述】 DES算法，解密base64编码的密文
     * @param key 解密私钥，长度不能够小于8位
     * @param data base64编码的密文
     * @param ivKey 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 解密后的字符串
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午4:04:07
     * @修改人：cc
     * @修改时间：2018年7月27日 下午4:04:07
     */
    public static String decodeValue(String key, String data, String ivKey) throws Exception {
        byte[] datas;
        String value = null;
        datas = decode(key, Base64.decodeBase64(data), ivKey);
        value = new String(datas, "UTF-8");
        if (value.equals("")) {
            throw new Exception();
        }
        return value;
    }

    /**
     * 
     * @方法名：encode
     * @方法描述【方法功能描述】 DES算法，加密
     * @param key 加密私钥，长度不能够小于8位
     * @param data 待加密字节数组
     * @param ivKey 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午3:59:28
     * @修改人：cc
     * @修改时间：2018年7月27日 下午3:59:28
     */
    public static String encode(String key, byte[] data, String ivKey) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // key的长度不能够小于8位字节
        Key secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes());
        AlgorithmParameterSpec paramSpec = iv;
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
        byte[] bytes = cipher.doFinal(data);
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * 
     * @方法名：decode
     * @方法描述【方法功能描述】 DES算法，解密
     * @param key 解密私钥，长度不能够小于8位
     * @param data 待解密字节数组
     * @param ivKey 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 解密后的字节数组
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午4:03:36
     * @修改人：cc
     * @修改时间：2018年7月27日 下午4:03:36
     */
    public static byte[] decode(String key, byte[] data, String ivKey) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // key的长度不能够小于8位字节
        Key secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes());
        AlgorithmParameterSpec paramSpec = iv;
        cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(DESUtil.encode("t1714201", "cc", "t1714201"));// A6vgfIhyyVc=
        System.out.println(DESUtil.decodeValue("t1714201", "A6vgfIhyyVc=", "t1714201"));// cc
    }
}
