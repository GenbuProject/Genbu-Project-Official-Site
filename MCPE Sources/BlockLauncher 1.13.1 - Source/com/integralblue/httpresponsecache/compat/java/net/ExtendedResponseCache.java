package com.integralblue.httpresponsecache.compat.java.net;

import java.net.CacheResponse;
import java.net.HttpURLConnection;

public abstract interface ExtendedResponseCache
{
  public abstract void trackConditionalCacheHit();
  
  public abstract void trackResponse(ResponseSource paramResponseSource);
  
  public abstract void update(CacheResponse paramCacheResponse, HttpURLConnection paramHttpURLConnection);
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\java\net\ExtendedResponseCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */