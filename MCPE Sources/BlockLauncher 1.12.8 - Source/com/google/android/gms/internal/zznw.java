package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class zznw
{
  private static final Set<String> zzazD = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { zznt.zzaxI.name, zznt.zzaxW.name })));
  
  public static String zzd(DataPoint paramDataPoint)
  {
    DataType localDataType = paramDataPoint.getDataType();
    if (!zznu.zzdD(localDataType.getName())) {
      return null;
    }
    Iterator localIterator = localDataType.getFields().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (Field)localIterator.next();
      Object localObject2 = paramDataPoint.getValue((Field)localObject1);
      if (!((Value)localObject2).isSet())
      {
        if (!zzazD.contains(((Field)localObject1).getName())) {
          return ((Field)localObject1).getName() + " not set";
        }
      }
      else
      {
        if (((Field)localObject1).getFormat() == 1) {}
        for (double d = ((Value)localObject2).asInt();; d = ((Value)localObject2).asFloat())
        {
          localObject2 = zznx.zzuG().zzdE(((Field)localObject1).getName());
          if ((localObject2 == null) || (((zznx.zza)localObject2).zzh(d))) {
            break label174;
          }
          return "Field out of range";
          if (((Field)localObject1).getFormat() != 2) {
            break;
          }
        }
        label174:
        localObject1 = zznx.zzuG().zzC(localDataType.getName(), ((Field)localObject1).getName());
        if (localObject1 != null)
        {
          long l = paramDataPoint.getTimestampNanos() - paramDataPoint.zzuj();
          if (l == 0L)
          {
            if (d == 0.0D) {
              return null;
            }
            return "DataPoint out of range";
          }
          if (!((zznx.zza)localObject1).zzh(d / l)) {
            return "DataPoint out of range";
          }
        }
      }
    }
    return null;
  }
  
  public static void zze(DataPoint paramDataPoint)
    throws IllegalArgumentException
  {
    String str = zzd(paramDataPoint);
    if (str != null)
    {
      Log.w("Fitness", "Invalid data point: " + paramDataPoint);
      throw new IllegalArgumentException(str);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zznw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */