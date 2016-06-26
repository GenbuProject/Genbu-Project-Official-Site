package org.mozilla.javascript;

public abstract interface ConstProperties
{
  public abstract void defineConst(String paramString, Scriptable paramScriptable);
  
  public abstract boolean isConst(String paramString);
  
  public abstract void putConst(String paramString, Scriptable paramScriptable, Object paramObject);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ConstProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */