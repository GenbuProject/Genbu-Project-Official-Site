package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class Session
  implements Map
{
  private final Map map = new HashMap();
  private final boolean strict;
  
  public Session()
  {
    this(true);
  }
  
  public Session(boolean paramBoolean)
  {
    this.strict = paramBoolean;
  }
  
  public void clear()
  {
    this.map.clear();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return this.map.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject)
  {
    return this.map.containsValue(paramObject);
  }
  
  public Set entrySet()
  {
    return this.map.entrySet();
  }
  
  public Object get(Object paramObject)
  {
    return this.map.get(paramObject);
  }
  
  public Map getMap()
  {
    return this.map;
  }
  
  public boolean isEmpty()
  {
    return this.map.isEmpty();
  }
  
  public boolean isStrict()
  {
    return this.strict;
  }
  
  public Set keySet()
  {
    return this.map.keySet();
  }
  
  public Object put(Object paramObject1, Object paramObject2)
  {
    return this.map.put(paramObject1, paramObject2);
  }
  
  public void putAll(Map paramMap)
  {
    this.map.putAll(paramMap);
  }
  
  public Object remove(Object paramObject)
  {
    return this.map.remove(paramObject);
  }
  
  public int size()
  {
    return this.map.size();
  }
  
  public Collection values()
  {
    return this.map.values();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Session.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */