package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zznm;
import com.google.android.gms.internal.zznn;
import com.google.android.gms.internal.zzno;
import com.google.android.gms.internal.zznq;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zze
{
  private static final Map<String, MetadataField<?>> zzasO = new HashMap();
  private static final Map<String, zza> zzasP = new HashMap();
  
  static
  {
    zzb(zznm.zzasU);
    zzb(zznm.zzatA);
    zzb(zznm.zzatr);
    zzb(zznm.zzaty);
    zzb(zznm.zzatB);
    zzb(zznm.zzath);
    zzb(zznm.zzatg);
    zzb(zznm.zzati);
    zzb(zznm.zzatj);
    zzb(zznm.zzatk);
    zzb(zznm.zzate);
    zzb(zznm.zzatm);
    zzb(zznm.zzatn);
    zzb(zznm.zzato);
    zzb(zznm.zzatw);
    zzb(zznm.zzasV);
    zzb(zznm.zzatt);
    zzb(zznm.zzasX);
    zzb(zznm.zzatf);
    zzb(zznm.zzasY);
    zzb(zznm.zzasZ);
    zzb(zznm.zzata);
    zzb(zznm.zzatb);
    zzb(zznm.zzatq);
    zzb(zznm.zzatl);
    zzb(zznm.zzats);
    zzb(zznm.zzatu);
    zzb(zznm.zzatv);
    zzb(zznm.zzatx);
    zzb(zznm.zzatC);
    zzb(zznm.zzatD);
    zzb(zznm.zzatd);
    zzb(zznm.zzatc);
    zzb(zznm.zzatz);
    zzb(zznm.zzatp);
    zzb(zznm.zzasW);
    zzb(zznm.zzatE);
    zzb(zznm.zzatF);
    zzb(zznm.zzatG);
    zzb(zznm.zzatH);
    zzb(zznm.zzatI);
    zzb(zznm.zzatJ);
    zzb(zznm.zzatK);
    zzb(zzno.zzatM);
    zzb(zzno.zzatO);
    zzb(zzno.zzatP);
    zzb(zzno.zzatQ);
    zzb(zzno.zzatN);
    zzb(zzno.zzatR);
    zzb(zznq.zzatT);
    zzb(zznq.zzatU);
    zzm localzzm = zznm.zzatw;
    zza(zzm.zzasT);
    zza(zznn.zzatL);
  }
  
  public static void zza(DataHolder paramDataHolder)
  {
    Iterator localIterator = zzasP.values().iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zzb(paramDataHolder);
    }
  }
  
  private static void zza(zza paramzza)
  {
    if (zzasP.put(paramzza.zztD(), paramzza) != null) {
      throw new IllegalStateException("A cleaner for key " + paramzza.zztD() + " has already been registered");
    }
  }
  
  private static void zzb(MetadataField<?> paramMetadataField)
  {
    if (zzasO.containsKey(paramMetadataField.getName())) {
      throw new IllegalArgumentException("Duplicate field name registered: " + paramMetadataField.getName());
    }
    zzasO.put(paramMetadataField.getName(), paramMetadataField);
  }
  
  public static MetadataField<?> zzdc(String paramString)
  {
    return (MetadataField)zzasO.get(paramString);
  }
  
  public static Collection<MetadataField<?>> zztC()
  {
    return Collections.unmodifiableCollection(zzasO.values());
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(DataHolder paramDataHolder);
    
    public abstract String zztD();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */