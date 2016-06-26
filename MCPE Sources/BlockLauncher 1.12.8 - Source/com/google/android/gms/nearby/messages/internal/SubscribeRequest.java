package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.Strategy;

public final class SubscribeRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SubscribeRequest> CREATOR = new zzs();
  final int mVersionCode;
  @Deprecated
  public final String zzbbF;
  public final boolean zzbbG;
  @Deprecated
  public final boolean zzbbH;
  public final Strategy zzbcU;
  public final zzd zzbcY;
  public final MessageFilter zzbcZ;
  @Deprecated
  public final String zzbco;
  public final zze zzbcr;
  public final ClientAppContext zzbcs;
  public final PendingIntent zzbda;
  public final int zzbdb;
  public final byte[] zzbdc;
  public final zzi zzbdd;
  
  SubscribeRequest(int paramInt1, IBinder paramIBinder1, Strategy paramStrategy, IBinder paramIBinder2, MessageFilter paramMessageFilter, PendingIntent paramPendingIntent, int paramInt2, String paramString1, String paramString2, byte[] paramArrayOfByte, boolean paramBoolean1, IBinder paramIBinder3, boolean paramBoolean2, ClientAppContext paramClientAppContext)
  {
    this.mVersionCode = paramInt1;
    this.zzbcY = zzd.zza.zzdy(paramIBinder1);
    this.zzbcU = paramStrategy;
    this.zzbcr = zze.zza.zzdz(paramIBinder2);
    this.zzbcZ = paramMessageFilter;
    this.zzbda = paramPendingIntent;
    this.zzbdb = paramInt2;
    this.zzbbF = paramString1;
    this.zzbco = paramString2;
    this.zzbdc = paramArrayOfByte;
    this.zzbbG = paramBoolean1;
    if (paramIBinder3 == null)
    {
      paramIBinder1 = null;
      this.zzbdd = paramIBinder1;
      this.zzbbH = paramBoolean2;
      if (paramClientAppContext == null) {
        break label112;
      }
    }
    for (;;)
    {
      this.zzbcs = paramClientAppContext;
      return;
      paramIBinder1 = zzi.zza.zzdD(paramIBinder3);
      break;
      label112:
      paramClientAppContext = new ClientAppContext(this.zzbco, this.zzbbF, this.zzbbH);
    }
  }
  
  public SubscribeRequest(IBinder paramIBinder1, Strategy paramStrategy, IBinder paramIBinder2, MessageFilter paramMessageFilter, PendingIntent paramPendingIntent, int paramInt, byte[] paramArrayOfByte, boolean paramBoolean, IBinder paramIBinder3, ClientAppContext paramClientAppContext)
  {
    this(3, paramIBinder1, paramStrategy, paramIBinder2, paramMessageFilter, paramPendingIntent, paramInt, null, null, paramArrayOfByte, paramBoolean, paramIBinder3, false, paramClientAppContext);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzs.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    if (this.zzbcr == null) {
      return null;
    }
    return this.zzbcr.asBinder();
  }
  
  IBinder zzEH()
  {
    if (this.zzbcY == null) {
      return null;
    }
    return this.zzbcY.asBinder();
  }
  
  IBinder zzEI()
  {
    if (this.zzbdd == null) {
      return null;
    }
    return this.zzbdd.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\SubscribeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */