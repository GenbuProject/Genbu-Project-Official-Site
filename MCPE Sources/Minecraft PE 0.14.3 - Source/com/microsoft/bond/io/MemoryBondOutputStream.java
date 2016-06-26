package com.microsoft.bond.io;

import com.microsoft.bond.BondBlob;
import java.io.IOException;

public class MemoryBondOutputStream
  extends BondOutputStream
{
  private static final int DEFAULT_CAPACITY_BYTES = 1024;
  private byte[] buffer;
  private int position;
  
  public MemoryBondOutputStream()
  {
    this(1024);
  }
  
  public MemoryBondOutputStream(int paramInt)
  {
    this.buffer = new byte[paramInt];
    this.position = 0;
  }
  
  private void ensureBufferSizeForExtraBytes(int paramInt)
  {
    if (this.buffer.length >= this.position + paramInt) {
      return;
    }
    int j = this.buffer.length + (this.buffer.length >> 1);
    int i = j;
    if (j < this.position + paramInt) {
      i = this.position + paramInt;
    }
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.position);
    this.buffer = arrayOfByte;
  }
  
  public void close()
    throws IOException
  {
    this.buffer = null;
    this.position = -1;
  }
  
  public int getPosition()
    throws IOException
  {
    return this.position;
  }
  
  public boolean isSeekable()
  {
    return true;
  }
  
  public int setPosition(int paramInt)
    throws IOException
  {
    if ((paramInt < 0) || (paramInt >= this.buffer.length)) {
      throw new IllegalArgumentException(String.format("Cannot jump to position [%d]. Valid positions are from [%d] to [%d] inclusive.", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(0), Integer.valueOf(this.buffer.length - 1) }));
    }
    this.position = paramInt;
    return this.position;
  }
  
  public int setPositionRelative(int paramInt)
    throws IOException
  {
    return setPosition(this.position + paramInt);
  }
  
  public BondBlob toBondBlod()
  {
    return new BondBlob(this.buffer, 0, this.position);
  }
  
  public byte[] toByteArray()
  {
    byte[] arrayOfByte = new byte[this.position];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public void write(byte paramByte)
  {
    ensureBufferSizeForExtraBytes(1);
    this.buffer[this.position] = paramByte;
    this.position += 1;
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    ensureBufferSizeForExtraBytes(paramInt2);
    System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.position, paramInt2);
    this.position += paramInt2;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\bond\io\MemoryBondOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */