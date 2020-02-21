package util;

import nao.DFYZKey;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class RSAUtil {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        KeyFactory k = KeyFactory.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    public static void auth(String var0, String var1) {
        try {
            if(!DFYZKey.sign.equals(var0))
                throw new Exception("sign不正确！");
            String sign = sign(DFYZKey.sign, getPrivateKey(var1));
            if(!verify(DFYZKey.sign, getPublicKey(DFYZKey.publicKey), sign))
                throw new Exception("privateKey不正确！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }

    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
//            String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJHwsX7aEjcrmvYWF0kaRbCRRV3U1909eZEi+g9ETItkXwtUjkmU4AA9LFOjZsT5qGL/QDqJna8H45WykbToIX/Ruxg5njbVmLIImvwqTQ2l6FW8sl5pjUIpS69TRYnkVe+81E6yGHUpxf827UBli6M+njnW23UZCf7LrDW8n7FNAgMBAAECgYBF+5jyDpq7kBKAPXmvGYUftZoA8+cL9cXM/mvRVFwsX9Zf9Y1B5b1BABS4itvmSx4fUu225HUUEQFmsLnEeahODEVjJqezIwDi0TcWxDYDHD/yBfTVTmnYcWQ9fhWqTJg34uuGHTIsC7QX5xM5vRHzHJ5DL3XU/QvBhhgNICTt9QJBAM2rDJlutEGnG67V5rO4sn8Ly3bZzX/ox6Cx0jK5/kH/pHfVy519/a5YDxWRPoJwKbUbCu+mtyZoEL+8Efp90pcCQQC1p7v1d0CbY4qN/FAcaBjTeMq1GFzAsSefgoCcp3dho3CHDcTaOlGz4Q78R0T/nLtXy5GmSIp5nqR3Xn9+1Ku7AkBJ96SWYfwuSH1qSb+pD1oS2xkX64k+4Wc6YfmpCL9q2Rg7iQar464iz8TNCumiW9iEK++o9RqVk4PCkU8d+baNAkEArxu05UeCgQfwfTRW6Tszi07qYK3OY3WiU3ISlnAcdYN+qsGMHC2RqsttjaUKDzk/or86dMeCrQBPXD48ur85lwJAOMeyZ0eAxnIWx2EZTXPttH94dSLDUtT4HuZabFiE+kD0ZMll2cqhjMZ2vr4Q/BR5jnyvXx43FpTFoCiFAEJ88Q==";
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
//            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCR8LF+2hI3K5r2FhdJGkWwkUVd1NfdPXmRIvoPREyLZF8LVI5JlOAAPSxTo2bE+ahi/0A6iZ2vB+OVspG06CF/0bsYOZ421ZiyCJr8Kk0NpehVvLJeaY1CKUuvU0WJ5FXvvNROshh1KcX/Nu1AZYujPp451tt1GQn+y6w1vJ+xTQIDAQAB";
            System.out.println("私钥字符串:" + privateKey);
            System.out.println("公钥字符串:" + publicKey);
            System.out.println("公钥加密——私钥解密");

            // RSA加密
            String str = "dfyz_project";
            System.out.println("\r明文：\r\n" + str);
            System.out.println("\r明文大小：\r\n" + str.getBytes().length);
            String encryptData = encrypt(str, getPublicKey(publicKey));
            System.out.println("密文:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);

            // RSA签名
            String sign = sign("dfyz_project", getPrivateKey(privateKey));
            System.out.println("签名:" + sign);
            // RSA验签
            boolean result = verify("dfyz_project", getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);

            System.out.println(Arrays.toString(Hex.encodeHex(privateKey.getBytes())).equals(Arrays.toString(Hex.encodeHex(privateKey.getBytes()))));
            KeyFactory k = KeyFactory.getInstance("RSA");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }


    }
}