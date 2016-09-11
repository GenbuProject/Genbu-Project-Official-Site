package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzsy
{
  public static final class zza
    extends zzsu
  {
    public String name;
    public zzsy.zzb[] zzbuE;
    
    public zza()
    {
      zzJz();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zza)) {
          return false;
        }
        paramObject = (zza)paramObject;
        if (this.name == null)
        {
          if (((zza)paramObject).name != null) {
            return false;
          }
        }
        else if (!this.name.equals(((zza)paramObject).name)) {
          return false;
        }
      } while (zzss.equals(this.zzbuE, ((zza)paramObject).zzbuE));
      return false;
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      if (this.name == null) {}
      for (int i = 0;; i = this.name.hashCode()) {
        return (i + (j + 527) * 31) * 31 + zzss.hashCode(this.zzbuE);
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.name != null) {
        paramzzsn.zzn(1, this.name);
      }
      if ((this.zzbuE != null) && (this.zzbuE.length > 0))
      {
        int i = 0;
        while (i < this.zzbuE.length)
        {
          zzsy.zzb localzzb = this.zzbuE[i];
          if (localzzb != null) {
            paramzzsn.zza(2, localzzb);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzJz()
    {
      this.name = null;
      this.zzbuE = zzsy.zzb.zzJA();
      this.zzbuu = -1;
      return this;
    }
    
    public zza zzQ(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.name = paramzzsm.readString();
          break;
        case 18: 
          int j = zzsx.zzc(paramzzsm, 18);
          if (this.zzbuE == null) {}
          zzsy.zzb[] arrayOfzzb;
          for (i = 0;; i = this.zzbuE.length)
          {
            arrayOfzzb = new zzsy.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuE, 0, arrayOfzzb, 0, i);
              j = i;
            }
            while (j < arrayOfzzb.length - 1)
            {
              arrayOfzzb[j] = new zzsy.zzb();
              paramzzsm.zza(arrayOfzzb[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfzzb[j] = new zzsy.zzb();
          paramzzsm.zza(arrayOfzzb[j]);
          this.zzbuE = arrayOfzzb;
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.name != null) {
        i = j + zzsn.zzo(1, this.name);
      }
      j = i;
      if (this.zzbuE != null)
      {
        j = i;
        if (this.zzbuE.length > 0)
        {
          j = 0;
          while (j < this.zzbuE.length)
          {
            zzsy.zzb localzzb = this.zzbuE[j];
            int k = i;
            if (localzzb != null) {
              k = i + zzsn.zzc(2, localzzb);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      return j;
    }
  }
  
  public static final class zzb
    extends zzsu
  {
    private static volatile zzb[] zzbuF;
    public String name;
    public Integer zzbuG;
    public Boolean zzbuH;
    
    public zzb()
    {
      zzJB();
    }
    
    public static zzb[] zzJA()
    {
      if (zzbuF == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzbuF == null) {
          zzbuF = new zzb[0];
        }
        return zzbuF;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zzb)) {
            return false;
          }
          paramObject = (zzb)paramObject;
          if (this.name == null)
          {
            if (((zzb)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zzb)paramObject).name)) {
            return false;
          }
          if (this.zzbuG == null)
          {
            if (((zzb)paramObject).zzbuG != null) {
              return false;
            }
          }
          else if (!this.zzbuG.equals(((zzb)paramObject).zzbuG)) {
            return false;
          }
          if (this.zzbuH != null) {
            break;
          }
        } while (((zzb)paramObject).zzbuH == null);
        return false;
      } while (this.zzbuH.equals(((zzb)paramObject).zzbuH));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.name == null)
      {
        i = 0;
        if (this.zzbuG != null) {
          break label72;
        }
        j = 0;
        label32:
        if (this.zzbuH != null) {
          break label83;
        }
      }
      for (;;)
      {
        return (j + (i + (m + 527) * 31) * 31) * 31 + k;
        i = this.name.hashCode();
        break;
        label72:
        j = this.zzbuG.intValue();
        break label32;
        label83:
        k = this.zzbuH.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.name != null) {
        paramzzsn.zzn(1, this.name);
      }
      if (this.zzbuG != null) {
        paramzzsn.zzA(3, this.zzbuG.intValue());
      }
      if (this.zzbuH != null) {
        paramzzsn.zze(4, this.zzbuH.booleanValue());
      }
      super.writeTo(paramzzsn);
    }
    
    public zzb zzJB()
    {
      this.name = null;
      this.zzbuG = null;
      this.zzbuH = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzb zzR(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.name = paramzzsm.readString();
          break;
        case 24: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
          case 7: 
            this.zzbuG = Integer.valueOf(i);
          }
          break;
        case 32: 
          this.zzbuH = Boolean.valueOf(paramzzsm.zzJc());
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.name != null) {
        i = j + zzsn.zzo(1, this.name);
      }
      j = i;
      if (this.zzbuG != null) {
        j = i + zzsn.zzC(3, this.zzbuG.intValue());
      }
      i = j;
      if (this.zzbuH != null) {
        i = j + zzsn.zzf(4, this.zzbuH.booleanValue());
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzsy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */