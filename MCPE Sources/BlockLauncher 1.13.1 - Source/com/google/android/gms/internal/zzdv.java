package com.google.android.gms.internal;

import android.content.Context;
import android.content.MutableContextWrapper;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzk;

@zzhb
public class zzdv
{
  private MutableContextWrapper zzAb;
  private final VersionInfoParcel zzpT;
  private final zzd zzpm;
  private final zzex zzpn;
  
  zzdv(Context paramContext, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel, zzd paramzzd)
  {
    this.zzAb = new MutableContextWrapper(paramContext.getApplicationContext());
    this.zzpn = paramzzex;
    this.zzpT = paramVersionInfoParcel;
    this.zzpm = paramzzd;
  }
  
  public zzk zzX(String paramString)
  {
    return new zzk(this.zzAb, new AdSizeParcel(), paramString, this.zzpn, this.zzpT, this.zzpm);
  }
  
  public zzdv zzec()
  {
    return new zzdv(this.zzAb.getBaseContext(), this.zzpn, this.zzpT, this.zzpm);
  }
  
  public MutableContextWrapper zzed()
  {
    return this.zzAb;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */