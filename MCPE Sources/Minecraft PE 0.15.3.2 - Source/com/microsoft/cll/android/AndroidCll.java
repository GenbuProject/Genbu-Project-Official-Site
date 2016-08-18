package com.microsoft.cll.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AndroidCll
  implements ICll, SettingsStore.UpdateListener
{
  private final String TAG = "AndroidCll-AndroidCll";
  protected ISingletonCll cll;
  private final SharedPreferences cllPreferences;
  private final SharedPreferences hostPreferences;
  private final ILogger logger = AndroidLogger.getInstance();
  private final String sharedCllPreferencesName = "AndroidCllSettingsSharedPreferences";
  private final String sharedHostPreferencesName = "AndroidHostSettingsSharedPreferences";
  
  protected AndroidCll()
  {
    this.cllPreferences = null;
    this.hostPreferences = null;
  }
  
  public AndroidCll(String paramString, Context paramContext)
  {
    CorrelationVector localCorrelationVector = new CorrelationVector();
    String str = paramContext.getFilesDir().getPath();
    AndroidPartA localAndroidPartA = new AndroidPartA(AndroidLogger.getInstance(), paramString, paramContext, localCorrelationVector);
    this.cll = SingletonCll.getInstance(paramString, AndroidLogger.getInstance(), str, localAndroidPartA, localCorrelationVector);
    this.cllPreferences = paramContext.getSharedPreferences("AndroidCllSettingsSharedPreferences", 0);
    this.hostPreferences = paramContext.getSharedPreferences("AndroidHostSettingsSharedPreferences", 0);
    SettingsStore.setUpdateListener(this);
    setSettingsStoreValues();
  }
  
  private void setSettingsStoreValues()
  {
    Iterator localIterator = this.cllPreferences.getAll().entrySet().iterator();
    Map.Entry localEntry;
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      try
      {
        SettingsStore.Settings localSettings = SettingsStore.Settings.valueOf((String)localEntry.getKey());
        SettingsStore.updateCllSetting(localSettings, (String)localEntry.getValue());
      }
      catch (Exception localException)
      {
        SharedPreferences.Editor localEditor = this.cllPreferences.edit();
        localEditor.remove((String)localEntry.getKey());
        localEditor.apply();
      }
    }
    localIterator = this.hostPreferences.getAll().entrySet().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      SettingsStore.updateHostSetting((String)localEntry.getKey(), (String)localEntry.getValue());
    }
  }
  
  public void OnCllSettingUpdate(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.cllPreferences.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.apply();
  }
  
  public void OnHostSettingUpdate(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.hostPreferences.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.apply();
  }
  
  public void SubscribeCllEvents(ICllEvents paramICllEvents)
  {
    this.cll.SubscribeCllEvents(paramICllEvents);
  }
  
  public String getAppUserId()
  {
    return this.cll.getAppUserId();
  }
  
  public CorrelationVector getCorrelationVector()
  {
    return ((SingletonCll)this.cll).correlationVector;
  }
  
  public void log(Microsoft.Telemetry.Base paramBase)
  {
    log(paramBase, null);
  }
  
  public void log(Microsoft.Telemetry.Base paramBase, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList)
  {
    paramBase = PreSerializedEvent.createFromStaticEvent(this.logger, paramBase);
    this.cll.log(paramBase, paramLatency, paramPersistence, paramEnumSet, paramDouble, paramList);
  }
  
  public void log(Microsoft.Telemetry.Base paramBase, List<String> paramList)
  {
    log(paramBase, EventEnums.Latency.LatencyUnspecified, EventEnums.Persistence.PersistenceUnspecified, EnumSet.of(EventEnums.Sensitivity.SensitivityUnspecified), -1.0D, paramList);
  }
  
  public void log(String paramString1, String paramString2, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList)
  {
    if (!paramString1.contains("."))
    {
      this.logger.error("AndroidCll-AndroidCll", "Event Name does not follow a valid format. Your event must have at least one . between two words. E.g. Microsoft.MyEvent");
      return;
    }
    paramString1 = PreSerializedEvent.createFromDynamicEvent(paramString1, paramString2);
    this.cll.log(paramString1, paramLatency, paramPersistence, paramEnumSet, paramDouble, paramList);
  }
  
  public void logInternal(com.microsoft.telemetry.Base paramBase)
  {
    this.cll.log(paramBase, null, null, null, -1.0D, null);
  }
  
  public void pause()
  {
    this.cll.pause();
  }
  
  public void resume()
  {
    this.cll.resume();
  }
  
  public void send()
  {
    this.cll.send();
  }
  
  public void setAppUserId(String paramString)
  {
    this.cll.setAppUserId(paramString);
  }
  
  public void setDebugVerbosity(Verbosity paramVerbosity)
  {
    this.cll.setDebugVerbosity(paramVerbosity);
  }
  
  public void setEndpointUrl(String paramString)
  {
    this.cll.setEndpointUrl(paramString);
  }
  
  public void setExperimentId(String paramString)
  {
    this.cll.setExperimentId(paramString);
  }
  
  public void setXuidCallback(ITicketCallback paramITicketCallback)
  {
    this.cll.setXuidCallback(paramITicketCallback);
  }
  
  public void start()
  {
    this.cll.start();
  }
  
  public void stop()
  {
    this.cll.stop();
  }
  
  public void synchronize()
  {
    this.cll.synchronize();
  }
  
  public void useLegacyCS(boolean paramBoolean)
  {
    this.cll.useLegacyCS(paramBoolean);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\AndroidCll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */