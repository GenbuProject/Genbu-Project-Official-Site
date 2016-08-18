package com.microsoft.cll.android;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class SettingsSync
  extends ScheduledWorker
{
  private final String TAG = "AndroidCll-SettingsSync";
  private final ClientTelemetry clientTelemetry;
  private final ILogger logger;
  private final List<AbstractSettings> settingsList;
  
  public SettingsSync(ClientTelemetry paramClientTelemetry, ILogger paramILogger, String paramString, PartA paramPartA)
  {
    super(SettingsStore.getCllSettingsAsLong(SettingsStore.Settings.SYNCREFRESHINTERVAL));
    this.clientTelemetry = paramClientTelemetry;
    this.logger = paramILogger;
    this.settingsList = new ArrayList();
    this.settingsList.add(new CllSettings(paramClientTelemetry, paramILogger, this, paramPartA));
    if (!paramString.equals("")) {
      this.settingsList.add(new HostSettings(paramClientTelemetry, paramILogger, paramString, paramPartA));
    }
  }
  
  private void GetCloudSettings()
  {
    Iterator localIterator = this.settingsList.iterator();
    while (localIterator.hasNext())
    {
      AbstractSettings localAbstractSettings = (AbstractSettings)localIterator.next();
      JSONObject localJSONObject = localAbstractSettings.getSettings();
      if (localJSONObject == null) {
        this.logger.error("AndroidCll-SettingsSync", "Could not get or parse settings");
      } else {
        localAbstractSettings.ParseSettings(localJSONObject);
      }
    }
  }
  
  public void run()
  {
    this.logger.info("AndroidCll-SettingsSync", "Cloud sync!");
    GetCloudSettings();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\SettingsSync.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */