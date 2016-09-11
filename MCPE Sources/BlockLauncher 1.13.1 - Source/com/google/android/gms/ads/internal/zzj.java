package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzs.zza;
import com.google.android.gms.ads.internal.client.zzx;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcs;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzj
  extends zzs.zza
{
  private final Context mContext;
  private zzq zzpK;
  private NativeAdOptionsParcel zzpP;
  private zzx zzpR;
  private final String zzpS;
  private final VersionInfoParcel zzpT;
  private zzcr zzpY;
  private zzcs zzpZ;
  private final zzd zzpm;
  private final zzex zzpn;
  private SimpleArrayMap<String, zzct> zzqa;
  private SimpleArrayMap<String, zzcu> zzqb;
  
  public zzj(Context paramContext, String paramString, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel, zzd paramzzd)
  {
    this.mContext = paramContext;
    this.zzpS = paramString;
    this.zzpn = paramzzex;
    this.zzpT = paramVersionInfoParcel;
    this.zzqb = new SimpleArrayMap();
    this.zzqa = new SimpleArrayMap();
    this.zzpm = paramzzd;
  }
  
  public void zza(NativeAdOptionsParcel paramNativeAdOptionsParcel)
  {
    this.zzpP = paramNativeAdOptionsParcel;
  }
  
  public void zza(zzcr paramzzcr)
  {
    this.zzpY = paramzzcr;
  }
  
  public void zza(zzcs paramzzcs)
  {
    this.zzpZ = paramzzcs;
  }
  
  public void zza(String paramString, zzcu paramzzcu, zzct paramzzct)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
    }
    this.zzqb.put(paramString, paramzzcu);
    this.zzqa.put(paramString, paramzzct);
  }
  
  public void zzb(zzq paramzzq)
  {
    this.zzpK = paramzzq;
  }
  
  public void zzb(zzx paramzzx)
  {
    this.zzpR = paramzzx;
  }
  
  public zzr zzbn()
  {
    return new zzi(this.mContext, this.zzpS, this.zzpn, this.zzpT, this.zzpK, this.zzpY, this.zzpZ, this.zzqb, this.zzqa, this.zzpP, this.zzpR, this.zzpm);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */