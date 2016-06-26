package com.microsoft.telemetry.extensions;

import com.microsoft.telemetry.Extension;
import com.microsoft.telemetry.IJsonSerializable;
import com.microsoft.telemetry.JsonHelper;
import java.io.IOException;
import java.io.Writer;

public class os
  extends Extension
  implements IJsonSerializable
{
  private String expId;
  private String locale;
  
  public os()
  {
    InitializeFields();
  }
  
  protected void InitializeFields() {}
  
  public String getExpId()
  {
    return this.expId;
  }
  
  public String getLocale()
  {
    return this.locale;
  }
  
  protected String serializeContent(Writer paramWriter)
    throws IOException
  {
    Object localObject2 = super.serializeContent(paramWriter);
    Object localObject1 = localObject2;
    if (this.locale != null)
    {
      paramWriter.write((String)localObject2 + "\"locale\":");
      paramWriter.write(JsonHelper.convert(this.locale));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.expId != null)
    {
      paramWriter.write((String)localObject1 + "\"expId\":");
      paramWriter.write(JsonHelper.convert(this.expId));
      localObject2 = ",";
    }
    return (String)localObject2;
  }
  
  public void setExpId(String paramString)
  {
    this.expId = paramString;
  }
  
  public void setLocale(String paramString)
  {
    this.locale = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\telemetry\extensions\os.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */