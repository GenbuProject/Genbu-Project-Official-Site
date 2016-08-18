package com.microsoft.xbox.idp.model.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.iid.InstanceID;
import com.microsoft.xbox.idp.interop.Interop;
import java.io.IOException;

public class RegistrationIntentService
  extends IntentService
{
  private static final String REGISTRATION_MODE = "com.microsoft.xbox.idp.model.gcm";
  private static final String REGISTRATION_TOKEN_FIELD = "registrationToken";
  private static final String SENDER_ID = "86584527366";
  private static final String TAG = RegistrationIntentService.class.getSimpleName();
  
  public RegistrationIntentService()
  {
    super(TAG);
  }
  
  public void onHandleIntent(Intent paramIntent)
  {
    boolean bool2 = true;
    SharedPreferences localSharedPreferences = getSharedPreferences("com.microsoft.xbox.idp.model.gcm", 0);
    Object localObject = null;
    if (localSharedPreferences != null) {
      localObject = localSharedPreferences.getString("registrationToken", "");
    }
    boolean bool1 = false;
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null) {
      bool1 = paramIntent.getBoolean(NotificationInstanceIDListenerService.REFRESH_FLAG, false);
    }
    if (!((String)localObject).isEmpty())
    {
      paramIntent = (Intent)localObject;
      if (!bool1) {}
    }
    else
    {
      bool2 = false;
      paramIntent = InstanceID.getInstance(this);
    }
    try
    {
      paramIntent = paramIntent.getToken("86584527366", "GCM", null);
      localObject = paramIntent;
    }
    catch (IOException paramIntent)
    {
      for (;;)
      {
        Log.d("XSAPI.Android", paramIntent.getMessage());
      }
    }
    catch (SecurityException paramIntent)
    {
      for (;;)
      {
        Log.d("XSAPI.Android", paramIntent.getMessage());
      }
    }
    localSharedPreferences.edit().putString("registrationToken", (String)localObject).commit();
    paramIntent = (Intent)localObject;
    Interop.NotificationRegisterCallback(paramIntent, bool2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\model\gcm\RegistrationIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */