package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TryStatement
  extends AstNode
{
  private static final List<CatchClause> NO_CATCHES = Collections.unmodifiableList(new ArrayList());
  private List<CatchClause> catchClauses;
  private AstNode finallyBlock;
  private int finallyPosition = -1;
  private AstNode tryBlock;
  
  public TryStatement()
  {
    this.type = 82;
  }
  
  public TryStatement(int paramInt)
  {
    super(paramInt);
    this.type = 82;
  }
  
  public TryStatement(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 82;
  }
  
  public void addCatchClause(CatchClause paramCatchClause)
  {
    assertNotNull(paramCatchClause);
    if (this.catchClauses == null) {
      this.catchClauses = new ArrayList();
    }
    this.catchClauses.add(paramCatchClause);
    paramCatchClause.setParent(this);
  }
  
  public List<CatchClause> getCatchClauses()
  {
    if (this.catchClauses != null) {
      return this.catchClauses;
    }
    return NO_CATCHES;
  }
  
  public AstNode getFinallyBlock()
  {
    return this.finallyBlock;
  }
  
  public int getFinallyPosition()
  {
    return this.finallyPosition;
  }
  
  public AstNode getTryBlock()
  {
    return this.tryBlock;
  }
  
  public void setCatchClauses(List<CatchClause> paramList)
  {
    if (paramList == null) {
      this.catchClauses = null;
    }
    for (;;)
    {
      return;
      if (this.catchClauses != null) {
        this.catchClauses.clear();
      }
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        addCatchClause((CatchClause)paramList.next());
      }
    }
  }
  
  public void setFinallyBlock(AstNode paramAstNode)
  {
    this.finallyBlock = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public void setFinallyPosition(int paramInt)
  {
    this.finallyPosition = paramInt;
  }
  
  public void setTryBlock(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.tryBlock = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(250);
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("try ");
    localStringBuilder.append(this.tryBlock.toSource(paramInt).trim());
    Iterator localIterator = getCatchClauses().iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((CatchClause)localIterator.next()).toSource(paramInt));
    }
    if (this.finallyBlock != null)
    {
      localStringBuilder.append(" finally ");
      localStringBuilder.append(this.finallyBlock.toSource(paramInt));
    }
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.tryBlock.visit(paramNodeVisitor);
      Iterator localIterator = getCatchClauses().iterator();
      while (localIterator.hasNext()) {
        ((CatchClause)localIterator.next()).visit(paramNodeVisitor);
      }
      if (this.finallyBlock != null) {
        this.finallyBlock.visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\TryStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */