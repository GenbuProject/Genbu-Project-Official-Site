package org.mozilla.javascript;

public class NativeJavaConstructor
  extends BaseFunction
{
  static final long serialVersionUID = -8149253217482668463L;
  MemberBox ctor;
  
  public NativeJavaConstructor(MemberBox paramMemberBox)
  {
    this.ctor = paramMemberBox;
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return NativeJavaClass.constructSpecific(paramContext, paramScriptable1, paramArrayOfObject, this.ctor);
  }
  
  public String getFunctionName()
  {
    return "<init>".concat(JavaMembers.liveConnectSignature(this.ctor.argTypes));
  }
  
  public String toString()
  {
    return "[JavaConstructor " + this.ctor.getName() + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeJavaConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */