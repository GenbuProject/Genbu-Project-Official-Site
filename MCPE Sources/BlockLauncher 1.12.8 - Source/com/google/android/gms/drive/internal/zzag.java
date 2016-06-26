package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzag
  implements Parcelable.Creator<FileUploadPreferencesImpl>
{
  static void zza(FileUploadPreferencesImpl paramFileUploadPreferencesImpl, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramFileUploadPreferencesImpl.mVersionCode);
    zzb.zzc(paramParcel, 2, paramFileUploadPreferencesImpl.zzarG);
    zzb.zzc(paramParcel, 3, paramFileUploadPreferencesImpl.zzarH);
    zzb.zza(paramParcel, 4, paramFileUploadPreferencesImpl.zzarI);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public FileUploadPreferencesImpl zzbq(Parcel paramParcel)
  {
    boolean bool = false;
    int m = zza.zzau(paramParcel);
    int k = 0;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        i = zza.zzg(paramParcel, n);
        break;
      case 2: 
        j = zza.zzg(paramParcel, n);
        break;
      case 3: 
        k = zza.zzg(paramParcel, n);
        break;
      case 4: 
        bool = zza.zzc(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new FileUploadPreferencesImpl(i, j, k, bool);
  }
  
  public FileUploadPreferencesImpl[] zzdl(int paramInt)
  {
    return new FileUploadPreferencesImpl[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */