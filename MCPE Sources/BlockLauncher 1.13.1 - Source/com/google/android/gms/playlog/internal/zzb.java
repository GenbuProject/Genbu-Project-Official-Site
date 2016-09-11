package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzsz.zzd;
import java.util.ArrayList;

public class zzb
{
  private final ArrayList<zza> zzbdE = new ArrayList();
  private int zzbdF;
  
  public zzb()
  {
    this(100);
  }
  
  public zzb(int paramInt)
  {
    this.zzbdF = paramInt;
  }
  
  private void zzEV()
  {
    while (getSize() > getCapacity()) {
      this.zzbdE.remove(0);
    }
  }
  
  public void clear()
  {
    this.zzbdE.clear();
  }
  
  public int getCapacity()
  {
    return this.zzbdF;
  }
  
  public int getSize()
  {
    return this.zzbdE.size();
  }
  
  public boolean isEmpty()
  {
    return this.zzbdE.isEmpty();
  }
  
  public ArrayList<zza> zzEU()
  {
    return this.zzbdE;
  }
  
  public void zza(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
  {
    this.zzbdE.add(new zza(paramPlayLoggerContext, paramLogEvent, null));
    zzEV();
  }
  
  public static class zza
  {
    public final PlayLoggerContext zzbdG;
    public final LogEvent zzbdH;
    public final zzsz.zzd zzbdI;
    
    private zza(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
    {
      this.zzbdG = ((PlayLoggerContext)zzx.zzz(paramPlayLoggerContext));
      this.zzbdH = ((LogEvent)zzx.zzz(paramLogEvent));
      this.zzbdI = null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\playlog\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */