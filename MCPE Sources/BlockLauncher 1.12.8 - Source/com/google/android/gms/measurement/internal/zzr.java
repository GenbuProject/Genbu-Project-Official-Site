package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzx;

class zzr
  extends BroadcastReceiver
{
  static final String zzSZ = zzr.class.getName();
  private boolean zzTa;
  private boolean zzTb;
  private final zzw zzaTV;
  
  zzr(zzw paramzzw)
  {
    zzx.zzz(paramzzw);
    this.zzaTV = paramzzw;
  }
  
  private Context getContext()
  {
    return this.zzaTV.getContext();
  }
  
  private zzp zzAo()
  {
    return this.zzaTV.zzAo();
  }
  
  @WorkerThread
  public boolean isRegistered()
  {
    this.zzaTV.zzjk();
    return this.zzTa;
  }
  
  @MainThread
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.zzaTV.zzjv();
    paramContext = paramIntent.getAction();
    zzAo().zzCK().zzj("NetworkBroadcastReceiver received action", paramContext);
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext))
    {
      final boolean bool = this.zzaTV.zzCW().zzlB();
      if (this.zzTb != bool)
      {
        this.zzTb = bool;
        this.zzaTV.zzCn().zzg(new Runnable()
        {
          public void run()
          {
            zzr.zza(zzr.this).zzJ(bool);
          }
        });
      }
      return;
    }
    zzAo().zzCF().zzj("NetworkBroadcastReceiver received unknown action", paramContext);
  }
  
  @WorkerThread
  public void unregister()
  {
    this.zzaTV.zzjv();
    this.zzaTV.zzjk();
    if (!isRegistered()) {
      return;
    }
    zzAo().zzCK().zzfg("Unregistering connectivity change receiver");
    this.zzTa = false;
    this.zzTb = false;
    Context localContext = getContext();
    try
    {
      localContext.unregisterReceiver(this);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      zzAo().zzCE().zzj("Failed to unregister the network broadcast receiver", localIllegalArgumentException);
    }
  }
  
  @WorkerThread
  public void zzly()
  {
    this.zzaTV.zzjv();
    this.zzaTV.zzjk();
    if (this.zzTa) {
      return;
    }
    getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    this.zzTb = this.zzaTV.zzCW().zzlB();
    zzAo().zzCK().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzTb));
    this.zzTa = true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */