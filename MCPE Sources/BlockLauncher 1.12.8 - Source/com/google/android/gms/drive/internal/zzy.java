package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.DriveFolder.DriveFolderResult;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.zzi;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.Query.Builder;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.zzh;
import com.google.android.gms.drive.zzh.zza;
import com.google.android.gms.internal.zznm;

public class zzy
  extends zzab
  implements DriveFolder
{
  public zzy(DriveId paramDriveId)
  {
    super(paramDriveId);
  }
  
  private int zza(DriveContents paramDriveContents, zzi paramzzi)
  {
    if (paramDriveContents == null)
    {
      if ((paramzzi != null) && (paramzzi.zztI())) {
        return 0;
      }
      return 1;
    }
    int i = paramDriveContents.zzsx().getRequestId();
    paramDriveContents.zzsy();
    return i;
  }
  
  private PendingResult<DriveFolder.DriveFileResult> zza(GoogleApiClient paramGoogleApiClient, final MetadataChangeSet paramMetadataChangeSet, final int paramInt, final zzh paramzzh)
  {
    zzi localzzi = zzi.zzdd(paramMetadataChangeSet.getMimeType());
    if ((localzzi != null) && (localzzi.zztI())) {}
    for (final int i = 1;; i = 0) {
      paramGoogleApiClient.zzb(new zzd(paramGoogleApiClient)
      {
        protected void zza(zzu paramAnonymouszzu)
          throws RemoteException
        {
          paramMetadataChangeSet.zzsL().setContext(paramAnonymouszzu.getContext());
          CreateFileRequest localCreateFileRequest = new CreateFileRequest(zzy.this.getDriveId(), paramMetadataChangeSet.zzsL(), paramInt, i, paramzzh);
          paramAnonymouszzu.zzte().zza(localCreateFileRequest, new zzy.zza(this));
        }
      });
    }
  }
  
  private MetadataChangeSet zza(MetadataChangeSet paramMetadataChangeSet, String paramString)
  {
    return paramMetadataChangeSet.zza(zznm.zzatE, paramString);
  }
  
  private Query zza(Query paramQuery)
  {
    Query.Builder localBuilder = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, getDriveId()));
    if (paramQuery != null)
    {
      if (paramQuery.getFilter() != null) {
        localBuilder.addFilter(paramQuery.getFilter());
      }
      localBuilder.setPageToken(paramQuery.getPageToken());
      localBuilder.setSortOrder(paramQuery.getSortOrder());
    }
    return localBuilder.build();
  }
  
  private void zzb(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, DriveContents paramDriveContents, zzh paramzzh)
  {
    if (paramMetadataChangeSet == null) {
      throw new IllegalArgumentException("MetadataChangeSet must be provided.");
    }
    paramMetadataChangeSet = zzi.zzdd(paramMetadataChangeSet.getMimeType());
    if ((paramMetadataChangeSet != null) && (paramMetadataChangeSet.isFolder())) {
      throw new IllegalArgumentException("May not create folders using this method. Use DriveFolder.createFolder() instead of mime type application/vnd.google-apps.folder");
    }
    paramzzh.zzg(paramGoogleApiClient);
    if (paramDriveContents == null) {}
    do
    {
      return;
      if (!(paramDriveContents instanceof zzv)) {
        throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
      }
      if (paramDriveContents.getDriveId() != null) {
        throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
      }
    } while (!paramDriveContents.zzsz());
    throw new IllegalArgumentException("DriveContents are already closed.");
  }
  
  private void zzb(MetadataChangeSet paramMetadataChangeSet)
  {
    if (paramMetadataChangeSet == null) {
      throw new IllegalArgumentException("MetadataChangeSet must be provided.");
    }
    paramMetadataChangeSet = zzi.zzdd(paramMetadataChangeSet.getMimeType());
    if ((paramMetadataChangeSet != null) && (!paramMetadataChangeSet.zztH())) {
      throw new IllegalArgumentException("May not create shortcut files using this method. Use DriveFolder.createShortcutFile() instead.");
    }
  }
  
  public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, DriveContents paramDriveContents)
  {
    zzb(paramMetadataChangeSet);
    return zza(paramGoogleApiClient, paramMetadataChangeSet, paramDriveContents, null);
  }
  
  public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, DriveContents paramDriveContents, ExecutionOptions paramExecutionOptions)
  {
    zzb(paramMetadataChangeSet);
    return zza(paramGoogleApiClient, paramMetadataChangeSet, paramDriveContents, zzh.zza(paramExecutionOptions));
  }
  
  public PendingResult<DriveFolder.DriveFolderResult> createFolder(GoogleApiClient paramGoogleApiClient, final MetadataChangeSet paramMetadataChangeSet)
  {
    if (paramMetadataChangeSet == null) {
      throw new IllegalArgumentException("MetadataChangeSet must be provided.");
    }
    if ((paramMetadataChangeSet.getMimeType() != null) && (!paramMetadataChangeSet.getMimeType().equals("application/vnd.google-apps.folder"))) {
      throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
    }
    paramGoogleApiClient.zzb(new zzf(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramMetadataChangeSet.zzsL().setContext(paramAnonymouszzu.getContext());
        paramAnonymouszzu.zzte().zza(new CreateFolderRequest(zzy.this.getDriveId(), paramMetadataChangeSet.zzsL()), new zzy.zzb(this));
      }
    });
  }
  
  public PendingResult<DriveApi.MetadataBufferResult> listChildren(GoogleApiClient paramGoogleApiClient)
  {
    return queryChildren(paramGoogleApiClient, null);
  }
  
  public PendingResult<DriveApi.MetadataBufferResult> queryChildren(GoogleApiClient paramGoogleApiClient, Query paramQuery)
  {
    return new zzs().query(paramGoogleApiClient, zza(paramQuery));
  }
  
  public PendingResult<DriveFolder.DriveFileResult> zza(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, DriveContents paramDriveContents, zzh paramzzh)
  {
    zzh localzzh = paramzzh;
    if (paramzzh == null) {
      localzzh = new zzh.zza().zzsH();
    }
    zzb(paramGoogleApiClient, paramMetadataChangeSet, paramDriveContents, localzzh);
    int i = zza(paramDriveContents, zzi.zzdd(paramMetadataChangeSet.getMimeType()));
    paramzzh = localzzh.zzsG();
    paramDriveContents = paramMetadataChangeSet;
    if (paramzzh != null) {
      paramDriveContents = zza(paramMetadataChangeSet, paramzzh);
    }
    return zza(paramGoogleApiClient, paramDriveContents, i, localzzh);
  }
  
  private static class zza
    extends zzd
  {
    private final zza.zzb<DriveFolder.DriveFileResult> zzamC;
    
    public zza(zza.zzb<DriveFolder.DriveFileResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void onError(Status paramStatus)
      throws RemoteException
    {
      this.zzamC.zzs(new zzy.zzc(paramStatus, null));
    }
    
    public void zza(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.zzamC.zzs(new zzy.zzc(Status.zzagC, new zzw(paramOnDriveIdResponse.getDriveId())));
    }
  }
  
  private static class zzb
    extends zzd
  {
    private final zza.zzb<DriveFolder.DriveFolderResult> zzamC;
    
    public zzb(zza.zzb<DriveFolder.DriveFolderResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void onError(Status paramStatus)
      throws RemoteException
    {
      this.zzamC.zzs(new zzy.zze(paramStatus, null));
    }
    
    public void zza(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.zzamC.zzs(new zzy.zze(Status.zzagC, new zzy(paramOnDriveIdResponse.getDriveId())));
    }
  }
  
  private static class zzc
    implements DriveFolder.DriveFileResult
  {
    private final Status zzUX;
    private final DriveFile zzarr;
    
    public zzc(Status paramStatus, DriveFile paramDriveFile)
    {
      this.zzUX = paramStatus;
      this.zzarr = paramDriveFile;
    }
    
    public DriveFile getDriveFile()
    {
      return this.zzarr;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  static abstract class zzd
    extends zzt<DriveFolder.DriveFileResult>
  {
    zzd(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public DriveFolder.DriveFileResult zzE(Status paramStatus)
    {
      return new zzy.zzc(paramStatus, null);
    }
  }
  
  private static class zze
    implements DriveFolder.DriveFolderResult
  {
    private final Status zzUX;
    private final DriveFolder zzars;
    
    public zze(Status paramStatus, DriveFolder paramDriveFolder)
    {
      this.zzUX = paramStatus;
      this.zzars = paramDriveFolder;
    }
    
    public DriveFolder getDriveFolder()
    {
      return this.zzars;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  static abstract class zzf
    extends zzt<DriveFolder.DriveFolderResult>
  {
    zzf(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public DriveFolder.DriveFolderResult zzF(Status paramStatus)
    {
      return new zzy.zze(paramStatus, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */