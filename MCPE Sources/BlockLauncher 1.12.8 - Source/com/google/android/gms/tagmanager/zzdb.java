package com.google.android.gms.tagmanager;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzdb
  extends zzak
{
  private static final String ID = zzad.zzdl.toString();
  private static final String NAME = zzae.zzgo.toString();
  private static final String zzblp = zzae.zzfT.toString();
  private static final String zzblq = zzae.zzgb.toString();
  private static final String zzblr = zzae.zzhW.toString();
  private final Context mContext;
  private Handler mHandler;
  private DataLayer zzbhN;
  private boolean zzbls;
  private boolean zzblt;
  private final HandlerThread zzblu;
  private final Set<String> zzblv = new HashSet();
  
  public zzdb(Context paramContext, DataLayer paramDataLayer)
  {
    super(ID, new String[] { zzblp, NAME });
    this.mContext = paramContext;
    this.zzbhN = paramDataLayer;
    this.zzblu = new HandlerThread("Google GTM SDK Timer", 10);
    this.zzblu.start();
    this.mHandler = new Handler(this.zzblu.getLooper());
  }
  
  public boolean zzFW()
  {
    return false;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    String str2 = zzdf.zzg((zzag.zza)paramMap.get(NAME));
    String str1 = zzdf.zzg((zzag.zza)paramMap.get(zzblr));
    String str3 = zzdf.zzg((zzag.zza)paramMap.get(zzblp));
    paramMap = zzdf.zzg((zzag.zza)paramMap.get(zzblq));
    try
    {
      l1 = Long.parseLong(str3);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      try
      {
        for (;;)
        {
          l2 = Long.parseLong(paramMap);
          if ((l1 > 0L) && (!TextUtils.isEmpty(str2)))
          {
            if (str1 != null)
            {
              paramMap = str1;
              if (!str1.isEmpty()) {}
            }
            else
            {
              paramMap = "0";
            }
            if (!this.zzblv.contains(paramMap))
            {
              if (!"0".equals(paramMap)) {
                this.zzblv.add(paramMap);
              }
              this.mHandler.postDelayed(new zza(str2, paramMap, l1, l2), l1);
            }
          }
          return zzdf.zzHF();
          localNumberFormatException = localNumberFormatException;
          long l1 = 0L;
        }
      }
      catch (NumberFormatException paramMap)
      {
        for (;;)
        {
          long l2 = 0L;
        }
      }
    }
  }
  
  private final class zza
    implements Runnable
  {
    private final long zzCv;
    private final long zzaNY;
    private final String zzblw;
    private final String zzblx;
    private final long zzbly;
    private long zzblz;
    
    zza(String paramString1, String paramString2, long paramLong1, long paramLong2)
    {
      this.zzblw = paramString1;
      this.zzblx = paramString2;
      this.zzaNY = paramLong1;
      this.zzbly = paramLong2;
      this.zzCv = System.currentTimeMillis();
    }
    
    public void run()
    {
      if ((this.zzbly > 0L) && (this.zzblz >= this.zzbly))
      {
        if (!"0".equals(this.zzblx)) {
          zzdb.zza(zzdb.this).remove(this.zzblx);
        }
        return;
      }
      this.zzblz += 1L;
      if (zzcH())
      {
        long l = System.currentTimeMillis();
        zzdb.zzb(zzdb.this).push(DataLayer.mapOf(new Object[] { "event", this.zzblw, "gtm.timerInterval", String.valueOf(this.zzaNY), "gtm.timerLimit", String.valueOf(this.zzbly), "gtm.timerStartTime", String.valueOf(this.zzCv), "gtm.timerCurrentTime", String.valueOf(l), "gtm.timerElapsedTime", String.valueOf(l - this.zzCv), "gtm.timerEventNumber", String.valueOf(this.zzblz), "gtm.triggers", this.zzblx }));
      }
      zzdb.zzc(zzdb.this).postDelayed(this, this.zzaNY);
    }
    
    protected boolean zzcH()
    {
      if (zzdb.zzd(zzdb.this)) {
        return zzdb.zze(zzdb.this);
      }
      Object localObject = (ActivityManager)zzdb.zzf(zzdb.this).getSystemService("activity");
      KeyguardManager localKeyguardManager = (KeyguardManager)zzdb.zzf(zzdb.this).getSystemService("keyguard");
      PowerManager localPowerManager = (PowerManager)zzdb.zzf(zzdb.this).getSystemService("power");
      localObject = ((ActivityManager)localObject).getRunningAppProcesses().iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if ((Process.myPid() == localRunningAppProcessInfo.pid) && (localRunningAppProcessInfo.importance == 100) && (!localKeyguardManager.inKeyguardRestrictedInputMode()) && (localPowerManager.isScreenOn())) {
          return true;
        }
      }
      return false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzdb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */