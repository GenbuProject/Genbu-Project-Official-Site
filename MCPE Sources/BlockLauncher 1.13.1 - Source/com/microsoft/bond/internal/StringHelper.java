package com.microsoft.bond.internal;

import java.nio.charset.Charset;

public final class StringHelper
{
  public static final Charset UTF16 = Charset.forName("utf-16le");
  public static final Charset UTF8 = Charset.forName("utf-8");
  
  public static String decodeFromUtf16(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new String(paramArrayOfByte, paramInt1, paramInt2, UTF16);
  }
  
  public static String decodeFromUtf8(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new String(paramArrayOfByte, paramInt1, paramInt2, UTF8);
  }
  
  public static byte[] encodeToUtf16(String paramString)
  {
    return paramString.getBytes(UTF16);
  }
  
  public static byte[] encodeToUtf8(String paramString)
  {
    return paramString.getBytes(UTF8);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\internal\StringHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */