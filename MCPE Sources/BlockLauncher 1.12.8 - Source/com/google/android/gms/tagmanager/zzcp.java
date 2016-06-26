package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf.zzi;
import com.google.android.gms.internal.zzag.zza;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrs.zza;
import com.google.android.gms.internal.zzrs.zzc;
import com.google.android.gms.internal.zzrs.zze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzcp
{
  private static final zzbw<zzag.zza> zzbkq = new zzbw(zzdf.zzHF(), true);
  private final DataLayer zzbhN;
  private volatile String zzbkA;
  private int zzbkB;
  private final zzrs.zzc zzbkr;
  private final zzah zzbks;
  private final Map<String, zzak> zzbkt;
  private final Map<String, zzak> zzbku;
  private final Map<String, zzak> zzbkv;
  private final zzl<zzrs.zza, zzbw<zzag.zza>> zzbkw;
  private final zzl<String, zzb> zzbkx;
  private final Set<zzrs.zze> zzbky;
  private final Map<String, zzc> zzbkz;
  
  public zzcp(Context paramContext, zzrs.zzc paramzzc, DataLayer paramDataLayer, zzt.zza paramzza1, zzt.zza paramzza2, zzah paramzzah)
  {
    if (paramzzc == null) {
      throw new NullPointerException("resource cannot be null");
    }
    this.zzbkr = paramzzc;
    this.zzbky = new HashSet(paramzzc.zzHL());
    this.zzbhN = paramDataLayer;
    this.zzbks = paramzzah;
    paramzzc = new zzm.zza()
    {
      public int zza(zzrs.zza paramAnonymouszza, zzbw<zzag.zza> paramAnonymouszzbw)
      {
        return ((zzag.zza)paramAnonymouszzbw.getObject()).getCachedSize();
      }
    };
    this.zzbkw = new zzm().zza(1048576, paramzzc);
    paramzzc = new zzm.zza()
    {
      public int zza(String paramAnonymousString, zzcp.zzb paramAnonymouszzb)
      {
        return paramAnonymousString.length() + paramAnonymouszzb.getSize();
      }
    };
    this.zzbkx = new zzm().zza(1048576, paramzzc);
    this.zzbkt = new HashMap();
    zzb(new zzj(paramContext));
    zzb(new zzt(paramzza2));
    zzb(new zzx(paramDataLayer));
    zzb(new zzdg(paramContext, paramDataLayer));
    zzb(new zzdb(paramContext, paramDataLayer));
    this.zzbku = new HashMap();
    zzc(new zzr());
    zzc(new zzae());
    zzc(new zzaf());
    zzc(new zzam());
    zzc(new zzan());
    zzc(new zzbc());
    zzc(new zzbd());
    zzc(new zzcf());
    zzc(new zzcy());
    this.zzbkv = new HashMap();
    zza(new zzb(paramContext));
    zza(new zzc(paramContext));
    zza(new zze(paramContext));
    zza(new zzf(paramContext));
    zza(new zzg(paramContext));
    zza(new zzh(paramContext));
    zza(new zzi(paramContext));
    zza(new zzn());
    zza(new zzq(this.zzbkr.getVersion()));
    zza(new zzt(paramzza1));
    zza(new zzv(paramDataLayer));
    zza(new zzaa(paramContext));
    zza(new zzab());
    zza(new zzad());
    zza(new zzai(this));
    zza(new zzao());
    zza(new zzap());
    zza(new zzaw(paramContext));
    zza(new zzay());
    zza(new zzbb());
    zza(new zzbi());
    zza(new zzbk(paramContext));
    zza(new zzbx());
    zza(new zzbz());
    zza(new zzcc());
    zza(new zzce());
    zza(new zzcg(paramContext));
    zza(new zzcq());
    zza(new zzcr());
    zza(new zzda());
    zza(new zzdh());
    this.zzbkz = new HashMap();
    paramDataLayer = this.zzbky.iterator();
    while (paramDataLayer.hasNext())
    {
      paramzza1 = (zzrs.zze)paramDataLayer.next();
      if (paramzzah.zzGA())
      {
        zza(paramzza1.zzHT(), paramzza1.zzHU(), "add macro");
        zza(paramzza1.zzHY(), paramzza1.zzHV(), "remove macro");
        zza(paramzza1.zzHR(), paramzza1.zzHW(), "add tag");
        zza(paramzza1.zzHS(), paramzza1.zzHX(), "remove tag");
      }
      int i = 0;
      while (i < paramzza1.zzHT().size())
      {
        paramzza2 = (zzrs.zza)paramzza1.zzHT().get(i);
        paramzzc = "Unknown";
        paramContext = paramzzc;
        if (paramzzah.zzGA())
        {
          paramContext = paramzzc;
          if (i < paramzza1.zzHU().size()) {
            paramContext = (String)paramzza1.zzHU().get(i);
          }
        }
        paramzzc = zzi(this.zzbkz, zza(paramzza2));
        paramzzc.zza(paramzza1);
        paramzzc.zza(paramzza1, paramzza2);
        paramzzc.zza(paramzza1, paramContext);
        i += 1;
      }
      i = 0;
      while (i < paramzza1.zzHY().size())
      {
        paramzza2 = (zzrs.zza)paramzza1.zzHY().get(i);
        paramzzc = "Unknown";
        paramContext = paramzzc;
        if (paramzzah.zzGA())
        {
          paramContext = paramzzc;
          if (i < paramzza1.zzHV().size()) {
            paramContext = (String)paramzza1.zzHV().get(i);
          }
        }
        paramzzc = zzi(this.zzbkz, zza(paramzza2));
        paramzzc.zza(paramzza1);
        paramzzc.zzb(paramzza1, paramzza2);
        paramzzc.zzb(paramzza1, paramContext);
        i += 1;
      }
    }
    paramContext = this.zzbkr.zzHM().entrySet().iterator();
    while (paramContext.hasNext())
    {
      paramzzc = (Map.Entry)paramContext.next();
      paramDataLayer = ((List)paramzzc.getValue()).iterator();
      while (paramDataLayer.hasNext())
      {
        paramzza1 = (zzrs.zza)paramDataLayer.next();
        if (!zzdf.zzk((zzag.zza)paramzza1.zzHI().get(com.google.android.gms.internal.zzae.zzgt.toString())).booleanValue()) {
          zzi(this.zzbkz, (String)paramzzc.getKey()).zzb(paramzza1);
        }
      }
    }
  }
  
  private String zzHf()
  {
    if (this.zzbkB <= 1) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toString(this.zzbkB));
    int i = 2;
    while (i < this.zzbkB)
    {
      localStringBuilder.append(' ');
      i += 1;
    }
    localStringBuilder.append(": ");
    return localStringBuilder.toString();
  }
  
  private zzbw<zzag.zza> zza(zzag.zza paramzza, Set<String> paramSet, zzdi paramzzdi)
  {
    if (!paramzza.zzjH) {
      return new zzbw(paramzza, true);
    }
    zzbw localzzbw1;
    switch (paramzza.type)
    {
    case 5: 
    case 6: 
    default: 
      zzbg.e("Unknown type: " + paramzza.type);
      return zzbkq;
    case 2: 
      localzza = zzrs.zzo(paramzza);
      localzza.zzjy = new zzag.zza[paramzza.zzjy.length];
      i = 0;
      while (i < paramzza.zzjy.length)
      {
        localzzbw1 = zza(paramzza.zzjy[i], paramSet, paramzzdi.zzkh(i));
        if (localzzbw1 == zzbkq) {
          return zzbkq;
        }
        localzza.zzjy[i] = ((zzag.zza)localzzbw1.getObject());
        i += 1;
      }
      return new zzbw(localzza, false);
    case 3: 
      localzza = zzrs.zzo(paramzza);
      if (paramzza.zzjz.length != paramzza.zzjA.length)
      {
        zzbg.e("Invalid serving value: " + paramzza.toString());
        return zzbkq;
      }
      localzza.zzjz = new zzag.zza[paramzza.zzjz.length];
      localzza.zzjA = new zzag.zza[paramzza.zzjz.length];
      i = 0;
      while (i < paramzza.zzjz.length)
      {
        localzzbw1 = zza(paramzza.zzjz[i], paramSet, paramzzdi.zzki(i));
        zzbw localzzbw2 = zza(paramzza.zzjA[i], paramSet, paramzzdi.zzkj(i));
        if ((localzzbw1 == zzbkq) || (localzzbw2 == zzbkq)) {
          return zzbkq;
        }
        localzza.zzjz[i] = ((zzag.zza)localzzbw1.getObject());
        localzza.zzjA[i] = ((zzag.zza)localzzbw2.getObject());
        i += 1;
      }
      return new zzbw(localzza, false);
    case 4: 
      if (paramSet.contains(paramzza.zzjB))
      {
        zzbg.e("Macro cycle detected.  Current macro reference: " + paramzza.zzjB + "." + "  Previous macro references: " + paramSet.toString() + ".");
        return zzbkq;
      }
      paramSet.add(paramzza.zzjB);
      paramzzdi = zzdj.zza(zza(paramzza.zzjB, paramSet, paramzzdi.zzGO()), paramzza.zzjG);
      paramSet.remove(paramzza.zzjB);
      return paramzzdi;
    }
    zzag.zza localzza = zzrs.zzo(paramzza);
    localzza.zzjF = new zzag.zza[paramzza.zzjF.length];
    int i = 0;
    while (i < paramzza.zzjF.length)
    {
      localzzbw1 = zza(paramzza.zzjF[i], paramSet, paramzzdi.zzkk(i));
      if (localzzbw1 == zzbkq) {
        return zzbkq;
      }
      localzza.zzjF[i] = ((zzag.zza)localzzbw1.getObject());
      i += 1;
    }
    return new zzbw(localzza, false);
  }
  
  private zzbw<zzag.zza> zza(String paramString, Set<String> paramSet, zzbj paramzzbj)
  {
    this.zzbkB += 1;
    Object localObject = (zzb)this.zzbkx.get(paramString);
    if ((localObject != null) && (!this.zzbks.zzGA()))
    {
      zza(((zzb)localObject).zzHh(), paramSet);
      this.zzbkB -= 1;
      return ((zzb)localObject).zzHg();
    }
    localObject = (zzc)this.zzbkz.get(paramString);
    if (localObject == null)
    {
      zzbg.e(zzHf() + "Invalid macro: " + paramString);
      this.zzbkB -= 1;
      return zzbkq;
    }
    zzbw localzzbw = zza(paramString, ((zzc)localObject).zzHi(), ((zzc)localObject).zzHj(), ((zzc)localObject).zzHk(), ((zzc)localObject).zzHm(), ((zzc)localObject).zzHl(), paramSet, paramzzbj.zzGq());
    if (((Set)localzzbw.getObject()).isEmpty()) {}
    for (localObject = ((zzc)localObject).zzHn(); localObject == null; localObject = (zzrs.zza)((Set)localzzbw.getObject()).iterator().next())
    {
      this.zzbkB -= 1;
      return zzbkq;
      if (((Set)localzzbw.getObject()).size() > 1) {
        zzbg.zzaK(zzHf() + "Multiple macros active for macroName " + paramString);
      }
    }
    paramzzbj = zza(this.zzbkv, (zzrs.zza)localObject, paramSet, paramzzbj.zzGG());
    boolean bool;
    if ((localzzbw.zzGP()) && (paramzzbj.zzGP()))
    {
      bool = true;
      if (paramzzbj != zzbkq) {
        break label392;
      }
    }
    label392:
    for (paramzzbj = zzbkq;; paramzzbj = new zzbw(paramzzbj.getObject(), bool))
    {
      localObject = ((zzrs.zza)localObject).zzHh();
      if (paramzzbj.zzGP()) {
        this.zzbkx.zzh(paramString, new zzb(paramzzbj, (zzag.zza)localObject));
      }
      zza((zzag.zza)localObject, paramSet);
      this.zzbkB -= 1;
      return paramzzbj;
      bool = false;
      break;
    }
  }
  
  private zzbw<zzag.zza> zza(Map<String, zzak> paramMap, zzrs.zza paramzza, Set<String> paramSet, zzch paramzzch)
  {
    boolean bool = true;
    Object localObject1 = (zzag.zza)paramzza.zzHI().get(com.google.android.gms.internal.zzae.zzfG.toString());
    if (localObject1 == null)
    {
      zzbg.e("No function id in properties");
      paramMap = zzbkq;
    }
    zzak localzzak;
    do
    {
      return paramMap;
      localObject1 = ((zzag.zza)localObject1).zzjC;
      localzzak = (zzak)paramMap.get(localObject1);
      if (localzzak == null)
      {
        zzbg.e((String)localObject1 + " has no backing implementation.");
        return zzbkq;
      }
      paramMap = (zzbw)this.zzbkw.get(paramzza);
    } while ((paramMap != null) && (!this.zzbks.zzGA()));
    paramMap = new HashMap();
    Iterator localIterator = paramzza.zzHI().entrySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject2 = paramzzch.zzgj((String)localEntry.getKey());
      localObject2 = zza((zzag.zza)localEntry.getValue(), paramSet, ((zzcj)localObject2).zze((zzag.zza)localEntry.getValue()));
      if (localObject2 == zzbkq) {
        return zzbkq;
      }
      if (((zzbw)localObject2).zzGP()) {
        paramzza.zza((String)localEntry.getKey(), (zzag.zza)((zzbw)localObject2).getObject());
      }
      for (;;)
      {
        paramMap.put(localEntry.getKey(), ((zzbw)localObject2).getObject());
        break;
        i = 0;
      }
    }
    if (!localzzak.zze(paramMap.keySet()))
    {
      zzbg.e("Incorrect keys for function " + (String)localObject1 + " required " + localzzak.zzGC() + " had " + paramMap.keySet());
      return zzbkq;
    }
    if ((i != 0) && (localzzak.zzFW())) {}
    for (;;)
    {
      paramMap = new zzbw(localzzak.zzP(paramMap), bool);
      if (bool) {
        this.zzbkw.zzh(paramzza, paramMap);
      }
      paramzzch.zzd((zzag.zza)paramMap.getObject());
      return paramMap;
      bool = false;
    }
  }
  
  private zzbw<Set<zzrs.zza>> zza(Set<zzrs.zze> paramSet, Set<String> paramSet1, zza paramzza, zzco paramzzco)
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    paramSet = paramSet.iterator();
    boolean bool = true;
    if (paramSet.hasNext())
    {
      zzrs.zze localzze = (zzrs.zze)paramSet.next();
      zzck localzzck = paramzzco.zzGN();
      zzbw localzzbw = zza(localzze, paramSet1, localzzck);
      if (((Boolean)localzzbw.getObject()).booleanValue()) {
        paramzza.zza(localzze, localHashSet1, localHashSet2, localzzck);
      }
      if ((bool) && (localzzbw.zzGP())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    localHashSet1.removeAll(localHashSet2);
    paramzzco.zzf(localHashSet1);
    return new zzbw(localHashSet1, bool);
  }
  
  private static String zza(zzrs.zza paramzza)
  {
    return zzdf.zzg((zzag.zza)paramzza.zzHI().get(com.google.android.gms.internal.zzae.zzfR.toString()));
  }
  
  private void zza(zzag.zza paramzza, Set<String> paramSet)
  {
    if (paramzza == null) {}
    for (;;)
    {
      return;
      paramzza = zza(paramzza, paramSet, new zzbu());
      if (paramzza != zzbkq)
      {
        paramzza = zzdf.zzl((zzag.zza)paramzza.getObject());
        if ((paramzza instanceof Map))
        {
          paramzza = (Map)paramzza;
          this.zzbhN.push(paramzza);
          return;
        }
        if (!(paramzza instanceof List)) {
          break;
        }
        paramzza = ((List)paramzza).iterator();
        while (paramzza.hasNext())
        {
          paramSet = paramzza.next();
          if ((paramSet instanceof Map))
          {
            paramSet = (Map)paramSet;
            this.zzbhN.push(paramSet);
          }
          else
          {
            zzbg.zzaK("pushAfterEvaluate: value not a Map");
          }
        }
      }
    }
    zzbg.zzaK("pushAfterEvaluate: value not a Map or List");
  }
  
  private static void zza(List<zzrs.zza> paramList, List<String> paramList1, String paramString)
  {
    if (paramList.size() != paramList1.size()) {
      zzbg.zzaJ("Invalid resource: imbalance of rule names of functions for " + paramString + " operation. Using default rule name instead");
    }
  }
  
  private static void zza(Map<String, zzak> paramMap, zzak paramzzak)
  {
    if (paramMap.containsKey(paramzzak.zzGB())) {
      throw new IllegalArgumentException("Duplicate function type name: " + paramzzak.zzGB());
    }
    paramMap.put(paramzzak.zzGB(), paramzzak);
  }
  
  private static zzc zzi(Map<String, zzc> paramMap, String paramString)
  {
    zzc localzzc2 = (zzc)paramMap.get(paramString);
    zzc localzzc1 = localzzc2;
    if (localzzc2 == null)
    {
      localzzc1 = new zzc();
      paramMap.put(paramString, localzzc1);
    }
    return localzzc1;
  }
  
  public void zzF(List<zzaf.zzi> paramList)
  {
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (!paramList.hasNext()) {
          break;
        }
        zzaf.zzi localzzi = (zzaf.zzi)paramList.next();
        if ((localzzi.name == null) || (!localzzi.name.startsWith("gaExperiment:"))) {
          zzbg.v("Ignored supplemental: " + localzzi);
        } else {
          zzaj.zza(this.zzbhN, localzzi);
        }
      }
      finally {}
    }
  }
  
  String zzHe()
  {
    try
    {
      String str = this.zzbkA;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  zzbw<Boolean> zza(zzrs.zza paramzza, Set<String> paramSet, zzch paramzzch)
  {
    paramzza = zza(this.zzbku, paramzza, paramSet, paramzzch);
    paramSet = zzdf.zzk((zzag.zza)paramzza.getObject());
    paramzzch.zzd(zzdf.zzR(paramSet));
    return new zzbw(paramSet, paramzza.zzGP());
  }
  
  zzbw<Boolean> zza(zzrs.zze paramzze, Set<String> paramSet, zzck paramzzck)
  {
    Object localObject = paramzze.zzHQ().iterator();
    boolean bool = true;
    if (((Iterator)localObject).hasNext())
    {
      zzbw localzzbw = zza((zzrs.zza)((Iterator)localObject).next(), paramSet, paramzzck.zzGH());
      if (((Boolean)localzzbw.getObject()).booleanValue())
      {
        paramzzck.zzf(zzdf.zzR(Boolean.valueOf(false)));
        return new zzbw(Boolean.valueOf(false), localzzbw.zzGP());
      }
      if ((bool) && (localzzbw.zzGP())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    paramzze = paramzze.zzHP().iterator();
    while (paramzze.hasNext())
    {
      localObject = zza((zzrs.zza)paramzze.next(), paramSet, paramzzck.zzGI());
      if (!((Boolean)((zzbw)localObject).getObject()).booleanValue())
      {
        paramzzck.zzf(zzdf.zzR(Boolean.valueOf(false)));
        return new zzbw(Boolean.valueOf(false), ((zzbw)localObject).zzGP());
      }
      if ((bool) && (((zzbw)localObject).zzGP())) {
        bool = true;
      } else {
        bool = false;
      }
    }
    paramzzck.zzf(zzdf.zzR(Boolean.valueOf(true)));
    return new zzbw(Boolean.valueOf(true), bool);
  }
  
  zzbw<Set<zzrs.zza>> zza(String paramString, Set<zzrs.zze> paramSet, final Map<zzrs.zze, List<zzrs.zza>> paramMap1, final Map<zzrs.zze, List<String>> paramMap2, final Map<zzrs.zze, List<zzrs.zza>> paramMap3, final Map<zzrs.zze, List<String>> paramMap4, Set<String> paramSet1, zzco paramzzco)
  {
    zza(paramSet, paramSet1, new zza()
    {
      public void zza(zzrs.zze paramAnonymouszze, Set<zzrs.zza> paramAnonymousSet1, Set<zzrs.zza> paramAnonymousSet2, zzck paramAnonymouszzck)
      {
        List localList1 = (List)paramMap1.get(paramAnonymouszze);
        List localList2 = (List)paramMap2.get(paramAnonymouszze);
        if (localList1 != null)
        {
          paramAnonymousSet1.addAll(localList1);
          paramAnonymouszzck.zzGJ().zzc(localList1, localList2);
        }
        paramAnonymousSet1 = (List)paramMap3.get(paramAnonymouszze);
        paramAnonymouszze = (List)paramMap4.get(paramAnonymouszze);
        if (paramAnonymousSet1 != null)
        {
          paramAnonymousSet2.addAll(paramAnonymousSet1);
          paramAnonymouszzck.zzGK().zzc(paramAnonymousSet1, paramAnonymouszze);
        }
      }
    }, paramzzco);
  }
  
  zzbw<Set<zzrs.zza>> zza(Set<zzrs.zze> paramSet, zzco paramzzco)
  {
    zza(paramSet, new HashSet(), new zza()
    {
      public void zza(zzrs.zze paramAnonymouszze, Set<zzrs.zza> paramAnonymousSet1, Set<zzrs.zza> paramAnonymousSet2, zzck paramAnonymouszzck)
      {
        paramAnonymousSet1.addAll(paramAnonymouszze.zzHR());
        paramAnonymousSet2.addAll(paramAnonymouszze.zzHS());
        paramAnonymouszzck.zzGL().zzc(paramAnonymouszze.zzHR(), paramAnonymouszze.zzHW());
        paramAnonymouszzck.zzGM().zzc(paramAnonymouszze.zzHS(), paramAnonymouszze.zzHX());
      }
    }, paramzzco);
  }
  
  void zza(zzak paramzzak)
  {
    zza(this.zzbkv, paramzzak);
  }
  
  void zzb(zzak paramzzak)
  {
    zza(this.zzbkt, paramzzak);
  }
  
  void zzc(zzak paramzzak)
  {
    zza(this.zzbku, paramzzak);
  }
  
  public void zzfR(String paramString)
  {
    try
    {
      zzgo(paramString);
      paramString = this.zzbks.zzge(paramString);
      zzu localzzu = paramString.zzGy();
      Iterator localIterator = ((Set)zza(this.zzbky, localzzu.zzGq()).getObject()).iterator();
      while (localIterator.hasNext())
      {
        zzrs.zza localzza = (zzrs.zza)localIterator.next();
        zza(this.zzbkt, localzza, new HashSet(), localzzu.zzGp());
      }
      paramString.zzGz();
    }
    finally {}
    zzgo(null);
  }
  
  public zzbw<zzag.zza> zzgn(String paramString)
  {
    this.zzbkB = 0;
    zzag localzzag = this.zzbks.zzgd(paramString);
    paramString = zza(paramString, new HashSet(), localzzag.zzGx());
    localzzag.zzGz();
    return paramString;
  }
  
  void zzgo(String paramString)
  {
    try
    {
      this.zzbkA = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  static abstract interface zza
  {
    public abstract void zza(zzrs.zze paramzze, Set<zzrs.zza> paramSet1, Set<zzrs.zza> paramSet2, zzck paramzzck);
  }
  
  private static class zzb
  {
    private zzbw<zzag.zza> zzbkH;
    private zzag.zza zzbkI;
    
    public zzb(zzbw<zzag.zza> paramzzbw, zzag.zza paramzza)
    {
      this.zzbkH = paramzzbw;
      this.zzbkI = paramzza;
    }
    
    public int getSize()
    {
      int j = ((zzag.zza)this.zzbkH.getObject()).getCachedSize();
      if (this.zzbkI == null) {}
      for (int i = 0;; i = this.zzbkI.getCachedSize()) {
        return i + j;
      }
    }
    
    public zzbw<zzag.zza> zzHg()
    {
      return this.zzbkH;
    }
    
    public zzag.zza zzHh()
    {
      return this.zzbkI;
    }
  }
  
  private static class zzc
  {
    private final Map<zzrs.zze, List<zzrs.zza>> zzbkJ = new HashMap();
    private final Map<zzrs.zze, List<zzrs.zza>> zzbkK = new HashMap();
    private final Map<zzrs.zze, List<String>> zzbkL = new HashMap();
    private final Map<zzrs.zze, List<String>> zzbkM = new HashMap();
    private zzrs.zza zzbkN;
    private final Set<zzrs.zze> zzbky = new HashSet();
    
    public Set<zzrs.zze> zzHi()
    {
      return this.zzbky;
    }
    
    public Map<zzrs.zze, List<zzrs.zza>> zzHj()
    {
      return this.zzbkJ;
    }
    
    public Map<zzrs.zze, List<String>> zzHk()
    {
      return this.zzbkL;
    }
    
    public Map<zzrs.zze, List<String>> zzHl()
    {
      return this.zzbkM;
    }
    
    public Map<zzrs.zze, List<zzrs.zza>> zzHm()
    {
      return this.zzbkK;
    }
    
    public zzrs.zza zzHn()
    {
      return this.zzbkN;
    }
    
    public void zza(zzrs.zze paramzze)
    {
      this.zzbky.add(paramzze);
    }
    
    public void zza(zzrs.zze paramzze, zzrs.zza paramzza)
    {
      List localList = (List)this.zzbkJ.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.zzbkJ.put(paramzze, localObject);
      }
      ((List)localObject).add(paramzza);
    }
    
    public void zza(zzrs.zze paramzze, String paramString)
    {
      List localList = (List)this.zzbkL.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.zzbkL.put(paramzze, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void zzb(zzrs.zza paramzza)
    {
      this.zzbkN = paramzza;
    }
    
    public void zzb(zzrs.zze paramzze, zzrs.zza paramzza)
    {
      List localList = (List)this.zzbkK.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.zzbkK.put(paramzze, localObject);
      }
      ((List)localObject).add(paramzza);
    }
    
    public void zzb(zzrs.zze paramzze, String paramString)
    {
      List localList = (List)this.zzbkM.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.zzbkM.put(paramzze, localObject);
      }
      ((List)localObject).add(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzcp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */