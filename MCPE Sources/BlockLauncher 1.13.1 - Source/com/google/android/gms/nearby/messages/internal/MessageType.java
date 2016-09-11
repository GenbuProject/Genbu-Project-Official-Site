package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class MessageType
  implements SafeParcelable
{
  public static final Parcelable.Creator<MessageType> CREATOR = new zzk();
  final int mVersionCode;
  public final String type;
  public final String zzamD;
  
  MessageType(int paramInt, String paramString1, String paramString2)
  {
    this.mVersionCode = paramInt;
    this.zzamD = paramString1;
    this.type = paramString2;
  }
  
  public MessageType(String paramString1, String paramString2)
  {
    this(1, paramString1, paramString2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof MessageType)) {
        return false;
      }
      paramObject = (MessageType)paramObject;
    } while ((zzw.equal(this.zzamD, ((MessageType)paramObject).zzamD)) && (zzw.equal(this.type, ((MessageType)paramObject).type)));
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzamD, this.type });
  }
  
  public String toString()
  {
    return "namespace=" + this.zzamD + ", type=" + this.type;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\MessageType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */