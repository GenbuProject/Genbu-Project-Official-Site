package com.google.android.gms.analytics.internal;

public class zzz
  extends zzq<zzaa>
{
  public zzz(zzf paramzzf)
  {
    super(paramzzf, new zza(paramzzf));
  }
  
  private static class zza
    implements zzq.zza<zzaa>
  {
    private final zzf zzQj;
    private final zzaa zzSD;
    
    public zza(zzf paramzzf)
    {
      this.zzQj = paramzzf;
      this.zzSD = new zzaa();
    }
    
    public void zzc(String paramString, int paramInt)
    {
      if ("ga_dispatchPeriod".equals(paramString))
      {
        this.zzSD.zzSH = paramInt;
        return;
      }
      this.zzQj.zzjm().zzd("Int xml configuration name not recognized", paramString);
    }
    
    public void zzf(String paramString, boolean paramBoolean)
    {
      if ("ga_dryRun".equals(paramString))
      {
        paramString = this.zzSD;
        if (paramBoolean) {}
        for (int i = 1;; i = 0)
        {
          paramString.zzSI = i;
          return;
        }
      }
      this.zzQj.zzjm().zzd("Bool xml configuration name not recognized", paramString);
    }
    
    public void zzj(String paramString1, String paramString2) {}
    
    public void zzk(String paramString1, String paramString2)
    {
      if ("ga_appName".equals(paramString1))
      {
        this.zzSD.zzSE = paramString2;
        return;
      }
      if ("ga_appVersion".equals(paramString1))
      {
        this.zzSD.zzSF = paramString2;
        return;
      }
      if ("ga_logLevel".equals(paramString1))
      {
        this.zzSD.zzSG = paramString2;
        return;
      }
      this.zzQj.zzjm().zzd("String xml configuration name not recognized", paramString1);
    }
    
    public zzaa zzle()
    {
      return this.zzSD;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\analytics\internal\zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */