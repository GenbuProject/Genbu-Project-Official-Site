package com.microsoft.telemetry.extensions;

import com.microsoft.telemetry.Extension;
import com.microsoft.telemetry.IJsonSerializable;
import com.microsoft.telemetry.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class android
  extends Extension
  implements IJsonSerializable
{
  private String libVer;
  private List<String> tickets;
  
  public android()
  {
    InitializeFields();
  }
  
  protected void InitializeFields() {}
  
  public String getLibVer()
  {
    return this.libVer;
  }
  
  public List<String> getTickets()
  {
    if (this.tickets == null) {
      this.tickets = new ArrayList();
    }
    return this.tickets;
  }
  
  protected String serializeContent(Writer paramWriter)
    throws IOException
  {
    Object localObject2 = super.serializeContent(paramWriter);
    Object localObject1 = localObject2;
    if (this.libVer != null)
    {
      paramWriter.write((String)localObject2 + "\"libVer\":");
      paramWriter.write(JsonHelper.convert(this.libVer));
      localObject1 = ",";
    }
    localObject2 = localObject1;
    if (this.tickets != null)
    {
      paramWriter.write((String)localObject1 + "\"tickets\":");
      JsonHelper.writeListString(paramWriter, this.tickets);
      localObject2 = ",";
    }
    return (String)localObject2;
  }
  
  public void setLibVer(String paramString)
  {
    this.libVer = paramString;
  }
  
  public void setTickets(List<String> paramList)
  {
    this.tickets = paramList;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\telemetry\extensions\android.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */