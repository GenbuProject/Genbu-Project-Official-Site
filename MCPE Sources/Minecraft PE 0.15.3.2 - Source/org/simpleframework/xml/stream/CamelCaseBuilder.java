package org.simpleframework.xml.stream;

class CamelCaseBuilder
  implements Style
{
  protected final boolean attribute;
  protected final boolean element;
  
  public CamelCaseBuilder(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.attribute = paramBoolean2;
    this.element = paramBoolean1;
  }
  
  public String getAttribute(String paramString)
  {
    String str = null;
    if (paramString != null) {
      str = new Attribute(paramString, null).process();
    }
    return str;
  }
  
  public String getElement(String paramString)
  {
    String str = null;
    if (paramString != null) {
      str = new Element(paramString, null).process();
    }
    return str;
  }
  
  private class Attribute
    extends Splitter
  {
    private boolean capital;
    
    private Attribute(String paramString)
    {
      super();
    }
    
    protected void commit(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      this.builder.append(paramArrayOfChar, paramInt1, paramInt2);
    }
    
    protected void parse(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      if ((CamelCaseBuilder.this.attribute) || (this.capital)) {
        paramArrayOfChar[paramInt1] = toUpper(paramArrayOfChar[paramInt1]);
      }
      this.capital = true;
    }
  }
  
  private class Element
    extends CamelCaseBuilder.Attribute
  {
    private boolean capital;
    
    private Element(String paramString)
    {
      super(paramString, null);
    }
    
    protected void parse(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      if ((CamelCaseBuilder.this.element) || (this.capital)) {
        paramArrayOfChar[paramInt1] = toUpper(paramArrayOfChar[paramInt1]);
      }
      this.capital = true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\CamelCaseBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */