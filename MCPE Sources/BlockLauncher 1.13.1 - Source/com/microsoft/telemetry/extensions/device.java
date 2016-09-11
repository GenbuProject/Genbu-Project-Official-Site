package com.microsoft.telemetry.extensions;

import com.microsoft.telemetry.Extension;
import com.microsoft.telemetry.IJsonSerializable;
import com.microsoft.telemetry.JsonHelper;
import java.io.IOException;
import java.io.Writer;

public class device
  extends Extension
  implements IJsonSerializable
{
  private String authId;
  private String authSecId;
  private String deviceClass;
  private String id;
  private String localId;
  
  public device()
  {
    InitializeFields();
  }
  
  protected void InitializeFields() {}
  
  public String getAuthId()
  {
    return this.authId;
  }
  
  public String getAuthSecId()
  {
    return this.authSecId;
  }
  
  public String getDeviceClass()
  {
    return this.deviceClass;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getLocalId()
  {
    return this.localId;
  }
  
  protected String serializeContent(Writer paramWriter)
    throws IOException
  {
    Object localObject2 = super.serializeContent(paramWriter);
    Object localObject1 = localObject2;
    if (this.id != null)
    {
      paramWriter.write((String)localObject2 + "\"id\":");
      paramWriter.write(JsonHelper.convert(this.id));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.localId != null)
    {
      paramWriter.write((String)localObject1 + "\"localId\":");
      paramWriter.write(JsonHelper.convert(this.localId));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.authId != null)
    {
      paramWriter.write((String)localObject2 + "\"authId\":");
      paramWriter.write(JsonHelper.convert(this.authId));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.authSecId != null)
    {
      paramWriter.write((String)localObject1 + "\"authSecId\":");
      paramWriter.write(JsonHelper.convert(this.authSecId));
      localObject2 = ",";
    }
    localObject1 = localObject2;
    if (this.deviceClass != null)
    {
      paramWriter.write((String)localObject2 + "\"deviceClass\":");
      paramWriter.write(JsonHelper.convert(this.deviceClass));
      localObject1 = ",";
    }
    return (String)localObject1;
  }
  
  public void setAuthId(String paramString)
  {
    this.authId = paramString;
  }
  
  public void setAuthSecId(String paramString)
  {
    this.authSecId = paramString;
  }
  
  public void setDeviceClass(String paramString)
  {
    this.deviceClass = paramString;
  }
  
  public void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public void setLocalId(String paramString)
  {
    this.localId = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\telemetry\extensions\device.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */