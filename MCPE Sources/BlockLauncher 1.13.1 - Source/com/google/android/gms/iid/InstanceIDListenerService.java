package com.google.android.gms.iid;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.gcm.GcmReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;

public class InstanceIDListenerService
  extends Service
{
  static String ACTION = "action";
  private static String zzaLH = "gcm.googleapis.com/refresh";
  private static String zzaMY = "google.com/iid";
  private static String zzaMZ = "CMD";
  MessengerCompat zzaMW = new MessengerCompat(new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      InstanceIDListenerService.zza(InstanceIDListenerService.this, paramAnonymousMessage, MessengerCompat.zzc(paramAnonymousMessage));
    }
  });
  BroadcastReceiver zzaMX = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (Log.isLoggable("InstanceID", 3))
      {
        paramAnonymousIntent.getStringExtra("registration_id");
        Log.d("InstanceID", "Received GSF callback using dynamic receiver: " + paramAnonymousIntent.getExtras());
      }
      InstanceIDListenerService.this.zzp(paramAnonymousIntent);
      InstanceIDListenerService.this.stop();
    }
  };
  int zzaNa;
  int zzaNb;
  
  static void zza(Context paramContext, zzd paramzzd)
  {
    paramzzd.zzyG();
    paramzzd = new Intent("com.google.android.gms.iid.InstanceID");
    paramzzd.putExtra(zzaMZ, "RST");
    paramzzd.setPackage(paramContext.getPackageName());
    paramContext.startService(paramzzd);
  }
  
  private void zza(Message paramMessage, int paramInt)
  {
    zzc.zzaN(this);
    getPackageManager();
    if ((paramInt != zzc.zzaNi) && (paramInt != zzc.zzaNh))
    {
      Log.w("InstanceID", "Message from unexpected caller " + paramInt + " mine=" + zzc.zzaNh + " appid=" + zzc.zzaNi);
      return;
    }
    zzp((Intent)paramMessage.obj);
  }
  
  static void zzaM(Context paramContext)
  {
    Intent localIntent = new Intent("com.google.android.gms.iid.InstanceID");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra(zzaMZ, "SYNC");
    paramContext.startService(localIntent);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    if ((paramIntent != null) && ("com.google.android.gms.iid.InstanceID".equals(paramIntent.getAction()))) {
      return this.zzaMW.getBinder();
    }
    return null;
  }
  
  public void onCreate()
  {
    IntentFilter localIntentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
    localIntentFilter.addCategory(getPackageName());
    registerReceiver(this.zzaMX, localIntentFilter, "com.google.android.c2dm.permission.RECEIVE", null);
  }
  
  public void onDestroy()
  {
    unregisterReceiver(this.zzaMX);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    zzhl(paramInt2);
    if (paramIntent == null)
    {
      stop();
      return 2;
    }
    try
    {
      if ("com.google.android.gms.iid.InstanceID".equals(paramIntent.getAction()))
      {
        if (Build.VERSION.SDK_INT <= 18)
        {
          Intent localIntent = (Intent)paramIntent.getParcelableExtra("GSF");
          if (localIntent != null)
          {
            startService(localIntent);
            return 1;
          }
        }
        zzp(paramIntent);
      }
      stop();
      if (paramIntent.getStringExtra("from") != null) {
        GcmReceiver.completeWakefulIntent(paramIntent);
      }
      return 2;
    }
    finally
    {
      stop();
    }
  }
  
  public void onTokenRefresh() {}
  
  void stop()
  {
    try
    {
      this.zzaNa -= 1;
      if (this.zzaNa == 0) {
        stopSelf(this.zzaNb);
      }
      if (Log.isLoggable("InstanceID", 3)) {
        Log.d("InstanceID", "Stop " + this.zzaNa + " " + this.zzaNb);
      }
      return;
    }
    finally {}
  }
  
  public void zzal(boolean paramBoolean)
  {
    onTokenRefresh();
  }
  
  void zzhl(int paramInt)
  {
    try
    {
      this.zzaNa += 1;
      if (paramInt > this.zzaNb) {
        this.zzaNb = paramInt;
      }
      return;
    }
    finally {}
  }
  
  public void zzp(Intent paramIntent)
  {
    String str2 = paramIntent.getStringExtra("subtype");
    Object localObject;
    String str1;
    if (str2 == null)
    {
      localObject = InstanceID.getInstance(this);
      str1 = paramIntent.getStringExtra(zzaMZ);
      if ((paramIntent.getStringExtra("error") == null) && (paramIntent.getStringExtra("registration_id") == null)) {
        break label116;
      }
      if (Log.isLoggable("InstanceID", 3)) {
        Log.d("InstanceID", "Register result in service " + str2);
      }
      ((InstanceID)localObject).zzyC().zzw(paramIntent);
    }
    label116:
    label298:
    do
    {
      do
      {
        return;
        localObject = new Bundle();
        ((Bundle)localObject).putString("subtype", str2);
        localObject = InstanceID.zza(this, (Bundle)localObject);
        break;
        if (Log.isLoggable("InstanceID", 3)) {
          Log.d("InstanceID", "Service command " + str2 + " " + str1 + " " + paramIntent.getExtras());
        }
        if (paramIntent.getStringExtra("unregistered") != null)
        {
          zzd localzzd = ((InstanceID)localObject).zzyB();
          str1 = str2;
          if (str2 == null) {
            str1 = "";
          }
          localzzd.zzef(str1);
          ((InstanceID)localObject).zzyC().zzw(paramIntent);
          return;
        }
        if (zzaLH.equals(paramIntent.getStringExtra("from")))
        {
          ((InstanceID)localObject).zzyB().zzef(str2);
          zzal(false);
          return;
        }
        if ("RST".equals(str1))
        {
          ((InstanceID)localObject).zzyA();
          zzal(true);
          return;
        }
        if (!"RST_FULL".equals(str1)) {
          break label298;
        }
      } while (((InstanceID)localObject).zzyB().isEmpty());
      ((InstanceID)localObject).zzyB().zzyG();
      zzal(true);
      return;
      if ("SYNC".equals(str1))
      {
        ((InstanceID)localObject).zzyB().zzef(str2);
        zzal(false);
        return;
      }
    } while (!"PING".equals(str1));
    try
    {
      GoogleCloudMessaging.getInstance(this).send(zzaMY, zzc.zzyF(), 0L, paramIntent.getExtras());
      return;
    }
    catch (IOException paramIntent)
    {
      Log.w("InstanceID", "Failed to send ping response");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\iid\InstanceIDListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */