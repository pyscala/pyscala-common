package com.learner.unicom;

//package org.apache.a.a.a;
//import org.apache.a.a.a;


public class b {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private  String c;

    protected static int a(char c, int i) {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        System.out.println("err : "+c);
        return 0;
//        throw new a(new StringBuffer("Illegal hexadecimal charcter ").append(c).append(" at index ").append(i).toString());
    }

    public static byte[] a(char[] cArr) {
        int i = 0;
        int length = cArr.length;
        if ((length & 1) != 0) {
//            throw new Exception("Odd number of characters.");
            System.out.println("Odd number of characters.");
        }
        byte[] bArr = new byte[(length >> 1)];
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) (((a(cArr[i], i) << 4) | a(cArr[i], i)) & 255);
            i++;
            i++;
            i2++;
        }
        return bArr;
    }

    public static char[] a(byte[] bArr) {
        return a(bArr, true);
    }

    public static char[] a(byte[] bArr, boolean z) {
        return a(bArr, z ? a : b);
    }

    protected static char[] a(byte[] bArr, char[] cArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public static String b(byte[] bArr) {
        return new String(a(bArr));
    }

    public String toString() {
        return new StringBuffer(String.valueOf(super.toString())).append("[charsetName=").append(this.c).append("]").toString();
    }
}
