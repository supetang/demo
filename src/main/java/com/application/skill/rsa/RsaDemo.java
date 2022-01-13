package com.application.skill.rsa;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaDemo {

    public static BASE64Encoder base64Encoder = new BASE64Encoder();
    public static BASE64Decoder base64Decoder = new BASE64Decoder();


    // 公钥216
    private static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCaGn5+Yq9o7kxmDS2zwIREIEkcpKbx44uPT/PX22XOjgzkMvbCEamu7KyatNhkrrSH6E14l1F739WztXffJ/OCXUmgF4egMedyGCA8ymZWbzyhwnZcywa8me8S2rjb3gkCD6VFq8BSPJx9vaMvlR7eZxe1O5lllaV6tE6SlNY7QIDAQAB";
    //私钥844
    private static String privateKeyStr =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJoafn5ir2juTGYNLbPAhEQgSRy" +
                    "kpvHji49P89fbZc6ODOQy9sIRqa7srJq02GSutIfoTXiXUXvf1bO1d98n84JdSaAXh6Ax53IYIDz" +
                    "KZlZvPKHCdlzLBryZ7xLauNveCQIPpUWrwFI8nH29oy+VHt5nF7U7mWWVpXq0TpKU1jtAgMBAAEC" +
                    "gYBOzKsk+s4EM5dnSXKo+ENmblOq43SFnzrh4+7X7vD4zZxCRH96NfEDNS4Qs45RSmmLKMOwHL2B" +
                    "0etfWBJSPisNZomhOBgTSfvHDSIFNbvVFqQBH5zEtxIB3bPrkW5X6EDF2BYCatC70Al21kmzHr2d" +
                    "BaL/c2p/4ueTdna/zKSKwQJBAMZLvFeyLi9jiIXXUzv7AkxF4SICtOOL56mKMkVlAXOWQgQe9FVs" +
                    "O8lTTK6GqqLrYe5cqgTiQqcLCP8/5n33oF0CQQCoW0ospTg8JEqStTmwemF8HfgxLU6YGj5kegD2" +
                    "Fq6HfCTq/z59THohbq6l+yoSxO3mjZb+7Zv1zu6oDR7YXFHRAkB66dEaDtFAAJNMWxc107Yt7xbI" +
                    "zSKw9TSoy4ezqhNHQXk0MrfDB27bsS2T9NdqWzr91CRzGIi2IEn4ZfSKWmblAkB2S1bOEfV2hMWF" +
                    "WiND9mnDDUfUPhKIW4BVl0hPodZWSouiN2DQJ8l07lF3PQjuEUNcCUb8rzYzvIgCut1eh1fRAkEA" +
                    "qBOEqBuRq9W7vrBlHbqEk1EXu2MfeRLZWljZn6uoyedJCdSh+6neUUvqHDGi3DNdss/ByWHI9e+9" +
                    "zOXTURmMaQ==";


    private static final com.application.skill.rsa.RSAUtil ourInstance = new com.application.skill.rsa.RSAUtil();

    public static com.application.skill.rsa.RSAUtil getInstance() {
        return ourInstance;
    }


    // 生成密钥对
    private static void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator;
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥，并以base64格式打印出来
        PublicKey publicKey = keyPair.getPublic();
        publicKeyStr = new String(base64Encoder.encode(publicKey.getEncoded()));
        System.out.println("公钥" + publicKeyStr);
        // 获取私钥，并以base64格式打印出来
        PrivateKey privateKey = keyPair.getPrivate();
        privateKeyStr = new String(base64Encoder.encode(privateKey.getEncoded()));
        System.out.println("私钥" + privateKeyStr);
    }


    // 将base64编码后的公钥字符串转成PublicKey实例
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = base64Decoder.decodeBuffer(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // 将base64编码后的私钥字符串转成PrivateKey实例
    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = base64Decoder.decodeBuffer(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }


    // 私钥加密
    public static String encryptByPrivateKey(String content) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        String cipherStr = base64Encoder.encode(cipherText);
        return cipherStr;
    }

    // 私钥解密
    public static String decryptByPrivateKey(String content) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherText = base64Decoder.decodeBuffer(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }

    // 公钥加密
    public static String encryptByPublicKey(String content) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        String cipherStr = base64Encoder.encode(cipherText);
        return cipherStr;
    }

    // 公钥解密
    public static String decryptByPublicKey(String content) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] cipherText = base64Decoder.decodeBuffer(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }


    public static void main(String[] args) throws Exception {
        /*String data="111111111666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666";



        System.out.println("初始数据："+data);
        // 公钥加密
        String encryptedBytes = encryptByPublicKey(data);
        System.out.println("公钥加密后：" + encryptedBytes);
        // 私钥解密
        String decryptedBytes = decryptByPrivateKey(encryptedBytes);

        System.out.println("私钥解密后：" + decryptedBytes);





        // 私钥加密
        String encryptedBytes2 = encryptByPrivateKey(data);
        System.out.println("私钥加密后：" + encryptedBytes2);
        // 公钥解密
        String decryptedBytes2 = decryptByPublicKey(encryptedBytes2);
        System.out.println("公钥解密后：" + decryptedBytes2);*/

        generateKeyPair();
    }
}