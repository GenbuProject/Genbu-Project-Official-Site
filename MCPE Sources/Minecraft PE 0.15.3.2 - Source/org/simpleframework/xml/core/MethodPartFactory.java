package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodPartFactory
{
  private final AnnotationFactory factory;
  
  public MethodPartFactory(Detail paramDetail, Support paramSupport)
  {
    this.factory = new AnnotationFactory(paramDetail, paramSupport);
  }
  
  private Annotation getAnnotation(Method paramMethod)
    throws Exception
  {
    Class[] arrayOfClass = getDependents(paramMethod);
    paramMethod = getType(paramMethod);
    if (paramMethod != null) {
      return this.factory.getInstance(paramMethod, arrayOfClass);
    }
    return null;
  }
  
  private Class[] getDependents(Method paramMethod)
    throws Exception
  {
    MethodType localMethodType = getMethodType(paramMethod);
    if (localMethodType == MethodType.SET) {
      return Reflector.getParameterDependents(paramMethod, 0);
    }
    if (localMethodType == MethodType.GET) {
      return Reflector.getReturnDependents(paramMethod);
    }
    if (localMethodType == MethodType.IS) {
      return Reflector.getReturnDependents(paramMethod);
    }
    return null;
  }
  
  private MethodType getMethodType(Method paramMethod)
  {
    paramMethod = paramMethod.getName();
    if (paramMethod.startsWith("get")) {
      return MethodType.GET;
    }
    if (paramMethod.startsWith("is")) {
      return MethodType.IS;
    }
    if (paramMethod.startsWith("set")) {
      return MethodType.SET;
    }
    return MethodType.NONE;
  }
  
  private MethodName getName(Method paramMethod, Annotation paramAnnotation)
    throws Exception
  {
    MethodType localMethodType = getMethodType(paramMethod);
    if (localMethodType == MethodType.GET) {
      return getRead(paramMethod, localMethodType);
    }
    if (localMethodType == MethodType.IS) {
      return getRead(paramMethod, localMethodType);
    }
    if (localMethodType == MethodType.SET) {
      return getWrite(paramMethod, localMethodType);
    }
    throw new MethodException("Annotation %s must mark a set or get method", new Object[] { paramAnnotation });
  }
  
  private Class getParameterType(Method paramMethod)
    throws Exception
  {
    if (paramMethod.getParameterTypes().length == 1) {
      return paramMethod.getParameterTypes()[0];
    }
    return null;
  }
  
  private MethodName getRead(Method paramMethod, MethodType paramMethodType)
    throws Exception
  {
    Object localObject = paramMethod.getParameterTypes();
    String str = paramMethod.getName();
    if (localObject.length != 0) {
      throw new MethodException("Get method %s is not a valid property", new Object[] { paramMethod });
    }
    localObject = getTypeName(str, paramMethodType);
    if (localObject == null) {
      throw new MethodException("Could not get name for %s", new Object[] { paramMethod });
    }
    return new MethodName(paramMethod, paramMethodType, (String)localObject);
  }
  
  private Class getReturnType(Method paramMethod)
    throws Exception
  {
    if (paramMethod.getParameterTypes().length == 0) {
      return paramMethod.getReturnType();
    }
    return null;
  }
  
  private String getTypeName(String paramString, MethodType paramMethodType)
  {
    int i = paramMethodType.getPrefix();
    int j = paramString.length();
    paramMethodType = paramString;
    if (j > i) {
      paramMethodType = paramString.substring(i, j);
    }
    return Reflector.getName(paramMethodType);
  }
  
  private MethodName getWrite(Method paramMethod, MethodType paramMethodType)
    throws Exception
  {
    Object localObject = paramMethod.getParameterTypes();
    String str = paramMethod.getName();
    if (localObject.length != 1) {
      throw new MethodException("Set method %s is not a valid property", new Object[] { paramMethod });
    }
    localObject = getTypeName(str, paramMethodType);
    if (localObject == null) {
      throw new MethodException("Could not get name for %s", new Object[] { paramMethod });
    }
    return new MethodName(paramMethod, paramMethodType, (String)localObject);
  }
  
  public MethodPart getInstance(Method paramMethod, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
    throws Exception
  {
    paramMethod = getName(paramMethod, paramAnnotation);
    if (paramMethod.getType() == MethodType.SET) {
      return new SetPart(paramMethod, paramAnnotation, paramArrayOfAnnotation);
    }
    return new GetPart(paramMethod, paramAnnotation, paramArrayOfAnnotation);
  }
  
  public MethodPart getInstance(Method paramMethod, Annotation[] paramArrayOfAnnotation)
    throws Exception
  {
    Annotation localAnnotation = getAnnotation(paramMethod);
    if (localAnnotation != null) {
      return getInstance(paramMethod, localAnnotation, paramArrayOfAnnotation);
    }
    return null;
  }
  
  public Class getType(Method paramMethod)
    throws Exception
  {
    MethodType localMethodType = getMethodType(paramMethod);
    if (localMethodType == MethodType.SET) {
      return getParameterType(paramMethod);
    }
    if (localMethodType == MethodType.GET) {
      return getReturnType(paramMethod);
    }
    if (localMethodType == MethodType.IS) {
      return getReturnType(paramMethod);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\MethodPartFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */