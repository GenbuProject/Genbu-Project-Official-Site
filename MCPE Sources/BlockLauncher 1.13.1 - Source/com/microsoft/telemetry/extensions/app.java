package com.microsoft.telemetry.extensions;

import com.microsoft.telemetry.Extension;
import com.microsoft.telemetry.IJsonSerializable;
import com.microsoft.telemetry.JsonHelper;
import java.io.IOException;
import java.io.Writer;

public class app
  extends Extension
  implements IJsonSerializable
{
  private String expId;
  private String userId;
  
  public app()
  {
    InitializeFields();
  }
  
  protected void InitializeFields() {}
  
  public String getExpId()
  {
    return this.expId;
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  protected String serializeContent(Writer paramWriter)
    throws IOException
  {
    Object localObject2 = super.serializeContent(paramWriter);
    Object localObject1 = localObject2;
    if (this.expId != null)
    {
      paramWriter.write((String)localObject2 + "\"expId\":");
      paramWriter.write(JsonHelper.convert(this.expId));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.userId != null)
    {
      paramWriter.write((String)localObject1 + "\"userId\":");
      paramWriter.write(JsonHelper.convert(this.userId));
      localObject2 = ",";
    }
    return (String)localObject2;
  }
  
  public void setExpId(String paramString)
  {
    this.expId = paramString;
  }
  
  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\telemetry\extensions\app.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */