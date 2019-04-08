package com.monkey.taf.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.encryption
 * @类名称： AESEncrypt aes加密
 * @类描述：【类描述】高级加密标准（英语：Advanced Encryption
 *                              Standard，缩写：AES），在密码学中又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用
 *                              。经过五年的甄选流程，高级加密标准由美国国家标准与技术研究院（NIST）于2001年11月26日发布于FIPS PUB
 *                              197，并在2002年5月26日成为有效的标准。2006年，高级加密标准已然成为对称密钥加密中最流行的算法之一。
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年7月27日下午2:19:59
 */
public class AESEncrypt {
    /**
     * 算法=AES
     */
    public static final String ALGORITHM_AES = "AES";

    /**
     * 算法/模式/补码方式=AES/CBC/PKCS5Padding
     */
    public static final String ALGORITHM_AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";

    /**
     * 
     * @方法名：aesEncrypt
     * @方法描述【方法功能描述】 AES加密(算法=AES)
     * @param pwd 加密密钥
     * @param strEncpt 即将被加密的字符串
     * @return 加密密文
     * @throws Exception 加密异常
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:41:59
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:41:59
     */
    public static String aesEncrypt(String pwd, String strEncpt) throws Exception {
        byte[] bytIn = strEncpt.getBytes("UTF8");
        SecretKeySpec skeySpec = new SecretKeySpec(pwd.getBytes("UTF8"), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] bytOut = cipher.doFinal(bytIn);
        String ecrOut = new sun.misc.BASE64Encoder().encode(bytOut);
        return ecrOut;
    }

    /**
     * 
     * @方法名：aesDencrypt
     * @方法描述【方法功能描述】 AES解密(算法=AES)
     * @param pwd 解密密钥
     * @param strDencpt 即将被解密的字符串
     * @return 解密密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:44:27
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:44:27
     */
    public static String aesDencrypt(String pwd, String strDencpt) throws Exception {
        byte[] bytIn;
        String ecrOut = "";
        bytIn = new sun.misc.BASE64Decoder().decodeBuffer(strDencpt);
        SecretKeySpec skeySpec = new SecretKeySpec(pwd.getBytes("UTF8"), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] bytOut = cipher.doFinal(bytIn);
        ecrOut = new String(bytOut, "UTF8");
        return ecrOut;
    }

    /**
     * 
     * @方法名：aesEncrypt
     * @方法描述【方法功能描述】 AES加密(算法/模式/补码方式=AES/CBC/PKCS5Padding)
     * @param pwd 加密密钥
     * @param strEncpt 即将被加密的字符串
     * @param ivKey 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 加密密文
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:46:01
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:46:01
     */
    public static String aesEncrypt(String pwd, String strEncpt, String ivKey) throws Exception {
        try {
            byte[] bytIn = strEncpt.getBytes("UTF8");
            SecretKeySpec skeySpec = new SecretKeySpec(pwd.getBytes("UTF8"), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES_CBC_PKCS5PADDING);// "算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] bytOut = cipher.doFinal(bytIn);
            String ecrOut = new sun.misc.BASE64Encoder().encode(bytOut);// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
            return ecrOut;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * 
     * @方法名：aesDencrypt
     * @方法描述【方法功能描述】 AES解密(算法/模式/补码方式=AES/CBC/PKCS5Padding)
     * @param pwd 解密密钥
     * @param strDencpt 即将被解密的字符串
     * @param ivKey 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 加密密文
     * @throws Exception
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:48:36
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:48:36
     */
    public static String aesDencrypt(String pwd, String strDencpt, String ivKey) throws Exception {
        byte[] bytIn;
        String ecrOut = "";
        bytIn = new sun.misc.BASE64Decoder().decodeBuffer(strDencpt);
        SecretKeySpec skeySpec = new SecretKeySpec(pwd.getBytes("UTF8"), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES_CBC_PKCS5PADDING);// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] bytOut = cipher.doFinal(bytIn);
        ecrOut = new String(bytOut, "UTF8");
        return ecrOut;
    }

    /**
     * 
     * @方法名：getKey
     * @方法描述【方法功能描述】随机生成秘钥
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:49:54
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:49:54
     */
    public static void getKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);// 要生成多少位，只需要修改这里即可128, 192或256
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = byteToHexString(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为" + s.length());
            System.out.println("二进制密钥的长度为" + s.length() * 4);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
    }

    /**
     * 
     * @方法名：getKeyByPass
     * @方法描述【方法功能描述】使用指定的字符串生成秘钥
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:50:35
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:50:35
     */
    public static void getKeyByPass() {
        // 生成秘钥
        String password = "IOS";
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            // kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
            // SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
            kg.init(256, new SecureRandom(password.getBytes()));
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = byteToHexString(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为" + s.length());
            System.out.println("二进制密钥的长度为" + s.length() * 4);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
    }

    /**
     * 
     * @方法名：byteToHexString
     * @方法描述【方法功能描述】 byte数组转化为16进制字符串
     * @param bytes byte数组
     * @return 16进制字符串
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月27日 下午2:50:54
     * @修改人：cc
     * @修改时间：2018年7月27日 下午2:50:54
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            }
            else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                }
                else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String str = "PIzGyqyyxU1AAZCS7/gl3w==";
            String str3 = AESEncrypt.aesDencrypt("t171420100302rsa", str, "t171420100302rsa");
            System.out.println(str3);// cc
            String str2 = AESEncrypt.aesEncrypt("t171420100302rsa", "cc", "t171420100302rsa");
            System.out.println(str2);// PIzGyqyyxU1AAZCS7/gl3w==
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}
