package com.google.android.gms.internal;

import com.google.android.gms.cast.internal.zzl;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class zzlk
{
  private static final zzl zzaaf = new zzl("GameManagerMessage");
  protected final zzlj zzacE;
  protected final String zzacX;
  protected final long zzacY;
  protected final JSONObject zzacZ;
  protected final int zzadd;
  protected final int zzade;
  protected final String zzadf;
  protected final int zzadg;
  protected final int zzadh;
  protected final List<zzlo> zzadi;
  protected final JSONObject zzadj;
  protected final String zzadk;
  protected final String zzadl;
  
  public zzlk(int paramInt1, int paramInt2, String paramString1, JSONObject paramJSONObject1, int paramInt3, int paramInt4, List<zzlo> paramList, JSONObject paramJSONObject2, String paramString2, String paramString3, long paramLong, String paramString4, zzlj paramzzlj)
  {
    this.zzadd = paramInt1;
    this.zzade = paramInt2;
    this.zzadf = paramString1;
    this.zzacZ = paramJSONObject1;
    this.zzadg = paramInt3;
    this.zzadh = paramInt4;
    this.zzadi = paramList;
    this.zzadj = paramJSONObject2;
    this.zzadk = paramString2;
    this.zzacX = paramString3;
    this.zzacY = paramLong;
    this.zzadl = paramString4;
    this.zzacE = paramzzlj;
  }
  
  private static List<zzlo> zzb(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONArray == null) {
      return localArrayList;
    }
    int i = 0;
    for (;;)
    {
      if (i < paramJSONArray.length())
      {
        Object localObject1 = paramJSONArray.optJSONObject(i);
        if (localObject1 != null) {}
        try
        {
          localObject1 = new zzlo((JSONObject)localObject1);
          if (localObject1 != null) {
            localArrayList.add(localObject1);
          }
          i += 1;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            zzaaf.zzc(localJSONException, "Exception when attempting to parse PlayerInfoMessageComponent at index %d", new Object[] { Integer.valueOf(i) });
            Object localObject2 = null;
          }
        }
      }
    }
    return localArrayList;
  }
  
  protected static zzlk zzi(JSONObject paramJSONObject)
  {
    int i = paramJSONObject.optInt("type", -1);
    switch (i)
    {
    }
    try
    {
      zzaaf.zzf("Unrecognized Game Message type %d", new Object[] { Integer.valueOf(i) });
    }
    catch (JSONException paramJSONObject)
    {
      zzaaf.zzc(paramJSONObject, "Exception while parsing GameManagerMessage from json", new Object[0]);
      break label247;
      zzlj localzzlj = null;
      JSONObject localJSONObject = paramJSONObject.optJSONObject("gameManagerConfig");
      if (localJSONObject == null) {
        break label166;
      }
      localzzlj = new zzlj(localJSONObject);
      label166:
      paramJSONObject = new zzlk(i, paramJSONObject.optInt("statusCode"), paramJSONObject.optString("errorDescription"), paramJSONObject.optJSONObject("extraMessageData"), paramJSONObject.optInt("gameplayState"), paramJSONObject.optInt("lobbyState"), zzb(paramJSONObject.optJSONArray("players")), paramJSONObject.optJSONObject("gameData"), paramJSONObject.optString("gameStatusText"), paramJSONObject.optString("playerId"), paramJSONObject.optLong("requestId"), paramJSONObject.optString("playerToken"), localzzlj);
      return paramJSONObject;
    }
    paramJSONObject = new zzlk(i, paramJSONObject.optInt("statusCode"), paramJSONObject.optString("errorDescription"), paramJSONObject.optJSONObject("extraMessageData"), paramJSONObject.optInt("gameplayState"), paramJSONObject.optInt("lobbyState"), zzb(paramJSONObject.optJSONArray("players")), paramJSONObject.optJSONObject("gameData"), paramJSONObject.optString("gameStatusText"), paramJSONObject.optString("playerId"), -1L, null, null);
    return paramJSONObject;
    label247:
    return null;
  }
  
  public final JSONObject getExtraMessageData()
  {
    return this.zzacZ;
  }
  
  public final JSONObject getGameData()
  {
    return this.zzadj;
  }
  
  public final int getGameplayState()
  {
    return this.zzadg;
  }
  
  public final int getLobbyState()
  {
    return this.zzadh;
  }
  
  public final String getPlayerId()
  {
    return this.zzacX;
  }
  
  public final long getRequestId()
  {
    return this.zzacY;
  }
  
  public final int getStatusCode()
  {
    return this.zzade;
  }
  
  public final int zznY()
  {
    return this.zzadd;
  }
  
  public final String zznZ()
  {
    return this.zzadf;
  }
  
  public final List<zzlo> zzoa()
  {
    return this.zzadi;
  }
  
  public final String zzob()
  {
    return this.zzadk;
  }
  
  public final String zzoc()
  {
    return this.zzadl;
  }
  
  public final zzlj zzod()
  {
    return this.zzacE;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzlk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */