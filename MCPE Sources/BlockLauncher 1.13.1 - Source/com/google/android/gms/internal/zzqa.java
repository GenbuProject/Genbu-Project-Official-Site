package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzqa
{
  public static final class zza
    extends zzsu
  {
    private static volatile zza[] zzaZR;
    public String name;
    public Boolean zzaZS;
    
    public zza()
    {
      zzDL();
    }
    
    public static zza[] zzDK()
    {
      if (zzaZR == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaZR == null) {
          zzaZR = new zza[0];
        }
        return zzaZR;
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
          if (this.zzaZS != null) {
            break;
          }
        } while (((zza)paramObject).zzaZS == null);
        return false;
      } while (this.zzaZS.equals(((zza)paramObject).zzaZS));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int k = getClass().getName().hashCode();
      int i;
      if (this.name == null)
      {
        i = 0;
        if (this.zzaZS != null) {
          break label56;
        }
      }
      for (;;)
      {
        return (i + (k + 527) * 31) * 31 + j;
        i = this.name.hashCode();
        break;
        label56:
        j = this.zzaZS.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.name != null) {
        paramzzsn.zzn(1, this.name);
      }
      if (this.zzaZS != null) {
        paramzzsn.zze(2, this.zzaZS.booleanValue());
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzDL()
    {
      this.name = null;
      this.zzaZS = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.name != null) {
        i = j + zzsn.zzo(1, this.name);
      }
      j = i;
      if (this.zzaZS != null) {
        j = i + zzsn.zzf(2, this.zzaZS.booleanValue());
      }
      return j;
    }
    
    public zza zzz(zzsm paramzzsm)
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
        case 16: 
          this.zzaZS = Boolean.valueOf(paramzzsm.zzJc());
        }
      }
    }
  }
  
  public static final class zzb
    extends zzsu
  {
    public String zzaVt;
    public Long zzaZT;
    public Integer zzaZU;
    public zzqa.zzc[] zzaZV;
    public zzqa.zza[] zzaZW;
    public zzpz.zza[] zzaZX;
    
    public zzb()
    {
      zzDM();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzb)) {
          return false;
        }
        paramObject = (zzb)paramObject;
        if (this.zzaZT == null)
        {
          if (((zzb)paramObject).zzaZT != null) {
            return false;
          }
        }
        else if (!this.zzaZT.equals(((zzb)paramObject).zzaZT)) {
          return false;
        }
        if (this.zzaVt == null)
        {
          if (((zzb)paramObject).zzaVt != null) {
            return false;
          }
        }
        else if (!this.zzaVt.equals(((zzb)paramObject).zzaVt)) {
          return false;
        }
        if (this.zzaZU == null)
        {
          if (((zzb)paramObject).zzaZU != null) {
            return false;
          }
        }
        else if (!this.zzaZU.equals(((zzb)paramObject).zzaZU)) {
          return false;
        }
        if (!zzss.equals(this.zzaZV, ((zzb)paramObject).zzaZV)) {
          return false;
        }
        if (!zzss.equals(this.zzaZW, ((zzb)paramObject).zzaZW)) {
          return false;
        }
      } while (zzss.equals(this.zzaZX, ((zzb)paramObject).zzaZX));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.zzaZT == null)
      {
        i = 0;
        if (this.zzaVt != null) {
          break label105;
        }
        j = 0;
        label32:
        if (this.zzaZU != null) {
          break label116;
        }
      }
      for (;;)
      {
        return ((((j + (i + (m + 527) * 31) * 31) * 31 + k) * 31 + zzss.hashCode(this.zzaZV)) * 31 + zzss.hashCode(this.zzaZW)) * 31 + zzss.hashCode(this.zzaZX);
        i = this.zzaZT.hashCode();
        break;
        label105:
        j = this.zzaVt.hashCode();
        break label32;
        label116:
        k = this.zzaZU.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      if (this.zzaZT != null) {
        paramzzsn.zzb(1, this.zzaZT.longValue());
      }
      if (this.zzaVt != null) {
        paramzzsn.zzn(2, this.zzaVt);
      }
      if (this.zzaZU != null) {
        paramzzsn.zzA(3, this.zzaZU.intValue());
      }
      int i;
      Object localObject;
      if ((this.zzaZV != null) && (this.zzaZV.length > 0))
      {
        i = 0;
        while (i < this.zzaZV.length)
        {
          localObject = this.zzaZV[i];
          if (localObject != null) {
            paramzzsn.zza(4, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzaZW != null) && (this.zzaZW.length > 0))
      {
        i = 0;
        while (i < this.zzaZW.length)
        {
          localObject = this.zzaZW[i];
          if (localObject != null) {
            paramzzsn.zza(5, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzaZX != null) && (this.zzaZX.length > 0))
      {
        i = j;
        while (i < this.zzaZX.length)
        {
          localObject = this.zzaZX[i];
          if (localObject != null) {
            paramzzsn.zza(6, (zzsu)localObject);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzb zzA(zzsm paramzzsm)
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
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzaZT = Long.valueOf(paramzzsm.zzJa());
          break;
        case 18: 
          this.zzaVt = paramzzsm.readString();
          break;
        case 24: 
          this.zzaZU = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 34: 
          j = zzsx.zzc(paramzzsm, 34);
          if (this.zzaZV == null) {}
          for (i = 0;; i = this.zzaZV.length)
          {
            localObject = new zzqa.zzc[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaZV, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzqa.zzc();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzqa.zzc();
          paramzzsm.zza(localObject[j]);
          this.zzaZV = ((zzqa.zzc[])localObject);
          break;
        case 42: 
          j = zzsx.zzc(paramzzsm, 42);
          if (this.zzaZW == null) {}
          for (i = 0;; i = this.zzaZW.length)
          {
            localObject = new zzqa.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaZW, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzqa.zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzqa.zza();
          paramzzsm.zza(localObject[j]);
          this.zzaZW = ((zzqa.zza[])localObject);
          break;
        case 50: 
          j = zzsx.zzc(paramzzsm, 50);
          if (this.zzaZX == null) {}
          for (i = 0;; i = this.zzaZX.length)
          {
            localObject = new zzpz.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaZX, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzpz.zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzpz.zza();
          paramzzsm.zza(localObject[j]);
          this.zzaZX = ((zzpz.zza[])localObject);
        }
      }
    }
    
    public zzb zzDM()
    {
      this.zzaZT = null;
      this.zzaVt = null;
      this.zzaZU = null;
      this.zzaZV = zzqa.zzc.zzDN();
      this.zzaZW = zzqa.zza.zzDK();
      this.zzaZX = zzpz.zza.zzDA();
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int m = 0;
      int j = super.zzz();
      int i = j;
      if (this.zzaZT != null) {
        i = j + zzsn.zzd(1, this.zzaZT.longValue());
      }
      j = i;
      if (this.zzaVt != null) {
        j = i + zzsn.zzo(2, this.zzaVt);
      }
      i = j;
      if (this.zzaZU != null) {
        i = j + zzsn.zzC(3, this.zzaZU.intValue());
      }
      j = i;
      Object localObject;
      if (this.zzaZV != null)
      {
        j = i;
        if (this.zzaZV.length > 0)
        {
          j = 0;
          while (j < this.zzaZV.length)
          {
            localObject = this.zzaZV[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(4, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zzaZW != null)
      {
        i = j;
        if (this.zzaZW.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zzaZW.length)
          {
            localObject = this.zzaZW[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(5, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      int k = i;
      if (this.zzaZX != null)
      {
        k = i;
        if (this.zzaZX.length > 0)
        {
          j = m;
          for (;;)
          {
            k = i;
            if (j >= this.zzaZX.length) {
              break;
            }
            localObject = this.zzaZX[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(6, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
  }
  
  public static final class zzc
    extends zzsu
  {
    private static volatile zzc[] zzaZY;
    public String key;
    public String value;
    
    public zzc()
    {
      zzDO();
    }
    
    public static zzc[] zzDN()
    {
      if (zzaZY == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaZY == null) {
          zzaZY = new zzc[0];
        }
        return zzaZY;
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
          if (!(paramObject instanceof zzc)) {
            return false;
          }
          paramObject = (zzc)paramObject;
          if (this.key == null)
          {
            if (((zzc)paramObject).key != null) {
              return false;
            }
          }
          else if (!this.key.equals(((zzc)paramObject).key)) {
            return false;
          }
          if (this.value != null) {
            break;
          }
        } while (((zzc)paramObject).value == null);
        return false;
      } while (this.value.equals(((zzc)paramObject).value));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int k = getClass().getName().hashCode();
      int i;
      if (this.key == null)
      {
        i = 0;
        if (this.value != null) {
          break label56;
        }
      }
      for (;;)
      {
        return (i + (k + 527) * 31) * 31 + j;
        i = this.key.hashCode();
        break;
        label56:
        j = this.value.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.key != null) {
        paramzzsn.zzn(1, this.key);
      }
      if (this.value != null) {
        paramzzsn.zzn(2, this.value);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzc zzB(zzsm paramzzsm)
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
          this.key = paramzzsm.readString();
          break;
        case 18: 
          this.value = paramzzsm.readString();
        }
      }
    }
    
    public zzc zzDO()
    {
      this.key = null;
      this.value = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.key != null) {
        i = j + zzsn.zzo(1, this.key);
      }
      j = i;
      if (this.value != null) {
        j = i + zzsn.zzo(2, this.value);
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzqa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */