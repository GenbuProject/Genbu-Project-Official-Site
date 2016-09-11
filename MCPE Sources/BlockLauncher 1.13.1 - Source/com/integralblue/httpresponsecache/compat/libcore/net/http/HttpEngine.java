package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.Charsets;
import com.integralblue.httpresponsecache.compat.Strings;
import com.integralblue.httpresponsecache.compat.URLs;
import com.integralblue.httpresponsecache.compat.java.net.ExtendedResponseCache;
import com.integralblue.httpresponsecache.compat.java.net.ResponseSource;
import com.integralblue.httpresponsecache.compat.libcore.io.IoUtils;
import com.integralblue.httpresponsecache.compat.libcore.io.Streams;
import com.integralblue.httpresponsecache.compat.libcore.util.EmptyArray;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLSocketFactory;

public class HttpEngine
{
  public static final String CONNECT = "CONNECT";
  public static final int DEFAULT_CHUNK_LENGTH = 1024;
  public static final String DELETE = "DELETE";
  private static final CacheResponse GATEWAY_TIMEOUT_RESPONSE = new CacheResponse()
  {
    public InputStream getBody()
      throws IOException
    {
      return new ByteArrayInputStream(EmptyArray.BYTE);
    }
    
    public Map<String, List<String>> getHeaders()
      throws IOException
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put(null, Collections.singletonList("HTTP/1.1 504 Gateway Timeout"));
      return localHashMap;
    }
  };
  public static final String GET = "GET";
  public static final String HEAD = "HEAD";
  public static final int HTTP_CONTINUE = 100;
  public static final int MAX_REDIRECTS = 5;
  private static final int MAX_REQUEST_BUFFER_LENGTH = 32768;
  public static final String OPTIONS = "OPTIONS";
  public static final String POST = "POST";
  public static final String PUT = "PUT";
  public static final String TRACE = "TRACE";
  private boolean automaticallyReleaseConnectionToPool;
  private CacheRequest cacheRequest;
  private CacheResponse cacheResponse;
  private InputStream cachedResponseBody;
  private ResponseHeaders cachedResponseHeaders;
  protected HttpConnection connection;
  private boolean connectionReleased;
  private int httpMinorVersion = 1;
  protected final String method;
  protected final HttpURLConnectionImpl policy;
  private AbstractHttpOutputStream requestBodyOut;
  private final RequestHeaders requestHeaders;
  private OutputStream requestOut;
  private InputStream responseBodyIn;
  private final ResponseCache responseCache = ResponseCache.getDefault();
  private ResponseHeaders responseHeaders;
  private ResponseSource responseSource;
  boolean sendChunked;
  private long sentRequestMillis = -1L;
  private InputStream socketIn;
  private OutputStream socketOut;
  private boolean transparentGzip;
  private final URI uri;
  
  public HttpEngine(HttpURLConnectionImpl paramHttpURLConnectionImpl, String paramString, RawHeaders paramRawHeaders, HttpConnection paramHttpConnection, RetryableOutputStream paramRetryableOutputStream)
    throws IOException
  {
    this.policy = paramHttpURLConnectionImpl;
    this.method = paramString;
    this.connection = paramHttpConnection;
    this.requestBodyOut = paramRetryableOutputStream;
    try
    {
      this.uri = URLs.toURILenient(paramHttpURLConnectionImpl.getURL());
      this.requestHeaders = new RequestHeaders(this.uri, new RawHeaders(paramRawHeaders));
      return;
    }
    catch (URISyntaxException paramHttpURLConnectionImpl)
    {
      throw new IOException(paramHttpURLConnectionImpl.toString());
    }
  }
  
  private String getRequestLine()
  {
    if (this.httpMinorVersion == 0) {}
    for (String str = "HTTP/1.0";; str = "HTTP/1.1") {
      return this.method + " " + requestString() + " " + str;
    }
  }
  
  private InputStream getTransferStream()
    throws IOException
  {
    if (!hasResponseBody()) {
      return new FixedLengthInputStream(this.socketIn, this.cacheRequest, this, 0);
    }
    if (this.responseHeaders.isChunked()) {
      return new ChunkedInputStream(this.socketIn, this.cacheRequest, this);
    }
    if (this.responseHeaders.getContentLength() != -1) {
      return new FixedLengthInputStream(this.socketIn, this.cacheRequest, this, this.responseHeaders.getContentLength());
    }
    return new UnknownLengthHttpInputStream(this.socketIn, this.cacheRequest, this);
  }
  
  private boolean hasConnectionCloseHeader()
  {
    return ((this.responseHeaders != null) && (this.responseHeaders.hasConnectionClose())) || (this.requestHeaders.hasConnectionClose());
  }
  
  private boolean hasRequestBody()
  {
    return (this.method == "POST") || (this.method == "PUT");
  }
  
  private void initContentStream(InputStream paramInputStream)
    throws IOException
  {
    if ((this.transparentGzip) && (this.responseHeaders.isContentEncodingGzip()))
    {
      this.responseHeaders.stripContentEncoding();
      this.responseBodyIn = new GZIPInputStream(paramInputStream);
      return;
    }
    this.responseBodyIn = paramInputStream;
  }
  
  private void initResponseSource()
    throws IOException
  {
    this.responseSource = ResponseSource.NETWORK;
    if ((!this.policy.getUseCaches()) || (this.responseCache == null)) {}
    CacheResponse localCacheResponse;
    do
    {
      return;
      localCacheResponse = this.responseCache.get(this.uri, this.method, this.requestHeaders.getHeaders().toMultimap());
    } while (localCacheResponse == null);
    Object localObject = localCacheResponse.getHeaders();
    this.cachedResponseBody = localCacheResponse.getBody();
    if ((!acceptCacheResponseType(localCacheResponse)) || (localObject == null) || (this.cachedResponseBody == null))
    {
      IoUtils.closeQuietly(this.cachedResponseBody);
      return;
    }
    localObject = RawHeaders.fromMultimap((Map)localObject);
    this.cachedResponseHeaders = new ResponseHeaders(this.uri, (RawHeaders)localObject);
    long l = System.currentTimeMillis();
    this.responseSource = this.cachedResponseHeaders.chooseResponseSource(l, this.requestHeaders);
    if (this.responseSource == ResponseSource.CACHE)
    {
      this.cacheResponse = localCacheResponse;
      setResponse(this.cachedResponseHeaders, this.cachedResponseBody);
      return;
    }
    if (this.responseSource == ResponseSource.CONDITIONAL_CACHE)
    {
      this.cacheResponse = localCacheResponse;
      return;
    }
    if (this.responseSource == ResponseSource.NETWORK)
    {
      IoUtils.closeQuietly(this.cachedResponseBody);
      return;
    }
    throw new AssertionError();
  }
  
  private void maybeCache()
    throws IOException
  {
    if (this.method == "CONNECT") {}
    while ((!this.policy.getUseCaches()) || (this.responseCache == null) || (!this.responseHeaders.isCacheable(this.requestHeaders))) {
      return;
    }
    this.cacheRequest = this.responseCache.put(this.uri, getHttpConnectionToCache());
  }
  
  private void prepareRawRequestHeaders()
    throws IOException
  {
    this.requestHeaders.getHeaders().setStatusLine(getRequestLine());
    if (this.requestHeaders.getUserAgent() == null) {
      this.requestHeaders.setUserAgent(getDefaultUserAgent());
    }
    if (this.requestHeaders.getHost() == null) {
      this.requestHeaders.setHost(getOriginAddress(this.policy.getURL()));
    }
    Object localObject = System.getProperty("http.keepAlive");
    if ((this.httpMinorVersion > 0) && (this.requestHeaders.getConnection() == null) && ((localObject == null) || (Boolean.parseBoolean((String)localObject)))) {
      this.requestHeaders.setConnection("Keep-Alive");
    }
    if (this.requestHeaders.getAcceptEncoding() == null)
    {
      this.transparentGzip = true;
      this.requestHeaders.setAcceptEncoding("gzip");
    }
    if ((hasRequestBody()) && (this.requestHeaders.getContentType() == null)) {
      this.requestHeaders.setContentType("application/x-www-form-urlencoded");
    }
    long l = this.policy.getIfModifiedSince();
    if (l != 0L) {
      this.requestHeaders.setIfModifiedSince(new Date(l));
    }
    localObject = CookieHandler.getDefault();
    if (localObject != null) {
      this.requestHeaders.addCookies(((CookieHandler)localObject).get(this.uri, this.requestHeaders.getHeaders().toMultimap()));
    }
  }
  
  private void readHeaders(RawHeaders paramRawHeaders)
    throws IOException
  {
    for (;;)
    {
      localObject = Streams.readAsciiLine(this.socketIn);
      if (Strings.isEmpty((String)localObject)) {
        break;
      }
      paramRawHeaders.addLine((String)localObject);
    }
    Object localObject = CookieHandler.getDefault();
    if (localObject != null) {
      ((CookieHandler)localObject).put(this.uri, paramRawHeaders.toMultimap());
    }
  }
  
  private void readResponseHeaders()
    throws IOException
  {
    RawHeaders localRawHeaders;
    do
    {
      localRawHeaders = new RawHeaders();
      localRawHeaders.setStatusLine(Streams.readAsciiLine(this.socketIn));
      readHeaders(localRawHeaders);
    } while (localRawHeaders.getResponseCode() == 100);
    setResponse(new ResponseHeaders(this.uri, localRawHeaders), null);
  }
  
  private String requestString()
  {
    Object localObject = this.policy.getURL();
    if (includeAuthorityInRequestLine()) {
      localObject = ((URL)localObject).toString();
    }
    String str;
    do
    {
      return (String)localObject;
      str = ((URL)localObject).getFile();
      if (str == null) {
        return "/";
      }
      localObject = str;
    } while (str.startsWith("/"));
    return "/" + str;
  }
  
  private void sendSocketRequest()
    throws IOException
  {
    if (this.connection == null) {
      connect();
    }
    if ((this.socketOut != null) || (this.requestOut != null) || (this.socketIn != null)) {
      throw new IllegalStateException();
    }
    this.socketOut = this.connection.getOutputStream();
    this.requestOut = this.socketOut;
    this.socketIn = this.connection.getInputStream();
    if (hasRequestBody()) {
      initRequestBodyOut();
    }
  }
  
  private void setResponse(ResponseHeaders paramResponseHeaders, InputStream paramInputStream)
    throws IOException
  {
    if (this.responseBodyIn != null) {
      throw new IllegalStateException();
    }
    this.responseHeaders = paramResponseHeaders;
    this.httpMinorVersion = this.responseHeaders.getHeaders().getHttpMinorVersion();
    if (paramInputStream != null) {
      initContentStream(paramInputStream);
    }
  }
  
  private void writeRequestHeaders(int paramInt)
    throws IOException
  {
    if (this.sentRequestMillis != -1L) {
      throw new IllegalStateException();
    }
    byte[] arrayOfByte = Strings.getBytes(getNetworkRequestHeaders().toHeaderString(), Charsets.ISO_8859_1);
    if ((paramInt != -1) && (arrayOfByte.length + paramInt <= 32768)) {
      this.requestOut = new BufferedOutputStream(this.socketOut, arrayOfByte.length + paramInt);
    }
    this.sentRequestMillis = System.currentTimeMillis();
    this.requestOut.write(arrayOfByte);
  }
  
  protected boolean acceptCacheResponseType(CacheResponse paramCacheResponse)
  {
    return true;
  }
  
  public final void automaticallyReleaseConnectionToPool()
  {
    this.automaticallyReleaseConnectionToPool = true;
    if ((this.connection != null) && (this.connectionReleased))
    {
      HttpConnectionPool.INSTANCE.recycle(this.connection);
      this.connection = null;
    }
  }
  
  protected void connect()
    throws IOException
  {
    if (this.connection == null) {
      this.connection = openSocketConnection();
    }
  }
  
  public final CacheResponse getCacheResponse()
  {
    return this.cacheResponse;
  }
  
  public final HttpConnection getConnection()
  {
    return this.connection;
  }
  
  protected final String getDefaultUserAgent()
  {
    String str = System.getProperty("http.agent");
    if (str != null) {
      return str;
    }
    return "Java" + System.getProperty("java.version");
  }
  
  protected HttpURLConnection getHttpConnectionToCache()
  {
    return this.policy;
  }
  
  protected RawHeaders getNetworkRequestHeaders()
    throws IOException
  {
    this.requestHeaders.getHeaders().setStatusLine(getRequestLine());
    int i = this.policy.getFixedContentLength();
    if (i != -1) {
      this.requestHeaders.setContentLength(i);
    }
    for (;;)
    {
      return this.requestHeaders.getHeaders();
      if (this.sendChunked)
      {
        this.requestHeaders.setChunked();
      }
      else if ((this.requestBodyOut instanceof RetryableOutputStream))
      {
        i = ((RetryableOutputStream)this.requestBodyOut).contentLength();
        this.requestHeaders.setContentLength(i);
      }
    }
  }
  
  protected final String getOriginAddress(URL paramURL)
  {
    int i = paramURL.getPort();
    String str = paramURL.getHost();
    paramURL = str;
    if (i > 0)
    {
      paramURL = str;
      if (i != this.policy.getDefaultPort()) {
        paramURL = str + ":" + i;
      }
    }
    return paramURL;
  }
  
  public final OutputStream getRequestBody()
  {
    if (this.responseSource == null) {
      throw new IllegalStateException();
    }
    return this.requestBodyOut;
  }
  
  public final RequestHeaders getRequestHeaders()
  {
    return this.requestHeaders;
  }
  
  public final InputStream getResponseBody()
  {
    if (this.responseHeaders == null) {
      throw new IllegalStateException();
    }
    return this.responseBodyIn;
  }
  
  public final int getResponseCode()
  {
    if (this.responseHeaders == null) {
      throw new IllegalStateException();
    }
    return this.responseHeaders.getHeaders().getResponseCode();
  }
  
  public final ResponseHeaders getResponseHeaders()
  {
    if (this.responseHeaders == null) {
      throw new IllegalStateException();
    }
    return this.responseHeaders;
  }
  
  protected SSLSocketFactory getSslSocketFactory()
  {
    return null;
  }
  
  public URI getUri()
  {
    return this.uri;
  }
  
  public final boolean hasRecycledConnection()
  {
    return (this.connection != null) && (this.connection.isRecycled());
  }
  
  public final boolean hasResponse()
  {
    return this.responseHeaders != null;
  }
  
  public final boolean hasResponseBody()
  {
    int i = this.responseHeaders.getHeaders().getResponseCode();
    if (this.method == "HEAD") {}
    do
    {
      return false;
      if ((this.method != "CONNECT") && ((i < 100) || (i >= 200)) && (i != 204) && (i != 304)) {
        return true;
      }
    } while ((this.responseHeaders.getContentLength() == -1) && (!this.responseHeaders.isChunked()));
    return true;
  }
  
  protected boolean includeAuthorityInRequestLine()
  {
    return this.policy.usingProxy();
  }
  
  protected void initRequestBodyOut()
    throws IOException
  {
    int j = this.policy.getChunkLength();
    int i;
    if (j <= 0)
    {
      i = j;
      if (!this.requestHeaders.isChunked()) {}
    }
    else
    {
      this.sendChunked = true;
      i = j;
      if (j == -1) {
        i = 1024;
      }
    }
    if (this.socketOut == null) {
      throw new IllegalStateException("No socket to write to; was a POST cached?");
    }
    if (this.httpMinorVersion == 0) {
      this.sendChunked = false;
    }
    j = this.policy.getFixedContentLength();
    if (this.requestBodyOut != null) {
      return;
    }
    if (j != -1)
    {
      writeRequestHeaders(j);
      this.requestBodyOut = new FixedLengthOutputStream(this.requestOut, j);
      return;
    }
    if (this.sendChunked)
    {
      writeRequestHeaders(-1);
      this.requestBodyOut = new ChunkedOutputStream(this.requestOut, i);
      return;
    }
    if (this.requestHeaders.getContentLength() != -1)
    {
      writeRequestHeaders(this.requestHeaders.getContentLength());
      this.requestBodyOut = new RetryableOutputStream(this.requestHeaders.getContentLength());
      return;
    }
    this.requestBodyOut = new RetryableOutputStream();
  }
  
  protected final HttpConnection openSocketConnection()
    throws IOException
  {
    HttpConnection localHttpConnection = HttpConnection.connect(this.uri, getSslSocketFactory(), this.policy.getProxy(), requiresTunnel(), this.policy.getConnectTimeout());
    Proxy localProxy = localHttpConnection.getAddress().getProxy();
    if (localProxy != null) {
      this.policy.setProxy(localProxy);
    }
    localHttpConnection.setSoTimeout(this.policy.getReadTimeout());
    return localHttpConnection;
  }
  
  public final void readResponse()
    throws IOException
  {
    if (hasResponse()) {}
    do
    {
      return;
      if (this.responseSource == null) {
        throw new IllegalStateException("readResponse() without sendRequest()");
      }
    } while (!this.responseSource.requiresConnection());
    if (this.sentRequestMillis == -1L) {
      if (!(this.requestBodyOut instanceof RetryableOutputStream)) {
        break label231;
      }
    }
    label231:
    for (int i = ((RetryableOutputStream)this.requestBodyOut).contentLength();; i = -1)
    {
      writeRequestHeaders(i);
      if (this.requestBodyOut != null)
      {
        this.requestBodyOut.close();
        if ((this.requestBodyOut instanceof RetryableOutputStream)) {
          ((RetryableOutputStream)this.requestBodyOut).writeToSocket(this.requestOut);
        }
      }
      this.requestOut.flush();
      this.requestOut = this.socketOut;
      readResponseHeaders();
      this.responseHeaders.setLocalTimestamps(this.sentRequestMillis, System.currentTimeMillis());
      if (this.responseSource != ResponseSource.CONDITIONAL_CACHE) {
        break label243;
      }
      if (!this.cachedResponseHeaders.validate(this.responseHeaders)) {
        break label236;
      }
      release(true);
      setResponse(this.cachedResponseHeaders.combine(this.responseHeaders), this.cachedResponseBody);
      if (!(this.responseCache instanceof ExtendedResponseCache)) {
        break;
      }
      ExtendedResponseCache localExtendedResponseCache = (ExtendedResponseCache)this.responseCache;
      localExtendedResponseCache.trackConditionalCacheHit();
      localExtendedResponseCache.update(this.cacheResponse, getHttpConnectionToCache());
      return;
    }
    label236:
    IoUtils.closeQuietly(this.cachedResponseBody);
    label243:
    if (hasResponseBody()) {
      maybeCache();
    }
    initContentStream(getTransferStream());
  }
  
  final void readTrailers()
    throws IOException
  {
    readHeaders(this.responseHeaders.getHeaders());
  }
  
  public final void release(boolean paramBoolean)
  {
    if (this.responseBodyIn == this.cachedResponseBody) {
      IoUtils.closeQuietly(this.responseBodyIn);
    }
    boolean bool;
    if ((!this.connectionReleased) && (this.connection != null))
    {
      this.connectionReleased = true;
      bool = paramBoolean;
      if (this.requestBodyOut != null)
      {
        bool = paramBoolean;
        if (!this.requestBodyOut.closed) {
          bool = false;
        }
      }
      paramBoolean = bool;
      if (hasConnectionCloseHeader()) {
        paramBoolean = false;
      }
      if ((this.responseBodyIn instanceof UnknownLengthHttpInputStream)) {
        paramBoolean = false;
      }
      bool = paramBoolean;
      if (paramBoolean)
      {
        bool = paramBoolean;
        if (this.responseBodyIn == null) {}
      }
    }
    try
    {
      Streams.skipAll(this.responseBodyIn);
      bool = paramBoolean;
    }
    catch (IOException localIOException)
    {
      do
      {
        for (;;)
        {
          bool = false;
        }
      } while (!this.automaticallyReleaseConnectionToPool);
      HttpConnectionPool.INSTANCE.recycle(this.connection);
      this.connection = null;
    }
    if (!bool)
    {
      this.connection.closeSocketAndStreams();
      this.connection = null;
      return;
    }
  }
  
  protected boolean requiresTunnel()
  {
    return false;
  }
  
  public final void sendRequest()
    throws IOException
  {
    if (this.responseSource != null) {}
    do
    {
      return;
      prepareRawRequestHeaders();
      initResponseSource();
      if ((this.responseCache instanceof ExtendedResponseCache)) {
        ((ExtendedResponseCache)this.responseCache).trackResponse(this.responseSource);
      }
      if ((this.requestHeaders.isOnlyIfCached()) && (this.responseSource.requiresConnection()))
      {
        if (this.responseSource == ResponseSource.CONDITIONAL_CACHE) {
          IoUtils.closeQuietly(this.cachedResponseBody);
        }
        this.responseSource = ResponseSource.CACHE;
        this.cacheResponse = GATEWAY_TIMEOUT_RESPONSE;
        RawHeaders localRawHeaders = RawHeaders.fromMultimap(this.cacheResponse.getHeaders());
        setResponse(new ResponseHeaders(this.uri, localRawHeaders), this.cacheResponse.getBody());
      }
      if (this.responseSource.requiresConnection())
      {
        sendSocketRequest();
        return;
      }
    } while (this.connection == null);
    HttpConnectionPool.INSTANCE.recycle(this.connection);
    this.connection = null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\HttpEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */