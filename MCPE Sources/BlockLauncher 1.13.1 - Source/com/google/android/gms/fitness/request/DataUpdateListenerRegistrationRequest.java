package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class DataUpdateListenerRegistrationRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataUpdateListenerRegistrationRequest> CREATOR = new zzk();
  private final PendingIntent mPendingIntent;
  private final int mVersionCode;
  private final zzow zzaAD;
  private DataType zzavT;
  private DataSource zzavU;
  
  DataUpdateListenerRegistrationRequest(int paramInt, DataSource paramDataSource, DataType paramDataType, PendingIntent paramPendingIntent, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzavU = paramDataSource;
    this.zzavT = paramDataType;
    this.mPendingIntent = paramPendingIntent;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public DataUpdateListenerRegistrationRequest(DataSource paramDataSource, DataType paramDataType, PendingIntent paramPendingIntent, IBinder paramIBinder)
  {
    this.mVersionCode = 1;
    this.zzavU = paramDataSource;
    this.zzavT = paramDataType;
    this.mPendingIntent = paramPendingIntent;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  private DataUpdateListenerRegistrationRequest(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), null);
  }
  
  private boolean zzb(DataUpdateListenerRegistrationRequest paramDataUpdateListenerRegistrationRequest)
  {
    return (zzw.equal(this.zzavU, paramDataUpdateListenerRegistrationRequest.zzavU)) && (zzw.equal(this.zzavT, paramDataUpdateListenerRegistrationRequest.zzavT)) && (zzw.equal(this.mPendingIntent, paramDataUpdateListenerRegistrationRequest.mPendingIntent));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataUpdateListenerRegistrationRequest)) && (zzb((DataUpdateListenerRegistrationRequest)paramObject)));
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
  
  public PendingIntent getIntent()
  {
    return this.mPendingIntent;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU, this.zzavT, this.mPendingIntent });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("dataSource", this.zzavU).zzg("dataType", this.zzavT).zzg("pendingIntent", this.mPendingIntent).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private PendingIntent mPendingIntent;
    private DataType zzavT;
    private DataSource zzavU;
    
    public DataUpdateListenerRegistrationRequest build()
    {
      if ((this.zzavU != null) || (this.zzavT != null)) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Set either dataSource or dataTYpe");
        zzx.zzb(this.mPendingIntent, "pendingIntent must be set");
        return new DataUpdateListenerRegistrationRequest(this, null);
      }
    }
    
    public Builder setDataSource(DataSource paramDataSource)
      throws NullPointerException
    {
      zzx.zzz(paramDataSource);
      this.zzavU = paramDataSource;
      return this;
    }
    
    public Builder setDataType(DataType paramDataType)
    {
      zzx.zzz(paramDataType);
      this.zzavT = paramDataType;
      return this;
    }
    
    public Builder setPendingIntent(PendingIntent paramPendingIntent)
    {
      zzx.zzz(paramPendingIntent);
      this.mPendingIntent = paramPendingIntent;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\DataUpdateListenerRegistrationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */