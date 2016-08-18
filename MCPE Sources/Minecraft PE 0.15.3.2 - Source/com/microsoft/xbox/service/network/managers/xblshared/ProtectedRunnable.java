package com.microsoft.xbox.service.network.managers.xblshared;

public class ProtectedRunnable
  implements Runnable
{
  private static final String TAG = ProtectedRunnable.class.getSimpleName();
  private final Runnable runnable;
  
  public ProtectedRunnable(Runnable paramRunnable)
  {
    this.runnable = paramRunnable;
  }
  
  public void run()
  {
    int j = 0;
    int i = 0;
    for (;;)
    {
      if ((j != 0) || (i >= 10)) {
        break label46;
      }
      try
      {
        this.runnable.run();
        j = 1;
      }
      catch (LinkageError localLinkageError)
      {
        for (;;)
        {
          try
          {
            Thread.sleep(500L);
          }
          catch (InterruptedException localInterruptedException) {}
        }
      }
      i += 1;
    }
    label46:
    if (j == 0) {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\xblshared\ProtectedRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */