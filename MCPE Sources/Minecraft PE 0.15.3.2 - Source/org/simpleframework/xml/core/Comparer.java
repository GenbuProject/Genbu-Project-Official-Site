package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class Comparer
{
  private static final String NAME = "name";
  private final String[] ignore;
  
  public Comparer()
  {
    this(new String[] { "name" });
  }
  
  public Comparer(String... paramVarArgs)
  {
    this.ignore = paramVarArgs;
  }
  
  private boolean isIgnore(Method paramMethod)
  {
    paramMethod = paramMethod.getName();
    if (this.ignore != null)
    {
      String[] arrayOfString = this.ignore;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (paramMethod.equals(arrayOfString[i])) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public boolean equals(Annotation paramAnnotation1, Annotation paramAnnotation2)
    throws Exception
  {
    Class localClass1 = paramAnnotation1.annotationType();
    Class localClass2 = paramAnnotation2.annotationType();
    Method[] arrayOfMethod = localClass1.getDeclaredMethods();
    int j;
    int i;
    if (localClass1.equals(localClass2))
    {
      j = arrayOfMethod.length;
      i = 0;
    }
    while (i < j)
    {
      localClass1 = arrayOfMethod[i];
      if ((!isIgnore(localClass1)) && (!localClass1.invoke(paramAnnotation1, new Object[0]).equals(localClass1.invoke(paramAnnotation2, new Object[0])))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Comparer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */