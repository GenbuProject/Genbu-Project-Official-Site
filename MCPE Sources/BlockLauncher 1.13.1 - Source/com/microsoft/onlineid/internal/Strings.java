package com.microsoft.onlineid.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Strings
{
  public static final Charset Utf8Charset = Charset.forName("UTF-8");
  
  public static boolean equalsIgnoreCase(String paramString1, String paramString2)
  {
    if (paramString1 == paramString2) {
      return true;
    }
    if ((paramString1 == null) || (paramString2 == null)) {
      return false;
    }
    return paramString1.equalsIgnoreCase(paramString2);
  }
  
  public static String fromStream(InputStream paramInputStream, Charset paramCharset)
    throws IOException
  {
    paramInputStream = new InputStreamReader(paramInputStream, paramCharset);
    paramCharset = new StringBuilder();
    char[] arrayOfChar = new char['?'];
    try
    {
      for (;;)
      {
        int i = paramInputStream.read(arrayOfChar);
        if (i < 0) {
          break;
        }
        paramCharset.append(arrayOfChar, 0, i);
      }
    }
    finally
    {
      paramInputStream.close();
    }
    return paramCharset.toString();
  }
  
  public static String pluralize(long paramLong, String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(paramLong).append(" ");
    if (paramLong == 1L) {
      paramString2 = paramString1;
    }
    return paramString2;
  }
  
  public static void verifyArgumentNotNullOrEmpty(String paramString1, String paramString2)
  {
    Objects.verifyArgumentNotNull(paramString1, paramString2);
    if (paramString1.isEmpty()) {
      throw new IllegalArgumentException(paramString2 + " must not be empty.");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\Strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */