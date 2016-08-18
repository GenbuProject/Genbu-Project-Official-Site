package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum AppInviteDialogFeature
  implements DialogFeature
{
  APP_INVITES_DIALOG(20140701);
  
  private int minVersion;
  
  private AppInviteDialogFeature(int paramInt)
  {
    this.minVersion = paramInt;
  }
  
  public String getAction()
  {
    return "com.facebook.platform.action.request.APPINVITES_DIALOG";
  }
  
  public int getMinVersion()
  {
    return this.minVersion;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\internal\AppInviteDialogFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */