package org.simpleframework.xml.util;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache<T>
  extends ConcurrentHashMap<Object, T>
  implements Cache<T>
{
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
  
  public T take(Object paramObject)
  {
    return (T)remove(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\util\ConcurrentCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */