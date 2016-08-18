package com.microsoft.cll.android;

import com.microsoft.telemetry.extensions.device;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class CllSettings
  extends AbstractSettings
{
  private final SettingsSync settingsSync;
  
  public CllSettings(ClientTelemetry paramClientTelemetry, ILogger paramILogger, SettingsSync paramSettingsSync, PartA paramPartA)
  {
    super(paramClientTelemetry, paramILogger, paramPartA);
    this.settingsSync = paramSettingsSync;
    this.TAG = "AndroidCll-CllSettings";
    this.ETagSettingName = SettingsStore.Settings.CLLSETTINGSETAG;
    this.endpoint = SettingsStore.getCllSettingsAsString(SettingsStore.Settings.CLLSETTINGSURL);
    this.queryParam = ("?iKey=" + paramPartA.iKey + "&os=" + paramPartA.osName + "&osVer=" + paramPartA.osVer + "&deviceClass=" + paramPartA.deviceExt.getDeviceClass() + "&deviceId=" + paramPartA.deviceExt.getLocalId());
  }
  
  public void ParseSettings(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        if (paramJSONObject.has("settings"))
        {
          int i = paramJSONObject.getInt("refreshInterval") * 60;
          if (i != SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.SYNCREFRESHINTERVAL))
          {
            SettingsStore.cllSettings.put(SettingsStore.Settings.SYNCREFRESHINTERVAL, Integer.valueOf(i));
            this.settingsSync.nextExecution.cancel(false);
            this.settingsSync.nextExecution = this.settingsSync.executor.scheduleAtFixedRate(this.settingsSync, SettingsStore.getCllSettingsAsLong(SettingsStore.Settings.SYNCREFRESHINTERVAL), SettingsStore.getCllSettingsAsLong(SettingsStore.Settings.SYNCREFRESHINTERVAL), TimeUnit.SECONDS);
          }
          paramJSONObject = (JSONObject)paramJSONObject.get("settings");
          Iterator localIterator = paramJSONObject.keys();
          while (localIterator.hasNext())
          {
            String str1 = (String)localIterator.next();
            String str2 = paramJSONObject.getString(str1);
            try
            {
              SettingsStore.updateCllSetting(SettingsStore.Settings.valueOf(str1), str2);
              this.logger.info(this.TAG, "Json Settings, Key: " + str1 + " Value: " + str2);
            }
            catch (Exception localException)
            {
              this.logger.warn(this.TAG, "Key: " + str1 + " was not found");
            }
          }
        }
        return;
      }
      catch (Exception paramJSONObject)
      {
        this.logger.error(this.TAG, "An exception occurred while parsing settings");
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\CllSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */