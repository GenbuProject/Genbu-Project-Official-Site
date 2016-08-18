package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

abstract interface Contact
  extends Type
{
  public abstract Object get(Object paramObject)
    throws Exception;
  
  public abstract Annotation getAnnotation();
  
  public abstract Class getDeclaringClass();
  
  public abstract Class getDependent();
  
  public abstract Class[] getDependents();
  
  public abstract String getName();
  
  public abstract boolean isReadOnly();
  
  public abstract void set(Object paramObject1, Object paramObject2)
    throws Exception;
  
  public abstract String toString();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Contact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */