package com.microsoft.bond;

import com.microsoft.bond.io.BondInputStream;
import java.io.IOException;

public class BondBlob
{
  private final byte[] buffer;
  private final int length;
  private final int offset;
  
  public BondBlob()
  {
    this.buffer = null;
    this.length = 0;
    this.offset = 0;
  }
  
  public BondBlob(BondBlob paramBondBlob)
  {
    this.buffer = paramBondBlob.buffer;
    this.offset = paramBondBlob.offset;
    this.length = paramBondBlob.length;
  }
  
  public BondBlob(BondInputStream paramBondInputStream, int paramInt)
    throws IOException
  {
    this.buffer = new byte[paramInt];
    this.length = paramInt;
    this.offset = 0;
    paramBondInputStream.read(this.buffer, this.offset, this.length);
  }
  
  public BondBlob(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.offset = paramInt1;
    this.length = paramInt2;
  }
  
  public boolean equals(BondBlob paramBondBlob)
  {
    if (this.length != paramBondBlob.length) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= this.length) {
        break label55;
      }
      if (this.buffer[(this.offset + i)] != paramBondBlob.buffer[(paramBondBlob.offset + i)]) {
        break;
      }
      i += 1;
    }
    label55:
    return true;
  }
  
  public byte[] getBuffer()
  {
    return this.buffer;
  }
  
  public int getOffset()
  {
    return this.offset;
  }
  
  public int size()
  {
    return this.length;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\BondBlob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */