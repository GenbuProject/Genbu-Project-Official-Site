package com.microsoft.bond.io;

import java.io.IOException;

public abstract interface Seekable
{
  public abstract int getPosition()
    throws IOException;
  
  public abstract boolean isSeekable();
  
  public abstract int setPosition(int paramInt)
    throws IOException;
  
  public abstract int setPositionRelative(int paramInt)
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\io\Seekable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */