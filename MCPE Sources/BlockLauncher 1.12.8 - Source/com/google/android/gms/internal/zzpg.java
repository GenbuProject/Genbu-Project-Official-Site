package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.SessionsApi;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.SessionRegistrationRequest;
import com.google.android.gms.fitness.request.SessionStartRequest;
import com.google.android.gms.fitness.request.SessionStopRequest;
import com.google.android.gms.fitness.request.SessionUnregistrationRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.concurrent.TimeUnit;

public class zzpg
  implements SessionsApi
{
  private PendingResult<SessionStopResult> zza(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2)
  {
    paramGoogleApiClient.zzb(new zzof.zza(paramGoogleApiClient)
    {
      protected SessionStopResult zzO(Status paramAnonymousStatus)
      {
        return SessionStopResult.zzV(paramAnonymousStatus);
      }
      
      protected void zza(zzof paramAnonymouszzof)
        throws RemoteException
      {
        zzpg.zzb localzzb = new zzpg.zzb(this, null);
        ((zzoq)paramAnonymouszzof.zzqJ()).zza(new SessionStopRequest(paramString1, paramString2, localzzb));
      }
    });
  }
  
  public PendingResult<Status> insertSession(GoogleApiClient paramGoogleApiClient, final SessionInsertRequest paramSessionInsertRequest)
  {
    paramGoogleApiClient.zza(new zzof.zzc(paramGoogleApiClient)
    {
      protected void zza(zzof paramAnonymouszzof)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzoq)paramAnonymouszzof.zzqJ()).zza(new SessionInsertRequest(paramSessionInsertRequest, localzzph));
      }
    });
  }
  
  public PendingResult<SessionReadResult> readSession(GoogleApiClient paramGoogleApiClient, final SessionReadRequest paramSessionReadRequest)
  {
    paramGoogleApiClient.zza(new zzof.zza(paramGoogleApiClient)
    {
      protected SessionReadResult zzP(Status paramAnonymousStatus)
      {
        return SessionReadResult.zzU(paramAnonymousStatus);
      }
      
      protected void zza(zzof paramAnonymouszzof)
        throws RemoteException
      {
        zzpg.zza localzza = new zzpg.zza(this, null);
        ((zzoq)paramAnonymouszzof.zzqJ()).zza(new SessionReadRequest(paramSessionReadRequest, localzza));
      }
    });
  }
  
  public PendingResult<Status> registerForSessions(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return zza(paramGoogleApiClient, paramPendingIntent, 0);
  }
  
  public PendingResult<Status> startSession(GoogleApiClient paramGoogleApiClient, final Session paramSession)
  {
    zzx.zzb(paramSession, "Session cannot be null");
    if (paramSession.getEndTime(TimeUnit.MILLISECONDS) == 0L) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Cannot start a session which has already ended");
      paramGoogleApiClient.zzb(new zzof.zzc(paramGoogleApiClient)
      {
        protected void zza(zzof paramAnonymouszzof)
          throws RemoteException
        {
          zzph localzzph = new zzph(this);
          ((zzoq)paramAnonymouszzof.zzqJ()).zza(new SessionStartRequest(paramSession, localzzph));
        }
      });
    }
  }
  
  public PendingResult<SessionStopResult> stopSession(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return zza(paramGoogleApiClient, null, paramString);
  }
  
  public PendingResult<Status> unregisterForSessions(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.zzb(new zzof.zzc(paramGoogleApiClient)
    {
      protected void zza(zzof paramAnonymouszzof)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzoq)paramAnonymouszzof.zzqJ()).zza(new SessionUnregistrationRequest(paramPendingIntent, localzzph));
      }
    });
  }
  
  public PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent, final int paramInt)
  {
    paramGoogleApiClient.zzb(new zzof.zzc(paramGoogleApiClient)
    {
      protected void zza(zzof paramAnonymouszzof)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzoq)paramAnonymouszzof.zzqJ()).zza(new SessionRegistrationRequest(paramPendingIntent, localzzph, paramInt));
      }
    });
  }
  
  private static class zza
    extends zzou.zza
  {
    private final zza.zzb<SessionReadResult> zzamC;
    
    private zza(zza.zzb<SessionReadResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zza(SessionReadResult paramSessionReadResult)
      throws RemoteException
    {
      this.zzamC.zzs(paramSessionReadResult);
    }
  }
  
  private static class zzb
    extends zzov.zza
  {
    private final zza.zzb<SessionStopResult> zzamC;
    
    private zzb(zza.zzb<SessionStopResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zza(SessionStopResult paramSessionStopResult)
    {
      this.zzamC.zzs(paramSessionStopResult);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */