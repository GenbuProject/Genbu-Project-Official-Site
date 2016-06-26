package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;

public class DataSourceQueryParams
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataSourceQueryParams> CREATOR = new zzg();
  final int mVersionCode;
  public final long zzUZ;
  public final int zzaAO;
  public final long zzaAT;
  public final int zzaAU;
  public final DataSource zzavU;
  public final long zzawk;
  
  DataSourceQueryParams(int paramInt1, DataSource paramDataSource, long paramLong1, long paramLong2, long paramLong3, int paramInt2, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzavU = paramDataSource;
    this.zzUZ = paramLong1;
    this.zzawk = paramLong2;
    this.zzaAT = paramLong3;
    this.zzaAO = paramInt2;
    this.zzaAU = paramInt3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\DataSourceQueryParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */