package com.google.android.gms.auth.api.credentials.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh
  implements Parcelable.Creator<GeneratePasswordRequest>
{
  static void zza(GeneratePasswordRequest paramGeneratePasswordRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramGeneratePasswordRequest.zzmr(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramGeneratePasswordRequest.mVersionCode);
    zzb.zzI(paramParcel, i);
  }
  
  public GeneratePasswordRequest zzL(Parcel paramParcel)
  {
    int j = zza.zzau(paramParcel);
    int i = 0;
    PasswordSpecification localPasswordSpecification = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localPasswordSpecification = (PasswordSpecification)zza.zza(paramParcel, k, PasswordSpecification.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new GeneratePasswordRequest(i, localPasswordSpecification);
  }
  
  public GeneratePasswordRequest[] zzaG(int paramInt)
  {
    return new GeneratePasswordRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\credentials\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */