package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzfs
{
  private final String zzDJ;
  private final zzjp zzpD;
  
  public zzfs(zzjp paramzzjp)
  {
    this(paramzzjp, "");
  }
  
  public zzfs(zzjp paramzzjp, String paramString)
  {
    this.zzpD = paramzzjp;
    this.zzDJ = paramString;
  }
  
  public void zza(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("width", paramInt1).put("height", paramInt2).put("maxSizeWidth", paramInt3).put("maxSizeHeight", paramInt4).put("density", paramFloat).put("rotation", paramInt5);
      this.zzpD.zzb("onScreenInfoChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      zzin.zzb("Error occured while obtaining screen information.", localJSONException);
    }
  }
  
  public void zzam(String paramString)
  {
    try
    {
      paramString = new JSONObject().put("message", paramString).put("action", this.zzDJ);
      this.zzpD.zzb("onError", paramString);
      return;
    }
    catch (JSONException paramString)
    {
      zzin.zzb("Error occurred while dispatching error event.", paramString);
    }
  }
  
  public void zzan(String paramString)
  {
    try
    {
      paramString = new JSONObject().put("js", paramString);
      this.zzpD.zzb("onReadyEventReceived", paramString);
      return;
    }
    catch (JSONException paramString)
    {
      zzin.zzb("Error occured while dispatching ready Event.", paramString);
    }
  }
  
  public void zzao(String paramString)
  {
    try
    {
      paramString = new JSONObject().put("state", paramString);
      this.zzpD.zzb("onStateChanged", paramString);
      return;
    }
    catch (JSONException paramString)
    {
      zzin.zzb("Error occured while dispatching state change.", paramString);
    }
  }
  
  public void zzb(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("x", paramInt1).put("y", paramInt2).put("width", paramInt3).put("height", paramInt4);
      this.zzpD.zzb("onSizeChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      zzin.zzb("Error occured while dispatching size change.", localJSONException);
    }
  }
  
  public void zzc(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("x", paramInt1).put("y", paramInt2).put("width", paramInt3).put("height", paramInt4);
      this.zzpD.zzb("onDefaultPositionReceived", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      zzin.zzb("Error occured while dispatching default position.", localJSONException);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */