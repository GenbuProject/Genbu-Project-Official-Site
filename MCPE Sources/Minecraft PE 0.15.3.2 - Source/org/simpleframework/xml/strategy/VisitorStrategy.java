package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class VisitorStrategy
  implements Strategy
{
  private final Strategy strategy;
  private final Visitor visitor;
  
  public VisitorStrategy(Visitor paramVisitor)
  {
    this(paramVisitor, new TreeStrategy());
  }
  
  public VisitorStrategy(Visitor paramVisitor, Strategy paramStrategy)
  {
    this.strategy = paramStrategy;
    this.visitor = paramVisitor;
  }
  
  public Value read(Type paramType, NodeMap<InputNode> paramNodeMap, Map paramMap)
    throws Exception
  {
    if (this.visitor != null) {
      this.visitor.read(paramType, paramNodeMap);
    }
    return this.strategy.read(paramType, paramNodeMap, paramMap);
  }
  
  public boolean write(Type paramType, Object paramObject, NodeMap<OutputNode> paramNodeMap, Map paramMap)
    throws Exception
  {
    boolean bool = this.strategy.write(paramType, paramObject, paramNodeMap, paramMap);
    if (this.visitor != null) {
      this.visitor.write(paramType, paramNodeMap);
    }
    return bool;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\VisitorStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */