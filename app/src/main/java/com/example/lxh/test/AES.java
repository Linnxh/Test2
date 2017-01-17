/***
 * Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com)
 ***/
package com.example.lxh.test;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    static final String AES_KEY = "k;)*(+nmjdsf$#@d";

    public static String aesEncrypt(String str) {
        return aesEncrypt("k;)*(+nmjdsf$#@d", str);// 23
    }

    public static String aesDecrypt(String str) {
        return aesDecrypt("k;)*(+nmjdsf$#@d", str);// 33
    }

    public static String aesEncrypt(String aes_KEY, String str) {
        try {
            str = new String(str.getBytes("UTF-8"), "UTF-8");// 44
            SecretKeySpec skeySpec = new SecretKeySpec(aes_KEY.getBytes(),
                    "AES");// 46
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 47
            cipher.init(1, skeySpec);// 48
            byte[] bs = cipher.doFinal(str.getBytes("UTF-8"));// 49
            byte[] bs64 = Base64.encode(bs, Base64.DEFAULT);// 50
            String strTmp = new String(bs64);// 51
            return strTmp;// 52
        } catch (Exception arg7) {// 54
            throw new RuntimeException("AES加密时异常" + arg7.getMessage());// 55
        }
    }

    public static String aesDecrypt(String aes_KEY, String str) {
        try {
            if (str != null && !str.isEmpty()) {// 68
                SecretKeySpec skeySpec = new SecretKeySpec(aes_KEY.getBytes(),
                        "AES");// 72
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 73
                cipher.init(2, skeySpec);// 74
                byte[] bs = Base64.decode(str.replace(" ", "+"), Base64.DEFAULT);// 75
                String strTmp = new String(cipher.doFinal(bs), "UTF-8");// 76
                return strTmp;// 77
            } else {
                return "";// 69
            }
        } catch (Exception arg6) {// 79
            throw new RuntimeException("AES解密时异常" + arg6.getMessage());// 80
        }
    }

    public static void main(String[] args) {
        String str = "掌合天下";// 85
        String encrypt = aesEncrypt(str);// 86
        System.out.println("99999999999" + encrypt);// 87
        System.out.println("11111111111" + aesDecrypt(encrypt));// 88
    }// 90
}