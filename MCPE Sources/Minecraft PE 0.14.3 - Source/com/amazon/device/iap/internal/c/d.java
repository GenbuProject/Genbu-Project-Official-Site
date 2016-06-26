package com.amazon.device.iap.internal.c;

import org.json.JSONException;
import org.json.JSONObject;

class d
{
  private final String a;
  private final String b;
  private final long c;
  private final String d;
  
  public d(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.d = paramString3;
    this.c = paramLong;
  }
  
  public static d a(String paramString)
    throws e
  {
    try
    {
      Object localObject = new JSONObject(paramString);
      localObject = new d(((JSONObject)localObject).getString("KEY_USER_ID"), ((JSONObject)localObject).getString("KEY_RECEIPT_STRING"), ((JSONObject)localObject).getString("KEY_REQUEST_ID"), ((JSONObject)localObject).getLong("KEY_TIMESTAMP"));
      return (d)localObject;
    }
    catch (Throwable localThrowable)
    {
      throw new e("Input invalid for PendingReceipt Object:" + paramString, localThrowable);
    }
  }
  
  public String a()
  {
    return this.d;
  }
  
  public String b()
  {
    return this.b;
  }
  
  public long c()
  {
    return this.c;
  }
  
  public String d()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("KEY_USER_ID", this.a);
    localJSONObject.put("KEY_RECEIPT_STRING", this.b);
    localJSONObject.put("KEY_REQUEST_ID", this.d);
    localJSONObject.put("KEY_TIMESTAMP", this.c);
    return localJSONObject.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\c\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */