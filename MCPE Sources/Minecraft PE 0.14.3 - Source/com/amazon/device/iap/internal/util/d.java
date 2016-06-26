package com.amazon.device.iap.internal.util;

import java.util.Collection;

public class d
{
  public static void a(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException(paramString + " must not be null");
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    if (a(paramString1)) {
      throw new IllegalArgumentException(paramString2 + " must not be null or empty");
    }
  }
  
  public static void a(Collection<? extends Object> paramCollection, String paramString)
  {
    if (paramCollection.isEmpty()) {
      throw new IllegalArgumentException(paramString + " must not be empty");
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\util\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */