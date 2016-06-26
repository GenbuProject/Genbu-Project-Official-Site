package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class zzk<T>
  implements Comparable<zzk<T>>
{
  private final zzs.zza zzD;
  private final int zzE;
  private final String zzF;
  private final int zzG;
  private final zzm.zza zzH;
  private Integer zzI;
  private zzl zzJ;
  private boolean zzK;
  private boolean zzL;
  private boolean zzM;
  private long zzN;
  private zzo zzO;
  private zzb.zza zzP;
  
  public zzk(int paramInt, String paramString, zzm.zza paramzza)
  {
    if (zzs.zza.zzak) {}
    for (zzs.zza localzza = new zzs.zza();; localzza = null)
    {
      this.zzD = localzza;
      this.zzK = true;
      this.zzL = false;
      this.zzM = false;
      this.zzN = 0L;
      this.zzP = null;
      this.zzE = paramInt;
      this.zzF = paramString;
      this.zzH = paramzza;
      zza(new zzd());
      this.zzG = zzb(paramString);
      return;
    }
  }
  
  private byte[] zza(Map<String, String> paramMap, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localStringBuilder.append(URLEncoder.encode((String)localEntry.getKey(), paramString));
        localStringBuilder.append('=');
        localStringBuilder.append(URLEncoder.encode((String)localEntry.getValue(), paramString));
        localStringBuilder.append('&');
      }
      paramMap = localStringBuilder.toString().getBytes(paramString);
    }
    catch (UnsupportedEncodingException paramMap)
    {
      throw new RuntimeException("Encoding not supported: " + paramString, paramMap);
    }
    return paramMap;
  }
  
  private static int zzb(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = Uri.parse(paramString);
      if (paramString != null)
      {
        paramString = paramString.getHost();
        if (paramString != null) {
          return paramString.hashCode();
        }
      }
    }
    return 0;
  }
  
  public Map<String, String> getHeaders()
    throws zza
  {
    return Collections.emptyMap();
  }
  
  public int getMethod()
  {
    return this.zzE;
  }
  
  public String getUrl()
  {
    return this.zzF;
  }
  
  public boolean isCanceled()
  {
    return this.zzL;
  }
  
  public String toString()
  {
    String str2 = "0x" + Integer.toHexString(zzg());
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.zzL) {}
    for (String str1 = "[X] ";; str1 = "[ ] ") {
      return str1 + getUrl() + " " + str2 + " " + zzs() + " " + this.zzI;
    }
  }
  
  public final zzk<?> zza(int paramInt)
  {
    this.zzI = Integer.valueOf(paramInt);
    return this;
  }
  
  public zzk<?> zza(zzb.zza paramzza)
  {
    this.zzP = paramzza;
    return this;
  }
  
  public zzk<?> zza(zzl paramzzl)
  {
    this.zzJ = paramzzl;
    return this;
  }
  
  public zzk<?> zza(zzo paramzzo)
  {
    this.zzO = paramzzo;
    return this;
  }
  
  protected abstract zzm<T> zza(zzi paramzzi);
  
  protected abstract void zza(T paramT);
  
  protected zzr zzb(zzr paramzzr)
  {
    return paramzzr;
  }
  
  public int zzc(zzk<T> paramzzk)
  {
    zza localzza1 = zzs();
    zza localzza2 = paramzzk.zzs();
    if (localzza1 == localzza2) {
      return this.zzI.intValue() - paramzzk.zzI.intValue();
    }
    return localzza2.ordinal() - localzza1.ordinal();
  }
  
  public void zzc(zzr paramzzr)
  {
    if (this.zzH != null) {
      this.zzH.zze(paramzzr);
    }
  }
  
  public void zzc(String paramString)
  {
    if (zzs.zza.zzak) {
      this.zzD.zza(paramString, Thread.currentThread().getId());
    }
    while (this.zzN != 0L) {
      return;
    }
    this.zzN = SystemClock.elapsedRealtime();
  }
  
  void zzd(final String paramString)
  {
    if (this.zzJ != null) {
      this.zzJ.zzf(this);
    }
    final long l;
    if (zzs.zza.zzak)
    {
      l = Thread.currentThread().getId();
      if (Looper.myLooper() != Looper.getMainLooper()) {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            zzk.zzd(zzk.this).zza(paramString, l);
            zzk.zzd(zzk.this).zzd(toString());
          }
        });
      }
    }
    do
    {
      return;
      this.zzD.zza(paramString, l);
      this.zzD.zzd(toString());
      return;
      l = SystemClock.elapsedRealtime() - this.zzN;
    } while (l < 3000L);
    zzs.zzb("%d ms: %s", new Object[] { Long.valueOf(l), toString() });
  }
  
  public int zzg()
  {
    return this.zzG;
  }
  
  public String zzh()
  {
    return getUrl();
  }
  
  public zzb.zza zzi()
  {
    return this.zzP;
  }
  
  @Deprecated
  protected Map<String, String> zzj()
    throws zza
  {
    return zzn();
  }
  
  @Deprecated
  protected String zzk()
  {
    return zzo();
  }
  
  @Deprecated
  public String zzl()
  {
    return zzp();
  }
  
  @Deprecated
  public byte[] zzm()
    throws zza
  {
    Map localMap = zzj();
    if ((localMap != null) && (localMap.size() > 0)) {
      return zza(localMap, zzk());
    }
    return null;
  }
  
  protected Map<String, String> zzn()
    throws zza
  {
    return null;
  }
  
  protected String zzo()
  {
    return "UTF-8";
  }
  
  public String zzp()
  {
    return "application/x-www-form-urlencoded; charset=" + zzo();
  }
  
  public byte[] zzq()
    throws zza
  {
    Map localMap = zzn();
    if ((localMap != null) && (localMap.size() > 0)) {
      return zza(localMap, zzo());
    }
    return null;
  }
  
  public final boolean zzr()
  {
    return this.zzK;
  }
  
  public zza zzs()
  {
    return zza.zzU;
  }
  
  public final int zzt()
  {
    return this.zzO.zzd();
  }
  
  public zzo zzu()
  {
    return this.zzO;
  }
  
  public void zzv()
  {
    this.zzM = true;
  }
  
  public boolean zzw()
  {
    return this.zzM;
  }
  
  public static enum zza
  {
    private zza() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */