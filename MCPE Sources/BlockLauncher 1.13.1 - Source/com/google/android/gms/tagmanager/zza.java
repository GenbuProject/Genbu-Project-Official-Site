package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmt;
import java.io.IOException;

public class zza
{
  private static zza zzbhA;
  private static Object zzbhz = new Object();
  private volatile boolean mClosed = false;
  private final Context mContext;
  private final Thread zzLM;
  private volatile AdvertisingIdClient.Info zzPW;
  private volatile long zzbht = 900000L;
  private volatile long zzbhu = 30000L;
  private volatile long zzbhv;
  private volatile long zzbhw;
  private final Object zzbhx = new Object();
  private zza zzbhy = new zza()
  {
    public AdvertisingIdClient.Info zzFV()
    {
      try
      {
        AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(zza.zza(zza.this));
        return localInfo;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        zzbg.zzd("IllegalStateException getting Advertising Id Info", localIllegalStateException);
        return null;
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        zzbg.zzd("GooglePlayServicesRepairableException getting Advertising Id Info", localGooglePlayServicesRepairableException);
        return null;
      }
      catch (IOException localIOException)
      {
        zzbg.zzd("IOException getting Ad Id Info", localIOException);
        return null;
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        zzbg.zzd("GooglePlayServicesNotAvailableException getting Advertising Id Info", localGooglePlayServicesNotAvailableException);
        return null;
      }
      catch (Exception localException)
      {
        zzbg.zzd("Unknown exception. Could not get the Advertising Id Info.", localException);
      }
      return null;
    }
  };
  private final zzmq zzqW;
  
  private zza(Context paramContext)
  {
    this(paramContext, null, zzmt.zzsc());
  }
  
  public zza(Context paramContext, zza paramzza, zzmq paramzzmq)
  {
    this.zzqW = paramzzmq;
    if (paramContext != null) {}
    for (this.mContext = paramContext.getApplicationContext();; this.mContext = paramContext)
    {
      if (paramzza != null) {
        this.zzbhy = paramzza;
      }
      this.zzbhv = this.zzqW.currentTimeMillis();
      this.zzLM = new Thread(new Runnable()
      {
        public void run()
        {
          zza.zzb(zza.this);
        }
      });
      return;
    }
  }
  
  /* Error */
  private void zzFR()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 100	com/google/android/gms/tagmanager/zza:zzFS	()V
    //   6: aload_0
    //   7: ldc2_w 101
    //   10: invokevirtual 106	java/lang/Object:wait	(J)V
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: athrow
    //   21: astore_1
    //   22: goto -9 -> 13
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	25	0	this	zza
    //   16	4	1	localObject	Object
    //   21	1	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	13	16	finally
    //   13	15	16	finally
    //   17	19	16	finally
    //   2	13	21	java/lang/InterruptedException
  }
  
  private void zzFS()
  {
    if (this.zzqW.currentTimeMillis() - this.zzbhv > this.zzbhu) {}
    synchronized (this.zzbhx)
    {
      this.zzbhx.notify();
      this.zzbhv = this.zzqW.currentTimeMillis();
      return;
    }
  }
  
  private void zzFT()
  {
    if (this.zzqW.currentTimeMillis() - this.zzbhw > 3600000L) {
      this.zzPW = null;
    }
  }
  
  private void zzFU()
  {
    Process.setThreadPriority(10);
    while (!this.mClosed)
    {
      ??? = this.zzbhy.zzFV();
      if (??? != null)
      {
        this.zzPW = ((AdvertisingIdClient.Info)???);
        this.zzbhw = this.zzqW.currentTimeMillis();
        zzbg.zzaJ("Obtained fresh AdvertisingId info from GmsCore.");
      }
      try
      {
        notifyAll();
        try
        {
          synchronized (this.zzbhx)
          {
            this.zzbhx.wait(this.zzbht);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          zzbg.zzaJ("sleep interrupted in AdvertiserDataPoller thread; continuing");
        }
        return;
      }
      finally {}
    }
  }
  
  public static zza zzaW(Context paramContext)
  {
    if (zzbhA == null) {}
    synchronized (zzbhz)
    {
      if (zzbhA == null)
      {
        zzbhA = new zza(paramContext);
        zzbhA.start();
      }
      return zzbhA;
    }
  }
  
  public boolean isLimitAdTrackingEnabled()
  {
    if (this.zzPW == null) {
      zzFR();
    }
    for (;;)
    {
      zzFT();
      if (this.zzPW != null) {
        break;
      }
      return true;
      zzFS();
    }
    return this.zzPW.isLimitAdTrackingEnabled();
  }
  
  public void start()
  {
    this.zzLM.start();
  }
  
  public String zzFQ()
  {
    if (this.zzPW == null) {
      zzFR();
    }
    for (;;)
    {
      zzFT();
      if (this.zzPW != null) {
        break;
      }
      return null;
      zzFS();
    }
    return this.zzPW.getId();
  }
  
  public static abstract interface zza
  {
    public abstract AdvertisingIdClient.Info zzFV();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */