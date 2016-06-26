package Ms.Telemetry;

import com.microsoft.telemetry.Domain;
import com.microsoft.telemetry.IJsonSerializable;
import com.microsoft.telemetry.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class CllHeartBeat
  extends Domain
  implements IJsonSerializable
{
  private int avgSettingsLatencyMs;
  private int avgVortexLatencyMs;
  private double cacheUsagePercent;
  private int eventsQueued;
  private String lastHeartBeat;
  private int logFailures;
  private int maxSettingsLatencyMs;
  private int maxVortexLatencyMs;
  private int quotaDropCount;
  private int rejectDropCount;
  private int settingsFailures4xx;
  private int settingsFailures5xx;
  private int settingsFailuresTimeout;
  private int settingsHttpAttempts;
  private int settingsHttpFailures;
  private int vortexFailures4xx;
  private int vortexFailures5xx;
  private int vortexFailuresTimeout;
  private int vortexHttpAttempts;
  private int vortexHttpFailures;
  
  public CllHeartBeat()
  {
    InitializeFields();
    SetupAttributes();
  }
  
  protected void InitializeFields()
  {
    this.QualifiedName = "Ms.Telemetry.CllHeartBeat";
  }
  
  public void SetupAttributes()
  {
    this.Attributes.put("Description", "This event is meant to be sent on a regular basis by all persistent in-process and out-of-process Logging Libraries.");
  }
  
  public int getAvgSettingsLatencyMs()
  {
    return this.avgSettingsLatencyMs;
  }
  
  public int getAvgVortexLatencyMs()
  {
    return this.avgVortexLatencyMs;
  }
  
  public double getCacheUsagePercent()
  {
    return this.cacheUsagePercent;
  }
  
  public int getEventsQueued()
  {
    return this.eventsQueued;
  }
  
  public String getLastHeartBeat()
  {
    return this.lastHeartBeat;
  }
  
  public int getLogFailures()
  {
    return this.logFailures;
  }
  
  public int getMaxSettingsLatencyMs()
  {
    return this.maxSettingsLatencyMs;
  }
  
  public int getMaxVortexLatencyMs()
  {
    return this.maxVortexLatencyMs;
  }
  
  public int getQuotaDropCount()
  {
    return this.quotaDropCount;
  }
  
  public int getRejectDropCount()
  {
    return this.rejectDropCount;
  }
  
  public int getSettingsFailures4xx()
  {
    return this.settingsFailures4xx;
  }
  
  public int getSettingsFailures5xx()
  {
    return this.settingsFailures5xx;
  }
  
  public int getSettingsFailuresTimeout()
  {
    return this.settingsFailuresTimeout;
  }
  
  public int getSettingsHttpAttempts()
  {
    return this.settingsHttpAttempts;
  }
  
  public int getSettingsHttpFailures()
  {
    return this.settingsHttpFailures;
  }
  
  public int getVortexFailures4xx()
  {
    return this.vortexFailures4xx;
  }
  
  public int getVortexFailures5xx()
  {
    return this.vortexFailures5xx;
  }
  
  public int getVortexFailuresTimeout()
  {
    return this.vortexFailuresTimeout;
  }
  
  public int getVortexHttpAttempts()
  {
    return this.vortexHttpAttempts;
  }
  
  public int getVortexHttpFailures()
  {
    return this.vortexHttpFailures;
  }
  
  protected String serializeContent(Writer paramWriter)
    throws IOException
  {
    Object localObject2 = super.serializeContent(paramWriter);
    Object localObject1 = localObject2;
    if (this.lastHeartBeat != null)
    {
      paramWriter.write((String)localObject2 + "\"lastHeartBeat\":");
      paramWriter.write(JsonHelper.convert(this.lastHeartBeat));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.eventsQueued != 0)
    {
      paramWriter.write((String)localObject1 + "\"eventsQueued\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.eventsQueued)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.logFailures != 0)
    {
      paramWriter.write((String)localObject2 + "\"logFailures\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.logFailures)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.quotaDropCount != 0)
    {
      paramWriter.write((String)localObject1 + "\"quotaDropCount\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.quotaDropCount)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.rejectDropCount != 0)
    {
      paramWriter.write((String)localObject2 + "\"rejectDropCount\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.rejectDropCount)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.vortexHttpAttempts != 0)
    {
      paramWriter.write((String)localObject1 + "\"vortexHttpAttempts\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.vortexHttpAttempts)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.vortexHttpFailures != 0)
    {
      paramWriter.write((String)localObject2 + "\"vortexHttpFailures\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.vortexHttpFailures)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.cacheUsagePercent > 0.0D)
    {
      paramWriter.write((String)localObject1 + "\"cacheUsagePercent\":");
      paramWriter.write(JsonHelper.convert(Double.valueOf(this.cacheUsagePercent)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.avgVortexLatencyMs != 0)
    {
      paramWriter.write((String)localObject2 + "\"avgVortexLatencyMs\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.avgVortexLatencyMs)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.maxVortexLatencyMs != 0)
    {
      paramWriter.write((String)localObject1 + "\"maxVortexLatencyMs\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.maxVortexLatencyMs)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.settingsHttpAttempts != 0)
    {
      paramWriter.write((String)localObject2 + "\"settingsHttpAttempts\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.settingsHttpAttempts)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.settingsHttpFailures != 0)
    {
      paramWriter.write((String)localObject1 + "\"settingsHttpFailures\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.settingsHttpFailures)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.avgSettingsLatencyMs != 0)
    {
      paramWriter.write((String)localObject2 + "\"avgSettingsLatencyMs\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.avgSettingsLatencyMs)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.maxSettingsLatencyMs != 0)
    {
      paramWriter.write((String)localObject1 + "\"maxSettingsLatencyMs\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.maxSettingsLatencyMs)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.vortexFailures5xx != 0)
    {
      paramWriter.write((String)localObject2 + "\"vortexFailures5xx\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.vortexFailures5xx)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.vortexFailures4xx != 0)
    {
      paramWriter.write((String)localObject1 + "\"vortexFailures4xx\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.vortexFailures4xx)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.vortexFailuresTimeout != 0)
    {
      paramWriter.write((String)localObject2 + "\"vortexFailuresTimeout\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.vortexFailuresTimeout)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.settingsFailures5xx != 0)
    {
      paramWriter.write((String)localObject1 + "\"settingsFailures5xx\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.settingsFailures5xx)));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.settingsFailures4xx != 0)
    {
      paramWriter.write((String)localObject2 + "\"settingsFailures4xx\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.settingsFailures4xx)));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.settingsFailuresTimeout != 0)
    {
      paramWriter.write((String)localObject1 + "\"settingsFailuresTimeout\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.settingsFailuresTimeout)));
      localObject2 = ",";
    }
    return (String)localObject2;
  }
  
  public void setAvgSettingsLatencyMs(int paramInt)
  {
    this.avgSettingsLatencyMs = paramInt;
  }
  
  public void setAvgVortexLatencyMs(int paramInt)
  {
    this.avgVortexLatencyMs = paramInt;
  }
  
  public void setCacheUsagePercent(double paramDouble)
  {
    this.cacheUsagePercent = paramDouble;
  }
  
  public void setEventsQueued(int paramInt)
  {
    this.eventsQueued = paramInt;
  }
  
  public void setLastHeartBeat(String paramString)
  {
    this.lastHeartBeat = paramString;
  }
  
  public void setLogFailures(int paramInt)
  {
    this.logFailures = paramInt;
  }
  
  public void setMaxSettingsLatencyMs(int paramInt)
  {
    this.maxSettingsLatencyMs = paramInt;
  }
  
  public void setMaxVortexLatencyMs(int paramInt)
  {
    this.maxVortexLatencyMs = paramInt;
  }
  
  public void setQuotaDropCount(int paramInt)
  {
    this.quotaDropCount = paramInt;
  }
  
  public void setRejectDropCount(int paramInt)
  {
    this.rejectDropCount = paramInt;
  }
  
  public void setSettingsFailures4xx(int paramInt)
  {
    this.settingsFailures4xx = paramInt;
  }
  
  public void setSettingsFailures5xx(int paramInt)
  {
    this.settingsFailures5xx = paramInt;
  }
  
  public void setSettingsFailuresTimeout(int paramInt)
  {
    this.settingsFailuresTimeout = paramInt;
  }
  
  public void setSettingsHttpAttempts(int paramInt)
  {
    this.settingsHttpAttempts = paramInt;
  }
  
  public void setSettingsHttpFailures(int paramInt)
  {
    this.settingsHttpFailures = paramInt;
  }
  
  public void setVortexFailures4xx(int paramInt)
  {
    this.vortexFailures4xx = paramInt;
  }
  
  public void setVortexFailures5xx(int paramInt)
  {
    this.vortexFailures5xx = paramInt;
  }
  
  public void setVortexFailuresTimeout(int paramInt)
  {
    this.vortexFailuresTimeout = paramInt;
  }
  
  public void setVortexHttpAttempts(int paramInt)
  {
    this.vortexHttpAttempts = paramInt;
  }
  
  public void setVortexHttpFailures(int paramInt)
  {
    this.vortexHttpFailures = paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\Ms\Telemetry\CllHeartBeat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */