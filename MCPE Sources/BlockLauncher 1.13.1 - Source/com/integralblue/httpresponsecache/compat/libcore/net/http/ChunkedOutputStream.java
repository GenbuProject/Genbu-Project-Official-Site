package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class ChunkedOutputStream
  extends AbstractHttpOutputStream
{
  private static final byte[] CRLF = { 13, 10 };
  private static final byte[] FINAL_CHUNK = { 48, 13, 10, 13, 10 };
  private static final byte[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private final ByteArrayOutputStream bufferedChunk;
  private final byte[] hex = { 0, 0, 0, 0, 0, 0, 0, 0, 13, 10 };
  private final int maxChunkLength;
  private final OutputStream socketOut;
  
  public ChunkedOutputStream(OutputStream paramOutputStream, int paramInt)
  {
    this.socketOut = paramOutputStream;
    this.maxChunkLength = Math.max(1, dataLength(paramInt));
    this.bufferedChunk = new ByteArrayOutputStream(paramInt);
  }
  
  private int dataLength(int paramInt)
  {
    int j = 4;
    int i = paramInt - 4;
    while (i > 0)
    {
      j += 1;
      i >>= 4;
    }
    return paramInt - j;
  }
  
  private void writeBufferedChunkToSocket()
    throws IOException
  {
    int i = this.bufferedChunk.size();
    if (i <= 0) {
      return;
    }
    writeHex(i);
    this.bufferedChunk.writeTo(this.socketOut);
    this.bufferedChunk.reset();
    this.socketOut.write(CRLF);
  }
  
  private void writeHex(int paramInt)
    throws IOException
  {
    int i = 8;
    int j;
    int k;
    do
    {
      byte[] arrayOfByte = this.hex;
      j = i - 1;
      arrayOfByte[j] = HEX_DIGITS[(paramInt & 0xF)];
      k = paramInt >>> 4;
      i = j;
      paramInt = k;
    } while (k != 0);
    this.socketOut.write(this.hex, j, this.hex.length - j);
  }
  
  /* Error */
  public void close()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 102	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:closed	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 102	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:closed	Z
    //   19: aload_0
    //   20: invokespecial 104	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:writeBufferedChunkToSocket	()V
    //   23: aload_0
    //   24: getfield 51	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:socketOut	Ljava/io/OutputStream;
    //   27: getstatic 41	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:FINAL_CHUNK	[B
    //   30: invokevirtual 93	java/io/OutputStream:write	([B)V
    //   33: goto -22 -> 11
    //   36: astore_2
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_2
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	ChunkedOutputStream
    //   6	2	1	bool	boolean
    //   36	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	36	finally
    //   14	33	36	finally
  }
  
  /* Error */
  public void flush()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 102	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:closed	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: invokespecial 104	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:writeBufferedChunkToSocket	()V
    //   18: aload_0
    //   19: getfield 51	com/integralblue/httpresponsecache/compat/libcore/net/http/ChunkedOutputStream:socketOut	Ljava/io/OutputStream;
    //   22: invokevirtual 107	java/io/OutputStream:flush	()V
    //   25: goto -14 -> 11
    //   28: astore_2
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_2
    //   32: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	33	0	this	ChunkedOutputStream
    //   6	2	1	bool	boolean
    //   28	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	28	finally
    //   14	25	28	finally
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    for (;;)
    {
      int i;
      try
      {
        checkNotClosed();
        Arrays.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
        if (paramInt2 > 0) {
          if ((this.bufferedChunk.size() > 0) || (paramInt2 < this.maxChunkLength))
          {
            int j = Math.min(paramInt2, this.maxChunkLength - this.bufferedChunk.size());
            this.bufferedChunk.write(paramArrayOfByte, paramInt1, j);
            i = j;
            if (this.bufferedChunk.size() == this.maxChunkLength)
            {
              writeBufferedChunkToSocket();
              i = j;
            }
          }
          else
          {
            i = this.maxChunkLength;
            writeHex(i);
            this.socketOut.write(paramArrayOfByte, paramInt1, i);
            this.socketOut.write(CRLF);
          }
        }
      }
      finally {}
      return;
      paramInt1 += i;
      paramInt2 -= i;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\ChunkedOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */