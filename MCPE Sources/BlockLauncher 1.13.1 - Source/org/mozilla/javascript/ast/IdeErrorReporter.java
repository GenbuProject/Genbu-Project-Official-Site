package org.mozilla.javascript.ast;

import org.mozilla.javascript.ErrorReporter;

public abstract interface IdeErrorReporter
  extends ErrorReporter
{
  public abstract void error(String paramString1, String paramString2, int paramInt1, int paramInt2);
  
  public abstract void warning(String paramString1, String paramString2, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\IdeErrorReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */