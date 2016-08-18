package com.microsoft.cll.android;

import com.microsoft.telemetry.extensions.device;
import java.util.Iterator;
import org.json.JSONObject;

public class HostSettings
  extends AbstractSettings
{
  private final String baseUrl = "https://settings.data.microsoft.com/settings/v2.0/telemetry/";
  
  public HostSettings(ClientTelemetry paramClientTelemetry, ILogger paramILogger, String paramString, PartA paramPartA)
  {
    super(paramClientTelemetry, paramILogger, paramPartA);
    this.TAG = "AndroidCll-HostSettings";
    this.ETagSettingName = SettingsStore.Settings.HOSTSETTINGSETAG;
    this.disableUploadOn404 = true;
    this.endpoint = ("https://settings.data.microsoft.com/settings/v2.0/telemetry/" + paramString);
    this.queryParam = ("?os=" + paramPartA.osName + "&osVer=" + paramPartA.osVer + "&deviceClass=" + paramPartA.deviceExt.getDeviceClass() + "&deviceId=" + paramPartA.deviceExt.getLocalId());
  }
  
  public void ParseSettings(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        if (paramJSONObject.has("settings"))
        {
          paramJSONObject = (JSONObject)paramJSONObject.get("settings");
          Iterator localIterator = paramJSONObject.keys();
          while (localIterator.hasNext())
          {
            String str1 = (String)localIterator.next();
            String str2 = paramJSONObject.getString(str1);
            if (str1.split(":").length != 4) {
              this.logger.error(this.TAG, "Bad Settings Format");
            }
            str2 = str2.replaceAll(" ", "").replaceAll("_", "").toUpperCase();
            SettingsStore.updateHostSetting(str1.toUpperCase(), str2);
          }
          return;
        }
      }
      catch (Exception paramJSONObject)
      {
        this.logger.error(this.TAG, "An exception occurred while parsing settings");
      }
    }
    this.logger.info(this.TAG, "Json result did not contain a \"settings\" field!");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\HostSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */