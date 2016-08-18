package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

class Collector
  implements Criteria
{
  private final Registry alias = new Registry(null);
  private final Registry registry = new Registry(null);
  
  public void commit(Object paramObject)
    throws Exception
  {
    Iterator localIterator = this.registry.values().iterator();
    while (localIterator.hasNext())
    {
      Variable localVariable = (Variable)localIterator.next();
      localVariable.getContact().set(paramObject, localVariable.getValue());
    }
  }
  
  public Variable get(Object paramObject)
  {
    return (Variable)this.registry.get(paramObject);
  }
  
  public Variable get(Label paramLabel)
    throws Exception
  {
    if (paramLabel != null)
    {
      paramLabel = paramLabel.getKey();
      return (Variable)this.registry.get(paramLabel);
    }
    return null;
  }
  
  public Iterator<Object> iterator()
  {
    return this.registry.iterator();
  }
  
  public Variable remove(Object paramObject)
    throws Exception
  {
    return (Variable)this.registry.remove(paramObject);
  }
  
  public Variable resolve(String paramString)
  {
    return (Variable)this.alias.get(paramString);
  }
  
  public void set(Label paramLabel, Object paramObject)
    throws Exception
  {
    paramObject = new Variable(paramLabel, paramObject);
    if (paramLabel != null)
    {
      String[] arrayOfString = paramLabel.getPaths();
      paramLabel = paramLabel.getKey();
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        this.alias.put(str, paramObject);
        i += 1;
      }
      this.registry.put(paramLabel, paramObject);
    }
  }
  
  private static class Registry
    extends LinkedHashMap<Object, Variable>
  {
    public Iterator<Object> iterator()
    {
      return keySet().iterator();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Collector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */