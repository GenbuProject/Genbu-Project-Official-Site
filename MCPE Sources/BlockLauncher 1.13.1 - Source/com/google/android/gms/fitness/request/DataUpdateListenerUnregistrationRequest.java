package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class DataUpdateListenerUnregistrationRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataUpdateListenerUnregistrationRequest> CREATOR = new zzl();
  private final PendingIntent mPendingIntent;
  private final int mVersionCode;
  private final zzow zzaAD;
  
  DataUpdateListenerUnregistrationRequest(int paramInt, PendingIntent paramPendingIntent, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.mPendingIntent = paramPendingIntent;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  private boolean zzb(DataUpdateListenerUnregistrationRequest paramDataUpdateListenerUnregistrationRequest)
  {
    return zzw.equal(this.mPendingIntent, paramDataUpdateListenerUnregistrationRequest.mPendingIntent);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataUpdateListenerUnregistrationRequest)) && (zzb((DataUpdateListenerUnregistrationRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
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
    return zzw.hashCode(new Object[] { this.mPendingIntent });
  }
  
  public String toString()
  {
    return String.format("DataUpdateListenerUnregistrationRequest", new Object[0]);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzl.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\DataUpdateListenerUnregistrationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */