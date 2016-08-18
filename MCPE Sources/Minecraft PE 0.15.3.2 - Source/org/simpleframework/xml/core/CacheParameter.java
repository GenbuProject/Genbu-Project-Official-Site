package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

class CacheParameter
  implements Parameter
{
  private final Annotation annotation;
  private final boolean attribute;
  private final Expression expression;
  private final int index;
  private final Object key;
  private final String name;
  private final String path;
  private final boolean primitive;
  private final boolean required;
  private final String string;
  private final boolean text;
  private final Class type;
  
  public CacheParameter(Parameter paramParameter, Label paramLabel)
    throws Exception
  {
    this.annotation = paramParameter.getAnnotation();
    this.expression = paramParameter.getExpression();
    this.attribute = paramParameter.isAttribute();
    this.primitive = paramParameter.isPrimitive();
    this.required = paramLabel.isRequired();
    this.string = paramParameter.toString();
    this.text = paramParameter.isText();
    this.index = paramParameter.getIndex();
    this.name = paramParameter.getName();
    this.path = paramParameter.getPath();
    this.type = paramParameter.getType();
    this.key = paramLabel.getKey();
  }
  
  public Annotation getAnnotation()
  {
    return this.annotation;
  }
  
  public Expression getExpression()
  {
    return this.expression;
  }
  
  public int getIndex()
  {
    return this.index;
  }
  
  public Object getKey()
  {
    return this.key;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPath()
  {
    return this.path;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public boolean isAttribute()
  {
    return this.attribute;
  }
  
  public boolean isPrimitive()
  {
    return this.primitive;
  }
  
  public boolean isRequired()
  {
    return this.required;
  }
  
  public boolean isText()
  {
    return this.text;
  }
  
  public String toString()
  {
    return this.string;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CacheParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */