package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.stream.Format;

class ExtractorFactory
{
  private final Contact contact;
  private final Format format;
  private final Annotation label;
  
  public ExtractorFactory(Contact paramContact, Annotation paramAnnotation, Format paramFormat)
  {
    this.contact = paramContact;
    this.format = paramFormat;
    this.label = paramAnnotation;
  }
  
  private ExtractorBuilder getBuilder(Annotation paramAnnotation)
    throws Exception
  {
    if ((paramAnnotation instanceof ElementUnion)) {
      return new ExtractorBuilder(ElementUnion.class, ElementExtractor.class);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      return new ExtractorBuilder(ElementListUnion.class, ElementListExtractor.class);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      return new ExtractorBuilder(ElementMapUnion.class, ElementMapExtractor.class);
    }
    throw new PersistenceException("Annotation %s is not a union", new Object[] { paramAnnotation });
  }
  
  private Object getInstance(Annotation paramAnnotation)
    throws Exception
  {
    Constructor localConstructor = getBuilder(paramAnnotation).getConstructor();
    if (!localConstructor.isAccessible()) {
      localConstructor.setAccessible(true);
    }
    return localConstructor.newInstance(new Object[] { this.contact, paramAnnotation, this.format });
  }
  
  public Extractor getInstance()
    throws Exception
  {
    return (Extractor)getInstance(this.label);
  }
  
  private static class ElementExtractor
    implements Extractor<Element>
  {
    private final Contact contact;
    private final Format format;
    private final ElementUnion union;
    
    public ElementExtractor(Contact paramContact, ElementUnion paramElementUnion, Format paramFormat)
      throws Exception
    {
      this.contact = paramContact;
      this.format = paramFormat;
      this.union = paramElementUnion;
    }
    
    public Element[] getAnnotations()
    {
      return this.union.value();
    }
    
    public Label getLabel(Element paramElement)
    {
      return new ElementLabel(this.contact, paramElement, this.format);
    }
    
    public Class getType(Element paramElement)
    {
      Class localClass = paramElement.type();
      paramElement = localClass;
      if (localClass == Void.TYPE) {
        paramElement = this.contact.getType();
      }
      return paramElement;
    }
  }
  
  private static class ElementListExtractor
    implements Extractor<ElementList>
  {
    private final Contact contact;
    private final Format format;
    private final ElementListUnion union;
    
    public ElementListExtractor(Contact paramContact, ElementListUnion paramElementListUnion, Format paramFormat)
      throws Exception
    {
      this.contact = paramContact;
      this.format = paramFormat;
      this.union = paramElementListUnion;
    }
    
    public ElementList[] getAnnotations()
    {
      return this.union.value();
    }
    
    public Label getLabel(ElementList paramElementList)
    {
      return new ElementListLabel(this.contact, paramElementList, this.format);
    }
    
    public Class getType(ElementList paramElementList)
    {
      return paramElementList.type();
    }
  }
  
  private static class ElementMapExtractor
    implements Extractor<ElementMap>
  {
    private final Contact contact;
    private final Format format;
    private final ElementMapUnion union;
    
    public ElementMapExtractor(Contact paramContact, ElementMapUnion paramElementMapUnion, Format paramFormat)
      throws Exception
    {
      this.contact = paramContact;
      this.format = paramFormat;
      this.union = paramElementMapUnion;
    }
    
    public ElementMap[] getAnnotations()
    {
      return this.union.value();
    }
    
    public Label getLabel(ElementMap paramElementMap)
    {
      return new ElementMapLabel(this.contact, paramElementMap, this.format);
    }
    
    public Class getType(ElementMap paramElementMap)
    {
      return paramElementMap.valueType();
    }
  }
  
  private static class ExtractorBuilder
  {
    private final Class label;
    private final Class type;
    
    public ExtractorBuilder(Class paramClass1, Class paramClass2)
    {
      this.label = paramClass1;
      this.type = paramClass2;
    }
    
    private Constructor getConstructor()
      throws Exception
    {
      return this.type.getConstructor(new Class[] { Contact.class, this.label, Format.class });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ExtractorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */