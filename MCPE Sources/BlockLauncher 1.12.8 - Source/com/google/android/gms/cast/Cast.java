package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.cast.internal.zzh;
import com.google.android.gms.cast.internal.zzk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import java.io.IOException;

public final class Cast
{
  public static final int ACTIVE_INPUT_STATE_NO = 0;
  public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
  public static final int ACTIVE_INPUT_STATE_YES = 1;
  public static final Api<CastOptions> API = new Api("Cast.API", zzUJ, zzk.zzUI);
  public static final CastApi CastApi = new Cast.CastApi.zza();
  public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
  public static final int MAX_MESSAGE_LENGTH = 65536;
  public static final int MAX_NAMESPACE_LENGTH = 128;
  public static final int STANDBY_STATE_NO = 0;
  public static final int STANDBY_STATE_UNKNOWN = -1;
  public static final int STANDBY_STATE_YES = 1;
  private static final Api.zza<zze, CastOptions> zzUJ = new Api.zza()
  {
    public zze zza(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, Cast.CastOptions paramAnonymousCastOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      zzx.zzb(paramAnonymousCastOptions, "Setting the API options is required.");
      return new zze(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousCastOptions.zzZL, Cast.CastOptions.zza(paramAnonymousCastOptions), paramAnonymousCastOptions.zzZM, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  
  public static abstract interface ApplicationConnectionResult
    extends Result
  {
    public abstract ApplicationMetadata getApplicationMetadata();
    
    public abstract String getApplicationStatus();
    
    public abstract String getSessionId();
    
    public abstract boolean getWasLaunched();
  }
  
  public static abstract interface CastApi
  {
    public abstract int getActiveInputState(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;
    
    public abstract ApplicationMetadata getApplicationMetadata(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;
    
    public abstract String getApplicationStatus(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;
    
    public abstract int getStandbyState(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;
    
    public abstract double getVolume(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;
    
    public abstract boolean isMute(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;
    
    public abstract PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient);
    
    public abstract PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString);
    
    public abstract PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2);
    
    public abstract PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString);
    
    public abstract PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString, LaunchOptions paramLaunchOptions);
    
    @Deprecated
    public abstract PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean);
    
    public abstract PendingResult<Status> leaveApplication(GoogleApiClient paramGoogleApiClient);
    
    public abstract void removeMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString)
      throws IOException, IllegalArgumentException;
    
    public abstract void requestStatus(GoogleApiClient paramGoogleApiClient)
      throws IOException, IllegalStateException;
    
    public abstract PendingResult<Status> sendMessage(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2);
    
    public abstract void setMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString, Cast.MessageReceivedCallback paramMessageReceivedCallback)
      throws IOException, IllegalStateException;
    
    public abstract void setMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
      throws IOException, IllegalStateException;
    
    public abstract void setVolume(GoogleApiClient paramGoogleApiClient, double paramDouble)
      throws IOException, IllegalArgumentException, IllegalStateException;
    
    public abstract PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient);
    
    public abstract PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient, String paramString);
    
    public static final class zza
      implements Cast.CastApi
    {
      public int getActiveInputState(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zzol();
      }
      
      public ApplicationMetadata getApplicationMetadata(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((zze)paramGoogleApiClient.zza(zzk.zzUI)).getApplicationMetadata();
      }
      
      public String getApplicationStatus(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((zze)paramGoogleApiClient.zza(zzk.zzUI)).getApplicationStatus();
      }
      
      public int getStandbyState(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zzom();
      }
      
      public double getVolume(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zzok();
      }
      
      public boolean isMute(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((zze)paramGoogleApiClient.zza(zzk.zzUI)).isMute();
      }
      
      public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient)
      {
        return zza(paramGoogleApiClient, null, null, null);
      }
      
      public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString)
      {
        return zza(paramGoogleApiClient, paramString, null, null);
      }
      
      public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2)
      {
        return zza(paramGoogleApiClient, paramString1, paramString2, null);
      }
      
      public PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, final String paramString)
      {
        paramGoogleApiClient.zzb(new Cast.zza(paramGoogleApiClient)
        {
          protected void zza(zze paramAnonymouszze)
            throws RemoteException
          {
            try
            {
              paramAnonymouszze.zza(paramString, false, this);
              return;
            }
            catch (IllegalStateException paramAnonymouszze)
            {
              zzbj(2001);
            }
          }
        });
      }
      
      public PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, final String paramString, final LaunchOptions paramLaunchOptions)
      {
        paramGoogleApiClient.zzb(new Cast.zza(paramGoogleApiClient)
        {
          protected void zza(zze paramAnonymouszze)
            throws RemoteException
          {
            try
            {
              paramAnonymouszze.zza(paramString, paramLaunchOptions, this);
              return;
            }
            catch (IllegalStateException paramAnonymouszze)
            {
              zzbj(2001);
            }
          }
        });
      }
      
      @Deprecated
      public PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean)
      {
        return launchApplication(paramGoogleApiClient, paramString, new LaunchOptions.Builder().setRelaunchIfRunning(paramBoolean).build());
      }
      
      public PendingResult<Status> leaveApplication(GoogleApiClient paramGoogleApiClient)
      {
        paramGoogleApiClient.zzb(new zzh(paramGoogleApiClient)
        {
          protected void zza(zze paramAnonymouszze)
            throws RemoteException
          {
            try
            {
              paramAnonymouszze.zzb(this);
              return;
            }
            catch (IllegalStateException paramAnonymouszze)
            {
              zzbj(2001);
            }
          }
        });
      }
      
      public void removeMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString)
        throws IOException, IllegalArgumentException
      {
        try
        {
          ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zzcg(paramString);
          return;
        }
        catch (RemoteException paramGoogleApiClient)
        {
          throw new IOException("service error");
        }
      }
      
      public void requestStatus(GoogleApiClient paramGoogleApiClient)
        throws IOException, IllegalStateException
      {
        try
        {
          ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zzoj();
          return;
        }
        catch (RemoteException paramGoogleApiClient)
        {
          throw new IOException("service error");
        }
      }
      
      public PendingResult<Status> sendMessage(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2)
      {
        paramGoogleApiClient.zzb(new zzh(paramGoogleApiClient)
        {
          protected void zza(zze paramAnonymouszze)
            throws RemoteException
          {
            try
            {
              paramAnonymouszze.zza(paramString1, paramString2, this);
              return;
            }
            catch (IllegalStateException paramAnonymouszze)
            {
              zzbj(2001);
              return;
            }
            catch (IllegalArgumentException paramAnonymouszze)
            {
              for (;;) {}
            }
          }
        });
      }
      
      public void setMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString, Cast.MessageReceivedCallback paramMessageReceivedCallback)
        throws IOException, IllegalStateException
      {
        try
        {
          ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zza(paramString, paramMessageReceivedCallback);
          return;
        }
        catch (RemoteException paramGoogleApiClient)
        {
          throw new IOException("service error");
        }
      }
      
      public void setMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
        throws IOException, IllegalStateException
      {
        try
        {
          ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zzX(paramBoolean);
          return;
        }
        catch (RemoteException paramGoogleApiClient)
        {
          throw new IOException("service error");
        }
      }
      
      public void setVolume(GoogleApiClient paramGoogleApiClient, double paramDouble)
        throws IOException, IllegalArgumentException, IllegalStateException
      {
        try
        {
          ((zze)paramGoogleApiClient.zza(zzk.zzUI)).zzf(paramDouble);
          return;
        }
        catch (RemoteException paramGoogleApiClient)
        {
          throw new IOException("service error");
        }
      }
      
      public PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient)
      {
        paramGoogleApiClient.zzb(new zzh(paramGoogleApiClient)
        {
          protected void zza(zze paramAnonymouszze)
            throws RemoteException
          {
            try
            {
              paramAnonymouszze.zza("", this);
              return;
            }
            catch (IllegalStateException paramAnonymouszze)
            {
              zzbj(2001);
            }
          }
        });
      }
      
      public PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient, final String paramString)
      {
        paramGoogleApiClient.zzb(new zzh(paramGoogleApiClient)
        {
          protected void zza(zze paramAnonymouszze)
            throws RemoteException
          {
            if (TextUtils.isEmpty(paramString))
            {
              zze(2001, "IllegalArgument: sessionId cannot be null or empty");
              return;
            }
            try
            {
              paramAnonymouszze.zza(paramString, this);
              return;
            }
            catch (IllegalStateException paramAnonymouszze)
            {
              zzbj(2001);
            }
          }
        });
      }
      
      public PendingResult<Cast.ApplicationConnectionResult> zza(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2, final JoinOptions paramJoinOptions)
      {
        paramGoogleApiClient.zzb(new Cast.zza(paramGoogleApiClient)
        {
          protected void zza(zze paramAnonymouszze)
            throws RemoteException
          {
            try
            {
              paramAnonymouszze.zza(paramString1, paramString2, paramJoinOptions, this);
              return;
            }
            catch (IllegalStateException paramAnonymouszze)
            {
              zzbj(2001);
            }
          }
        });
      }
    }
  }
  
  public static final class CastOptions
    implements Api.ApiOptions.HasOptions
  {
    final CastDevice zzZL;
    final Cast.Listener zzZM;
    private final int zzZN;
    
    private CastOptions(Builder paramBuilder)
    {
      this.zzZL = paramBuilder.zzZO;
      this.zzZM = paramBuilder.zzZP;
      this.zzZN = Builder.zza(paramBuilder);
    }
    
    @Deprecated
    public static Builder builder(CastDevice paramCastDevice, Cast.Listener paramListener)
    {
      return new Builder(paramCastDevice, paramListener);
    }
    
    public static final class Builder
    {
      CastDevice zzZO;
      Cast.Listener zzZP;
      private int zzZQ;
      
      public Builder(CastDevice paramCastDevice, Cast.Listener paramListener)
      {
        zzx.zzb(paramCastDevice, "CastDevice parameter cannot be null");
        zzx.zzb(paramListener, "CastListener parameter cannot be null");
        this.zzZO = paramCastDevice;
        this.zzZP = paramListener;
        this.zzZQ = 0;
      }
      
      public Cast.CastOptions build()
      {
        return new Cast.CastOptions(this, null);
      }
      
      public Builder setVerboseLoggingEnabled(boolean paramBoolean)
      {
        if (paramBoolean)
        {
          this.zzZQ |= 0x1;
          return this;
        }
        this.zzZQ &= 0xFFFFFFFE;
        return this;
      }
    }
  }
  
  public static class Listener
  {
    public void onActiveInputStateChanged(int paramInt) {}
    
    public void onApplicationDisconnected(int paramInt) {}
    
    public void onApplicationMetadataChanged(ApplicationMetadata paramApplicationMetadata) {}
    
    public void onApplicationStatusChanged() {}
    
    public void onStandbyStateChanged(int paramInt) {}
    
    public void onVolumeChanged() {}
  }
  
  public static abstract interface MessageReceivedCallback
  {
    public abstract void onMessageReceived(CastDevice paramCastDevice, String paramString1, String paramString2);
  }
  
  private static abstract class zza
    extends zzb<Cast.ApplicationConnectionResult>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Cast.ApplicationConnectionResult zzo(final Status paramStatus)
    {
      new Cast.ApplicationConnectionResult()
      {
        public ApplicationMetadata getApplicationMetadata()
        {
          return null;
        }
        
        public String getApplicationStatus()
        {
          return null;
        }
        
        public String getSessionId()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public boolean getWasLaunched()
        {
          return false;
        }
      };
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\Cast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */