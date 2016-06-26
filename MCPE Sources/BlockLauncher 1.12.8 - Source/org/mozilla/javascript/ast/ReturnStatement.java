package org.mozilla.javascript.ast;

public class ReturnStatement
  extends AstNode
{
  private AstNode returnValue;
  
  public ReturnStatement()
  {
    this.type = 4;
  }
  
  public ReturnStatement(int paramInt)
  {
    super(paramInt);
    this.type = 4;
  }
  
  public ReturnStatement(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 4;
  }
  
  public ReturnStatement(int paramInt1, int paramInt2, AstNode paramAstNode)
  {
    super(paramInt1, paramInt2);
    this.type = 4;
    setReturnValue(paramAstNode);
  }
  
  public AstNode getReturnValue()
  {
    return this.returnValue;
  }
  
  public void setReturnValue(AstNode paramAstNode)
  {
    this.returnValue = paramAstNode;
    if (paramAstNode != null) {
      paramAstNode.setParent(this);
    }
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append("return");
    if (this.returnValue != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(this.returnValue.toSource(0));
    }
    localStringBuilder.append(";\n");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if ((paramNodeVisitor.visit(this)) && (this.returnValue != null)) {
      this.returnValue.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\ReturnStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */