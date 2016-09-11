package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public class zzc
  implements Parcelable.Creator<DataReadResult>
{
  static void zza(DataReadResult paramDataReadResult, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzd(paramParcel, 1, paramDataReadResult.zzvl(), false);
    zzb.zzc(paramParcel, 1000, paramDataReadResult.getVersionCode());
    zzb.zza(paramParcel, 2, paramDataReadResult.getStatus(), paramInt, false);
    zzb.zzd(paramParcel, 3, paramDataReadResult.zzvk(), false);
    zzb.zzc(paramParcel, 5, paramDataReadResult.zzvj());
    zzb.zzc(paramParcel, 6, paramDataReadResult.zzul(), false);
    zzb.zzc(paramParcel, 7, paramDataReadResult.zzvm(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public DataReadResult zzdP(Parcel paramParcel)
  {
    int i = 0;
    ArrayList localArrayList1 = null;
    int k = zza.zzau(paramParcel);
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    ArrayList localArrayList2 = null;
    Status localStatus = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        zza.zza(paramParcel, m, localArrayList3, getClass().getClassLoader());
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        localStatus = (Status)zza.zza(paramParcel, m, Status.CREATOR);
        break;
      case 3: 
        zza.zza(paramParcel, m, localArrayList4, getClass().getClassLoader());
        break;
      case 5: 
        i = zza.zzg(paramParcel, m);
        break;
      case 6: 
        localArrayList2 = zza.zzc(paramParcel, m, DataSource.CREATOR);
        break;
      case 7: 
        localArrayList1 = zza.zzc(paramParcel, m, DataType.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new DataReadResult(j, localArrayList3, localStatus, localArrayList4, i, localArrayList2, localArrayList1);
  }
  
  public DataReadResult[] zzfQ(int paramInt)
  {
    return new DataReadResult[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\result\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */