package com.amazon.android.k;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class a
  implements Iterable
{
  public Map a = new HashMap();
  
  public final a a(Object paramObject1, Object paramObject2, c paramc)
  {
    boolean bool;
    if (paramObject1 == null) {
      if (paramObject2 == null) {
        bool = true;
      }
    }
    for (;;)
    {
      if (!bool)
      {
        paramObject1 = new b(paramc, "'" + paramObject1 + "' != '" + paramObject2 + "'");
        this.a.put(paramc, paramObject1);
      }
      return this;
      bool = false;
      continue;
      bool = paramObject1.equals(paramObject2);
    }
  }
  
  public final boolean a()
  {
    return !this.a.isEmpty();
  }
  
  public final Iterator iterator()
  {
    return this.a.values().iterator();
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Verifier:");
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext())
    {
      b localb = (b)localIterator.next();
      localStringBuilder.append("\n\t" + localb);
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\k\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */