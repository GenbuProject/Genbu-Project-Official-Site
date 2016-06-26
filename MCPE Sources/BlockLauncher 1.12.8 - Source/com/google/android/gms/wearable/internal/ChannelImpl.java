package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.Channel.GetInputStreamResult;
import com.google.android.gms.wearable.Channel.GetOutputStreamResult;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ChannelImpl
  implements SafeParcelable, Channel
{
  public static final Parcelable.Creator<ChannelImpl> CREATOR = new zzo();
  private final String mPath;
  final int mVersionCode;
  private final String zzVo;
  private final String zzbrb;
  
  ChannelImpl(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.mVersionCode = paramInt;
    this.zzVo = ((String)zzx.zzz(paramString1));
    this.zzbrb = ((String)zzx.zzz(paramString2));
    this.mPath = ((String)zzx.zzz(paramString3));
  }
  
  private static zzb.zza<ChannelApi.ChannelListener> zza(String paramString, final IntentFilter[] paramArrayOfIntentFilter)
  {
    new zzb.zza()
    {
      public void zza(zzbp paramAnonymouszzbp, zza.zzb<Status> paramAnonymouszzb, ChannelApi.ChannelListener paramAnonymousChannelListener, zzq<ChannelApi.ChannelListener> paramAnonymouszzq)
        throws RemoteException
      {
        paramAnonymouszzbp.zza(paramAnonymouszzb, paramAnonymousChannelListener, paramAnonymouszzq, this.zzVj, paramArrayOfIntentFilter);
      }
    };
  }
  
  public PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, ChannelApi.ChannelListener paramChannelListener)
  {
    IntentFilter localIntentFilter = zzbn.zzgM("com.google.android.gms.wearable.CHANNEL_EVENT");
    return zzb.zza(paramGoogleApiClient, zza(this.zzVo, new IntentFilter[] { localIntentFilter }), paramChannelListener);
  }
  
  public PendingResult<Status> close(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zzt(this, ChannelImpl.zza(ChannelImpl.this));
      }
      
      protected Status zzb(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  public PendingResult<Status> close(GoogleApiClient paramGoogleApiClient, final int paramInt)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zzh(this, ChannelImpl.zza(ChannelImpl.this), paramInt);
      }
      
      protected Status zzb(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof ChannelImpl)) {
        return false;
      }
      paramObject = (ChannelImpl)paramObject;
    } while ((this.zzVo.equals(((ChannelImpl)paramObject).zzVo)) && (zzw.equal(((ChannelImpl)paramObject).zzbrb, this.zzbrb)) && (zzw.equal(((ChannelImpl)paramObject).mPath, this.mPath)) && (((ChannelImpl)paramObject).mVersionCode == this.mVersionCode));
    return false;
  }
  
  public PendingResult<Channel.GetInputStreamResult> getInputStream(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zzu(this, ChannelImpl.zza(ChannelImpl.this));
      }
      
      public Channel.GetInputStreamResult zzbt(Status paramAnonymousStatus)
      {
        return new ChannelImpl.zza(paramAnonymousStatus, null);
      }
    });
  }
  
  public String getNodeId()
  {
    return this.zzbrb;
  }
  
  public PendingResult<Channel.GetOutputStreamResult> getOutputStream(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zzv(this, ChannelImpl.zza(ChannelImpl.this));
      }
      
      public Channel.GetOutputStreamResult zzbu(Status paramAnonymousStatus)
      {
        return new ChannelImpl.zzb(paramAnonymousStatus, null);
      }
    });
  }
  
  public String getPath()
  {
    return this.mPath;
  }
  
  public String getToken()
  {
    return this.zzVo;
  }
  
  public int hashCode()
  {
    return this.zzVo.hashCode();
  }
  
  public PendingResult<Status> receiveFile(GoogleApiClient paramGoogleApiClient, final Uri paramUri, final boolean paramBoolean)
  {
    zzx.zzb(paramGoogleApiClient, "client is null");
    zzx.zzb(paramUri, "uri is null");
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zza(this, ChannelImpl.zza(ChannelImpl.this), paramUri, paramBoolean);
      }
      
      public Status zzb(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  public PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, ChannelApi.ChannelListener paramChannelListener)
  {
    zzx.zzb(paramGoogleApiClient, "client is null");
    zzx.zzb(paramChannelListener, "listener is null");
    return paramGoogleApiClient.zza(new zzl.zzb(paramGoogleApiClient, paramChannelListener, this.zzVo));
  }
  
  public PendingResult<Status> sendFile(GoogleApiClient paramGoogleApiClient, Uri paramUri)
  {
    return sendFile(paramGoogleApiClient, paramUri, 0L, -1L);
  }
  
  public PendingResult<Status> sendFile(GoogleApiClient paramGoogleApiClient, final Uri paramUri, final long paramLong1, long paramLong2)
  {
    zzx.zzb(paramGoogleApiClient, "client is null");
    zzx.zzb(this.zzVo, "token is null");
    zzx.zzb(paramUri, "uri is null");
    if (paramLong1 >= 0L)
    {
      bool = true;
      zzx.zzb(bool, "startOffset is negative: %s", new Object[] { Long.valueOf(paramLong1) });
      if ((paramLong2 < 0L) && (paramLong2 != -1L)) {
        break label113;
      }
    }
    label113:
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "invalid length: %s", new Object[] { Long.valueOf(paramLong2) });
      paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
      {
        protected void zza(zzbp paramAnonymouszzbp)
          throws RemoteException
        {
          paramAnonymouszzbp.zza(this, ChannelImpl.zza(ChannelImpl.this), paramUri, paramLong1, this.zzbsg);
        }
        
        public Status zzb(Status paramAnonymousStatus)
        {
          return paramAnonymousStatus;
        }
      });
      bool = false;
      break;
    }
  }
  
  public String toString()
  {
    return "ChannelImpl{versionCode=" + this.mVersionCode + ", token='" + this.zzVo + '\'' + ", nodeId='" + this.zzbrb + '\'' + ", path='" + this.mPath + '\'' + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo.zza(this, paramParcel, paramInt);
  }
  
  static final class zza
    implements Channel.GetInputStreamResult
  {
    private final Status zzUX;
    private final InputStream zzbsh;
    
    zza(Status paramStatus, InputStream paramInputStream)
    {
      this.zzUX = ((Status)zzx.zzz(paramStatus));
      this.zzbsh = paramInputStream;
    }
    
    public InputStream getInputStream()
    {
      return this.zzbsh;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public void release()
    {
      if (this.zzbsh != null) {}
      try
      {
        this.zzbsh.close();
        return;
      }
      catch (IOException localIOException) {}
    }
  }
  
  static final class zzb
    implements Channel.GetOutputStreamResult
  {
    private final Status zzUX;
    private final OutputStream zzbsi;
    
    zzb(Status paramStatus, OutputStream paramOutputStream)
    {
      this.zzUX = ((Status)zzx.zzz(paramStatus));
      this.zzbsi = paramOutputStream;
    }
    
    public OutputStream getOutputStream()
    {
      return this.zzbsi;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public void release()
    {
      if (this.zzbsi != null) {}
      try
      {
        this.zzbsi.close();
        return;
      }
      catch (IOException localIOException) {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\ChannelImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */