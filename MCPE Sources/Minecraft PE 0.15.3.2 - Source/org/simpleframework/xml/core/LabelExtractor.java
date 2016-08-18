package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class LabelExtractor
{
  private final Cache<LabelGroup> cache = new ConcurrentCache();
  private final Format format;
  
  public LabelExtractor(Format paramFormat)
  {
    this.format = paramFormat;
  }
  
  private Annotation[] getAnnotations(Annotation paramAnnotation)
    throws Exception
  {
    Method[] arrayOfMethod = paramAnnotation.annotationType().getDeclaredMethods();
    if (arrayOfMethod.length > 0) {
      return (Annotation[])arrayOfMethod[0].invoke(paramAnnotation, new Object[0]);
    }
    return new Annotation[0];
  }
  
  private LabelBuilder getBuilder(Annotation paramAnnotation)
    throws Exception
  {
    if ((paramAnnotation instanceof Element)) {
      return new LabelBuilder(ElementLabel.class, Element.class);
    }
    if ((paramAnnotation instanceof ElementList)) {
      return new LabelBuilder(ElementListLabel.class, ElementList.class);
    }
    if ((paramAnnotation instanceof ElementArray)) {
      return new LabelBuilder(ElementArrayLabel.class, ElementArray.class);
    }
    if ((paramAnnotation instanceof ElementMap)) {
      return new LabelBuilder(ElementMapLabel.class, ElementMap.class);
    }
    if ((paramAnnotation instanceof ElementUnion)) {
      return new LabelBuilder(ElementUnionLabel.class, ElementUnion.class, Element.class);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      return new LabelBuilder(ElementListUnionLabel.class, ElementListUnion.class, ElementList.class);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      return new LabelBuilder(ElementMapUnionLabel.class, ElementMapUnion.class, ElementMap.class);
    }
    if ((paramAnnotation instanceof Attribute)) {
      return new LabelBuilder(AttributeLabel.class, Attribute.class);
    }
    if ((paramAnnotation instanceof Version)) {
      return new LabelBuilder(VersionLabel.class, Version.class);
    }
    if ((paramAnnotation instanceof Text)) {
      return new LabelBuilder(TextLabel.class, Text.class);
    }
    throw new PersistenceException("Annotation %s not supported", new Object[] { paramAnnotation });
  }
  
  private Constructor getConstructor(Annotation paramAnnotation)
    throws Exception
  {
    paramAnnotation = getBuilder(paramAnnotation).getConstructor();
    if (!paramAnnotation.isAccessible()) {
      paramAnnotation.setAccessible(true);
    }
    return paramAnnotation;
  }
  
  private LabelGroup getGroup(Contact paramContact, Annotation paramAnnotation, Object paramObject)
    throws Exception
  {
    LabelGroup localLabelGroup = (LabelGroup)this.cache.fetch(paramObject);
    if (localLabelGroup == null)
    {
      paramContact = getLabels(paramContact, paramAnnotation);
      if (paramContact != null) {
        this.cache.cache(paramObject, paramContact);
      }
      return paramContact;
    }
    return localLabelGroup;
  }
  
  private Object getKey(Contact paramContact, Annotation paramAnnotation)
  {
    return new LabelKey(paramContact, paramAnnotation);
  }
  
  private Label getLabel(Contact paramContact, Annotation paramAnnotation1, Annotation paramAnnotation2)
    throws Exception
  {
    Constructor localConstructor = getConstructor(paramAnnotation1);
    if (paramAnnotation2 != null) {
      return (Label)localConstructor.newInstance(new Object[] { paramContact, paramAnnotation1, paramAnnotation2, this.format });
    }
    return (Label)localConstructor.newInstance(new Object[] { paramContact, paramAnnotation1, this.format });
  }
  
  private LabelGroup getLabels(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    if ((paramAnnotation instanceof ElementUnion)) {
      return getUnion(paramContact, paramAnnotation);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      return getUnion(paramContact, paramAnnotation);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      return getUnion(paramContact, paramAnnotation);
    }
    return getSingle(paramContact, paramAnnotation);
  }
  
  private LabelGroup getSingle(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    paramAnnotation = getLabel(paramContact, paramAnnotation, null);
    paramContact = paramAnnotation;
    if (paramAnnotation != null) {
      paramContact = new CacheLabel(paramAnnotation);
    }
    return new LabelGroup(paramContact);
  }
  
  private LabelGroup getUnion(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    Annotation[] arrayOfAnnotation = getAnnotations(paramAnnotation);
    if (arrayOfAnnotation.length > 0)
    {
      LinkedList localLinkedList = new LinkedList();
      int j = arrayOfAnnotation.length;
      int i = 0;
      while (i < j)
      {
        Label localLabel = getLabel(paramContact, paramAnnotation, arrayOfAnnotation[i]);
        Object localObject = localLabel;
        if (localLabel != null) {
          localObject = new CacheLabel(localLabel);
        }
        localLinkedList.add(localObject);
        i += 1;
      }
      return new LabelGroup(localLinkedList);
    }
    return null;
  }
  
  public Label getLabel(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    paramContact = getGroup(paramContact, paramAnnotation, getKey(paramContact, paramAnnotation));
    if (paramContact != null) {
      return paramContact.getPrimary();
    }
    return null;
  }
  
  public List<Label> getList(Contact paramContact, Annotation paramAnnotation)
    throws Exception
  {
    paramContact = getGroup(paramContact, paramAnnotation, getKey(paramContact, paramAnnotation));
    if (paramContact != null) {
      return paramContact.getList();
    }
    return Collections.emptyList();
  }
  
  private static class LabelBuilder
  {
    private final Class entry;
    private final Class label;
    private final Class type;
    
    public LabelBuilder(Class paramClass1, Class paramClass2)
    {
      this(paramClass1, paramClass2, null);
    }
    
    public LabelBuilder(Class paramClass1, Class paramClass2, Class paramClass3)
    {
      this.entry = paramClass3;
      this.label = paramClass2;
      this.type = paramClass1;
    }
    
    private Constructor getConstructor(Class paramClass)
      throws Exception
    {
      return this.type.getConstructor(new Class[] { Contact.class, paramClass, Format.class });
    }
    
    private Constructor getConstructor(Class paramClass1, Class paramClass2)
      throws Exception
    {
      return this.type.getConstructor(new Class[] { Contact.class, paramClass1, paramClass2, Format.class });
    }
    
    public Constructor getConstructor()
      throws Exception
    {
      if (this.entry != null) {
        return getConstructor(this.label, this.entry);
      }
      return getConstructor(this.label);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\LabelExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */