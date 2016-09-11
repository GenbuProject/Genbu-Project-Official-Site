package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.stats.zzg;
import com.google.android.gms.common.stats.zzi;

public class zzrp
{
  private static boolean DEBUG = false;
  private static String TAG = "WakeLock";
  private static String zzbhl = "*gcore*:";
  private final Context mContext;
  private final String zzanQ;
  private final PowerManager.WakeLock zzbhm;
  private WorkSource zzbhn;
  private final int zzbho;
  private final String zzbhp;
  private boolean zzbhq = true;
  private int zzbhr;
  private int zzbhs;
  
  public zzrp(Context paramContext, int paramInt, String paramString) {}
  
  @SuppressLint({"UnwrappedWakeLock"})
  public zzrp(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    zzx.zzh(paramString1, "Wake lock name can NOT be empty");
    this.zzbho = paramInt;
    this.zzbhp = paramString2;
    this.mContext = paramContext.getApplicationContext();
    if ((!zzni.zzcV(paramString3)) && ("com.google.android.gms" != paramString3))
    {
      this.zzanQ = (zzbhl + paramString1);
      this.zzbhm = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(paramInt, paramString1);
      if (zznj.zzaA(this.mContext))
      {
        paramString1 = paramString3;
        if (zzni.zzcV(paramString3))
        {
          if ((!zzd.zzakE) || (!zzlz.isInitialized())) {
            break label195;
          }
          Log.e(TAG, "callingPackage is not supposed to be empty for wakelock " + this.zzanQ + "!", new IllegalArgumentException());
        }
      }
    }
    label195:
    for (paramString1 = "com.google.android.gms";; paramString1 = paramContext.getPackageName())
    {
      this.zzbhn = zznj.zzl(paramContext, paramString1);
      zzc(this.zzbhn);
      return;
      this.zzanQ = paramString1;
      break;
    }
  }
  
  private void zzfJ(String paramString)
  {
    boolean bool = zzfK(paramString);
    String str = zzn(paramString, bool);
    if (DEBUG) {
      Log.d(TAG, "Release:\n mWakeLockName: " + this.zzanQ + "\n mSecondaryName: " + this.zzbhp + "\nmReferenceCounted: " + this.zzbhq + "\nreason: " + paramString + "\n mOpenEventCount" + this.zzbhs + "\nuseWithReason: " + bool + "\ntrackingName: " + str);
    }
    try
    {
      if (this.zzbhq)
      {
        int i = this.zzbhr - 1;
        this.zzbhr = i;
        if ((i == 0) || (bool)) {}
      }
      else
      {
        if ((this.zzbhq) || (this.zzbhs != 1)) {
          break label205;
        }
      }
      zzi.zzrZ().zza(this.mContext, zzg.zza(this.zzbhm, str), 8, this.zzanQ, str, this.zzbho, zznj.zzb(this.zzbhn));
      this.zzbhs -= 1;
      label205:
      return;
    }
    finally {}
  }
  
  private boolean zzfK(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (!paramString.equals(this.zzbhp));
  }
  
  private void zzj(String paramString, long paramLong)
  {
    boolean bool = zzfK(paramString);
    String str = zzn(paramString, bool);
    if (DEBUG) {
      Log.d(TAG, "Acquire:\n mWakeLockName: " + this.zzanQ + "\n mSecondaryName: " + this.zzbhp + "\nmReferenceCounted: " + this.zzbhq + "\nreason: " + paramString + "\nmOpenEventCount" + this.zzbhs + "\nuseWithReason: " + bool + "\ntrackingName: " + str + "\ntimeout: " + paramLong);
    }
    try
    {
      if (this.zzbhq)
      {
        int i = this.zzbhr;
        this.zzbhr = (i + 1);
        if ((i == 0) || (bool)) {}
      }
      else
      {
        if ((this.zzbhq) || (this.zzbhs != 0)) {
          break label221;
        }
      }
      zzi.zzrZ().zza(this.mContext, zzg.zza(this.zzbhm, str), 7, this.zzanQ, str, this.zzbho, zznj.zzb(this.zzbhn), paramLong);
      this.zzbhs += 1;
      label221:
      return;
    }
    finally {}
  }
  
  private String zzn(String paramString, boolean paramBoolean)
  {
    if (this.zzbhq)
    {
      if (paramBoolean) {
        return paramString;
      }
      return this.zzbhp;
    }
    return this.zzbhp;
  }
  
  public void acquire(long paramLong)
  {
    if ((!zzne.zzsg()) && (this.zzbhq)) {
      Log.wtf(TAG, "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: " + this.zzanQ);
    }
    zzj(null, paramLong);
    this.zzbhm.acquire(paramLong);
  }
  
  public boolean isHeld()
  {
    return this.zzbhm.isHeld();
  }
  
  public void release()
  {
    zzfJ(null);
    this.zzbhm.release();
  }
  
  public void setReferenceCounted(boolean paramBoolean)
  {
    this.zzbhm.setReferenceCounted(paramBoolean);
    this.zzbhq = paramBoolean;
  }
  
  public void zzc(WorkSource paramWorkSource)
  {
    if ((zznj.zzaA(this.mContext)) && (paramWorkSource != null))
    {
      if (this.zzbhn == null) {
        break label42;
      }
      this.zzbhn.add(paramWorkSource);
    }
    for (;;)
    {
      this.zzbhm.setWorkSource(this.zzbhn);
      return;
      label42:
      this.zzbhn = paramWorkSource;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzrp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */