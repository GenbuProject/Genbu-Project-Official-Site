package com.microsoft.xbox.toolkit;

import android.util.LruCache;

public class XLEMemoryCache<K, V>
{
  private int itemCount = 0;
  private final LruCache<K, XLEMemoryCacheEntry<V>> lruCache;
  private final int maxFileSizeBytes;
  
  public XLEMemoryCache(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("sizeInBytes");
    }
    if (paramInt2 < 0) {
      throw new IllegalArgumentException("maxFileSizeInBytes");
    }
    this.maxFileSizeBytes = paramInt2;
    if (paramInt1 == 0) {}
    for (LruCache local1 = null;; local1 = new LruCache(paramInt1)
        {
          protected void entryRemoved(boolean paramAnonymousBoolean, K paramAnonymousK, XLEMemoryCacheEntry<V> paramAnonymousXLEMemoryCacheEntry1, XLEMemoryCacheEntry<V> paramAnonymousXLEMemoryCacheEntry2)
          {
            XLEMemoryCache.access$006(XLEMemoryCache.this);
          }
          
          protected int sizeOf(K paramAnonymousK, XLEMemoryCacheEntry<V> paramAnonymousXLEMemoryCacheEntry)
          {
            return paramAnonymousXLEMemoryCacheEntry.getByteCount();
          }
        })
    {
      this.lruCache = local1;
      return;
    }
  }
  
  public boolean add(K paramK, V paramV, int paramInt)
  {
    if (paramInt > this.maxFileSizeBytes) {}
    while (this.lruCache == null) {
      return false;
    }
    paramV = new XLEMemoryCacheEntry(paramV, paramInt);
    this.itemCount += 1;
    this.lruCache.put(paramK, paramV);
    return true;
  }
  
  public V get(K paramK)
  {
    if (this.lruCache != null)
    {
      paramK = (XLEMemoryCacheEntry)this.lruCache.get(paramK);
      if (paramK != null) {
        return (V)paramK.getValue();
      }
    }
    return null;
  }
  
  public int getBytesCurrent()
  {
    if (this.lruCache == null) {
      return 0;
    }
    return this.lruCache.size();
  }
  
  public int getBytesFree()
  {
    if (this.lruCache == null) {
      return 0;
    }
    return this.lruCache.maxSize() - this.lruCache.size();
  }
  
  public int getItemsInCache()
  {
    return this.itemCount;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEMemoryCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */