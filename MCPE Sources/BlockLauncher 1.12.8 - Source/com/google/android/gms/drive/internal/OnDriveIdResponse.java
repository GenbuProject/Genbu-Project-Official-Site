package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OnDriveIdResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnDriveIdResponse> CREATOR = new zzaz();
  final int mVersionCode;
  DriveId zzaqj;
  
  OnDriveIdResponse(int paramInt, DriveId paramDriveId)
  {
    this.mVersionCode = paramInt;
    this.zzaqj = paramDriveId;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DriveId getDriveId()
  {
    return this.zzaqj;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzaz.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\OnDriveIdResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */