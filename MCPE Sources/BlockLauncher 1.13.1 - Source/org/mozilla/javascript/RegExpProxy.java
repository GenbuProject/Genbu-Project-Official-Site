package org.mozilla.javascript;

public abstract interface RegExpProxy
{
  public static final int RA_MATCH = 1;
  public static final int RA_REPLACE = 2;
  public static final int RA_SEARCH = 3;
  
  public abstract Object action(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject, int paramInt);
  
  public abstract Object compileRegExp(Context paramContext, String paramString1, String paramString2);
  
  public abstract int find_split(Context paramContext, Scriptable paramScriptable1, String paramString1, String paramString2, Scriptable paramScriptable2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean, String[][] paramArrayOfString);
  
  public abstract boolean isRegExp(Scriptable paramScriptable);
  
  public abstract Object js_split(Context paramContext, Scriptable paramScriptable, String paramString, Object[] paramArrayOfObject);
  
  public abstract Scriptable wrapRegExp(Context paramContext, Scriptable paramScriptable, Object paramObject);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\RegExpProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */