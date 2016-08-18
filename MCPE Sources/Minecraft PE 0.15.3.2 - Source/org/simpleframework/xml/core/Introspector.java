package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

class Introspector
{
  private final Contact contact;
  private final Format format;
  private final Label label;
  private final Annotation marker;
  
  public Introspector(Contact paramContact, Label paramLabel, Format paramFormat)
  {
    this.marker = paramContact.getAnnotation();
    this.contact = paramContact;
    this.format = paramFormat;
    this.label = paramLabel;
  }
  
  private String getDefault()
    throws Exception
  {
    String str = this.label.getOverride();
    if (!isEmpty(str)) {
      return str;
    }
    return this.contact.getName();
  }
  
  private String getName(Class paramClass)
    throws Exception
  {
    String str = getRoot(paramClass);
    if (str != null) {
      return str;
    }
    return Reflector.getName(paramClass.getSimpleName());
  }
  
  private String getRoot(Class paramClass)
  {
    for (Class localClass = paramClass; localClass != null; localClass = localClass.getSuperclass())
    {
      String str = getRoot(paramClass, localClass);
      if (str != null) {
        return str;
      }
    }
    return null;
  }
  
  private String getRoot(Class<?> paramClass1, Class<?> paramClass2)
  {
    paramClass1 = paramClass2.getSimpleName();
    paramClass2 = (Root)paramClass2.getAnnotation(Root.class);
    if (paramClass2 != null)
    {
      paramClass2 = paramClass2.name();
      if (!isEmpty(paramClass2)) {
        return paramClass2;
      }
      return Reflector.getName(paramClass1);
    }
    return null;
  }
  
  public Contact getContact()
  {
    return this.contact;
  }
  
  public Type getDependent()
    throws Exception
  {
    return this.label.getDependent();
  }
  
  public String getEntry()
    throws Exception
  {
    Class localClass2 = getDependent().getType();
    Class localClass1 = localClass2;
    if (localClass2.isArray()) {
      localClass1 = localClass2.getComponentType();
    }
    return getName(localClass1);
  }
  
  public Expression getExpression()
    throws Exception
  {
    String str = getPath();
    if (str != null) {
      return new PathParser(str, this.contact, this.format);
    }
    return new EmptyExpression(this.format);
  }
  
  public String getName()
    throws Exception
  {
    String str = this.label.getEntry();
    if (!this.label.isInline()) {
      str = getDefault();
    }
    return str;
  }
  
  public String getPath()
    throws Exception
  {
    Path localPath = (Path)this.contact.getAnnotation(Path.class);
    if (localPath == null) {
      return null;
    }
    return localPath.value();
  }
  
  public boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public String toString()
  {
    return String.format("%s on %s", new Object[] { this.marker, this.contact });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Introspector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */