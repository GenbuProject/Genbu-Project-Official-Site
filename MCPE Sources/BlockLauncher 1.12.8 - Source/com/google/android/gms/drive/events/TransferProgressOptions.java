package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.Locale;

public final class TransferProgressOptions
  implements SafeParcelable
{
  public static final Parcelable.Creator<TransferProgressOptions> CREATOR = new zzo();
  final int mVersionCode;
  final int zzapT;
  
  TransferProgressOptions(int paramInt1, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzapT = paramInt2;
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
    paramObject = (TransferProgressOptions)paramObject;
    return zzw.equal(Integer.valueOf(this.zzapT), Integer.valueOf(((TransferProgressOptions)paramObject).zzapT));
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzapT) });
  }
  
  public String toString()
  {
    return String.format(Locale.US, "TransferProgressOptions[type=%d]", new Object[] { Integer.valueOf(this.zzapT) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\TransferProgressOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */