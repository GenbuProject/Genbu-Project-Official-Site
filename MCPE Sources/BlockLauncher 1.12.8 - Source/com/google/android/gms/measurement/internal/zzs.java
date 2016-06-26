package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzpz.zzd;

class zzs
{
  final boolean zzaWY;
  final int zzaWZ;
  long zzaXa;
  float zzaXb;
  long zzaXc;
  float zzaXd;
  long zzaXe;
  float zzaXf;
  final boolean zzaXg;
  
  public zzs(zzpz.zzd paramzzd)
  {
    zzx.zzz(paramzzd);
    boolean bool1;
    if ((paramzzd.zzaZF == null) || (paramzzd.zzaZF.intValue() == 0)) {
      bool1 = false;
    }
    for (;;)
    {
      if (bool1)
      {
        this.zzaWZ = paramzzd.zzaZF.intValue();
        if ((paramzzd.zzaZG != null) && (paramzzd.zzaZG.booleanValue()))
        {
          label62:
          this.zzaWY = bool2;
          if (paramzzd.zzaZF.intValue() != 4) {
            break label185;
          }
          if (!this.zzaWY) {
            break label160;
          }
          this.zzaXd = Float.parseFloat(paramzzd.zzaZI);
          this.zzaXf = Float.parseFloat(paramzzd.zzaZJ);
        }
      }
      for (;;)
      {
        this.zzaXg = bool1;
        return;
        if (paramzzd.zzaZF.intValue() != 4)
        {
          if (paramzzd.zzaZH != null) {
            break label233;
          }
          bool1 = false;
          break;
        }
        if ((paramzzd.zzaZI != null) && (paramzzd.zzaZJ != null)) {
          break label233;
        }
        bool1 = false;
        break;
        bool2 = false;
        break label62;
        label160:
        this.zzaXc = Long.parseLong(paramzzd.zzaZI);
        this.zzaXe = Long.parseLong(paramzzd.zzaZJ);
        continue;
        label185:
        if (this.zzaWY)
        {
          this.zzaXb = Float.parseFloat(paramzzd.zzaZH);
        }
        else
        {
          this.zzaXa = Long.parseLong(paramzzd.zzaZH);
          continue;
          this.zzaWZ = 0;
          this.zzaWY = false;
        }
      }
      label233:
      bool1 = true;
    }
  }
  
  public Boolean zzac(long paramLong)
  {
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool4 = true;
    boolean bool1 = true;
    if (!this.zzaXg) {
      return null;
    }
    if (this.zzaWY) {
      return null;
    }
    switch (this.zzaWZ)
    {
    default: 
      return null;
    case 1: 
      if (paramLong < this.zzaXa) {}
      for (;;)
      {
        return Boolean.valueOf(bool1);
        bool1 = false;
      }
    case 2: 
      if (paramLong > this.zzaXa) {}
      for (bool1 = bool2;; bool1 = false) {
        return Boolean.valueOf(bool1);
      }
    case 3: 
      if (paramLong == this.zzaXa) {}
      for (bool1 = bool3;; bool1 = false) {
        return Boolean.valueOf(bool1);
      }
    }
    if ((paramLong >= this.zzaXc) && (paramLong <= this.zzaXe)) {}
    for (bool1 = bool4;; bool1 = false) {
      return Boolean.valueOf(bool1);
    }
  }
  
  public Boolean zzi(float paramFloat)
  {
    boolean bool3 = true;
    boolean bool4 = true;
    boolean bool1 = true;
    boolean bool2 = false;
    if (!this.zzaXg) {
      return null;
    }
    if (!this.zzaWY) {
      return null;
    }
    switch (this.zzaWZ)
    {
    default: 
      return null;
    case 1: 
      if (paramFloat < this.zzaXb) {}
      for (;;)
      {
        return Boolean.valueOf(bool1);
        bool1 = false;
      }
    case 2: 
      if (paramFloat > this.zzaXb) {}
      for (bool1 = bool3;; bool1 = false) {
        return Boolean.valueOf(bool1);
      }
    case 3: 
      if (paramFloat != this.zzaXb)
      {
        bool1 = bool2;
        if (Math.abs(paramFloat - this.zzaXb) >= 2.0F * Math.max(Math.ulp(paramFloat), Math.ulp(this.zzaXb))) {}
      }
      else
      {
        bool1 = true;
      }
      return Boolean.valueOf(bool1);
    }
    if ((paramFloat >= this.zzaXd) && (paramFloat <= this.zzaXf)) {}
    for (bool1 = bool4;; bool1 = false) {
      return Boolean.valueOf(bool1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */