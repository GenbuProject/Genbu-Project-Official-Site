package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzbg;
import com.google.android.gms.tagmanager.zzdf;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzrs
{
  private static zzag.zza zza(int paramInt, zzaf.zzf paramzzf, zzag.zza[] paramArrayOfzza, Set<Integer> paramSet)
    throws zzrs.zzg
  {
    int k = 0;
    int m = 0;
    int j = 0;
    if (paramSet.contains(Integer.valueOf(paramInt))) {
      zzgC("Value cycle detected.  Current value reference: " + paramInt + "." + "  Previous value references: " + paramSet + ".");
    }
    zzag.zza localzza1 = (zzag.zza)zza(paramzzf.zziI, paramInt, "values");
    if (paramArrayOfzza[paramInt] != null) {
      return paramArrayOfzza[paramInt];
    }
    Object localObject = null;
    paramSet.add(Integer.valueOf(paramInt));
    switch (localzza1.type)
    {
    }
    for (;;)
    {
      if (localObject == null) {
        zzgC("Invalid value: " + localzza1);
      }
      paramArrayOfzza[paramInt] = localObject;
      paramSet.remove(Integer.valueOf(paramInt));
      return (zzag.zza)localObject;
      localObject = zzp(localzza1);
      zzag.zza localzza2 = zzo(localzza1);
      localzza2.zzjy = new zzag.zza[((zzaf.zzh)localObject).zzjj.length];
      int[] arrayOfInt = ((zzaf.zzh)localObject).zzjj;
      k = arrayOfInt.length;
      int i = 0;
      for (;;)
      {
        localObject = localzza2;
        if (j >= k) {
          break;
        }
        m = arrayOfInt[j];
        localzza2.zzjy[i] = zza(m, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      localzza2 = zzo(localzza1);
      localObject = zzp(localzza1);
      if (((zzaf.zzh)localObject).zzjk.length != ((zzaf.zzh)localObject).zzjl.length) {
        zzgC("Uneven map keys (" + ((zzaf.zzh)localObject).zzjk.length + ") and map values (" + ((zzaf.zzh)localObject).zzjl.length + ")");
      }
      localzza2.zzjz = new zzag.zza[((zzaf.zzh)localObject).zzjk.length];
      localzza2.zzjA = new zzag.zza[((zzaf.zzh)localObject).zzjk.length];
      arrayOfInt = ((zzaf.zzh)localObject).zzjk;
      m = arrayOfInt.length;
      j = 0;
      i = 0;
      while (j < m)
      {
        int n = arrayOfInt[j];
        localzza2.zzjz[i] = zza(n, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      arrayOfInt = ((zzaf.zzh)localObject).zzjl;
      m = arrayOfInt.length;
      i = 0;
      j = k;
      for (;;)
      {
        localObject = localzza2;
        if (j >= m) {
          break;
        }
        k = arrayOfInt[j];
        localzza2.zzjA[i] = zza(k, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      localObject = zzo(localzza1);
      ((zzag.zza)localObject).zzjB = zzdf.zzg(zza(zzp(localzza1).zzjo, paramzzf, paramArrayOfzza, paramSet));
      continue;
      localzza2 = zzo(localzza1);
      localObject = zzp(localzza1);
      localzza2.zzjF = new zzag.zza[((zzaf.zzh)localObject).zzjn.length];
      arrayOfInt = ((zzaf.zzh)localObject).zzjn;
      k = arrayOfInt.length;
      i = 0;
      j = m;
      for (;;)
      {
        localObject = localzza2;
        if (j >= k) {
          break;
        }
        m = arrayOfInt[j];
        localzza2.zzjF[i] = zza(m, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      localObject = localzza1;
    }
  }
  
  private static zza zza(zzaf.zzb paramzzb, zzaf.zzf paramzzf, zzag.zza[] paramArrayOfzza, int paramInt)
    throws zzrs.zzg
  {
    zzb localzzb = zza.zzHH();
    paramzzb = paramzzb.zzit;
    int i = paramzzb.length;
    paramInt = 0;
    if (paramInt < i)
    {
      int j = paramzzb[paramInt];
      Object localObject = (zzaf.zze)zza(paramzzf.zziJ, Integer.valueOf(j).intValue(), "properties");
      String str = (String)zza(paramzzf.zziH, ((zzaf.zze)localObject).key, "keys");
      localObject = (zzag.zza)zza(paramArrayOfzza, ((zzaf.zze)localObject).value, "values");
      if (zzae.zzgU.toString().equals(str)) {
        localzzb.zzq((zzag.zza)localObject);
      }
      for (;;)
      {
        paramInt += 1;
        break;
        localzzb.zzb(str, (zzag.zza)localObject);
      }
    }
    return localzzb.zzHJ();
  }
  
  private static zze zza(zzaf.zzg paramzzg, List<zza> paramList1, List<zza> paramList2, List<zza> paramList3, zzaf.zzf paramzzf)
  {
    zzf localzzf = zze.zzHO();
    int[] arrayOfInt = paramzzg.zziX;
    int j = arrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      localzzf.zzd((zza)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
      i += 1;
    }
    arrayOfInt = paramzzg.zziY;
    j = arrayOfInt.length;
    i = 0;
    while (i < j)
    {
      localzzf.zze((zza)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
      i += 1;
    }
    paramList3 = paramzzg.zziZ;
    j = paramList3.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzf((zza)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
      i += 1;
    }
    paramList3 = paramzzg.zzjb;
    j = paramList3.length;
    i = 0;
    int k;
    while (i < j)
    {
      k = paramList3[i];
      localzzf.zzgE(paramzzf.zziI[Integer.valueOf(k).intValue()].zzjx);
      i += 1;
    }
    paramList3 = paramzzg.zzja;
    j = paramList3.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzg((zza)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
      i += 1;
    }
    paramList1 = paramzzg.zzjc;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      k = paramList1[i];
      localzzf.zzgF(paramzzf.zziI[Integer.valueOf(k).intValue()].zzjx);
      i += 1;
    }
    paramList1 = paramzzg.zzjd;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzh((zza)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
      i += 1;
    }
    paramList1 = paramzzg.zzjf;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      k = paramList1[i];
      localzzf.zzgG(paramzzf.zziI[Integer.valueOf(k).intValue()].zzjx);
      i += 1;
    }
    paramList1 = paramzzg.zzje;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzi((zza)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
      i += 1;
    }
    paramzzg = paramzzg.zzjg;
    j = paramzzg.length;
    i = 0;
    while (i < j)
    {
      k = paramzzg[i];
      localzzf.zzgH(paramzzf.zziI[Integer.valueOf(k).intValue()].zzjx);
      i += 1;
    }
    return localzzf.zzHZ();
  }
  
  private static <T> T zza(T[] paramArrayOfT, int paramInt, String paramString)
    throws zzrs.zzg
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfT.length)) {
      zzgC("Index out of bounds detected: " + paramInt + " in " + paramString);
    }
    return paramArrayOfT[paramInt];
  }
  
  public static zzc zzb(zzaf.zzf paramzzf)
    throws zzrs.zzg
  {
    int j = 0;
    Object localObject = new zzag.zza[paramzzf.zziI.length];
    int i = 0;
    while (i < paramzzf.zziI.length)
    {
      zza(i, paramzzf, (zzag.zza[])localObject, new HashSet(0));
      i += 1;
    }
    zzd localzzd = zzc.zzHK();
    ArrayList localArrayList1 = new ArrayList();
    i = 0;
    while (i < paramzzf.zziL.length)
    {
      localArrayList1.add(zza(paramzzf.zziL[i], paramzzf, (zzag.zza[])localObject, i));
      i += 1;
    }
    ArrayList localArrayList2 = new ArrayList();
    i = 0;
    while (i < paramzzf.zziM.length)
    {
      localArrayList2.add(zza(paramzzf.zziM[i], paramzzf, (zzag.zza[])localObject, i));
      i += 1;
    }
    ArrayList localArrayList3 = new ArrayList();
    i = 0;
    while (i < paramzzf.zziK.length)
    {
      zza localzza = zza(paramzzf.zziK[i], paramzzf, (zzag.zza[])localObject, i);
      localzzd.zzc(localzza);
      localArrayList3.add(localzza);
      i += 1;
    }
    localObject = paramzzf.zziN;
    int k = localObject.length;
    i = j;
    while (i < k)
    {
      localzzd.zzb(zza(localObject[i], localArrayList1, localArrayList3, localArrayList2, paramzzf));
      i += 1;
    }
    localzzd.zzgD(paramzzf.version);
    localzzd.zzko(paramzzf.zziV);
    return localzzd.zzHN();
  }
  
  public static void zzb(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['?'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private static void zzgC(String paramString)
    throws zzrs.zzg
  {
    zzbg.e(paramString);
    throw new zzg(paramString);
  }
  
  public static zzag.zza zzo(zzag.zza paramzza)
  {
    zzag.zza localzza = new zzag.zza();
    localzza.type = paramzza.type;
    localzza.zzjG = ((int[])paramzza.zzjG.clone());
    if (paramzza.zzjH) {
      localzza.zzjH = paramzza.zzjH;
    }
    return localzza;
  }
  
  private static zzaf.zzh zzp(zzag.zza paramzza)
    throws zzrs.zzg
  {
    if ((zzaf.zzh)paramzza.zza(zzaf.zzh.zzjh) == null) {
      zzgC("Expected a ServingValue and didn't get one. Value is: " + paramzza);
    }
    return (zzaf.zzh)paramzza.zza(zzaf.zzh.zzjh);
  }
  
  public static class zza
  {
    private final zzag.zza zzbkI;
    private final Map<String, zzag.zza> zzbmi;
    
    private zza(Map<String, zzag.zza> paramMap, zzag.zza paramzza)
    {
      this.zzbmi = paramMap;
      this.zzbkI = paramzza;
    }
    
    public static zzrs.zzb zzHH()
    {
      return new zzrs.zzb(null);
    }
    
    public String toString()
    {
      return "Properties: " + zzHI() + " pushAfterEvaluate: " + this.zzbkI;
    }
    
    public Map<String, zzag.zza> zzHI()
    {
      return Collections.unmodifiableMap(this.zzbmi);
    }
    
    public zzag.zza zzHh()
    {
      return this.zzbkI;
    }
    
    public void zza(String paramString, zzag.zza paramzza)
    {
      this.zzbmi.put(paramString, paramzza);
    }
  }
  
  public static class zzb
  {
    private zzag.zza zzbkI;
    private final Map<String, zzag.zza> zzbmi = new HashMap();
    
    public zzrs.zza zzHJ()
    {
      return new zzrs.zza(this.zzbmi, this.zzbkI, null);
    }
    
    public zzb zzb(String paramString, zzag.zza paramzza)
    {
      this.zzbmi.put(paramString, paramzza);
      return this;
    }
    
    public zzb zzq(zzag.zza paramzza)
    {
      this.zzbkI = paramzza;
      return this;
    }
  }
  
  public static class zzc
  {
    private final String zzadc;
    private final List<zzrs.zze> zzbmj;
    private final Map<String, List<zzrs.zza>> zzbmk;
    private final int zzbml;
    
    private zzc(List<zzrs.zze> paramList, Map<String, List<zzrs.zza>> paramMap, String paramString, int paramInt)
    {
      this.zzbmj = Collections.unmodifiableList(paramList);
      this.zzbmk = Collections.unmodifiableMap(paramMap);
      this.zzadc = paramString;
      this.zzbml = paramInt;
    }
    
    public static zzrs.zzd zzHK()
    {
      return new zzrs.zzd(null);
    }
    
    public String getVersion()
    {
      return this.zzadc;
    }
    
    public String toString()
    {
      return "Rules: " + zzHL() + "  Macros: " + this.zzbmk;
    }
    
    public List<zzrs.zze> zzHL()
    {
      return this.zzbmj;
    }
    
    public Map<String, List<zzrs.zza>> zzHM()
    {
      return this.zzbmk;
    }
  }
  
  public static class zzd
  {
    private String zzadc = "";
    private final List<zzrs.zze> zzbmj = new ArrayList();
    private final Map<String, List<zzrs.zza>> zzbmk = new HashMap();
    private int zzbml = 0;
    
    public zzrs.zzc zzHN()
    {
      return new zzrs.zzc(this.zzbmj, this.zzbmk, this.zzadc, this.zzbml, null);
    }
    
    public zzd zzb(zzrs.zze paramzze)
    {
      this.zzbmj.add(paramzze);
      return this;
    }
    
    public zzd zzc(zzrs.zza paramzza)
    {
      String str = zzdf.zzg((zzag.zza)paramzza.zzHI().get(zzae.zzfR.toString()));
      List localList = (List)this.zzbmk.get(str);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.zzbmk.put(str, localObject);
      }
      ((List)localObject).add(paramzza);
      return this;
    }
    
    public zzd zzgD(String paramString)
    {
      this.zzadc = paramString;
      return this;
    }
    
    public zzd zzko(int paramInt)
    {
      this.zzbml = paramInt;
      return this;
    }
  }
  
  public static class zze
  {
    private final List<zzrs.zza> zzbmm;
    private final List<zzrs.zza> zzbmn;
    private final List<zzrs.zza> zzbmo;
    private final List<zzrs.zza> zzbmp;
    private final List<zzrs.zza> zzbmq;
    private final List<zzrs.zza> zzbmr;
    private final List<String> zzbms;
    private final List<String> zzbmt;
    private final List<String> zzbmu;
    private final List<String> zzbmv;
    
    private zze(List<zzrs.zza> paramList1, List<zzrs.zza> paramList2, List<zzrs.zza> paramList3, List<zzrs.zza> paramList4, List<zzrs.zza> paramList5, List<zzrs.zza> paramList6, List<String> paramList7, List<String> paramList8, List<String> paramList9, List<String> paramList10)
    {
      this.zzbmm = Collections.unmodifiableList(paramList1);
      this.zzbmn = Collections.unmodifiableList(paramList2);
      this.zzbmo = Collections.unmodifiableList(paramList3);
      this.zzbmp = Collections.unmodifiableList(paramList4);
      this.zzbmq = Collections.unmodifiableList(paramList5);
      this.zzbmr = Collections.unmodifiableList(paramList6);
      this.zzbms = Collections.unmodifiableList(paramList7);
      this.zzbmt = Collections.unmodifiableList(paramList8);
      this.zzbmu = Collections.unmodifiableList(paramList9);
      this.zzbmv = Collections.unmodifiableList(paramList10);
    }
    
    public static zzrs.zzf zzHO()
    {
      return new zzrs.zzf(null);
    }
    
    public String toString()
    {
      return "Positive predicates: " + zzHP() + "  Negative predicates: " + zzHQ() + "  Add tags: " + zzHR() + "  Remove tags: " + zzHS() + "  Add macros: " + zzHT() + "  Remove macros: " + zzHY();
    }
    
    public List<zzrs.zza> zzHP()
    {
      return this.zzbmm;
    }
    
    public List<zzrs.zza> zzHQ()
    {
      return this.zzbmn;
    }
    
    public List<zzrs.zza> zzHR()
    {
      return this.zzbmo;
    }
    
    public List<zzrs.zza> zzHS()
    {
      return this.zzbmp;
    }
    
    public List<zzrs.zza> zzHT()
    {
      return this.zzbmq;
    }
    
    public List<String> zzHU()
    {
      return this.zzbms;
    }
    
    public List<String> zzHV()
    {
      return this.zzbmt;
    }
    
    public List<String> zzHW()
    {
      return this.zzbmu;
    }
    
    public List<String> zzHX()
    {
      return this.zzbmv;
    }
    
    public List<zzrs.zza> zzHY()
    {
      return this.zzbmr;
    }
  }
  
  public static class zzf
  {
    private final List<zzrs.zza> zzbmm = new ArrayList();
    private final List<zzrs.zza> zzbmn = new ArrayList();
    private final List<zzrs.zza> zzbmo = new ArrayList();
    private final List<zzrs.zza> zzbmp = new ArrayList();
    private final List<zzrs.zza> zzbmq = new ArrayList();
    private final List<zzrs.zza> zzbmr = new ArrayList();
    private final List<String> zzbms = new ArrayList();
    private final List<String> zzbmt = new ArrayList();
    private final List<String> zzbmu = new ArrayList();
    private final List<String> zzbmv = new ArrayList();
    
    public zzrs.zze zzHZ()
    {
      return new zzrs.zze(this.zzbmm, this.zzbmn, this.zzbmo, this.zzbmp, this.zzbmq, this.zzbmr, this.zzbms, this.zzbmt, this.zzbmu, this.zzbmv, null);
    }
    
    public zzf zzd(zzrs.zza paramzza)
    {
      this.zzbmm.add(paramzza);
      return this;
    }
    
    public zzf zze(zzrs.zza paramzza)
    {
      this.zzbmn.add(paramzza);
      return this;
    }
    
    public zzf zzf(zzrs.zza paramzza)
    {
      this.zzbmo.add(paramzza);
      return this;
    }
    
    public zzf zzg(zzrs.zza paramzza)
    {
      this.zzbmp.add(paramzza);
      return this;
    }
    
    public zzf zzgE(String paramString)
    {
      this.zzbmu.add(paramString);
      return this;
    }
    
    public zzf zzgF(String paramString)
    {
      this.zzbmv.add(paramString);
      return this;
    }
    
    public zzf zzgG(String paramString)
    {
      this.zzbms.add(paramString);
      return this;
    }
    
    public zzf zzgH(String paramString)
    {
      this.zzbmt.add(paramString);
      return this;
    }
    
    public zzf zzh(zzrs.zza paramzza)
    {
      this.zzbmq.add(paramzza);
      return this;
    }
    
    public zzf zzi(zzrs.zza paramzza)
    {
      this.zzbmr.add(paramzza);
      return this;
    }
  }
  
  public static class zzg
    extends Exception
  {
    public zzg(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzrs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */