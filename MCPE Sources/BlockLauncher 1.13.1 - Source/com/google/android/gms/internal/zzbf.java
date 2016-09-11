package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
@TargetApi(14)
public class zzbf
  extends Thread
{
  private boolean mStarted = false;
  private boolean zzam = false;
  private final Object zzpV;
  private final int zzsK;
  private final int zzsM;
  private boolean zzsY = false;
  private final zzbe zzsZ;
  private final zzbd zzta;
  private final zzha zztb;
  private final int zztc;
  private final int zztd;
  private final int zzte;
  
  public zzbf(zzbe paramzzbe, zzbd paramzzbd, zzha paramzzha)
  {
    this.zzsZ = paramzzbe;
    this.zzta = paramzzbd;
    this.zztb = paramzzha;
    this.zzpV = new Object();
    this.zzsK = ((Integer)zzbt.zzwk.get()).intValue();
    this.zztd = ((Integer)zzbt.zzwl.get()).intValue();
    this.zzsM = ((Integer)zzbt.zzwm.get()).intValue();
    this.zzte = ((Integer)zzbt.zzwn.get()).intValue();
    this.zztc = ((Integer)zzbt.zzwo.get()).intValue();
    setName("ContentFetchTask");
  }
  
  public void run()
  {
    while (!this.zzam) {
      try
      {
        if (zzcH())
        {
          Activity localActivity = this.zzsZ.getActivity();
          if (localActivity == null) {
            zzin.zzaI("ContentFetchThread: no activity");
          }
        }
      }
      catch (Throwable localThrowable)
      {
        zzin.zzb("Error in ContentFetchTask", localThrowable);
        this.zztb.zza(localThrowable, true);
        synchronized (this.zzpV)
        {
          for (;;)
          {
            boolean bool = this.zzsY;
            if (!bool) {
              break;
            }
            try
            {
              zzin.zzaI("ContentFetchTask: waiting");
              this.zzpV.wait();
            }
            catch (InterruptedException localInterruptedException) {}
          }
          zza((Activity)???);
          for (;;)
          {
            Thread.sleep(this.zztc * 1000);
            break;
            zzin.zzaI("ContentFetchTask: sleeping");
            zzcJ();
          }
        }
      }
    }
  }
  
  public void wakeup()
  {
    synchronized (this.zzpV)
    {
      this.zzsY = false;
      this.zzpV.notifyAll();
      zzin.zzaI("ContentFetchThread: wakeup");
      return;
    }
  }
  
  zza zza(View paramView, zzbc paramzzbc)
  {
    int i = 0;
    if (paramView == null) {
      return new zza(0, 0);
    }
    boolean bool = paramView.getGlobalVisibleRect(new Rect());
    if (((paramView instanceof TextView)) && (!(paramView instanceof EditText)))
    {
      paramView = ((TextView)paramView).getText();
      if (!TextUtils.isEmpty(paramView))
      {
        paramzzbc.zzd(paramView.toString(), bool);
        return new zza(1, 0);
      }
      return new zza(0, 0);
    }
    if (((paramView instanceof WebView)) && (!(paramView instanceof zzjp)))
    {
      paramzzbc.zzcC();
      if (zza((WebView)paramView, paramzzbc, bool)) {
        return new zza(0, 1);
      }
      return new zza(0, 0);
    }
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int j = 0;
      int k = 0;
      while (i < paramView.getChildCount())
      {
        zza localzza = zza(paramView.getChildAt(i), paramzzbc);
        k += localzza.zztm;
        j += localzza.zztn;
        i += 1;
      }
      return new zza(k, j);
    }
    return new zza(0, 0);
  }
  
  void zza(Activity paramActivity)
  {
    if (paramActivity == null) {}
    Object localObject1;
    do
    {
      return;
      Object localObject2 = null;
      localObject1 = localObject2;
      if (paramActivity.getWindow() != null)
      {
        localObject1 = localObject2;
        if (paramActivity.getWindow().getDecorView() != null) {
          localObject1 = paramActivity.getWindow().getDecorView().findViewById(16908290);
        }
      }
    } while (localObject1 == null);
    zze((View)localObject1);
  }
  
  void zza(zzbc paramzzbc, WebView paramWebView, String paramString, boolean paramBoolean)
  {
    paramzzbc.zzcB();
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = new JSONObject(paramString).optString("text");
        if (TextUtils.isEmpty(paramWebView.getTitle())) {
          break label86;
        }
        paramzzbc.zzc(paramWebView.getTitle() + "\n" + paramString, paramBoolean);
      }
      while (paramzzbc.zzcx())
      {
        this.zzta.zzb(paramzzbc);
        return;
        label86:
        paramzzbc.zzc(paramString, paramBoolean);
      }
      return;
    }
    catch (JSONException paramzzbc)
    {
      zzin.zzaI("Json string may be malformed.");
      return;
    }
    catch (Throwable paramzzbc)
    {
      zzin.zza("Failed to get webview content.", paramzzbc);
      this.zztb.zza(paramzzbc, true);
    }
  }
  
  boolean zza(ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo)
  {
    return paramRunningAppProcessInfo.importance == 100;
  }
  
  @TargetApi(19)
  boolean zza(final WebView paramWebView, final zzbc paramzzbc, final boolean paramBoolean)
  {
    if (!zzne.zzsk()) {
      return false;
    }
    paramzzbc.zzcC();
    paramWebView.post(new Runnable()
    {
      ValueCallback<String> zzth = new ValueCallback()
      {
        public void zzt(String paramAnonymous2String)
        {
          zzbf.this.zza(zzbf.2.this.zzti, zzbf.2.this.zztj, paramAnonymous2String, zzbf.2.this.zztk);
        }
      };
      
      public void run()
      {
        if (paramWebView.getSettings().getJavaScriptEnabled()) {}
        try
        {
          paramWebView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzth);
          return;
        }
        catch (Throwable localThrowable)
        {
          this.zzth.onReceiveValue("");
        }
      }
    });
    return true;
  }
  
  public void zzcG()
  {
    synchronized (this.zzpV)
    {
      if (this.mStarted)
      {
        zzin.zzaI("Content hash thread already started, quiting...");
        return;
      }
      this.mStarted = true;
      start();
      return;
    }
  }
  
  boolean zzcH()
  {
    try
    {
      Context localContext = this.zzsZ.getContext();
      if (localContext == null) {
        return false;
      }
      Object localObject = (ActivityManager)localContext.getSystemService("activity");
      KeyguardManager localKeyguardManager = (KeyguardManager)localContext.getSystemService("keyguard");
      if ((localObject != null) && (localKeyguardManager != null))
      {
        localObject = ((ActivityManager)localObject).getRunningAppProcesses();
        if (localObject == null) {
          return false;
        }
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
          if (Process.myPid() == localRunningAppProcessInfo.pid) {
            if ((zza(localRunningAppProcessInfo)) && (!localKeyguardManager.inKeyguardRestrictedInputMode()))
            {
              boolean bool = zzs(localContext);
              if (bool) {
                return true;
              }
            }
          }
        }
        return false;
      }
    }
    catch (Throwable localThrowable)
    {
      return false;
    }
    return false;
  }
  
  public zzbc zzcI()
  {
    return this.zzta.zzcF();
  }
  
  public void zzcJ()
  {
    synchronized (this.zzpV)
    {
      this.zzsY = true;
      zzin.zzaI("ContentFetchThread: paused, mPause = " + this.zzsY);
      return;
    }
  }
  
  public boolean zzcK()
  {
    return this.zzsY;
  }
  
  boolean zze(final View paramView)
  {
    if (paramView == null) {
      return false;
    }
    paramView.post(new Runnable()
    {
      public void run()
      {
        zzbf.this.zzf(paramView);
      }
    });
    return true;
  }
  
  void zzf(View paramView)
  {
    try
    {
      zzbc localzzbc = new zzbc(this.zzsK, this.zztd, this.zzsM, this.zzte);
      paramView = zza(paramView, localzzbc);
      localzzbc.zzcD();
      if ((paramView.zztm == 0) && (paramView.zztn == 0)) {
        return;
      }
      if (((paramView.zztn != 0) || (localzzbc.zzcE() != 0)) && ((paramView.zztn != 0) || (!this.zzta.zza(localzzbc))))
      {
        this.zzta.zzc(localzzbc);
        return;
      }
    }
    catch (Exception paramView)
    {
      zzin.zzb("Exception in fetchContentOnUIThread", paramView);
      this.zztb.zza(paramView, true);
    }
  }
  
  boolean zzs(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (paramContext == null) {
      return false;
    }
    return paramContext.isScreenOn();
  }
  
  @zzhb
  class zza
  {
    final int zztm;
    final int zztn;
    
    zza(int paramInt1, int paramInt2)
    {
      this.zztm = paramInt1;
      this.zztn = paramInt2;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzbf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */