package com.amazon.android.licensing;

import com.amazon.android.b.h;
import java.util.Map;

public final class c
{
  final String a;
  final String b;
  final String c;
  
  public c(Map paramMap)
    throws h
  {
    if (paramMap == null) {
      throw new h("EMPTY", null);
    }
    this.a = a("license", paramMap);
    this.b = a("customerId", paramMap);
    this.c = a("deviceId", paramMap);
  }
  
  private static String a(String paramString, Map paramMap)
    throws h
  {
    paramMap = (String)paramMap.get(paramString);
    if ((paramMap == null) || (paramMap.length() == 0)) {}
    for (int i = 1; i != 0; i = 0) {
      throw new h("MISSING_FIELD", paramString);
    }
    return paramMap;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */