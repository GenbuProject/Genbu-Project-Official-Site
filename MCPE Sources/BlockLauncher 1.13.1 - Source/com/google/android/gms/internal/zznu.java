package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zznu
{
  public static final Set<String> AGGREGATE_INPUT_TYPES;
  private static final Map<String, List<zzsy.zza>> zzawC;
  public static final zzsy.zza zzayA = zza("com.google.step_count.delta", new zzsy.zzb[] { zznt.zzaxz });
  public static final zzsy.zza zzayB = zza("com.google.step_count.cumulative", new zzsy.zzb[] { zznt.zzaxz });
  public static final zzsy.zza zzayC = zza("com.google.step_count.cadence", new zzsy.zzb[] { zznt.zzaxR });
  public static final zzsy.zza zzayD = zza("com.google.activity.segment", new zzsy.zzb[] { zznt.zzaxw });
  public static final zzsy.zza zzayE = zza("com.google.floor_change", new zzsy.zzb[] { zznt.zzaxw, zznt.zzaxx, zznt.zzaxY, zznt.zzayb });
  public static final zzsy.zza zzayF = zza("com.google.calories.consumed", new zzsy.zzb[] { zznt.zzaxT });
  public static final zzsy.zza zzayG = zza("com.google.calories.expended", new zzsy.zzb[] { zznt.zzaxT });
  public static final zzsy.zza zzayH = zza("com.google.calories.bmr", new zzsy.zzb[] { zznt.zzaxT });
  public static final zzsy.zza zzayI = zza("com.google.power.sample", new zzsy.zzb[] { zznt.zzaxU });
  public static final zzsy.zza zzayJ = zza("com.google.activity.sample", new zzsy.zzb[] { zznt.zzaxw, zznt.zzaxx });
  public static final zzsy.zza zzayK = zza("com.google.accelerometer", new zzsy.zzb[] { zznt.zzayq, zznt.zzayr, zznt.zzays });
  public static final zzsy.zza zzayL = zza("com.google.sensor.events", new zzsy.zzb[] { zznt.zzayv, zznt.zzayt, zznt.zzayu });
  public static final zzsy.zza zzayM = zza("com.google.internal.goal", new zzsy.zzb[] { zznt.zzaxK });
  public static final zzsy.zza zzayN = zza("com.google.heart_rate.bpm", new zzsy.zzb[] { zznt.zzaxE });
  public static final zzsy.zza zzayO = zza("com.google.location.sample", new zzsy.zzb[] { zznt.zzaxF, zznt.zzaxG, zznt.zzaxH, zznt.zzaxI });
  public static final zzsy.zza zzayP = zza("com.google.location.track", new zzsy.zzb[] { zznt.zzaxF, zznt.zzaxG, zznt.zzaxH, zznt.zzaxI });
  public static final zzsy.zza zzayQ = zza("com.google.distance.delta", new zzsy.zzb[] { zznt.zzaxJ });
  public static final zzsy.zza zzayR = zza("com.google.distance.cumulative", new zzsy.zzb[] { zznt.zzaxJ });
  public static final zzsy.zza zzayS = zza("com.google.speed", new zzsy.zzb[] { zznt.zzaxQ });
  public static final zzsy.zza zzayT = zza("com.google.cycling.wheel_revolution.cumulative", new zzsy.zzb[] { zznt.zzaxS });
  public static final zzsy.zza zzayU = zza("com.google.cycling.wheel_revolution.rpm", new zzsy.zzb[] { zznt.zzaxR });
  public static final zzsy.zza zzayV = zza("com.google.cycling.pedaling.cumulative", new zzsy.zzb[] { zznt.zzaxS });
  public static final zzsy.zza zzayW = zza("com.google.cycling.pedaling.cadence", new zzsy.zzb[] { zznt.zzaxR });
  public static final zzsy.zza zzayX = zza("com.google.height", new zzsy.zzb[] { zznt.zzaxM });
  public static final zzsy.zza zzayY = zza("com.google.weight", new zzsy.zzb[] { zznt.zzaxN });
  public static final zzsy.zza zzayZ = zza("com.google.body.fat.percentage", new zzsy.zzb[] { zznt.zzaxP });
  public static final zzsy.zza zzaza = zza("com.google.body.waist.circumference", new zzsy.zzb[] { zznt.zzaxO });
  public static final zzsy.zza zzazb = zza("com.google.body.hip.circumference", new zzsy.zzb[] { zznt.zzaxO });
  public static final zzsy.zza zzazc = zza("com.google.nutrition", new zzsy.zzb[] { zznt.zzaxX, zznt.zzaxV, zznt.zzaxW });
  public static final zzsy.zza zzazd = zza("com.google.activity.exercise", new zzsy.zzb[] { zznt.zzaye, zznt.zzayf, zznt.zzaxA, zznt.zzayh, zznt.zzayg });
  public static final zzsy.zza zzaze;
  public static final zzsy.zza zzazf;
  public static final zzsy.zza zzazg;
  public static final zzsy.zza zzazh;
  public static final zzsy.zza zzazi;
  public static final zzsy.zza zzazj;
  public static final zzsy.zza zzazk;
  public static final zzsy.zza zzazl;
  public static final zzsy.zza zzazm;
  public static final zzsy.zza zzazn;
  public static final zzsy.zza zzazo;
  public static final zzsy.zza zzazp;
  public static final zzsy.zza zzazq;
  public static final zzsy.zza zzazr;
  public static final zzsy.zza zzazs;
  public static final zzsy.zza zzazt;
  public static final zzsy.zza zzazu;
  public static final String[] zzazv;
  private static final Map<zzsy.zza, zza> zzazw;
  
  static
  {
    AGGREGATE_INPUT_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { zzayD.name, zzayF.name, zzayG.name, zzayQ.name, zzayE.name, zzayN.name, zzayO.name, zzazc.name, zzayS.name, zzayA.name, zzayY.name })));
    zzaze = zza("com.google.activity.summary", new zzsy.zzb[] { zznt.zzaxw, zznt.zzaxA, zznt.zzayi });
    zzazf = zza("com.google.floor_change.summary", new zzsy.zzb[] { zznt.zzaxC, zznt.zzaxD, zznt.zzaxZ, zznt.zzaya, zznt.zzayc, zznt.zzayd });
    zzazg = zzayA;
    zzazh = zzayQ;
    zzazi = zzayF;
    zzazj = zzayG;
    zzazk = zza("com.google.heart_rate.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazl = zza("com.google.location.bounding_box", new zzsy.zzb[] { zznt.zzaym, zznt.zzayn, zznt.zzayo, zznt.zzayp });
    zzazm = zza("com.google.power.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazn = zza("com.google.speed.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazo = zza("com.google.weight.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazp = zza("com.google.calories.bmr.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazq = zza("com.google.body.fat.percentage.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazr = zza("com.google.body.hip.circumference.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazs = zza("com.google.body.waist.circumference.summary", new zzsy.zzb[] { zznt.zzayj, zznt.zzayk, zznt.zzayl });
    zzazt = zza("com.google.nutrition.summary", new zzsy.zzb[] { zznt.zzaxX, zznt.zzaxV });
    zzazu = zza("com.google.internal.session", new zzsy.zzb[] { zznt.zzayw, zznt.zzaxw, zznt.zzayx, zznt.zzayy, zznt.zzayz });
    zzawC = zzuF();
    zzazv = new String[] { "com.google.accelerometer", "com.google.activity.exercise", "com.google.activity.sample", "com.google.activity.segment", "com.google.activity.summary", "com.google.body.fat.percentage", "com.google.body.fat.percentage.summary", "com.google.body.hip.circumference", "com.google.body.hip.circumference.summary", "com.google.body.waist.circumference", "com.google.body.waist.circumference.summary", "com.google.calories.bmr", "com.google.calories.bmr.summary", "com.google.calories.consumed", "com.google.calories.expended", "com.google.cycling.pedaling.cadence", "com.google.cycling.pedaling.cumulative", "com.google.cycling.wheel_revolution.cumulative", "com.google.cycling.wheel_revolution.rpm", "com.google.distance.cumulative", "com.google.distance.delta", "com.google.floor_change", "com.google.floor_change.summary", "com.google.heart_rate.bpm", "com.google.heart_rate.summary", "com.google.height", "com.google.internal.goal", "com.google.internal.session", "com.google.location.bounding_box", "com.google.location.sample", "com.google.location.track", "com.google.nutrition", "com.google.nutrition.summary", "com.google.power.sample", "com.google.power.summary", "com.google.sensor.events", "com.google.speed", "com.google.speed.summary", "com.google.step_count.cadence", "com.google.step_count.cumulative", "com.google.step_count.delta", "com.google.weight", "com.google.weight.summary" };
    HashSet localHashSet1 = new HashSet();
    localHashSet1.add(zzayB);
    localHashSet1.add(zzayR);
    localHashSet1.add(zzayV);
    HashSet localHashSet2 = new HashSet();
    localHashSet2.add(zzayQ);
    localHashSet2.add(zzayA);
    localHashSet2.add(zzayG);
    localHashSet2.add(zzayF);
    localHashSet2.add(zzayE);
    HashSet localHashSet3 = new HashSet();
    localHashSet3.add(zzayZ);
    localHashSet3.add(zzazb);
    localHashSet3.add(zzaza);
    localHashSet3.add(zzazc);
    localHashSet3.add(zzayX);
    localHashSet3.add(zzayY);
    localHashSet3.add(zzayN);
    HashMap localHashMap = new HashMap();
    zza(localHashMap, localHashSet1, zza.zzazx);
    zza(localHashMap, localHashSet2, zza.zzazy);
    zza(localHashMap, localHashSet3, zza.zzazz);
    zzazw = Collections.unmodifiableMap(localHashMap);
  }
  
  public static zzsy.zza zza(String paramString, zzsy.zzb... paramVarArgs)
  {
    zzsy.zza localzza = new zzsy.zza();
    localzza.name = paramString;
    localzza.zzbuE = paramVarArgs;
    return localzza;
  }
  
  private static void zza(Map<zzsy.zza, zza> paramMap, Collection<zzsy.zza> paramCollection, zza paramzza)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      paramMap.put((zzsy.zza)paramCollection.next(), paramzza);
    }
  }
  
  public static boolean zzdD(String paramString)
  {
    return Arrays.binarySearch(zzazv, paramString) >= 0;
  }
  
  private static Map<String, List<zzsy.zza>> zzuF()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(zzayD.name, Collections.singletonList(zzaze));
    localHashMap.put(zzayF.name, Collections.singletonList(zzazi));
    localHashMap.put(zzayG.name, Collections.singletonList(zzazj));
    localHashMap.put(zzayQ.name, Collections.singletonList(zzazh));
    localHashMap.put(zzayE.name, Collections.singletonList(zzazf));
    localHashMap.put(zzayO.name, Collections.singletonList(zzazl));
    localHashMap.put(zzayI.name, Collections.singletonList(zzazm));
    localHashMap.put(zzayN.name, Collections.singletonList(zzazk));
    localHashMap.put(zzayS.name, Collections.singletonList(zzazn));
    localHashMap.put(zzayA.name, Collections.singletonList(zzazg));
    localHashMap.put(zzayY.name, Collections.singletonList(zzazo));
    return localHashMap;
  }
  
  public static enum zza
  {
    private zza() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zznu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */