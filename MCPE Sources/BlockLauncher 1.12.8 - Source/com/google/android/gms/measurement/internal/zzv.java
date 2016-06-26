package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class zzv
  extends zzz
{
  private zzc zzaXI;
  private zzc zzaXJ;
  private final BlockingQueue<FutureTask<?>> zzaXK = new LinkedBlockingQueue();
  private final BlockingQueue<FutureTask<?>> zzaXL = new LinkedBlockingQueue();
  private final Thread.UncaughtExceptionHandler zzaXM = new zzb("Thread death: Uncaught exception on worker thread");
  private final Thread.UncaughtExceptionHandler zzaXN = new zzb("Thread death: Uncaught exception on network thread");
  private final Object zzaXO = new Object();
  private final Semaphore zzaXP = new Semaphore(2);
  private volatile boolean zzaXQ;
  
  zzv(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  private void zza(FutureTask<?> paramFutureTask)
  {
    synchronized (this.zzaXO)
    {
      this.zzaXK.add(paramFutureTask);
      if (this.zzaXI == null)
      {
        this.zzaXI = new zzc("Measurement Worker", this.zzaXK);
        this.zzaXI.setUncaughtExceptionHandler(this.zzaXM);
        this.zzaXI.start();
        return;
      }
      this.zzaXI.zzfb();
    }
  }
  
  private void zzb(FutureTask<?> paramFutureTask)
  {
    synchronized (this.zzaXO)
    {
      this.zzaXL.add(paramFutureTask);
      if (this.zzaXJ == null)
      {
        this.zzaXJ = new zzc("Measurement Network", this.zzaXL);
        this.zzaXJ.setUncaughtExceptionHandler(this.zzaXN);
        this.zzaXJ.start();
        return;
      }
      this.zzaXJ.zzfb();
    }
  }
  
  public void zzCd()
  {
    if (Thread.currentThread() != this.zzaXJ) {
      throw new IllegalStateException("Call expected from network thread");
    }
  }
  
  public <V> Future<V> zzd(Callable<V> paramCallable)
    throws IllegalStateException
  {
    zzjv();
    zzx.zzz(paramCallable);
    paramCallable = new zza(paramCallable, "Task exception on worker thread");
    if (Thread.currentThread() == this.zzaXI)
    {
      paramCallable.run();
      return paramCallable;
    }
    zza(paramCallable);
    return paramCallable;
  }
  
  public void zzg(Runnable paramRunnable)
    throws IllegalStateException
  {
    zzjv();
    zzx.zzz(paramRunnable);
    zza(new zza(paramRunnable, "Task exception on worker thread"));
  }
  
  public void zzh(Runnable paramRunnable)
    throws IllegalStateException
  {
    zzjv();
    zzx.zzz(paramRunnable);
    zzb(new zza(paramRunnable, "Task exception on network thread"));
  }
  
  protected void zziJ() {}
  
  public void zzjk()
  {
    if (Thread.currentThread() != this.zzaXI) {
      throw new IllegalStateException("Call expected from worker thread");
    }
  }
  
  private final class zza<V>
    extends FutureTask<V>
  {
    private final String zzaXR;
    
    zza(Runnable paramRunnable, String paramString)
    {
      super(null);
      zzx.zzz(paramString);
      this.zzaXR = paramString;
    }
    
    zza(String paramString)
    {
      super();
      Object localObject;
      zzx.zzz(localObject);
      this.zzaXR = ((String)localObject);
    }
    
    protected void setException(Throwable paramThrowable)
    {
      zzv.this.zzAo().zzCE().zzj(this.zzaXR, paramThrowable);
      super.setException(paramThrowable);
    }
  }
  
  private final class zzb
    implements Thread.UncaughtExceptionHandler
  {
    private final String zzaXR;
    
    public zzb(String paramString)
    {
      zzx.zzz(paramString);
      this.zzaXR = paramString;
    }
    
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      try
      {
        zzv.this.zzAo().zzCE().zzj(this.zzaXR, paramThrowable);
        return;
      }
      finally
      {
        paramThread = finally;
        throw paramThread;
      }
    }
  }
  
  private final class zzc
    extends Thread
  {
    private final Object zzaXT;
    private final BlockingQueue<FutureTask<?>> zzaXU;
    
    public zzc(BlockingQueue<FutureTask<?>> paramBlockingQueue)
    {
      zzx.zzz(paramBlockingQueue);
      this.zzaXT = new Object();
      BlockingQueue localBlockingQueue;
      this.zzaXU = localBlockingQueue;
      setName(paramBlockingQueue);
    }
    
    private void zza(InterruptedException paramInterruptedException)
    {
      zzv.this.zzAo().zzCF().zzj(getName() + " was interrupted", paramInterruptedException);
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_1
      //   2: iload_1
      //   3: ifne +27 -> 30
      //   6: aload_0
      //   7: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   10: invokestatic 81	com/google/android/gms/measurement/internal/zzv:zza	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/util/concurrent/Semaphore;
      //   13: invokevirtual 86	java/util/concurrent/Semaphore:acquire	()V
      //   16: iconst_1
      //   17: istore_1
      //   18: goto -16 -> 2
      //   21: astore_3
      //   22: aload_0
      //   23: aload_3
      //   24: invokespecial 88	com/google/android/gms/measurement/internal/zzv$zzc:zza	(Ljava/lang/InterruptedException;)V
      //   27: goto -25 -> 2
      //   30: aload_0
      //   31: getfield 34	com/google/android/gms/measurement/internal/zzv$zzc:zzaXU	Ljava/util/concurrent/BlockingQueue;
      //   34: invokeinterface 94 1 0
      //   39: checkcast 96	java/util/concurrent/FutureTask
      //   42: astore_3
      //   43: aload_3
      //   44: ifnull +67 -> 111
      //   47: aload_3
      //   48: invokevirtual 98	java/util/concurrent/FutureTask:run	()V
      //   51: goto -21 -> 30
      //   54: astore 4
      //   56: aload_0
      //   57: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   60: invokestatic 101	com/google/android/gms/measurement/internal/zzv:zzc	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
      //   63: astore_3
      //   64: aload_3
      //   65: monitorenter
      //   66: aload_0
      //   67: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   70: invokestatic 81	com/google/android/gms/measurement/internal/zzv:zza	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/util/concurrent/Semaphore;
      //   73: invokevirtual 104	java/util/concurrent/Semaphore:release	()V
      //   76: aload_0
      //   77: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   80: invokestatic 101	com/google/android/gms/measurement/internal/zzv:zzc	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
      //   83: invokevirtual 107	java/lang/Object:notifyAll	()V
      //   86: aload_0
      //   87: aload_0
      //   88: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   91: invokestatic 111	com/google/android/gms/measurement/internal/zzv:zzd	(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   94: if_acmpne +215 -> 309
      //   97: aload_0
      //   98: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   101: aconst_null
      //   102: invokestatic 114	com/google/android/gms/measurement/internal/zzv:zza	(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   105: pop
      //   106: aload_3
      //   107: monitorexit
      //   108: aload 4
      //   110: athrow
      //   111: aload_0
      //   112: getfield 32	com/google/android/gms/measurement/internal/zzv$zzc:zzaXT	Ljava/lang/Object;
      //   115: astore_3
      //   116: aload_3
      //   117: monitorenter
      //   118: aload_0
      //   119: getfield 34	com/google/android/gms/measurement/internal/zzv$zzc:zzaXU	Ljava/util/concurrent/BlockingQueue;
      //   122: invokeinterface 117 1 0
      //   127: ifnonnull +25 -> 152
      //   130: aload_0
      //   131: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   134: invokestatic 121	com/google/android/gms/measurement/internal/zzv:zzb	(Lcom/google/android/gms/measurement/internal/zzv;)Z
      //   137: istore_2
      //   138: iload_2
      //   139: ifne +13 -> 152
      //   142: aload_0
      //   143: getfield 32	com/google/android/gms/measurement/internal/zzv$zzc:zzaXT	Ljava/lang/Object;
      //   146: ldc2_w 122
      //   149: invokevirtual 127	java/lang/Object:wait	(J)V
      //   152: aload_3
      //   153: monitorexit
      //   154: aload_0
      //   155: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   158: invokestatic 101	com/google/android/gms/measurement/internal/zzv:zzc	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
      //   161: astore_3
      //   162: aload_3
      //   163: monitorenter
      //   164: aload_0
      //   165: getfield 34	com/google/android/gms/measurement/internal/zzv$zzc:zzaXU	Ljava/util/concurrent/BlockingQueue;
      //   168: invokeinterface 117 1 0
      //   173: ifnonnull +124 -> 297
      //   176: aload_3
      //   177: monitorexit
      //   178: aload_0
      //   179: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   182: invokestatic 101	com/google/android/gms/measurement/internal/zzv:zzc	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
      //   185: astore_3
      //   186: aload_3
      //   187: monitorenter
      //   188: aload_0
      //   189: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   192: invokestatic 81	com/google/android/gms/measurement/internal/zzv:zza	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/util/concurrent/Semaphore;
      //   195: invokevirtual 104	java/util/concurrent/Semaphore:release	()V
      //   198: aload_0
      //   199: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   202: invokestatic 101	com/google/android/gms/measurement/internal/zzv:zzc	(Lcom/google/android/gms/measurement/internal/zzv;)Ljava/lang/Object;
      //   205: invokevirtual 107	java/lang/Object:notifyAll	()V
      //   208: aload_0
      //   209: aload_0
      //   210: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   213: invokestatic 111	com/google/android/gms/measurement/internal/zzv:zzd	(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   216: if_acmpne +33 -> 249
      //   219: aload_0
      //   220: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   223: aconst_null
      //   224: invokestatic 114	com/google/android/gms/measurement/internal/zzv:zza	(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   227: pop
      //   228: aload_3
      //   229: monitorexit
      //   230: return
      //   231: astore 4
      //   233: aload_0
      //   234: aload 4
      //   236: invokespecial 88	com/google/android/gms/measurement/internal/zzv$zzc:zza	(Ljava/lang/InterruptedException;)V
      //   239: goto -87 -> 152
      //   242: astore 4
      //   244: aload_3
      //   245: monitorexit
      //   246: aload 4
      //   248: athrow
      //   249: aload_0
      //   250: aload_0
      //   251: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   254: invokestatic 130	com/google/android/gms/measurement/internal/zzv:zze	(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   257: if_acmpne +22 -> 279
      //   260: aload_0
      //   261: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   264: aconst_null
      //   265: invokestatic 132	com/google/android/gms/measurement/internal/zzv:zzb	(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   268: pop
      //   269: goto -41 -> 228
      //   272: astore 4
      //   274: aload_3
      //   275: monitorexit
      //   276: aload 4
      //   278: athrow
      //   279: aload_0
      //   280: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   283: invokevirtual 47	com/google/android/gms/measurement/internal/zzv:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   286: invokevirtual 135	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   289: ldc -119
      //   291: invokevirtual 140	com/google/android/gms/measurement/internal/zzp$zza:zzfg	(Ljava/lang/String;)V
      //   294: goto -66 -> 228
      //   297: aload_3
      //   298: monitorexit
      //   299: goto -269 -> 30
      //   302: astore 4
      //   304: aload_3
      //   305: monitorexit
      //   306: aload 4
      //   308: athrow
      //   309: aload_0
      //   310: aload_0
      //   311: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   314: invokestatic 130	com/google/android/gms/measurement/internal/zzv:zze	(Lcom/google/android/gms/measurement/internal/zzv;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   317: if_acmpne +22 -> 339
      //   320: aload_0
      //   321: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   324: aconst_null
      //   325: invokestatic 132	com/google/android/gms/measurement/internal/zzv:zzb	(Lcom/google/android/gms/measurement/internal/zzv;Lcom/google/android/gms/measurement/internal/zzv$zzc;)Lcom/google/android/gms/measurement/internal/zzv$zzc;
      //   328: pop
      //   329: goto -223 -> 106
      //   332: astore 4
      //   334: aload_3
      //   335: monitorexit
      //   336: aload 4
      //   338: athrow
      //   339: aload_0
      //   340: getfield 18	com/google/android/gms/measurement/internal/zzv$zzc:zzaXS	Lcom/google/android/gms/measurement/internal/zzv;
      //   343: invokevirtual 47	com/google/android/gms/measurement/internal/zzv:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   346: invokevirtual 135	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   349: ldc -119
      //   351: invokevirtual 140	com/google/android/gms/measurement/internal/zzp$zza:zzfg	(Ljava/lang/String;)V
      //   354: goto -248 -> 106
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	357	0	this	zzc
      //   1	17	1	i	int
      //   137	2	2	bool	boolean
      //   21	3	3	localInterruptedException1	InterruptedException
      //   54	55	4	localObject2	Object
      //   231	4	4	localInterruptedException2	InterruptedException
      //   242	5	4	localObject3	Object
      //   272	5	4	localObject4	Object
      //   302	5	4	localObject5	Object
      //   332	5	4	localObject6	Object
      // Exception table:
      //   from	to	target	type
      //   6	16	21	java/lang/InterruptedException
      //   30	43	54	finally
      //   47	51	54	finally
      //   111	118	54	finally
      //   154	164	54	finally
      //   246	249	54	finally
      //   306	309	54	finally
      //   142	152	231	java/lang/InterruptedException
      //   118	138	242	finally
      //   142	152	242	finally
      //   152	154	242	finally
      //   233	239	242	finally
      //   244	246	242	finally
      //   188	228	272	finally
      //   228	230	272	finally
      //   249	269	272	finally
      //   274	276	272	finally
      //   279	294	272	finally
      //   164	178	302	finally
      //   297	299	302	finally
      //   304	306	302	finally
      //   66	106	332	finally
      //   106	108	332	finally
      //   309	329	332	finally
      //   334	336	332	finally
      //   339	354	332	finally
    }
    
    public void zzfb()
    {
      synchronized (this.zzaXT)
      {
        this.zzaXT.notifyAll();
        return;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */