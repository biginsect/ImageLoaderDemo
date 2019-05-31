package com.chrisly.imageloaderdemo.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author big insect
 * @date 2019/5/31.
 */
public class MD5Utils {

    private MD5Utils(){
        throw new UnsupportedOperationException("cannot be initialized");
    }

    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e("MD5Utils","md5  is unavailable");
        }
    }

    public static String toMD5(String key){
        if (digest == null ){
            return String.valueOf(key.hashCode());
        }

        digest.update(key.getBytes());
        return convertToHexString(digest.digest());
    }

    /**
     * 转成十六进制
     * */
    private static String convertToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest){
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }

        return sb.toString();
    }


}
