package org.mozilla.javascript.ast;

public class XmlExpression
  extends XmlFragment
{
  private AstNode expression;
  private boolean isXmlAttribute;
  
  public XmlExpression() {}
  
  public XmlExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public XmlExpression(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public XmlExpression(int paramInt, AstNode paramAstNode)
  {
    super(paramInt);
    setExpression(paramAstNode);
  }
  
  public AstNode getExpression()
  {
    return this.expression;
  }
  
  public boolean isXmlAttribute()
  {
    return this.isXmlAttribute;
  }
  
  public void setExpression(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    this.expression = paramAstNode;
    paramAstNode.setParent(this);
  }
  
  public void setIsXmlAttribute(boolean paramBoolean)
  {
    this.isXmlAttribute = paramBoolean;
  }
  
  public String toSource(int paramInt)
  {
    return makeIndent(paramInt) + "{" + this.expression.toSource(paramInt) + "}";
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this)) {
      this.expression.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\XmlExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */