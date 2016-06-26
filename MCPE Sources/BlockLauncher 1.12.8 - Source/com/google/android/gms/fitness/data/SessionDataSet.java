package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public class SessionDataSet
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionDataSet> CREATOR = new zzr();
  final int mVersionCode;
  private final Session zzavX;
  private final DataSet zzaxn;
  
  SessionDataSet(int paramInt, Session paramSession, DataSet paramDataSet)
  {
    this.mVersionCode = paramInt;
    this.zzavX = paramSession;
    this.zzaxn = paramDataSet;
  }
  
  private boolean zza(SessionDataSet paramSessionDataSet)
  {
    return (zzw.equal(this.zzavX, paramSessionDataSet.zzavX)) && (zzw.equal(this.zzaxn, paramSessionDataSet.zzaxn));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof SessionDataSet)) && (zza((SessionDataSet)paramObject)));
  }
  
  public DataSet getDataSet()
  {
    return this.zzaxn;
  }
  
  public Session getSession()
  {
    return this.zzavX;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavX, this.zzaxn });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("session", this.zzavX).zzg("dataSet", this.zzaxn).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzr.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\SessionDataSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */