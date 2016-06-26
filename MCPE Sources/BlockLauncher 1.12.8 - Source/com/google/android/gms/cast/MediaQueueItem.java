package com.google.android.gms.cast;

import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zznb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQueueItem
{
  public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
  public static final int INVALID_ITEM_ID = 0;
  private JSONObject zzaaU;
  private MediaInfo zzabd;
  private int zzabe = 0;
  private boolean zzabf = true;
  private double zzabg;
  private double zzabh = Double.POSITIVE_INFINITY;
  private double zzabi;
  private long[] zzabj;
  
  private MediaQueueItem(MediaInfo paramMediaInfo)
    throws IllegalArgumentException
  {
    if (paramMediaInfo == null) {
      throw new IllegalArgumentException("media cannot be null.");
    }
    this.zzabd = paramMediaInfo;
  }
  
  private MediaQueueItem(MediaQueueItem paramMediaQueueItem)
    throws IllegalArgumentException
  {
    this.zzabd = paramMediaQueueItem.getMedia();
    if (this.zzabd == null) {
      throw new IllegalArgumentException("media cannot be null.");
    }
    this.zzabe = paramMediaQueueItem.getItemId();
    this.zzabf = paramMediaQueueItem.getAutoplay();
    this.zzabg = paramMediaQueueItem.getStartTime();
    this.zzabh = paramMediaQueueItem.getPlaybackDuration();
    this.zzabi = paramMediaQueueItem.getPreloadTime();
    this.zzabj = paramMediaQueueItem.getActiveTrackIds();
    this.zzaaU = paramMediaQueueItem.getCustomData();
  }
  
  MediaQueueItem(JSONObject paramJSONObject)
    throws JSONException
  {
    zzh(paramJSONObject);
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
        } while (!(paramObject instanceof MediaQueueItem));
        paramObject = (MediaQueueItem)paramObject;
        if (this.zzaaU != null) {
          break;
        }
        i = 1;
        if (((MediaQueueItem)paramObject).zzaaU != null) {
          break label190;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.zzaaU == null) || (((MediaQueueItem)paramObject).zzaaU == null)) {
        break;
      }
      bool1 = bool3;
    } while (!zznb.zze(this.zzaaU, ((MediaQueueItem)paramObject).zzaaU));
    if ((zzf.zza(this.zzabd, ((MediaQueueItem)paramObject).zzabd)) && (this.zzabe == ((MediaQueueItem)paramObject).zzabe) && (this.zzabf == ((MediaQueueItem)paramObject).zzabf) && (this.zzabg == ((MediaQueueItem)paramObject).zzabg) && (this.zzabh == ((MediaQueueItem)paramObject).zzabh) && (this.zzabi == ((MediaQueueItem)paramObject).zzabi) && (zzf.zza(this.zzabj, ((MediaQueueItem)paramObject).zzabj))) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label190:
      j = 0;
      break label51;
    }
  }
  
  public long[] getActiveTrackIds()
  {
    return this.zzabj;
  }
  
  public boolean getAutoplay()
  {
    return this.zzabf;
  }
  
  public JSONObject getCustomData()
  {
    return this.zzaaU;
  }
  
  public int getItemId()
  {
    return this.zzabe;
  }
  
  public MediaInfo getMedia()
  {
    return this.zzabd;
  }
  
  public double getPlaybackDuration()
  {
    return this.zzabh;
  }
  
  public double getPreloadTime()
  {
    return this.zzabi;
  }
  
  public double getStartTime()
  {
    return this.zzabg;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzabd, Integer.valueOf(this.zzabe), Boolean.valueOf(this.zzabf), Double.valueOf(this.zzabg), Double.valueOf(this.zzabh), Double.valueOf(this.zzabi), this.zzabj, String.valueOf(this.zzaaU) });
  }
  
  void setCustomData(JSONObject paramJSONObject)
  {
    this.zzaaU = paramJSONObject;
  }
  
  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("media", this.zzabd.toJson());
      if (this.zzabe != 0) {
        localJSONObject.put("itemId", this.zzabe);
      }
      localJSONObject.put("autoplay", this.zzabf);
      localJSONObject.put("startTime", this.zzabg);
      if (this.zzabh != Double.POSITIVE_INFINITY) {
        localJSONObject.put("playbackDuration", this.zzabh);
      }
      localJSONObject.put("preloadTime", this.zzabi);
      if (this.zzabj != null)
      {
        JSONArray localJSONArray = new JSONArray();
        long[] arrayOfLong = this.zzabj;
        int j = arrayOfLong.length;
        int i = 0;
        while (i < j)
        {
          localJSONArray.put(arrayOfLong[i]);
          i += 1;
        }
        localJSONObject.put("activeTrackIds", localJSONArray);
      }
      if (this.zzaaU != null) {
        localJSONObject.put("customData", this.zzaaU);
      }
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  void zzV(boolean paramBoolean)
  {
    this.zzabf = paramBoolean;
  }
  
  void zza(long[] paramArrayOfLong)
  {
    this.zzabj = paramArrayOfLong;
  }
  
  void zzba(int paramInt)
  {
    this.zzabe = paramInt;
  }
  
  void zzc(double paramDouble)
    throws IllegalArgumentException
  {
    if ((Double.isNaN(paramDouble)) || (paramDouble < 0.0D)) {
      throw new IllegalArgumentException("startTime cannot be negative or NaN.");
    }
    this.zzabg = paramDouble;
  }
  
  void zzd(double paramDouble)
    throws IllegalArgumentException
  {
    if (Double.isNaN(paramDouble)) {
      throw new IllegalArgumentException("playbackDuration cannot be NaN.");
    }
    this.zzabh = paramDouble;
  }
  
  void zze(double paramDouble)
    throws IllegalArgumentException
  {
    if ((Double.isNaN(paramDouble)) || (paramDouble < 0.0D)) {
      throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
    }
    this.zzabi = paramDouble;
  }
  
  public boolean zzh(JSONObject paramJSONObject)
    throws JSONException
  {
    if (paramJSONObject.has("media")) {
      this.zzabd = new MediaInfo(paramJSONObject.getJSONObject("media"));
    }
    for (boolean bool2 = true;; bool2 = false)
    {
      boolean bool1 = bool2;
      int i;
      if (paramJSONObject.has("itemId"))
      {
        i = paramJSONObject.getInt("itemId");
        bool1 = bool2;
        if (this.zzabe != i)
        {
          this.zzabe = i;
          bool1 = true;
        }
      }
      bool2 = bool1;
      if (paramJSONObject.has("autoplay"))
      {
        boolean bool3 = paramJSONObject.getBoolean("autoplay");
        bool2 = bool1;
        if (this.zzabf != bool3)
        {
          this.zzabf = bool3;
          bool2 = true;
        }
      }
      bool1 = bool2;
      double d;
      if (paramJSONObject.has("startTime"))
      {
        d = paramJSONObject.getDouble("startTime");
        bool1 = bool2;
        if (Math.abs(d - this.zzabg) > 1.0E-7D)
        {
          this.zzabg = d;
          bool1 = true;
        }
      }
      bool2 = bool1;
      if (paramJSONObject.has("playbackDuration"))
      {
        d = paramJSONObject.getDouble("playbackDuration");
        bool2 = bool1;
        if (Math.abs(d - this.zzabh) > 1.0E-7D)
        {
          this.zzabh = d;
          bool2 = true;
        }
      }
      bool1 = bool2;
      if (paramJSONObject.has("preloadTime"))
      {
        d = paramJSONObject.getDouble("preloadTime");
        bool1 = bool2;
        if (Math.abs(d - this.zzabi) > 1.0E-7D)
        {
          this.zzabi = d;
          bool1 = true;
        }
      }
      int j;
      long[] arrayOfLong;
      if (paramJSONObject.has("activeTrackIds"))
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("activeTrackIds");
        j = localJSONArray.length();
        arrayOfLong = new long[j];
        i = 0;
        while (i < j)
        {
          arrayOfLong[i] = localJSONArray.getLong(i);
          i += 1;
        }
        if (this.zzabj == null) {
          i = 1;
        }
      }
      for (;;)
      {
        if (i != 0)
        {
          this.zzabj = arrayOfLong;
          bool1 = true;
        }
        if (paramJSONObject.has("customData"))
        {
          this.zzaaU = paramJSONObject.getJSONObject("customData");
          return true;
          if (this.zzabj.length != j)
          {
            i = 1;
          }
          else
          {
            i = 0;
            for (;;)
            {
              if (i >= j) {
                break label425;
              }
              if (this.zzabj[i] != arrayOfLong[i])
              {
                i = 1;
                break;
              }
              i += 1;
            }
          }
        }
        else
        {
          return bool1;
          label425:
          i = 0;
          continue;
          i = 0;
          arrayOfLong = null;
        }
      }
    }
  }
  
  void zznN()
    throws IllegalArgumentException
  {
    if (this.zzabd == null) {
      throw new IllegalArgumentException("media cannot be null.");
    }
    if ((Double.isNaN(this.zzabg)) || (this.zzabg < 0.0D)) {
      throw new IllegalArgumentException("startTime cannot be negative or NaN.");
    }
    if (Double.isNaN(this.zzabh)) {
      throw new IllegalArgumentException("playbackDuration cannot be NaN.");
    }
    if ((Double.isNaN(this.zzabi)) || (this.zzabi < 0.0D)) {
      throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
    }
  }
  
  public static class Builder
  {
    private final MediaQueueItem zzabk;
    
    public Builder(MediaInfo paramMediaInfo)
      throws IllegalArgumentException
    {
      this.zzabk = new MediaQueueItem(paramMediaInfo, null);
    }
    
    public Builder(MediaQueueItem paramMediaQueueItem)
      throws IllegalArgumentException
    {
      this.zzabk = new MediaQueueItem(paramMediaQueueItem, null);
    }
    
    public Builder(JSONObject paramJSONObject)
      throws JSONException
    {
      this.zzabk = new MediaQueueItem(paramJSONObject);
    }
    
    public MediaQueueItem build()
    {
      this.zzabk.zznN();
      return this.zzabk;
    }
    
    public Builder clearItemId()
    {
      this.zzabk.zzba(0);
      return this;
    }
    
    public Builder setActiveTrackIds(long[] paramArrayOfLong)
    {
      this.zzabk.zza(paramArrayOfLong);
      return this;
    }
    
    public Builder setAutoplay(boolean paramBoolean)
    {
      this.zzabk.zzV(paramBoolean);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.zzabk.setCustomData(paramJSONObject);
      return this;
    }
    
    public Builder setPlaybackDuration(double paramDouble)
    {
      this.zzabk.zzd(paramDouble);
      return this;
    }
    
    public Builder setPreloadTime(double paramDouble)
      throws IllegalArgumentException
    {
      this.zzabk.zze(paramDouble);
      return this;
    }
    
    public Builder setStartTime(double paramDouble)
      throws IllegalArgumentException
    {
      this.zzabk.zzc(paramDouble);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\MediaQueueItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */