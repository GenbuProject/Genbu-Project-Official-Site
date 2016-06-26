package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.api.Status;

public class zzkn
  implements AppInviteInvitationResult
{
  private final Status zzUX;
  private final Intent zzUY;
  
  public zzkn(Status paramStatus, Intent paramIntent)
  {
    this.zzUX = paramStatus;
    this.zzUY = paramIntent;
  }
  
  public Intent getInvitationIntent()
  {
    return this.zzUY;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzkn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */