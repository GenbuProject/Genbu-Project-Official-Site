package com.integralblue.httpresponsecache.compat;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URLs
{
  public static final int getEffectivePort(URL paramURL)
  {
    return URIs.getEffectivePort(paramURL.getProtocol(), paramURL.getPort());
  }
  
  public static final URI toURILenient(URL paramURL)
    throws URISyntaxException
  {
    return paramURL.toURI();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\URLs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */