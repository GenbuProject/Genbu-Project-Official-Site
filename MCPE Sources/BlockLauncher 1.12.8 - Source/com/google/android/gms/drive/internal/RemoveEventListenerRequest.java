package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.TransferProgressOptions;

public class RemoveEventListenerRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<RemoveEventListenerRequest> CREATOR = new zzbp();
  final int mVersionCode;
  final int zzanf;
  final DriveId zzaoz;
  final TransferProgressOptions zzapZ;
  
  RemoveEventListenerRequest(int paramInt1, DriveId paramDriveId, int paramInt2, TransferProgressOptions paramTransferProgressOptions)
  {
    this.mVersionCode = paramInt1;
    this.zzaoz = paramDriveId;
    this.zzanf = paramInt2;
    this.zzapZ = paramTransferProgressOptions;
  }
  
  public RemoveEventListenerRequest(DriveId paramDriveId, int paramInt)
  {
    this(paramDriveId, paramInt, null);
  }
  
  RemoveEventListenerRequest(DriveId paramDriveId, int paramInt, TransferProgressOptions paramTransferProgressOptions)
  {
    this(1, paramDriveId, paramInt, paramTransferProgressOptions);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbp.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\RemoveEventListenerRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */