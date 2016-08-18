package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

abstract interface Extractor<T extends Annotation>
{
  public abstract T[] getAnnotations()
    throws Exception;
  
  public abstract Label getLabel(T paramT)
    throws Exception;
  
  public abstract Class getType(T paramT)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Extractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */