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


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\exception\RequestThrottledException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */