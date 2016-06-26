package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzf
  implements Parcelable.Creator<AmsEntityUpdateParcelable>
{
  static void zza(AmsEntityUpdateParcelable paramAmsEntityUpdateParcelable, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramAmsEntityUpdateParcelable.mVersionCode);
    zzb.zza(paramParcel, 2, paramAmsEntityUpdateParcelable.zzIz());
    zzb.zza(paramParcel, 3, paramAmsEntityUpdateParcelable.zzIA());
    zzb.zza(paramParcel, 4, paramAmsEntityUpdateParcelable.getValue(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public AmsEntityUpdateParcelable zzic(Parcel paramParcel)
  {
    byte b2 = 0;
    int j = zza.zzau(paramParcel);
    String str = null;
    byte b1 = 0;
    int i = 0;
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
        b1 = zza.zze(paramParcel, k);
        break;
      case 3: 
        b2 = zza.zze(paramParcel, k);
        break;
      case 4: 
        str = zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new AmsEntityUpdateParcelable(i, b1, b2, str);
  }
  
  public AmsEntityUpdateParcelable[] zzlD(int paramInt)
  {
    return new AmsEntityUpdateParcelable[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */