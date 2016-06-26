package com.microsoft.onlineid.internal.exception;

import com.microsoft.onlineid.exception.AuthenticationException;

public class AccountNotFoundException
  extends AuthenticationException
{
  private static final long serialVersionUID = 1L;
  
  public AccountNotFoundException() {}
  
  public AccountNotFoundException(String paramString)
  {
    super(paramString);
  }
  
  public AccountNotFoundException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public AccountNotFoundException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\exception\AccountNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */