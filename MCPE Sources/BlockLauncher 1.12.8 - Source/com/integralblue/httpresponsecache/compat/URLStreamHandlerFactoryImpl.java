package com.integralblue.httpresponsecache.compat;

import com.integralblue.httpresponsecache.compat.libcore.net.http.HttpHandler;
import com.integralblue.httpresponsecache.compat.libcore.net.http.HttpsHandler;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class URLStreamHandlerFactoryImpl
  implements URLStreamHandlerFactory
{
  public URLStreamHandler createURLStreamHandler(String paramString)
  {
    if (paramString.equals("http")) {
      return new HttpHandler();
    }
    if (paramString.equals("https")) {
      return new HttpsHandler();
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\URLStreamHandlerFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */