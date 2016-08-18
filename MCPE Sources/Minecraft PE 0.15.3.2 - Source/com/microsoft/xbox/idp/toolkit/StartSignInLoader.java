package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import com.microsoft.xbox.idp.interop.XsapiUser;
import com.microsoft.xbox.idp.interop.XsapiUser.StartSignInCallback;
import com.microsoft.xbox.idp.util.ResultCache;

public class StartSignInLoader
  extends WorkerLoader<Result>
{
  public StartSignInLoader(Context paramContext, ResultCache<Result> paramResultCache, Object paramObject)
  {
    super(paramContext, new MyWorker(paramResultCache, paramObject, null));
  }
  
  protected boolean isDataReleased(Result paramResult)
  {
    return paramResult.isReleased();
  }
  
  protected void releaseData(Result paramResult)
  {
    paramResult.release();
  }
  
  public static class Data {}
  
  private static class MyWorker
    implements WorkerLoader.Worker<StartSignInLoader.Result>
  {
    private final ResultCache<StartSignInLoader.Result> cache;
    private final Object resultKey;
    
    private MyWorker(ResultCache<StartSignInLoader.Result> paramResultCache, Object paramObject)
    {
      this.cache = paramResultCache;
      this.resultKey = paramObject;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<StartSignInLoader.Result> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          StartSignInLoader.Result localResult = (StartSignInLoader.Result)this.cache.get(this.resultKey);
          if (localResult != null)
          {
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }
      XsapiUser.getInstance().startSignIn(new XsapiUser.StartSignInCallback()
      {
        public void onError(int paramAnonymousInt1, int paramAnonymousInt2, String arg3)
        {
          StartSignInLoader.Result localResult = new StartSignInLoader.Result(null, new HttpError(paramAnonymousInt2, paramAnonymousInt1, ???));
          if (StartSignInLoader.MyWorker.this.hasCache()) {}
          synchronized (StartSignInLoader.MyWorker.this.cache)
          {
            StartSignInLoader.MyWorker.this.cache.put(StartSignInLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
        
        public void onSuccess()
        {
          StartSignInLoader.Result localResult = new StartSignInLoader.Result(new StartSignInLoader.Data(), null);
          if (StartSignInLoader.MyWorker.this.hasCache()) {}
          synchronized (StartSignInLoader.MyWorker.this.cache)
          {
            StartSignInLoader.MyWorker.this.cache.put(StartSignInLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
      });
    }
  }
  
  public static class Result
    extends LoaderResult<StartSignInLoader.Data>
  {
    protected Result(StartSignInLoader.Data paramData, HttpError paramHttpError)
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\toolkit\StartSignInLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */