package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class StopBleScanRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<StopBleScanRequest> CREATOR = new zzae();
  private final int mVersionCode;
  private final zzow zzaAD;
  private final zzq zzaBA;
  
  StopBleScanRequest(int paramInt, IBinder paramIBinder1, IBinder paramIBinder2)
  {
    this.mVersionCode = paramInt;
    this.zzaBA = zzq.zza.zzbU(paramIBinder1);
    this.zzaAD = zzow.zza.zzbR(paramIBinder2);
  }
  
  public StopBleScanRequest(BleScanCallback paramBleScanCallback, zzow paramzzow)
  {
    this(zza.zza.zzuJ().zzb(paramBleScanCallback), paramzzow);
  }
  
  public StopBleScanRequest(zzq paramzzq, zzow paramzzow)
  {
    this.mVersionCode = 3;
    this.zzaBA = paramzzq;
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
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzae.zza(this, paramParcel, paramInt);
  }
  
  public IBinder zzvg()
  {
    return this.zzaBA.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\StopBleScanRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */