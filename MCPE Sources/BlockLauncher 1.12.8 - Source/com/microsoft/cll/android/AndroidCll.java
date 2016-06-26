package com.microsoft.cll.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.microsoft.telemetry.IChannel;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AndroidCll
  extends Cll
  implements SettingsStore.UpdateListener
{
  private final SharedPreferences cllPreferences;
  private final SharedPreferences hostPreferences;
  private final String sharedCllPreferencesName = "AndroidCllSettingsSharedPreferences";
  private final String sharedHostPreferencesName = "AndroidHostSettingsSharedPreferences";
  
  public AndroidCll(String paramString, Context paramContext)
  {
    this(paramString, paramContext, new CorrelationVector());
  }
  
  private AndroidCll(String paramString, Context paramContext, CorrelationVector paramCorrelationVector)
  {
    super(paramString, AndroidLogger.getInstance(), paramContext.getFilesDir().getPath().toString(), new AndroidPartA(AndroidLogger.getInstance(), paramString, paramContext, paramCorrelationVector), paramCorrelationVector);
    this.cllPreferences = paramContext.getSharedPreferences("AndroidCllSettingsSharedPreferences", 0);
    this.hostPreferences = paramContext.getSharedPreferences("AndroidHostSettingsSharedPreferences", 0);
    SettingsStore.setUpdateListener(this);
    setSettingsStoreValues();
  }
  
  public static IChannel initialize(String paramString1, Context paramContext, String paramString2)
  {
    paramString1 = new AndroidCll(paramString1, paramContext);
    paramString1.setEndpointUrl(paramString2);
    paramString1.start();
    return paramString1;
  }
  
  private void setSettingsStoreValues()
  {
    Iterator localIterator = this.cllPreferences.getAll().entrySet().iterator();
    Map.Entry localEntry;
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      SettingsStore.updateCllSetting(SettingsStore.Settings.valueOf((String)localEntry.getKey()), (String)localEntry.getValue());
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\AndroidCll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */