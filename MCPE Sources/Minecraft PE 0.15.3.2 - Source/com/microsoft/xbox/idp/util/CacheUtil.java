package com.microsoft.xbox.idp.util;

import android.util.Log;
import com.microsoft.xbox.idp.toolkit.BitmapLoader.Cache;
import com.microsoft.xbox.idp.toolkit.EventInitializationLoader.Result;
import com.microsoft.xbox.idp.toolkit.FinishSignInLoader.Result;
import com.microsoft.xbox.idp.toolkit.ObjectLoader.Cache;
import com.microsoft.xbox.idp.toolkit.SignOutLoader.Result;
import com.microsoft.xbox.idp.toolkit.StartSignInLoader.Result;
import com.microsoft.xbox.idp.toolkit.XBLoginLoader.Result;
import com.microsoft.xbox.idp.toolkit.XTokenLoader.Result;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class CacheUtil
{
  private static final String TAG = CacheUtil.class.getSimpleName();
  private static final BitmapLoader.Cache bitmapCache;
  private static final HashMap<Class<?>, ResultCache<?>> map = new HashMap();
  private static final ObjectLoader.Cache objectLoaderCache = new ObjectLoaderCache();
  
  static
  {
    bitmapCache = new BitmapLoaderCache(50);
    map.put(XTokenLoader.Result.class, new ResultCache());
    map.put(XBLoginLoader.Result.class, new ResultCache());
    map.put(EventInitializationLoader.Result.class, new ResultCache());
    map.put(StartSignInLoader.Result.class, new ResultCache());
    map.put(FinishSignInLoader.Result.class, new ResultCache());
    map.put(SignOutLoader.Result.class, new ResultCache());
  }
  
  public static void clearCaches()
  {
    Log.d(TAG, "clearCaches");
    synchronized (objectLoaderCache)
    {
      objectLoaderCache.clear();
    }
    synchronized (bitmapCache)
    {
      bitmapCache.clear();
      Iterator localIterator = map.values().iterator();
      while (localIterator.hasNext()) {
        synchronized ((ResultCache)localIterator.next())
        {
          ((ResultCache)???).clear();
        }
      }
      localObject3 = finally;
      throw ((Throwable)localObject3);
    }
  }
  
  public static BitmapLoader.Cache getBitmapCache()
  {
    return bitmapCache;
  }
  
  public static ObjectLoader.Cache getObjectLoaderCache()
  {
    return objectLoaderCache;
  }
  
  public static <R> ResultCache<R> getResultCache(Class<R> paramClass)
  {
    return (ResultCache)map.get(paramClass);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\util\CacheUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */