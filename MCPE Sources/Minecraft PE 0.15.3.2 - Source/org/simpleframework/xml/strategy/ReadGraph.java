package org.simpleframework.xml.strategy;

import java.util.HashMap;
import org.simpleframework.xml.stream.Node;
import org.simpleframework.xml.stream.NodeMap;

class ReadGraph
  extends HashMap
{
  private final String label;
  private final String length;
  private final Loader loader;
  private final String mark;
  private final String refer;
  
  public ReadGraph(Contract paramContract, Loader paramLoader)
  {
    this.refer = paramContract.getReference();
    this.mark = paramContract.getIdentity();
    this.length = paramContract.getLength();
    this.label = paramContract.getLabel();
    this.loader = paramLoader;
  }
  
  private Value readArray(Type paramType, Class paramClass, NodeMap paramNodeMap)
    throws Exception
  {
    paramType = paramNodeMap.remove(this.length);
    int i = 0;
    if (paramType != null) {
      i = Integer.parseInt(paramType.getValue());
    }
    return new ArrayValue(paramClass, i);
  }
  
  private Value readInstance(Type paramType, Class paramClass, NodeMap paramNodeMap)
    throws Exception
  {
    Object localObject = paramNodeMap.remove(this.mark);
    if (localObject == null) {
      return readReference(paramType, paramClass, paramNodeMap);
    }
    localObject = ((Node)localObject).getValue();
    if (containsKey(localObject)) {
      throw new CycleException("Element '%s' already exists", new Object[] { localObject });
    }
    return readValue(paramType, paramClass, paramNodeMap, (String)localObject);
  }
  
  private Value readReference(Type paramType, Class paramClass, NodeMap paramNodeMap)
    throws Exception
  {
    Node localNode = paramNodeMap.remove(this.refer);
    if (localNode == null) {
      return readValue(paramType, paramClass, paramNodeMap);
    }
    paramType = localNode.getValue();
    paramNodeMap = get(paramType);
    if (!containsKey(paramType)) {
      throw new CycleException("Invalid reference '%s' found", new Object[] { paramType });
    }
    return new Reference(paramNodeMap, paramClass);
  }
  
  private Value readValue(Type paramType, Class paramClass, NodeMap paramNodeMap)
    throws Exception
  {
    if (paramType.getType().isArray()) {
      return readArray(paramType, paramClass, paramNodeMap);
    }
    return new ObjectValue(paramClass);
  }
  
  private Value readValue(Type paramType, Class paramClass, NodeMap paramNodeMap, String paramString)
    throws Exception
  {
    paramClass = readValue(paramType, paramClass, paramNodeMap);
    paramType = paramClass;
    if (paramString != null) {
      paramType = new Allocate(paramClass, this, paramString);
    }
    return paramType;
  }
  
  public Value read(Type paramType, NodeMap paramNodeMap)
    throws Exception
  {
    Node localNode = paramNodeMap.remove(this.label);
    Class localClass = paramType.getType();
    Object localObject = localClass;
    if (localClass.isArray()) {
      localObject = localClass.getComponentType();
    }
    if (localNode != null)
    {
      localObject = localNode.getValue();
      localObject = this.loader.load((String)localObject);
    }
    return readInstance(paramType, (Class)localObject, paramNodeMap);
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\ReadGraph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */