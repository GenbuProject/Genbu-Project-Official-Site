package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

public class ReadRawResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<ReadRawResult> CREATOR = new zzi();
  private final int mVersionCode;
  private final List<DataHolder> zzaBN;
  private final DataHolder zzahi;
  
  ReadRawResult(int paramInt, DataHolder paramDataHolder, List<DataHolder> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzahi = paramDataHolder;
    Object localObject = paramList;
    if (paramList == null) {
      localObject = Collections.singletonList(paramDataHolder);
    }
    this.zzaBN = ((List)localObject);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Status getStatus()
  {
    return new Status(this.zzahi.getStatusCode());
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  DataHolder zzsX()
  {
    return this.zzahi;
  }
  
  public List<DataHolder> zzvo()
  {
    return this.zzaBN;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\result\ReadRawResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */