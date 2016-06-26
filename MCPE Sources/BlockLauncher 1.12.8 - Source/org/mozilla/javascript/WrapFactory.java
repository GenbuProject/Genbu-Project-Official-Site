package org.mozilla.javascript;

public class WrapFactory
{
  private boolean javaPrimitiveWrap = true;
  
  public final boolean isJavaPrimitiveWrap()
  {
    return this.javaPrimitiveWrap;
  }
  
  public final void setJavaPrimitiveWrap(boolean paramBoolean)
  {
    Context localContext = Context.getCurrentContext();
    if ((localContext != null) && (localContext.isSealed())) {
      Context.onSealedMutation();
    }
    this.javaPrimitiveWrap = paramBoolean;
  }
  
  public Object wrap(Context paramContext, Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    if ((paramObject == null) || (paramObject == Undefined.instance) || ((paramObject instanceof Scriptable))) {}
    do
    {
      do
      {
        return paramObject;
        if ((paramClass == null) || (!paramClass.isPrimitive())) {
          break;
        }
        if (paramClass == Void.TYPE) {
          return Undefined.instance;
        }
      } while (paramClass != Character.TYPE);
      return Integer.valueOf(((Character)paramObject).charValue());
      if (isJavaPrimitiveWrap()) {
        break;
      }
    } while (((paramObject instanceof String)) || ((paramObject instanceof Number)) || ((paramObject instanceof Boolean)));
    if ((paramObject instanceof Character)) {
      return String.valueOf(((Character)paramObject).charValue());
    }
    if (paramObject.getClass().isArray()) {
      return NativeJavaArray.wrap(paramScriptable, paramObject);
    }
    return wrapAsJavaObject(paramContext, paramScriptable, paramObject, paramClass);
  }
  
  public Scriptable wrapAsJavaObject(Context paramContext, Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    return new NativeJavaObject(paramScriptable, paramObject, paramClass);
  }
  
  public Scriptable wrapJavaClass(Context paramContext, Scriptable paramScriptable, Class<?> paramClass)
  {
    return new NativeJavaClass(paramScriptable, paramClass);
  }
  
  public Scriptable wrapNewObject(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    if ((paramObject instanceof Scriptable)) {
      return (Scriptable)paramObject;
    }
    if (paramObject.getClass().isArray()) {
      return NativeJavaArray.wrap(paramScriptable, paramObject);
    }
    return wrapAsJavaObject(paramContext, paramScriptable, paramObject, null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\WrapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */