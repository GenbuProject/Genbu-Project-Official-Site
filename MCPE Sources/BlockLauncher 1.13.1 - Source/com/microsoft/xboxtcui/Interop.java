package com.microsoft.xboxtcui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.xle.app.activity.FriendFinder.FriendFinderHomeScreen;
import com.microsoft.xbox.xle.app.activity.Profile.ProfileScreen;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class Interop
{
  private static final String TAG = Interop.class.getSimpleName();
  private static final XboxTcuiWindowDialog.DetachedCallback detachedCallback = new XboxTcuiWindowDialog.DetachedCallback()
  {
    public void onDetachedFromWindow()
    {
      Interop.tcui_completed_callback(0);
    }
  };
  
  public static void ShowAddFriends(Context paramContext)
  {
    Log.i(TAG, "Deeplink - ShowAddFriends");
    if (XboxAppDeepLinker.showAddFriends(paramContext))
    {
      tcui_completed_callback(0);
      return;
    }
    tcui_completed_callback(1);
  }
  
  public static void ShowFriendFinder(Activity paramActivity, String paramString1, String paramString2)
  {
    Log.i(TAG, "TCUI- ShowFriendFinder - meXuid:" + paramString1);
    Log.i(TAG, "TCUI- ShowFriendFinder: privileges:" + paramString2);
    if (paramString2.contains("255"))
    {
      final Object localObject = getForegroundActivity();
      if (localObject == null) {}
      for (;;)
      {
        localObject = new ActivityParameters();
        ((ActivityParameters)localObject).putMeXuid(paramString1);
        ((ActivityParameters)localObject).putPrivileges(paramString2);
        paramActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            try
            {
              XboxTcuiWindowDialog localXboxTcuiWindowDialog = new XboxTcuiWindowDialog(this.val$activityToUse, FriendFinderHomeScreen.class, localObject);
              localXboxTcuiWindowDialog.setDetachedCallback(Interop.detachedCallback);
              localXboxTcuiWindowDialog.show();
              return;
            }
            catch (Exception localException)
            {
              Log.i(Interop.TAG, Log.getStackTraceString(localException));
              Interop.tcui_completed_callback(1);
            }
          }
        });
        return;
        paramActivity = (Activity)localObject;
      }
    }
    tcui_completed_callback(1);
  }
  
  public static void ShowProfileCardUI(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    Log.i(TAG, "TCUI- ShowProfileCardUI: meXuid:" + paramString1);
    Log.i(TAG, "TCUI- ShowProfileCardUI: targeProfileXuid:" + paramString2);
    Log.i(TAG, "TCUI- ShowProfileCardUI: privileges:" + paramString3);
    final Object localObject2 = getForegroundActivity();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = paramActivity;
    }
    localObject2 = new ActivityParameters();
    ((ActivityParameters)localObject2).putMeXuid(paramString1);
    ((ActivityParameters)localObject2).putSelectedProfile(paramString2);
    ((ActivityParameters)localObject2).putPrivileges(paramString3);
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          XboxTcuiWindowDialog localXboxTcuiWindowDialog = new XboxTcuiWindowDialog(this.val$activityToUse, ProfileScreen.class, localObject2);
          localXboxTcuiWindowDialog.setDetachedCallback(Interop.detachedCallback);
          localXboxTcuiWindowDialog.show();
          return;
        }
        catch (Exception localException)
        {
          Log.i(Interop.TAG, Log.getStackTraceString(localException));
          Interop.tcui_completed_callback(1);
        }
      }
    });
  }
  
  public static void ShowTitleAchievements(Context paramContext, String paramString)
  {
    Log.i(TAG, "Deeplink - ShowTitleAchievements");
    if (XboxAppDeepLinker.showTitleAchievements(paramContext, paramString))
    {
      tcui_completed_callback(0);
      return;
    }
    tcui_completed_callback(1);
  }
  
  public static void ShowTitleHub(Context paramContext, String paramString)
  {
    Log.i(TAG, "Deeplink - ShowTitleHub");
    if (XboxAppDeepLinker.showTitleHub(paramContext, paramString))
    {
      tcui_completed_callback(0);
      return;
    }
    tcui_completed_callback(1);
  }
  
  public static void ShowUserProfile(Context paramContext, String paramString)
  {
    Log.i(TAG, "Deeplink - ShowUserProfile");
    if (XboxAppDeepLinker.showUserProfile(paramContext, paramString))
    {
      tcui_completed_callback(0);
      return;
    }
    tcui_completed_callback(1);
  }
  
  public static void ShowUserSettings(Context paramContext)
  {
    Log.i(TAG, "Deeplink - ShowUserSettings");
    if (XboxAppDeepLinker.showUserSettings(paramContext))
    {
      tcui_completed_callback(0);
      return;
    }
    tcui_completed_callback(1);
  }
  
  private static Activity getForegroundActivity()
  {
    try
    {
      Object localObject2 = Class.forName("android.app.ActivityThread");
      Object localObject1 = ((Class)localObject2).getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
      localObject2 = ((Class)localObject2).getDeclaredField("mActivities");
      ((Field)localObject2).setAccessible(true);
      localObject2 = ((Map)((Field)localObject2).get(localObject1)).values().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject1 = ((Iterator)localObject2).next();
        Class localClass = localObject1.getClass();
        Field localField = localClass.getDeclaredField("paused");
        localField.setAccessible(true);
        if (!localField.getBoolean(localObject1))
        {
          localObject2 = localClass.getDeclaredField("activity");
          ((Field)localObject2).setAccessible(true);
          localObject1 = (Activity)((Field)localObject2).get(localObject1);
          return (Activity)localObject1;
        }
      }
    }
    catch (Exception localException)
    {
      Log.i(TAG, Log.getStackTraceString(localException));
    }
    return null;
  }
  
  private static native void tcui_completed_callback(int paramInt);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xboxtcui\Interop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */