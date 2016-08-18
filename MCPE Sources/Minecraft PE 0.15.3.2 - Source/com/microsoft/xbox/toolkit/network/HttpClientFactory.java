package com.microsoft.xbox.toolkit.network;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpClientFactory
{
  private static final int CONNECTION_PER_ROUTE = 16;
  private static final int DEFAULT_TIMEOUT_IN_SECONDS = 40;
  private static final int MAX_TOTAL_CONNECTIONS = 32;
  public static HttpClientFactory networkOperationsFactory = new HttpClientFactory();
  public static HttpClientFactory noRedirectNetworkOperationsFactory = new HttpClientFactory(false);
  public static HttpClientFactory textureFactory = new HttpClientFactory(true);
  private AbstractXLEHttpClient client = null;
  private AbstractXLEHttpClient clientWithTimeoutOverride = null;
  private ClientConnectionManager connectionManager = null;
  private Object httpSyncObject = new Object();
  private HttpParams params = new BasicHttpParams();
  
  public HttpClientFactory()
  {
    this(false);
  }
  
  public HttpClientFactory(boolean paramBoolean)
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
    HttpProtocolParams.setVersion(this.params, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(this.params, "UTF-8");
    HttpProtocolParams.setUseExpectContinue(this.params, false);
    HttpClientParams.setRedirecting(this.params, paramBoolean);
    if (XboxLiveEnvironment.Instance().getProxyEnabled())
    {
      HttpHost localHttpHost = new HttpHost("itgproxy.redmond.corp.microsoft.com", 80);
      this.params.setParameter("http.route.default-proxy", localHttpHost);
    }
    HttpConnectionParams.setConnectionTimeout(this.params, 40000);
    HttpConnectionParams.setSoTimeout(this.params, 40000);
    HttpConnectionParams.setSocketBufferSize(this.params, 8192);
    ConnManagerParams.setMaxConnectionsPerRoute(this.params, new ConnPerRouteBean(16));
    ConnManagerParams.setMaxTotalConnections(this.params, 32);
    this.connectionManager = new ThreadSafeClientConnManager(this.params, localSchemeRegistry);
  }
  
  public ClientConnectionManager getClientConnectionManager()
  {
    return this.connectionManager;
  }
  
  public AbstractXLEHttpClient getHttpClient(int paramInt)
  {
    Object localObject1 = this.httpSyncObject;
    if (paramInt > 0) {
      try
      {
        if (this.clientWithTimeoutOverride == null)
        {
          localObject2 = this.params.copy();
          HttpConnectionParams.setConnectionTimeout((HttpParams)localObject2, paramInt * 1000);
          HttpConnectionParams.setSoTimeout((HttpParams)localObject2, paramInt * 1000);
          localObject2 = new XLEHttpClient(this.connectionManager, (HttpParams)localObject2);
          return (AbstractXLEHttpClient)localObject2;
        }
        Object localObject2 = this.clientWithTimeoutOverride;
        return (AbstractXLEHttpClient)localObject2;
      }
      finally {}
    }
    if (this.client == null) {
      this.client = new XLEHttpClient(this.connectionManager, this.params);
    }
    AbstractXLEHttpClient localAbstractXLEHttpClient = this.client;
    return localAbstractXLEHttpClient;
  }
  
  public HttpParams getHttpParams()
  {
    return this.params;
  }
  
  public void setHttpClient(AbstractXLEHttpClient paramAbstractXLEHttpClient) {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\network\HttpClientFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */