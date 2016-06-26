package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.java.util.Arrays;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheRequest;

final class FixedLengthInputStream
  extends AbstractHttpInputStream
{
  private int bytesRemaining;
  
  public FixedLengthInputStream(InputStream paramInputStream, CacheRequest paramCacheRequest, HttpEngine paramHttpEngine, int paramInt)
    throws IOException
  {
    super(paramInputStream, paramHttpEngine, paramCacheRequest);
    this.bytesRemaining = paramInt;
    if (this.bytesRemaining == 0) {
      endOfInput(true);
    }
  }
  
  public int available()
    throws IOException
  {
    checkNotClosed();
    if (this.bytesRemaining == 0) {
      return 0;
    }
    return Math.min(this.in.available(), this.bytesRemaining);
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {}
    do
    {
      return;
      this.closed = true;
    } while (this.bytesRemaining == 0);
    unexpectedEndOfInput();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    Arrays.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    checkNotClosed();
    if (this.bytesRemaining == 0) {
      paramInt1 = -1;
    }
    do
    {
      return paramInt1;
      paramInt2 = this.in.read(paramArrayOfByte, paramInt1, Math.min(paramInt2, this.bytesRemaining));
      if (paramInt2 == -1)
      {
        unexpectedEndOfInput();
        throw new IOException("unexpected end of stream");
      }
      this.bytesRemaining -= paramInt2;
      cacheWrite(paramArrayOfByte, paramInt1, paramInt2);
      paramInt1 = paramInt2;
    } while (this.bytesRemaining != 0);
    endOfInput(true);
    return paramInt2;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\FixedLengthInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */