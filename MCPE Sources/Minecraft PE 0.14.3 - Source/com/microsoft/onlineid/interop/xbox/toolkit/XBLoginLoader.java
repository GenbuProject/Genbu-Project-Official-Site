package com.microsoft.onlineid.interop.xbox.toolkit;

import android.content.Context;
import com.microsoft.onlineid.interop.Interop;
import com.microsoft.onlineid.interop.Interop.XBLoginCallback;
import com.microsoft.onlineid.interop.xbox.util.AuthFlowResult;

public class XBLoginLoader
  extends WorkerLoader<Result>
{
  public XBLoginLoader(Context paramContext, long paramLong, String paramString)
  {
    this(paramContext, paramLong, paramString, null, null);
  }
  
  public XBLoginLoader(Context paramContext, long paramLong, String paramString, Cache paramCache, Object paramObject)
  {
    super(paramContext, new MyWorker(paramLong, paramString, paramCache, paramObject, null));
  }
  
  protected boolean isDataReleased(Result paramResult)
  {
    return paramResult.isReleased();
  }
  
  protected void releaseData(Result paramResult)
  {
    paramResult.release();
  }
  
  public static abstract interface Cache
  {
    public abstract void clear();
    
    public abstract XBLoginLoader.Result get(Object paramObject);
    
    public abstract XBLoginLoader.Result put(Object paramObject, XBLoginLoader.Result paramResult);
    
    public abstract XBLoginLoader.Result remove(Object paramObject);
  }
  
  public static class Data
  {
    private final AuthFlowResult authFlowResult;
    private final boolean createAccount;
    
    public Data(AuthFlowResult paramAuthFlowResult, boolean paramBoolean)
    {
      this.authFlowResult = paramAuthFlowResult;
      this.createAccount = paramBoolean;
    }
    
    public AuthFlowResult getAuthFlowResult()
    {
      return this.authFlowResult;
    }
    
    public boolean isCreateAccount()
    {
      return this.createAccount;
    }
  }
  
  private static class MyWorker
    implements WorkerLoader.Worker<XBLoginLoader.Result>
  {
    private final XBLoginLoader.Cache cache;
    private final Object resultKey;
    private final String rpsTicket;
    private final long userPtr;
    
    private MyWorker(long paramLong, String paramString, XBLoginLoader.Cache paramCache, Object paramObject)
    {
      this.userPtr = paramLong;
      this.rpsTicket = paramString;
      this.cache = paramCache;
      this.resultKey = paramObject;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<XBLoginLoader.Result> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          XBLoginLoader.Result localResult = this.cache.get(this.resultKey);
          if (localResult != null)
          {
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }
      Interop.InvokeXBLogin(this.userPtr, this.rpsTicket, new Interop.XBLoginCallback()
      {
        public void onError(int paramAnonymousInt1, int paramAnonymousInt2, String arg3)
        {
          XBLoginLoader.Result localResult = new XBLoginLoader.Result(null, new HttpError(paramAnonymousInt2, paramAnonymousInt1, ???));
          if (XBLoginLoader.MyWorker.this.hasCache()) {}
          synchronized (XBLoginLoader.MyWorker.this.cache)
          {
            XBLoginLoader.MyWorker.this.cache.put(XBLoginLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
        
        public void onLogin(long paramAnonymousLong, boolean paramAnonymousBoolean)
        {
          XBLoginLoader.Result localResult = new XBLoginLoader.Result(new XBLoginLoader.Data(new AuthFlowResult(paramAnonymousLong), paramAnonymousBoolean), null);
          if (XBLoginLoader.MyWorker.this.hasCache()) {}
          synchronized (XBLoginLoader.MyWorker.this.cache)
          {
            XBLoginLoader.MyWorker.this.cache.put(XBLoginLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
      });
    }
  }
  
  public static class Result
    extends LoaderResult<XBLoginLoader.Data>
  {
    protected Result(XBLoginLoader.Data paramData, HttpError paramHttpError)
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\toolkit\XBLoginLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */