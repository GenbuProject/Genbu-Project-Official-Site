package com.google.android.gms.fitness;

import android.content.Intent;
import android.os.Build.VERSION;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zznz;
import com.google.android.gms.internal.zznz.zzb;
import com.google.android.gms.internal.zzoa;
import com.google.android.gms.internal.zzoa.zzb;
import com.google.android.gms.internal.zzob;
import com.google.android.gms.internal.zzob.zzb;
import com.google.android.gms.internal.zzoc;
import com.google.android.gms.internal.zzoc.zza;
import com.google.android.gms.internal.zzod;
import com.google.android.gms.internal.zzod.zzb;
import com.google.android.gms.internal.zzoe;
import com.google.android.gms.internal.zzoe.zzb;
import com.google.android.gms.internal.zzof;
import com.google.android.gms.internal.zzof.zzb;
import com.google.android.gms.internal.zzoy;
import com.google.android.gms.internal.zzpa;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpc;
import com.google.android.gms.internal.zzpd;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpf;
import com.google.android.gms.internal.zzpg;
import com.google.android.gms.internal.zzpi;
import java.util.concurrent.TimeUnit;

public class Fitness
{
  public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
  public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
  public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
  @Deprecated
  public static final Void API;
  public static final Api<Api.ApiOptions.NoOptions> BLE_API;
  public static final BleApi BleApi;
  public static final Api<Api.ApiOptions.NoOptions> CONFIG_API;
  public static final ConfigApi ConfigApi;
  public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
  public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
  public static final Api<Api.ApiOptions.NoOptions> HISTORY_API;
  public static final HistoryApi HistoryApi;
  public static final Api<Api.ApiOptions.NoOptions> RECORDING_API;
  public static final RecordingApi RecordingApi;
  public static final Scope SCOPE_ACTIVITY_READ;
  public static final Scope SCOPE_ACTIVITY_READ_WRITE;
  public static final Scope SCOPE_BODY_READ = new Scope("https://www.googleapis.com/auth/fitness.body.read");
  public static final Scope SCOPE_BODY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.body.write");
  public static final Scope SCOPE_LOCATION_READ;
  public static final Scope SCOPE_LOCATION_READ_WRITE;
  public static final Scope SCOPE_NUTRITION_READ = new Scope("https://www.googleapis.com/auth/fitness.nutrition.read");
  public static final Scope SCOPE_NUTRITION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.nutrition.write");
  public static final Api<Api.ApiOptions.NoOptions> SENSORS_API;
  public static final Api<Api.ApiOptions.NoOptions> SESSIONS_API;
  public static final SensorsApi SensorsApi;
  public static final SessionsApi SessionsApi;
  public static final Api<Api.ApiOptions.NoOptions> zzaoG;
  public static final Api.zzc<zznz> zzavK = new Api.zzc();
  public static final Api.zzc<zzoa> zzavL = new Api.zzc();
  public static final Api.zzc<zzob> zzavM = new Api.zzc();
  public static final Api.zzc<zzoc> zzavN = new Api.zzc();
  public static final Api.zzc<zzod> zzavO = new Api.zzc();
  public static final Api.zzc<zzoe> zzavP = new Api.zzc();
  public static final Api.zzc<zzof> zzavQ = new Api.zzc();
  public static final zzoy zzavR;
  
  static
  {
    API = null;
    SENSORS_API = new Api("Fitness.SENSORS_API", new zzoe.zzb(), zzavP);
    SensorsApi = new zzpf();
    RECORDING_API = new Api("Fitness.RECORDING_API", new zzod.zzb(), zzavO);
    RecordingApi = new zzpe();
    SESSIONS_API = new Api("Fitness.SESSIONS_API", new zzof.zzb(), zzavQ);
    SessionsApi = new zzpg();
    HISTORY_API = new Api("Fitness.HISTORY_API", new zzob.zzb(), zzavM);
    HistoryApi = new zzpc();
    CONFIG_API = new Api("Fitness.CONFIG_API", new zzoa.zzb(), zzavL);
    ConfigApi = new zzpb();
    BLE_API = new Api("Fitness.BLE_API", new zznz.zzb(), zzavK);
    BleApi = zztZ();
    zzaoG = new Api("Fitness.INTERNAL_API", new zzoc.zza(), zzavN);
    zzavR = new zzpd();
    SCOPE_ACTIVITY_READ = new Scope("https://www.googleapis.com/auth/fitness.activity.read");
    SCOPE_ACTIVITY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
    SCOPE_LOCATION_READ = new Scope("https://www.googleapis.com/auth/fitness.location.read");
    SCOPE_LOCATION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.location.write");
  }
  
  public static long getEndTime(Intent paramIntent, TimeUnit paramTimeUnit)
  {
    long l = paramIntent.getLongExtra("vnd.google.fitness.end_time", -1L);
    if (l == -1L) {
      return -1L;
    }
    return paramTimeUnit.convert(l, TimeUnit.MILLISECONDS);
  }
  
  public static long getStartTime(Intent paramIntent, TimeUnit paramTimeUnit)
  {
    long l = paramIntent.getLongExtra("vnd.google.fitness.start_time", -1L);
    if (l == -1L) {
      return -1L;
    }
    return paramTimeUnit.convert(l, TimeUnit.MILLISECONDS);
  }
  
  private static BleApi zztZ()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return new zzpa();
    }
    return new zzpi();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\Fitness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */