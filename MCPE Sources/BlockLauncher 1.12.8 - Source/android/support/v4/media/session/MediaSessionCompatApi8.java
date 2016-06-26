package android.support.v4.media.session;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;

class MediaSessionCompatApi8
{
  public static void registerMediaButtonEventReceiver(Context paramContext, ComponentName paramComponentName)
  {
    ((AudioManager)paramContext.getSystemService("audio")).registerMediaButtonEventReceiver(paramComponentName);
  }
  
  public static void unregisterMediaButtonEventReceiver(Context paramContext, ComponentName paramComponentName)
  {
    ((AudioManager)paramContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(paramComponentName);
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\android\support\v4\media\session\MediaSessionCompatApi8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */