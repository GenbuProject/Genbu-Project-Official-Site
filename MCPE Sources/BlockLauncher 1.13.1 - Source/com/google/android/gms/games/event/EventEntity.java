package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.zzms;

public final class EventEntity
  implements SafeParcelable, Event
{
  public static final EventEntityCreator CREATOR = new EventEntityCreator();
  private final String mName;
  private final int mVersionCode;
  private final Uri zzaCd;
  private final String zzaCo;
  private final String zzaDU;
  private final long zzaDV;
  private final String zzaDW;
  private final PlayerEntity zzaDq;
  private final String zzaxl;
  private final boolean zzsj;
  
  EventEntity(int paramInt, String paramString1, String paramString2, String paramString3, Uri paramUri, String paramString4, Player paramPlayer, long paramLong, String paramString5, boolean paramBoolean)
  {
    this.mVersionCode = paramInt;
    this.zzaDU = paramString1;
    this.mName = paramString2;
    this.zzaxl = paramString3;
    this.zzaCd = paramUri;
    this.zzaCo = paramString4;
    this.zzaDq = new PlayerEntity(paramPlayer);
    this.zzaDV = paramLong;
    this.zzaDW = paramString5;
    this.zzsj = paramBoolean;
  }
  
  public EventEntity(Event paramEvent)
  {
    this.mVersionCode = 1;
    this.zzaDU = paramEvent.getEventId();
    this.mName = paramEvent.getName();
    this.zzaxl = paramEvent.getDescription();
    this.zzaCd = paramEvent.getIconImageUri();
    this.zzaCo = paramEvent.getIconImageUrl();
    this.zzaDq = ((PlayerEntity)paramEvent.getPlayer().freeze());
    this.zzaDV = paramEvent.getValue();
    this.zzaDW = paramEvent.getFormattedValue();
    this.zzsj = paramEvent.isVisible();
  }
  
  static int zza(Event paramEvent)
  {
    return zzw.hashCode(new Object[] { paramEvent.getEventId(), paramEvent.getName(), paramEvent.getDescription(), paramEvent.getIconImageUri(), paramEvent.getIconImageUrl(), paramEvent.getPlayer(), Long.valueOf(paramEvent.getValue()), paramEvent.getFormattedValue(), Boolean.valueOf(paramEvent.isVisible()) });
  }
  
  static boolean zza(Event paramEvent, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Event)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramEvent == paramObject);
      paramObject = (Event)paramObject;
      if ((!zzw.equal(((Event)paramObject).getEventId(), paramEvent.getEventId())) || (!zzw.equal(((Event)paramObject).getName(), paramEvent.getName())) || (!zzw.equal(((Event)paramObject).getDescription(), paramEvent.getDescription())) || (!zzw.equal(((Event)paramObject).getIconImageUri(), paramEvent.getIconImageUri())) || (!zzw.equal(((Event)paramObject).getIconImageUrl(), paramEvent.getIconImageUrl())) || (!zzw.equal(((Event)paramObject).getPlayer(), paramEvent.getPlayer())) || (!zzw.equal(Long.valueOf(((Event)paramObject).getValue()), Long.valueOf(paramEvent.getValue()))) || (!zzw.equal(((Event)paramObject).getFormattedValue(), paramEvent.getFormattedValue()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Boolean.valueOf(((Event)paramObject).isVisible()), Boolean.valueOf(paramEvent.isVisible())));
    return false;
  }
  
  static String zzb(Event paramEvent)
  {
    return zzw.zzy(paramEvent).zzg("Id", paramEvent.getEventId()).zzg("Name", paramEvent.getName()).zzg("Description", paramEvent.getDescription()).zzg("IconImageUri", paramEvent.getIconImageUri()).zzg("IconImageUrl", paramEvent.getIconImageUrl()).zzg("Player", paramEvent.getPlayer()).zzg("Value", Long.valueOf(paramEvent.getValue())).zzg("FormattedValue", paramEvent.getFormattedValue()).zzg("isVisible", Boolean.valueOf(paramEvent.isVisible())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Event freeze()
  {
    return this;
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaxl, paramCharArrayBuffer);
  }
  
  public String getEventId()
  {
    return this.zzaDU;
  }
  
  public String getFormattedValue()
  {
    return this.zzaDW;
  }
  
  public void getFormattedValue(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaDW, paramCharArrayBuffer);
  }
  
  public Uri getIconImageUri()
  {
    return this.zzaCd;
  }
  
  public String getIconImageUrl()
  {
    return this.zzaCo;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.mName, paramCharArrayBuffer);
  }
  
  public Player getPlayer()
  {
    return this.zzaDq;
  }
  
  public long getValue()
  {
    return this.zzaDV;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zza(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isVisible()
  {
    return this.zzsj;
  }
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    EventEntityCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\event\EventEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */