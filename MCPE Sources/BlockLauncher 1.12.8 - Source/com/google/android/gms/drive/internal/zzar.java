package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;

public class zzar
  implements Parcelable.Creator<LoadRealtimeRequest>
{
  static void zza(LoadRealtimeRequest paramLoadRealtimeRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramLoadRealtimeRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramLoadRealtimeRequest.zzaoz, paramInt, false);
    zzb.zza(paramParcel, 3, paramLoadRealtimeRequest.zzarQ);
    zzb.zzb(paramParcel, 4, paramLoadRealtimeRequest.zzarR, false);
    zzb.zza(paramParcel, 5, paramLoadRealtimeRequest.zzarS);
    zzb.zza(paramParcel, 6, paramLoadRealtimeRequest.zzarT, paramInt, false);
    zzb.zza(paramParcel, 7, paramLoadRealtimeRequest.zzarU, false);
    zzb.zzI(paramParcel, i);
  }
  
  public LoadRealtimeRequest zzbx(Parcel paramParcel)
  {
    boolean bool1 = false;
    String str = null;
    int j = zza.zzau(paramParcel);
    DataHolder localDataHolder = null;
    ArrayList localArrayList = null;
    boolean bool2 = false;
    DriveId localDriveId = null;
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
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localDriveId = (DriveId)zza.zza(paramParcel, k, DriveId.CREATOR);
        break;
      case 3: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 4: 
        localArrayList = zza.zzD(paramParcel, k);
        break;
      case 5: 
        bool1 = zza.zzc(paramParcel, k);
        break;
      case 6: 
        localDataHolder = (DataHolder)zza.zza(paramParcel, k, DataHolder.CREATOR);
        break;
      case 7: 
        str = zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new LoadRealtimeRequest(i, localDriveId, bool2, localArrayList, bool1, localDataHolder, str);
  }
  
  public LoadRealtimeRequest[] zzds(int paramInt)
  {
    return new LoadRealtimeRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */