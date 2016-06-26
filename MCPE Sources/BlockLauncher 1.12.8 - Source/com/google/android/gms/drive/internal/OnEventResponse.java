package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangesAvailableEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.QueryResultEventParcelable;
import com.google.android.gms.drive.events.TransferProgressEvent;
import com.google.android.gms.drive.events.TransferStateEvent;

public class OnEventResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnEventResponse> CREATOR = new zzba();
  final int mVersionCode;
  final int zzanf;
  final ChangeEvent zzasl;
  final CompletionEvent zzasm;
  final QueryResultEventParcelable zzasn;
  final ChangesAvailableEvent zzaso;
  final TransferStateEvent zzasp;
  final TransferProgressEvent zzasq;
  
  OnEventResponse(int paramInt1, int paramInt2, ChangeEvent paramChangeEvent, CompletionEvent paramCompletionEvent, QueryResultEventParcelable paramQueryResultEventParcelable, ChangesAvailableEvent paramChangesAvailableEvent, TransferStateEvent paramTransferStateEvent, TransferProgressEvent paramTransferProgressEvent)
  {
    this.mVersionCode = paramInt1;
    this.zzanf = paramInt2;
    this.zzasl = paramChangeEvent;
    this.zzasm = paramCompletionEvent;
    this.zzasn = paramQueryResultEventParcelable;
    this.zzaso = paramChangesAvailableEvent;
    this.zzasp = paramTransferStateEvent;
    this.zzasq = paramTransferProgressEvent;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzba.zza(this, paramParcel, paramInt);
  }
  
  public DriveEvent zzts()
  {
    switch (this.zzanf)
    {
    case 5: 
    case 6: 
    default: 
      throw new IllegalStateException("Unexpected event type " + this.zzanf);
    case 1: 
      return this.zzasl;
    case 2: 
      return this.zzasm;
    case 3: 
      return this.zzasn;
    case 4: 
      return this.zzaso;
    case 7: 
      return this.zzasp;
    }
    return this.zzasq;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\OnEventResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */