package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.java.util.Arrays;
import java.io.IOException;
import java.io.OutputStream;

final class FixedLengthOutputStream
  extends AbstractHttpOutputStream
{
  private int bytesRemaining;
  private final OutputStream socketOut;
  
  public FixedLengthOutputStream(OutputStream paramOutputStream, int paramInt)
  {
    this.socketOut = paramOutputStream;
    this.bytesRemaining = paramInt;
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {}
    do
    {
      return;
      this.closed = true;
    } while (this.bytesRemaining <= 0);
    throw new IOException("unexpected end of stream");
  }
  
  public void flush()
    throws IOException
  {
    if (this.closed) {
      return;
    }
    this.socketOut.flush();
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    checkNotClosed();
    Arrays.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    if (paramInt2 > this.bytesRemaining) {
      throw new IOException("expected " + this.bytesRemaining + " bytes but received " + paramInt2);
    }
    this.socketOut.write(paramArrayOfByte, paramInt1, paramInt2);
    this.bytesRemaining -= paramInt2;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\FixedLengthOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */