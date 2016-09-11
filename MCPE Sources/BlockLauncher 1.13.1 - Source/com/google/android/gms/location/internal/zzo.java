package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo
  implements Parcelable.Creator<ParcelableGeofence>
{
  static void zza(ParcelableGeofence paramParcelableGeofence, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramParcelableGeofence.getRequestId(), false);
    zzb.zzc(paramParcel, 1000, paramParcelableGeofence.getVersionCode());
    zzb.zza(paramParcel, 2, paramParcelableGeofence.getExpirationTime());
    zzb.zza(paramParcel, 3, paramParcelableGeofence.zzyT());
    zzb.zza(paramParcel, 4, paramParcelableGeofence.getLatitude());
    zzb.zza(paramParcel, 5, paramParcelableGeofence.getLongitude());
    zzb.zza(paramParcel, 6, paramParcelableGeofence.zzyU());
    zzb.zzc(paramParcel, 7, paramParcelableGeofence.zzyV());
    zzb.zzc(paramParcel, 8, paramParcelableGeofence.getNotificationResponsiveness());
    zzb.zzc(paramParcel, 9, paramParcelableGeofence.zzyW());
    zzb.zzI(paramParcel, paramInt);
  }
  
  public ParcelableGeofence zzeZ(Parcel paramParcel)
  {
    int n = zza.zzau(paramParcel);
    int m = 0;
    String str = null;
    int k = 0;
    short s = 0;
    double d2 = 0.0D;
    double d1 = 0.0D;
    float f = 0.0F;
    long l = 0L;
    int j = 0;
    int i = -1;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = zza.zzat(paramParcel);
      switch (zza.zzca(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        str = zza.zzp(paramParcel, i1);
        break;
      case 1000: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        l = zza.zzi(paramParcel, i1);
        break;
      case 3: 
        s = zza.zzf(paramParcel, i1);
        break;
      case 4: 
        d2 = zza.zzn(paramParcel, i1);
        break;
      case 5: 
        d1 = zza.zzn(paramParcel, i1);
        break;
      case 6: 
        f = zza.zzl(paramParcel, i1);
        break;
      case 7: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 8: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 9: 
        i = zza.zzg(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zza.zza("Overread allowed size end=" + n, paramParcel);
    }
    return new ParcelableGeofence(m, str, k, s, d2, d1, f, l, j, i);
  }
  
  public ParcelableGeofence[] zzhH(int paramInt)
  {
    return new ParcelableGeofence[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\internal\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */