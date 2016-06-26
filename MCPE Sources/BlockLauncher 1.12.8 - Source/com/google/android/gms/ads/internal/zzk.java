package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import android.view.Window;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzax;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzdj;
import com.google.android.gms.internal.zzdn;
import com.google.android.gms.internal.zzdn.zza;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzey;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzis;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjr;

@zzhb
public class zzk
  extends zzc
  implements zzdj, zzdn.zza
{
  protected transient boolean zzqc = false;
  private boolean zzqd;
  private float zzqe;
  private String zzqf = "background" + hashCode() + "." + "png";
  
  public zzk(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel, zzd paramzzd)
  {
    super(paramContext, paramAdSizeParcel, paramString, paramzzex, paramVersionInfoParcel, paramzzd);
  }
  
  private void zzb(Bundle paramBundle)
  {
    zzr.zzbC().zzb(this.zzpj.context, this.zzpj.zzrl.afmaVersion, "gmob-apps", paramBundle, false);
  }
  
  public void showInterstitial()
  {
    zzx.zzcD("showInterstitial must be called on the main UI thread.");
    if (this.zzpj.zzrq == null)
    {
      zzin.zzaK("The interstitial has not loaded.");
      return;
    }
    if (((Boolean)zzbt.zzwN.get()).booleanValue()) {
      if (this.zzpj.context.getApplicationContext() == null) {
        break label211;
      }
    }
    label211:
    for (String str = this.zzpj.context.getApplicationContext().getPackageName();; localObject = this.zzpj.context.getPackageName())
    {
      Bundle localBundle;
      if (!this.zzqc)
      {
        zzin.zzaK("It is not recommended to show an interstitial before onAdLoaded completes.");
        localBundle = new Bundle();
        localBundle.putString("appid", str);
        localBundle.putString("action", "show_interstitial_before_load_finish");
        zzb(localBundle);
      }
      if (!zzr.zzbC().zzO(this.zzpj.context))
      {
        zzin.zzaK("It is not recommended to show an interstitial when app is not in foreground.");
        localBundle = new Bundle();
        localBundle.putString("appid", str);
        localBundle.putString("action", "show_interstitial_app_not_in_foreground");
        zzb(localBundle);
      }
      if (this.zzpj.zzbX()) {
        break;
      }
      if (!this.zzpj.zzrq.zzHT) {
        break label225;
      }
      try
      {
        this.zzpj.zzrq.zzCq.showInterstitial();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        zzin.zzd("Could not show interstitial.", localRemoteException);
        zzbp();
        return;
      }
    }
    label225:
    if (this.zzpj.zzrq.zzED == null)
    {
      zzin.zzaK("The interstitial failed to load.");
      return;
    }
    if (this.zzpj.zzrq.zzED.zzhY())
    {
      zzin.zzaK("The interstitial is already showing.");
      return;
    }
    this.zzpj.zzrq.zzED.zzD(true);
    if (this.zzpj.zzrq.zzKT != null) {
      this.zzpl.zza(this.zzpj.zzrp, this.zzpj.zzrq);
    }
    if (this.zzpj.zzql) {}
    for (Object localObject = zzr.zzbC().zzP(this.zzpj.context); (((Boolean)zzbt.zzxe.get()).booleanValue()) && (localObject != null); localObject = null)
    {
      new zzb((Bitmap)localObject, this.zzqf).zzhn();
      return;
    }
    localObject = new InterstitialAdParameterParcel(this.zzpj.zzql, zzbo(), null, false, 0.0F);
    int j = this.zzpj.zzrq.zzED.getRequestedOrientation();
    int i = j;
    if (j == -1) {
      i = this.zzpj.zzrq.orientation;
    }
    localObject = new AdOverlayInfoParcel(this, this, this, this.zzpj.zzrq.zzED, i, this.zzpj.zzrl, this.zzpj.zzrq.zzHY, (InterstitialAdParameterParcel)localObject);
    zzr.zzbA().zza(this.zzpj.context, (AdOverlayInfoParcel)localObject);
  }
  
  protected zzjp zza(zzif.zza paramzza, zze paramzze)
  {
    zzjp localzzjp = zzr.zzbD().zza(this.zzpj.context, this.zzpj.zzrp, false, false, this.zzpj.zzrk, this.zzpj.zzrl, this.zzpe, this.zzpm);
    localzzjp.zzhU().zzb(this, null, this, this, ((Boolean)zzbt.zzwv.get()).booleanValue(), this, this, paramzze, null);
    zza(localzzjp);
    localzzjp.zzaM(paramzza.zzLd.zzHI);
    zzdn.zza(localzzjp, this);
    return localzzjp;
  }
  
  public void zza(boolean paramBoolean, float paramFloat)
  {
    this.zzqd = paramBoolean;
    this.zzqe = paramFloat;
  }
  
  public boolean zza(AdRequestParcel paramAdRequestParcel, zzcb paramzzcb)
  {
    if (this.zzpj.zzrq != null)
    {
      zzin.zzaK("An interstitial is already loading. Aborting.");
      return false;
    }
    return super.zza(paramAdRequestParcel, paramzzcb);
  }
  
  protected boolean zza(AdRequestParcel paramAdRequestParcel, zzif paramzzif, boolean paramBoolean)
  {
    if ((this.zzpj.zzbW()) && (paramzzif.zzED != null)) {
      zzr.zzbE().zzi(paramzzif.zzED);
    }
    return this.zzpi.zzbw();
  }
  
  public boolean zza(zzif paramzzif1, zzif paramzzif2)
  {
    if (!super.zza(paramzzif1, paramzzif2)) {
      return false;
    }
    if ((!this.zzpj.zzbW()) && (this.zzpj.zzrK != null) && (paramzzif2.zzKT != null)) {
      this.zzpl.zza(this.zzpj.zzrp, paramzzif2, this.zzpj.zzrK);
    }
    return true;
  }
  
  protected void zzaQ()
  {
    zzbp();
    super.zzaQ();
  }
  
  protected void zzaT()
  {
    super.zzaT();
    this.zzqc = true;
  }
  
  public void zzaX()
  {
    recordImpression();
    super.zzaX();
  }
  
  public void zzb(RewardItemParcel paramRewardItemParcel)
  {
    RewardItemParcel localRewardItemParcel = paramRewardItemParcel;
    if (this.zzpj.zzrq != null)
    {
      if (this.zzpj.zzrq.zzIl != null) {
        zzr.zzbC().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq.zzIl);
      }
      localRewardItemParcel = paramRewardItemParcel;
      if (this.zzpj.zzrq.zzIj != null) {
        localRewardItemParcel = this.zzpj.zzrq.zzIj;
      }
    }
    zza(localRewardItemParcel);
  }
  
  protected boolean zzbo()
  {
    if (!(this.zzpj.context instanceof Activity)) {}
    Window localWindow;
    do
    {
      return false;
      localWindow = ((Activity)this.zzpj.context).getWindow();
    } while ((localWindow == null) || (localWindow.getDecorView() == null));
    Rect localRect1 = new Rect();
    Rect localRect2 = new Rect();
    localWindow.getDecorView().getGlobalVisibleRect(localRect1, null);
    localWindow.getDecorView().getWindowVisibleDisplayFrame(localRect2);
    if ((localRect1.bottom != 0) && (localRect2.bottom != 0) && (localRect1.top == localRect2.top)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void zzbp()
  {
    new zza(this.zzqf).zzhn();
    if (this.zzpj.zzbW())
    {
      this.zzpj.zzbT();
      this.zzpj.zzrq = null;
      this.zzpj.zzql = false;
      this.zzqc = false;
    }
  }
  
  public void zzbq()
  {
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzKX != null)) {
      zzr.zzbC().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq.zzKX);
    }
    zzaU();
  }
  
  public void zzd(boolean paramBoolean)
  {
    this.zzpj.zzql = paramBoolean;
  }
  
  @zzhb
  private class zza
    extends zzim
  {
    private final String zzqg;
    
    public zza(String paramString)
    {
      this.zzqg = paramString;
    }
    
    public void onStop() {}
    
    public void zzbr()
    {
      zzr.zzbC().zzg(zzk.this.zzpj.context, this.zzqg);
    }
  }
  
  @zzhb
  private class zzb
    extends zzim
  {
    private final String zzqg;
    private final Bitmap zzqi;
    
    public zzb(Bitmap paramBitmap, String paramString)
    {
      this.zzqi = paramBitmap;
      this.zzqg = paramString;
    }
    
    public void onStop() {}
    
    public void zzbr()
    {
      boolean bool1;
      boolean bool2;
      boolean bool3;
      if (zzk.this.zzpj.zzql)
      {
        bool1 = zzr.zzbC().zza(zzk.this.zzpj.context, this.zzqi, this.zzqg);
        bool2 = zzk.this.zzpj.zzql;
        bool3 = zzk.this.zzbo();
        if (!bool1) {
          break label221;
        }
      }
      label221:
      for (final Object localObject = this.zzqg;; localObject = null)
      {
        localObject = new InterstitialAdParameterParcel(bool2, bool3, (String)localObject, zzk.zza(zzk.this), zzk.zzb(zzk.this));
        int j = zzk.this.zzpj.zzrq.zzED.getRequestedOrientation();
        int i = j;
        if (j == -1) {
          i = zzk.this.zzpj.zzrq.orientation;
        }
        localObject = new AdOverlayInfoParcel(zzk.this, zzk.this, zzk.this, zzk.this.zzpj.zzrq.zzED, i, zzk.this.zzpj.zzrl, zzk.this.zzpj.zzrq.zzHY, (InterstitialAdParameterParcel)localObject);
        zzir.zzMc.post(new Runnable()
        {
          public void run()
          {
            zzr.zzbA().zza(zzk.this.zzpj.context, localObject);
          }
        });
        return;
        bool1 = false;
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */