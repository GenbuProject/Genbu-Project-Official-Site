package com.facebook.appevents.internal;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Logger;
import java.util.Locale;

class SessionLogger
{
  private static final long[] INACTIVE_SECONDS_QUANTA = { 300000L, 900000L, 1800000L, 3600000L, 21600000L, 43200000L, 86400000L, 172800000L, 259200000L, 604800000L, 1209600000L, 1814400000L, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L };
  private static final String TAG = SessionLogger.class.getCanonicalName();
  
  private static int getQuantaIndex(long paramLong)
  {
    int i = 0;
    while ((i < INACTIVE_SECONDS_QUANTA.length) && (INACTIVE_SECONDS_QUANTA[i] < paramLong)) {
      i += 1;
    }
    return i;
  }
  
  public static void logActivateApp(Activity paramActivity, SourceApplicationInfo paramSourceApplicationInfo, String paramString)
  {
    if (paramSourceApplicationInfo != null) {}
    for (paramSourceApplicationInfo = paramSourceApplicationInfo.toString();; paramSourceApplicationInfo = "Unclassified")
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("fb_mobile_launch_source", paramSourceApplicationInfo);
      AppEventsLogger.newLogger(paramActivity, paramString).logEvent("fb_mobile_activate_app", localBundle);
      return;
    }
  }
  
  private static void logClockSkewEvent()
  {
    Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Clock skew detected");
  }
  
  public static void logDeactivateApp(Activity paramActivity, SessionInfo paramSessionInfo, String paramString)
  {
    Object localObject1 = Long.valueOf(paramSessionInfo.getDiskRestoreTime() - paramSessionInfo.getSessionLastEventTime().longValue());
    Object localObject2 = localObject1;
    if (((Long)localObject1).longValue() < 0L)
    {
      localObject2 = Long.valueOf(0L);
      logClockSkewEvent();
    }
    Object localObject3 = Long.valueOf(paramSessionInfo.getSessionLength());
    localObject1 = localObject3;
    if (((Long)localObject3).longValue() < 0L)
    {
      logClockSkewEvent();
      localObject1 = Long.valueOf(0L);
    }
    localObject3 = new Bundle();
    ((Bundle)localObject3).putInt("fb_mobile_app_interruptions", paramSessionInfo.getInterruptionCount());
    ((Bundle)localObject3).putString("fb_mobile_time_between_sessions", String.format(Locale.ROOT, "session_quanta_%d", new Object[] { Integer.valueOf(getQuantaIndex(((Long)localObject2).longValue())) }));
    localObject2 = paramSessionInfo.getSourceApplicationInfo();
    if (localObject2 != null) {}
    for (localObject2 = ((SourceApplicationInfo)localObject2).toString();; localObject2 = "Unclassified")
    {
      ((Bundle)localObject3).putString("fb_mobile_launch_source", (String)localObject2);
      ((Bundle)localObject3).putLong("_logTime", paramSessionInfo.getSessionLastEventTime().longValue() / 1000L);
      AppEventsLogger.newLogger(paramActivity, paramString).logEvent("fb_mobile_deactivate_app", ((Long)localObject1).longValue() / 1000L, (Bundle)localObject3);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\appevents\internal\SessionLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */