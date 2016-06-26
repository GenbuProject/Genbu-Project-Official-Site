package org.mozilla.javascript.ast;

import java.util.Iterator;
import java.util.List;

public class NewExpression
  extends FunctionCall
{
  private ObjectLiteral initializer;
  
  public NewExpression()
  {
    this.type = 30;
  }
  
  public NewExpression(int paramInt)
  {
    super(paramInt);
    this.type = 30;
  }
  
  public NewExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 30;
  }
  
  public ObjectLiteral getInitializer()
  {
    return this.initializer;
  }
  
  public void setInitializer(ObjectLiteral paramObjectLiteral)
  {
    this.initializer = paramObjectLiteral;
    if (paramObjectLiteral != null) {
      paramObjectLiteral.setParent(this);
    }
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("new ");
    localStringBuilder.append(this.target.toSource(0));
    localStringBuilder.append("(");
    if (this.arguments != null) {
      printList(this.arguments, localStringBuilder);
    }
    localStringBuilder.append(")");
    if (this.initializer != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(this.initializer.toSource(0));
    }
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
      if (this.initializer != null) {
        this.initializer.visit(paramNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\NewExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */