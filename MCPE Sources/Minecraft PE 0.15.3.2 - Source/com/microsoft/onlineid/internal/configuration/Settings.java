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
  private static Settings Instance = null;
  public static final String IsCertificateTelemetryNeeded = "isCertificateTelemetryNeeded";
  public static final String IsLoggingEnabled = "isLoggingEnabled";
  public static final String IsPseudoLocBuild = "isPseudoLocBuild";
  public static final String IsRedactionEnabled = "isRedactionEnabled";
  public static final String ShouldCheckSsoCertificatesInDebug = "shouldCheckSsoCertificatesInDebug";
  public static final String ShowMockNotifications = "showMockNotifications";
  public static final String ShowMockNotificationsCompactStyle = "showMockNotificationsCompactStyle";
  private static final String StorageFile = "settings";
  private ConcurrentMap<String, String> _defaultSettingsMap = new ConcurrentHashMap();
  private Storage _storage;
  
  Settings(Context paramContext)
  {
    this._storage = new Storage(paramContext, "settings");
    this._defaultSettingsMap.put("areTestHooksEnabled", String.valueOf(false));
    this._defaultSettingsMap.put("isPseudoLocBuild", String.valueOf(false));
    this._defaultSettingsMap.put("isLoggingEnabled", String.valueOf(true));
    this._defaultSettingsMap.put("isRedactionEnabled", String.valueOf(true));
    this._defaultSettingsMap.put("hasUnlockProcedureCheckRun", String.valueOf(false));
    this._defaultSettingsMap.put("isCertificateTelemetryNeeded", String.valueOf(true));
  }
  
  public static Settings getInstance(Context paramContext)
  {
    if (Instance == null) {}
    try
    {
      if (Instance == null) {
        Instance = new Settings(paramContext);
      }
      return Instance;
    }
    finally {}
  }
  
  public static boolean isDebugBuild()
  {
    return false;
  }
  
  public static void resetSettings()
  {
    Instance = null;
  }
  
  public String getSetting(String paramString)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = this._storage.readString(paramString, (String)this._defaultSettingsMap.get(paramString));
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
      localObject2 = (String)this._defaultSettingsMap.get(paramString);
    }
    return (String)localObject2;
  }
  
  public boolean isSettingEnabled(String paramString)
  {
    return Strings.equalsIgnoreCase(getSetting(paramString), "true");
  }
  
  public void removeSetting(String paramString)
  {
    this._storage.edit().remove(paramString).apply();
  }
  
  public void setSetting(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      throw new IllegalArgumentException("SettingName cannot be null");
    }
    this._storage.edit().writeString(paramString1, paramString2).apply();
  }
  
  void setStorage(Storage paramStorage)
  {
    this._storage = paramStorage;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\configuration\Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */