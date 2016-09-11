package com.microsoft.bond.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileBondOutputStream
  extends BondOutputStream
{
  private final FileOutputStream stream;
  
  public FileBondOutputStream(FileOutputStream paramFileOutputStream)
  {
    this.stream = paramFileOutputStream;
  }
  
  public void close()
    throws IOException
  {
    this.stream.close();
  }
  
  public int getPosition()
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isSeekable()
  {
    return false;
  }
  
  public int setPosition(int paramInt)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public int setPositionRelative(int paramInt)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public void write(byte paramByte)
    throws IOException
  {
    this.stream.write(paramByte);
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.stream.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\io\FileBondOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */