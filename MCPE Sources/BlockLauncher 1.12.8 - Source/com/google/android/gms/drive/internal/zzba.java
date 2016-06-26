package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangesAvailableEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.QueryResultEventParcelable;
import com.google.android.gms.drive.events.TransferProgressEvent;
import com.google.android.gms.drive.events.TransferStateEvent;

public class zzba
  implements Parcelable.Creator<OnEventResponse>
{
  static void zza(OnEventResponse paramOnEventResponse, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramOnEventResponse.mVersionCode);
    zzb.zzc(paramParcel, 2, paramOnEventResponse.zzanf);
    zzb.zza(paramParcel, 3, paramOnEventResponse.zzasl, paramInt, false);
    zzb.zza(paramParcel, 5, paramOnEventResponse.zzasm, paramInt, false);
    zzb.zza(paramParcel, 6, paramOnEventResponse.zzasn, paramInt, false);
    zzb.zza(paramParcel, 7, paramOnEventResponse.zzaso, paramInt, false);
    zzb.zza(paramParcel, 9, paramOnEventResponse.zzasp, paramInt, false);
    zzb.zza(paramParcel, 10, paramOnEventResponse.zzasq, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public OnEventResponse zzbD(Parcel paramParcel)
  {
    int i = 0;
    TransferProgressEvent localTransferProgressEvent = null;
    int k = zza.zzau(paramParcel);
    TransferStateEvent localTransferStateEvent = null;
    ChangesAvailableEvent localChangesAvailableEvent = null;
    QueryResultEventParcelable localQueryResultEventParcelable = null;
    CompletionEvent localCompletionEvent = null;
    ChangeEvent localChangeEvent = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      case 4: 
      case 8: 
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        i = zza.zzg(paramParcel, m);
        break;
      case 3: 
        localChangeEvent = (ChangeEvent)zza.zza(paramParcel, m, ChangeEvent.CREATOR);
        break;
      case 5: 
        localCompletionEvent = (CompletionEvent)zza.zza(paramParcel, m, CompletionEvent.CREATOR);
        break;
      case 6: 
        localQueryResultEventParcelable = (QueryResultEventParcelable)zza.zza(paramParcel, m, QueryResultEventParcelable.CREATOR);
        break;
      case 7: 
        localChangesAvailableEvent = (ChangesAvailableEvent)zza.zza(paramParcel, m, ChangesAvailableEvent.CREATOR);
        break;
      case 9: 
        localTransferStateEvent = (TransferStateEvent)zza.zza(paramParcel, m, TransferStateEvent.CREATOR);
        break;
      case 10: 
        localTransferProgressEvent = (TransferProgressEvent)zza.zza(paramParcel, m, TransferProgressEvent.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new OnEventResponse(j, i, localChangeEvent, localCompletionEvent, localQueryResultEventParcelable, localChangesAvailableEvent, localTransferStateEvent, localTransferProgressEvent);
  }
  
  public OnEventResponse[] zzdy(int paramInt)
  {
    return new OnEventResponse[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */