package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.java.util.Arrays;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheRequest;

final class UnknownLengthHttpInputStream
  extends AbstractHttpInputStream
{
  private boolean inputExhausted;
  
  UnknownLengthHttpInputStream(InputStream paramInputStream, CacheRequest paramCacheRequest, HttpEngine paramHttpEngine)
    throws IOException
  {
    super(paramInputStream, paramHttpEngine, paramCacheRequest);
  }
  
  public int available()
    throws IOException
  {
    checkNotClosed();
    if (this.in == null) {
      return 0;
    }
    return this.in.available();
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {}
    do
    {
      return;
      this.closed = true;
    } while (this.inputExhausted);
    unexpectedEndOfInput();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    Arrays.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    checkNotClosed();
    if ((this.in == null) || (this.inputExhausted)) {
      return -1;
    }
    paramInt2 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt2 == -1)
    {
      this.inputExhausted = true;
      endOfInput(false);
      return -1;
    }
    cacheWrite(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\UnknownLengthHttpInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */