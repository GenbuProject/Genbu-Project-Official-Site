package org.mozilla.javascript.ast;

public abstract class XmlRef
  extends AstNode
{
  protected int atPos = -1;
  protected int colonPos = -1;
  protected Name namespace;
  
  public XmlRef() {}
  
  public XmlRef(int paramInt)
  {
    super(paramInt);
  }
  
  public XmlRef(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public int getAtPos()
  {
    return this.atPos;
  }
  
  public int getColonPos()
  {
    return this.colonPos;
  }
  
  public Name getNamespace()
  {
    return this.namespace;
  }
  
  public boolean isAttributeAccess()
  {
    return this.atPos >= 0;
  }
  
  public void setAtPos(int paramInt)
  {
    this.atPos = paramInt;
  }
  
  public void setColonPos(int paramInt)
  {
    this.colonPos = paramInt;
  }
  
  public void setNamespace(Name paramName)
  {
    this.namespace = paramName;
    if (paramName != null) {
      paramName.setParent(this);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ast\XmlRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */