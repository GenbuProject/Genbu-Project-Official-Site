package com.google.android.gms.ads.internal.formats;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzch.zza;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzc
  extends zzch.zza
{
  private final Uri mUri;
  private final Drawable zzxU;
  private final double zzxV;
  
  public zzc(Drawable paramDrawable, Uri paramUri, double paramDouble)
  {
    this.zzxU = paramDrawable;
    this.mUri = paramUri;
    this.zzxV = paramDouble;
  }
  
  public double getScale()
  {
    return this.zzxV;
  }
  
  public Uri getUri()
    throws RemoteException
  {
    return this.mUri;
  }
  
  public zzd zzdJ()
    throws RemoteException
  {
    return zze.zzC(this.zzxU);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\formats\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */