package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzor;
import com.google.android.gms.internal.zzor.zza;

public class ListSubscriptionsRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ListSubscriptionsRequest> CREATOR = new zzs();
  private final int mVersionCode;
  private final zzor zzaBd;
  private final DataType zzavT;
  
  ListSubscriptionsRequest(int paramInt, DataType paramDataType, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzavT = paramDataType;
    this.zzaBd = zzor.zza.zzbM(paramIBinder);
  }
  
  public ListSubscriptionsRequest(DataType paramDataType, zzor paramzzor)
  {
    this.mVersionCode = 3;
    this.zzavT = paramDataType;
    this.zzaBd = paramzzor;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaBd == null) {
      return null;
    }
    return this.zzaBd.asBinder();
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzs.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\ListSubscriptionsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */