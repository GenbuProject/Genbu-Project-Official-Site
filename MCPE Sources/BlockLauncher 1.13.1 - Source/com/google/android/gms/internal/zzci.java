package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;

@zzhb
public class zzci
  extends NativeAd.Image
{
  private final Drawable mDrawable;
  private final Uri mUri;
  private final double zzxV;
  private final zzch zzyL;
  
  public zzci(zzch paramzzch)
  {
    this.zzyL = paramzzch;
    try
    {
      paramzzch = this.zzyL.zzdJ();
      if (paramzzch == null) {
        break label83;
      }
      paramzzch = (Drawable)zze.zzp(paramzzch);
    }
    catch (RemoteException paramzzch)
    {
      try
      {
        paramzzch = this.zzyL.getUri();
        this.mUri = paramzzch;
        double d1 = 1.0D;
        try
        {
          double d2 = this.zzyL.getScale();
          d1 = d2;
        }
        catch (RemoteException paramzzch)
        {
          for (;;)
          {
            zzb.zzb("Failed to get scale.", paramzzch);
          }
        }
        this.zzxV = d1;
        return;
        paramzzch = paramzzch;
        zzb.zzb("Failed to get drawable.", paramzzch);
        paramzzch = null;
      }
      catch (RemoteException paramzzch)
      {
        for (;;)
        {
          zzb.zzb("Failed to get uri.", paramzzch);
          paramzzch = (zzch)localObject;
        }
      }
    }
    this.mDrawable = paramzzch;
  }
  
  public Drawable getDrawable()
  {
    return this.mDrawable;
  }
  
  public double getScale()
  {
    return this.zzxV;
  }
  
  public Uri getUri()
  {
    return this.mUri;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */