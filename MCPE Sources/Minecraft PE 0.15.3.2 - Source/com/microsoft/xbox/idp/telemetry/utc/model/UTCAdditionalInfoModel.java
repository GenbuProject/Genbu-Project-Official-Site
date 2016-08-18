package com.microsoft.xbox.idp.telemetry.utc.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.microsoft.xbox.idp.telemetry.helpers.UTCLog;
import java.util.HashMap;

public class UTCAdditionalInfoModel
  extends UTCJsonBase
{
  private HashMap<String, Object> additionalInfo = new HashMap();
  
  public void addValue(String paramString, Object paramObject)
  {
    if ((paramString != null) && (!this.additionalInfo.containsKey(paramString))) {
      this.additionalInfo.put(paramString, paramObject);
    }
  }
  
  public HashMap<String, Object> getAdditionalInfo()
  {
    return this.additionalInfo;
  }
  
  public void setAdditionalInfo(HashMap<String, Object> paramHashMap)
  {
    this.additionalInfo = paramHashMap;
  }
  
  public String toJson()
  {
    Object localObject = this.additionalInfo;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\utc\model\UTCAdditionalInfoModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */