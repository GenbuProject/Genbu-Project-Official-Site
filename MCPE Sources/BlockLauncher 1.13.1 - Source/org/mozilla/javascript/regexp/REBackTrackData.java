package org.mozilla.javascript.regexp;

class REBackTrackData
{
  final int continuationOp;
  final int continuationPc;
  final int cp;
  final int op;
  final long[] parens;
  final int pc;
  final REBackTrackData previous;
  final REProgState stateStackTop;
  
  REBackTrackData(REGlobalData paramREGlobalData, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    this.previous = paramREGlobalData.backTrackStackTop;
    this.op = paramInt1;
    this.pc = paramInt2;
    this.cp = paramInt3;
    this.continuationOp = paramInt4;
    this.continuationPc = paramInt5;
    this.parens = paramREGlobalData.parens;
    this.stateStackTop = paramREGlobalData.stateStackTop;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\REBackTrackData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */