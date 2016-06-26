package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class UnsubscribeRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<UnsubscribeRequest> CREATOR = new zzah();
  private final int mVersionCode;
  private final zzow zzaAD;
  private final DataType zzavT;
  private final DataSource zzavU;
  
  UnsubscribeRequest(int paramInt, DataType paramDataType, DataSource paramDataSource, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzavT = paramDataType;
    this.zzavU = paramDataSource;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public UnsubscribeRequest(DataType paramDataType, DataSource paramDataSource, zzow paramzzow)
  {
    this.mVersionCode = 3;
    this.zzavT = paramDataType;
    this.zzavU = paramDataSource;
    this.zzaAD = paramzzow;
  }
  
  private boolean zzb(UnsubscribeRequest paramUnsubscribeRequest)
  {
    return (zzw.equal(this.zzavU, paramUnsubscribeRequest.zzavU)) && (zzw.equal(this.zzavT, paramUnsubscribeRequest.zzavT));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof UnsubscribeRequest)) && (zzb((UnsubscribeRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU, this.zzavT });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzah.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\UnsubscribeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */