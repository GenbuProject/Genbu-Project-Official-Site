package com.google.android.gms.drive.events;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.WriteAwareParcelable;

public class QueryResultEventParcelable
  extends WriteAwareParcelable
  implements DriveEvent
{
  public static final zzl CREATOR = new zzl();
  final int mVersionCode;
  final DataHolder zzahi;
  final boolean zzapQ;
  final int zzapR;
  
  QueryResultEventParcelable(int paramInt1, DataHolder paramDataHolder, boolean paramBoolean, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzahi = paramDataHolder;
    this.zzapQ = paramBoolean;
    this.zzapR = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getType()
  {
    return 3;
  }
  
  public void zzJ(Parcel paramParcel, int paramInt)
  {
    zzl.zza(this, paramParcel, paramInt);
  }
  
  public DataHolder zzsX()
  {
    return this.zzahi;
  }
  
  public boolean zzsY()
  {
    return this.zzapQ;
  }
  
  public int zzsZ()
  {
    return this.zzapR;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\QueryResultEventParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */