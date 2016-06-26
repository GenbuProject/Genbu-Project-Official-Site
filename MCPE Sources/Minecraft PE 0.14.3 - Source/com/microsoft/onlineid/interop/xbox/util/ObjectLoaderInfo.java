package com.microsoft.onlineid.interop.xbox.util;

import android.app.LoaderManager.LoaderCallbacks;
import com.microsoft.onlineid.interop.xbox.toolkit.ObjectLoader.Cache;

public class ObjectLoaderInfo
  implements ErrorHelper.LoaderInfo
{
  private final LoaderManager.LoaderCallbacks<?> callbacks;
  
  public ObjectLoaderInfo(LoaderManager.LoaderCallbacks<?> paramLoaderCallbacks)
  {
    this.callbacks = paramLoaderCallbacks;
  }
  
  public void clearCache(Object paramObject)
  {
    synchronized ()
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
      synchronized ()
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\util\ObjectLoaderInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */