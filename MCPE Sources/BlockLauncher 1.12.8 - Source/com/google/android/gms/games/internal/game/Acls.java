package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public abstract interface Acls
{
  public static abstract interface LoadAclResult
    extends Releasable, Result
  {}
  
  public static abstract interface LoadFAclResult
    extends Releasable, Result
  {}
  
  public static abstract interface OnGameplayAclLoadedCallback {}
  
  public static abstract interface OnGameplayAclUpdatedCallback {}
  
  public static abstract interface OnNotifyAclLoadedCallback {}
  
  public static abstract interface OnNotifyAclUpdatedCallback {}
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\game\Acls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */