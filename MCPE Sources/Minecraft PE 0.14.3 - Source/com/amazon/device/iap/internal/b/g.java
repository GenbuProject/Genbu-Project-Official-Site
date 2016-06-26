package com.amazon.device.iap.internal.b;

import com.amazon.device.iap.internal.a;
import com.amazon.device.iap.internal.b;
import java.util.HashMap;
import java.util.Map;

public final class g
  implements b
{
  private static final Map<Class, Class> a = new HashMap();
  
  static
  {
    a.put(com.amazon.device.iap.internal.c.class, c.class);
    a.put(a.class, f.class);
  }
  
  public <T> Class<T> a(Class<T> paramClass)
  {
    return (Class)a.get(paramClass);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */