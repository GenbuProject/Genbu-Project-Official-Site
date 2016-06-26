package org.fmod;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;

public class MediaCodec
  implements InvocationHandler
{
  private int mChannelCount = 0;
  private long mCodecPtr = 0L;
  private int mCurrentOutputBufferIndex = -1;
  private Object mDataSourceProxy = null;
  private android.media.MediaCodec mDecoder = null;
  private MediaExtractor mExtractor = null;
  private ByteBuffer[] mInputBuffers = null;
  private boolean mInputFinished = false;
  private long mLength = 0L;
  private ByteBuffer[] mOutputBuffers = null;
  private boolean mOutputFinished = false;
  private int mSampleRate = 0;
  
  private static native long fmodGetSize(long paramLong);
  
  private static native int fmodReadAt(long paramLong1, long paramLong2, byte[] paramArrayOfByte, int paramInt);
  
  public void close()
  {
    if (this.mDecoder != null)
    {
      this.mDecoder.stop();
      this.mDecoder.release();
      this.mDecoder = null;
    }
    if (this.mExtractor != null)
    {
      this.mExtractor.release();
      this.mExtractor = null;
    }
  }
  
  public int getChannelCount()
  {
    return this.mChannelCount;
  }
  
  public long getLength()
  {
    return this.mLength;
  }
  
  public int getSampleRate()
  {
    return this.mSampleRate;
  }
  
  public boolean init(long paramLong)
  {
    int j = 0;
    if (Build.VERSION.SDK_INT < 17)
    {
      Log.w("fmod", "MediaCodec::init : MediaCodec unavailable, ensure device is running at least 4.2 (JellyBean).\n");
      return false;
    }
    this.mCodecPtr = paramLong;
    this.mExtractor = new MediaExtractor();
    for (;;)
    {
      int i;
      try
      {
        Object localObject1 = Class.forName("android.media.DataSource");
        Object localObject2 = Class.forName("android.media.MediaExtractor").getMethod("setDataSource", new Class[] { localObject1 });
        this.mDataSourceProxy = Proxy.newProxyInstance(((Class)localObject1).getClassLoader(), new Class[] { localObject1 }, this);
        ((Method)localObject2).invoke(this.mExtractor, new Object[] { this.mDataSourceProxy });
        int k = this.mExtractor.getTrackCount();
        i = 0;
        if (i >= k) {
          break;
        }
        localObject1 = this.mExtractor.getTrackFormat(i);
        localObject2 = ((MediaFormat)localObject1).getString("mime");
        Log.d("fmod", "MediaCodec::init : Format " + i + " / " + k + " -- " + localObject1);
        if (!((String)localObject2).equals("audio/mp4a-latm")) {
          break label524;
        }
        i = 0;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        try
        {
          this.mDecoder = android.media.MediaCodec.createDecoderByType((String)localObject2);
          this.mExtractor.selectTrack(i);
          this.mDecoder.configure((MediaFormat)localObject1, null, null, 0);
          this.mDecoder.start();
          this.mInputBuffers = this.mDecoder.getInputBuffers();
          this.mOutputBuffers = this.mDecoder.getOutputBuffers();
          if (!((MediaFormat)localObject1).containsKey("encoder-delay")) {
            break label519;
          }
          i = ((MediaFormat)localObject1).getInteger("encoder-delay");
          if (((MediaFormat)localObject1).containsKey("encoder-padding")) {
            j = ((MediaFormat)localObject1).getInteger("encoder-padding");
          }
          paramLong = ((MediaFormat)localObject1).getLong("durationUs");
          this.mChannelCount = ((MediaFormat)localObject1).getInteger("channel-count");
          this.mSampleRate = ((MediaFormat)localObject1).getInteger("sample-rate");
          this.mLength = ((int)((paramLong * this.mSampleRate + (1000000L - 1L)) / 1000000L) - i - j);
          return true;
        }
        catch (IOException localIOException)
        {
          Log.e("fmod", "MediaCodec::init : " + localIOException.toString());
          return false;
        }
        localClassNotFoundException = localClassNotFoundException;
        Log.w("fmod", "MediaCodec::init : " + localClassNotFoundException.toString());
        return false;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.w("fmod", "MediaCodec::init : " + localNoSuchMethodException.toString());
        return false;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.e("fmod", "MediaCodec::init : " + localIllegalAccessException.toString());
        return false;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Log.e("fmod", "MediaCodec::init : " + localInvocationTargetException.toString());
        return false;
      }
      label519:
      continue;
      label524:
      i += 1;
    }
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    paramObject = null;
    if (paramMethod.getName().equals("readAt")) {
      paramObject = Integer.valueOf(fmodReadAt(this.mCodecPtr, ((Long)paramArrayOfObject[0]).longValue(), (byte[])paramArrayOfObject[1], ((Integer)paramArrayOfObject[2]).intValue()));
    }
    do
    {
      return paramObject;
      if (paramMethod.getName().equals("getSize")) {
        return Long.valueOf(fmodGetSize(this.mCodecPtr));
      }
    } while (paramMethod.getName().equals("close"));
    Log.w("fmod", "MediaCodec::invoke : Unrecognised method found: " + paramMethod.getName());
    return null;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt)
  {
    int i;
    if ((this.mInputFinished) && (this.mOutputFinished) && (this.mCurrentOutputBufferIndex == -1)) {
      i = -1;
    }
    for (;;)
    {
      int j;
      if (!this.mInputFinished)
      {
        j = this.mDecoder.dequeueInputBuffer(0L);
        if (j >= 0)
        {
          int k = this.mExtractor.readSampleData(this.mInputBuffers[j], 0);
          if (k >= 0)
          {
            this.mDecoder.queueInputBuffer(j, 0, k, this.mExtractor.getSampleTime(), 0);
            this.mExtractor.advance();
            continue;
          }
          this.mDecoder.queueInputBuffer(j, 0, 0, 0L, 4);
          this.mInputFinished = true;
          continue;
        }
      }
      Object localObject;
      if ((!this.mOutputFinished) && (this.mCurrentOutputBufferIndex == -1))
      {
        localObject = new MediaCodec.BufferInfo();
        j = this.mDecoder.dequeueOutputBuffer((MediaCodec.BufferInfo)localObject, 10000L);
        if (j < 0) {
          break label291;
        }
        this.mCurrentOutputBufferIndex = j;
        this.mOutputBuffers[j].limit(((MediaCodec.BufferInfo)localObject).size);
        this.mOutputBuffers[j].position(((MediaCodec.BufferInfo)localObject).offset);
      }
      for (;;)
      {
        if ((((MediaCodec.BufferInfo)localObject).flags & 0x4) != 0) {
          this.mOutputFinished = true;
        }
        if (this.mCurrentOutputBufferIndex != -1)
        {
          localObject = this.mOutputBuffers[this.mCurrentOutputBufferIndex];
          paramInt = Math.min(((ByteBuffer)localObject).remaining(), paramInt);
          ((ByteBuffer)localObject).get(paramArrayOfByte, 0, paramInt);
          i = paramInt;
          if (!((ByteBuffer)localObject).hasRemaining())
          {
            ((ByteBuffer)localObject).clear();
            this.mDecoder.releaseOutputBuffer(this.mCurrentOutputBufferIndex, false);
            this.mCurrentOutputBufferIndex = -1;
            i = paramInt;
          }
        }
        return i;
        label291:
        if (j == -3) {
          this.mOutputBuffers = this.mDecoder.getOutputBuffers();
        } else if (j == -2) {
          Log.d("fmod", "MediaCodec::read : MediaCodec::dequeueOutputBuffer returned MediaCodec.INFO_OUTPUT_FORMAT_CHANGED " + this.mDecoder.getOutputFormat());
        } else if (j == -1) {
          Log.d("fmod", "MediaCodec::read : MediaCodec::dequeueOutputBuffer returned MediaCodec.INFO_TRY_AGAIN_LATER.");
        } else {
          Log.w("fmod", "MediaCodec::read : MediaCodec::dequeueOutputBuffer returned " + j);
        }
      }
      i = 0;
    }
  }
  
  public void seek(int paramInt)
  {
    if (this.mCurrentOutputBufferIndex != -1)
    {
      this.mOutputBuffers[this.mCurrentOutputBufferIndex].clear();
      this.mCurrentOutputBufferIndex = -1;
    }
    this.mInputFinished = false;
    this.mOutputFinished = false;
    this.mDecoder.flush();
    long l = paramInt * 1000000L / this.mSampleRate;
    this.mExtractor.seekTo(l, 0);
    l = (this.mExtractor.getSampleTime() * this.mSampleRate + (1000000L - 1L)) / 1000000L;
    int i = (int)((paramInt - l) * this.mChannelCount * 2L);
    if (i < 0) {
      Log.w("fmod", "MediaCodec::seek : Seek to " + paramInt + " resulted in position " + l);
    }
    for (;;)
    {
      return;
      byte[] arrayOfByte = new byte['?'];
      paramInt = i;
      while (paramInt > 0) {
        paramInt -= read(arrayOfByte, Math.min(arrayOfByte.length, paramInt));
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\org\fmod\MediaCodec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */