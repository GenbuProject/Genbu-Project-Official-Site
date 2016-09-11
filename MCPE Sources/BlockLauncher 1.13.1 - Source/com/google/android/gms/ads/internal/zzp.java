package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.formats.zzh.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzax;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzcf;
import com.google.android.gms.internal.zzch;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcs;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzey;
import com.google.android.gms.internal.zzfb;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import java.util.List;

@zzhb
public class zzp
  extends zzb
{
  public zzp(Context paramContext, zzd paramzzd, AdSizeParcel paramAdSizeParcel, String paramString, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel)
  {
    super(paramContext, paramAdSizeParcel, paramString, paramzzex, paramVersionInfoParcel, paramzzd);
  }
  
  private static com.google.android.gms.ads.internal.formats.zzd zza(zzfb paramzzfb)
    throws RemoteException
  {
    String str1 = paramzzfb.getHeadline();
    List localList = paramzzfb.getImages();
    String str2 = paramzzfb.getBody();
    if (paramzzfb.zzdK() != null) {}
    for (zzch localzzch = paramzzfb.zzdK();; localzzch = null) {
      return new com.google.android.gms.ads.internal.formats.zzd(str1, localList, str2, localzzch, paramzzfb.getCallToAction(), paramzzfb.getStarRating(), paramzzfb.getStore(), paramzzfb.getPrice(), null, paramzzfb.getExtras());
    }
  }
  
  private static zze zza(zzfc paramzzfc)
    throws RemoteException
  {
    String str1 = paramzzfc.getHeadline();
    List localList = paramzzfc.getImages();
    String str2 = paramzzfc.getBody();
    if (paramzzfc.zzdO() != null) {}
    for (zzch localzzch = paramzzfc.zzdO();; localzzch = null) {
      return new zze(str1, localList, str2, localzzch, paramzzfc.getCallToAction(), paramzzfc.getAdvertiser(), null, paramzzfc.getExtras());
    }
  }
  
  private void zza(final com.google.android.gms.ads.internal.formats.zzd paramzzd)
  {
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        try
        {
          zzp.this.zzpj.zzrz.zza(paramzzd);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzin.zzd("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", localRemoteException);
        }
      }
    });
  }
  
  private void zza(final zze paramzze)
  {
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        try
        {
          zzp.this.zzpj.zzrA.zza(paramzze);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzin.zzd("Could not call OnContentAdLoadedListener.onContentAdLoaded().", localRemoteException);
        }
      }
    });
  }
  
  private void zza(final zzif paramzzif, final String paramString)
  {
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        try
        {
          ((zzcu)zzp.this.zzpj.zzrC.get(paramString)).zza((zzf)paramzzif.zzLa);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzin.zzd("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", localRemoteException);
        }
      }
    });
  }
  
  public void pause()
  {
    throw new IllegalStateException("Native Ad DOES NOT support pause().");
  }
  
  public void resume()
  {
    throw new IllegalStateException("Native Ad DOES NOT support resume().");
  }
  
  public void showInterstitial()
  {
    throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
  }
  
  public void zza(SimpleArrayMap<String, zzcu> paramSimpleArrayMap)
  {
    zzx.zzcD("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
    this.zzpj.zzrC = paramSimpleArrayMap;
  }
  
  public void zza(zzh paramzzh)
  {
    if (this.zzpj.zzrq.zzKT != null) {
      zzr.zzbF().zzhh().zza(this.zzpj.zzrp, this.zzpj.zzrq, paramzzh);
    }
  }
  
  public void zza(zzcf paramzzcf)
  {
    throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
  }
  
  public void zza(zzgd paramzzgd)
  {
    throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
  }
  
  public void zza(final zzif.zza paramzza, zzcb paramzzcb)
  {
    if (paramzza.zzrp != null) {
      this.zzpj.zzrp = paramzza.zzrp;
    }
    if (paramzza.errorCode != -2)
    {
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          zzp.this.zzb(new zzif(paramzza, null, null, null, null, null, null));
        }
      });
      return;
    }
    this.zzpj.zzrL = 0;
    this.zzpj.zzro = zzr.zzbB().zza(this.zzpj.context, this, paramzza, this.zzpj.zzrk, null, this.zzpn, this, paramzzcb);
    zzin.zzaI("AdRenderer: " + this.zzpj.zzro.getClass().getName());
  }
  
  public void zza(List<String> paramList)
  {
    zzx.zzcD("setNativeTemplates must be called on the main UI thread.");
    this.zzpj.zzrH = paramList;
  }
  
  protected boolean zza(AdRequestParcel paramAdRequestParcel, zzif paramzzif, boolean paramBoolean)
  {
    return this.zzpi.zzbw();
  }
  
  protected boolean zza(zzif paramzzif1, zzif paramzzif2)
  {
    zza(null);
    if (!this.zzpj.zzbW()) {
      throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }
    if (paramzzif2.zzHT) {}
    for (;;)
    {
      try
      {
        localObject1 = paramzzif2.zzCq.zzeF();
        localObject2 = paramzzif2.zzCq.zzeG();
        if (localObject1 == null) {
          continue;
        }
        localObject2 = zza((zzfb)localObject1);
        ((com.google.android.gms.ads.internal.formats.zzd)localObject2).zzb(new zzg(this.zzpj.context, this, this.zzpj.zzrk, (zzfb)localObject1));
        zza((com.google.android.gms.ads.internal.formats.zzd)localObject2);
      }
      catch (RemoteException localRemoteException)
      {
        Object localObject1;
        Object localObject2;
        zzin.zzd("Failed to get native ad mapper", localRemoteException);
        continue;
        zzin.zzaK("No matching mapper for retrieved native ad template.");
        zzf(0);
        return false;
      }
      return super.zza(paramzzif1, paramzzif2);
      if (localObject2 != null)
      {
        localObject1 = zza((zzfc)localObject2);
        ((zze)localObject1).zzb(new zzg(this.zzpj.context, this, this.zzpj.zzrk, (zzfc)localObject2));
        zza((zze)localObject1);
      }
      else
      {
        zzh.zza localzza = paramzzif2.zzLa;
        if (((localzza instanceof zze)) && (this.zzpj.zzrA != null))
        {
          zza((zze)paramzzif2.zzLa);
        }
        else if (((localzza instanceof com.google.android.gms.ads.internal.formats.zzd)) && (this.zzpj.zzrz != null))
        {
          zza((com.google.android.gms.ads.internal.formats.zzd)paramzzif2.zzLa);
        }
        else
        {
          if ((!(localzza instanceof zzf)) || (this.zzpj.zzrC == null) || (this.zzpj.zzrC.get(((zzf)localzza).getCustomTemplateId()) == null)) {
            break;
          }
          zza(paramzzif2, ((zzf)localzza).getCustomTemplateId());
        }
      }
    }
    zzin.zzaK("No matching listener for retrieved native ad template.");
    zzf(0);
    return false;
  }
  
  public void zzb(SimpleArrayMap<String, zzct> paramSimpleArrayMap)
  {
    zzx.zzcD("setOnCustomClickListener must be called on the main UI thread.");
    this.zzpj.zzrB = paramSimpleArrayMap;
  }
  
  public void zzb(NativeAdOptionsParcel paramNativeAdOptionsParcel)
  {
    zzx.zzcD("setNativeAdOptions must be called on the main UI thread.");
    this.zzpj.zzrD = paramNativeAdOptionsParcel;
  }
  
  public void zzb(zzcr paramzzcr)
  {
    zzx.zzcD("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
    this.zzpj.zzrz = paramzzcr;
  }
  
  public void zzb(zzcs paramzzcs)
  {
    zzx.zzcD("setOnContentAdLoadedListener must be called on the main UI thread.");
    this.zzpj.zzrA = paramzzcs;
  }
  
  public SimpleArrayMap<String, zzcu> zzbv()
  {
    zzx.zzcD("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
    return this.zzpj.zzrC;
  }
  
  public zzct zzs(String paramString)
  {
    zzx.zzcD("getOnCustomClickListener must be called on the main UI thread.");
    return (zzct)this.zzpj.zzrB.get(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */