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

public class SessionRegistrationRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionRegistrationRequest> CREATOR = new zzz();
  private final PendingIntent mPendingIntent;
  private final int mVersionCode;
  private final zzow zzaAD;
  private final int zzaBy;
  
  SessionRegistrationRequest(int paramInt1, PendingIntent paramPendingIntent, IBinder paramIBinder, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.mPendingIntent = paramPendingIntent;
    if (paramIBinder == null) {}
    for (paramPendingIntent = null;; paramPendingIntent = zzow.zza.zzbR(paramIBinder))
    {
      this.zzaAD = paramPendingIntent;
      this.zzaBy = paramInt2;
      return;
    }
  }
  
  public SessionRegistrationRequest(PendingIntent paramPendingIntent, zzow paramzzow, int paramInt)
  {
    this.mVersionCode = 6;
    this.mPendingIntent = paramPendingIntent;
    this.zzaAD = paramzzow;
    this.zzaBy = paramInt;
  }
  
  private boolean zzb(SessionRegistrationRequest paramSessionRegistrationRequest)
  {
    return (this.zzaBy == paramSessionRegistrationRequest.zzaBy) && (zzw.equal(this.mPendingIntent, paramSessionRegistrationRequest.mPendingIntent));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SessionRegistrationRequest)) && (zzb((SessionRegistrationRequest)paramObject)));
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
    return zzw.hashCode(new Object[] { this.mPendingIntent, Integer.valueOf(this.zzaBy) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("pendingIntent", this.mPendingIntent).zzg("sessionRegistrationOption", Integer.valueOf(this.zzaBy)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzz.zza(this, paramParcel, paramInt);
  }
  
  public int zzvf()
  {
    return this.zzaBy;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\SessionRegistrationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */