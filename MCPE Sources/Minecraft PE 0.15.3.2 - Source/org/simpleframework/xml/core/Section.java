package org.simpleframework.xml.core;

abstract interface Section
  extends Iterable<String>
{
  public abstract String getAttribute(String paramString)
    throws Exception;
  
  public abstract LabelMap getAttributes()
    throws Exception;
  
  public abstract Label getElement(String paramString)
    throws Exception;
  
  public abstract LabelMap getElements()
    throws Exception;
  
  public abstract String getName();
  
  public abstract String getPath(String paramString)
    throws Exception;
  
  public abstract String getPrefix();
  
  public abstract Section getSection(String paramString)
    throws Exception;
  
  public abstract Label getText()
    throws Exception;
  
  public abstract boolean isSection(String paramString)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Section.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */