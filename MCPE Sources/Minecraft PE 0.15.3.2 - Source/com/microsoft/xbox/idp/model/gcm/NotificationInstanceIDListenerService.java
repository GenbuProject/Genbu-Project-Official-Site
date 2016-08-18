package com.microsoft.xbox.idp.model.gcm;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;

public class NotificationInstanceIDListenerService
  extends InstanceIDListenerService
{
  public static String REFRESH_FLAG = "isRefresh";
  private static final String TAG = "MyInstanceIDLS";
  
  public void onTokenRefresh()
  {
    Intent localIntent = new Intent(this, RegistrationIntentService.class);
    localIntent.putExtra(REFRESH_FLAG, true);
    startService(localIntent);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\model\gcm\NotificationInstanceIDListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */