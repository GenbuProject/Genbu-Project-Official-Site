package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.events.internal.TransferProgressData;

public final class TransferProgressEvent
  implements DriveEvent
{
  public static final Parcelable.Creator<TransferProgressEvent> CREATOR = new zzn();
  final int mVersionCode;
  final TransferProgressData zzapS;
  
  TransferProgressEvent(int paramInt, TransferProgressData paramTransferProgressData)
  {
    this.mVersionCode = paramInt;
    this.zzapS = paramTransferProgressData;
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
    paramObject = (TransferProgressEvent)paramObject;
    return zzw.equal(this.zzapS, ((TransferProgressEvent)paramObject).zzapS);
  }
  
  public int getType()
  {
    return 8;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzapS });
  }
  
  public String toString()
  {
    return String.format("TransferProgressEvent[%s]", new Object[] { this.zzapS.toString() });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzn.zza(this, paramParcel, paramInt);
  }
  
  public TransferProgressData zzta()
  {
    return this.zzapS;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\TransferProgressEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */