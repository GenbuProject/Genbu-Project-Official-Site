package com.microsoft.onlineid.sts.exception;

import java.util.Locale;

public class StsParseException
  extends InvalidResponseException
{
  private static final long serialVersionUID = 1L;
  
  public StsParseException() {}
  
  public StsParseException(String paramString, Throwable paramThrowable, Object... paramVarArgs)
  {
    super(String.format(Locale.US, paramString, paramVarArgs), paramThrowable);
  }
  
  public StsParseException(String paramString, Object... paramVarArgs)
  {
    super(String.format(Locale.US, paramString, paramVarArgs));
  }
  
  public StsParseException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\exception\StsParseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */