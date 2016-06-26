package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;

public class SubscribeRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SubscribeRequest> CREATOR = new zzaf();
  private final int mVersionCode;
  private final zzow zzaAD;
  private Subscription zzaBC;
  private final boolean zzaBD;
  
  SubscribeRequest(int paramInt, Subscription paramSubscription, boolean paramBoolean, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzaBC = paramSubscription;
    this.zzaBD = paramBoolean;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public SubscribeRequest(Subscription paramSubscription, boolean paramBoolean, zzow paramzzow)
  {
    this.mVersionCode = 3;
    this.zzaBC = paramSubscription;
    this.zzaBD = paramBoolean;
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
  
  public String toString()
  {
    return zzw.zzy(this).zzg("subscription", this.zzaBC).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzaf.zza(this, paramParcel, paramInt);
  }
  
  public Subscription zzvh()
  {
    return this.zzaBC;
  }
  
  public boolean zzvi()
  {
    return this.zzaBD;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\SubscribeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */