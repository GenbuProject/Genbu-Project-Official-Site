package com.google.android.gms.plus.internal.model.moments;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class zza
  implements Parcelable.Creator<ItemScopeEntity>
{
  static void zza(ItemScopeEntity paramItemScopeEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    Set localSet = paramItemScopeEntity.zzbeN;
    if (localSet.contains(Integer.valueOf(1))) {
      zzb.zzc(paramParcel, 1, paramItemScopeEntity.mVersionCode);
    }
    if (localSet.contains(Integer.valueOf(2))) {
      zzb.zza(paramParcel, 2, paramItemScopeEntity.zzbeO, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(3))) {
      zzb.zzb(paramParcel, 3, paramItemScopeEntity.zzbeP, true);
    }
    if (localSet.contains(Integer.valueOf(4))) {
      zzb.zza(paramParcel, 4, paramItemScopeEntity.zzbeQ, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(5))) {
      zzb.zza(paramParcel, 5, paramItemScopeEntity.zzbeR, true);
    }
    if (localSet.contains(Integer.valueOf(6))) {
      zzb.zza(paramParcel, 6, paramItemScopeEntity.zzbeS, true);
    }
    if (localSet.contains(Integer.valueOf(7))) {
      zzb.zza(paramParcel, 7, paramItemScopeEntity.zzbeT, true);
    }
    if (localSet.contains(Integer.valueOf(8))) {
      zzb.zzc(paramParcel, 8, paramItemScopeEntity.zzbeU, true);
    }
    if (localSet.contains(Integer.valueOf(9))) {
      zzb.zzc(paramParcel, 9, paramItemScopeEntity.zzbeV);
    }
    if (localSet.contains(Integer.valueOf(10))) {
      zzb.zzc(paramParcel, 10, paramItemScopeEntity.zzbeW, true);
    }
    if (localSet.contains(Integer.valueOf(11))) {
      zzb.zza(paramParcel, 11, paramItemScopeEntity.zzbeX, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(12))) {
      zzb.zzc(paramParcel, 12, paramItemScopeEntity.zzbeY, true);
    }
    if (localSet.contains(Integer.valueOf(13))) {
      zzb.zza(paramParcel, 13, paramItemScopeEntity.zzbeZ, true);
    }
    if (localSet.contains(Integer.valueOf(14))) {
      zzb.zza(paramParcel, 14, paramItemScopeEntity.zzbfa, true);
    }
    if (localSet.contains(Integer.valueOf(15))) {
      zzb.zza(paramParcel, 15, paramItemScopeEntity.zzbfb, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(17))) {
      zzb.zza(paramParcel, 17, paramItemScopeEntity.zzbfd, true);
    }
    if (localSet.contains(Integer.valueOf(16))) {
      zzb.zza(paramParcel, 16, paramItemScopeEntity.zzbfc, true);
    }
    if (localSet.contains(Integer.valueOf(19))) {
      zzb.zzc(paramParcel, 19, paramItemScopeEntity.zzbfe, true);
    }
    if (localSet.contains(Integer.valueOf(18))) {
      zzb.zza(paramParcel, 18, paramItemScopeEntity.zztZ, true);
    }
    if (localSet.contains(Integer.valueOf(21))) {
      zzb.zza(paramParcel, 21, paramItemScopeEntity.zzbfg, true);
    }
    if (localSet.contains(Integer.valueOf(20))) {
      zzb.zza(paramParcel, 20, paramItemScopeEntity.zzbff, true);
    }
    if (localSet.contains(Integer.valueOf(23))) {
      zzb.zza(paramParcel, 23, paramItemScopeEntity.zzaxl, true);
    }
    if (localSet.contains(Integer.valueOf(22))) {
      zzb.zza(paramParcel, 22, paramItemScopeEntity.zzbfh, true);
    }
    if (localSet.contains(Integer.valueOf(25))) {
      zzb.zza(paramParcel, 25, paramItemScopeEntity.zzbfj, true);
    }
    if (localSet.contains(Integer.valueOf(24))) {
      zzb.zza(paramParcel, 24, paramItemScopeEntity.zzbfi, true);
    }
    if (localSet.contains(Integer.valueOf(27))) {
      zzb.zza(paramParcel, 27, paramItemScopeEntity.zzbfl, true);
    }
    if (localSet.contains(Integer.valueOf(26))) {
      zzb.zza(paramParcel, 26, paramItemScopeEntity.zzbfk, true);
    }
    if (localSet.contains(Integer.valueOf(29))) {
      zzb.zza(paramParcel, 29, paramItemScopeEntity.zzbfn, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(28))) {
      zzb.zza(paramParcel, 28, paramItemScopeEntity.zzbfm, true);
    }
    if (localSet.contains(Integer.valueOf(31))) {
      zzb.zza(paramParcel, 31, paramItemScopeEntity.zzbfp, true);
    }
    if (localSet.contains(Integer.valueOf(30))) {
      zzb.zza(paramParcel, 30, paramItemScopeEntity.zzbfo, true);
    }
    if (localSet.contains(Integer.valueOf(34))) {
      zzb.zza(paramParcel, 34, paramItemScopeEntity.zzbfr, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(32))) {
      zzb.zza(paramParcel, 32, paramItemScopeEntity.zzyv, true);
    }
    if (localSet.contains(Integer.valueOf(33))) {
      zzb.zza(paramParcel, 33, paramItemScopeEntity.zzbfq, true);
    }
    if (localSet.contains(Integer.valueOf(38))) {
      zzb.zza(paramParcel, 38, paramItemScopeEntity.zzaNG);
    }
    if (localSet.contains(Integer.valueOf(39))) {
      zzb.zza(paramParcel, 39, paramItemScopeEntity.mName, true);
    }
    if (localSet.contains(Integer.valueOf(36))) {
      zzb.zza(paramParcel, 36, paramItemScopeEntity.zzaNF);
    }
    if (localSet.contains(Integer.valueOf(37))) {
      zzb.zza(paramParcel, 37, paramItemScopeEntity.zzbfs, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(42))) {
      zzb.zza(paramParcel, 42, paramItemScopeEntity.zzbfv, true);
    }
    if (localSet.contains(Integer.valueOf(43))) {
      zzb.zza(paramParcel, 43, paramItemScopeEntity.zzbfw, true);
    }
    if (localSet.contains(Integer.valueOf(40))) {
      zzb.zza(paramParcel, 40, paramItemScopeEntity.zzbft, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(41))) {
      zzb.zzc(paramParcel, 41, paramItemScopeEntity.zzbfu, true);
    }
    if (localSet.contains(Integer.valueOf(46))) {
      zzb.zza(paramParcel, 46, paramItemScopeEntity.zzbfz, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(47))) {
      zzb.zza(paramParcel, 47, paramItemScopeEntity.zzbfA, true);
    }
    if (localSet.contains(Integer.valueOf(44))) {
      zzb.zza(paramParcel, 44, paramItemScopeEntity.zzbfx, true);
    }
    if (localSet.contains(Integer.valueOf(45))) {
      zzb.zza(paramParcel, 45, paramItemScopeEntity.zzbfy, true);
    }
    if (localSet.contains(Integer.valueOf(51))) {
      zzb.zza(paramParcel, 51, paramItemScopeEntity.zzbfE, true);
    }
    if (localSet.contains(Integer.valueOf(50))) {
      zzb.zza(paramParcel, 50, paramItemScopeEntity.zzbfD, paramInt, true);
    }
    if (localSet.contains(Integer.valueOf(49))) {
      zzb.zza(paramParcel, 49, paramItemScopeEntity.zzbfC, true);
    }
    if (localSet.contains(Integer.valueOf(48))) {
      zzb.zza(paramParcel, 48, paramItemScopeEntity.zzbfB, true);
    }
    if (localSet.contains(Integer.valueOf(55))) {
      zzb.zza(paramParcel, 55, paramItemScopeEntity.zzbfG, true);
    }
    if (localSet.contains(Integer.valueOf(54))) {
      zzb.zza(paramParcel, 54, paramItemScopeEntity.zzF, true);
    }
    if (localSet.contains(Integer.valueOf(53))) {
      zzb.zza(paramParcel, 53, paramItemScopeEntity.zzJN, true);
    }
    if (localSet.contains(Integer.valueOf(52))) {
      zzb.zza(paramParcel, 52, paramItemScopeEntity.zzbfF, true);
    }
    if (localSet.contains(Integer.valueOf(56))) {
      zzb.zza(paramParcel, 56, paramItemScopeEntity.zzbfH, true);
    }
    zzb.zzI(paramParcel, i);
  }
  
  public ItemScopeEntity zzgC(Parcel paramParcel)
  {
    int k = com.google.android.gms.common.internal.safeparcel.zza.zzau(paramParcel);
    HashSet localHashSet = new HashSet();
    int j = 0;
    ItemScopeEntity localItemScopeEntity10 = null;
    ArrayList localArrayList6 = null;
    ItemScopeEntity localItemScopeEntity9 = null;
    String str35 = null;
    String str34 = null;
    String str33 = null;
    ArrayList localArrayList5 = null;
    int i = 0;
    ArrayList localArrayList4 = null;
    ItemScopeEntity localItemScopeEntity8 = null;
    ArrayList localArrayList3 = null;
    String str32 = null;
    String str31 = null;
    ItemScopeEntity localItemScopeEntity7 = null;
    String str30 = null;
    String str29 = null;
    String str28 = null;
    ArrayList localArrayList2 = null;
    String str27 = null;
    String str26 = null;
    String str25 = null;
    String str24 = null;
    String str23 = null;
    String str22 = null;
    String str21 = null;
    String str20 = null;
    String str19 = null;
    ItemScopeEntity localItemScopeEntity6 = null;
    String str18 = null;
    String str17 = null;
    String str16 = null;
    String str15 = null;
    ItemScopeEntity localItemScopeEntity5 = null;
    double d2 = 0.0D;
    ItemScopeEntity localItemScopeEntity4 = null;
    double d1 = 0.0D;
    String str14 = null;
    ItemScopeEntity localItemScopeEntity3 = null;
    ArrayList localArrayList1 = null;
    String str13 = null;
    String str12 = null;
    String str11 = null;
    String str10 = null;
    ItemScopeEntity localItemScopeEntity2 = null;
    String str9 = null;
    String str8 = null;
    String str7 = null;
    ItemScopeEntity localItemScopeEntity1 = null;
    String str6 = null;
    String str5 = null;
    String str4 = null;
    String str3 = null;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzat(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(m))
      {
      case 35: 
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        localItemScopeEntity10 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3: 
        localArrayList6 = com.google.android.gms.common.internal.safeparcel.zza.zzD(paramParcel, m);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4: 
        localItemScopeEntity9 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(4));
        break;
      case 5: 
        str35 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6: 
        str34 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(6));
        break;
      case 7: 
        str33 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(7));
        break;
      case 8: 
        localArrayList5 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(8));
        break;
      case 9: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        localHashSet.add(Integer.valueOf(9));
        break;
      case 10: 
        localArrayList4 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(10));
        break;
      case 11: 
        localItemScopeEntity8 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(11));
        break;
      case 12: 
        localArrayList3 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(12));
        break;
      case 13: 
        str32 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(13));
        break;
      case 14: 
        str31 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(14));
        break;
      case 15: 
        localItemScopeEntity7 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(15));
        break;
      case 17: 
        str29 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(17));
        break;
      case 16: 
        str30 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(16));
        break;
      case 19: 
        localArrayList2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(19));
        break;
      case 18: 
        str28 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(18));
        break;
      case 21: 
        str26 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(21));
        break;
      case 20: 
        str27 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(20));
        break;
      case 23: 
        str24 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(23));
        break;
      case 22: 
        str25 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(22));
        break;
      case 25: 
        str22 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(25));
        break;
      case 24: 
        str23 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(24));
        break;
      case 27: 
        str20 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(27));
        break;
      case 26: 
        str21 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(26));
        break;
      case 29: 
        localItemScopeEntity6 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(29));
        break;
      case 28: 
        str19 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(28));
        break;
      case 31: 
        str17 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(31));
        break;
      case 30: 
        str18 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(30));
        break;
      case 34: 
        localItemScopeEntity5 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(34));
        break;
      case 32: 
        str16 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(32));
        break;
      case 33: 
        str15 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(33));
        break;
      case 38: 
        d1 = com.google.android.gms.common.internal.safeparcel.zza.zzn(paramParcel, m);
        localHashSet.add(Integer.valueOf(38));
        break;
      case 39: 
        str14 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(39));
        break;
      case 36: 
        d2 = com.google.android.gms.common.internal.safeparcel.zza.zzn(paramParcel, m);
        localHashSet.add(Integer.valueOf(36));
        break;
      case 37: 
        localItemScopeEntity4 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(37));
        break;
      case 42: 
        str13 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(42));
        break;
      case 43: 
        str12 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(43));
        break;
      case 40: 
        localItemScopeEntity3 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(40));
        break;
      case 41: 
        localArrayList1 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(41));
        break;
      case 46: 
        localItemScopeEntity2 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(46));
        break;
      case 47: 
        str9 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(47));
        break;
      case 44: 
        str11 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(44));
        break;
      case 45: 
        str10 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(45));
        break;
      case 51: 
        str6 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(51));
        break;
      case 50: 
        localItemScopeEntity1 = (ItemScopeEntity)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ItemScopeEntity.CREATOR);
        localHashSet.add(Integer.valueOf(50));
        break;
      case 49: 
        str7 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(49));
        break;
      case 48: 
        str8 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(48));
        break;
      case 55: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(55));
        break;
      case 54: 
        str3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(54));
        break;
      case 53: 
        str4 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(53));
        break;
      case 52: 
        str5 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(52));
        break;
      case 56: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        localHashSet.add(Integer.valueOf(56));
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new ItemScopeEntity(localHashSet, j, localItemScopeEntity10, localArrayList6, localItemScopeEntity9, str35, str34, str33, localArrayList5, i, localArrayList4, localItemScopeEntity8, localArrayList3, str32, str31, localItemScopeEntity7, str30, str29, str28, localArrayList2, str27, str26, str25, str24, str23, str22, str21, str20, str19, localItemScopeEntity6, str18, str17, str16, str15, localItemScopeEntity5, d2, localItemScopeEntity4, d1, str14, localItemScopeEntity3, localArrayList1, str13, str12, str11, str10, localItemScopeEntity2, str9, str8, str7, localItemScopeEntity1, str6, str5, str4, str3, str2, str1);
  }
  
  public ItemScopeEntity[] zzjJ(int paramInt)
  {
    return new ItemScopeEntity[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\plus\internal\model\moments\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */