package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.measurement.zzg;

public class zzc
{
  private final zzf zzQj;
  
  protected zzc(zzf paramzzf)
  {
    zzx.zzz(paramzzf);
    this.zzQj = paramzzf;
  }
  
  private void zza(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject = null;
    if (this.zzQj != null) {
      localObject = this.zzQj.zzjy();
    }
    if (localObject != null) {
      ((zzaf)localObject).zza(paramInt, paramString, paramObject1, paramObject2, paramObject3);
    }
    do
    {
      return;
      localObject = (String)zzy.zzRL.get();
    } while (!Log.isLoggable((String)localObject, paramInt));
    Log.println(paramInt, (String)localObject, zzc(paramString, paramObject1, paramObject2, paramObject3));
  }
  
  protected static String zzc(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    String str1 = paramString;
    if (paramString == null) {
      str1 = "";
    }
    String str2 = zzj(paramObject1);
    paramObject2 = zzj(paramObject2);
    paramObject3 = zzj(paramObject3);
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = "";
    if (!TextUtils.isEmpty(str1))
    {
      localStringBuilder.append(str1);
      paramString = ": ";
    }
    paramObject1 = paramString;
    if (!TextUtils.isEmpty(str2))
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append(str2);
      paramObject1 = ", ";
    }
    paramString = (String)paramObject1;
    if (!TextUtils.isEmpty((CharSequence)paramObject2))
    {
      localStringBuilder.append((String)paramObject1);
      localStringBuilder.append((String)paramObject2);
      paramString = ", ";
    }
    if (!TextUtils.isEmpty((CharSequence)paramObject3))
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append((String)paramObject3);
    }
    return localStringBuilder.toString();
  }
  
  private static String zzj(Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if ((paramObject instanceof Boolean))
    {
      if (paramObject == Boolean.TRUE) {}
      for (paramObject = "true";; paramObject = "false") {
        return (String)paramObject;
      }
    }
    if ((paramObject instanceof Throwable)) {
      return ((Throwable)paramObject).toString();
    }
    return paramObject.toString();
  }
  
  protected Context getContext()
  {
    return this.zzQj.getContext();
  }
  
  public void zza(String paramString, Object paramObject)
  {
    zza(2, paramString, paramObject, null, null);
  }
  
  public void zza(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(2, paramString, paramObject1, paramObject2, null);
  }
  
  public void zza(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    zza(3, paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public void zzb(String paramString, Object paramObject)
  {
    zza(3, paramString, paramObject, null, null);
  }
  
  public void zzb(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(3, paramString, paramObject1, paramObject2, null);
  }
  
  public void zzb(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    zza(5, paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public void zzbd(String paramString)
  {
    zza(2, paramString, null, null, null);
  }
  
  public void zzbe(String paramString)
  {
    zza(3, paramString, null, null, null);
  }
  
  public void zzbf(String paramString)
  {
    zza(4, paramString, null, null, null);
  }
  
  public void zzbg(String paramString)
  {
    zza(5, paramString, null, null, null);
  }
  
  public void zzbh(String paramString)
  {
    zza(6, paramString, null, null, null);
  }
  
  public void zzc(String paramString, Object paramObject)
  {
    zza(4, paramString, paramObject, null, null);
  }
  
  public void zzc(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(5, paramString, paramObject1, paramObject2, null);
  }
  
  public void zzd(String paramString, Object paramObject)
  {
    zza(5, paramString, paramObject, null, null);
  }
  
  public void zzd(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(6, paramString, paramObject1, paramObject2, null);
  }
  
  public void zze(String paramString, Object paramObject)
  {
    zza(6, paramString, paramObject, null, null);
  }
  
  public boolean zzhp()
  {
    return Log.isLoggable((String)zzy.zzRL.get(), 2);
  }
  
  public GoogleAnalytics zziC()
  {
    return this.zzQj.zzjz();
  }
  
  protected zzb zziH()
  {
    return this.zzQj.zziH();
  }
  
  protected zzan zziI()
  {
    return this.zzQj.zziI();
  }
  
  public zzf zzji()
  {
    return this.zzQj;
  }
  
  protected void zzjj()
  {
    if (zzjn().zzkr()) {
      throw new IllegalStateException("Call only supported on the client side");
    }
  }
  
  protected void zzjk()
  {
    this.zzQj.zzjk();
  }
  
  protected zzmq zzjl()
  {
    return this.zzQj.zzjl();
  }
  
  protected zzaf zzjm()
  {
    return this.zzQj.zzjm();
  }
  
  protected zzr zzjn()
  {
    return this.zzQj.zzjn();
  }
  
  protected zzg zzjo()
  {
    return this.zzQj.zzjo();
  }
  
  protected zzv zzjp()
  {
    return this.zzQj.zzjp();
  }
  
  protected zzai zzjq()
  {
    return this.zzQj.zzjq();
  }
  
  protected zzn zzjr()
  {
    return this.zzQj.zzjC();
  }
  
  protected zza zzjs()
  {
    return this.zzQj.zzjB();
  }
  
  protected zzk zzjt()
  {
    return this.zzQj.zzjt();
  }
  
  protected zzu zzju()
  {
    return this.zzQj.zzju();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */