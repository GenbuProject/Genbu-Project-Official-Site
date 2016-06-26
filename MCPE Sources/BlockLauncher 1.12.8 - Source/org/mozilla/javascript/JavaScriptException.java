package org.mozilla.javascript;

public class JavaScriptException
  extends RhinoException
{
  static final long serialVersionUID = -7666130513694669293L;
  private Object value;
  
  @Deprecated
  public JavaScriptException(Object paramObject)
  {
    this(paramObject, "", 0);
  }
  
  public JavaScriptException(Object paramObject, String paramString, int paramInt)
  {
    recordErrorOrigin(paramString, paramInt, null, 0);
    this.value = paramObject;
    if (((paramObject instanceof NativeError)) && (Context.getContext().hasFeature(10)))
    {
      paramObject = (NativeError)paramObject;
      if (!((NativeError)paramObject).has("fileName", (Scriptable)paramObject)) {
        ((NativeError)paramObject).put("fileName", (Scriptable)paramObject, paramString);
      }
      if (!((NativeError)paramObject).has("lineNumber", (Scriptable)paramObject)) {
        ((NativeError)paramObject).put("lineNumber", (Scriptable)paramObject, Integer.valueOf(paramInt));
      }
      ((NativeError)paramObject).setStackProvider(this);
    }
  }
  
  public String details()
  {
    if (this.value == null) {
      return "null";
    }
    if ((this.value instanceof NativeError)) {
      return this.value.toString();
    }
    try
    {
      String str = ScriptRuntime.toString(this.value);
      return str;
    }
    catch (RuntimeException localRuntimeException)
    {
      if ((this.value instanceof Scriptable)) {
        return ScriptRuntime.defaultObjectToString((Scriptable)this.value);
      }
    }
    return this.value.toString();
  }
  
  @Deprecated
  public int getLineNumber()
  {
    return lineNumber();
  }
  
  @Deprecated
  public String getSourceName()
  {
    return sourceName();
  }
  
  public Object getValue()
  {
    return this.value;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\JavaScriptException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */