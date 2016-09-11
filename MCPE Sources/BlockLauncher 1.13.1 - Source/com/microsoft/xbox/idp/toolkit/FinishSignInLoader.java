package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import com.microsoft.xbox.idp.interop.Interop.AuthFlowScreenStatus;
import com.microsoft.xbox.idp.interop.XsapiUser;
import com.microsoft.xbox.idp.interop.XsapiUser.FinishSignInCallback;
import com.microsoft.xbox.idp.util.ResultCache;

public class FinishSignInLoader
  extends WorkerLoader<Result>
{
  public FinishSignInLoader(Context paramContext, Interop.AuthFlowScreenStatus paramAuthFlowScreenStatus, String paramString, ResultCache<Result> paramResultCache, Object paramObject)
  {
    super(paramContext, new MyWorker(paramAuthFlowScreenStatus, paramString, paramResultCache, paramObject, null));
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
    implements WorkerLoader.Worker<FinishSignInLoader.Result>
  {
    private final Interop.AuthFlowScreenStatus authStatus;
    private final ResultCache<FinishSignInLoader.Result> cache;
    private final String cid;
    private final Object resultKey;
    
    private MyWorker(Interop.AuthFlowScreenStatus paramAuthFlowScreenStatus, String paramString, ResultCache<FinishSignInLoader.Result> paramResultCache, Object paramObject)
    {
      this.authStatus = paramAuthFlowScreenStatus;
      this.cid = paramString;
      this.cache = paramResultCache;
      this.resultKey = paramObject;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<FinishSignInLoader.Result> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          FinishSignInLoader.Result localResult = (FinishSignInLoader.Result)this.cache.get(this.resultKey);
          if (localResult != null)
          {
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }
      XsapiUser.getInstance().finishSignIn(new XsapiUser.FinishSignInCallback()
      {
        public void onError(int paramAnonymousInt1, int paramAnonymousInt2, String arg3)
        {
          FinishSignInLoader.Result localResult = new FinishSignInLoader.Result(null, new HttpError(paramAnonymousInt2, paramAnonymousInt1, ???));
          if (FinishSignInLoader.MyWorker.this.hasCache()) {}
          synchronized (FinishSignInLoader.MyWorker.this.cache)
          {
            FinishSignInLoader.MyWorker.this.cache.put(FinishSignInLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
        
        public void onSuccess()
        {
          FinishSignInLoader.Result localResult = new FinishSignInLoader.Result(new FinishSignInLoader.Data(), null);
          if (FinishSignInLoader.MyWorker.this.hasCache()) {}
          synchronized (FinishSignInLoader.MyWorker.this.cache)
          {
            FinishSignInLoader.MyWorker.this.cache.put(FinishSignInLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }, this.authStatus, this.cid);
    }
  }
  
  public static class Result
    extends LoaderResult<FinishSignInLoader.Data>
  {
    protected Result(FinishSignInLoader.Data paramData, HttpError paramHttpError)
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\toolkit\FinishSignInLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */