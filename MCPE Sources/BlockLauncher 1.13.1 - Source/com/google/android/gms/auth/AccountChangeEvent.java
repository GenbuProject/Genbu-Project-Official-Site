package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public class AccountChangeEvent
  implements SafeParcelable
{
  public static final Parcelable.Creator<AccountChangeEvent> CREATOR = new zza();
  final int mVersion;
  final long zzUZ;
  final String zzVa;
  final int zzVb;
  final int zzVc;
  final String zzVd;
  
  AccountChangeEvent(int paramInt1, long paramLong, String paramString1, int paramInt2, int paramInt3, String paramString2)
  {
    this.mVersion = paramInt1;
    this.zzUZ = paramLong;
    this.zzVa = ((String)zzx.zzz(paramString1));
    this.zzVb = paramInt2;
    this.zzVc = paramInt3;
    this.zzVd = paramString2;
  }
  
  public AccountChangeEvent(long paramLong, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    this.mVersion = 1;
    this.zzUZ = paramLong;
    this.zzVa = ((String)zzx.zzz(paramString1));
    this.zzVb = paramInt1;
    this.zzVc = paramInt2;
    this.zzVd = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof AccountChangeEvent)) {
        break;
      }
      paramObject = (AccountChangeEvent)paramObject;
    } while ((this.mVersion == ((AccountChangeEvent)paramObject).mVersion) && (this.zzUZ == ((AccountChangeEvent)paramObject).zzUZ) && (zzw.equal(this.zzVa, ((AccountChangeEvent)paramObject).zzVa)) && (this.zzVb == ((AccountChangeEvent)paramObject).zzVb) && (this.zzVc == ((AccountChangeEvent)paramObject).zzVc) && (zzw.equal(this.zzVd, ((AccountChangeEvent)paramObject).zzVd)));
    return false;
    return false;
  }
  
  public String getAccountName()
  {
    return this.zzVa;
  }
  
  public String getChangeData()
  {
    return this.zzVd;
  }
  
  public int getChangeType()
  {
    return this.zzVb;
  }
  
  public int getEventIndex()
  {
    return this.zzVc;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.mVersion), Long.valueOf(this.zzUZ), this.zzVa, Integer.valueOf(this.zzVb), Integer.valueOf(this.zzVc), this.zzVd });
  }
  
  public String toString()
  {
    String str = "UNKNOWN";
    switch (this.zzVb)
    {
    }
    for (;;)
    {
      return "AccountChangeEvent {accountName = " + this.zzVa + ", changeType = " + str + ", changeData = " + this.zzVd + ", eventIndex = " + this.zzVc + "}";
      str = "ADDED";
      continue;
      str = "REMOVED";
      continue;
      str = "RENAMED_TO";
      continue;
      str = "RENAMED_FROM";
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\AccountChangeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */