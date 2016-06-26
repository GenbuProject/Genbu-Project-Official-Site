package com.google.android.gms.auth.api.signin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zzl;

public final class RevocationBoundService
  extends Service
{
  public IBinder onBind(Intent paramIntent)
  {
    if ("com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(paramIntent.getAction()))
    {
      if (Log.isLoggable("RevocationService", 2)) {
        Log.v("RevocationService", "RevocationBoundService handling disconnect.");
      }
      return new zzl(this);
    }
    Log.w("RevocationService", "Unknown action sent to RevocationBoundService: " + paramIntent.getAction());
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\RevocationBoundService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */