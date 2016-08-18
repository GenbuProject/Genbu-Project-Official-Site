package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

class DetailScanner
  implements Detail
{
  private DefaultType access;
  private NamespaceList declaration;
  private List<FieldDetail> fields = new LinkedList();
  private Annotation[] labels;
  private List<MethodDetail> methods = new LinkedList();
  private String name;
  private Namespace namespace;
  private Order order;
  private DefaultType override;
  private boolean required;
  private Root root;
  private boolean strict;
  private Class type;
  
  public DetailScanner(Class paramClass)
  {
    this(paramClass, null);
  }
  
  public DetailScanner(Class paramClass, DefaultType paramDefaultType)
  {
    this.labels = paramClass.getDeclaredAnnotations();
    this.override = paramDefaultType;
    this.strict = true;
    this.type = paramClass;
    scan(paramClass);
  }
  
  private void access(Annotation paramAnnotation)
  {
    if (paramAnnotation != null)
    {
      paramAnnotation = (Default)paramAnnotation;
      this.required = paramAnnotation.required();
      this.access = paramAnnotation.value();
    }
  }
  
  private void extract(Class paramClass)
  {
    paramClass = this.labels;
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Annotation localAnnotation = paramClass[i];
      if ((localAnnotation instanceof Namespace)) {
        namespace(localAnnotation);
      }
      if ((localAnnotation instanceof NamespaceList)) {
        scope(localAnnotation);
      }
      if ((localAnnotation instanceof Root)) {
        root(localAnnotation);
      }
      if ((localAnnotation instanceof Order)) {
        order(localAnnotation);
      }
      if ((localAnnotation instanceof Default)) {
        access(localAnnotation);
      }
      i += 1;
    }
  }
  
  private void fields(Class paramClass)
  {
    paramClass = paramClass.getDeclaredFields();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      FieldDetail localFieldDetail = new FieldDetail(paramClass[i]);
      this.fields.add(localFieldDetail);
      i += 1;
    }
  }
  
  private boolean isEmpty(String paramString)
  {
    return paramString.length() == 0;
  }
  
  private void methods(Class paramClass)
  {
    paramClass = paramClass.getDeclaredMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      MethodDetail localMethodDetail = new MethodDetail(paramClass[i]);
      this.methods.add(localMethodDetail);
      i += 1;
    }
  }
  
  private void namespace(Annotation paramAnnotation)
  {
    if (paramAnnotation != null) {
      this.namespace = ((Namespace)paramAnnotation);
    }
  }
  
  private void order(Annotation paramAnnotation)
  {
    if (paramAnnotation != null) {
      this.order = ((Order)paramAnnotation);
    }
  }
  
  private void root(Annotation paramAnnotation)
  {
    if (paramAnnotation != null)
    {
      Root localRoot = (Root)paramAnnotation;
      String str2 = this.type.getSimpleName();
      if (localRoot != null)
      {
        String str1 = localRoot.name();
        paramAnnotation = str1;
        if (isEmpty(str1)) {
          paramAnnotation = Reflector.getName(str2);
        }
        this.strict = localRoot.strict();
        this.root = localRoot;
        this.name = paramAnnotation;
      }
    }
  }
  
  private void scan(Class paramClass)
  {
    methods(paramClass);
    fields(paramClass);
    extract(paramClass);
  }
  
  private void scope(Annotation paramAnnotation)
  {
    if (paramAnnotation != null) {
      this.declaration = ((NamespaceList)paramAnnotation);
    }
  }
  
  public DefaultType getAccess()
  {
    if (this.override != null) {
      return this.override;
    }
    return this.access;
  }
  
  public Annotation[] getAnnotations()
  {
    return this.labels;
  }
  
  public Constructor[] getConstructors()
  {
    return this.type.getDeclaredConstructors();
  }
  
  public List<FieldDetail> getFields()
  {
    return this.fields;
  }
  
  public List<MethodDetail> getMethods()
  {
    return this.methods;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Namespace getNamespace()
  {
    return this.namespace;
  }
  
  public NamespaceList getNamespaceList()
  {
    return this.declaration;
  }
  
  public Order getOrder()
  {
    return this.order;
  }
  
  public DefaultType getOverride()
  {
    return this.override;
  }
  
  public Root getRoot()
  {
    return this.root;
  }
  
  public Class getSuper()
  {
    Class localClass2 = this.type.getSuperclass();
    Class localClass1 = localClass2;
    if (localClass2 == Object.class) {
      localClass1 = null;
    }
    return localClass1;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public boolean isInstantiable()
  {
    if (Modifier.isStatic(this.type.getModifiers())) {}
    while (!this.type.isMemberClass()) {
      return true;
    }
    return false;
  }
  
  public boolean isPrimitive()
  {
    return this.type.isPrimitive();
  }
  
  public boolean isRequired()
  {
    return this.required;
  }
  
  public boolean isStrict()
  {
    return this.strict;
  }
  
  public String toString()
  {
    return this.type.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\DetailScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */