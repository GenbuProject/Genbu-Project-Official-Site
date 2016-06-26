package org.mozilla.javascript;

public class RhinoSecurityManager
  extends SecurityManager
{
  protected Class<?> getCurrentScriptClass()
  {
    Class[] arrayOfClass = getClassContext();
    int j = arrayOfClass.length;
    int i = 0;
    while (i < j)
    {
      Class localClass = arrayOfClass[i];
      if (((localClass != InterpretedFunction.class) && (NativeFunction.class.isAssignableFrom(localClass))) || (PolicySecurityController.SecureCaller.class.isAssignableFrom(localClass))) {
        return localClass;
      }
      i += 1;
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\RhinoSecurityManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */