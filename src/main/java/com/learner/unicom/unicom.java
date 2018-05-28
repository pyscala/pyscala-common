package com.learner.unicom;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
@Slf4j
public class unicom {

    private final static Logger logger= LoggerFactory.getLogger(unicom.class);

    public static byte[] getBytes(String str){
        String param1=str;
        byte [] paramArrayOfByte1=param1.getBytes();
        System.out.println(paramArrayOfByte1.toString());
        String param2="f6b0d3f905bf02939b4f6d29f257c2ab";
        byte [] paramArrayOfByte2=b.a(param2.toCharArray());
        System.out.println(paramArrayOfByte2.toString());
        String param3="1a42eb4565be8628a807403d67dce78d";
        byte [] paramArrayOfByte3=b.a(param3.toCharArray());
        System.out.println(paramArrayOfByte3.toString());


        return enc(paramArrayOfByte1,paramArrayOfByte2,paramArrayOfByte3,1);
    }

    private static byte[] enc(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        try {
            Key secretKeySpec = new SecretKeySpec(bArr2, "AES");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(i, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

    public static String encrypt(String str){
        return new String(b.a(unicom.getBytes(str)));
    }

    public static void main(String[] args) {
        //return g.a(a.a(paramString.getBytes(), g.a("f6b0d3f905bf02939b4f6d29f257c2ab"), g.a("1a42eb4565be8628a807403d67dce78d")));
//        byte [] enc = getBytes("14531620693000000");
//        String str =new String(b.a(enc));
//        System.out.print(str);

        String phoneNo = "14531620693123400";
        logger.info(encrypt(phoneNo));

    }

}
