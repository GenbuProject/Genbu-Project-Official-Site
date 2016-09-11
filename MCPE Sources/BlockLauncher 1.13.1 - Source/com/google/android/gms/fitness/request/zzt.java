package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzt
  implements Parcelable.Creator<ReadRawRequest>
{
  static void zza(ReadRawRequest paramReadRawRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramReadRawRequest.getCallbackBinder(), false);
    zzb.zzc(paramParcel, 1000, paramReadRawRequest.getVersionCode());
    zzb.zzc(paramParcel, 3, paramReadRawRequest.zzuW(), false);
    zzb.zza(paramParcel, 4, paramReadRawRequest.zzuQ());
    zzb.zza(paramParcel, 5, paramReadRawRequest.zzuP());
    zzb.zzI(paramParcel, paramInt);
  }
  
  public ReadRawRequest zzdy(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    boolean bool1 = false;
    int j = zza.zzau(paramParcel);
    boolean bool2 = false;
    IBinder localIBinder = null;
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
        localIBinder = zza.zzq(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 3: 
        localArrayList = zza.zzc(paramParcel, k, DataSourceQueryParams.CREATOR);
        break;
      case 4: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 5: 
        bool1 = zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ReadRawRequest(i, localIBinder, localArrayList, bool2, bool1);
  }
  
  public ReadRawRequest[] zzfy(int paramInt)
  {
    return new ReadRawRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */