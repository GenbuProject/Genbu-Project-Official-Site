package com.google.android.gms.nearby.messages.devices;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class NearbyDeviceFilter
  implements SafeParcelable
{
  public static final Parcelable.Creator<NearbyDeviceFilter> CREATOR = new zzg();
  final int mVersionCode;
  final int zzbci;
  final byte[] zzbcj;
  final boolean zzbck;
  
  NearbyDeviceFilter(int paramInt1, int paramInt2, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzbci = paramInt2;
    this.zzbcj = paramArrayOfByte;
    this.zzbck = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\devices\NearbyDeviceFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */