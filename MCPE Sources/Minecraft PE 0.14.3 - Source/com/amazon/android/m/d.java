package com.amazon.android.m;

import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.h.b;
import com.amazon.android.h.c;
import com.amazon.mas.kiwi.util.Base64;
import java.io.IOException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public final class d
{
  private static final KiwiLogger a = new KiwiLogger("SignedToken");
  private final Map b = new HashMap();
  
  public d(String paramString, PublicKey paramPublicKey)
    throws b, c
  {
    String str = b(paramString);
    int i = str.lastIndexOf("|");
    if (i == -1) {
      throw b.a();
    }
    paramString = str.substring(0, i);
    str = str.substring(i + 1);
    if (KiwiLogger.TRACE_ON)
    {
      a.trace("Token data: " + paramString);
      a.trace("Signature: " + str);
    }
    if (!a.a(paramString, str, paramPublicKey)) {
      throw new c();
    }
    c(paramString);
  }
  
  private static String b(String paramString)
    throws b
  {
    try
    {
      paramString = new String(Base64.decode(paramString.getBytes()));
      return paramString;
    }
    catch (IOException paramString)
    {
      throw new b("DECODE", paramString.getMessage());
    }
  }
  
  private void c(String paramString)
    throws b
  {
    paramString = new StringTokenizer(paramString, ",");
    while (paramString.hasMoreElements())
    {
      String str2 = paramString.nextToken();
      a.trace("Field: " + str2);
      int i = str2.indexOf("=");
      if (i == -1) {
        throw b.a();
      }
      String str1 = str2.substring(0, i);
      str2 = str2.substring(i + 1);
      a.trace("FieldName: " + str1);
      a.trace("FieldValue: " + str2);
      this.b.put(str1, str2);
    }
  }
  
  public final String a(String paramString)
  {
    com.amazon.android.d.a.a(paramString, "key");
    return (String)this.b.get(paramString);
  }
  
  public final String toString()
  {
    return "Signed Token: " + this.b;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\m\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */