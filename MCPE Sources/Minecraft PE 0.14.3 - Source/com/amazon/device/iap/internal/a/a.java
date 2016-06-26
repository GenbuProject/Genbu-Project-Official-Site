package com.amazon.device.iap.internal.a;

import android.util.Log;

public class a
  implements com.amazon.device.iap.internal.a
{
  private static String a(String paramString)
  {
    return "In App Purchasing SDK - Sandbox Mode: " + paramString;
  }
  
  public void a(String paramString1, String paramString2)
  {
    Log.d(paramString1, a(paramString2));
  }
  
  public boolean a()
  {
    return true;
  }
  
  public void b(String paramString1, String paramString2)
  {
    Log.e(paramString1, a(paramString2));
  }
  
  public boolean b()
  {
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */