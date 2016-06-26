package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties.zza;
import com.google.android.gms.drive.metadata.internal.CustomProperty;
import com.google.android.gms.drive.metadata.internal.zze.zza;
import com.google.android.gms.drive.metadata.internal.zzk;
import java.util.Arrays;

public class zznn
  extends zzk<AppVisibleCustomProperties>
{
  public static final zze.zza zzatL = new zze.zza()
  {
    public void zzb(DataHolder paramAnonymousDataHolder)
    {
      zznn.zze(paramAnonymousDataHolder);
    }
    
    public String zztD()
    {
      return "customPropertiesExtraHolder";
    }
  };
  
  public zznn(int paramInt)
  {
    super("customProperties", Arrays.asList(new String[] { "hasCustomProperties", "sqlId" }), Arrays.asList(new String[] { "customPropertiesExtra", "customPropertiesExtraHolder" }), paramInt);
  }
  
  /* Error */
  private static void zzc(DataHolder paramDataHolder)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 47	com/google/android/gms/common/data/DataHolder:zzpZ	()Landroid/os/Bundle;
    //   6: ldc 36
    //   8: invokevirtual 53	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   11: checkcast 43	com/google/android/gms/common/data/DataHolder
    //   14: astore_2
    //   15: aload_2
    //   16: ifnonnull +6 -> 22
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_2
    //   23: invokestatic 57	com/google/android/gms/internal/zznn:zzf	(Lcom/google/android/gms/common/data/DataHolder;)Landroid/support/v4/util/LongSparseArray;
    //   26: astore_3
    //   27: new 59	android/util/SparseArray
    //   30: dup
    //   31: invokespecial 60	android/util/SparseArray:<init>	()V
    //   34: astore 4
    //   36: iconst_0
    //   37: istore_1
    //   38: iload_1
    //   39: aload_0
    //   40: invokevirtual 64	com/google/android/gms/common/data/DataHolder:getCount	()I
    //   43: if_icmpge +43 -> 86
    //   46: aload_3
    //   47: aload_0
    //   48: ldc 26
    //   50: iload_1
    //   51: aload_0
    //   52: iload_1
    //   53: invokevirtual 68	com/google/android/gms/common/data/DataHolder:zzbH	(I)I
    //   56: invokevirtual 72	com/google/android/gms/common/data/DataHolder:zzb	(Ljava/lang/String;II)J
    //   59: invokevirtual 78	android/support/v4/util/LongSparseArray:get	(J)Ljava/lang/Object;
    //   62: checkcast 80	com/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza
    //   65: astore 5
    //   67: aload 5
    //   69: ifnull +65 -> 134
    //   72: aload 4
    //   74: iload_1
    //   75: aload 5
    //   77: invokevirtual 84	com/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza:zztA	()Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties;
    //   80: invokevirtual 88	android/util/SparseArray:append	(ILjava/lang/Object;)V
    //   83: goto +51 -> 134
    //   86: aload_0
    //   87: invokevirtual 47	com/google/android/gms/common/data/DataHolder:zzpZ	()Landroid/os/Bundle;
    //   90: ldc 34
    //   92: aload 4
    //   94: invokevirtual 92	android/os/Bundle:putSparseParcelableArray	(Ljava/lang/String;Landroid/util/SparseArray;)V
    //   97: aload_2
    //   98: invokevirtual 95	com/google/android/gms/common/data/DataHolder:close	()V
    //   101: aload_0
    //   102: invokevirtual 47	com/google/android/gms/common/data/DataHolder:zzpZ	()Landroid/os/Bundle;
    //   105: ldc 36
    //   107: invokevirtual 99	android/os/Bundle:remove	(Ljava/lang/String;)V
    //   110: aload_0
    //   111: monitorexit
    //   112: return
    //   113: astore_2
    //   114: aload_0
    //   115: monitorexit
    //   116: aload_2
    //   117: athrow
    //   118: astore_3
    //   119: aload_2
    //   120: invokevirtual 95	com/google/android/gms/common/data/DataHolder:close	()V
    //   123: aload_0
    //   124: invokevirtual 47	com/google/android/gms/common/data/DataHolder:zzpZ	()Landroid/os/Bundle;
    //   127: ldc 36
    //   129: invokevirtual 99	android/os/Bundle:remove	(Ljava/lang/String;)V
    //   132: aload_3
    //   133: athrow
    //   134: iload_1
    //   135: iconst_1
    //   136: iadd
    //   137: istore_1
    //   138: goto -100 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	paramDataHolder	DataHolder
    //   37	101	1	i	int
    //   14	84	2	localDataHolder	DataHolder
    //   113	7	2	localObject1	Object
    //   26	21	3	localLongSparseArray	LongSparseArray
    //   118	15	3	localObject2	Object
    //   34	59	4	localSparseArray	SparseArray
    //   65	11	5	localzza	AppVisibleCustomProperties.zza
    // Exception table:
    //   from	to	target	type
    //   2	15	113	finally
    //   19	21	113	finally
    //   97	112	113	finally
    //   114	116	113	finally
    //   119	134	113	finally
    //   22	36	118	finally
    //   38	67	118	finally
    //   72	83	118	finally
    //   86	97	118	finally
  }
  
  private static void zzd(DataHolder paramDataHolder)
  {
    Bundle localBundle = paramDataHolder.zzpZ();
    if (localBundle == null) {
      return;
    }
    try
    {
      DataHolder localDataHolder = (DataHolder)localBundle.getParcelable("customPropertiesExtraHolder");
      if (localDataHolder != null)
      {
        localDataHolder.close();
        localBundle.remove("customPropertiesExtraHolder");
      }
      return;
    }
    finally {}
  }
  
  private static LongSparseArray<AppVisibleCustomProperties.zza> zzf(DataHolder paramDataHolder)
  {
    Object localObject1 = paramDataHolder.zzpZ();
    String str1 = ((Bundle)localObject1).getString("entryIdColumn");
    String str2 = ((Bundle)localObject1).getString("keyColumn");
    String str3 = ((Bundle)localObject1).getString("visibilityColumn");
    String str4 = ((Bundle)localObject1).getString("valueColumn");
    LongSparseArray localLongSparseArray = new LongSparseArray();
    int i = 0;
    while (i < paramDataHolder.getCount())
    {
      int j = paramDataHolder.zzbH(i);
      long l = paramDataHolder.zzb(str1, i, j);
      localObject1 = paramDataHolder.zzd(str2, i, j);
      int k = paramDataHolder.zzc(str3, i, j);
      Object localObject2 = paramDataHolder.zzd(str4, i, j);
      CustomProperty localCustomProperty = new CustomProperty(new CustomPropertyKey((String)localObject1, k), (String)localObject2);
      localObject2 = (AppVisibleCustomProperties.zza)localLongSparseArray.get(l);
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new AppVisibleCustomProperties.zza();
        localLongSparseArray.put(l, localObject1);
      }
      ((AppVisibleCustomProperties.zza)localObject1).zza(localCustomProperty);
      i += 1;
    }
    return localLongSparseArray;
  }
  
  protected AppVisibleCustomProperties zzl(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    Bundle localBundle = paramDataHolder.zzpZ();
    SparseArray localSparseArray1 = localBundle.getSparseParcelableArray("customPropertiesExtra");
    SparseArray localSparseArray2 = localSparseArray1;
    if (localSparseArray1 == null)
    {
      if (localBundle.getParcelable("customPropertiesExtraHolder") != null)
      {
        zzc(paramDataHolder);
        localSparseArray1 = localBundle.getSparseParcelableArray("customPropertiesExtra");
      }
      localSparseArray2 = localSparseArray1;
      if (localSparseArray1 == null) {
        return AppVisibleCustomProperties.zzasK;
      }
    }
    return (AppVisibleCustomProperties)localSparseArray2.get(paramInt1, AppVisibleCustomProperties.zzasK);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zznn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */