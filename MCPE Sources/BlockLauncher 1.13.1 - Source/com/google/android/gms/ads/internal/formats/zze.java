package com.google.android.gms.ads.internal.formats;

import android.os.Bundle;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzch;
import com.google.android.gms.internal.zzcn.zza;
import com.google.android.gms.internal.zzhb;
import java.util.List;

@zzhb
public class zze
  extends zzcn.zza
  implements zzh.zza
{
  private Bundle mExtras;
  private Object zzpV = new Object();
  private String zzxW;
  private List<zzc> zzxX;
  private String zzxY;
  private String zzya;
  private zza zzye;
  private zzh zzyf;
  private zzch zzyg;
  private String zzyh;
  
  public zze(String paramString1, List paramList, String paramString2, zzch paramzzch, String paramString3, String paramString4, zza paramzza, Bundle paramBundle)
  {
    this.zzxW = paramString1;
    this.zzxX = paramList;
    this.zzxY = paramString2;
    this.zzyg = paramzzch;
    this.zzya = paramString3;
    this.zzyh = paramString4;
    this.zzye = paramzza;
    this.mExtras = paramBundle;
  }
  
  public void destroy()
  {
    this.zzxW = null;
    this.zzxX = null;
    this.zzxY = null;
    this.zzyg = null;
    this.zzya = null;
    this.zzyh = null;
    this.zzye = null;
    this.mExtras = null;
    this.zzpV = null;
    this.zzyf = null;
  }
  
  public String getAdvertiser()
  {
    return this.zzyh;
  }
  
  public String getBody()
  {
    return this.zzxY;
  }
  
  public String getCallToAction()
  {
    return this.zzya;
  }
  
  public String getCustomTemplateId()
  {
    return "";
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
  
  public String getHeadline()
  {
    return this.zzxW;
  }
  
  public List getImages()
  {
    return this.zzxX;
  }
  
  public void zzb(zzh paramzzh)
  {
    synchronized (this.zzpV)
    {
      this.zzyf = paramzzh;
      return;
    }
  }
  
  public zzd zzdL()
  {
    return com.google.android.gms.dynamic.zze.zzC(this.zzyf);
  }
  
  public String zzdM()
  {
    return "1";
  }
  
  public zza zzdN()
  {
    return this.zzye;
  }
  
  public zzch zzdO()
  {
    return this.zzyg;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\formats\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */