package com.microsoft.cll.android;

public class SerializedEvent
{
  private String deviceId;
  private Cll.EventLatency latency;
  private Cll.EventPersistence persistence;
  private double sampleRate;
  private String serializedData;
  
  public String getDeviceId()
  {
    return this.deviceId;
  }
  
  public Cll.EventLatency getLatency()
  {
    return this.latency;
  }
  
  public Cll.EventPersistence getPersistence()
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
  
  public void setLatency(Cll.EventLatency paramEventLatency)
  {
    this.latency = paramEventLatency;
  }
  
  public void setPersistence(Cll.EventPersistence paramEventPersistence)
  {
    this.persistence = paramEventPersistence;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\SerializedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */