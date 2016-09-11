package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpj.zza;

public class ListClaimedBleDevicesRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ListClaimedBleDevicesRequest> CREATOR = new zzr();
  private final int mVersionCode;
  private final zzpj zzaBc;
  
  ListClaimedBleDevicesRequest(int paramInt, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzaBc = zzpj.zza.zzbT(paramIBinder);
  }
  
  public ListClaimedBleDevicesRequest(zzpj paramzzpj)
  {
    this.mVersionCode = 2;
    this.zzaBc = paramzzpj;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    return this.zzaBc.asBinder();
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzr.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\ListClaimedBleDevicesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */