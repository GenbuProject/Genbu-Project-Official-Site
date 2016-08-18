package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

class VersionLabel
  extends TemplateLabel
{
  private Decorator decorator;
  private Introspector detail;
  private Format format;
  private Version label;
  private String name;
  private Expression path;
  private boolean required;
  private Class type;
  
  public VersionLabel(Contact paramContact, Version paramVersion, Format paramFormat)
  {
    this.detail = new Introspector(paramContact, this, paramFormat);
    this.decorator = new Qualifier(paramContact);
    this.required = paramVersion.required();
    this.type = paramContact.getType();
    this.name = paramVersion.name();
    this.format = paramFormat;
    this.label = paramVersion;
  }
  
  public Annotation getAnnotation()
  {
    return this.label;
  }
  
  public Contact getContact()
  {
    return this.detail.getContact();
  }
  
  public Converter getConverter(Context paramContext)
    throws Exception
  {
    String str = getEmpty(paramContext);
    Contact localContact = getContact();
    if (!paramContext.isFloat(localContact)) {
      throw new AttributeException("Cannot use %s to represent %s", new Object[] { this.label, localContact });
    }
    return new Primitive(paramContext, localContact, str);
  }
  
  public Decorator getDecorator()
    throws Exception
  {
    return this.decorator;
  }
  
  public String getEmpty(Context paramContext)
  {
    return null;
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
    throws Exception
  {
    return this.format.getStyle().getAttribute(this.detail.getName());
  }
  
  public String getOverride()
  {
    return this.name;
  }
  
  public String getPath()
    throws Exception
  {
    return getExpression().getAttribute(getName());
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public boolean isAttribute()
  {
    return true;
  }
  
  public boolean isData()
  {
    return false;
  }
  
  public boolean isRequired()
  {
    return this.required;
  }
  
  public String toString()
  {
    return this.detail.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\VersionLabel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */