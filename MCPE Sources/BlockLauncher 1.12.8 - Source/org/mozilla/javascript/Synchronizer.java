package org.mozilla.javascript;

public class Synchronizer
  extends Delegator
{
  private Object syncObject;
  
  public Synchronizer(Scriptable paramScriptable)
  {
    super(paramScriptable);
  }
  
  public Synchronizer(Scriptable paramScriptable, Object paramObject)
  {
    super(paramScriptable);
    this.syncObject = paramObject;
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (this.syncObject != null)
    {
      ??? = this.syncObject;
      if (!(??? instanceof Wrapper)) {
        break label71;
      }
    }
    label71:
    for (;;)
    {
      synchronized (((Wrapper)???).unwrap())
      {
        paramContext = ((Function)this.obj).call(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
        return paramContext;
      }
      ??? = paramScriptable2;
      break;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\Synchronizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */