package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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

class SignatureScanner
{
  private final SignatureBuilder builder;
  private final Constructor constructor;
  private final ParameterFactory factory;
  private final ParameterMap registry;
  private final Class type;
  
  public SignatureScanner(Constructor paramConstructor, ParameterMap paramParameterMap, Support paramSupport)
    throws Exception
  {
    this.builder = new SignatureBuilder(paramConstructor);
    this.factory = new ParameterFactory(paramSupport);
    this.type = paramConstructor.getDeclaringClass();
    this.constructor = paramConstructor;
    this.registry = paramParameterMap;
    scan(this.type);
  }
  
  private List<Parameter> create(Annotation paramAnnotation, int paramInt)
    throws Exception
  {
    paramAnnotation = this.factory.getInstance(this.constructor, paramAnnotation, paramInt);
    if (paramAnnotation != null) {
      register(paramAnnotation);
    }
    return Collections.singletonList(paramAnnotation);
  }
  
  private Annotation[] extract(Annotation paramAnnotation)
    throws Exception
  {
    Method[] arrayOfMethod = paramAnnotation.annotationType().getDeclaredMethods();
    if (arrayOfMethod.length != 1) {
      throw new UnionException("Annotation '%s' is not a valid union for %s", new Object[] { paramAnnotation, this.type });
    }
    return (Annotation[])arrayOfMethod[0].invoke(paramAnnotation, new Object[0]);
  }
  
  private List<Parameter> process(Annotation paramAnnotation, int paramInt)
    throws Exception
  {
    if ((paramAnnotation instanceof Attribute)) {
      return create(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof Element)) {
      return create(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof ElementList)) {
      return create(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof ElementArray)) {
      return create(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof ElementMap)) {
      return create(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof ElementListUnion)) {
      return union(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof ElementMapUnion)) {
      return union(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof ElementUnion)) {
      return union(paramAnnotation, paramInt);
    }
    if ((paramAnnotation instanceof Text)) {
      return create(paramAnnotation, paramInt);
    }
    return Collections.emptyList();
  }
  
  private void register(Parameter paramParameter)
    throws Exception
  {
    String str = paramParameter.getPath();
    Object localObject = paramParameter.getKey();
    if (this.registry.containsKey(localObject)) {
      validate(paramParameter, localObject);
    }
    if (this.registry.containsKey(str)) {
      validate(paramParameter, str);
    }
    this.registry.put(str, paramParameter);
    this.registry.put(localObject, paramParameter);
  }
  
  private void scan(Class paramClass)
    throws Exception
  {
    paramClass = this.constructor.getParameterTypes();
    int i = 0;
    while (i < paramClass.length)
    {
      scan(paramClass[i], i);
      i += 1;
    }
  }
  
  private void scan(Class paramClass, int paramInt)
    throws Exception
  {
    paramClass = this.constructor.getParameterAnnotations();
    int i = 0;
    while (i < paramClass[paramInt].length)
    {
      Iterator localIterator = process(paramClass[paramInt][i], paramInt).iterator();
      while (localIterator.hasNext())
      {
        Parameter localParameter = (Parameter)localIterator.next();
        this.builder.insert(localParameter, paramInt);
      }
      i += 1;
    }
  }
  
  private List<Parameter> union(Annotation paramAnnotation, int paramInt)
    throws Exception
  {
    Signature localSignature = new Signature(this.constructor);
    Annotation[] arrayOfAnnotation = extract(paramAnnotation);
    int j = arrayOfAnnotation.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfAnnotation[i];
      localObject = this.factory.getInstance(this.constructor, paramAnnotation, (Annotation)localObject, paramInt);
      String str = ((Parameter)localObject).getPath();
      if (localSignature.contains(str)) {
        throw new UnionException("Annotation name '%s' used more than once in %s for %s", new Object[] { str, paramAnnotation, this.type });
      }
      localSignature.set(str, (Parameter)localObject);
      register((Parameter)localObject);
      i += 1;
    }
    return localSignature.getAll();
  }
  
  private void validate(Parameter paramParameter, Object paramObject)
    throws Exception
  {
    paramObject = (Parameter)this.registry.get(paramObject);
    if (paramParameter.isText() != ((Parameter)paramObject).isText())
    {
      Annotation localAnnotation1 = paramParameter.getAnnotation();
      Annotation localAnnotation2 = ((Parameter)paramObject).getAnnotation();
      String str = paramParameter.getPath();
      if (!localAnnotation1.equals(localAnnotation2)) {
        throw new ConstructorException("Annotations do not match for '%s' in %s", new Object[] { str, this.type });
      }
      if (((Parameter)paramObject).getType() != paramParameter.getType()) {
        throw new ConstructorException("Parameter types do not match for '%s' in %s", new Object[] { str, this.type });
      }
    }
  }
  
  public List<Signature> getSignatures()
    throws Exception
  {
    return this.builder.build();
  }
  
  public boolean isValid()
  {
    return this.builder.isValid();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\SignatureScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */