package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zznb;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack
{
  public static final int SUBTYPE_CAPTIONS = 2;
  public static final int SUBTYPE_CHAPTERS = 4;
  public static final int SUBTYPE_DESCRIPTIONS = 3;
  public static final int SUBTYPE_METADATA = 5;
  public static final int SUBTYPE_NONE = 0;
  public static final int SUBTYPE_SUBTITLES = 1;
  public static final int SUBTYPE_UNKNOWN = -1;
  public static final int TYPE_AUDIO = 2;
  public static final int TYPE_TEXT = 1;
  public static final int TYPE_UNKNOWN = 0;
  public static final int TYPE_VIDEO = 3;
  private String mName;
  private long zzUZ;
  private String zzaaL;
  private String zzaaN;
  private String zzaaP;
  private JSONObject zzaaU;
  private int zzabB;
  private int zzabC;
  
  MediaTrack(long paramLong, int paramInt)
    throws IllegalArgumentException
  {
    clear();
    this.zzUZ = paramLong;
    if ((paramInt <= 0) || (paramInt > 3)) {
      throw new IllegalArgumentException("invalid type " + paramInt);
    }
    this.zzabB = paramInt;
  }
  
  MediaTrack(JSONObject paramJSONObject)
    throws JSONException
  {
    zzg(paramJSONObject);
  }
  
  private void clear()
  {
    this.zzUZ = 0L;
    this.zzabB = 0;
    this.zzaaN = null;
    this.mName = null;
    this.zzaaL = null;
    this.zzabC = -1;
    this.zzaaU = null;
  }
  
  private void zzg(JSONObject paramJSONObject)
    throws JSONException
  {
    clear();
    this.zzUZ = paramJSONObject.getLong("trackId");
    String str = paramJSONObject.getString("type");
    if ("TEXT".equals(str))
    {
      this.zzabB = 1;
      this.zzaaN = paramJSONObject.optString("trackContentId", null);
      this.zzaaP = paramJSONObject.optString("trackContentType", null);
      this.mName = paramJSONObject.optString("name", null);
      this.zzaaL = paramJSONObject.optString("language", null);
      if (!paramJSONObject.has("subtype")) {
        break label276;
      }
      str = paramJSONObject.getString("subtype");
      if (!"SUBTITLES".equals(str)) {
        break label181;
      }
      this.zzabC = 1;
    }
    for (;;)
    {
      this.zzaaU = paramJSONObject.optJSONObject("customData");
      return;
      if ("AUDIO".equals(str))
      {
        this.zzabB = 2;
        break;
      }
      if ("VIDEO".equals(str))
      {
        this.zzabB = 3;
        break;
      }
      throw new JSONException("invalid type: " + str);
      label181:
      if ("CAPTIONS".equals(str))
      {
        this.zzabC = 2;
      }
      else if ("DESCRIPTIONS".equals(str))
      {
        this.zzabC = 3;
      }
      else if ("CHAPTERS".equals(str))
      {
        this.zzabC = 4;
      }
      else if ("METADATA".equals(str))
      {
        this.zzabC = 5;
      }
      else
      {
        throw new JSONException("invalid subtype: " + str);
        label276:
        this.zzabC = 0;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool3 = false;
    if (this == paramObject) {
      bool1 = true;
    }
    int i;
    int j;
    label51:
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool3;
        } while (!(paramObject instanceof MediaTrack));
        paramObject = (MediaTrack)paramObject;
        if (this.zzaaU != null) {
          break;
        }
        i = 1;
        if (((MediaTrack)paramObject).zzaaU != null) {
          break label194;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.zzaaU == null) || (((MediaTrack)paramObject).zzaaU == null)) {
        break;
      }
      bool1 = bool3;
    } while (!zznb.zze(this.zzaaU, ((MediaTrack)paramObject).zzaaU));
    if ((this.zzUZ == ((MediaTrack)paramObject).zzUZ) && (this.zzabB == ((MediaTrack)paramObject).zzabB) && (zzf.zza(this.zzaaN, ((MediaTrack)paramObject).zzaaN)) && (zzf.zza(this.zzaaP, ((MediaTrack)paramObject).zzaaP)) && (zzf.zza(this.mName, ((MediaTrack)paramObject).mName)) && (zzf.zza(this.zzaaL, ((MediaTrack)paramObject).zzaaL)) && (this.zzabC == ((MediaTrack)paramObject).zzabC)) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label194:
      j = 0;
      break label51;
    }
  }
  
  public String getContentId()
  {
    return this.zzaaN;
  }
  
  public String getContentType()
  {
    return this.zzaaP;
  }
  
  public JSONObject getCustomData()
  {
    return this.zzaaU;
  }
  
  public long getId()
  {
    return this.zzUZ;
  }
  
  public String getLanguage()
  {
    return this.zzaaL;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public int getSubtype()
  {
    return this.zzabC;
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzUZ), Integer.valueOf(this.zzabB), this.zzaaN, this.zzaaP, this.mName, this.zzaaL, Integer.valueOf(this.zzabC), this.zzaaU });
  }
  
  public void setContentId(String paramString)
  {
    this.zzaaN = paramString;
  }
  
  public void setContentType(String paramString)
  {
    this.zzaaP = paramString;
  }
  
  void setCustomData(JSONObject paramJSONObject)
  {
    this.zzaaU = paramJSONObject;
  }
  
  void setLanguage(String paramString)
  {
    this.zzaaL = paramString;
  }
  
  void setName(String paramString)
  {
    this.mName = paramString;
  }
  
  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("trackId", this.zzUZ);
      switch (this.zzabB)
      {
      case 1: 
        if (this.zzaaN != null) {
          localJSONObject.put("trackContentId", this.zzaaN);
        }
        if (this.zzaaP != null) {
          localJSONObject.put("trackContentType", this.zzaaP);
        }
        if (this.mName != null) {
          localJSONObject.put("name", this.mName);
        }
        if (!TextUtils.isEmpty(this.zzaaL)) {
          localJSONObject.put("language", this.zzaaL);
        }
        switch (this.zzabC)
        {
        }
        break;
      }
      for (;;)
      {
        if (this.zzaaU == null) {
          break label282;
        }
        localJSONObject.put("customData", this.zzaaU);
        return localJSONObject;
        localJSONObject.put("type", "TEXT");
        break;
        localJSONObject.put("type", "AUDIO");
        break;
        localJSONObject.put("type", "VIDEO");
        break;
        localJSONObject.put("subtype", "SUBTITLES");
        continue;
        localJSONObject.put("subtype", "CAPTIONS");
        continue;
        localJSONObject.put("subtype", "DESCRIPTIONS");
        continue;
        localJSONObject.put("subtype", "CHAPTERS");
        continue;
        localJSONObject.put("subtype", "METADATA");
        continue;
        break;
      }
      label282:
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  void zzbe(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt <= -1) || (paramInt > 5)) {
      throw new IllegalArgumentException("invalid subtype " + paramInt);
    }
    if ((paramInt != 0) && (this.zzabB != 1)) {
      throw new IllegalArgumentException("subtypes are only valid for text tracks");
    }
    this.zzabC = paramInt;
  }
  
  public static class Builder
  {
    private final MediaTrack zzabD;
    
    public Builder(long paramLong, int paramInt)
      throws IllegalArgumentException
    {
      this.zzabD = new MediaTrack(paramLong, paramInt);
    }
    
    public MediaTrack build()
    {
      return this.zzabD;
    }
    
    public Builder setContentId(String paramString)
    {
      this.zzabD.setContentId(paramString);
      return this;
    }
    
    public Builder setContentType(String paramString)
    {
      this.zzabD.setContentType(paramString);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.zzabD.setCustomData(paramJSONObject);
      return this;
    }
    
    public Builder setLanguage(String paramString)
    {
      this.zzabD.setLanguage(paramString);
      return this;
    }
    
    public Builder setLanguage(Locale paramLocale)
    {
      this.zzabD.setLanguage(zzf.zzb(paramLocale));
      return this;
    }
    
    public Builder setName(String paramString)
    {
      this.zzabD.setName(paramString);
      return this;
    }
    
    public Builder setSubtype(int paramInt)
      throws IllegalArgumentException
    {
      this.zzabD.zzbe(paramInt);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\MediaTrack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */