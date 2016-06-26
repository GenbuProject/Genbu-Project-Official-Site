package com.google.android.gms.cast.internal;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.IOException;

public abstract class zzd
{
  protected final zzl zzadu;
  private final String zzadv;
  private zzn zzadw;
  
  protected zzd(String paramString1, String paramString2, String paramString3)
  {
    zzf.zzch(paramString1);
    this.zzadv = paramString1;
    this.zzadu = new zzl(paramString2);
    setSessionLabel(paramString3);
  }
  
  public final String getNamespace()
  {
    return this.zzadv;
  }
  
  public void setSessionLabel(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      this.zzadu.zzcn(paramString);
    }
  }
  
  public final void zza(zzn paramzzn)
  {
    this.zzadw = paramzzn;
    if (this.zzadw == null) {
      zzof();
    }
  }
  
  protected final void zza(String paramString1, long paramLong, String paramString2)
    throws IOException
  {
    this.zzadu.zza("Sending text message: %s to: %s", new Object[] { paramString1, paramString2 });
    this.zzadw.zza(this.zzadv, paramString1, paramLong, paramString2);
  }
  
  public void zzb(long paramLong, int paramInt) {}
  
  public void zzcf(@NonNull String paramString) {}
  
  public void zzof() {}
  
  protected final long zzog()
  {
    return this.zzadw.zznQ();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */