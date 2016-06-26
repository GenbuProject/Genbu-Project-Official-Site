package com.google.android.gms.measurement;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class zzf<T extends zzf>
{
  private final zzg zzaUs;
  protected final zzc zzaUt;
  private final List<zzd> zzaUu;
  
  protected zzf(zzg paramzzg, zzmq paramzzmq)
  {
    zzx.zzz(paramzzg);
    this.zzaUs = paramzzg;
    this.zzaUu = new ArrayList();
    paramzzg = new zzc(this, paramzzmq);
    paramzzg.zzAE();
    this.zzaUt = paramzzg;
  }
  
  protected zzg zzAC()
  {
    return this.zzaUs;
  }
  
  public zzc zzAF()
  {
    return this.zzaUt;
  }
  
  public List<zzi> zzAG()
  {
    return this.zzaUt.zzAw();
  }
  
  protected void zza(zzc paramzzc) {}
  
  protected void zzd(zzc paramzzc)
  {
    Iterator localIterator = this.zzaUu.iterator();
    while (localIterator.hasNext()) {
      ((zzd)localIterator.next()).zza(this, paramzzc);
    }
  }
  
  public zzc zziy()
  {
    zzc localzzc = this.zzaUt.zzAu();
    zzd(localzzc);
    return localzzc;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */