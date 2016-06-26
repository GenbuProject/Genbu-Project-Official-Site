package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AddListenerRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<AddListenerRequest> CREATOR = new zzc();
  final int mVersionCode;
  public final zzaw zzbrB;
  public final IntentFilter[] zzbrC;
  public final String zzbrD;
  public final String zzbrE;
  
  AddListenerRequest(int paramInt, IBinder paramIBinder, IntentFilter[] paramArrayOfIntentFilter, String paramString1, String paramString2)
  {
    this.mVersionCode = paramInt;
    if (paramIBinder != null) {}
    for (this.zzbrB = zzaw.zza.zzet(paramIBinder);; this.zzbrB = null)
    {
      this.zzbrC = paramArrayOfIntentFilter;
      this.zzbrD = paramString1;
      this.zzbrE = paramString2;
      return;
    }
  }
  
  public AddListenerRequest(zzbq paramzzbq)
  {
    this.mVersionCode = 1;
    this.zzbrB = paramzzbq;
    this.zzbrC = paramzzbq.zzIO();
    this.zzbrD = paramzzbq.zzIP();
    this.zzbrE = null;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzIy()
  {
    if (this.zzbrB == null) {
      return null;
    }
    return this.zzbrB.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\AddListenerRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */