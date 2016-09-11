package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;
import com.google.android.gms.nearby.connection.Connections.ConnectionResponseCallback;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;
import com.google.android.gms.nearby.connection.Connections.MessageListener;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;
import java.util.List;

public final class zzql
  implements Connections
{
  public static final Api.zzc<zzqk> zzUI = new Api.zzc();
  public static final Api.zza<zzqk, Api.ApiOptions.NoOptions> zzUJ = new Api.zza()
  {
    public zzqk zzp(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzqk(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  
  public static zzqk zzd(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    if (paramGoogleApiClient != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "GoogleApiClient parameter is required.");
      zzx.zza(paramGoogleApiClient.isConnected(), "GoogleApiClient must be connected.");
      return zze(paramGoogleApiClient, paramBoolean);
    }
  }
  
  public static zzqk zze(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    zzx.zza(paramGoogleApiClient.zza(Nearby.CONNECTIONS_API), "GoogleApiClient is not configured to use the Nearby Connections Api. Pass Nearby.CONNECTIONS_API into GoogleApiClient.Builder#addApi() to use this feature.");
    boolean bool = paramGoogleApiClient.hasConnectedApi(Nearby.CONNECTIONS_API);
    if ((paramBoolean) && (!bool)) {
      throw new IllegalStateException("GoogleApiClient has an optional Nearby.CONNECTIONS_API and is not connected to Nearby Connections. Use GoogleApiClient.hasConnectedApi(Nearby.CONNECTIONS_API) to guard this call.");
    }
    if (bool) {
      return (zzqk)paramGoogleApiClient.zza(zzUI);
    }
    return null;
  }
  
  public PendingResult<Status> acceptConnectionRequest(GoogleApiClient paramGoogleApiClient, final String paramString, final byte[] paramArrayOfByte, Connections.MessageListener paramMessageListener)
  {
    paramGoogleApiClient.zzb(new zzc(paramGoogleApiClient, paramString)
    {
      protected void zza(zzqk paramAnonymouszzqk)
        throws RemoteException
      {
        paramAnonymouszzqk.zza(this, paramString, paramArrayOfByte, this.zzbbx);
      }
    });
  }
  
  public void disconnectFromEndpoint(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    zzd(paramGoogleApiClient, false).zzfA(paramString);
  }
  
  public String getLocalDeviceId(GoogleApiClient paramGoogleApiClient)
  {
    return zzd(paramGoogleApiClient, true).zzEk();
  }
  
  public String getLocalEndpointId(GoogleApiClient paramGoogleApiClient)
  {
    return zzd(paramGoogleApiClient, true).zzEj();
  }
  
  public PendingResult<Status> rejectConnectionRequest(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.zzb(new zzc(paramGoogleApiClient, paramString)
    {
      protected void zza(zzqk paramAnonymouszzqk)
        throws RemoteException
      {
        paramAnonymouszzqk.zzp(this, paramString);
      }
    });
  }
  
  public PendingResult<Status> sendConnectionRequest(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2, final byte[] paramArrayOfByte, Connections.ConnectionResponseCallback paramConnectionResponseCallback, Connections.MessageListener paramMessageListener)
  {
    paramGoogleApiClient.zzb(new zzc(paramGoogleApiClient, paramString1)
    {
      protected void zza(zzqk paramAnonymouszzqk)
        throws RemoteException
      {
        paramAnonymouszzqk.zza(this, paramString1, paramString2, paramArrayOfByte, this.zzbbw, this.zzbbx);
      }
    });
  }
  
  public void sendReliableMessage(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte)
  {
    zzd(paramGoogleApiClient, false).zza(new String[] { paramString }, paramArrayOfByte);
  }
  
  public void sendReliableMessage(GoogleApiClient paramGoogleApiClient, List<String> paramList, byte[] paramArrayOfByte)
  {
    zzd(paramGoogleApiClient, false).zza((String[])paramList.toArray(new String[paramList.size()]), paramArrayOfByte);
  }
  
  public void sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte)
  {
    zzd(paramGoogleApiClient, false).zzb(new String[] { paramString }, paramArrayOfByte);
  }
  
  public void sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, List<String> paramList, byte[] paramArrayOfByte)
  {
    zzd(paramGoogleApiClient, false).zzb((String[])paramList.toArray(new String[paramList.size()]), paramArrayOfByte);
  }
  
  public PendingResult<Connections.StartAdvertisingResult> startAdvertising(GoogleApiClient paramGoogleApiClient, final String paramString, final AppMetadata paramAppMetadata, final long paramLong, Connections.ConnectionRequestListener paramConnectionRequestListener)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient, paramString)
    {
      protected void zza(zzqk paramAnonymouszzqk)
        throws RemoteException
      {
        paramAnonymouszzqk.zza(this, paramString, paramAppMetadata, paramLong, this.zzbbt);
      }
    });
  }
  
  public PendingResult<Status> startDiscovery(GoogleApiClient paramGoogleApiClient, final String paramString, final long paramLong, Connections.EndpointDiscoveryListener paramEndpointDiscoveryListener)
  {
    paramGoogleApiClient.zzb(new zzc(paramGoogleApiClient, paramString)
    {
      protected void zza(zzqk paramAnonymouszzqk)
        throws RemoteException
      {
        paramAnonymouszzqk.zza(this, paramString, paramLong, this.zzbbv);
      }
    });
  }
  
  public void stopAdvertising(GoogleApiClient paramGoogleApiClient)
  {
    zzd(paramGoogleApiClient, false).zzEl();
  }
  
  public void stopAllEndpoints(GoogleApiClient paramGoogleApiClient)
  {
    zzd(paramGoogleApiClient, false).zzEm();
  }
  
  public void stopDiscovery(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    zzd(paramGoogleApiClient, false).zzfz(paramString);
  }
  
  private static abstract class zza<R extends Result>
    extends zza.zza<R, zzqk>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  private static abstract class zzb
    extends zzql.zza<Connections.StartAdvertisingResult>
  {
    private zzb(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Connections.StartAdvertisingResult zzbc(final Status paramStatus)
    {
      new Connections.StartAdvertisingResult()
      {
        public String getLocalEndpointName()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class zzc
    extends zzql.zza<Status>
  {
    private zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzql.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */