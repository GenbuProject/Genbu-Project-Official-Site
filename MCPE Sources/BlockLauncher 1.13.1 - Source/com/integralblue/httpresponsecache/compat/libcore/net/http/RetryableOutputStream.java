package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class RetryableOutputStream
  extends AbstractHttpOutputStream
{
  private final ByteArrayOutputStream content;
  private final int limit;
  
  public RetryableOutputStream()
  {
    this.limit = -1;
    this.content = new ByteArrayOutputStream();
  }
  
  public RetryableOutputStream(int paramInt)
  {
    this.limit = paramInt;
    this.content = new ByteArrayOutputStream(paramInt);
  }
  
  public void close()
    throws IOException
  {
    try
    {
      boolean bool = this.closed;
      if (bool) {}
      do
      {
        return;
        this.closed = true;
      } while (this.content.size() >= this.limit);
      throw new IOException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
    }
    finally {}
  }
  
  public int contentLength()
    throws IOException
  {
    try
    {
      close();
      int i = this.content.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      checkNotClosed();
      Arrays.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      if ((this.limit != -1) && (this.content.size() > this.limit - paramInt2)) {
        throw new IOException("exceeded content-length limit of " + this.limit + " bytes");
      }
    }
    finally {}
    this.content.write(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void writeToSocket(OutputStream paramOutputStream)
    throws IOException
  {
    this.content.writeTo(paramOutputStream);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\RetryableOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */