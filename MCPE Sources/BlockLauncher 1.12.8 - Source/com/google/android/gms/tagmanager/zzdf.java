package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzdf
{
  private static final Object zzblE = null;
  private static Long zzblF = new Long(0L);
  private static Double zzblG = new Double(0.0D);
  private static zzde zzblH = zzde.zzam(0L);
  private static String zzblI = new String("");
  private static Boolean zzblJ = new Boolean(false);
  private static List<Object> zzblK = new ArrayList(0);
  private static Map<Object, Object> zzblL = new HashMap();
  private static zzag.zza zzblM = zzR(zzblI);
  
  private static double getDouble(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).doubleValue();
    }
    zzbg.e("getDouble received non-Number");
    return 0.0D;
  }
  
  public static Long zzHA()
  {
    return zzblF;
  }
  
  public static Double zzHB()
  {
    return zzblG;
  }
  
  public static Boolean zzHC()
  {
    return zzblJ;
  }
  
  public static zzde zzHD()
  {
    return zzblH;
  }
  
  public static String zzHE()
  {
    return zzblI;
  }
  
  public static zzag.zza zzHF()
  {
    return zzblM;
  }
  
  public static Object zzHz()
  {
    return zzblE;
  }
  
  public static String zzM(Object paramObject)
  {
    if (paramObject == null) {
      return zzblI;
    }
    return paramObject.toString();
  }
  
  public static zzde zzN(Object paramObject)
  {
    if ((paramObject instanceof zzde)) {
      return (zzde)paramObject;
    }
    if (zzT(paramObject)) {
      return zzde.zzam(zzU(paramObject));
    }
    if (zzS(paramObject)) {
      return zzde.zza(Double.valueOf(getDouble(paramObject)));
    }
    return zzgu(zzM(paramObject));
  }
  
  public static Long zzO(Object paramObject)
  {
    if (zzT(paramObject)) {
      return Long.valueOf(zzU(paramObject));
    }
    return zzgv(zzM(paramObject));
  }
  
  public static Double zzP(Object paramObject)
  {
    if (zzS(paramObject)) {
      return Double.valueOf(getDouble(paramObject));
    }
    return zzgw(zzM(paramObject));
  }
  
  public static Boolean zzQ(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return (Boolean)paramObject;
    }
    return zzgx(zzM(paramObject));
  }
  
  public static zzag.zza zzR(Object paramObject)
  {
    boolean bool = false;
    Object localObject1 = new zzag.zza();
    if ((paramObject instanceof zzag.zza)) {
      return (zzag.zza)paramObject;
    }
    if ((paramObject instanceof String))
    {
      ((zzag.zza)localObject1).type = 1;
      ((zzag.zza)localObject1).zzjx = ((String)paramObject);
    }
    for (;;)
    {
      ((zzag.zza)localObject1).zzjH = bool;
      return (zzag.zza)localObject1;
      Object localObject2;
      Object localObject3;
      if ((paramObject instanceof List))
      {
        ((zzag.zza)localObject1).type = 2;
        localObject2 = (List)paramObject;
        paramObject = new ArrayList(((List)localObject2).size());
        localObject2 = ((List)localObject2).iterator();
        bool = false;
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = zzR(((Iterator)localObject2).next());
          if (localObject3 == zzblM) {
            return zzblM;
          }
          if ((bool) || (((zzag.zza)localObject3).zzjH)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localObject3);
            break;
          }
        }
        ((zzag.zza)localObject1).zzjy = ((zzag.zza[])((List)paramObject).toArray(new zzag.zza[0]));
      }
      else if ((paramObject instanceof Map))
      {
        ((zzag.zza)localObject1).type = 3;
        localObject3 = ((Map)paramObject).entrySet();
        paramObject = new ArrayList(((Set)localObject3).size());
        localObject2 = new ArrayList(((Set)localObject3).size());
        localObject3 = ((Set)localObject3).iterator();
        bool = false;
        if (((Iterator)localObject3).hasNext())
        {
          Object localObject4 = (Map.Entry)((Iterator)localObject3).next();
          zzag.zza localzza = zzR(((Map.Entry)localObject4).getKey());
          localObject4 = zzR(((Map.Entry)localObject4).getValue());
          if ((localzza == zzblM) || (localObject4 == zzblM)) {
            return zzblM;
          }
          if ((bool) || (localzza.zzjH) || (((zzag.zza)localObject4).zzjH)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localzza);
            ((List)localObject2).add(localObject4);
            break;
          }
        }
        ((zzag.zza)localObject1).zzjz = ((zzag.zza[])((List)paramObject).toArray(new zzag.zza[0]));
        ((zzag.zza)localObject1).zzjA = ((zzag.zza[])((List)localObject2).toArray(new zzag.zza[0]));
      }
      else if (zzS(paramObject))
      {
        ((zzag.zza)localObject1).type = 1;
        ((zzag.zza)localObject1).zzjx = paramObject.toString();
      }
      else if (zzT(paramObject))
      {
        ((zzag.zza)localObject1).type = 6;
        ((zzag.zza)localObject1).zzjD = zzU(paramObject);
      }
      else
      {
        if (!(paramObject instanceof Boolean)) {
          break;
        }
        ((zzag.zza)localObject1).type = 8;
        ((zzag.zza)localObject1).zzjE = ((Boolean)paramObject).booleanValue();
      }
    }
    localObject1 = new StringBuilder().append("Converting to Value from unknown object type: ");
    if (paramObject == null) {}
    for (paramObject = "null";; paramObject = paramObject.getClass().toString())
    {
      zzbg.e((String)paramObject);
      return zzblM;
    }
  }
  
  private static boolean zzS(Object paramObject)
  {
    return ((paramObject instanceof Double)) || ((paramObject instanceof Float)) || (((paramObject instanceof zzde)) && (((zzde)paramObject).zzHu()));
  }
  
  private static boolean zzT(Object paramObject)
  {
    return ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || (((paramObject instanceof zzde)) && (((zzde)paramObject).zzHv()));
  }
  
  private static long zzU(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).longValue();
    }
    zzbg.e("getInt64 received non-Number");
    return 0L;
  }
  
  public static String zzg(zzag.zza paramzza)
  {
    return zzM(zzl(paramzza));
  }
  
  public static zzag.zza zzgt(String paramString)
  {
    zzag.zza localzza = new zzag.zza();
    localzza.type = 5;
    localzza.zzjC = paramString;
    return localzza;
  }
  
  private static zzde zzgu(String paramString)
  {
    try
    {
      zzde localzzde = zzde.zzgs(paramString);
      return localzzde;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      zzbg.e("Failed to convert '" + paramString + "' to a number.");
    }
    return zzblH;
  }
  
  private static Long zzgv(String paramString)
  {
    paramString = zzgu(paramString);
    if (paramString == zzblH) {
      return zzblF;
    }
    return Long.valueOf(paramString.longValue());
  }
  
  private static Double zzgw(String paramString)
  {
    paramString = zzgu(paramString);
    if (paramString == zzblH) {
      return zzblG;
    }
    return Double.valueOf(paramString.doubleValue());
  }
  
  private static Boolean zzgx(String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      return Boolean.TRUE;
    }
    if ("false".equalsIgnoreCase(paramString)) {
      return Boolean.FALSE;
    }
    return zzblJ;
  }
  
  public static zzde zzh(zzag.zza paramzza)
  {
    return zzN(zzl(paramzza));
  }
  
  public static Long zzi(zzag.zza paramzza)
  {
    return zzO(zzl(paramzza));
  }
  
  public static Double zzj(zzag.zza paramzza)
  {
    return zzP(zzl(paramzza));
  }
  
  public static Boolean zzk(zzag.zza paramzza)
  {
    return zzQ(zzl(paramzza));
  }
  
  public static Object zzl(zzag.zza paramzza)
  {
    int k = 0;
    int j = 0;
    int i = 0;
    if (paramzza == null) {
      return zzblE;
    }
    Object localObject1;
    Object localObject2;
    switch (paramzza.type)
    {
    default: 
      zzbg.e("Failed to convert a value of type: " + paramzza.type);
      return zzblE;
    case 1: 
      return paramzza.zzjx;
    case 2: 
      localObject1 = new ArrayList(paramzza.zzjy.length);
      paramzza = paramzza.zzjy;
      j = paramzza.length;
      while (i < j)
      {
        localObject2 = zzl(paramzza[i]);
        if (localObject2 == zzblE) {
          return zzblE;
        }
        ((ArrayList)localObject1).add(localObject2);
        i += 1;
      }
      return localObject1;
    case 3: 
      if (paramzza.zzjz.length != paramzza.zzjA.length)
      {
        zzbg.e("Converting an invalid value to object: " + paramzza.toString());
        return zzblE;
      }
      localObject1 = new HashMap(paramzza.zzjA.length);
      i = k;
      while (i < paramzza.zzjz.length)
      {
        localObject2 = zzl(paramzza.zzjz[i]);
        Object localObject3 = zzl(paramzza.zzjA[i]);
        if ((localObject2 == zzblE) || (localObject3 == zzblE)) {
          return zzblE;
        }
        ((Map)localObject1).put(localObject2, localObject3);
        i += 1;
      }
      return localObject1;
    case 4: 
      zzbg.e("Trying to convert a macro reference to object");
      return zzblE;
    case 5: 
      zzbg.e("Trying to convert a function id to object");
      return zzblE;
    case 6: 
      return Long.valueOf(paramzza.zzjD);
    case 7: 
      localObject1 = new StringBuffer();
      paramzza = paramzza.zzjF;
      k = paramzza.length;
      i = j;
      while (i < k)
      {
        localObject2 = zzg(paramzza[i]);
        if (localObject2 == zzblI) {
          return zzblE;
        }
        ((StringBuffer)localObject1).append((String)localObject2);
        i += 1;
      }
      return ((StringBuffer)localObject1).toString();
    }
    return Boolean.valueOf(paramzza.zzjE);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzdf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */