package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.zzc;
import java.util.Collection;

public class GetServiceRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzi();
  final int version;
  final int zzall;
  int zzalm;
  String zzaln;
  IBinder zzalo;
  Scope[] zzalp;
  Bundle zzalq;
  Account zzalr;
  
  public GetServiceRequest(int paramInt)
  {
    this.version = 2;
    this.zzalm = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    this.zzall = paramInt;
  }
  
  GetServiceRequest(int paramInt1, int paramInt2, int paramInt3, String paramString, IBinder paramIBinder, Scope[] paramArrayOfScope, Bundle paramBundle, Account paramAccount)
  {
    this.version = paramInt1;
    this.zzall = paramInt2;
    this.zzalm = paramInt3;
    this.zzaln = paramString;
    if (paramInt1 < 2) {}
    for (this.zzalr = zzaO(paramIBinder);; this.zzalr = paramAccount)
    {
      this.zzalp = paramArrayOfScope;
      this.zzalq = paramBundle;
      return;
      this.zzalo = paramIBinder;
    }
  }
  
  private Account zzaO(IBinder paramIBinder)
  {
    Account localAccount = null;
    if (paramIBinder != null) {
      localAccount = zza.zza(zzp.zza.zzaP(paramIBinder));
    }
    return localAccount;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public GetServiceRequest zzb(zzp paramzzp)
  {
    if (paramzzp != null) {
      this.zzalo = paramzzp.asBinder();
    }
    return this;
  }
  
  public GetServiceRequest zzc(Account paramAccount)
  {
    this.zzalr = paramAccount;
    return this;
  }
  
  public GetServiceRequest zzcG(String paramString)
  {
    this.zzaln = paramString;
    return this;
  }
  
  public GetServiceRequest zzd(Collection<Scope> paramCollection)
  {
    this.zzalp = ((Scope[])paramCollection.toArray(new Scope[paramCollection.size()]));
    return this;
  }
  
  public GetServiceRequest zzj(Bundle paramBundle)
  {
    this.zzalq = paramBundle;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\internal\GetServiceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */