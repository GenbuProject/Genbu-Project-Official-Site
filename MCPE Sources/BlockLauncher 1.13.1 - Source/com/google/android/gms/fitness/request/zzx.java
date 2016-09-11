package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

public class zzx
  implements Parcelable.Creator<SessionInsertRequest>
{
  static void zza(SessionInsertRequest paramSessionInsertRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramSessionInsertRequest.getSession(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramSessionInsertRequest.getVersionCode());
    zzb.zzc(paramParcel, 2, paramSessionInsertRequest.getDataSets(), false);
    zzb.zzc(paramParcel, 3, paramSessionInsertRequest.getAggregateDataPoints(), false);
    zzb.zza(paramParcel, 4, paramSessionInsertRequest.getCallbackBinder(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public SessionInsertRequest zzdC(Parcel paramParcel)
  {
    IBinder localIBinder = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    ArrayList localArrayList1 = null;
    ArrayList localArrayList2 = null;
    Session localSession = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localSession = (Session)zza.zza(paramParcel, k, Session.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localArrayList2 = zza.zzc(paramParcel, k, DataSet.CREATOR);
        break;
      case 3: 
        localArrayList1 = zza.zzc(paramParcel, k, DataPoint.CREATOR);
        break;
      case 4: 
        localIBinder = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new SessionInsertRequest(i, localSession, localArrayList2, localArrayList1, localIBinder);
  }
  
  public SessionInsertRequest[] zzfD(int paramInt)
  {
    return new SessionInsertRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */