package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.java.util.Arrays;
import com.integralblue.httpresponsecache.compat.libcore.io.Streams;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheRequest;

final class ChunkedInputStream
  extends AbstractHttpInputStream
{
  private static final int MIN_LAST_CHUNK_LENGTH = "\r\n0\r\n\r\n".length();
  private static final int NO_CHUNK_YET = -1;
  private int bytesRemainingInChunk = -1;
  private boolean hasMoreChunks = true;
  
  ChunkedInputStream(InputStream paramInputStream, CacheRequest paramCacheRequest, HttpEngine paramHttpEngine)
    throws IOException
  {
    super(paramInputStream, paramHttpEngine, paramCacheRequest);
  }
  
  private void readChunkSize()
    throws IOException
  {
    if (this.bytesRemainingInChunk != -1) {
      Streams.readAsciiLine(this.in);
    }
    String str2 = Streams.readAsciiLine(this.in);
    int i = str2.indexOf(";");
    String str1 = str2;
    if (i != -1) {
      str1 = str2.substring(0, i);
    }
    try
    {
      this.bytesRemainingInChunk = Integer.parseInt(str1.trim(), 16);
      if (this.bytesRemainingInChunk == 0)
      {
        this.hasMoreChunks = false;
        this.httpEngine.readTrailers();
        endOfInput(true);
      }
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new IOException("Expected a hex chunk size, but was " + str1);
    }
  }
  
  public int available()
    throws IOException
  {
    checkNotClosed();
    if ((!this.hasMoreChunks) || (this.bytesRemainingInChunk == -1)) {
      return 0;
    }
    return Math.min(this.in.available(), this.bytesRemainingInChunk);
  }
  
  public void close()
    throws IOException
  {
    if (this.closed) {}
    do
    {
      return;
      this.closed = true;
    } while (!this.hasMoreChunks);
    unexpectedEndOfInput();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    Arrays.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    checkNotClosed();
    if (!this.hasMoreChunks) {
      paramInt1 = -1;
    }
    do
    {
      do
      {
        return paramInt1;
        if ((this.bytesRemainingInChunk == 0) || (this.bytesRemainingInChunk == -1))
        {
          readChunkSize();
          if (!this.hasMoreChunks) {
            return -1;
          }
        }
        paramInt2 = this.in.read(paramArrayOfByte, paramInt1, Math.min(paramInt2, this.bytesRemainingInChunk));
        if (paramInt2 == -1)
        {
          unexpectedEndOfInput();
          throw new IOException("unexpected end of stream");
        }
        this.bytesRemainingInChunk -= paramInt2;
        cacheWrite(paramArrayOfByte, paramInt1, paramInt2);
        paramInt1 = paramInt2;
      } while (this.bytesRemainingInChunk != 0);
      paramInt1 = paramInt2;
    } while (this.in.available() < MIN_LAST_CHUNK_LENGTH);
    readChunkSize();
    return paramInt2;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\ChunkedInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */