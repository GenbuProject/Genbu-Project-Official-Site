package org.mozilla.javascript.ast;

public class XmlElemRef
  extends XmlRef
{
  private AstNode indexExpr;
  private int lb = -1;
  private int rb = -1;
  
  public XmlElemRef()
  {
    this.type = 78;
  }
  
  public XmlElemRef(int paramInt)
  {
    super(paramInt);
    this.type = 78;
  }
  
  public XmlElemRef(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 78;
  }
  
  public AstNode getExpression()
  {
    return this.indexExpr;
  }
  
  public int getLb()
  {
    return this.lb;
  }
  
  public int getRb()
  {
    return this.rb;
  }
  
  public void setBrackets(int paramInt1, int paramInt2)
  {
    this.lb = paramInt1;
    this.rb = paramInt2;
  }
  
  public void setExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.indexExpr = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setLb(int paramInt)
  {
    this.lb = paramInt;
  }
  
  public void setRb(int paramInt)
  {
    this.rb = paramInt;
  }
  
  public String toSource(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(makeIndent(paramInt));
    if (isAttributeAccess()) {
      localStringBuilder.append("@");
    }
    if (this.namespace != null)
    {
      localStringBuilder.append(this.namespace.toSource(0));
      localStringBuilder.append("::");
    }
    localStringBuilder.append("[");
    localStringBuilder.append(this.indexExpr.toSource(0));
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      if (this.namespace != null) {
        this.namespace.visit(paramNodeVisitor);
      }
      this.indexExpr.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\XmlElemRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */