package com.microsoft.onlineid.internal.transport;

import android.content.Context;
import android.text.TextUtils;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.PackageInfoHelper;
import com.microsoft.onlineid.internal.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;

public class Transport
{
  private static final String GetMethod = "GET";
  private static final String PostMethod = "POST";
  public static final String SdkIdentifier = "MsaAndroidSdk";
  private int _connectionTimeoutMilliseconds = 60000;
  private String _customUserAgentString;
  private HttpsURLConnectionWrapper _httpsURLConnectionWrapper;
  private int _readTimeoutMilliseconds = 30000;
  
  public static String buildUserAgentString(Context paramContext)
  {
    return mergeUserAgentStrings(paramContext.getPackageName() + "/" + PackageInfoHelper.getCurrentAppVersionName(paramContext), "MsaAndroidSdk/" + Resources.getSdkVersion(paramContext));
  }
  
  private void initializeConnection(URL paramURL)
    throws NetworkException
  {
    if (this._httpsURLConnectionWrapper == null) {
      this._httpsURLConnectionWrapper = new HttpsURLConnectionWrapper(paramURL);
    }
    for (;;)
    {
      try
      {
        this._httpsURLConnectionWrapper.openConnection();
        this._httpsURLConnectionWrapper.setConnectTimeout(this._connectionTimeoutMilliseconds);
        this._httpsURLConnectionWrapper.setReadTimeout(this._readTimeoutMilliseconds);
        this._httpsURLConnectionWrapper.setDoInput(true);
        this._httpsURLConnectionWrapper.setUseCaches(false);
        setUserAgent();
        return;
      }
      catch (IOException paramURL)
      {
        throw new NetworkException(paramURL.getMessage(), paramURL);
      }
      this._httpsURLConnectionWrapper.setUrl(paramURL);
    }
  }
  
  public static String mergeUserAgentStrings(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return paramString2;
    }
    if (TextUtils.isEmpty(paramString2)) {
      return paramString1;
    }
    return paramString1 + "; " + paramString2;
  }
  
  private void setUserAgent()
  {
    if (!TextUtils.isEmpty(this._customUserAgentString)) {}
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      this._httpsURLConnectionWrapper.addRequestProperty("User-Agent", mergeUserAgentStrings(System.getProperty("http.agent"), this._customUserAgentString));
      return;
    }
  }
  
  public void addRequestProperty(String paramString1, String paramString2)
  {
    this._httpsURLConnectionWrapper.addRequestProperty(paramString1, paramString2);
  }
  
  void appendCustomUserAgentString(String paramString)
  {
    this._customUserAgentString = mergeUserAgentStrings(this._customUserAgentString, paramString);
  }
  
  public void closeConnection()
  {
    this._httpsURLConnectionWrapper.disconnect();
  }
  
  public int getConnectionTimeoutMilliseconds()
  {
    return this._connectionTimeoutMilliseconds;
  }
  
  public int getReadTimeoutMilliseconds()
  {
    return this._readTimeoutMilliseconds;
  }
  
  public OutputStream getRequestStream()
    throws NetworkException
  {
    if (this._httpsURLConnectionWrapper.getRequestMethod().equals("GET")) {
      throw new NetworkException("A GET request cannot have an OutputStream");
    }
    try
    {
      OutputStream localOutputStream = this._httpsURLConnectionWrapper.getOutputStream();
      return localOutputStream;
    }
    catch (IOException localIOException)
    {
      throw new NetworkException(localIOException);
    }
  }
  
  public int getResponseCode()
    throws NetworkException
  {
    try
    {
      int i = this._httpsURLConnectionWrapper.getResponseCode();
      return i;
    }
    catch (IOException localIOException)
    {
      throw new NetworkException(localIOException);
    }
  }
  
  public long getResponseDate()
  {
    return this._httpsURLConnectionWrapper.getDate();
  }
  
  public InputStream getResponseStream()
    throws NetworkException
  {
    try
    {
      if (this._httpsURLConnectionWrapper.getResponseCode() == 200) {
        return this._httpsURLConnectionWrapper.getInputStream();
      }
      InputStream localInputStream = this._httpsURLConnectionWrapper.getErrorStream();
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      throw new NetworkException(localIOException);
    }
  }
  
  public void openGetRequest(URL paramURL)
    throws NetworkException
  {
    if (paramURL == null) {
      throw new IllegalArgumentException("URL cannot be null");
    }
    try
    {
      initializeConnection(paramURL);
      this._httpsURLConnectionWrapper.setRequestMethod("GET");
      return;
    }
    catch (ProtocolException paramURL)
    {
      throw new NetworkException(paramURL.getMessage(), paramURL);
    }
  }
  
  public void openPostRequest(URL paramURL)
    throws NetworkException
  {
    if (paramURL == null) {
      throw new IllegalArgumentException("URL cannot be null");
    }
    try
    {
      initializeConnection(paramURL);
      this._httpsURLConnectionWrapper.setRequestMethod("POST");
      this._httpsURLConnectionWrapper.setDoOutput(true);
      return;
    }
    catch (IOException paramURL)
    {
      throw new NetworkException(paramURL.getMessage(), paramURL);
    }
  }
  
  public void setConnectionTimeoutMilliseconds(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Connection timeout value is out of range");
    }
    this._connectionTimeoutMilliseconds = paramInt;
  }
  
  void setHttpsURLConnectionWrapper(HttpsURLConnectionWrapper paramHttpsURLConnectionWrapper)
  {
    this._httpsURLConnectionWrapper = paramHttpsURLConnectionWrapper;
  }
  
  public void setReadTimeoutMilliseconds(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Read timeout value is out of range");
    }
    this._readTimeoutMilliseconds = paramInt;
  }
  
  public void setUseCaches(boolean paramBoolean)
  {
    this._httpsURLConnectionWrapper.setUseCaches(paramBoolean);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\transport\Transport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */