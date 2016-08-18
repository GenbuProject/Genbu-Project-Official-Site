package org.simpleframework.xml.transform;

class DefaultMatcher
  implements Matcher
{
  private Matcher array = new ArrayMatcher(this);
  private Matcher matcher;
  private Matcher primitive = new PrimitiveMatcher();
  private Matcher stock = new PackageMatcher();
  
  public DefaultMatcher(Matcher paramMatcher)
  {
    this.matcher = paramMatcher;
  }
  
  private Transform matchType(Class paramClass)
    throws Exception
  {
    if (paramClass.isArray()) {
      return this.array.match(paramClass);
    }
    if (paramClass.isPrimitive()) {
      return this.primitive.match(paramClass);
    }
    return this.stock.match(paramClass);
  }
  
  public Transform match(Class paramClass)
    throws Exception
  {
    Transform localTransform = this.matcher.match(paramClass);
    if (localTransform != null) {
      return localTransform;
    }
    return matchType(paramClass);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\DefaultMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */