package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class ControlProgressRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ControlProgressRequest> CREATOR = new zzj();
  final int mVersionCode;
  final DriveId zzaoz;
  final int zzaqq;
  final int zzaqr;
  final ParcelableTransferPreferences zzaqs;
  
  ControlProgressRequest(int paramInt1, int paramInt2, int paramInt3, DriveId paramDriveId, ParcelableTransferPreferences paramParcelableTransferPreferences)
  {
    this.mVersionCode = paramInt1;
    this.zzaqq = paramInt2;
    this.zzaqr = paramInt3;
    this.zzaoz = paramDriveId;
    this.zzaqs = paramParcelableTransferPreferences;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\ControlProgressRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */