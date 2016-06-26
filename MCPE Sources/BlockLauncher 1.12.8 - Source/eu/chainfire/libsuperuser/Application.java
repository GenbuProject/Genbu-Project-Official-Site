package eu.chainfire.libsuperuser;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class Application
  extends android.app.Application
{
  private static Handler mApplicationHandler = new Handler();
  
  public static void toast(Context paramContext, final String paramString)
  {
    if (paramContext == null) {}
    Context localContext;
    do
    {
      return;
      localContext = paramContext;
      if (!(paramContext instanceof Application)) {
        localContext = paramContext.getApplicationContext();
      }
    } while (!(localContext instanceof Application));
    ((Application)localContext).runInApplicationThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(this.val$c, paramString, 1).show();
      }
    });
  }
  
  public void onCreate()
  {
    super.onCreate();
    try
    {
      Class.forName("android.os.AsyncTask");
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
  }
  
  public void runInApplicationThread(Runnable paramRunnable)
  {
    mApplicationHandler.post(paramRunnable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\eu\chainfire\libsuperuser\Application.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */