package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzmn;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzoi.zza;
import java.util.Arrays;
import java.util.List;

public class DataSourcesRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new zzh();
  private final int mVersionCode;
  private final List<Integer> zzaAV;
  private final boolean zzaAW;
  private final zzoi zzaAX;
  private final List<DataType> zzawe;
  
  DataSourcesRequest(int paramInt, List<DataType> paramList, List<Integer> paramList1, boolean paramBoolean, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzawe = paramList;
    this.zzaAV = paramList1;
    this.zzaAW = paramBoolean;
    this.zzaAX = zzoi.zza.zzbD(paramIBinder);
  }
  
  private DataSourcesRequest(Builder paramBuilder)
  {
    this(zzmn.zzb(Builder.zza(paramBuilder)), Arrays.asList(zzmn.zza(Builder.zzb(paramBuilder))), Builder.zzc(paramBuilder), null);
  }
  
  public DataSourcesRequest(DataSourcesRequest paramDataSourcesRequest, zzoi paramzzoi)
  {
    this(paramDataSourcesRequest.zzawe, paramDataSourcesRequest.zzaAV, paramDataSourcesRequest.zzaAW, paramzzoi);
  }
  
  public DataSourcesRequest(List<DataType> paramList, List<Integer> paramList1, boolean paramBoolean, zzoi paramzzoi)
  {
    this.mVersionCode = 4;
    this.zzawe = paramList;
    this.zzaAV = paramList1;
    this.zzaAW = paramBoolean;
    this.zzaAX = paramzzoi;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAX == null) {
      return null;
    }
    return this.zzaAX.asBinder();
  }
  
  public List<DataType> getDataTypes()
  {
    return this.zzawe;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzy(this).zzg("dataTypes", this.zzawe).zzg("sourceTypes", this.zzaAV);
    if (this.zzaAW) {
      localzza.zzg("includeDbOnlySources", "true");
    }
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public List<Integer> zzuT()
  {
    return this.zzaAV;
  }
  
  public boolean zzuU()
  {
    return this.zzaAW;
  }
  
  public static class Builder
  {
    private boolean zzaAW = false;
    private DataType[] zzaAY = new DataType[0];
    private int[] zzaAZ = { 0, 1 };
    
    public DataSourcesRequest build()
    {
      boolean bool2 = true;
      if (this.zzaAY.length > 0)
      {
        bool1 = true;
        zzx.zza(bool1, "Must add at least one data type");
        if (this.zzaAZ.length <= 0) {
          break label49;
        }
      }
      label49:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zza(bool1, "Must add at least one data source type");
        return new DataSourcesRequest(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setDataSourceTypes(int... paramVarArgs)
    {
      this.zzaAZ = paramVarArgs;
      return this;
    }
    
    public Builder setDataTypes(DataType... paramVarArgs)
    {
      this.zzaAY = paramVarArgs;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\DataSourcesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */