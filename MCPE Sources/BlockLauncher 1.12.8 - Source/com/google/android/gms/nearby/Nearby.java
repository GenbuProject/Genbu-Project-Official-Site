package com.google.android.gms.nearby;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.internal.zzqd;
import com.google.android.gms.internal.zzql;
import com.google.android.gms.nearby.bootstrap.zza;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.internal.zzn;
import com.google.android.gms.nearby.messages.internal.zzo;
import com.google.android.gms.nearby.messages.zzc;
import com.google.android.gms.nearby.sharing.internal.zzh;
import com.google.android.gms.nearby.sharing.internal.zzi;
import com.google.android.gms.nearby.sharing.zzd;
import com.google.android.gms.nearby.sharing.zze;

public final class Nearby
{
  public static final Api<Api.ApiOptions.NoOptions> CONNECTIONS_API = new Api("Nearby.CONNECTIONS_API", zzql.zzUJ, zzql.zzUI);
  public static final Connections Connections = new zzql();
  public static final Api<MessagesOptions> MESSAGES_API = new Api("Nearby.MESSAGES_API", zzn.zzUJ, zzn.zzUI);
  public static final Messages Messages = new zzn();
  public static final zzc zzbaK = new zzo();
  public static final Api<Api.ApiOptions.NoOptions> zzbaL = new Api("Nearby.SHARING_API", zzh.zzUJ, zzh.zzUI);
  public static final zzd zzbaM = new zzh();
  public static final zze zzbaN = new zzi();
  public static final Api<Api.ApiOptions.NoOptions> zzbaO = new Api("Nearby.BOOTSTRAP_API", zzqd.zzUJ, zzqd.zzUI);
  public static final zza zzbaP = new zzqd();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\Nearby.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */