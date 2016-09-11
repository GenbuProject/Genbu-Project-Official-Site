package org.mozilla.javascript.regexp;

class RENode
{
  int bmsize;
  char chr;
  int flatIndex;
  boolean greedy;
  int index;
  RENode kid;
  RENode kid2;
  int kidlen;
  int length;
  int max;
  int min;
  RENode next;
  byte op;
  int parenCount;
  int parenIndex;
  boolean sense;
  int startIndex;
  
  RENode(byte paramByte)
  {
    this.op = paramByte;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\RENode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */