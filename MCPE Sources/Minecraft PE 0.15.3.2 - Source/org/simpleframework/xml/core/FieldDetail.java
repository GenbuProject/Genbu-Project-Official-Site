package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class FieldDetail
{
  private final Field field;
  private final Annotation[] list;
  private final String name;
  
  public FieldDetail(Field paramField)
  {
    this.list = paramField.getDeclaredAnnotations();
    this.name = paramField.getName();
    this.field = paramField;
  }
  
  public Annotation[] getAnnotations()
  {
    return this.list;
  }
  
  public Field getField()
  {
    return this.field;
  }
  
  public String getName()
  {
    return this.name;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\FieldDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */