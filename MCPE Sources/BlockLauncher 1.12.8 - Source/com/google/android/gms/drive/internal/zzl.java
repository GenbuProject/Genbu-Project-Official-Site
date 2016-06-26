package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class zzl
{
  private String zzapg;
  private DriveId zzapj;
  protected MetadataChangeSet zzaqt;
  private Integer zzaqu;
  private final int zzaqv;
  
  public zzl(int paramInt)
  {
    this.zzaqv = paramInt;
  }
  
  public IntentSender build(GoogleApiClient paramGoogleApiClient)
  {
    zzx.zzb(this.zzaqt, "Must provide initial metadata to CreateFileActivityBuilder.");
    zzx.zza(paramGoogleApiClient.isConnected(), "Client must be connected");
    paramGoogleApiClient = (zzu)paramGoogleApiClient.zza(Drive.zzUI);
    this.zzaqt.zzsL().setContext(paramGoogleApiClient.getContext());
    if (this.zzaqu == null) {}
    for (int i = 0;; i = this.zzaqu.intValue()) {
      try
      {
        paramGoogleApiClient = paramGoogleApiClient.zzte().zza(new CreateFileIntentSenderRequest(this.zzaqt.zzsL(), i, this.zzapg, this.zzapj, this.zzaqv));
        return paramGoogleApiClient;
      }
      catch (RemoteException paramGoogleApiClient)
      {
        throw new RuntimeException("Unable to connect Drive Play Service", paramGoogleApiClient);
      }
    }
  }
  
  public void zza(DriveId paramDriveId)
  {
    this.zzapj = ((DriveId)zzx.zzz(paramDriveId));
  }
  
  public void zza(MetadataChangeSet paramMetadataChangeSet)
  {
    this.zzaqt = ((MetadataChangeSet)zzx.zzz(paramMetadataChangeSet));
  }
  
  public void zzcZ(int paramInt)
  {
    this.zzaqu = Integer.valueOf(paramInt);
  }
  
  public void zzdb(String paramString)
  {
    this.zzapg = ((String)zzx.zzz(paramString));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */