package com.microsoft.xboxtcui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.xle.telemetry.helpers.UTCDeepLink;
import java.util.Iterator;
import java.util.List;

public class XboxAppDeepLinker
{
  public static final String ACTION_FIND_PEOPLE = "com.microsoft.xbox.action.ACTION_FIND_PEOPLE";
  private static final String ACTION_VIEW_ACHIEVEMENTS = "com.microsoft.xbox.action.ACTION_VIEW_ACHIEVEMENTS";
  private static final String ACTION_VIEW_GAME_PROFILE = "com.microsoft.xbox.action.ACTION_VIEW_GAME_PROFILE";
  private static final String ACTION_VIEW_SETTINGS = "com.microsoft.xbox.action.ACTION_VIEW_SETTINGS";
  private static final String ACTION_VIEW_USER_PROFILE = "com.microsoft.xbox.action.ACTION_VIEW_USER_PROFILE";
  private static final String AMAZON_FIRE_TV_MODEL_PREFIX = "AFT";
  private static final String AMAZON_MANUFACTURER = "Amazon";
  private static final String AMAZON_STORE_URI = "amzn://apps/android?p=";
  private static final String AMAZON_TABLET_STORE_PACKAGE = "com.amazon.venezia";
  private static final String AMAZON_UNDERGROUND_PACKAGE = "com.amazon.mShop.android";
  private static final String EXTRA_IS_XBOX360_GAME = "com.microsoft.xbox.extra.IS_XBOX360_GAME";
  private static final String EXTRA_TITLEID = "com.microsoft.xbox.extra.TITLEID";
  private static final String EXTRA_XUID = "com.microsoft.xbox.extra.XUID";
  private static final String OCULUS_STORE_WEB_URI = "oculus.store://link/products?referrer=manual&item_id=";
  private static final String OCULUS_XBOXAPP_APP_ID = "1193603937358048";
  private static final String PLAY_STORE_PACKAGE = "com.android.vending";
  private static final String PLAY_STORE_URI = "market://details?id=";
  private static final String PLAY_STORE_WEB_URI = "https://play.google.com/store/apps/details?id=";
  private static final String XBOXAPP_BETA_PACKAGE = "com.microsoft.xboxone.smartglass.beta";
  private static final String XBOXAPP_PACKAGE = "com.microsoft.xboxone.smartglass";
  private static boolean betaAppInstalled;
  private static boolean mainAppInstalled;
  
  public static boolean appDeeplinkingSupported()
  {
    if ((Build.MANUFACTURER.equalsIgnoreCase("Amazon")) && (Build.MODEL.startsWith("AFT"))) {}
    for (int i = 1; i == 0; i = 0) {
      return true;
    }
    return false;
  }
  
  private static String getActivityTitle()
  {
    return "DeepLink";
  }
  
  private static Intent getXboxAppInStoreIntent(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1 + "com.microsoft.xboxone.smartglass"));
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramString1, 0).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (localResolveInfo.activityInfo.applicationInfo.packageName.equals(paramString2))
      {
        paramContext = localResolveInfo.activityInfo;
        paramContext = new ComponentName(paramContext.applicationInfo.packageName, paramContext.name);
        paramString1.setFlags(270532608);
        paramString1.setComponent(paramContext);
        return paramString1;
      }
    }
    return null;
  }
  
  private static Intent getXboxAppLaunchIntent(Context paramContext)
  {
    if ((mainAppInstalled) || (betaAppInstalled)) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      if (!betaAppInstalled) {
        break;
      }
      return paramContext.getPackageManager().getLaunchIntentForPackage("com.microsoft.xboxone.smartglass.beta");
    }
    return paramContext.getPackageManager().getLaunchIntentForPackage("com.microsoft.xboxone.smartglass");
  }
  
  private static void launchXboxAppStorePage(Context paramContext)
  {
    Object localObject2 = getXboxAppInStoreIntent(paramContext, "market://details?id=", "com.android.vending");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = getXboxAppInStoreIntent(paramContext, "amzn://apps/android?p=", "com.amazon.mShop.android");
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = getXboxAppInStoreIntent(paramContext, "amzn://apps/android?p=", "com.amazon.venezia");
    }
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.microsoft.xboxone.smartglass"));
    }
    ((Intent)localObject1).setFlags(270565376);
    paramContext.startActivity((Intent)localObject1);
  }
  
  private static void launchXboxAppStorePageInOculusStore(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("oculus.store://link/products?referrer=manual&item_id=1193603937358048"));
    localIntent.setFlags(270565376);
    paramContext.startActivity(localIntent);
  }
  
  public static boolean showAddFriends(Context paramContext)
  {
    if (!appDeeplinkingSupported()) {
      return false;
    }
    String str1;
    if (paramContext == null)
    {
      str1 = "";
      if (!xboxAppIsInstalled(paramContext)) {
        break label73;
      }
      String str2 = UTCDeepLink.trackFriendSuggestionsLink(getActivityTitle(), str1);
      Intent localIntent = getXboxAppLaunchIntent(paramContext);
      localIntent.setAction("com.microsoft.xbox.action.ACTION_FIND_PEOPLE");
      localIntent.putExtra("deepLinkId", str2);
      localIntent.putExtra("deepLinkCaller", str1);
      paramContext.startActivity(localIntent);
    }
    for (;;)
    {
      return true;
      str1 = paramContext.getPackageName();
      break;
      label73:
      UTCDeepLink.trackUserSendToStore(getActivityTitle(), str1, "DeepLink - Friend Suggestions");
      launchXboxAppStorePageInOculusStore(paramContext);
    }
  }
  
  public static boolean showTitleAchievements(Context paramContext, String paramString)
  {
    if (!appDeeplinkingSupported()) {
      return false;
    }
    String str1;
    if (paramContext == null)
    {
      str1 = "";
      if (!xboxAppIsInstalled(paramContext)) {
        break label88;
      }
      String str2 = UTCDeepLink.trackGameHubAchievementsLink(getActivityTitle(), str1, paramString);
      Intent localIntent = getXboxAppLaunchIntent(paramContext);
      localIntent.setAction("com.microsoft.xbox.action.ACTION_VIEW_ACHIEVEMENTS");
      localIntent.putExtra("com.microsoft.xbox.extra.TITLEID", paramString);
      localIntent.putExtra("deepLinkId", str2);
      localIntent.putExtra("deepLinkCaller", str1);
      paramContext.startActivity(localIntent);
    }
    for (;;)
    {
      return true;
      str1 = paramContext.getPackageName();
      break;
      label88:
      UTCDeepLink.trackUserSendToStore(getActivityTitle(), str1, "DeepLink - GameHub Achievements");
      launchXboxAppStorePage(paramContext);
    }
  }
  
  public static boolean showTitleHub(Context paramContext, String paramString)
  {
    if (!appDeeplinkingSupported()) {
      return false;
    }
    String str1;
    if (paramContext == null)
    {
      str1 = "";
      if (!xboxAppIsInstalled(paramContext)) {
        break label88;
      }
      String str2 = UTCDeepLink.trackGameHubLink(getActivityTitle(), str1, paramString);
      Intent localIntent = getXboxAppLaunchIntent(paramContext);
      localIntent.setAction("com.microsoft.xbox.action.ACTION_VIEW_GAME_PROFILE");
      localIntent.putExtra("com.microsoft.xbox.extra.TITLEID", paramString);
      localIntent.putExtra("deepLinkId", str2);
      localIntent.putExtra("deepLinkCaller", str1);
      paramContext.startActivity(localIntent);
    }
    for (;;)
    {
      return true;
      str1 = paramContext.getPackageName();
      break;
      label88:
      UTCDeepLink.trackUserSendToStore(getActivityTitle(), str1, "DeepLink - GameHub");
      launchXboxAppStorePage(paramContext);
    }
  }
  
  public static boolean showUserProfile(Context paramContext, String paramString)
  {
    if (!appDeeplinkingSupported()) {
      return false;
    }
    String str1;
    if (paramContext == null)
    {
      str1 = "";
      if (!xboxAppIsInstalled(paramContext)) {
        break label88;
      }
      String str2 = UTCDeepLink.trackUserProfileLink(getActivityTitle(), str1, paramString);
      Intent localIntent = getXboxAppLaunchIntent(paramContext);
      localIntent.setAction("com.microsoft.xbox.action.ACTION_VIEW_USER_PROFILE");
      localIntent.putExtra("com.microsoft.xbox.extra.XUID", paramString);
      localIntent.putExtra("deepLinkId", str2);
      localIntent.putExtra("deepLinkCaller", str1);
      paramContext.startActivity(localIntent);
    }
    for (;;)
    {
      return true;
      str1 = paramContext.getPackageName();
      break;
      label88:
      UTCDeepLink.trackUserSendToStore(getActivityTitle(), str1, "DeepLink - User Profile");
      launchXboxAppStorePage(paramContext);
    }
  }
  
  public static boolean showUserSettings(Context paramContext)
  {
    if (!appDeeplinkingSupported()) {
      return false;
    }
    String str1;
    if (paramContext == null)
    {
      str1 = "";
      if (!xboxAppIsInstalled(paramContext)) {
        break label73;
      }
      String str2 = UTCDeepLink.trackUserSettingsLink(getActivityTitle(), str1);
      Intent localIntent = getXboxAppLaunchIntent(paramContext);
      localIntent.setAction("com.microsoft.xbox.action.ACTION_VIEW_SETTINGS");
      localIntent.putExtra("deepLinkId", str2);
      localIntent.putExtra("deepLinkCaller", str1);
      paramContext.startActivity(localIntent);
    }
    for (;;)
    {
      return true;
      str1 = paramContext.getPackageName();
      break;
      label73:
      UTCDeepLink.trackUserSendToStore(getActivityTitle(), str1, "DeepLink - User Settings");
      launchXboxAppStorePage(paramContext);
    }
  }
  
  private static boolean xboxAppIsInstalled(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext.getPackageManager().getPackageInfo("com.microsoft.xboxone.smartglass", 1);
      mainAppInstalled = true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      try
      {
        for (;;)
        {
          paramContext.getPackageManager().getPackageInfo("com.microsoft.xboxone.smartglass.beta", 1);
          betaAppInstalled = true;
          if ((mainAppInstalled) || (betaAppInstalled)) {
            bool = true;
          }
          return bool;
          localNameNotFoundException = localNameNotFoundException;
          mainAppInstalled = false;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        for (;;)
        {
          betaAppInstalled = false;
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xboxtcui\XboxAppDeepLinker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */