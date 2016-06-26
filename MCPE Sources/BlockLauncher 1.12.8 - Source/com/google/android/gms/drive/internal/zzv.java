package com.google.android.gms.drive.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.zzi;
import com.google.android.gms.drive.zzi.zza;
import com.google.android.gms.internal.zzna;
import java.io.InputStream;
import java.io.OutputStream;

public class zzv
  implements DriveContents
{
  private boolean mClosed = false;
  private final Contents zzara;
  private boolean zzarb = false;
  private boolean zzarc = false;
  
  public zzv(Contents paramContents)
  {
    this.zzara = ((Contents)zzx.zzz(paramContents));
  }
  
  public PendingResult<Status> commit(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet)
  {
    return zza(paramGoogleApiClient, paramMetadataChangeSet, null);
  }
  
  public PendingResult<Status> commit(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, ExecutionOptions paramExecutionOptions)
  {
    if (paramExecutionOptions == null) {}
    for (paramExecutionOptions = null;; paramExecutionOptions = zzi.zzb(paramExecutionOptions)) {
      return zza(paramGoogleApiClient, paramMetadataChangeSet, paramExecutionOptions);
    }
  }
  
  public void discard(GoogleApiClient paramGoogleApiClient)
  {
    if (zzsz()) {
      throw new IllegalStateException("DriveContents already closed.");
    }
    zzsy();
    ((4)paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(new CloseContentsRequest(zzv.zza(zzv.this).getRequestId(), false), new zzbu(this));
      }
    })).setResultCallback(new ResultCallback()
    {
      public void zzp(Status paramAnonymousStatus)
      {
        if (!paramAnonymousStatus.isSuccess())
        {
          zzz.zzA("DriveContentsImpl", "Error discarding contents");
          return;
        }
        zzz.zzy("DriveContentsImpl", "Contents discarded");
      }
    });
  }
  
  public DriveId getDriveId()
  {
    return this.zzara.getDriveId();
  }
  
  public InputStream getInputStream()
  {
    if (zzsz()) {
      throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
    }
    if (this.zzara.getMode() != 268435456) {
      throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
    }
    if (this.zzarb) {
      throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
    }
    this.zzarb = true;
    return this.zzara.getInputStream();
  }
  
  public int getMode()
  {
    return this.zzara.getMode();
  }
  
  public OutputStream getOutputStream()
  {
    if (zzsz()) {
      throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }
    if (this.zzara.getMode() != 536870912) {
      throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
    }
    if (this.zzarc) {
      throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
    }
    this.zzarc = true;
    return this.zzara.getOutputStream();
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
  {
    if (zzsz()) {
      throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }
    return this.zzara.getParcelFileDescriptor();
  }
  
  public PendingResult<DriveApi.DriveContentsResult> reopenForWrite(GoogleApiClient paramGoogleApiClient)
  {
    if (zzsz()) {
      throw new IllegalStateException("DriveContents already closed.");
    }
    if (this.zzara.getMode() != 268435456) {
      throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
    }
    zzsy();
    paramGoogleApiClient.zza(new zzs.zzc(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(new OpenContentsRequest(zzv.this.getDriveId(), 536870912, zzv.zza(zzv.this).getRequestId()), new zzbl(this, null));
      }
    });
  }
  
  public PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final MetadataChangeSet paramMetadataChangeSet, zzi paramzzi)
  {
    final zzi localzzi = paramzzi;
    if (paramzzi == null) {
      localzzi = new zzi.zza().zzsJ();
    }
    if (this.zzara.getMode() == 268435456) {
      throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
    }
    if ((ExecutionOptions.zzcv(localzzi.zzsD())) && (!this.zzara.zzsv())) {
      throw new IllegalStateException("DriveContents must be valid for conflict detection.");
    }
    localzzi.zzg(paramGoogleApiClient);
    if (zzsz()) {
      throw new IllegalStateException("DriveContents already closed.");
    }
    if (getDriveId() == null) {
      throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
    }
    if (paramMetadataChangeSet != null) {}
    for (;;)
    {
      zzsy();
      paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
      {
        protected void zza(zzu paramAnonymouszzu)
          throws RemoteException
        {
          paramMetadataChangeSet.zzsL().setContext(paramAnonymouszzu.getContext());
          paramAnonymouszzu.zzte().zza(new CloseContentsAndUpdateMetadataRequest(zzv.zza(zzv.this).getDriveId(), paramMetadataChangeSet.zzsL(), zzv.zza(zzv.this).getRequestId(), zzv.zza(zzv.this).zzsv(), localzzi), new zzbu(this));
        }
      });
      paramMetadataChangeSet = MetadataChangeSet.zzapd;
    }
  }
  
  public Contents zzsx()
  {
    return this.zzara;
  }
  
  public void zzsy()
  {
    zzna.zza(this.zzara.getParcelFileDescriptor());
    this.mClosed = true;
  }
  
  public boolean zzsz()
  {
    return this.mClosed;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */