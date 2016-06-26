package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<FaceSettingsParcel>
{
  static void zza(FaceSettingsParcel paramFaceSettingsParcel, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.zzb.zzav(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramFaceSettingsParcel.versionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 2, paramFaceSettingsParcel.mode);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 3, paramFaceSettingsParcel.zzbnV);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 4, paramFaceSettingsParcel.zzbnW);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 5, paramFaceSettingsParcel.zzbnX);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 6, paramFaceSettingsParcel.zzbnY);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 7, paramFaceSettingsParcel.zzbnZ);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, paramInt);
  }
  
  public FaceSettingsParcel zzhl(Parcel paramParcel)
  {
    boolean bool1 = false;
    int n = zza.zzau(paramParcel);
    float f = -1.0F;
    boolean bool2 = false;
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = zza.zzat(paramParcel);
      switch (zza.zzca(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 3: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 4: 
        i = zza.zzg(paramParcel, i1);
        break;
      case 5: 
        bool2 = zza.zzc(paramParcel, i1);
        break;
      case 6: 
        bool1 = zza.zzc(paramParcel, i1);
        break;
      case 7: 
        f = zza.zzl(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zza.zza("Overread allowed size end=" + n, paramParcel);
    }
    return new FaceSettingsParcel(m, k, j, i, bool2, bool1, f);
  }
  
  public FaceSettingsParcel[] zzkI(int paramInt)
  {
    return new FaceSettingsParcel[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\vision\face\internal\client\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */