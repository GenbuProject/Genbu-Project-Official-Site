package com.microsoft.xbox.service.model;

import android.os.Bundle;

public final class UpdateData
{
  private final Bundle extra;
  private final boolean isFinal;
  private final UpdateType updateType;
  
  public UpdateData(UpdateType paramUpdateType, boolean paramBoolean)
  {
    this.updateType = paramUpdateType;
    this.isFinal = paramBoolean;
    this.extra = null;
  }
  
  public UpdateData(UpdateType paramUpdateType, boolean paramBoolean, Bundle paramBundle)
  {
    this.updateType = paramUpdateType;
    this.isFinal = paramBoolean;
    this.extra = paramBundle;
  }
  
  public Bundle getExtra()
  {
    return this.extra;
  }
  
  public boolean getIsFinal()
  {
    return this.isFinal;
  }
  
  public UpdateType getUpdateType()
  {
    return this.updateType;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\UpdateData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */