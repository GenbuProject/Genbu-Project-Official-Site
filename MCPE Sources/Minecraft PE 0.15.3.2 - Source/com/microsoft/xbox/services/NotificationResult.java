package com.microsoft.xbox.services;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.microsoft.xbox.idp.R.string;

public class NotificationResult
{
  public String body;
  public String data;
  public NotificationType notificationType;
  public String title;
  
  public NotificationResult(Bundle paramBundle, Context paramContext)
  {
    Object localObject = paramBundle.getString("type");
    if (localObject == null) {
      this.notificationType = NotificationType.Unknown;
    }
    for (;;)
    {
      this.data = paramBundle.getString("xbl");
      return;
      if (((String)localObject).equals("xbox_live_game_invite"))
      {
        this.title = paramContext.getString(R.string.xbox_live_game_invite_title);
        paramContext = paramContext.getString(R.string.xbox_live_game_invite_body);
        localObject = paramBundle.getBundle("notification");
        if (localObject != null)
        {
          localObject = ((Bundle)localObject).getString("body_loc_args");
          if (localObject != null)
          {
            localObject = ((String)localObject).replace("[", "").replace("]", "").split(",");
            this.body = String.format(paramContext, new Object[] { localObject[0], localObject[1] });
          }
        }
        for (;;)
        {
          this.notificationType = NotificationType.Invite;
          break;
          Log.i("XSAPI.Android", "could not parse notification");
        }
      }
      if (((String)localObject).equals("xbox_live_achievement_unlock"))
      {
        this.notificationType = NotificationType.Achievement;
        paramContext = paramBundle.getBundle("notification");
        if (paramContext != null)
        {
          this.title = paramContext.getString("title");
          this.body = paramContext.getString("body");
        }
      }
      else
      {
        this.notificationType = NotificationType.Unknown;
      }
    }
  }
  
  public static enum NotificationType
  {
    Achievement,  Invite,  Unknown;
    
    private NotificationType() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\services\NotificationResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */