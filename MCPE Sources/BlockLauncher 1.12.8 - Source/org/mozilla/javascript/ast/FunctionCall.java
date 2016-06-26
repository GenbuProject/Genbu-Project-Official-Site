package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FunctionCall
  extends AstNode
{
  protected static final List<AstNode> NO_ARGS = Collections.unmodifiableList(new ArrayList());
  protected List<AstNode> arguments;
  protected int lp = -1;
  protected int rp = -1;
  protected AstNode target;
  
  public FunctionCall()
  {
    this.type = 38;
  }
  
  public FunctionCall(int paramInt)
  {
    super(paramInt);
    this.type = 38;
  }
  
  public FunctionCall(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 38;
  }
  
  public void addArgument(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    if (this.arguments == null) {
      this.arguments = new ArrayList();
    }
    this.arguments.add(paramAstNode);
    paramAstNode.setParent(this);
  }
  
  public List<AstNode> getArguments()
  {
    if (this.arguments != null) {
      return this.arguments;
    }
    return NO_ARGS;
  }
  
  public int getLp()
  {
    return this.lp;
  }
  
  public int getRp()
  {
    return this.rp;
  }
  
  public AstNode getTarget()
  {
    return this.target;
  }
  
  public void setArguments(List<AstNode> paramList)
  {
    if (paramList == null) {
      this.arguments = null;
    }
    for (;;)
    {
      return;
      if (this.arguments != null) {
        this.arguments.clear();
      }
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        addArgument((AstNode)paramList.next());
      }
    }
  }
  
  public void setLp(int paramInt)
  {
    this.lp = paramInt;
  }
  
  public void setParens(int paramInt1, int paramInt2)
  {
    this.lp = paramInt1;
    this.rp = paramInt2;
  }
  
  public void setRp(int paramInt)
  {
    this.rp = paramInt;
  }
  
  public void setTarget(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.target = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(this.target.toSource(0));
    localStringBuilder.append("(");
    if (this.arguments != null) {
      printList(this.arguments, localStringBuilder);
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      this.target.visit(paramNodeVisitor);
      Iterator localIterator = getArguments().iterator();
      while (localIterator.hasNext()) {
        ((AstNode)localIterator.next()).visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\FunctionCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */