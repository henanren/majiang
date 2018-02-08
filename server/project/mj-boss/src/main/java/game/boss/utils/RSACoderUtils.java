package game.boss.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * @author zuoge85@gmail.com on 2016/12/10.
 */
public class RSACoderUtils {
    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";


    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 1024;

    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * 初始化密钥对
     *
     * @return key 公钥 ，value 私钥
     */
    public static Map.Entry<String, String> initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        //将密钥存储在map中
        return new java.util.AbstractMap.SimpleImmutableEntry<>(
                Base64.encodeBase64URLSafeString(publicKey.getEncoded()),
                Base64.encodeBase64URLSafeString(privateKey.getEncoded())
        );
    }


    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {

        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i< data.length; i+=MAX_ENCRYPT_BLOCK){
            byte[] update = cipher.update(data, i, Math.min(data.length - i, MAX_ENCRYPT_BLOCK));
            out.write(
                    cipher.doFinal()
            );
        }

        return out.toByteArray();
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i< data.length; i+=MAX_DECRYPT_BLOCK){
            byte[] update = cipher.update(data, i, Math.min(data.length - i, MAX_DECRYPT_BLOCK));
            out.write(
                    cipher.doFinal()
            );
        }

        return out.toByteArray();
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //初始化密钥
        //生成密钥对
        Map.Entry<String, String> stringStringEntry = RSACoderUtils.initKey();
        //公钥
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOLX6y_Ud7svHi4PDOUgQMhn9DrWjTU7che_8KOK0XoItY8NlAhVXdus82Xvs268O-GgmeVmOGwXfxrFgft5CCQx-okQM2_qbuKWH1IA3JRYbKz2Kt93wtHZHqSFmwtIcv3kxupbw9umqP8dBjsPUue9vHcbem3uys6e56FHvjowIDAQAB";

        //私钥
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI4tfrL9R3uy8eLg8M5SBAyGf0OtaNNTtyF7_wo4rRegi1jw2UCFVd26zzZe-zbrw74aCZ5WY4bBd_GsWB-3kIJDH6iRAzb-pu4pYfUgDclFhsrPYq33fC0dkepIWbC0hy_eTG6lvD26ao_x0GOw9S5728dxt6be7Kzp7noUe-OjAgMBAAECgYAe3C8hCKrIZbntBege0FyCn3rmJLCZFhWs1e4wuMaRV06HupddUtOlchPh-FHMxPXSwHIQBS5LJJk1QrRKBhz-ZXzvHWxDC3xJSeGbaIhklUFosmXRT_JpwIdbO75TDCXBJu-LWIkEpPRwZvcNab6U6vRuTXs4XobaQegGCW_GAQJBAN0PAaeghrmKmUZ7GprDvciuJHry_V7UICqqqaKn7B7tRUZjO0FRWBzKIrRtC9_B3QnHTY3qI0xf5pVYlaEzuqMCQQCkpp-05qX7A1_hEWj6cy7I_VPRxcWxQzjA2mhGEazI1z417PBfNcSg77-xQl3eHz16VYde7OJvvZ7-k0QTSsMBAkAzrMZeQRA9X0DlUPGvLAK5Xrv4YcUI3d2HG837z_VKZXzEL34yjqliUX47XyJfdBoPpx9iBVGrEN_-GW8g986xAkAw2EV0sx_2UU_2QuXztovgkFZ3WsaFCb4iaCia8M9jXYdVFA9TD25fmAA0I84ZAQeJ7SmVnYynPYeXWjTNzFYBAkAxj0SzNblwSAVp7yZ0D4mga4E2Y-kGbl70losoy_fEP-TIG5mjmnqqts_PPj4HHjahNrD_6aqyVe-IUIxPsNH7";
        System.out.println("公钥:" + publicKey);
//        System.out.println("公钥:" + "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJCNKgT_OyOrnJV7MRIoLhGQpbc0I5QKTbwBuVHyPZzyqw9qFo4cdPF4nZKlphoQ6vd0KyO9UhMYVVcVY9M4ChECAwEAAQ");
        System.out.println("私钥:" + privateKey);
//        System.out.println("私钥:" + "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAnFdfMADsxA1LdN09JUWUDMMeNE5pag8YokmKYEbq6zl4ZqzJfRNoxkTel2VrM5oHS5HjJe-FapmdZqSi4gG3lwIDAQABAkBla9zJVGBEUueXeYqYKkn_-XgXsKJ79i0h4plNGdR-ITbq6sPJuNUUaocclHwF3QdPcuse20K8EQdQMnmHLvApAiEA15EamGlZcVYdUVrtWuwtsii0iVCnPPJnZdwtZ7nO8Z0CIQC5qm37EvzzKVIxLiohha8mqogSheNJ3VCll7z6iZFRwwIhAJTU2kfgdKCLlDJXVQkxh1s6P2uPA-hQdJMfMTosT3UBAiBChAto0tGqb9hRZD-VyOYKDTg4opzeHprA6I68l0JnrwIgYoZb_02AiqYHYz8RHbeCm6DWfo9QLnadVswOcZYqFY4");

        System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
        String str = "abc";
        System.out.println("/n===========甲方向乙方发送加密数据==============");
        System.out.println("原文:" + str);
//        //甲方进行数据的加密
//        byte[] code1 = RSACoderUtils.encryptByPrivateKey(str.getBytes(), privateKey);
//        System.out.println("加密后的数据：" + Base64.encodeBase64String(code1));
//        System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
//        //乙方进行数据的解密
//        byte[] decode1 = RSACoderUtils.decryptByPublicKey(code1, publicKey);
//        System.out.println("乙方解密后的数据：" + new String(decode1) + "/n/n");

        System.out.println("===========反向进行操作，乙方向甲方发送数据==============/n/n");

//        str = "乙方向甲方发送数据RSA算法";

        System.out.println("原文:" + str);

        //乙方使用公钥对数据进行加密
        byte[] code2 = RSACoderUtils.encryptByPublicKey(str.getBytes(), publicKey);
        System.out.println("===========乙方使用公钥对数据进行加密==============");
        String data = Base64.encodeBase64URLSafeString(code2);
        System.out.println("加密后的数据：" + data);

        System.out.println("=============乙方将数据传送给甲方======================");
        System.out.println("===========甲方使用私钥对数据进行解密==============");

        //甲方使用私钥对数据进行解密
        byte[] decode2 = RSACoderUtils.decryptByPrivateKey(Base64.decodeBase64(data), privateKey);

        System.out.println("甲方解密后的数据：" + new String(decode2));
    }
}
