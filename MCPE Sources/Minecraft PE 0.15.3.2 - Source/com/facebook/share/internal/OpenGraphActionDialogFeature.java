package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum OpenGraphActionDialogFeature
  implements DialogFeature
{
  OG_ACTION_DIALOG(20130618);
  
  private int minVersion;
  
  private OpenGraphActionDialogFeature(int paramInt)
  {
    this.minVersion = paramInt;
  }
  
  public String getAction()
  {
    return "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
  }
  
  public int getMinVersion()
  {
    return this.minVersion;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\internal\OpenGraphActionDialogFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */