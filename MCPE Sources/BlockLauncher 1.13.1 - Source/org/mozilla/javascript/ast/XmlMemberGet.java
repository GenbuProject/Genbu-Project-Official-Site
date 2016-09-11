package org.mozilla.javascript.ast;

public class XmlMemberGet
  extends InfixExpression
{
  public XmlMemberGet()
  {
    this.type = 144;
  }
  
  public XmlMemberGet(int paramInt)
  {
    super(paramInt);
    this.type = 144;
  }
  
  public XmlMemberGet(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 144;
  }
  
  public XmlMemberGet(int paramInt1, int paramInt2, AstNode paramAstNode, XmlRef paramXmlRef)
  {
    super(paramInt1, paramInt2, paramAstNode, paramXmlRef);
    this.type = 144;
  }
  
  public XmlMemberGet(AstNode paramAstNode, XmlRef paramXmlRef)
  {
    super(paramAstNode, paramXmlRef);
    this.type = 144;
  }
  
  public XmlMemberGet(AstNode paramAstNode, XmlRef paramXmlRef, int paramInt)
  {
    super(144, paramAstNode, paramXmlRef, paramInt);
    this.type = 144;
  }
  
  public XmlRef getMemberRef()
  {
    return (XmlRef)getRight();
  }
  
  public AstNode getTarget()
  {
    return getLeft();
  }
  
  public void setProperty(XmlRef paramXmlRef)
  {
    setRight(paramXmlRef);
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
    localStringBuilder.append(operatorToString(getType()));
    localStringBuilder.append(getRight().toSource(0));
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\XmlMemberGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */