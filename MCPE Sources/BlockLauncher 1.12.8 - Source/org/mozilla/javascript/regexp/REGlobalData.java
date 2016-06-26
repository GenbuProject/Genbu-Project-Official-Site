package org.mozilla.javascript.regexp;

class REGlobalData
{
  REBackTrackData backTrackStackTop;
  int cp;
  boolean multiline;
  long[] parens;
  RECompiled regexp;
  int skipped;
  REProgState stateStackTop;
  
  int parensIndex(int paramInt)
  {
    return (int)this.parens[paramInt];
  }
  
  int parensLength(int paramInt)
  {
    return (int)(this.parens[paramInt] >>> 32);
  }
  
  void setParens(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((this.backTrackStackTop != null) && (this.backTrackStackTop.parens == this.parens)) {
      this.parens = ((long[])this.parens.clone());
    }
    this.parens[paramInt1] = (paramInt2 & 0xFFFFFFFF | paramInt3 << 32);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\regexp\REGlobalData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */