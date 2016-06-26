package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzr;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzhb
public class zzdq
  implements Iterable<zzdp>
{
  private final List<zzdp> zzzM = new LinkedList();
  
  private zzdp zzf(zzjp paramzzjp)
  {
    Iterator localIterator = zzr.zzbR().iterator();
    while (localIterator.hasNext())
    {
      zzdp localzzdp = (zzdp)localIterator.next();
      if (localzzdp.zzpD == paramzzjp) {
        return localzzdp;
      }
    }
    return null;
  }
  
  public Iterator<zzdp> iterator()
  {
    return this.zzzM.iterator();
  }
  
  public void zza(zzdp paramzzdp)
  {
    this.zzzM.add(paramzzdp);
  }
  
  public void zzb(zzdp paramzzdp)
  {
    this.zzzM.remove(paramzzdp);
  }
  
  public boolean zzd(zzjp paramzzjp)
  {
    paramzzjp = zzf(paramzzjp);
    if (paramzzjp != null)
    {
      paramzzjp.zzzJ.abort();
      return true;
    }
    return false;
  }
  
  public boolean zze(zzjp paramzzjp)
  {
    return zzf(paramzzjp) != null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzdq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */