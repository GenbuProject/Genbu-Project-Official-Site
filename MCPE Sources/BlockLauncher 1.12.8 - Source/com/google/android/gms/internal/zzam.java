package com.google.android.gms.internal;

import android.content.Context;
import com.google.ads.afma.nano.NanoAfmaSignals.AFMASignals;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzam
  extends zzal
{
  private static AdvertisingIdClient zzok = null;
  private static CountDownLatch zzol = new CountDownLatch(1);
  private boolean zzom;
  
  protected zzam(Context paramContext, zzap paramzzap, boolean paramBoolean)
  {
    super(paramContext, paramzzap);
    this.zzom = paramBoolean;
  }
  
  public static zzam zza(String paramString, Context paramContext, boolean paramBoolean)
  {
    zzah localzzah = new zzah();
    zza(paramString, paramContext, localzzah);
    if (paramBoolean) {}
    try
    {
      if (zzok == null) {
        new Thread(new zzb(paramContext)).start();
      }
      return new zzam(paramContext, localzzah, paramBoolean);
    }
    finally {}
  }
  
  private void zza(Context paramContext, NanoAfmaSignals.AFMASignals paramAFMASignals)
  {
    if (!this.zzom) {}
    for (;;)
    {
      return;
      try
      {
        if (zzS())
        {
          paramContext = zzY();
          String str = paramContext.getId();
          if (str != null)
          {
            paramAFMASignals.didOptOut = Boolean.valueOf(paramContext.isLimitAdTrackingEnabled());
            paramAFMASignals.didSignalType = Integer.valueOf(5);
            paramAFMASignals.didSignal = str;
            zza(28, zzob);
          }
        }
        else
        {
          paramAFMASignals.didSignal = zzf(paramContext);
          zza(24, zzob);
          return;
        }
      }
      catch (zzal.zza paramContext) {}catch (IOException paramContext) {}
    }
  }
  
  zza zzY()
    throws IOException
  {
    try
    {
      if (!zzol.await(2L, TimeUnit.SECONDS))
      {
        zza localzza1 = new zza(null, false);
        return localzza1;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      return new zza(null, false);
    }
    try
    {
      if (zzok == null)
      {
        zza localzza2 = new zza(null, false);
        return localzza2;
      }
    }
    finally {}
    AdvertisingIdClient.Info localInfo = zzok.getInfo();
    return new zza(zzk(localInfo.getId()), localInfo.isLimitAdTrackingEnabled());
  }
  
  protected NanoAfmaSignals.AFMASignals zzc(Context paramContext)
  {
    NanoAfmaSignals.AFMASignals localAFMASignals = super.zzc(paramContext);
    zza(paramContext, localAFMASignals);
    return localAFMASignals;
  }
  
  class zza
  {
    private String zzon;
    private boolean zzoo;
    
    public zza(String paramString, boolean paramBoolean)
    {
      this.zzon = paramString;
      this.zzoo = paramBoolean;
    }
    
    public String getId()
    {
      return this.zzon;
    }
    
    public boolean isLimitAdTrackingEnabled()
    {
      return this.zzoo;
    }
  }
  
  private static final class zzb
    implements Runnable
  {
    private Context zzoq;
    
    public zzb(Context paramContext)
    {
      this.zzoq = paramContext.getApplicationContext();
      if (this.zzoq == null) {
        this.zzoq = paramContext;
      }
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: ldc 8
      //   2: monitorenter
      //   3: invokestatic 36	com/google/android/gms/internal/zzam:zzZ	()Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;
      //   6: ifnonnull +28 -> 34
      //   9: iconst_1
      //   10: invokestatic 42	com/google/android/gms/ads/identifier/AdvertisingIdClient:setShouldSkipGmsCoreVersionCheck	(Z)V
      //   13: new 38	com/google/android/gms/ads/identifier/AdvertisingIdClient
      //   16: dup
      //   17: aload_0
      //   18: getfield 24	com/google/android/gms/internal/zzam$zzb:zzoq	Landroid/content/Context;
      //   21: invokespecial 44	com/google/android/gms/ads/identifier/AdvertisingIdClient:<init>	(Landroid/content/Context;)V
      //   24: astore_1
      //   25: aload_1
      //   26: invokevirtual 47	com/google/android/gms/ads/identifier/AdvertisingIdClient:start	()V
      //   29: aload_1
      //   30: invokestatic 51	com/google/android/gms/internal/zzam:zza	(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;
      //   33: pop
      //   34: invokestatic 55	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   37: invokevirtual 60	java/util/concurrent/CountDownLatch:countDown	()V
      //   40: ldc 8
      //   42: monitorexit
      //   43: return
      //   44: astore_1
      //   45: aconst_null
      //   46: invokestatic 51	com/google/android/gms/internal/zzam:zza	(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;
      //   49: pop
      //   50: invokestatic 55	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   53: invokevirtual 60	java/util/concurrent/CountDownLatch:countDown	()V
      //   56: goto -16 -> 40
      //   59: astore_1
      //   60: ldc 8
      //   62: monitorexit
      //   63: aload_1
      //   64: athrow
      //   65: astore_1
      //   66: invokestatic 55	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   69: invokevirtual 60	java/util/concurrent/CountDownLatch:countDown	()V
      //   72: aload_1
      //   73: athrow
      //   74: astore_1
      //   75: goto -30 -> 45
      //   78: astore_1
      //   79: goto -34 -> 45
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	82	0	this	zzb
      //   24	6	1	localAdvertisingIdClient	AdvertisingIdClient
      //   44	1	1	localIOException	IOException
      //   59	5	1	localObject1	Object
      //   65	8	1	localObject2	Object
      //   74	1	1	localGooglePlayServicesNotAvailableException	com.google.android.gms.common.GooglePlayServicesNotAvailableException
      //   78	1	1	localGooglePlayServicesRepairableException	com.google.android.gms.common.GooglePlayServicesRepairableException
      // Exception table:
      //   from	to	target	type
      //   3	34	44	java/io/IOException
      //   34	40	59	finally
      //   40	43	59	finally
      //   50	56	59	finally
      //   60	63	59	finally
      //   66	74	59	finally
      //   3	34	65	finally
      //   45	50	65	finally
      //   3	34	74	com/google/android/gms/common/GooglePlayServicesNotAvailableException
      //   3	34	78	com/google/android/gms/common/GooglePlayServicesRepairableException
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */