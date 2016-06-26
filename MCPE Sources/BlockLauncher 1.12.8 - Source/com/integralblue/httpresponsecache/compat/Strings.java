package com.integralblue.httpresponsecache.compat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class Strings
{
  private static final char[] DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
  private static final char[] UPPER_CASE_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 };
  
  public static String bytesToHexString(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramBoolean) {}
    char[] arrayOfChar2;
    for (char[] arrayOfChar1 = UPPER_CASE_DIGITS;; arrayOfChar1 = DIGITS)
    {
      arrayOfChar2 = new char[paramArrayOfByte.length * 2];
      int k = paramArrayOfByte.length;
      int i = 0;
      int j = 0;
      while (i < k)
      {
        int m = paramArrayOfByte[i];
        int n = j + 1;
        arrayOfChar2[j] = arrayOfChar1[(m >> 4 & 0xF)];
        j = n + 1;
        arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
        i += 1;
      }
    }
    return new String(arrayOfChar2);
  }
  
  public static final String construct(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Charset paramCharset)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, paramInt1, paramInt2, paramCharset.name());
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      throw new IllegalArgumentException(paramArrayOfByte);
    }
  }
  
  public static byte[] getBytes(String paramString, Charset paramCharset)
  {
    try
    {
      paramString = paramString.getBytes(paramCharset.name());
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static final boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\Strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */