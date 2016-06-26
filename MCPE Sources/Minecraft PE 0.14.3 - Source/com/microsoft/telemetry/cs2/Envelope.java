package com.microsoft.telemetry.cs2;

import com.microsoft.telemetry.Base;
import com.microsoft.telemetry.IJsonSerializable;
import com.microsoft.telemetry.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class Envelope
  implements IJsonSerializable
{
  private String appId;
  private String appVer;
  private Base data;
  private String deviceId;
  private long flags;
  private String iKey;
  private String name;
  private String os;
  private String osVer;
  private double sampleRate = 100.0D;
  private String seq;
  private Map<String, String> tags;
  private String time;
  private String userId;
  private int ver = 1;
  
  public Envelope()
  {
    InitializeFields();
  }
  
  protected void InitializeFields() {}
  
  public String getAppId()
  {
    return this.appId;
  }
  
  public String getAppVer()
  {
    return this.appVer;
  }
  
  public Base getData()
  {
    return this.data;
  }
  
  public String getDeviceId()
  {
    return this.deviceId;
  }
  
  public long getFlags()
  {
    return this.flags;
  }
  
  public String getIKey()
  {
    return this.iKey;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getOs()
  {
    return this.os;
  }
  
  public String getOsVer()
  {
    return this.osVer;
  }
  
  public double getSampleRate()
  {
    return this.sampleRate;
  }
  
  public String getSeq()
  {
    return this.seq;
  }
  
  public Map<String, String> getTags()
  {
    if (this.tags == null) {
      this.tags = new LinkedHashMap();
    }
    return this.tags;
  }
  
  public String getTime()
  {
    return this.time;
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  public int getVer()
  {
    return this.ver;
  }
  
  public void serialize(Writer paramWriter)
    throws IOException
  {
    if (paramWriter == null) {
      throw new IllegalArgumentException("writer");
    }
    paramWriter.write(123);
    serializeContent(paramWriter);
    paramWriter.write(125);
  }
  
  protected String serializeContent(Writer paramWriter)
    throws IOException
  {
    String str = "";
    if (this.ver != 0)
    {
      paramWriter.write("" + "\"ver\":");
      paramWriter.write(JsonHelper.convert(Integer.valueOf(this.ver)));
      str = ",";
    }
    paramWriter.write(str + "\"name\":");
    paramWriter.write(JsonHelper.convert(this.name));
    paramWriter.write("," + "\"time\":");
    paramWriter.write(JsonHelper.convert(this.time));
    if (this.sampleRate > 0.0D)
    {
      paramWriter.write("," + "\"sampleRate\":");
      paramWriter.write(JsonHelper.convert(Double.valueOf(this.sampleRate)));
    }
    if (this.seq != null)
    {
      paramWriter.write("," + "\"seq\":");
      paramWriter.write(JsonHelper.convert(this.seq));
    }
    if (this.iKey != null)
    {
      paramWriter.write("," + "\"iKey\":");
      paramWriter.write(JsonHelper.convert(this.iKey));
    }
    if (this.flags != 0L)
    {
      paramWriter.write("," + "\"flags\":");
      paramWriter.write(JsonHelper.convert(Long.valueOf(this.flags)));
    }
    if (this.deviceId != null)
    {
      paramWriter.write("," + "\"deviceId\":");
      paramWriter.write(JsonHelper.convert(this.deviceId));
    }
    if (this.os != null)
    {
      paramWriter.write("," + "\"os\":");
      paramWriter.write(JsonHelper.convert(this.os));
    }
    if (this.osVer != null)
    {
      paramWriter.write("," + "\"osVer\":");
      paramWriter.write(JsonHelper.convert(this.osVer));
    }
    if (this.appId != null)
    {
      paramWriter.write("," + "\"appId\":");
      paramWriter.write(JsonHelper.convert(this.appId));
    }
    if (this.appVer != null)
    {
      paramWriter.write("," + "\"appVer\":");
      paramWriter.write(JsonHelper.convert(this.appVer));
    }
    if (this.userId != null)
    {
      paramWriter.write("," + "\"userId\":");
      paramWriter.write(JsonHelper.convert(this.userId));
    }
    if (this.tags != null)
    {
      paramWriter.write("," + "\"tags\":");
      JsonHelper.writeDictionary(paramWriter, this.tags);
    }
    if (this.data != null)
    {
      paramWriter.write("," + "\"data\":");
      JsonHelper.writeJsonSerializable(paramWriter, this.data);
    }
    return ",";
  }
  
  public void setAppId(String paramString)
  {
    this.appId = paramString;
  }
  
  public void setAppVer(String paramString)
  {
    this.appVer = paramString;
  }
  
  public void setData(Base paramBase)
  {
    this.data = paramBase;
  }
  
  public void setDeviceId(String paramString)
  {
    this.deviceId = paramString;
  }
  
  public void setFlags(long paramLong)
  {
    this.flags = paramLong;
  }
  
  public void setIKey(String paramString)
  {
    this.iKey = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setOs(String paramString)
  {
    this.os = paramString;
  }
  
  public void setOsVer(String paramString)
  {
    this.osVer = paramString;
  }
  
  public void setSampleRate(double paramDouble)
  {
    this.sampleRate = paramDouble;
  }
  
  public void setSeq(String paramString)
  {
    this.seq = paramString;
  }
  
  public void setTags(Map<String, String> paramMap)
  {
    this.tags = paramMap;
  }
  
  public void setTime(String paramString)
  {
    this.time = paramString;
  }
  
  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }
  
  public void setVer(int paramInt)
  {
    this.ver = paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\telemetry\cs2\Envelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */