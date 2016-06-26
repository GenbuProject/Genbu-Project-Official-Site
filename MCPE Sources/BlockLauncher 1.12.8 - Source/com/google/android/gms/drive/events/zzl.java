package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzl
  implements Parcelable.Creator<QueryResultEventParcelable>
{
  static void zza(QueryResultEventParcelable paramQueryResultEventParcelable, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramQueryResultEventParcelable.mVersionCode);
    zzb.zza(paramParcel, 2, paramQueryResultEventParcelable.zzahi, paramInt, false);
    zzb.zza(paramParcel, 3, paramQueryResultEventParcelable.zzapQ);
    zzb.zzc(paramParcel, 4, paramQueryResultEventParcelable.zzapR);
    zzb.zzI(paramParcel, i);
  }
  
  public QueryResultEventParcelable zzaT(Parcel paramParcel)
  {
    int i = 0;
    int k = zza.zzau(paramParcel);
    DataHolder localDataHolder = null;
    boolean bool = false;
    int j = 0;
    if (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
      }
      for (;;)
      {
        break;
        j = zza.zzg(paramParcel, m);
        continue;
        localDataHolder = (DataHolder)zza.zza(paramParcel, m, DataHolder.CREATOR);
        continue;
        bool = zza.zzc(paramParcel, m);
        continue;
        i = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new QueryResultEventParcelable(j, localDataHolder, bool, i);
  }
  
  public QueryResultEventParcelable[] zzcI(int paramInt)
  {
    return new QueryResultEventParcelable[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */