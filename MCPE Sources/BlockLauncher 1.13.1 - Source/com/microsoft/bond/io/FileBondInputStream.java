package com.microsoft.bond.io;

import com.microsoft.bond.BondBlob;
import com.microsoft.bond.BondException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileBondInputStream
  extends BondInputStream
{
  private final File file;
  private int fileLength;
  private int position;
  private FileInputStream stream;
  
  public FileBondInputStream(File paramFile)
    throws FileNotFoundException
  {
    this.file = paramFile;
    resetStream();
  }
  
  private void resetStream()
    throws FileNotFoundException
  {
    this.fileLength = ((int)this.file.length());
    this.position = 0;
    this.stream = new FileInputStream(this.file);
  }
  
  public BondInputStream clone(boolean paramBoolean)
    throws IOException
  {
    FileBondInputStream localFileBondInputStream = new FileBondInputStream(this.file);
    localFileBondInputStream.setPosition(this.position);
    return localFileBondInputStream;
  }
  
  public void close()
    throws IOException
  {
    this.stream.close();
  }
  
  public int getPosition()
  {
    return this.position;
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
    throws IOException
  {
    this.position += 1;
    return (byte)this.stream.read();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    i = 0;
    for (;;)
    {
      if (i < paramInt2) {}
      try
      {
        int j = this.stream.read(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
        i = j + i;
      }
      finally
      {
        this.position = (i + this.position);
      }
    }
    this.position += i;
    return i;
  }
  
  public BondBlob readBlob(int paramInt)
    throws IOException
  {
    return new BondBlob(this, paramInt);
  }
  
  public int setPosition(int paramInt)
    throws IOException
  {
    if ((paramInt < 0) || (paramInt > this.fileLength)) {
      throw new BondException("Invalid position: " + paramInt);
    }
    if (paramInt >= this.position) {}
    for (int i = paramInt - this.position;; i = paramInt)
    {
      this.position = paramInt;
      this.stream.skip(i);
      return this.position;
      resetStream();
    }
  }
  
  public int setPositionRelative(int paramInt)
    throws IOException
  {
    setPosition(this.position + paramInt);
    return this.position;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\io\FileBondInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */