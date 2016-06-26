package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class AppContentCardEntityCreator
  implements Parcelable.Creator<AppContentCardEntity>
{
  static void zza(AppContentCardEntity paramAppContentCardEntity, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramAppContentCardEntity.getActions(), false);
    zzb.zzc(paramParcel, 1000, paramAppContentCardEntity.getVersionCode());
    zzb.zzc(paramParcel, 2, paramAppContentCardEntity.zzwa(), false);
    zzb.zzc(paramParcel, 3, paramAppContentCardEntity.zzvP(), false);
    zzb.zza(paramParcel, 4, paramAppContentCardEntity.zzvQ(), false);
    zzb.zzc(paramParcel, 5, paramAppContentCardEntity.zzwb());
    zzb.zza(paramParcel, 6, paramAppContentCardEntity.getDescription(), false);
    zzb.zza(paramParcel, 7, paramAppContentCardEntity.getExtras(), false);
    zzb.zza(paramParcel, 10, paramAppContentCardEntity.zzwc(), false);
    zzb.zza(paramParcel, 11, paramAppContentCardEntity.getTitle(), false);
    zzb.zzc(paramParcel, 12, paramAppContentCardEntity.zzwd());
    zzb.zza(paramParcel, 13, paramAppContentCardEntity.getType(), false);
    zzb.zza(paramParcel, 14, paramAppContentCardEntity.getId(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public AppContentCardEntity zzeh(Parcel paramParcel)
  {
    int m = zza.zzau(paramParcel);
    int k = 0;
    ArrayList localArrayList3 = null;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList1 = null;
    String str6 = null;
    int j = 0;
    String str5 = null;
    Bundle localBundle = null;
    String str4 = null;
    String str3 = null;
    int i = 0;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzat(paramParcel);
      switch (zza.zzca(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        localArrayList3 = zza.zzc(paramParcel, n, AppContentActionEntity.CREATOR);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, n);
        break;
      case 2: 
        localArrayList2 = zza.zzc(paramParcel, n, AppContentAnnotationEntity.CREATOR);
        break;
      case 3: 
        localArrayList1 = zza.zzc(paramParcel, n, AppContentConditionEntity.CREATOR);
        break;
      case 4: 
        str6 = zza.zzp(paramParcel, n);
        break;
      case 5: 
        j = zza.zzg(paramParcel, n);
        break;
      case 6: 
        str5 = zza.zzp(paramParcel, n);
        break;
      case 7: 
        localBundle = zza.zzr(paramParcel, n);
        break;
      case 10: 
        str4 = zza.zzp(paramParcel, n);
        break;
      case 11: 
        str3 = zza.zzp(paramParcel, n);
        break;
      case 12: 
        i = zza.zzg(paramParcel, n);
        break;
      case 13: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 14: 
        str1 = zza.zzp(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza("Overread allowed size end=" + m, paramParcel);
    }
    return new AppContentCardEntity(k, localArrayList3, localArrayList2, localArrayList1, str6, j, str5, localBundle, str4, str3, i, str2, str1);
  }
  
  public AppContentCardEntity[] zzgj(int paramInt)
  {
    return new AppContentCardEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentCardEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */