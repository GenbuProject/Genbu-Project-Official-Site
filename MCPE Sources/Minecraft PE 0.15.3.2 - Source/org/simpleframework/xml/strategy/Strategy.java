package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public abstract interface Strategy
{
  public abstract Value read(Type paramType, NodeMap<InputNode> paramNodeMap, Map paramMap)
    throws Exception;
  
  public abstract boolean write(Type paramType, Object paramObject, NodeMap<OutputNode> paramNodeMap, Map paramMap)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\Strategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */