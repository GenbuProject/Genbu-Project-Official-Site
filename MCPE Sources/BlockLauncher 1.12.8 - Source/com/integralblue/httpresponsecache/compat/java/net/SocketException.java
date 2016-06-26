package com.integralblue.httpresponsecache.compat.java.net;

public class SocketException
  extends java.net.SocketException
{
  public SocketException() {}
  
  public SocketException(String paramString)
  {
    super(paramString);
  }
  
  public SocketException(String paramString, Throwable paramThrowable)
  {
    super(paramString + "\n" + paramThrowable.toString());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\java\net\SocketException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */