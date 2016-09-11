package org.mozilla.javascript.ast;

public class XmlString
  extends XmlFragment
{
  private String xml;
  
  public XmlString() {}
  
  public XmlString(int paramInt)
  {
    super(paramInt);
  }
  
  public XmlString(int paramInt, String paramString)
  {
    super(paramInt);
    setXml(paramString);
  }
  
  public String getXml()
  {
    return this.xml;
  }
  
  public void setXml(String paramString)
  {
    assertNotNull(paramString);
    this.xml = paramString;
    setLength(paramString.length());
  }
  
  public String toSource(int paramInt)
  {
    return makeIndent(paramInt) + this.xml;
  }
  
  public void visit(NodeVisitor paramNodeVisitor)
  {
    paramNodeVisitor.visit(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\XmlString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */