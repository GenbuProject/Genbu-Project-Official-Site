package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.internal.zzx;

public class zzi
{
  private String zzasR;
  
  private zzi(String paramString)
  {
    this.zzasR = paramString.toLowerCase();
  }
  
  public static zzi zzdd(String paramString)
  {
    if ((paramString == null) || (!paramString.isEmpty())) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzac(bool);
      if (paramString != null) {
        break;
      }
      return null;
    }
    return new zzi(paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      return false;
      if (paramObject == this) {
        return true;
      }
    } while (paramObject.getClass() != getClass());
    paramObject = (zzi)paramObject;
    return this.zzasR.equals(((zzi)paramObject).zzasR);
  }
  
  public int hashCode()
  {
    return this.zzasR.hashCode();
  }
  
  public boolean isFolder()
  {
    return this.zzasR.equals("application/vnd.google-apps.folder");
  }
  
  public String toString()
  {
    return this.zzasR;
  }
  
  public boolean zztH()
  {
    return (!zztI()) && (!isFolder());
  }
  
  public boolean zztI()
  {
    return this.zzasR.startsWith("application/vnd.google-apps");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\internal\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */