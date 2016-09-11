package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class zzan
  extends zzd
{
  protected boolean zzPi;
  protected int zzRB;
  protected String zzSE;
  protected String zzSF;
  protected int zzSH;
  protected boolean zzTv;
  protected boolean zzTw;
  protected boolean zzTx;
  
  public zzan(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  private static int zzby(String paramString)
  {
    paramString = paramString.toLowerCase();
    if ("verbose".equals(paramString)) {
      return 0;
    }
    if ("info".equals(paramString)) {
      return 1;
    }
    if ("warning".equals(paramString)) {
      return 2;
    }
    if ("error".equals(paramString)) {
      return 3;
    }
    return -1;
  }
  
  public int getLogLevel()
  {
    zzjv();
    return this.zzRB;
  }
  
  void zza(zzaa paramzzaa)
  {
    zzbd("Loading global XML config values");
    String str;
    if (paramzzaa.zzlf())
    {
      str = paramzzaa.zzlg();
      this.zzSE = str;
      zzb("XML config - app name", str);
    }
    if (paramzzaa.zzlh())
    {
      str = paramzzaa.zzli();
      this.zzSF = str;
      zzb("XML config - app version", str);
    }
    int i;
    if (paramzzaa.zzlj())
    {
      i = zzby(paramzzaa.zzlk());
      if (i >= 0)
      {
        this.zzRB = i;
        zza("XML config - log level", Integer.valueOf(i));
      }
    }
    if (paramzzaa.zzll())
    {
      i = paramzzaa.zzlm();
      this.zzSH = i;
      this.zzTw = true;
      zzb("XML config - dispatch period (sec)", Integer.valueOf(i));
    }
    if (paramzzaa.zzln())
    {
      boolean bool = paramzzaa.zzlo();
      this.zzPi = bool;
      this.zzTx = true;
      zzb("XML config - dry run", Boolean.valueOf(bool));
    }
  }
  
  protected void zziJ()
  {
    zzmd();
  }
  
  public String zzlg()
  {
    zzjv();
    return this.zzSE;
  }
  
  public String zzli()
  {
    zzjv();
    return this.zzSF;
  }
  
  public boolean zzlj()
  {
    zzjv();
    return this.zzTv;
  }
  
  public boolean zzll()
  {
    zzjv();
    return this.zzTw;
  }
  
  public boolean zzln()
  {
    zzjv();
    return this.zzTx;
  }
  
  public boolean zzlo()
  {
    zzjv();
    return this.zzPi;
  }
  
  public int zzmc()
  {
    zzjv();
    return this.zzSH;
  }
  
  protected void zzmd()
  {
    Object localObject1 = getContext();
    try
    {
      localObject1 = ((Context)localObject1).getPackageManager().getApplicationInfo(((Context)localObject1).getPackageName(), 129);
      if (localObject1 == null)
      {
        zzbg("Couldn't get ApplicationInfo to load global config");
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject2;
      do
      {
        int i;
        do
        {
          do
          {
            for (;;)
            {
              zzd("PackageManager doesn't know about the app package", localNameNotFoundException);
              localObject2 = null;
            }
            localObject2 = ((ApplicationInfo)localObject2).metaData;
          } while (localObject2 == null);
          i = ((Bundle)localObject2).getInt("com.google.android.gms.analytics.globalConfigResource");
        } while (i <= 0);
        localObject2 = (zzaa)new zzz(zzji()).zzah(i);
      } while (localObject2 == null);
      zza((zzaa)localObject2);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */