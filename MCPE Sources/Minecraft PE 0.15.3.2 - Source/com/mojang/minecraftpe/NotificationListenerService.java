package com.mojang.minecraftpe;

import android.os.Bundle;
import com.google.android.gms.gcm.GcmListenerService;
import com.microsoft.xbox.services.NotificationHelper;
import com.microsoft.xbox.services.NotificationResult;
import com.microsoft.xbox.services.NotificationResult.NotificationType;

public class NotificationListenerService
  extends GcmListenerService
{
  native void nativePushNotificationReceived(int paramInt, String paramString1, String paramString2, String paramString3);
  
  public void onMessageReceived(String paramString, Bundle paramBundle)
  {
    paramString = NotificationHelper.tryParseXboxLiveNotification(paramBundle, this);
    nativePushNotificationReceived(paramString.notificationType.ordinal(), paramString.title, paramString.body, paramString.data);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\NotificationListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */