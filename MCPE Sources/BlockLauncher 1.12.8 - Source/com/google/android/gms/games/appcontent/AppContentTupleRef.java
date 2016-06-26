package com.google.android.gms.games.appcontent;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;

public final class AppContentTupleRef
  extends zzc
  implements AppContentTuple
{
  AppContentTupleRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return AppContentTupleEntity.zza(this, paramObject);
  }
  
  public String getName()
  {
    return getString("tuple_name");
  }
  
  public String getValue()
  {
    return getString("tuple_value");
  }
  
  public int hashCode()
  {
    return AppContentTupleEntity.zza(this);
  }
  
  public String toString()
  {
    return AppContentTupleEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((AppContentTupleEntity)zzwq()).writeToParcel(paramParcel, paramInt);
  }
  
  public AppContentTuple zzwq()
  {
    return new AppContentTupleEntity(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentTupleRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */