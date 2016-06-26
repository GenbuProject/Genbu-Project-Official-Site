package com.google.android.gms.appdatasearch;

import java.util.HashMap;
import java.util.Map;

public class zzh
{
  private static final String[] zzUb;
  private static final Map<String, Integer> zzUc;
  
  static
  {
    int i = 0;
    zzUb = new String[] { "text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity" };
    zzUc = new HashMap(zzUb.length);
    while (i < zzUb.length)
    {
      zzUc.put(zzUb[i], Integer.valueOf(i));
      i += 1;
    }
  }
  
  public static String zzao(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= zzUb.length)) {
      return null;
    }
    return zzUb[paramInt];
  }
  
  public static int zzbA(String paramString)
  {
    Integer localInteger = (Integer)zzUc.get(paramString);
    if (localInteger == null) {
      throw new IllegalArgumentException("[" + paramString + "] is not a valid global search section name");
    }
    return localInteger.intValue();
  }
  
  public static int zzmg()
  {
    return zzUb.length;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\appdatasearch\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */