package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

class LabelKey
{
  private final Class label;
  private final String name;
  private final Class owner;
  private final Class type;
  
  public LabelKey(Contact paramContact, Annotation paramAnnotation)
  {
    this.owner = paramContact.getDeclaringClass();
    this.label = paramAnnotation.annotationType();
    this.name = paramContact.getName();
    this.type = paramContact.getType();
  }
  
  private boolean equals(LabelKey paramLabelKey)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramLabelKey == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramLabelKey.label != this.label);
        bool1 = bool2;
      } while (paramLabelKey.owner != this.owner);
      bool1 = bool2;
    } while (paramLabelKey.type != this.type);
    return paramLabelKey.name.equals(this.name);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof LabelKey)) {
      return equals((LabelKey)paramObject);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.name.hashCode() ^ this.owner.hashCode();
  }
  
  public String toString()
  {
    return String.format("key '%s' for %s", new Object[] { this.name, this.owner });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\LabelKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */