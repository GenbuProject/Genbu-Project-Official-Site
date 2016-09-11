package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzot.zza;
import java.util.List;

public class ReadStatsRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ReadStatsRequest> CREATOR = new zzu();
  private final int mVersionCode;
  private final List<DataSource> zzaAF;
  private final zzot zzaBg;
  
  ReadStatsRequest(int paramInt, IBinder paramIBinder, List<DataSource> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzaBg = zzot.zza.zzbO(paramIBinder);
    this.zzaAF = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    return this.zzaBg.asBinder();
  }
  
  public List<DataSource> getDataSources()
  {
    return this.zzaAF;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public String toString()
  {
    return String.format("ReadStatsRequest", new Object[0]);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzu.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\ReadStatsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */