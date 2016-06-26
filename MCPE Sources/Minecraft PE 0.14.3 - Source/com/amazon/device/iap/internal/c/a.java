package com.amazon.device.iap.internal.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import com.amazon.device.iap.model.Receipt;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  private static final String a = a.class.getSimpleName();
  private static final String b = a.class.getName() + "_PREFS";
  private static final String c = a.class.getName() + "_CLEANER_PREFS";
  private static int d = 604800000;
  private static final a e = new a();
  
  public static a a()
  {
    return e;
  }
  
  private void a(long paramLong)
  {
    Object localObject = com.amazon.device.iap.internal.d.d().b();
    com.amazon.device.iap.internal.util.d.a(localObject, "context");
    localObject = ((Context)localObject).getSharedPreferences(c, 0).edit();
    ((SharedPreferences.Editor)localObject).putLong("LAST_CLEANING_TIME", paramLong);
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  private void e()
  {
    com.amazon.device.iap.internal.util.e.a(a, "enter old receipts cleanup! ");
    final Object localObject = com.amazon.device.iap.internal.d.d().b();
    com.amazon.device.iap.internal.util.d.a(localObject, "context");
    a(System.currentTimeMillis());
    localObject = new Runnable()
    {
      public void run()
      {
        try
        {
          com.amazon.device.iap.internal.util.e.a(a.b(), "perform house keeping! ");
          SharedPreferences localSharedPreferences = localObject.getSharedPreferences(a.c(), 0);
          Iterator localIterator = localSharedPreferences.getAll().keySet().iterator();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            try
            {
              d locald = d.a(localSharedPreferences.getString(str, null));
              if (System.currentTimeMillis() - locald.c() > a.d())
              {
                com.amazon.device.iap.internal.util.e.a(a.b(), "house keeping - try remove Receipt:" + str + " since it's too old");
                a.this.a(str);
              }
            }
            catch (e locale)
            {
              com.amazon.device.iap.internal.util.e.a(a.b(), "house keeping - try remove Receipt:" + str + " since it's invalid ");
              a.this.a(str);
            }
          }
          return;
        }
        catch (Throwable localThrowable)
        {
          com.amazon.device.iap.internal.util.e.a(a.b(), "Error in running cleaning job:" + localThrowable);
        }
      }
    };
    new Handler().post((Runnable)localObject);
  }
  
  private long f()
  {
    Context localContext = com.amazon.device.iap.internal.d.d().b();
    com.amazon.device.iap.internal.util.d.a(localContext, "context");
    long l1 = System.currentTimeMillis();
    long l2 = localContext.getSharedPreferences(c, 0).getLong("LAST_CLEANING_TIME", 0L);
    if (l2 == 0L)
    {
      a(l1);
      return l1;
    }
    return l2;
  }
  
  public void a(String paramString)
  {
    com.amazon.device.iap.internal.util.e.a(a, "enter removeReceipt for receipt[" + paramString + "]");
    Object localObject = com.amazon.device.iap.internal.d.d().b();
    com.amazon.device.iap.internal.util.d.a(localObject, "context");
    localObject = ((Context)localObject).getSharedPreferences(b, 0).edit();
    ((SharedPreferences.Editor)localObject).remove(paramString);
    ((SharedPreferences.Editor)localObject).commit();
    com.amazon.device.iap.internal.util.e.a(a, "leave removeReceipt for receipt[" + paramString + "]");
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    com.amazon.device.iap.internal.util.e.a(a, "enter saveReceipt for receipt [" + paramString4 + "]");
    try
    {
      com.amazon.device.iap.internal.util.d.a(paramString2, "userId");
      com.amazon.device.iap.internal.util.d.a(paramString3, "receiptId");
      com.amazon.device.iap.internal.util.d.a(paramString4, "receiptString");
      Object localObject = com.amazon.device.iap.internal.d.d().b();
      com.amazon.device.iap.internal.util.d.a(localObject, "context");
      paramString2 = new d(paramString2, paramString4, paramString1, System.currentTimeMillis());
      localObject = ((Context)localObject).getSharedPreferences(b, 0).edit();
      ((SharedPreferences.Editor)localObject).putString(paramString3, paramString2.d());
      ((SharedPreferences.Editor)localObject).commit();
      com.amazon.device.iap.internal.util.e.a(a, "leaving saveReceipt for receipt id [" + paramString3 + "]");
      return;
    }
    catch (Throwable paramString2)
    {
      for (;;)
      {
        com.amazon.device.iap.internal.util.e.a(a, "error in saving pending receipt:" + paramString1 + "/" + paramString4 + ":" + paramString2.getMessage());
      }
    }
  }
  
  public Set<Receipt> b(String paramString)
  {
    Object localObject = com.amazon.device.iap.internal.d.d().b();
    com.amazon.device.iap.internal.util.d.a(localObject, "context");
    com.amazon.device.iap.internal.util.e.a(a, "enter getLocalReceipts for user[" + paramString + "]");
    HashSet localHashSet = new HashSet();
    if (com.amazon.device.iap.internal.util.d.a(paramString))
    {
      com.amazon.device.iap.internal.util.e.b(a, "empty UserId: " + paramString);
      throw new RuntimeException("Invalid UserId:" + paramString);
    }
    localObject = ((Context)localObject).getSharedPreferences(b, 0).getAll();
    Iterator localIterator = ((Map)localObject).keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      String str1 = (String)((Map)localObject).get(str2);
      try
      {
        d locald = d.a(str1);
        localHashSet.add(com.amazon.device.iap.internal.util.a.a(new JSONObject(locald.b()), paramString, locald.a()));
      }
      catch (com.amazon.device.iap.internal.b.d locald1)
      {
        a(str2);
        com.amazon.device.iap.internal.util.e.b(a, "failed to verify signature:[" + str1 + "]");
      }
      catch (JSONException localJSONException)
      {
        a(str2);
        com.amazon.device.iap.internal.util.e.b(a, "failed to convert string to JSON object:[" + str1 + "]");
      }
      catch (Throwable localThrowable)
      {
        com.amazon.device.iap.internal.util.e.b(a, "failed to load the receipt from SharedPreference:[" + str1 + "]");
      }
    }
    com.amazon.device.iap.internal.util.e.a(a, "leaving getLocalReceipts for user[" + paramString + "], " + localHashSet.size() + " local receipts found.");
    if (System.currentTimeMillis() - f() > d) {
      e();
    }
    return localHashSet;
  }
  
  public String c(String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = com.amazon.device.iap.internal.d.d().b();
    com.amazon.device.iap.internal.util.d.a(localObject2, "context");
    if (com.amazon.device.iap.internal.util.d.a(paramString))
    {
      com.amazon.device.iap.internal.util.e.b(a, "empty receiptId: " + paramString);
      throw new RuntimeException("Invalid ReceiptId:" + paramString);
    }
    localObject2 = ((Context)localObject2).getSharedPreferences(b, 0).getString(paramString, null);
    paramString = (String)localObject1;
    if (localObject2 != null) {}
    try
    {
      paramString = d.a((String)localObject2).a();
      return paramString;
    }
    catch (e paramString) {}
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\c\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */