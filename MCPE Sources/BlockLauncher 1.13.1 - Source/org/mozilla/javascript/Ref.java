package org.mozilla.javascript;

import java.io.Serializable;

public abstract class Ref
  implements Serializable
{
  static final long serialVersionUID = 4044540354730911424L;
  
  public boolean delete(Context paramContext)
  {
    return false;
  }
  
  public abstract Object get(Context paramContext);
  
  public boolean has(Context paramContext)
  {
    return true;
  }
  
  @Deprecated
  public abstract Object set(Context paramContext, Object paramObject);
  
  public Object set(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    return set(paramContext, paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Ref.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */