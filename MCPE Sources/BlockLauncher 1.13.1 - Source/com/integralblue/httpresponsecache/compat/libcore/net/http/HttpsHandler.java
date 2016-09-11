package com.integralblue.httpresponsecache.compat.libcore.net.http;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public final class HttpsHandler
  extends URLStreamHandler
{
  protected int getDefaultPort()
  {
    return 443;
  }
  
  protected URLConnection openConnection(URL paramURL)
    throws IOException
  {
    return new HttpsURLConnectionImpl(paramURL, getDefaultPort());
  }
  
  protected URLConnection openConnection(URL paramURL, Proxy paramProxy)
    throws IOException
  {
    if ((paramURL == null) || (paramProxy == null)) {
      throw new IllegalArgumentException("url == null || proxy == null");
    }
    return new HttpsURLConnectionImpl(paramURL, getDefaultPort(), paramProxy);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\HttpsHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */