package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.zzr;
import java.util.LinkedHashMap;
import java.util.Map;

@zzhb
public class zzbu
{
  private Context mContext = null;
  private String zzsy = null;
  private boolean zzxi;
  private String zzxj;
  private Map<String, String> zzxk;
  
  public zzbu(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.zzsy = paramString;
    this.zzxi = ((Boolean)zzbt.zzwg.get()).booleanValue();
    this.zzxj = ((String)zzbt.zzwh.get());
    this.zzxk = new LinkedHashMap();
    this.zzxk.put("s", "gmob_sdk");
    this.zzxk.put("v", "3");
    this.zzxk.put("os", Build.VERSION.RELEASE);
    this.zzxk.put("sdk", Build.VERSION.SDK);
    this.zzxk.put("device", zzr.zzbC().zzht());
    paramString = this.zzxk;
    if (paramContext.getApplicationContext() != null) {}
    for (paramContext = paramContext.getApplicationContext().getPackageName();; paramContext = paramContext.getPackageName())
    {
      paramString.put("app", paramContext);
      paramContext = zzr.zzbI().zzE(this.mContext);
      this.zzxk.put("network_coarse", Integer.toString(paramContext.zzKc));
      this.zzxk.put("network_fine", Integer.toString(paramContext.zzKd));
      return;
    }
  }
  
  Context getContext()
  {
    return this.mContext;
  }
  
  String zzcs()
  {
    return this.zzsy;
  }
  
  boolean zzdu()
  {
    return this.zzxi;
  }
  
  String zzdv()
  {
    return this.zzxj;
  }
  
  Map<String, String> zzdw()
  {
    return this.zzxk;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzbu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */