package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzlw;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmt;
import com.google.android.gms.internal.zzsz.zzd;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class zzb
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api("ClearcutLogger.API", zzUJ, zzUI);
  public static final Api.zzc<zzlw> zzUI = new Api.zzc();
  public static final Api.zza<zzlw, Api.ApiOptions.NoOptions> zzUJ = new Api.zza()
  {
    public zzlw zze(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzlw(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  public static final zzc zzaeQ = new zzlv();
  private final Context mContext;
  private final String zzTJ;
  private final int zzaeR;
  private String zzaeS;
  private int zzaeT = -1;
  private String zzaeU;
  private String zzaeV;
  private final boolean zzaeW;
  private int zzaeX = 0;
  private final zzc zzaeY;
  private final zza zzaeZ;
  private zzc zzafa;
  private final zzmq zzqW;
  
  public zzb(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean, zzc paramzzc, zzmq paramzzmq, zzc paramzzc1, zza paramzza)
  {
    Context localContext = paramContext.getApplicationContext();
    if (localContext != null)
    {
      this.mContext = localContext;
      this.zzTJ = paramContext.getPackageName();
      this.zzaeR = zzai(paramContext);
      this.zzaeT = paramInt;
      this.zzaeS = paramString1;
      this.zzaeU = paramString2;
      this.zzaeV = paramString3;
      this.zzaeW = paramBoolean;
      this.zzaeY = paramzzc;
      this.zzqW = paramzzmq;
      if (paramzzc1 == null) {
        break label141;
      }
      label93:
      this.zzafa = paramzzc1;
      this.zzaeZ = paramzza;
      this.zzaeX = 0;
      if (this.zzaeW) {
        if (this.zzaeU != null) {
          break label153;
        }
      }
    }
    label141:
    label153:
    for (paramBoolean = true;; paramBoolean = false)
    {
      zzx.zzb(paramBoolean, "can't be anonymous with an upload account");
      return;
      localContext = paramContext;
      break;
      paramzzc1 = new zzc();
      break label93;
    }
  }
  
  @Deprecated
  public zzb(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this(paramContext, -1, paramString1, paramString2, paramString3, false, zzaeQ, zzmt.zzsc(), null, zza.zzaeP);
  }
  
  private int zzai(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.wtf("ClearcutLogger", "This can't happen.");
    }
    return 0;
  }
  
  private static int[] zzb(ArrayList<Integer> paramArrayList)
  {
    if (paramArrayList == null) {
      return null;
    }
    int[] arrayOfInt = new int[paramArrayList.size()];
    paramArrayList = paramArrayList.iterator();
    int i = 0;
    while (paramArrayList.hasNext())
    {
      arrayOfInt[i] = ((Integer)paramArrayList.next()).intValue();
      i += 1;
    }
    return arrayOfInt;
  }
  
  public boolean zza(GoogleApiClient paramGoogleApiClient, long paramLong, TimeUnit paramTimeUnit)
  {
    return this.zzaeY.zza(paramGoogleApiClient, paramLong, paramTimeUnit);
  }
  
  public zza zzi(byte[] paramArrayOfByte)
  {
    return new zza(paramArrayOfByte, null);
  }
  
  public class zza
  {
    private String zzaeS = zzb.zzb(zzb.this);
    private int zzaeT = zzb.zza(zzb.this);
    private String zzaeU = zzb.zzc(zzb.this);
    private String zzaeV = zzb.zzd(zzb.this);
    private int zzaeX = zzb.zze(zzb.this);
    private final zzb.zzb zzafb;
    private zzb.zzb zzafc;
    private ArrayList<Integer> zzafd = null;
    private final zzsz.zzd zzafe = new zzsz.zzd();
    private boolean zzaff = false;
    
    private zza(byte[] paramArrayOfByte)
    {
      this(paramArrayOfByte, null);
    }
    
    private zza(byte[] paramArrayOfByte, zzb.zzb paramzzb)
    {
      this.zzafe.zzbuR = zzb.zzf(zzb.this).currentTimeMillis();
      this.zzafe.zzbuS = zzb.zzf(zzb.this).elapsedRealtime();
      this.zzafe.zzbvi = zzb.zzh(zzb.this).zzah(zzb.zzg(zzb.this));
      this.zzafe.zzbvd = zzb.zzi(zzb.this).zzC(this.zzafe.zzbuR);
      if (paramArrayOfByte != null) {
        this.zzafe.zzbuY = paramArrayOfByte;
      }
      this.zzafb = paramzzb;
    }
    
    public zza zzbq(int paramInt)
    {
      this.zzafe.zzbuU = paramInt;
      return this;
    }
    
    public zza zzbr(int paramInt)
    {
      this.zzafe.zzob = paramInt;
      return this;
    }
    
    public PendingResult<Status> zzd(GoogleApiClient paramGoogleApiClient)
    {
      if (this.zzaff) {
        throw new IllegalStateException("do not reuse LogEventBuilder");
      }
      this.zzaff = true;
      return zzb.zzm(zzb.this).zza(paramGoogleApiClient, zzoE());
    }
    
    public LogEventParcelable zzoE()
    {
      return new LogEventParcelable(new PlayLoggerContext(zzb.zzk(zzb.this), zzb.zzl(zzb.this), this.zzaeT, this.zzaeS, this.zzaeU, this.zzaeV, zzb.zzj(zzb.this), this.zzaeX), this.zzafe, this.zzafb, this.zzafc, zzb.zzc(this.zzafd));
    }
  }
  
  public static abstract interface zzb
  {
    public abstract byte[] zzoF();
  }
  
  public static class zzc
  {
    public long zzC(long paramLong)
    {
      return TimeZone.getDefault().getOffset(paramLong) / 1000;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\clearcut\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */