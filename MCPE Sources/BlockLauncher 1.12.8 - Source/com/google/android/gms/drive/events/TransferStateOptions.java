package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class TransferStateOptions
  implements SafeParcelable
{
  public static final Parcelable.Creator<TransferStateOptions> CREATOR = new zzr();
  final int mVersionCode;
  final List<DriveSpace> zzapB;
  private final Set<DriveSpace> zzapC;
  
  TransferStateOptions(int paramInt, List<DriveSpace> paramList) {}
  
  private TransferStateOptions(int paramInt, List<DriveSpace> paramList, Set<DriveSpace> paramSet)
  {
    this.mVersionCode = paramInt;
    this.zzapB = paramList;
    this.zzapC = paramSet;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    paramObject = (TransferStateOptions)paramObject;
    return zzw.equal(this.zzapC, ((TransferStateOptions)paramObject).zzapC);
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzapC });
  }
  
  public String toString()
  {
    return String.format(Locale.US, "TransferStateOptions[Spaces=%s]", new Object[] { this.zzapB });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzr.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\TransferStateOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */