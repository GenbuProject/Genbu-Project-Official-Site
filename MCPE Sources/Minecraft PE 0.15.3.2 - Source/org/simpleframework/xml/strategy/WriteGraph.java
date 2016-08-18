package org.simpleframework.xml.strategy;

import java.lang.reflect.Array;
import java.util.IdentityHashMap;
import org.simpleframework.xml.stream.NodeMap;

class WriteGraph
  extends IdentityHashMap<Object, String>
{
  private final String label;
  private final String length;
  private final String mark;
  private final String refer;
  
  public WriteGraph(Contract paramContract)
  {
    this.refer = paramContract.getReference();
    this.mark = paramContract.getIdentity();
    this.length = paramContract.getLength();
    this.label = paramContract.getLabel();
  }
  
  private Class writeArray(Class paramClass, Object paramObject, NodeMap paramNodeMap)
  {
    int i = Array.getLength(paramObject);
    if (!containsKey(paramObject)) {
      paramNodeMap.put(this.length, String.valueOf(i));
    }
    return paramClass.getComponentType();
  }
  
  private boolean writeReference(Object paramObject, NodeMap paramNodeMap)
  {
    String str = (String)get(paramObject);
    int i = size();
    if (str != null)
    {
      paramNodeMap.put(this.refer, str);
      return true;
    }
    str = String.valueOf(i);
    paramNodeMap.put(this.mark, str);
    put(paramObject, str);
    return false;
  }
  
  public boolean write(Type paramType, Object paramObject, NodeMap paramNodeMap)
  {
    Class localClass1 = paramObject.getClass();
    Class localClass2 = paramType.getType();
    paramType = localClass1;
    if (localClass1.isArray()) {
      paramType = writeArray(localClass1, paramObject, paramNodeMap);
    }
    if (localClass1 != localClass2) {
      paramNodeMap.put(this.label, paramType.getName());
    }
    return writeReference(paramObject, paramNodeMap);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\WriteGraph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */