package com.google.android.gms.analytics.internal;

import java.util.Map;

public class zzak
  extends zzq<zzal>
{
  public zzak(zzf paramzzf)
  {
    super(paramzzf, new zza(paramzzf));
  }
  
  private static class zza
    extends zzc
    implements zzq.zza<zzal>
  {
    private final zzal zzTn = new zzal();
    
    public zza(zzf paramzzf)
    {
      super();
    }
    
    public void zzc(String paramString, int paramInt)
    {
      if ("ga_sessionTimeout".equals(paramString))
      {
        this.zzTn.zzTp = paramInt;
        return;
      }
      zzd("int configuration name not recognized", paramString);
    }
    
    public void zzf(String paramString, boolean paramBoolean)
    {
      int j = 1;
      int k = 1;
      int i = 1;
      if ("ga_autoActivityTracking".equals(paramString))
      {
        paramString = this.zzTn;
        if (paramBoolean) {}
        for (;;)
        {
          paramString.zzTq = i;
          return;
          i = 0;
        }
      }
      if ("ga_anonymizeIp".equals(paramString))
      {
        paramString = this.zzTn;
        if (paramBoolean) {}
        for (i = j;; i = 0)
        {
          paramString.zzTr = i;
          return;
        }
      }
      if ("ga_reportUncaughtExceptions".equals(paramString))
      {
        paramString = this.zzTn;
        if (paramBoolean) {}
        for (i = k;; i = 0)
        {
          paramString.zzTs = i;
          return;
        }
      }
      zzd("bool configuration name not recognized", paramString);
    }
    
    public void zzj(String paramString1, String paramString2)
    {
      this.zzTn.zzTt.put(paramString1, paramString2);
    }
    
    public void zzk(String paramString1, String paramString2)
    {
      if ("ga_trackingId".equals(paramString1))
      {
        this.zzTn.zzOV = paramString2;
        return;
      }
      if ("ga_sampleFrequency".equals(paramString1)) {
        try
        {
          this.zzTn.zzTo = Double.parseDouble(paramString2);
          return;
        }
        catch (NumberFormatException paramString1)
        {
          zzc("Error parsing ga_sampleFrequency value", paramString2, paramString1);
          return;
        }
      }
      zzd("string configuration name not recognized", paramString1);
    }
    
    public zzal zzlS()
    {
      return this.zzTn;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\analytics\internal\zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */