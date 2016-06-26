package net.zhuoweizhang.mcpelauncher;

import android.app.Activity;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;

public class ModernWrapFactory
  extends WrapFactory
{
  public static final String TAG = "BlockLauncher/ModernWrapFactory";
  private static MyExceptionHandler myExceptionHandler = new MyExceptionHandler(null);
  public List<WeakReference<PopupWindow>> popups = new ArrayList();
  
  protected void closePopups(Activity paramActivity)
  {
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        synchronized (ModernWrapFactory.this.popups)
        {
          Iterator localIterator = ModernWrapFactory.this.popups.iterator();
          while (localIterator.hasNext())
          {
            PopupWindow localPopupWindow = (PopupWindow)((WeakReference)localIterator.next()).get();
            if (localPopupWindow != null) {
              localPopupWindow.dismiss();
            }
          }
        }
      }
    });
  }
  
  public Scriptable wrapAsJavaObject(Context paramContext, Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    if ((paramObject instanceof PopupWindow)) {
      if (!ScriptManager.isScriptingEnabled()) {
        ((PopupWindow)paramObject).dismiss();
      }
    }
    synchronized (this.popups)
    {
      this.popups.add(new WeakReference((PopupWindow)paramObject));
      if ((paramObject instanceof Thread))
      {
        ??? = (Thread)paramObject;
        Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = ((Thread)???).getUncaughtExceptionHandler();
        if ((localUncaughtExceptionHandler == null) || ((localUncaughtExceptionHandler instanceof ThreadGroup))) {
          ((Thread)???).setUncaughtExceptionHandler(myExceptionHandler);
        }
      }
      return super.wrapAsJavaObject(paramContext, paramScriptable, paramObject, paramClass);
    }
  }
  
  private static class MyExceptionHandler
    implements Thread.UncaughtExceptionHandler
  {
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      ScriptManager.reportScriptError(null, paramThrowable);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ModernWrapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */