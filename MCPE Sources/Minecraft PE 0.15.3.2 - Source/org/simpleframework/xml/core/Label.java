package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

abstract interface Label
{
  public abstract Annotation getAnnotation();
  
  public abstract Contact getContact();
  
  public abstract Converter getConverter(Context paramContext)
    throws Exception;
  
  public abstract Decorator getDecorator()
    throws Exception;
  
  public abstract Type getDependent()
    throws Exception;
  
  public abstract Object getEmpty(Context paramContext)
    throws Exception;
  
  public abstract String getEntry()
    throws Exception;
  
  public abstract Expression getExpression()
    throws Exception;
  
  public abstract Object getKey()
    throws Exception;
  
  public abstract Label getLabel(Class paramClass)
    throws Exception;
  
  public abstract String getName()
    throws Exception;
  
  public abstract String[] getNames()
    throws Exception;
  
  public abstract String getOverride();
  
  public abstract String getPath()
    throws Exception;
  
  public abstract String[] getPaths()
    throws Exception;
  
  public abstract Class getType();
  
  public abstract Type getType(Class paramClass)
    throws Exception;
  
  public abstract boolean isAttribute();
  
  public abstract boolean isCollection();
  
  public abstract boolean isData();
  
  public abstract boolean isInline();
  
  public abstract boolean isRequired();
  
  public abstract boolean isText();
  
  public abstract boolean isTextList();
  
  public abstract boolean isUnion();
  
  public abstract String toString();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Label.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */