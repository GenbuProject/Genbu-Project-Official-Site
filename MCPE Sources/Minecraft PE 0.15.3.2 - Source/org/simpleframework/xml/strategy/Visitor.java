package org.simpleframework.xml.strategy;

import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public abstract interface Visitor
{
  public abstract void read(Type paramType, NodeMap<InputNode> paramNodeMap)
    throws Exception;
  
  public abstract void write(Type paramType, NodeMap<OutputNode> paramNodeMap)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\Visitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */