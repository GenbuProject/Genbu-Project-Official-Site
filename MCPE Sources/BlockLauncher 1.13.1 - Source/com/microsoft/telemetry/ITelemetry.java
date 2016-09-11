package com.microsoft.telemetry;

import java.util.Map;

public abstract class ITelemetry
  extends Domain
{
  public abstract String getBaseType();
  
  public abstract String getEnvelopeName();
  
  public abstract Map<String, String> getProperties();
  
  public abstract void setProperties(Map<String, String> paramMap);
  
  public abstract void setVer(int paramInt);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\telemetry\ITelemetry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */