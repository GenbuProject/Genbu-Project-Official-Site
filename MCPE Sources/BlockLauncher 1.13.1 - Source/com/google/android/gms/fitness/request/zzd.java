package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

public class zzd
  implements Parcelable.Creator<DataDeleteRequest>
{
  static void zza(DataDeleteRequest paramDataDeleteRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramDataDeleteRequest.zzlO());
    zzb.zzc(paramParcel, 1000, paramDataDeleteRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramDataDeleteRequest.zzud());
    zzb.zzc(paramParcel, 3, paramDataDeleteRequest.getDataSources(), false);
    zzb.zzc(paramParcel, 4, paramDataDeleteRequest.getDataTypes(), false);
    zzb.zzc(paramParcel, 5, paramDataDeleteRequest.getSessions(), false);
    zzb.zza(paramParcel, 6, paramDataDeleteRequest.zzuL());
    zzb.zza(paramParcel, 7, paramDataDeleteRequest.zzuM());
    zzb.zza(paramParcel, 8, paramDataDeleteRequest.getCallbackBinder(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public DataDeleteRequest zzdj(Parcel paramParcel)
  {
    long l1 = 0L;
    boolean bool1 = false;
    IBinder localIBinder = null;
    int j = zza.zzau(paramParcel);
    boolean bool2 = false;
    ArrayList localArrayList1 = null;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList3 = null;
    long l2 = 0L;
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
        l2 = zza.zzi(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        l1 = zza.zzi(paramParcel, k);
        break;
      case 3: 
        localArrayList3 = zza.zzc(paramParcel, k, DataSource.CREATOR);
        break;
      case 4: 
        localArrayList2 = zza.zzc(paramParcel, k, DataType.CREATOR);
        break;
      case 5: 
        localArrayList1 = zza.zzc(paramParcel, k, Session.CREATOR);
        break;
      case 6: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 7: 
        bool1 = zza.zzc(paramParcel, k);
        break;
      case 8: 
        localIBinder = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new DataDeleteRequest(i, l2, l1, localArrayList3, localArrayList2, localArrayList1, bool2, bool1, localIBinder);
  }
  
  public DataDeleteRequest[] zzfj(int paramInt)
  {
    return new DataDeleteRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */