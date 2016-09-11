package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UnpublishRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<UnpublishRequest> CREATOR = new zzt();
  final int mVersionCode;
  @Deprecated
  public final String zzbbF;
  @Deprecated
  public final boolean zzbbH;
  public final MessageWrapper zzbcT;
  @Deprecated
  public final String zzbco;
  public final zze zzbcr;
  public final ClientAppContext zzbcs;
  
  UnpublishRequest(int paramInt, MessageWrapper paramMessageWrapper, IBinder paramIBinder, String paramString1, String paramString2, boolean paramBoolean, ClientAppContext paramClientAppContext)
  {
    this.mVersionCode = paramInt;
    this.zzbcT = paramMessageWrapper;
    this.zzbcr = zze.zza.zzdz(paramIBinder);
    this.zzbbF = paramString1;
    this.zzbco = paramString2;
    this.zzbbH = paramBoolean;
    if (paramClientAppContext != null) {}
    for (;;)
    {
      this.zzbcs = paramClientAppContext;
      return;
      paramClientAppContext = new ClientAppContext(this.zzbco, this.zzbbF, this.zzbbH);
    }
  }
  
  UnpublishRequest(MessageWrapper paramMessageWrapper, IBinder paramIBinder, ClientAppContext paramClientAppContext)
  {
    this(1, paramMessageWrapper, paramIBinder, null, null, false, paramClientAppContext);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzt.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbcr.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\UnpublishRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */