package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.internal.zzqt;

public final class zzo
{
  public static final int zzaml = 23 - " PII_LOG".length();
  private static final String zzamm = null;
  private final String zzamn;
  private final String zzamo;
  
  public zzo(String paramString)
  {
    this(paramString, zzamm);
  }
  
  public zzo(String paramString1, String paramString2)
  {
    zzx.zzb(paramString1, "log tag cannot be null");
    if (paramString1.length() <= 23) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "tag \"%s\" is longer than the %d character maximum", new Object[] { paramString1, Integer.valueOf(23) });
      this.zzamn = paramString1;
      if ((paramString2 != null) && (paramString2.length() > 0)) {
        break;
      }
      this.zzamo = zzamm;
      return;
    }
    this.zzamo = paramString2;
  }
  
  private String zzcK(String paramString)
  {
    if (this.zzamo == null) {
      return paramString;
    }
    return this.zzamo.concat(paramString);
  }
  
  public void zzA(String paramString1, String paramString2)
  {
    if (zzbU(6)) {
      Log.e(paramString1, zzcK(paramString2));
    }
  }
  
  public void zza(Context paramContext, String paramString1, String paramString2, Throwable paramThrowable)
  {
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while ((i < arrayOfStackTraceElement.length) && (i < 2))
    {
      localStringBuilder.append(arrayOfStackTraceElement[i].toString());
      localStringBuilder.append("\n");
      i += 1;
    }
    paramContext = new zzqt(paramContext, 10);
    paramContext.zza("GMS_WTF", null, new String[] { "GMS_WTF", localStringBuilder.toString() });
    paramContext.send();
    if (zzbU(7))
    {
      Log.e(paramString1, zzcK(paramString2), paramThrowable);
      Log.wtf(paramString1, zzcK(paramString2), paramThrowable);
    }
  }
  
  public void zza(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzbU(4)) {
      Log.i(paramString1, zzcK(paramString2), paramThrowable);
    }
  }
  
  public void zzb(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzbU(5)) {
      Log.w(paramString1, zzcK(paramString2), paramThrowable);
    }
  }
  
  public boolean zzbU(int paramInt)
  {
    return Log.isLoggable(this.zzamn, paramInt);
  }
  
  public void zzc(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzbU(6)) {
      Log.e(paramString1, zzcK(paramString2), paramThrowable);
    }
  }
  
  public void zzy(String paramString1, String paramString2)
  {
    if (zzbU(3)) {
      Log.d(paramString1, zzcK(paramString2));
    }
  }
  
  public void zzz(String paramString1, String paramString2)
  {
    if (zzbU(5)) {
      Log.w(paramString1, zzcK(paramString2));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\internal\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */