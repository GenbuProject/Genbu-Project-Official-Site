package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteApi;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;

public class zzkl
  implements AppInviteApi
{
  public PendingResult<Status> convertInvitation(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.zza(new zzd(paramGoogleApiClient, paramString));
  }
  
  public PendingResult<AppInviteInvitationResult> getInvitation(GoogleApiClient paramGoogleApiClient, Activity paramActivity, boolean paramBoolean)
  {
    return paramGoogleApiClient.zza(new zze(paramGoogleApiClient, paramActivity, paramBoolean));
  }
  
  public PendingResult<Status> updateInvitationOnInstall(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.zza(new zzc(paramGoogleApiClient, paramString));
  }
  
  static class zza
    extends zzko.zza
  {
    public void zza(Status paramStatus, Intent paramIntent)
    {
      throw new UnsupportedOperationException();
    }
    
    public void zzd(Status paramStatus)
      throws RemoteException
    {
      throw new UnsupportedOperationException();
    }
  }
  
  static abstract class zzb<R extends Result>
    extends zza.zza<R, zzkm>
  {
    public zzb(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  final class zzc
    extends zzkl.zzb<Status>
  {
    private final String zzUO;
    
    public zzc(GoogleApiClient paramGoogleApiClient, String paramString)
    {
      super();
      this.zzUO = paramString;
    }
    
    protected void zza(zzkm paramzzkm)
      throws RemoteException
    {
      paramzzkm.zzb(new zzkl.zza()
      {
        public void zzd(Status paramAnonymousStatus)
          throws RemoteException
        {
          zzkl.zzc.this.zza(paramAnonymousStatus);
        }
      }, this.zzUO);
    }
    
    protected Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
  
  final class zzd
    extends zzkl.zzb<Status>
  {
    private final String zzUO;
    
    public zzd(GoogleApiClient paramGoogleApiClient, String paramString)
    {
      super();
      this.zzUO = paramString;
    }
    
    protected void zza(zzkm paramzzkm)
      throws RemoteException
    {
      paramzzkm.zza(new zzkl.zza()
      {
        public void zzd(Status paramAnonymousStatus)
          throws RemoteException
        {
          zzkl.zzd.this.zza(paramAnonymousStatus);
        }
      }, this.zzUO);
    }
    
    protected Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
  
  final class zze
    extends zzkl.zzb<AppInviteInvitationResult>
  {
    private final Activity zzUS;
    private final boolean zzUT;
    private final Intent zzUU;
    
    public zze(GoogleApiClient paramGoogleApiClient, Activity paramActivity, boolean paramBoolean)
    {
      super();
      this.zzUS = paramActivity;
      this.zzUT = paramBoolean;
      if (this.zzUS != null) {}
      for (this$1 = this.zzUS.getIntent();; this$1 = null)
      {
        this.zzUU = zzkl.this;
        return;
      }
    }
    
    protected void zza(zzkm paramzzkm)
      throws RemoteException
    {
      if (AppInviteReferral.hasReferral(this.zzUU))
      {
        zza(new zzkn(Status.zzagC, this.zzUU));
        return;
      }
      paramzzkm.zza(new zzkl.zza()
      {
        public void zza(Status paramAnonymousStatus, Intent paramAnonymousIntent)
        {
          zzkl.zze.this.zza(new zzkn(paramAnonymousStatus, paramAnonymousIntent));
          if ((AppInviteReferral.hasReferral(paramAnonymousIntent)) && (zzkl.zze.zza(zzkl.zze.this)) && (zzkl.zze.zzb(zzkl.zze.this) != null)) {
            zzkl.zze.zzb(zzkl.zze.this).startActivity(paramAnonymousIntent);
          }
        }
      });
    }
    
    protected AppInviteInvitationResult zze(Status paramStatus)
    {
      return new zzkn(paramStatus, new Intent());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzkl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */