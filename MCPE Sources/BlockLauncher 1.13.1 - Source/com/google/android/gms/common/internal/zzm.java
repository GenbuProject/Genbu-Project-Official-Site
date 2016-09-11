package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.common.stats.zzb;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzm
  extends zzl
  implements Handler.Callback
{
  private final Handler mHandler;
  private final HashMap<zza, zzb> zzalZ = new HashMap();
  private final zzb zzama;
  private final long zzamb;
  private final Context zzsa;
  
  zzm(Context paramContext)
  {
    this.zzsa = paramContext.getApplicationContext();
    this.mHandler = new Handler(paramContext.getMainLooper(), this);
    this.zzama = zzb.zzrP();
    this.zzamb = 5000L;
  }
  
  private boolean zza(zza paramzza, ServiceConnection paramServiceConnection, String paramString)
  {
    zzx.zzb(paramServiceConnection, "ServiceConnection must not be null");
    for (;;)
    {
      zzb localzzb;
      synchronized (this.zzalZ)
      {
        localzzb = (zzb)this.zzalZ.get(paramzza);
        if (localzzb == null)
        {
          localzzb = new zzb(paramzza);
          localzzb.zza(paramServiceConnection, paramString);
          localzzb.zzcH(paramString);
          this.zzalZ.put(paramzza, localzzb);
          paramzza = localzzb;
          boolean bool = paramzza.isBound();
          return bool;
        }
        this.mHandler.removeMessages(0, localzzb);
        if (localzzb.zza(paramServiceConnection)) {
          throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + paramzza);
        }
      }
      localzzb.zza(paramServiceConnection, paramString);
      switch (localzzb.getState())
      {
      case 1: 
        paramServiceConnection.onServiceConnected(localzzb.getComponentName(), localzzb.getBinder());
        paramzza = localzzb;
        break;
      case 2: 
        localzzb.zzcH(paramString);
        paramzza = localzzb;
        break;
      default: 
        paramzza = localzzb;
      }
    }
  }
  
  private void zzb(zza paramzza, ServiceConnection paramServiceConnection, String paramString)
  {
    zzx.zzb(paramServiceConnection, "ServiceConnection must not be null");
    zzb localzzb;
    synchronized (this.zzalZ)
    {
      localzzb = (zzb)this.zzalZ.get(paramzza);
      if (localzzb == null) {
        throw new IllegalStateException("Nonexistent connection status for service config: " + paramzza);
      }
    }
    if (!localzzb.zza(paramServiceConnection)) {
      throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + paramzza);
    }
    localzzb.zzb(paramServiceConnection, paramString);
    if (localzzb.zzqT())
    {
      paramzza = this.mHandler.obtainMessage(0, localzzb);
      this.mHandler.sendMessageDelayed(paramzza, this.zzamb);
    }
  }
  
  public boolean handleMessage(Message arg1)
  {
    switch (???.what)
    {
    default: 
      return false;
    }
    zzb localzzb = (zzb)???.obj;
    synchronized (this.zzalZ)
    {
      if (localzzb.zzqT())
      {
        if (localzzb.isBound()) {
          localzzb.zzcI("GmsClientSupervisor");
        }
        this.zzalZ.remove(zzb.zza(localzzb));
      }
      return true;
    }
  }
  
  public boolean zza(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    return zza(new zza(paramComponentName), paramServiceConnection, paramString);
  }
  
  public boolean zza(String paramString1, ServiceConnection paramServiceConnection, String paramString2)
  {
    return zza(new zza(paramString1), paramServiceConnection, paramString2);
  }
  
  public void zzb(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString)
  {
    zzb(new zza(paramComponentName), paramServiceConnection, paramString);
  }
  
  public void zzb(String paramString1, ServiceConnection paramServiceConnection, String paramString2)
  {
    zzb(new zza(paramString1), paramServiceConnection, paramString2);
  }
  
  private static final class zza
  {
    private final String zzSU;
    private final ComponentName zzamc;
    
    public zza(ComponentName paramComponentName)
    {
      this.zzSU = null;
      this.zzamc = ((ComponentName)zzx.zzz(paramComponentName));
    }
    
    public zza(String paramString)
    {
      this.zzSU = zzx.zzcM(paramString);
      this.zzamc = null;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof zza)) {
          return false;
        }
        paramObject = (zza)paramObject;
      } while ((zzw.equal(this.zzSU, ((zza)paramObject).zzSU)) && (zzw.equal(this.zzamc, ((zza)paramObject).zzamc)));
      return false;
    }
    
    public int hashCode()
    {
      return zzw.hashCode(new Object[] { this.zzSU, this.zzamc });
    }
    
    public String toString()
    {
      if (this.zzSU == null) {
        return this.zzamc.flattenToString();
      }
      return this.zzSU;
    }
    
    public Intent zzqS()
    {
      if (this.zzSU != null) {
        return new Intent(this.zzSU).setPackage("com.google.android.gms");
      }
      return new Intent().setComponent(this.zzamc);
    }
  }
  
  private final class zzb
  {
    private int mState;
    private IBinder zzakD;
    private ComponentName zzamc;
    private final zza zzamd;
    private final Set<ServiceConnection> zzame;
    private boolean zzamf;
    private final zzm.zza zzamg;
    
    public zzb(zzm.zza paramzza)
    {
      this.zzamg = paramzza;
      this.zzamd = new zza();
      this.zzame = new HashSet();
      this.mState = 2;
    }
    
    public IBinder getBinder()
    {
      return this.zzakD;
    }
    
    public ComponentName getComponentName()
    {
      return this.zzamc;
    }
    
    public int getState()
    {
      return this.mState;
    }
    
    public boolean isBound()
    {
      return this.zzamf;
    }
    
    public void zza(ServiceConnection paramServiceConnection, String paramString)
    {
      zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), paramServiceConnection, paramString, this.zzamg.zzqS());
      this.zzame.add(paramServiceConnection);
    }
    
    public boolean zza(ServiceConnection paramServiceConnection)
    {
      return this.zzame.contains(paramServiceConnection);
    }
    
    public void zzb(ServiceConnection paramServiceConnection, String paramString)
    {
      zzm.zzc(zzm.this).zzb(zzm.zzb(zzm.this), paramServiceConnection);
      this.zzame.remove(paramServiceConnection);
    }
    
    @TargetApi(14)
    public void zzcH(String paramString)
    {
      this.mState = 3;
      this.zzamf = zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), paramString, this.zzamg.zzqS(), this.zzamd, 129);
      if (!this.zzamf) {
        this.mState = 2;
      }
      try
      {
        zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), this.zzamd);
        return;
      }
      catch (IllegalArgumentException paramString) {}
    }
    
    public void zzcI(String paramString)
    {
      zzm.zzc(zzm.this).zza(zzm.zzb(zzm.this), this.zzamd);
      this.zzamf = false;
      this.mState = 2;
    }
    
    public boolean zzqT()
    {
      return this.zzame.isEmpty();
    }
    
    public class zza
      implements ServiceConnection
    {
      public zza() {}
      
      public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        synchronized (zzm.zza(zzm.this))
        {
          zzm.zzb.zza(zzm.zzb.this, paramIBinder);
          zzm.zzb.zza(zzm.zzb.this, paramComponentName);
          Iterator localIterator = zzm.zzb.zzb(zzm.zzb.this).iterator();
          if (localIterator.hasNext()) {
            ((ServiceConnection)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
          }
        }
        zzm.zzb.zza(zzm.zzb.this, 1);
      }
      
      public void onServiceDisconnected(ComponentName paramComponentName)
      {
        synchronized (zzm.zza(zzm.this))
        {
          zzm.zzb.zza(zzm.zzb.this, null);
          zzm.zzb.zza(zzm.zzb.this, paramComponentName);
          Iterator localIterator = zzm.zzb.zzb(zzm.zzb.this).iterator();
          if (localIterator.hasNext()) {
            ((ServiceConnection)localIterator.next()).onServiceDisconnected(paramComponentName);
          }
        }
        zzm.zzb.zza(zzm.zzb.this, 2);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\internal\zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */