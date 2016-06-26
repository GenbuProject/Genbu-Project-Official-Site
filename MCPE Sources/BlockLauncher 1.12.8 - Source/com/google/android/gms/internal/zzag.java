package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzag
{
  public static final class zza
    extends zzso<zza>
  {
    private static volatile zza[] zzjw;
    public int type;
    public zza[] zzjA;
    public String zzjB;
    public String zzjC;
    public long zzjD;
    public boolean zzjE;
    public zza[] zzjF;
    public int[] zzjG;
    public boolean zzjH;
    public String zzjx;
    public zza[] zzjy;
    public zza[] zzjz;
    
    public zza()
    {
      zzR();
    }
    
    public static zza[] zzQ()
    {
      if (zzjw == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzjw == null) {
          zzjw = new zza[0];
        }
        return zzjw;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label54:
      label118:
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
                    } while (!(paramObject instanceof zza));
                    paramObject = (zza)paramObject;
                    bool1 = bool2;
                  } while (this.type != ((zza)paramObject).type);
                  if (this.zzjx != null) {
                    break;
                  }
                  bool1 = bool2;
                } while (((zza)paramObject).zzjx != null);
                bool1 = bool2;
              } while (!zzss.equals(this.zzjy, ((zza)paramObject).zzjy));
              bool1 = bool2;
            } while (!zzss.equals(this.zzjz, ((zza)paramObject).zzjz));
            bool1 = bool2;
          } while (!zzss.equals(this.zzjA, ((zza)paramObject).zzjA));
          if (this.zzjB != null) {
            break label260;
          }
          bool1 = bool2;
        } while (((zza)paramObject).zzjB != null);
        if (this.zzjC != null) {
          break label276;
        }
        bool1 = bool2;
      } while (((zza)paramObject).zzjC != null);
      label260:
      label276:
      while (this.zzjC.equals(((zza)paramObject).zzjC))
      {
        bool1 = bool2;
        if (this.zzjD != ((zza)paramObject).zzjD) {
          break;
        }
        bool1 = bool2;
        if (this.zzjE != ((zza)paramObject).zzjE) {
          break;
        }
        bool1 = bool2;
        if (!zzss.equals(this.zzjF, ((zza)paramObject).zzjF)) {
          break;
        }
        bool1 = bool2;
        if (!zzss.equals(this.zzjG, ((zza)paramObject).zzjG)) {
          break;
        }
        bool1 = bool2;
        if (this.zzjH != ((zza)paramObject).zzjH) {
          break;
        }
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label292;
        }
        if (((zza)paramObject).zzbuj != null)
        {
          bool1 = bool2;
          if (!((zza)paramObject).zzbuj.isEmpty()) {
            break;
          }
        }
        return true;
        if (this.zzjx.equals(((zza)paramObject).zzjx)) {
          break label54;
        }
        return false;
        if (this.zzjB.equals(((zza)paramObject).zzjB)) {
          break label118;
        }
        return false;
      }
      return false;
      label292:
      return this.zzbuj.equals(((zza)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int n = 1231;
      int i2 = 0;
      int i3 = getClass().getName().hashCode();
      int i4 = this.type;
      int i;
      int i5;
      int i6;
      int i7;
      int j;
      label71:
      int k;
      label80:
      int i8;
      int m;
      label107:
      int i9;
      int i10;
      if (this.zzjx == null)
      {
        i = 0;
        i5 = zzss.hashCode(this.zzjy);
        i6 = zzss.hashCode(this.zzjz);
        i7 = zzss.hashCode(this.zzjA);
        if (this.zzjB != null) {
          break label250;
        }
        j = 0;
        if (this.zzjC != null) {
          break label261;
        }
        k = 0;
        i8 = (int)(this.zzjD ^ this.zzjD >>> 32);
        if (!this.zzjE) {
          break label272;
        }
        m = 1231;
        i9 = zzss.hashCode(this.zzjF);
        i10 = zzss.hashCode(this.zzjG);
        if (!this.zzjH) {
          break label280;
        }
        label132:
        i1 = i2;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label288;
          }
        }
      }
      label250:
      label261:
      label272:
      label280:
      label288:
      for (int i1 = i2;; i1 = this.zzbuj.hashCode())
      {
        return ((((m + ((k + (j + ((((i + ((i3 + 527) * 31 + i4) * 31) * 31 + i5) * 31 + i6) * 31 + i7) * 31) * 31) * 31 + i8) * 31) * 31 + i9) * 31 + i10) * 31 + n) * 31 + i1;
        i = this.zzjx.hashCode();
        break;
        j = this.zzjB.hashCode();
        break label71;
        k = this.zzjC.hashCode();
        break label80;
        m = 1237;
        break label107;
        n = 1237;
        break label132;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      paramzzsn.zzA(1, this.type);
      if (!this.zzjx.equals("")) {
        paramzzsn.zzn(2, this.zzjx);
      }
      int i;
      zza localzza;
      if ((this.zzjy != null) && (this.zzjy.length > 0))
      {
        i = 0;
        while (i < this.zzjy.length)
        {
          localzza = this.zzjy[i];
          if (localzza != null) {
            paramzzsn.zza(3, localzza);
          }
          i += 1;
        }
      }
      if ((this.zzjz != null) && (this.zzjz.length > 0))
      {
        i = 0;
        while (i < this.zzjz.length)
        {
          localzza = this.zzjz[i];
          if (localzza != null) {
            paramzzsn.zza(4, localzza);
          }
          i += 1;
        }
      }
      if ((this.zzjA != null) && (this.zzjA.length > 0))
      {
        i = 0;
        while (i < this.zzjA.length)
        {
          localzza = this.zzjA[i];
          if (localzza != null) {
            paramzzsn.zza(5, localzza);
          }
          i += 1;
        }
      }
      if (!this.zzjB.equals("")) {
        paramzzsn.zzn(6, this.zzjB);
      }
      if (!this.zzjC.equals("")) {
        paramzzsn.zzn(7, this.zzjC);
      }
      if (this.zzjD != 0L) {
        paramzzsn.zzb(8, this.zzjD);
      }
      if (this.zzjH) {
        paramzzsn.zze(9, this.zzjH);
      }
      if ((this.zzjG != null) && (this.zzjG.length > 0))
      {
        i = 0;
        while (i < this.zzjG.length)
        {
          paramzzsn.zzA(10, this.zzjG[i]);
          i += 1;
        }
      }
      if ((this.zzjF != null) && (this.zzjF.length > 0))
      {
        i = j;
        while (i < this.zzjF.length)
        {
          localzza = this.zzjF[i];
          if (localzza != null) {
            paramzzsn.zza(11, localzza);
          }
          i += 1;
        }
      }
      if (this.zzjE) {
        paramzzsn.zze(12, this.zzjE);
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzR()
    {
      this.type = 1;
      this.zzjx = "";
      this.zzjy = zzQ();
      this.zzjz = zzQ();
      this.zzjA = zzQ();
      this.zzjB = "";
      this.zzjC = "";
      this.zzjD = 0L;
      this.zzjE = false;
      this.zzjF = zzQ();
      this.zzjG = zzsx.zzbuw;
      this.zzjH = false;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zza zzk(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        int j;
        Object localObject;
        int k;
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
          case 4: 
          case 5: 
          case 6: 
          case 7: 
          case 8: 
            this.type = i;
          }
          break;
        case 18: 
          this.zzjx = paramzzsm.readString();
          break;
        case 26: 
          j = zzsx.zzc(paramzzsm, 26);
          if (this.zzjy == null) {}
          for (i = 0;; i = this.zzjy.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjy, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzsm.zza(localObject[j]);
          this.zzjy = ((zza[])localObject);
          break;
        case 34: 
          j = zzsx.zzc(paramzzsm, 34);
          if (this.zzjz == null) {}
          for (i = 0;; i = this.zzjz.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjz, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzsm.zza(localObject[j]);
          this.zzjz = ((zza[])localObject);
          break;
        case 42: 
          j = zzsx.zzc(paramzzsm, 42);
          if (this.zzjA == null) {}
          for (i = 0;; i = this.zzjA.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjA, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzsm.zza(localObject[j]);
          this.zzjA = ((zza[])localObject);
          break;
        case 50: 
          this.zzjB = paramzzsm.readString();
          break;
        case 58: 
          this.zzjC = paramzzsm.readString();
          break;
        case 64: 
          this.zzjD = paramzzsm.zzJa();
          break;
        case 72: 
          this.zzjH = paramzzsm.zzJc();
          break;
        case 80: 
          int m = zzsx.zzc(paramzzsm, 80);
          localObject = new int[m];
          j = 0;
          i = 0;
          if (j < m)
          {
            if (j != 0) {
              paramzzsm.zzIX();
            }
            int n = paramzzsm.zzJb();
            switch (n)
            {
            }
            for (;;)
            {
              j += 1;
              break;
              k = i + 1;
              localObject[i] = n;
              i = k;
            }
          }
          if (i != 0)
          {
            if (this.zzjG == null) {}
            for (j = 0;; j = this.zzjG.length)
            {
              if ((j != 0) || (i != localObject.length)) {
                break label810;
              }
              this.zzjG = ((int[])localObject);
              break;
            }
            int[] arrayOfInt = new int[j + i];
            if (j != 0) {
              System.arraycopy(this.zzjG, 0, arrayOfInt, 0, j);
            }
            System.arraycopy(localObject, 0, arrayOfInt, j, i);
            this.zzjG = arrayOfInt;
          }
          break;
        case 82: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0) {
            switch (paramzzsm.zzJb())
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
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
              j += 1;
            }
          }
          if (j != 0)
          {
            paramzzsm.zzms(i);
            if (this.zzjG == null) {}
            for (i = 0;; i = this.zzjG.length)
            {
              localObject = new int[j + i];
              j = i;
              if (i != 0)
              {
                System.arraycopy(this.zzjG, 0, localObject, 0, i);
                j = i;
              }
              while (paramzzsm.zzJk() > 0)
              {
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
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 17: 
                  localObject[j] = i;
                  j += 1;
                }
              }
            }
            this.zzjG = ((int[])localObject);
          }
          paramzzsm.zzmr(k);
          break;
        case 90: 
          j = zzsx.zzc(paramzzsm, 90);
          if (this.zzjF == null) {}
          for (i = 0;; i = this.zzjF.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzjF, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzsm.zza(localObject[j]);
          this.zzjF = ((zza[])localObject);
          break;
        case 96: 
          label810:
          this.zzjE = paramzzsm.zzJc();
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int j = super.zzz() + zzsn.zzC(1, this.type);
      int i = j;
      if (!this.zzjx.equals("")) {
        i = j + zzsn.zzo(2, this.zzjx);
      }
      j = i;
      zza localzza;
      int k;
      if (this.zzjy != null)
      {
        j = i;
        if (this.zzjy.length > 0)
        {
          j = 0;
          while (j < this.zzjy.length)
          {
            localzza = this.zzjy[j];
            k = i;
            if (localzza != null) {
              k = i + zzsn.zzc(3, localzza);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zzjz != null)
      {
        i = j;
        if (this.zzjz.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zzjz.length)
          {
            localzza = this.zzjz[j];
            k = i;
            if (localzza != null) {
              k = i + zzsn.zzc(4, localzza);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (this.zzjA != null)
      {
        j = i;
        if (this.zzjA.length > 0)
        {
          j = 0;
          while (j < this.zzjA.length)
          {
            localzza = this.zzjA[j];
            k = i;
            if (localzza != null) {
              k = i + zzsn.zzc(5, localzza);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (!this.zzjB.equals("")) {
        i = j + zzsn.zzo(6, this.zzjB);
      }
      j = i;
      if (!this.zzjC.equals("")) {
        j = i + zzsn.zzo(7, this.zzjC);
      }
      i = j;
      if (this.zzjD != 0L) {
        i = j + zzsn.zzd(8, this.zzjD);
      }
      j = i;
      if (this.zzjH) {
        j = i + zzsn.zzf(9, this.zzjH);
      }
      i = j;
      if (this.zzjG != null)
      {
        i = j;
        if (this.zzjG.length > 0)
        {
          i = 0;
          k = 0;
          while (i < this.zzjG.length)
          {
            k += zzsn.zzmx(this.zzjG[i]);
            i += 1;
          }
          i = j + k + this.zzjG.length * 1;
        }
      }
      j = i;
      if (this.zzjF != null)
      {
        j = i;
        if (this.zzjF.length > 0)
        {
          k = m;
          for (;;)
          {
            j = i;
            if (k >= this.zzjF.length) {
              break;
            }
            localzza = this.zzjF[k];
            j = i;
            if (localzza != null) {
              j = i + zzsn.zzc(11, localzza);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.zzjE) {
        i = j + zzsn.zzf(12, this.zzjE);
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */