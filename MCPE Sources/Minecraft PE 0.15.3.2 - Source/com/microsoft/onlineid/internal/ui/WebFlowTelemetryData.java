package com.microsoft.onlineid.internal.ui;

import android.os.Bundle;

public class WebFlowTelemetryData
{
  protected final Bundle _values;
  
  public WebFlowTelemetryData()
  {
    this(null);
  }
  
  public WebFlowTelemetryData(Bundle paramBundle)
  {
    if (paramBundle != null) {}
    for (;;)
    {
      this._values = paramBundle;
      return;
      paramBundle = new Bundle();
    }
  }
  
  public Bundle asBundle()
  {
    return this._values;
  }
  
  public String getCallingAppPackageName()
  {
    return this._values.getString("com.microsoft.onlineid.client_package_name");
  }
  
  public String getCallingAppVersionName()
  {
    return this._values.getString("com.microsoft.onlineid.client_app_version_name");
  }
  
  public boolean getIsWebTelemetryRequested()
  {
    return this._values.getBoolean("com.microsoft.onlineid.web_telemetry_requested", false);
  }
  
  public boolean getWasPrecachingEnabled()
  {
    return this._values.getBoolean("com.microsoft.onlineid.web_telemetry_precaching_enabled");
  }
  
  public WebFlowTelemetryData setCallingAppPackageName(String paramString)
  {
    this._values.putString("com.microsoft.onlineid.client_package_name", paramString);
    return this;
  }
  
  public WebFlowTelemetryData setCallingAppVersionName(String paramString)
  {
    this._values.putString("com.microsoft.onlineid.client_app_version_name", paramString);
    return this;
  }
  
  public WebFlowTelemetryData setIsWebTelemetryRequested(boolean paramBoolean)
  {
    this._values.putBoolean("com.microsoft.onlineid.web_telemetry_requested", paramBoolean);
    return this;
  }
  
  public WebFlowTelemetryData setWasPrecachingEnabled(boolean paramBoolean)
  {
    this._values.putBoolean("com.microsoft.onlineid.web_telemetry_precaching_enabled", paramBoolean);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\WebFlowTelemetryData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */