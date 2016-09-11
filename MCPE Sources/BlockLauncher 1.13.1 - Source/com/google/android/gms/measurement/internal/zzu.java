package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzqa.zza;
import com.google.android.gms.internal.zzqa.zzb;
import com.google.android.gms.internal.zzqa.zzc;
import com.google.android.gms.internal.zzsm;
import com.google.android.gms.internal.zzsn;
import java.io.IOException;
import java.util.Map;

public class zzu
  extends zzz
{
  private final Map<String, Map<String, String>> zzaXF = new ArrayMap();
  private final Map<String, Map<String, Boolean>> zzaXG = new ArrayMap();
  private final Map<String, zzqa.zzb> zzaXH = new ArrayMap();
  
  zzu(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  private Map<String, String> zza(zzqa.zzb paramzzb)
  {
    ArrayMap localArrayMap = new ArrayMap();
    if ((paramzzb != null) && (paramzzb.zzaZV != null))
    {
      paramzzb = paramzzb.zzaZV;
      int j = paramzzb.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramzzb[i];
        if (localObject != null) {
          localArrayMap.put(((zzqa.zzc)localObject).key, ((zzqa.zzc)localObject).value);
        }
        i += 1;
      }
    }
    return localArrayMap;
  }
  
  private Map<String, Boolean> zzb(zzqa.zzb paramzzb)
  {
    ArrayMap localArrayMap = new ArrayMap();
    if ((paramzzb != null) && (paramzzb.zzaZW != null))
    {
      paramzzb = paramzzb.zzaZW;
      int j = paramzzb.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramzzb[i];
        if (localObject != null) {
          localArrayMap.put(((zzqa.zza)localObject).name, ((zzqa.zza)localObject).zzaZS);
        }
        i += 1;
      }
    }
    return localArrayMap;
  }
  
  @WorkerThread
  private zzqa.zzb zzf(String paramString, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return new zzqa.zzb();
    }
    paramArrayOfByte = zzsm.zzD(paramArrayOfByte);
    zzqa.zzb localzzb = new zzqa.zzb();
    try
    {
      localzzb.zzA(paramArrayOfByte);
      zzAo().zzCK().zze("Parsed config. version, gmp_app_id", localzzb.zzaZT, localzzb.zzaVt);
      return localzzb;
    }
    catch (IOException paramArrayOfByte)
    {
      zzAo().zzCF().zze("Unable to merge remote config", paramString, paramArrayOfByte);
    }
    return null;
  }
  
  @WorkerThread
  private void zzfj(String paramString)
  {
    zzjv();
    zzjk();
    zzx.zzcM(paramString);
    if (!this.zzaXH.containsKey(paramString))
    {
      localObject = zzCj().zzfa(paramString);
      if (localObject == null)
      {
        this.zzaXF.put(paramString, null);
        this.zzaXG.put(paramString, null);
        this.zzaXH.put(paramString, null);
      }
    }
    else
    {
      return;
    }
    Object localObject = zzf(paramString, (byte[])localObject);
    this.zzaXF.put(paramString, zza((zzqa.zzb)localObject));
    this.zzaXG.put(paramString, zzb((zzqa.zzb)localObject));
    this.zzaXH.put(paramString, localObject);
  }
  
  @WorkerThread
  String zzO(String paramString1, String paramString2)
  {
    zzjk();
    zzfj(paramString1);
    paramString1 = (Map)this.zzaXF.get(paramString1);
    if (paramString1 != null) {
      return (String)paramString1.get(paramString2);
    }
    return null;
  }
  
  @WorkerThread
  boolean zzP(String paramString1, String paramString2)
  {
    zzjk();
    zzfj(paramString1);
    paramString1 = (Map)this.zzaXG.get(paramString1);
    if (paramString1 != null)
    {
      paramString1 = (Boolean)paramString1.get(paramString2);
      if (paramString1 == null) {
        return false;
      }
      return paramString1.booleanValue();
    }
    return false;
  }
  
  @WorkerThread
  protected boolean zze(String paramString, byte[] paramArrayOfByte)
  {
    zzjv();
    zzjk();
    zzx.zzcM(paramString);
    zzqa.zzb localzzb = zzf(paramString, paramArrayOfByte);
    if (localzzb == null) {
      return false;
    }
    this.zzaXG.put(paramString, zzb(localzzb));
    this.zzaXH.put(paramString, localzzb);
    this.zzaXF.put(paramString, zza(localzzb));
    zzCe().zza(paramString, localzzb.zzaZX);
    try
    {
      localzzb.zzaZX = null;
      byte[] arrayOfByte = new byte[localzzb.getSerializedSize()];
      localzzb.writeTo(zzsn.zzE(arrayOfByte));
      paramArrayOfByte = arrayOfByte;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        zzAo().zzCF().zzj("Unable to serialize reduced-size config.  Storing full config instead.", localIOException);
      }
    }
    zzCj().zzd(paramString, paramArrayOfByte);
    return true;
  }
  
  @WorkerThread
  protected zzqa.zzb zzfk(String paramString)
  {
    zzjv();
    zzjk();
    zzx.zzcM(paramString);
    zzfj(paramString);
    return (zzqa.zzb)this.zzaXH.get(paramString);
  }
  
  protected void zziJ() {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */