package net.zhuoweizhang.mcpelauncher;

import android.app.Application;
import android.os.Build.VERSION;

public class BlockLauncher
  extends Application
{
  public void onCreate()
  {
    Utils.setContext(getApplicationContext());
    super.onCreate();
    if (Build.VERSION.SDK_INT >= 14) {
      registerActivityLifecycleCallbacks(new ThemeLifecycleCallbacks());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\BlockLauncher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */