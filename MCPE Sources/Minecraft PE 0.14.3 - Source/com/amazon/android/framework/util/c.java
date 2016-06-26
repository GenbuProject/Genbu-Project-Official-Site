package com.amazon.android.framework.util;

import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public final class c
  implements Iterable
{
  public WeakHashMap a = new WeakHashMap();
  
  public final void a(Object paramObject)
  {
    this.a.put(paramObject, null);
  }
  
  public final void b(Object paramObject)
  {
    this.a.remove(paramObject);
  }
  
  public final Iterator iterator()
  {
    return this.a.keySet().iterator();
  }
  
  public final String toString()
  {
    return this.a.keySet().toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\util\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */