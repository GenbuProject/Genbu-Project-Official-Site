package org.simpleframework.xml.transform;

class ArrayMatcher
  implements Matcher
{
  private final Matcher primary;
  
  public ArrayMatcher(Matcher paramMatcher)
  {
    this.primary = paramMatcher;
  }
  
  private Transform matchArray(Class paramClass)
    throws Exception
  {
    Transform localTransform = this.primary.match(paramClass);
    if (localTransform == null) {
      return null;
    }
    return new ArrayTransform(localTransform, paramClass);
  }
  
  public Transform match(Class paramClass)
    throws Exception
  {
    paramClass = paramClass.getComponentType();
    if (paramClass == Character.TYPE) {
      return new CharacterArrayTransform(paramClass);
    }
    if (paramClass == Character.class) {
      return new CharacterArrayTransform(paramClass);
    }
    if (paramClass == String.class) {
      return new StringArrayTransform();
    }
    return matchArray(paramClass);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\ArrayMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */