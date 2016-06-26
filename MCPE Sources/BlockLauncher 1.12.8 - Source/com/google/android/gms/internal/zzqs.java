package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.panorama.PanoramaApi.PanoramaResult;

class zzqs
  implements PanoramaApi.PanoramaResult
{
  private final Status zzUX;
  private final Intent zzbdv;
  
  public zzqs(Status paramStatus, Intent paramIntent)
  {
    this.zzUX = ((Status)zzx.zzz(paramStatus));
    this.zzbdv = paramIntent;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
  
  public Intent getViewerIntent()
  {
    return this.zzbdv;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzqs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */