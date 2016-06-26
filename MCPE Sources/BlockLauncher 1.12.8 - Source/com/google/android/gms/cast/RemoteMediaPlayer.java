package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import com.google.android.gms.cast.internal.zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.cast.internal.zzm;
import com.google.android.gms.cast.internal.zzn;
import com.google.android.gms.cast.internal.zzo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONObject;

@SuppressLint({"MissingRemoteException"})
public class RemoteMediaPlayer
  implements Cast.MessageReceivedCallback
{
  public static final int RESUME_STATE_PAUSE = 2;
  public static final int RESUME_STATE_PLAY = 1;
  public static final int RESUME_STATE_UNCHANGED = 0;
  public static final int STATUS_CANCELED = 2101;
  public static final int STATUS_FAILED = 2100;
  public static final int STATUS_REPLACED = 2103;
  public static final int STATUS_SUCCEEDED = 0;
  public static final int STATUS_TIMED_OUT = 2102;
  private final zzm zzabE = new zzm(null)
  {
    protected void onMetadataUpdated()
    {
      RemoteMediaPlayer.zzb(RemoteMediaPlayer.this);
    }
    
    protected void onPreloadStatusUpdated()
    {
      RemoteMediaPlayer.zzd(RemoteMediaPlayer.this);
    }
    
    protected void onQueueStatusUpdated()
    {
      RemoteMediaPlayer.zzc(RemoteMediaPlayer.this);
    }
    
    protected void onStatusUpdated()
    {
      RemoteMediaPlayer.zza(RemoteMediaPlayer.this);
    }
  };
  private final zza zzabF = new zza();
  private OnPreloadStatusUpdatedListener zzabG;
  private OnQueueStatusUpdatedListener zzabH;
  private OnMetadataUpdatedListener zzabI;
  private OnStatusUpdatedListener zzabJ;
  private final Object zzpV = new Object();
  
  public RemoteMediaPlayer()
  {
    this.zzabE.zza(this.zzabF);
  }
  
  private void onMetadataUpdated()
  {
    if (this.zzabI != null) {
      this.zzabI.onMetadataUpdated();
    }
  }
  
  private void onPreloadStatusUpdated()
  {
    if (this.zzabG != null) {
      this.zzabG.onPreloadStatusUpdated();
    }
  }
  
  private void onQueueStatusUpdated()
  {
    if (this.zzabH != null) {
      this.zzabH.onQueueStatusUpdated();
    }
  }
  
  private void onStatusUpdated()
  {
    if (this.zzabJ != null) {
      this.zzabJ.onStatusUpdated();
    }
  }
  
  private int zzbf(int paramInt)
  {
    MediaStatus localMediaStatus = getMediaStatus();
    int i = 0;
    while (i < localMediaStatus.getQueueItemCount())
    {
      if (localMediaStatus.getQueueItem(i).getItemId() == paramInt) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public long getApproximateStreamPosition()
  {
    synchronized (this.zzpV)
    {
      long l = this.zzabE.getApproximateStreamPosition();
      return l;
    }
  }
  
  public MediaInfo getMediaInfo()
  {
    synchronized (this.zzpV)
    {
      MediaInfo localMediaInfo = this.zzabE.getMediaInfo();
      return localMediaInfo;
    }
  }
  
  public MediaStatus getMediaStatus()
  {
    synchronized (this.zzpV)
    {
      MediaStatus localMediaStatus = this.zzabE.getMediaStatus();
      return localMediaStatus;
    }
  }
  
  public String getNamespace()
  {
    return this.zzabE.getNamespace();
  }
  
  public long getStreamDuration()
  {
    synchronized (this.zzpV)
    {
      long l = this.zzabE.getStreamDuration();
      return l;
    }
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo)
  {
    return load(paramGoogleApiClient, paramMediaInfo, true, 0L, null, null);
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, 0L, null, null);
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, paramLong, null, null);
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong, JSONObject paramJSONObject)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, paramLong, null, paramJSONObject);
  }
  
  public PendingResult<MediaChannelResult> load(final GoogleApiClient paramGoogleApiClient, final MediaInfo paramMediaInfo, final boolean paramBoolean, final long paramLong, long[] paramArrayOfLong, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramMediaInfo, paramBoolean, paramLong, paramJSONObject, this.zzabS);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public void onMessageReceived(CastDevice paramCastDevice, String paramString1, String paramString2)
  {
    this.zzabE.zzcf(paramString2);
  }
  
  public PendingResult<MediaChannelResult> pause(GoogleApiClient paramGoogleApiClient)
  {
    return pause(paramGoogleApiClient, null);
  }
  
  public PendingResult<MediaChannelResult> pause(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> play(GoogleApiClient paramGoogleApiClient)
  {
    return play(paramGoogleApiClient, null);
  }
  
  public PendingResult<MediaChannelResult> play(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zzc(this.zzacm, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueAppendItem(GoogleApiClient paramGoogleApiClient, MediaQueueItem paramMediaQueueItem, JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    return queueInsertItems(paramGoogleApiClient, new MediaQueueItem[] { paramMediaQueueItem }, 0, paramJSONObject);
  }
  
  public PendingResult<MediaChannelResult> queueInsertAndPlayItem(final GoogleApiClient paramGoogleApiClient, final MediaQueueItem paramMediaQueueItem, final int paramInt, final long paramLong, JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            zzm localzzm = RemoteMediaPlayer.zzg(RemoteMediaPlayer.this);
            zzo localzzo = this.zzacm;
            MediaQueueItem localMediaQueueItem = paramMediaQueueItem;
            int i = paramInt;
            long l = paramLong;
            JSONObject localJSONObject = this.zzabS;
            localzzm.zza(localzzo, new MediaQueueItem[] { localMediaQueueItem }, i, 0, 0, l, localJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient paramGoogleApiClient, MediaQueueItem paramMediaQueueItem, int paramInt, JSONObject paramJSONObject)
  {
    return queueInsertAndPlayItem(paramGoogleApiClient, paramMediaQueueItem, paramInt, -1L, paramJSONObject);
  }
  
  public PendingResult<MediaChannelResult> queueInsertItems(final GoogleApiClient paramGoogleApiClient, final MediaQueueItem[] paramArrayOfMediaQueueItem, final int paramInt, final JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramArrayOfMediaQueueItem, paramInt, 0, -1, -1L, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueJumpToItem(final GoogleApiClient paramGoogleApiClient, final int paramInt, final long paramLong, JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          if (RemoteMediaPlayer.zza(RemoteMediaPlayer.this, paramInt) == -1)
          {
            zza(zzq(new Status(0)));
            return;
          }
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramInt, paramLong, null, 0, null, this.zzabS);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient paramGoogleApiClient, int paramInt, JSONObject paramJSONObject)
  {
    return queueJumpToItem(paramGoogleApiClient, paramInt, -1L, paramJSONObject);
  }
  
  public PendingResult<MediaChannelResult> queueLoad(final GoogleApiClient paramGoogleApiClient, final MediaQueueItem[] paramArrayOfMediaQueueItem, final int paramInt1, final int paramInt2, final long paramLong, JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramArrayOfMediaQueueItem, paramInt1, paramInt2, paramLong, this.zzabS);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient paramGoogleApiClient, MediaQueueItem[] paramArrayOfMediaQueueItem, int paramInt1, int paramInt2, JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    return queueLoad(paramGoogleApiClient, paramArrayOfMediaQueueItem, paramInt1, paramInt2, -1L, paramJSONObject);
  }
  
  public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(final GoogleApiClient paramGoogleApiClient, final int paramInt1, final int paramInt2, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        int j = 0;
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          i = RemoteMediaPlayer.zza(RemoteMediaPlayer.this, paramInt1);
          if (i == -1)
          {
            zza(zzq(new Status(0)));
            return;
          }
          if (paramInt2 < 0)
          {
            zza(zzq(new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", new Object[] { Integer.valueOf(paramInt2) }))));
            return;
          }
        }
        if (i == paramInt2)
        {
          zza(zzq(new Status(0)));
          return;
        }
        if (paramInt2 > i) {}
        for (int i = paramInt2 + 1;; i = paramInt2)
        {
          Object localObject2 = RemoteMediaPlayer.this.getMediaStatus().getQueueItem(i);
          i = j;
          if (localObject2 != null) {
            i = ((MediaQueueItem)localObject2).getItemId();
          }
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            localObject2 = RemoteMediaPlayer.zzg(RemoteMediaPlayer.this);
            zzo localzzo = this.zzacm;
            j = paramInt1;
            JSONObject localJSONObject = paramJSONObject;
            ((zzm)localObject2).zza(localzzo, new int[] { j }, i, localJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueNext(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, 0, -1L, null, 1, null, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queuePrev(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, 0, -1L, null, -1, null, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueRemoveItem(final GoogleApiClient paramGoogleApiClient, final int paramInt, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          if (RemoteMediaPlayer.zza(RemoteMediaPlayer.this, paramInt) == -1)
          {
            zza(zzq(new Status(0)));
            return;
          }
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            zzm localzzm = RemoteMediaPlayer.zzg(RemoteMediaPlayer.this);
            zzo localzzo = this.zzacm;
            int i = paramInt;
            JSONObject localJSONObject = paramJSONObject;
            localzzm.zza(localzzo, new int[] { i }, localJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueRemoveItems(final GoogleApiClient paramGoogleApiClient, final int[] paramArrayOfInt, final JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramArrayOfInt, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueReorderItems(final GoogleApiClient paramGoogleApiClient, final int[] paramArrayOfInt, final int paramInt, final JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramArrayOfInt, paramInt, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueSetRepeatMode(final GoogleApiClient paramGoogleApiClient, final int paramInt, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, 0, -1L, null, 0, Integer.valueOf(paramInt), paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> queueUpdateItems(final GoogleApiClient paramGoogleApiClient, final MediaQueueItem[] paramArrayOfMediaQueueItem, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, 0, -1L, paramArrayOfMediaQueueItem, 0, null, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> requestStatus(final GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> seek(GoogleApiClient paramGoogleApiClient, long paramLong)
  {
    return seek(paramGoogleApiClient, paramLong, 0, null);
  }
  
  public PendingResult<MediaChannelResult> seek(GoogleApiClient paramGoogleApiClient, long paramLong, int paramInt)
  {
    return seek(paramGoogleApiClient, paramLong, paramInt, null);
  }
  
  public PendingResult<MediaChannelResult> seek(final GoogleApiClient paramGoogleApiClient, final long paramLong, int paramInt, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramLong, paramJSONObject, this.zzabS);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> setActiveMediaTracks(final GoogleApiClient paramGoogleApiClient, final long[] paramArrayOfLong)
  {
    if (paramArrayOfLong == null) {
      throw new IllegalArgumentException("trackIds cannot be null");
    }
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramArrayOfLong);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener paramOnMetadataUpdatedListener)
  {
    this.zzabI = paramOnMetadataUpdatedListener;
  }
  
  public void setOnPreloadStatusUpdatedListener(OnPreloadStatusUpdatedListener paramOnPreloadStatusUpdatedListener)
  {
    this.zzabG = paramOnPreloadStatusUpdatedListener;
  }
  
  public void setOnQueueStatusUpdatedListener(OnQueueStatusUpdatedListener paramOnQueueStatusUpdatedListener)
  {
    this.zzabH = paramOnQueueStatusUpdatedListener;
  }
  
  public void setOnStatusUpdatedListener(OnStatusUpdatedListener paramOnStatusUpdatedListener)
  {
    this.zzabJ = paramOnStatusUpdatedListener;
  }
  
  public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    return setStreamMute(paramGoogleApiClient, paramBoolean, null);
  }
  
  public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient paramGoogleApiClient, final boolean paramBoolean, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      /* Error */
      protected void zza(zze paramAnonymouszze)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   4: invokestatic 41	com/google/android/gms/cast/RemoteMediaPlayer:zze	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
        //   7: astore_1
        //   8: aload_1
        //   9: monitorenter
        //   10: aload_0
        //   11: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   14: invokestatic 45	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   17: aload_0
        //   18: getfield 23	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabL	Lcom/google/android/gms/common/api/GoogleApiClient;
        //   21: invokevirtual 50	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   24: aload_0
        //   25: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   28: invokestatic 54	com/google/android/gms/cast/RemoteMediaPlayer:zzg	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/internal/zzm;
        //   31: aload_0
        //   32: getfield 58	com/google/android/gms/cast/RemoteMediaPlayer$22:zzacm	Lcom/google/android/gms/cast/internal/zzo;
        //   35: aload_0
        //   36: getfield 25	com/google/android/gms/cast/RemoteMediaPlayer$22:zzach	Z
        //   39: aload_0
        //   40: getfield 27	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabS	Lorg/json/JSONObject;
        //   43: invokevirtual 63	com/google/android/gms/cast/internal/zzm:zza	(Lcom/google/android/gms/cast/internal/zzo;ZLorg/json/JSONObject;)J
        //   46: pop2
        //   47: aload_0
        //   48: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   51: invokestatic 45	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   54: aconst_null
        //   55: invokevirtual 50	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   58: aload_1
        //   59: monitorexit
        //   60: return
        //   61: astore_2
        //   62: aload_0
        //   63: aload_0
        //   64: new 65	com/google/android/gms/common/api/Status
        //   67: dup
        //   68: sipush 2100
        //   71: invokespecial 68	com/google/android/gms/common/api/Status:<init>	(I)V
        //   74: invokevirtual 72	com/google/android/gms/cast/RemoteMediaPlayer$22:zzq	(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //   77: invokevirtual 75	com/google/android/gms/cast/RemoteMediaPlayer$22:zza	(Lcom/google/android/gms/common/api/Result;)V
        //   80: aload_0
        //   81: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   84: invokestatic 45	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   87: aconst_null
        //   88: invokevirtual 50	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   91: goto -33 -> 58
        //   94: astore_2
        //   95: aload_1
        //   96: monitorexit
        //   97: aload_2
        //   98: athrow
        //   99: astore_2
        //   100: aload_0
        //   101: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$22:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   104: invokestatic 45	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   107: aconst_null
        //   108: invokevirtual 50	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   111: aload_2
        //   112: athrow
        //   113: astore_2
        //   114: goto -52 -> 62
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	22
        //   0	117	1	paramAnonymouszze	zze
        //   61	1	2	localIOException	IOException
        //   94	4	2	localObject1	Object
        //   99	13	2	localObject2	Object
        //   113	1	2	localIllegalStateException	IllegalStateException
        // Exception table:
        //   from	to	target	type
        //   24	47	61	java/io/IOException
        //   10	24	94	finally
        //   47	58	94	finally
        //   58	60	94	finally
        //   80	91	94	finally
        //   95	97	94	finally
        //   100	113	94	finally
        //   24	47	99	finally
        //   62	80	99	finally
        //   24	47	113	java/lang/IllegalStateException
      }
    });
  }
  
  public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient paramGoogleApiClient, double paramDouble)
    throws IllegalArgumentException
  {
    return setStreamVolume(paramGoogleApiClient, paramDouble, null);
  }
  
  public PendingResult<MediaChannelResult> setStreamVolume(final GoogleApiClient paramGoogleApiClient, final double paramDouble, JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    }
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      /* Error */
      protected void zza(zze paramAnonymouszze)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   4: invokestatic 43	com/google/android/gms/cast/RemoteMediaPlayer:zze	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Ljava/lang/Object;
        //   7: astore_1
        //   8: aload_1
        //   9: monitorenter
        //   10: aload_0
        //   11: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   14: invokestatic 47	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   17: aload_0
        //   18: getfield 23	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabL	Lcom/google/android/gms/common/api/GoogleApiClient;
        //   21: invokevirtual 52	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   24: aload_0
        //   25: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   28: invokestatic 56	com/google/android/gms/cast/RemoteMediaPlayer:zzg	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/internal/zzm;
        //   31: aload_0
        //   32: getfield 60	com/google/android/gms/cast/RemoteMediaPlayer$21:zzacm	Lcom/google/android/gms/cast/internal/zzo;
        //   35: aload_0
        //   36: getfield 25	com/google/android/gms/cast/RemoteMediaPlayer$21:zzacg	D
        //   39: aload_0
        //   40: getfield 27	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabS	Lorg/json/JSONObject;
        //   43: invokevirtual 65	com/google/android/gms/cast/internal/zzm:zza	(Lcom/google/android/gms/cast/internal/zzo;DLorg/json/JSONObject;)J
        //   46: pop2
        //   47: aload_0
        //   48: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   51: invokestatic 47	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   54: aconst_null
        //   55: invokevirtual 52	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   58: aload_1
        //   59: monitorexit
        //   60: return
        //   61: astore_2
        //   62: aload_0
        //   63: aload_0
        //   64: new 67	com/google/android/gms/common/api/Status
        //   67: dup
        //   68: sipush 2100
        //   71: invokespecial 70	com/google/android/gms/common/api/Status:<init>	(I)V
        //   74: invokevirtual 74	com/google/android/gms/cast/RemoteMediaPlayer$21:zzq	(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/cast/RemoteMediaPlayer$MediaChannelResult;
        //   77: invokevirtual 77	com/google/android/gms/cast/RemoteMediaPlayer$21:zza	(Lcom/google/android/gms/common/api/Result;)V
        //   80: aload_0
        //   81: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   84: invokestatic 47	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   87: aconst_null
        //   88: invokevirtual 52	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   91: goto -33 -> 58
        //   94: astore_2
        //   95: aload_1
        //   96: monitorexit
        //   97: aload_2
        //   98: athrow
        //   99: astore_2
        //   100: aload_0
        //   101: getfield 21	com/google/android/gms/cast/RemoteMediaPlayer$21:zzabK	Lcom/google/android/gms/cast/RemoteMediaPlayer;
        //   104: invokestatic 47	com/google/android/gms/cast/RemoteMediaPlayer:zzf	(Lcom/google/android/gms/cast/RemoteMediaPlayer;)Lcom/google/android/gms/cast/RemoteMediaPlayer$zza;
        //   107: aconst_null
        //   108: invokevirtual 52	com/google/android/gms/cast/RemoteMediaPlayer$zza:zzc	(Lcom/google/android/gms/common/api/GoogleApiClient;)V
        //   111: aload_2
        //   112: athrow
        //   113: astore_2
        //   114: goto -52 -> 62
        //   117: astore_2
        //   118: goto -56 -> 62
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	121	0	this	21
        //   0	121	1	paramAnonymouszze	zze
        //   61	1	2	localIOException	IOException
        //   94	4	2	localObject1	Object
        //   99	13	2	localObject2	Object
        //   113	1	2	localIllegalStateException	IllegalStateException
        //   117	1	2	localIllegalArgumentException	IllegalArgumentException
        // Exception table:
        //   from	to	target	type
        //   24	47	61	java/io/IOException
        //   10	24	94	finally
        //   47	58	94	finally
        //   58	60	94	finally
        //   80	91	94	finally
        //   95	97	94	finally
        //   100	113	94	finally
        //   24	47	99	finally
        //   62	80	99	finally
        //   24	47	113	java/lang/IllegalStateException
        //   24	47	117	java/lang/IllegalArgumentException
      }
    });
  }
  
  public PendingResult<MediaChannelResult> setTextTrackStyle(final GoogleApiClient paramGoogleApiClient, final TextTrackStyle paramTextTrackStyle)
  {
    if (paramTextTrackStyle == null) {
      throw new IllegalArgumentException("trackStyle cannot be null");
    }
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zza(this.zzacm, paramTextTrackStyle);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> stop(GoogleApiClient paramGoogleApiClient)
  {
    return stop(paramGoogleApiClient, null);
  }
  
  public PendingResult<MediaChannelResult> stop(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.zzb(new zzb(paramGoogleApiClient)
    {
      protected void zza(zze arg1)
      {
        synchronized (RemoteMediaPlayer.zze(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zzb(this.zzacm, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              zza(zzq(new Status(2100)));
              RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.zzf(RemoteMediaPlayer.this).zzc(null);
          }
          return;
        }
      }
    });
  }
  
  public static abstract interface MediaChannelResult
    extends Result
  {
    public abstract JSONObject getCustomData();
  }
  
  public static abstract interface OnMetadataUpdatedListener
  {
    public abstract void onMetadataUpdated();
  }
  
  public static abstract interface OnPreloadStatusUpdatedListener
  {
    public abstract void onPreloadStatusUpdated();
  }
  
  public static abstract interface OnQueueStatusUpdatedListener
  {
    public abstract void onQueueStatusUpdated();
  }
  
  public static abstract interface OnStatusUpdatedListener
  {
    public abstract void onStatusUpdated();
  }
  
  private class zza
    implements zzn
  {
    private GoogleApiClient zzaci;
    private long zzacj = 0L;
    
    public zza() {}
    
    public void zza(String paramString1, String paramString2, long paramLong, String paramString3)
      throws IOException
    {
      if (this.zzaci == null) {
        throw new IOException("No GoogleApiClient available");
      }
      Cast.CastApi.sendMessage(this.zzaci, paramString1, paramString2).setResultCallback(new zza(paramLong));
    }
    
    public void zzc(GoogleApiClient paramGoogleApiClient)
    {
      this.zzaci = paramGoogleApiClient;
    }
    
    public long zznQ()
    {
      long l = this.zzacj + 1L;
      this.zzacj = l;
      return l;
    }
    
    private final class zza
      implements ResultCallback<Status>
    {
      private final long zzack;
      
      zza(long paramLong)
      {
        this.zzack = paramLong;
      }
      
      public void zzp(Status paramStatus)
      {
        if (!paramStatus.isSuccess()) {
          RemoteMediaPlayer.zzg(RemoteMediaPlayer.this).zzb(this.zzack, paramStatus.getStatusCode());
        }
      }
    }
  }
  
  private static abstract class zzb
    extends zzb<RemoteMediaPlayer.MediaChannelResult>
  {
    zzo zzacm = new zzo()
    {
      public void zza(long paramAnonymousLong, int paramAnonymousInt, Object paramAnonymousObject)
      {
        if ((paramAnonymousObject instanceof JSONObject)) {}
        for (paramAnonymousObject = (JSONObject)paramAnonymousObject;; paramAnonymousObject = null)
        {
          RemoteMediaPlayer.zzb.this.zza(new RemoteMediaPlayer.zzc(new Status(paramAnonymousInt), (JSONObject)paramAnonymousObject));
          return;
        }
      }
      
      public void zzy(long paramAnonymousLong)
      {
        RemoteMediaPlayer.zzb.this.zza(RemoteMediaPlayer.zzb.this.zzq(new Status(2103)));
      }
    };
    
    zzb(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public RemoteMediaPlayer.MediaChannelResult zzq(final Status paramStatus)
    {
      new RemoteMediaPlayer.MediaChannelResult()
      {
        public JSONObject getCustomData()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static final class zzc
    implements RemoteMediaPlayer.MediaChannelResult
  {
    private final Status zzUX;
    private final JSONObject zzaaU;
    
    zzc(Status paramStatus, JSONObject paramJSONObject)
    {
      this.zzUX = paramStatus;
      this.zzaaU = paramJSONObject;
    }
    
    public JSONObject getCustomData()
    {
      return this.zzaaU;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\RemoteMediaPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */