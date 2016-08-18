package org.simpleframework.xml.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LimitedCache<T>
  extends LinkedHashMap<Object, T>
  implements Cache<T>
{
  private final int capacity;
  
  public LimitedCache()
  {
    this(50000);
  }
  
  public LimitedCache(int paramInt)
  {
    this.capacity = paramInt;
  }
  
  public void cache(Object paramObject, T paramT)
  {
    put(paramObject, paramT);
  }
  
  public boolean contains(Object paramObject)
  {
    return containsKey(paramObject);
  }
  
  public T fetch(Object paramObject)
  {
    return (T)get(paramObject);
  }
  
  protected boolean removeEldestEntry(Map.Entry<Object, T> paramEntry)
  {
    return size() > this.capacity;
  }
  
  public T take(Object paramObject)
  {
    return (T)remove(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\util\LimitedCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */