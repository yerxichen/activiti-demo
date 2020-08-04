package cn.yer.activiti.demo.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密解密工具类
 */
public class CryptoUtil {
    static Logger logger = LoggerFactory.getLogger(CryptoUtil.class);



    /**
     * 加密
     *
     * @param content 需要加密的字段
     * @param key     16个字符的密钥
     * @return
     */
    public static String encrypt(String content, String key) {
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.encryptHex(content);
    }


    /**
     * 解密
     *
     * @param content 需要解密的字段
     * @param key     需要跟解密的密钥相同
     * @return
     */
    public static String decrypt(String content, String key) {
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
    }

    public static void main(String[] args) {
        String content = "320683199001020001";
        String key="AB7C1840A5156ACA";
        String encrypt=encrypt(content, key);
        logger.info(encrypt);
        String decrypt=decrypt(encrypt, key);
        logger.info(decrypt);
    }
}
