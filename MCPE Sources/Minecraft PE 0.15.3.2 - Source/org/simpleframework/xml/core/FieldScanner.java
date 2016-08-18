package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.Transient;
import org.simpleframework.xml.Version;

class FieldScanner
  extends ContactList
{
  private final ContactMap done;
  private final AnnotationFactory factory;
  private final Support support;
  
  public FieldScanner(Detail paramDetail, Support paramSupport)
    throws Exception
  {
    this.factory = new AnnotationFactory(paramDetail, paramSupport);
    this.done = new ContactMap();
    this.support = paramSupport;
    scan(paramDetail);
  }
  
  private void build()
  {
    Iterator localIterator = this.done.iterator();
    while (localIterator.hasNext()) {
      add((Contact)localIterator.next());
    }
  }
  
  private void extend(Class paramClass, DefaultType paramDefaultType)
    throws Exception
  {
    paramClass = this.support.getFields(paramClass, paramDefaultType);
    if (paramClass != null) {
      addAll(paramClass);
    }
  }
  
  private void extract(Detail paramDetail)
  {
    paramDetail = paramDetail.getFields().iterator();
    while (paramDetail.hasNext())
    {
      Object localObject = (FieldDetail)paramDetail.next();
      Annotation[] arrayOfAnnotation = ((FieldDetail)localObject).getAnnotations();
      localObject = ((FieldDetail)localObject).getField();
      int j = arrayOfAnnotation.length;
      int i = 0;
      while (i < j)
      {
        scan((Field)localObject, arrayOfAnnotation[i], arrayOfAnnotation);
        i += 1;
      }
    }
  }
  
  private void extract(Detail paramDetail, DefaultType paramDefaultType)
    throws Exception
  {
    paramDetail = paramDetail.getFields();
    if (paramDefaultType == DefaultType.FIELD)
    {
      paramDetail = paramDetail.iterator();
      while (paramDetail.hasNext())
      {
        Object localObject = (FieldDetail)paramDetail.next();
        paramDefaultType = ((FieldDetail)localObject).getAnnotations();
        localObject = ((FieldDetail)localObject).getField();
        Class localClass = ((Field)localObject).getType();
        if ((!isStatic((Field)localObject)) && (!isTransient((Field)localObject))) {
          process((Field)localObject, localClass, paramDefaultType);
        }
      }
    }
  }
  
  private void insert(Object paramObject, Contact paramContact)
  {
    Contact localContact2 = (Contact)this.done.remove(paramObject);
    Contact localContact1 = paramContact;
    if (localContact2 != null)
    {
      localContact1 = paramContact;
      if (isText(paramContact)) {
        localContact1 = localContact2;
      }
    }
    this.done.put(paramObject, localContact1);
  }
  
  private boolean isStatic(Field paramField)
  {
    return Modifier.isStatic(paramField.getModifiers());
  }
  
  private boolean isText(Contact paramContact)
  {
    return (paramContact.getAnnotation() instanceof Text);
  }
  
  private boolean isTransient(Field paramField)
  {
    return Modifier.isTransient(paramField.getModifiers());
  }
  
  private void process(Field paramField, Class paramClass, Annotation[] paramArrayOfAnnotation)
    throws Exception
  {
    Class[] arrayOfClass = Reflector.getDependents(paramField);
    paramClass = this.factory.getInstance(paramClass, arrayOfClass);
    if (paramClass != null) {
      process(paramField, paramClass, paramArrayOfAnnotation);
    }
  }
  
  private void process(Field paramField, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
  {
    paramAnnotation = new FieldContact(paramField, paramAnnotation, paramArrayOfAnnotation);
    paramArrayOfAnnotation = new FieldKey(paramField);
    if (!paramField.isAccessible()) {
      paramField.setAccessible(true);
    }
    insert(paramArrayOfAnnotation, paramAnnotation);
  }
  
  private void remove(Field paramField, Annotation paramAnnotation)
  {
    this.done.remove(new FieldKey(paramField));
  }
  
  private void scan(Field paramField, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
  {
    if ((paramAnnotation instanceof Attribute)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementUnion)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementList)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementArray)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementMap)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Element)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Version)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Text)) {
      process(paramField, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Transient)) {
      remove(paramField, paramAnnotation);
    }
  }
  
  private void scan(Detail paramDetail)
    throws Exception
  {
    DefaultType localDefaultType1 = paramDetail.getOverride();
    DefaultType localDefaultType2 = paramDetail.getAccess();
    Class localClass = paramDetail.getSuper();
    if (localClass != null) {
      extend(localClass, localDefaultType1);
    }
    extract(paramDetail, localDefaultType2);
    extract(paramDetail);
    build();
  }
  
  private static class FieldKey
  {
    private final String name;
    private final Class type;
    
    public FieldKey(Field paramField)
    {
      this.type = paramField.getDeclaringClass();
      this.name = paramField.getName();
    }
    
    private boolean equals(FieldKey paramFieldKey)
    {
      if (paramFieldKey.type != this.type) {
        return false;
      }
      return paramFieldKey.name.equals(this.name);
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof FieldKey)) {
        return equals((FieldKey)paramObject);
      }
      return false;
    }
    
    public int hashCode()
    {
      return this.name.hashCode();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\FieldScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */