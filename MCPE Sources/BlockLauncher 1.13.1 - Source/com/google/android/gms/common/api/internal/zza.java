package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.atomic.AtomicReference;

public class zza
{
  public static abstract class zza<R extends Result, A extends Api.zzb>
    extends zzb<R>
    implements zza.zzb<R>, zzj.zze<A>
  {
    private final Api.zzc<A> zzaeE;
    private AtomicReference<zzj.zzd> zzagH = new AtomicReference();
    
    protected zza(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super();
      this.zzaeE = ((Api.zzc)zzx.zzz(paramzzc));
    }
    
    private void zza(RemoteException paramRemoteException)
    {
      zzw(new Status(8, paramRemoteException.getLocalizedMessage(), null));
    }
    
    protected abstract void zza(A paramA)
      throws RemoteException;
    
    public void zza(zzj.zzd paramzzd)
    {
      this.zzagH.set(paramzzd);
    }
    
    public final void zzb(A paramA)
      throws DeadObjectException
    {
      try
      {
        zza(paramA);
        return;
      }
      catch (DeadObjectException paramA)
      {
        zza(paramA);
        throw paramA;
      }
      catch (RemoteException paramA)
      {
        zza(paramA);
      }
    }
    
    public final Api.zzc<A> zzoR()
    {
      return this.zzaeE;
    }
    
    public void zzpe()
    {
      setResultCallback(null);
    }
    
    protected void zzpf()
    {
      zzj.zzd localzzd = (zzj.zzd)this.zzagH.getAndSet(null);
      if (localzzd != null) {
        localzzd.zzc(this);
      }
    }
    
    public final void zzw(Status paramStatus)
    {
      if (!paramStatus.isSuccess()) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Failed result must not be success");
        zza(zzc(paramStatus));
        return;
      }
    }
  }
  
  public static abstract interface zzb<R>
  {
    public abstract void zzs(R paramR);
    
    public abstract void zzw(Status paramStatus);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\api\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */