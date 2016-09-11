package com.integralblue.httpresponsecache.compat.libcore.net.http;

import com.integralblue.httpresponsecache.compat.Charsets;
import com.integralblue.httpresponsecache.compat.MD5;
import com.integralblue.httpresponsecache.compat.Strings;
import com.integralblue.httpresponsecache.compat.java.net.ExtendedResponseCache;
import com.integralblue.httpresponsecache.compat.java.net.ResponseSource;
import com.integralblue.httpresponsecache.compat.libcore.io.IoUtils;
import com.integralblue.httpresponsecache.compat.libcore.io.Streams;
import com.jakewharton.DiskLruCache;
import com.jakewharton.DiskLruCache.Editor;
import com.jakewharton.DiskLruCache.Snapshot;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.SecureCacheResponse;
import java.net.URI;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class HttpResponseCache
  extends ResponseCache
  implements ExtendedResponseCache
{
  private static final int ENTRY_BODY = 1;
  private static final int ENTRY_COUNT = 2;
  private static final int ENTRY_METADATA = 0;
  private static final int VERSION = 201105;
  private final DiskLruCache cache;
  private int hitCount;
  private int networkCount;
  private int requestCount;
  private int writeAbortCount;
  private int writeSuccessCount;
  
  public HttpResponseCache(File paramFile, long paramLong)
    throws IOException
  {
    this.cache = DiskLruCache.open(paramFile, 201105, 2, paramLong);
  }
  
  private void abortQuietly(DiskLruCache.Editor paramEditor)
  {
    if (paramEditor != null) {}
    try
    {
      paramEditor.abort();
      return;
    }
    catch (IOException paramEditor) {}
  }
  
  private HttpEngine getHttpEngine(HttpURLConnection paramHttpURLConnection)
  {
    if ((paramHttpURLConnection instanceof HttpURLConnectionImpl)) {
      return ((HttpURLConnectionImpl)paramHttpURLConnection).getHttpEngine();
    }
    if ((paramHttpURLConnection instanceof HttpsURLConnectionImpl)) {
      return ((HttpsURLConnectionImpl)paramHttpURLConnection).getHttpEngine();
    }
    return null;
  }
  
  private static InputStream newBodyInputStream(final DiskLruCache.Snapshot paramSnapshot)
  {
    new FilterInputStream(paramSnapshot.getInputStream(1))
    {
      public void close()
        throws IOException
      {
        paramSnapshot.close();
        super.close();
      }
    };
  }
  
  private String uriToKey(URI paramURI)
  {
    return Strings.bytesToHexString(new MD5().digest(Strings.getBytes(paramURI.toString(), Charsets.UTF_8)), false);
  }
  
  public CacheResponse get(URI paramURI, String paramString, Map<String, List<String>> paramMap)
  {
    Object localObject = uriToKey(paramURI);
    Entry localEntry;
    try
    {
      localObject = this.cache.get((String)localObject);
      if (localObject == null) {
        return null;
      }
      localEntry = new Entry(new BufferedInputStream(((DiskLruCache.Snapshot)localObject).getInputStream(0)));
      if (!localEntry.matches(paramURI, paramString, paramMap))
      {
        ((DiskLruCache.Snapshot)localObject).close();
        return null;
      }
    }
    catch (IOException paramURI)
    {
      return null;
    }
    if (localEntry.isHttps()) {
      return new EntrySecureCacheResponse(localEntry, (DiskLruCache.Snapshot)localObject);
    }
    return new EntryCacheResponse(localEntry, (DiskLruCache.Snapshot)localObject);
  }
  
  public DiskLruCache getCache()
  {
    return this.cache;
  }
  
  public int getHitCount()
  {
    try
    {
      int i = this.hitCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getNetworkCount()
  {
    try
    {
      int i = this.networkCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getRequestCount()
  {
    try
    {
      int i = this.requestCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getWriteAbortCount()
  {
    try
    {
      int i = this.writeAbortCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getWriteSuccessCount()
  {
    try
    {
      int i = this.writeSuccessCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public CacheRequest put(URI paramURI, URLConnection paramURLConnection)
    throws IOException
  {
    if (!(paramURLConnection instanceof HttpURLConnection)) {}
    for (;;)
    {
      return null;
      Object localObject1 = (HttpURLConnection)paramURLConnection;
      Object localObject2 = ((HttpURLConnection)localObject1).getRequestMethod();
      paramURLConnection = uriToKey(paramURI);
      if ((((String)localObject2).equals("POST")) || (((String)localObject2).equals("PUT")) || (((String)localObject2).equals("DELETE"))) {
        try
        {
          this.cache.remove(paramURLConnection);
          return null;
        }
        catch (IOException paramURI)
        {
          return null;
        }
      }
      if (((String)localObject2).equals("GET"))
      {
        localObject2 = getHttpEngine((HttpURLConnection)localObject1);
        if (localObject2 != null)
        {
          ResponseHeaders localResponseHeaders = ((HttpEngine)localObject2).getResponseHeaders();
          if (!localResponseHeaders.hasVaryAll())
          {
            localObject1 = new Entry(paramURI, ((HttpEngine)localObject2).getRequestHeaders().getHeaders().getAll(localResponseHeaders.getVaryFields()), (HttpURLConnection)localObject1);
            paramURI = null;
            try
            {
              paramURLConnection = this.cache.edit(paramURLConnection);
              if (paramURLConnection != null)
              {
                paramURI = paramURLConnection;
                ((Entry)localObject1).writeTo(paramURLConnection);
                paramURI = paramURLConnection;
                paramURLConnection = new CacheRequestImpl(paramURLConnection);
                return paramURLConnection;
              }
            }
            catch (IOException paramURLConnection)
            {
              abortQuietly(paramURI);
            }
          }
        }
      }
    }
    return null;
  }
  
  public void trackConditionalCacheHit()
  {
    try
    {
      this.hitCount += 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void trackResponse(ResponseSource paramResponseSource)
  {
    for (;;)
    {
      try
      {
        this.requestCount += 1;
        int i = 2.$SwitchMap$com$integralblue$httpresponsecache$compat$java$net$ResponseSource[paramResponseSource.ordinal()];
        switch (i)
        {
        default: 
          return;
        }
      }
      finally {}
      this.hitCount += 1;
      continue;
      this.networkCount += 1;
    }
  }
  
  public void update(CacheResponse paramCacheResponse, HttpURLConnection paramHttpURLConnection)
  {
    Object localObject = getHttpEngine(paramHttpURLConnection);
    URI localURI = ((HttpEngine)localObject).getUri();
    ResponseHeaders localResponseHeaders = ((HttpEngine)localObject).getResponseHeaders();
    localObject = new Entry(localURI, ((HttpEngine)localObject).getRequestHeaders().getHeaders().getAll(localResponseHeaders.getVaryFields()), paramHttpURLConnection);
    if ((paramCacheResponse instanceof EntryCacheResponse)) {}
    for (paramHttpURLConnection = ((EntryCacheResponse)paramCacheResponse).snapshot;; paramHttpURLConnection = ((EntrySecureCacheResponse)paramCacheResponse).snapshot)
    {
      paramCacheResponse = null;
      try
      {
        paramHttpURLConnection = paramHttpURLConnection.edit();
        if (paramHttpURLConnection != null)
        {
          paramCacheResponse = paramHttpURLConnection;
          ((Entry)localObject).writeTo(paramHttpURLConnection);
          paramCacheResponse = paramHttpURLConnection;
          paramHttpURLConnection.commit();
        }
        return;
      }
      catch (IOException paramHttpURLConnection)
      {
        abortQuietly(paramCacheResponse);
      }
    }
  }
  
  private final class CacheRequestImpl
    extends CacheRequest
  {
    private OutputStream body;
    private OutputStream cacheOut;
    private boolean done;
    private final DiskLruCache.Editor editor;
    
    public CacheRequestImpl(final DiskLruCache.Editor paramEditor)
      throws IOException
    {
      this.editor = paramEditor;
      this.cacheOut = paramEditor.newOutputStream(1);
      this.body = new FilterOutputStream(this.cacheOut)
      {
        public void close()
          throws IOException
        {
          synchronized (HttpResponseCache.this)
          {
            if (HttpResponseCache.CacheRequestImpl.this.done) {
              return;
            }
            HttpResponseCache.CacheRequestImpl.access$302(HttpResponseCache.CacheRequestImpl.this, true);
            HttpResponseCache.access$408(HttpResponseCache.this);
            super.close();
            paramEditor.commit();
            return;
          }
        }
        
        public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
          throws IOException
        {
          this.out.write(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        }
      };
    }
    
    public void abort()
    {
      synchronized (HttpResponseCache.this)
      {
        if (this.done) {
          return;
        }
        this.done = true;
        HttpResponseCache.access$508(HttpResponseCache.this);
        IoUtils.closeQuietly(this.cacheOut);
        try
        {
          this.editor.abort();
          return;
        }
        catch (IOException localIOException) {}
      }
    }
    
    public OutputStream getBody()
      throws IOException
    {
      return this.body;
    }
  }
  
  private static final class Entry
  {
    private final String cipherSuite;
    private final Certificate[] localCertificates;
    private final Certificate[] peerCertificates;
    private final String requestMethod;
    private final RawHeaders responseHeaders;
    private final String uri;
    private final RawHeaders varyHeaders;
    
    public Entry(InputStream paramInputStream)
      throws IOException
    {
      try
      {
        this.uri = Streams.readAsciiLine(paramInputStream);
        this.requestMethod = Streams.readAsciiLine(paramInputStream);
        this.varyHeaders = new RawHeaders();
        int j = readInt(paramInputStream);
        int i = 0;
        while (i < j)
        {
          this.varyHeaders.addLine(Streams.readAsciiLine(paramInputStream));
          i += 1;
        }
        this.responseHeaders = new RawHeaders();
        this.responseHeaders.setStatusLine(Streams.readAsciiLine(paramInputStream));
        j = readInt(paramInputStream);
        i = 0;
        while (i < j)
        {
          this.responseHeaders.addLine(Streams.readAsciiLine(paramInputStream));
          i += 1;
        }
        if (!isHttps()) {
          break label209;
        }
        String str = Streams.readAsciiLine(paramInputStream);
        if (!Strings.isEmpty(str)) {
          throw new IOException("expected \"\" but was \"" + str + "\"");
        }
      }
      finally
      {
        paramInputStream.close();
      }
      this.cipherSuite = Streams.readAsciiLine(paramInputStream);
      this.peerCertificates = readCertArray(paramInputStream);
      for (this.localCertificates = readCertArray(paramInputStream);; this.localCertificates = null)
      {
        paramInputStream.close();
        return;
        label209:
        this.cipherSuite = null;
        this.peerCertificates = null;
      }
    }
    
    public Entry(URI paramURI, RawHeaders paramRawHeaders, HttpURLConnection paramHttpURLConnection)
    {
      this.uri = paramURI.toString();
      this.varyHeaders = paramRawHeaders;
      this.requestMethod = paramHttpURLConnection.getRequestMethod();
      this.responseHeaders = RawHeaders.fromMultimap(paramHttpURLConnection.getHeaderFields());
      if (isHttps())
      {
        paramHttpURLConnection = (HttpsURLConnection)paramHttpURLConnection;
        this.cipherSuite = paramHttpURLConnection.getCipherSuite();
        paramURI = null;
      }
      try
      {
        paramRawHeaders = paramHttpURLConnection.getServerCertificates();
        paramURI = paramRawHeaders;
      }
      catch (SSLPeerUnverifiedException paramRawHeaders)
      {
        for (;;) {}
      }
      this.peerCertificates = paramURI;
      this.localCertificates = paramHttpURLConnection.getLocalCertificates();
      return;
      this.cipherSuite = null;
      this.peerCertificates = null;
      this.localCertificates = null;
    }
    
    private boolean isHttps()
    {
      return this.uri.startsWith("https://");
    }
    
    /* Error */
    private Certificate[] readCertArray(InputStream paramInputStream)
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 43	com/integralblue/httpresponsecache/compat/libcore/net/http/HttpResponseCache$Entry:readInt	(Ljava/io/InputStream;)I
      //   5: istore_2
      //   6: iload_2
      //   7: iconst_m1
      //   8: if_icmpne +7 -> 15
      //   11: aconst_null
      //   12: astore_3
      //   13: aload_3
      //   14: areturn
      //   15: ldc -109
      //   17: invokestatic 153	java/security/cert/CertificateFactory:getInstance	(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
      //   20: astore 5
      //   22: iload_2
      //   23: anewarray 155	java/security/cert/Certificate
      //   26: astore 4
      //   28: iconst_0
      //   29: istore_2
      //   30: aload 4
      //   32: astore_3
      //   33: iload_2
      //   34: aload 4
      //   36: arraylength
      //   37: if_icmpge -24 -> 13
      //   40: aload 4
      //   42: iload_2
      //   43: aload 5
      //   45: new 157	java/io/ByteArrayInputStream
      //   48: dup
      //   49: aload_1
      //   50: invokestatic 30	com/integralblue/httpresponsecache/compat/libcore/io/Streams:readAsciiLine	(Ljava/io/InputStream;)Ljava/lang/String;
      //   53: getstatic 163	com/integralblue/httpresponsecache/compat/Charsets:US_ASCII	Ljava/nio/charset/Charset;
      //   56: invokestatic 167	com/integralblue/httpresponsecache/compat/Strings:getBytes	(Ljava/lang/String;Ljava/nio/charset/Charset;)[B
      //   59: invokestatic 173	com/integralblue/httpresponsecache/compat/libcore/io/Base64:decode	([B)[B
      //   62: invokespecial 176	java/io/ByteArrayInputStream:<init>	([B)V
      //   65: invokevirtual 180	java/security/cert/CertificateFactory:generateCertificate	(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
      //   68: aastore
      //   69: iload_2
      //   70: iconst_1
      //   71: iadd
      //   72: istore_2
      //   73: goto -43 -> 30
      //   76: astore_1
      //   77: new 21	java/io/IOException
      //   80: dup
      //   81: aload_1
      //   82: invokevirtual 181	java/security/cert/CertificateException:toString	()Ljava/lang/String;
      //   85: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   88: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	89	0	this	Entry
      //   0	89	1	paramInputStream	InputStream
      //   5	68	2	i	int
      //   12	21	3	localObject	Object
      //   26	15	4	arrayOfCertificate	Certificate[]
      //   20	24	5	localCertificateFactory	java.security.cert.CertificateFactory
      // Exception table:
      //   from	to	target	type
      //   15	28	76	java/security/cert/CertificateException
      //   33	69	76	java/security/cert/CertificateException
    }
    
    private int readInt(InputStream paramInputStream)
      throws IOException
    {
      paramInputStream = Streams.readAsciiLine(paramInputStream);
      try
      {
        int i = Integer.parseInt(paramInputStream);
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new IOException("expected an int but was \"" + paramInputStream + "\"");
      }
    }
    
    /* Error */
    private void writeCertArray(Writer paramWriter, Certificate[] paramArrayOfCertificate)
      throws IOException
    {
      // Byte code:
      //   0: aload_2
      //   1: ifnonnull +10 -> 11
      //   4: aload_1
      //   5: ldc -59
      //   7: invokevirtual 202	java/io/Writer:write	(Ljava/lang/String;)V
      //   10: return
      //   11: aload_1
      //   12: new 64	java/lang/StringBuilder
      //   15: dup
      //   16: invokespecial 65	java/lang/StringBuilder:<init>	()V
      //   19: aload_2
      //   20: arraylength
      //   21: invokestatic 205	java/lang/Integer:toString	(I)Ljava/lang/String;
      //   24: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   27: bipush 10
      //   29: invokevirtual 208	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
      //   32: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   35: invokevirtual 202	java/io/Writer:write	(Ljava/lang/String;)V
      //   38: aload_2
      //   39: arraylength
      //   40: istore 4
      //   42: iconst_0
      //   43: istore_3
      //   44: iload_3
      //   45: iload 4
      //   47: if_icmpge -37 -> 10
      //   50: aload_2
      //   51: iload_3
      //   52: aaload
      //   53: invokevirtual 212	java/security/cert/Certificate:getEncoded	()[B
      //   56: invokestatic 216	com/integralblue/httpresponsecache/compat/libcore/io/Base64:encode	([B)Ljava/lang/String;
      //   59: astore 5
      //   61: aload_1
      //   62: new 64	java/lang/StringBuilder
      //   65: dup
      //   66: invokespecial 65	java/lang/StringBuilder:<init>	()V
      //   69: aload 5
      //   71: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   74: bipush 10
      //   76: invokevirtual 208	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
      //   79: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   82: invokevirtual 202	java/io/Writer:write	(Ljava/lang/String;)V
      //   85: iload_3
      //   86: iconst_1
      //   87: iadd
      //   88: istore_3
      //   89: goto -45 -> 44
      //   92: astore_1
      //   93: new 21	java/io/IOException
      //   96: dup
      //   97: aload_1
      //   98: invokevirtual 217	java/security/cert/CertificateEncodingException:toString	()Ljava/lang/String;
      //   101: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   104: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	105	0	this	Entry
      //   0	105	1	paramWriter	Writer
      //   0	105	2	paramArrayOfCertificate	Certificate[]
      //   43	46	3	i	int
      //   40	8	4	j	int
      //   59	11	5	str	String
      // Exception table:
      //   from	to	target	type
      //   11	42	92	java/security/cert/CertificateEncodingException
      //   50	85	92	java/security/cert/CertificateEncodingException
    }
    
    public boolean matches(URI paramURI, String paramString, Map<String, List<String>> paramMap)
    {
      return (this.uri.equals(paramURI.toString())) && (this.requestMethod.equals(paramString)) && (new ResponseHeaders(paramURI, this.responseHeaders).varyMatches(this.varyHeaders.toMultimap(), paramMap));
    }
    
    public void writeTo(DiskLruCache.Editor paramEditor)
      throws IOException
    {
      paramEditor = new BufferedWriter(new OutputStreamWriter(paramEditor.newOutputStream(0), Charsets.UTF_8));
      paramEditor.write(this.uri + '\n');
      paramEditor.write(this.requestMethod + '\n');
      paramEditor.write(Integer.toString(this.varyHeaders.length()) + '\n');
      int i = 0;
      while (i < this.varyHeaders.length())
      {
        paramEditor.write(this.varyHeaders.getFieldName(i) + ": " + this.varyHeaders.getValue(i) + '\n');
        i += 1;
      }
      paramEditor.write(this.responseHeaders.getStatusLine() + '\n');
      paramEditor.write(Integer.toString(this.responseHeaders.length()) + '\n');
      i = 0;
      while (i < this.responseHeaders.length())
      {
        paramEditor.write(this.responseHeaders.getFieldName(i) + ": " + this.responseHeaders.getValue(i) + '\n');
        i += 1;
      }
      if (isHttps())
      {
        paramEditor.write(10);
        paramEditor.write(this.cipherSuite + '\n');
        writeCertArray(paramEditor, this.peerCertificates);
        writeCertArray(paramEditor, this.localCertificates);
      }
      paramEditor.close();
    }
  }
  
  static class EntryCacheResponse
    extends CacheResponse
  {
    private final HttpResponseCache.Entry entry;
    private final InputStream in;
    private final DiskLruCache.Snapshot snapshot;
    
    public EntryCacheResponse(HttpResponseCache.Entry paramEntry, DiskLruCache.Snapshot paramSnapshot)
    {
      this.entry = paramEntry;
      this.snapshot = paramSnapshot;
      this.in = HttpResponseCache.newBodyInputStream(paramSnapshot);
    }
    
    public InputStream getBody()
    {
      return this.in;
    }
    
    public Map<String, List<String>> getHeaders()
    {
      return this.entry.responseHeaders.toMultimap();
    }
  }
  
  static class EntrySecureCacheResponse
    extends SecureCacheResponse
  {
    private final HttpResponseCache.Entry entry;
    private final InputStream in;
    private final DiskLruCache.Snapshot snapshot;
    
    public EntrySecureCacheResponse(HttpResponseCache.Entry paramEntry, DiskLruCache.Snapshot paramSnapshot)
    {
      this.entry = paramEntry;
      this.snapshot = paramSnapshot;
      this.in = HttpResponseCache.newBodyInputStream(paramSnapshot);
    }
    
    public InputStream getBody()
    {
      return this.in;
    }
    
    public String getCipherSuite()
    {
      return this.entry.cipherSuite;
    }
    
    public Map<String, List<String>> getHeaders()
    {
      return this.entry.responseHeaders.toMultimap();
    }
    
    public List<Certificate> getLocalCertificateChain()
    {
      if ((this.entry.localCertificates == null) || (this.entry.localCertificates.length == 0)) {
        return null;
      }
      return Arrays.asList((Object[])this.entry.localCertificates.clone());
    }
    
    public Principal getLocalPrincipal()
    {
      if ((this.entry.localCertificates == null) || (this.entry.localCertificates.length == 0)) {
        return null;
      }
      return ((X509Certificate)this.entry.localCertificates[0]).getSubjectX500Principal();
    }
    
    public Principal getPeerPrincipal()
      throws SSLPeerUnverifiedException
    {
      if ((this.entry.peerCertificates == null) || (this.entry.peerCertificates.length == 0)) {
        throw new SSLPeerUnverifiedException(null);
      }
      return ((X509Certificate)this.entry.peerCertificates[0]).getSubjectX500Principal();
    }
    
    public List<Certificate> getServerCertificateChain()
      throws SSLPeerUnverifiedException
    {
      if ((this.entry.peerCertificates == null) || (this.entry.peerCertificates.length == 0)) {
        throw new SSLPeerUnverifiedException(null);
      }
      return Arrays.asList((Object[])this.entry.peerCertificates.clone());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\HttpResponseCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */