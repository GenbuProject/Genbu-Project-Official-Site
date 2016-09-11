package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class zzat
  extends Thread
  implements zzas
{
  private static zzat zzbjb;
  private volatile boolean mClosed = false;
  private final Context mContext;
  private volatile boolean zzRE = false;
  private final LinkedBlockingQueue<Runnable> zzbja = new LinkedBlockingQueue();
  private volatile zzau zzbjc;
  
  private zzat(Context paramContext)
  {
    super("GAThread");
    if (paramContext != null) {}
    for (this.mContext = paramContext.getApplicationContext();; this.mContext = paramContext)
    {
      start();
      return;
    }
  }
  
  static zzat zzaZ(Context paramContext)
  {
    if (zzbjb == null) {
      zzbjb = new zzat(paramContext);
    }
    return zzbjb;
  }
  
  private String zzd(Throwable paramThrowable)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream localPrintStream = new PrintStream(localByteArrayOutputStream);
    paramThrowable.printStackTrace(localPrintStream);
    localPrintStream.flush();
    return new String(localByteArrayOutputStream.toByteArray());
  }
  
  public void run()
  {
    while (!this.mClosed) {
      try
      {
        Runnable localRunnable = (Runnable)this.zzbja.take();
        if (!this.zzRE) {
          localRunnable.run();
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        zzbg.zzaJ(localInterruptedException.toString());
      }
      catch (Throwable localThrowable)
      {
        zzbg.e("Error on Google TagManager Thread: " + zzd(localThrowable));
        zzbg.e("Google TagManager is shutting down.");
        this.zzRE = true;
      }
    }
  }
  
  public void zzgg(String paramString)
  {
    zzk(paramString, System.currentTimeMillis());
  }
  
  public void zzj(Runnable paramRunnable)
  {
    this.zzbja.add(paramRunnable);
  }
  
  void zzk(String paramString, final long paramLong)
  {
    zzj(new Runnable()
    {
      public void run()
      {
        if (zzat.zza(zzat.this) == null)
        {
          zzcu localzzcu = zzcu.zzHo();
          localzzcu.zza(zzat.zzb(zzat.this), jdField_this);
          zzat.zza(zzat.this, localzzcu.zzHr());
        }
        zzat.zza(zzat.this).zzg(paramLong, this.zzzP);
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */