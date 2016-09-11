package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzit;
import com.google.android.gms.internal.zzji;

@zzhb
public final class zzc
{
  public static zzit zza(Context paramContext, VersionInfoParcel paramVersionInfoParcel, zzji<AdRequestInfoParcel> paramzzji, zza paramzza)
  {
    zza(paramContext, paramVersionInfoParcel, paramzzji, paramzza, new zzb()
    {
      public boolean zza(VersionInfoParcel paramAnonymousVersionInfoParcel)
      {
        return (paramAnonymousVersionInfoParcel.zzNb) || ((zze.zzap(this.zzxh)) && (!((Boolean)zzbt.zzwb.get()).booleanValue()));
      }
    });
  }
  
  static zzit zza(Context paramContext, VersionInfoParcel paramVersionInfoParcel, zzji<AdRequestInfoParcel> paramzzji, zza paramzza, zzb paramzzb)
  {
    if (paramzzb.zza(paramVersionInfoParcel)) {
      return zza(paramContext, paramzzji, paramzza);
    }
    return zzb(paramContext, paramVersionInfoParcel, paramzzji, paramzza);
  }
  
  private static zzit zza(Context paramContext, zzji<AdRequestInfoParcel> paramzzji, zza paramzza)
  {
    zzin.zzaI("Fetching ad response from local ad request service.");
    paramContext = new zzd.zza(paramContext, paramzzji, paramzza);
    paramContext.zzga();
    return paramContext;
  }
  
  private static zzit zzb(Context paramContext, VersionInfoParcel paramVersionInfoParcel, zzji<AdRequestInfoParcel> paramzzji, zza paramzza)
  {
    zzin.zzaI("Fetching ad response from remote ad request service.");
    if (!zzn.zzcS().zzU(paramContext))
    {
      zzin.zzaK("Failed to connect to remote ad request service.");
      return null;
    }
    return new zzd.zzb(paramContext, paramVersionInfoParcel, paramzzji, paramzza);
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(AdResponseParcel paramAdResponseParcel);
  }
  
  static abstract interface zzb
  {
    public abstract boolean zza(VersionInfoParcel paramVersionInfoParcel);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\request\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */