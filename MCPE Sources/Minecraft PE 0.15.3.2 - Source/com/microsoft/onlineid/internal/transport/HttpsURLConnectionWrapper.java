package com.microsoft.onlineid.internal.transport;

import com.microsoft.onlineid.internal.Assertion;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpsURLConnectionWrapper
{
  private HttpsURLConnection _connection;
  private URL _url;
  
  public HttpsURLConnectionWrapper(URL paramURL)
  {
    if (paramURL == null) {
      throw new IllegalArgumentException("URL cannot be null");
    }
    this._url = paramURL;
  }
  
  private void verifyConnectionIsOpened()
  {
    if (this._connection == null) {
      throw new IllegalStateException("openConnection should have been called first");
    }
  }
  
  public void addRequestProperty(String paramString1, String paramString2)
  {
    verifyConnectionIsOpened();
    this._connection.addRequestProperty(paramString1, paramString2);
  }
  
  public void disconnect()
  {
    verifyConnectionIsOpened();
    this._connection.disconnect();
  }
  
  public int getContentLength()
  {
    verifyConnectionIsOpened();
    return this._connection.getContentLength();
  }
  
  public long getDate()
  {
    verifyConnectionIsOpened();
    return this._connection.getDate();
  }
  
  public InputStream getErrorStream()
  {
    verifyConnectionIsOpened();
    return this._connection.getErrorStream();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    verifyConnectionIsOpened();
    return this._connection.getInputStream();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    verifyConnectionIsOpened();
    return this._connection.getOutputStream();
  }
  
  public String getRequestMethod()
  {
    verifyConnectionIsOpened();
    return this._connection.getRequestMethod();
  }
  
  public int getResponseCode()
    throws IOException
  {
    verifyConnectionIsOpened();
    return this._connection.getResponseCode();
  }
  
  public void openConnection()
    throws IOException
  {
    this._connection = ((HttpsURLConnection)this._url.openConnection());
  }
  
  public void setConnectTimeout(int paramInt)
  {
    verifyConnectionIsOpened();
    this._connection.setConnectTimeout(paramInt);
  }
  
  public void setDoInput(boolean paramBoolean)
  {
    verifyConnectionIsOpened();
    this._connection.setDoInput(paramBoolean);
  }
  
  public void setDoOutput(boolean paramBoolean)
  {
    verifyConnectionIsOpened();
    this._connection.setDoOutput(paramBoolean);
  }
  
  public void setReadTimeout(int paramInt)
  {
    verifyConnectionIsOpened();
    this._connection.setReadTimeout(paramInt);
  }
  
  public void setRequestMethod(String paramString)
    throws ProtocolException
  {
    verifyConnectionIsOpened();
    this._connection.setRequestMethod(paramString);
  }
  
  public void setUrl(URL paramURL)
  {
    try
    {
      disconnect();
      this._url = paramURL;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        Assertion.check(false);
      }
    }
  }
  
  public void setUseCaches(boolean paramBoolean)
  {
    verifyConnectionIsOpened();
    this._connection.setUseCaches(paramBoolean);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\transport\HttpsURLConnectionWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */