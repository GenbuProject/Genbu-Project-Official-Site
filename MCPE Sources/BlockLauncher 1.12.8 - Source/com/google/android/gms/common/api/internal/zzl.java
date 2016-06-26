package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzrn;
import com.google.android.gms.internal.zzro;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzl
  implements zzp
{
  private final Context mContext;
  private final Lock zzXG;
  final zzj zzagW;
  private final com.google.android.gms.common.zzc zzags;
  final Api.zza<? extends zzrn, zzro> zzagt;
  final Map<Api<?>, Integer> zzahA;
  final Map<Api.zzc<?>, Api.zzb> zzahT;
  final zzf zzahz;
  private final Condition zzaim;
  private final zzb zzain;
  final Map<Api.zzc<?>, ConnectionResult> zzaio = new HashMap();
  private volatile zzk zzaip;
  private ConnectionResult zzaiq = null;
  int zzair;
  final zzp.zza zzais;
  
  public zzl(Context paramContext, zzj paramzzj, Lock paramLock, Looper paramLooper, com.google.android.gms.common.zzc paramzzc, Map<Api.zzc<?>, Api.zzb> paramMap, zzf paramzzf, Map<Api<?>, Integer> paramMap1, Api.zza<? extends zzrn, zzro> paramzza, ArrayList<zzc> paramArrayList, zzp.zza paramzza1)
  {
    this.mContext = paramContext;
    this.zzXG = paramLock;
    this.zzags = paramzzc;
    this.zzahT = paramMap;
    this.zzahz = paramzzf;
    this.zzahA = paramMap1;
    this.zzagt = paramzza;
    this.zzagW = paramzzj;
    this.zzais = paramzza1;
    paramContext = paramArrayList.iterator();
    while (paramContext.hasNext()) {
      ((zzc)paramContext.next()).zza(this);
    }
    this.zzain = new zzb(paramLooper);
    this.zzaim = paramLock.newCondition();
    this.zzaip = new zzi(this);
  }
  
  public ConnectionResult blockingConnect()
  {
    connect();
    while (isConnecting()) {
      try
      {
        this.zzaim.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      }
    }
    if (isConnected()) {
      return ConnectionResult.zzafB;
    }
    if (this.zzaiq != null) {
      return this.zzaiq;
    }
    return new ConnectionResult(13, null);
  }
  
  public ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    connect();
    for (paramLong = paramTimeUnit.toNanos(paramLong); isConnecting(); paramLong = this.zzaim.awaitNanos(paramLong))
    {
      if (paramLong <= 0L) {}
      try
      {
        disconnect();
        return new ConnectionResult(14, null);
      }
      catch (InterruptedException paramTimeUnit)
      {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      }
    }
    if (isConnected()) {
      return ConnectionResult.zzafB;
    }
    if (this.zzaiq != null) {
      return this.zzaiq;
    }
    return new ConnectionResult(13, null);
  }
  
  public void connect()
  {
    this.zzaip.connect();
  }
  
  public boolean disconnect()
  {
    boolean bool = this.zzaip.disconnect();
    if (bool) {
      this.zzaio.clear();
    }
    return bool;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    String str = paramString + "  ";
    Iterator localIterator = this.zzahA.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      paramPrintWriter.append(paramString).append(localApi.getName()).println(":");
      ((Api.zzb)this.zzahT.get(localApi.zzoR())).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  @Nullable
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi)
  {
    paramApi = paramApi.zzoR();
    if (this.zzahT.containsKey(paramApi))
    {
      if (((Api.zzb)this.zzahT.get(paramApi)).isConnected()) {
        return ConnectionResult.zzafB;
      }
      if (this.zzaio.containsKey(paramApi)) {
        return (ConnectionResult)this.zzaio.get(paramApi);
      }
    }
    return null;
  }
  
  public boolean isConnected()
  {
    return this.zzaip instanceof zzg;
  }
  
  public boolean isConnecting()
  {
    return this.zzaip instanceof zzh;
  }
  
  public void onConnected(@Nullable Bundle paramBundle)
  {
    this.zzXG.lock();
    try
    {
      this.zzaip.onConnected(paramBundle);
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    this.zzXG.lock();
    try
    {
      this.zzaip.onConnectionSuspended(paramInt);
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(@NonNull T paramT)
  {
    return this.zzaip.zza(paramT);
  }
  
  public void zza(@NonNull ConnectionResult paramConnectionResult, @NonNull Api<?> paramApi, int paramInt)
  {
    this.zzXG.lock();
    try
    {
      this.zzaip.zza(paramConnectionResult, paramApi, paramInt);
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  void zza(zza paramzza)
  {
    paramzza = this.zzain.obtainMessage(1, paramzza);
    this.zzain.sendMessage(paramzza);
  }
  
  void zza(RuntimeException paramRuntimeException)
  {
    paramRuntimeException = this.zzain.obtainMessage(2, paramRuntimeException);
    this.zzain.sendMessage(paramRuntimeException);
  }
  
  public boolean zza(zzu paramzzu)
  {
    return false;
  }
  
  public <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    return this.zzaip.zzb(paramT);
  }
  
  void zzh(ConnectionResult paramConnectionResult)
  {
    this.zzXG.lock();
    try
    {
      this.zzaiq = paramConnectionResult;
      this.zzaip = new zzi(this);
      this.zzaip.begin();
      this.zzaim.signalAll();
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public void zzoW() {}
  
  void zzpK()
  {
    this.zzXG.lock();
    try
    {
      this.zzaip = new zzh(this, this.zzahz, this.zzahA, this.zzags, this.zzagt, this.zzXG, this.mContext);
      this.zzaip.begin();
      this.zzaim.signalAll();
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  void zzpL()
  {
    this.zzXG.lock();
    try
    {
      this.zzagW.zzpF();
      this.zzaip = new zzg(this);
      this.zzaip.begin();
      this.zzaim.signalAll();
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  void zzpM()
  {
    Iterator localIterator = this.zzahT.values().iterator();
    while (localIterator.hasNext()) {
      ((Api.zzb)localIterator.next()).disconnect();
    }
  }
  
  public void zzpj()
  {
    if (isConnected()) {
      ((zzg)this.zzaip).zzps();
    }
  }
  
  static abstract class zza
  {
    private final zzk zzait;
    
    protected zza(zzk paramzzk)
    {
      this.zzait = paramzzk;
    }
    
    public final void zzd(zzl paramzzl)
    {
      zzl.zzb(paramzzl).lock();
      try
      {
        zzk localzzk1 = zzl.zzc(paramzzl);
        zzk localzzk2 = this.zzait;
        if (localzzk1 != localzzk2) {
          return;
        }
        zzpt();
        return;
      }
      finally
      {
        zzl.zzb(paramzzl).unlock();
      }
    }
    
    protected abstract void zzpt();
  }
  
  final class zzb
    extends Handler
  {
    zzb(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.w("GACStateManager", "Unknown message id: " + paramMessage.what);
        return;
      case 1: 
        ((zzl.zza)paramMessage.obj).zzd(zzl.this);
        return;
      }
      throw ((RuntimeException)paramMessage.obj);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */