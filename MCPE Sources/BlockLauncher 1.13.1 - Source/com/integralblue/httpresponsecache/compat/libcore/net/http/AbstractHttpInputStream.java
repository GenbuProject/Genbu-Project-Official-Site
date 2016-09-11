package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.libcore.io.Streams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;

abstract class AbstractHttpInputStream
  extends InputStream
{
  private final OutputStream cacheBody;
  private final CacheRequest cacheRequest;
  protected boolean closed;
  protected final HttpEngine httpEngine;
  protected final InputStream in;
  
  AbstractHttpInputStream(InputStream paramInputStream, HttpEngine paramHttpEngine, CacheRequest paramCacheRequest)
    throws IOException
  {
    this.in = paramInputStream;
    this.httpEngine = paramHttpEngine;
    if (paramCacheRequest != null) {}
    for (paramInputStream = paramCacheRequest.getBody();; paramInputStream = null)
    {
      if (paramInputStream == null) {
        paramCacheRequest = null;
      }
      this.cacheBody = paramInputStream;
      this.cacheRequest = paramCacheRequest;
      return;
    }
  }
  
  protected final void cacheWrite(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.cacheBody != null) {
      this.cacheBody.write(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  protected final void checkNotClosed()
    throws IOException
  {
    if (this.closed) {
      throw new IOException("stream closed");
    }
  }
  
  protected final void endOfInput(boolean paramBoolean)
    throws IOException
  {
    if (this.cacheRequest != null) {
      this.cacheBody.close();
    }
    this.httpEngine.release(paramBoolean);
  }
  
  public final int read()
    throws IOException
  {
    return Streams.readSingleByte(this);
  }
  
  protected final void unexpectedEndOfInput()
  {
    if (this.cacheRequest != null) {
      this.cacheRequest.abort();
    }
    this.httpEngine.release(false);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\AbstractHttpInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */