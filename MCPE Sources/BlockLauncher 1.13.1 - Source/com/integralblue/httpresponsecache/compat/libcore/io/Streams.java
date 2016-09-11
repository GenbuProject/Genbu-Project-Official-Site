package com.integralblue.httpresponsecache.compat.libcore.io;

import com.integralblue.httpresponsecache.compat.java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicReference;

public final class Streams
{
  private static AtomicReference<byte[]> skipBuffer = new AtomicReference();
  
  public static int copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    int i = 0;
    byte[] arrayOfByte = new byte['?'];
    for (;;)
    {
      int j = paramInputStream.read(arrayOfByte);
      if (j == -1) {
        break;
      }
      i += j;
      paramOutputStream.write(arrayOfByte, 0, j);
    }
    return i;
  }
  
  public static String readAsciiLine(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(80);
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        throw new EOFException();
      }
      if (i == 10)
      {
        i = localStringBuilder.length();
        if ((i > 0) && (localStringBuilder.charAt(i - 1) == '\r')) {
          localStringBuilder.setLength(i - 1);
        }
        return localStringBuilder.toString();
      }
      localStringBuilder.append((char)i);
    }
  }
  
  public static String readFully(Reader paramReader)
    throws IOException
  {
    try
    {
      StringWriter localStringWriter = new StringWriter();
      char[] arrayOfChar = new char['?'];
      for (;;)
      {
        int i = paramReader.read(arrayOfChar);
        if (i == -1) {
          break;
        }
        localStringWriter.write(arrayOfChar, 0, i);
      }
      str = ((StringWriter)localObject).toString();
    }
    finally
    {
      paramReader.close();
    }
    String str;
    paramReader.close();
    return str;
  }
  
  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    readFully(paramInputStream, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 == 0) {}
    for (;;)
    {
      return;
      if (paramInputStream == null) {
        throw new NullPointerException("in == null");
      }
      if (paramArrayOfByte == null) {
        throw new NullPointerException("dst == null");
      }
      Arrays.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      while (paramInt2 > 0)
      {
        int i = paramInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
        if (i < 0) {
          throw new EOFException();
        }
        paramInt1 += i;
        paramInt2 -= i;
      }
    }
  }
  
  public static byte[] readFully(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      byte[] arrayOfByte = readFullyNoClose(paramInputStream);
      return arrayOfByte;
    }
    finally
    {
      paramInputStream.close();
    }
  }
  
  public static byte[] readFullyNoClose(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['?'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static int readSingleByte(InputStream paramInputStream)
    throws IOException
  {
    int i = -1;
    byte[] arrayOfByte = new byte[1];
    if (paramInputStream.read(arrayOfByte, 0, 1) != -1) {
      i = arrayOfByte[0] & 0xFF;
    }
    return i;
  }
  
  public static void skipAll(InputStream paramInputStream)
    throws IOException
  {
    do
    {
      paramInputStream.skip(Long.MAX_VALUE);
    } while (paramInputStream.read() != -1);
  }
  
  public static long skipByReading(InputStream paramInputStream, long paramLong)
    throws IOException
  {
    byte[] arrayOfByte2 = (byte[])skipBuffer.getAndSet(null);
    byte[] arrayOfByte1 = arrayOfByte2;
    if (arrayOfByte2 == null) {
      arrayOfByte1 = new byte['?'];
    }
    long l1 = 0L;
    long l2 = l1;
    int i;
    int j;
    if (l1 < paramLong)
    {
      i = (int)Math.min(paramLong - l1, arrayOfByte1.length);
      j = paramInputStream.read(arrayOfByte1, 0, i);
      if (j != -1) {
        break label86;
      }
      l2 = l1;
    }
    for (;;)
    {
      skipBuffer.set(arrayOfByte1);
      return l2;
      label86:
      l2 = l1 + j;
      l1 = l2;
      if (j >= i) {
        break;
      }
    }
  }
  
  public static void writeSingleByte(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(new byte[] { (byte)(paramInt & 0xFF) });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\io\Streams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */