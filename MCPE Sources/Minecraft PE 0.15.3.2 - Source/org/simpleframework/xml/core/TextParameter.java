package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

class TextParameter
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
  
  public TextParameter(Constructor paramConstructor, Text paramText, Format paramFormat, int paramInt)
    throws Exception
  {
    this.contact = new Contact(paramText, paramConstructor, paramInt);
    this.label = new TextLabel(this.contact, paramText, paramFormat);
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
  
  public String getName(Context paramContext)
  {
    return getName();
  }
  
  public String getPath()
  {
    return this.path;
  }
  
  public String getPath(Context paramContext)
  {
    return getPath();
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
  
  public boolean isText()
  {
    return true;
  }
  
  public String toString()
  {
    return this.contact.toString();
  }
  
  private static class Contact
    extends ParameterContact<Text>
  {
    public Contact(Text paramText, Constructor paramConstructor, int paramInt)
    {
      super(paramConstructor, paramInt);
    }
    
    public String getName()
    {
      return "";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\TextParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */