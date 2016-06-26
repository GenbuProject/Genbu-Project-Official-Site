package com.microsoft.bond.io;

import com.microsoft.bond.BondBlob;
import com.microsoft.bond.BondException;
import java.io.IOException;
import java.io.InputStream;

public class GenericBondInputStream
  extends BondInputStream
{
  private final InputStream stream;
  
  public GenericBondInputStream(InputStream paramInputStream)
  {
    this.stream = paramInputStream;
  }
  
  public BondInputStream clone(boolean paramBoolean)
  {
    throw new UnsupportedOperationException();
  }
  
  public void close()
    throws IOException
  {
    this.stream.close();
  }
  
  public int getPosition()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isCloneable()
  {
    return false;
  }
  
  public boolean isSeekable()
  {
    return false;
  }
  
  public byte read()
  {
    try
    {
      int i = this.stream.read();
      return (byte)i;
    }
    catch (IOException localIOException)
    {
      throw new BondException(localIOException);
    }
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      paramInt1 = this.stream.read(paramArrayOfByte, paramInt1, paramInt2);
      return paramInt1;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new BondException(paramArrayOfByte);
    }
  }
  
  public BondBlob readBlob(int paramInt)
  {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  public int setPosition(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public int setPositionRelative(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\io\GenericBondInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */