package com.microsoft.onlineid.interop.xbox.util;

import android.util.LruCache;
import com.microsoft.onlineid.interop.xbox.toolkit.BitmapLoader.Cache;

public class BitmapLoaderCache
  implements BitmapLoader.Cache
{
  private static final BitmapLoader.Cache instance = new BitmapLoaderCache(50);
  private final LruCache<Object, byte[]> cache;
  
  private BitmapLoaderCache(int paramInt)
  {
    this.cache = new LruCache(paramInt);
  }
  
  public static BitmapLoader.Cache getInstance()
  {
    return instance;
  }
  
  public void clear()
  {
    this.cache.evictAll();
  }
  
  public byte[] get(Object paramObject)
  {
    return (byte[])this.cache.get(paramObject);
  }
  
  public byte[] put(Object paramObject, byte[] paramArrayOfByte)
  {
    return (byte[])this.cache.put(paramObject, paramArrayOfByte);
  }
  
  public byte[] remove(Object paramObject)
  {
    return (byte[])this.cache.remove(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\util\BitmapLoaderCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */