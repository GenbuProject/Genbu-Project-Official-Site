package com.microsoft.cll.android;

public class SerializedEvent
{
  private String deviceId;
  private EventEnums.Latency latency;
  private EventEnums.Persistence persistence;
  private double sampleRate;
  private String serializedData;
  
  public String getDeviceId()
  {
    return this.deviceId;
  }
  
  public EventEnums.Latency getLatency()
  {
    return this.latency;
  }
  
  public EventEnums.Persistence getPersistence()
  {
    return this.persistence;
  }
  
  public double getSampleRate()
  {
    return this.sampleRate;
  }
  
  public String getSerializedData()
  {
    return this.serializedData;
  }
  
  public void setDeviceId(String paramString)
  {
    this.deviceId = paramString;
  }
  
  public void setLatency(EventEnums.Latency paramLatency)
  {
    this.latency = paramLatency;
  }
  
  public void setPersistence(EventEnums.Persistence paramPersistence)
  {
    this.persistence = paramPersistence;
  }
  
  public void setSampleRate(double paramDouble)
  {
    this.sampleRate = paramDouble;
  }
  
  public void setSerializedData(String paramString)
  {
    this.serializedData = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\SerializedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */