package com.microsoft.onlineid.interop.xbox.util;

import android.util.Log;
import com.microsoft.onlineid.interop.xbox.toolkit.BitmapLoader.Cache;
import com.microsoft.onlineid.interop.xbox.toolkit.ObjectLoader.Cache;
import com.microsoft.onlineid.interop.xbox.toolkit.XBLoginLoader.Cache;
import com.microsoft.onlineid.interop.xbox.toolkit.XTokenLoader.Cache;

public final class CacheUtil
{
  private static final String TAG = CacheUtil.class.getSimpleName();
  
  public static void clearCaches()
  {
    Log.d(TAG, "clearCaches");
    synchronized (ObjectLoaderCache.getInstance())
    {
      ((ObjectLoader.Cache)???).clear();
      synchronized (XTokenLoaderCache.getInstance())
      {
        ((XTokenLoader.Cache)???).clear();
        synchronized (XBLoginLoaderCache.getInstance())
        {
          ((XBLoginLoader.Cache)???).clear();
        }
      }
    }
    synchronized (BitmapLoaderCache.getInstance())
    {
      ((BitmapLoader.Cache)???).clear();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
      localObject3 = finally;
      throw ((Throwable)localObject3);
      localObject4 = finally;
      throw ((Throwable)localObject4);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\util\CacheUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */