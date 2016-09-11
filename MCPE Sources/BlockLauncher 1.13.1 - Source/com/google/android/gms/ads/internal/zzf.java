package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzau;
import com.google.android.gms.internal.zzax;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzjk;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjq.zzb;
import java.util.List;

@zzhb
public class zzf
  extends zzc
  implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener
{
  private boolean zzpE;
  
  public zzf(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel, zzd paramzzd)
  {
    super(paramContext, paramAdSizeParcel, paramString, paramzzex, paramVersionInfoParcel, paramzzd);
  }
  
  private AdSizeParcel zzb(zzif.zza paramzza)
  {
    if (paramzza.zzLe.zzul) {
      return this.zzpj.zzrp;
    }
    paramzza = paramzza.zzLe.zzHW;
    if (paramzza != null)
    {
      paramzza = paramzza.split("[xX]");
      paramzza[0] = paramzza[0].trim();
      paramzza[1] = paramzza[1].trim();
    }
    for (paramzza = new AdSize(Integer.parseInt(paramzza[0]), Integer.parseInt(paramzza[1]));; paramzza = this.zzpj.zzrp.zzcQ()) {
      return new AdSizeParcel(this.zzpj.context, paramzza);
    }
  }
  
  private boolean zzb(zzif paramzzif1, zzif paramzzif2)
  {
    View localView1;
    if (paramzzif2.zzHT)
    {
      localView1 = zzm.zzf(paramzzif2);
      if (localView1 == null)
      {
        zzin.zzaK("Could not get mediation view");
        return false;
      }
      View localView2 = this.zzpj.zzrm.getNextView();
      if (localView2 != null)
      {
        if ((localView2 instanceof zzjp)) {
          ((zzjp)localView2).destroy();
        }
        this.zzpj.zzrm.removeView(localView2);
      }
      if (zzm.zzg(paramzzif2)) {}
    }
    for (;;)
    {
      try
      {
        zzb(localView1);
        if (this.zzpj.zzrm.getChildCount() > 1) {
          this.zzpj.zzrm.showNext();
        }
        if (paramzzif1 != null)
        {
          paramzzif1 = this.zzpj.zzrm.getNextView();
          if (!(paramzzif1 instanceof zzjp)) {
            break label271;
          }
          ((zzjp)paramzzif1).zza(this.zzpj.context, this.zzpj.zzrp, this.zzpe);
          this.zzpj.zzbV();
        }
        this.zzpj.zzrm.setVisibility(0);
        return true;
      }
      catch (Throwable paramzzif1)
      {
        zzin.zzd("Could not add mediation view to view hierarchy.", paramzzif1);
        return false;
      }
      if ((paramzzif2.zzKW != null) && (paramzzif2.zzED != null))
      {
        paramzzif2.zzED.zza(paramzzif2.zzKW);
        this.zzpj.zzrm.removeAllViews();
        this.zzpj.zzrm.setMinimumWidth(paramzzif2.zzKW.widthPixels);
        this.zzpj.zzrm.setMinimumHeight(paramzzif2.zzKW.heightPixels);
        zzb(paramzzif2.zzED.getView());
        continue;
        label271:
        if (paramzzif1 != null) {
          this.zzpj.zzrm.removeView(paramzzif1);
        }
      }
    }
  }
  
  private void zzd(final zzif paramzzif)
  {
    if (this.zzpj.zzbW()) {
      if (paramzzif.zzED != null)
      {
        if (paramzzif.zzKT != null) {
          this.zzpl.zza(this.zzpj.zzrp, paramzzif);
        }
        if (!paramzzif.zzcv()) {
          break label70;
        }
        this.zzpl.zza(this.zzpj.zzrp, paramzzif).zza(paramzzif.zzED);
      }
    }
    label70:
    while ((this.zzpj.zzrK == null) || (paramzzif.zzKT == null))
    {
      return;
      paramzzif.zzED.zzhU().zza(new zzjq.zzb()
      {
        public void zzbi()
        {
          zzf.this.zzpl.zza(zzf.this.zzpj.zzrp, paramzzif).zza(paramzzif.zzED);
        }
      });
      return;
    }
    this.zzpl.zza(this.zzpj.zzrp, paramzzif, this.zzpj.zzrK);
  }
  
  public void onGlobalLayout()
  {
    zze(this.zzpj.zzrq);
  }
  
  public void onScrollChanged()
  {
    zze(this.zzpj.zzrq);
  }
  
  public void setManualImpressionsEnabled(boolean paramBoolean)
  {
    zzx.zzcD("setManualImpressionsEnabled must be called from the main thread.");
    this.zzpE = paramBoolean;
  }
  
  public void showInterstitial()
  {
    throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
  }
  
  protected zzjp zza(zzif.zza paramzza, zze paramzze)
  {
    if (this.zzpj.zzrp.zzul) {
      this.zzpj.zzrp = zzb(paramzza);
    }
    return super.zza(paramzza, paramzze);
  }
  
  protected void zza(zzif paramzzif, boolean paramBoolean)
  {
    super.zza(paramzzif, paramBoolean);
    if (zzm.zzg(paramzzif)) {
      zzm.zza(paramzzif, new zza());
    }
  }
  
  public boolean zza(zzif paramzzif1, zzif paramzzif2)
  {
    if (!super.zza(paramzzif1, paramzzif2)) {
      return false;
    }
    if ((this.zzpj.zzbW()) && (!zzb(paramzzif1, paramzzif2)))
    {
      zzf(0);
      return false;
    }
    if (paramzzif2.zzIm)
    {
      zze(paramzzif2);
      zzjk.zza(this.zzpj.zzrm, this);
      zzjk.zza(this.zzpj.zzrm, this);
    }
    for (;;)
    {
      zzd(paramzzif2);
      return true;
      if ((!this.zzpj.zzbX()) || (((Boolean)zzbt.zzxg.get()).booleanValue())) {
        zza(paramzzif2, false);
      }
    }
  }
  
  protected boolean zzaV()
  {
    boolean bool = true;
    if (!zzr.zzbC().zza(this.zzpj.context.getPackageManager(), this.zzpj.context.getPackageName(), "android.permission.INTERNET"))
    {
      zzn.zzcS().zza(this.zzpj.zzrm, this.zzpj.zzrp, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
      bool = false;
    }
    if (!zzr.zzbC().zzI(this.zzpj.context))
    {
      zzn.zzcS().zza(this.zzpj.zzrm, this.zzpj.zzrp, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
      bool = false;
    }
    if ((!bool) && (this.zzpj.zzrm != null)) {
      this.zzpj.zzrm.setVisibility(0);
    }
    return bool;
  }
  
  public boolean zzb(AdRequestParcel paramAdRequestParcel)
  {
    return super.zzb(zze(paramAdRequestParcel));
  }
  
  AdRequestParcel zze(AdRequestParcel paramAdRequestParcel)
  {
    if (paramAdRequestParcel.zztH == this.zzpE) {
      return paramAdRequestParcel;
    }
    int i = paramAdRequestParcel.versionCode;
    long l = paramAdRequestParcel.zztC;
    Bundle localBundle = paramAdRequestParcel.extras;
    int j = paramAdRequestParcel.zztD;
    List localList = paramAdRequestParcel.zztE;
    boolean bool2 = paramAdRequestParcel.zztF;
    int k = paramAdRequestParcel.zztG;
    if ((paramAdRequestParcel.zztH) || (this.zzpE)) {}
    for (boolean bool1 = true;; bool1 = false) {
      return new AdRequestParcel(i, l, localBundle, j, localList, bool2, k, bool1, paramAdRequestParcel.zztI, paramAdRequestParcel.zztJ, paramAdRequestParcel.zztK, paramAdRequestParcel.zztL, paramAdRequestParcel.zztM, paramAdRequestParcel.zztN, paramAdRequestParcel.zztO, paramAdRequestParcel.zztP, paramAdRequestParcel.zztQ, paramAdRequestParcel.zztR);
    }
  }
  
  void zze(zzif paramzzif)
  {
    if (paramzzif == null) {}
    while ((paramzzif.zzKU) || (this.zzpj.zzrm == null) || (!zzr.zzbC().zza(this.zzpj.zzrm, this.zzpj.context)) || (!this.zzpj.zzrm.getGlobalVisibleRect(new Rect(), null))) {
      return;
    }
    zza(paramzzif, false);
    paramzzif.zzKU = true;
  }
  
  public class zza
  {
    public zza() {}
    
    public void onClick()
    {
      zzf.this.onAdClicked();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */