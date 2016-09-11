package com.google.android.gms.vision;

public class Tracker<T>
{
  public void onDone() {}
  
  public void onMissing(Detector.Detections<T> paramDetections) {}
  
  public void onNewItem(int paramInt, T paramT) {}
  
  public void onUpdate(Detector.Detections<T> paramDetections, T paramT) {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\vision\Tracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */