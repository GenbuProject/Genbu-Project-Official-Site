package com.microsoft.onlineid.internal.exception;

import com.microsoft.onlineid.exception.InternalException;

public class UserCancelledException
  extends InternalException
{
  private static final long serialVersionUID = 1L;
  
  public UserCancelledException() {}
  
  public UserCancelledException(String paramString)
  {
    super(paramString);
  }
  
  public UserCancelledException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public UserCancelledException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\exception\UserCancelledException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */