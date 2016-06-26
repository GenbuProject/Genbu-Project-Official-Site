package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzhb
public class zzcm
  extends NativeAppInstallAd
{
  private final zzcl zzyM;
  private final List<NativeAd.Image> zzyN = new ArrayList();
  private final zzci zzyO;
  
  public zzcm(zzcl paramzzcl)
  {
    this.zzyM = paramzzcl;
    try
    {
      paramzzcl = this.zzyM.getImages();
      if (paramzzcl != null)
      {
        paramzzcl = paramzzcl.iterator();
        while (paramzzcl.hasNext())
        {
          zzch localzzch = zzc(paramzzcl.next());
          if (localzzch != null) {
            this.zzyN.add(new zzci(localzzch));
          }
        }
      }
      try
      {
        paramzzcl = this.zzyM.zzdK();
        if (paramzzcl == null) {
          break label129;
        }
        paramzzcl = new zzci(paramzzcl);
      }
      catch (RemoteException paramzzcl)
      {
        for (;;)
        {
          zzb.zzb("Failed to get icon.", paramzzcl);
          paramzzcl = null;
        }
      }
    }
    catch (RemoteException paramzzcl)
    {
      zzb.zzb("Failed to get image.", paramzzcl);
    }
    this.zzyO = paramzzcl;
  }
  
  public void destroy()
  {
    try
    {
      this.zzyM.destroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to destroy", localRemoteException);
    }
  }
  
  public CharSequence getBody()
  {
    try
    {
      String str = this.zzyM.getBody();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get body.", localRemoteException);
    }
    return null;
  }
  
  public CharSequence getCallToAction()
  {
    try
    {
      String str = this.zzyM.getCallToAction();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get call to action.", localRemoteException);
    }
    return null;
  }
  
  public Bundle getExtras()
  {
    try
    {
      Bundle localBundle = this.zzyM.getExtras();
      return localBundle;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get extras", localRemoteException);
    }
    return null;
  }
  
  public CharSequence getHeadline()
  {
    try
    {
      String str = this.zzyM.getHeadline();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get headline.", localRemoteException);
    }
    return null;
  }
  
  public NativeAd.Image getIcon()
  {
    return this.zzyO;
  }
  
  public List<NativeAd.Image> getImages()
  {
    return this.zzyN;
  }
  
  public CharSequence getPrice()
  {
    try
    {
      String str = this.zzyM.getPrice();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get price.", localRemoteException);
    }
    return null;
  }
  
  public Double getStarRating()
  {
    try
    {
      double d = this.zzyM.getStarRating();
      if (d == -1.0D) {
        return null;
      }
      return Double.valueOf(d);
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get star rating.", localRemoteException);
    }
    return null;
  }
  
  public CharSequence getStore()
  {
    try
    {
      String str = this.zzyM.getStore();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get store", localRemoteException);
    }
    return null;
  }
  
  zzch zzc(Object paramObject)
  {
    if ((paramObject instanceof IBinder)) {
      return zzch.zza.zzt((IBinder)paramObject);
    }
    return null;
  }
  
  protected zzd zzdL()
  {
    try
    {
      zzd localzzd = this.zzyM.zzdL();
      return localzzd;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to retrieve native ad engine.", localRemoteException);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzcm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */