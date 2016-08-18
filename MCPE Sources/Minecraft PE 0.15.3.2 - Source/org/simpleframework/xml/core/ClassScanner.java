package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

class ClassScanner
{
  private Function commit;
  private Function complete;
  private NamespaceDecorator decorator;
  private Order order;
  private Function persist;
  private Function replace;
  private Function resolve;
  private Root root;
  private ConstructorScanner scanner;
  private Support support;
  private Function validate;
  
  public ClassScanner(Detail paramDetail, Support paramSupport)
    throws Exception
  {
    this.scanner = new ConstructorScanner(paramDetail, paramSupport);
    this.decorator = new NamespaceDecorator();
    this.support = paramSupport;
    scan(paramDetail);
  }
  
  private void commit(Method paramMethod)
  {
    if (this.commit == null) {
      this.commit = getFunction(paramMethod);
    }
  }
  
  private void commit(Detail paramDetail)
  {
    paramDetail = paramDetail.getNamespace();
    if (paramDetail != null) {
      this.decorator.set(paramDetail);
    }
  }
  
  private void complete(Method paramMethod)
  {
    if (this.complete == null) {
      this.complete = getFunction(paramMethod);
    }
  }
  
  private void definition(Detail paramDetail)
    throws Exception
  {
    if (this.root == null) {
      this.root = paramDetail.getRoot();
    }
    if (this.order == null) {
      this.order = paramDetail.getOrder();
    }
  }
  
  private Function getFunction(Method paramMethod)
  {
    boolean bool = isContextual(paramMethod);
    if (!paramMethod.isAccessible()) {
      paramMethod.setAccessible(true);
    }
    return new Function(paramMethod, bool);
  }
  
  private boolean isContextual(Method paramMethod)
  {
    boolean bool = false;
    paramMethod = paramMethod.getParameterTypes();
    if (paramMethod.length == 1) {
      bool = Map.class.equals(paramMethod[0]);
    }
    return bool;
  }
  
  private void method(Detail paramDetail)
    throws Exception
  {
    paramDetail = paramDetail.getMethods().iterator();
    while (paramDetail.hasNext()) {
      method((MethodDetail)paramDetail.next());
    }
  }
  
  private void method(MethodDetail paramMethodDetail)
  {
    Annotation[] arrayOfAnnotation = paramMethodDetail.getAnnotations();
    paramMethodDetail = paramMethodDetail.getMethod();
    int j = arrayOfAnnotation.length;
    int i = 0;
    while (i < j)
    {
      Annotation localAnnotation = arrayOfAnnotation[i];
      if ((localAnnotation instanceof Commit)) {
        commit(paramMethodDetail);
      }
      if ((localAnnotation instanceof Validate)) {
        validate(paramMethodDetail);
      }
      if ((localAnnotation instanceof Persist)) {
        persist(paramMethodDetail);
      }
      if ((localAnnotation instanceof Complete)) {
        complete(paramMethodDetail);
      }
      if ((localAnnotation instanceof Replace)) {
        replace(paramMethodDetail);
      }
      if ((localAnnotation instanceof Resolve)) {
        resolve(paramMethodDetail);
      }
      i += 1;
    }
  }
  
  private void namespace(Detail paramDetail)
    throws Exception
  {
    NamespaceList localNamespaceList = paramDetail.getNamespaceList();
    paramDetail = paramDetail.getNamespace();
    if (paramDetail != null) {
      this.decorator.add(paramDetail);
    }
    if (localNamespaceList != null)
    {
      paramDetail = localNamespaceList.value();
      int j = paramDetail.length;
      int i = 0;
      while (i < j)
      {
        localNamespaceList = paramDetail[i];
        this.decorator.add(localNamespaceList);
        i += 1;
      }
    }
  }
  
  private void persist(Method paramMethod)
  {
    if (this.persist == null) {
      this.persist = getFunction(paramMethod);
    }
  }
  
  private void replace(Method paramMethod)
  {
    if (this.replace == null) {
      this.replace = getFunction(paramMethod);
    }
  }
  
  private void resolve(Method paramMethod)
  {
    if (this.resolve == null) {
      this.resolve = getFunction(paramMethod);
    }
  }
  
  private void scan(Detail paramDetail)
    throws Exception
  {
    DefaultType localDefaultType = paramDetail.getOverride();
    for (Object localObject = paramDetail.getType(); localObject != null; localObject = ((Detail)localObject).getSuper())
    {
      localObject = this.support.getDetail((Class)localObject, localDefaultType);
      namespace((Detail)localObject);
      method((Detail)localObject);
      definition((Detail)localObject);
    }
    commit(paramDetail);
  }
  
  private void validate(Method paramMethod)
  {
    if (this.validate == null) {
      this.validate = getFunction(paramMethod);
    }
  }
  
  public Function getCommit()
  {
    return this.commit;
  }
  
  public Function getComplete()
  {
    return this.complete;
  }
  
  public Decorator getDecorator()
  {
    return this.decorator;
  }
  
  public Order getOrder()
  {
    return this.order;
  }
  
  public ParameterMap getParameters()
  {
    return this.scanner.getParameters();
  }
  
  public Function getPersist()
  {
    return this.persist;
  }
  
  public Function getReplace()
  {
    return this.replace;
  }
  
  public Function getResolve()
  {
    return this.resolve;
  }
  
  public Root getRoot()
  {
    return this.root;
  }
  
  public Signature getSignature()
  {
    return this.scanner.getSignature();
  }
  
  public List<Signature> getSignatures()
  {
    return this.scanner.getSignatures();
  }
  
  public Function getValidate()
  {
    return this.validate;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ClassScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */