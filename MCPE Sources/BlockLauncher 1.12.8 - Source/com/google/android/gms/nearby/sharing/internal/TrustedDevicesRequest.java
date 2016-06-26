package com.google.android.gms.nearby.sharing.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class TrustedDevicesRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<TrustedDevicesRequest> CREATOR = new zzk();
  final int versionCode;
  public zzc zzbdo;
  public String zzbdq;
  public byte[] zzbdr;
  
  TrustedDevicesRequest(int paramInt, String paramString, byte[] paramArrayOfByte, IBinder paramIBinder)
  {
    this.versionCode = paramInt;
    this.zzbdq = ((String)zzx.zzz(paramString));
    this.zzbdr = ((byte[])zzx.zzz(paramArrayOfByte));
    this.zzbdo = zzc.zza.zzdH(paramIBinder);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbdo.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\sharing\internal\TrustedDevicesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */