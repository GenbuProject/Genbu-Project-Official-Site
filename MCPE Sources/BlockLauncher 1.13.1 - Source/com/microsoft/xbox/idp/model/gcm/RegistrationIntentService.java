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
    Object localObject = null;
    boolean bool2 = false;
    SharedPreferences localSharedPreferences = getSharedPreferences("com.microsoft.xbox.idp.model.gcm", 0);
    if (localSharedPreferences != null) {
      localObject = localSharedPreferences.getString("registrationToken", "");
    }
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null) {}
    for (boolean bool1 = paramIntent.getBoolean(NotificationInstanceIDListenerService.REFRESH_FLAG, false);; bool1 = false)
    {
      if ((((String)localObject).isEmpty()) || (bool1))
      {
        paramIntent = InstanceID.getInstance(this);
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
      }
      for (bool1 = bool2;; bool1 = true)
      {
        Interop.NotificationRegisterCallback((String)localObject, bool1);
        return;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\model\gcm\RegistrationIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */