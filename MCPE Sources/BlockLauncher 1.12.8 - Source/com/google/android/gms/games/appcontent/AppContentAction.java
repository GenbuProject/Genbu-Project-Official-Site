package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public abstract interface AppContentAction
  extends Parcelable, Freezable<AppContentAction>
{
  public abstract Bundle getExtras();
  
  public abstract String getId();
  
  public abstract String getType();
  
  public abstract AppContentAnnotation zzvO();
  
  public abstract List<AppContentCondition> zzvP();
  
  public abstract String zzvQ();
  
  public abstract String zzvR();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */