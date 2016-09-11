package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

final class InterpretedFunction
  extends NativeFunction
  implements Script
{
  static final long serialVersionUID = 541475680333911468L;
  InterpreterData idata;
  SecurityController securityController;
  Object securityDomain;
  
  private InterpretedFunction(InterpretedFunction paramInterpretedFunction, int paramInt)
  {
    this.idata = paramInterpretedFunction.idata.itsNestedFunctions[paramInt];
    this.securityController = paramInterpretedFunction.securityController;
    this.securityDomain = paramInterpretedFunction.securityDomain;
  }
  
  private InterpretedFunction(InterpreterData paramInterpreterData, Object paramObject)
  {
    this.idata = paramInterpreterData;
    SecurityController localSecurityController = Context.getContext().getSecurityController();
    if (localSecurityController != null) {}
    for (paramInterpreterData = localSecurityController.getDynamicSecurityDomain(paramObject);; paramInterpreterData = null)
    {
      this.securityController = localSecurityController;
      this.securityDomain = paramInterpreterData;
      return;
      if (paramObject != null) {
        throw new IllegalArgumentException();
      }
    }
  }
  
  static InterpretedFunction createFunction(Context paramContext, Scriptable paramScriptable, InterpretedFunction paramInterpretedFunction, int paramInt)
  {
    paramInterpretedFunction = new InterpretedFunction(paramInterpretedFunction, paramInt);
    paramInterpretedFunction.initScriptFunction(paramContext, paramScriptable);
    return paramInterpretedFunction;
  }
  
  static InterpretedFunction createFunction(Context paramContext, Scriptable paramScriptable, InterpreterData paramInterpreterData, Object paramObject)
  {
    paramInterpreterData = new InterpretedFunction(paramInterpreterData, paramObject);
    paramInterpreterData.initScriptFunction(paramContext, paramScriptable);
    return paramInterpreterData;
  }
  
  static InterpretedFunction createScript(InterpreterData paramInterpreterData, Object paramObject)
  {
    return new InterpretedFunction(paramInterpreterData, paramObject);
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!ScriptRuntime.hasTopCall(paramContext)) {
      return ScriptRuntime.doTopCall(this, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject, this.idata.isStrict);
    }
    return Interpreter.interpret(this, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
  }
  
  public Object exec(Context paramContext, Scriptable paramScriptable)
  {
    if (!isScript()) {
      throw new IllegalStateException();
    }
    if (!ScriptRuntime.hasTopCall(paramContext)) {
      return ScriptRuntime.doTopCall(this, paramContext, paramScriptable, paramScriptable, ScriptRuntime.emptyArgs, this.idata.isStrict);
    }
    return Interpreter.interpret(this, paramContext, paramScriptable, paramScriptable, ScriptRuntime.emptyArgs);
  }
  
  public DebuggableScript getDebuggableView()
  {
    return this.idata;
  }
  
  public String getEncodedSource()
  {
    return Interpreter.getEncodedSource(this.idata);
  }
  
  public String getFunctionName()
  {
    if (this.idata.itsName == null) {
      return "";
    }
    return this.idata.itsName;
  }
  
  protected int getLanguageVersion()
  {
    return this.idata.languageVersion;
  }
  
  protected int getParamAndVarCount()
  {
    return this.idata.argNames.length;
  }
  
  protected int getParamCount()
  {
    return this.idata.argCount;
  }
  
  protected boolean getParamOrVarConst(int paramInt)
  {
    return this.idata.argIsConst[paramInt];
  }
  
  protected String getParamOrVarName(int paramInt)
  {
    return this.idata.argNames[paramInt];
  }
  
  public boolean isScript()
  {
    return this.idata.itsFunctionType == 0;
  }
  
  public Object resumeGenerator(Context paramContext, Scriptable paramScriptable, int paramInt, Object paramObject1, Object paramObject2)
  {
    return Interpreter.resumeGenerator(paramContext, paramScriptable, paramInt, paramObject1, paramObject2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\InterpretedFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */