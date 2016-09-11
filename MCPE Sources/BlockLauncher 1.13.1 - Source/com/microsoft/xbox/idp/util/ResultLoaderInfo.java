package com.microsoft.xbox.idp.util;

import android.app.LoaderManager.LoaderCallbacks;

public class ResultLoaderInfo<R>
  implements ErrorHelper.LoaderInfo
{
  private final LoaderManager.LoaderCallbacks<?> callbacks;
  private final Class<R> cls;
  
  public ResultLoaderInfo(Class<R> paramClass, LoaderManager.LoaderCallbacks<?> paramLoaderCallbacks)
  {
    this.cls = paramClass;
    this.callbacks = paramLoaderCallbacks;
  }
  
  public void clearCache(Object paramObject)
  {
    synchronized (CacheUtil.getResultCache(this.cls))
    {
      ???.remove(paramObject);
      return;
    }
  }
  
  public LoaderManager.LoaderCallbacks<?> getLoaderCallbacks()
  {
    return this.callbacks;
  }
  
  public boolean hasCachedData(Object paramObject)
  {
    for (;;)
    {
      synchronized (CacheUtil.getResultCache(this.cls))
      {
        if (???.get(paramObject) != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\util\ResultLoaderInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */