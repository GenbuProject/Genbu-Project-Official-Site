package com.microsoft.telemetry;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Base
  implements IJsonSerializable
{
  public LinkedHashMap<String, String> Attributes = new LinkedHashMap();
  public String QualifiedName;
  private String baseType;
  
  public Base()
  {
    InitializeFields();
  }
  
  protected void InitializeFields() {}
  
  public String getBaseType()
  {
    return this.baseType;
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
    if (this.baseType != null)
    {
      paramWriter.write("" + "\"baseType\":");
      paramWriter.write(JsonHelper.convert(this.baseType));
      str = ",";
    }
    return str;
  }
  
  public void setBaseType(String paramString)
  {
    this.baseType = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\telemetry\Base.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */