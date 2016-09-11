package com.google.android.gms.games.internal.notification;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class GameNotificationRef
  extends zzc
  implements GameNotification
{
  GameNotificationRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public long getId()
  {
    return getLong("_id");
  }
  
  public String getText()
  {
    return getString("text");
  }
  
  public String getTitle()
  {
    return getString("title");
  }
  
  public int getType()
  {
    return getInteger("type");
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("Id", Long.valueOf(getId())).zzg("NotificationId", zzxt()).zzg("Type", Integer.valueOf(getType())).zzg("Title", getTitle()).zzg("Ticker", zzxu()).zzg("Text", getText()).zzg("CoalescedText", zzxv()).zzg("isAcknowledged", Boolean.valueOf(zzxw())).zzg("isSilent", Boolean.valueOf(zzxx())).toString();
  }
  
  public String zzxt()
  {
    return getString("notification_id");
  }
  
  public String zzxu()
  {
    return getString("ticker");
  }
  
  public String zzxv()
  {
    return getString("coalesced_text");
  }
  
  public boolean zzxw()
  {
    return getInteger("acknowledged") > 0;
  }
  
  public boolean zzxx()
  {
    return getInteger("alert_level") == 0;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\internal\notification\GameNotificationRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */