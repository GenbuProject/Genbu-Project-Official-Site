package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.zzk;
import com.google.android.gms.fitness.data.zzk.zza;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class SensorUnregistrationRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SensorUnregistrationRequest> CREATOR = new zzw();
  private final PendingIntent mPendingIntent;
  private final int mVersionCode;
  private final zzow zzaAD;
  private final zzk zzaBh;
  
  SensorUnregistrationRequest(int paramInt, IBinder paramIBinder1, PendingIntent paramPendingIntent, IBinder paramIBinder2)
  {
    this.mVersionCode = paramInt;
    if (paramIBinder1 == null) {}
    for (paramIBinder1 = null;; paramIBinder1 = zzk.zza.zzbt(paramIBinder1))
    {
      this.zzaBh = paramIBinder1;
      this.mPendingIntent = paramPendingIntent;
      this.zzaAD = zzow.zza.zzbR(paramIBinder2);
      return;
    }
  }
  
  public SensorUnregistrationRequest(zzk paramzzk, PendingIntent paramPendingIntent, zzow paramzzow)
  {
    this.mVersionCode = 4;
    this.zzaBh = paramzzk;
    this.mPendingIntent = paramPendingIntent;
    this.zzaAD = paramzzow;
  }
  
  public int describeContents()
  {
    return 0;
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
  
  public String toString()
  {
    return String.format("SensorUnregistrationRequest{%s}", new Object[] { this.zzaBh });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzw.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzvb()
  {
    if (this.zzaBh == null) {
      return null;
    }
    return this.zzaBh.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\SensorUnregistrationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */