package com.google.android.gms.vision.barcode.internal.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class BarcodeDetectorOptions
  implements SafeParcelable
{
  public static final zza CREATOR = new zza();
  final int versionCode;
  public int zzbnw;
  
  public BarcodeDetectorOptions()
  {
    this.versionCode = 1;
  }
  
  public BarcodeDetectorOptions(int paramInt1, int paramInt2)
  {
    this.versionCode = paramInt1;
    this.zzbnw = paramInt2;
  }
  
  public int describeContents()
  {
    zza localzza = CREATOR;
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza localzza = CREATOR;
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\vision\barcode\internal\client\BarcodeDetectorOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */