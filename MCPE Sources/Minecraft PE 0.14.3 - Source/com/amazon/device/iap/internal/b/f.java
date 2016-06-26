package com.amazon.device.iap.internal.b;

import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.device.iap.internal.a;

public class f
  implements a
{
  private static KiwiLogger a = new KiwiLogger("In App Purchasing SDK - Production Mode");
  
  private static String c(String paramString1, String paramString2)
  {
    return paramString1 + ": " + paramString2;
  }
  
  public void a(String paramString1, String paramString2)
  {
    a.trace(c(paramString1, paramString2));
  }
  
  public boolean a()
  {
    return KiwiLogger.TRACE_ON;
  }
  
  public void b(String paramString1, String paramString2)
  {
    a.error(c(paramString1, paramString2));
  }
  
  public boolean b()
  {
    return KiwiLogger.ERROR_ON;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */