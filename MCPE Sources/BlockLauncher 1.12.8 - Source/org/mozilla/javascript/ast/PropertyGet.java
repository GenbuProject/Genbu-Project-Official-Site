package org.mozilla.javascript.ast;

public class PropertyGet
  extends InfixExpression
{
  public PropertyGet()
  {
    this.type = 33;
  }
  
  public PropertyGet(int paramInt)
  {
    super(paramInt);
    this.type = 33;
  }
  
  public PropertyGet(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 33;
  }
  
  public PropertyGet(int paramInt1, int paramInt2, AstNode paramAstNode, Name paramName)
  {
    super(paramInt1, paramInt2, paramAstNode, paramName);
    this.type = 33;
  }
  
  public PropertyGet(AstNode paramAstNode, Name paramName)
  {
    super(paramAstNode, paramName);
    this.type = 33;
  }
  
  public PropertyGet(AstNode paramAstNode, Name paramName, int paramInt)
  {
    super(33, paramAstNode, paramName, paramInt);
    this.type = 33;
  }
  
  public Name getProperty()
  {
    return (Name)getRight();
  }
  
  public AstNode getTarget()
  {
    return getLeft();
  }
  
  public void setProperty(Name paramName)
  {
    setRight(paramName);
  }
  
  public void setTarget(AstNode paramAstNode)
  {
    setLeft(paramAstNode);
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    localStringBuilder.append(getLeft().toSource(0));
    localStringBuilder.append(".");
    localStringBuilder.append(getRight().toSource(0));
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      getTarget().visit(paramNodeVisitor);
      getProperty().visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\PropertyGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */