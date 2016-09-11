package net.zhuoweizhang.mcpelauncher.texture.tga;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LEDataInputStream
  extends FilterInputStream
  implements DataInput
{
  DataInputStream dataIn;
  
  public LEDataInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
    this.dataIn = new DataInputStream(paramInputStream);
  }
  
  public static final String readUTF(DataInput paramDataInput)
    throws IOException
  {
    return new String();
  }
  
  public void close()
    throws IOException
  {
    this.dataIn.close();
  }
  
  public final int read(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      int i = this.dataIn.read(paramArrayOfByte, 0, paramArrayOfByte.length);
      return i;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public final int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      paramInt1 = this.dataIn.read(paramArrayOfByte, paramInt1, paramInt2);
      return paramInt1;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public final boolean readBoolean()
    throws IOException
  {
    int i = this.dataIn.read();
    if (i < 0) {
      throw new EOFException();
    }
    return i != 0;
  }
  
  public final byte readByte()
    throws IOException
  {
    int i = this.dataIn.read();
    if (i < 0) {
      throw new EOFException();
    }
    return (byte)i;
  }
  
  public final char readChar()
    throws IOException
  {
    int i = this.dataIn.read();
    int j = this.dataIn.read();
    if ((i | j) < 0) {
      throw new EOFException();
    }
    return (char)((i << 0) + (j << 8));
  }
  
  public final double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(readLong());
  }
  
  public final float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(readInt());
  }
  
  public final void readFully(byte[] paramArrayOfByte)
    throws IOException
  {
    this.dataIn.readFully(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public final void readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.dataIn.readFully(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public final int readInt()
    throws IOException
  {
    int i = this.dataIn.read();
    int j = this.dataIn.read();
    int k = this.dataIn.read();
    int m = this.dataIn.read();
    if ((i | j | k | m) < 0) {
      throw new EOFException();
    }
    return (i << 0) + (j << 8) + (k << 16) + (m << 24);
  }
  
  public final String readLine()
    throws IOException
  {
    return new String();
  }
  
  public final long readLong()
    throws IOException
  {
    int i = readInt();
    int j = readInt();
    return (i & 0xFFFFFFFF) + (j << 32);
  }
  
  public final short readShort()
    throws IOException
  {
    int i = this.dataIn.read();
    int j = this.dataIn.read();
    if ((i | j) < 0) {
      throw new EOFException();
    }
    return (short)((i << 0) + (j << 8));
  }
  
  public final String readUTF()
    throws IOException
  {
    return new String();
  }
  
  public final int readUnsignedByte()
    throws IOException
  {
    int i = this.dataIn.read();
    if (i < 0) {
      throw new EOFException();
    }
    return i;
  }
  
  public final int readUnsignedShort()
    throws IOException
  {
    int i = this.dataIn.read();
    int j = this.dataIn.read();
    if ((i | j) < 0) {
      throw new EOFException();
    }
    return (i << 0) + (j << 8);
  }
  
  public final int skipBytes(int paramInt)
    throws IOException
  {
    return this.dataIn.skipBytes(paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\tga\LEDataInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */