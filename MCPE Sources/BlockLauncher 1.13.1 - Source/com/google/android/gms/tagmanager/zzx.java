package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class zzx
  extends zzdd
{
  private static final String ID = zzad.zzcg.toString();
  private static final String VALUE = zzae.zzih.toString();
  private static final String zzbiL = zzae.zzes.toString();
  private final DataLayer zzbhN;
  
  public zzx(DataLayer paramDataLayer)
  {
    super(ID, new String[] { VALUE });
    this.zzbhN = paramDataLayer;
  }
  
  private void zza(zzag.zza paramzza)
  {
    if ((paramzza == null) || (paramzza == zzdf.zzHz())) {}
    do
    {
      return;
      paramzza = zzdf.zzg(paramzza);
    } while (paramzza == zzdf.zzHE());
    this.zzbhN.zzfX(paramzza);
  }
  
  private void zzb(zzag.zza paramzza)
  {
    if ((paramzza == null) || (paramzza == zzdf.zzHz())) {}
    for (;;)
    {
      return;
      paramzza = zzdf.zzl(paramzza);
      if ((paramzza instanceof List))
      {
        paramzza = ((List)paramzza).iterator();
        while (paramzza.hasNext())
        {
          Object localObject = paramzza.next();
          if ((localObject instanceof Map))
          {
            localObject = (Map)localObject;
            this.zzbhN.push((Map)localObject);
          }
        }
      }
    }
  }
  
  public void zzR(Map<String, zzag.zza> paramMap)
  {
    zzb((zzag.zza)paramMap.get(VALUE));
    zza((zzag.zza)paramMap.get(zzbiL));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */