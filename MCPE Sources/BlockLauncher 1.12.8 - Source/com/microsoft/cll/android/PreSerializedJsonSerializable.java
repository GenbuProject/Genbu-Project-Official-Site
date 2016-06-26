package com.microsoft.cll.android;

import com.microsoft.telemetry.Data;
import com.microsoft.telemetry.Domain;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreSerializedJsonSerializable
  extends Data
{
  public String serializedData;
  
  public PreSerializedJsonSerializable(String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    this.serializedData = paramString1;
    paramString1 = (Data)this;
    paramString1.setBaseData(new Domain());
    paramString1.getBaseData().QualifiedName = paramString3;
    this.QualifiedName = paramString2;
    if (paramMap != null) {
      this.Attributes.putAll(paramMap);
    }
  }
  
  public void serialize(Writer paramWriter)
    throws IOException
  {
    paramWriter.write(this.serializedData);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\PreSerializedJsonSerializable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */