package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<AppMetadata>
{
  static void zza(AppMetadata paramAppMetadata, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.zzb.zzav(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramAppMetadata.versionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramAppMetadata.packageName, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramAppMetadata.zzaVt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramAppMetadata.zzaMV, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 5, paramAppMetadata.zzaVu, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 6, paramAppMetadata.zzaVv);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 7, paramAppMetadata.zzaVw);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 8, paramAppMetadata.zzaVx, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 9, paramAppMetadata.zzaVy);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 10, paramAppMetadata.zzaVz);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, paramInt);
  }
  
  public AppMetadata zzfL(Parcel paramParcel)
  {
    int j = zza.zzau(paramParcel);
    int i = 0;
    String str5 = null;
    String str4 = null;
    String str3 = null;
    String str2 = null;
    long l2 = 0L;
    long l1 = 0L;
    String str1 = null;
    boolean bool2 = false;
    boolean bool1 = false;
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
        str5 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        str4 = zza.zzp(paramParcel, k);
        break;
      case 4: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 5: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 6: 
        l2 = zza.zzi(paramParcel, k);
        break;
      case 7: 
        l1 = zza.zzi(paramParcel, k);
        break;
      case 8: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 9: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 10: 
        bool1 = zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new AppMetadata(i, str5, str4, str3, str2, l2, l1, str1, bool2, bool1);
  }
  
  public AppMetadata[] zziH(int paramInt)
  {
    return new AppMetadata[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */