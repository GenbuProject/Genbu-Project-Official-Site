package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

public abstract class NativeFunction
  extends BaseFunction
{
  static final long serialVersionUID = 8713897114082216401L;
  
  final String decompile(int paramInt1, int paramInt2)
  {
    String str = getEncodedSource();
    if (str == null) {
      return super.decompile(paramInt1, paramInt2);
    }
    UintMap localUintMap = new UintMap(1);
    localUintMap.put(1, paramInt1);
    return Decompiler.decompile(str, paramInt2, localUintMap);
  }
  
  public int getArity()
  {
    return getParamCount();
  }
  
  public DebuggableScript getDebuggableView()
  {
    return null;
  }
  
  public String getEncodedSource()
  {
    return null;
  }
  
  protected abstract int getLanguageVersion();
  
  public int getLength()
  {
    int i = getParamCount();
    if (getLanguageVersion() != 120) {}
    NativeCall localNativeCall;
    do
    {
      return i;
      localNativeCall = ScriptRuntime.findFunctionActivation(Context.getContext(), this);
    } while (localNativeCall == null);
    return localNativeCall.originalArgs.length;
  }
  
  protected abstract int getParamAndVarCount();
  
  protected abstract int getParamCount();
  
  protected boolean getParamOrVarConst(int paramInt)
  {
    return false;
  }
  
  protected abstract String getParamOrVarName(int paramInt);
  
  public final void initScriptFunction(Context paramContext, Scriptable paramScriptable)
  {
    ScriptRuntime.setFunctionProtoAndParent(this, paramScriptable);
  }
  
  @Deprecated
  public String jsGet_name()
  {
    return getFunctionName();
  }
  
  public Object resumeGenerator(Context paramContext, Scriptable paramScriptable, int paramInt, Object paramObject1, Object paramObject2)
  {
    throw new EvaluatorException("resumeGenerator() not implemented");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */