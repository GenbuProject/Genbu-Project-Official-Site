package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zzhb
public class zzeu
  implements zzem
{
  private final Context mContext;
  private final zzeo zzCf;
  private final AdRequestInfoParcel zzCu;
  private final long zzCv;
  private final long zzCw;
  private final int zzCx;
  private boolean zzCy = false;
  private final Map<zzjg<zzes>, zzer> zzCz = new HashMap();
  private final Object zzpV = new Object();
  private final zzex zzpn;
  private final boolean zzsA;
  private final boolean zzuS;
  
  public zzeu(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zzex paramzzex, zzeo paramzzeo, boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2, int paramInt)
  {
    this.mContext = paramContext;
    this.zzCu = paramAdRequestInfoParcel;
    this.zzpn = paramzzex;
    this.zzCf = paramzzeo;
    this.zzsA = paramBoolean1;
    this.zzuS = paramBoolean2;
    this.zzCv = paramLong1;
    this.zzCw = paramLong2;
    this.zzCx = paramInt;
  }
  
  private void zza(final zzjg<zzes> paramzzjg)
  {
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = zzeu.zze(zzeu.this).keySet().iterator();
        while (localIterator.hasNext())
        {
          zzjg localzzjg = (zzjg)localIterator.next();
          if (localzzjg != paramzzjg) {
            ((zzer)zzeu.zze(zzeu.this).get(localzzjg)).cancel();
          }
        }
      }
    });
  }
  
  private zzes zzd(List<zzjg<zzes>> paramList)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzCy)
        {
          paramList = new zzes(-1);
          return paramList;
        }
        ??? = paramList.iterator();
        if (((Iterator)???).hasNext()) {
          paramList = (zzjg)((Iterator)???).next();
        }
      }
      try
      {
        zzes localzzes = (zzes)paramList.get();
        if ((localzzes == null) || (localzzes.zzCo != 0)) {
          continue;
        }
        zza(paramList);
        return localzzes;
      }
      catch (InterruptedException paramList)
      {
        zzin.zzd("Exception while processing an adapter; continuing with other adapters", paramList);
        continue;
        paramList = finally;
        throw paramList;
        zza(null);
        return new zzes(1);
      }
      catch (ExecutionException paramList)
      {
        for (;;) {}
      }
    }
  }
  
  /* Error */
  private zzes zze(List<zzjg<zzes>> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 40	com/google/android/gms/internal/zzeu:zzpV	Ljava/lang/Object;
    //   4: astore 8
    //   6: aload 8
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 42	com/google/android/gms/internal/zzeu:zzCy	Z
    //   13: ifeq +17 -> 30
    //   16: new 98	com/google/android/gms/internal/zzes
    //   19: dup
    //   20: iconst_m1
    //   21: invokespecial 101	com/google/android/gms/internal/zzes:<init>	(I)V
    //   24: astore_1
    //   25: aload 8
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: aload 8
    //   32: monitorexit
    //   33: iconst_m1
    //   34: istore_2
    //   35: aconst_null
    //   36: astore 8
    //   38: aconst_null
    //   39: astore 9
    //   41: aload_0
    //   42: getfield 55	com/google/android/gms/internal/zzeu:zzCf	Lcom/google/android/gms/internal/zzeo;
    //   45: getfield 145	com/google/android/gms/internal/zzeo:zzBY	J
    //   48: ldc2_w 146
    //   51: lcmp
    //   52: ifeq +159 -> 211
    //   55: aload_0
    //   56: getfield 55	com/google/android/gms/internal/zzeu:zzCf	Lcom/google/android/gms/internal/zzeo;
    //   59: getfield 145	com/google/android/gms/internal/zzeo:zzBY	J
    //   62: lstore 4
    //   64: aload_1
    //   65: invokeinterface 107 1 0
    //   70: astore 11
    //   72: aload 11
    //   74: invokeinterface 113 1 0
    //   79: ifeq +214 -> 293
    //   82: aload 11
    //   84: invokeinterface 117 1 0
    //   89: checkcast 119	com/google/android/gms/internal/zzjg
    //   92: astore 10
    //   94: invokestatic 153	com/google/android/gms/ads/internal/zzr:zzbG	()Lcom/google/android/gms/internal/zzmq;
    //   97: invokeinterface 159 1 0
    //   102: lstore 6
    //   104: lload 4
    //   106: lconst_0
    //   107: lcmp
    //   108: ifne +111 -> 219
    //   111: aload 10
    //   113: invokeinterface 162 1 0
    //   118: ifeq +101 -> 219
    //   121: aload 10
    //   123: invokeinterface 122 1 0
    //   128: checkcast 98	com/google/android/gms/internal/zzes
    //   131: astore_1
    //   132: aload_1
    //   133: ifnull +192 -> 325
    //   136: aload_1
    //   137: getfield 125	com/google/android/gms/internal/zzes:zzCo	I
    //   140: ifne +185 -> 325
    //   143: aload_1
    //   144: getfield 166	com/google/android/gms/internal/zzes:zzCt	Lcom/google/android/gms/internal/zzfa;
    //   147: astore 12
    //   149: aload 12
    //   151: ifnull +174 -> 325
    //   154: aload 12
    //   156: invokeinterface 172 1 0
    //   161: iload_2
    //   162: if_icmple +163 -> 325
    //   165: aload 12
    //   167: invokeinterface 172 1 0
    //   172: istore_3
    //   173: iload_3
    //   174: istore_2
    //   175: aload 10
    //   177: astore 8
    //   179: lload 4
    //   181: invokestatic 153	com/google/android/gms/ads/internal/zzr:zzbG	()Lcom/google/android/gms/internal/zzmq;
    //   184: invokeinterface 159 1 0
    //   189: lload 6
    //   191: lsub
    //   192: lsub
    //   193: lconst_0
    //   194: invokestatic 178	java/lang/Math:max	(JJ)J
    //   197: lstore 4
    //   199: aload_1
    //   200: astore 9
    //   202: goto -130 -> 72
    //   205: astore_1
    //   206: aload 8
    //   208: monitorexit
    //   209: aload_1
    //   210: athrow
    //   211: ldc2_w 179
    //   214: lstore 4
    //   216: goto -152 -> 64
    //   219: aload 10
    //   221: lload 4
    //   223: getstatic 186	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   226: invokeinterface 189 4 0
    //   231: checkcast 98	com/google/android/gms/internal/zzes
    //   234: astore_1
    //   235: goto -103 -> 132
    //   238: astore_1
    //   239: ldc -127
    //   241: aload_1
    //   242: invokestatic 134	com/google/android/gms/internal/zzin:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   245: lload 4
    //   247: invokestatic 153	com/google/android/gms/ads/internal/zzr:zzbG	()Lcom/google/android/gms/internal/zzmq;
    //   250: invokeinterface 159 1 0
    //   255: lload 6
    //   257: lsub
    //   258: lsub
    //   259: lconst_0
    //   260: invokestatic 178	java/lang/Math:max	(JJ)J
    //   263: lstore 4
    //   265: aload 9
    //   267: astore_1
    //   268: goto -69 -> 199
    //   271: astore_1
    //   272: lload 4
    //   274: invokestatic 153	com/google/android/gms/ads/internal/zzr:zzbG	()Lcom/google/android/gms/internal/zzmq;
    //   277: invokeinterface 159 1 0
    //   282: lload 6
    //   284: lsub
    //   285: lsub
    //   286: lconst_0
    //   287: invokestatic 178	java/lang/Math:max	(JJ)J
    //   290: pop2
    //   291: aload_1
    //   292: athrow
    //   293: aload_0
    //   294: aload 8
    //   296: invokespecial 127	com/google/android/gms/internal/zzeu:zza	(Lcom/google/android/gms/internal/zzjg;)V
    //   299: aload 9
    //   301: ifnonnull +30 -> 331
    //   304: new 98	com/google/android/gms/internal/zzes
    //   307: dup
    //   308: iconst_1
    //   309: invokespecial 101	com/google/android/gms/internal/zzes:<init>	(I)V
    //   312: areturn
    //   313: astore_1
    //   314: goto -75 -> 239
    //   317: astore_1
    //   318: goto -79 -> 239
    //   321: astore_1
    //   322: goto -83 -> 239
    //   325: aload 9
    //   327: astore_1
    //   328: goto -149 -> 179
    //   331: aload 9
    //   333: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	334	0	this	zzeu
    //   0	334	1	paramList	List<zzjg<zzes>>
    //   34	141	2	i	int
    //   172	2	3	j	int
    //   62	211	4	l1	long
    //   102	181	6	l2	long
    //   4	291	8	localObject	Object
    //   39	293	9	localList	List<zzjg<zzes>>
    //   92	128	10	localzzjg	zzjg
    //   70	13	11	localIterator	Iterator
    //   147	19	12	localzzfa	zzfa
    // Exception table:
    //   from	to	target	type
    //   9	28	205	finally
    //   30	33	205	finally
    //   206	209	205	finally
    //   111	132	238	java/lang/InterruptedException
    //   136	149	238	java/lang/InterruptedException
    //   154	173	238	java/lang/InterruptedException
    //   219	235	238	java/lang/InterruptedException
    //   111	132	271	finally
    //   136	149	271	finally
    //   154	173	271	finally
    //   219	235	271	finally
    //   239	245	271	finally
    //   111	132	313	java/util/concurrent/TimeoutException
    //   136	149	313	java/util/concurrent/TimeoutException
    //   154	173	313	java/util/concurrent/TimeoutException
    //   219	235	313	java/util/concurrent/TimeoutException
    //   111	132	317	java/util/concurrent/ExecutionException
    //   136	149	317	java/util/concurrent/ExecutionException
    //   154	173	317	java/util/concurrent/ExecutionException
    //   219	235	317	java/util/concurrent/ExecutionException
    //   111	132	321	android/os/RemoteException
    //   136	149	321	android/os/RemoteException
    //   154	173	321	android/os/RemoteException
    //   219	235	321	android/os/RemoteException
  }
  
  public void cancel()
  {
    synchronized (this.zzpV)
    {
      this.zzCy = true;
      Iterator localIterator = this.zzCz.values().iterator();
      if (localIterator.hasNext()) {
        ((zzer)localIterator.next()).cancel();
      }
    }
  }
  
  public zzes zzc(List<zzen> paramList)
  {
    zzin.zzaI("Starting mediation.");
    ExecutorService localExecutorService = Executors.newCachedThreadPool();
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzen localzzen = (zzen)paramList.next();
      zzin.zzaJ("Trying mediation network: " + localzzen.zzBA);
      Iterator localIterator = localzzen.zzBB.iterator();
      while (localIterator.hasNext())
      {
        final Object localObject = (String)localIterator.next();
        localObject = new zzer(this.mContext, (String)localObject, this.zzpn, this.zzCf, localzzen, this.zzCu.zzHt, this.zzCu.zzrp, this.zzCu.zzrl, this.zzsA, this.zzuS, this.zzCu.zzrD, this.zzCu.zzrH);
        zzjg localzzjg = zziq.zza(localExecutorService, new Callable()
        {
          public zzes zzeE()
            throws Exception
          {
            synchronized (zzeu.zza(zzeu.this))
            {
              if (zzeu.zzb(zzeu.this)) {
                return null;
              }
              return localObject.zza(zzeu.zzc(zzeu.this), zzeu.zzd(zzeu.this));
            }
          }
        });
        this.zzCz.put(localzzjg, localObject);
        localArrayList.add(localzzjg);
      }
    }
    switch (this.zzCx)
    {
    default: 
      return zzd(localArrayList);
    }
    return zze(localArrayList);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzeu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */