package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd
  implements Parcelable.Creator<HintRequest>
{
  static void zza(HintRequest paramHintRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramHintRequest.getHintPickerConfig(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramHintRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramHintRequest.isEmailAddressIdentifierSupported());
    zzb.zza(paramParcel, 3, paramHintRequest.zzmy());
    zzb.zza(paramParcel, 4, paramHintRequest.getAccountTypes(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public HintRequest zzH(Parcel paramParcel)
  {
    String[] arrayOfString = null;
    boolean bool1 = false;
    int j = zza.zzau(paramParcel);
    boolean bool2 = false;
    CredentialPickerConfig localCredentialPickerConfig = null;
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
        localCredentialPickerConfig = (CredentialPickerConfig)zza.zza(paramParcel, k, CredentialPickerConfig.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 3: 
        bool1 = zza.zzc(paramParcel, k);
        break;
      case 4: 
        arrayOfString = zza.zzB(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new HintRequest(i, localCredentialPickerConfig, bool2, bool1, arrayOfString);
  }
  
  public HintRequest[] zzaC(int paramInt)
  {
    return new HintRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\credentials\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */