package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzlj
{
  private final String zzada;
  private final int zzadb;
  private final String zzadc;
  
  public zzlj(String paramString1, int paramInt, String paramString2)
  {
    this.zzada = paramString1;
    this.zzadb = paramInt;
    this.zzadc = paramString2;
  }
  
  public zzlj(JSONObject paramJSONObject)
    throws JSONException
  {
    this(paramJSONObject.optString("applicationName"), paramJSONObject.optInt("maxPlayers"), paramJSONObject.optString("version"));
  }
  
  public final int getMaxPlayers()
  {
    return this.zzadb;
  }
  
  public final String getVersion()
  {
    return this.zzadc;
  }
  
  public final String zznX()
  {
    return this.zzada;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzlj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */