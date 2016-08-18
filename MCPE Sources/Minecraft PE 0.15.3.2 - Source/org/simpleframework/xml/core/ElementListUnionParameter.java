package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.stream.Format;

class ElementListUnionParameter
  extends TemplateParameter
{
  private final Contact contact;
  private final Expression expression;
  private final int index;
  private final Object key;
  private final Label label;
  private final String name;
  private final String path;
  private final Class type;
  
  public ElementListUnionParameter(Constructor paramConstructor, ElementListUnion paramElementListUnion, ElementList paramElementList, Format paramFormat, int paramInt)
    throws Exception
  {
    this.contact = new Contact(paramElementList, paramConstructor, paramInt);
    this.label = new ElementListUnionLabel(this.contact, paramElementListUnion, paramElementList, paramFormat);
    this.expression = this.label.getExpression();
    this.path = this.label.getPath();
    this.type = this.label.getType();
    this.name = this.label.getName();
    this.key = this.label.getKey();
    this.index = paramInt;
  }
  
  public Annotation getAnnotation()
  {
    return this.contact.getAnnotation();
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
  
  public boolean isPrimitive()
  {
    return this.type.isPrimitive();
  }
  
  public boolean isRequired()
  {
    return this.label.isRequired();
  }
  
  public String toString()
  {
    return this.contact.toString();
  }
  
  private static class Contact
    extends ParameterContact<ElementList>
  {
    public Contact(ElementList paramElementList, Constructor paramConstructor, int paramInt)
    {
      super(paramConstructor, paramInt);
    }
    
    public String getName()
    {
      return ((ElementList)this.label).name();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ElementListUnionParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */