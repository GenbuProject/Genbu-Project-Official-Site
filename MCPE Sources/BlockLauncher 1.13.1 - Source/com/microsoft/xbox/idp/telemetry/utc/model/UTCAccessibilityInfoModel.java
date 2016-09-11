package com.microsoft.xbox.idp.telemetry.utc.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.microsoft.xbox.idp.telemetry.helpers.UTCLog;
import java.util.HashMap;

public class UTCAccessibilityInfoModel
  extends UTCJsonBase
{
  private HashMap<String, Object> info = new HashMap();
  
  public void addValue(String paramString, Object paramObject)
  {
    if ((paramString != null) && (!this.info.containsKey(paramString))) {
      this.info.put(paramString, paramObject);
    }
  }
  
  public HashMap<String, Object> getInfo()
  {
    return this.info;
  }
  
  public void setInfo(HashMap<String, Object> paramHashMap)
  {
    this.info = paramHashMap;
  }
  
  public String toJson()
  {
    Object localObject = getInfo();
    Gson localGson = new GsonBuilder().serializeNulls().create();
    try
    {
      localObject = localGson.toJson(localObject);
      return (String)localObject;
    }
    catch (JsonIOException localJsonIOException)
    {
      UTCLog.log("UTCJsonSerializer", new Object[] { "Error in json serialization" + localJsonIOException.toString() });
    }
    return "";
  }
  
  public String toString()
  {
    return toJson();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\utc\model\UTCAccessibilityInfoModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */