package org.simpleframework.xml.convert;

import java.util.Map;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class RegistryStrategy
  implements Strategy
{
  private final Registry registry;
  private final Strategy strategy;
  
  public RegistryStrategy(Registry paramRegistry)
  {
    this(paramRegistry, new TreeStrategy());
  }
  
  public RegistryStrategy(Registry paramRegistry, Strategy paramStrategy)
  {
    this.registry = paramRegistry;
    this.strategy = paramStrategy;
  }
  
  private boolean isReference(Value paramValue)
  {
    return (paramValue != null) && (paramValue.isReference());
  }
  
  private Converter lookup(Type paramType, Object paramObject)
    throws Exception
  {
    paramType = paramType.getType();
    if (paramObject != null) {
      paramType = paramObject.getClass();
    }
    return this.registry.lookup(paramType);
  }
  
  private Converter lookup(Type paramType, Value paramValue)
    throws Exception
  {
    paramType = paramType.getType();
    if (paramValue != null) {
      paramType = paramValue.getType();
    }
    return this.registry.lookup(paramType);
  }
  
  private Value read(Type paramType, NodeMap<InputNode> paramNodeMap, Value paramValue)
    throws Exception
  {
    Converter localConverter = lookup(paramType, paramValue);
    InputNode localInputNode = (InputNode)paramNodeMap.getNode();
    paramNodeMap = paramValue;
    if (localConverter != null)
    {
      paramNodeMap = localConverter.read(localInputNode);
      paramType = paramType.getType();
      if (paramValue != null) {
        paramValue.setValue(paramNodeMap);
      }
      paramNodeMap = new Reference(paramValue, paramNodeMap, paramType);
    }
    return paramNodeMap;
  }
  
  private boolean write(Type paramType, Object paramObject, NodeMap<OutputNode> paramNodeMap)
    throws Exception
  {
    paramType = lookup(paramType, paramObject);
    paramNodeMap = (OutputNode)paramNodeMap.getNode();
    if (paramType != null)
    {
      paramType.write(paramNodeMap, paramObject);
      return true;
    }
    return false;
  }
  
  public Value read(Type paramType, NodeMap<InputNode> paramNodeMap, Map paramMap)
    throws Exception
  {
    paramMap = this.strategy.read(paramType, paramNodeMap, paramMap);
    if (isReference(paramMap)) {
      return paramMap;
    }
    return read(paramType, paramNodeMap, paramMap);
  }
  
  public boolean write(Type paramType, Object paramObject, NodeMap<OutputNode> paramNodeMap, Map paramMap)
    throws Exception
  {
    boolean bool2 = this.strategy.write(paramType, paramObject, paramNodeMap, paramMap);
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = write(paramType, paramObject, paramNodeMap);
    }
    return bool1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\RegistryStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */