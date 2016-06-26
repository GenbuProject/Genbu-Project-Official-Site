package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.drive.internal.OnEventResponse;
import com.google.android.gms.drive.internal.zzao.zza;
import com.google.android.gms.drive.internal.zzz;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class DriveEventService
  extends Service
  implements ChangeListener, CompletionListener, zzc, zzq
{
  public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
  private final String mName;
  int zzakz = -1;
  private CountDownLatch zzapL;
  zza zzapM;
  boolean zzapN = false;
  
  protected DriveEventService()
  {
    this("DriveEventService");
  }
  
  protected DriveEventService(String paramString)
  {
    this.mName = paramString;
  }
  
  private void zza(OnEventResponse paramOnEventResponse)
  {
    paramOnEventResponse = paramOnEventResponse.zzts();
    zzz.zzy("DriveEventService", "handleEventMessage: " + paramOnEventResponse);
    for (;;)
    {
      try
      {
        switch (paramOnEventResponse.getType())
        {
        case 3: 
        case 5: 
        case 6: 
          zzz.zzz(this.mName, "Unhandled event: " + paramOnEventResponse);
          return;
        }
      }
      catch (Exception localException)
      {
        zzz.zza(this.mName, localException, "Error handling event: " + paramOnEventResponse);
        return;
      }
      onChange((ChangeEvent)paramOnEventResponse);
      return;
      onCompletion((CompletionEvent)paramOnEventResponse);
      return;
      zza((ChangesAvailableEvent)paramOnEventResponse);
      return;
      zza((TransferStateEvent)paramOnEventResponse);
      return;
    }
  }
  
  private void zzsV()
    throws SecurityException
  {
    int i = getCallingUid();
    if (i == this.zzakz) {
      return;
    }
    if (GooglePlayServicesUtil.zzf(this, i))
    {
      this.zzakz = i;
      return;
    }
    throw new SecurityException("Caller is not GooglePlayServices");
  }
  
  protected int getCallingUid()
  {
    return Binder.getCallingUid();
  }
  
  public final IBinder onBind(final Intent paramIntent)
  {
    for (;;)
    {
      try
      {
        if ("com.google.android.gms.drive.events.HANDLE_EVENT".equals(paramIntent.getAction()))
        {
          if ((this.zzapM == null) && (!this.zzapN))
          {
            this.zzapN = true;
            paramIntent = new CountDownLatch(1);
            this.zzapL = new CountDownLatch(1);
            new Thread()
            {
              public void run()
              {
                try
                {
                  Looper.prepare();
                  DriveEventService.this.zzapM = new DriveEventService.zza(DriveEventService.this);
                  DriveEventService.this.zzapN = false;
                  paramIntent.countDown();
                  zzz.zzy("DriveEventService", "Bound and starting loop");
                  Looper.loop();
                  zzz.zzy("DriveEventService", "Finished loop");
                  return;
                }
                finally
                {
                  if (DriveEventService.zzb(DriveEventService.this) != null) {
                    DriveEventService.zzb(DriveEventService.this).countDown();
                  }
                }
              }
            }.start();
          }
          try
          {
            if (!paramIntent.await(5000L, TimeUnit.MILLISECONDS)) {
              zzz.zzA("DriveEventService", "Failed to synchronously initialize event handler.");
            }
            paramIntent = new zzb().asBinder();
            return paramIntent;
          }
          catch (InterruptedException paramIntent)
          {
            throw new RuntimeException("Unable to start event handler", paramIntent);
          }
        }
        paramIntent = null;
      }
      finally {}
    }
  }
  
  public void onChange(ChangeEvent paramChangeEvent)
  {
    zzz.zzz(this.mName, "Unhandled change event: " + paramChangeEvent);
  }
  
  public void onCompletion(CompletionEvent paramCompletionEvent)
  {
    zzz.zzz(this.mName, "Unhandled completion event: " + paramCompletionEvent);
  }
  
  /* Error */
  public void onDestroy()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 37
    //   4: ldc -46
    //   6: invokestatic 90	com/google/android/gms/drive/internal/zzz:zzy	(Ljava/lang/String;Ljava/lang/String;)V
    //   9: aload_0
    //   10: getfield 163	com/google/android/gms/drive/events/DriveEventService:zzapM	Lcom/google/android/gms/drive/events/DriveEventService$zza;
    //   13: ifnull +53 -> 66
    //   16: aload_0
    //   17: getfield 163	com/google/android/gms/drive/events/DriveEventService:zzapM	Lcom/google/android/gms/drive/events/DriveEventService$zza;
    //   20: invokestatic 213	com/google/android/gms/drive/events/DriveEventService$zza:zza	(Lcom/google/android/gms/drive/events/DriveEventService$zza;)Landroid/os/Message;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 163	com/google/android/gms/drive/events/DriveEventService:zzapM	Lcom/google/android/gms/drive/events/DriveEventService$zza;
    //   28: aload_1
    //   29: invokevirtual 217	com/google/android/gms/drive/events/DriveEventService$zza:sendMessage	(Landroid/os/Message;)Z
    //   32: pop
    //   33: aload_0
    //   34: aconst_null
    //   35: putfield 163	com/google/android/gms/drive/events/DriveEventService:zzapM	Lcom/google/android/gms/drive/events/DriveEventService$zza;
    //   38: aload_0
    //   39: getfield 131	com/google/android/gms/drive/events/DriveEventService:zzapL	Ljava/util/concurrent/CountDownLatch;
    //   42: ldc2_w 175
    //   45: getstatic 182	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   48: invokevirtual 186	java/util/concurrent/CountDownLatch:await	(JLjava/util/concurrent/TimeUnit;)Z
    //   51: ifne +10 -> 61
    //   54: ldc 37
    //   56: ldc -37
    //   58: invokestatic 101	com/google/android/gms/drive/internal/zzz:zzz	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: aload_0
    //   62: aconst_null
    //   63: putfield 131	com/google/android/gms/drive/events/DriveEventService:zzapL	Ljava/util/concurrent/CountDownLatch;
    //   66: aload_0
    //   67: invokespecial 221	android/app/Service:onDestroy	()V
    //   70: aload_0
    //   71: monitorexit
    //   72: return
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    //   78: astore_1
    //   79: goto -18 -> 61
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	this	DriveEventService
    //   23	6	1	localMessage	Message
    //   73	4	1	localObject	Object
    //   78	1	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	38	73	finally
    //   38	61	73	finally
    //   61	66	73	finally
    //   66	70	73	finally
    //   38	61	78	java/lang/InterruptedException
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    return true;
  }
  
  public void zza(ChangesAvailableEvent paramChangesAvailableEvent)
  {
    zzz.zzz(this.mName, "Unhandled changes available event: " + paramChangesAvailableEvent);
  }
  
  public void zza(TransferStateEvent paramTransferStateEvent)
  {
    zzz.zzz(this.mName, "Unhandled transfer state event: " + paramTransferStateEvent);
  }
  
  final class zza
    extends Handler
  {
    zza() {}
    
    private Message zzb(OnEventResponse paramOnEventResponse)
    {
      return obtainMessage(1, paramOnEventResponse);
    }
    
    private Message zzsW()
    {
      return obtainMessage(2);
    }
    
    public void handleMessage(Message paramMessage)
    {
      zzz.zzy("DriveEventService", "handleMessage message type:" + paramMessage.what);
      switch (paramMessage.what)
      {
      default: 
        zzz.zzz("DriveEventService", "Unexpected message type:" + paramMessage.what);
        return;
      case 1: 
        DriveEventService.zza(DriveEventService.this, (OnEventResponse)paramMessage.obj);
        return;
      }
      getLooper().quit();
    }
  }
  
  final class zzb
    extends zzao.zza
  {
    zzb() {}
    
    public void zzc(OnEventResponse paramOnEventResponse)
      throws RemoteException
    {
      synchronized (DriveEventService.this)
      {
        zzz.zzy("DriveEventService", "onEvent: " + paramOnEventResponse);
        DriveEventService.zza(DriveEventService.this);
        if (DriveEventService.this.zzapM != null)
        {
          paramOnEventResponse = DriveEventService.zza.zza(DriveEventService.this.zzapM, paramOnEventResponse);
          DriveEventService.this.zzapM.sendMessage(paramOnEventResponse);
          return;
        }
        zzz.zzA("DriveEventService", "Receiving event before initialize is completed.");
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\DriveEventService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */