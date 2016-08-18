package org.simpleframework.xml.strategy;

import java.lang.reflect.Array;
import java.util.Map;
import org.simpleframework.xml.stream.Node;
import org.simpleframework.xml.stream.NodeMap;

public class TreeStrategy
  implements Strategy
{
  private final String label;
  private final String length;
  private final Loader loader = new Loader();
  
  public TreeStrategy()
  {
    this("class", "length");
  }
  
  public TreeStrategy(String paramString1, String paramString2)
  {
    this.length = paramString2;
    this.label = paramString1;
  }
  
  private Value readArray(Class paramClass, NodeMap paramNodeMap)
    throws Exception
  {
    paramNodeMap = paramNodeMap.remove(this.length);
    int i = 0;
    if (paramNodeMap != null) {
      i = Integer.parseInt(paramNodeMap.getValue());
    }
    return new ArrayValue(paramClass, i);
  }
  
  private Class readValue(Type paramType, NodeMap paramNodeMap)
    throws Exception
  {
    Node localNode = paramNodeMap.remove(this.label);
    paramNodeMap = paramType.getType();
    paramType = paramNodeMap;
    if (paramNodeMap.isArray()) {
      paramType = paramNodeMap.getComponentType();
    }
    if (localNode != null)
    {
      paramType = localNode.getValue();
      paramType = this.loader.load(paramType);
    }
    return paramType;
  }
  
  private Class writeArray(Class paramClass, Object paramObject, NodeMap paramNodeMap)
  {
    int i = Array.getLength(paramObject);
    if (this.length != null) {
      paramNodeMap.put(this.length, String.valueOf(i));
    }
    return paramClass.getComponentType();
  }
  
  public Value read(Type paramType, NodeMap paramNodeMap, Map paramMap)
    throws Exception
  {
    paramMap = readValue(paramType, paramNodeMap);
    paramType = paramType.getType();
    if (paramType.isArray()) {
      return readArray(paramMap, paramNodeMap);
    }
    if (paramType != paramMap) {
      return new ObjectValue(paramMap);
    }
    return null;
  }
  
  public boolean write(Type paramType, Object paramObject, NodeMap paramNodeMap, Map paramMap)
  {
    paramMap = paramObject.getClass();
    Class localClass = paramType.getType();
    paramType = paramMap;
    if (paramMap.isArray()) {
      paramType = writeArray(localClass, paramObject, paramNodeMap);
    }
    if (paramMap != localClass) {
      paramNodeMap.put(this.label, paramType.getName());
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\TreeStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */