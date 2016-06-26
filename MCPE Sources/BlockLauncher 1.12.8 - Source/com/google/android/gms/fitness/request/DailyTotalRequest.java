package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzog;
import com.google.android.gms.internal.zzog.zza;

public class DailyTotalRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DailyTotalRequest> CREATOR = new zzc();
  private final int mVersionCode;
  private final zzog zzaAE;
  private DataType zzavT;
  
  DailyTotalRequest(int paramInt, IBinder paramIBinder, DataType paramDataType)
  {
    this.mVersionCode = paramInt;
    this.zzaAE = zzog.zza.zzbB(paramIBinder);
    this.zzavT = paramDataType;
  }
  
  public DailyTotalRequest(zzog paramzzog, DataType paramDataType)
  {
    this.mVersionCode = 2;
    this.zzaAE = paramzzog;
    this.zzavT = paramDataType;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    return this.zzaAE.asBinder();
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public String toString()
  {
    return String.format("DailyTotalRequest{%s}", new Object[] { this.zzavT.zzuo() });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\DailyTotalRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */