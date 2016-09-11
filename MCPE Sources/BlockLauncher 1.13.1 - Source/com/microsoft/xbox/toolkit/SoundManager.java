package com.microsoft.xbox.toolkit;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;
import java.util.HashMap;

public class SoundManager
{
  private static final int MAX_STREAM_SIZE = 14;
  private static final int NO_LOOP = 0;
  private AudioManager audioManager;
  private Context context;
  private boolean isEnabled = false;
  private ArrayList<Integer> recentlyPlayedResourceIds = new ArrayList();
  private HashMap<Integer, Integer> resourceSoundIdMap = new HashMap();
  private SoundPool soundPool;
  
  private SoundManager()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue("You must access sound manager on UI thread.", bool);
      this.context = XboxTcuiSdk.getApplicationContext();
      this.soundPool = new SoundPool(14, 3, 0);
      this.audioManager = ((AudioManager)this.context.getSystemService("audio"));
      return;
    }
  }
  
  public static SoundManager getInstance()
  {
    return SoundManagerHolder.instance;
  }
  
  public void clearMostRecentlyPlayedResourceIds() {}
  
  public Integer[] getMostRecentlyPlayedResourceIds()
  {
    return new Integer[0];
  }
  
  public void loadSound(int paramInt)
  {
    if (!this.resourceSoundIdMap.containsKey(Integer.valueOf(paramInt)))
    {
      int i = this.soundPool.load(this.context, paramInt, 1);
      this.resourceSoundIdMap.put(Integer.valueOf(paramInt), Integer.valueOf(i));
    }
  }
  
  public void playSound(int paramInt)
  {
    if (!this.isEnabled) {
      return;
    }
    int i;
    if (!this.resourceSoundIdMap.containsKey(Integer.valueOf(paramInt)))
    {
      i = this.soundPool.load(this.context, paramInt, 1);
      this.resourceSoundIdMap.put(Integer.valueOf(paramInt), Integer.valueOf(i));
    }
    for (paramInt = i;; paramInt = ((Integer)this.resourceSoundIdMap.get(Integer.valueOf(paramInt))).intValue())
    {
      float f = this.audioManager.getStreamMaxVolume(3);
      f = this.audioManager.getStreamVolume(3) / f;
      this.soundPool.play(paramInt, f, f, 1, 0, 1.0F);
      return;
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (this.isEnabled != paramBoolean) {
      this.isEnabled = paramBoolean;
    }
  }
  
  private static class SoundManagerHolder
  {
    public static final SoundManager instance = new SoundManager(null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\SoundManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */