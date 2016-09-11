package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

public class zzx<R extends Result>
  extends TransformedResult<R>
  implements ResultCallback<R>
{
  private final Object zzagI = new Object();
  private final WeakReference<GoogleApiClient> zzagK;
  private ResultTransform<? super R, ? extends Result> zzaiN = null;
  private zzx<? extends Result> zzaiO = null;
  private ResultCallbacks<? super R> zzaiP = null;
  private PendingResult<R> zzaiQ = null;
  private Status zzaiR = null;
  private final zzx<R>.zza zzaiS;
  
  public zzx(WeakReference<GoogleApiClient> paramWeakReference)
  {
    com.google.android.gms.common.internal.zzx.zzb(paramWeakReference, "GoogleApiClient reference must not be null");
    this.zzagK = paramWeakReference;
    paramWeakReference = (GoogleApiClient)this.zzagK.get();
    if (paramWeakReference != null) {}
    for (paramWeakReference = paramWeakReference.getLooper();; paramWeakReference = Looper.getMainLooper())
    {
      this.zzaiS = new zza(paramWeakReference);
      return;
    }
  }
  
  private void zzc(Result paramResult)
  {
    if ((paramResult instanceof Releasable)) {}
    try
    {
      ((Releasable)paramResult).release();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w("TransformedResultImpl", "Unable to release " + paramResult, localRuntimeException);
    }
  }
  
  private void zzpT()
  {
    if ((this.zzaiN == null) && (this.zzaiP == null)) {}
    do
    {
      return;
      GoogleApiClient localGoogleApiClient = (GoogleApiClient)this.zzagK.get();
      if ((this.zzaiN != null) && (localGoogleApiClient != null)) {
        localGoogleApiClient.zza(this);
      }
      if (this.zzaiR != null)
      {
        zzz(this.zzaiR);
        return;
      }
    } while (this.zzaiQ == null);
    this.zzaiQ.setResultCallback(this);
  }
  
  private boolean zzpV()
  {
    GoogleApiClient localGoogleApiClient = (GoogleApiClient)this.zzagK.get();
    return (this.zzaiP != null) && (localGoogleApiClient != null);
  }
  
  private void zzy(Status paramStatus)
  {
    synchronized (this.zzagI)
    {
      this.zzaiR = paramStatus;
      zzz(this.zzaiR);
      return;
    }
  }
  
  private void zzz(Status paramStatus)
  {
    synchronized (this.zzagI)
    {
      if (this.zzaiN != null)
      {
        paramStatus = this.zzaiN.onFailure(paramStatus);
        com.google.android.gms.common.internal.zzx.zzb(paramStatus, "onFailure must not return null");
        this.zzaiO.zzy(paramStatus);
      }
      while (!zzpV()) {
        return;
      }
      this.zzaiP.onFailure(paramStatus);
    }
  }
  
  public void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks)
  {
    boolean bool2 = true;
    for (;;)
    {
      synchronized (this.zzagI)
      {
        if (this.zzaiP == null)
        {
          bool1 = true;
          com.google.android.gms.common.internal.zzx.zza(bool1, "Cannot call andFinally() twice.");
          if (this.zzaiN != null) {
            break label65;
          }
          bool1 = bool2;
          com.google.android.gms.common.internal.zzx.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          this.zzaiP = paramResultCallbacks;
          zzpT();
          return;
        }
      }
      boolean bool1 = false;
      continue;
      label65:
      bool1 = false;
    }
  }
  
  public void onResult(final R paramR)
  {
    for (;;)
    {
      synchronized (this.zzagI)
      {
        if (paramR.getStatus().isSuccess())
        {
          if (this.zzaiN != null)
          {
            zzs.zzpN().submit(new Runnable()
            {
              @WorkerThread
              public void run()
              {
                try
                {
                  Object localObject1 = zzx.zzc(zzx.this).onSuccess(paramR);
                  zzx.zzd(zzx.this).sendMessage(zzx.zzd(zzx.this).obtainMessage(0, localObject1));
                  zzx.zza(zzx.this, paramR);
                  localObject1 = (GoogleApiClient)zzx.zze(zzx.this).get();
                  if (localObject1 != null) {
                    ((GoogleApiClient)localObject1).zzb(zzx.this);
                  }
                  return;
                }
                catch (RuntimeException localRuntimeException)
                {
                  zzx.zzd(zzx.this).sendMessage(zzx.zzd(zzx.this).obtainMessage(1, localRuntimeException));
                  GoogleApiClient localGoogleApiClient1;
                  return;
                }
                finally
                {
                  zzx.zza(zzx.this, paramR);
                  GoogleApiClient localGoogleApiClient2 = (GoogleApiClient)zzx.zze(zzx.this).get();
                  if (localGoogleApiClient2 != null) {
                    localGoogleApiClient2.zzb(zzx.this);
                  }
                }
              }
            });
            return;
          }
          if (!zzpV()) {
            continue;
          }
          this.zzaiP.onSuccess(paramR);
        }
      }
      zzy(paramR.getStatus());
      zzc(paramR);
    }
  }
  
  @NonNull
  public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform)
  {
    boolean bool2 = true;
    for (;;)
    {
      synchronized (this.zzagI)
      {
        if (this.zzaiN == null)
        {
          bool1 = true;
          com.google.android.gms.common.internal.zzx.zza(bool1, "Cannot call then() twice.");
          if (this.zzaiP != null) {
            break label83;
          }
          bool1 = bool2;
          com.google.android.gms.common.internal.zzx.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          this.zzaiN = paramResultTransform;
          paramResultTransform = new zzx(this.zzagK);
          this.zzaiO = paramResultTransform;
          zzpT();
          return paramResultTransform;
        }
      }
      boolean bool1 = false;
      continue;
      label83:
      bool1 = false;
    }
  }
  
  public void zza(PendingResult<?> paramPendingResult)
  {
    synchronized (this.zzagI)
    {
      this.zzaiQ = paramPendingResult;
      zzpT();
      return;
    }
  }
  
  void zzpU()
  {
    synchronized (this.zzagI)
    {
      this.zzaiP = null;
      return;
    }
  }
  
  private final class zza
    extends Handler
  {
    public zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + paramMessage.what);
        return;
      case 0: 
        PendingResult localPendingResult1 = (PendingResult)paramMessage.obj;
        paramMessage = zzx.zzf(zzx.this);
        if (localPendingResult1 == null) {}
        for (;;)
        {
          try
          {
            zzx.zza(zzx.zzg(zzx.this), new Status(13, "Transform returned null"));
            return;
          }
          finally {}
          if ((localPendingResult2 instanceof zzt)) {
            zzx.zza(zzx.zzg(zzx.this), ((zzt)localPendingResult2).getStatus());
          } else {
            zzx.zzg(zzx.this).zza(localPendingResult2);
          }
        }
      }
      paramMessage = (RuntimeException)paramMessage.obj;
      Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: " + paramMessage.getMessage());
      throw paramMessage;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\api\internal\zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */