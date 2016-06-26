package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.zzh;

public class CreateFileRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<CreateFileRequest> CREATOR = new zzn();
  final int mVersionCode;
  final String zzaoV;
  final String zzaoY;
  final int zzaqA;
  final boolean zzaqd;
  final Contents zzaql;
  final MetadataBundle zzaqw;
  final Integer zzaqx;
  final DriveId zzaqy;
  final int zzaqz;
  
  CreateFileRequest(int paramInt1, DriveId paramDriveId, MetadataBundle paramMetadataBundle, Contents paramContents, Integer paramInteger, boolean paramBoolean, String paramString1, int paramInt2, int paramInt3, String paramString2)
  {
    if ((paramContents != null) && (paramInt3 != 0)) {
      if (paramContents.getRequestId() != paramInt3) {
        break label67;
      }
    }
    label67:
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "inconsistent contents reference");
      if (((paramInteger != null) && (paramInteger.intValue() != 0)) || (paramContents != null) || (paramInt3 != 0)) {
        break;
      }
      throw new IllegalArgumentException("Need a valid contents");
    }
    this.mVersionCode = paramInt1;
    this.zzaqy = ((DriveId)zzx.zzz(paramDriveId));
    this.zzaqw = ((MetadataBundle)zzx.zzz(paramMetadataBundle));
    this.zzaql = paramContents;
    this.zzaqx = paramInteger;
    this.zzaoV = paramString1;
    this.zzaqz = paramInt2;
    this.zzaqd = paramBoolean;
    this.zzaqA = paramInt3;
    this.zzaoY = paramString2;
  }
  
  public CreateFileRequest(DriveId paramDriveId, MetadataBundle paramMetadataBundle, int paramInt1, int paramInt2, zzh paramzzh)
  {
    this(2, paramDriveId, paramMetadataBundle, null, Integer.valueOf(paramInt2), paramzzh.zzsC(), paramzzh.zzsB(), paramzzh.zzsD(), paramInt1, paramzzh.zzsF());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzn.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\CreateFileRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */