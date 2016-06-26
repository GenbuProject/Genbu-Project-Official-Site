package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.clearcut.zzb.zzb;
import com.google.android.gms.clearcut.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class zzlv
  implements zzc
{
  private static final Object zzafn = new Object();
  private static final zze zzafo = new zze(null);
  private static final long zzafp = TimeUnit.MILLISECONDS.convert(2L, TimeUnit.MINUTES);
  private GoogleApiClient zzaaj = null;
  private final zza zzafq;
  private final Object zzafr = new Object();
  private long zzafs = 0L;
  private final long zzaft;
  private ScheduledFuture<?> zzafu = null;
  private final Runnable zzafv = new Runnable()
  {
    public void run()
    {
      synchronized (zzlv.zza(zzlv.this))
      {
        if ((zzlv.zzb(zzlv.this) <= zzlv.zzc(zzlv.this).elapsedRealtime()) && (zzlv.zzd(zzlv.this) != null))
        {
          Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
          zzlv.zzd(zzlv.this).disconnect();
          zzlv.zza(zzlv.this, null);
        }
        return;
      }
    }
  };
  private final zzmq zzqW;
  
  public zzlv()
  {
    this(new zzmt(), zzafp, new zzb());
  }
  
  public zzlv(zzmq paramzzmq, long paramLong, zza paramzza)
  {
    this.zzqW = paramzzmq;
    this.zzaft = paramLong;
    this.zzafq = paramzza;
  }
  
  private static void zza(LogEventParcelable paramLogEventParcelable)
  {
    if ((paramLogEventParcelable.zzafl != null) && (paramLogEventParcelable.zzafk.zzbuY.length == 0)) {
      paramLogEventParcelable.zzafk.zzbuY = paramLogEventParcelable.zzafl.zzoF();
    }
    if ((paramLogEventParcelable.zzafm != null) && (paramLogEventParcelable.zzafk.zzbvf.length == 0)) {
      paramLogEventParcelable.zzafk.zzbvf = paramLogEventParcelable.zzafm.zzoF();
    }
    paramLogEventParcelable.zzafi = zzsu.toByteArray(paramLogEventParcelable.zzafk);
  }
  
  private zzd zzb(GoogleApiClient paramGoogleApiClient, LogEventParcelable paramLogEventParcelable)
  {
    zzafo.zzoH();
    paramGoogleApiClient = new zzd(paramLogEventParcelable, paramGoogleApiClient);
    paramGoogleApiClient.zza(new PendingResult.zza()
    {
      public void zzu(Status paramAnonymousStatus)
      {
        zzlv.zzoG().zzoI();
      }
    });
    return paramGoogleApiClient;
  }
  
  public PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, LogEventParcelable paramLogEventParcelable)
  {
    zza(paramLogEventParcelable);
    return paramGoogleApiClient.zza(zzb(paramGoogleApiClient, paramLogEventParcelable));
  }
  
  public boolean zza(GoogleApiClient paramGoogleApiClient, long paramLong, TimeUnit paramTimeUnit)
  {
    try
    {
      boolean bool = zzafo.zza(paramLong, paramTimeUnit);
      return bool;
    }
    catch (InterruptedException paramGoogleApiClient)
    {
      Log.e("ClearcutLoggerApiImpl", "flush interrupted");
      Thread.currentThread().interrupt();
    }
    return false;
  }
  
  public static abstract interface zza {}
  
  public static class zzb
    implements zzlv.zza
  {}
  
  static abstract class zzc<R extends Result>
    extends zza.zza<R, zzlw>
  {
    public zzc(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  final class zzd
    extends zzlv.zzc<Status>
  {
    private final LogEventParcelable zzafx;
    
    zzd(LogEventParcelable paramLogEventParcelable, GoogleApiClient paramGoogleApiClient)
    {
      super();
      this.zzafx = paramLogEventParcelable;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zzd)) {
        return false;
      }
      paramObject = (zzd)paramObject;
      return this.zzafx.equals(((zzd)paramObject).zzafx);
    }
    
    public String toString()
    {
      return "MethodImpl(" + this.zzafx + ")";
    }
    
    protected void zza(zzlw paramzzlw)
      throws RemoteException
    {
      zzlx.zza local1 = new zzlx.zza()
      {
        public void zzv(Status paramAnonymousStatus)
        {
          zzlv.zzd.this.zza(paramAnonymousStatus);
        }
      };
      try
      {
        zzlv.zzb(this.zzafx);
        paramzzlw.zza(local1, this.zzafx);
        return;
      }
      catch (Throwable paramzzlw)
      {
        Log.e("ClearcutLoggerApiImpl", "MessageNanoProducer " + this.zzafx.zzafl.toString() + " threw: " + paramzzlw.toString());
      }
    }
    
    protected Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
  
  private static final class zze
  {
    private int mSize = 0;
    
    public boolean zza(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      long l1 = System.currentTimeMillis();
      paramLong = TimeUnit.MILLISECONDS.convert(paramLong, paramTimeUnit);
      for (;;)
      {
        try
        {
          if (this.mSize == 0) {
            return true;
          }
          if (paramLong <= 0L) {
            return false;
          }
        }
        finally {}
        wait(paramLong);
        long l2 = System.currentTimeMillis();
        paramLong -= l2 - l1;
      }
    }
    
    public void zzoH()
    {
      try
      {
        this.mSize += 1;
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    public void zzoI()
    {
      try
      {
        if (this.mSize == 0) {
          throw new RuntimeException("too many decrements");
        }
      }
      finally {}
      this.mSize -= 1;
      if (this.mSize == 0) {
        notifyAll();
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzlv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */