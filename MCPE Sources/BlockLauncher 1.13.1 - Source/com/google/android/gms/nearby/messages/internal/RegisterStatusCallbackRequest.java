package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class RegisterStatusCallbackRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<RegisterStatusCallbackRequest> CREATOR = new zzr();
  final int versionCode;
  @Deprecated
  public String zzbbF;
  public final zzh zzbcW;
  public boolean zzbcX;
  public final zze zzbcr;
  public final ClientAppContext zzbcs;
  
  RegisterStatusCallbackRequest(int paramInt, IBinder paramIBinder1, IBinder paramIBinder2, boolean paramBoolean, String paramString, ClientAppContext paramClientAppContext)
  {
    this.versionCode = paramInt;
    this.zzbcr = zze.zza.zzdz(paramIBinder1);
    this.zzbcW = zzh.zza.zzdC(paramIBinder2);
    this.zzbcX = paramBoolean;
    this.zzbbF = paramString;
    if (paramClientAppContext != null) {}
    for (;;)
    {
      this.zzbcs = paramClientAppContext;
      return;
      paramClientAppContext = new ClientAppContext(null, this.zzbbF);
    }
  }
  
  RegisterStatusCallbackRequest(IBinder paramIBinder1, IBinder paramIBinder2, ClientAppContext paramClientAppContext)
  {
    this(1, paramIBinder1, paramIBinder2, false, null, paramClientAppContext);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzr.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbcr.asBinder();
  }
  
  IBinder zzEG()
  {
    return this.zzbcW.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\RegisterStatusCallbackRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */