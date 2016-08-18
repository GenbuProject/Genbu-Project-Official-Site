package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

abstract interface Parameter
{
  public abstract Annotation getAnnotation();
  
  public abstract Expression getExpression();
  
  public abstract int getIndex();
  
  public abstract Object getKey();
  
  public abstract String getName();
  
  public abstract String getPath();
  
  public abstract Class getType();
  
  public abstract boolean isAttribute();
  
  public abstract boolean isPrimitive();
  
  public abstract boolean isRequired();
  
  public abstract boolean isText();
  
  public abstract String toString();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Parameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */