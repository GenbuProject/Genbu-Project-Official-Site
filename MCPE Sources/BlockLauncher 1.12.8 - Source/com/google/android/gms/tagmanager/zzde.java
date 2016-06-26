package com.google.android.gms.tagmanager;

class zzde
  extends Number
  implements Comparable<zzde>
{
  private double zzblB;
  private long zzblC;
  private boolean zzblD;
  
  private zzde(double paramDouble)
  {
    this.zzblB = paramDouble;
    this.zzblD = false;
  }
  
  private zzde(long paramLong)
  {
    this.zzblC = paramLong;
    this.zzblD = true;
  }
  
  public static zzde zza(Double paramDouble)
  {
    return new zzde(paramDouble.doubleValue());
  }
  
  public static zzde zzam(long paramLong)
  {
    return new zzde(paramLong);
  }
  
  public static zzde zzgs(String paramString)
    throws NumberFormatException
  {
    try
    {
      zzde localzzde1 = new zzde(Long.parseLong(paramString));
      return localzzde1;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        zzde localzzde2 = new zzde(Double.parseDouble(paramString));
        return localzzde2;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        throw new NumberFormatException(paramString + " is not a valid TypedNumber");
      }
    }
  }
  
  public byte byteValue()
  {
    return (byte)(int)longValue();
  }
  
  public double doubleValue()
  {
    if (zzHv()) {
      return this.zzblC;
    }
    return this.zzblB;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof zzde)) && (zza((zzde)paramObject) == 0);
  }
  
  public float floatValue()
  {
    return (float)doubleValue();
  }
  
  public int hashCode()
  {
    return new Long(longValue()).hashCode();
  }
  
  public int intValue()
  {
    return zzHx();
  }
  
  public long longValue()
  {
    return zzHw();
  }
  
  public short shortValue()
  {
    return zzHy();
  }
  
  public String toString()
  {
    if (zzHv()) {
      return Long.toString(this.zzblC);
    }
    return Double.toString(this.zzblB);
  }
  
  public boolean zzHu()
  {
    return !zzHv();
  }
  
  public boolean zzHv()
  {
    return this.zzblD;
  }
  
  public long zzHw()
  {
    if (zzHv()) {
      return this.zzblC;
    }
    return this.zzblB;
  }
  
  public int zzHx()
  {
    return (int)longValue();
  }
  
  public short zzHy()
  {
    return (short)(int)longValue();
  }
  
  public int zza(zzde paramzzde)
  {
    if ((zzHv()) && (paramzzde.zzHv())) {
      return new Long(this.zzblC).compareTo(Long.valueOf(paramzzde.zzblC));
    }
    return Double.compare(doubleValue(), paramzzde.doubleValue());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzde.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */