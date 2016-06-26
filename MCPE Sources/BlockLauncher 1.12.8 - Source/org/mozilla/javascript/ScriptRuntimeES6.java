package org.mozilla.javascript;

public class ScriptRuntimeES6
{
  public static Scriptable requireObjectCoercible(Context paramContext, Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject)
  {
    if ((paramScriptable == null) || (Undefined.isUndefined(paramScriptable))) {
      throw ScriptRuntime.typeError2("msg.called.null.or.undefined", paramIdFunctionObject.getTag(), paramIdFunctionObject.getFunctionName());
    }
    return paramScriptable;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ScriptRuntimeES6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */