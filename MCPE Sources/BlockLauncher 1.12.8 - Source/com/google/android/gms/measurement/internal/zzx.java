package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Process;
import android.support.annotation.BinderThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import java.util.List;
import java.util.concurrent.Callable;

public class zzx
  extends zzm.zza
{
  private final zzw zzaTV;
  private final boolean zzaYw;
  
  public zzx(zzw paramzzw)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramzzw);
    this.zzaTV = paramzzw;
    this.zzaYw = false;
  }
  
  public zzx(zzw paramzzw, boolean paramBoolean)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramzzw);
    this.zzaTV = paramzzw;
    this.zzaYw = paramBoolean;
  }
  
  @BinderThread
  private void zzfm(String paramString)
    throws SecurityException
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.zzaTV.zzAo().zzCE().zzfg("Measurement Service called without app package");
      throw new SecurityException("Measurement Service called without app package");
    }
    try
    {
      zzfn(paramString);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      this.zzaTV.zzAo().zzCE().zzj("Measurement Service called with invalid calling package", paramString);
      throw localSecurityException;
    }
  }
  
  private void zzfn(String paramString)
    throws SecurityException
  {
    int i;
    if (this.zzaYw)
    {
      i = Process.myUid();
      if (!com.google.android.gms.common.zze.zzb(this.zzaTV.getContext(), i, paramString)) {
        break label34;
      }
    }
    label34:
    while ((com.google.android.gms.common.zze.zzf(this.zzaTV.getContext(), i)) && (!this.zzaTV.zzCZ()))
    {
      return;
      i = Binder.getCallingUid();
      break;
    }
    throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[] { paramString }));
  }
  
  /* Error */
  @BinderThread
  public List<UserAttributeParcel> zza(final AppMetadata paramAppMetadata, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 33	com/google/android/gms/common/internal/zzx:zzz	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_0
    //   6: aload_1
    //   7: getfield 134	com/google/android/gms/measurement/internal/AppMetadata:packageName	Ljava/lang/String;
    //   10: invokespecial 136	com/google/android/gms/measurement/internal/zzx:zzfm	(Ljava/lang/String;)V
    //   13: aload_0
    //   14: getfield 35	com/google/android/gms/measurement/internal/zzx:zzaTV	Lcom/google/android/gms/measurement/internal/zzw;
    //   17: invokevirtual 140	com/google/android/gms/measurement/internal/zzw:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
    //   20: new 16	com/google/android/gms/measurement/internal/zzx$6
    //   23: dup
    //   24: aload_0
    //   25: aload_1
    //   26: invokespecial 143	com/google/android/gms/measurement/internal/zzx$6:<init>	(Lcom/google/android/gms/measurement/internal/zzx;Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   29: invokevirtual 149	com/google/android/gms/measurement/internal/zzv:zzd	(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
    //   32: astore_1
    //   33: aload_1
    //   34: invokeinterface 155 1 0
    //   39: checkcast 157	java/util/List
    //   42: astore_3
    //   43: new 159	java/util/ArrayList
    //   46: dup
    //   47: aload_3
    //   48: invokeinterface 162 1 0
    //   53: invokespecial 165	java/util/ArrayList:<init>	(I)V
    //   56: astore_1
    //   57: aload_3
    //   58: invokeinterface 169 1 0
    //   63: astore_3
    //   64: aload_3
    //   65: invokeinterface 174 1 0
    //   70: ifeq +67 -> 137
    //   73: aload_3
    //   74: invokeinterface 177 1 0
    //   79: checkcast 179	com/google/android/gms/measurement/internal/zzai
    //   82: astore 4
    //   84: iload_2
    //   85: ifne +14 -> 99
    //   88: aload 4
    //   90: getfield 182	com/google/android/gms/measurement/internal/zzai:mName	Ljava/lang/String;
    //   93: invokestatic 188	com/google/android/gms/measurement/internal/zzaj:zzfv	(Ljava/lang/String;)Z
    //   96: ifne -32 -> 64
    //   99: aload_1
    //   100: new 190	com/google/android/gms/measurement/internal/UserAttributeParcel
    //   103: dup
    //   104: aload 4
    //   106: invokespecial 193	com/google/android/gms/measurement/internal/UserAttributeParcel:<init>	(Lcom/google/android/gms/measurement/internal/zzai;)V
    //   109: invokeinterface 197 2 0
    //   114: pop
    //   115: goto -51 -> 64
    //   118: astore_1
    //   119: aload_0
    //   120: getfield 35	com/google/android/gms/measurement/internal/zzx:zzaTV	Lcom/google/android/gms/measurement/internal/zzw;
    //   123: invokevirtual 58	com/google/android/gms/measurement/internal/zzw:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
    //   126: invokevirtual 64	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
    //   129: ldc -57
    //   131: aload_1
    //   132: invokevirtual 82	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
    //   135: aconst_null
    //   136: areturn
    //   137: aload_1
    //   138: areturn
    //   139: astore_1
    //   140: goto -21 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	143	0	this	zzx
    //   0	143	1	paramAppMetadata	AppMetadata
    //   0	143	2	paramBoolean	boolean
    //   42	32	3	localObject	Object
    //   82	23	4	localzzai	zzai
    // Exception table:
    //   from	to	target	type
    //   33	64	118	java/lang/InterruptedException
    //   64	84	118	java/lang/InterruptedException
    //   88	99	118	java/lang/InterruptedException
    //   99	115	118	java/lang/InterruptedException
    //   33	64	139	java/util/concurrent/ExecutionException
    //   64	84	139	java/util/concurrent/ExecutionException
    //   88	99	139	java/util/concurrent/ExecutionException
    //   99	115	139	java/util/concurrent/ExecutionException
  }
  
  @BinderThread
  public void zza(final AppMetadata paramAppMetadata)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramAppMetadata);
    zzfm(paramAppMetadata.packageName);
    this.zzaTV.zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzx.this.zzfl(paramAppMetadata.zzaVx);
        zzx.zza(zzx.this).zzd(paramAppMetadata);
      }
    });
  }
  
  @BinderThread
  public void zza(final EventParcel paramEventParcel, final AppMetadata paramAppMetadata)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramEventParcel);
    com.google.android.gms.common.internal.zzx.zzz(paramAppMetadata);
    zzfm(paramAppMetadata.packageName);
    this.zzaTV.zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzx.this.zzfl(paramAppMetadata.zzaVx);
        zzx.zza(zzx.this).zzb(paramEventParcel, paramAppMetadata);
      }
    });
  }
  
  @BinderThread
  public void zza(final EventParcel paramEventParcel, final String paramString1, final String paramString2)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramEventParcel);
    com.google.android.gms.common.internal.zzx.zzcM(paramString1);
    zzfm(paramString1);
    this.zzaTV.zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzx.this.zzfl(paramString2);
        zzx.zza(zzx.this).zza(paramEventParcel, paramString1);
      }
    });
  }
  
  @BinderThread
  public void zza(final UserAttributeParcel paramUserAttributeParcel, final AppMetadata paramAppMetadata)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramUserAttributeParcel);
    com.google.android.gms.common.internal.zzx.zzz(paramAppMetadata);
    zzfm(paramAppMetadata.packageName);
    if (paramUserAttributeParcel.getValue() == null)
    {
      this.zzaTV.zzCn().zzg(new Runnable()
      {
        public void run()
        {
          zzx.this.zzfl(paramAppMetadata.zzaVx);
          zzx.zza(zzx.this).zzc(paramUserAttributeParcel, paramAppMetadata);
        }
      });
      return;
    }
    this.zzaTV.zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzx.this.zzfl(paramAppMetadata.zzaVx);
        zzx.zza(zzx.this).zzb(paramUserAttributeParcel, paramAppMetadata);
      }
    });
  }
  
  @BinderThread
  public void zzb(final AppMetadata paramAppMetadata)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramAppMetadata);
    zzfm(paramAppMetadata.packageName);
    this.zzaTV.zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzx.this.zzfl(paramAppMetadata.zzaVx);
        zzx.zza(zzx.this).zzc(paramAppMetadata);
      }
    });
  }
  
  @WorkerThread
  void zzfl(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = paramString.split(":", 2);
      if (paramString.length != 2) {}
    }
    long l;
    try
    {
      l = Long.valueOf(paramString[0]).longValue();
      if (l > 0L)
      {
        this.zzaTV.zzCo().zzaXi.zzf(paramString[1], l);
        return;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      this.zzaTV.zzAo().zzCF().zzj("Combining sample with a non-number weight", paramString[0]);
      return;
    }
    this.zzaTV.zzAo().zzCF().zzj("Combining sample with a non-positive weight", Long.valueOf(l));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */