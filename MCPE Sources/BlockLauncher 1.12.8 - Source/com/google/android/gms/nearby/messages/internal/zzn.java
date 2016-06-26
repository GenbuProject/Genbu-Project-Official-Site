package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.PublishOptions.Builder;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.google.android.gms.nearby.messages.SubscribeOptions.Builder;
import java.util.Iterator;
import java.util.List;

public class zzn
  implements Messages
{
  public static final Api.zzc<zzm> zzUI = new Api.zzc();
  public static final Api.zza<zzm, MessagesOptions> zzUJ = new Api.zza()
  {
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
    
    public zzm zza(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, MessagesOptions paramAnonymousMessagesOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzm(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, paramAnonymouszzf, paramAnonymousMessagesOptions);
    }
  };
  
  @Nullable
  private static Message zzA(Intent paramIntent)
  {
    return (Message)zzj.zzc(paramIntent, "com.google.android.gms.nearby.messages.MESSAGES");
  }
  
  @Nullable
  private static Message zzB(Intent paramIntent)
  {
    return (Message)zzj.zzc(paramIntent, "com.google.android.gms.nearby.messages.LOST_MESSAGE");
  }
  
  private static List<Message> zzC(Intent paramIntent)
  {
    return zzj.zzd(paramIntent, "com.google.android.gms.nearby.messages.UPDATED_MESSAGES");
  }
  
  public PendingResult<Status> getPermissionStatus(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zzm(this);
      }
    });
  }
  
  public void handleIntent(Intent paramIntent, MessageListener paramMessageListener)
  {
    Message localMessage = zzA(paramIntent);
    if (localMessage != null) {
      paramMessageListener.onFound(localMessage);
    }
    localMessage = zzB(paramIntent);
    if (localMessage != null) {
      paramMessageListener.onLost(localMessage);
    }
    paramIntent = zzC(paramIntent).iterator();
    while (paramIntent.hasNext()) {
      paramMessageListener.zza((Message)paramIntent.next());
    }
  }
  
  public PendingResult<Status> publish(GoogleApiClient paramGoogleApiClient, Message paramMessage)
  {
    return publish(paramGoogleApiClient, paramMessage, PublishOptions.DEFAULT);
  }
  
  public PendingResult<Status> publish(GoogleApiClient paramGoogleApiClient, final Message paramMessage, final PublishOptions paramPublishOptions)
  {
    zzx.zzz(paramMessage);
    zzx.zzz(paramPublishOptions);
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zza(this, MessageWrapper.zzb(paramMessage), paramPublishOptions);
      }
    });
  }
  
  @Deprecated
  public PendingResult<Status> publish(GoogleApiClient paramGoogleApiClient, Message paramMessage, Strategy paramStrategy)
  {
    return publish(paramGoogleApiClient, paramMessage, new PublishOptions.Builder().setStrategy(paramStrategy).build());
  }
  
  public PendingResult<Status> registerStatusCallback(GoogleApiClient paramGoogleApiClient, final StatusCallback paramStatusCallback)
  {
    zzx.zzz(paramStatusCallback);
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zza(this, this.zzbcL, paramStatusCallback);
      }
    });
  }
  
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return subscribe(paramGoogleApiClient, paramPendingIntent, SubscribeOptions.DEFAULT);
  }
  
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent, final SubscribeOptions paramSubscribeOptions)
  {
    zzx.zzz(paramPendingIntent);
    zzx.zzz(paramSubscribeOptions);
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zza(this, paramPendingIntent, paramSubscribeOptions);
      }
    });
  }
  
  @Deprecated
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, MessageListener paramMessageListener)
  {
    return subscribe(paramGoogleApiClient, paramMessageListener, SubscribeOptions.DEFAULT);
  }
  
  @Deprecated
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, MessageListener paramMessageListener, Strategy paramStrategy)
  {
    return subscribe(paramGoogleApiClient, paramMessageListener, new SubscribeOptions.Builder().setStrategy(paramStrategy).build());
  }
  
  @Deprecated
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, MessageListener paramMessageListener, Strategy paramStrategy, MessageFilter paramMessageFilter)
  {
    return subscribe(paramGoogleApiClient, paramMessageListener, new SubscribeOptions.Builder().setStrategy(paramStrategy).setFilter(paramMessageFilter).build());
  }
  
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, final MessageListener paramMessageListener, final SubscribeOptions paramSubscribeOptions)
  {
    zzx.zzz(paramMessageListener);
    zzx.zzz(paramSubscribeOptions);
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zza(this, this.zzbbv, paramMessageListener, paramSubscribeOptions, null);
      }
    });
  }
  
  public PendingResult<Status> unpublish(GoogleApiClient paramGoogleApiClient, final Message paramMessage)
  {
    zzx.zzz(paramMessage);
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zza(this, MessageWrapper.zzb(paramMessage));
      }
    });
  }
  
  public PendingResult<Status> unregisterStatusCallback(GoogleApiClient paramGoogleApiClient, final StatusCallback paramStatusCallback)
  {
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zzb(this, this.zzbcL, paramStatusCallback);
      }
    });
  }
  
  public PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent)
  {
    zzx.zzz(paramPendingIntent);
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zza(this, paramPendingIntent);
      }
    });
  }
  
  public PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, final MessageListener paramMessageListener)
  {
    zzx.zzz(paramMessageListener);
    paramGoogleApiClient.zzb(new zza(paramGoogleApiClient)
    {
      protected void zza(zzm paramAnonymouszzm)
        throws RemoteException
      {
        paramAnonymouszzm.zza(this, this.zzbbv, paramMessageListener);
      }
    });
  }
  
  static abstract class zza
    extends zza.zza<Status, zzm>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    public Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */