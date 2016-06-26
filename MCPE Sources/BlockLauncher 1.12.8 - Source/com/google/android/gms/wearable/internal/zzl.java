package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;
import com.google.android.gms.wearable.ChannelApi.OpenChannelResult;

public final class zzl
  implements ChannelApi
{
  private static zzb.zza<ChannelApi.ChannelListener> zza(IntentFilter[] paramArrayOfIntentFilter)
  {
    new zzb.zza()
    {
      public void zza(zzbp paramAnonymouszzbp, zza.zzb<Status> paramAnonymouszzb, ChannelApi.ChannelListener paramAnonymousChannelListener, zzq<ChannelApi.ChannelListener> paramAnonymouszzq)
        throws RemoteException
      {
        paramAnonymouszzbp.zza(paramAnonymouszzb, paramAnonymousChannelListener, paramAnonymouszzq, null, this.zzbrP);
      }
    };
  }
  
  public PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, ChannelApi.ChannelListener paramChannelListener)
  {
    zzx.zzb(paramGoogleApiClient, "client is null");
    zzx.zzb(paramChannelListener, "listener is null");
    return zzb.zza(paramGoogleApiClient, zza(new IntentFilter[] { zzbn.zzgM("com.google.android.gms.wearable.CHANNEL_EVENT") }), paramChannelListener);
  }
  
  public PendingResult<ChannelApi.OpenChannelResult> openChannel(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2)
  {
    zzx.zzb(paramGoogleApiClient, "client is null");
    zzx.zzb(paramString1, "nodeId is null");
    zzx.zzb(paramString2, "path is null");
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zze(this, paramString1, paramString2);
      }
      
      public ChannelApi.OpenChannelResult zzbs(Status paramAnonymousStatus)
      {
        return new zzl.zza(paramAnonymousStatus, null);
      }
    });
  }
  
  public PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, ChannelApi.ChannelListener paramChannelListener)
  {
    zzx.zzb(paramGoogleApiClient, "client is null");
    zzx.zzb(paramChannelListener, "listener is null");
    return paramGoogleApiClient.zza(new zzb(paramGoogleApiClient, paramChannelListener, null));
  }
  
  static final class zza
    implements ChannelApi.OpenChannelResult
  {
    private final Status zzUX;
    private final Channel zzbrY;
    
    zza(Status paramStatus, Channel paramChannel)
    {
      this.zzUX = ((Status)zzx.zzz(paramStatus));
      this.zzbrY = paramChannel;
    }
    
    public Channel getChannel()
    {
      return this.zzbrY;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  static final class zzb
    extends zzi<Status>
  {
    private final String zzVo;
    private ChannelApi.ChannelListener zzbrZ;
    
    zzb(GoogleApiClient paramGoogleApiClient, ChannelApi.ChannelListener paramChannelListener, String paramString)
    {
      super();
      this.zzbrZ = ((ChannelApi.ChannelListener)zzx.zzz(paramChannelListener));
      this.zzVo = paramString;
    }
    
    protected void zza(zzbp paramzzbp)
      throws RemoteException
    {
      paramzzbp.zza(this, this.zzbrZ, this.zzVo);
      this.zzbrZ = null;
    }
    
    public Status zzb(Status paramStatus)
    {
      this.zzbrZ = null;
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */