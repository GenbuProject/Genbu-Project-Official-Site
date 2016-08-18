package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import com.microsoft.xbox.idp.interop.Interop;
import com.microsoft.xbox.idp.interop.Interop.Callback;
import com.microsoft.xbox.idp.util.AuthFlowResult;
import com.microsoft.xbox.idp.util.ResultCache;

public class XTokenLoader
  extends WorkerLoader<Result>
{
  public XTokenLoader(Context paramContext, long paramLong)
  {
    this(paramContext, paramLong, null, null);
  }
  
  public XTokenLoader(Context paramContext, long paramLong, ResultCache<Result> paramResultCache, Object paramObject)
  {
    super(paramContext, new MyWorker(paramLong, paramResultCache, paramObject));
  }
  
  protected boolean isDataReleased(Result paramResult)
  {
    return paramResult.isReleased();
  }
  
  protected void releaseData(Result paramResult)
  {
    paramResult.release();
  }
  
  public static class Data
  {
    private final AuthFlowResult authFlowResult;
    
    public Data(AuthFlowResult paramAuthFlowResult)
    {
      this.authFlowResult = paramAuthFlowResult;
    }
    
    public AuthFlowResult getAuthFlowResult()
    {
      return this.authFlowResult;
    }
  }
  
  private static class MyWorker
    implements WorkerLoader.Worker<XTokenLoader.Result>
  {
    private final ResultCache<XTokenLoader.Result> cache;
    private final Object resultKey;
    private final long userPtr;
    
    public MyWorker(long paramLong, ResultCache<XTokenLoader.Result> paramResultCache, Object paramObject)
    {
      this.userPtr = paramLong;
      this.cache = paramResultCache;
      this.resultKey = paramObject;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<XTokenLoader.Result> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          XTokenLoader.Result localResult = (XTokenLoader.Result)this.cache.get(this.resultKey);
          if (localResult != null)
          {
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }
      Interop.InvokeXTokenCallback(this.userPtr, new Interop.Callback()
      {
        public void onError(int paramAnonymousInt1, int paramAnonymousInt2, String arg3)
        {
          XTokenLoader.Result localResult = new XTokenLoader.Result(null, new HttpError(paramAnonymousInt2, paramAnonymousInt1, ???));
          if (XTokenLoader.MyWorker.this.hasCache()) {}
          synchronized (XTokenLoader.MyWorker.this.cache)
          {
            XTokenLoader.MyWorker.this.cache.put(XTokenLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
        
        public void onXTokenAcquired(long paramAnonymousLong)
        {
          XTokenLoader.Result localResult = new XTokenLoader.Result(new XTokenLoader.Data(new AuthFlowResult(paramAnonymousLong)), null);
          if (XTokenLoader.MyWorker.this.hasCache()) {}
          synchronized (XTokenLoader.MyWorker.this.cache)
          {
            XTokenLoader.MyWorker.this.cache.put(XTokenLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
      });
    }
  }
  
  public static class Result
    extends LoaderResult<XTokenLoader.Data>
  {
    protected Result(XTokenLoader.Data paramData, HttpError paramHttpError)
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\toolkit\XTokenLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */