package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class DataInsertRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataInsertRequest> CREATOR = new zze();
  private final int mVersionCode;
  private final zzow zzaAD;
  private final boolean zzaAJ;
  private final DataSet zzaxn;
  
  DataInsertRequest(int paramInt, DataSet paramDataSet, IBinder paramIBinder, boolean paramBoolean)
  {
    this.mVersionCode = paramInt;
    this.zzaxn = paramDataSet;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
    this.zzaAJ = paramBoolean;
  }
  
  public DataInsertRequest(DataSet paramDataSet, zzow paramzzow, boolean paramBoolean)
  {
    this.mVersionCode = 4;
    this.zzaxn = paramDataSet;
    this.zzaAD = paramzzow;
    this.zzaAJ = paramBoolean;
  }
  
  private boolean zzc(DataInsertRequest paramDataInsertRequest)
  {
    return zzw.equal(this.zzaxn, paramDataInsertRequest.zzaxn);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataInsertRequest)) && (zzc((DataInsertRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
  }
  
  public DataSet getDataSet()
  {
    return this.zzaxn;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaxn });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("dataSet", this.zzaxn).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzuO()
  {
    return this.zzaAJ;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\DataInsertRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */