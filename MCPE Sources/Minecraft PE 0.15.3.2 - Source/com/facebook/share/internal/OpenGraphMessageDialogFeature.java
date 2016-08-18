package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum OpenGraphMessageDialogFeature
  implements DialogFeature
{
  OG_MESSAGE_DIALOG(20140204);
  
  private int minVersion;
  
  private OpenGraphMessageDialogFeature(int paramInt)
  {
    this.minVersion = paramInt;
  }
  
  public String getAction()
  {
    return "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
  }
  
  public int getMinVersion()
  {
    return this.minVersion;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\internal\OpenGraphMessageDialogFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */