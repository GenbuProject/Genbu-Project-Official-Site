package org.mozilla.javascript;

import java.io.Serializable;

public final class UniqueTag
  implements Serializable
{
  public static final UniqueTag DOUBLE_MARK = new UniqueTag(3);
  private static final int ID_DOUBLE_MARK = 3;
  private static final int ID_NOT_FOUND = 1;
  private static final int ID_NULL_VALUE = 2;
  public static final UniqueTag NOT_FOUND = new UniqueTag(1);
  public static final UniqueTag NULL_VALUE = new UniqueTag(2);
  static final long serialVersionUID = -4320556826714577259L;
  private final int tagId;
  
  private UniqueTag(int paramInt)
  {
    this.tagId = paramInt;
  }
  
  public Object readResolve()
  {
    switch (this.tagId)
    {
    default: 
      throw new IllegalStateException(String.valueOf(this.tagId));
    case 1: 
      return NOT_FOUND;
    case 2: 
      return NULL_VALUE;
    }
    return DOUBLE_MARK;
  }
  
  public String toString()
  {
    String str;
    switch (this.tagId)
    {
    default: 
      throw Kit.codeBug();
    case 1: 
      str = "NOT_FOUND";
    }
    for (;;)
    {
      return super.toString() + ": " + str;
      str = "NULL_VALUE";
      continue;
      str = "DOUBLE_MARK";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\UniqueTag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */