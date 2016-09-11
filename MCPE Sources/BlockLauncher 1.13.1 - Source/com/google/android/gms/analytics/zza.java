package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzk;
import com.google.android.gms.analytics.internal.zzn;
import com.google.android.gms.analytics.internal.zzu;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.measurement.zzc;
import com.google.android.gms.measurement.zzi;
import java.util.List;
import java.util.ListIterator;

public class zza
  extends com.google.android.gms.measurement.zzf<zza>
{
  private final com.google.android.gms.analytics.internal.zzf zzOK;
  private boolean zzOL;
  
  public zza(com.google.android.gms.analytics.internal.zzf paramzzf)
  {
    super(paramzzf.zzjo(), paramzzf.zzjl());
    this.zzOK = paramzzf;
  }
  
  public void enableAdvertisingIdCollection(boolean paramBoolean)
  {
    this.zzOL = paramBoolean;
  }
  
  protected void zza(zzc paramzzc)
  {
    paramzzc = (zzke)paramzzc.zzf(zzke.class);
    if (TextUtils.isEmpty(paramzzc.getClientId())) {
      paramzzc.setClientId(this.zzOK.zzjC().zzkk());
    }
    if ((this.zzOL) && (TextUtils.isEmpty(paramzzc.zziT())))
    {
      com.google.android.gms.analytics.internal.zza localzza = this.zzOK.zzjB();
      paramzzc.zzaY(localzza.zziY());
      paramzzc.zzH(localzza.zziU());
    }
  }
  
  public void zzaS(String paramString)
  {
    zzx.zzcM(paramString);
    zzaT(paramString);
    zzAG().add(new zzb(this.zzOK, paramString));
  }
  
  public void zzaT(String paramString)
  {
    paramString = zzb.zzaU(paramString);
    ListIterator localListIterator = zzAG().listIterator();
    while (localListIterator.hasNext()) {
      if (paramString.equals(((zzi)localListIterator.next()).zziA())) {
        localListIterator.remove();
      }
    }
  }
  
  com.google.android.gms.analytics.internal.zzf zzix()
  {
    return this.zzOK;
  }
  
  public zzc zziy()
  {
    zzc localzzc = zzAF().zzAu();
    localzzc.zzb(this.zzOK.zzjt().zzjS());
    localzzc.zzb(this.zzOK.zzju().zzkZ());
    zzd(localzzc);
    return localzzc;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */