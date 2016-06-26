package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzhb
public class zzfh
  extends zzfc.zza
{
  private final NativeContentAdMapper zzCN;
  
  public zzfh(NativeContentAdMapper paramNativeContentAdMapper)
  {
    this.zzCN = paramNativeContentAdMapper;
  }
  
  public String getAdvertiser()
  {
    return this.zzCN.getAdvertiser();
  }
  
  public String getBody()
  {
    return this.zzCN.getBody();
  }
  
  public String getCallToAction()
  {
    return this.zzCN.getCallToAction();
  }
  
  public Bundle getExtras()
  {
    return this.zzCN.getExtras();
  }
  
  public String getHeadline()
  {
    return this.zzCN.getHeadline();
  }
  
  public List getImages()
  {
    Object localObject = this.zzCN.getImages();
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
    return this.zzCN.getOverrideClickHandling();
  }
  
  public boolean getOverrideImpressionRecording()
  {
    return this.zzCN.getOverrideImpressionRecording();
  }
  
  public void recordImpression()
  {
    this.zzCN.recordImpression();
  }
  
  public void zzc(zzd paramzzd)
  {
    this.zzCN.handleClick((View)zze.zzp(paramzzd));
  }
  
  public void zzd(zzd paramzzd)
  {
    this.zzCN.trackView((View)zze.zzp(paramzzd));
  }
  
  public zzch zzdO()
  {
    NativeAd.Image localImage = this.zzCN.getLogo();
    if (localImage != null) {
      return new zzc(localImage.getDrawable(), localImage.getUri(), localImage.getScale());
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzfh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */