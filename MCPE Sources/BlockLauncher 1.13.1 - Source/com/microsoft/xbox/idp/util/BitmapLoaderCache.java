package com.microsoft.xbox.idp.util;

import android.util.LruCache;
import com.microsoft.xbox.idp.toolkit.BitmapLoader.Cache;

public class BitmapLoaderCache
  implements BitmapLoader.Cache
{
  private final LruCache<Object, byte[]> cache;
  
  public BitmapLoaderCache(int paramInt)
  {
    this.cache = new LruCache(paramInt);
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\util\BitmapLoaderCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */