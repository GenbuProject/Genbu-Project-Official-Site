package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzhb
public class zzco
  extends NativeContentAd
{
  private final List<NativeAd.Image> zzyN = new ArrayList();
  private final zzcn zzyP;
  private final zzci zzyQ;
  
  public zzco(zzcn paramzzcn)
  {
    this.zzyP = paramzzcn;
    try
    {
      paramzzcn = this.zzyP.getImages();
      if (paramzzcn != null)
      {
        paramzzcn = paramzzcn.iterator();
        while (paramzzcn.hasNext())
        {
          zzch localzzch = zzc(paramzzcn.next());
          if (localzzch != null) {
            this.zzyN.add(new zzci(localzzch));
          }
        }
      }
      try
      {
        paramzzcn = this.zzyP.zzdO();
        if (paramzzcn == null) {
          break label129;
        }
        paramzzcn = new zzci(paramzzcn);
      }
      catch (RemoteException paramzzcn)
      {
        for (;;)
        {
          zzb.zzb("Failed to get icon.", paramzzcn);
          paramzzcn = null;
        }
      }
    }
    catch (RemoteException paramzzcn)
    {
      zzb.zzb("Failed to get image.", paramzzcn);
    }
    this.zzyQ = paramzzcn;
  }
  
  public void destroy()
  {
    try
    {
      this.zzyP.destroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to destroy", localRemoteException);
    }
  }
  
  public CharSequence getAdvertiser()
  {
    try
    {
      String str = this.zzyP.getAdvertiser();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get attribution.", localRemoteException);
    }
    return null;
  }
  
  public CharSequence getBody()
  {
    try
    {
      String str = this.zzyP.getBody();
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
      String str = this.zzyP.getCallToAction();
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
      Bundle localBundle = this.zzyP.getExtras();
      return localBundle;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Failed to get extras", localRemoteException);
    }
    return null;
  }
  
  public CharSequence getHeadline()
  {
    try
    {
      String str = this.zzyP.getHeadline();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get headline.", localRemoteException);
    }
    return null;
  }
  
  public List<NativeAd.Image> getImages()
  {
    return this.zzyN;
  }
  
  public NativeAd.Image getLogo()
  {
    return this.zzyQ;
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
      zzd localzzd = this.zzyP.zzdL();
      return localzzd;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to retrieve native ad engine.", localRemoteException);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzco.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */