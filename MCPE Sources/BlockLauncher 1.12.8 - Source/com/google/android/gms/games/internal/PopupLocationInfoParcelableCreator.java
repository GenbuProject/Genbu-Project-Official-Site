package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class PopupLocationInfoParcelableCreator
  implements Parcelable.Creator<PopupLocationInfoParcelable>
{
  static void zza(PopupLocationInfoParcelable paramPopupLocationInfoParcelable, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramPopupLocationInfoParcelable.zzxg(), false);
    zzb.zzc(paramParcel, 1000, paramPopupLocationInfoParcelable.getVersionCode());
    zzb.zza(paramParcel, 2, paramPopupLocationInfoParcelable.getWindowToken(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public PopupLocationInfoParcelable zzem(Parcel paramParcel)
  {
    IBinder localIBinder = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    Bundle localBundle = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localBundle = zza.zzr(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localIBinder = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new PopupLocationInfoParcelable(i, localBundle, localIBinder);
  }
  
  public PopupLocationInfoParcelable[] zzgu(int paramInt)
  {
    return new PopupLocationInfoParcelable[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\PopupLocationInfoParcelableCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */