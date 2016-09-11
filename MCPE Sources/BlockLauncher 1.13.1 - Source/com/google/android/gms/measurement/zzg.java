package com.google.android.gms.measurement;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzps;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzg
{
  private static volatile zzg zzaUv;
  private final Context mContext;
  private volatile zzpq zzQX;
  private final List<zzh> zzaUw;
  private final zzb zzaUx;
  private final zza zzaUy;
  private Thread.UncaughtExceptionHandler zzaUz;
  
  zzg(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    zzx.zzz(paramContext);
    this.mContext = paramContext;
    this.zzaUy = new zza();
    this.zzaUw = new CopyOnWriteArrayList();
    this.zzaUx = new zzb();
  }
  
  public static zzg zzaS(Context paramContext)
  {
    zzx.zzz(paramContext);
    if (zzaUv == null) {}
    try
    {
      if (zzaUv == null) {
        zzaUv = new zzg(paramContext);
      }
      return zzaUv;
    }
    finally {}
  }
  
  private void zzb(zzc paramzzc)
  {
    zzx.zzcE("deliver should be called from worker thread");
    zzx.zzb(paramzzc.zzAz(), "Measurement must be submitted");
    Object localObject = paramzzc.zzAw();
    if (((List)localObject).isEmpty()) {}
    for (;;)
    {
      return;
      HashSet localHashSet = new HashSet();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        zzi localzzi = (zzi)((Iterator)localObject).next();
        Uri localUri = localzzi.zziA();
        if (!localHashSet.contains(localUri))
        {
          localHashSet.add(localUri);
          localzzi.zzb(paramzzc);
        }
      }
    }
  }
  
  public static void zzjk()
  {
    if (!(Thread.currentThread() instanceof zzc)) {
      throw new IllegalStateException("Call expected from worker thread");
    }
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public zzpq zzAH()
  {
    if (this.zzQX == null) {}
    Object localObject5;
    Object localObject3;
    try
    {
      zzpq localzzpq;
      PackageManager localPackageManager;
      String str2;
      if (this.zzQX == null)
      {
        localzzpq = new zzpq();
        localPackageManager = this.mContext.getPackageManager();
        str2 = this.mContext.getPackageName();
        localzzpq.setAppId(str2);
        localzzpq.setAppInstallerId(localPackageManager.getInstallerPackageName(str2));
        localObject5 = null;
        localObject3 = str2;
      }
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(this.mContext.getPackageName(), 0);
        localObject4 = localObject5;
        str1 = str2;
        if (localPackageInfo != null)
        {
          localObject3 = str2;
          localObject4 = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo);
          str1 = str2;
          localObject3 = str2;
          if (!TextUtils.isEmpty((CharSequence)localObject4))
          {
            localObject3 = str2;
            str1 = ((CharSequence)localObject4).toString();
          }
          localObject3 = str1;
          localObject4 = localPackageInfo.versionName;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          String str1;
          Log.e("GAv4", "Error retrieving package info: appName set to " + (String)localObject3);
          Object localObject4 = localObject5;
          Object localObject1 = localObject3;
        }
      }
      localzzpq.setAppName(str1);
      localzzpq.setAppVersion((String)localObject4);
      this.zzQX = localzzpq;
      return this.zzQX;
    }
    finally {}
  }
  
  public zzps zzAI()
  {
    DisplayMetrics localDisplayMetrics = this.mContext.getResources().getDisplayMetrics();
    zzps localzzps = new zzps();
    localzzps.setLanguage(zzam.zza(Locale.getDefault()));
    localzzps.zziB(localDisplayMetrics.widthPixels);
    localzzps.zziC(localDisplayMetrics.heightPixels);
    return localzzps;
  }
  
  public void zza(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    this.zzaUz = paramUncaughtExceptionHandler;
  }
  
  public <V> Future<V> zzc(Callable<V> paramCallable)
  {
    zzx.zzz(paramCallable);
    if ((Thread.currentThread() instanceof zzc))
    {
      paramCallable = new FutureTask(paramCallable);
      paramCallable.run();
      return paramCallable;
    }
    return this.zzaUy.submit(paramCallable);
  }
  
  void zze(final zzc paramzzc)
  {
    if (paramzzc.zzAD()) {
      throw new IllegalStateException("Measurement prototype can't be submitted");
    }
    if (paramzzc.zzAz()) {
      throw new IllegalStateException("Measurement can only be submitted once");
    }
    paramzzc = paramzzc.zzAu();
    paramzzc.zzAA();
    this.zzaUy.execute(new Runnable()
    {
      public void run()
      {
        paramzzc.zzAB().zza(paramzzc);
        Iterator localIterator = zzg.zza(zzg.this).iterator();
        while (localIterator.hasNext()) {
          ((zzh)localIterator.next()).zza(paramzzc);
        }
        zzg.zza(zzg.this, paramzzc);
      }
    });
  }
  
  public void zzf(Runnable paramRunnable)
  {
    zzx.zzz(paramRunnable);
    this.zzaUy.submit(paramRunnable);
  }
  
  private class zza
    extends ThreadPoolExecutor
  {
    public zza()
    {
      super(1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
      setThreadFactory(new zzg.zzb(null));
    }
    
    protected <T> RunnableFuture<T> newTaskFor(Runnable paramRunnable, T paramT)
    {
      new FutureTask(paramRunnable, paramT)
      {
        protected void setException(Throwable paramAnonymousThrowable)
        {
          Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = zzg.zzb(zzg.this);
          if (localUncaughtExceptionHandler != null) {
            localUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), paramAnonymousThrowable);
          }
          for (;;)
          {
            super.setException(paramAnonymousThrowable);
            return;
            if (Log.isLoggable("GAv4", 6)) {
              Log.e("GAv4", "MeasurementExecutor: job failed with " + paramAnonymousThrowable);
            }
          }
        }
      };
    }
  }
  
  private static class zzb
    implements ThreadFactory
  {
    private static final AtomicInteger zzaUD = new AtomicInteger();
    
    public Thread newThread(Runnable paramRunnable)
    {
      return new zzg.zzc(paramRunnable, "measurement-" + zzaUD.incrementAndGet());
    }
  }
  
  private static class zzc
    extends Thread
  {
    zzc(Runnable paramRunnable, String paramString)
    {
      super(paramString);
    }
    
    public void run()
    {
      Process.setThreadPriority(10);
      super.run();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */