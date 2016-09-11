package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class AppContentAnnotationEntityCreator
  implements Parcelable.Creator<AppContentAnnotationEntity>
{
  static void zza(AppContentAnnotationEntity paramAppContentAnnotationEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramAppContentAnnotationEntity.getDescription(), false);
    zzb.zzc(paramParcel, 1000, paramAppContentAnnotationEntity.getVersionCode());
    zzb.zza(paramParcel, 2, paramAppContentAnnotationEntity.zzvV(), paramInt, false);
    zzb.zza(paramParcel, 3, paramAppContentAnnotationEntity.getTitle(), false);
    zzb.zza(paramParcel, 5, paramAppContentAnnotationEntity.getId(), false);
    zzb.zza(paramParcel, 6, paramAppContentAnnotationEntity.zzvY(), false);
    zzb.zza(paramParcel, 7, paramAppContentAnnotationEntity.zzvT(), false);
    zzb.zzc(paramParcel, 8, paramAppContentAnnotationEntity.zzvU());
    zzb.zzc(paramParcel, 9, paramAppContentAnnotationEntity.zzvX());
    zzb.zza(paramParcel, 10, paramAppContentAnnotationEntity.zzvW(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public AppContentAnnotationEntity zzeg(Parcel paramParcel)
  {
    int i = 0;
    Bundle localBundle = null;
    int m = zza.zzau(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    Uri localUri = null;
    String str5 = null;
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
        str5 = zza.zzp(paramParcel, n);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        localUri = (Uri)zza.zza(paramParcel, n, Uri.CREATOR);
        break;
      case 3: 
        str4 = zza.zzp(paramParcel, n);
        break;
      case 5: 
        str3 = zza.zzp(paramParcel, n);
        break;
      case 6: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 7: 
        str1 = zza.zzp(paramParcel, n);
        break;
      case 8: 
        j = zza.zzg(paramParcel, n);
        break;
      case 9: 
        i = zza.zzg(paramParcel, n);
        break;
      case 10: 
        localBundle = zza.zzr(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new AppContentAnnotationEntity(k, str5, localUri, str4, str3, str2, str1, j, i, localBundle);
  }
  
  public AppContentAnnotationEntity[] zzgi(int paramInt)
  {
    return new AppContentAnnotationEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\appcontent\AppContentAnnotationEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */