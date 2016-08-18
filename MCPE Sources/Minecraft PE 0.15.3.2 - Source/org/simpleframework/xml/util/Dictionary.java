package org.simpleframework.xml.util;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Dictionary<T extends Entry>
  extends AbstractSet<T>
{
  protected final Table<T> map = new Table();
  
  public boolean add(T paramT)
  {
    return this.map.put(paramT.getName(), paramT) != null;
  }
  
  public T get(String paramString)
  {
    return (Entry)this.map.get(paramString);
  }
  
  public Iterator<T> iterator()
  {
    return this.map.values().iterator();
  }
  
  public T remove(String paramString)
  {
    return (Entry)this.map.remove(paramString);
  }
  
  public int size()
  {
    return this.map.size();
  }
  
  private static class Table<T>
    extends HashMap<String, T>
  {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\util\Dictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */