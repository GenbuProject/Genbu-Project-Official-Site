package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.common.zzc;

public class zzd
  extends zzy
{
  static final String zzaVA = String.valueOf(zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
  private Boolean zzRy;
  
  zzd(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  public int zzBA()
  {
    return 25;
  }
  
  int zzBB()
  {
    return 32;
  }
  
  public int zzBC()
  {
    return 24;
  }
  
  int zzBD()
  {
    return 36;
  }
  
  int zzBE()
  {
    return 256;
  }
  
  public int zzBF()
  {
    return 36;
  }
  
  public int zzBG()
  {
    return 2048;
  }
  
  int zzBH()
  {
    return 500;
  }
  
  public long zzBI()
  {
    return ((Integer)zzl.zzaWk.get()).intValue();
  }
  
  public long zzBJ()
  {
    return ((Integer)zzl.zzaWl.get()).intValue();
  }
  
  public long zzBK()
  {
    return ((Integer)zzl.zzaWm.get()).intValue();
  }
  
  int zzBL()
  {
    return 25;
  }
  
  int zzBM()
  {
    return 50;
  }
  
  long zzBN()
  {
    return 3600000L;
  }
  
  long zzBO()
  {
    return 60000L;
  }
  
  long zzBP()
  {
    return 61000L;
  }
  
  public long zzBQ()
  {
    return ((Long)zzl.zzaWw.get()).longValue();
  }
  
  public long zzBR()
  {
    return ((Long)zzl.zzaWs.get()).longValue();
  }
  
  public long zzBS()
  {
    return 1000L;
  }
  
  public int zzBT()
  {
    return Math.max(0, ((Integer)zzl.zzaWi.get()).intValue());
  }
  
  public int zzBU()
  {
    return Math.max(1, ((Integer)zzl.zzaWj.get()).intValue());
  }
  
  public String zzBV()
  {
    return (String)zzl.zzaWo.get();
  }
  
  public long zzBW()
  {
    return ((Long)zzl.zzaWd.get()).longValue();
  }
  
  public long zzBX()
  {
    return Math.max(0L, ((Long)zzl.zzaWp.get()).longValue());
  }
  
  public long zzBY()
  {
    return Math.max(0L, ((Long)zzl.zzaWr.get()).longValue());
  }
  
  public long zzBZ()
  {
    return ((Long)zzl.zzaWq.get()).longValue();
  }
  
  public long zzBp()
  {
    return zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000;
  }
  
  String zzBz()
  {
    return (String)zzl.zzaWa.get();
  }
  
  public long zzCa()
  {
    return Math.max(0L, ((Long)zzl.zzaWt.get()).longValue());
  }
  
  public long zzCb()
  {
    return Math.max(0L, ((Long)zzl.zzaWu.get()).longValue());
  }
  
  public int zzCc()
  {
    return Math.min(20, Math.max(0, ((Integer)zzl.zzaWv.get()).intValue()));
  }
  
  public String zzH(String paramString1, String paramString2)
  {
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme((String)zzl.zzaWe.get()).authority((String)zzl.zzaWf.get()).path("config/app/" + paramString1).appendQueryParameter("app_instance_id", paramString2).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", String.valueOf(zzBp()));
    return localBuilder.build().toString();
  }
  
  public long zza(String paramString, zzl.zza<Long> paramzza)
  {
    if (paramString == null) {
      return ((Long)paramzza.get()).longValue();
    }
    paramString = zzCl().zzO(paramString, paramzza.getKey());
    if (TextUtils.isEmpty(paramString)) {
      return ((Long)paramzza.get()).longValue();
    }
    try
    {
      long l = ((Long)paramzza.get(Long.valueOf(Long.valueOf(paramString).longValue()))).longValue();
      return l;
    }
    catch (NumberFormatException paramString) {}
    return ((Long)paramzza.get()).longValue();
  }
  
  public int zzb(String paramString, zzl.zza<Integer> paramzza)
  {
    if (paramString == null) {
      return ((Integer)paramzza.get()).intValue();
    }
    paramString = zzCl().zzO(paramString, paramzza.getKey());
    if (TextUtils.isEmpty(paramString)) {
      return ((Integer)paramzza.get()).intValue();
    }
    try
    {
      int i = ((Integer)paramzza.get(Integer.valueOf(Integer.valueOf(paramString).intValue()))).intValue();
      return i;
    }
    catch (NumberFormatException paramString) {}
    return ((Integer)paramzza.get()).intValue();
  }
  
  long zzeS(String paramString)
  {
    return zza(paramString, zzl.zzaWb);
  }
  
  int zzeT(String paramString)
  {
    return zzb(paramString, zzl.zzaWx);
  }
  
  public int zzeU(String paramString)
  {
    return zzb(paramString, zzl.zzaWg);
  }
  
  public int zzeV(String paramString)
  {
    return Math.max(0, zzb(paramString, zzl.zzaWh));
  }
  
  public int zzeW(String paramString)
  {
    return Math.max(0, Math.min(1000000, zzb(paramString, zzl.zzaWn)));
  }
  
  long zzkM()
  {
    return ((Long)zzl.zzaWy.get()).longValue();
  }
  
  public String zzkR()
  {
    return "google_app_measurement.db";
  }
  
  public String zzkS()
  {
    return "google_app_measurement2.db";
  }
  
  public long zzkX()
  {
    return Math.max(0L, ((Long)zzl.zzaWc.get()).longValue());
  }
  
  public boolean zzkr()
  {
    return com.google.android.gms.common.internal.zzd.zzakE;
  }
  
  /* Error */
  public boolean zzks()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 375	com/google/android/gms/measurement/internal/zzd:zzRy	Ljava/lang/Boolean;
    //   4: ifnonnull +91 -> 95
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 375	com/google/android/gms/measurement/internal/zzd:zzRy	Ljava/lang/Boolean;
    //   13: ifnonnull +80 -> 93
    //   16: aload_0
    //   17: invokevirtual 376	com/google/android/gms/measurement/internal/zzd:getContext	()Landroid/content/Context;
    //   20: invokevirtual 382	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   23: astore_3
    //   24: aload_0
    //   25: invokevirtual 376	com/google/android/gms/measurement/internal/zzd:getContext	()Landroid/content/Context;
    //   28: invokestatic 387	android/os/Process:myPid	()I
    //   31: invokestatic 393	com/google/android/gms/internal/zznf:zzi	(Landroid/content/Context;I)Ljava/lang/String;
    //   34: astore_2
    //   35: aload_3
    //   36: ifnull +30 -> 66
    //   39: aload_3
    //   40: getfield 398	android/content/pm/ApplicationInfo:processName	Ljava/lang/String;
    //   43: astore_3
    //   44: aload_3
    //   45: ifnull +58 -> 103
    //   48: aload_3
    //   49: aload_2
    //   50: invokevirtual 402	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   53: ifeq +50 -> 103
    //   56: iconst_1
    //   57: istore_1
    //   58: aload_0
    //   59: iload_1
    //   60: invokestatic 407	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   63: putfield 375	com/google/android/gms/measurement/internal/zzd:zzRy	Ljava/lang/Boolean;
    //   66: aload_0
    //   67: getfield 375	com/google/android/gms/measurement/internal/zzd:zzRy	Ljava/lang/Boolean;
    //   70: ifnonnull +23 -> 93
    //   73: aload_0
    //   74: getstatic 410	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   77: putfield 375	com/google/android/gms/measurement/internal/zzd:zzRy	Ljava/lang/Boolean;
    //   80: aload_0
    //   81: invokevirtual 411	com/google/android/gms/measurement/internal/zzd:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
    //   84: invokevirtual 417	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
    //   87: ldc_w 419
    //   90: invokevirtual 425	com/google/android/gms/measurement/internal/zzp$zza:zzfg	(Ljava/lang/String;)V
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_0
    //   96: getfield 375	com/google/android/gms/measurement/internal/zzd:zzRy	Ljava/lang/Boolean;
    //   99: invokevirtual 428	java/lang/Boolean:booleanValue	()Z
    //   102: ireturn
    //   103: iconst_0
    //   104: istore_1
    //   105: goto -47 -> 58
    //   108: astore_2
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_2
    //   112: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	zzd
    //   57	48	1	bool	boolean
    //   34	16	2	str	String
    //   108	4	2	localObject1	Object
    //   23	26	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   9	35	108	finally
    //   39	44	108	finally
    //   48	56	108	finally
    //   58	66	108	finally
    //   66	93	108	finally
    //   93	95	108	finally
    //   109	111	108	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */