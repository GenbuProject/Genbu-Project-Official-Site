package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzr;
import java.util.WeakHashMap;

@zzhb
public final class zzhk
{
  private WeakHashMap<Context, zza> zzKm = new WeakHashMap();
  
  public zzhj zzE(Context paramContext)
  {
    Object localObject = (zza)this.zzKm.get(paramContext);
    if ((localObject != null) && (!((zza)localObject).hasExpired()) && (((Boolean)zzbt.zzwL.get()).booleanValue())) {}
    for (localObject = new zzhj.zza(paramContext, ((zza)localObject).zzKo).zzgI();; localObject = new zzhj.zza(paramContext).zzgI())
    {
      this.zzKm.put(paramContext, new zza((zzhj)localObject));
      return (zzhj)localObject;
    }
  }
  
  private class zza
  {
    public final long zzKn = zzr.zzbG().currentTimeMillis();
    public final zzhj zzKo;
    
    public zza(zzhj paramzzhj)
    {
      this.zzKo = paramzzhj;
    }
    
    public boolean hasExpired()
    {
      long l = this.zzKn;
      return ((Long)zzbt.zzwM.get()).longValue() + l < zzr.zzbG().currentTimeMillis();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzhk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */