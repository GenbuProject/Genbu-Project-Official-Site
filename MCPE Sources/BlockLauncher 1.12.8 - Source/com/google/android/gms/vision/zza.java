package com.google.android.gms.vision;

import android.util.SparseArray;

public class zza
{
  private static int zzbnl = 0;
  private static final Object zzqy = new Object();
  private SparseArray<Integer> zzbnm = new SparseArray();
  private SparseArray<Integer> zzbnn = new SparseArray();
  
  public int zzkr(int paramInt)
  {
    synchronized (zzqy)
    {
      Integer localInteger = (Integer)this.zzbnm.get(paramInt);
      if (localInteger != null)
      {
        paramInt = localInteger.intValue();
        return paramInt;
      }
      int i = zzbnl;
      zzbnl += 1;
      this.zzbnm.append(paramInt, Integer.valueOf(i));
      this.zzbnn.append(i, Integer.valueOf(paramInt));
      return i;
    }
  }
  
  public int zzks(int paramInt)
  {
    synchronized (zzqy)
    {
      paramInt = ((Integer)this.zzbnn.get(paramInt)).intValue();
      return paramInt;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\vision\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */