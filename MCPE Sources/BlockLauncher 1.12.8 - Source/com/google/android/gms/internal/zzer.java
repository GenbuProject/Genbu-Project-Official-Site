package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.dynamic.zze;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public final class zzer
  implements zzes.zza
{
  private final Context mContext;
  private final String zzCd;
  private final long zzCe;
  private final zzeo zzCf;
  private final zzen zzCg;
  private final AdSizeParcel zzCh;
  private zzey zzCi;
  private int zzCj = -2;
  private zzfa zzCk;
  private final NativeAdOptionsParcel zzpP;
  private final List<String> zzpQ;
  private final VersionInfoParcel zzpT;
  private final Object zzpV = new Object();
  private final zzex zzpn;
  private final AdRequestParcel zzqH;
  private final boolean zzsA;
  private final boolean zzuS;
  
  public zzer(Context paramContext, String paramString, zzex paramzzex, zzeo paramzzeo, zzen paramzzen, AdRequestParcel paramAdRequestParcel, AdSizeParcel paramAdSizeParcel, VersionInfoParcel paramVersionInfoParcel, boolean paramBoolean1, boolean paramBoolean2, NativeAdOptionsParcel paramNativeAdOptionsParcel, List<String> paramList)
  {
    this.mContext = paramContext;
    this.zzpn = paramzzex;
    this.zzCg = paramzzen;
    if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(paramString))
    {
      this.zzCd = zzey();
      this.zzCf = paramzzeo;
      if (paramzzeo.zzBP == -1L) {
        break label136;
      }
    }
    label136:
    for (long l = paramzzeo.zzBP;; l = 10000L)
    {
      this.zzCe = l;
      this.zzqH = paramAdRequestParcel;
      this.zzCh = paramAdSizeParcel;
      this.zzpT = paramVersionInfoParcel;
      this.zzsA = paramBoolean1;
      this.zzuS = paramBoolean2;
      this.zzpP = paramNativeAdOptionsParcel;
      this.zzpQ = paramList;
      return;
      this.zzCd = paramString;
      break;
    }
  }
  
  private void zza(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    for (;;)
    {
      if (this.zzCj != -2) {
        return;
      }
      zzb(paramLong1, paramLong2, paramLong3, paramLong4);
    }
  }
  
  private void zza(zzeq paramzzeq)
  {
    if ("com.google.ads.mediation.AdUrlAdapter".equals(this.zzCd))
    {
      Bundle localBundle = this.zzqH.zztM.getBundle(this.zzCd);
      localObject = localBundle;
      if (localBundle == null) {
        localObject = new Bundle();
      }
      ((Bundle)localObject).putString("sdk_less_network_id", this.zzCg.zzBA);
      this.zzqH.zztM.putBundle(this.zzCd, (Bundle)localObject);
    }
    Object localObject = zzac(this.zzCg.zzBG);
    try
    {
      if (this.zzpT.zzNa < 4100000)
      {
        if (this.zzCh.zzui)
        {
          this.zzCi.zza(zze.zzC(this.mContext), this.zzqH, (String)localObject, paramzzeq);
          return;
        }
        this.zzCi.zza(zze.zzC(this.mContext), this.zzCh, this.zzqH, (String)localObject, paramzzeq);
        return;
      }
    }
    catch (RemoteException paramzzeq)
    {
      zzin.zzd("Could not request ad from mediation adapter.", paramzzeq);
      zzr(5);
      return;
    }
    if (this.zzsA)
    {
      this.zzCi.zza(zze.zzC(this.mContext), this.zzqH, (String)localObject, this.zzCg.zzBz, paramzzeq, this.zzpP, this.zzpQ);
      return;
    }
    if (this.zzCh.zzui)
    {
      this.zzCi.zza(zze.zzC(this.mContext), this.zzqH, (String)localObject, this.zzCg.zzBz, paramzzeq);
      return;
    }
    if (this.zzuS)
    {
      if (this.zzCg.zzBJ != null)
      {
        this.zzCi.zza(zze.zzC(this.mContext), this.zzqH, (String)localObject, this.zzCg.zzBz, paramzzeq, new NativeAdOptionsParcel(zzad(this.zzCg.zzBN)), this.zzCg.zzBM);
        return;
      }
      this.zzCi.zza(zze.zzC(this.mContext), this.zzCh, this.zzqH, (String)localObject, this.zzCg.zzBz, paramzzeq);
      return;
    }
    this.zzCi.zza(zze.zzC(this.mContext), this.zzCh, this.zzqH, (String)localObject, this.zzCg.zzBz, paramzzeq);
  }
  
  private String zzac(String paramString)
  {
    if ((paramString == null) || (!zzeB()) || (zzs(2))) {
      return paramString;
    }
    try
    {
      Object localObject = new JSONObject(paramString);
      ((JSONObject)localObject).remove("cpm_floor_cents");
      localObject = ((JSONObject)localObject).toString();
      return (String)localObject;
    }
    catch (JSONException localJSONException)
    {
      zzin.zzaK("Could not remove field. Returning the original value");
    }
    return paramString;
  }
  
  private static NativeAdOptions zzad(String paramString)
  {
    NativeAdOptions.Builder localBuilder = new NativeAdOptions.Builder();
    if (paramString == null) {
      return localBuilder.build();
    }
    try
    {
      paramString = new JSONObject(paramString);
      localBuilder.setRequestMultipleImages(paramString.optBoolean("multiple_images", false));
      localBuilder.setReturnUrlsForImageAssets(paramString.optBoolean("only_urls", false));
      localBuilder.setImageOrientation(zzae(paramString.optString("native_image_orientation", "any")));
      return localBuilder.build();
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        zzin.zzd("Exception occurred when creating native ad options", paramString);
      }
    }
  }
  
  private static int zzae(String paramString)
  {
    if ("landscape".equals(paramString)) {
      return 2;
    }
    if ("portrait".equals(paramString)) {
      return 1;
    }
    return 0;
  }
  
  private void zzb(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    long l = SystemClock.elapsedRealtime();
    paramLong1 = paramLong2 - (l - paramLong1);
    paramLong2 = paramLong4 - (l - paramLong3);
    if ((paramLong1 <= 0L) || (paramLong2 <= 0L))
    {
      zzin.zzaJ("Timed out waiting for adapter.");
      this.zzCj = 3;
      return;
    }
    try
    {
      this.zzpV.wait(Math.min(paramLong1, paramLong2));
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      this.zzCj = -1;
    }
  }
  
  private zzey zzeA()
  {
    zzin.zzaJ("Instantiating mediation adapter: " + this.zzCd);
    if ((((Boolean)zzbt.zzwV.get()).booleanValue()) && ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzCd))) {
      return new zzfe(new AdMobAdapter());
    }
    if ((((Boolean)zzbt.zzwW.get()).booleanValue()) && ("com.google.ads.mediation.AdUrlAdapter".equals(this.zzCd))) {
      return new zzfe(new AdUrlAdapter());
    }
    try
    {
      zzey localzzey = this.zzpn.zzaf(this.zzCd);
      return localzzey;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zza("Could not instantiate mediation adapter: " + this.zzCd, localRemoteException);
    }
    return null;
  }
  
  private boolean zzeB()
  {
    return this.zzCf.zzBX != -1;
  }
  
  private int zzeC()
  {
    int j;
    if (this.zzCg.zzBG == null)
    {
      j = 0;
      return j;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject(this.zzCg.zzBG);
      if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzCd)) {
        return localJSONObject.optInt("cpm_cents", 0);
      }
    }
    catch (JSONException localJSONException)
    {
      zzin.zzaK("Could not convert to json. Returning 0");
      return 0;
    }
    if (zzs(2)) {}
    for (int i = localJSONException.optInt("cpm_floor_cents", 0);; i = 0)
    {
      j = i;
      if (i != 0) {
        break;
      }
      return localJSONException.optInt("penalized_average_cpm_cents", 0);
    }
  }
  
  private String zzey()
  {
    try
    {
      if (!TextUtils.isEmpty(this.zzCg.zzBD))
      {
        if (this.zzpn.zzag(this.zzCg.zzBD)) {
          return "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
      }
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzaK("Fail to determine the custom event's version, assuming the old one.");
    }
    return "com.google.ads.mediation.customevent.CustomEventAdapter";
  }
  
  private zzfa zzez()
  {
    if ((this.zzCj != 0) || (!zzeB())) {
      return null;
    }
    try
    {
      if ((zzs(4)) && (this.zzCk != null) && (this.zzCk.zzeD() != 0))
      {
        zzfa localzzfa = this.zzCk;
        return localzzfa;
      }
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzaK("Could not get cpm value from MediationResponseMetadata");
    }
    return zzt(zzeC());
  }
  
  private boolean zzs(int paramInt)
  {
    boolean bool = false;
    for (;;)
    {
      try
      {
        Bundle localBundle;
        if (this.zzsA)
        {
          localBundle = this.zzCi.zzeI();
          if (localBundle != null)
          {
            if ((localBundle.getInt("capabilities", 0) & paramInt) == paramInt) {
              bool = true;
            }
          }
          else {
            return bool;
          }
        }
        else
        {
          if (this.zzCh.zzui)
          {
            localBundle = this.zzCi.getInterstitialAdapterInfo();
            continue;
          }
          localBundle = this.zzCi.zzeH();
          continue;
        }
        bool = false;
      }
      catch (RemoteException localRemoteException)
      {
        zzin.zzaK("Could not get adapter info. Returning false");
        return false;
      }
    }
  }
  
  private static zzfa zzt(int paramInt)
  {
    new zzfa.zza()
    {
      public int zzeD()
        throws RemoteException
      {
        return this.zzCn;
      }
    };
  }
  
  public void cancel()
  {
    synchronized (this.zzpV)
    {
      try
      {
        if (this.zzCi != null) {
          this.zzCi.destroy();
        }
        this.zzCj = -1;
        this.zzpV.notify();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          zzin.zzd("Could not destroy mediation adapter.", localRemoteException);
        }
      }
    }
  }
  
  public zzes zza(long paramLong1, long paramLong2)
  {
    synchronized (this.zzpV)
    {
      long l = SystemClock.elapsedRealtime();
      final Object localObject2 = new zzeq();
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          synchronized (zzer.zza(zzer.this))
          {
            if (zzer.zzb(zzer.this) != -2) {
              return;
            }
            zzer.zza(zzer.this, zzer.zzc(zzer.this));
            if (zzer.zzd(zzer.this) == null)
            {
              zzer.this.zzr(4);
              return;
            }
          }
          if ((zzer.zze(zzer.this)) && (!zzer.zza(zzer.this, 1)))
          {
            zzin.zzaK("Ignoring adapter " + zzer.zzf(zzer.this) + " as delayed" + " impression is not supported");
            zzer.this.zzr(2);
            return;
          }
          localObject2.zza(zzer.this);
          zzer.zza(zzer.this, localObject2);
        }
      });
      zza(l, this.zzCe, paramLong1, paramLong2);
      localObject2 = new zzes(this.zzCg, this.zzCi, this.zzCd, (zzeq)localObject2, this.zzCj, zzez());
      return (zzes)localObject2;
    }
  }
  
  public void zza(int paramInt, zzfa paramzzfa)
  {
    synchronized (this.zzpV)
    {
      this.zzCj = paramInt;
      this.zzCk = paramzzfa;
      this.zzpV.notify();
      return;
    }
  }
  
  public void zzr(int paramInt)
  {
    synchronized (this.zzpV)
    {
      this.zzCj = paramInt;
      this.zzpV.notify();
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */