package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzb
  extends zzd
{
  private final zzl zzQb;
  
  public zzb(zzf paramzzf, zzg paramzzg)
  {
    super(paramzzf);
    zzx.zzz(paramzzg);
    this.zzQb = paramzzg.zzj(paramzzf);
  }
  
  void onServiceConnected()
  {
    zzjk();
    this.zzQb.onServiceConnected();
  }
  
  public void setLocalDispatchPeriod(final int paramInt)
  {
    zzjv();
    zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(paramInt));
    zzjo().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzs(paramInt * 1000L);
      }
    });
  }
  
  public void start()
  {
    this.zzQb.start();
  }
  
  public void zzJ(final boolean paramBoolean)
  {
    zza("Network connectivity status changed", Boolean.valueOf(paramBoolean));
    zzjo().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzJ(paramBoolean);
      }
    });
  }
  
  public long zza(zzh paramzzh)
  {
    zzjv();
    zzx.zzz(paramzzh);
    zzjk();
    long l = this.zzQb.zza(paramzzh, true);
    if (l == 0L) {
      this.zzQb.zzc(paramzzh);
    }
    return l;
  }
  
  public void zza(final zzab paramzzab)
  {
    zzx.zzz(paramzzab);
    zzjv();
    zzb("Hit delivery requested", paramzzab);
    zzjo().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zza(paramzzab);
      }
    });
  }
  
  public void zza(final zzw paramzzw)
  {
    zzjv();
    zzjo().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzb(paramzzw);
      }
    });
  }
  
  public void zza(final String paramString, final Runnable paramRunnable)
  {
    zzx.zzh(paramString, "campaign param can't be empty");
    zzjo().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzbl(paramString);
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
    });
  }
  
  protected void zziJ()
  {
    this.zzQb.zza();
  }
  
  public void zzjc()
  {
    zzjv();
    zzjj();
    zzjo().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzjc();
      }
    });
  }
  
  public void zzjd()
  {
    zzjv();
    Context localContext = getContext();
    if ((AnalyticsReceiver.zzY(localContext)) && (AnalyticsService.zzZ(localContext)))
    {
      Intent localIntent = new Intent(localContext, AnalyticsService.class);
      localIntent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
      localContext.startService(localIntent);
      return;
    }
    zza(null);
  }
  
  public boolean zzje()
  {
    zzjv();
    Future localFuture = zzjo().zzc(new Callable()
    {
      public Void zzdt()
        throws Exception
      {
        zzb.zza(zzb.this).zzka();
        return null;
      }
    });
    try
    {
      localFuture.get(4L, TimeUnit.SECONDS);
      return true;
    }
    catch (InterruptedException localInterruptedException)
    {
      zzd("syncDispatchLocalHits interrupted", localInterruptedException);
      return false;
    }
    catch (ExecutionException localExecutionException)
    {
      zze("syncDispatchLocalHits failed", localExecutionException);
      return false;
    }
    catch (TimeoutException localTimeoutException)
    {
      zzd("syncDispatchLocalHits timed out", localTimeoutException);
    }
    return false;
  }
  
  public void zzjf()
  {
    zzjv();
    com.google.android.gms.measurement.zzg.zzjk();
    this.zzQb.zzjf();
  }
  
  public void zzjg()
  {
    zzbd("Radio powered up");
    zzjd();
  }
  
  void zzjh()
  {
    zzjk();
    this.zzQb.zzjh();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */