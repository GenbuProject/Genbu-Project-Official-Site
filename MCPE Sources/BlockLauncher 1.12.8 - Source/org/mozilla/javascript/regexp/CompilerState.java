package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;

class CompilerState
{
  int backReferenceLimit;
  int classCount;
  int cp;
  char[] cpbegin;
  int cpend;
  Context cx;
  int flags;
  int maxBackReference;
  int parenCount;
  int parenNesting;
  int progLength;
  RENode result;
  
  CompilerState(Context paramContext, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this.cx = paramContext;
    this.cpbegin = paramArrayOfChar;
    this.cp = 0;
    this.cpend = paramInt1;
    this.flags = paramInt2;
    this.backReferenceLimit = Integer.MAX_VALUE;
    this.maxBackReference = 0;
    this.parenCount = 0;
    this.classCount = 0;
    this.progLength = 0;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\regexp\CompilerState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */