package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import com.microsoft.xbox.idp.interop.XsapiUser;
import com.microsoft.xbox.idp.interop.XsapiUser.SignOutCallback;
import com.microsoft.xbox.idp.util.ResultCache;

public class SignOutLoader
  extends WorkerLoader<Result>
{
  public SignOutLoader(Context paramContext, ResultCache<Result> paramResultCache, Object paramObject)
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
    implements WorkerLoader.Worker<SignOutLoader.Result>
  {
    private final ResultCache<SignOutLoader.Result> cache;
    private final Object resultKey;
    
    private MyWorker(ResultCache<SignOutLoader.Result> paramResultCache, Object paramObject)
    {
      this.cache = paramResultCache;
      this.resultKey = paramObject;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<SignOutLoader.Result> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          SignOutLoader.Result localResult = (SignOutLoader.Result)this.cache.get(this.resultKey);
          if (localResult != null)
          {
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }
      XsapiUser.getInstance().signOut(new XsapiUser.SignOutCallback()
      {
        public void onError(int paramAnonymousInt1, int paramAnonymousInt2, String arg3)
        {
          SignOutLoader.Result localResult = new SignOutLoader.Result(null, new HttpError(paramAnonymousInt2, paramAnonymousInt1, ???));
          if (SignOutLoader.MyWorker.this.hasCache()) {}
          synchronized (SignOutLoader.MyWorker.this.cache)
          {
            SignOutLoader.MyWorker.this.cache.put(SignOutLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
        
        public void onSuccess()
        {
          SignOutLoader.Result localResult = new SignOutLoader.Result(new SignOutLoader.Data(), null);
          if (SignOutLoader.MyWorker.this.hasCache()) {}
          synchronized (SignOutLoader.MyWorker.this.cache)
          {
            SignOutLoader.MyWorker.this.cache.put(SignOutLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
      });
    }
  }
  
  public static class Result
    extends LoaderResult<SignOutLoader.Data>
  {
    protected Result(SignOutLoader.Data paramData, HttpError paramHttpError)
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\toolkit\SignOutLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */