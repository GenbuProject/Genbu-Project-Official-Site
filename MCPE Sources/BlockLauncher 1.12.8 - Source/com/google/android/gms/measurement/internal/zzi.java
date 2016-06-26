package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzx;

class zzi
{
  final String mName;
  final String zzaUa;
  final long zzaVP;
  final long zzaVQ;
  final long zzaVR;
  
  zzi(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3)
  {
    zzx.zzcM(paramString1);
    zzx.zzcM(paramString2);
    if (paramLong1 >= 0L)
    {
      bool1 = true;
      zzx.zzac(bool1);
      if (paramLong2 < 0L) {
        break label81;
      }
    }
    label81:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zzac(bool1);
      this.zzaUa = paramString1;
      this.mName = paramString2;
      this.zzaVP = paramLong1;
      this.zzaVQ = paramLong2;
      this.zzaVR = paramLong3;
      return;
      bool1 = false;
      break;
    }
  }
  
  zzi zzCB()
  {
    return new zzi(this.zzaUa, this.mName, this.zzaVP + 1L, this.zzaVQ + 1L, this.zzaVR);
  }
  
  zzi zzab(long paramLong)
  {
    return new zzi(this.zzaUa, this.mName, this.zzaVP, this.zzaVQ, paramLong);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */