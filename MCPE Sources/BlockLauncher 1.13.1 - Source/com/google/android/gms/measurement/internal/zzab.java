package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.measurement.AppMeasurement.zza;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

public class zzab
  extends zzz
{
  private zza zzaYD;
  private AppMeasurement.zza zzaYE;
  private boolean zzaYF;
  
  protected zzab(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  @WorkerThread
  private void zzDm()
  {
    try
    {
      zzh(Class.forName(zzDn()));
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      zzAo().zzCI().zzfg("Tag Manager is not found and thus will not be used");
    }
  }
  
  private String zzDn()
  {
    return "com.google.android.gms.tagmanager.TagManagerService";
  }
  
  private void zza(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean, String paramString3)
  {
    zza(paramString1, paramString2, paramBundle, paramBoolean, paramString3, zzjl().currentTimeMillis());
  }
  
  private void zza(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean, String paramString3, long paramLong)
  {
    zzx.zzcM(paramString1);
    zzCk().zzfr(paramString2);
    Bundle localBundle = new Bundle();
    if (paramBundle != null)
    {
      int k = zzCp().zzBA();
      Iterator localIterator = paramBundle.keySet().iterator();
      i = 0;
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        zzCk().zzft(str);
        int j = i;
        if (zzaj.zzfq(str))
        {
          j = i + 1;
          if (j > k) {
            break label184;
          }
        }
        label184:
        for (boolean bool = true;; bool = false)
        {
          zzx.zzb(bool, "Event can't contain more then " + k + " params");
          Object localObject = zzCk().zzk(str, paramBundle.get(str));
          i = j;
          if (localObject == null) {
            break;
          }
          zzCk().zza(localBundle, str, localObject);
          i = j;
          break;
        }
      }
    }
    int i = zzCp().zzBD();
    if (paramString1.length() <= i) {}
    for (paramBundle = paramString1;; paramBundle = paramString1.substring(0, i))
    {
      localBundle.putString("_o", paramBundle);
      zza(paramString1, paramString2, paramLong, localBundle, paramBoolean, paramString3);
      return;
    }
  }
  
  @WorkerThread
  private void zza(String paramString1, String paramString2, Object paramObject, long paramLong)
  {
    zzx.zzcM(paramString1);
    zzx.zzcM(paramString2);
    zzjk();
    zzjj();
    zzjv();
    if (!zzCo().zzAr()) {
      zzAo().zzCJ().zzfg("User property not set since app measurement is disabled");
    }
    while (!this.zzaTV.zzCS()) {
      return;
    }
    zzAo().zzCJ().zze("Setting user property (FE)", paramString2, paramObject);
    paramString1 = new UserAttributeParcel(paramString2, paramLong, paramObject, paramString1);
    zzCi().zza(paramString1);
  }
  
  @WorkerThread
  private void zzas(boolean paramBoolean)
  {
    zzjk();
    zzjj();
    zzjv();
    zzAo().zzCJ().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(paramBoolean));
    zzCo().setMeasurementEnabled(paramBoolean);
    zzCi().zzDo();
  }
  
  @WorkerThread
  private void zzb(String paramString1, String paramString2, long paramLong, Bundle paramBundle, boolean paramBoolean, String paramString3)
  {
    zzx.zzcM(paramString1);
    zzx.zzcM(paramString2);
    zzx.zzz(paramBundle);
    zzjk();
    zzjv();
    if (!zzCo().zzAr()) {
      zzAo().zzCJ().zzfg("Event not sent since app measurement is disabled");
    }
    do
    {
      return;
      if (!this.zzaYF)
      {
        this.zzaYF = true;
        zzDm();
      }
      if ((paramBoolean) && (this.zzaYE != null) && (!zzaj.zzfv(paramString2)))
      {
        zzAo().zzCJ().zze("Passing event to registered event handler (FE)", paramString2, paramBundle);
        this.zzaYE.zza(paramString1, paramString2, paramBundle, paramLong);
        return;
      }
    } while (!this.zzaTV.zzCS());
    zzAo().zzCJ().zze("Logging event (FE)", paramString2, paramBundle);
    paramString1 = new EventParcel(paramString2, new EventParams(paramBundle), paramString1, paramLong);
    zzCi().zzb(paramString1, paramString3);
  }
  
  public void setMeasurementEnabled(final boolean paramBoolean)
  {
    zzjv();
    zzjj();
    zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzab.zza(zzab.this, paramBoolean);
      }
    });
  }
  
  @TargetApi(14)
  public void zzDk()
  {
    if ((getContext().getApplicationContext() instanceof Application))
    {
      Application localApplication = (Application)getContext().getApplicationContext();
      if (this.zzaYD == null) {
        this.zzaYD = new zza(null);
      }
      localApplication.unregisterActivityLifecycleCallbacks(this.zzaYD);
      localApplication.registerActivityLifecycleCallbacks(this.zzaYD);
      zzAo().zzCK().zzfg("Registered activity lifecycle callback");
    }
  }
  
  @WorkerThread
  public void zzDl()
  {
    zzjk();
    zzjj();
    zzjv();
    if (!this.zzaTV.zzCS()) {}
    String str;
    do
    {
      return;
      zzCi().zzDl();
      str = zzCo().zzCQ();
    } while ((TextUtils.isEmpty(str)) || (str.equals(zzCh().zzCy())));
    Bundle localBundle = new Bundle();
    localBundle.putString("_po", str);
    zze("auto", "_ou", localBundle);
  }
  
  protected void zza(final String paramString1, final String paramString2, final long paramLong, Bundle paramBundle, final boolean paramBoolean, final String paramString3)
  {
    zzx.zzz(paramBundle);
    zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzab.zza(zzab.this, paramString1, paramString2, paramLong, paramBoolean, paramString3, this.zzaHU);
      }
    });
  }
  
  void zza(final String paramString1, final String paramString2, final long paramLong, final Object paramObject)
  {
    zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzab.zza(zzab.this, paramString1, paramString2, paramObject, paramLong);
      }
    });
  }
  
  public void zza(String paramString1, String paramString2, Object paramObject)
  {
    zzx.zzcM(paramString1);
    long l = zzjl().currentTimeMillis();
    zzCk().zzfs(paramString2);
    if (paramObject != null)
    {
      zzCk().zzl(paramString2, paramObject);
      paramObject = zzCk().zzm(paramString2, paramObject);
      if (paramObject != null) {
        zza(paramString1, paramString2, l, paramObject);
      }
      return;
    }
    zza(paramString1, paramString2, l, null);
  }
  
  public void zze(String paramString1, String paramString2, Bundle paramBundle)
  {
    zzjj();
    zza(paramString1, paramString2, paramBundle, true, null);
  }
  
  @WorkerThread
  public void zzh(Class<?> paramClass)
  {
    try
    {
      paramClass.getDeclaredMethod("initialize", new Class[] { Context.class }).invoke(null, new Object[] { getContext() });
      return;
    }
    catch (Exception paramClass)
    {
      zzAo().zzCF().zzj("Failed to invoke Tag Manager's initialize() method", paramClass);
    }
  }
  
  protected void zziJ() {}
  
  @TargetApi(14)
  @MainThread
  private class zza
    implements Application.ActivityLifecycleCallbacks
  {
    private zza() {}
    
    private boolean zzfo(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        zzab.this.zza("auto", "_ldl", paramString);
        return true;
      }
      return false;
    }
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
    {
      try
      {
        zzab.this.zzAo().zzCK().zzfg("onActivityCreated");
        paramActivity = paramActivity.getIntent();
        if (paramActivity == null) {
          return;
        }
        paramActivity = paramActivity.getData();
        if ((paramActivity == null) || (!paramActivity.isHierarchical())) {
          return;
        }
        paramActivity = paramActivity.getQueryParameter("referrer");
        if (TextUtils.isEmpty(paramActivity)) {
          return;
        }
        if (!paramActivity.contains("gclid"))
        {
          zzab.this.zzAo().zzCJ().zzfg("Activity created with data 'referrer' param without gclid");
          return;
        }
      }
      catch (Throwable paramActivity)
      {
        zzab.this.zzAo().zzCE().zzj("Throwable caught in onActivityCreated", paramActivity);
        return;
      }
      zzab.this.zzAo().zzCJ().zzj("Activity created with referrer", paramActivity);
      zzfo(paramActivity);
    }
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    @MainThread
    public void onActivityPaused(Activity paramActivity)
    {
      zzab.this.zzCm().zzDw();
    }
    
    @MainThread
    public void onActivityResumed(Activity paramActivity)
    {
      zzab.this.zzCm().zzDu();
    }
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity) {}
    
    public void onActivityStopped(Activity paramActivity) {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */