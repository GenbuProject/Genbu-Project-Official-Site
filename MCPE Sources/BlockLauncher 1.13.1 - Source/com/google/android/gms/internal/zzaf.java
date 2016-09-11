package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzaf
{
  public static final class zza
    extends zzso<zza>
  {
    public int level;
    public int zziq;
    public int zzir;
    
    public zza()
    {
      zzB();
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
              } while (!(paramObject instanceof zza));
              paramObject = (zza)paramObject;
              bool1 = bool2;
            } while (this.level != ((zza)paramObject).level);
            bool1 = bool2;
          } while (this.zziq != ((zza)paramObject).zziq);
          bool1 = bool2;
        } while (this.zzir != ((zza)paramObject).zzir);
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label102;
        }
        if (((zza)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zza)paramObject).zzbuj.isEmpty());
      return true;
      label102:
      return this.zzbuj.equals(((zza)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = this.level;
      int m = this.zziq;
      int n = this.zzir;
      if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
      for (int i = 0;; i = this.zzbuj.hashCode()) {
        return i + ((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.level != 1) {
        paramzzsn.zzA(1, this.level);
      }
      if (this.zziq != 0) {
        paramzzsn.zzA(2, this.zziq);
      }
      if (this.zzir != 0) {
        paramzzsn.zzA(3, this.zzir);
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzB()
    {
      this.level = 1;
      this.zziq = 0;
      this.zzir = 0;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zza zza(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 1: 
          case 2: 
          case 3: 
            this.level = i;
          }
          break;
        case 16: 
          this.zziq = paramzzsm.zzJb();
          break;
        case 24: 
          this.zzir = paramzzsm.zzJb();
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.level != 1) {
        i = j + zzsn.zzC(1, this.level);
      }
      j = i;
      if (this.zziq != 0) {
        j = i + zzsn.zzC(2, this.zziq);
      }
      i = j;
      if (this.zzir != 0) {
        i = j + zzsn.zzC(3, this.zzir);
      }
      return i;
    }
  }
  
  public static final class zzb
    extends zzso<zzb>
  {
    private static volatile zzb[] zzis;
    public int name;
    public int[] zzit;
    public int zziu;
    public boolean zziv;
    public boolean zziw;
    
    public zzb()
    {
      zzD();
    }
    
    public static zzb[] zzC()
    {
      if (zzis == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzis == null) {
          zzis = new zzb[0];
        }
        return zzis;
      }
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
                do
                {
                  do
                  {
                    return bool1;
                    bool1 = bool2;
                  } while (!(paramObject instanceof zzb));
                  paramObject = (zzb)paramObject;
                  bool1 = bool2;
                } while (!zzss.equals(this.zzit, ((zzb)paramObject).zzit));
                bool1 = bool2;
              } while (this.zziu != ((zzb)paramObject).zziu);
              bool1 = bool2;
            } while (this.name != ((zzb)paramObject).name);
            bool1 = bool2;
          } while (this.zziv != ((zzb)paramObject).zziv);
          bool1 = bool2;
        } while (this.zziw != ((zzb)paramObject).zziw);
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label131;
        }
        if (((zzb)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zzb)paramObject).zzbuj.isEmpty());
      return true;
      label131:
      return this.zzbuj.equals(((zzb)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = 1231;
      int m = getClass().getName().hashCode();
      int n = zzss.hashCode(this.zzit);
      int i1 = this.zziu;
      int i2 = this.name;
      int i;
      if (this.zziv)
      {
        i = 1231;
        if (!this.zziw) {
          break label121;
        }
        label55:
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label128;
        }
      }
      label121:
      label128:
      for (int k = 0;; k = this.zzbuj.hashCode())
      {
        return k + ((i + ((((m + 527) * 31 + n) * 31 + i1) * 31 + i2) * 31) * 31 + j) * 31;
        i = 1237;
        break;
        j = 1237;
        break label55;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zziw) {
        paramzzsn.zze(1, this.zziw);
      }
      paramzzsn.zzA(2, this.zziu);
      if ((this.zzit != null) && (this.zzit.length > 0))
      {
        int i = 0;
        while (i < this.zzit.length)
        {
          paramzzsn.zzA(3, this.zzit[i]);
          i += 1;
        }
      }
      if (this.name != 0) {
        paramzzsn.zzA(4, this.name);
      }
      if (this.zziv) {
        paramzzsn.zze(6, this.zziv);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzb zzD()
    {
      this.zzit = zzsx.zzbuw;
      this.zziu = 0;
      this.name = 0;
      this.zziv = false;
      this.zziw = false;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzb zzb(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        int j;
        int[] arrayOfInt;
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zziw = paramzzsm.zzJc();
          break;
        case 16: 
          this.zziu = paramzzsm.zzJb();
          break;
        case 24: 
          j = zzsx.zzc(paramzzsm, 24);
          if (this.zzit == null) {}
          for (i = 0;; i = this.zzit.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzit, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzit = arrayOfInt;
          break;
        case 26: 
          int k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzit == null) {}
          for (i = 0;; i = this.zzit.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzit, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzit = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 32: 
          this.name = paramzzsm.zzJb();
          break;
        case 48: 
          this.zziv = paramzzsm.zzJc();
        }
      }
    }
    
    protected int zzz()
    {
      int j = 0;
      int k = super.zzz();
      int i = k;
      if (this.zziw) {
        i = k + zzsn.zzf(1, this.zziw);
      }
      k = zzsn.zzC(2, this.zziu) + i;
      if ((this.zzit != null) && (this.zzit.length > 0))
      {
        i = 0;
        while (i < this.zzit.length)
        {
          j += zzsn.zzmx(this.zzit[i]);
          i += 1;
        }
      }
      for (j = k + j + this.zzit.length * 1;; j = k)
      {
        i = j;
        if (this.name != 0) {
          i = j + zzsn.zzC(4, this.name);
        }
        j = i;
        if (this.zziv) {
          j = i + zzsn.zzf(6, this.zziv);
        }
        return j;
      }
    }
  }
  
  public static final class zzc
    extends zzso<zzc>
  {
    private static volatile zzc[] zzix;
    public String key;
    public boolean zziA;
    public long zziB;
    public long zziy;
    public long zziz;
    
    public zzc()
    {
      zzF();
    }
    
    public static zzc[] zzE()
    {
      if (zzix == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzix == null) {
          zzix = new zzc[0];
        }
        return zzix;
      }
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
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof zzc));
        paramObject = (zzc)paramObject;
        if (this.key != null) {
          break;
        }
        bool1 = bool2;
      } while (((zzc)paramObject).key != null);
      while (this.key.equals(((zzc)paramObject).key))
      {
        bool1 = bool2;
        if (this.zziy != ((zzc)paramObject).zziy) {
          break;
        }
        bool1 = bool2;
        if (this.zziz != ((zzc)paramObject).zziz) {
          break;
        }
        bool1 = bool2;
        if (this.zziA != ((zzc)paramObject).zziA) {
          break;
        }
        bool1 = bool2;
        if (this.zziB != ((zzc)paramObject).zziB) {
          break;
        }
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label150;
        }
        if (((zzc)paramObject).zzbuj != null)
        {
          bool1 = bool2;
          if (!((zzc)paramObject).zzbuj.isEmpty()) {
            break;
          }
        }
        return true;
      }
      return false;
      label150:
      return this.zzbuj.equals(((zzc)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int i1;
      int i2;
      int j;
      label65:
      int i3;
      if (this.key == null)
      {
        i = 0;
        i1 = (int)(this.zziy ^ this.zziy >>> 32);
        i2 = (int)(this.zziz ^ this.zziz >>> 32);
        if (!this.zziA) {
          break label154;
        }
        j = 1231;
        i3 = (int)(this.zziB ^ this.zziB >>> 32);
        k = m;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label161;
          }
        }
      }
      label154:
      label161:
      for (int k = m;; k = this.zzbuj.hashCode())
      {
        return ((j + (((i + (n + 527) * 31) * 31 + i1) * 31 + i2) * 31) * 31 + i3) * 31 + k;
        i = this.key.hashCode();
        break;
        j = 1237;
        break label65;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (!this.key.equals("")) {
        paramzzsn.zzn(1, this.key);
      }
      if (this.zziy != 0L) {
        paramzzsn.zzb(2, this.zziy);
      }
      if (this.zziz != 2147483647L) {
        paramzzsn.zzb(3, this.zziz);
      }
      if (this.zziA) {
        paramzzsn.zze(4, this.zziA);
      }
      if (this.zziB != 0L) {
        paramzzsn.zzb(5, this.zziB);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzc zzF()
    {
      this.key = "";
      this.zziy = 0L;
      this.zziz = 2147483647L;
      this.zziA = false;
      this.zziB = 0L;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzc zzc(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.key = paramzzsm.readString();
          break;
        case 16: 
          this.zziy = paramzzsm.zzJa();
          break;
        case 24: 
          this.zziz = paramzzsm.zzJa();
          break;
        case 32: 
          this.zziA = paramzzsm.zzJc();
          break;
        case 40: 
          this.zziB = paramzzsm.zzJa();
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (!this.key.equals("")) {
        i = j + zzsn.zzo(1, this.key);
      }
      j = i;
      if (this.zziy != 0L) {
        j = i + zzsn.zzd(2, this.zziy);
      }
      i = j;
      if (this.zziz != 2147483647L) {
        i = j + zzsn.zzd(3, this.zziz);
      }
      j = i;
      if (this.zziA) {
        j = i + zzsn.zzf(4, this.zziA);
      }
      i = j;
      if (this.zziB != 0L) {
        i = j + zzsn.zzd(5, this.zziB);
      }
      return i;
    }
  }
  
  public static final class zzd
    extends zzso<zzd>
  {
    public zzag.zza[] zziC;
    public zzag.zza[] zziD;
    public zzaf.zzc[] zziE;
    
    public zzd()
    {
      zzG();
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
              } while (!(paramObject instanceof zzd));
              paramObject = (zzd)paramObject;
              bool1 = bool2;
            } while (!zzss.equals(this.zziC, ((zzd)paramObject).zziC));
            bool1 = bool2;
          } while (!zzss.equals(this.zziD, ((zzd)paramObject).zziD));
          bool1 = bool2;
        } while (!zzss.equals(this.zziE, ((zzd)paramObject).zziE));
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label111;
        }
        if (((zzd)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zzd)paramObject).zzbuj.isEmpty());
      return true;
      label111:
      return this.zzbuj.equals(((zzd)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = zzss.hashCode(this.zziC);
      int m = zzss.hashCode(this.zziD);
      int n = zzss.hashCode(this.zziE);
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
      if ((this.zziC != null) && (this.zziC.length > 0))
      {
        i = 0;
        while (i < this.zziC.length)
        {
          localObject = this.zziC[i];
          if (localObject != null) {
            paramzzsn.zza(1, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziD != null) && (this.zziD.length > 0))
      {
        i = 0;
        while (i < this.zziD.length)
        {
          localObject = this.zziD[i];
          if (localObject != null) {
            paramzzsn.zza(2, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziE != null) && (this.zziE.length > 0))
      {
        i = j;
        while (i < this.zziE.length)
        {
          localObject = this.zziE[i];
          if (localObject != null) {
            paramzzsn.zza(3, (zzsu)localObject);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzd zzG()
    {
      this.zziC = zzag.zza.zzQ();
      this.zziD = zzag.zza.zzQ();
      this.zziE = zzaf.zzc.zzE();
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzd zzd(zzsm paramzzsm)
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
          if (this.zziC == null) {}
          for (i = 0;; i = this.zziC.length)
          {
            localObject = new zzag.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziC, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzag.zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzag.zza();
          paramzzsm.zza(localObject[j]);
          this.zziC = ((zzag.zza[])localObject);
          break;
        case 18: 
          j = zzsx.zzc(paramzzsm, 18);
          if (this.zziD == null) {}
          for (i = 0;; i = this.zziD.length)
          {
            localObject = new zzag.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziD, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzag.zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzag.zza();
          paramzzsm.zza(localObject[j]);
          this.zziD = ((zzag.zza[])localObject);
          break;
        case 26: 
          j = zzsx.zzc(paramzzsm, 26);
          if (this.zziE == null) {}
          for (i = 0;; i = this.zziE.length)
          {
            localObject = new zzaf.zzc[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziE, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaf.zzc();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzaf.zzc();
          paramzzsm.zza(localObject[j]);
          this.zziE = ((zzaf.zzc[])localObject);
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int i = super.zzz();
      int j = i;
      Object localObject;
      if (this.zziC != null)
      {
        j = i;
        if (this.zziC.length > 0)
        {
          j = 0;
          while (j < this.zziC.length)
          {
            localObject = this.zziC[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(1, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zziD != null)
      {
        i = j;
        if (this.zziD.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zziD.length)
          {
            localObject = this.zziD[j];
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
      if (this.zziE != null)
      {
        k = i;
        if (this.zziE.length > 0)
        {
          j = m;
          for (;;)
          {
            k = i;
            if (j >= this.zziE.length) {
              break;
            }
            localObject = this.zziE[j];
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
  
  public static final class zze
    extends zzso<zze>
  {
    private static volatile zze[] zziF;
    public int key;
    public int value;
    
    public zze()
    {
      zzI();
    }
    
    public static zze[] zzH()
    {
      if (zziF == null) {}
      synchronized (zzss.zzbut)
      {
        if (zziF == null) {
          zziF = new zze[0];
        }
        return zziF;
      }
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
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zze));
            paramObject = (zze)paramObject;
            bool1 = bool2;
          } while (this.key != ((zze)paramObject).key);
          bool1 = bool2;
        } while (this.value != ((zze)paramObject).value);
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label89;
        }
        if (((zze)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zze)paramObject).zzbuj.isEmpty());
      return true;
      label89:
      return this.zzbuj.equals(((zze)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = this.key;
      int m = this.value;
      if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
      for (int i = 0;; i = this.zzbuj.hashCode()) {
        return i + (((j + 527) * 31 + k) * 31 + m) * 31;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      paramzzsn.zzA(1, this.key);
      paramzzsn.zzA(2, this.value);
      super.writeTo(paramzzsn);
    }
    
    public zze zzI()
    {
      this.key = 0;
      this.value = 0;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zze zze(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.key = paramzzsm.zzJb();
          break;
        case 16: 
          this.value = paramzzsm.zzJb();
        }
      }
    }
    
    protected int zzz()
    {
      return super.zzz() + zzsn.zzC(1, this.key) + zzsn.zzC(2, this.value);
    }
  }
  
  public static final class zzf
    extends zzso<zzf>
  {
    public String version;
    public String[] zziG;
    public String[] zziH;
    public zzag.zza[] zziI;
    public zzaf.zze[] zziJ;
    public zzaf.zzb[] zziK;
    public zzaf.zzb[] zziL;
    public zzaf.zzb[] zziM;
    public zzaf.zzg[] zziN;
    public String zziO;
    public String zziP;
    public String zziQ;
    public zzaf.zza zziR;
    public float zziS;
    public boolean zziT;
    public String[] zziU;
    public int zziV;
    
    public zzf()
    {
      zzJ();
    }
    
    public static zzf zzc(byte[] paramArrayOfByte)
      throws zzst
    {
      return (zzf)zzsu.mergeFrom(new zzf(), paramArrayOfByte);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label169:
      label185:
      label201:
      label217:
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
                                } while (!(paramObject instanceof zzf));
                                paramObject = (zzf)paramObject;
                                bool1 = bool2;
                              } while (!zzss.equals(this.zziG, ((zzf)paramObject).zziG));
                              bool1 = bool2;
                            } while (!zzss.equals(this.zziH, ((zzf)paramObject).zziH));
                            bool1 = bool2;
                          } while (!zzss.equals(this.zziI, ((zzf)paramObject).zziI));
                          bool1 = bool2;
                        } while (!zzss.equals(this.zziJ, ((zzf)paramObject).zziJ));
                        bool1 = bool2;
                      } while (!zzss.equals(this.zziK, ((zzf)paramObject).zziK));
                      bool1 = bool2;
                    } while (!zzss.equals(this.zziL, ((zzf)paramObject).zziL));
                    bool1 = bool2;
                  } while (!zzss.equals(this.zziM, ((zzf)paramObject).zziM));
                  bool1 = bool2;
                } while (!zzss.equals(this.zziN, ((zzf)paramObject).zziN));
                if (this.zziO != null) {
                  break;
                }
                bool1 = bool2;
              } while (((zzf)paramObject).zziO != null);
              if (this.zziP != null) {
                break label348;
              }
              bool1 = bool2;
            } while (((zzf)paramObject).zziP != null);
            if (this.zziQ != null) {
              break label364;
            }
            bool1 = bool2;
          } while (((zzf)paramObject).zziQ != null);
          if (this.version != null) {
            break label380;
          }
          bool1 = bool2;
        } while (((zzf)paramObject).version != null);
        if (this.zziR != null) {
          break label396;
        }
        bool1 = bool2;
      } while (((zzf)paramObject).zziR != null);
      label348:
      label364:
      label380:
      label396:
      while (this.zziR.equals(((zzf)paramObject).zziR))
      {
        bool1 = bool2;
        if (Float.floatToIntBits(this.zziS) != Float.floatToIntBits(((zzf)paramObject).zziS)) {
          break;
        }
        bool1 = bool2;
        if (this.zziT != ((zzf)paramObject).zziT) {
          break;
        }
        bool1 = bool2;
        if (!zzss.equals(this.zziU, ((zzf)paramObject).zziU)) {
          break;
        }
        bool1 = bool2;
        if (this.zziV != ((zzf)paramObject).zziV) {
          break;
        }
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label412;
        }
        if (((zzf)paramObject).zzbuj != null)
        {
          bool1 = bool2;
          if (!((zzf)paramObject).zzbuj.isEmpty()) {
            break;
          }
        }
        return true;
        if (this.zziO.equals(((zzf)paramObject).zziO)) {
          break label169;
        }
        return false;
        if (this.zziP.equals(((zzf)paramObject).zziP)) {
          break label185;
        }
        return false;
        if (this.zziQ.equals(((zzf)paramObject).zziQ)) {
          break label201;
        }
        return false;
        if (this.version.equals(((zzf)paramObject).version)) {
          break label217;
        }
        return false;
      }
      return false;
      label412:
      return this.zzbuj.equals(((zzf)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int i3 = 0;
      int i4 = getClass().getName().hashCode();
      int i5 = zzss.hashCode(this.zziG);
      int i6 = zzss.hashCode(this.zziH);
      int i7 = zzss.hashCode(this.zziI);
      int i8 = zzss.hashCode(this.zziJ);
      int i9 = zzss.hashCode(this.zziK);
      int i10 = zzss.hashCode(this.zziL);
      int i11 = zzss.hashCode(this.zziM);
      int i12 = zzss.hashCode(this.zziN);
      int i;
      int j;
      label105:
      int k;
      label114:
      int m;
      label124:
      int n;
      label134:
      int i13;
      int i1;
      label155:
      int i14;
      int i15;
      if (this.zziO == null)
      {
        i = 0;
        if (this.zziP != null) {
          break label318;
        }
        j = 0;
        if (this.zziQ != null) {
          break label329;
        }
        k = 0;
        if (this.version != null) {
          break label340;
        }
        m = 0;
        if (this.zziR != null) {
          break label352;
        }
        n = 0;
        i13 = Float.floatToIntBits(this.zziS);
        if (!this.zziT) {
          break label364;
        }
        i1 = 1231;
        i14 = zzss.hashCode(this.zziU);
        i15 = this.zziV;
        i2 = i3;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label372;
          }
        }
      }
      label318:
      label329:
      label340:
      label352:
      label364:
      label372:
      for (int i2 = i3;; i2 = this.zzbuj.hashCode())
      {
        return (((i1 + ((n + (m + (k + (j + (i + (((((((((i4 + 527) * 31 + i5) * 31 + i6) * 31 + i7) * 31 + i8) * 31 + i9) * 31 + i10) * 31 + i11) * 31 + i12) * 31) * 31) * 31) * 31) * 31) * 31 + i13) * 31) * 31 + i14) * 31 + i15) * 31 + i2;
        i = this.zziO.hashCode();
        break;
        j = this.zziP.hashCode();
        break label105;
        k = this.zziQ.hashCode();
        break label114;
        m = this.version.hashCode();
        break label124;
        n = this.zziR.hashCode();
        break label134;
        i1 = 1237;
        break label155;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      int i;
      Object localObject;
      if ((this.zziH != null) && (this.zziH.length > 0))
      {
        i = 0;
        while (i < this.zziH.length)
        {
          localObject = this.zziH[i];
          if (localObject != null) {
            paramzzsn.zzn(1, (String)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziI != null) && (this.zziI.length > 0))
      {
        i = 0;
        while (i < this.zziI.length)
        {
          localObject = this.zziI[i];
          if (localObject != null) {
            paramzzsn.zza(2, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziJ != null) && (this.zziJ.length > 0))
      {
        i = 0;
        while (i < this.zziJ.length)
        {
          localObject = this.zziJ[i];
          if (localObject != null) {
            paramzzsn.zza(3, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziK != null) && (this.zziK.length > 0))
      {
        i = 0;
        while (i < this.zziK.length)
        {
          localObject = this.zziK[i];
          if (localObject != null) {
            paramzzsn.zza(4, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziL != null) && (this.zziL.length > 0))
      {
        i = 0;
        while (i < this.zziL.length)
        {
          localObject = this.zziL[i];
          if (localObject != null) {
            paramzzsn.zza(5, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziM != null) && (this.zziM.length > 0))
      {
        i = 0;
        while (i < this.zziM.length)
        {
          localObject = this.zziM[i];
          if (localObject != null) {
            paramzzsn.zza(6, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zziN != null) && (this.zziN.length > 0))
      {
        i = 0;
        while (i < this.zziN.length)
        {
          localObject = this.zziN[i];
          if (localObject != null) {
            paramzzsn.zza(7, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if (!this.zziO.equals("")) {
        paramzzsn.zzn(9, this.zziO);
      }
      if (!this.zziP.equals("")) {
        paramzzsn.zzn(10, this.zziP);
      }
      if (!this.zziQ.equals("0")) {
        paramzzsn.zzn(12, this.zziQ);
      }
      if (!this.version.equals("")) {
        paramzzsn.zzn(13, this.version);
      }
      if (this.zziR != null) {
        paramzzsn.zza(14, this.zziR);
      }
      if (Float.floatToIntBits(this.zziS) != Float.floatToIntBits(0.0F)) {
        paramzzsn.zzb(15, this.zziS);
      }
      if ((this.zziU != null) && (this.zziU.length > 0))
      {
        i = 0;
        while (i < this.zziU.length)
        {
          localObject = this.zziU[i];
          if (localObject != null) {
            paramzzsn.zzn(16, (String)localObject);
          }
          i += 1;
        }
      }
      if (this.zziV != 0) {
        paramzzsn.zzA(17, this.zziV);
      }
      if (this.zziT) {
        paramzzsn.zze(18, this.zziT);
      }
      if ((this.zziG != null) && (this.zziG.length > 0))
      {
        i = j;
        while (i < this.zziG.length)
        {
          localObject = this.zziG[i];
          if (localObject != null) {
            paramzzsn.zzn(19, (String)localObject);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzf zzJ()
    {
      this.zziG = zzsx.zzbuB;
      this.zziH = zzsx.zzbuB;
      this.zziI = zzag.zza.zzQ();
      this.zziJ = zzaf.zze.zzH();
      this.zziK = zzaf.zzb.zzC();
      this.zziL = zzaf.zzb.zzC();
      this.zziM = zzaf.zzb.zzC();
      this.zziN = zzaf.zzg.zzK();
      this.zziO = "";
      this.zziP = "";
      this.zziQ = "0";
      this.version = "";
      this.zziR = null;
      this.zziS = 0.0F;
      this.zziT = false;
      this.zziU = zzsx.zzbuB;
      this.zziV = 0;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzf zzf(zzsm paramzzsm)
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
          if (this.zziH == null) {}
          for (i = 0;; i = this.zziH.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziH, 0, localObject, 0, i);
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
          this.zziH = ((String[])localObject);
          break;
        case 18: 
          j = zzsx.zzc(paramzzsm, 18);
          if (this.zziI == null) {}
          for (i = 0;; i = this.zziI.length)
          {
            localObject = new zzag.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziI, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzag.zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzag.zza();
          paramzzsm.zza(localObject[j]);
          this.zziI = ((zzag.zza[])localObject);
          break;
        case 26: 
          j = zzsx.zzc(paramzzsm, 26);
          if (this.zziJ == null) {}
          for (i = 0;; i = this.zziJ.length)
          {
            localObject = new zzaf.zze[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziJ, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaf.zze();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzaf.zze();
          paramzzsm.zza(localObject[j]);
          this.zziJ = ((zzaf.zze[])localObject);
          break;
        case 34: 
          j = zzsx.zzc(paramzzsm, 34);
          if (this.zziK == null) {}
          for (i = 0;; i = this.zziK.length)
          {
            localObject = new zzaf.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziK, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaf.zzb();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzaf.zzb();
          paramzzsm.zza(localObject[j]);
          this.zziK = ((zzaf.zzb[])localObject);
          break;
        case 42: 
          j = zzsx.zzc(paramzzsm, 42);
          if (this.zziL == null) {}
          for (i = 0;; i = this.zziL.length)
          {
            localObject = new zzaf.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziL, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaf.zzb();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzaf.zzb();
          paramzzsm.zza(localObject[j]);
          this.zziL = ((zzaf.zzb[])localObject);
          break;
        case 50: 
          j = zzsx.zzc(paramzzsm, 50);
          if (this.zziM == null) {}
          for (i = 0;; i = this.zziM.length)
          {
            localObject = new zzaf.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziM, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaf.zzb();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzaf.zzb();
          paramzzsm.zza(localObject[j]);
          this.zziM = ((zzaf.zzb[])localObject);
          break;
        case 58: 
          j = zzsx.zzc(paramzzsm, 58);
          if (this.zziN == null) {}
          for (i = 0;; i = this.zziN.length)
          {
            localObject = new zzaf.zzg[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziN, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaf.zzg();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzaf.zzg();
          paramzzsm.zza(localObject[j]);
          this.zziN = ((zzaf.zzg[])localObject);
          break;
        case 74: 
          this.zziO = paramzzsm.readString();
          break;
        case 82: 
          this.zziP = paramzzsm.readString();
          break;
        case 98: 
          this.zziQ = paramzzsm.readString();
          break;
        case 106: 
          this.version = paramzzsm.readString();
          break;
        case 114: 
          if (this.zziR == null) {
            this.zziR = new zzaf.zza();
          }
          paramzzsm.zza(this.zziR);
          break;
        case 125: 
          this.zziS = paramzzsm.readFloat();
          break;
        case 130: 
          j = zzsx.zzc(paramzzsm, 130);
          if (this.zziU == null) {}
          for (i = 0;; i = this.zziU.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziU, 0, localObject, 0, i);
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
          this.zziU = ((String[])localObject);
          break;
        case 136: 
          this.zziV = paramzzsm.zzJb();
          break;
        case 144: 
          this.zziT = paramzzsm.zzJc();
          break;
        case 154: 
          j = zzsx.zzc(paramzzsm, 154);
          if (this.zziG == null) {}
          for (i = 0;; i = this.zziG.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziG, 0, localObject, 0, i);
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
          this.zziG = ((String[])localObject);
        }
      }
    }
    
    protected int zzz()
    {
      int i2 = 0;
      int i1 = super.zzz();
      int i;
      int k;
      Object localObject;
      int n;
      int m;
      if ((this.zziH != null) && (this.zziH.length > 0))
      {
        i = 0;
        j = 0;
        for (k = 0; i < this.zziH.length; k = m)
        {
          localObject = this.zziH[i];
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
      for (int j = i1 + j + k * 1;; j = i1)
      {
        i = j;
        if (this.zziI != null)
        {
          i = j;
          if (this.zziI.length > 0)
          {
            i = j;
            j = 0;
            while (j < this.zziI.length)
            {
              localObject = this.zziI[j];
              k = i;
              if (localObject != null) {
                k = i + zzsn.zzc(2, (zzsu)localObject);
              }
              j += 1;
              i = k;
            }
          }
        }
        j = i;
        if (this.zziJ != null)
        {
          j = i;
          if (this.zziJ.length > 0)
          {
            j = 0;
            while (j < this.zziJ.length)
            {
              localObject = this.zziJ[j];
              k = i;
              if (localObject != null) {
                k = i + zzsn.zzc(3, (zzsu)localObject);
              }
              j += 1;
              i = k;
            }
            j = i;
          }
        }
        i = j;
        if (this.zziK != null)
        {
          i = j;
          if (this.zziK.length > 0)
          {
            i = j;
            j = 0;
            while (j < this.zziK.length)
            {
              localObject = this.zziK[j];
              k = i;
              if (localObject != null) {
                k = i + zzsn.zzc(4, (zzsu)localObject);
              }
              j += 1;
              i = k;
            }
          }
        }
        j = i;
        if (this.zziL != null)
        {
          j = i;
          if (this.zziL.length > 0)
          {
            j = 0;
            while (j < this.zziL.length)
            {
              localObject = this.zziL[j];
              k = i;
              if (localObject != null) {
                k = i + zzsn.zzc(5, (zzsu)localObject);
              }
              j += 1;
              i = k;
            }
            j = i;
          }
        }
        i = j;
        if (this.zziM != null)
        {
          i = j;
          if (this.zziM.length > 0)
          {
            i = j;
            j = 0;
            while (j < this.zziM.length)
            {
              localObject = this.zziM[j];
              k = i;
              if (localObject != null) {
                k = i + zzsn.zzc(6, (zzsu)localObject);
              }
              j += 1;
              i = k;
            }
          }
        }
        j = i;
        if (this.zziN != null)
        {
          j = i;
          if (this.zziN.length > 0)
          {
            j = 0;
            while (j < this.zziN.length)
            {
              localObject = this.zziN[j];
              k = i;
              if (localObject != null) {
                k = i + zzsn.zzc(7, (zzsu)localObject);
              }
              j += 1;
              i = k;
            }
            j = i;
          }
        }
        i = j;
        if (!this.zziO.equals("")) {
          i = j + zzsn.zzo(9, this.zziO);
        }
        j = i;
        if (!this.zziP.equals("")) {
          j = i + zzsn.zzo(10, this.zziP);
        }
        i = j;
        if (!this.zziQ.equals("0")) {
          i = j + zzsn.zzo(12, this.zziQ);
        }
        j = i;
        if (!this.version.equals("")) {
          j = i + zzsn.zzo(13, this.version);
        }
        k = j;
        if (this.zziR != null) {
          k = j + zzsn.zzc(14, this.zziR);
        }
        i = k;
        if (Float.floatToIntBits(this.zziS) != Float.floatToIntBits(0.0F)) {
          i = k + zzsn.zzc(15, this.zziS);
        }
        j = i;
        if (this.zziU != null)
        {
          j = i;
          if (this.zziU.length > 0)
          {
            j = 0;
            k = 0;
            for (m = 0; j < this.zziU.length; m = n)
            {
              localObject = this.zziU[j];
              i1 = k;
              n = m;
              if (localObject != null)
              {
                n = m + 1;
                i1 = k + zzsn.zzgO((String)localObject);
              }
              j += 1;
              k = i1;
            }
            j = i + k + m * 2;
          }
        }
        k = j;
        if (this.zziV != 0) {
          k = j + zzsn.zzC(17, this.zziV);
        }
        i = k;
        if (this.zziT) {
          i = k + zzsn.zzf(18, this.zziT);
        }
        j = i;
        if (this.zziG != null)
        {
          j = i;
          if (this.zziG.length > 0)
          {
            k = 0;
            m = 0;
            j = i2;
            while (j < this.zziG.length)
            {
              localObject = this.zziG[j];
              i1 = k;
              n = m;
              if (localObject != null)
              {
                n = m + 1;
                i1 = k + zzsn.zzgO((String)localObject);
              }
              j += 1;
              k = i1;
              m = n;
            }
            j = i + k + m * 2;
          }
        }
        return j;
      }
    }
  }
  
  public static final class zzg
    extends zzso<zzg>
  {
    private static volatile zzg[] zziW;
    public int[] zziX;
    public int[] zziY;
    public int[] zziZ;
    public int[] zzja;
    public int[] zzjb;
    public int[] zzjc;
    public int[] zzjd;
    public int[] zzje;
    public int[] zzjf;
    public int[] zzjg;
    
    public zzg()
    {
      zzL();
    }
    
    public static zzg[] zzK()
    {
      if (zziW == null) {}
      synchronized (zzss.zzbut)
      {
        if (zziW == null) {
          zziW = new zzg[0];
        }
        return zziW;
      }
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
                          do
                          {
                            do
                            {
                              return bool1;
                              bool1 = bool2;
                            } while (!(paramObject instanceof zzg));
                            paramObject = (zzg)paramObject;
                            bool1 = bool2;
                          } while (!zzss.equals(this.zziX, ((zzg)paramObject).zziX));
                          bool1 = bool2;
                        } while (!zzss.equals(this.zziY, ((zzg)paramObject).zziY));
                        bool1 = bool2;
                      } while (!zzss.equals(this.zziZ, ((zzg)paramObject).zziZ));
                      bool1 = bool2;
                    } while (!zzss.equals(this.zzja, ((zzg)paramObject).zzja));
                    bool1 = bool2;
                  } while (!zzss.equals(this.zzjb, ((zzg)paramObject).zzjb));
                  bool1 = bool2;
                } while (!zzss.equals(this.zzjc, ((zzg)paramObject).zzjc));
                bool1 = bool2;
              } while (!zzss.equals(this.zzjd, ((zzg)paramObject).zzjd));
              bool1 = bool2;
            } while (!zzss.equals(this.zzje, ((zzg)paramObject).zzje));
            bool1 = bool2;
          } while (!zzss.equals(this.zzjf, ((zzg)paramObject).zzjf));
          bool1 = bool2;
        } while (!zzss.equals(this.zzjg, ((zzg)paramObject).zzjg));
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label223;
        }
        if (((zzg)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zzg)paramObject).zzbuj.isEmpty());
      return true;
      label223:
      return this.zzbuj.equals(((zzg)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = zzss.hashCode(this.zziX);
      int m = zzss.hashCode(this.zziY);
      int n = zzss.hashCode(this.zziZ);
      int i1 = zzss.hashCode(this.zzja);
      int i2 = zzss.hashCode(this.zzjb);
      int i3 = zzss.hashCode(this.zzjc);
      int i4 = zzss.hashCode(this.zzjd);
      int i5 = zzss.hashCode(this.zzje);
      int i6 = zzss.hashCode(this.zzjf);
      int i7 = zzss.hashCode(this.zzjg);
      if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
      for (int i = 0;; i = this.zzbuj.hashCode()) {
        return i + (((((((((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i6) * 31 + i7) * 31;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      int i;
      if ((this.zziX != null) && (this.zziX.length > 0))
      {
        i = 0;
        while (i < this.zziX.length)
        {
          paramzzsn.zzA(1, this.zziX[i]);
          i += 1;
        }
      }
      if ((this.zziY != null) && (this.zziY.length > 0))
      {
        i = 0;
        while (i < this.zziY.length)
        {
          paramzzsn.zzA(2, this.zziY[i]);
          i += 1;
        }
      }
      if ((this.zziZ != null) && (this.zziZ.length > 0))
      {
        i = 0;
        while (i < this.zziZ.length)
        {
          paramzzsn.zzA(3, this.zziZ[i]);
          i += 1;
        }
      }
      if ((this.zzja != null) && (this.zzja.length > 0))
      {
        i = 0;
        while (i < this.zzja.length)
        {
          paramzzsn.zzA(4, this.zzja[i]);
          i += 1;
        }
      }
      if ((this.zzjb != null) && (this.zzjb.length > 0))
      {
        i = 0;
        while (i < this.zzjb.length)
        {
          paramzzsn.zzA(5, this.zzjb[i]);
          i += 1;
        }
      }
      if ((this.zzjc != null) && (this.zzjc.length > 0))
      {
        i = 0;
        while (i < this.zzjc.length)
        {
          paramzzsn.zzA(6, this.zzjc[i]);
          i += 1;
        }
      }
      if ((this.zzjd != null) && (this.zzjd.length > 0))
      {
        i = 0;
        while (i < this.zzjd.length)
        {
          paramzzsn.zzA(7, this.zzjd[i]);
          i += 1;
        }
      }
      if ((this.zzje != null) && (this.zzje.length > 0))
      {
        i = 0;
        while (i < this.zzje.length)
        {
          paramzzsn.zzA(8, this.zzje[i]);
          i += 1;
        }
      }
      if ((this.zzjf != null) && (this.zzjf.length > 0))
      {
        i = 0;
        while (i < this.zzjf.length)
        {
          paramzzsn.zzA(9, this.zzjf[i]);
          i += 1;
        }
      }
      if ((this.zzjg != null) && (this.zzjg.length > 0))
      {
        i = j;
        while (i < this.zzjg.length)
        {
          paramzzsn.zzA(10, this.zzjg[i]);
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzg zzL()
    {
      this.zziX = zzsx.zzbuw;
      this.zziY = zzsx.zzbuw;
      this.zziZ = zzsx.zzbuw;
      this.zzja = zzsx.zzbuw;
      this.zzjb = zzsx.zzbuw;
      this.zzjc = zzsx.zzbuw;
      this.zzjd = zzsx.zzbuw;
      this.zzje = zzsx.zzbuw;
      this.zzjf = zzsx.zzbuw;
      this.zzjg = zzsx.zzbuw;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzg zzg(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        int j;
        int[] arrayOfInt;
        int k;
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          j = zzsx.zzc(paramzzsm, 8);
          if (this.zziX == null) {}
          for (i = 0;; i = this.zziX.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziX, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zziX = arrayOfInt;
          break;
        case 10: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zziX == null) {}
          for (i = 0;; i = this.zziX.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziX, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zziX = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 16: 
          j = zzsx.zzc(paramzzsm, 16);
          if (this.zziY == null) {}
          for (i = 0;; i = this.zziY.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziY, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zziY = arrayOfInt;
          break;
        case 18: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zziY == null) {}
          for (i = 0;; i = this.zziY.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziY, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zziY = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 24: 
          j = zzsx.zzc(paramzzsm, 24);
          if (this.zziZ == null) {}
          for (i = 0;; i = this.zziZ.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziZ, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zziZ = arrayOfInt;
          break;
        case 26: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zziZ == null) {}
          for (i = 0;; i = this.zziZ.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zziZ, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zziZ = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 32: 
          j = zzsx.zzc(paramzzsm, 32);
          if (this.zzja == null) {}
          for (i = 0;; i = this.zzja.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzja, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzja = arrayOfInt;
          break;
        case 34: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzja == null) {}
          for (i = 0;; i = this.zzja.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzja, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzja = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 40: 
          j = zzsx.zzc(paramzzsm, 40);
          if (this.zzjb == null) {}
          for (i = 0;; i = this.zzjb.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjb, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjb = arrayOfInt;
          break;
        case 42: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjb == null) {}
          for (i = 0;; i = this.zzjb.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjb, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjb = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 48: 
          j = zzsx.zzc(paramzzsm, 48);
          if (this.zzjc == null) {}
          for (i = 0;; i = this.zzjc.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjc, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjc = arrayOfInt;
          break;
        case 50: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjc == null) {}
          for (i = 0;; i = this.zzjc.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjc, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjc = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 56: 
          j = zzsx.zzc(paramzzsm, 56);
          if (this.zzjd == null) {}
          for (i = 0;; i = this.zzjd.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjd, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjd = arrayOfInt;
          break;
        case 58: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjd == null) {}
          for (i = 0;; i = this.zzjd.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjd, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjd = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 64: 
          j = zzsx.zzc(paramzzsm, 64);
          if (this.zzje == null) {}
          for (i = 0;; i = this.zzje.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzje, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzje = arrayOfInt;
          break;
        case 66: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzje == null) {}
          for (i = 0;; i = this.zzje.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzje, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzje = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 72: 
          j = zzsx.zzc(paramzzsm, 72);
          if (this.zzjf == null) {}
          for (i = 0;; i = this.zzjf.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjf, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjf = arrayOfInt;
          break;
        case 74: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjf == null) {}
          for (i = 0;; i = this.zzjf.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjf, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjf = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 80: 
          j = zzsx.zzc(paramzzsm, 80);
          if (this.zzjg == null) {}
          for (i = 0;; i = this.zzjg.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjg, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjg = arrayOfInt;
          break;
        case 82: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjg == null) {}
          for (i = 0;; i = this.zzjg.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjg, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjg = arrayOfInt;
          paramzzsm.zzmr(k);
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int k = super.zzz();
      int i;
      if ((this.zziX != null) && (this.zziX.length > 0))
      {
        i = 0;
        j = 0;
        while (i < this.zziX.length)
        {
          j += zzsn.zzmx(this.zziX[i]);
          i += 1;
        }
      }
      for (int j = k + j + this.zziX.length * 1;; j = k)
      {
        i = j;
        if (this.zziY != null)
        {
          i = j;
          if (this.zziY.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.zziY.length)
            {
              k += zzsn.zzmx(this.zziY[i]);
              i += 1;
            }
            i = j + k + this.zziY.length * 1;
          }
        }
        j = i;
        if (this.zziZ != null)
        {
          j = i;
          if (this.zziZ.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.zziZ.length)
            {
              k += zzsn.zzmx(this.zziZ[j]);
              j += 1;
            }
            j = i + k + this.zziZ.length * 1;
          }
        }
        i = j;
        if (this.zzja != null)
        {
          i = j;
          if (this.zzja.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.zzja.length)
            {
              k += zzsn.zzmx(this.zzja[i]);
              i += 1;
            }
            i = j + k + this.zzja.length * 1;
          }
        }
        j = i;
        if (this.zzjb != null)
        {
          j = i;
          if (this.zzjb.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.zzjb.length)
            {
              k += zzsn.zzmx(this.zzjb[j]);
              j += 1;
            }
            j = i + k + this.zzjb.length * 1;
          }
        }
        i = j;
        if (this.zzjc != null)
        {
          i = j;
          if (this.zzjc.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.zzjc.length)
            {
              k += zzsn.zzmx(this.zzjc[i]);
              i += 1;
            }
            i = j + k + this.zzjc.length * 1;
          }
        }
        j = i;
        if (this.zzjd != null)
        {
          j = i;
          if (this.zzjd.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.zzjd.length)
            {
              k += zzsn.zzmx(this.zzjd[j]);
              j += 1;
            }
            j = i + k + this.zzjd.length * 1;
          }
        }
        i = j;
        if (this.zzje != null)
        {
          i = j;
          if (this.zzje.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.zzje.length)
            {
              k += zzsn.zzmx(this.zzje[i]);
              i += 1;
            }
            i = j + k + this.zzje.length * 1;
          }
        }
        j = i;
        if (this.zzjf != null)
        {
          j = i;
          if (this.zzjf.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.zzjf.length)
            {
              k += zzsn.zzmx(this.zzjf[j]);
              j += 1;
            }
            j = i + k + this.zzjf.length * 1;
          }
        }
        i = j;
        if (this.zzjg != null)
        {
          i = j;
          if (this.zzjg.length > 0)
          {
            k = 0;
            i = m;
            while (i < this.zzjg.length)
            {
              k += zzsn.zzmx(this.zzjg[i]);
              i += 1;
            }
            i = j + k + this.zzjg.length * 1;
          }
        }
        return i;
      }
    }
  }
  
  public static final class zzh
    extends zzso<zzh>
  {
    public static final zzsp<zzag.zza, zzh> zzjh = zzsp.zza(11, zzh.class, 810L);
    private static final zzh[] zzji = new zzh[0];
    public int[] zzjj;
    public int[] zzjk;
    public int[] zzjl;
    public int zzjm;
    public int[] zzjn;
    public int zzjo;
    public int zzjp;
    
    public zzh()
    {
      zzM();
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
                      } while (!(paramObject instanceof zzh));
                      paramObject = (zzh)paramObject;
                      bool1 = bool2;
                    } while (!zzss.equals(this.zzjj, ((zzh)paramObject).zzjj));
                    bool1 = bool2;
                  } while (!zzss.equals(this.zzjk, ((zzh)paramObject).zzjk));
                  bool1 = bool2;
                } while (!zzss.equals(this.zzjl, ((zzh)paramObject).zzjl));
                bool1 = bool2;
              } while (this.zzjm != ((zzh)paramObject).zzjm);
              bool1 = bool2;
            } while (!zzss.equals(this.zzjn, ((zzh)paramObject).zzjn));
            bool1 = bool2;
          } while (this.zzjo != ((zzh)paramObject).zzjo);
          bool1 = bool2;
        } while (this.zzjp != ((zzh)paramObject).zzjp);
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label166;
        }
        if (((zzh)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zzh)paramObject).zzbuj.isEmpty());
      return true;
      label166:
      return this.zzbuj.equals(((zzh)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = zzss.hashCode(this.zzjj);
      int m = zzss.hashCode(this.zzjk);
      int n = zzss.hashCode(this.zzjl);
      int i1 = this.zzjm;
      int i2 = zzss.hashCode(this.zzjn);
      int i3 = this.zzjo;
      int i4 = this.zzjp;
      if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
      for (int i = 0;; i = this.zzbuj.hashCode()) {
        return i + ((((((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      int i;
      if ((this.zzjj != null) && (this.zzjj.length > 0))
      {
        i = 0;
        while (i < this.zzjj.length)
        {
          paramzzsn.zzA(1, this.zzjj[i]);
          i += 1;
        }
      }
      if ((this.zzjk != null) && (this.zzjk.length > 0))
      {
        i = 0;
        while (i < this.zzjk.length)
        {
          paramzzsn.zzA(2, this.zzjk[i]);
          i += 1;
        }
      }
      if ((this.zzjl != null) && (this.zzjl.length > 0))
      {
        i = 0;
        while (i < this.zzjl.length)
        {
          paramzzsn.zzA(3, this.zzjl[i]);
          i += 1;
        }
      }
      if (this.zzjm != 0) {
        paramzzsn.zzA(4, this.zzjm);
      }
      if ((this.zzjn != null) && (this.zzjn.length > 0))
      {
        i = j;
        while (i < this.zzjn.length)
        {
          paramzzsn.zzA(5, this.zzjn[i]);
          i += 1;
        }
      }
      if (this.zzjo != 0) {
        paramzzsn.zzA(6, this.zzjo);
      }
      if (this.zzjp != 0) {
        paramzzsn.zzA(7, this.zzjp);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzh zzM()
    {
      this.zzjj = zzsx.zzbuw;
      this.zzjk = zzsx.zzbuw;
      this.zzjl = zzsx.zzbuw;
      this.zzjm = 0;
      this.zzjn = zzsx.zzbuw;
      this.zzjo = 0;
      this.zzjp = 0;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzh zzh(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        int j;
        int[] arrayOfInt;
        int k;
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          j = zzsx.zzc(paramzzsm, 8);
          if (this.zzjj == null) {}
          for (i = 0;; i = this.zzjj.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjj, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjj = arrayOfInt;
          break;
        case 10: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjj == null) {}
          for (i = 0;; i = this.zzjj.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjj, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjj = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 16: 
          j = zzsx.zzc(paramzzsm, 16);
          if (this.zzjk == null) {}
          for (i = 0;; i = this.zzjk.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjk, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjk = arrayOfInt;
          break;
        case 18: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjk == null) {}
          for (i = 0;; i = this.zzjk.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjk, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjk = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 24: 
          j = zzsx.zzc(paramzzsm, 24);
          if (this.zzjl == null) {}
          for (i = 0;; i = this.zzjl.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjl, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjl = arrayOfInt;
          break;
        case 26: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjl == null) {}
          for (i = 0;; i = this.zzjl.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjl, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjl = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 32: 
          this.zzjm = paramzzsm.zzJb();
          break;
        case 40: 
          j = zzsx.zzc(paramzzsm, 40);
          if (this.zzjn == null) {}
          for (i = 0;; i = this.zzjn.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjn, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfInt[j] = paramzzsm.zzJb();
          this.zzjn = arrayOfInt;
          break;
        case 42: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzjn == null) {}
          for (i = 0;; i = this.zzjn.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjn, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzjn = arrayOfInt;
          paramzzsm.zzmr(k);
          break;
        case 48: 
          this.zzjo = paramzzsm.zzJb();
          break;
        case 56: 
          this.zzjp = paramzzsm.zzJb();
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int k = super.zzz();
      int i;
      if ((this.zzjj != null) && (this.zzjj.length > 0))
      {
        i = 0;
        j = 0;
        while (i < this.zzjj.length)
        {
          j += zzsn.zzmx(this.zzjj[i]);
          i += 1;
        }
      }
      for (int j = k + j + this.zzjj.length * 1;; j = k)
      {
        i = j;
        if (this.zzjk != null)
        {
          i = j;
          if (this.zzjk.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.zzjk.length)
            {
              k += zzsn.zzmx(this.zzjk[i]);
              i += 1;
            }
            i = j + k + this.zzjk.length * 1;
          }
        }
        j = i;
        if (this.zzjl != null)
        {
          j = i;
          if (this.zzjl.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.zzjl.length)
            {
              k += zzsn.zzmx(this.zzjl[j]);
              j += 1;
            }
            j = i + k + this.zzjl.length * 1;
          }
        }
        i = j;
        if (this.zzjm != 0) {
          i = j + zzsn.zzC(4, this.zzjm);
        }
        j = i;
        if (this.zzjn != null)
        {
          j = i;
          if (this.zzjn.length > 0)
          {
            k = 0;
            j = m;
            while (j < this.zzjn.length)
            {
              k += zzsn.zzmx(this.zzjn[j]);
              j += 1;
            }
            j = i + k + this.zzjn.length * 1;
          }
        }
        i = j;
        if (this.zzjo != 0) {
          i = j + zzsn.zzC(6, this.zzjo);
        }
        j = i;
        if (this.zzjp != 0) {
          j = i + zzsn.zzC(7, this.zzjp);
        }
        return j;
      }
    }
  }
  
  public static final class zzi
    extends zzso<zzi>
  {
    private static volatile zzi[] zzjq;
    public String name;
    public zzag.zza zzjr;
    public zzaf.zzd zzjs;
    
    public zzi()
    {
      zzO();
    }
    
    public static zzi[] zzN()
    {
      if (zzjq == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzjq == null) {
          zzjq = new zzi[0];
        }
        return zzjq;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label41:
      label57:
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
            } while (!(paramObject instanceof zzi));
            paramObject = (zzi)paramObject;
            if (this.name != null) {
              break;
            }
            bool1 = bool2;
          } while (((zzi)paramObject).name != null);
          if (this.zzjr != null) {
            break label127;
          }
          bool1 = bool2;
        } while (((zzi)paramObject).zzjr != null);
        if (this.zzjs != null) {
          break label143;
        }
        bool1 = bool2;
      } while (((zzi)paramObject).zzjs != null);
      for (;;)
      {
        if ((this.zzbuj == null) || (this.zzbuj.isEmpty()))
        {
          if (((zzi)paramObject).zzbuj != null)
          {
            bool1 = bool2;
            if (!((zzi)paramObject).zzbuj.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.name.equals(((zzi)paramObject).name)) {
            break label41;
          }
          return false;
          label127:
          if (this.zzjr.equals(((zzi)paramObject).zzjr)) {
            break label57;
          }
          return false;
          label143:
          if (!this.zzjs.equals(((zzi)paramObject).zzjs)) {
            return false;
          }
        }
      }
      return this.zzbuj.equals(((zzi)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      if (this.name == null)
      {
        i = 0;
        if (this.zzjr != null) {
          break label106;
        }
        j = 0;
        if (this.zzjs != null) {
          break label117;
        }
        k = 0;
        label42:
        m = n;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label128;
          }
        }
      }
      label106:
      label117:
      label128:
      for (int m = n;; m = this.zzbuj.hashCode())
      {
        return (k + (j + (i + (i1 + 527) * 31) * 31) * 31) * 31 + m;
        i = this.name.hashCode();
        break;
        j = this.zzjr.hashCode();
        break label33;
        k = this.zzjs.hashCode();
        break label42;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (!this.name.equals("")) {
        paramzzsn.zzn(1, this.name);
      }
      if (this.zzjr != null) {
        paramzzsn.zza(2, this.zzjr);
      }
      if (this.zzjs != null) {
        paramzzsn.zza(3, this.zzjs);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzi zzO()
    {
      this.name = "";
      this.zzjr = null;
      this.zzjs = null;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzi zzi(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.name = paramzzsm.readString();
          break;
        case 18: 
          if (this.zzjr == null) {
            this.zzjr = new zzag.zza();
          }
          paramzzsm.zza(this.zzjr);
          break;
        case 26: 
          if (this.zzjs == null) {
            this.zzjs = new zzaf.zzd();
          }
          paramzzsm.zza(this.zzjs);
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (!this.name.equals("")) {
        i = j + zzsn.zzo(1, this.name);
      }
      j = i;
      if (this.zzjr != null) {
        j = i + zzsn.zzc(2, this.zzjr);
      }
      i = j;
      if (this.zzjs != null) {
        i = j + zzsn.zzc(3, this.zzjs);
      }
      return i;
    }
  }
  
  public static final class zzj
    extends zzso<zzj>
  {
    public zzaf.zzi[] zzjt;
    public zzaf.zzf zzju;
    public String zzjv;
    
    public zzj()
    {
      zzP();
    }
    
    public static zzj zzd(byte[] paramArrayOfByte)
      throws zzst
    {
      return (zzj)zzsu.mergeFrom(new zzj(), paramArrayOfByte);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label57:
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
            } while (!(paramObject instanceof zzj));
            paramObject = (zzj)paramObject;
            bool1 = bool2;
          } while (!zzss.equals(this.zzjt, ((zzj)paramObject).zzjt));
          if (this.zzju != null) {
            break;
          }
          bool1 = bool2;
        } while (((zzj)paramObject).zzju != null);
        if (this.zzjv != null) {
          break label127;
        }
        bool1 = bool2;
      } while (((zzj)paramObject).zzjv != null);
      for (;;)
      {
        if ((this.zzbuj == null) || (this.zzbuj.isEmpty()))
        {
          if (((zzj)paramObject).zzbuj != null)
          {
            bool1 = bool2;
            if (!((zzj)paramObject).zzbuj.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.zzju.equals(((zzj)paramObject).zzju)) {
            break label57;
          }
          return false;
          label127:
          if (!this.zzjv.equals(((zzj)paramObject).zzjv)) {
            return false;
          }
        }
      }
      return this.zzbuj.equals(((zzj)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i1 = zzss.hashCode(this.zzjt);
      int i;
      int j;
      if (this.zzju == null)
      {
        i = 0;
        if (this.zzjv != null) {
          break label104;
        }
        j = 0;
        label42:
        k = m;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label115;
          }
        }
      }
      label104:
      label115:
      for (int k = m;; k = this.zzbuj.hashCode())
      {
        return (j + (i + ((n + 527) * 31 + i1) * 31) * 31) * 31 + k;
        i = this.zzju.hashCode();
        break;
        j = this.zzjv.hashCode();
        break label42;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if ((this.zzjt != null) && (this.zzjt.length > 0))
      {
        int i = 0;
        while (i < this.zzjt.length)
        {
          zzaf.zzi localzzi = this.zzjt[i];
          if (localzzi != null) {
            paramzzsn.zza(1, localzzi);
          }
          i += 1;
        }
      }
      if (this.zzju != null) {
        paramzzsn.zza(2, this.zzju);
      }
      if (!this.zzjv.equals("")) {
        paramzzsn.zzn(3, this.zzjv);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzj zzP()
    {
      this.zzjt = zzaf.zzi.zzN();
      this.zzju = null;
      this.zzjv = "";
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzj zzj(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          int j = zzsx.zzc(paramzzsm, 10);
          if (this.zzjt == null) {}
          zzaf.zzi[] arrayOfzzi;
          for (i = 0;; i = this.zzjt.length)
          {
            arrayOfzzi = new zzaf.zzi[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjt, 0, arrayOfzzi, 0, i);
              j = i;
            }
            while (j < arrayOfzzi.length - 1)
            {
              arrayOfzzi[j] = new zzaf.zzi();
              paramzzsm.zza(arrayOfzzi[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfzzi[j] = new zzaf.zzi();
          paramzzsm.zza(arrayOfzzi[j]);
          this.zzjt = arrayOfzzi;
          break;
        case 18: 
          if (this.zzju == null) {
            this.zzju = new zzaf.zzf();
          }
          paramzzsm.zza(this.zzju);
          break;
        case 26: 
          this.zzjv = paramzzsm.readString();
        }
      }
    }
    
    protected int zzz()
    {
      int i = super.zzz();
      int j = i;
      if (this.zzjt != null)
      {
        j = i;
        if (this.zzjt.length > 0)
        {
          int k = 0;
          for (;;)
          {
            j = i;
            if (k >= this.zzjt.length) {
              break;
            }
            zzaf.zzi localzzi = this.zzjt[k];
            j = i;
            if (localzzi != null) {
              j = i + zzsn.zzc(1, localzzi);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.zzju != null) {
        i = j + zzsn.zzc(2, this.zzju);
      }
      j = i;
      if (!this.zzjv.equals("")) {
        j = i + zzsn.zzo(3, this.zzjv);
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */