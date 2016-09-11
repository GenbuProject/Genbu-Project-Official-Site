package com.google.android.gms.panorama;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public abstract interface PanoramaApi
{
  public abstract PendingResult<PanoramaResult> loadPanoramaInfo(GoogleApiClient paramGoogleApiClient, Uri paramUri);
  
  public abstract PendingResult<PanoramaResult> loadPanoramaInfoAndGrantAccess(GoogleApiClient paramGoogleApiClient, Uri paramUri);
  
  public static abstract interface PanoramaResult
    extends Result
  {
    public abstract Intent getViewerIntent();
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\panorama\PanoramaApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */