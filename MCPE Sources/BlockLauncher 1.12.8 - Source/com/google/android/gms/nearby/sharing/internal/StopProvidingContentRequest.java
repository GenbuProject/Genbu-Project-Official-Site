package com.google.android.gms.nearby.sharing.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class StopProvidingContentRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<StopProvidingContentRequest> CREATOR = new zzj();
  final int versionCode;
  public long zzbdn;
  public zzc zzbdo;
  
  StopProvidingContentRequest()
  {
    this.versionCode = 1;
  }
  
  StopProvidingContentRequest(int paramInt, long paramLong, IBinder paramIBinder)
  {
    this.versionCode = paramInt;
    this.zzbdn = paramLong;
    this.zzbdo = zzc.zza.zzdH(paramIBinder);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbdo.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\sharing\internal\StopProvidingContentRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */