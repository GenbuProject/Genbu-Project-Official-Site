package org.mozilla.javascript.regexp;

import java.io.Serializable;

final class RECharSet
  implements Serializable
{
  static final long serialVersionUID = 7931787979395898394L;
  volatile transient byte[] bits;
  volatile transient boolean converted;
  final int length;
  final boolean sense;
  final int startIndex;
  final int strlength;
  
  RECharSet(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.length = paramInt1;
    this.startIndex = paramInt2;
    this.strlength = paramInt3;
    this.sense = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\regexp\RECharSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */