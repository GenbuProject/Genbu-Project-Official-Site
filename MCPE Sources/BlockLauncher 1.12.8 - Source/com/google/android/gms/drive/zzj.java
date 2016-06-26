package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj
  implements Parcelable.Creator<Permission>
{
  static void zza(Permission paramPermission, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramPermission.mVersionCode);
    zzb.zza(paramParcel, 2, paramPermission.zzsO(), false);
    zzb.zzc(paramParcel, 3, paramPermission.zzsP());
    zzb.zza(paramParcel, 4, paramPermission.zzsQ(), false);
    zzb.zza(paramParcel, 5, paramPermission.zzsR(), false);
    zzb.zzc(paramParcel, 6, paramPermission.getRole());
    zzb.zza(paramParcel, 7, paramPermission.zzsS());
    zzb.zzI(paramParcel, paramInt);
  }
  
  public Permission zzaM(Parcel paramParcel)
  {
    String str1 = null;
    boolean bool = false;
    int m = zza.zzau(paramParcel);
    int i = 0;
    String str2 = null;
    int j = 0;
    String str3 = null;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        str3 = zza.zzp(paramParcel, n);
        break;
      case 3: 
        j = zza.zzg(paramParcel, n);
        break;
      case 4: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 5: 
        str1 = zza.zzp(paramParcel, n);
        break;
      case 6: 
        i = zza.zzg(paramParcel, n);
        break;
      case 7: 
        bool = zza.zzc(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new Permission(k, str3, j, str2, str1, i, bool);
  }
  
  public Permission[] zzcB(int paramInt)
  {
    return new Permission[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */