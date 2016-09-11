package org.mozilla.javascript.regexp;

import java.io.Serializable;

class RECompiled
  implements Serializable
{
  static final long serialVersionUID = -6144956577595844213L;
  int anchorCh = -1;
  int classCount;
  RECharSet[] classList;
  int flags;
  int parenCount;
  byte[] program;
  final char[] source;
  
  RECompiled(String paramString)
  {
    this.source = paramString.toCharArray();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\regexp\RECompiled.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */