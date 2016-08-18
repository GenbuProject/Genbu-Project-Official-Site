package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum MessageDialogFeature
  implements DialogFeature
{
  MESSAGE_DIALOG(20140204),  PHOTOS(20140324),  VIDEO(20141218);
  
  private int minVersion;
  
  private MessageDialogFeature(int paramInt)
  {
    this.minVersion = paramInt;
  }
  
  public String getAction()
  {
    return "com.facebook.platform.action.request.MESSAGE_DIALOG";
  }
  
  public int getMinVersion()
  {
    return this.minVersion;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\internal\MessageDialogFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */