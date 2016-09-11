package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayComprehension
  extends Scope
{
  private AstNode filter;
  private int ifPosition = -1;
  private List<ArrayComprehensionLoop> loops = new ArrayList();
  private int lp = -1;
  private AstNode result;
  private int rp = -1;
  
  public ArrayComprehension()
  {
    this.type = 158;
  }
  
  public ArrayComprehension(int paramInt)
  {
    super(paramInt);
    this.type = 158;
  }
  
  public ArrayComprehension(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 158;
  }
  
  public void addLoop(ArrayComprehensionLoop paramArrayComprehensionLoop)
  {
    assertNotNull(paramArrayComprehensionLoop);
    this.loops.add(paramArrayComprehensionLoop);
    paramArrayComprehensionLoop.setParent(this);
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
  
  public List<ArrayComprehensionLoop> getLoops()
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
  
  public void setLoops(List<ArrayComprehensionLoop> paramList)
  {
    assertNotNull(paramList);
    this.loops.clear();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addLoop((ArrayComprehensionLoop)paramList.next());
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
    localStringBuilder.append("[");
    localStringBuilder.append(this.result.toSource(0));
    Iterator localIterator = this.loops.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((ArrayComprehensionLoop)localIterator.next()).toSource(0));
    }
    if (this.filter != null)
    {
      localStringBuilder.append(" if (");
      localStringBuilder.append(this.filter.toSource(0));
      localStringBuilder.append(")");
    }
    localStringBuilder.append("]");
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
        ((ArrayComprehensionLoop)localIterator.next()).visit(paramNodeVisitor);
      }
    } while (this.filter == null);
    this.filter.visit(paramNodeVisitor);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ArrayComprehension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */