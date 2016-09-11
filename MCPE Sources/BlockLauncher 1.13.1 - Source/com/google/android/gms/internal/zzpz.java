package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzpz
{
  public static final class zza
    extends zzsu
  {
    private static volatile zza[] zzaZq;
    public Integer zzaZr;
    public zzpz.zze[] zzaZs;
    public zzpz.zzb[] zzaZt;
    
    public zza()
    {
      zzDB();
    }
    
    public static zza[] zzDA()
    {
      if (zzaZq == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaZq == null) {
          zzaZq = new zza[0];
        }
        return zzaZq;
      }
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
        if (this.zzaZr == null)
        {
          if (((zza)paramObject).zzaZr != null) {
            return false;
          }
        }
        else if (!this.zzaZr.equals(((zza)paramObject).zzaZr)) {
          return false;
        }
        if (!zzss.equals(this.zzaZs, ((zza)paramObject).zzaZs)) {
          return false;
        }
      } while (zzss.equals(this.zzaZt, ((zza)paramObject).zzaZt));
      return false;
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      if (this.zzaZr == null) {}
      for (int i = 0;; i = this.zzaZr.hashCode()) {
        return ((i + (j + 527) * 31) * 31 + zzss.hashCode(this.zzaZs)) * 31 + zzss.hashCode(this.zzaZt);
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      if (this.zzaZr != null) {
        paramzzsn.zzA(1, this.zzaZr.intValue());
      }
      int i;
      Object localObject;
      if ((this.zzaZs != null) && (this.zzaZs.length > 0))
      {
        i = 0;
        while (i < this.zzaZs.length)
        {
          localObject = this.zzaZs[i];
          if (localObject != null) {
            paramzzsn.zza(2, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzaZt != null) && (this.zzaZt.length > 0))
      {
        i = j;
        while (i < this.zzaZt.length)
        {
          localObject = this.zzaZt[i];
          if (localObject != null) {
            paramzzsn.zza(3, (zzsu)localObject);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzDB()
    {
      this.zzaZr = null;
      this.zzaZs = zzpz.zze.zzDH();
      this.zzaZt = zzpz.zzb.zzDC();
      this.zzbuu = -1;
      return this;
    }
    
    public zza zzt(zzsm paramzzsm)
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
          this.zzaZr = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 18: 
          j = zzsx.zzc(paramzzsm, 18);
          if (this.zzaZs == null) {}
          for (i = 0;; i = this.zzaZs.length)
          {
            localObject = new zzpz.zze[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaZs, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzpz.zze();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzpz.zze();
          paramzzsm.zza(localObject[j]);
          this.zzaZs = ((zzpz.zze[])localObject);
          break;
        case 26: 
          j = zzsx.zzc(paramzzsm, 26);
          if (this.zzaZt == null) {}
          for (i = 0;; i = this.zzaZt.length)
          {
            localObject = new zzpz.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaZt, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzpz.zzb();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzpz.zzb();
          paramzzsm.zza(localObject[j]);
          this.zzaZt = ((zzpz.zzb[])localObject);
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int i = super.zzz();
      int j = i;
      if (this.zzaZr != null) {
        j = i + zzsn.zzC(1, this.zzaZr.intValue());
      }
      i = j;
      Object localObject;
      if (this.zzaZs != null)
      {
        i = j;
        if (this.zzaZs.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zzaZs.length)
          {
            localObject = this.zzaZs[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(2, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      int k = i;
      if (this.zzaZt != null)
      {
        k = i;
        if (this.zzaZt.length > 0)
        {
          j = m;
          for (;;)
          {
            k = i;
            if (j >= this.zzaZt.length) {
              break;
            }
            localObject = this.zzaZt[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(3, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
  }
  
  public static final class zzb
    extends zzsu
  {
    private static volatile zzb[] zzaZu;
    public Integer zzaZv;
    public String zzaZw;
    public zzpz.zzc[] zzaZx;
    public Boolean zzaZy;
    public zzpz.zzd zzaZz;
    
    public zzb()
    {
      zzDD();
    }
    
    public static zzb[] zzDC()
    {
      if (zzaZu == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaZu == null) {
          zzaZu = new zzb[0];
        }
        return zzaZu;
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
          if (this.zzaZv == null)
          {
            if (((zzb)paramObject).zzaZv != null) {
              return false;
            }
          }
          else if (!this.zzaZv.equals(((zzb)paramObject).zzaZv)) {
            return false;
          }
          if (this.zzaZw == null)
          {
            if (((zzb)paramObject).zzaZw != null) {
              return false;
            }
          }
          else if (!this.zzaZw.equals(((zzb)paramObject).zzaZw)) {
            return false;
          }
          if (!zzss.equals(this.zzaZx, ((zzb)paramObject).zzaZx)) {
            return false;
          }
          if (this.zzaZy == null)
          {
            if (((zzb)paramObject).zzaZy != null) {
              return false;
            }
          }
          else if (!this.zzaZy.equals(((zzb)paramObject).zzaZy)) {
            return false;
          }
          if (this.zzaZz != null) {
            break;
          }
        } while (((zzb)paramObject).zzaZz == null);
        return false;
      } while (this.zzaZz.equals(((zzb)paramObject).zzaZz));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int i1;
      int k;
      if (this.zzaZv == null)
      {
        i = 0;
        if (this.zzaZw != null) {
          break label103;
        }
        j = 0;
        i1 = zzss.hashCode(this.zzaZx);
        if (this.zzaZy != null) {
          break label114;
        }
        k = 0;
        label51:
        if (this.zzaZz != null) {
          break label125;
        }
      }
      for (;;)
      {
        return (k + ((j + (i + (n + 527) * 31) * 31) * 31 + i1) * 31) * 31 + m;
        i = this.zzaZv.hashCode();
        break;
        label103:
        j = this.zzaZw.hashCode();
        break label33;
        label114:
        k = this.zzaZy.hashCode();
        break label51;
        label125:
        m = this.zzaZz.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzaZv != null) {
        paramzzsn.zzA(1, this.zzaZv.intValue());
      }
      if (this.zzaZw != null) {
        paramzzsn.zzn(2, this.zzaZw);
      }
      if ((this.zzaZx != null) && (this.zzaZx.length > 0))
      {
        int i = 0;
        while (i < this.zzaZx.length)
        {
          zzpz.zzc localzzc = this.zzaZx[i];
          if (localzzc != null) {
            paramzzsn.zza(3, localzzc);
          }
          i += 1;
        }
      }
      if (this.zzaZy != null) {
        paramzzsn.zze(4, this.zzaZy.booleanValue());
      }
      if (this.zzaZz != null) {
        paramzzsn.zza(5, this.zzaZz);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzb zzDD()
    {
      this.zzaZv = null;
      this.zzaZw = null;
      this.zzaZx = zzpz.zzc.zzDE();
      this.zzaZy = null;
      this.zzaZz = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzb zzu(zzsm paramzzsm)
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
        case 8: 
          this.zzaZv = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 18: 
          this.zzaZw = paramzzsm.readString();
          break;
        case 26: 
          int j = zzsx.zzc(paramzzsm, 26);
          if (this.zzaZx == null) {}
          zzpz.zzc[] arrayOfzzc;
          for (i = 0;; i = this.zzaZx.length)
          {
            arrayOfzzc = new zzpz.zzc[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaZx, 0, arrayOfzzc, 0, i);
              j = i;
            }
            while (j < arrayOfzzc.length - 1)
            {
              arrayOfzzc[j] = new zzpz.zzc();
              paramzzsm.zza(arrayOfzzc[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfzzc[j] = new zzpz.zzc();
          paramzzsm.zza(arrayOfzzc[j]);
          this.zzaZx = arrayOfzzc;
          break;
        case 32: 
          this.zzaZy = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 42: 
          if (this.zzaZz == null) {
            this.zzaZz = new zzpz.zzd();
          }
          paramzzsm.zza(this.zzaZz);
        }
      }
    }
    
    protected int zzz()
    {
      int i = super.zzz();
      int j = i;
      if (this.zzaZv != null) {
        j = i + zzsn.zzC(1, this.zzaZv.intValue());
      }
      i = j;
      if (this.zzaZw != null) {
        i = j + zzsn.zzo(2, this.zzaZw);
      }
      j = i;
      if (this.zzaZx != null)
      {
        j = i;
        if (this.zzaZx.length > 0)
        {
          j = 0;
          while (j < this.zzaZx.length)
          {
            zzpz.zzc localzzc = this.zzaZx[j];
            int k = i;
            if (localzzc != null) {
              k = i + zzsn.zzc(3, localzzc);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zzaZy != null) {
        i = j + zzsn.zzf(4, this.zzaZy.booleanValue());
      }
      j = i;
      if (this.zzaZz != null) {
        j = i + zzsn.zzc(5, this.zzaZz);
      }
      return j;
    }
  }
  
  public static final class zzc
    extends zzsu
  {
    private static volatile zzc[] zzaZA;
    public zzpz.zzf zzaZB;
    public zzpz.zzd zzaZC;
    public Boolean zzaZD;
    public String zzaZE;
    
    public zzc()
    {
      zzDF();
    }
    
    public static zzc[] zzDE()
    {
      if (zzaZA == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaZA == null) {
          zzaZA = new zzc[0];
        }
        return zzaZA;
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
          if (this.zzaZB == null)
          {
            if (((zzc)paramObject).zzaZB != null) {
              return false;
            }
          }
          else if (!this.zzaZB.equals(((zzc)paramObject).zzaZB)) {
            return false;
          }
          if (this.zzaZC == null)
          {
            if (((zzc)paramObject).zzaZC != null) {
              return false;
            }
          }
          else if (!this.zzaZC.equals(((zzc)paramObject).zzaZC)) {
            return false;
          }
          if (this.zzaZD == null)
          {
            if (((zzc)paramObject).zzaZD != null) {
              return false;
            }
          }
          else if (!this.zzaZD.equals(((zzc)paramObject).zzaZD)) {
            return false;
          }
          if (this.zzaZE != null) {
            break;
          }
        } while (((zzc)paramObject).zzaZE == null);
        return false;
      } while (this.zzaZE.equals(((zzc)paramObject).zzaZE));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      if (this.zzaZB == null)
      {
        i = 0;
        if (this.zzaZC != null) {
          break label88;
        }
        j = 0;
        if (this.zzaZD != null) {
          break label99;
        }
        k = 0;
        label42:
        if (this.zzaZE != null) {
          break label110;
        }
      }
      for (;;)
      {
        return (k + (j + (i + (n + 527) * 31) * 31) * 31) * 31 + m;
        i = this.zzaZB.hashCode();
        break;
        label88:
        j = this.zzaZC.hashCode();
        break label33;
        label99:
        k = this.zzaZD.hashCode();
        break label42;
        label110:
        m = this.zzaZE.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzaZB != null) {
        paramzzsn.zza(1, this.zzaZB);
      }
      if (this.zzaZC != null) {
        paramzzsn.zza(2, this.zzaZC);
      }
      if (this.zzaZD != null) {
        paramzzsn.zze(3, this.zzaZD.booleanValue());
      }
      if (this.zzaZE != null) {
        paramzzsn.zzn(4, this.zzaZE);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzc zzDF()
    {
      this.zzaZB = null;
      this.zzaZC = null;
      this.zzaZD = null;
      this.zzaZE = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzc zzv(zzsm paramzzsm)
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
          if (this.zzaZB == null) {
            this.zzaZB = new zzpz.zzf();
          }
          paramzzsm.zza(this.zzaZB);
          break;
        case 18: 
          if (this.zzaZC == null) {
            this.zzaZC = new zzpz.zzd();
          }
          paramzzsm.zza(this.zzaZC);
          break;
        case 24: 
          this.zzaZD = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 34: 
          this.zzaZE = paramzzsm.readString();
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.zzaZB != null) {
        i = j + zzsn.zzc(1, this.zzaZB);
      }
      j = i;
      if (this.zzaZC != null) {
        j = i + zzsn.zzc(2, this.zzaZC);
      }
      i = j;
      if (this.zzaZD != null) {
        i = j + zzsn.zzf(3, this.zzaZD.booleanValue());
      }
      j = i;
      if (this.zzaZE != null) {
        j = i + zzsn.zzo(4, this.zzaZE);
      }
      return j;
    }
  }
  
  public static final class zzd
    extends zzsu
  {
    public Integer zzaZF;
    public Boolean zzaZG;
    public String zzaZH;
    public String zzaZI;
    public String zzaZJ;
    
    public zzd()
    {
      zzDG();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zzd)) {
            return false;
          }
          paramObject = (zzd)paramObject;
          if (this.zzaZF == null)
          {
            if (((zzd)paramObject).zzaZF != null) {
              return false;
            }
          }
          else if (!this.zzaZF.equals(((zzd)paramObject).zzaZF)) {
            return false;
          }
          if (this.zzaZG == null)
          {
            if (((zzd)paramObject).zzaZG != null) {
              return false;
            }
          }
          else if (!this.zzaZG.equals(((zzd)paramObject).zzaZG)) {
            return false;
          }
          if (this.zzaZH == null)
          {
            if (((zzd)paramObject).zzaZH != null) {
              return false;
            }
          }
          else if (!this.zzaZH.equals(((zzd)paramObject).zzaZH)) {
            return false;
          }
          if (this.zzaZI == null)
          {
            if (((zzd)paramObject).zzaZI != null) {
              return false;
            }
          }
          else if (!this.zzaZI.equals(((zzd)paramObject).zzaZI)) {
            return false;
          }
          if (this.zzaZJ != null) {
            break;
          }
        } while (((zzd)paramObject).zzaZJ == null);
        return false;
      } while (this.zzaZJ.equals(((zzd)paramObject).zzaZJ));
      return false;
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      label42:
      int m;
      if (this.zzaZF == null)
      {
        i = 0;
        if (this.zzaZG != null) {
          break label104;
        }
        j = 0;
        if (this.zzaZH != null) {
          break label115;
        }
        k = 0;
        if (this.zzaZI != null) {
          break label126;
        }
        m = 0;
        label52:
        if (this.zzaZJ != null) {
          break label138;
        }
      }
      for (;;)
      {
        return (m + (k + (j + (i + (i1 + 527) * 31) * 31) * 31) * 31) * 31 + n;
        i = this.zzaZF.intValue();
        break;
        label104:
        j = this.zzaZG.hashCode();
        break label33;
        label115:
        k = this.zzaZH.hashCode();
        break label42;
        label126:
        m = this.zzaZI.hashCode();
        break label52;
        label138:
        n = this.zzaZJ.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzaZF != null) {
        paramzzsn.zzA(1, this.zzaZF.intValue());
      }
      if (this.zzaZG != null) {
        paramzzsn.zze(2, this.zzaZG.booleanValue());
      }
      if (this.zzaZH != null) {
        paramzzsn.zzn(3, this.zzaZH);
      }
      if (this.zzaZI != null) {
        paramzzsn.zzn(4, this.zzaZI);
      }
      if (this.zzaZJ != null) {
        paramzzsn.zzn(5, this.zzaZJ);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzd zzDG()
    {
      this.zzaZF = null;
      this.zzaZG = null;
      this.zzaZH = null;
      this.zzaZI = null;
      this.zzaZJ = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzd zzw(zzsm paramzzsm)
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
        case 8: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
            this.zzaZF = Integer.valueOf(i);
          }
          break;
        case 16: 
          this.zzaZG = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 26: 
          this.zzaZH = paramzzsm.readString();
          break;
        case 34: 
          this.zzaZI = paramzzsm.readString();
          break;
        case 42: 
          this.zzaZJ = paramzzsm.readString();
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.zzaZF != null) {
        i = j + zzsn.zzC(1, this.zzaZF.intValue());
      }
      j = i;
      if (this.zzaZG != null) {
        j = i + zzsn.zzf(2, this.zzaZG.booleanValue());
      }
      i = j;
      if (this.zzaZH != null) {
        i = j + zzsn.zzo(3, this.zzaZH);
      }
      j = i;
      if (this.zzaZI != null) {
        j = i + zzsn.zzo(4, this.zzaZI);
      }
      i = j;
      if (this.zzaZJ != null) {
        i = j + zzsn.zzo(5, this.zzaZJ);
      }
      return i;
    }
  }
  
  public static final class zze
    extends zzsu
  {
    private static volatile zze[] zzaZK;
    public String zzaZL;
    public zzpz.zzc zzaZM;
    public Integer zzaZv;
    
    public zze()
    {
      zzDI();
    }
    
    public static zze[] zzDH()
    {
      if (zzaZK == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaZK == null) {
          zzaZK = new zze[0];
        }
        return zzaZK;
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
          if (!(paramObject instanceof zze)) {
            return false;
          }
          paramObject = (zze)paramObject;
          if (this.zzaZv == null)
          {
            if (((zze)paramObject).zzaZv != null) {
              return false;
            }
          }
          else if (!this.zzaZv.equals(((zze)paramObject).zzaZv)) {
            return false;
          }
          if (this.zzaZL == null)
          {
            if (((zze)paramObject).zzaZL != null) {
              return false;
            }
          }
          else if (!this.zzaZL.equals(((zze)paramObject).zzaZL)) {
            return false;
          }
          if (this.zzaZM != null) {
            break;
          }
        } while (((zze)paramObject).zzaZM == null);
        return false;
      } while (this.zzaZM.equals(((zze)paramObject).zzaZM));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.zzaZv == null)
      {
        i = 0;
        if (this.zzaZL != null) {
          break label72;
        }
        j = 0;
        label32:
        if (this.zzaZM != null) {
          break label83;
        }
      }
      for (;;)
      {
        return (j + (i + (m + 527) * 31) * 31) * 31 + k;
        i = this.zzaZv.hashCode();
        break;
        label72:
        j = this.zzaZL.hashCode();
        break label32;
        label83:
        k = this.zzaZM.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzaZv != null) {
        paramzzsn.zzA(1, this.zzaZv.intValue());
      }
      if (this.zzaZL != null) {
        paramzzsn.zzn(2, this.zzaZL);
      }
      if (this.zzaZM != null) {
        paramzzsn.zza(3, this.zzaZM);
      }
      super.writeTo(paramzzsn);
    }
    
    public zze zzDI()
    {
      this.zzaZv = null;
      this.zzaZL = null;
      this.zzaZM = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zze zzx(zzsm paramzzsm)
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
        case 8: 
          this.zzaZv = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 18: 
          this.zzaZL = paramzzsm.readString();
          break;
        case 26: 
          if (this.zzaZM == null) {
            this.zzaZM = new zzpz.zzc();
          }
          paramzzsm.zza(this.zzaZM);
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.zzaZv != null) {
        i = j + zzsn.zzC(1, this.zzaZv.intValue());
      }
      j = i;
      if (this.zzaZL != null) {
        j = i + zzsn.zzo(2, this.zzaZL);
      }
      i = j;
      if (this.zzaZM != null) {
        i = j + zzsn.zzc(3, this.zzaZM);
      }
      return i;
    }
  }
  
  public static final class zzf
    extends zzsu
  {
    public Integer zzaZN;
    public String zzaZO;
    public Boolean zzaZP;
    public String[] zzaZQ;
    
    public zzf()
    {
      zzDJ();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzf)) {
          return false;
        }
        paramObject = (zzf)paramObject;
        if (this.zzaZN == null)
        {
          if (((zzf)paramObject).zzaZN != null) {
            return false;
          }
        }
        else if (!this.zzaZN.equals(((zzf)paramObject).zzaZN)) {
          return false;
        }
        if (this.zzaZO == null)
        {
          if (((zzf)paramObject).zzaZO != null) {
            return false;
          }
        }
        else if (!this.zzaZO.equals(((zzf)paramObject).zzaZO)) {
          return false;
        }
        if (this.zzaZP == null)
        {
          if (((zzf)paramObject).zzaZP != null) {
            return false;
          }
        }
        else if (!this.zzaZP.equals(((zzf)paramObject).zzaZP)) {
          return false;
        }
      } while (zzss.equals(this.zzaZQ, ((zzf)paramObject).zzaZQ));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.zzaZN == null)
      {
        i = 0;
        if (this.zzaZO != null) {
          break label83;
        }
        j = 0;
        label32:
        if (this.zzaZP != null) {
          break label94;
        }
      }
      for (;;)
      {
        return ((j + (i + (m + 527) * 31) * 31) * 31 + k) * 31 + zzss.hashCode(this.zzaZQ);
        i = this.zzaZN.intValue();
        break;
        label83:
        j = this.zzaZO.hashCode();
        break label32;
        label94:
        k = this.zzaZP.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzaZN != null) {
        paramzzsn.zzA(1, this.zzaZN.intValue());
      }
      if (this.zzaZO != null) {
        paramzzsn.zzn(2, this.zzaZO);
      }
      if (this.zzaZP != null) {
        paramzzsn.zze(3, this.zzaZP.booleanValue());
      }
      if ((this.zzaZQ != null) && (this.zzaZQ.length > 0))
      {
        int i = 0;
        while (i < this.zzaZQ.length)
        {
          String str = this.zzaZQ[i];
          if (str != null) {
            paramzzsn.zzn(4, str);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzf zzDJ()
    {
      this.zzaZN = null;
      this.zzaZO = null;
      this.zzaZP = null;
      this.zzaZQ = zzsx.zzbuB;
      this.zzbuu = -1;
      return this;
    }
    
    public zzf zzy(zzsm paramzzsm)
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
        case 8: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
            this.zzaZN = Integer.valueOf(i);
          }
          break;
        case 18: 
          this.zzaZO = paramzzsm.readString();
          break;
        case 24: 
          this.zzaZP = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 34: 
          int j = zzsx.zzc(paramzzsm, 34);
          if (this.zzaZQ == null) {}
          String[] arrayOfString;
          for (i = 0;; i = this.zzaZQ.length)
          {
            arrayOfString = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaZQ, 0, arrayOfString, 0, i);
              j = i;
            }
            while (j < arrayOfString.length - 1)
            {
              arrayOfString[j] = paramzzsm.readString();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfString[j] = paramzzsm.readString();
          this.zzaZQ = arrayOfString;
        }
      }
    }
    
    protected int zzz()
    {
      int n = 0;
      int j = super.zzz();
      int i = j;
      if (this.zzaZN != null) {
        i = j + zzsn.zzC(1, this.zzaZN.intValue());
      }
      j = i;
      if (this.zzaZO != null) {
        j = i + zzsn.zzo(2, this.zzaZO);
      }
      i = j;
      if (this.zzaZP != null) {
        i = j + zzsn.zzf(3, this.zzaZP.booleanValue());
      }
      j = i;
      if (this.zzaZQ != null)
      {
        j = i;
        if (this.zzaZQ.length > 0)
        {
          int k = 0;
          int m = 0;
          j = n;
          while (j < this.zzaZQ.length)
          {
            String str = this.zzaZQ[j];
            int i1 = k;
            n = m;
            if (str != null)
            {
              n = m + 1;
              i1 = k + zzsn.zzgO(str);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */