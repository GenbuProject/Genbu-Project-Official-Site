package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import com.microsoft.xbox.idp.interop.Interop;
import com.microsoft.xbox.idp.interop.Interop.XBLogoutCallback;

public class XBLogoutLoader
  extends WorkerLoader<Result>
{
  public XBLogoutLoader(Context paramContext, long paramLong)
  {
    super(paramContext, new MyWorker(paramLong, null));
  }
  
  protected boolean isDataReleased(Result paramResult)
  {
    return paramResult.isReleased();
  }
  
  protected void releaseData(Result paramResult)
  {
    paramResult.release();
  }
  
  private static class MyWorker
    implements WorkerLoader.Worker<XBLogoutLoader.Result>
  {
    private final long userPtr;
    
    private MyWorker(long paramLong)
    {
      this.userPtr = paramLong;
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<XBLogoutLoader.Result> paramResultListener)
    {
      Interop.InvokeXBLogout(this.userPtr, new Interop.XBLogoutCallback()
      {
        public void onLoggedOut()
        {
          paramResultListener.onResult(new XBLogoutLoader.Result());
        }
      });
    }
  }
  
  public static class Result
    extends LoaderResult<Void>
  {
    protected Result()
    {
      super(null);
    }
    
    public boolean isReleased()
    {
      return true;
    }
    
    public void release() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\toolkit\XBLogoutLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */