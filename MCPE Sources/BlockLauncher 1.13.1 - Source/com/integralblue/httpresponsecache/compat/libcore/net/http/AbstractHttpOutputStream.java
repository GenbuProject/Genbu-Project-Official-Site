package com.integralblue.httpresponsecache.compat.libcore.net.http;

import java.io.IOException;
import java.io.OutputStream;

abstract class AbstractHttpOutputStream
  extends OutputStream
{
  protected boolean closed;
  
  protected final void checkNotClosed()
    throws IOException
  {
    if (this.closed) {
      throw new IOException("stream closed");
    }
  }
  
  public final void write(int paramInt)
    throws IOException
  {
    write(new byte[] { (byte)paramInt });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\AbstractHttpOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */