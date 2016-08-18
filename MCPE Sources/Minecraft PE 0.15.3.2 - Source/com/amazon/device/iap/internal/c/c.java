package com.amazon.device.iap.internal.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.amazon.device.iap.internal.util.e;

public class c
{
  private static c a = new c();
  private static final String b = c.class.getSimpleName();
  private static final String c = c.class.getName() + "_PREFS_";
  
  public static c a()
  {
    return a;
  }
  
  public String a(String paramString1, String paramString2)
  {
    Object localObject1 = null;
    e.a(b, "enter getReceiptIdFromSku for sku [" + paramString2 + "], user [" + paramString1 + "]");
    try
    {
      com.amazon.device.iap.internal.util.d.a(paramString1, "userId");
      com.amazon.device.iap.internal.util.d.a(paramString2, "sku");
      Object localObject2 = com.amazon.device.iap.internal.d.d().b();
      com.amazon.device.iap.internal.util.d.a(localObject2, "context");
      localObject2 = ((Context)localObject2).getSharedPreferences(c + paramString1, 0).getString(paramString2, null);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        e.a(b, "error in saving v1 Entitlement:" + paramString2 + ":" + localThrowable.getMessage());
      }
    }
    e.a(b, "leaving saveEntitlementRecord for sku [" + paramString2 + "], user [" + paramString1 + "]");
    return (String)localObject1;
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    e.a(b, "enter saveEntitlementRecord for v1 Entitlement [" + paramString2 + "/" + paramString3 + "], user [" + paramString1 + "]");
    try
    {
      com.amazon.device.iap.internal.util.d.a(paramString1, "userId");
      com.amazon.device.iap.internal.util.d.a(paramString2, "receiptId");
      com.amazon.device.iap.internal.util.d.a(paramString3, "sku");
      Object localObject = com.amazon.device.iap.internal.d.d().b();
      com.amazon.device.iap.internal.util.d.a(localObject, "context");
      localObject = ((Context)localObject).getSharedPreferences(c + paramString1, 0).edit();
      ((SharedPreferences.Editor)localObject).putString(paramString3, paramString2);
      ((SharedPreferences.Editor)localObject).commit();
      e.a(b, "leaving saveEntitlementRecord for v1 Entitlement [" + paramString2 + "/" + paramString3 + "], user [" + paramString1 + "]");
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        e.a(b, "error in saving v1 Entitlement:" + paramString2 + "/" + paramString3 + ":" + localThrowable.getMessage());
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\c\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */