package org.simpleframework.xml.convert;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.util.ConcurrentCache;

class ScannerBuilder
  extends ConcurrentCache<Scanner>
{
  public Scanner build(Class<?> paramClass)
  {
    Scanner localScanner = (Scanner)get(paramClass);
    Object localObject = localScanner;
    if (localScanner == null)
    {
      localObject = new Entry(paramClass);
      put(paramClass, localObject);
    }
    return (Scanner)localObject;
  }
  
  private static class Entry
    extends ConcurrentCache<Annotation>
    implements Scanner
  {
    private final Class root;
    
    public Entry(Class paramClass)
    {
      this.root = paramClass;
    }
    
    private <T extends Annotation> T find(Class<T> paramClass)
    {
      for (Class localClass = this.root; localClass != null; localClass = localClass.getSuperclass())
      {
        Annotation localAnnotation = localClass.getAnnotation(paramClass);
        if (localAnnotation != null) {
          return localAnnotation;
        }
      }
      return null;
    }
    
    public <T extends Annotation> T scan(Class<T> paramClass)
    {
      if (!contains(paramClass))
      {
        Annotation localAnnotation = find(paramClass);
        if ((paramClass != null) && (localAnnotation != null)) {
          put(paramClass, localAnnotation);
        }
      }
      return (Annotation)get(paramClass);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\ScannerBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */