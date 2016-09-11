package com.microsoft.cll.android;

import Microsoft.Telemetry.Base;
import com.microsoft.bond.Metadata;
import com.microsoft.bond.SchemaDef;
import com.microsoft.bond.StructDef;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreSerializedEvent
  extends com.microsoft.telemetry.Data
{
  private static final String TAG = "AndroidCll-PreSerializedEvent";
  public String serializedData;
  
  public PreSerializedEvent(String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    this.serializedData = paramString2;
    paramString2 = (com.microsoft.telemetry.Data)this;
    paramString2.setBaseData(new com.microsoft.telemetry.Domain());
    paramString2.getBaseData().QualifiedName = paramString3;
    this.QualifiedName = paramString1;
    if (paramMap != null) {
      this.Attributes.putAll(paramMap);
    }
  }
  
  public static PreSerializedEvent createFromDynamicEvent(String paramString1, String paramString2)
  {
    return new PreSerializedEvent(paramString1, paramString2, "", null);
  }
  
  public static PreSerializedEvent createFromStaticEvent(ILogger paramILogger, Base paramBase)
  {
    String str1 = getPartCName(paramBase);
    String str2 = getPartBName(paramILogger, paramBase);
    Map localMap = getAttributes(paramBase);
    if (!str2.isEmpty()) {
      paramBase.setBaseType(str2);
    }
    return new PreSerializedEvent(str1, serializeEvent(paramILogger, paramBase), str2, localMap);
  }
  
  private static Map<String, String> getAttributes(Base paramBase)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(((StructDef)paramBase.getSchema().getStructs().get(0)).getMetadata().getAttributes());
    return localHashMap;
  }
  
  private static String getPartBName(ILogger paramILogger, Base paramBase)
  {
    try
    {
      paramBase = ((StructDef)((Microsoft.Telemetry.Domain)((Microsoft.Telemetry.Data)paramBase).getBaseData()).getSchema().getStructs().get(0)).getMetadata().getQualified_name();
      return paramBase;
    }
    catch (ClassCastException paramBase)
    {
      paramILogger.info("AndroidCll-PreSerializedEvent", "This event doesn't extend data");
    }
    return "";
  }
  
  private static String getPartCName(Base paramBase)
  {
    return ((StructDef)paramBase.getSchema().getStructs().get(0)).getMetadata().getQualified_name();
  }
  
  private static String serializeEvent(ILogger paramILogger, Base paramBase)
  {
    return new BondJsonSerializer(paramILogger).serialize(paramBase);
  }
  
  public void serialize(Writer paramWriter)
    throws IOException
  {
    paramWriter.write(this.serializedData);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\PreSerializedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */