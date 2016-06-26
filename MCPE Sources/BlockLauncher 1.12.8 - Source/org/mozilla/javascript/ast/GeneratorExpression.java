package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneratorExpression
  extends Scope
{
  private AstNode filter;
  private int ifPosition = -1;
  private List<GeneratorExpressionLoop> loops = new ArrayList();
  private int lp = -1;
  private AstNode result;
  private int rp = -1;
  
  public GeneratorExpression()
  {
    this.type = 163;
  }
  
  public GeneratorExpression(int paramInt)
  {
    super(paramInt);
    this.type = 163;
  }
  
  public GeneratorExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 163;
  }
  
  public void addLoop(GeneratorExpressionLoop paramGeneratorExpressionLoop)
  {
    assertNotNull(paramGeneratorExpressionLoop);
    this.loops.add(paramGeneratorExpressionLoop);
    paramGeneratorExpressionLoop.setParent(this);
  }
  
  public AstNode getFilter()
  {
    return this.filter;
  }
  
  public int getFilterLp()
  {
    return this.lp;
  }
  
  public int getFilterRp()
  {
    return this.rp;
  }
  
  public int getIfPosition()
  {
    return this.ifPosition;
  }
  
  public List<GeneratorExpressionLoop> getLoops()
  {
    return this.loops;
  }
  
  public AstNode getResult()
  {
    return this.result;
  }
  
  public void setFilter(AstNode paramAstNode)
  {
    this.filter = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public void setFilterLp(int paramInt)
  {
    this.lp = paramInt;
  }
  
  public void setFilterRp(int paramInt)
  {
    this.rp = paramInt;
  }
  
  public void setIfPosition(int paramInt)
  {
    this.ifPosition = paramInt;
  }
  
  public void setLoops(List<GeneratorExpressionLoop> paramList)
  {
    assertNotNull(paramList);
    this.loops.clear();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addLoop((GeneratorExpressionLoop)paramList.next());
    }
  }
  
  public void setResult(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.result = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(250);
    localStringBuilder.append("(");
    localStringBuilder.append(this.result.toSource(0));
    Iterator localIterator = this.loops.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((GeneratorExpressionLoop)localIterator.next()).toSource(0));
    }
    if (this.filter != null)
    {
      localStringBuilder.append(" if (");
      localStringBuilder.append(this.filter.toSource(0));
      localStringBuilder.append(")");
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (!paramNodeVisitor.visit(this)) {}
    do
    {
      return;
      this.result.visit(paramNodeVisitor);
      Iterator localIterator = this.loops.iterator();
      while (localIterator.hasNext()) {
        ((GeneratorExpressionLoop)localIterator.next()).visit(paramNodeVisitor);
      }
    } while (this.filter == null);
    this.filter.visit(paramNodeVisitor);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\GeneratorExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */