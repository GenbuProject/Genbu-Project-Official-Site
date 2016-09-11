package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AuthAccountResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<AuthAccountResult> CREATOR = new zza();
  final int mVersionCode;
  private int zzbhd;
  private Intent zzbhe;
  
  public AuthAccountResult()
  {
    this(0, null);
  }
  
  AuthAccountResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.mVersionCode = paramInt1;
    this.zzbhd = paramInt2;
    this.zzbhe = paramIntent;
  }
  
  public AuthAccountResult(int paramInt, Intent paramIntent)
  {
    this(2, paramInt, paramIntent);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Status getStatus()
  {
    if (this.zzbhd == 0) {
      return Status.zzagC;
    }
    return Status.zzagG;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public int zzFK()
  {
    return this.zzbhd;
  }
  
  public Intent zzFL()
  {
    return this.zzbhe;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\signin\internal\AuthAccountResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */