package org.mozilla.javascript;

class BeanProperty
{
  MemberBox getter;
  MemberBox setter;
  NativeJavaMethod setters;
  
  BeanProperty(MemberBox paramMemberBox1, MemberBox paramMemberBox2, NativeJavaMethod paramNativeJavaMethod)
  {
    this.getter = paramMemberBox1;
    this.setter = paramMemberBox2;
    this.setters = paramNativeJavaMethod;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\BeanProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */