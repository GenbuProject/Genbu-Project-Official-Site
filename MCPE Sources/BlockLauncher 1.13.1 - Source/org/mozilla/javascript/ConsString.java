package org.mozilla.javascript;

import java.io.Serializable;
import java.util.ArrayList;

public class ConsString
  implements CharSequence, Serializable
{
  private static final long serialVersionUID = -8432806714471372570L;
  private int depth;
  private final int length;
  private CharSequence s1;
  private CharSequence s2;
  
  public ConsString(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    this.s1 = paramCharSequence1;
    this.s2 = paramCharSequence2;
    this.length = (paramCharSequence1.length() + paramCharSequence2.length());
    this.depth = 1;
    if ((paramCharSequence1 instanceof ConsString)) {
      this.depth += ((ConsString)paramCharSequence1).depth;
    }
    if ((paramCharSequence2 instanceof ConsString)) {
      this.depth += ((ConsString)paramCharSequence2).depth;
    }
  }
  
  private String flatten()
  {
    for (;;)
    {
      try
      {
        if (this.depth <= 0) {
          break label134;
        }
        StringBuilder localStringBuilder = new StringBuilder(this.length);
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(this.s2);
        localArrayList.add(this.s1);
        if (localArrayList.isEmpty()) {
          break;
        }
        Object localObject2 = (CharSequence)localArrayList.remove(localArrayList.size() - 1);
        if ((localObject2 instanceof ConsString))
        {
          localObject2 = (ConsString)localObject2;
          localArrayList.add(((ConsString)localObject2).s2);
          localArrayList.add(((ConsString)localObject2).s1);
        }
        else
        {
          ((StringBuilder)localObject1).append((CharSequence)localObject2);
        }
      }
      finally {}
    }
    this.s1 = ((StringBuilder)localObject1).toString();
    this.s2 = "";
    this.depth = 0;
    label134:
    String str = (String)this.s1;
    return str;
  }
  
  private Object writeReplace()
  {
    return toString();
  }
  
  public char charAt(int paramInt)
  {
    if (this.depth == 0) {}
    for (String str = (String)this.s1;; str = flatten()) {
      return str.charAt(paramInt);
    }
  }
  
  public int length()
  {
    return this.length;
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    if (this.depth == 0) {}
    for (String str = (String)this.s1;; str = flatten()) {
      return str.substring(paramInt1, paramInt2);
    }
  }
  
  public String toString()
  {
    if (this.depth == 0) {
      return (String)this.s1;
    }
    return flatten();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ConsString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */