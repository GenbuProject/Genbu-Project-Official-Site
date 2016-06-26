package com.microsoft.onlineid.sts.exception;

import com.microsoft.onlineid.exception.NetworkException;

public class RequestThrottledException
  extends NetworkException
{
  private static final long serialVersionUID = 1L;
  
  public RequestThrottledException() {}
  
  public RequestThrottledException(String paramString)
  {
    super(paramString);
  }
  
  public RequestThrottledException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public RequestThrottledException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\exception\RequestThrottledException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */