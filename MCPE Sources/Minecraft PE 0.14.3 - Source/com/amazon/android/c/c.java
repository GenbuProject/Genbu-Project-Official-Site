package com.amazon.android.c;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

final class c
  implements FilenameFilter
{
  c(a parama) {}
  
  public final boolean accept(File paramFile, String paramString)
  {
    return (paramString.endsWith(".amzst")) && (!a.a(this.a).containsValue(paramString));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\c\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */