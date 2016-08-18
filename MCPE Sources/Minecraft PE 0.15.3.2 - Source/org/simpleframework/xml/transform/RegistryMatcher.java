package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

public class RegistryMatcher
  implements Matcher
{
  private final Cache<Transform> transforms = new ConcurrentCache();
  private final Cache<Class> types = new ConcurrentCache();
  
  private Transform create(Class paramClass)
    throws Exception
  {
    Class localClass = (Class)this.types.fetch(paramClass);
    if (localClass != null) {
      return create(paramClass, localClass);
    }
    return null;
  }
  
  private Transform create(Class paramClass1, Class paramClass2)
    throws Exception
  {
    paramClass2 = (Transform)paramClass2.newInstance();
    if (paramClass2 != null) {
      this.transforms.cache(paramClass1, paramClass2);
    }
    return paramClass2;
  }
  
  public void bind(Class paramClass1, Class paramClass2)
  {
    this.types.cache(paramClass1, paramClass2);
  }
  
  public void bind(Class paramClass, Transform paramTransform)
  {
    this.transforms.cache(paramClass, paramTransform);
  }
  
  public Transform match(Class paramClass)
    throws Exception
  {
    Transform localTransform2 = (Transform)this.transforms.fetch(paramClass);
    Transform localTransform1 = localTransform2;
    if (localTransform2 == null) {
      localTransform1 = create(paramClass);
    }
    return localTransform1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\RegistryMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */