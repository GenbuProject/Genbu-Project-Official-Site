package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;

public abstract class zzb<R extends Result>
  extends zza.zza<R, zze>
{
  public zzb(GoogleApiClient paramGoogleApiClient)
  {
    super(zzk.zzUI, paramGoogleApiClient);
  }
  
  public void zzbj(int paramInt)
  {
    zza(zzc(new Status(paramInt)));
  }
  
  public void zze(int paramInt, String paramString)
  {
    zza(zzc(new Status(paramInt, paramString, null)));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */