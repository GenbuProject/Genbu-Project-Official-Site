package com.microsoft.onlineid.internal.configuration;

import android.content.Context;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.storage.Storage;
import com.microsoft.onlineid.internal.storage.Storage.Editor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Settings
{
  public static final String AreTestHooksEnabled = "areTestHooksEnabled";
  public static final String HasUnlockProcedureCheckRun = "hasUnlockProcedureCheckRun";
  public static final String IsCertificateTelemetryNeeded = "isCertificateTelemetryNeeded";
  public static final String IsDebugBuild = "isDebugBuild";
  private static volatile boolean IsInitialized = false;
  public static final String IsLoggingEnabled = "isLoggingEnabled";
  public static final String IsPseudoLocBuild = "isPseudoLocBuild";
  public static final String IsRedactionEnabled = "isRedactionEnabled";
  public static final String ShouldCheckSsoCertificatesInDebug = "shouldCheckSsoCertificatesInDebug";
  public static final String ShowMockNotifications = "showMockNotifications";
  public static final String ShowMockNotificationsCompactStyle = "showMockNotificationsCompactStyle";
  private static volatile ConcurrentMap<String, String> _defaultSettingsMap = new ConcurrentHashMap();
  private static volatile Storage _storage;
  private static final String _storageFile = "settings";
  
  public static String getSetting(String paramString)
  {
    Assertion.check(IsInitialized);
    Object localObject1 = null;
    try
    {
      localObject2 = _storage.readString(paramString, (String)_defaultSettingsMap.get(paramString));
      localObject1 = localObject2;
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;)
      {
        Object localObject2;
        Assertion.check(false);
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = (String)_defaultSettingsMap.get(paramString);
    }
    return (String)localObject2;
  }
  
  public static void initialize(Context paramContext)
  {
    try
    {
      if (!IsInitialized)
      {
        _storage = new Storage(paramContext, "settings");
        _defaultSettingsMap.put("areTestHooksEnabled", String.valueOf(false));
        _defaultSettingsMap.put("isDebugBuild", String.valueOf(false));
        _defaultSettingsMap.put("isPseudoLocBuild", String.valueOf(false));
        _defaultSettingsMap.put("isLoggingEnabled", String.valueOf(true));
        _defaultSettingsMap.put("isRedactionEnabled", String.valueOf(true));
        _defaultSettingsMap.put("hasUnlockProcedureCheckRun", String.valueOf(false));
        _defaultSettingsMap.put("isCertificateTelemetryNeeded", String.valueOf(true));
        IsInitialized = true;
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static boolean isDebugBuild()
  {
    return false;
  }
  
  public static boolean isSettingEnabled(String paramString)
  {
    return Strings.equalsIgnoreCase(getSetting(paramString), "true");
  }
  
  public static void removeSetting(String paramString)
  {
    _storage.edit().remove(paramString).apply();
  }
  
  public static void resetSettings()
  {
    IsInitialized = false;
  }
  
  public static void setSetting(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      throw new IllegalArgumentException("SettingName cannot be null");
    }
    _storage.edit().writeString(paramString1, paramString2).apply();
  }
  
  static void setStorage(Storage paramStorage)
  {
    _storage = paramStorage;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\configuration\Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */