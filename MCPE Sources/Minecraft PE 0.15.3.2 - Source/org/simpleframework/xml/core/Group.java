package org.simpleframework.xml.core;

abstract interface Group
{
  public abstract LabelMap getElements()
    throws Exception;
  
  public abstract Label getLabel(Class paramClass);
  
  public abstract Label getText()
    throws Exception;
  
  public abstract boolean isInline();
  
  public abstract boolean isTextList();
  
  public abstract String toString();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Group.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */