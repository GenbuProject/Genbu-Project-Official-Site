package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.nearby.messages.Strategy;

public final class PublishRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<PublishRequest> CREATOR = new zzq();
  final int mVersionCode;
  @Deprecated
  public final String zzbbF;
  public final boolean zzbbG;
  @Deprecated
  public final boolean zzbbH;
  public final MessageWrapper zzbcT;
  public final Strategy zzbcU;
  public final zzg zzbcV;
  @Deprecated
  public final String zzbco;
  public final zze zzbcr;
  public final ClientAppContext zzbcs;
  
  PublishRequest(int paramInt, MessageWrapper paramMessageWrapper, Strategy paramStrategy, IBinder paramIBinder1, String paramString1, String paramString2, boolean paramBoolean1, IBinder paramIBinder2, boolean paramBoolean2, ClientAppContext paramClientAppContext)
  {
    this.mVersionCode = paramInt;
    this.zzbcT = paramMessageWrapper;
    this.zzbcU = paramStrategy;
    this.zzbcr = zze.zza.zzdz(paramIBinder1);
    this.zzbbF = paramString1;
    this.zzbco = paramString2;
    this.zzbbG = paramBoolean1;
    if (paramIBinder2 == null)
    {
      paramMessageWrapper = null;
      this.zzbcV = paramMessageWrapper;
      this.zzbbH = paramBoolean2;
      if (paramClientAppContext == null) {
        break label85;
      }
    }
    for (;;)
    {
      this.zzbcs = paramClientAppContext;
      return;
      paramMessageWrapper = zzg.zza.zzdB(paramIBinder2);
      break;
      label85:
      paramClientAppContext = new ClientAppContext(this.zzbco, this.zzbbF, this.zzbbH);
    }
  }
  
  PublishRequest(MessageWrapper paramMessageWrapper, Strategy paramStrategy, IBinder paramIBinder1, boolean paramBoolean, IBinder paramIBinder2, ClientAppContext paramClientAppContext)
  {
    this(2, paramMessageWrapper, paramStrategy, paramIBinder1, null, null, paramBoolean, paramIBinder2, false, paramClientAppContext);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzq.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbcr.asBinder();
  }
  
  IBinder zzEF()
  {
    if (this.zzbcV == null) {
      return null;
    }
    return this.zzbcV.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\PublishRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */