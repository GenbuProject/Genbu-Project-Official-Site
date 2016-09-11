package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzhb
public class zzfg
  extends zzfb.zza
{
  private final NativeAppInstallAdMapper zzCM;
  
  public zzfg(NativeAppInstallAdMapper paramNativeAppInstallAdMapper)
  {
    this.zzCM = paramNativeAppInstallAdMapper;
  }
  
  public String getBody()
  {
    return this.zzCM.getBody();
  }
  
  public String getCallToAction()
  {
    return this.zzCM.getCallToAction();
  }
  
  public Bundle getExtras()
  {
    return this.zzCM.getExtras();
  }
  
  public String getHeadline()
  {
    return this.zzCM.getHeadline();
  }
  
  public List getImages()
  {
    Object localObject = this.zzCM.getImages();
    if (localObject != null)
    {
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        NativeAd.Image localImage = (NativeAd.Image)((Iterator)localObject).next();
        localArrayList.add(new zzc(localImage.getDrawable(), localImage.getUri(), localImage.getScale()));
      }
      return localArrayList;
    }
    return null;
  }
  
  public boolean getOverrideClickHandling()
  {
    return this.zzCM.getOverrideClickHandling();
  }
  
  public boolean getOverrideImpressionRecording()
  {
    return this.zzCM.getOverrideImpressionRecording();
  }
  
  public String getPrice()
  {
    return this.zzCM.getPrice();
  }
  
  public double getStarRating()
  {
    return this.zzCM.getStarRating();
  }
  
  public String getStore()
  {
    return this.zzCM.getStore();
  }
  
  public void recordImpression()
  {
    this.zzCM.recordImpression();
  }
  
  public void zzc(zzd paramzzd)
  {
    this.zzCM.handleClick((View)zze.zzp(paramzzd));
  }
  
  public void zzd(zzd paramzzd)
  {
    this.zzCM.trackView((View)zze.zzp(paramzzd));
  }
  
  public zzch zzdK()
  {
    NativeAd.Image localImage = this.zzCM.getIcon();
    if (localImage != null) {
      return new zzc(localImage.getDrawable(), localImage.getUri(), localImage.getScale());
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */