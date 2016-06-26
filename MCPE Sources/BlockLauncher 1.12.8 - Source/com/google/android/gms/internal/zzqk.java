package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.api.internal.zzq.zzb;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;
import com.google.android.gms.nearby.connection.Connections.ConnectionResponseCallback;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;
import com.google.android.gms.nearby.connection.Connections.MessageListener;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

public final class zzqk
  extends zzj<zzqn>
{
  private final long zzaEg = hashCode();
  
  public zzqk(Context paramContext, Looper paramLooper, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 54, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public void disconnect()
  {
    if (isConnected()) {}
    try
    {
      ((zzqn)zzqJ()).zzF(this.zzaEg);
      super.disconnect();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.w("NearbyConnectionsClient", "Failed to notify client disconnect.", localRemoteException);
      }
    }
  }
  
  public String zzEj()
  {
    try
    {
      String str = ((zzqn)zzqJ()).zzaj(this.zzaEg);
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException(localRemoteException);
    }
  }
  
  public String zzEk()
  {
    try
    {
      String str = ((zzqn)zzqJ()).zzEk();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException(localRemoteException);
    }
  }
  
  public void zzEl()
  {
    try
    {
      ((zzqn)zzqJ()).zzag(this.zzaEg);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("NearbyConnectionsClient", "Couldn't stop advertising", localRemoteException);
    }
  }
  
  public void zzEm()
  {
    try
    {
      ((zzqn)zzqJ()).zzai(this.zzaEg);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("NearbyConnectionsClient", "Couldn't stop all endpoints", localRemoteException);
    }
  }
  
  public void zza(zza.zzb<Status> paramzzb, String paramString, long paramLong, zzq<Connections.EndpointDiscoveryListener> paramzzq)
    throws RemoteException
  {
    ((zzqn)zzqJ()).zza(new zzg(paramzzb, paramzzq), paramString, paramLong, this.zzaEg);
  }
  
  public void zza(zza.zzb<Connections.StartAdvertisingResult> paramzzb, String paramString, AppMetadata paramAppMetadata, long paramLong, zzq<Connections.ConnectionRequestListener> paramzzq)
    throws RemoteException
  {
    ((zzqn)zzqJ()).zza(new zze(paramzzb, paramzzq), paramString, paramAppMetadata, paramLong, this.zzaEg);
  }
  
  public void zza(zza.zzb<Status> paramzzb, String paramString1, String paramString2, byte[] paramArrayOfByte, zzq<Connections.ConnectionResponseCallback> paramzzq, zzq<Connections.MessageListener> paramzzq1)
    throws RemoteException
  {
    ((zzqn)zzqJ()).zza(new zzd(paramzzb, paramzzq, paramzzq1), paramString1, paramString2, paramArrayOfByte, this.zzaEg);
  }
  
  public void zza(zza.zzb<Status> paramzzb, String paramString, byte[] paramArrayOfByte, zzq<Connections.MessageListener> paramzzq)
    throws RemoteException
  {
    ((zzqn)zzqJ()).zza(new zza(paramzzb, paramzzq), paramString, paramArrayOfByte, this.zzaEg);
  }
  
  public void zza(String[] paramArrayOfString, byte[] paramArrayOfByte)
  {
    try
    {
      ((zzqn)zzqJ()).zza(paramArrayOfString, paramArrayOfByte, this.zzaEg);
      return;
    }
    catch (RemoteException paramArrayOfString)
    {
      Log.w("NearbyConnectionsClient", "Couldn't send reliable message", paramArrayOfString);
    }
  }
  
  public void zzb(String[] paramArrayOfString, byte[] paramArrayOfByte)
  {
    try
    {
      ((zzqn)zzqJ()).zzb(paramArrayOfString, paramArrayOfByte, this.zzaEg);
      return;
    }
    catch (RemoteException paramArrayOfString)
    {
      Log.w("NearbyConnectionsClient", "Couldn't send unreliable message", paramArrayOfString);
    }
  }
  
  protected zzqn zzdv(IBinder paramIBinder)
  {
    return zzqn.zza.zzdx(paramIBinder);
  }
  
  public void zzfA(String paramString)
  {
    try
    {
      ((zzqn)zzqJ()).zzi(paramString, this.zzaEg);
      return;
    }
    catch (RemoteException paramString)
    {
      Log.w("NearbyConnectionsClient", "Couldn't disconnect from endpoint", paramString);
    }
  }
  
  public void zzfz(String paramString)
  {
    try
    {
      ((zzqn)zzqJ()).zzh(paramString, this.zzaEg);
      return;
    }
    catch (RemoteException paramString)
    {
      Log.w("NearbyConnectionsClient", "Couldn't stop discovery", paramString);
    }
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.nearby.connection.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.nearby.internal.connection.INearbyConnectionService";
  }
  
  public void zzp(zza.zzb<Status> paramzzb, String paramString)
    throws RemoteException
  {
    ((zzqn)zzqJ()).zza(new zzc(paramzzb), paramString, this.zzaEg);
  }
  
  private static final class zza
    extends zzqk.zzb
  {
    private final zza.zzb<Status> zzamC;
    
    public zza(zza.zzb<Status> paramzzb, zzq<Connections.MessageListener> paramzzq)
    {
      super();
      this.zzamC = ((zza.zzb)zzx.zzz(paramzzb));
    }
    
    public void zziZ(int paramInt)
      throws RemoteException
    {
      this.zzamC.zzs(new Status(paramInt));
    }
  }
  
  private static class zzb
    extends zzqj
  {
    private final zzq<Connections.MessageListener> zzbbb;
    
    zzb(zzq<Connections.MessageListener> paramzzq)
    {
      this.zzbbb = paramzzq;
    }
    
    public void onDisconnected(final String paramString)
      throws RemoteException
    {
      this.zzbbb.zza(new zzq.zzb()
      {
        public void zza(Connections.MessageListener paramAnonymousMessageListener)
        {
          paramAnonymousMessageListener.onDisconnected(paramString);
        }
        
        public void zzpr() {}
      });
    }
    
    public void onMessageReceived(final String paramString, final byte[] paramArrayOfByte, final boolean paramBoolean)
      throws RemoteException
    {
      this.zzbbb.zza(new zzq.zzb()
      {
        public void zza(Connections.MessageListener paramAnonymousMessageListener)
        {
          paramAnonymousMessageListener.onMessageReceived(paramString, paramArrayOfByte, paramBoolean);
        }
        
        public void zzpr() {}
      });
    }
  }
  
  private static class zzc
    extends zzqj
  {
    private final zza.zzb<Status> zzbbf;
    
    zzc(zza.zzb<Status> paramzzb)
    {
      this.zzbbf = paramzzb;
    }
    
    public void zzja(int paramInt)
      throws RemoteException
    {
      this.zzbbf.zzs(new Status(paramInt));
    }
  }
  
  private static final class zzd
    extends zzqk.zzb
  {
    private final zza.zzb<Status> zzamC;
    private final zzq<Connections.ConnectionResponseCallback> zzbbg;
    
    public zzd(zza.zzb<Status> paramzzb, zzq<Connections.ConnectionResponseCallback> paramzzq, zzq<Connections.MessageListener> paramzzq1)
    {
      super();
      this.zzamC = ((zza.zzb)zzx.zzz(paramzzb));
      this.zzbbg = ((zzq)zzx.zzz(paramzzq));
    }
    
    public void zza(final String paramString, final int paramInt, final byte[] paramArrayOfByte)
      throws RemoteException
    {
      this.zzbbg.zza(new zzq.zzb()
      {
        public void zza(Connections.ConnectionResponseCallback paramAnonymousConnectionResponseCallback)
        {
          paramAnonymousConnectionResponseCallback.onConnectionResponse(paramString, new Status(paramInt), paramArrayOfByte);
        }
        
        public void zzpr() {}
      });
    }
    
    public void zziY(int paramInt)
      throws RemoteException
    {
      this.zzamC.zzs(new Status(paramInt));
    }
  }
  
  private static final class zze
    extends zzqj
  {
    private final zza.zzb<Connections.StartAdvertisingResult> zzamC;
    private final zzq<Connections.ConnectionRequestListener> zzbbi;
    
    zze(zza.zzb<Connections.StartAdvertisingResult> paramzzb, zzq<Connections.ConnectionRequestListener> paramzzq)
    {
      this.zzamC = ((zza.zzb)zzx.zzz(paramzzb));
      this.zzbbi = ((zzq)zzx.zzz(paramzzq));
    }
    
    public void onConnectionRequest(final String paramString1, final String paramString2, final String paramString3, final byte[] paramArrayOfByte)
      throws RemoteException
    {
      this.zzbbi.zza(new zzq.zzb()
      {
        public void zza(Connections.ConnectionRequestListener paramAnonymousConnectionRequestListener)
        {
          paramAnonymousConnectionRequestListener.onConnectionRequest(paramString1, paramString2, paramString3, paramArrayOfByte);
        }
        
        public void zzpr() {}
      });
    }
    
    public void zzm(int paramInt, String paramString)
      throws RemoteException
    {
      this.zzamC.zzs(new zzqk.zzf(new Status(paramInt), paramString));
    }
  }
  
  private static final class zzf
    implements Connections.StartAdvertisingResult
  {
    private final Status zzUX;
    private final String zzbbm;
    
    zzf(Status paramStatus, String paramString)
    {
      this.zzUX = paramStatus;
      this.zzbbm = paramString;
    }
    
    public String getLocalEndpointName()
    {
      return this.zzbbm;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class zzg
    extends zzqj
  {
    private final zza.zzb<Status> zzamC;
    private final zzq<Connections.EndpointDiscoveryListener> zzbbi;
    
    zzg(zza.zzb<Status> paramzzb, zzq<Connections.EndpointDiscoveryListener> paramzzq)
    {
      this.zzamC = ((zza.zzb)zzx.zzz(paramzzb));
      this.zzbbi = ((zzq)zzx.zzz(paramzzq));
    }
    
    public void onEndpointFound(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
      throws RemoteException
    {
      this.zzbbi.zza(new zzq.zzb()
      {
        public void zza(Connections.EndpointDiscoveryListener paramAnonymousEndpointDiscoveryListener)
        {
          paramAnonymousEndpointDiscoveryListener.onEndpointFound(paramString1, paramString2, paramString3, paramString4);
        }
        
        public void zzpr() {}
      });
    }
    
    public void onEndpointLost(final String paramString)
      throws RemoteException
    {
      this.zzbbi.zza(new zzq.zzb()
      {
        public void zza(Connections.EndpointDiscoveryListener paramAnonymousEndpointDiscoveryListener)
        {
          paramAnonymousEndpointDiscoveryListener.onEndpointLost(paramString);
        }
        
        public void zzpr() {}
      });
    }
    
    public void zziW(int paramInt)
      throws RemoteException
    {
      this.zzamC.zzs(new Status(paramInt));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzqk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */