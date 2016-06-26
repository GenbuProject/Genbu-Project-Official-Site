package com.amazon.device.iap.internal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class b
{
  private static final String a = b.class.getName() + "_PREFS";
  
  public static String a(String paramString)
  {
    d.a(paramString, "userId");
    Context localContext = com.amazon.device.iap.internal.d.d().b();
    d.a(localContext, "context");
    return localContext.getSharedPreferences(a, 0).getString(paramString, null);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    d.a(paramString1, "userId");
    Object localObject = com.amazon.device.iap.internal.d.d().b();
    d.a(localObject, "context");
    localObject = ((Context)localObject).getSharedPreferences(a, 0).edit();
    ((SharedPreferences.Editor)localObject).putString(paramString1, paramString2);
    ((SharedPreferences.Editor)localObject).commit();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\util\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */