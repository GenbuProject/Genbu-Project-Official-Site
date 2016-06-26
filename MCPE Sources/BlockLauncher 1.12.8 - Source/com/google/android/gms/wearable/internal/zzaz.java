package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.MessageApi.SendMessageResult;

public final class zzaz
  implements MessageApi
{
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, MessageApi.MessageListener paramMessageListener, IntentFilter[] paramArrayOfIntentFilter)
  {
    return paramGoogleApiClient.zza(new zza(paramGoogleApiClient, paramMessageListener, paramGoogleApiClient.zzr(paramMessageListener), paramArrayOfIntentFilter, null));
  }
  
  public PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, MessageApi.MessageListener paramMessageListener)
  {
    return zza(paramGoogleApiClient, paramMessageListener, new IntentFilter[] { zzbn.zzgM("com.google.android.gms.wearable.MESSAGE_RECEIVED") });
  }
  
  public PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, MessageApi.MessageListener paramMessageListener, Uri paramUri, int paramInt)
  {
    if (paramUri != null)
    {
      bool = true;
      zzx.zzb(bool, "uri must not be null");
      if ((paramInt != 0) && (paramInt != 1)) {
        break label63;
      }
    }
    label63:
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "invalid filter type");
      return zza(paramGoogleApiClient, paramMessageListener, new IntentFilter[] { zzbn.zza("com.google.android.gms.wearable.MESSAGE_RECEIVED", paramUri, paramInt) });
      bool = false;
      break;
    }
  }
  
  public PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, final MessageApi.MessageListener paramMessageListener)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zza(this, paramMessageListener);
      }
      
      public Status zzb(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  public PendingResult<MessageApi.SendMessageResult> sendMessage(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2, final byte[] paramArrayOfByte)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zza(this, paramString1, paramString2, paramArrayOfByte);
      }
      
      protected MessageApi.SendMessageResult zzbz(Status paramAnonymousStatus)
      {
        return new zzaz.zzb(paramAnonymousStatus, -1);
      }
    });
  }
  
  private static final class zza
    extends zzi<Status>
  {
    private zzq<MessageApi.MessageListener> zzbbi;
    private MessageApi.MessageListener zzbsS;
    private IntentFilter[] zzbsT;
    
    private zza(GoogleApiClient paramGoogleApiClient, MessageApi.MessageListener paramMessageListener, zzq<MessageApi.MessageListener> paramzzq, IntentFilter[] paramArrayOfIntentFilter)
    {
      super();
      this.zzbsS = ((MessageApi.MessageListener)zzx.zzz(paramMessageListener));
      this.zzbbi = ((zzq)zzx.zzz(paramzzq));
      this.zzbsT = ((IntentFilter[])zzx.zzz(paramArrayOfIntentFilter));
    }
    
    protected void zza(zzbp paramzzbp)
      throws RemoteException
    {
      paramzzbp.zza(this, this.zzbsS, this.zzbbi, this.zzbsT);
      this.zzbsS = null;
      this.zzbbi = null;
      this.zzbsT = null;
    }
    
    public Status zzb(Status paramStatus)
    {
      this.zzbsS = null;
      this.zzbbi = null;
      this.zzbsT = null;
      return paramStatus;
    }
  }
  
  public static class zzb
    implements MessageApi.SendMessageResult
  {
    private final Status zzUX;
    private final int zzaox;
    
    public zzb(Status paramStatus, int paramInt)
    {
      this.zzUX = paramStatus;
      this.zzaox = paramInt;
    }
    
    public int getRequestId()
    {
      return this.zzaox;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */