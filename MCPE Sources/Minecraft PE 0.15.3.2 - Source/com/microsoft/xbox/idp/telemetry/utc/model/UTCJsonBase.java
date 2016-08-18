package com.microsoft.xbox.idp.telemetry.utc.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.microsoft.xbox.idp.telemetry.helpers.UTCLog;

public abstract class UTCJsonBase
{
  public String toJson()
  {
    Object localObject = new GsonBuilder().serializeNulls().create();
    try
    {
      localObject = ((Gson)localObject).toJson(this);
      return (String)localObject;
    }
    catch (JsonIOException localJsonIOException)
    {
      UTCLog.log("UTCJsonSerializer", new Object[] { "Error in json serialization" + localJsonIOException.toString() });
    }
    return "";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\utc\model\UTCJsonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */