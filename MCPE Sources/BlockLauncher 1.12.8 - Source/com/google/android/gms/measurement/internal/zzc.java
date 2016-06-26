package com.google.android.gms.measurement.internal;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzpz.zza;
import com.google.android.gms.internal.zzpz.zzb;
import com.google.android.gms.internal.zzpz.zzc;
import com.google.android.gms.internal.zzpz.zzd;
import com.google.android.gms.internal.zzpz.zze;
import com.google.android.gms.internal.zzqb.zza;
import com.google.android.gms.internal.zzqb.zzb;
import com.google.android.gms.internal.zzqb.zzc;
import com.google.android.gms.internal.zzqb.zzf;
import com.google.android.gms.internal.zzqb.zzg;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

class zzc
  extends zzz
{
  zzc(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  private Boolean zza(zzpz.zzb paramzzb, zzqb.zzb paramzzb1, long paramLong)
  {
    if (paramzzb.zzaZz != null)
    {
      localObject1 = new zzs(paramzzb.zzaZz).zzac(paramLong);
      if (localObject1 == null) {
        return null;
      }
      if (!((Boolean)localObject1).booleanValue()) {
        return Boolean.valueOf(false);
      }
    }
    Object localObject2 = new HashSet();
    Object localObject1 = paramzzb.zzaZx;
    int j = localObject1.length;
    int i = 0;
    while (i < j)
    {
      localObject3 = localObject1[i];
      if (TextUtils.isEmpty(((zzpz.zzc)localObject3).zzaZE))
      {
        zzAo().zzCF().zzj("null or empty param name in filter. event", paramzzb1.name);
        return null;
      }
      ((Set)localObject2).add(((zzpz.zzc)localObject3).zzaZE);
      i += 1;
    }
    localObject1 = new ArrayMap();
    Object localObject3 = paramzzb1.zzbae;
    j = localObject3.length;
    i = 0;
    Object localObject4;
    if (i < j)
    {
      localObject4 = localObject3[i];
      if (((Set)localObject2).contains(((zzqb.zzc)localObject4).name))
      {
        if (((zzqb.zzc)localObject4).zzbai == null) {
          break label219;
        }
        ((Map)localObject1).put(((zzqb.zzc)localObject4).name, ((zzqb.zzc)localObject4).zzbai);
      }
      for (;;)
      {
        i += 1;
        break;
        label219:
        if (((zzqb.zzc)localObject4).zzaZo != null)
        {
          ((Map)localObject1).put(((zzqb.zzc)localObject4).name, ((zzqb.zzc)localObject4).zzaZo);
        }
        else
        {
          if (((zzqb.zzc)localObject4).zzamJ == null) {
            break label277;
          }
          ((Map)localObject1).put(((zzqb.zzc)localObject4).name, ((zzqb.zzc)localObject4).zzamJ);
        }
      }
      label277:
      zzAo().zzCF().zze("Unknown value for param. event, param", paramzzb1.name, ((zzqb.zzc)localObject4).name);
      return null;
    }
    paramzzb = paramzzb.zzaZx;
    j = paramzzb.length;
    i = 0;
    while (i < j)
    {
      localObject2 = paramzzb[i];
      localObject3 = ((zzpz.zzc)localObject2).zzaZE;
      if (TextUtils.isEmpty((CharSequence)localObject3))
      {
        zzAo().zzCF().zzj("Event has empty param name. event", paramzzb1.name);
        return null;
      }
      localObject4 = ((Map)localObject1).get(localObject3);
      if ((localObject4 instanceof Long))
      {
        if (((zzpz.zzc)localObject2).zzaZC == null)
        {
          zzAo().zzCF().zze("No number filter for long param. event, param", paramzzb1.name, localObject3);
          return null;
        }
        localObject2 = new zzs(((zzpz.zzc)localObject2).zzaZC).zzac(((Long)localObject4).longValue());
        if (localObject2 == null) {
          return null;
        }
        if (!((Boolean)localObject2).booleanValue()) {
          return Boolean.valueOf(false);
        }
      }
      else if ((localObject4 instanceof Float))
      {
        if (((zzpz.zzc)localObject2).zzaZC == null)
        {
          zzAo().zzCF().zze("No number filter for float param. event, param", paramzzb1.name, localObject3);
          return null;
        }
        localObject2 = new zzs(((zzpz.zzc)localObject2).zzaZC).zzi(((Float)localObject4).floatValue());
        if (localObject2 == null) {
          return null;
        }
        if (!((Boolean)localObject2).booleanValue()) {
          return Boolean.valueOf(false);
        }
      }
      else if ((localObject4 instanceof String))
      {
        if (((zzpz.zzc)localObject2).zzaZB == null)
        {
          zzAo().zzCF().zze("No string filter for String param. event, param", paramzzb1.name, localObject3);
          return null;
        }
        localObject2 = new zzae(((zzpz.zzc)localObject2).zzaZB).zzfp((String)localObject4);
        if (localObject2 == null) {
          return null;
        }
        if (!((Boolean)localObject2).booleanValue()) {
          return Boolean.valueOf(false);
        }
      }
      else
      {
        if (localObject4 == null)
        {
          zzAo().zzCK().zze("Missing param for filter. event, param", paramzzb1.name, localObject3);
          return Boolean.valueOf(false);
        }
        zzAo().zzCF().zze("Unknown param type. event, param", paramzzb1.name, localObject3);
        return null;
      }
      i += 1;
    }
    return Boolean.valueOf(true);
  }
  
  private Boolean zza(zzpz.zze paramzze, zzqb.zzg paramzzg)
  {
    paramzze = paramzze.zzaZM;
    if (paramzze == null)
    {
      zzAo().zzCF().zzj("Missing property filter. property", paramzzg.name);
      return null;
    }
    if (paramzzg.zzbai != null)
    {
      if (paramzze.zzaZC == null)
      {
        zzAo().zzCF().zzj("No number filter for long property. property", paramzzg.name);
        return null;
      }
      return new zzs(paramzze.zzaZC).zzac(paramzzg.zzbai.longValue());
    }
    if (paramzzg.zzaZo != null)
    {
      if (paramzze.zzaZC == null)
      {
        zzAo().zzCF().zzj("No number filter for float property. property", paramzzg.name);
        return null;
      }
      return new zzs(paramzze.zzaZC).zzi(paramzzg.zzaZo.floatValue());
    }
    if (paramzzg.zzamJ != null)
    {
      if (paramzze.zzaZB == null)
      {
        if (paramzze.zzaZC == null)
        {
          zzAo().zzCF().zzj("No string or number filter defined. property", paramzzg.name);
          return null;
        }
        zzs localzzs = new zzs(paramzze.zzaZC);
        if (!paramzze.zzaZC.zzaZG.booleanValue())
        {
          if (zzeQ(paramzzg.zzamJ)) {
            try
            {
              paramzze = localzzs.zzac(Long.parseLong(paramzzg.zzamJ));
              return paramzze;
            }
            catch (NumberFormatException paramzze)
            {
              zzAo().zzCF().zze("User property value exceeded Long value range. property, value", paramzzg.name, paramzzg.zzamJ);
              return null;
            }
          }
          zzAo().zzCF().zze("Invalid user property value for Long number filter. property, value", paramzzg.name, paramzzg.zzamJ);
          return null;
        }
        if (zzeR(paramzzg.zzamJ)) {
          try
          {
            float f = Float.parseFloat(paramzzg.zzamJ);
            if (!Float.isInfinite(f)) {
              return localzzs.zzi(f);
            }
            zzAo().zzCF().zze("User property value exceeded Float value range. property, value", paramzzg.name, paramzzg.zzamJ);
            return null;
          }
          catch (NumberFormatException paramzze)
          {
            zzAo().zzCF().zze("User property value exceeded Float value range. property, value", paramzzg.name, paramzzg.zzamJ);
            return null;
          }
        }
        zzAo().zzCF().zze("Invalid user property value for Float number filter. property, value", paramzzg.name, paramzzg.zzamJ);
        return null;
      }
      return new zzae(paramzze.zzaZB).zzfp(paramzzg.zzamJ);
    }
    zzAo().zzCF().zzj("User property has no value, property", paramzzg.name);
    return null;
  }
  
  void zza(String paramString, zzpz.zza[] paramArrayOfzza)
  {
    zzCj().zzb(paramString, paramArrayOfzza);
  }
  
  zzqb.zza[] zza(String paramString, zzqb.zzb[] paramArrayOfzzb, zzqb.zzg[] paramArrayOfzzg)
  {
    zzx.zzcM(paramString);
    HashSet localHashSet = new HashSet();
    ArrayMap localArrayMap1 = new ArrayMap();
    ArrayMap localArrayMap2 = new ArrayMap();
    ArrayMap localArrayMap3 = new ArrayMap();
    Object localObject6;
    int k;
    int i;
    Object localObject7;
    Object localObject1;
    long l;
    Object localObject2;
    if (paramArrayOfzzb != null)
    {
      localObject6 = new ArrayMap();
      k = paramArrayOfzzb.length;
      i = 0;
      if (i < k)
      {
        localObject7 = paramArrayOfzzb[i];
        localObject1 = zzCj().zzI(paramString, ((zzqb.zzb)localObject7).name);
        if (localObject1 == null)
        {
          zzAo().zzCF().zzj("Event aggregate wasn't created during raw event logging. event", ((zzqb.zzb)localObject7).name);
          localObject1 = new zzi(paramString, ((zzqb.zzb)localObject7).name, 1L, 1L, ((zzqb.zzb)localObject7).zzbaf.longValue());
          zzCj().zza((zzi)localObject1);
          l = ((zzi)localObject1).zzaVP;
          localObject1 = (Map)((Map)localObject6).get(((zzqb.zzb)localObject7).name);
          if (localObject1 != null) {
            break label1817;
          }
          localObject2 = zzCj().zzL(paramString, ((zzqb.zzb)localObject7).name);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = new ArrayMap();
          }
          ((Map)localObject6).put(((zzqb.zzb)localObject7).name, localObject1);
        }
      }
    }
    label265:
    label846:
    label995:
    label1263:
    label1590:
    label1805:
    label1808:
    label1811:
    label1814:
    label1817:
    for (;;)
    {
      zzAo().zzCK().zze("Found audiences. event, audience count", ((zzqb.zzb)localObject7).name, Integer.valueOf(((Map)localObject1).size()));
      Object localObject8 = ((Map)localObject1).keySet().iterator();
      int m;
      Object localObject4;
      for (;;)
      {
        if (((Iterator)localObject8).hasNext())
        {
          m = ((Integer)((Iterator)localObject8).next()).intValue();
          if (localHashSet.contains(Integer.valueOf(m)))
          {
            zzAo().zzCK().zzj("Skipping failed audience ID", Integer.valueOf(m));
            continue;
            localObject1 = ((zzi)localObject1).zzCB();
            break;
          }
          localObject4 = (zzqb.zza)localArrayMap1.get(Integer.valueOf(m));
          if (localObject4 != null) {
            break label1814;
          }
          localObject4 = new zzqb.zza();
          localArrayMap1.put(Integer.valueOf(m), localObject4);
          ((zzqb.zza)localObject4).zzbac = Boolean.valueOf(false);
        }
      }
      for (;;)
      {
        Object localObject9 = (List)((Map)localObject1).get(Integer.valueOf(m));
        Object localObject5 = (BitSet)localArrayMap2.get(Integer.valueOf(m));
        Object localObject3 = (BitSet)localArrayMap3.get(Integer.valueOf(m));
        localObject2 = localObject5;
        if (localObject5 == null)
        {
          localObject2 = new BitSet();
          localArrayMap2.put(Integer.valueOf(m), localObject2);
          localObject3 = new BitSet();
          localArrayMap3.put(Integer.valueOf(m), localObject3);
        }
        if ((((zzqb.zza)localObject4).zzbab == null) && (!((zzqb.zza)localObject4).zzbac.booleanValue()))
        {
          localObject5 = zzCj().zzC(paramString, m);
          if (localObject5 == null) {
            ((zzqb.zza)localObject4).zzbac = Boolean.valueOf(true);
          }
        }
        else
        {
          localObject4 = ((List)localObject9).iterator();
        }
        int j;
        for (;;)
        {
          if (!((Iterator)localObject4).hasNext()) {
            break label846;
          }
          localObject5 = (zzpz.zzb)((Iterator)localObject4).next();
          if (zzAo().zzQ(2))
          {
            zzAo().zzCK().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(m), ((zzpz.zzb)localObject5).zzaZv, ((zzpz.zzb)localObject5).zzaZw);
            zzAo().zzCK().zzj("Filter definition", localObject5);
          }
          if (((zzpz.zzb)localObject5).zzaZv.intValue() > 256)
          {
            zzAo().zzCF().zzj("Invalid event filter ID > 256. id", ((zzpz.zzb)localObject5).zzaZv);
            continue;
            ((zzqb.zza)localObject4).zzbab = ((zzqb.zzf)localObject5);
            j = 0;
            while (j < ((zzqb.zzf)localObject5).zzbaH.length * 64)
            {
              if (zzaj.zza(((zzqb.zzf)localObject5).zzbaH, j))
              {
                zzAo().zzCK().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(m), Integer.valueOf(j));
                ((BitSet)localObject2).set(j);
                ((BitSet)localObject3).set(j);
              }
              j += 1;
            }
            break;
          }
          if (!((BitSet)localObject3).get(((zzpz.zzb)localObject5).zzaZv.intValue()))
          {
            localObject9 = zza((zzpz.zzb)localObject5, (zzqb.zzb)localObject7, l);
            zzAo().zzCK().zzj("Event filter result", localObject9);
            if (localObject9 == null)
            {
              localHashSet.add(Integer.valueOf(m));
            }
            else
            {
              ((BitSet)localObject3).set(((zzpz.zzb)localObject5).zzaZv.intValue());
              if (((Boolean)localObject9).booleanValue()) {
                ((BitSet)localObject2).set(((zzpz.zzb)localObject5).zzaZv.intValue());
              }
            }
          }
        }
        break label265;
        i += 1;
        break;
        if (paramArrayOfzzg != null)
        {
          localObject5 = new ArrayMap();
          k = paramArrayOfzzg.length;
          i = 0;
          if (i < k)
          {
            localObject6 = paramArrayOfzzg[i];
            paramArrayOfzzb = (Map)((Map)localObject5).get(((zzqb.zzg)localObject6).name);
            if (paramArrayOfzzb != null) {
              break label1811;
            }
            localObject1 = zzCj().zzM(paramString, ((zzqb.zzg)localObject6).name);
            paramArrayOfzzb = (zzqb.zzb[])localObject1;
            if (localObject1 == null) {
              paramArrayOfzzb = new ArrayMap();
            }
            ((Map)localObject5).put(((zzqb.zzg)localObject6).name, paramArrayOfzzb);
          }
        }
        for (;;)
        {
          zzAo().zzCK().zze("Found audiences. property, audience count", ((zzqb.zzg)localObject6).name, Integer.valueOf(paramArrayOfzzb.size()));
          localObject7 = paramArrayOfzzb.keySet().iterator();
          while (((Iterator)localObject7).hasNext())
          {
            m = ((Integer)((Iterator)localObject7).next()).intValue();
            if (localHashSet.contains(Integer.valueOf(m)))
            {
              zzAo().zzCK().zzj("Skipping failed audience ID", Integer.valueOf(m));
            }
            else
            {
              localObject3 = (zzqb.zza)localArrayMap1.get(Integer.valueOf(m));
              if (localObject3 != null) {
                break label1808;
              }
              localObject3 = new zzqb.zza();
              localArrayMap1.put(Integer.valueOf(m), localObject3);
              ((zzqb.zza)localObject3).zzbac = Boolean.valueOf(false);
            }
          }
          for (;;)
          {
            localObject8 = (List)paramArrayOfzzb.get(Integer.valueOf(m));
            localObject4 = (BitSet)localArrayMap2.get(Integer.valueOf(m));
            localObject2 = (BitSet)localArrayMap3.get(Integer.valueOf(m));
            localObject1 = localObject4;
            if (localObject4 == null)
            {
              localObject1 = new BitSet();
              localArrayMap2.put(Integer.valueOf(m), localObject1);
              localObject2 = new BitSet();
              localArrayMap3.put(Integer.valueOf(m), localObject2);
            }
            if ((((zzqb.zza)localObject3).zzbab == null) && (!((zzqb.zza)localObject3).zzbac.booleanValue()))
            {
              localObject4 = zzCj().zzC(paramString, m);
              if (localObject4 == null) {
                ((zzqb.zza)localObject3).zzbac = Boolean.valueOf(true);
              }
            }
            else
            {
              localObject3 = ((List)localObject8).iterator();
            }
            for (;;)
            {
              if (!((Iterator)localObject3).hasNext()) {
                break label1590;
              }
              localObject4 = (zzpz.zze)((Iterator)localObject3).next();
              if (zzAo().zzQ(2))
              {
                zzAo().zzCK().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(m), ((zzpz.zze)localObject4).zzaZv, ((zzpz.zze)localObject4).zzaZL);
                zzAo().zzCK().zzj("Filter definition", localObject4);
              }
              if ((((zzpz.zze)localObject4).zzaZv == null) || (((zzpz.zze)localObject4).zzaZv.intValue() > 256))
              {
                zzAo().zzCF().zzj("Invalid property filter ID. id", String.valueOf(((zzpz.zze)localObject4).zzaZv));
                localHashSet.add(Integer.valueOf(m));
                break;
                ((zzqb.zza)localObject3).zzbab = ((zzqb.zzf)localObject4);
                j = 0;
                while (j < ((zzqb.zzf)localObject4).zzbaH.length * 64)
                {
                  if (zzaj.zza(((zzqb.zzf)localObject4).zzbaH, j))
                  {
                    ((BitSet)localObject1).set(j);
                    ((BitSet)localObject2).set(j);
                  }
                  j += 1;
                }
                break label1263;
              }
              if (((BitSet)localObject2).get(((zzpz.zze)localObject4).zzaZv.intValue()))
              {
                zzAo().zzCK().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(m), ((zzpz.zze)localObject4).zzaZv);
              }
              else
              {
                localObject8 = zza((zzpz.zze)localObject4, (zzqb.zzg)localObject6);
                zzAo().zzCK().zzj("Property filter result", localObject8);
                if (localObject8 == null)
                {
                  localHashSet.add(Integer.valueOf(m));
                }
                else
                {
                  ((BitSet)localObject2).set(((zzpz.zze)localObject4).zzaZv.intValue());
                  if (((Boolean)localObject8).booleanValue()) {
                    ((BitSet)localObject1).set(((zzpz.zze)localObject4).zzaZv.intValue());
                  }
                }
              }
            }
            break label995;
            i += 1;
            break;
            paramArrayOfzzg = new zzqb.zza[localArrayMap2.size()];
            localObject1 = localArrayMap2.keySet().iterator();
            i = 0;
            while (((Iterator)localObject1).hasNext())
            {
              j = ((Integer)((Iterator)localObject1).next()).intValue();
              if (!localHashSet.contains(Integer.valueOf(j)))
              {
                paramArrayOfzzb = (zzqb.zza)localArrayMap1.get(Integer.valueOf(j));
                if (paramArrayOfzzb != null) {
                  break label1805;
                }
                paramArrayOfzzb = new zzqb.zza();
              }
            }
            for (;;)
            {
              paramArrayOfzzg[i] = paramArrayOfzzb;
              paramArrayOfzzb.zzaZr = Integer.valueOf(j);
              paramArrayOfzzb.zzbaa = new zzqb.zzf();
              paramArrayOfzzb.zzbaa.zzbaH = zzaj.zza((BitSet)localArrayMap2.get(Integer.valueOf(j)));
              paramArrayOfzzb.zzbaa.zzbaG = zzaj.zza((BitSet)localArrayMap3.get(Integer.valueOf(j)));
              zzCj().zza(paramString, j, paramArrayOfzzb.zzbaa);
              i += 1;
              break;
              return (zzqb.zza[])Arrays.copyOf(paramArrayOfzzg, i);
            }
          }
        }
      }
    }
  }
  
  boolean zzeQ(String paramString)
  {
    return Pattern.matches("[+-]?[0-9]+", paramString);
  }
  
  boolean zzeR(String paramString)
  {
    return Pattern.matches("[+-]?(([0-9]+\\.?)|([0-9]*\\.[0-9]+))", paramString);
  }
  
  protected void zziJ() {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */