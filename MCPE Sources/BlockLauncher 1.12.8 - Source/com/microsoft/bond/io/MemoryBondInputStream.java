package com.microsoft.bond.io;

import com.microsoft.bond.BondBlob;
import com.microsoft.bond.BondException;
import java.io.EOFException;
import java.io.IOException;

public class MemoryBondInputStream
  extends BondInputStream
{
  private final byte[] buffer;
  private final int bufferLength;
  private final int bufferOffset;
  private int readPosition;
  
  public MemoryBondInputStream(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public MemoryBondInputStream(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.bufferOffset = paramInt1;
    this.bufferLength = paramInt2;
    this.readPosition = 0;
  }
  
  private void validateNewPosition(int paramInt)
  {
    if (paramInt < 0) {
      throw new BondException(String.format("Invalid stream position [%s].", new Object[] { Integer.valueOf(paramInt) }));
    }
    if (paramInt > this.bufferLength) {
      throw new BondException(String.format("Position [%s] is past the end of the buffer.", new Object[] { Integer.valueOf(paramInt) }));
    }
  }
  
  private void validateRead(int paramInt)
    throws EOFException
  {
    if (this.readPosition + paramInt > this.bufferLength) {
      throw new EOFException(String.format("EOF reached. Trying to read [%d] bytes", new Object[] { Integer.valueOf(paramInt) }));
    }
  }
  
  public BondInputStream clone(boolean paramBoolean)
  {
    MemoryBondInputStream localMemoryBondInputStream = new MemoryBondInputStream(this.buffer, this.bufferOffset, this.bufferLength);
    localMemoryBondInputStream.readPosition = this.readPosition;
    return localMemoryBondInputStream;
  }
  
  public void close()
    throws IOException
  {}
  
  public int getPosition()
  {
    return this.readPosition;
  }
  
  public boolean isCloneable()
  {
    return true;
  }
  
  public boolean isSeekable()
  {
    return true;
  }
  
  public byte read()
    throws EOFException
  {
    validateRead(1);
    this.readPosition += 1;
    return this.buffer[(this.bufferOffset + this.readPosition - 1)];
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws EOFException
  {
    validateRead(paramInt2);
    System.arraycopy(this.buffer, this.bufferOffset + this.readPosition, paramArrayOfByte, paramInt1, paramInt2);
    this.readPosition += paramInt2;
    return paramInt2;
  }
  
  public BondBlob readBlob(int paramInt)
    throws IOException
  {
    return new BondBlob(this, paramInt);
  }
  
  public int setPosition(int paramInt)
  {
    validateNewPosition(paramInt);
    this.readPosition = paramInt;
    return this.readPosition;
  }
  
  public int setPositionRelative(int paramInt)
  {
    paramInt = this.readPosition + paramInt;
    validateNewPosition(paramInt);
    this.readPosition = paramInt;
    return this.readPosition;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\io\MemoryBondInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */