package com.microsoft.onlineid.sts.exception;

import java.util.Locale;

public class StsSignatureException
  extends StsParseException
{
  private static final long serialVersionUID = 1L;
  
  public StsSignatureException() {}
  
  public StsSignatureException(String paramString, Throwable paramThrowable, Object... paramVarArgs)
  {
    super(String.format(Locale.US, paramString, paramVarArgs), paramThrowable, new Object[0]);
  }
  
  public StsSignatureException(String paramString, Object... paramVarArgs)
  {
    super(String.format(Locale.US, paramString, paramVarArgs), new Object[0]);
  }
  
  public StsSignatureException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\exception\StsSignatureException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */