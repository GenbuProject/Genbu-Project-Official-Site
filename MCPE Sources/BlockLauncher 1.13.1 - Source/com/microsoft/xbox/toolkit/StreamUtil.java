package com.microsoft.xbox.toolkit;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class StreamUtil
{
  public static void CopyStream(OutputStream paramOutputStream, InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['?'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i <= 0) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
    paramOutputStream.flush();
  }
  
  public static byte[] CreateByteArray(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      CopyStream(localByteArrayOutputStream, paramInputStream);
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException paramInputStream) {}
    return null;
  }
  
  public static byte[] HexStringToByteArray(String paramString)
  {
    int i = 0;
    if (paramString == null) {
      throw new IllegalArgumentException("hexString invalid");
    }
    String str = paramString;
    if (paramString.length() % 2 != 0) {
      str = "0" + paramString;
    }
    if (str.length() % 2 == 0) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString = new byte[str.length() / 2];
      while (i < str.length())
      {
        paramString[(i / 2)] = Byte.parseByte(str.substring(i, i + 2), 16);
        i += 2;
      }
    }
    return paramString;
  }
  
  public static String ReadAsString(InputStream paramInputStream)
  {
    localStringBuilder = new StringBuilder();
    paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream));
    try
    {
      for (;;)
      {
        String str = paramInputStream.readLine();
        if (str == null) {
          break;
        }
        localStringBuilder.append(str);
        localStringBuilder.append('\n');
      }
      return localStringBuilder.toString();
    }
    catch (IOException paramInputStream)
    {
      return null;
    }
  }
  
  public static void consumeAndClose(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new BufferedInputStream(paramInputStream);
    try
    {
      int i;
      do
      {
        i = paramInputStream.read();
      } while (i != -1);
      return;
    }
    finally
    {
      paramInputStream.close();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\StreamUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */