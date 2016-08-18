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


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\io\Seekable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */