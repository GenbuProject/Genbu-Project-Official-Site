package com.google.android.gms.vision;

import android.util.SparseArray;

public abstract class Detector<T>
{
  private Object zzbmY = new Object();
  private Processor<T> zzbmZ;
  
  public abstract SparseArray<T> detect(Frame paramFrame);
  
  public boolean isOperational()
  {
    return true;
  }
  
  public void receiveFrame(Frame paramFrame)
  {
    synchronized (this.zzbmY)
    {
      if (this.zzbmZ == null) {
        throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
      }
    }
    Frame.Metadata localMetadata = new Frame.Metadata(paramFrame.getMetadata());
    localMetadata.zzIf();
    paramFrame = new Detections(detect(paramFrame), localMetadata, isOperational());
    this.zzbmZ.receiveDetections(paramFrame);
  }
  
  public void release()
  {
    synchronized (this.zzbmY)
    {
      if (this.zzbmZ != null)
      {
        this.zzbmZ.release();
        this.zzbmZ = null;
      }
      return;
    }
  }
  
  public boolean setFocus(int paramInt)
  {
    return true;
  }
  
  public void setProcessor(Processor<T> paramProcessor)
  {
    this.zzbmZ = paramProcessor;
  }
  
  public static class Detections<T>
  {
    private SparseArray<T> zzbna;
    private Frame.Metadata zzbnb;
    private boolean zzbnc;
    
    public Detections(SparseArray<T> paramSparseArray, Frame.Metadata paramMetadata, boolean paramBoolean)
    {
      this.zzbna = paramSparseArray;
      this.zzbnb = paramMetadata;
      this.zzbnc = paramBoolean;
    }
    
    public boolean detectorIsOperational()
    {
      return this.zzbnc;
    }
    
    public SparseArray<T> getDetectedItems()
    {
      return this.zzbna;
    }
    
    public Frame.Metadata getFrameMetadata()
    {
      return this.zzbnb;
    }
  }
  
  public static abstract interface Processor<T>
  {
    public abstract void receiveDetections(Detector.Detections<T> paramDetections);
    
    public abstract void release();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\vision\Detector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */