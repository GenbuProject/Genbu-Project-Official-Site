package com.google.android.gms.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.internal.zzl;
import com.google.android.gms.drive.internal.zzv;

public class CreateFileActivityBuilder
{
  public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
  private final zzl zzaoB = new zzl(0);
  private DriveContents zzaoC;
  private boolean zzaoD;
  
  public IntentSender build(GoogleApiClient paramGoogleApiClient)
  {
    zzx.zzb(Boolean.valueOf(this.zzaoD), "Must call setInitialDriveContents to CreateFileActivityBuilder.");
    zzx.zza(paramGoogleApiClient.isConnected(), "Client must be connected");
    if (this.zzaoC != null) {
      this.zzaoC.zzsy();
    }
    return this.zzaoB.build(paramGoogleApiClient);
  }
  
  public CreateFileActivityBuilder setActivityStartFolder(DriveId paramDriveId)
  {
    this.zzaoB.zza(paramDriveId);
    return this;
  }
  
  public CreateFileActivityBuilder setActivityTitle(String paramString)
  {
    this.zzaoB.zzdb(paramString);
    return this;
  }
  
  public CreateFileActivityBuilder setInitialDriveContents(DriveContents paramDriveContents)
  {
    if (paramDriveContents != null)
    {
      if (!(paramDriveContents instanceof zzv)) {
        throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
      }
      if (paramDriveContents.getDriveId() != null) {
        throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
      }
      if (paramDriveContents.zzsz()) {
        throw new IllegalArgumentException("DriveContents are already closed.");
      }
      this.zzaoB.zzcZ(paramDriveContents.zzsx().getRequestId());
      this.zzaoC = paramDriveContents;
    }
    for (;;)
    {
      this.zzaoD = true;
      return this;
      this.zzaoB.zzcZ(1);
    }
  }
  
  public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet paramMetadataChangeSet)
  {
    this.zzaoB.zza(paramMetadataChangeSet);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\CreateFileActivityBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */