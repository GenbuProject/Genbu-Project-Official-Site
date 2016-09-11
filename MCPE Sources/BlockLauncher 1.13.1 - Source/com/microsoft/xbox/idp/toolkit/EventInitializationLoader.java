package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import com.microsoft.xbox.idp.interop.Interop;
import com.microsoft.xbox.idp.interop.Interop.EventInitializationCallback;
import com.microsoft.xbox.idp.util.ResultCache;

public class EventInitializationLoader
  extends WorkerLoader<Result>
{
  public EventInitializationLoader(Context paramContext, long paramLong, String paramString, ResultCache<Result> paramResultCache, Object paramObject)
  {
    super(paramContext, new MyWorker(paramLong, paramString, paramResultCache, paramObject, null));
  }
  
  protected boolean isDataReleased(Result paramResult)
  {
    return true;
  }
  
  protected void releaseData(Result paramResult) {}
  
  private static class MyWorker
    implements WorkerLoader.Worker<EventInitializationLoader.Result>
  {
    private final ResultCache<EventInitializationLoader.Result> cache;
    private final Object resultKey;
    private final String rpsTicket;
    private final long userPtr;
    
    private MyWorker(long paramLong, String paramString, ResultCache<EventInitializationLoader.Result> paramResultCache, Object paramObject)
    {
      this.userPtr = paramLong;
      this.rpsTicket = paramString;
      this.cache = paramResultCache;
      this.resultKey = paramObject;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<EventInitializationLoader.Result> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          EventInitializationLoader.Result localResult = (EventInitializationLoader.Result)this.cache.get(this.resultKey);
          if (localResult != null)
          {
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }
      Interop.InvokeEventInitialization(this.userPtr, this.rpsTicket, new Interop.EventInitializationCallback()
      {
        public void onError(int paramAnonymousInt1, int paramAnonymousInt2, String arg3)
        {
          EventInitializationLoader.Result localResult = new EventInitializationLoader.Result(new HttpError(paramAnonymousInt2, paramAnonymousInt1, ???));
          if (EventInitializationLoader.MyWorker.this.hasCache()) {}
          synchronized (EventInitializationLoader.MyWorker.this.cache)
          {
            EventInitializationLoader.MyWorker.this.cache.put(EventInitializationLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
        
        public void onSuccess()
        {
          EventInitializationLoader.Result localResult = new EventInitializationLoader.Result(null);
          if (EventInitializationLoader.MyWorker.this.hasCache()) {}
          synchronized (EventInitializationLoader.MyWorker.this.cache)
          {
            EventInitializationLoader.MyWorker.this.cache.put(EventInitializationLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
      });
    }
  }
  
  public static class Result
    extends LoaderResult<Void>
  {
    protected Result(HttpError paramHttpError)
    {
      super(paramHttpError);
    }
    
    public boolean isReleased()
    {
      return true;
    }
    
    public void release() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\toolkit\EventInitializationLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */