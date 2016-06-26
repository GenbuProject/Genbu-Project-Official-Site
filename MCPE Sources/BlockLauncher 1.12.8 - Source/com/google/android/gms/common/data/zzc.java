package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public abstract class zzc
{
  protected final DataHolder zzahi;
  protected int zzaje;
  private int zzajf;
  
  public zzc(DataHolder paramDataHolder, int paramInt)
  {
    this.zzahi = ((DataHolder)zzx.zzz(paramDataHolder));
    zzbF(paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof zzc))
    {
      paramObject = (zzc)paramObject;
      bool1 = bool2;
      if (zzw.equal(Integer.valueOf(((zzc)paramObject).zzaje), Integer.valueOf(this.zzaje)))
      {
        bool1 = bool2;
        if (zzw.equal(Integer.valueOf(((zzc)paramObject).zzajf), Integer.valueOf(this.zzajf)))
        {
          bool1 = bool2;
          if (((zzc)paramObject).zzahi == this.zzahi) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  protected boolean getBoolean(String paramString)
  {
    return this.zzahi.zze(paramString, this.zzaje, this.zzajf);
  }
  
  protected byte[] getByteArray(String paramString)
  {
    return this.zzahi.zzg(paramString, this.zzaje, this.zzajf);
  }
  
  protected float getFloat(String paramString)
  {
    return this.zzahi.zzf(paramString, this.zzaje, this.zzajf);
  }
  
  protected int getInteger(String paramString)
  {
    return this.zzahi.zzc(paramString, this.zzaje, this.zzajf);
  }
  
  protected long getLong(String paramString)
  {
    return this.zzahi.zzb(paramString, this.zzaje, this.zzajf);
  }
  
  protected String getString(String paramString)
  {
    return this.zzahi.zzd(paramString, this.zzaje, this.zzajf);
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzaje), Integer.valueOf(this.zzajf), this.zzahi });
  }
  
  public boolean isDataValid()
  {
    return !this.zzahi.isClosed();
  }
  
  protected void zza(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    this.zzahi.zza(paramString, this.zzaje, this.zzajf, paramCharArrayBuffer);
  }
  
  protected void zzbF(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.zzahi.getCount())) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzab(bool);
      this.zzaje = paramInt;
      this.zzajf = this.zzahi.zzbH(this.zzaje);
      return;
    }
  }
  
  protected Uri zzcA(String paramString)
  {
    return this.zzahi.zzh(paramString, this.zzaje, this.zzajf);
  }
  
  protected boolean zzcB(String paramString)
  {
    return this.zzahi.zzi(paramString, this.zzaje, this.zzajf);
  }
  
  public boolean zzcz(String paramString)
  {
    return this.zzahi.zzcz(paramString);
  }
  
  protected int zzqc()
  {
    return this.zzaje;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\data\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */