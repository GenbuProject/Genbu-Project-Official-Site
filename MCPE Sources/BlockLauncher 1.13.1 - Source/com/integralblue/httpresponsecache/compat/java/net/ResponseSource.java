package com.integralblue.httpresponsecache.compat.java.net;

public enum ResponseSource
{
  CACHE,  CONDITIONAL_CACHE,  NETWORK;
  
  private ResponseSource() {}
  
  public boolean requiresConnection()
  {
    return (this == CONDITIONAL_CACHE) || (this == NETWORK);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\java\net\ResponseSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */