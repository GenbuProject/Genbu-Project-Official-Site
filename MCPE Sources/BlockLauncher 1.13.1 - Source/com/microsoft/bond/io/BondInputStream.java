package com.microsoft.bond.io;

import com.microsoft.bond.BondBlob;
import java.io.IOException;

public abstract class BondInputStream
  implements BondStream, Seekable
{
  public abstract BondInputStream clone(boolean paramBoolean)
    throws IOException;
  
  public abstract boolean isCloneable();
  
  public abstract byte read()
    throws IOException;
  
  public abstract int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract BondBlob readBlob(int paramInt)
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\io\BondInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */