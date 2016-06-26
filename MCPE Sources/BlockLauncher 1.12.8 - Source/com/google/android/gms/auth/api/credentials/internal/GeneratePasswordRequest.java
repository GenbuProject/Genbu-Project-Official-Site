package com.google.android.gms.auth.api.credentials.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GeneratePasswordRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<GeneratePasswordRequest> CREATOR = new zzh();
  final int mVersionCode;
  private final PasswordSpecification zzVM;
  
  GeneratePasswordRequest(int paramInt, PasswordSpecification paramPasswordSpecification)
  {
    this.mVersionCode = paramInt;
    this.zzVM = paramPasswordSpecification;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public PasswordSpecification zzmr()
  {
    return this.zzVM;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\credentials\internal\GeneratePasswordRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */