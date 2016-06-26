package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

public abstract class GcmListenerService
  extends Service
{
  private int zzaLy;
  private int zzaLz = 0;
  private final Object zzpV = new Object();
  
  private void zzm(Intent paramIntent)
  {
    PendingIntent localPendingIntent = (PendingIntent)paramIntent.getParcelableExtra("com.google.android.gms.gcm.PENDING_INTENT");
    if (localPendingIntent != null) {}
    try
    {
      localPendingIntent.send();
      if (zzx(paramIntent.getExtras())) {
        zza.zzf(this, paramIntent);
      }
      return;
    }
    catch (PendingIntent.CanceledException localCanceledException)
    {
      for (;;)
      {
        Log.e("GcmListenerService", "Notification pending intent canceled");
      }
    }
  }
  
  @TargetApi(11)
  private void zzn(final Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable()
      {
        public void run()
        {
          GcmListenerService.zza(GcmListenerService.this, paramIntent);
        }
      });
      return;
    }
    new AsyncTask()
    {
      protected Void zzb(Void... paramAnonymousVarArgs)
      {
        GcmListenerService.zza(GcmListenerService.this, paramIntent);
        return null;
      }
    }.execute(new Void[0]);
  }
  
  private void zzo(Intent paramIntent)
  {
    for (;;)
    {
      int i;
      try
      {
        str = paramIntent.getAction();
        i = -1;
        switch (str.hashCode())
        {
        case 366519424: 
          Log.d("GcmListenerService", "Unknown intent action: " + paramIntent.getAction());
          zzyh();
          return;
        }
      }
      finally
      {
        String str;
        GcmReceiver.completeWakefulIntent(paramIntent);
      }
      if (str.equals("com.google.android.c2dm.intent.RECEIVE"))
      {
        i = 0;
        break label136;
        if (str.equals("com.google.android.gms.gcm.NOTIFICATION_DISMISS"))
        {
          i = 1;
          break label136;
          zzp(paramIntent);
          continue;
          if (!zzx(paramIntent.getExtras())) {
            continue;
          }
          zza.zzg(this, paramIntent);
          continue;
        }
      }
      label136:
      switch (i)
      {
      }
    }
  }
  
  private void zzp(Intent paramIntent)
  {
    String str2 = paramIntent.getStringExtra("message_type");
    String str1 = str2;
    if (str2 == null) {
      str1 = "gcm";
    }
    int i = -1;
    switch (str1.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        Log.w("GcmListenerService", "Received message with unknown type: " + str1);
        return;
        if (str1.equals("gcm"))
        {
          i = 0;
          continue;
          if (str1.equals("deleted_messages"))
          {
            i = 1;
            continue;
            if (str1.equals("send_event"))
            {
              i = 2;
              continue;
              if (str1.equals("send_error")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    if (zzx(paramIntent.getExtras())) {
      zza.zze(this, paramIntent);
    }
    zzq(paramIntent);
    return;
    onDeletedMessages();
    return;
    onMessageSent(paramIntent.getStringExtra("google.message_id"));
    return;
    onSendError(paramIntent.getStringExtra("google.message_id"), paramIntent.getStringExtra("error"));
  }
  
  private void zzq(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    localBundle.remove("message_type");
    localBundle.remove("android.support.content.wakelockid");
    if (zzb.zzy(localBundle))
    {
      if (!zzb.zzaI(this))
      {
        zzb.zzc(this, getClass()).zzA(localBundle);
        return;
      }
      if (zzx(paramIntent.getExtras())) {
        zza.zzh(this, paramIntent);
      }
      zzb.zzz(localBundle);
    }
    paramIntent = localBundle.getString("from");
    localBundle.remove("from");
    zzw(localBundle);
    onMessageReceived(paramIntent, localBundle);
  }
  
  static void zzw(Bundle paramBundle)
  {
    paramBundle = paramBundle.keySet().iterator();
    while (paramBundle.hasNext())
    {
      String str = (String)paramBundle.next();
      if ((str != null) && (str.startsWith("google.c."))) {
        paramBundle.remove();
      }
    }
  }
  
  static boolean zzx(Bundle paramBundle)
  {
    return "1".equals(paramBundle.getString("google.c.a.e"));
  }
  
  private void zzyh()
  {
    synchronized (this.zzpV)
    {
      this.zzaLz -= 1;
      if (this.zzaLz == 0) {
        zzhd(this.zzaLy);
      }
      return;
    }
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onDeletedMessages() {}
  
  public void onMessageReceived(String paramString, Bundle paramBundle) {}
  
  public void onMessageSent(String paramString) {}
  
  public void onSendError(String paramString1, String paramString2) {}
  
  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    synchronized (this.zzpV)
    {
      this.zzaLy = paramInt2;
      this.zzaLz += 1;
      if (paramIntent == null)
      {
        zzyh();
        return 2;
      }
    }
    if ("com.google.android.gms.gcm.NOTIFICATION_OPEN".equals(paramIntent.getAction()))
    {
      zzm(paramIntent);
      zzyh();
      GcmReceiver.completeWakefulIntent(paramIntent);
    }
    for (;;)
    {
      return 3;
      zzn(paramIntent);
    }
  }
  
  boolean zzhd(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\gcm\GcmListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */