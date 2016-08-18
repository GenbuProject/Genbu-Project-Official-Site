package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class AnnotationHandler
  implements InvocationHandler
{
  private static final String ATTRIBUTE = "attribute";
  private static final String CLASS = "annotationType";
  private static final String EQUAL = "equals";
  private static final String REQUIRED = "required";
  private static final String STRING = "toString";
  private final boolean attribute;
  private final Comparer comparer = new Comparer();
  private final boolean required;
  private final Class type;
  
  public AnnotationHandler(Class paramClass)
  {
    this(paramClass, true);
  }
  
  public AnnotationHandler(Class paramClass, boolean paramBoolean)
  {
    this(paramClass, paramBoolean, false);
  }
  
  public AnnotationHandler(Class paramClass, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.attribute = paramBoolean2;
    this.required = paramBoolean1;
    this.type = paramClass;
  }
  
  private void attributes(StringBuilder paramStringBuilder)
  {
    Method[] arrayOfMethod = this.type.getDeclaredMethods();
    int i = 0;
    while (i < arrayOfMethod.length)
    {
      String str = arrayOfMethod[i].getName();
      Object localObject = value(arrayOfMethod[i]);
      if (i > 0)
      {
        paramStringBuilder.append(',');
        paramStringBuilder.append(' ');
      }
      paramStringBuilder.append(str);
      paramStringBuilder.append('=');
      paramStringBuilder.append(localObject);
      i += 1;
    }
    paramStringBuilder.append(')');
  }
  
  private boolean equals(Object paramObject, Object[] paramArrayOfObject)
    throws Throwable
  {
    paramObject = (Annotation)paramObject;
    paramArrayOfObject = (Annotation)paramArrayOfObject[0];
    if (((Annotation)paramObject).annotationType() != paramArrayOfObject.annotationType()) {
      throw new PersistenceException("Annotation %s is not the same as %s", new Object[] { paramObject, paramArrayOfObject });
    }
    return this.comparer.equals((Annotation)paramObject, paramArrayOfObject);
  }
  
  private void name(StringBuilder paramStringBuilder)
  {
    String str = this.type.getName();
    if (str != null)
    {
      paramStringBuilder.append('@');
      paramStringBuilder.append(str);
      paramStringBuilder.append('(');
    }
  }
  
  private Object value(Method paramMethod)
  {
    String str = paramMethod.getName();
    if (str.equals("required")) {
      return Boolean.valueOf(this.required);
    }
    if (str.equals("attribute")) {
      return Boolean.valueOf(this.attribute);
    }
    return paramMethod.getDefaultValue();
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    String str = paramMethod.getName();
    if (str.equals("toString")) {
      return toString();
    }
    if (str.equals("equals")) {
      return Boolean.valueOf(equals(paramObject, paramArrayOfObject));
    }
    if (str.equals("annotationType")) {
      return this.type;
    }
    if (str.equals("required")) {
      return Boolean.valueOf(this.required);
    }
    if (str.equals("attribute")) {
      return Boolean.valueOf(this.attribute);
    }
    return paramMethod.getDefaultValue();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.type != null)
    {
      name(localStringBuilder);
      attributes(localStringBuilder);
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\AnnotationHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */