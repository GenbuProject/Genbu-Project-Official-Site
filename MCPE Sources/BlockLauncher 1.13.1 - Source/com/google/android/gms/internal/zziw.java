package com.google.android.gms.internal;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

@zzhb
public class zziw
{
  private static zzl zzMy;
  public static final zza<Void> zzMz = new zza()
  {
    public Void zzhB()
    {
      return null;
    }
    
    public Void zzi(InputStream paramAnonymousInputStream)
    {
      return null;
    }
  };
  private static final Object zzqy = new Object();
  
  public zziw(Context paramContext)
  {
    zzMy = zzS(paramContext);
  }
  
  private static zzl zzS(Context paramContext)
  {
    synchronized (zzqy)
    {
      if (zzMy == null) {
        zzMy = zzac.zza(paramContext.getApplicationContext());
      }
      paramContext = zzMy;
      return paramContext;
    }
  }
  
  public <T> zzjg<T> zza(String paramString, zza<T> paramzza)
  {
    zzc localzzc = new zzc(null);
    zzMy.zze(new zzb(paramString, paramzza, localzzc));
    return localzzc;
  }
  
  public zzjg<String> zzb(final String paramString, final Map<String, String> paramMap)
  {
    final zzc localzzc = new zzc(null);
    paramString = new zzab(paramString, localzzc, new zzm.zza()
    {
      public void zze(zzr paramAnonymouszzr)
      {
        zzin.zzaK("Failed to load URL: " + paramString + "\n" + paramAnonymouszzr.toString());
        localzzc.zzb(null);
      }
    })
    {
      public Map<String, String> getHeaders()
        throws zza
      {
        if (paramMap == null) {
          return super.getHeaders();
        }
        return paramMap;
      }
    };
    zzMy.zze(paramString);
    return localzzc;
  }
  
  public static abstract interface zza<T>
  {
    public abstract T zzgp();
    
    public abstract T zzh(InputStream paramInputStream);
  }
  
  private static class zzb<T>
    extends zzk<InputStream>
  {
    private final zziw.zza<T> zzMD;
    private final zzm.zzb<T> zzaG;
    
    public zzb(String paramString, final zziw.zza<T> paramzza, zzm.zzb<T> paramzzb)
    {
      super(paramString, new zzm.zza()
      {
        public void zze(zzr paramAnonymouszzr)
        {
          zziw.zzb.this.zzb(paramzza.zzgp());
        }
      });
      this.zzMD = paramzza;
      this.zzaG = paramzzb;
    }
    
    protected zzm<InputStream> zza(zzi paramzzi)
    {
      return zzm.zza(new ByteArrayInputStream(paramzzi.data), zzx.zzb(paramzzi));
    }
    
    protected void zzj(InputStream paramInputStream)
    {
      this.zzaG.zzb(this.zzMD.zzh(paramInputStream));
    }
  }
  
  private class zzc<T>
    extends zzjd<T>
    implements zzm.zzb<T>
  {
    private zzc() {}
    
    public void zzb(T paramT)
    {
      super.zzg(paramT);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zziw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */