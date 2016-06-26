package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.internal.constants.PlatformType;

public final class GameInstanceRef
  extends zzc
  implements GameInstance
{
  GameInstanceRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public String getApplicationId()
  {
    return getString("external_game_id");
  }
  
  public String getDisplayName()
  {
    return getString("instance_display_name");
  }
  
  public String getPackageName()
  {
    return getString("package_name");
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("ApplicationId", getApplicationId()).zzg("DisplayName", getDisplayName()).zzg("SupportsRealTime", Boolean.valueOf(zzxn())).zzg("SupportsTurnBased", Boolean.valueOf(zzxo())).zzg("PlatformType", PlatformType.zzgw(zzur())).zzg("PackageName", getPackageName()).zzg("PiracyCheckEnabled", Boolean.valueOf(zzxp())).zzg("Installed", Boolean.valueOf(zzxq())).toString();
  }
  
  public int zzur()
  {
    return getInteger("platform_type");
  }
  
  public boolean zzxn()
  {
    return getInteger("real_time_support") > 0;
  }
  
  public boolean zzxo()
  {
    return getInteger("turn_based_support") > 0;
  }
  
  public boolean zzxp()
  {
    return getInteger("piracy_check") > 0;
  }
  
  public boolean zzxq()
  {
    return getInteger("installed") > 0;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\game\GameInstanceRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */