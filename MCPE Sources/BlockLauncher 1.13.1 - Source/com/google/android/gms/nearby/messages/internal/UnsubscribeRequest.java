package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UnsubscribeRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<UnsubscribeRequest> CREATOR = new zzu();
  final int mVersionCode;
  @Deprecated
  public final String zzbbF;
  @Deprecated
  public final boolean zzbbH;
  public final zzd zzbcY;
  @Deprecated
  public final String zzbco;
  public final zze zzbcr;
  public final ClientAppContext zzbcs;
  public final PendingIntent zzbda;
  public final int zzbdb;
  
  UnsubscribeRequest(int paramInt1, IBinder paramIBinder1, IBinder paramIBinder2, PendingIntent paramPendingIntent, int paramInt2, String paramString1, String paramString2, boolean paramBoolean, ClientAppContext paramClientAppContext)
  {
    this.mVersionCode = paramInt1;
    this.zzbcY = zzd.zza.zzdy(paramIBinder1);
    this.zzbcr = zze.zza.zzdz(paramIBinder2);
    this.zzbda = paramPendingIntent;
    this.zzbdb = paramInt2;
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
  
  UnsubscribeRequest(IBinder paramIBinder1, IBinder paramIBinder2, PendingIntent paramPendingIntent, int paramInt, ClientAppContext paramClientAppContext)
  {
    this(1, paramIBinder1, paramIBinder2, paramPendingIntent, paramInt, null, null, false, paramClientAppContext);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzu.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbcr.asBinder();
  }
  
  IBinder zzEH()
  {
    if (this.zzbcY == null) {
      return null;
    }
    return this.zzbcY.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\UnsubscribeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */