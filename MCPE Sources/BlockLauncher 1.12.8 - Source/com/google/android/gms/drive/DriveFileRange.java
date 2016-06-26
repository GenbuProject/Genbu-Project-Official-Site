package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DriveFileRange
  implements SafeParcelable
{
  public static final Parcelable.Creator<DriveFileRange> CREATOR = new zzc();
  final int mVersionCode;
  final long zzaoJ;
  final long zzaoK;
  
  DriveFileRange(int paramInt, long paramLong1, long paramLong2)
  {
    this.mVersionCode = paramInt;
    this.zzaoJ = paramLong1;
    this.zzaoK = paramLong2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\DriveFileRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */