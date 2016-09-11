package com.google.android.gms.common.api;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api<O extends ApiOptions>
{
  private final String mName;
  private final zzc<?> zzaeE;
  private final zza<?, O> zzafW;
  private final zze<?, O> zzafX;
  private final zzf<?> zzafY;
  
  public <C extends zzb> Api(String paramString, zza<C, O> paramzza, zzc<C> paramzzc)
  {
    zzx.zzb(paramzza, "Cannot construct an Api with a null ClientBuilder");
    zzx.zzb(paramzzc, "Cannot construct an Api with a null ClientKey");
    this.mName = paramString;
    this.zzafW = paramzza;
    this.zzafX = null;
    this.zzaeE = paramzzc;
    this.zzafY = null;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public zza<?, O> zzoP()
  {
    if (this.zzafW != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
      return this.zzafW;
    }
  }
  
  public zze<?, O> zzoQ()
  {
    if (this.zzafX != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "This API was constructed with a ClientBuilder. Use getClientBuilder");
      return this.zzafX;
    }
  }
  
  public zzc<?> zzoR()
  {
    if (this.zzaeE != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
      return this.zzaeE;
    }
  }
  
  public boolean zzoS()
  {
    return this.zzafY != null;
  }
  
  public static abstract interface ApiOptions
  {
    public static abstract interface HasOptions
      extends Api.ApiOptions
    {}
    
    public static final class NoOptions
      implements Api.ApiOptions.NotRequiredOptions
    {}
    
    public static abstract interface NotRequiredOptions
      extends Api.ApiOptions
    {}
    
    public static abstract interface Optional
      extends Api.ApiOptions.HasOptions, Api.ApiOptions.NotRequiredOptions
    {}
  }
  
  public static abstract class zza<T extends Api.zzb, O>
  {
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
    
    public abstract T zza(Context paramContext, Looper paramLooper, zzf paramzzf, O paramO, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener);
    
    public List<Scope> zzo(O paramO)
    {
      return Collections.emptyList();
    }
  }
  
  public static abstract interface zzb
  {
    public abstract void disconnect();
    
    public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
    
    public abstract boolean isConnected();
    
    public abstract void zza(GoogleApiClient.zza paramzza);
    
    public abstract void zza(zzp paramzzp, Set<Scope> paramSet);
    
    public abstract boolean zzmE();
    
    public abstract boolean zznb();
    
    public abstract Intent zznc();
    
    public abstract IBinder zzoT();
  }
  
  public static final class zzc<C extends Api.zzb> {}
  
  public static abstract interface zzd<T extends IInterface>
  {
    public abstract T zzW(IBinder paramIBinder);
    
    public abstract void zza(int paramInt, T paramT);
    
    public abstract String zzgu();
    
    public abstract String zzgv();
  }
  
  public static abstract interface zze<T extends Api.zzd, O>
  {
    public abstract int getPriority();
    
    public abstract int zzoU();
    
    public abstract T zzq(O paramO);
  }
  
  public static final class zzf<C extends Api.zzd> {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\api\Api.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */