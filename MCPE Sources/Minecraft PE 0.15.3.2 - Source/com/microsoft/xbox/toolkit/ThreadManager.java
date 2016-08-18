package com.microsoft.xbox.toolkit;

import android.os.Handler;

public class ThreadManager
{
  public static Handler Handler;
  public static Thread UIThread;
  
  public static void UIThreadPost(Runnable paramRunnable)
  {
    UIThreadPostDelayed(paramRunnable, 0L);
  }
  
  public static void UIThreadPostDelayed(Runnable paramRunnable, long paramLong)
  {
    Handler.postDelayed(paramRunnable, paramLong);
  }
  
  public static void UIThreadSend(Runnable paramRunnable)
  {
    if (UIThread == Thread.currentThread())
    {
      paramRunnable.run();
      return;
    }
    final Ready localReady = new Ready();
    Handler.post(new Runnable()
    {
      public void run()
      {
        this.val$runnable.run();
        localReady.setReady();
      }
    });
    localReady.waitForReady();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ThreadManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */