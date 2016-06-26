package com.microsoft.onlineid.sts.exception;

import com.microsoft.onlineid.exception.InternalException;

public class InvalidResponseException
  extends InternalException
{
  private static final long serialVersionUID = 1L;
  
  public InvalidResponseException() {}
  
  public InvalidResponseException(String paramString)
  {
    super(paramString);
  }
  
  public InvalidResponseException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public InvalidResponseException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\exception\InvalidResponseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */