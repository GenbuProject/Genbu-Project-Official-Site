package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.common.internal.zzx;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzht
  extends zzb
  implements zzhw
{
  private static final zzew zzKv = new zzew();
  private final Map<String, zzia> zzKw = new HashMap();
  private boolean zzKx;
  
  public zzht(Context paramContext, zzd paramzzd, AdSizeParcel paramAdSizeParcel, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel)
  {
    super(paramContext, paramAdSizeParcel, null, paramzzex, paramVersionInfoParcel, paramzzd);
  }
  
  private zzif.zza zzc(zzif.zza paramzza)
  {
    zzin.v("Creating mediation ad response for non-mediated rewarded ad.");
    try
    {
      Object localObject1 = zzhe.zzc(paramzza.zzLe).toString();
      Object localObject2 = new JSONObject();
      ((JSONObject)localObject2).put("pubid", paramzza.zzLd.zzrj);
      localObject2 = ((JSONObject)localObject2).toString();
      localObject1 = new zzeo(Arrays.asList(new zzen[] { new zzen((String)localObject1, null, Arrays.asList(new String[] { "com.google.ads.mediation.admob.AdMobAdapter" }), null, null, Collections.emptyList(), Collections.emptyList(), (String)localObject2, null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null) }), -1L, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), "", -1L, 0, 1, null, 0, -1, -1L);
      return new zzif.zza(paramzza.zzLd, paramzza.zzLe, (zzeo)localObject1, paramzza.zzrp, paramzza.errorCode, paramzza.zzKY, paramzza.zzKZ, paramzza.zzKT);
    }
    catch (JSONException localJSONException)
    {
      zzin.zzb("Unable to generate ad state for non-mediated rewarded video.", localJSONException);
    }
    return zzd(paramzza);
  }
  
  private zzif.zza zzd(zzif.zza paramzza)
  {
    return new zzif.zza(paramzza.zzLd, paramzza.zzLe, null, paramzza.zzrp, 0, paramzza.zzKY, paramzza.zzKZ, paramzza.zzKT);
  }
  
  public void destroy()
  {
    zzx.zzcD("destroy must be called on the main UI thread.");
    Iterator localIterator = this.zzKw.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        zzia localzzia = (zzia)this.zzKw.get(str);
        if ((localzzia != null) && (localzzia.zzgP() != null)) {
          localzzia.zzgP().destroy();
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzin.zzaK("Fail to destroy adapter: " + str);
      }
    }
  }
  
  public boolean isLoaded()
  {
    zzx.zzcD("isLoaded must be called on the main UI thread.");
    return (this.zzpj.zzrn == null) && (this.zzpj.zzro == null) && (this.zzpj.zzrq != null) && (!this.zzKx);
  }
  
  public void onRewardedVideoAdClosed()
  {
    zzaQ();
  }
  
  public void onRewardedVideoAdLeftApplication()
  {
    zzaR();
  }
  
  public void onRewardedVideoAdOpened()
  {
    zza(this.zzpj.zzrq, false);
    zzaS();
  }
  
  public void onRewardedVideoStarted()
  {
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzCp != null)) {
      zzr.zzbP().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq, this.zzpj.zzrj, false, this.zzpj.zzrq.zzCp.zzBH);
    }
    zzaU();
  }
  
  public void pause()
  {
    zzx.zzcD("pause must be called on the main UI thread.");
    Iterator localIterator = this.zzKw.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        zzia localzzia = (zzia)this.zzKw.get(str);
        if ((localzzia != null) && (localzzia.zzgP() != null)) {
          localzzia.zzgP().pause();
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzin.zzaK("Fail to pause adapter: " + str);
      }
    }
  }
  
  public void resume()
  {
    zzx.zzcD("resume must be called on the main UI thread.");
    Iterator localIterator = this.zzKw.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        zzia localzzia = (zzia)this.zzKw.get(str);
        if ((localzzia != null) && (localzzia.zzgP() != null)) {
          localzzia.zzgP().resume();
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzin.zzaK("Fail to resume adapter: " + str);
      }
    }
  }
  
  public void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel)
  {
    zzx.zzcD("loadAd must be called on the main UI thread.");
    if (TextUtils.isEmpty(paramRewardedVideoAdRequestParcel.zzrj))
    {
      zzin.zzaK("Invalid ad unit id. Aborting.");
      return;
    }
    this.zzKx = false;
    this.zzpj.zzrj = paramRewardedVideoAdRequestParcel.zzrj;
    super.zzb(paramRewardedVideoAdRequestParcel.zzHt);
  }
  
  public void zza(final zzif.zza paramzza, zzcb paramzzcb)
  {
    if (paramzza.errorCode != -2)
    {
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          zzht.this.zzb(new zzif(paramzza, null, null, null, null, null, null));
        }
      });
      return;
    }
    this.zzpj.zzrr = paramzza;
    if (paramzza.zzKV == null) {
      this.zzpj.zzrr = zzc(paramzza);
    }
    this.zzpj.zzrL = 0;
    this.zzpj.zzro = zzr.zzbB().zza(this.zzpj.context, this.zzpj.getUserId(), this.zzpj.zzrr, this);
  }
  
  public boolean zza(zzif paramzzif1, zzif paramzzif2)
  {
    return true;
  }
  
  @Nullable
  public zzia zzaw(String paramString)
  {
    localObject1 = (zzia)this.zzKw.get(paramString);
    Object localObject2 = localObject1;
    if (localObject1 == null) {}
    try
    {
      localObject2 = this.zzpn;
      if ("com.google.ads.mediation.admob.AdMobAdapter".equals(paramString)) {}
      Object localObject3;
      for (localObject2 = zzKv;; localObject2 = new zzia(((zzex)localObject2).zzaf(paramString), this)) {}
    }
    catch (Exception localException1)
    {
      try
      {
        this.zzKw.put(paramString, localObject2);
        return (zzia)localObject2;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          localObject1 = localException1;
          localObject3 = localException2;
        }
      }
      localException1 = localException1;
      zzin.zzd("Fail to instantiate adapter " + paramString, localException1);
      return (zzia)localObject1;
    }
  }
  
  public void zzc(@Nullable RewardItemParcel paramRewardItemParcel)
  {
    if ((this.zzpj.zzrq != null) && (this.zzpj.zzrq.zzCp != null)) {
      zzr.zzbP().zza(this.zzpj.context, this.zzpj.zzrl.afmaVersion, this.zzpj.zzrq, this.zzpj.zzrj, false, this.zzpj.zzrq.zzCp.zzBI);
    }
    RewardItemParcel localRewardItemParcel = paramRewardItemParcel;
    if (this.zzpj.zzrq != null)
    {
      localRewardItemParcel = paramRewardItemParcel;
      if (this.zzpj.zzrq.zzKV != null)
      {
        localRewardItemParcel = paramRewardItemParcel;
        if (!TextUtils.isEmpty(this.zzpj.zzrq.zzKV.zzBV)) {
          localRewardItemParcel = new RewardItemParcel(this.zzpj.zzrq.zzKV.zzBV, this.zzpj.zzrq.zzKV.zzBW);
        }
      }
    }
    zza(localRewardItemParcel);
  }
  
  public void zzgL()
  {
    zzx.zzcD("showAd must be called on the main UI thread.");
    if (!isLoaded()) {
      zzin.zzaK("The reward video has not loaded.");
    }
    zzia localzzia;
    do
    {
      return;
      this.zzKx = true;
      localzzia = zzaw(this.zzpj.zzrq.zzCr);
    } while ((localzzia == null) || (localzzia.zzgP() == null));
    try
    {
      localzzia.zzgP().showVideo();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not call showVideo.", localRemoteException);
    }
  }
  
  public void zzgM()
  {
    onAdClicked();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzht.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */