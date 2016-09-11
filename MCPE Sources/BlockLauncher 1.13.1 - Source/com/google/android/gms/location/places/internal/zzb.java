package com.google.android.gms.location.places.internal;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public class zzb
  extends zzt
  implements AutocompletePrediction
{
  public zzb(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private String zzzh()
  {
    return zzG("ap_description", "");
  }
  
  private String zzzi()
  {
    return zzG("ap_primary_text", "");
  }
  
  private String zzzj()
  {
    return zzG("ap_secondary_text", "");
  }
  
  private List<AutocompletePredictionEntity.SubstringEntity> zzzk()
  {
    return zza("ap_matched_subscriptions", AutocompletePredictionEntity.SubstringEntity.CREATOR, Collections.emptyList());
  }
  
  private List<AutocompletePredictionEntity.SubstringEntity> zzzl()
  {
    return zza("ap_primary_text_matched", AutocompletePredictionEntity.SubstringEntity.CREATOR, Collections.emptyList());
  }
  
  private List<AutocompletePredictionEntity.SubstringEntity> zzzm()
  {
    return zza("ap_secondary_text_matched", AutocompletePredictionEntity.SubstringEntity.CREATOR, Collections.emptyList());
  }
  
  public String getDescription()
  {
    return zzzh();
  }
  
  public CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(zzzh(), zzzk(), paramCharacterStyle);
  }
  
  public List<AutocompletePredictionEntity.SubstringEntity> getMatchedSubstrings()
  {
    return zzzk();
  }
  
  public String getPlaceId()
  {
    return zzG("ap_place_id", null);
  }
  
  public List<Integer> getPlaceTypes()
  {
    return zza("ap_place_types", Collections.emptyList());
  }
  
  public CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(zzzi(), zzzl(), paramCharacterStyle);
  }
  
  public CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(zzzj(), zzzm(), paramCharacterStyle);
  }
  
  public AutocompletePrediction zzzf()
  {
    return AutocompletePredictionEntity.zza(getPlaceId(), getPlaceTypes(), zzzg(), zzzh(), zzzk(), zzzi(), zzzl(), zzzj(), zzzm());
  }
  
  public int zzzg()
  {
    return zzz("ap_personalization_type", 6);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */