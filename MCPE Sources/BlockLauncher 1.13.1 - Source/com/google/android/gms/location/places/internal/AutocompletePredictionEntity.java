package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePrediction.Substring;
import java.util.Collections;
import java.util.List;

public class AutocompletePredictionEntity
  implements SafeParcelable, AutocompletePrediction
{
  public static final Parcelable.Creator<AutocompletePredictionEntity> CREATOR = new zza();
  private static final List<SubstringEntity> zzaQc = Collections.emptyList();
  final int mVersionCode;
  final String zzaPH;
  final List<Integer> zzaPd;
  final String zzaQd;
  final List<SubstringEntity> zzaQe;
  final int zzaQf;
  final String zzaQg;
  final List<SubstringEntity> zzaQh;
  final String zzaQi;
  final List<SubstringEntity> zzaQj;
  
  AutocompletePredictionEntity(int paramInt1, String paramString1, List<Integer> paramList, int paramInt2, String paramString2, List<SubstringEntity> paramList1, String paramString3, List<SubstringEntity> paramList2, String paramString4, List<SubstringEntity> paramList3)
  {
    this.mVersionCode = paramInt1;
    this.zzaPH = paramString1;
    this.zzaPd = paramList;
    this.zzaQf = paramInt2;
    this.zzaQd = paramString2;
    this.zzaQe = paramList1;
    this.zzaQg = paramString3;
    this.zzaQh = paramList2;
    this.zzaQi = paramString4;
    this.zzaQj = paramList3;
  }
  
  public static AutocompletePredictionEntity zza(String paramString1, List<Integer> paramList, int paramInt, String paramString2, List<SubstringEntity> paramList1, String paramString3, List<SubstringEntity> paramList2, String paramString4, List<SubstringEntity> paramList3)
  {
    return new AutocompletePredictionEntity(0, paramString1, paramList, paramInt, (String)zzx.zzz(paramString2), paramList1, paramString3, paramList2, paramString4, paramList3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof AutocompletePredictionEntity)) {
        return false;
      }
      paramObject = (AutocompletePredictionEntity)paramObject;
    } while ((zzw.equal(this.zzaPH, ((AutocompletePredictionEntity)paramObject).zzaPH)) && (zzw.equal(this.zzaPd, ((AutocompletePredictionEntity)paramObject).zzaPd)) && (zzw.equal(Integer.valueOf(this.zzaQf), Integer.valueOf(((AutocompletePredictionEntity)paramObject).zzaQf))) && (zzw.equal(this.zzaQd, ((AutocompletePredictionEntity)paramObject).zzaQd)) && (zzw.equal(this.zzaQe, ((AutocompletePredictionEntity)paramObject).zzaQe)) && (zzw.equal(this.zzaQg, ((AutocompletePredictionEntity)paramObject).zzaQg)) && (zzw.equal(this.zzaQh, ((AutocompletePredictionEntity)paramObject).zzaQh)) && (zzw.equal(this.zzaQi, ((AutocompletePredictionEntity)paramObject).zzaQi)) && (zzw.equal(this.zzaQj, ((AutocompletePredictionEntity)paramObject).zzaQj)));
    return false;
  }
  
  public String getDescription()
  {
    return this.zzaQd;
  }
  
  public CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(this.zzaQd, this.zzaQe, paramCharacterStyle);
  }
  
  public List<? extends AutocompletePrediction.Substring> getMatchedSubstrings()
  {
    return this.zzaQe;
  }
  
  @Nullable
  public String getPlaceId()
  {
    return this.zzaPH;
  }
  
  public List<Integer> getPlaceTypes()
  {
    return this.zzaPd;
  }
  
  public CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(this.zzaQg, this.zzaQh, paramCharacterStyle);
  }
  
  public CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(this.zzaQi, this.zzaQj, paramCharacterStyle);
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaPH, this.zzaPd, Integer.valueOf(this.zzaQf), this.zzaQd, this.zzaQe, this.zzaQg, this.zzaQh, this.zzaQi, this.zzaQj });
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("placeId", this.zzaPH).zzg("placeTypes", this.zzaPd).zzg("fullText", this.zzaQd).zzg("fullTextMatchedSubstrings", this.zzaQe).zzg("primaryText", this.zzaQg).zzg("primaryTextMatchedSubstrings", this.zzaQh).zzg("secondaryText", this.zzaQi).zzg("secondaryTextMatchedSubstrings", this.zzaQj).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public AutocompletePrediction zzzf()
  {
    return this;
  }
  
  public static class SubstringEntity
    implements SafeParcelable, AutocompletePrediction.Substring
  {
    public static final Parcelable.Creator<SubstringEntity> CREATOR = new zzu();
    final int mLength;
    final int mOffset;
    final int mVersionCode;
    
    public SubstringEntity(int paramInt1, int paramInt2, int paramInt3)
    {
      this.mVersionCode = paramInt1;
      this.mOffset = paramInt2;
      this.mLength = paramInt3;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof SubstringEntity)) {
          return false;
        }
        paramObject = (SubstringEntity)paramObject;
      } while ((zzw.equal(Integer.valueOf(this.mOffset), Integer.valueOf(((SubstringEntity)paramObject).mOffset))) && (zzw.equal(Integer.valueOf(this.mLength), Integer.valueOf(((SubstringEntity)paramObject).mLength))));
      return false;
    }
    
    public int getLength()
    {
      return this.mLength;
    }
    
    public int getOffset()
    {
      return this.mOffset;
    }
    
    public int hashCode()
    {
      return zzw.hashCode(new Object[] { Integer.valueOf(this.mOffset), Integer.valueOf(this.mLength) });
    }
    
    public String toString()
    {
      return zzw.zzy(this).zzg("offset", Integer.valueOf(this.mOffset)).zzg("length", Integer.valueOf(this.mLength)).toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzu.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\AutocompletePredictionEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */