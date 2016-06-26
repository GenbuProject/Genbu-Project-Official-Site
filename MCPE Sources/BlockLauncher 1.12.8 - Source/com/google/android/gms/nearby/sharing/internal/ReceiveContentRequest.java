package com.google.android.gms.nearby.sharing.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ReceiveContentRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ReceiveContentRequest> CREATOR = new zzg();
  public String packageName;
  final int versionCode;
  public IBinder zzbdk;
  public zzc zzbdo;
  public zza zzbdp;
  
  ReceiveContentRequest()
  {
    this.versionCode = 1;
  }
  
  ReceiveContentRequest(int paramInt, IBinder paramIBinder1, IBinder paramIBinder2, String paramString, IBinder paramIBinder3)
  {
    this.versionCode = paramInt;
    this.zzbdk = paramIBinder1;
    this.zzbdp = zza.zza.zzdF(paramIBinder2);
    this.packageName = paramString;
    this.zzbdo = zzc.zza.zzdH(paramIBinder3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbdo.asBinder();
  }
  
  IBinder zzEQ()
  {
    if (this.zzbdp == null) {
      return null;
    }
    return this.zzbdp.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\sharing\internal\ReceiveContentRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */