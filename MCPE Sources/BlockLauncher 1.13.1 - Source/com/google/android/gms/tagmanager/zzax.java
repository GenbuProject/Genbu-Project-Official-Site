package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzax
{
  private static String zzbjg;
  static Map<String, String> zzbjh = new HashMap();
  
  public static String zzU(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      if (paramString1.length() > 0) {
        return paramString1;
      }
      return null;
    }
    return Uri.parse("http://hostname/?" + paramString1).getQueryParameter(paramString2);
  }
  
  public static String zzf(Context paramContext, String paramString1, String paramString2)
  {
    String str = (String)zzbjh.get(paramString1);
    Object localObject = str;
    if (str == null)
    {
      paramContext = paramContext.getSharedPreferences("gtm_click_referrers", 0);
      if (paramContext == null) {
        break label63;
      }
    }
    label63:
    for (paramContext = paramContext.getString(paramString1, "");; paramContext = "")
    {
      zzbjh.put(paramString1, paramContext);
      localObject = paramContext;
      return zzU((String)localObject, paramString2);
    }
  }
  
  public static void zzgh(String paramString)
  {
    try
    {
      zzbjg = paramString;
      return;
    }
    finally {}
  }
  
  /* Error */
  public static String zzm(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: getstatic 84	com/google/android/gms/tagmanager/zzax:zzbjg	Ljava/lang/String;
    //   3: ifnonnull +40 -> 43
    //   6: ldc 2
    //   8: monitorenter
    //   9: getstatic 84	com/google/android/gms/tagmanager/zzax:zzbjg	Ljava/lang/String;
    //   12: ifnonnull +28 -> 40
    //   15: aload_0
    //   16: ldc 88
    //   18: iconst_0
    //   19: invokevirtual 67	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   22: astore_0
    //   23: aload_0
    //   24: ifnull +27 -> 51
    //   27: aload_0
    //   28: ldc 90
    //   30: ldc 69
    //   32: invokeinterface 74 3 0
    //   37: putstatic 84	com/google/android/gms/tagmanager/zzax:zzbjg	Ljava/lang/String;
    //   40: ldc 2
    //   42: monitorexit
    //   43: getstatic 84	com/google/android/gms/tagmanager/zzax:zzbjg	Ljava/lang/String;
    //   46: aload_1
    //   47: invokestatic 80	com/google/android/gms/tagmanager/zzax:zzU	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   50: areturn
    //   51: ldc 69
    //   53: putstatic 84	com/google/android/gms/tagmanager/zzax:zzbjg	Ljava/lang/String;
    //   56: goto -16 -> 40
    //   59: astore_0
    //   60: ldc 2
    //   62: monitorexit
    //   63: aload_0
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	paramContext	Context
    //   0	65	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   9	23	59	finally
    //   27	40	59	finally
    //   40	43	59	finally
    //   51	56	59	finally
    //   60	63	59	finally
  }
  
  public static void zzn(Context paramContext, String paramString)
  {
    String str = zzU(paramString, "conv");
    if ((str != null) && (str.length() > 0))
    {
      zzbjh.put(str, paramString);
      zzcv.zzb(paramContext, "gtm_click_referrers", str, paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */