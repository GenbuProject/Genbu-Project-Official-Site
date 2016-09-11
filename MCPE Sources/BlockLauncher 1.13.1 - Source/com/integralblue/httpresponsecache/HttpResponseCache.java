package com.integralblue.httpresponsecache;

import com.integralblue.httpresponsecache.compat.URLStreamHandlerFactoryImpl;
import com.integralblue.httpresponsecache.compat.java.net.ExtendedResponseCache;
import com.integralblue.httpresponsecache.compat.java.net.ResponseSource;
import com.integralblue.httpresponsecache.compat.javax.net.ssl.DefaultHostnameVerifier;
import com.integralblue.httpresponsecache.compat.libcore.io.IoUtils;
import com.jakewharton.DiskLruCache;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public final class HttpResponseCache
  extends ResponseCache
  implements Closeable, ExtendedResponseCache
{
  private static boolean calledSetURLStreamHandlerFactory = false;
  private final com.integralblue.httpresponsecache.compat.libcore.net.http.HttpResponseCache delegate;
  
  private HttpResponseCache(File paramFile, long paramLong)
    throws IOException
  {
    this.delegate = new com.integralblue.httpresponsecache.compat.libcore.net.http.HttpResponseCache(paramFile, paramLong);
  }
  
  public static HttpResponseCache getInstalled()
  {
    ResponseCache localResponseCache = ResponseCache.getDefault();
    if ((localResponseCache instanceof HttpResponseCache)) {
      return (HttpResponseCache)localResponseCache;
    }
    return null;
  }
  
  public static HttpResponseCache install(File paramFile, long paramLong)
    throws IOException
  {
    HttpResponseCache localHttpResponseCache = getInstalled();
    if (localHttpResponseCache != null)
    {
      DiskLruCache localDiskLruCache = localHttpResponseCache.delegate.getCache();
      if ((localDiskLruCache.getDirectory().equals(paramFile)) && (localDiskLruCache.getMaxSize() == paramLong) && (!localDiskLruCache.isClosed())) {
        return localHttpResponseCache;
      }
      IoUtils.closeQuietly(localHttpResponseCache);
    }
    paramFile = new HttpResponseCache(paramFile, paramLong);
    ResponseCache.setDefault(paramFile);
    HttpsURLConnection.setDefaultHostnameVerifier(new DefaultHostnameVerifier());
    if (!calledSetURLStreamHandlerFactory)
    {
      calledSetURLStreamHandlerFactory = true;
      URL.setURLStreamHandlerFactory(new URLStreamHandlerFactoryImpl());
    }
    return paramFile;
  }
  
  public void close()
    throws IOException
  {
    if (ResponseCache.getDefault() == this) {
      ResponseCache.setDefault(null);
    }
    this.delegate.getCache().close();
  }
  
  public void delete()
    throws IOException
  {
    if (ResponseCache.getDefault() == this) {
      ResponseCache.setDefault(null);
    }
    this.delegate.getCache().delete();
  }
  
  public void flush()
  {
    try
    {
      this.delegate.getCache().flush();
      return;
    }
    catch (IOException localIOException) {}
  }
  
  public CacheResponse get(URI paramURI, String paramString, Map<String, List<String>> paramMap)
    throws IOException
  {
    return this.delegate.get(paramURI, paramString, paramMap);
  }
  
  public int getHitCount()
  {
    return this.delegate.getHitCount();
  }
  
  public int getNetworkCount()
  {
    return this.delegate.getNetworkCount();
  }
  
  public int getRequestCount()
  {
    return this.delegate.getRequestCount();
  }
  
  public long maxSize()
  {
    return this.delegate.getCache().getMaxSize();
  }
  
  public CacheRequest put(URI paramURI, URLConnection paramURLConnection)
    throws IOException
  {
    return this.delegate.put(paramURI, paramURLConnection);
  }
  
  public long size()
  {
    return this.delegate.getCache().size();
  }
  
  public void trackConditionalCacheHit()
  {
    this.delegate.trackConditionalCacheHit();
  }
  
  public void trackResponse(ResponseSource paramResponseSource)
  {
    this.delegate.trackResponse(paramResponseSource);
  }
  
  public void update(CacheResponse paramCacheResponse, HttpURLConnection paramHttpURLConnection)
  {
    this.delegate.update(paramCacheResponse, paramHttpURLConnection);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\HttpResponseCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */