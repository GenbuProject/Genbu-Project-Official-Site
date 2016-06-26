package com.microsoft.onlineid.exception;

public class NetworkException
  extends AuthenticationException
{
  private static final long serialVersionUID = 1L;
  
  public NetworkException()
  {
    this("No internet connection");
  }
  
  public NetworkException(String paramString)
  {
    super(paramString);
  }
  
  public NetworkException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public NetworkException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\exception\NetworkException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */