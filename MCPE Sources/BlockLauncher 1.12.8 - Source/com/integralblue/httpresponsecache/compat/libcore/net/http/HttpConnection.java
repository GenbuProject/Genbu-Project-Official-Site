package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.URIs;
import com.integralblue.httpresponsecache.compat.libcore.io.IoUtils;
import com.integralblue.httpresponsecache.compat.libcore.util.Objects;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

final class HttpConnection
{
  private final Address address;
  private InputStream inputStream;
  private OutputStream outputStream;
  private boolean recycled = false;
  private final Socket socket;
  private InputStream sslInputStream;
  private OutputStream sslOutputStream;
  private SSLSocket sslSocket;
  private SSLSocket unverifiedSocket;
  
  private HttpConnection(Address paramAddress, int paramInt)
    throws IOException
  {
    this.address = paramAddress;
    Socket localSocket = null;
    InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(paramAddress.socketHost);
    int i = 0;
    for (;;)
    {
      if (i < arrayOfInetAddress.length) {
        if ((paramAddress.proxy == null) || (paramAddress.proxy.type() == Proxy.Type.HTTP)) {
          break label96;
        }
      }
      for (localSocket = new Socket(paramAddress.proxy);; localSocket = new Socket()) {
        try
        {
          localSocket.connect(new InetSocketAddress(arrayOfInetAddress[i], paramAddress.socketPort), paramInt);
          this.socket = localSocket;
          return;
        }
        catch (IOException localIOException)
        {
          label96:
          if (i != arrayOfInetAddress.length - 1) {
            break;
          }
          throw localIOException;
          i += 1;
        }
      }
    }
  }
  
  public static HttpConnection connect(URI paramURI, SSLSocketFactory paramSSLSocketFactory, Proxy paramProxy, boolean paramBoolean, int paramInt)
    throws IOException
  {
    if (paramProxy != null)
    {
      if (paramProxy.type() == Proxy.Type.DIRECT) {}
      for (paramURI = new Address(paramURI, paramSSLSocketFactory);; paramURI = new Address(paramURI, paramSSLSocketFactory, paramProxy, paramBoolean)) {
        return HttpConnectionPool.INSTANCE.get(paramURI, paramInt);
      }
    }
    paramProxy = ProxySelector.getDefault();
    Object localObject1 = paramProxy.select(paramURI);
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Proxy localProxy = (Proxy)((Iterator)localObject1).next();
        if (localProxy.type() != Proxy.Type.DIRECT) {
          try
          {
            Object localObject2 = new Address(paramURI, paramSSLSocketFactory, localProxy, paramBoolean);
            localObject2 = HttpConnectionPool.INSTANCE.get((Address)localObject2, paramInt);
            return (HttpConnection)localObject2;
          }
          catch (IOException localIOException)
          {
            paramProxy.connectFailed(paramURI, localProxy.address(), localIOException);
          }
        }
      }
    }
    return HttpConnectionPool.INSTANCE.get(new Address(paramURI, paramSSLSocketFactory), paramInt);
  }
  
  public void closeSocketAndStreams()
  {
    IoUtils.closeQuietly(this.sslOutputStream);
    IoUtils.closeQuietly(this.sslInputStream);
    IoUtils.closeQuietly(this.sslSocket);
    IoUtils.closeQuietly(this.outputStream);
    IoUtils.closeQuietly(this.inputStream);
    IoUtils.closeQuietly(this.socket);
  }
  
  public Address getAddress()
  {
    return this.address;
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    if (this.sslSocket != null)
    {
      if (this.sslInputStream == null) {
        this.sslInputStream = this.sslSocket.getInputStream();
      }
      return this.sslInputStream;
    }
    if (this.inputStream == null) {
      if (!this.address.requiresTunnel) {
        break label65;
      }
    }
    label65:
    for (Object localObject = this.socket.getInputStream();; localObject = new BufferedInputStream(this.socket.getInputStream(), 128))
    {
      this.inputStream = ((InputStream)localObject);
      return this.inputStream;
    }
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    if (this.sslSocket != null)
    {
      if (this.sslOutputStream == null) {
        this.sslOutputStream = this.sslSocket.getOutputStream();
      }
      return this.sslOutputStream;
    }
    if (this.outputStream == null) {
      this.outputStream = this.socket.getOutputStream();
    }
    return this.outputStream;
  }
  
  public SSLSocket getSecureSocketIfConnected()
  {
    return this.sslSocket;
  }
  
  protected Socket getSocket()
  {
    if (this.sslSocket != null) {
      return this.sslSocket;
    }
    return this.socket;
  }
  
  protected boolean isEligibleForRecycling()
  {
    return (!this.socket.isClosed()) && (!this.socket.isInputShutdown()) && (!this.socket.isOutputShutdown());
  }
  
  public boolean isRecycled()
  {
    return this.recycled;
  }
  
  public void setRecycled()
  {
    this.recycled = true;
  }
  
  public void setSoTimeout(int paramInt)
    throws SocketException
  {
    this.socket.setSoTimeout(paramInt);
  }
  
  public void setupSecureSocket(SSLSocketFactory paramSSLSocketFactory, boolean paramBoolean)
    throws IOException
  {
    this.unverifiedSocket = ((SSLSocket)paramSSLSocketFactory.createSocket(this.socket, this.address.uriHost, this.address.uriPort, true));
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        paramSSLSocketFactory = this.unverifiedSocket.getClass();
        paramSSLSocketFactory.getMethod("setEnabledCompressionMethods", new Class[] { String[].class }).invoke(this.unverifiedSocket, new Object[] { { "ZLIB" } });
        paramSSLSocketFactory.getMethod("setUseSessionTickets", new Class[] { Boolean.TYPE }).invoke(this.unverifiedSocket, new Object[] { Boolean.valueOf(true) });
        paramSSLSocketFactory.getMethod("setHostname", new Class[] { String.class }).invoke(this.unverifiedSocket, new Object[] { this.address.socketHost });
        this.unverifiedSocket.startHandshake();
        return;
      }
      catch (Exception paramSSLSocketFactory)
      {
        this.unverifiedSocket.setEnabledProtocols(new String[] { "SSLv3" });
        continue;
      }
      this.unverifiedSocket.setEnabledProtocols(new String[] { "SSLv3" });
    }
  }
  
  public SSLSocket verifySecureSocketHostname(HostnameVerifier paramHostnameVerifier)
    throws IOException
  {
    if (!paramHostnameVerifier.verify(this.address.uriHost, this.unverifiedSocket.getSession())) {
      throw new IOException("Hostname '" + this.address.uriHost + "' was not verified");
    }
    this.sslSocket = this.unverifiedSocket;
    return this.sslSocket;
  }
  
  public static final class Address
  {
    private final Proxy proxy;
    private final boolean requiresTunnel;
    private final String socketHost;
    private final int socketPort;
    private final SSLSocketFactory sslSocketFactory;
    private final String uriHost;
    private final int uriPort;
    
    public Address(URI paramURI, SSLSocketFactory paramSSLSocketFactory)
      throws UnknownHostException
    {
      this.proxy = null;
      this.requiresTunnel = false;
      this.uriHost = paramURI.getHost();
      this.uriPort = URIs.getEffectivePort(paramURI);
      this.sslSocketFactory = paramSSLSocketFactory;
      this.socketHost = this.uriHost;
      this.socketPort = this.uriPort;
      if (this.uriHost == null) {
        throw new UnknownHostException(paramURI.toString());
      }
    }
    
    public Address(URI paramURI, SSLSocketFactory paramSSLSocketFactory, Proxy paramProxy, boolean paramBoolean)
      throws UnknownHostException
    {
      this.proxy = paramProxy;
      this.requiresTunnel = paramBoolean;
      this.uriHost = paramURI.getHost();
      this.uriPort = URIs.getEffectivePort(paramURI);
      this.sslSocketFactory = paramSSLSocketFactory;
      paramSSLSocketFactory = paramProxy.address();
      if (!(paramSSLSocketFactory instanceof InetSocketAddress)) {
        throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + paramSSLSocketFactory.getClass());
      }
      paramSSLSocketFactory = (InetSocketAddress)paramSSLSocketFactory;
      this.socketHost = paramSSLSocketFactory.getHostName();
      this.socketPort = paramSSLSocketFactory.getPort();
      if (this.uriHost == null) {
        throw new UnknownHostException(paramURI.toString());
      }
    }
    
    public HttpConnection connect(int paramInt)
      throws IOException
    {
      return new HttpConnection(this, paramInt, null);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if ((paramObject instanceof Address))
      {
        paramObject = (Address)paramObject;
        bool1 = bool2;
        if (Objects.equal(this.proxy, ((Address)paramObject).proxy))
        {
          bool1 = bool2;
          if (this.uriHost.equals(((Address)paramObject).uriHost))
          {
            bool1 = bool2;
            if (this.uriPort == ((Address)paramObject).uriPort)
            {
              bool1 = bool2;
              if (this.requiresTunnel == ((Address)paramObject).requiresTunnel) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    
    public Proxy getProxy()
    {
      return this.proxy;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = this.uriHost.hashCode();
      int n = this.uriPort;
      int i;
      if (this.sslSocketFactory != null)
      {
        i = this.sslSocketFactory.hashCode();
        if (this.proxy == null) {
          break label89;
        }
      }
      label89:
      for (int j = this.proxy.hashCode();; j = 0)
      {
        if (this.requiresTunnel) {
          k = 1;
        }
        return ((((m + 527) * 31 + n) * 31 + i) * 31 + j) * 31 + k;
        i = 0;
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\HttpConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */