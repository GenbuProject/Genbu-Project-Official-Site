package org.mozilla.javascript.regexp;

class REProgState
{
  final REBackTrackData backTrack;
  final int continuationOp;
  final int continuationPc;
  final int index;
  final int max;
  final int min;
  final REProgState previous;
  
  REProgState(REProgState paramREProgState, int paramInt1, int paramInt2, int paramInt3, REBackTrackData paramREBackTrackData, int paramInt4, int paramInt5)
  {
    this.previous = paramREProgState;
    this.min = paramInt1;
    this.max = paramInt2;
    this.index = paramInt3;
    this.continuationOp = paramInt4;
    this.continuationPc = paramInt5;
    this.backTrack = paramREBackTrackData;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\REProgState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */