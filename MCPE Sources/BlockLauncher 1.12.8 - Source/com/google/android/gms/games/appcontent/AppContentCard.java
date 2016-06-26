package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public abstract interface AppContentCard
  extends Parcelable, Freezable<AppContentCard>
{
  public abstract List<AppContentAction> getActions();
  
  public abstract String getDescription();
  
  public abstract Bundle getExtras();
  
  public abstract String getId();
  
  public abstract String getTitle();
  
  public abstract String getType();
  
  public abstract List<AppContentCondition> zzvP();
  
  public abstract String zzvQ();
  
  public abstract List<AppContentAnnotation> zzwa();
  
  public abstract int zzwb();
  
  public abstract String zzwc();
  
  public abstract int zzwd();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentCard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */