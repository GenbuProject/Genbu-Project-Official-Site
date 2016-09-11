package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.panorama.PanoramaApi;
import com.google.android.gms.panorama.PanoramaApi.PanoramaResult;

public class zzqq
  implements PanoramaApi
{
  private static void zza(Context paramContext, Uri paramUri)
  {
    paramContext.revokeUriPermission(paramUri, 1);
  }
  
  private static void zza(Context paramContext, zzqp paramzzqp, final zzqo paramzzqo, final Uri paramUri, Bundle paramBundle)
    throws RemoteException
  {
    paramContext.grantUriPermission("com.google.android.gms", paramUri, 1);
    paramzzqo = new zzqo.zza()
    {
      public void zza(int paramAnonymousInt1, Bundle paramAnonymousBundle, int paramAnonymousInt2, Intent paramAnonymousIntent)
        throws RemoteException
      {
        zzqq.zzb(this.zzxh, paramUri);
        paramzzqo.zza(paramAnonymousInt1, paramAnonymousBundle, paramAnonymousInt2, paramAnonymousIntent);
      }
    };
    try
    {
      paramzzqp.zza(paramzzqo, paramUri, paramBundle, true);
      return;
    }
    catch (RemoteException paramzzqp)
    {
      zza(paramContext, paramUri);
      throw paramzzqp;
    }
    catch (RuntimeException paramzzqp)
    {
      zza(paramContext, paramUri);
      throw paramzzqp;
    }
  }
  
  public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfo(GoogleApiClient paramGoogleApiClient, final Uri paramUri)
  {
    paramGoogleApiClient.zza(new zza(paramGoogleApiClient)
    {
      protected void zza(Context paramAnonymousContext, zzqp paramAnonymouszzqp)
        throws RemoteException
      {
        paramAnonymouszzqp.zza(new zzqq.zzb(this), paramUri, null, false);
      }
    });
  }
  
  public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfoAndGrantAccess(GoogleApiClient paramGoogleApiClient, final Uri paramUri)
  {
    paramGoogleApiClient.zza(new zza(paramGoogleApiClient)
    {
      protected void zza(Context paramAnonymousContext, zzqp paramAnonymouszzqp)
        throws RemoteException
      {
        zzqq.zzb(paramAnonymousContext, paramAnonymouszzqp, new zzqq.zzb(this), paramUri, null);
      }
    });
  }
  
  private static abstract class zza
    extends zzqq.zzc<PanoramaApi.PanoramaResult>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    protected PanoramaApi.PanoramaResult zzbd(Status paramStatus)
    {
      return new zzqs(paramStatus, null);
    }
  }
  
  private static final class zzb
    extends zzqo.zza
  {
    private final zza.zzb<PanoramaApi.PanoramaResult> zzamC;
    
    public zzb(zza.zzb<PanoramaApi.PanoramaResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zza(int paramInt1, Bundle paramBundle, int paramInt2, Intent paramIntent)
    {
      if (paramBundle != null) {}
      for (paramBundle = (PendingIntent)paramBundle.getParcelable("pendingIntent");; paramBundle = null)
      {
        paramBundle = new Status(paramInt1, null, paramBundle);
        this.zzamC.zzs(new zzqs(paramBundle, paramIntent));
        return;
      }
    }
  }
  
  private static abstract class zzc<R extends Result>
    extends zza.zza<R, zzqr>
  {
    protected zzc(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected abstract void zza(Context paramContext, zzqp paramzzqp)
      throws RemoteException;
    
    protected final void zza(zzqr paramzzqr)
      throws RemoteException
    {
      zza(paramzzqr.getContext(), (zzqp)paramzzqr.zzqJ());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzqq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */