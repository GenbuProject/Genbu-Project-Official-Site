package com.google.android.gms.internal;

import java.io.IOException;

public final class zzsk
  extends zzso<zzsk>
{
  public String[] zzbtT;
  public int[] zzbtU;
  public byte[][] zzbtV;
  
  public zzsk()
  {
    zzIW();
  }
  
  public static zzsk zzB(byte[] paramArrayOfByte)
    throws zzst
  {
    return (zzsk)zzsu.mergeFrom(new zzsk(), paramArrayOfByte);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zzsk));
            paramObject = (zzsk)paramObject;
            bool1 = bool2;
          } while (!zzss.equals(this.zzbtT, ((zzsk)paramObject).zzbtT));
          bool1 = bool2;
        } while (!zzss.equals(this.zzbtU, ((zzsk)paramObject).zzbtU));
        bool1 = bool2;
      } while (!zzss.zza(this.zzbtV, ((zzsk)paramObject).zzbtV));
      if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
        break label111;
      }
      if (((zzsk)paramObject).zzbuj == null) {
        break;
      }
      bool1 = bool2;
    } while (!((zzsk)paramObject).zzbuj.isEmpty());
    return true;
    label111:
    return this.zzbuj.equals(((zzsk)paramObject).zzbuj);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = zzss.hashCode(this.zzbtT);
    int m = zzss.hashCode(this.zzbtU);
    int n = zzss.zza(this.zzbtV);
    if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
    for (int i = 0;; i = this.zzbuj.hashCode()) {
      return i + ((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31;
    }
  }
  
  public void writeTo(zzsn paramzzsn)
    throws IOException
  {
    int j = 0;
    int i;
    Object localObject;
    if ((this.zzbtT != null) && (this.zzbtT.length > 0))
    {
      i = 0;
      while (i < this.zzbtT.length)
      {
        localObject = this.zzbtT[i];
        if (localObject != null) {
          paramzzsn.zzn(1, (String)localObject);
        }
        i += 1;
      }
    }
    if ((this.zzbtU != null) && (this.zzbtU.length > 0))
    {
      i = 0;
      while (i < this.zzbtU.length)
      {
        paramzzsn.zzA(2, this.zzbtU[i]);
        i += 1;
      }
    }
    if ((this.zzbtV != null) && (this.zzbtV.length > 0))
    {
      i = j;
      while (i < this.zzbtV.length)
      {
        localObject = this.zzbtV[i];
        if (localObject != null) {
          paramzzsn.zza(3, (byte[])localObject);
        }
        i += 1;
      }
    }
    super.writeTo(paramzzsn);
  }
  
  public zzsk zzIW()
  {
    this.zzbtT = zzsx.zzbuB;
    this.zzbtU = zzsx.zzbuw;
    this.zzbtV = zzsx.zzbuC;
    this.zzbuj = null;
    this.zzbuu = -1;
    return this;
  }
  
  public zzsk zzO(zzsm paramzzsm)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzsm.zzIX();
      int j;
      Object localObject;
      switch (i)
      {
      default: 
        if (zza(paramzzsm, i)) {}
        break;
      case 0: 
        return this;
      case 10: 
        j = zzsx.zzc(paramzzsm, 10);
        if (this.zzbtT == null) {}
        for (i = 0;; i = this.zzbtT.length)
        {
          localObject = new String[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbtT, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzsm.readString();
            paramzzsm.zzIX();
            j += 1;
          }
        }
        localObject[j] = paramzzsm.readString();
        this.zzbtT = ((String[])localObject);
        break;
      case 16: 
        j = zzsx.zzc(paramzzsm, 16);
        if (this.zzbtU == null) {}
        for (i = 0;; i = this.zzbtU.length)
        {
          localObject = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbtU, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzsm.zzJb();
            paramzzsm.zzIX();
            j += 1;
          }
        }
        localObject[j] = paramzzsm.zzJb();
        this.zzbtU = ((int[])localObject);
        break;
      case 18: 
        int k = paramzzsm.zzmq(paramzzsm.zzJf());
        i = paramzzsm.getPosition();
        j = 0;
        while (paramzzsm.zzJk() > 0)
        {
          paramzzsm.zzJb();
          j += 1;
        }
        paramzzsm.zzms(i);
        if (this.zzbtU == null) {}
        for (i = 0;; i = this.zzbtU.length)
        {
          localObject = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbtU, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length)
          {
            localObject[j] = paramzzsm.zzJb();
            j += 1;
          }
        }
        this.zzbtU = ((int[])localObject);
        paramzzsm.zzmr(k);
        break;
      case 26: 
        j = zzsx.zzc(paramzzsm, 26);
        if (this.zzbtV == null) {}
        for (i = 0;; i = this.zzbtV.length)
        {
          localObject = new byte[j + i][];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbtV, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzsm.readBytes();
            paramzzsm.zzIX();
            j += 1;
          }
        }
        localObject[j] = paramzzsm.readBytes();
        this.zzbtV = ((byte[][])localObject);
      }
    }
  }
  
  protected int zzz()
  {
    int i1 = 0;
    int i2 = super.zzz();
    int i;
    int k;
    Object localObject;
    int n;
    int m;
    if ((this.zzbtT != null) && (this.zzbtT.length > 0))
    {
      i = 0;
      j = 0;
      for (k = 0; i < this.zzbtT.length; k = m)
      {
        localObject = this.zzbtT[i];
        n = j;
        m = k;
        if (localObject != null)
        {
          m = k + 1;
          n = j + zzsn.zzgO((String)localObject);
        }
        i += 1;
        j = n;
      }
    }
    for (int j = i2 + j + k * 1;; j = i2)
    {
      i = j;
      if (this.zzbtU != null)
      {
        i = j;
        if (this.zzbtU.length > 0)
        {
          i = 0;
          k = 0;
          while (i < this.zzbtU.length)
          {
            k += zzsn.zzmx(this.zzbtU[i]);
            i += 1;
          }
          i = j + k + this.zzbtU.length * 1;
        }
      }
      j = i;
      if (this.zzbtV != null)
      {
        j = i;
        if (this.zzbtV.length > 0)
        {
          k = 0;
          m = 0;
          j = i1;
          while (j < this.zzbtV.length)
          {
            localObject = this.zzbtV[j];
            i1 = k;
            n = m;
            if (localObject != null)
            {
              n = m + 1;
              i1 = k + zzsn.zzG((byte[])localObject);
            }
            j += 1;
            k = i1;
            m = n;
          }
          j = i + k + m * 1;
        }
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */