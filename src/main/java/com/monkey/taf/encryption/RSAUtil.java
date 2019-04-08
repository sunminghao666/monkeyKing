/**
 * @模块名：taf
 * @包名：com.tit.taf.encryption
 * @描述：RSAUtil.java
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日上午10:06:50
 */

package com.monkey.taf.encryption;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * @模块名：taf
 * @包名：com.tit.taf.encryption
 * @类名称： RSAUtil
 * @类描述：【类描述】
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日上午10:06:50
 */

public class RSAUtil {
    public static final String CHARSET = "UTF-8";

    public static final String RSA_ALGORITHM = "RSA";

    /**
     * 
     * @方法名：createKeys
     * @方法描述【方法功能描述】 生成加解密公钥和私钥
     * @param keySize 秘钥大小
     * @return 公钥：publicKey，私钥：privateKey
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:22:40
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:22:40
     */
    public static Map < String, String > createKeys(int keySize) {
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map < String, String > keyPairMap = new HashMap < String, String >();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 
     * @方法名：getPublicKey
     * @方法描述【方法功能描述】 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @return 公钥
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:30:05
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:30:05
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 
     * @方法名：getPrivateKey
     * @方法描述【方法功能描述】 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @return 得到私钥
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:30:31
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:30:31
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 
     * @方法名：publicEncrypt
     * @方法描述【方法功能描述】 公钥加密
     * @param data 明文
     * @param publicKey 公钥
     * @return 公钥加密密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:30:54
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:30:54
     */
    public static String publicEncrypt(String data, String publicKey) {
        try {
            RSAPublicKey rsaPublicKey = RSAUtil.getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
                    rsaPublicKey.getModulus().bitLength()));
        }
        catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 
     * @方法名：privateDecrypt
     * @方法描述【方法功能描述】私钥解密
     * @param data 公钥加密密文
     * @param privateKey 私钥
     * @return 私钥解密密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:31:52
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:31:52
     */
    public static String privateDecrypt(String data, String privateKey) {
        try {
            RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), rsaPrivateKey
                    .getModulus().bitLength()), CHARSET);
        }
        catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 
     * @方法名：privateEncrypt
     * @方法描述【方法功能描述】 私钥加密
     * @param data 明文
     * @param privateKey 私钥
     * @return 私钥加密密文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:32:41
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:32:41
     */
    public static String privateEncrypt(String data, String privateKey) {
        try {
            RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
                    rsaPrivateKey.getModulus().bitLength()));
        }
        catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 
     * @方法名：publicDecrypt
     * @方法描述【方法功能描述】 公钥解密
     * @param data 私钥加密密文
     * @param publicKey 公钥
     * @return 公钥解密明文
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:33:16
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:33:16
     */
    public static String publicDecrypt(String data, String publicKey) {
        try {
            RSAPublicKey rsaPublicKey = RSAUtil.getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), rsaPublicKey
                    .getModulus().bitLength()), CHARSET);
        }
        catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 
     * @方法名：rsaSplitCodec
     * @方法描述【方法功能描述】RSA加密算法对于加密数据的长度是有要求的。一般来说，明文长度小于等于密钥长度（Bytes）-11。解决这个问题需要对较长的明文进行分段加解密
     * @param cipher
     * @param opmode
     * @param datas
     * @param keySize
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月27日 上午10:37:59
     * @修改人：cc
     * @修改时间：2018年8月27日 上午10:37:59
     */
    @SuppressWarnings("deprecation")
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        }
        else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }
                else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }
        catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

    public static void main(String[] args) {
        try {
            Map < String, String > keyMap = RSAUtil.createKeys(1024);
            String publicKey = keyMap.get("publicKey");
            String privateKey = keyMap.get("privateKey");
            System.out.println("公钥:" + publicKey);
            System.out.println("私钥" + privateKey);

            String str = "cc";
            System.out.println("公钥加密——私钥解密");
            String encodedData = RSAUtil.publicEncrypt(str, publicKey);
            System.out.println("密文：" + encodedData);
            String decodedData = RSAUtil.privateDecrypt(encodedData, privateKey);
            System.out.println("解密后文字: " + decodedData);

            System.out.println("私钥加密——公钥解密");
            String encodedData1 = RSAUtil.privateEncrypt(str, privateKey);
            System.out.println("密文：" + encodedData1);
            String decodedData1 = RSAUtil.publicDecrypt(encodedData1, publicKey);
            System.out.println("解密后文字: " + decodedData1);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
