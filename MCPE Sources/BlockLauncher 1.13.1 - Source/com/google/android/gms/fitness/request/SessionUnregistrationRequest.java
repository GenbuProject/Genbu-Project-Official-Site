package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class SessionUnregistrationRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionUnregistrationRequest> CREATOR = new zzac();
  private final PendingIntent mPendingIntent;
  private final int mVersionCode;
  private final zzow zzaAD;
  
  SessionUnregistrationRequest(int paramInt, PendingIntent paramPendingIntent, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.mPendingIntent = paramPendingIntent;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public SessionUnregistrationRequest(PendingIntent paramPendingIntent, zzow paramzzow)
  {
    this.mVersionCode = 5;
    this.mPendingIntent = paramPendingIntent;
    this.zzaAD = paramzzow;
  }
  
  private boolean zzb(SessionUnregistrationRequest paramSessionUnregistrationRequest)
  {
    return zzw.equal(this.mPendingIntent, paramSessionUnregistrationRequest.mPendingIntent);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SessionUnregistrationRequest)) && (zzb((SessionUnregistrationRequest)paramObject)));
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
    return zzw.zzy(this).zzg("pendingIntent", this.mPendingIntent).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzac.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\SessionUnregistrationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */