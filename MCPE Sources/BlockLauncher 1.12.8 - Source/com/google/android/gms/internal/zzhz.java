package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

@zzhb
public class zzhz
  extends zzim
  implements zzhy
{
  private final Context mContext;
  private final zzif.zza zzGd;
  private final ArrayList<Future> zzKL = new ArrayList();
  private final ArrayList<String> zzKM = new ArrayList();
  private final HashSet<String> zzKN = new HashSet();
  private final zzht zzKO;
  private final Object zzpV = new Object();
  private final String zzrG;
  
  public zzhz(Context paramContext, String paramString, zzif.zza paramzza, zzht paramzzht)
  {
    this.mContext = paramContext;
    this.zzrG = paramString;
    this.zzGd = paramzza;
    this.zzKO = paramzzht;
  }
  
  private zzif zza(int paramInt, @Nullable String paramString, @Nullable zzen paramzzen)
  {
    return new zzif(this.zzGd.zzLd.zzHt, null, this.zzGd.zzLe.zzBQ, paramInt, this.zzGd.zzLe.zzBR, this.zzGd.zzLe.zzHV, this.zzGd.zzLe.orientation, this.zzGd.zzLe.zzBU, this.zzGd.zzLd.zzHw, this.zzGd.zzLe.zzHT, paramzzen, null, paramString, this.zzGd.zzKV, null, this.zzGd.zzLe.zzHU, this.zzGd.zzrp, this.zzGd.zzLe.zzHS, this.zzGd.zzKY, this.zzGd.zzLe.zzHX, this.zzGd.zzLe.zzHY, this.zzGd.zzKT, null, this.zzGd.zzLe.zzIj, this.zzGd.zzLe.zzIk, this.zzGd.zzLe.zzIl, this.zzGd.zzLe.zzIm);
  }
  
  private zzif zza(String paramString, zzen paramzzen)
  {
    return zza(-2, paramString, paramzzen);
  }
  
  private void zzd(String paramString1, String paramString2, String paramString3)
  {
    synchronized (this.zzpV)
    {
      zzia localzzia = this.zzKO.zzaw(paramString1);
      if ((localzzia == null) || (localzzia.zzgQ() == null) || (localzzia.zzgP() == null)) {
        return;
      }
      paramString2 = new zzhu(this.mContext, paramString1, this.zzrG, paramString2, paramString3, this.zzGd, localzzia, this);
      this.zzKL.add(paramString2.zzhn());
      this.zzKM.add(paramString1);
      return;
    }
  }
  
  private zzif zzgO()
  {
    return zza(3, null, null);
  }
  
  public void onStop() {}
  
  public void zza(String paramString, int paramInt) {}
  
  public void zzax(String paramString)
  {
    synchronized (this.zzpV)
    {
      this.zzKN.add(paramString);
      return;
    }
  }
  
  public void zzbr()
  {
    ??? = this.zzGd.zzKV.zzBO.iterator();
    final Object localObject2;
    while (((Iterator)???).hasNext())
    {
      localObject2 = (zzen)((Iterator)???).next();
      String str = ((zzen)localObject2).zzBG;
      Iterator localIterator = ((zzen)localObject2).zzBB.iterator();
      while (localIterator.hasNext()) {
        zzd((String)localIterator.next(), str, ((zzen)localObject2).zzBz);
      }
    }
    int i = 0;
    for (;;)
    {
      if (i < this.zzKL.size()) {}
      try
      {
        ((Future)this.zzKL.get(i)).get();
        synchronized (this.zzpV)
        {
          if (this.zzKN.contains(this.zzKM.get(i)))
          {
            localObject2 = zza((String)this.zzKM.get(i), (zzen)this.zzGd.zzKV.zzBO.get(i));
            zza.zzMS.post(new Runnable()
            {
              public void run()
              {
                zzhz.zza(zzhz.this).zzb(localObject2);
              }
            });
            return;
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        final zzif localzzif = zzgO();
        zza.zzMS.post(new Runnable()
        {
          public void run()
          {
            zzhz.zza(zzhz.this).zzb(localzzif);
          }
        });
        return;
      }
      catch (Exception localException)
      {
        i += 1;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzhz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */