package net.zhuoweizhang.mcpelauncher.texture.tga;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LEDataOutputStream
  extends FilterOutputStream
  implements DataOutput
{
  DataOutputStream dataOut;
  
  public LEDataOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
    this.dataOut = new DataOutputStream(paramOutputStream);
  }
  
  public void close()
    throws IOException
  {
    this.dataOut.close();
  }
  
  public final void write(int paramInt)
    throws IOException
  {
    this.dataOut.write(paramInt);
  }
  
  public final void write(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      this.dataOut.write(paramArrayOfByte, 0, paramArrayOfByte.length);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      this.dataOut.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public final void writeBoolean(boolean paramBoolean)
    throws IOException
  {
    this.dataOut.writeBoolean(paramBoolean);
  }
  
  public final void writeByte(int paramInt)
    throws IOException
  {
    this.dataOut.writeByte(paramInt);
  }
  
  public final void writeBytes(String paramString)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public final void writeChar(int paramInt)
    throws IOException
  {
    this.dataOut.writeChar(paramInt >> 8 & 0xFF | (paramInt & 0xFF) << 8);
  }
  
  public final void writeChars(String paramString)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public final void writeDouble(double paramDouble)
    throws IOException
  {
    writeLong(Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void writeFloat(float paramFloat)
    throws IOException
  {
    writeInt(Float.floatToRawIntBits(paramFloat));
  }
  
  public final void writeInt(int paramInt)
    throws IOException
  {
    this.dataOut.writeInt(paramInt >>> 24 | paramInt >>> 8 & 0xFF00 | paramInt << 8 & 0xFF00 | paramInt << 24);
  }
  
  public final void writeLong(long paramLong)
    throws IOException
  {
    writeInt((int)paramLong);
    writeInt((int)(paramLong >>> 32));
  }
  
  public final void writeShort(int paramInt)
    throws IOException
  {
    this.dataOut.writeShort(paramInt >> 8 & 0xFF | (paramInt & 0xFF) << 8);
  }
  
  public final void writeUTF(String paramString)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\texture\tga\LEDataOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */