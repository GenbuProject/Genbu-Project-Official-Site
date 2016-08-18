package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

class ElementListLabel
  extends TemplateLabel
{
  private Expression cache;
  private boolean data;
  private Decorator decorator;
  private Introspector detail;
  private String entry;
  private Format format;
  private boolean inline;
  private Class item;
  private ElementList label;
  private String name;
  private String override;
  private String path;
  private boolean required;
  private Class type;
  
  public ElementListLabel(Contact paramContact, ElementList paramElementList, Format paramFormat)
  {
    this.detail = new Introspector(paramContact, this, paramFormat);
    this.decorator = new Qualifier(paramContact);
    this.required = paramElementList.required();
    this.type = paramContact.getType();
    this.override = paramElementList.name();
    this.inline = paramElementList.inline();
    this.entry = paramElementList.entry();
    this.data = paramElementList.data();
    this.item = paramElementList.type();
    this.format = paramFormat;
    this.label = paramElementList;
  }
  
  private Converter getConverter(Context paramContext, String paramString)
    throws Exception
  {
    Type localType = getDependent();
    Contact localContact = getContact();
    if (!paramContext.isPrimitive(localType)) {
      return new CompositeList(paramContext, localContact, localType, paramString);
    }
    return new PrimitiveList(paramContext, localContact, localType, paramString);
  }
  
  private Converter getInlineConverter(Context paramContext, String paramString)
    throws Exception
  {
    Type localType = getDependent();
    Contact localContact = getContact();
    if (!paramContext.isPrimitive(localType)) {
      return new CompositeInlineList(paramContext, localContact, localType, paramString);
    }
    return new PrimitiveInlineList(paramContext, localContact, localType, paramString);
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
    String str = getEntry();
    if (!this.label.inline()) {
      return getConverter(paramContext, str);
    }
    return getInlineConverter(paramContext, str);
  }
  
  public Decorator getDecorator()
    throws Exception
  {
    return this.decorator;
  }
  
  public Type getDependent()
    throws Exception
  {
    Contact localContact = getContact();
    if (this.item == Void.TYPE) {
      this.item = localContact.getDependent();
    }
    if (this.item == null) {
      throw new ElementException("Unable to determine generic type for %s", new Object[] { localContact });
    }
    return new ClassType(this.item);
  }
  
  public Object getEmpty(Context paramContext)
    throws Exception
  {
    paramContext = new CollectionFactory(paramContext, new ClassType(this.type));
    if (!this.label.empty()) {
      return paramContext.getInstance();
    }
    return null;
  }
  
  public String getEntry()
    throws Exception
  {
    Style localStyle = this.format.getStyle();
    if (this.detail.isEmpty(this.entry)) {
      this.entry = this.detail.getEntry();
    }
    return localStyle.getElement(this.entry);
  }
  
  public Expression getExpression()
    throws Exception
  {
    if (this.cache == null) {
      this.cache = this.detail.getExpression();
    }
    return this.cache;
  }
  
  public String getName()
    throws Exception
  {
    if (this.name == null) {
      this.name = this.format.getStyle().getElement(this.detail.getName());
    }
    return this.name;
  }
  
  public String getOverride()
  {
    return this.override;
  }
  
  public String getPath()
    throws Exception
  {
    if (this.path == null) {
      this.path = getExpression().getElement(getName());
    }
    return this.path;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public boolean isCollection()
  {
    return true;
  }
  
  public boolean isData()
  {
    return this.data;
  }
  
  public boolean isInline()
  {
    return this.inline;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ElementListLabel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */