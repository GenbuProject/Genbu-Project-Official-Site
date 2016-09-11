package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.util.SimpleArrayMap;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.overlay.zzm;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.purchase.zzc;
import com.google.android.gms.ads.internal.purchase.zzf;
import com.google.android.gms.ads.internal.purchase.zzi;
import com.google.android.gms.ads.internal.purchase.zzj;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza;
import com.google.android.gms.ads.internal.request.CapabilityParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzax;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzdh;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzeo;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzeq;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzey;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzik;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzis;
import com.google.android.gms.internal.zzjp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@zzhb
public abstract class zzb
  extends zza
  implements com.google.android.gms.ads.internal.overlay.zzg, zzj, zzdh, zzep
{
  private final Messenger mMessenger;
  protected final zzex zzpn;
  protected transient boolean zzpo;
  
  public zzb(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel, zzd paramzzd)
  {
    this(new zzs(paramContext, paramAdSizeParcel, paramString, paramVersionInfoParcel), paramzzex, null, paramzzd);
  }
  
  zzb(zzs paramzzs, zzex paramzzex, zzq paramzzq, zzd paramzzd)
  {
    super(paramzzs, paramzzq, paramzzd);
    this.zzpn = paramzzex;
    this.mMessenger = new Messenger(new zzga(this.zzpj.context));
    this.zzpo = false;
  }
  
  private AdRequestInfoParcel.zza zza(AdRequestParcel paramAdRequestParcel, Bundle paramBundle)
  {
    ApplicationInfo localApplicationInfo = this.zzpj.context.getApplicationInfo();
    DisplayMetrics localDisplayMetrics;
    String str1;
    Object localObject;
    String str2;
    long l1;
    Bundle localBundle;
    ArrayList localArrayList;
    PackageInfo localPackageInfo2;
    try
    {
      PackageInfo localPackageInfo1 = this.zzpj.context.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 0);
      localDisplayMetrics = this.zzpj.context.getResources().getDisplayMetrics();
      str1 = null;
      localObject = str1;
      if (this.zzpj.zzrm != null)
      {
        localObject = str1;
        if (this.zzpj.zzrm.getParent() != null)
        {
          localObject = new int[2];
          this.zzpj.zzrm.getLocationOnScreen((int[])localObject);
          int k = localObject[0];
          int m = localObject[1];
          int n = this.zzpj.zzrm.getWidth();
          int i1 = this.zzpj.zzrm.getHeight();
          int j = 0;
          i = j;
          if (this.zzpj.zzrm.isShown())
          {
            i = j;
            if (k + n > 0)
            {
              i = j;
              if (m + i1 > 0)
              {
                i = j;
                if (k <= localDisplayMetrics.widthPixels)
                {
                  i = j;
                  if (m <= localDisplayMetrics.heightPixels) {
                    i = 1;
                  }
                }
              }
            }
          }
          localObject = new Bundle(5);
          ((Bundle)localObject).putInt("x", k);
          ((Bundle)localObject).putInt("y", m);
          ((Bundle)localObject).putInt("width", n);
          ((Bundle)localObject).putInt("height", i1);
          ((Bundle)localObject).putInt("visible", i);
        }
      }
      str1 = zzr.zzbF().zzgZ();
      this.zzpj.zzrs = new zzig(str1, this.zzpj.zzrj);
      this.zzpj.zzrs.zzk(paramAdRequestParcel);
      str2 = zzr.zzbC().zza(this.zzpj.context, this.zzpj.zzrm, this.zzpj.zzrp);
      l2 = 0L;
      l1 = l2;
      if (this.zzpj.zzrw == null) {}
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      try
      {
        l1 = this.zzpj.zzrw.getValue();
        String str3 = UUID.randomUUID().toString();
        localBundle = zzr.zzbF().zza(this.zzpj.context, this, str1);
        localArrayList = new ArrayList();
        int i = 0;
        while (i < this.zzpj.zzrC.size())
        {
          localArrayList.add(this.zzpj.zzrC.keyAt(i));
          i += 1;
          continue;
          localNameNotFoundException = localNameNotFoundException;
          localPackageInfo2 = null;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          long l2;
          zzin.zzaK("Cannot get correlation id, default to 0.");
          l1 = l2;
        }
      }
    }
    boolean bool1;
    if (this.zzpj.zzrx != null)
    {
      bool1 = true;
      if ((this.zzpj.zzry == null) || (!zzr.zzbF().zzhj())) {
        break label670;
      }
    }
    label670:
    for (boolean bool2 = true;; bool2 = false)
    {
      boolean bool3 = this.zzpm.zzpy.zzfM();
      return new AdRequestInfoParcel.zza((Bundle)localObject, paramAdRequestParcel, this.zzpj.zzrp, this.zzpj.zzrj, localApplicationInfo, localPackageInfo2, str1, zzr.zzbF().getSessionId(), this.zzpj.zzrl, localBundle, this.zzpj.zzrH, localArrayList, paramBundle, zzr.zzbF().zzhd(), this.mMessenger, localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels, localDisplayMetrics.density, str2, l1, localRemoteException, zzbt.zzdr(), this.zzpj.zzri, this.zzpj.zzrD, new CapabilityParcel(bool1, bool2, bool3), this.zzpj.zzca(), zzr.zzbC().zzbt(), zzr.zzbC().zzR(this.zzpj.context), zzr.zzbC().zzl(this.zzpj.zzrm));
      bool1 = false;
      break;
    }
  }
  
  public String getMediationAdapterClassName()
  {
    if (this.zzpj.zzrq == null) {
      return null;
    }
    return this.zzpj.zzrq.zzCr;
  }
  
  public void onAdClicked()
  {
    if (this.zzpj.zzrq == null)
    {
      zzin.zzaK("Ad state was null when trying to ping click URLs.");
      return;
    }
    if ((this.zzpj.zzrq.zzKV != null) && (this.zzpj.zzrq.zzKV.zzBQ != null)) {
      zzr.zzbP().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq, this.zzpj.zzrj, false, this.zzpj.zzrq.zzKV.zzBQ);
    }
    if ((this.zzpj.zzrq.zzCp != null) && (this.zzpj.zzrq.zzCp.zzBE != null)) {
      zzr.zzbP().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq, this.zzpj.zzrj, false, this.zzpj.zzrq.zzCp.zzBE);
    }
    super.onAdClicked();
  }
  
  public void onPause()
  {
    this.zzpl.zzk(this.zzpj.zzrq);
  }
  
  public void onResume()
  {
    this.zzpl.zzl(this.zzpj.zzrq);
  }
  
  public void pause()
  {
    com.google.android.gms.common.internal.zzx.zzcD("pause must be called on the main UI thread.");
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzED != null) && (this.zzpj.zzbW())) {
      zzr.zzbE().zzi(this.zzpj.zzrq.zzED);
    }
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzCq != null)) {}
    try
    {
      this.zzpj.zzrq.zzCq.pause();
      this.zzpl.zzk(this.zzpj.zzrq);
      this.zzpi.pause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzin.zzaK("Could not pause mediation adapter.");
      }
    }
  }
  
  public void recordImpression()
  {
    zza(this.zzpj.zzrq, false);
  }
  
  public void resume()
  {
    com.google.android.gms.common.internal.zzx.zzcD("resume must be called on the main UI thread.");
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzED != null) && (this.zzpj.zzbW())) {
      zzr.zzbE().zzj(this.zzpj.zzrq.zzED);
    }
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzCq != null)) {}
    try
    {
      this.zzpj.zzrq.zzCq.resume();
      this.zzpi.resume();
      this.zzpl.zzl(this.zzpj.zzrq);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzin.zzaK("Could not resume mediation adapter.");
      }
    }
  }
  
  public void showInterstitial()
  {
    throw new IllegalStateException("showInterstitial is not supported for current ad type");
  }
  
  public void zza(zzgd paramzzgd)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setInAppPurchaseListener must be called on the main UI thread.");
    this.zzpj.zzrx = paramzzgd;
  }
  
  public void zza(zzgh paramzzgh, String paramString)
  {
    com.google.android.gms.common.internal.zzx.zzcD("setPlayStorePurchaseParams must be called on the main UI thread.");
    this.zzpj.zzrI = new zzk(paramString);
    this.zzpj.zzry = paramzzgh;
    if ((!zzr.zzbF().zzhc()) && (paramzzgh != null)) {
      new zzc(this.zzpj.context, this.zzpj.zzry, this.zzpj.zzrI).zzhn();
    }
  }
  
  protected void zza(zzif paramzzif, boolean paramBoolean)
  {
    if (paramzzif == null) {
      zzin.zzaK("Ad state was null when trying to ping impression URLs.");
    }
    do
    {
      return;
      super.zzc(paramzzif);
      if ((paramzzif.zzKV != null) && (paramzzif.zzKV.zzBR != null)) {
        zzr.zzbP().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, paramzzif, this.zzpj.zzrj, paramBoolean, paramzzif.zzKV.zzBR);
      }
    } while ((paramzzif.zzCp == null) || (paramzzif.zzCp.zzBF == null));
    zzr.zzbP().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, paramzzif, this.zzpj.zzrj, paramBoolean, paramzzif.zzCp.zzBF);
  }
  
  public void zza(String paramString, ArrayList<String> paramArrayList)
  {
    paramArrayList = new com.google.android.gms.ads.internal.purchase.zzd(paramString, paramArrayList, this.zzpj.context, this.zzpj.zzrl.afmaVersion);
    if (this.zzpj.zzrx == null)
    {
      zzin.zzaK("InAppPurchaseListener is not set. Try to launch default purchase flow.");
      if (!zzn.zzcS().zzU(this.zzpj.context))
      {
        zzin.zzaK("Google Play Service unavailable, cannot launch default purchase flow.");
        return;
      }
      if (this.zzpj.zzry == null)
      {
        zzin.zzaK("PlayStorePurchaseListener is not set.");
        return;
      }
      if (this.zzpj.zzrI == null)
      {
        zzin.zzaK("PlayStorePurchaseVerifier is not initialized.");
        return;
      }
      if (this.zzpj.zzrM)
      {
        zzin.zzaK("An in-app purchase request is already in progress, abort");
        return;
      }
      this.zzpj.zzrM = true;
      try
      {
        if (!this.zzpj.zzry.isValidPurchase(paramString))
        {
          this.zzpj.zzrM = false;
          return;
        }
      }
      catch (RemoteException paramString)
      {
        zzin.zzaK("Could not start In-App purchase.");
        this.zzpj.zzrM = false;
        return;
      }
      zzr.zzbM().zza(this.zzpj.context, this.zzpj.zzrl.zzNb, new GInAppPurchaseManagerInfoParcel(this.zzpj.context, this.zzpj.zzrI, paramArrayList, this));
      return;
    }
    try
    {
      this.zzpj.zzrx.zza(paramArrayList);
      return;
    }
    catch (RemoteException paramString)
    {
      zzin.zzaK("Could not start In-App purchase.");
    }
  }
  
  public void zza(String paramString, boolean paramBoolean, int paramInt, final Intent paramIntent, zzf paramzzf)
  {
    try
    {
      if (this.zzpj.zzry != null) {
        this.zzpj.zzry.zza(new com.google.android.gms.ads.internal.purchase.zzg(this.zzpj.context, paramString, paramBoolean, paramInt, paramIntent, paramzzf));
      }
      zzir.zzMc.postDelayed(new Runnable()
      {
        public void run()
        {
          int i = zzr.zzbM().zzd(paramIntent);
          zzr.zzbM();
          if ((i == 0) && (zzb.this.zzpj.zzrq != null) && (zzb.this.zzpj.zzrq.zzED != null) && (zzb.this.zzpj.zzrq.zzED.zzhS() != null)) {
            zzb.this.zzpj.zzrq.zzED.zzhS().close();
          }
          zzb.this.zzpj.zzrM = false;
        }
      }, 500L);
      return;
    }
    catch (RemoteException paramString)
    {
      for (;;)
      {
        zzin.zzaK("Fail to invoke PlayStorePurchaseListener.");
      }
    }
  }
  
  public boolean zza(AdRequestParcel paramAdRequestParcel, zzcb paramzzcb)
  {
    if (!zzaV()) {
      return false;
    }
    Bundle localBundle = zza(zzr.zzbF().zzG(this.zzpj.context));
    this.zzpi.cancel();
    this.zzpj.zzrL = 0;
    paramAdRequestParcel = zza(paramAdRequestParcel, localBundle);
    paramzzcb.zzc("seq_num", paramAdRequestParcel.zzHw);
    paramzzcb.zzc("request_id", paramAdRequestParcel.zzHI);
    paramzzcb.zzc("session_id", paramAdRequestParcel.zzHx);
    if (paramAdRequestParcel.zzHu != null) {
      paramzzcb.zzc("app_version", String.valueOf(paramAdRequestParcel.zzHu.versionCode));
    }
    this.zzpj.zzrn = zzr.zzby().zza(this.zzpj.context, paramAdRequestParcel, this.zzpj.zzrk, this);
    return true;
  }
  
  protected boolean zza(AdRequestParcel paramAdRequestParcel, zzif paramzzif, boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.zzpj.zzbW()))
    {
      if (paramzzif.zzBU <= 0L) {
        break label43;
      }
      this.zzpi.zza(paramAdRequestParcel, paramzzif.zzBU);
    }
    for (;;)
    {
      return this.zzpi.zzbw();
      label43:
      if ((paramzzif.zzKV != null) && (paramzzif.zzKV.zzBU > 0L)) {
        this.zzpi.zza(paramAdRequestParcel, paramzzif.zzKV.zzBU);
      } else if ((!paramzzif.zzHT) && (paramzzif.errorCode == 2)) {
        this.zzpi.zzg(paramAdRequestParcel);
      }
    }
  }
  
  boolean zza(zzif paramzzif)
  {
    boolean bool = false;
    Object localObject;
    if (this.zzpk != null)
    {
      localObject = this.zzpk;
      this.zzpk = null;
    }
    for (;;)
    {
      return zza((AdRequestParcel)localObject, paramzzif, bool);
      AdRequestParcel localAdRequestParcel = paramzzif.zzHt;
      localObject = localAdRequestParcel;
      if (localAdRequestParcel.extras != null)
      {
        bool = localAdRequestParcel.extras.getBoolean("_noRefresh", false);
        localObject = localAdRequestParcel;
      }
    }
  }
  
  protected boolean zza(zzif paramzzif1, zzif paramzzif2)
  {
    int i = 0;
    if ((paramzzif1 != null) && (paramzzif1.zzCs != null)) {
      paramzzif1.zzCs.zza(null);
    }
    if (paramzzif2.zzCs != null) {
      paramzzif2.zzCs.zza(this);
    }
    int j;
    if (paramzzif2.zzKV != null)
    {
      j = paramzzif2.zzKV.zzBZ;
      i = paramzzif2.zzKV.zzCa;
    }
    for (;;)
    {
      this.zzpj.zzrJ.zzg(j, i);
      return true;
      j = 0;
    }
  }
  
  protected boolean zzaV()
  {
    boolean bool = true;
    if ((!zzr.zzbC().zza(this.zzpj.context.getPackageManager(), this.zzpj.context.getPackageName(), "android.permission.INTERNET")) || (!zzr.zzbC().zzI(this.zzpj.context))) {
      bool = false;
    }
    return bool;
  }
  
  public void zzaW()
  {
    this.zzpl.zzi(this.zzpj.zzrq);
    this.zzpo = false;
    zzaQ();
    this.zzpj.zzrs.zzgU();
  }
  
  public void zzaX()
  {
    this.zzpo = true;
    zzaS();
  }
  
  public void zzaY()
  {
    onAdClicked();
  }
  
  public void zzaZ()
  {
    zzaW();
  }
  
  public void zzb(zzif paramzzif)
  {
    super.zzb(paramzzif);
    if ((paramzzif.errorCode == 3) && (paramzzif.zzKV != null) && (paramzzif.zzKV.zzBS != null))
    {
      zzin.zzaI("Pinging no fill URLs.");
      zzr.zzbP().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, paramzzif, this.zzpj.zzrj, false, paramzzif.zzKV.zzBS);
    }
  }
  
  public void zzba()
  {
    zzaO();
  }
  
  public void zzbb()
  {
    zzaX();
  }
  
  public void zzbc()
  {
    if (this.zzpj.zzrq != null) {
      zzin.zzaK("Mediation adapter " + this.zzpj.zzrq.zzCr + " refreshed, but mediation adapters should never refresh.");
    }
    zza(this.zzpj.zzrq, true);
    zzaT();
  }
  
  protected boolean zzc(AdRequestParcel paramAdRequestParcel)
  {
    return (super.zzc(paramAdRequestParcel)) && (!this.zzpo);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */