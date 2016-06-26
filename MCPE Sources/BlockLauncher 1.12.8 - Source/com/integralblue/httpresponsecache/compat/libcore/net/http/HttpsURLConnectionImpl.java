package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.URLs;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SecureCacheResponse;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

final class HttpsURLConnectionImpl
  extends HttpsURLConnection
{
  private final HttpUrlConnectionDelegate delegate;
  
  protected HttpsURLConnectionImpl(URL paramURL, int paramInt)
  {
    super(paramURL);
    this.delegate = new HttpUrlConnectionDelegate(paramURL, paramInt, null);
  }
  
  protected HttpsURLConnectionImpl(URL paramURL, int paramInt, Proxy paramProxy)
  {
    super(paramURL);
    this.delegate = new HttpUrlConnectionDelegate(paramURL, paramInt, paramProxy, null);
  }
  
  private void checkConnected()
  {
    if (this.delegate.getSSLSocket() == null) {
      throw new IllegalStateException("Connection has not yet been established");
    }
  }
  
  public void addRequestProperty(String paramString1, String paramString2)
  {
    this.delegate.addRequestProperty(paramString1, paramString2);
  }
  
  public void connect()
    throws IOException
  {
    this.connected = true;
    this.delegate.connect();
  }
  
  public void disconnect()
  {
    this.delegate.disconnect();
  }
  
  public boolean getAllowUserInteraction()
  {
    return this.delegate.getAllowUserInteraction();
  }
  
  public String getCipherSuite()
  {
    SecureCacheResponse localSecureCacheResponse = this.delegate.getCacheResponse();
    if (localSecureCacheResponse != null) {
      return localSecureCacheResponse.getCipherSuite();
    }
    checkConnected();
    return this.delegate.getSSLSocket().getSession().getCipherSuite();
  }
  
  public int getConnectTimeout()
  {
    return this.delegate.getConnectTimeout();
  }
  
  public Object getContent()
    throws IOException
  {
    return this.delegate.getContent();
  }
  
  public Object getContent(Class[] paramArrayOfClass)
    throws IOException
  {
    return this.delegate.getContent(paramArrayOfClass);
  }
  
  public String getContentEncoding()
  {
    return this.delegate.getContentEncoding();
  }
  
  public int getContentLength()
  {
    return this.delegate.getContentLength();
  }
  
  public String getContentType()
  {
    return this.delegate.getContentType();
  }
  
  public long getDate()
  {
    return this.delegate.getDate();
  }
  
  public boolean getDefaultUseCaches()
  {
    return this.delegate.getDefaultUseCaches();
  }
  
  public boolean getDoInput()
  {
    return this.delegate.getDoInput();
  }
  
  public boolean getDoOutput()
  {
    return this.delegate.getDoOutput();
  }
  
  public InputStream getErrorStream()
  {
    return this.delegate.getErrorStream();
  }
  
  public long getExpiration()
  {
    return this.delegate.getExpiration();
  }
  
  public String getHeaderField(int paramInt)
  {
    return this.delegate.getHeaderField(paramInt);
  }
  
  public String getHeaderField(String paramString)
  {
    return this.delegate.getHeaderField(paramString);
  }
  
  public long getHeaderFieldDate(String paramString, long paramLong)
  {
    return this.delegate.getHeaderFieldDate(paramString, paramLong);
  }
  
  public int getHeaderFieldInt(String paramString, int paramInt)
  {
    return this.delegate.getHeaderFieldInt(paramString, paramInt);
  }
  
  public String getHeaderFieldKey(int paramInt)
  {
    return this.delegate.getHeaderFieldKey(paramInt);
  }
  
  public Map<String, List<String>> getHeaderFields()
  {
    return this.delegate.getHeaderFields();
  }
  
  HttpEngine getHttpEngine()
  {
    return this.delegate.getHttpEngine();
  }
  
  public long getIfModifiedSince()
  {
    return this.delegate.getIfModifiedSince();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.delegate.getInputStream();
  }
  
  public boolean getInstanceFollowRedirects()
  {
    return this.delegate.getInstanceFollowRedirects();
  }
  
  public long getLastModified()
  {
    return this.delegate.getLastModified();
  }
  
  public Certificate[] getLocalCertificates()
  {
    Object localObject = this.delegate.getCacheResponse();
    if (localObject != null)
    {
      localObject = ((SecureCacheResponse)localObject).getLocalCertificateChain();
      if (localObject != null) {
        return (Certificate[])((List)localObject).toArray(new Certificate[((List)localObject).size()]);
      }
      return null;
    }
    checkConnected();
    return this.delegate.getSSLSocket().getSession().getLocalCertificates();
  }
  
  public Principal getLocalPrincipal()
  {
    SecureCacheResponse localSecureCacheResponse = this.delegate.getCacheResponse();
    if (localSecureCacheResponse != null) {
      return localSecureCacheResponse.getLocalPrincipal();
    }
    checkConnected();
    return this.delegate.getSSLSocket().getSession().getLocalPrincipal();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return this.delegate.getOutputStream();
  }
  
  public Principal getPeerPrincipal()
    throws SSLPeerUnverifiedException
  {
    SecureCacheResponse localSecureCacheResponse = this.delegate.getCacheResponse();
    if (localSecureCacheResponse != null) {
      return localSecureCacheResponse.getPeerPrincipal();
    }
    checkConnected();
    return this.delegate.getSSLSocket().getSession().getPeerPrincipal();
  }
  
  public Permission getPermission()
    throws IOException
  {
    return this.delegate.getPermission();
  }
  
  public int getReadTimeout()
  {
    return this.delegate.getReadTimeout();
  }
  
  public String getRequestMethod()
  {
    return this.delegate.getRequestMethod();
  }
  
  public Map<String, List<String>> getRequestProperties()
  {
    return this.delegate.getRequestProperties();
  }
  
  public String getRequestProperty(String paramString)
  {
    return this.delegate.getRequestProperty(paramString);
  }
  
  public int getResponseCode()
    throws IOException
  {
    return this.delegate.getResponseCode();
  }
  
  public String getResponseMessage()
    throws IOException
  {
    return this.delegate.getResponseMessage();
  }
  
  public Certificate[] getServerCertificates()
    throws SSLPeerUnverifiedException
  {
    Object localObject = this.delegate.getCacheResponse();
    if (localObject != null)
    {
      localObject = ((SecureCacheResponse)localObject).getServerCertificateChain();
      if (localObject != null) {
        return (Certificate[])((List)localObject).toArray(new Certificate[((List)localObject).size()]);
      }
      return null;
    }
    checkConnected();
    return this.delegate.getSSLSocket().getSession().getPeerCertificates();
  }
  
  public URL getURL()
  {
    return this.delegate.getURL();
  }
  
  public boolean getUseCaches()
  {
    return this.delegate.getUseCaches();
  }
  
  public void setAllowUserInteraction(boolean paramBoolean)
  {
    this.delegate.setAllowUserInteraction(paramBoolean);
  }
  
  public void setChunkedStreamingMode(int paramInt)
  {
    this.delegate.setChunkedStreamingMode(paramInt);
  }
  
  public void setConnectTimeout(int paramInt)
  {
    this.delegate.setConnectTimeout(paramInt);
  }
  
  public void setDefaultUseCaches(boolean paramBoolean)
  {
    this.delegate.setDefaultUseCaches(paramBoolean);
  }
  
  public void setDoInput(boolean paramBoolean)
  {
    this.delegate.setDoInput(paramBoolean);
  }
  
  public void setDoOutput(boolean paramBoolean)
  {
    this.delegate.setDoOutput(paramBoolean);
  }
  
  public void setFixedLengthStreamingMode(int paramInt)
  {
    this.delegate.setFixedLengthStreamingMode(paramInt);
  }
  
  public void setIfModifiedSince(long paramLong)
  {
    this.delegate.setIfModifiedSince(paramLong);
  }
  
  public void setInstanceFollowRedirects(boolean paramBoolean)
  {
    this.delegate.setInstanceFollowRedirects(paramBoolean);
  }
  
  public void setReadTimeout(int paramInt)
  {
    this.delegate.setReadTimeout(paramInt);
  }
  
  public void setRequestMethod(String paramString)
    throws ProtocolException
  {
    this.delegate.setRequestMethod(paramString);
  }
  
  public void setRequestProperty(String paramString1, String paramString2)
  {
    this.delegate.setRequestProperty(paramString1, paramString2);
  }
  
  public void setUseCaches(boolean paramBoolean)
  {
    this.delegate.setUseCaches(paramBoolean);
  }
  
  public String toString()
  {
    return this.delegate.toString();
  }
  
  public boolean usingProxy()
  {
    return this.delegate.usingProxy();
  }
  
  private final class HttpUrlConnectionDelegate
    extends HttpURLConnectionImpl
  {
    private HttpUrlConnectionDelegate(URL paramURL, int paramInt)
    {
      super(paramInt);
    }
    
    private HttpUrlConnectionDelegate(URL paramURL, int paramInt, Proxy paramProxy)
    {
      super(paramInt, paramProxy);
    }
    
    public SecureCacheResponse getCacheResponse()
    {
      HttpsURLConnectionImpl.HttpsEngine localHttpsEngine = (HttpsURLConnectionImpl.HttpsEngine)this.httpEngine;
      if (localHttpsEngine != null) {
        return (SecureCacheResponse)localHttpsEngine.getCacheResponse();
      }
      return null;
    }
    
    public SSLSocket getSSLSocket()
    {
      HttpsURLConnectionImpl.HttpsEngine localHttpsEngine = (HttpsURLConnectionImpl.HttpsEngine)this.httpEngine;
      if (localHttpsEngine != null) {
        return HttpsURLConnectionImpl.HttpsEngine.access$300(localHttpsEngine);
      }
      return null;
    }
    
    protected HttpEngine newHttpEngine(String paramString, RawHeaders paramRawHeaders, HttpConnection paramHttpConnection, RetryableOutputStream paramRetryableOutputStream)
      throws IOException
    {
      return new HttpsURLConnectionImpl.HttpsEngine(this, paramString, paramRawHeaders, paramHttpConnection, paramRetryableOutputStream, HttpsURLConnectionImpl.this, null);
    }
  }
  
  private static class HttpsEngine
    extends HttpEngine
  {
    private final HttpsURLConnectionImpl enclosing;
    private SSLSocket sslSocket;
    
    private HttpsEngine(HttpURLConnectionImpl paramHttpURLConnectionImpl, String paramString, RawHeaders paramRawHeaders, HttpConnection paramHttpConnection, RetryableOutputStream paramRetryableOutputStream, HttpsURLConnectionImpl paramHttpsURLConnectionImpl)
      throws IOException
    {
      super(paramString, paramRawHeaders, paramHttpConnection, paramRetryableOutputStream);
      if (paramHttpConnection != null) {}
      for (paramHttpURLConnectionImpl = paramHttpConnection.getSecureSocketIfConnected();; paramHttpURLConnectionImpl = null)
      {
        this.sslSocket = paramHttpURLConnectionImpl;
        this.enclosing = paramHttpsURLConnectionImpl;
        return;
      }
    }
    
    private boolean makeSslConnection(boolean paramBoolean)
      throws IOException
    {
      if (this.connection == null)
      {
        this.connection = openSocketConnection();
        if (this.connection.getAddress().getProxy() != null) {
          makeTunnel(this.policy, this.connection, getRequestHeaders());
        }
      }
      this.sslSocket = this.connection.getSecureSocketIfConnected();
      if (this.sslSocket != null) {
        return true;
      }
      this.connection.setupSecureSocket(this.enclosing.getSSLSocketFactory(), paramBoolean);
      return false;
    }
    
    private void makeTunnel(HttpURLConnectionImpl paramHttpURLConnectionImpl, HttpConnection paramHttpConnection, RequestHeaders paramRequestHeaders)
      throws IOException
    {
      paramRequestHeaders = paramRequestHeaders.getHeaders();
      HttpsURLConnectionImpl.ProxyConnectEngine localProxyConnectEngine;
      do
      {
        localProxyConnectEngine = new HttpsURLConnectionImpl.ProxyConnectEngine(paramHttpURLConnectionImpl, paramRequestHeaders, paramHttpConnection);
        localProxyConnectEngine.sendRequest();
        localProxyConnectEngine.readResponse();
        int i = localProxyConnectEngine.getResponseCode();
        switch (localProxyConnectEngine.getResponseCode())
        {
        default: 
          throw new IOException("Unexpected response code for CONNECT: " + i);
        case 407: 
          paramRequestHeaders = new RawHeaders(paramRequestHeaders);
        }
      } while (paramHttpURLConnectionImpl.processAuthHeader(407, localProxyConnectEngine.getResponseHeaders(), paramRequestHeaders));
      throw new IOException("Failed to authenticate with proxy");
    }
    
    protected boolean acceptCacheResponseType(CacheResponse paramCacheResponse)
    {
      return paramCacheResponse instanceof SecureCacheResponse;
    }
    
    protected void connect()
      throws IOException
    {
      try
      {
        bool = makeSslConnection(true);
        if (!bool) {
          this.sslSocket = this.connection.verifySecureSocketHostname(this.enclosing.getHostnameVerifier());
        }
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          if (((localIOException instanceof SSLHandshakeException)) && ((localIOException.getCause() instanceof CertificateException))) {
            throw localIOException;
          }
          release(false);
          boolean bool = makeSslConnection(false);
        }
      }
    }
    
    protected HttpURLConnection getHttpConnectionToCache()
    {
      return this.enclosing;
    }
    
    protected SSLSocketFactory getSslSocketFactory()
    {
      return this.enclosing.getSSLSocketFactory();
    }
    
    protected boolean includeAuthorityInRequestLine()
    {
      return false;
    }
  }
  
  private static class ProxyConnectEngine
    extends HttpEngine
  {
    public ProxyConnectEngine(HttpURLConnectionImpl paramHttpURLConnectionImpl, RawHeaders paramRawHeaders, HttpConnection paramHttpConnection)
      throws IOException
    {
      super("CONNECT", paramRawHeaders, paramHttpConnection, null);
    }
    
    protected RawHeaders getNetworkRequestHeaders()
      throws IOException
    {
      RequestHeaders localRequestHeaders = getRequestHeaders();
      URL localURL = this.policy.getURL();
      RawHeaders localRawHeaders = new RawHeaders();
      localRawHeaders.setStatusLine("CONNECT " + localURL.getHost() + ":" + URLs.getEffectivePort(localURL) + " HTTP/1.1");
      String str2 = localRequestHeaders.getHost();
      String str1 = str2;
      if (str2 == null) {
        str1 = getOriginAddress(localURL);
      }
      localRawHeaders.set("Host", str1);
      str2 = localRequestHeaders.getUserAgent();
      str1 = str2;
      if (str2 == null) {
        str1 = getDefaultUserAgent();
      }
      localRawHeaders.set("User-Agent", str1);
      str1 = localRequestHeaders.getProxyAuthorization();
      if (str1 != null) {
        localRawHeaders.set("Proxy-Authorization", str1);
      }
      localRawHeaders.set("Proxy-Connection", "Keep-Alive");
      return localRawHeaders;
    }
    
    protected boolean requiresTunnel()
    {
      return true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\HttpsURLConnectionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */