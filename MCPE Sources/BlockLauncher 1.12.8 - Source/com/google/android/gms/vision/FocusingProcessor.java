package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;

public abstract class FocusingProcessor<T>
  implements Detector.Processor<T>
{
  private Detector<T> zzbmN;
  private Tracker<T> zzbnd;
  private int zzbne = 3;
  private boolean zzbnf = false;
  private int zzbng;
  private int zzbnh = 0;
  
  public FocusingProcessor(Detector<T> paramDetector, Tracker<T> paramTracker)
  {
    this.zzbmN = paramDetector;
    this.zzbnd = paramTracker;
  }
  
  public void receiveDetections(Detector.Detections<T> paramDetections)
  {
    Object localObject1 = paramDetections.getDetectedItems();
    if (((SparseArray)localObject1).size() == 0)
    {
      if (this.zzbnh == this.zzbne)
      {
        this.zzbnd.onDone();
        this.zzbnf = false;
      }
      for (;;)
      {
        this.zzbnh += 1;
        return;
        this.zzbnd.onMissing(paramDetections);
      }
    }
    this.zzbnh = 0;
    if (this.zzbnf)
    {
      Object localObject2 = ((SparseArray)localObject1).get(this.zzbng);
      if (localObject2 != null)
      {
        this.zzbnd.onUpdate(paramDetections, localObject2);
        return;
      }
      this.zzbnd.onDone();
      this.zzbnf = false;
    }
    int i = selectFocus(paramDetections);
    localObject1 = ((SparseArray)localObject1).get(i);
    if (localObject1 == null)
    {
      Log.w("FocusingProcessor", "Invalid focus selected: " + i);
      return;
    }
    this.zzbnf = true;
    this.zzbng = i;
    this.zzbmN.setFocus(this.zzbng);
    this.zzbnd.onNewItem(this.zzbng, localObject1);
    this.zzbnd.onUpdate(paramDetections, localObject1);
  }
  
  public void release()
  {
    this.zzbnd.onDone();
  }
  
  public abstract int selectFocus(Detector.Detections<T> paramDetections);
  
  protected void zzkq(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Invalid max gap: " + paramInt);
    }
    this.zzbne = paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\vision\FocusingProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */