package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<AuthAccountRequest>
{
  static void zza(AuthAccountRequest paramAuthAccountRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramAuthAccountRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramAuthAccountRequest.zzakA, false);
    zzb.zza(paramParcel, 3, paramAuthAccountRequest.zzafT, paramInt, false);
    zzb.zza(paramParcel, 4, paramAuthAccountRequest.zzakB, false);
    zzb.zza(paramParcel, 5, paramAuthAccountRequest.zzakC, false);
    zzb.zzI(paramParcel, i);
  }
  
  public AuthAccountRequest zzam(Parcel paramParcel)
  {
    Integer localInteger1 = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    Integer localInteger2 = null;
    Scope[] arrayOfScope = null;
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
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localIBinder = zza.zzq(paramParcel, k);
        break;
      case 3: 
        arrayOfScope = (Scope[])zza.zzb(paramParcel, k, Scope.CREATOR);
        break;
      case 4: 
        localInteger2 = zza.zzh(paramParcel, k);
        break;
      case 5: 
        localInteger1 = zza.zzh(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new AuthAccountRequest(i, localIBinder, arrayOfScope, localInteger2, localInteger1);
  }
  
  public AuthAccountRequest[] zzbP(int paramInt)
  {
    return new AuthAccountRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */