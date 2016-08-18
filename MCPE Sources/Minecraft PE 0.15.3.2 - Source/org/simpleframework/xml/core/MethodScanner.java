package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
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

class MethodScanner
  extends ContactList
{
  private final Detail detail;
  private final MethodPartFactory factory;
  private final PartMap read;
  private final Support support;
  private final PartMap write;
  
  public MethodScanner(Detail paramDetail, Support paramSupport)
    throws Exception
  {
    this.factory = new MethodPartFactory(paramDetail, paramSupport);
    this.write = new PartMap(null);
    this.read = new PartMap(null);
    this.support = paramSupport;
    this.detail = paramDetail;
    scan(paramDetail);
  }
  
  private void build()
    throws Exception
  {
    Iterator localIterator = this.read.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      MethodPart localMethodPart = (MethodPart)this.read.get(str);
      if (localMethodPart != null) {
        build(localMethodPart, str);
      }
    }
  }
  
  private void build(MethodPart paramMethodPart)
    throws Exception
  {
    add(new MethodContact(paramMethodPart));
  }
  
  private void build(MethodPart paramMethodPart, String paramString)
    throws Exception
  {
    paramString = this.write.take(paramString);
    if (paramString != null)
    {
      build(paramMethodPart, paramString);
      return;
    }
    build(paramMethodPart);
  }
  
  private void build(MethodPart paramMethodPart1, MethodPart paramMethodPart2)
    throws Exception
  {
    Object localObject = paramMethodPart1.getAnnotation();
    String str = paramMethodPart1.getName();
    if (!paramMethodPart2.getAnnotation().equals(localObject)) {
      throw new MethodException("Annotations do not match for '%s' in %s", new Object[] { str, this.detail });
    }
    localObject = paramMethodPart1.getType();
    if (localObject != paramMethodPart2.getType()) {
      throw new MethodException("Method types do not match for %s in %s", new Object[] { str, localObject });
    }
    add(new MethodContact(paramMethodPart1, paramMethodPart2));
  }
  
  private void extend(Class paramClass, DefaultType paramDefaultType)
    throws Exception
  {
    paramClass = this.support.getMethods(paramClass, paramDefaultType).iterator();
    while (paramClass.hasNext()) {
      process((MethodContact)paramClass.next());
    }
  }
  
  private void extract(Detail paramDetail)
    throws Exception
  {
    paramDetail = paramDetail.getMethods().iterator();
    while (paramDetail.hasNext())
    {
      Object localObject = (MethodDetail)paramDetail.next();
      Annotation[] arrayOfAnnotation = ((MethodDetail)localObject).getAnnotations();
      localObject = ((MethodDetail)localObject).getMethod();
      int j = arrayOfAnnotation.length;
      int i = 0;
      while (i < j)
      {
        scan((Method)localObject, arrayOfAnnotation[i], arrayOfAnnotation);
        i += 1;
      }
    }
  }
  
  private void extract(Detail paramDetail, DefaultType paramDefaultType)
    throws Exception
  {
    paramDetail = paramDetail.getMethods();
    if (paramDefaultType == DefaultType.PROPERTY)
    {
      paramDetail = paramDetail.iterator();
      while (paramDetail.hasNext())
      {
        Object localObject = (MethodDetail)paramDetail.next();
        paramDefaultType = ((MethodDetail)localObject).getAnnotations();
        localObject = ((MethodDetail)localObject).getMethod();
        if (this.factory.getType((Method)localObject) != null) {
          process((Method)localObject, paramDefaultType);
        }
      }
    }
  }
  
  private void insert(MethodPart paramMethodPart, PartMap paramPartMap)
  {
    String str = paramMethodPart.getName();
    MethodPart localMethodPart2 = (MethodPart)paramPartMap.remove(str);
    MethodPart localMethodPart1 = paramMethodPart;
    if (localMethodPart2 != null)
    {
      localMethodPart1 = paramMethodPart;
      if (isText(paramMethodPart)) {
        localMethodPart1 = localMethodPart2;
      }
    }
    paramPartMap.put(str, localMethodPart1);
  }
  
  private boolean isText(MethodPart paramMethodPart)
  {
    return (paramMethodPart.getAnnotation() instanceof Text);
  }
  
  private void process(Method paramMethod, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
    throws Exception
  {
    paramMethod = this.factory.getInstance(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    paramAnnotation = paramMethod.getMethodType();
    if (paramAnnotation == MethodType.GET) {
      process(paramMethod, this.read);
    }
    if (paramAnnotation == MethodType.IS) {
      process(paramMethod, this.read);
    }
    if (paramAnnotation == MethodType.SET) {
      process(paramMethod, this.write);
    }
  }
  
  private void process(Method paramMethod, Annotation[] paramArrayOfAnnotation)
    throws Exception
  {
    paramMethod = this.factory.getInstance(paramMethod, paramArrayOfAnnotation);
    paramArrayOfAnnotation = paramMethod.getMethodType();
    if (paramArrayOfAnnotation == MethodType.GET) {
      process(paramMethod, this.read);
    }
    if (paramArrayOfAnnotation == MethodType.IS) {
      process(paramMethod, this.read);
    }
    if (paramArrayOfAnnotation == MethodType.SET) {
      process(paramMethod, this.write);
    }
  }
  
  private void process(MethodContact paramMethodContact)
  {
    MethodPart localMethodPart = paramMethodContact.getRead();
    paramMethodContact = paramMethodContact.getWrite();
    if (paramMethodContact != null) {
      insert(paramMethodContact, this.write);
    }
    insert(localMethodPart, this.read);
  }
  
  private void process(MethodPart paramMethodPart, PartMap paramPartMap)
  {
    String str = paramMethodPart.getName();
    if (str != null) {
      paramPartMap.put(str, paramMethodPart);
    }
  }
  
  private void remove(Method paramMethod, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
    throws Exception
  {
    paramMethod = this.factory.getInstance(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    paramAnnotation = paramMethod.getMethodType();
    if (paramAnnotation == MethodType.GET) {
      remove(paramMethod, this.read);
    }
    if (paramAnnotation == MethodType.IS) {
      remove(paramMethod, this.read);
    }
    if (paramAnnotation == MethodType.SET) {
      remove(paramMethod, this.write);
    }
  }
  
  private void remove(MethodPart paramMethodPart, PartMap paramPartMap)
    throws Exception
  {
    paramMethodPart = paramMethodPart.getName();
    if (paramMethodPart != null) {
      paramPartMap.remove(paramMethodPart);
    }
  }
  
  private void scan(Method paramMethod, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
    throws Exception
  {
    if ((paramAnnotation instanceof Attribute)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementUnion)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementList)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementArray)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof ElementMap)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Element)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Version)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Text)) {
      process(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    if ((paramAnnotation instanceof Transient)) {
      remove(paramMethod, paramAnnotation, paramArrayOfAnnotation);
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
    validate();
  }
  
  private void validate()
    throws Exception
  {
    Iterator localIterator = this.write.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      MethodPart localMethodPart = (MethodPart)this.write.get(str);
      if (localMethodPart != null) {
        validate(localMethodPart, str);
      }
    }
  }
  
  private void validate(MethodPart paramMethodPart, String paramString)
    throws Exception
  {
    paramString = this.read.take(paramString);
    paramMethodPart = paramMethodPart.getMethod();
    if (paramString == null) {
      throw new MethodException("No matching get method for %s in %s", new Object[] { paramMethodPart, this.detail });
    }
  }
  
  private static class PartMap
    extends LinkedHashMap<String, MethodPart>
    implements Iterable<String>
  {
    public Iterator<String> iterator()
    {
      return keySet().iterator();
    }
    
    public MethodPart take(String paramString)
    {
      return (MethodPart)remove(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\MethodScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */