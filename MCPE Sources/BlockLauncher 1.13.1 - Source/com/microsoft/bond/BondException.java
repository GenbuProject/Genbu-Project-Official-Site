package com.microsoft.bond;

import java.io.IOException;

public class BondException
  extends RuntimeException
{
  private static final long serialVersionUID = 1L;
  
  public BondException(IOException paramIOException)
  {
    super(paramIOException);
  }
  
  public BondException(String paramString)
  {
    super(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\BondException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */