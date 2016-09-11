package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public class zzae
{
  private static volatile Logger zzSV;
  
  static
  {
    setLogger(new zzs());
  }
  
  public static Logger getLogger()
  {
    return zzSV;
  }
  
  public static void setLogger(Logger paramLogger)
  {
    zzSV = paramLogger;
  }
  
  public static void v(String paramString)
  {
    Object localObject = zzaf.zzlx();
    if (localObject != null) {
      ((zzaf)localObject).zzbd(paramString);
    }
    for (;;)
    {
      localObject = zzSV;
      if (localObject != null) {
        ((Logger)localObject).verbose(paramString);
      }
      return;
      if (zzQ(0)) {
        Log.v((String)zzy.zzRL.get(), paramString);
      }
    }
  }
  
  public static boolean zzQ(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getLogger() != null)
    {
      bool1 = bool2;
      if (getLogger().getLogLevel() <= paramInt) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static void zzaJ(String paramString)
  {
    Object localObject = zzaf.zzlx();
    if (localObject != null) {
      ((zzaf)localObject).zzbf(paramString);
    }
    for (;;)
    {
      localObject = zzSV;
      if (localObject != null) {
        ((Logger)localObject).info(paramString);
      }
      return;
      if (zzQ(1)) {
        Log.i((String)zzy.zzRL.get(), paramString);
      }
    }
  }
  
  public static void zzaK(String paramString)
  {
    Object localObject = zzaf.zzlx();
    if (localObject != null) {
      ((zzaf)localObject).zzbg(paramString);
    }
    for (;;)
    {
      localObject = zzSV;
      if (localObject != null) {
        ((Logger)localObject).warn(paramString);
      }
      return;
      if (zzQ(2)) {
        Log.w((String)zzy.zzRL.get(), paramString);
      }
    }
  }
  
  public static void zzf(String paramString, Object paramObject)
  {
    zzaf localzzaf = zzaf.zzlx();
    if (localzzaf != null) {
      localzzaf.zze(paramString, paramObject);
    }
    while (!zzQ(3))
    {
      paramObject = zzSV;
      if (paramObject != null) {
        ((Logger)paramObject).error(paramString);
      }
      return;
    }
    if (paramObject != null) {}
    for (paramObject = paramString + ":" + paramObject;; paramObject = paramString)
    {
      Log.e((String)zzy.zzRL.get(), (String)paramObject);
      break;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */