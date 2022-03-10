package com.intellicar.dummylafproject;

import java.nio.charset.StandardCharsets;

public class LittleEndianUtils {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String printHexArray(byte[] inBuffer) {
        return printHexArray(inBuffer, 0, inBuffer.length);
    }

    public static String printHexArray(byte[] inBuffer, int startidx, int length) {
        if((startidx + length) > inBuffer.length)
        {
            if (startidx < inBuffer.length)
            {
                length = inBuffer.length - startidx;
            }else
            {
                length = 0;
            }
        }
        if (length == 0)
            return "";
        char[] hexChars = new char[length * 2];
        for (int j = 0; j < length; j++) {
            int v = inBuffer[startidx + j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
