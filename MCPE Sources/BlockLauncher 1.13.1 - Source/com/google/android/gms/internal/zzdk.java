package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzdk
  implements zzdf
{
  final HashMap<String, zzjd<JSONObject>> zzzz = new HashMap();
  
  public Future<JSONObject> zzR(String paramString)
  {
    zzjd localzzjd = new zzjd();
    this.zzzz.put(paramString, localzzjd);
    return localzzjd;
  }
  
  public void zzS(String paramString)
  {
    zzjd localzzjd = (zzjd)this.zzzz.get(paramString);
    if (localzzjd == null)
    {
      zzin.e("Could not find the ad request for the corresponding ad response.");
      return;
    }
    if (!localzzjd.isDone()) {
      localzzjd.cancel(true);
    }
    this.zzzz.remove(paramString);
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    zzd((String)paramMap.get("request_id"), (String)paramMap.get("fetched_ad"));
  }
  
  public void zzd(String paramString1, String paramString2)
  {
    zzin.zzaI("Received ad from the cache.");
    zzjd localzzjd = (zzjd)this.zzzz.get(paramString1);
    if (localzzjd == null)
    {
      zzin.e("Could not find the ad request for the corresponding ad response.");
      return;
    }
    try
    {
      localzzjd.zzg(new JSONObject(paramString2));
      return;
    }
    catch (JSONException paramString2)
    {
      zzin.zzb("Failed constructing JSON object from value passed from javascript", paramString2);
      localzzjd.zzg(null);
      return;
    }
    finally
    {
      this.zzzz.remove(paramString1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */