package com.dodo.baselibs.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密相关
 *
 * @author martin
 */
public class UEncryptUtil {

    /**
     * MD5加密算法(secretkey 没有改变)
     *
     * @param secretKey
     * @return
     */
    public static String md5(String secretKey) {
        if (TextUtils.isEmpty(secretKey)) {
            return "";
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(secretKey.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
        }

        byte[] byteArray = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                sb.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                sb.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return sb.toString();
    }

}
