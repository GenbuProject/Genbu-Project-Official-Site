package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class ClientAppContext
  implements SafeParcelable
{
  public static final Parcelable.Creator<ClientAppContext> CREATOR = new zza();
  final int versionCode;
  public final boolean zzbbH;
  public final String zzbco;
  public final String zzbcp;
  public final int zzbcq;
  
  ClientAppContext(int paramInt1, String paramString1, String paramString2, boolean paramBoolean, int paramInt2)
  {
    this.versionCode = paramInt1;
    this.zzbco = paramString1;
    this.zzbcp = paramString2;
    this.zzbbH = paramBoolean;
    this.zzbcq = paramInt2;
  }
  
  public ClientAppContext(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, false);
  }
  
  public ClientAppContext(String paramString1, String paramString2, boolean paramBoolean)
  {
    this(paramString1, paramString2, paramBoolean, 0);
  }
  
  public ClientAppContext(String paramString1, String paramString2, boolean paramBoolean, int paramInt)
  {
    this(1, paramString1, paramString2, paramBoolean, paramInt);
  }
  
  private static boolean zzT(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return TextUtils.isEmpty(paramString2);
    }
    return paramString1.equals(paramString2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ClientAppContext)) {
        return false;
      }
      paramObject = (ClientAppContext)paramObject;
    } while ((zzT(this.zzbco, ((ClientAppContext)paramObject).zzbco)) && (zzT(this.zzbcp, ((ClientAppContext)paramObject).zzbcp)) && (this.zzbbH == ((ClientAppContext)paramObject).zzbbH) && (this.zzbcq == ((ClientAppContext)paramObject).zzbcq));
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzbco, this.zzbcp, Boolean.valueOf(this.zzbbH), Integer.valueOf(this.zzbcq) });
  }
  
  public String toString()
  {
    return String.format("{realClientPackageName: %s, zeroPartyIdentifier: %s, useRealClientApiKey: %b, callingContext: %d}", new Object[] { this.zzbco, this.zzbcp, Boolean.valueOf(this.zzbbH), Integer.valueOf(this.zzbcq) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\ClientAppContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */