package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.ThinAdSizeParcel;
import com.google.android.gms.ads.internal.client.zzf;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zza.zza;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzax;
import com.google.android.gms.internal.zzbc;
import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzbv;
import com.google.android.gms.internal.zzbz;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzcf;
import com.google.android.gms.internal.zzdb;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzgr.zza;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzhr;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzij;
import com.google.android.gms.internal.zzik;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzis;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import java.util.HashSet;

@zzhb
public abstract class zza
  extends zzu.zza
  implements com.google.android.gms.ads.internal.client.zza, com.google.android.gms.ads.internal.overlay.zzp, zza.zza, zzdb, zzgr.zza, zzij
{
  protected zzcb zzpe;
  protected zzbz zzpf;
  protected zzbz zzpg;
  protected boolean zzph = false;
  protected final zzq zzpi;
  protected final zzs zzpj;
  protected transient AdRequestParcel zzpk;
  protected final zzax zzpl;
  protected final zzd zzpm;
  
  zza(zzs paramzzs, zzq paramzzq, zzd paramzzd)
  {
    this.zzpj = paramzzs;
    if (paramzzq != null) {}
    for (;;)
    {
      this.zzpi = paramzzq;
      this.zzpm = paramzzd;
      zzr.zzbC().zzJ(this.zzpj.context);
      zzr.zzbF().zzb(this.zzpj.context, this.zzpj.zzrl);
      this.zzpl = zzr.zzbF().zzhh();
      return;
      paramzzq = new zzq(this);
    }
  }
  
  private AdRequestParcel zza(AdRequestParcel paramAdRequestParcel)
  {
    AdRequestParcel localAdRequestParcel = paramAdRequestParcel;
    if (com.google.android.gms.common.zze.zzap(this.zzpj.context))
    {
      localAdRequestParcel = paramAdRequestParcel;
      if (paramAdRequestParcel.zztK != null) {
        localAdRequestParcel = new zzf(paramAdRequestParcel).zza(null).zzcN();
      }
    }
    return localAdRequestParcel;
  }
  
  public void destroy()
  {
    com.google.android.gms.common.internal.zzx.zzcD("destroy must be called on the main UI thread.");
    this.zzpi.cancel();
    this.zzpl.zzj(this.zzpj.zzrq);
    this.zzpj.destroy();
  }
  
  public boolean isLoading()
  {
    return this.zzph;
  }
  
  public boolean isReady()
  {
    com.google.android.gms.common.internal.zzx.zzcD("isLoaded must be called on the main UI thread.");
    return (this.zzpj.zzrn == null) && (this.zzpj.zzro == null) && (this.zzpj.zzrq != null);
  }
  
  public void onAdClicked()
  {
    if (this.zzpj.zzrq == null) {
      zzin.zzaK("Ad state was null when trying to ping click URLs.");
    }
    do
    {
      return;
      zzin.zzaI("Pinging click URLs.");
      this.zzpj.zzrs.zzgT();
      if (this.zzpj.zzrq.zzBQ != null) {
        zzr.zzbC().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq.zzBQ);
      }
    } while (this.zzpj.zzrt == null);
    try
    {
      this.zzpj.zzrt.onAdClicked();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not notify onAdClicked event.", localRemoteException);
    }
  }
  
  public void onAppEvent(String paramString1, String paramString2)
  {
    if (this.zzpj.zzrv != null) {}
    try
    {
      this.zzpj.zzrv.onAppEvent(paramString1, paramString2);
      return;
    }
    catch (RemoteException paramString1)
    {
      zzin.zzd("Could not call the AppEventListener.", paramString1);
    }
  }
  
  public void pause()
  {
    com.google.android.gms.common.internal.zzx.zzcD("pause must be called on the main UI thread.");
  }
  
  public void resume()
  {
    com.google.android.gms.common.internal.zzx.zzcD("resume must be called on the main UI thread.");
  }
  
  public void setManualImpressionsEnabled(boolean paramBoolean)
  {
    throw new UnsupportedOperationException("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
  }
  
  public void setUserId(String paramString)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setUserId must be called on the main UI thread.");
    this.zzpj.setUserId(paramString);
  }
  
  public void stopLoading()
  {
    com.google.android.gms.common.internal.zzx.zzcD("stopLoading must be called on the main UI thread.");
    this.zzph = false;
    this.zzpj.zzf(true);
  }
  
  Bundle zza(zzbf paramzzbf)
  {
    Object localObject2 = null;
    if (paramzzbf == null) {}
    label148:
    for (;;)
    {
      return (Bundle)localObject2;
      if (paramzzbf.zzcK()) {
        paramzzbf.wakeup();
      }
      paramzzbf = paramzzbf.zzcI();
      Object localObject3;
      String str;
      Object localObject1;
      if (paramzzbf != null)
      {
        localObject3 = paramzzbf.zzcy();
        str = paramzzbf.zzcz();
        zzin.zzaI("In AdManager: loadAd, " + paramzzbf.toString());
        paramzzbf = str;
        localObject1 = localObject3;
        if (localObject3 != null)
        {
          zzr.zzbF().zzaA((String)localObject3);
          localObject1 = localObject3;
        }
      }
      for (paramzzbf = str;; paramzzbf = null)
      {
        if (localObject1 == null) {
          break label148;
        }
        localObject3 = new Bundle(1);
        ((Bundle)localObject3).putString("fingerprint", (String)localObject1);
        localObject2 = localObject3;
        if (((String)localObject1).equals(paramzzbf)) {
          break;
        }
        ((Bundle)localObject3).putString("v_fp", paramzzbf);
        return (Bundle)localObject3;
        localObject1 = zzr.zzbF().zzhf();
      }
    }
  }
  
  public void zza(AdSizeParcel paramAdSizeParcel)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setAdSize must be called on the main UI thread.");
    this.zzpj.zzrp = paramAdSizeParcel;
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzED != null) && (this.zzpj.zzrL == 0)) {
      this.zzpj.zzrq.zzED.zza(paramAdSizeParcel);
    }
    if (this.zzpj.zzrm == null) {
      return;
    }
    if (this.zzpj.zzrm.getChildCount() > 1) {
      this.zzpj.zzrm.removeView(this.zzpj.zzrm.getNextView());
    }
    this.zzpj.zzrm.setMinimumWidth(paramAdSizeParcel.widthPixels);
    this.zzpj.zzrm.setMinimumHeight(paramAdSizeParcel.heightPixels);
    this.zzpj.zzrm.requestLayout();
  }
  
  public void zza(com.google.android.gms.ads.internal.client.zzp paramzzp)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setAdListener must be called on the main UI thread.");
    this.zzpj.zzrt = paramzzp;
  }
  
  public void zza(com.google.android.gms.ads.internal.client.zzq paramzzq)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setAdListener must be called on the main UI thread.");
    this.zzpj.zzru = paramzzq;
  }
  
  public void zza(zzw paramzzw)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setAppEventListener must be called on the main UI thread.");
    this.zzpj.zzrv = paramzzw;
  }
  
  public void zza(com.google.android.gms.ads.internal.client.zzx paramzzx)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setCorrelationIdProvider must be called on the main UI thread");
    this.zzpj.zzrw = paramzzx;
  }
  
  public void zza(com.google.android.gms.ads.internal.reward.client.zzd paramzzd)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setRewardedVideoAdListener can only be called from the UI thread.");
    this.zzpj.zzrF = paramzzd;
  }
  
  protected void zza(RewardItemParcel paramRewardItemParcel)
  {
    if (this.zzpj.zzrF == null) {
      return;
    }
    String str = "";
    int i = 0;
    if (paramRewardItemParcel != null) {}
    try
    {
      str = paramRewardItemParcel.type;
      i = paramRewardItemParcel.zzKS;
      this.zzpj.zzrF.zza(new zzhr(str, i));
      return;
    }
    catch (RemoteException paramRewardItemParcel)
    {
      zzin.zzd("Could not call RewardedVideoAdListener.onRewarded().", paramRewardItemParcel);
    }
  }
  
  public void zza(zzcf paramzzcf)
  {
    throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
  }
  
  public void zza(zzgd paramzzgd)
  {
    throw new IllegalStateException("setInAppPurchaseListener is not supported for current ad type");
  }
  
  public void zza(zzgh paramzzgh, String paramString)
  {
    throw new IllegalStateException("setPlayStorePurchaseParams is not supported for current ad type");
  }
  
  public void zza(zzif.zza paramzza)
  {
    if ((paramzza.zzLe.zzHX != -1L) && (!TextUtils.isEmpty(paramzza.zzLe.zzIh)))
    {
      long l = zzp(paramzza.zzLe.zzIh);
      if (l != -1L)
      {
        zzbz localzzbz = this.zzpe.zzb(l + paramzza.zzLe.zzHX);
        this.zzpe.zza(localzzbz, new String[] { "stc" });
      }
    }
    this.zzpe.zzN(paramzza.zzLe.zzIh);
    this.zzpe.zza(this.zzpf, new String[] { "arf" });
    this.zzpg = this.zzpe.zzdB();
    this.zzpe.zzc("gqi", paramzza.zzLe.zzIi);
    this.zzpj.zzrn = null;
    this.zzpj.zzrr = paramzza;
    zza(paramzza, this.zzpe);
  }
  
  protected abstract void zza(zzif.zza paramzza, zzcb paramzzcb);
  
  public void zza(HashSet<zzig> paramHashSet)
  {
    this.zzpj.zza(paramHashSet);
  }
  
  protected abstract boolean zza(AdRequestParcel paramAdRequestParcel, zzcb paramzzcb);
  
  boolean zza(zzif paramzzif)
  {
    return false;
  }
  
  protected abstract boolean zza(zzif paramzzif1, zzif paramzzif2);
  
  void zzaL()
  {
    this.zzpe = new zzcb(((Boolean)zzbt.zzwg.get()).booleanValue(), "load_ad", this.zzpj.zzrp.zzuh);
    this.zzpf = new zzbz(-1L, null, null);
    this.zzpg = new zzbz(-1L, null, null);
  }
  
  public com.google.android.gms.dynamic.zzd zzaM()
  {
    com.google.android.gms.common.internal.zzx.zzcD("getAdFrame must be called on the main UI thread.");
    return com.google.android.gms.dynamic.zze.zzC(this.zzpj.zzrm);
  }
  
  public AdSizeParcel zzaN()
  {
    com.google.android.gms.common.internal.zzx.zzcD("getAdSize must be called on the main UI thread.");
    if (this.zzpj.zzrp == null) {
      return null;
    }
    return new ThinAdSizeParcel(this.zzpj.zzrp);
  }
  
  public void zzaO()
  {
    zzaR();
  }
  
  public void zzaP()
  {
    com.google.android.gms.common.internal.zzx.zzcD("recordManualImpression must be called on the main UI thread.");
    if (this.zzpj.zzrq == null) {
      zzin.zzaK("Ad state was null when trying to ping manual tracking URLs.");
    }
    do
    {
      return;
      zzin.zzaI("Pinging manual tracking URLs.");
    } while ((this.zzpj.zzrq.zzHV == null) || (this.zzpj.zzrq.zzLc));
    zzr.zzbC().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq.zzHV);
    this.zzpj.zzrq.zzLc = true;
  }
  
  protected void zzaQ()
  {
    zzin.zzaJ("Ad closing.");
    if (this.zzpj.zzru != null) {}
    try
    {
      this.zzpj.zzru.onAdClosed();
      if (this.zzpj.zzrF == null) {}
    }
    catch (RemoteException localRemoteException1)
    {
      for (;;)
      {
        try
        {
          this.zzpj.zzrF.onRewardedVideoAdClosed();
          return;
        }
        catch (RemoteException localRemoteException2)
        {
          zzin.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", localRemoteException2);
        }
        localRemoteException1 = localRemoteException1;
        zzin.zzd("Could not call AdListener.onAdClosed().", localRemoteException1);
      }
    }
  }
  
  protected void zzaR()
  {
    zzin.zzaJ("Ad leaving application.");
    if (this.zzpj.zzru != null) {}
    try
    {
      this.zzpj.zzru.onAdLeftApplication();
      if (this.zzpj.zzrF == null) {}
    }
    catch (RemoteException localRemoteException1)
    {
      for (;;)
      {
        try
        {
          this.zzpj.zzrF.onRewardedVideoAdLeftApplication();
          return;
        }
        catch (RemoteException localRemoteException2)
        {
          zzin.zzd("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", localRemoteException2);
        }
        localRemoteException1 = localRemoteException1;
        zzin.zzd("Could not call AdListener.onAdLeftApplication().", localRemoteException1);
      }
    }
  }
  
  protected void zzaS()
  {
    zzin.zzaJ("Ad opening.");
    if (this.zzpj.zzru != null) {}
    try
    {
      this.zzpj.zzru.onAdOpened();
      if (this.zzpj.zzrF == null) {}
    }
    catch (RemoteException localRemoteException1)
    {
      for (;;)
      {
        try
        {
          this.zzpj.zzrF.onRewardedVideoAdOpened();
          return;
        }
        catch (RemoteException localRemoteException2)
        {
          zzin.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", localRemoteException2);
        }
        localRemoteException1 = localRemoteException1;
        zzin.zzd("Could not call AdListener.onAdOpened().", localRemoteException1);
      }
    }
  }
  
  protected void zzaT()
  {
    zzin.zzaJ("Ad finished loading.");
    this.zzph = false;
    if (this.zzpj.zzru != null) {}
    try
    {
      this.zzpj.zzru.onAdLoaded();
      if (this.zzpj.zzrF == null) {}
    }
    catch (RemoteException localRemoteException1)
    {
      for (;;)
      {
        try
        {
          this.zzpj.zzrF.onRewardedVideoAdLoaded();
          return;
        }
        catch (RemoteException localRemoteException2)
        {
          zzin.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", localRemoteException2);
        }
        localRemoteException1 = localRemoteException1;
        zzin.zzd("Could not call AdListener.onAdLoaded().", localRemoteException1);
      }
    }
  }
  
  protected void zzaU()
  {
    if (this.zzpj.zzrF == null) {
      return;
    }
    try
    {
      this.zzpj.zzrF.onRewardedVideoStarted();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not call RewardedVideoAdListener.onVideoStarted().", localRemoteException);
    }
  }
  
  protected void zzb(View paramView)
  {
    this.zzpj.zzrm.addView(paramView, zzr.zzbE().zzhy());
  }
  
  public void zzb(zzif paramzzif)
  {
    this.zzpe.zza(this.zzpg, new String[] { "awr" });
    this.zzpj.zzro = null;
    if ((paramzzif.errorCode != -2) && (paramzzif.errorCode != 3)) {
      zzr.zzbF().zzb(this.zzpj.zzbS());
    }
    if (paramzzif.errorCode == -1) {
      this.zzph = false;
    }
    do
    {
      return;
      if (zza(paramzzif)) {
        zzin.zzaI("Ad refresh scheduled.");
      }
      if (paramzzif.errorCode != -2)
      {
        zzf(paramzzif.errorCode);
        return;
      }
      if (this.zzpj.zzrJ == null) {
        this.zzpj.zzrJ = new zzik(this.zzpj.zzrj);
      }
      this.zzpl.zzi(this.zzpj.zzrq);
    } while (!zza(this.zzpj.zzrq, paramzzif));
    this.zzpj.zzrq = paramzzif;
    this.zzpj.zzcb();
    zzcb localzzcb = this.zzpe;
    if (this.zzpj.zzrq.zzcv())
    {
      paramzzif = "1";
      label203:
      localzzcb.zzc("is_mraid", paramzzif);
      localzzcb = this.zzpe;
      if (!this.zzpj.zzrq.zzHT) {
        break label377;
      }
      paramzzif = "1";
      label233:
      localzzcb.zzc("is_mediation", paramzzif);
      if ((this.zzpj.zzrq.zzED != null) && (this.zzpj.zzrq.zzED.zzhU() != null))
      {
        localzzcb = this.zzpe;
        if (!this.zzpj.zzrq.zzED.zzhU().zzih()) {
          break label384;
        }
      }
    }
    label377:
    label384:
    for (paramzzif = "1";; paramzzif = "0")
    {
      localzzcb.zzc("is_video", paramzzif);
      this.zzpe.zza(this.zzpf, new String[] { "ttc" });
      if (zzr.zzbF().zzhb() != null) {
        zzr.zzbF().zzhb().zza(this.zzpe);
      }
      if (!this.zzpj.zzbW()) {
        break;
      }
      zzaT();
      return;
      paramzzif = "0";
      break label203;
      paramzzif = "0";
      break label233;
    }
  }
  
  public boolean zzb(AdRequestParcel paramAdRequestParcel)
  {
    com.google.android.gms.common.internal.zzx.zzcD("loadAd must be called on the main UI thread.");
    paramAdRequestParcel = zza(paramAdRequestParcel);
    if ((this.zzpj.zzrn != null) || (this.zzpj.zzro != null))
    {
      if (this.zzpk != null) {
        zzin.zzaK("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
      }
      for (;;)
      {
        this.zzpk = paramAdRequestParcel;
        return false;
        zzin.zzaK("Loading already in progress, saving this object for future refreshes.");
      }
    }
    zzin.zzaJ("Starting ad request.");
    zzaL();
    this.zzpf = this.zzpe.zzdB();
    if (!paramAdRequestParcel.zztF) {
      zzin.zzaJ("Use AdRequest.Builder.addTestDevice(\"" + zzn.zzcS().zzT(this.zzpj.context) + "\") to get test ads on this device.");
    }
    this.zzph = zza(paramAdRequestParcel, this.zzpe);
    return this.zzph;
  }
  
  protected void zzc(zzif paramzzif)
  {
    if (paramzzif == null) {
      zzin.zzaK("Ad state was null when trying to ping impression URLs.");
    }
    do
    {
      return;
      zzin.zzaI("Pinging Impression URLs.");
      this.zzpj.zzrs.zzgS();
    } while ((paramzzif.zzBR == null) || (paramzzif.zzLb));
    zzr.zzbC().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, paramzzif.zzBR);
    paramzzif.zzLb = true;
  }
  
  protected boolean zzc(AdRequestParcel paramAdRequestParcel)
  {
    paramAdRequestParcel = this.zzpj.zzrm.getParent();
    return ((paramAdRequestParcel instanceof View)) && (((View)paramAdRequestParcel).isShown()) && (zzr.zzbC().zzhq());
  }
  
  public void zzd(AdRequestParcel paramAdRequestParcel)
  {
    if (zzc(paramAdRequestParcel))
    {
      zzb(paramAdRequestParcel);
      return;
    }
    zzin.zzaJ("Ad is not visible. Not refreshing ad.");
    this.zzpi.zzg(paramAdRequestParcel);
  }
  
  protected void zzf(int paramInt)
  {
    zzin.zzaK("Failed to load ad: " + paramInt);
    this.zzph = false;
    if (this.zzpj.zzru != null) {}
    try
    {
      this.zzpj.zzru.onAdFailedToLoad(paramInt);
      if (this.zzpj.zzrF == null) {}
    }
    catch (RemoteException localRemoteException1)
    {
      for (;;)
      {
        try
        {
          this.zzpj.zzrF.onRewardedVideoAdFailedToLoad(paramInt);
          return;
        }
        catch (RemoteException localRemoteException2)
        {
          zzin.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", localRemoteException2);
        }
        localRemoteException1 = localRemoteException1;
        zzin.zzd("Could not call AdListener.onAdFailedToLoad().", localRemoteException1);
      }
    }
  }
  
  long zzp(@NonNull String paramString)
  {
    int k = paramString.indexOf("ufe");
    int j = paramString.indexOf(',', k);
    int i = j;
    if (j == -1) {
      i = paramString.length();
    }
    try
    {
      long l = Long.parseLong(paramString.substring(k + 4, i));
      return l;
    }
    catch (IndexOutOfBoundsException paramString)
    {
      zzin.zzaK("Invalid index for Url fetch time in CSI latency info.");
      return -1L;
    }
    catch (NumberFormatException paramString)
    {
      for (;;)
      {
        zzin.zzaK("Cannot find valid format of Url fetch time in CSI latency info.");
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */