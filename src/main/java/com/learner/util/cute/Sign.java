package com.learner.util.cute;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by liufangliang on 2018/5/11.
 */
@Slf4j
public class Sign {

    private static final String a = "utf-8";
    private static final String b = "DESede";

    public static HashMap<String, String[]> b(String str, String str2) {
        HashMap<String, String[]> hashMap = new HashMap();
        if (str != null && str.length() > 0) {
            int i = 0;
            while (true) {
                String substring;
                int i2;
                String decode;
                String [] obj;
                int indexOf = str.indexOf(38, i) + 1;
                if (indexOf > 0) {
                    substring = str.substring(i, indexOf - 1);
                    i2 = indexOf;
                } else {
                    i2 = i;
                    substring = str.substring(i);
                }
                String[] split = substring.split("=");
                String obj2 = split[0];
                substring = split.length == 1 ? "" : split[1];
                try {
                    decode = URLDecoder.decode(substring, str2);
                } catch (UnsupportedEncodingException e) {
                    decode = substring;
                }
                if (hashMap.containsKey(obj2)) {
                    split = (String[]) hashMap.get(obj2);
                    int length = split.length;
                    String [] obj3 = new String[(length + 1)];
                    System.arraycopy(split, 0, obj3, 0, length);
                    obj3[length] = decode;
                    obj = obj3;
                } else {
                    obj = new String[]{decode};
                }
                hashMap.put(obj2, obj);
                if (indexOf <= 0) {
                    break;
                }
                i = i2;
            }
        }
        return hashMap;
    }


    public static String createNence(int i) {
        String str = "0123456789";
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = str.charAt((int) (Math.random() * 10.0d));
        }
        return String.valueOf(cArr);
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(toHexString.toUpperCase());
        }
        return stringBuilder.toString();
    }
    public static String encryp(String str) throws Exception {
        return a(MessageDigest.getInstance("SHA-1").digest(str.getBytes(a)));
    }

    public static String encryp(String id,String nonce,String timestamp){
        String secret="bbaungol30n4co27lkhg";
        HashMap<String ,String []> b = new HashMap();
        b.put("id",new String[]{id});
        b.put("timestamp", new String[]{timestamp});
        b.put("nonce",new String[]{nonce});
        b.put("app_version", new String []{"3.6.0"});
        b.put("secret", new String []{secret});
        List arrayList = new ArrayList(b.keySet());
        Collections.sort(arrayList);
        List arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add((arrayList.get(i)) + "=" + Arrays.toString((String[]) b.get(arrayList.get(i))).replace("[", "").replace("]", ""));
        }
        String str = null;
        try {
            String str1=String.join("&", arrayList2).toUpperCase();
            log.info(str1);
            str = encryp(str1).toLowerCase();
//            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    public static void main(String[] args) {
        String secret="bbaungol30n4co27lkhg";
        String.valueOf(((long) 0) + (System.currentTimeMillis() - 0));
        HashMap<String ,String []> b = new HashMap();
        b.put("timestamp", new String[]{System.currentTimeMillis()+""});
        b.put("nonce", new String [] {createNence(6)});
        b.put("app_version", new String []{"3.6.0"});
        b.put("secret", new String []{secret});

        List arrayList = new ArrayList(b.keySet());
        Collections.sort(arrayList);
        List arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            String str=(arrayList.get(i)) + "=" + Arrays.toString((String[]) b.get(arrayList.get(i))).replace("[", "").replace("]", "");
            System.out.println(str);
            arrayList2.add(str);
        }
        String str = null;
        try {
            str = encryp(String.join("&", arrayList2).toUpperCase()).toLowerCase();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
