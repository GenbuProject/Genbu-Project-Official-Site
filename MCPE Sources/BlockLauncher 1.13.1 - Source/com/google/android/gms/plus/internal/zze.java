package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.server.response.SafeParcelResponse;
import com.google.android.gms.plus.Moments.LoadMomentsResult;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.model.moments.MomentEntity;
import com.google.android.gms.plus.internal.model.people.PersonEntity;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class zze
  extends zzj<zzd>
{
  private Person zzbei;
  private final PlusSession zzbej;
  
  public zze(Context paramContext, Looper paramLooper, zzf paramzzf, PlusSession paramPlusSession, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 2, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzbej = paramPlusSession;
  }
  
  public static boolean zzd(Set<Scope> paramSet)
  {
    if ((paramSet == null) || (paramSet.isEmpty())) {}
    while ((paramSet.size() == 1) && (paramSet.contains(new Scope("plus_one_placeholder_scope")))) {
      return false;
    }
    return true;
  }
  
  public String getAccountName()
  {
    zzqI();
    try
    {
      String str = ((zzd)zzqJ()).getAccountName();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public void zzEY()
  {
    zzqI();
    try
    {
      this.zzbei = null;
      ((zzd)zzqJ()).zzEY();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public Person zzFa()
  {
    zzqI();
    return this.zzbei;
  }
  
  public zzq zza(zza.zzb<People.LoadPeopleResult> paramzzb, int paramInt, String paramString)
  {
    zzqI();
    paramzzb = new zze(paramzzb);
    try
    {
      paramString = ((zzd)zzqJ()).zza(paramzzb, 1, paramInt, -1, paramString);
      return paramString;
    }
    catch (RemoteException paramString)
    {
      paramzzb.zza(DataHolder.zzbI(8), null);
    }
    return null;
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramBundle != null) && (paramBundle.containsKey("loaded_person"))) {
      this.zzbei = PersonEntity.zzv(paramBundle.getByteArray("loaded_person"));
    }
    super.zza(paramInt1, paramIBinder, paramBundle, paramInt2);
  }
  
  public void zza(zza.zzb<Moments.LoadMomentsResult> paramzzb, int paramInt, String paramString1, Uri paramUri, String paramString2, String paramString3)
  {
    zzqI();
    if (paramzzb != null) {}
    for (paramzzb = new zzd(paramzzb);; paramzzb = null) {
      try
      {
        ((zzd)zzqJ()).zza(paramzzb, paramInt, paramString1, paramUri, paramString2, paramString3);
        return;
      }
      catch (RemoteException paramString1)
      {
        paramzzb.zza(DataHolder.zzbI(8), null, null);
      }
    }
  }
  
  public void zza(zza.zzb<Status> paramzzb, Moment paramMoment)
  {
    zzqI();
    if (paramzzb != null) {}
    for (paramzzb = new zzc(paramzzb);; paramzzb = null) {
      try
      {
        paramMoment = SafeParcelResponse.zza((MomentEntity)paramMoment);
        ((zzd)zzqJ()).zza(paramzzb, paramMoment);
        return;
      }
      catch (RemoteException paramMoment)
      {
        if (paramzzb != null) {
          break;
        }
        throw new IllegalStateException(paramMoment);
        paramzzb.zzbe(new Status(8, null, null));
      }
    }
  }
  
  public void zza(zza.zzb<People.LoadPeopleResult> paramzzb, Collection<String> paramCollection)
  {
    zzqI();
    paramzzb = new zze(paramzzb);
    try
    {
      ((zzd)zzqJ()).zza(paramzzb, new ArrayList(paramCollection));
      return;
    }
    catch (RemoteException paramCollection)
    {
      paramzzb.zza(DataHolder.zzbI(8), null);
    }
  }
  
  public void zzd(zza.zzb<People.LoadPeopleResult> paramzzb, String[] paramArrayOfString)
  {
    zza(paramzzb, Arrays.asList(paramArrayOfString));
  }
  
  protected zzd zzdS(IBinder paramIBinder)
  {
    return zzd.zza.zzdR(paramIBinder);
  }
  
  public void zzfG(String paramString)
  {
    zzqI();
    try
    {
      ((zzd)zzqJ()).zzfG(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new IllegalStateException(paramString);
    }
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.plus.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.plus.internal.IPlusService";
  }
  
  public boolean zzmE()
  {
    return zzd(zzqH().zzb(Plus.API));
  }
  
  protected Bundle zzml()
  {
    Bundle localBundle = this.zzbej.zzFk();
    localBundle.putStringArray("request_visible_actions", this.zzbej.zzFe());
    localBundle.putString("auth_package", this.zzbej.zzFg());
    return localBundle;
  }
  
  public void zzo(zza.zzb<Moments.LoadMomentsResult> paramzzb)
  {
    zza(paramzzb, 20, null, null, null, "me");
  }
  
  public void zzp(zza.zzb<People.LoadPeopleResult> paramzzb)
  {
    zzqI();
    paramzzb = new zze(paramzzb);
    try
    {
      ((zzd)zzqJ()).zza(paramzzb, 2, 1, -1, null);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      paramzzb.zza(DataHolder.zzbI(8), null);
    }
  }
  
  public zzq zzq(zza.zzb<People.LoadPeopleResult> paramzzb, String paramString)
  {
    return zza(paramzzb, 0, paramString);
  }
  
  public void zzq(zza.zzb<Status> paramzzb)
  {
    zzqI();
    zzEY();
    paramzzb = new zzf(paramzzb);
    try
    {
      ((zzd)zzqJ()).zzb(paramzzb);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      paramzzb.zzi(8, null);
    }
  }
  
  static final class zza
    implements Moments.LoadMomentsResult
  {
    private final Status zzUX;
    private final String zzbek;
    private final String zzbel;
    private final MomentBuffer zzbem;
    
    public zza(Status paramStatus, DataHolder paramDataHolder, String paramString1, String paramString2)
    {
      this.zzUX = paramStatus;
      this.zzbek = paramString1;
      this.zzbel = paramString2;
      if (paramDataHolder != null) {}
      for (paramStatus = new MomentBuffer(paramDataHolder);; paramStatus = null)
      {
        this.zzbem = paramStatus;
        return;
      }
    }
    
    public MomentBuffer getMomentBuffer()
    {
      return this.zzbem;
    }
    
    public String getNextPageToken()
    {
      return this.zzbek;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public String getUpdated()
    {
      return this.zzbel;
    }
    
    public void release()
    {
      if (this.zzbem != null) {
        this.zzbem.release();
      }
    }
  }
  
  static final class zzb
    implements People.LoadPeopleResult
  {
    private final Status zzUX;
    private final String zzbek;
    private final PersonBuffer zzben;
    
    public zzb(Status paramStatus, DataHolder paramDataHolder, String paramString)
    {
      this.zzUX = paramStatus;
      this.zzbek = paramString;
      if (paramDataHolder != null) {}
      for (paramStatus = new PersonBuffer(paramDataHolder);; paramStatus = null)
      {
        this.zzben = paramStatus;
        return;
      }
    }
    
    public String getNextPageToken()
    {
      return this.zzbek;
    }
    
    public PersonBuffer getPersonBuffer()
    {
      return this.zzben;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public void release()
    {
      if (this.zzben != null) {
        this.zzben.release();
      }
    }
  }
  
  static final class zzc
    extends zza
  {
    private final zza.zzb<Status> zzaON;
    
    public zzc(zza.zzb<Status> paramzzb)
    {
      this.zzaON = paramzzb;
    }
    
    public void zzbe(Status paramStatus)
    {
      this.zzaON.zzs(paramStatus);
    }
  }
  
  static final class zzd
    extends zza
  {
    private final zza.zzb<Moments.LoadMomentsResult> zzaON;
    
    public zzd(zza.zzb<Moments.LoadMomentsResult> paramzzb)
    {
      this.zzaON = paramzzb;
    }
    
    public void zza(DataHolder paramDataHolder, String paramString1, String paramString2)
    {
      if (paramDataHolder.zzpZ() != null) {}
      for (Object localObject = (PendingIntent)paramDataHolder.zzpZ().getParcelable("pendingIntent");; localObject = null)
      {
        Status localStatus = new Status(paramDataHolder.getStatusCode(), null, (PendingIntent)localObject);
        localObject = paramDataHolder;
        if (!localStatus.isSuccess())
        {
          localObject = paramDataHolder;
          if (paramDataHolder != null)
          {
            if (!paramDataHolder.isClosed()) {
              paramDataHolder.close();
            }
            localObject = null;
          }
        }
        this.zzaON.zzs(new zze.zza(localStatus, (DataHolder)localObject, paramString1, paramString2));
        return;
      }
    }
  }
  
  static final class zze
    extends zza
  {
    private final zza.zzb<People.LoadPeopleResult> zzaON;
    
    public zze(zza.zzb<People.LoadPeopleResult> paramzzb)
    {
      this.zzaON = paramzzb;
    }
    
    public void zza(DataHolder paramDataHolder, String paramString)
    {
      if (paramDataHolder.zzpZ() != null) {}
      for (Object localObject = (PendingIntent)paramDataHolder.zzpZ().getParcelable("pendingIntent");; localObject = null)
      {
        Status localStatus = new Status(paramDataHolder.getStatusCode(), null, (PendingIntent)localObject);
        localObject = paramDataHolder;
        if (!localStatus.isSuccess())
        {
          localObject = paramDataHolder;
          if (paramDataHolder != null)
          {
            if (!paramDataHolder.isClosed()) {
              paramDataHolder.close();
            }
            localObject = null;
          }
        }
        this.zzaON.zzs(new zze.zzb(localStatus, (DataHolder)localObject, paramString));
        return;
      }
    }
  }
  
  static final class zzf
    extends zza
  {
    private final zza.zzb<Status> zzaON;
    
    public zzf(zza.zzb<Status> paramzzb)
    {
      this.zzaON = paramzzb;
    }
    
    public void zzi(int paramInt, Bundle paramBundle)
    {
      if (paramBundle != null) {}
      for (paramBundle = (PendingIntent)paramBundle.getParcelable("pendingIntent");; paramBundle = null)
      {
        paramBundle = new Status(paramInt, null, paramBundle);
        this.zzaON.zzs(paramBundle);
        return;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\plus\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */