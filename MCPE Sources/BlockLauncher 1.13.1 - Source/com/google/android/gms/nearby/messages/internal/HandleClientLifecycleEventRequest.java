package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class HandleClientLifecycleEventRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<HandleClientLifecycleEventRequest> CREATOR = new zzc();
  public final int versionCode;
  public final ClientAppContext zzbcs;
  public final int zzbct;
  
  HandleClientLifecycleEventRequest(int paramInt1, ClientAppContext paramClientAppContext, int paramInt2)
  {
    this.versionCode = paramInt1;
    this.zzbcs = paramClientAppContext;
    this.zzbct = paramInt2;
  }
  
  public HandleClientLifecycleEventRequest(ClientAppContext paramClientAppContext, int paramInt)
  {
    this(1, paramClientAppContext, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\HandleClientLifecycleEventRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */