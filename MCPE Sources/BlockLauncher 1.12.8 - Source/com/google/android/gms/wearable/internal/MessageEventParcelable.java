package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.MessageEvent;

public class MessageEventParcelable
  implements SafeParcelable, MessageEvent
{
  public static final Parcelable.Creator<MessageEventParcelable> CREATOR = new zzba();
  private final String mPath;
  final int mVersionCode;
  private final byte[] zzaKm;
  private final String zzaPI;
  private final int zzaox;
  
  MessageEventParcelable(int paramInt1, int paramInt2, String paramString1, byte[] paramArrayOfByte, String paramString2)
  {
    this.mVersionCode = paramInt1;
    this.zzaox = paramInt2;
    this.mPath = paramString1;
    this.zzaKm = paramArrayOfByte;
    this.zzaPI = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public byte[] getData()
  {
    return this.zzaKm;
  }
  
  public String getPath()
  {
    return this.mPath;
  }
  
  public int getRequestId()
  {
    return this.zzaox;
  }
  
  public String getSourceNodeId()
  {
    return this.zzaPI;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("MessageEventParcelable[").append(this.zzaox).append(",").append(this.mPath).append(", size=");
    if (this.zzaKm == null) {}
    for (Object localObject = "null";; localObject = Integer.valueOf(this.zzaKm.length)) {
      return localObject + "]";
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzba.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\MessageEventParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */