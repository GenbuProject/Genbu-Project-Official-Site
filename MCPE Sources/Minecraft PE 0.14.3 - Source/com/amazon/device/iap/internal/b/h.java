package com.amazon.device.iap.internal.b;

import java.util.HashMap;
import java.util.Map;

public class h
{
  public final Map<String, Object> a = new HashMap();
  
  static
  {
    if (!h.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      b = bool;
      return;
    }
  }
  
  public Object a()
  {
    return this.a.get("RESPONSE");
  }
  
  public Object a(String paramString)
  {
    return this.a.get(paramString);
  }
  
  public void a(Object paramObject)
  {
    if ((!b) && (paramObject == null)) {
      throw new AssertionError();
    }
    this.a.put("RESPONSE", paramObject);
  }
  
  public void a(String paramString, Object paramObject)
  {
    this.a.put(paramString, paramObject);
  }
  
  public void b()
  {
    this.a.remove("RESPONSE");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */