package com.google.android.gms.drive.internal;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveApi.DriveIdResult;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.query.Query;
import java.util.List;

public class zzs
  implements DriveApi
{
  public PendingResult<Status> cancelPendingActions(GoogleApiClient paramGoogleApiClient, List<String> paramList)
  {
    return ((zzu)paramGoogleApiClient.zza(Drive.zzUI)).cancelPendingActions(paramGoogleApiClient, paramList);
  }
  
  public PendingResult<DriveApi.DriveIdResult> fetchDriveId(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.zza(new zzf(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(new GetMetadataRequest(DriveId.zzcW(paramString), false), new zzs.zzd(this));
      }
    });
  }
  
  public DriveFolder getAppFolder(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = (zzu)paramGoogleApiClient.zza(Drive.zzUI);
    if (!paramGoogleApiClient.zzth()) {
      throw new IllegalStateException("Client is not yet connected");
    }
    paramGoogleApiClient = paramGoogleApiClient.zztg();
    if (paramGoogleApiClient != null) {
      return new zzy(paramGoogleApiClient);
    }
    return null;
  }
  
  public DriveFile getFile(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    if (paramDriveId == null) {
      throw new IllegalArgumentException("Id must be provided.");
    }
    if (!paramGoogleApiClient.isConnected()) {
      throw new IllegalStateException("Client must be connected");
    }
    return new zzw(paramDriveId);
  }
  
  public DriveFolder getFolder(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    if (paramDriveId == null) {
      throw new IllegalArgumentException("Id must be provided.");
    }
    if (!paramGoogleApiClient.isConnected()) {
      throw new IllegalStateException("Client must be connected");
    }
    return new zzy(paramDriveId);
  }
  
  public DriveFolder getRootFolder(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = (zzu)paramGoogleApiClient.zza(Drive.zzUI);
    if (!paramGoogleApiClient.zzth()) {
      throw new IllegalStateException("Client is not yet connected");
    }
    paramGoogleApiClient = paramGoogleApiClient.zztf();
    if (paramGoogleApiClient != null) {
      return new zzy(paramGoogleApiClient);
    }
    return null;
  }
  
  public PendingResult<BooleanResult> isAutobackupEnabled(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zzt(paramGoogleApiClient)
    {
      protected BooleanResult zzA(Status paramAnonymousStatus)
      {
        return new BooleanResult(paramAnonymousStatus, false);
      }
      
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zze(new zzd()
        {
          public void zzaf(boolean paramAnonymous2Boolean)
          {
            jdField_this.zza(new BooleanResult(Status.zzagC, paramAnonymous2Boolean));
          }
        });
      }
    });
  }
  
  public CreateFileActivityBuilder newCreateFileActivityBuilder()
  {
    return new CreateFileActivityBuilder();
  }
  
  public PendingResult<DriveApi.DriveContentsResult> newDriveContents(GoogleApiClient paramGoogleApiClient)
  {
    return zza(paramGoogleApiClient, 536870912);
  }
  
  public OpenFileActivityBuilder newOpenFileActivityBuilder()
  {
    return new OpenFileActivityBuilder();
  }
  
  public PendingResult<DriveApi.MetadataBufferResult> query(GoogleApiClient paramGoogleApiClient, final Query paramQuery)
  {
    if (paramQuery == null) {
      throw new IllegalArgumentException("Query must be provided.");
    }
    paramGoogleApiClient.zza(new zzh(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(new QueryRequest(paramQuery), new zzs.zzi(this));
      }
    });
  }
  
  public PendingResult<Status> requestSync(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(new zzbu(this));
      }
    });
  }
  
  public PendingResult<DriveApi.DriveContentsResult> zza(GoogleApiClient paramGoogleApiClient, final int paramInt)
  {
    paramGoogleApiClient.zza(new zzc(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(new CreateContentsRequest(paramInt), new zzs.zza(this));
      }
    });
  }
  
  private static class zza
    extends zzd
  {
    private final zza.zzb<DriveApi.DriveContentsResult> zzamC;
    
    public zza(zza.zzb<DriveApi.DriveContentsResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void onError(Status paramStatus)
      throws RemoteException
    {
      this.zzamC.zzs(new zzs.zzb(paramStatus, null));
    }
    
    public void zza(OnContentsResponse paramOnContentsResponse)
      throws RemoteException
    {
      this.zzamC.zzs(new zzs.zzb(Status.zzagC, new zzv(paramOnContentsResponse.zztn())));
    }
  }
  
  static class zzb
    implements Releasable, DriveApi.DriveContentsResult
  {
    private final Status zzUX;
    private final DriveContents zzaoC;
    
    public zzb(Status paramStatus, DriveContents paramDriveContents)
    {
      this.zzUX = paramStatus;
      this.zzaoC = paramDriveContents;
    }
    
    public DriveContents getDriveContents()
    {
      return this.zzaoC;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public void release()
    {
      if (this.zzaoC != null) {
        this.zzaoC.zzsy();
      }
    }
  }
  
  static abstract class zzc
    extends zzt<DriveApi.DriveContentsResult>
  {
    zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public DriveApi.DriveContentsResult zzB(Status paramStatus)
    {
      return new zzs.zzb(paramStatus, null);
    }
  }
  
  static class zzd
    extends zzd
  {
    private final zza.zzb<DriveApi.DriveIdResult> zzamC;
    
    public zzd(zza.zzb<DriveApi.DriveIdResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void onError(Status paramStatus)
      throws RemoteException
    {
      this.zzamC.zzs(new zzs.zze(paramStatus, null));
    }
    
    public void zza(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.zzamC.zzs(new zzs.zze(Status.zzagC, paramOnDriveIdResponse.getDriveId()));
    }
    
    public void zza(OnMetadataResponse paramOnMetadataResponse)
      throws RemoteException
    {
      this.zzamC.zzs(new zzs.zze(Status.zzagC, new zzp(paramOnMetadataResponse.zztw()).getDriveId()));
    }
  }
  
  private static class zze
    implements DriveApi.DriveIdResult
  {
    private final Status zzUX;
    private final DriveId zzaoz;
    
    public zze(Status paramStatus, DriveId paramDriveId)
    {
      this.zzUX = paramStatus;
      this.zzaoz = paramDriveId;
    }
    
    public DriveId getDriveId()
    {
      return this.zzaoz;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  static abstract class zzf
    extends zzt<DriveApi.DriveIdResult>
  {
    zzf(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public DriveApi.DriveIdResult zzC(Status paramStatus)
    {
      return new zzs.zze(paramStatus, null);
    }
  }
  
  static class zzg
    implements DriveApi.MetadataBufferResult
  {
    private final Status zzUX;
    private final MetadataBuffer zzaqI;
    private final boolean zzaqJ;
    
    public zzg(Status paramStatus, MetadataBuffer paramMetadataBuffer, boolean paramBoolean)
    {
      this.zzUX = paramStatus;
      this.zzaqI = paramMetadataBuffer;
      this.zzaqJ = paramBoolean;
    }
    
    public MetadataBuffer getMetadataBuffer()
    {
      return this.zzaqI;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public void release()
    {
      if (this.zzaqI != null) {
        this.zzaqI.release();
      }
    }
  }
  
  static abstract class zzh
    extends zzt<DriveApi.MetadataBufferResult>
  {
    zzh(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public DriveApi.MetadataBufferResult zzD(Status paramStatus)
    {
      return new zzs.zzg(paramStatus, null, false);
    }
  }
  
  private static class zzi
    extends zzd
  {
    private final zza.zzb<DriveApi.MetadataBufferResult> zzamC;
    
    public zzi(zza.zzb<DriveApi.MetadataBufferResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void onError(Status paramStatus)
      throws RemoteException
    {
      this.zzamC.zzs(new zzs.zzg(paramStatus, null, false));
    }
    
    public void zza(OnListEntriesResponse paramOnListEntriesResponse)
      throws RemoteException
    {
      MetadataBuffer localMetadataBuffer = new MetadataBuffer(paramOnListEntriesResponse.zztt());
      this.zzamC.zzs(new zzs.zzg(Status.zzagC, localMetadataBuffer, paramOnListEntriesResponse.zztu()));
    }
  }
  
  @SuppressLint({"MissingRemoteException"})
  static class zzj
    extends zzt.zza
  {
    zzj(GoogleApiClient paramGoogleApiClient, Status paramStatus)
    {
      super();
      zza(paramStatus);
    }
    
    protected void zza(zzu paramzzu) {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */