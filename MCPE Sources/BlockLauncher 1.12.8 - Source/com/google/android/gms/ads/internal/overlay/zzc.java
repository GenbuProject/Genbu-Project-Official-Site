package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzmq;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzhb
@TargetApi(14)
public class zzc
  extends zzi
  implements AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener
{
  private static final Map<Integer, String> zzDN = new HashMap();
  private final zzt zzDO;
  private int zzDP = 0;
  private int zzDQ = 0;
  private MediaPlayer zzDR;
  private Uri zzDS;
  private int zzDT;
  private int zzDU;
  private int zzDV;
  private int zzDW;
  private int zzDX;
  private float zzDY = 1.0F;
  private boolean zzDZ;
  private boolean zzEa;
  private int zzEb;
  private zzh zzEc;
  
  static
  {
    zzDN.put(Integer.valueOf(64532), "MEDIA_ERROR_IO");
    zzDN.put(Integer.valueOf(64529), "MEDIA_ERROR_MALFORMED");
    zzDN.put(Integer.valueOf(64526), "MEDIA_ERROR_UNSUPPORTED");
    zzDN.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
    zzDN.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
    zzDN.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
    zzDN.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
    zzDN.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
    zzDN.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
    zzDN.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
    zzDN.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
    zzDN.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
    zzDN.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
    zzDN.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
    zzDN.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
    zzDN.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
  }
  
  public zzc(Context paramContext, zzt paramzzt)
  {
    super(paramContext);
    setSurfaceTextureListener(this);
    this.zzDO = paramzzt;
    this.zzDO.zza(this);
  }
  
  private void zzb(float paramFloat)
  {
    if (this.zzDR != null) {}
    try
    {
      this.zzDR.setVolume(paramFloat, paramFloat);
      return;
    }
    catch (IllegalStateException localIllegalStateException) {}
    zzin.zzaK("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
    return;
  }
  
  private void zzfa()
  {
    zzin.v("AdMediaPlayerView init MediaPlayer");
    SurfaceTexture localSurfaceTexture = getSurfaceTexture();
    if ((this.zzDS == null) || (localSurfaceTexture == null)) {
      return;
    }
    zzv(false);
    try
    {
      this.zzDR = new MediaPlayer();
      this.zzDR.setOnBufferingUpdateListener(this);
      this.zzDR.setOnCompletionListener(this);
      this.zzDR.setOnErrorListener(this);
      this.zzDR.setOnInfoListener(this);
      this.zzDR.setOnPreparedListener(this);
      this.zzDR.setOnVideoSizeChangedListener(this);
      this.zzDV = 0;
      this.zzDR.setDataSource(getContext(), this.zzDS);
      this.zzDR.setSurface(new Surface(localSurfaceTexture));
      this.zzDR.setAudioStreamType(3);
      this.zzDR.setScreenOnWhilePlaying(true);
      this.zzDR.prepareAsync();
      zzw(1);
      return;
    }
    catch (IOException localIOException)
    {
      zzin.zzd("Failed to initialize MediaPlayer at " + this.zzDS, localIOException);
      onError(this.zzDR, 1, 0);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
  }
  
  private void zzfb()
  {
    if ((zzfe()) && (this.zzDR.getCurrentPosition() > 0) && (this.zzDQ != 3))
    {
      zzin.v("AdMediaPlayerView nudging MediaPlayer");
      zzb(0.0F);
      this.zzDR.start();
      int i = this.zzDR.getCurrentPosition();
      long l = zzr.zzbG().currentTimeMillis();
      while ((zzfe()) && (this.zzDR.getCurrentPosition() == i) && (zzr.zzbG().currentTimeMillis() - l <= 250L)) {}
      this.zzDR.pause();
      zzfj();
    }
  }
  
  private void zzfc()
  {
    AudioManager localAudioManager = zzfk();
    if ((localAudioManager != null) && (!this.zzEa))
    {
      if (localAudioManager.requestAudioFocus(this, 3, 2) == 1) {
        zzfh();
      }
    }
    else {
      return;
    }
    zzin.zzaK("AdMediaPlayerView audio focus request failed");
  }
  
  private void zzfd()
  {
    zzin.v("AdMediaPlayerView abandon audio focus");
    AudioManager localAudioManager = zzfk();
    if ((localAudioManager != null) && (this.zzEa))
    {
      if (localAudioManager.abandonAudioFocus(this) == 1) {
        this.zzEa = false;
      }
    }
    else {
      return;
    }
    zzin.zzaK("AdMediaPlayerView abandon audio focus failed");
  }
  
  private boolean zzfe()
  {
    return (this.zzDR != null) && (this.zzDP != -1) && (this.zzDP != 0) && (this.zzDP != 1);
  }
  
  private void zzfh()
  {
    zzin.v("AdMediaPlayerView audio focus gained");
    this.zzEa = true;
    zzfj();
  }
  
  private void zzfi()
  {
    zzin.v("AdMediaPlayerView audio focus lost");
    this.zzEa = false;
    zzfj();
  }
  
  private void zzfj()
  {
    if ((!this.zzDZ) && (this.zzEa))
    {
      zzb(this.zzDY);
      return;
    }
    zzb(0.0F);
  }
  
  private AudioManager zzfk()
  {
    return (AudioManager)getContext().getSystemService("audio");
  }
  
  private void zzv(boolean paramBoolean)
  {
    zzin.v("AdMediaPlayerView release");
    if (this.zzDR != null)
    {
      this.zzDR.reset();
      this.zzDR.release();
      this.zzDR = null;
      zzw(0);
      if (paramBoolean)
      {
        this.zzDQ = 0;
        zzx(0);
      }
      zzfd();
    }
  }
  
  private void zzw(int paramInt)
  {
    if (paramInt == 3) {
      this.zzDO.zzfO();
    }
    for (;;)
    {
      this.zzDP = paramInt;
      return;
      if ((this.zzDP == 3) && (paramInt != 3)) {
        this.zzDO.zzfP();
      }
    }
  }
  
  private void zzx(int paramInt)
  {
    this.zzDQ = paramInt;
  }
  
  public int getCurrentPosition()
  {
    if (zzfe()) {
      return this.zzDR.getCurrentPosition();
    }
    return 0;
  }
  
  public int getDuration()
  {
    if (zzfe()) {
      return this.zzDR.getDuration();
    }
    return -1;
  }
  
  public int getVideoHeight()
  {
    if (this.zzDR != null) {
      return this.zzDR.getVideoHeight();
    }
    return 0;
  }
  
  public int getVideoWidth()
  {
    if (this.zzDR != null) {
      return this.zzDR.getVideoWidth();
    }
    return 0;
  }
  
  public void onAudioFocusChange(int paramInt)
  {
    if (paramInt > 0) {
      zzfh();
    }
    while (paramInt >= 0) {
      return;
    }
    zzfi();
  }
  
  public void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
  {
    this.zzDV = paramInt;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    zzin.v("AdMediaPlayerView completion");
    zzw(5);
    zzx(5);
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzfB();
        }
      }
    });
  }
  
  public boolean onError(final MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    paramMediaPlayer = (String)zzDN.get(Integer.valueOf(paramInt1));
    final String str = (String)zzDN.get(Integer.valueOf(paramInt2));
    zzin.zzaK("AdMediaPlayerView MediaPlayer error: " + paramMediaPlayer + ":" + str);
    zzw(-1);
    zzx(-1);
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzg(paramMediaPlayer, str);
        }
      }
    });
    return true;
  }
  
  public boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    paramMediaPlayer = (String)zzDN.get(Integer.valueOf(paramInt1));
    String str = (String)zzDN.get(Integer.valueOf(paramInt2));
    zzin.v("AdMediaPlayerView MediaPlayer info: " + paramMediaPlayer + ":" + str);
    return true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int k = getDefaultSize(this.zzDT, paramInt1);
    int m = getDefaultSize(this.zzDU, paramInt2);
    int j = m;
    int i = k;
    int n;
    if (this.zzDT > 0)
    {
      j = m;
      i = k;
      if (this.zzDU > 0)
      {
        m = View.MeasureSpec.getMode(paramInt1);
        paramInt1 = View.MeasureSpec.getSize(paramInt1);
        n = View.MeasureSpec.getMode(paramInt2);
        paramInt2 = View.MeasureSpec.getSize(paramInt2);
        if ((m != 1073741824) || (n != 1073741824)) {
          break label211;
        }
        if (this.zzDT * paramInt2 >= this.zzDU * paramInt1) {
          break label178;
        }
        i = this.zzDT * paramInt2 / this.zzDU;
        j = paramInt2;
      }
    }
    for (;;)
    {
      setMeasuredDimension(i, j);
      if (Build.VERSION.SDK_INT == 16)
      {
        if (((this.zzDW > 0) && (this.zzDW != i)) || ((this.zzDX > 0) && (this.zzDX != j))) {
          zzfb();
        }
        this.zzDW = i;
        this.zzDX = j;
      }
      return;
      label178:
      if (this.zzDT * paramInt2 > this.zzDU * paramInt1)
      {
        j = this.zzDU * paramInt1 / this.zzDT;
        i = paramInt1;
        continue;
        label211:
        if (m == 1073741824)
        {
          j = this.zzDU * paramInt1 / this.zzDT;
          if ((n == Integer.MIN_VALUE) && (j > paramInt2))
          {
            j = paramInt2;
            i = paramInt1;
          }
        }
        else
        {
          if (n == 1073741824)
          {
            k = this.zzDT * paramInt2 / this.zzDU;
            j = paramInt2;
            i = k;
            if (m != Integer.MIN_VALUE) {
              continue;
            }
            j = paramInt2;
            i = k;
            if (k <= paramInt1) {
              continue;
            }
            j = paramInt2;
            i = paramInt1;
            continue;
          }
          k = this.zzDT;
          i = this.zzDU;
          if ((n == Integer.MIN_VALUE) && (i > paramInt2)) {
            k = this.zzDT * paramInt2 / this.zzDU;
          }
          for (;;)
          {
            j = paramInt2;
            i = k;
            if (m != Integer.MIN_VALUE) {
              break;
            }
            j = paramInt2;
            i = k;
            if (k <= paramInt1) {
              break;
            }
            j = this.zzDU * paramInt1 / this.zzDT;
            i = paramInt1;
            break;
            paramInt2 = i;
          }
        }
        i = paramInt1;
      }
      else
      {
        j = paramInt2;
        i = paramInt1;
      }
    }
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    zzin.v("AdMediaPlayerView prepared");
    zzw(2);
    this.zzDO.zzfz();
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzfz();
        }
      }
    });
    this.zzDT = paramMediaPlayer.getVideoWidth();
    this.zzDU = paramMediaPlayer.getVideoHeight();
    if (this.zzEb != 0) {
      seekTo(this.zzEb);
    }
    zzfb();
    zzin.zzaJ("AdMediaPlayerView stream dimensions: " + this.zzDT + " x " + this.zzDU);
    if (this.zzDQ == 3) {
      play();
    }
    zzfc();
    zzfj();
  }
  
  public void onSurfaceTextureAvailable(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    zzin.v("AdMediaPlayerView surface created");
    zzfa();
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzfy();
        }
      }
    });
  }
  
  public boolean onSurfaceTextureDestroyed(SurfaceTexture paramSurfaceTexture)
  {
    zzin.v("AdMediaPlayerView surface destroyed");
    if ((this.zzDR != null) && (this.zzEb == 0)) {
      this.zzEb = this.zzDR.getCurrentPosition();
    }
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null)
        {
          zzc.zza(zzc.this).onPaused();
          zzc.zza(zzc.this).zzfC();
        }
      }
    });
    zzv(true);
    return true;
  }
  
  public void onSurfaceTextureSizeChanged(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    int j = 1;
    zzin.v("AdMediaPlayerView surface changed");
    int i;
    if (this.zzDQ == 3)
    {
      i = 1;
      if ((this.zzDT != paramInt1) || (this.zzDU != paramInt2)) {
        break label81;
      }
    }
    label81:
    for (paramInt1 = j;; paramInt1 = 0)
    {
      if ((this.zzDR != null) && (i != 0) && (paramInt1 != 0))
      {
        if (this.zzEb != 0) {
          seekTo(this.zzEb);
        }
        play();
      }
      return;
      i = 0;
      break;
    }
  }
  
  public void onSurfaceTextureUpdated(SurfaceTexture paramSurfaceTexture)
  {
    this.zzDO.zzb(this);
  }
  
  public void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    zzin.v("AdMediaPlayerView size changed: " + paramInt1 + " x " + paramInt2);
    this.zzDT = paramMediaPlayer.getVideoWidth();
    this.zzDU = paramMediaPlayer.getVideoHeight();
    if ((this.zzDT != 0) && (this.zzDU != 0)) {
      requestLayout();
    }
  }
  
  public void pause()
  {
    zzin.v("AdMediaPlayerView pause");
    if ((zzfe()) && (this.zzDR.isPlaying()))
    {
      this.zzDR.pause();
      zzw(4);
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          if (zzc.zza(zzc.this) != null) {
            zzc.zza(zzc.this).onPaused();
          }
        }
      });
    }
    zzx(4);
  }
  
  public void play()
  {
    zzin.v("AdMediaPlayerView play");
    if (zzfe())
    {
      this.zzDR.start();
      zzw(3);
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          if (zzc.zza(zzc.this) != null) {
            zzc.zza(zzc.this).zzfA();
          }
        }
      });
    }
    zzx(3);
  }
  
  public void seekTo(int paramInt)
  {
    zzin.v("AdMediaPlayerView seek " + paramInt);
    if (zzfe())
    {
      this.zzDR.seekTo(paramInt);
      this.zzEb = 0;
      return;
    }
    this.zzEb = paramInt;
  }
  
  public void setMimeType(String paramString) {}
  
  public void setVideoPath(String paramString)
  {
    setVideoURI(Uri.parse(paramString));
  }
  
  public void setVideoURI(Uri paramUri)
  {
    this.zzDS = paramUri;
    this.zzEb = 0;
    zzfa();
    requestLayout();
    invalidate();
  }
  
  public void stop()
  {
    zzin.v("AdMediaPlayerView stop");
    if (this.zzDR != null)
    {
      this.zzDR.stop();
      this.zzDR.release();
      this.zzDR = null;
      zzw(0);
      zzx(0);
      zzfd();
    }
    this.zzDO.onStop();
  }
  
  public String toString()
  {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
  }
  
  public void zza(float paramFloat)
  {
    this.zzDY = paramFloat;
    zzfj();
  }
  
  public void zza(zzh paramzzh)
  {
    this.zzEc = paramzzh;
  }
  
  public String zzeZ()
  {
    return "MediaPlayer";
  }
  
  public void zzff()
  {
    this.zzDZ = true;
    zzfj();
  }
  
  public void zzfg()
  {
    this.zzDZ = false;
    zzfj();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\overlay\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */