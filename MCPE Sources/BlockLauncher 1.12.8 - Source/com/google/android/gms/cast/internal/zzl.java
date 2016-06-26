package com.google.android.gms.cast.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import java.util.Locale;

public class zzl
{
  private static boolean zzaed = false;
  protected final String mTag;
  private final boolean zzaee;
  private boolean zzaef;
  private boolean zzaeg;
  private String zzaeh;
  
  public zzl(String paramString)
  {
    this(paramString, zzox());
  }
  
  public zzl(String paramString, boolean paramBoolean)
  {
    zzx.zzh(paramString, "The log tag cannot be null or empty.");
    this.mTag = paramString;
    if (paramString.length() <= 23) {}
    for (boolean bool = true;; bool = false)
    {
      this.zzaee = bool;
      this.zzaef = paramBoolean;
      this.zzaeg = false;
      return;
    }
  }
  
  public static boolean zzox()
  {
    return zzaed;
  }
  
  public void zzY(boolean paramBoolean)
  {
    this.zzaef = paramBoolean;
  }
  
  public void zza(String paramString, Object... paramVarArgs)
  {
    if (zzow()) {
      Log.v(this.mTag, zzg(paramString, paramVarArgs));
    }
  }
  
  public void zzb(String paramString, Object... paramVarArgs)
  {
    if ((zzov()) || (zzaed)) {
      Log.d(this.mTag, zzg(paramString, paramVarArgs));
    }
  }
  
  public void zzb(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    if ((zzov()) || (zzaed)) {
      Log.d(this.mTag, zzg(paramString, paramVarArgs), paramThrowable);
    }
  }
  
  public void zzc(String paramString, Object... paramVarArgs)
  {
    Log.e(this.mTag, zzg(paramString, paramVarArgs));
  }
  
  public void zzc(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    Log.w(this.mTag, zzg(paramString, paramVarArgs), paramThrowable);
  }
  
  public void zzcn(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (paramString = null;; paramString = String.format("[%s] ", new Object[] { paramString }))
    {
      this.zzaeh = paramString;
      return;
    }
  }
  
  public void zze(String paramString, Object... paramVarArgs)
  {
    Log.i(this.mTag, zzg(paramString, paramVarArgs));
  }
  
  public void zzf(String paramString, Object... paramVarArgs)
  {
    Log.w(this.mTag, zzg(paramString, paramVarArgs));
  }
  
  protected String zzg(String paramString, Object... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {}
    for (;;)
    {
      paramVarArgs = paramString;
      if (!TextUtils.isEmpty(this.zzaeh)) {
        paramVarArgs = this.zzaeh + paramString;
      }
      return paramVarArgs;
      paramString = String.format(Locale.ROOT, paramString, paramVarArgs);
    }
  }
  
  public boolean zzov()
  {
    return (this.zzaef) || ((this.zzaee) && (Log.isLoggable(this.mTag, 3)));
  }
  
  public boolean zzow()
  {
    return this.zzaeg;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */