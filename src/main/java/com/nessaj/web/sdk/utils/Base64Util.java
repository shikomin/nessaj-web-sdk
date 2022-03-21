package com.nessaj.web.sdk.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author keming
 * @Date 2022/03/21 22:17
 */
public class Base64Util {

    public static String base64Encoder(String plainText) {
        String base64encodedString = null;
        try {
            base64encodedString = Base64.getEncoder().encodeToString(plainText.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64encodedString;
    }

    public static String base64Deconder(String encodedText) {
        byte[] base64decodedBytes = Base64.getDecoder().decode(encodedText);
        String plainText = null;
        try {
            plainText = new String(base64decodedBytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return plainText;
    }

}
