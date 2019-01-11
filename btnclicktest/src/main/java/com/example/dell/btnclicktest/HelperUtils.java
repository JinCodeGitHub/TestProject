package com.example.dell.btnclicktest;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * <pre>
 *     author : dell
 *     time   : 2018/12/15
 *     desc   : use for project Utils
 *
 *     version: 1.0
 * </pre>
 */

public class HelperUtils {

    public static char[] getChars(byte[] bytes){
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }


    @TargetApi(Build.VERSION_CODES.O)

    public  static  String  getBase64(String str) throws UnsupportedEncodingException {

        if (str == null || str.length()  == 0){
            return str;
        }

        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();

        final byte[] textByte = str.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);


        return encodedText;
    }



}
