package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.measurement.AppMeasurement;

public class zzp
  extends zzz
{
  private final long zzaVj = zzCp().zzBp();
  private final char zzaWB;
  private final zza zzaWC;
  private final zza zzaWD;
  private final zza zzaWE;
  private final zza zzaWF;
  private final zza zzaWG;
  private final zza zzaWH;
  private final zza zzaWI;
  private final zza zzaWJ;
  private final zza zzaWK;
  private final String zzamn = zzCp().zzBz();
  
  zzp(zzw paramzzw)
  {
    super(paramzzw);
    if (zzCp().zzks())
    {
      if (zzCp().zzkr()) {}
      for (c = 'P';; c = 'C')
      {
        this.zzaWB = c;
        this.zzaWC = new zza(6, false, false);
        this.zzaWD = new zza(6, true, false);
        this.zzaWE = new zza(6, false, true);
        this.zzaWF = new zza(5, false, false);
        this.zzaWG = new zza(5, true, false);
        this.zzaWH = new zza(5, false, true);
        this.zzaWI = new zza(4, false, false);
        this.zzaWJ = new zza(3, false, false);
        this.zzaWK = new zza(2, false, false);
        return;
      }
    }
    if (zzCp().zzkr()) {}
    for (char c = 'p';; c = 'c')
    {
      this.zzaWB = c;
      break;
    }
  }
  
  static String zza(boolean paramBoolean, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    String str1 = paramString;
    if (paramString == null) {
      str1 = "";
    }
    String str2 = zzc(paramBoolean, paramObject1);
    paramObject2 = zzc(paramBoolean, paramObject2);
    paramObject3 = zzc(paramBoolean, paramObject3);
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
  
  static String zzc(boolean paramBoolean, Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    if ((paramObject instanceof Integer)) {
      paramObject = Long.valueOf(((Integer)paramObject).intValue());
    }
    for (;;)
    {
      String str1;
      if ((paramObject instanceof Long))
      {
        if (!paramBoolean) {
          return String.valueOf(paramObject);
        }
        if (Math.abs(((Long)paramObject).longValue()) < 100L) {
          return String.valueOf(paramObject);
        }
        if (String.valueOf(paramObject).charAt(0) == '-') {}
        for (str1 = "-";; str1 = "")
        {
          paramObject = String.valueOf(Math.abs(((Long)paramObject).longValue()));
          return str1 + Math.round(Math.pow(10.0D, ((String)paramObject).length() - 1)) + "..." + str1 + Math.round(Math.pow(10.0D, ((String)paramObject).length()) - 1.0D);
        }
      }
      if ((paramObject instanceof Boolean)) {
        return String.valueOf(paramObject);
      }
      if ((paramObject instanceof Throwable))
      {
        Object localObject1 = (Throwable)paramObject;
        paramObject = new StringBuilder(((Throwable)localObject1).toString());
        str1 = zzff(AppMeasurement.class.getCanonicalName());
        String str2 = zzff(zzw.class.getCanonicalName());
        localObject1 = ((Throwable)localObject1).getStackTrace();
        int j = localObject1.length;
        int i = 0;
        if (i < j)
        {
          Object localObject2 = localObject1[i];
          if (((StackTraceElement)localObject2).isNativeMethod()) {}
          String str3;
          do
          {
            do
            {
              i += 1;
              break;
              str3 = ((StackTraceElement)localObject2).getClassName();
            } while (str3 == null);
            str3 = zzff(str3);
          } while ((!str3.equals(str1)) && (!str3.equals(str2)));
          ((StringBuilder)paramObject).append(": ");
          ((StringBuilder)paramObject).append(localObject2);
        }
        return ((StringBuilder)paramObject).toString();
      }
      if (paramBoolean) {
        return "-";
      }
      return String.valueOf(paramObject);
    }
  }
  
  private static String zzff(String paramString)
  {
    String str;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    int i;
    do
    {
      return str;
      i = paramString.lastIndexOf('.');
      str = paramString;
    } while (i == -1);
    return paramString.substring(0, i);
  }
  
  public zza zzCE()
  {
    return this.zzaWC;
  }
  
  public zza zzCF()
  {
    return this.zzaWF;
  }
  
  public zza zzCG()
  {
    return this.zzaWG;
  }
  
  public zza zzCH()
  {
    return this.zzaWH;
  }
  
  public zza zzCI()
  {
    return this.zzaWI;
  }
  
  public zza zzCJ()
  {
    return this.zzaWJ;
  }
  
  public zza zzCK()
  {
    return this.zzaWK;
  }
  
  public String zzCL()
  {
    Pair localPair = zzCo().zzaXi.zzlN();
    if (localPair == null) {
      return null;
    }
    return String.valueOf(localPair.second) + ":" + (String)localPair.first;
  }
  
  protected boolean zzQ(int paramInt)
  {
    return Log.isLoggable(this.zzamn, paramInt);
  }
  
  protected void zza(int paramInt, boolean paramBoolean1, boolean paramBoolean2, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if ((!paramBoolean1) && (zzQ(paramInt))) {
      zzl(paramInt, zza(false, paramString, paramObject1, paramObject2, paramObject3));
    }
    if ((!paramBoolean2) && (paramInt >= 5)) {
      zzb(paramInt, paramString, paramObject1, paramObject2, paramObject3);
    }
  }
  
  public void zzb(int paramInt, final String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    zzx.zzz(paramString);
    zzv localzzv = this.zzaTV.zzCU();
    if (localzzv == null)
    {
      zzl(6, "Scheduler not set. Not logging error/warn.");
      return;
    }
    if (!localzzv.isInitialized())
    {
      zzl(6, "Scheduler not initialized. Not logging error/warn.");
      return;
    }
    if (localzzv.zzDi())
    {
      zzl(6, "Scheduler shutdown. Not logging error/warn.");
      return;
    }
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    paramInt = i;
    if (i >= "01VDIWEA?".length()) {
      paramInt = "01VDIWEA?".length() - 1;
    }
    paramObject1 = "1" + "01VDIWEA?".charAt(paramInt) + this.zzaWB + this.zzaVj + ":" + zza(true, paramString, paramObject1, paramObject2, paramObject3);
    if (((String)paramObject1).length() > 1024) {}
    for (paramString = paramString.substring(0, 1024);; paramString = (String)paramObject1)
    {
      localzzv.zzg(new Runnable()
      {
        public void run()
        {
          zzt localzzt = zzp.this.zzaTV.zzCo();
          if ((!localzzt.isInitialized()) || (localzzt.zzDi()))
          {
            zzp.this.zzl(6, "Persisted config not initialized . Not logging error/warn.");
            return;
          }
          localzzt.zzaXi.zzbq(paramString);
        }
      });
      return;
    }
  }
  
  protected void zziJ() {}
  
  protected void zzl(int paramInt, String paramString)
  {
    Log.println(paramInt, this.zzamn, paramString);
  }
  
  public class zza
  {
    private final int mPriority;
    private final boolean zzaWN;
    private final boolean zzaWO;
    
    zza(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.mPriority = paramInt;
      this.zzaWN = paramBoolean1;
      this.zzaWO = paramBoolean2;
    }
    
    public void zzd(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, paramString, paramObject1, paramObject2, paramObject3);
    }
    
    public void zze(String paramString, Object paramObject1, Object paramObject2)
    {
      zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, paramString, paramObject1, paramObject2, null);
    }
    
    public void zzfg(String paramString)
    {
      zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, paramString, null, null, null);
    }
    
    public void zzj(String paramString, Object paramObject)
    {
      zzp.this.zza(this.mPriority, this.zzaWN, this.zzaWO, paramString, paramObject, null, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */