package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.events.internal.TransferProgressData;
import java.util.List;

public final class TransferStateEvent
  implements DriveEvent
{
  public static final Parcelable.Creator<TransferStateEvent> CREATOR = new zzp();
  final int mVersionCode;
  final String zzVa;
  final List<TransferProgressData> zzapU;
  
  TransferStateEvent(int paramInt, String paramString, List<TransferProgressData> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzVa = paramString;
    this.zzapU = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == this);
      paramObject = (TransferStateEvent)paramObject;
      if (!zzw.equal(this.zzVa, ((TransferStateEvent)paramObject).zzVa)) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(this.zzapU, ((TransferStateEvent)paramObject).zzapU));
    return false;
  }
  
  public int getType()
  {
    return 7;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzVa, this.zzapU });
  }
  
  public String toString()
  {
    return String.format("TransferStateEvent[%s]", new Object[] { TextUtils.join("','", this.zzapU) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzp.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\TransferStateEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */