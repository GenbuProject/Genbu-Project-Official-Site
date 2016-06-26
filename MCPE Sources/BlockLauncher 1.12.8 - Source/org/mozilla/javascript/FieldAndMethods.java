package org.mozilla.javascript;

import java.lang.reflect.Field;

class FieldAndMethods
  extends NativeJavaMethod
{
  static final long serialVersionUID = -9222428244284796755L;
  Field field;
  Object javaObject;
  
  FieldAndMethods(Scriptable paramScriptable, MemberBox[] paramArrayOfMemberBox, Field paramField)
  {
    super(paramArrayOfMemberBox);
    this.field = paramField;
    setParentScope(paramScriptable);
    setPrototype(ScriptableObject.getFunctionPrototype(paramScriptable));
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    if (paramClass == ScriptRuntime.FunctionClass) {
      return this;
    }
    try
    {
      Object localObject1 = this.field.get(this.javaObject);
      Object localObject2 = this.field.getType();
      Context localContext = Context.getContext();
      localObject2 = localContext.getWrapFactory().wrap(localContext, this, localObject1, (Class)localObject2);
      localObject1 = localObject2;
      if ((localObject2 instanceof Scriptable)) {
        localObject1 = ((Scriptable)localObject2).getDefaultValue(paramClass);
      }
      return localObject1;
    }
    catch (IllegalAccessException paramClass)
    {
      throw Context.reportRuntimeError1("msg.java.internal.private", this.field.getName());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\FieldAndMethods.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */