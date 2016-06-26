package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzh
  implements Parcelable.Creator<AdResponseParcel>
{
  static void zza(AdResponseParcel paramAdResponseParcel, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramAdResponseParcel.versionCode);
    zzb.zza(paramParcel, 2, paramAdResponseParcel.zzEF, false);
    zzb.zza(paramParcel, 3, paramAdResponseParcel.body, false);
    zzb.zzb(paramParcel, 4, paramAdResponseParcel.zzBQ, false);
    zzb.zzc(paramParcel, 5, paramAdResponseParcel.errorCode);
    zzb.zzb(paramParcel, 6, paramAdResponseParcel.zzBR, false);
    zzb.zza(paramParcel, 7, paramAdResponseParcel.zzHS);
    zzb.zza(paramParcel, 8, paramAdResponseParcel.zzHT);
    zzb.zza(paramParcel, 9, paramAdResponseParcel.zzHU);
    zzb.zzb(paramParcel, 10, paramAdResponseParcel.zzHV, false);
    zzb.zza(paramParcel, 11, paramAdResponseParcel.zzBU);
    zzb.zzc(paramParcel, 12, paramAdResponseParcel.orientation);
    zzb.zza(paramParcel, 13, paramAdResponseParcel.zzHW, false);
    zzb.zza(paramParcel, 14, paramAdResponseParcel.zzHX);
    zzb.zza(paramParcel, 15, paramAdResponseParcel.zzHY, false);
    zzb.zza(paramParcel, 19, paramAdResponseParcel.zzIa, false);
    zzb.zza(paramParcel, 18, paramAdResponseParcel.zzHZ);
    zzb.zza(paramParcel, 21, paramAdResponseParcel.zzIb, false);
    zzb.zza(paramParcel, 23, paramAdResponseParcel.zzuk);
    zzb.zza(paramParcel, 22, paramAdResponseParcel.zzIc);
    zzb.zza(paramParcel, 25, paramAdResponseParcel.zzId);
    zzb.zza(paramParcel, 24, paramAdResponseParcel.zzHB);
    zzb.zzc(paramParcel, 27, paramAdResponseParcel.zzIf);
    zzb.zza(paramParcel, 26, paramAdResponseParcel.zzIe);
    zzb.zza(paramParcel, 29, paramAdResponseParcel.zzIh, false);
    zzb.zza(paramParcel, 28, paramAdResponseParcel.zzIg, paramInt, false);
    zzb.zza(paramParcel, 31, paramAdResponseParcel.zzul);
    zzb.zza(paramParcel, 30, paramAdResponseParcel.zzIi, false);
    zzb.zzb(paramParcel, 34, paramAdResponseParcel.zzIk, false);
    zzb.zzb(paramParcel, 35, paramAdResponseParcel.zzIl, false);
    zzb.zza(paramParcel, 32, paramAdResponseParcel.zzum);
    zzb.zza(paramParcel, 33, paramAdResponseParcel.zzIj, paramInt, false);
    zzb.zza(paramParcel, 36, paramAdResponseParcel.zzIm);
    zzb.zzI(paramParcel, i);
  }
  
  public AdResponseParcel[] zzH(int paramInt)
  {
    return new AdResponseParcel[paramInt];
  }
  
  public AdResponseParcel zzj(Parcel paramParcel)
  {
    int n = zza.zzau(paramParcel);
    int m = 0;
    String str8 = null;
    String str7 = null;
    ArrayList localArrayList5 = null;
    int k = 0;
    ArrayList localArrayList4 = null;
    long l4 = 0L;
    boolean bool10 = false;
    long l3 = 0L;
    ArrayList localArrayList3 = null;
    long l2 = 0L;
    int j = 0;
    String str6 = null;
    long l1 = 0L;
    String str5 = null;
    boolean bool9 = false;
    String str4 = null;
    String str3 = null;
    boolean bool8 = false;
    boolean bool7 = false;
    boolean bool6 = false;
    boolean bool5 = false;
    boolean bool4 = false;
    int i = 0;
    LargeParcelTeleporter localLargeParcelTeleporter = null;
    String str2 = null;
    String str1 = null;
    boolean bool3 = false;
    boolean bool2 = false;
    RewardItemParcel localRewardItemParcel = null;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList1 = null;
    boolean bool1 = false;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = zza.zzat(paramParcel);
      switch (zza.zzca(i1))
      {
      case 16: 
      case 17: 
      case 20: 
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        str8 = zza.zzp(paramParcel, i1);
        break;
      case 3: 
        str7 = zza.zzp(paramParcel, i1);
        break;
      case 4: 
        localArrayList5 = zza.zzD(paramParcel, i1);
        break;
      case 5: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 6: 
        localArrayList4 = zza.zzD(paramParcel, i1);
        break;
      case 7: 
        l4 = zza.zzi(paramParcel, i1);
        break;
      case 8: 
        bool10 = zza.zzc(paramParcel, i1);
        break;
      case 9: 
        l3 = zza.zzi(paramParcel, i1);
        break;
      case 10: 
        localArrayList3 = zza.zzD(paramParcel, i1);
        break;
      case 11: 
        l2 = zza.zzi(paramParcel, i1);
        break;
      case 12: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 13: 
        str6 = zza.zzp(paramParcel, i1);
        break;
      case 14: 
        l1 = zza.zzi(paramParcel, i1);
        break;
      case 15: 
        str5 = zza.zzp(paramParcel, i1);
        break;
      case 19: 
        str4 = zza.zzp(paramParcel, i1);
        break;
      case 18: 
        bool9 = zza.zzc(paramParcel, i1);
        break;
      case 21: 
        str3 = zza.zzp(paramParcel, i1);
        break;
      case 23: 
        bool7 = zza.zzc(paramParcel, i1);
        break;
      case 22: 
        bool8 = zza.zzc(paramParcel, i1);
        break;
      case 25: 
        bool5 = zza.zzc(paramParcel, i1);
        break;
      case 24: 
        bool6 = zza.zzc(paramParcel, i1);
        break;
      case 27: 
        i = zza.zzg(paramParcel, i1);
        break;
      case 26: 
        bool4 = zza.zzc(paramParcel, i1);
        break;
      case 29: 
        str2 = zza.zzp(paramParcel, i1);
        break;
      case 28: 
        localLargeParcelTeleporter = (LargeParcelTeleporter)zza.zza(paramParcel, i1, LargeParcelTeleporter.CREATOR);
        break;
      case 31: 
        bool3 = zza.zzc(paramParcel, i1);
        break;
      case 30: 
        str1 = zza.zzp(paramParcel, i1);
        break;
      case 34: 
        localArrayList2 = zza.zzD(paramParcel, i1);
        break;
      case 35: 
        localArrayList1 = zza.zzD(paramParcel, i1);
        break;
      case 32: 
        bool2 = zza.zzc(paramParcel, i1);
        break;
      case 33: 
        localRewardItemParcel = (RewardItemParcel)zza.zza(paramParcel, i1, RewardItemParcel.CREATOR);
        break;
      case 36: 
        bool1 = zza.zzc(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zza.zza("Overread allowed size end=" + n, paramParcel);
    }
    return new AdResponseParcel(m, str8, str7, localArrayList5, k, localArrayList4, l4, bool10, l3, localArrayList3, l2, j, str6, l1, str5, bool9, str4, str3, bool8, bool7, bool6, bool5, bool4, i, localLargeParcelTeleporter, str2, str1, bool3, bool2, localRewardItemParcel, localArrayList2, localArrayList1, bool1);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\request\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */