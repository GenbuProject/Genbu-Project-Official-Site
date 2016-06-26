package com.microsoft.cll.android.Internal;

import Microsoft.Telemetry.Base;
import Microsoft.Telemetry.Data;
import Microsoft.Telemetry.Domain;
import android.content.Context;
import com.microsoft.bond.Metadata;
import com.microsoft.bond.SchemaDef;
import com.microsoft.bond.StructDef;
import com.microsoft.cll.android.AndroidCll;
import com.microsoft.cll.android.AndroidLogger;
import com.microsoft.cll.android.EventSensitivity;
import com.microsoft.cll.android.ILogger;
import com.microsoft.cll.android.PreSerializedEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidInternalCll
  extends AndroidCll
{
  private final String TAG = "AndroidInternalCll";
  private final ILogger logger = AndroidLogger.getInstance();
  
  public AndroidInternalCll(String paramString, Context paramContext)
  {
    super(paramString, paramContext);
  }
  
  protected PreSerializedEvent createPreSerializedEvent(Base paramBase)
  {
    String str1 = getPartBName(paramBase);
    String str2 = getPartCName(paramBase);
    Map localMap = getAttributes(paramBase);
    if (!str1.isEmpty()) {
      paramBase.setBaseType(str1);
    }
    return new PreSerializedEvent(serialize(paramBase), str2, str1, localMap);
  }
  
  protected PreSerializedEvent createPreSerializedEvent(String paramString1, String paramString2)
  {
    return new PreSerializedEvent(paramString1, paramString2, "", null);
  }
  
  protected Map<String, String> getAttributes(Base paramBase)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(((StructDef)paramBase.getSchema().getStructs().get(0)).getMetadata().getAttributes());
    return localHashMap;
  }
  
  protected String getPartBName(Base paramBase)
  {
    try
    {
      paramBase = ((StructDef)((Domain)((Data)paramBase).getBaseData()).getSchema().getStructs().get(0)).getMetadata().getQualified_name();
      return paramBase;
    }
    catch (ClassCastException paramBase)
    {
      this.logger.info("AndroidInternalCll", "This event doesn't extend data");
    }
    return "";
  }
  
  protected String getPartCName(Base paramBase)
  {
    return ((StructDef)paramBase.getSchema().getStructs().get(0)).getMetadata().getQualified_name();
  }
  
  public void log(Base paramBase, List<String> paramList, EventSensitivity... paramVarArgs)
  {
    super.log(createPreSerializedEvent(paramBase), paramList, paramVarArgs);
  }
  
  public void log(Base paramBase, EventSensitivity... paramVarArgs)
  {
    super.log(createPreSerializedEvent(paramBase), null, paramVarArgs);
  }
  
  public void log(String paramString1, String paramString2, List<String> paramList, EventSensitivity... paramVarArgs)
  {
    if (!paramString2.contains("."))
    {
      this.logger.error("AndroidInternalCll", "Event Name does not follow a valid format. Your event must have at least one . between two words. E.g. Microsoft.MyEvent");
      return;
    }
    super.log(createPreSerializedEvent(paramString1, paramString2), paramList, paramVarArgs);
  }
  
  public void log(String paramString1, String paramString2, EventSensitivity... paramVarArgs)
  {
    log(paramString1, paramString2, null, paramVarArgs);
  }
  
  protected String serialize(Base paramBase)
  {
    return new BondJsonSerializer(this.logger).serialize(paramBase);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\Internal\AndroidInternalCll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */