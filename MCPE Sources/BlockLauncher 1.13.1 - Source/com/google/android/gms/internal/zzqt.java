package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;

@Deprecated
public class zzqt
  implements zzqu.zza
{
  private final zzqu zzbdw;
  private boolean zzbdx;
  
  public zzqt(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, null);
  }
  
  public zzqt(Context paramContext, int paramInt, String paramString)
  {
    this(paramContext, paramInt, paramString, null, true);
  }
  
  public zzqt(Context paramContext, int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramContext != paramContext.getApplicationContext()) {}
    for (String str = paramContext.getClass().getName();; str = "OneTimePlayLogger")
    {
      this.zzbdw = new zzqu(paramContext, paramInt, paramString1, paramString2, this, paramBoolean, str);
      this.zzbdx = true;
      return;
    }
  }
  
  private void zzER()
  {
    if (!this.zzbdx) {
      throw new IllegalStateException("Cannot reuse one-time logger after sending.");
    }
  }
  
  public void send()
  {
    zzER();
    this.zzbdw.start();
    this.zzbdx = false;
  }
  
  public void zzES()
  {
    this.zzbdw.stop();
  }
  
  public void zzET()
  {
    Log.w("OneTimePlayLogger", "logger connection failed");
  }
  
  public void zza(String paramString, byte[] paramArrayOfByte, String... paramVarArgs)
  {
    zzER();
    this.zzbdw.zzb(paramString, paramArrayOfByte, paramVarArgs);
  }
  
  public void zzc(PendingIntent paramPendingIntent)
  {
    Log.w("OneTimePlayLogger", "logger connection failed: " + paramPendingIntent);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzqt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */