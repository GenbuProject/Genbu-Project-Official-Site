package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public class zzy
  implements Parcelable.Creator<SessionReadRequest>
{
  static void zza(SessionReadRequest paramSessionReadRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zza(paramParcel, 1, paramSessionReadRequest.getSessionName(), false);
    zzb.zzc(paramParcel, 1000, paramSessionReadRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramSessionReadRequest.getSessionId(), false);
    zzb.zza(paramParcel, 3, paramSessionReadRequest.zzlO());
    zzb.zza(paramParcel, 4, paramSessionReadRequest.zzud());
    zzb.zzc(paramParcel, 5, paramSessionReadRequest.getDataTypes(), false);
    zzb.zzc(paramParcel, 6, paramSessionReadRequest.getDataSources(), false);
    zzb.zza(paramParcel, 7, paramSessionReadRequest.zzve());
    zzb.zza(paramParcel, 8, paramSessionReadRequest.zzuP());
    zzb.zzb(paramParcel, 9, paramSessionReadRequest.getExcludedPackages(), false);
    zzb.zza(paramParcel, 10, paramSessionReadRequest.getCallbackBinder(), false);
    zzb.zzI(paramParcel, paramInt);
  }
  
  public SessionReadRequest zzdD(Parcel paramParcel)
  {
    int j = zza.zzau(paramParcel);
    int i = 0;
    String str2 = null;
    String str1 = null;
    long l2 = 0L;
    long l1 = 0L;
    ArrayList localArrayList3 = null;
    ArrayList localArrayList2 = null;
    boolean bool2 = false;
    boolean bool1 = false;
    ArrayList localArrayList1 = null;
    IBinder localIBinder = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        l2 = zza.zzi(paramParcel, k);
        break;
      case 4: 
        l1 = zza.zzi(paramParcel, k);
        break;
      case 5: 
        localArrayList3 = zza.zzc(paramParcel, k, DataType.CREATOR);
        break;
      case 6: 
        localArrayList2 = zza.zzc(paramParcel, k, DataSource.CREATOR);
        break;
      case 7: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 8: 
        bool1 = zza.zzc(paramParcel, k);
        break;
      case 9: 
        localArrayList1 = zza.zzD(paramParcel, k);
        break;
      case 10: 
        localIBinder = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new SessionReadRequest(i, str2, str1, l2, l1, localArrayList3, localArrayList2, bool2, bool1, localArrayList1, localIBinder);
  }
  
  public SessionReadRequest[] zzfE(int paramInt)
  {
    return new SessionReadRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */