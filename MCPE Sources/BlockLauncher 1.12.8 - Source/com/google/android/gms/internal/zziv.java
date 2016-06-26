package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.List;

@zzhb
public class zziv
{
  private final String[] zzMn;
  private final double[] zzMo;
  private final double[] zzMp;
  private final int[] zzMq;
  private int zzMr;
  
  private zziv(zzb paramzzb)
  {
    int i = zzb.zza(paramzzb).size();
    this.zzMn = ((String[])zzb.zzb(paramzzb).toArray(new String[i]));
    this.zzMo = zzk(zzb.zza(paramzzb));
    this.zzMp = zzk(zzb.zzc(paramzzb));
    this.zzMq = new int[i];
    this.zzMr = 0;
  }
  
  private double[] zzk(List<Double> paramList)
  {
    double[] arrayOfDouble = new double[paramList.size()];
    int i = 0;
    while (i < arrayOfDouble.length)
    {
      arrayOfDouble[i] = ((Double)paramList.get(i)).doubleValue();
      i += 1;
    }
    return arrayOfDouble;
  }
  
  public List<zza> getBuckets()
  {
    ArrayList localArrayList = new ArrayList(this.zzMn.length);
    int i = 0;
    while (i < this.zzMn.length)
    {
      localArrayList.add(new zza(this.zzMn[i], this.zzMp[i], this.zzMo[i], this.zzMq[i] / this.zzMr, this.zzMq[i]));
      i += 1;
    }
    return localArrayList;
  }
  
  public void zza(double paramDouble)
  {
    this.zzMr += 1;
    int i = 0;
    for (;;)
    {
      if (i < this.zzMp.length)
      {
        if ((this.zzMp[i] <= paramDouble) && (paramDouble < this.zzMo[i]))
        {
          int[] arrayOfInt = this.zzMq;
          arrayOfInt[i] += 1;
        }
        if (paramDouble >= this.zzMp[i]) {}
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  public static class zza
  {
    public final int count;
    public final String name;
    public final double zzMs;
    public final double zzMt;
    public final double zzMu;
    
    public zza(String paramString, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt)
    {
      this.name = paramString;
      this.zzMt = paramDouble1;
      this.zzMs = paramDouble2;
      this.zzMu = paramDouble3;
      this.count = paramInt;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zza)) {}
      do
      {
        return false;
        paramObject = (zza)paramObject;
      } while ((!zzw.equal(this.name, ((zza)paramObject).name)) || (this.zzMs != ((zza)paramObject).zzMs) || (this.zzMt != ((zza)paramObject).zzMt) || (this.count != ((zza)paramObject).count) || (Double.compare(this.zzMu, ((zza)paramObject).zzMu) != 0));
      return true;
    }
    
    public int hashCode()
    {
      return zzw.hashCode(new Object[] { this.name, Double.valueOf(this.zzMs), Double.valueOf(this.zzMt), Double.valueOf(this.zzMu), Integer.valueOf(this.count) });
    }
    
    public String toString()
    {
      return zzw.zzy(this).zzg("name", this.name).zzg("minBound", Double.valueOf(this.zzMt)).zzg("maxBound", Double.valueOf(this.zzMs)).zzg("percent", Double.valueOf(this.zzMu)).zzg("count", Integer.valueOf(this.count)).toString();
    }
  }
  
  public static class zzb
  {
    private final List<String> zzMv = new ArrayList();
    private final List<Double> zzMw = new ArrayList();
    private final List<Double> zzMx = new ArrayList();
    
    public zzb zza(String paramString, double paramDouble1, double paramDouble2)
    {
      int i = 0;
      for (;;)
      {
        double d1;
        double d2;
        if (i < this.zzMv.size())
        {
          d1 = ((Double)this.zzMx.get(i)).doubleValue();
          d2 = ((Double)this.zzMw.get(i)).doubleValue();
          if (paramDouble1 >= d1) {
            break label107;
          }
        }
        label107:
        while ((d1 == paramDouble1) && (paramDouble2 < d2))
        {
          this.zzMv.add(i, paramString);
          this.zzMx.add(i, Double.valueOf(paramDouble1));
          this.zzMw.add(i, Double.valueOf(paramDouble2));
          return this;
        }
        i += 1;
      }
    }
    
    public zziv zzhA()
    {
      return new zziv(this, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zziv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */