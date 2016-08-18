package com.microsoft.onlineid.internal.ui;

import android.os.Bundle;
import com.microsoft.onlineid.internal.log.Logger;
import java.util.ArrayList;
import java.util.Iterator;

public class WebTelemetryRecorder
{
  private static final int MAX_CHAR_COUNT = 15000;
  private int _charCount;
  private ArrayList<String> _events;
  private boolean _shouldRecord;
  private boolean _wereAllEventsCaptured;
  
  public WebTelemetryRecorder(boolean paramBoolean, Bundle paramBundle)
  {
    this._shouldRecord = paramBoolean;
    this._charCount = 0;
    if ((paramBundle != null) && (paramBundle.containsKey("com.microsoft.onlineid.web_telemetry_events")))
    {
      this._events = paramBundle.getStringArrayList("com.microsoft.onlineid.web_telemetry_events");
      this._wereAllEventsCaptured = paramBundle.getBoolean("com.microsoft.onlineid.web_telemetry_all_events_captured", false);
      paramBundle = this._events.iterator();
    }
    while (paramBundle.hasNext())
    {
      String str = (String)paramBundle.next();
      this._charCount += str.length();
      continue;
      this._events = new ArrayList();
      this._wereAllEventsCaptured = true;
    }
  }
  
  private boolean canFitEvent(String paramString)
  {
    return this._charCount + paramString.length() <= 15000;
  }
  
  public ArrayList<String> getEvents()
  {
    return this._events;
  }
  
  public boolean hasEvents()
  {
    return (this._shouldRecord) && (!this._events.isEmpty());
  }
  
  public boolean isRequested()
  {
    return this._shouldRecord;
  }
  
  public void recordEvent(String paramString)
  {
    if (this._shouldRecord)
    {
      if (canFitEvent(paramString))
      {
        this._events.add(paramString);
        this._charCount += paramString.length();
      }
    }
    else {
      return;
    }
    this._wereAllEventsCaptured = false;
    Logger.warning("Dropped web telemetry event of size: " + paramString.length());
  }
  
  public void saveInstanceState(Bundle paramBundle)
  {
    paramBundle.putStringArrayList("com.microsoft.onlineid.web_telemetry_events", getEvents());
    paramBundle.putBoolean("com.microsoft.onlineid.web_telemetry_all_events_captured", wereAllEventsCaptured());
  }
  
  public boolean wereAllEventsCaptured()
  {
    return this._wereAllEventsCaptured;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\WebTelemetryRecorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */