package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.zzv;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class zzc
{
  public static CharSequence zza(String paramString, List<AutocompletePredictionEntity.SubstringEntity> paramList, CharacterStyle paramCharacterStyle)
  {
    if (paramCharacterStyle == null) {
      return paramString;
    }
    paramString = new SpannableString(paramString);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      AutocompletePredictionEntity.SubstringEntity localSubstringEntity = (AutocompletePredictionEntity.SubstringEntity)paramList.next();
      CharacterStyle localCharacterStyle = CharacterStyle.wrap(paramCharacterStyle);
      int i = localSubstringEntity.getOffset();
      int j = localSubstringEntity.getOffset();
      paramString.setSpan(localCharacterStyle, i, localSubstringEntity.getLength() + j, 0);
    }
    return paramString;
  }
  
  public static String zzj(Collection<String> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return null;
    }
    return zzv.zzcL(", ").zza(paramCollection);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */