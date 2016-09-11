package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;

@zzhb
public class zzbc
{
  private final Object zzpV = new Object();
  private final int zzsK;
  private final int zzsL;
  private final int zzsM;
  private final zzbh zzsN;
  private ArrayList<String> zzsO = new ArrayList();
  private ArrayList<String> zzsP = new ArrayList();
  private int zzsQ = 0;
  private int zzsR = 0;
  private int zzsS = 0;
  private int zzsT;
  private String zzsU = "";
  private String zzsV = "";
  
  public zzbc(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.zzsK = paramInt1;
    this.zzsL = paramInt2;
    this.zzsM = paramInt3;
    this.zzsN = new zzbh(paramInt4);
  }
  
  private String zza(ArrayList<String> paramArrayList, int paramInt)
  {
    if (paramArrayList.isEmpty()) {
      paramArrayList = "";
    }
    Object localObject;
    do
    {
      return paramArrayList;
      localObject = new StringBuffer();
      paramArrayList = paramArrayList.iterator();
      do
      {
        if (!paramArrayList.hasNext()) {
          break;
        }
        ((StringBuffer)localObject).append((String)paramArrayList.next());
        ((StringBuffer)localObject).append(' ');
      } while (((StringBuffer)localObject).length() <= paramInt);
      ((StringBuffer)localObject).deleteCharAt(((StringBuffer)localObject).length() - 1);
      localObject = ((StringBuffer)localObject).toString();
      paramArrayList = (ArrayList<String>)localObject;
    } while (((String)localObject).length() < paramInt);
    return ((String)localObject).substring(0, paramInt);
  }
  
  private void zze(String paramString, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.length() < this.zzsM)) {
      return;
    }
    synchronized (this.zzpV)
    {
      this.zzsO.add(paramString);
      this.zzsQ += paramString.length();
      if (paramBoolean) {
        this.zzsP.add(paramString);
      }
      return;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbc)) {}
    do
    {
      return false;
      if (paramObject == this) {
        return true;
      }
      paramObject = (zzbc)paramObject;
    } while ((((zzbc)paramObject).zzcy() == null) || (!((zzbc)paramObject).zzcy().equals(zzcy())));
    return true;
  }
  
  public int getScore()
  {
    return this.zzsT;
  }
  
  public int hashCode()
  {
    return zzcy().hashCode();
  }
  
  public String toString()
  {
    return "ActivityContent fetchId: " + this.zzsR + " score:" + this.zzsT + " total_length:" + this.zzsQ + "\n text: " + zza(this.zzsO, 100) + "\n viewableText" + zza(this.zzsP, 100) + "\n signture: " + this.zzsU + "\n viewableSignture: " + this.zzsV;
  }
  
  int zzb(int paramInt1, int paramInt2)
  {
    return this.zzsK * paramInt1 + this.zzsL * paramInt2;
  }
  
  public void zzc(String arg1, boolean paramBoolean)
  {
    zze(???, paramBoolean);
    synchronized (this.zzpV)
    {
      if (this.zzsS < 0) {
        zzin.zzaI("ActivityContent: negative number of WebViews.");
      }
      zzcD();
      return;
    }
  }
  
  public void zzcA()
  {
    synchronized (this.zzpV)
    {
      this.zzsT -= 100;
      return;
    }
  }
  
  public void zzcB()
  {
    synchronized (this.zzpV)
    {
      this.zzsS -= 1;
      return;
    }
  }
  
  public void zzcC()
  {
    synchronized (this.zzpV)
    {
      this.zzsS += 1;
      return;
    }
  }
  
  public void zzcD()
  {
    synchronized (this.zzpV)
    {
      int i = zzb(this.zzsQ, this.zzsR);
      if (i > this.zzsT)
      {
        this.zzsT = i;
        this.zzsU = this.zzsN.zza(this.zzsO);
        this.zzsV = this.zzsN.zza(this.zzsP);
      }
      return;
    }
  }
  
  int zzcE()
  {
    return this.zzsQ;
  }
  
  public boolean zzcx()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzsS == 0)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public String zzcy()
  {
    return this.zzsU;
  }
  
  public String zzcz()
  {
    return this.zzsV;
  }
  
  public void zzd(String paramString, boolean paramBoolean)
  {
    zze(paramString, paramBoolean);
  }
  
  public void zzh(int paramInt)
  {
    this.zzsR = paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzbc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */