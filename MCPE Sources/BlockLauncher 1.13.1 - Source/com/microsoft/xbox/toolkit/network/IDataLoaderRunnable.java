package com.microsoft.xbox.toolkit.network;

import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.XLEException;

public abstract class IDataLoaderRunnable<T>
{
  protected int retryCountOnTokenError = 1;
  
  public abstract T buildData()
    throws XLEException;
  
  public abstract long getDefaultErrorCode();
  
  public int getShouldRetryCountOnTokenError()
  {
    return this.retryCountOnTokenError;
  }
  
  public Object getUserObject()
  {
    return null;
  }
  
  public abstract void onPostExcute(AsyncResult<T> paramAsyncResult);
  
  public abstract void onPreExecute();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\network\IDataLoaderRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */