package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzan;
import com.google.android.gms.internal.zzfb;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjp;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zzhb
public class zzg
  extends zzi
{
  private Object zzpV = new Object();
  private zzfb zzyl;
  private zzfc zzym;
  private final zzp zzyn;
  private zzh zzyo;
  private boolean zzyp = false;
  
  private zzg(Context paramContext, zzp paramzzp, zzan paramzzan)
  {
    super(paramContext, paramzzp, null, paramzzan, null, null, null);
    this.zzyn = paramzzp;
  }
  
  public zzg(Context paramContext, zzp paramzzp, zzan paramzzan, zzfb paramzzfb)
  {
    this(paramContext, paramzzp, paramzzan);
    this.zzyl = paramzzfb;
  }
  
  public zzg(Context paramContext, zzp paramzzp, zzan paramzzan, zzfc paramzzfc)
  {
    this(paramContext, paramzzp, paramzzan);
    this.zzym = paramzzfc;
  }
  
  public void recordImpression()
  {
    zzx.zzcD("recordImpression must be called on the main UI thread.");
    for (;;)
    {
      synchronized (this.zzpV)
      {
        zzn(true);
        if (this.zzyo != null)
        {
          this.zzyo.recordImpression();
          this.zzyn.recordImpression();
          return;
        }
        try
        {
          if ((this.zzyl != null) && (!this.zzyl.getOverrideImpressionRecording())) {
            this.zzyl.recordImpression();
          }
        }
        catch (RemoteException localRemoteException)
        {
          zzin.zzd("Failed to call recordImpression", localRemoteException);
        }
      }
      if ((this.zzym != null) && (!this.zzym.getOverrideImpressionRecording())) {
        this.zzym.recordImpression();
      }
    }
  }
  
  public zzb zza(View.OnClickListener paramOnClickListener)
  {
    return null;
  }
  
  public void zza(View paramView, Map<String, WeakReference<View>> paramMap, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3)
  {
    zzx.zzcD("performClick must be called on the main UI thread.");
    synchronized (this.zzpV)
    {
      if (this.zzyo != null)
      {
        this.zzyo.zza(paramView, paramMap, paramJSONObject1, paramJSONObject2, paramJSONObject3);
        this.zzyn.onAdClicked();
      }
      for (;;)
      {
        return;
        try
        {
          if ((this.zzyl != null) && (!this.zzyl.getOverrideClickHandling()))
          {
            this.zzyl.zzc(zze.zzC(paramView));
            this.zzyn.onAdClicked();
          }
          if ((this.zzym == null) || (this.zzym.getOverrideClickHandling())) {
            continue;
          }
          this.zzym.zzc(zze.zzC(paramView));
          this.zzyn.onAdClicked();
        }
        catch (RemoteException paramView)
        {
          zzin.zzd("Failed to call performClick", paramView);
        }
      }
    }
  }
  
  public void zzc(zzh paramzzh)
  {
    synchronized (this.zzpV)
    {
      this.zzyo = paramzzh;
      return;
    }
  }
  
  public boolean zzdP()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzyp;
      return bool;
    }
  }
  
  public zzh zzdQ()
  {
    synchronized (this.zzpV)
    {
      zzh localzzh = this.zzyo;
      return localzzh;
    }
  }
  
  public zzjp zzdR()
  {
    return null;
  }
  
  public void zzg(View paramView)
  {
    synchronized (this.zzpV)
    {
      this.zzyp = true;
      try
      {
        if (this.zzyl != null) {
          this.zzyl.zzd(zze.zzC(paramView));
        }
        for (;;)
        {
          this.zzyp = false;
          return;
          if (this.zzym != null) {
            this.zzym.zzd(zze.zzC(paramView));
          }
        }
      }
      catch (RemoteException paramView)
      {
        for (;;)
        {
          zzin.zzd("Failed to call prepareAd", paramView);
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\formats\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */