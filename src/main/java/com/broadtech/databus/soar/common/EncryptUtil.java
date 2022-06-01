package com.broadtech.databus.soar.common;
/*
 * @author herunchen
 * @date 2021/9/8 11:29 上午
 * @describe
 */


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncryptUtil {

    private static final String KEY = "ngfwrestapilogin";

    //AES：加密方式   CBC：工作模式   PKCS5Padding：填充模式
    private static final String CBC_ZERO_PADDING = "AES/CBC/NoPadding";

    private static final String AES = "AES";


    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {

        if (content == null || "".equals(content)) {
            return content;
        }

        try {
            /*
             * 新建一个密码编译器的实例，由三部分构成，用"/"分隔，分别代表如下
             * 1. 加密的类型(如AES，DES，RC2等)
             * 2. 模式(AES中包含ECB，CBC，CFB，CTR，CTS等)
             * 3. 补码方式(包含nopadding/PKCS5Padding等等)
             * 依据这三个参数可以创建很多种加密方式
             */
            Cipher cipher = Cipher.getInstance(CBC_ZERO_PADDING);

            int blockSize = cipher.getBlockSize();
            //int blockSize = 128;
            byte[] dataBytes = content.getBytes();
            int length = dataBytes.length;

            //计算需填充长度
            if (length % blockSize != 0) {
                length = length + (blockSize - (length % blockSize));
            }

            byte[] plaintext = new byte[length];
            //填充
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), AES);

            //设置偏移量参数
            IvParameterSpec ivSpec = new IvParameterSpec(KEY.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] result = cipher.doFinal(plaintext);

            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(EncryptUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
}
