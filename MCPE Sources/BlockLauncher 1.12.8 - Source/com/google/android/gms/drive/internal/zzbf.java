package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzbf
  implements Parcelable.Creator<OnPinnedDownloadPreferencesResponse>
{
  static void zza(OnPinnedDownloadPreferencesResponse paramOnPinnedDownloadPreferencesResponse, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramOnPinnedDownloadPreferencesResponse.mVersionCode);
    zzb.zza(paramParcel, 2, paramOnPinnedDownloadPreferencesResponse.zzasu, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public OnPinnedDownloadPreferencesResponse zzbI(Parcel paramParcel)
  {
    int j = zza.zzau(paramParcel);
    int i = 0;
    ParcelableTransferPreferences localParcelableTransferPreferences = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localParcelableTransferPreferences = (ParcelableTransferPreferences)zza.zza(paramParcel, k, ParcelableTransferPreferences.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new OnPinnedDownloadPreferencesResponse(i, localParcelableTransferPreferences);
  }
  
  public OnPinnedDownloadPreferencesResponse[] zzdD(int paramInt)
  {
    return new OnPinnedDownloadPreferencesResponse[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzbf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */