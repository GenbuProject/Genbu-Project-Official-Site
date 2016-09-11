package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

public class zzdc
{
  private Context mContext;
  private Tracker zzPb;
  private GoogleAnalytics zzPd;
  
  public zzdc(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private void zzgr(String paramString)
  {
    try
    {
      if (this.zzPd == null)
      {
        this.zzPd = GoogleAnalytics.getInstance(this.mContext);
        this.zzPd.setLogger(new zza());
        this.zzPb = this.zzPd.newTracker(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public Tracker zzgq(String paramString)
  {
    zzgr(paramString);
    return this.zzPb;
  }
  
  static class zza
    implements Logger
  {
    private static int zzkn(int paramInt)
    {
      switch (paramInt)
      {
      case 6: 
      default: 
        return 3;
      case 5: 
        return 2;
      case 3: 
      case 4: 
        return 1;
      }
      return 0;
    }
    
    public void error(Exception paramException)
    {
      zzbg.zzb("", paramException);
    }
    
    public void error(String paramString)
    {
      zzbg.e(paramString);
    }
    
    public int getLogLevel()
    {
      return zzkn(zzbg.getLogLevel());
    }
    
    public void info(String paramString)
    {
      zzbg.zzaJ(paramString);
    }
    
    public void setLogLevel(int paramInt)
    {
      zzbg.zzaK("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }
    
    public void verbose(String paramString)
    {
      zzbg.v(paramString);
    }
    
    public void warn(String paramString)
    {
      zzbg.zzaK(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzdc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */