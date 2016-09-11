package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public abstract class zzca
  extends zzak
{
  private static final String zzbiQ = zzae.zzdV.toString();
  private static final String zzbjO = zzae.zzdW.toString();
  
  public zzca(String paramString)
  {
    super(paramString, new String[] { zzbiQ, zzbjO });
  }
  
  public boolean zzFW()
  {
    return true;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    Object localObject = paramMap.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((zzag.zza)((Iterator)localObject).next() == zzdf.zzHF()) {
        return zzdf.zzR(Boolean.valueOf(false));
      }
    }
    localObject = (zzag.zza)paramMap.get(zzbiQ);
    zzag.zza localzza = (zzag.zza)paramMap.get(zzbjO);
    if ((localObject == null) || (localzza == null)) {}
    for (boolean bool = false;; bool = zza((zzag.zza)localObject, localzza, paramMap)) {
      return zzdf.zzR(Boolean.valueOf(bool));
    }
  }
  
  protected abstract boolean zza(zzag.zza paramzza1, zzag.zza paramzza2, Map<String, zzag.zza> paramMap);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */