package com.microsoft.xbox.toolkit.network;

import com.microsoft.xbox.toolkit.XLEThread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class XLEThreadPool
{
  public static XLEThreadPool biOperationsThreadPool = new XLEThreadPool(false, 1, "XLEPerfMarkerOperationsPool");
  public static XLEThreadPool nativeOperationsThreadPool = new XLEThreadPool(true, 4, "XLENativeOperationsPool");
  public static XLEThreadPool networkOperationsThreadPool = new XLEThreadPool(false, 3, "XLENetworkOperationsPool");
  public static XLEThreadPool textureThreadPool = new XLEThreadPool(false, 1, "XLETexturePool");
  private ExecutorService executor;
  private String name;
  
  public XLEThreadPool(boolean paramBoolean, final int paramInt, String paramString)
  {
    this.name = paramString;
    paramString = new ThreadFactory()
    {
      public Thread newThread(Runnable paramAnonymousRunnable)
      {
        paramAnonymousRunnable = new XLEThread(paramAnonymousRunnable, XLEThreadPool.this.name);
        paramAnonymousRunnable.setDaemon(true);
        paramAnonymousRunnable.setPriority(paramInt);
        return paramAnonymousRunnable;
      }
    };
    if (paramBoolean)
    {
      this.executor = Executors.newSingleThreadExecutor(paramString);
      return;
    }
    this.executor = Executors.newCachedThreadPool(paramString);
  }
  
  public void run(Runnable paramRunnable)
  {
    this.executor.execute(paramRunnable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\network\XLEThreadPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */