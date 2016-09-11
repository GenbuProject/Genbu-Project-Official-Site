package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag.zza;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzak
{
  private final Set<String> zzbiU;
  private final String zzbiV;
  
  public zzak(String paramString, String... paramVarArgs)
  {
    this.zzbiV = paramString;
    this.zzbiU = new HashSet(paramVarArgs.length);
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramString = paramVarArgs[i];
      this.zzbiU.add(paramString);
      i += 1;
    }
  }
  
  public abstract boolean zzFW();
  
  public String zzGB()
  {
    return this.zzbiV;
  }
  
  public Set<String> zzGC()
  {
    return this.zzbiU;
  }
  
  public abstract zzag.zza zzP(Map<String, zzag.zza> paramMap);
  
  boolean zze(Set<String> paramSet)
  {
    return paramSet.containsAll(this.zzbiU);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */