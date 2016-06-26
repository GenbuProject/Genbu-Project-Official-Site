package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.internal.zzmw;

public abstract class GamesDowngradeableSafeParcel
  extends DowngradeableSafeParcel
{
  protected static boolean zzd(Integer paramInteger)
  {
    if (paramInteger == null) {
      return false;
    }
    return zzmw.zzcn(paramInteger.intValue());
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\GamesDowngradeableSafeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */