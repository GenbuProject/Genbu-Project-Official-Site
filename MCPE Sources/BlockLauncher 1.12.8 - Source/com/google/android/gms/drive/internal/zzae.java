package com.google.android.gms.drive.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.ChangesAvailableEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.QueryResultEventParcelable;
import com.google.android.gms.drive.events.TransferProgressEvent;
import com.google.android.gms.drive.events.internal.zza;
import com.google.android.gms.drive.events.zzc;
import com.google.android.gms.drive.events.zzf;
import com.google.android.gms.drive.events.zzh;
import com.google.android.gms.drive.events.zzi;
import com.google.android.gms.drive.events.zzk;
import com.google.android.gms.drive.events.zzm;
import java.util.ArrayList;
import java.util.List;

public class zzae
  extends zzao.zza
{
  private final int zzanf;
  private final zzf zzarC;
  private final zza zzarD;
  private final List<Integer> zzarE = new ArrayList();
  
  public zzae(Looper paramLooper, Context paramContext, int paramInt, zzf paramzzf)
  {
    this.zzanf = paramInt;
    this.zzarC = paramzzf;
    this.zzarD = new zza(paramLooper, paramContext, null);
  }
  
  public void zzc(OnEventResponse paramOnEventResponse)
    throws RemoteException
  {
    paramOnEventResponse = paramOnEventResponse.zzts();
    if (this.zzanf == paramOnEventResponse.getType()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzab(bool);
      zzx.zzab(this.zzarE.contains(Integer.valueOf(paramOnEventResponse.getType())));
      this.zzarD.zza(this.zzarC, paramOnEventResponse);
      return;
    }
  }
  
  public void zzdg(int paramInt)
  {
    this.zzarE.add(Integer.valueOf(paramInt));
  }
  
  public boolean zzdh(int paramInt)
  {
    return this.zzarE.contains(Integer.valueOf(paramInt));
  }
  
  private static class zza
    extends Handler
  {
    private final Context mContext;
    
    private zza(Looper paramLooper, Context paramContext)
    {
      super();
      this.mContext = paramContext;
    }
    
    private static void zza(zzm paramzzm, QueryResultEventParcelable paramQueryResultEventParcelable)
    {
      DataHolder localDataHolder = paramQueryResultEventParcelable.zzsX();
      if (localDataHolder != null) {
        paramzzm.zza(new zzk() {});
      }
      if (paramQueryResultEventParcelable.zzsY()) {
        paramzzm.zzcJ(paramQueryResultEventParcelable.zzsZ());
      }
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        zzz.zze(this.mContext, "EventCallback", "Don't know how to handle this event");
        return;
      }
      Object localObject = (Pair)paramMessage.obj;
      paramMessage = (zzf)((Pair)localObject).first;
      localObject = (DriveEvent)((Pair)localObject).second;
      switch (((DriveEvent)localObject).getType())
      {
      case 5: 
      case 6: 
      case 7: 
      default: 
        zzz.zzz("EventCallback", "Unexpected event: " + localObject);
        return;
      case 1: 
        ((ChangeListener)paramMessage).onChange((ChangeEvent)localObject);
        return;
      case 2: 
        ((CompletionListener)paramMessage).onCompletion((CompletionEvent)localObject);
        return;
      case 3: 
        zza((zzm)paramMessage, (QueryResultEventParcelable)localObject);
        return;
      case 4: 
        ((zzc)paramMessage).zza((ChangesAvailableEvent)localObject);
        return;
      }
      localObject = new zza(((TransferProgressEvent)localObject).zzta());
      ((zzi)paramMessage).zza((zzh)localObject);
    }
    
    public void zza(zzf paramzzf, DriveEvent paramDriveEvent)
    {
      sendMessage(obtainMessage(1, new Pair(paramzzf, paramDriveEvent)));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */