package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class zzg
  implements zzk
{
  private final zzl zzahj;
  private boolean zzahk = false;
  
  public zzg(zzl paramzzl)
  {
    this.zzahj = paramzzl;
  }
  
  private <A extends Api.zzb> void zza(zzj.zze<A> paramzze)
    throws DeadObjectException
  {
    this.zzahj.zzagW.zzb(paramzze);
    Api.zzb localzzb = this.zzahj.zzagW.zza(paramzze.zzoR());
    if ((!localzzb.isConnected()) && (this.zzahj.zzaio.containsKey(paramzze.zzoR())))
    {
      paramzze.zzw(new Status(17));
      return;
    }
    paramzze.zzb(localzzb);
  }
  
  public void begin() {}
  
  public void connect()
  {
    if (this.zzahk)
    {
      this.zzahk = false;
      this.zzahj.zza(new zzl.zza(this)
      {
        public void zzpt()
        {
          zzg.zza(zzg.this).zzais.zzi(null);
        }
      });
    }
  }
  
  public boolean disconnect()
  {
    if (this.zzahk) {
      return false;
    }
    if (this.zzahj.zzagW.zzpG())
    {
      this.zzahk = true;
      Iterator localIterator = this.zzahj.zzagW.zzaia.iterator();
      while (localIterator.hasNext()) {
        ((zzx)localIterator.next()).zzpU();
      }
      return false;
    }
    this.zzahj.zzh(null);
    return true;
  }
  
  public void onConnected(Bundle paramBundle) {}
  
  public void onConnectionSuspended(int paramInt)
  {
    this.zzahj.zzh(null);
    this.zzahj.zzais.zzc(paramInt, this.zzahk);
  }
  
  public <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(T paramT)
  {
    return zzb(paramT);
  }
  
  public void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt) {}
  
  public <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(T paramT)
  {
    try
    {
      zza(paramT);
      return paramT;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      this.zzahj.zza(new zzl.zza(this)
      {
        public void zzpt()
        {
          zzg.this.onConnectionSuspended(1);
        }
      });
    }
    return paramT;
  }
  
  void zzps()
  {
    if (this.zzahk)
    {
      this.zzahk = false;
      this.zzahj.zzagW.zzaa(false);
      disconnect();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\api\internal\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */