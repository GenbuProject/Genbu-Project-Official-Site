package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

abstract class TemplateLabel
  implements Label
{
  private final KeyBuilder builder = new KeyBuilder(this);
  
  public Type getDependent()
    throws Exception
  {
    return null;
  }
  
  public String getEntry()
    throws Exception
  {
    return null;
  }
  
  public Object getKey()
    throws Exception
  {
    return this.builder.getKey();
  }
  
  public Label getLabel(Class paramClass)
    throws Exception
  {
    return this;
  }
  
  public String[] getNames()
    throws Exception
  {
    return new String[] { getPath(), getName() };
  }
  
  public String[] getPaths()
    throws Exception
  {
    return new String[] { getPath() };
  }
  
  public Type getType(Class paramClass)
    throws Exception
  {
    return getContact();
  }
  
  public boolean isAttribute()
  {
    return false;
  }
  
  public boolean isCollection()
  {
    return false;
  }
  
  public boolean isInline()
  {
    return false;
  }
  
  public boolean isText()
  {
    return false;
  }
  
  public boolean isTextList()
  {
    return false;
  }
  
  public boolean isUnion()
  {
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\TemplateLabel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */