package org.fmod;

import android.media.AudioTrack;
import android.util.Log;

public class AudioDevice
{
  private AudioTrack mTrack = null;
  
  private int fetchChannelConfigFromCount(int paramInt)
  {
    if (paramInt == 1) {
      return 2;
    }
    if (paramInt == 2) {
      return 3;
    }
    if (paramInt == 6) {
      return 252;
    }
    return 0;
  }
  
  public void close()
  {
    try
    {
      this.mTrack.stop();
      this.mTrack.release();
      this.mTrack = null;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        Log.e("fmod", "AudioDevice::init : AudioTrack stop caused IllegalStateException");
      }
    }
  }
  
  public boolean init(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = fetchChannelConfigFromCount(paramInt1);
    int i = AudioTrack.getMinBufferSize(paramInt2, j, 2);
    if (i < 0)
    {
      Log.w("fmod", "AudioDevice::init : Couldn't query minimum buffer size, possibly unsupported sample rate or channel count");
      paramInt1 = paramInt3 * paramInt4 * paramInt1 * 2;
      if (paramInt1 <= i) {
        break label171;
      }
    }
    for (;;)
    {
      Log.i("fmod", "AudioDevice::init : Actual buffer size: " + paramInt1 + " bytes");
      try
      {
        this.mTrack = new AudioTrack(3, paramInt2, j, 2, paramInt1, 1);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Log.e("fmod", "AudioDevice::init : AudioTrack creation caused IllegalArgumentException");
        return false;
      }
      try
      {
        this.mTrack.play();
        return true;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        Log.e("fmod", "AudioDevice::init : AudioTrack play caused IllegalStateException");
        this.mTrack.release();
        this.mTrack = null;
        return false;
      }
      Log.i("fmod", "AudioDevice::init : Min buffer size: " + i + " bytes");
      break;
      label171:
      paramInt1 = i;
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt)
  {
    this.mTrack.write(paramArrayOfByte, 0, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\fmod\AudioDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */