package org.simpleframework.xml.core;

class Revision
{
  private boolean equal = true;
  
  public boolean compare(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 != null) {
      this.equal = paramObject2.equals(paramObject1);
    }
    for (;;)
    {
      return this.equal;
      if (paramObject1 != null) {
        this.equal = paramObject1.equals(Double.valueOf(1.0D));
      }
    }
  }
  
  public double getDefault()
  {
    return 1.0D;
  }
  
  public boolean isEqual()
  {
    return this.equal;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Revision.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */