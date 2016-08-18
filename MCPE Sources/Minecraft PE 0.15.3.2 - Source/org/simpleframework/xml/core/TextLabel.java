package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

class TextLabel
  extends TemplateLabel
{
  private Contact contact;
  private boolean data;
  private Introspector detail;
  private String empty;
  private Text label;
  private Expression path;
  private boolean required;
  private Class type;
  
  public TextLabel(Contact paramContact, Text paramText, Format paramFormat)
  {
    this.detail = new Introspector(paramContact, this, paramFormat);
    this.required = paramText.required();
    this.type = paramContact.getType();
    this.empty = paramText.empty();
    this.data = paramText.data();
    this.contact = paramContact;
    this.label = paramText;
  }
  
  public Annotation getAnnotation()
  {
    return this.label;
  }
  
  public Contact getContact()
  {
    return this.contact;
  }
  
  public Converter getConverter(Context paramContext)
    throws Exception
  {
    String str = getEmpty(paramContext);
    Contact localContact = getContact();
    if (!paramContext.isPrimitive(localContact)) {
      throw new TextException("Cannot use %s to represent %s", new Object[] { localContact, this.label });
    }
    return new Primitive(paramContext, localContact, str);
  }
  
  public Decorator getDecorator()
    throws Exception
  {
    return null;
  }
  
  public String getEmpty(Context paramContext)
  {
    if (this.detail.isEmpty(this.empty)) {
      return null;
    }
    return this.empty;
  }
  
  public Expression getExpression()
    throws Exception
  {
    if (this.path == null) {
      this.path = this.detail.getExpression();
    }
    return this.path;
  }
  
  public String getName()
  {
    return "";
  }
  
  public String getOverride()
  {
    return this.contact.toString();
  }
  
  public String getPath()
    throws Exception
  {
    return getExpression().getPath();
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public boolean isData()
  {
    return this.data;
  }
  
  public boolean isInline()
  {
    return true;
  }
  
  public boolean isRequired()
  {
    return this.required;
  }
  
  public boolean isText()
  {
    return true;
  }
  
  public String toString()
  {
    return this.detail.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\TextLabel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */