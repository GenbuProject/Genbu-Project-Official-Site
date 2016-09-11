package com.google.android.gms.analytics.internal;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

public class zzal
  implements zzp
{
  public String zzOV;
  public double zzTo = -1.0D;
  public int zzTp = -1;
  public int zzTq = -1;
  public int zzTr = -1;
  public int zzTs = -1;
  public Map<String, String> zzTt = new HashMap();
  
  public int getSessionTimeout()
  {
    return this.zzTp;
  }
  
  public String getTrackingId()
  {
    return this.zzOV;
  }
  
  public String zzbr(String paramString)
  {
    String str = (String)this.zzTt.get(paramString);
    if (str != null) {
      return str;
    }
    return paramString;
  }
  
  public boolean zzlT()
  {
    return this.zzOV != null;
  }
  
  public boolean zzlU()
  {
    return this.zzTo >= 0.0D;
  }
  
  public double zzlV()
  {
    return this.zzTo;
  }
  
  public boolean zzlW()
  {
    return this.zzTp >= 0;
  }
  
  public boolean zzlX()
  {
    return this.zzTq != -1;
  }
  
  public boolean zzlY()
  {
    return this.zzTq == 1;
  }
  
  public boolean zzlZ()
  {
    return this.zzTr != -1;
  }
  
  public boolean zzma()
  {
    return this.zzTr == 1;
  }
  
  public boolean zzmb()
  {
    return this.zzTs == 1;
  }
  
  public String zzo(Activity paramActivity)
  {
    return zzbr(paramActivity.getClass().getCanonicalName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */