package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;

class zzbl
  extends zzd
{
  private final zza.zzb<DriveApi.DriveContentsResult> zzamC;
  private final DriveFile.DownloadProgressListener zzasy;
  
  zzbl(zza.zzb<DriveApi.DriveContentsResult> paramzzb, DriveFile.DownloadProgressListener paramDownloadProgressListener)
  {
    this.zzamC = paramzzb;
    this.zzasy = paramDownloadProgressListener;
  }
  
  public void onError(Status paramStatus)
    throws RemoteException
  {
    this.zzamC.zzs(new zzs.zzb(paramStatus, null));
  }
  
  public void zza(OnContentsResponse paramOnContentsResponse)
    throws RemoteException
  {
    if (paramOnContentsResponse.zzto()) {}
    for (Status localStatus = new Status(-1);; localStatus = Status.zzagC)
    {
      this.zzamC.zzs(new zzs.zzb(localStatus, new zzv(paramOnContentsResponse.zztn())));
      return;
    }
  }
  
  public void zza(OnDownloadProgressResponse paramOnDownloadProgressResponse)
    throws RemoteException
  {
    if (this.zzasy != null) {
      this.zzasy.onProgress(paramOnDownloadProgressResponse.zztq(), paramOnDownloadProgressResponse.zztr());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzbl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */