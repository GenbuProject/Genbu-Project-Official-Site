package com.amazon.android.licensing;

import com.amazon.android.m.d;
import java.util.Date;

public final class f
{
  final String a;
  final String b;
  final String c;
  final Date d;
  final String e;
  
  public f(d paramd)
    throws com.amazon.android.h.b
  {
    this.a = a("checksum", paramd);
    this.b = a("customerId", paramd);
    this.c = a("deviceId", paramd);
    this.e = a("packageName", paramd);
    this.d = b("expiration", paramd);
  }
  
  private static String a(String paramString, d paramd)
    throws com.amazon.android.h.b
  {
    paramd = paramd.a(paramString);
    if (com.amazon.android.framework.util.b.a(paramd)) {
      throw new com.amazon.android.h.b("MISSING_FIELD", paramString);
    }
    return paramd;
  }
  
  private static Date b(String paramString, d paramd)
    throws com.amazon.android.h.b
  {
    paramd = a(paramString, paramd);
    try
    {
      Date localDate = new Date(Long.parseLong(paramd));
      return localDate;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new com.amazon.android.h.b("INVALID_FIELD_VALUE", paramString + ":" + paramd);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */