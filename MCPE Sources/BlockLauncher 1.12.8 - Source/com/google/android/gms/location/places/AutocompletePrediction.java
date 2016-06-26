package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public abstract interface AutocompletePrediction
  extends Freezable<AutocompletePrediction>
{
  @Deprecated
  public abstract String getDescription();
  
  public abstract CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle);
  
  @Deprecated
  public abstract List<? extends Substring> getMatchedSubstrings();
  
  @Nullable
  public abstract String getPlaceId();
  
  @Nullable
  public abstract List<Integer> getPlaceTypes();
  
  public abstract CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle);
  
  public abstract CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle);
  
  @Deprecated
  public static abstract interface Substring
  {
    public abstract int getLength();
    
    public abstract int getOffset();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\AutocompletePrediction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */