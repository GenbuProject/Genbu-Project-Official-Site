package com.microsoft.onlineid.exception;

public abstract class AuthenticationException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  
  public AuthenticationException() {}
  
  public AuthenticationException(String paramString)
  {
    super(paramString);
  }
  
  public AuthenticationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public AuthenticationException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\exception\AuthenticationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */