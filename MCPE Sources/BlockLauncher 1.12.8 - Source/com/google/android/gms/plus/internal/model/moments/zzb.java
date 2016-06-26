package com.google.android.gms.plus.internal.model.moments;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import java.util.HashSet;
import java.util.Set;

public class zzb
  implements Parcelable.Creator<MomentEntity>
{
  static void zza(MomentEntity paramMomentEntity, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzav(paramParcel);
    Set localSet = paramMomentEntity.zzbeN;
    if (localSet.contains(Integer.valueOf(1))) {
      com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramMomentEntity.mVersionCode);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramMomentEntity.zzyv, true);
    }
    if (localSet.contains(Integer.valueOf(4))) {
      com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramMomentEntity.zzbfI, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(5))) {
      com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 5, paramMomentEntity.zzbfA, true);
    }
    if (localSet.contains(Integer.valueOf(6))) {
      com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 6, paramMomentEntity.zzbfJ, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(7))) {
      com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 7, paramMomentEntity.zzJN, true);
    }
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public MomentEntity zzgD(Parcel paramParcel)
  {
    String str1 = null;
    int j = zza.zzau(paramParcel);
    HashSet localHashSet = new HashSet();
    int i = 0;
    ItemScopeEntity localItemScopeEntity1 = null;
    String str2 = null;
    ItemScopeEntity localItemScopeEntity2 = null;
    String str3 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      case 3: 
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        i = zza.zzg(paramParcel, k);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        str3 = zza.zzp(paramParcel, k);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 4: 
        localItemScopeEntity2 = (ItemScopeEntity)zza.zza(paramParcel, k, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(4));
        break;
      case 5: 
        str2 = zza.zzp(paramParcel, k);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6: 
        localItemScopeEntity1 = (ItemScopeEntity)zza.zza(paramParcel, k, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(6));
        break;
      case 7: 
        str1 = zza.zzp(paramParcel, k);
        localHashSet.add(Integer.valueOf(7));
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new MomentEntity(localHashSet, i, str3, localItemScopeEntity2, str2, localItemScopeEntity1, str1);
  }
  
  public MomentEntity[] zzjK(int paramInt)
  {
    return new MomentEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\plus\internal\model\moments\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */