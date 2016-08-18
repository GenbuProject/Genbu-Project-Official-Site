package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

class ParameterMap
  extends LinkedHashMap<Object, Parameter>
  implements Iterable<Parameter>
{
  public Parameter get(int paramInt)
  {
    return (Parameter)getAll().get(paramInt);
  }
  
  public List<Parameter> getAll()
  {
    Collection localCollection = values();
    if (!localCollection.isEmpty()) {
      return new ArrayList(localCollection);
    }
    return Collections.emptyList();
  }
  
  public Iterator<Parameter> iterator()
  {
    return values().iterator();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ParameterMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */