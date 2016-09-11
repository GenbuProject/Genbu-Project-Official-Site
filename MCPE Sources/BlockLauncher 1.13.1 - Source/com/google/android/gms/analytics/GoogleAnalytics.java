package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzak;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzn;
import com.google.android.gms.analytics.internal.zzy;
import com.google.android.gms.analytics.internal.zzy.zza;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics
  extends zza
{
  private static List<Runnable> zzPe = new ArrayList();
  private boolean zzPf;
  private Set<zza> zzPg = new HashSet();
  private boolean zzPh;
  private boolean zzPi;
  private volatile boolean zzPj;
  private boolean zzPk;
  private boolean zzqA;
  
  public GoogleAnalytics(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public static GoogleAnalytics getInstance(Context paramContext)
  {
    return zzf.zzaa(paramContext).zzjz();
  }
  
  public static void zziF()
  {
    try
    {
      if (zzPe != null)
      {
        Iterator localIterator = zzPe.iterator();
        while (localIterator.hasNext()) {
          ((Runnable)localIterator.next()).run();
        }
        zzPe = null;
      }
    }
    finally {}
  }
  
  private zzb zziH()
  {
    return zzix().zziH();
  }
  
  private zzan zziI()
  {
    return zzix().zziI();
  }
  
  public void dispatchLocalHits()
  {
    zziH().zzjd();
  }
  
  @TargetApi(14)
  public void enableAutoActivityReports(Application paramApplication)
  {
    if ((Build.VERSION.SDK_INT >= 14) && (!this.zzPh))
    {
      paramApplication.registerActivityLifecycleCallbacks(new zzb());
      this.zzPh = true;
    }
  }
  
  public boolean getAppOptOut()
  {
    return this.zzPj;
  }
  
  public String getClientId()
  {
    zzx.zzcE("getClientId can not be called from the main thread");
    return zzix().zzjC().zzkk();
  }
  
  @Deprecated
  public Logger getLogger()
  {
    return zzae.getLogger();
  }
  
  public boolean isDryRunEnabled()
  {
    return this.zzPi;
  }
  
  public boolean isInitialized()
  {
    return (this.zzqA) && (!this.zzPf);
  }
  
  public Tracker newTracker(int paramInt)
  {
    try
    {
      Tracker localTracker = new Tracker(zzix(), null, null);
      if (paramInt > 0)
      {
        zzal localzzal = (zzal)new zzak(zzix()).zzah(paramInt);
        if (localzzal != null) {
          localTracker.zza(localzzal);
        }
      }
      localTracker.zza();
      return localTracker;
    }
    finally {}
  }
  
  public Tracker newTracker(String paramString)
  {
    try
    {
      paramString = new Tracker(zzix(), paramString, null);
      paramString.zza();
      return paramString;
    }
    finally {}
  }
  
  public void reportActivityStart(Activity paramActivity)
  {
    if (!this.zzPh) {
      zzj(paramActivity);
    }
  }
  
  public void reportActivityStop(Activity paramActivity)
  {
    if (!this.zzPh) {
      zzk(paramActivity);
    }
  }
  
  public void setAppOptOut(boolean paramBoolean)
  {
    this.zzPj = paramBoolean;
    if (this.zzPj) {
      zziH().zzjc();
    }
  }
  
  public void setDryRun(boolean paramBoolean)
  {
    this.zzPi = paramBoolean;
  }
  
  public void setLocalDispatchPeriod(int paramInt)
  {
    zziH().setLocalDispatchPeriod(paramInt);
  }
  
  @Deprecated
  public void setLogger(Logger paramLogger)
  {
    zzae.setLogger(paramLogger);
    if (!this.zzPk)
    {
      Log.i((String)zzy.zzRL.get(), "GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + (String)zzy.zzRL.get() + " DEBUG");
      this.zzPk = true;
    }
  }
  
  public void zza()
  {
    zziE();
    this.zzqA = true;
  }
  
  void zza(zza paramzza)
  {
    this.zzPg.add(paramzza);
    paramzza = zzix().getContext();
    if ((paramzza instanceof Application)) {
      enableAutoActivityReports((Application)paramzza);
    }
  }
  
  void zzb(zza paramzza)
  {
    this.zzPg.remove(paramzza);
  }
  
  void zziE()
  {
    zzan localzzan = zziI();
    if (localzzan.zzlj()) {
      getLogger().setLogLevel(localzzan.getLogLevel());
    }
    if (localzzan.zzln()) {
      setDryRun(localzzan.zzlo());
    }
    if (localzzan.zzlj())
    {
      Logger localLogger = zzae.getLogger();
      if (localLogger != null) {
        localLogger.setLogLevel(localzzan.getLogLevel());
      }
    }
  }
  
  void zziG()
  {
    zziH().zzje();
  }
  
  void zzj(Activity paramActivity)
  {
    Iterator localIterator = this.zzPg.iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zzl(paramActivity);
    }
  }
  
  void zzk(Activity paramActivity)
  {
    Iterator localIterator = this.zzPg.iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zzm(paramActivity);
    }
  }
  
  static abstract interface zza
  {
    public abstract void zzl(Activity paramActivity);
    
    public abstract void zzm(Activity paramActivity);
  }
  
  @TargetApi(14)
  class zzb
    implements Application.ActivityLifecycleCallbacks
  {
    zzb() {}
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    public void onActivityPaused(Activity paramActivity) {}
    
    public void onActivityResumed(Activity paramActivity) {}
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity)
    {
      GoogleAnalytics.this.zzj(paramActivity);
    }
    
    public void onActivityStopped(Activity paramActivity)
    {
      GoogleAnalytics.this.zzk(paramActivity);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\GoogleAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */