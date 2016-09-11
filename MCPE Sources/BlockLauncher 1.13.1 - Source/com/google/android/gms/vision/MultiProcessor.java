package com.google.android.gms.vision;

import android.util.SparseArray;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MultiProcessor<T>
  implements Detector.Processor<T>
{
  private int zzbne = 3;
  private Factory<T> zzbnq;
  private SparseArray<MultiProcessor<T>.zza> zzbnr = new SparseArray();
  
  private void zza(Detector.Detections<T> paramDetections)
  {
    paramDetections = paramDetections.getDetectedItems();
    int i = 0;
    while (i < paramDetections.size())
    {
      int j = paramDetections.keyAt(i);
      Object localObject = paramDetections.valueAt(i);
      if (this.zzbnr.get(j) == null)
      {
        zza localzza = new zza(null);
        zza.zza(localzza, this.zzbnq.create(localObject));
        zza.zza(localzza).onNewItem(j, localObject);
        this.zzbnr.append(j, localzza);
      }
      i += 1;
    }
  }
  
  private void zzb(Detector.Detections<T> paramDetections)
  {
    Object localObject = paramDetections.getDetectedItems();
    HashSet localHashSet = new HashSet();
    int i = 0;
    if (i < this.zzbnr.size())
    {
      int j = this.zzbnr.keyAt(i);
      zza localzza;
      if (((SparseArray)localObject).get(j) == null)
      {
        localzza = (zza)this.zzbnr.valueAt(i);
        zza.zzb(localzza);
        if (zza.zzc(localzza) < this.zzbne) {
          break label104;
        }
        zza.zza(localzza).onDone();
        localHashSet.add(Integer.valueOf(j));
      }
      for (;;)
      {
        i += 1;
        break;
        label104:
        zza.zza(localzza).onMissing(paramDetections);
      }
    }
    paramDetections = localHashSet.iterator();
    while (paramDetections.hasNext())
    {
      localObject = (Integer)paramDetections.next();
      this.zzbnr.delete(((Integer)localObject).intValue());
    }
  }
  
  private void zzc(Detector.Detections<T> paramDetections)
  {
    SparseArray localSparseArray = paramDetections.getDetectedItems();
    int i = 0;
    while (i < localSparseArray.size())
    {
      int j = localSparseArray.keyAt(i);
      Object localObject = localSparseArray.valueAt(i);
      zza localzza = (zza)this.zzbnr.get(j);
      zza.zza(localzza, 0);
      zza.zza(localzza).onUpdate(paramDetections, localObject);
      i += 1;
    }
  }
  
  public void receiveDetections(Detector.Detections<T> paramDetections)
  {
    zza(paramDetections);
    zzb(paramDetections);
    zzc(paramDetections);
  }
  
  public void release()
  {
    int i = 0;
    while (i < this.zzbnr.size())
    {
      zza.zza((zza)this.zzbnr.valueAt(i)).onDone();
      i += 1;
    }
    this.zzbnr.clear();
  }
  
  public static class Builder<T>
  {
    private MultiProcessor<T> zzbns = new MultiProcessor(null);
    
    public Builder(MultiProcessor.Factory<T> paramFactory)
    {
      if (paramFactory == null) {
        throw new IllegalArgumentException("No factory supplied.");
      }
      MultiProcessor.zza(this.zzbns, paramFactory);
    }
    
    public MultiProcessor<T> build()
    {
      return this.zzbns;
    }
    
    public Builder<T> setMaxGapFrames(int paramInt)
    {
      if (paramInt < 0) {
        throw new IllegalArgumentException("Invalid max gap: " + paramInt);
      }
      MultiProcessor.zza(this.zzbns, paramInt);
      return this;
    }
  }
  
  public static abstract interface Factory<T>
  {
    public abstract Tracker<T> create(T paramT);
  }
  
  private class zza
  {
    private Tracker<T> zzbnd;
    private int zzbnh = 0;
    
    private zza() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\vision\MultiProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */