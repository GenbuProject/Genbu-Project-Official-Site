package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzov.zza;

public class SessionStopRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionStopRequest> CREATOR = new zzab();
  private final String mName;
  private final int mVersionCode;
  private final zzov zzaBz;
  private final String zzaxk;
  
  SessionStopRequest(int paramInt, String paramString1, String paramString2, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.mName = paramString1;
    this.zzaxk = paramString2;
    this.zzaBz = zzov.zza.zzbQ(paramIBinder);
  }
  
  public SessionStopRequest(String paramString1, String paramString2, zzov paramzzov)
  {
    this.mVersionCode = 3;
    this.mName = paramString1;
    this.zzaxk = paramString2;
    this.zzaBz = paramzzov;
  }
  
  private boolean zzb(SessionStopRequest paramSessionStopRequest)
  {
    return (zzw.equal(this.mName, paramSessionStopRequest.mName)) && (zzw.equal(this.zzaxk, paramSessionStopRequest.zzaxk));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof SessionStopRequest)) && (zzb((SessionStopRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaBz == null) {
      return null;
    }
    return this.zzaBz.asBinder();
  }
  
  public String getIdentifier()
  {
    return this.zzaxk;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.mName, this.zzaxk });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("name", this.mName).zzg("identifier", this.zzaxk).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzab.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\SessionStopRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */