package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zznb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaInfo
{
  public static final int STREAM_TYPE_BUFFERED = 1;
  public static final int STREAM_TYPE_INVALID = -1;
  public static final int STREAM_TYPE_LIVE = 2;
  public static final int STREAM_TYPE_NONE = 0;
  public static final long UNKNOWN_DURATION = -1L;
  private final String zzaaN;
  private int zzaaO;
  private String zzaaP;
  private MediaMetadata zzaaQ;
  private long zzaaR;
  private List<MediaTrack> zzaaS;
  private TextTrackStyle zzaaT;
  private JSONObject zzaaU;
  
  MediaInfo(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    this.zzaaN = paramString;
    this.zzaaO = -1;
    this.zzaaR = -1L;
  }
  
  MediaInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    this.zzaaN = paramJSONObject.getString("contentId");
    Object localObject1 = paramJSONObject.getString("streamType");
    if ("NONE".equals(localObject1)) {
      this.zzaaO = 0;
    }
    Object localObject2;
    for (;;)
    {
      this.zzaaP = paramJSONObject.getString("contentType");
      if (paramJSONObject.has("metadata"))
      {
        localObject1 = paramJSONObject.getJSONObject("metadata");
        this.zzaaQ = new MediaMetadata(((JSONObject)localObject1).getInt("metadataType"));
        this.zzaaQ.zzg((JSONObject)localObject1);
      }
      this.zzaaR = -1L;
      if ((paramJSONObject.has("duration")) && (!paramJSONObject.isNull("duration")))
      {
        double d = paramJSONObject.optDouble("duration", 0.0D);
        if ((!Double.isNaN(d)) && (!Double.isInfinite(d))) {
          this.zzaaR = zzf.zzg(d);
        }
      }
      if (!paramJSONObject.has("tracks")) {
        break;
      }
      this.zzaaS = new ArrayList();
      localObject1 = paramJSONObject.getJSONArray("tracks");
      while (i < ((JSONArray)localObject1).length())
      {
        localObject2 = new MediaTrack(((JSONArray)localObject1).getJSONObject(i));
        this.zzaaS.add(localObject2);
        i += 1;
      }
      if ("BUFFERED".equals(localObject1)) {
        this.zzaaO = 1;
      } else if ("LIVE".equals(localObject1)) {
        this.zzaaO = 2;
      } else {
        this.zzaaO = -1;
      }
    }
    this.zzaaS = null;
    if (paramJSONObject.has("textTrackStyle"))
    {
      localObject1 = paramJSONObject.getJSONObject("textTrackStyle");
      localObject2 = new TextTrackStyle();
      ((TextTrackStyle)localObject2).zzg((JSONObject)localObject1);
    }
    for (this.zzaaT = ((TextTrackStyle)localObject2);; this.zzaaT = null)
    {
      this.zzaaU = paramJSONObject.optJSONObject("customData");
      return;
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
        } while (!(paramObject instanceof MediaInfo));
        paramObject = (MediaInfo)paramObject;
        if (this.zzaaU != null) {
          break;
        }
        i = 1;
        if (((MediaInfo)paramObject).zzaaU != null) {
          break label169;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.zzaaU == null) || (((MediaInfo)paramObject).zzaaU == null)) {
        break;
      }
      bool1 = bool3;
    } while (!zznb.zze(this.zzaaU, ((MediaInfo)paramObject).zzaaU));
    if ((zzf.zza(this.zzaaN, ((MediaInfo)paramObject).zzaaN)) && (this.zzaaO == ((MediaInfo)paramObject).zzaaO) && (zzf.zza(this.zzaaP, ((MediaInfo)paramObject).zzaaP)) && (zzf.zza(this.zzaaQ, ((MediaInfo)paramObject).zzaaQ)) && (this.zzaaR == ((MediaInfo)paramObject).zzaaR)) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label169:
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
  
  public List<MediaTrack> getMediaTracks()
  {
    return this.zzaaS;
  }
  
  public MediaMetadata getMetadata()
  {
    return this.zzaaQ;
  }
  
  public long getStreamDuration()
  {
    return this.zzaaR;
  }
  
  public int getStreamType()
  {
    return this.zzaaO;
  }
  
  public TextTrackStyle getTextTrackStyle()
  {
    return this.zzaaT;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaaN, Integer.valueOf(this.zzaaO), this.zzaaP, this.zzaaQ, Long.valueOf(this.zzaaR), String.valueOf(this.zzaaU) });
  }
  
  void setContentType(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    this.zzaaP = paramString;
  }
  
  void setCustomData(JSONObject paramJSONObject)
  {
    this.zzaaU = paramJSONObject;
  }
  
  void setStreamType(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt < -1) || (paramInt > 2)) {
      throw new IllegalArgumentException("invalid stream type");
    }
    this.zzaaO = paramInt;
  }
  
  public void setTextTrackStyle(TextTrackStyle paramTextTrackStyle)
  {
    this.zzaaT = paramTextTrackStyle;
  }
  
  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("contentId", this.zzaaN);
        switch (this.zzaaO)
        {
        default: 
          localJSONObject.put("streamType", localObject);
          if (this.zzaaP != null) {
            localJSONObject.put("contentType", this.zzaaP);
          }
          if (this.zzaaQ != null) {
            localJSONObject.put("metadata", this.zzaaQ.toJson());
          }
          if (this.zzaaR <= -1L)
          {
            localJSONObject.put("duration", JSONObject.NULL);
            if (this.zzaaS == null) {
              continue;
            }
            localObject = new JSONArray();
            Iterator localIterator = this.zzaaS.iterator();
            if (localIterator.hasNext())
            {
              ((JSONArray)localObject).put(((MediaTrack)localIterator.next()).toJson());
              continue;
            }
          }
          else
          {
            localJSONObject.put("duration", zzf.zzA(this.zzaaR));
            continue;
          }
          localJSONObject.put("tracks", localObject);
          if (this.zzaaT != null) {
            localJSONObject.put("textTrackStyle", this.zzaaT.toJson());
          }
          if (this.zzaaU == null) {
            break label239;
          }
          localJSONObject.put("customData", this.zzaaU);
          return localJSONObject;
        }
      }
      catch (JSONException localJSONException) {}
      Object localObject = "NONE";
      continue;
      label239:
      return localJSONObject;
      String str = "BUFFERED";
      continue;
      str = "LIVE";
    }
  }
  
  void zza(MediaMetadata paramMediaMetadata)
  {
    this.zzaaQ = paramMediaMetadata;
  }
  
  void zznN()
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(this.zzaaN)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    if (TextUtils.isEmpty(this.zzaaP)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    if (this.zzaaO == -1) {
      throw new IllegalArgumentException("a valid stream type must be specified");
    }
  }
  
  void zzu(List<MediaTrack> paramList)
  {
    this.zzaaS = paramList;
  }
  
  void zzx(long paramLong)
    throws IllegalArgumentException
  {
    if ((paramLong < 0L) && (paramLong != -1L)) {
      throw new IllegalArgumentException("Invalid stream duration");
    }
    this.zzaaR = paramLong;
  }
  
  public static class Builder
  {
    private final MediaInfo zzaaV;
    
    public Builder(String paramString)
      throws IllegalArgumentException
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("Content ID cannot be empty");
      }
      this.zzaaV = new MediaInfo(paramString);
    }
    
    public MediaInfo build()
      throws IllegalArgumentException
    {
      this.zzaaV.zznN();
      return this.zzaaV;
    }
    
    public Builder setContentType(String paramString)
      throws IllegalArgumentException
    {
      this.zzaaV.setContentType(paramString);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.zzaaV.setCustomData(paramJSONObject);
      return this;
    }
    
    public Builder setMediaTracks(List<MediaTrack> paramList)
    {
      this.zzaaV.zzu(paramList);
      return this;
    }
    
    public Builder setMetadata(MediaMetadata paramMediaMetadata)
    {
      this.zzaaV.zza(paramMediaMetadata);
      return this;
    }
    
    public Builder setStreamDuration(long paramLong)
      throws IllegalArgumentException
    {
      this.zzaaV.zzx(paramLong);
      return this;
    }
    
    public Builder setStreamType(int paramInt)
      throws IllegalArgumentException
    {
      this.zzaaV.setStreamType(paramInt);
      return this;
    }
    
    public Builder setTextTrackStyle(TextTrackStyle paramTextTrackStyle)
    {
      this.zzaaV.setTextTrackStyle(paramTextTrackStyle);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\MediaInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */