package org.mozilla.javascript.ast;

public class XmlPropRef
  extends XmlRef
{
  private Name propName;
  
  public XmlPropRef()
  {
    this.type = 80;
  }
  
  public XmlPropRef(int paramInt)
  {
    super(paramInt);
    this.type = 80;
  }
  
  public XmlPropRef(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.type = 80;
  }
  
  public Name getPropName()
  {
    return this.propName;
  }
  
  public void setPropName(Name paramName)
  {
    assertNotNull(paramName);
    this.propName = paramName;
    paramName.setParent(this);
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
    localStringBuilder.append(this.propName.toSource(0));
    return localStringBuilder.toString();
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    if (paramNodeVisitor.visit(this))
    {
      if (this.namespace != null) {
        this.namespace.visit(paramNodeVisitor);
      }
      this.propName.visit(paramNodeVisitor);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\XmlPropRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */