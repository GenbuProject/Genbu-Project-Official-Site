package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.AgeRange;
import com.google.android.gms.plus.model.people.Person.Cover;
import com.google.android.gms.plus.model.people.Person.Cover.CoverInfo;
import com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto;
import com.google.android.gms.plus.model.people.Person.Image;
import com.google.android.gms.plus.model.people.Person.Name;
import com.google.android.gms.plus.model.people.Person.Organizations;
import com.google.android.gms.plus.model.people.Person.PlacesLived;
import com.google.android.gms.plus.model.people.Person.Urls;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class PersonEntity
  extends FastSafeParcelableJsonResponse
  implements Person
{
  public static final zza CREATOR = new zza();
  private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
  final int mVersionCode;
  String zzF;
  String zzWQ;
  String zzaaL;
  final Set<Integer> zzbeN;
  String zzbfL;
  AgeRangeEntity zzbfM;
  String zzbfN;
  String zzbfO;
  int zzbfP;
  CoverEntity zzbfQ;
  String zzbfR;
  ImageEntity zzbfS;
  boolean zzbfT;
  NameEntity zzbfU;
  String zzbfV;
  int zzbfW;
  List<OrganizationsEntity> zzbfX;
  List<PlacesLivedEntity> zzbfY;
  int zzbfZ;
  int zzbga;
  String zzbgb;
  List<UrlsEntity> zzbgc;
  boolean zzbgd;
  int zztT;
  String zzyv;
  
  static
  {
    zzbeM.put("aboutMe", FastJsonResponse.Field.zzl("aboutMe", 2));
    zzbeM.put("ageRange", FastJsonResponse.Field.zza("ageRange", 3, AgeRangeEntity.class));
    zzbeM.put("birthday", FastJsonResponse.Field.zzl("birthday", 4));
    zzbeM.put("braggingRights", FastJsonResponse.Field.zzl("braggingRights", 5));
    zzbeM.put("circledByCount", FastJsonResponse.Field.zzi("circledByCount", 6));
    zzbeM.put("cover", FastJsonResponse.Field.zza("cover", 7, CoverEntity.class));
    zzbeM.put("currentLocation", FastJsonResponse.Field.zzl("currentLocation", 8));
    zzbeM.put("displayName", FastJsonResponse.Field.zzl("displayName", 9));
    zzbeM.put("gender", FastJsonResponse.Field.zza("gender", 12, new StringToIntConverter().zzh("male", 0).zzh("female", 1).zzh("other", 2), false));
    zzbeM.put("id", FastJsonResponse.Field.zzl("id", 14));
    zzbeM.put("image", FastJsonResponse.Field.zza("image", 15, ImageEntity.class));
    zzbeM.put("isPlusUser", FastJsonResponse.Field.zzk("isPlusUser", 16));
    zzbeM.put("language", FastJsonResponse.Field.zzl("language", 18));
    zzbeM.put("name", FastJsonResponse.Field.zza("name", 19, NameEntity.class));
    zzbeM.put("nickname", FastJsonResponse.Field.zzl("nickname", 20));
    zzbeM.put("objectType", FastJsonResponse.Field.zza("objectType", 21, new StringToIntConverter().zzh("person", 0).zzh("page", 1), false));
    zzbeM.put("organizations", FastJsonResponse.Field.zzb("organizations", 22, OrganizationsEntity.class));
    zzbeM.put("placesLived", FastJsonResponse.Field.zzb("placesLived", 23, PlacesLivedEntity.class));
    zzbeM.put("plusOneCount", FastJsonResponse.Field.zzi("plusOneCount", 24));
    zzbeM.put("relationshipStatus", FastJsonResponse.Field.zza("relationshipStatus", 25, new StringToIntConverter().zzh("single", 0).zzh("in_a_relationship", 1).zzh("engaged", 2).zzh("married", 3).zzh("its_complicated", 4).zzh("open_relationship", 5).zzh("widowed", 6).zzh("in_domestic_partnership", 7).zzh("in_civil_union", 8), false));
    zzbeM.put("tagline", FastJsonResponse.Field.zzl("tagline", 26));
    zzbeM.put("url", FastJsonResponse.Field.zzl("url", 27));
    zzbeM.put("urls", FastJsonResponse.Field.zzb("urls", 28, UrlsEntity.class));
    zzbeM.put("verified", FastJsonResponse.Field.zzk("verified", 29));
  }
  
  public PersonEntity()
  {
    this.mVersionCode = 1;
    this.zzbeN = new HashSet();
  }
  
  public PersonEntity(String paramString1, String paramString2, ImageEntity paramImageEntity, int paramInt, String paramString3)
  {
    this.mVersionCode = 1;
    this.zzbeN = new HashSet();
    this.zzWQ = paramString1;
    this.zzbeN.add(Integer.valueOf(9));
    this.zzyv = paramString2;
    this.zzbeN.add(Integer.valueOf(14));
    this.zzbfS = paramImageEntity;
    this.zzbeN.add(Integer.valueOf(15));
    this.zzbfW = paramInt;
    this.zzbeN.add(Integer.valueOf(21));
    this.zzF = paramString3;
    this.zzbeN.add(Integer.valueOf(27));
  }
  
  PersonEntity(Set<Integer> paramSet, int paramInt1, String paramString1, AgeRangeEntity paramAgeRangeEntity, String paramString2, String paramString3, int paramInt2, CoverEntity paramCoverEntity, String paramString4, String paramString5, int paramInt3, String paramString6, ImageEntity paramImageEntity, boolean paramBoolean1, String paramString7, NameEntity paramNameEntity, String paramString8, int paramInt4, List<OrganizationsEntity> paramList, List<PlacesLivedEntity> paramList1, int paramInt5, int paramInt6, String paramString9, String paramString10, List<UrlsEntity> paramList2, boolean paramBoolean2)
  {
    this.zzbeN = paramSet;
    this.mVersionCode = paramInt1;
    this.zzbfL = paramString1;
    this.zzbfM = paramAgeRangeEntity;
    this.zzbfN = paramString2;
    this.zzbfO = paramString3;
    this.zzbfP = paramInt2;
    this.zzbfQ = paramCoverEntity;
    this.zzbfR = paramString4;
    this.zzWQ = paramString5;
    this.zztT = paramInt3;
    this.zzyv = paramString6;
    this.zzbfS = paramImageEntity;
    this.zzbfT = paramBoolean1;
    this.zzaaL = paramString7;
    this.zzbfU = paramNameEntity;
    this.zzbfV = paramString8;
    this.zzbfW = paramInt4;
    this.zzbfX = paramList;
    this.zzbfY = paramList1;
    this.zzbfZ = paramInt5;
    this.zzbga = paramInt6;
    this.zzbgb = paramString9;
    this.zzF = paramString10;
    this.zzbgc = paramList2;
    this.zzbgd = paramBoolean2;
  }
  
  public static PersonEntity zzv(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = CREATOR.zzgE(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
  }
  
  public int describeContents()
  {
    zza localzza = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PersonEntity)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (PersonEntity)paramObject;
    Iterator localIterator = zzbeM.values().iterator();
    while (localIterator.hasNext())
    {
      FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
      if (zza(localField))
      {
        if (((PersonEntity)paramObject).zza(localField))
        {
          if (!zzb(localField).equals(((PersonEntity)paramObject).zzb(localField))) {
            return false;
          }
        }
        else {
          return false;
        }
      }
      else if (((PersonEntity)paramObject).zza(localField)) {
        return false;
      }
    }
    return true;
  }
  
  public String getAboutMe()
  {
    return this.zzbfL;
  }
  
  public Person.AgeRange getAgeRange()
  {
    return this.zzbfM;
  }
  
  public String getBirthday()
  {
    return this.zzbfN;
  }
  
  public String getBraggingRights()
  {
    return this.zzbfO;
  }
  
  public int getCircledByCount()
  {
    return this.zzbfP;
  }
  
  public Person.Cover getCover()
  {
    return this.zzbfQ;
  }
  
  public String getCurrentLocation()
  {
    return this.zzbfR;
  }
  
  public String getDisplayName()
  {
    return this.zzWQ;
  }
  
  public int getGender()
  {
    return this.zztT;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public Person.Image getImage()
  {
    return this.zzbfS;
  }
  
  public String getLanguage()
  {
    return this.zzaaL;
  }
  
  public Person.Name getName()
  {
    return this.zzbfU;
  }
  
  public String getNickname()
  {
    return this.zzbfV;
  }
  
  public int getObjectType()
  {
    return this.zzbfW;
  }
  
  public List<Person.Organizations> getOrganizations()
  {
    return (ArrayList)this.zzbfX;
  }
  
  public List<Person.PlacesLived> getPlacesLived()
  {
    return (ArrayList)this.zzbfY;
  }
  
  public int getPlusOneCount()
  {
    return this.zzbfZ;
  }
  
  public int getRelationshipStatus()
  {
    return this.zzbga;
  }
  
  public String getTagline()
  {
    return this.zzbgb;
  }
  
  public String getUrl()
  {
    return this.zzF;
  }
  
  public List<Person.Urls> getUrls()
  {
    return (ArrayList)this.zzbgc;
  }
  
  public boolean hasAboutMe()
  {
    return this.zzbeN.contains(Integer.valueOf(2));
  }
  
  public boolean hasAgeRange()
  {
    return this.zzbeN.contains(Integer.valueOf(3));
  }
  
  public boolean hasBirthday()
  {
    return this.zzbeN.contains(Integer.valueOf(4));
  }
  
  public boolean hasBraggingRights()
  {
    return this.zzbeN.contains(Integer.valueOf(5));
  }
  
  public boolean hasCircledByCount()
  {
    return this.zzbeN.contains(Integer.valueOf(6));
  }
  
  public boolean hasCover()
  {
    return this.zzbeN.contains(Integer.valueOf(7));
  }
  
  public boolean hasCurrentLocation()
  {
    return this.zzbeN.contains(Integer.valueOf(8));
  }
  
  public boolean hasDisplayName()
  {
    return this.zzbeN.contains(Integer.valueOf(9));
  }
  
  public boolean hasGender()
  {
    return this.zzbeN.contains(Integer.valueOf(12));
  }
  
  public boolean hasId()
  {
    return this.zzbeN.contains(Integer.valueOf(14));
  }
  
  public boolean hasImage()
  {
    return this.zzbeN.contains(Integer.valueOf(15));
  }
  
  public boolean hasIsPlusUser()
  {
    return this.zzbeN.contains(Integer.valueOf(16));
  }
  
  public boolean hasLanguage()
  {
    return this.zzbeN.contains(Integer.valueOf(18));
  }
  
  public boolean hasName()
  {
    return this.zzbeN.contains(Integer.valueOf(19));
  }
  
  public boolean hasNickname()
  {
    return this.zzbeN.contains(Integer.valueOf(20));
  }
  
  public boolean hasObjectType()
  {
    return this.zzbeN.contains(Integer.valueOf(21));
  }
  
  public boolean hasOrganizations()
  {
    return this.zzbeN.contains(Integer.valueOf(22));
  }
  
  public boolean hasPlacesLived()
  {
    return this.zzbeN.contains(Integer.valueOf(23));
  }
  
  public boolean hasPlusOneCount()
  {
    return this.zzbeN.contains(Integer.valueOf(24));
  }
  
  public boolean hasRelationshipStatus()
  {
    return this.zzbeN.contains(Integer.valueOf(25));
  }
  
  public boolean hasTagline()
  {
    return this.zzbeN.contains(Integer.valueOf(26));
  }
  
  public boolean hasUrl()
  {
    return this.zzbeN.contains(Integer.valueOf(27));
  }
  
  public boolean hasUrls()
  {
    return this.zzbeN.contains(Integer.valueOf(28));
  }
  
  public boolean hasVerified()
  {
    return this.zzbeN.contains(Integer.valueOf(29));
  }
  
  public int hashCode()
  {
    Iterator localIterator = zzbeM.values().iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
      if (!zza(localField)) {
        break label68;
      }
      int j = localField.zzrs();
      i = zzb(localField).hashCode() + (i + j);
    }
    label68:
    for (;;)
    {
      break;
      return i;
    }
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isPlusUser()
  {
    return this.zzbfT;
  }
  
  public boolean isVerified()
  {
    return this.zzbgd;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza localzza = CREATOR;
    zza.zza(this, paramParcel, paramInt);
  }
  
  public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
  {
    return zzbeM;
  }
  
  public PersonEntity zzFp()
  {
    return this;
  }
  
  protected boolean zza(FastJsonResponse.Field paramField)
  {
    return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
  }
  
  protected Object zzb(FastJsonResponse.Field paramField)
  {
    switch (paramField.zzrs())
    {
    case 10: 
    case 11: 
    case 13: 
    case 17: 
    default: 
      throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
    case 2: 
      return this.zzbfL;
    case 3: 
      return this.zzbfM;
    case 4: 
      return this.zzbfN;
    case 5: 
      return this.zzbfO;
    case 6: 
      return Integer.valueOf(this.zzbfP);
    case 7: 
      return this.zzbfQ;
    case 8: 
      return this.zzbfR;
    case 9: 
      return this.zzWQ;
    case 12: 
      return Integer.valueOf(this.zztT);
    case 14: 
      return this.zzyv;
    case 15: 
      return this.zzbfS;
    case 16: 
      return Boolean.valueOf(this.zzbfT);
    case 18: 
      return this.zzaaL;
    case 19: 
      return this.zzbfU;
    case 20: 
      return this.zzbfV;
    case 21: 
      return Integer.valueOf(this.zzbfW);
    case 22: 
      return this.zzbfX;
    case 23: 
      return this.zzbfY;
    case 24: 
      return Integer.valueOf(this.zzbfZ);
    case 25: 
      return Integer.valueOf(this.zzbga);
    case 26: 
      return this.zzbgb;
    case 27: 
      return this.zzF;
    case 28: 
      return this.zzbgc;
    }
    return Boolean.valueOf(this.zzbgd);
  }
  
  public static final class AgeRangeEntity
    extends FastSafeParcelableJsonResponse
    implements Person.AgeRange
  {
    public static final zzb CREATOR = new zzb();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
    final int mVersionCode;
    final Set<Integer> zzbeN;
    int zzbge;
    int zzbgf;
    
    static
    {
      zzbeM.put("max", FastJsonResponse.Field.zzi("max", 2));
      zzbeM.put("min", FastJsonResponse.Field.zzi("min", 3));
    }
    
    public AgeRangeEntity()
    {
      this.mVersionCode = 1;
      this.zzbeN = new HashSet();
    }
    
    AgeRangeEntity(Set<Integer> paramSet, int paramInt1, int paramInt2, int paramInt3)
    {
      this.zzbeN = paramSet;
      this.mVersionCode = paramInt1;
      this.zzbge = paramInt2;
      this.zzbgf = paramInt3;
    }
    
    public int describeContents()
    {
      zzb localzzb = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof AgeRangeEntity)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (AgeRangeEntity)paramObject;
      Iterator localIterator = zzbeM.values().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (zza(localField))
        {
          if (((AgeRangeEntity)paramObject).zza(localField))
          {
            if (!zzb(localField).equals(((AgeRangeEntity)paramObject).zzb(localField))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((AgeRangeEntity)paramObject).zza(localField)) {
          return false;
        }
      }
      return true;
    }
    
    public int getMax()
    {
      return this.zzbge;
    }
    
    public int getMin()
    {
      return this.zzbgf;
    }
    
    public boolean hasMax()
    {
      return this.zzbeN.contains(Integer.valueOf(2));
    }
    
    public boolean hasMin()
    {
      return this.zzbeN.contains(Integer.valueOf(3));
    }
    
    public int hashCode()
    {
      Iterator localIterator = zzbeM.values().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (!zza(localField)) {
          break label68;
        }
        int j = localField.zzrs();
        i = zzb(localField).hashCode() + (i + j);
      }
      label68:
      for (;;)
      {
        break;
        return i;
      }
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzb localzzb = CREATOR;
      zzb.zza(this, paramParcel, paramInt);
    }
    
    public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
    {
      return zzbeM;
    }
    
    public AgeRangeEntity zzFq()
    {
      return this;
    }
    
    protected boolean zza(FastJsonResponse.Field paramField)
    {
      return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
    }
    
    protected Object zzb(FastJsonResponse.Field paramField)
    {
      switch (paramField.zzrs())
      {
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
      case 2: 
        return Integer.valueOf(this.zzbge);
      }
      return Integer.valueOf(this.zzbgf);
    }
  }
  
  public static final class CoverEntity
    extends FastSafeParcelableJsonResponse
    implements Person.Cover
  {
    public static final zzc CREATOR = new zzc();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
    final int mVersionCode;
    final Set<Integer> zzbeN;
    CoverInfoEntity zzbgg;
    CoverPhotoEntity zzbgh;
    int zzbgi;
    
    static
    {
      zzbeM.put("coverInfo", FastJsonResponse.Field.zza("coverInfo", 2, CoverInfoEntity.class));
      zzbeM.put("coverPhoto", FastJsonResponse.Field.zza("coverPhoto", 3, CoverPhotoEntity.class));
      zzbeM.put("layout", FastJsonResponse.Field.zza("layout", 4, new StringToIntConverter().zzh("banner", 0), false));
    }
    
    public CoverEntity()
    {
      this.mVersionCode = 1;
      this.zzbeN = new HashSet();
    }
    
    CoverEntity(Set<Integer> paramSet, int paramInt1, CoverInfoEntity paramCoverInfoEntity, CoverPhotoEntity paramCoverPhotoEntity, int paramInt2)
    {
      this.zzbeN = paramSet;
      this.mVersionCode = paramInt1;
      this.zzbgg = paramCoverInfoEntity;
      this.zzbgh = paramCoverPhotoEntity;
      this.zzbgi = paramInt2;
    }
    
    public int describeContents()
    {
      zzc localzzc = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof CoverEntity)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (CoverEntity)paramObject;
      Iterator localIterator = zzbeM.values().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (zza(localField))
        {
          if (((CoverEntity)paramObject).zza(localField))
          {
            if (!zzb(localField).equals(((CoverEntity)paramObject).zzb(localField))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((CoverEntity)paramObject).zza(localField)) {
          return false;
        }
      }
      return true;
    }
    
    public Person.Cover.CoverInfo getCoverInfo()
    {
      return this.zzbgg;
    }
    
    public Person.Cover.CoverPhoto getCoverPhoto()
    {
      return this.zzbgh;
    }
    
    public int getLayout()
    {
      return this.zzbgi;
    }
    
    public boolean hasCoverInfo()
    {
      return this.zzbeN.contains(Integer.valueOf(2));
    }
    
    public boolean hasCoverPhoto()
    {
      return this.zzbeN.contains(Integer.valueOf(3));
    }
    
    public boolean hasLayout()
    {
      return this.zzbeN.contains(Integer.valueOf(4));
    }
    
    public int hashCode()
    {
      Iterator localIterator = zzbeM.values().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (!zza(localField)) {
          break label68;
        }
        int j = localField.zzrs();
        i = zzb(localField).hashCode() + (i + j);
      }
      label68:
      for (;;)
      {
        break;
        return i;
      }
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzc localzzc = CREATOR;
      zzc.zza(this, paramParcel, paramInt);
    }
    
    public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
    {
      return zzbeM;
    }
    
    public CoverEntity zzFr()
    {
      return this;
    }
    
    protected boolean zza(FastJsonResponse.Field paramField)
    {
      return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
    }
    
    protected Object zzb(FastJsonResponse.Field paramField)
    {
      switch (paramField.zzrs())
      {
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
      case 2: 
        return this.zzbgg;
      case 3: 
        return this.zzbgh;
      }
      return Integer.valueOf(this.zzbgi);
    }
    
    public static final class CoverInfoEntity
      extends FastSafeParcelableJsonResponse
      implements Person.Cover.CoverInfo
    {
      public static final zzd CREATOR = new zzd();
      private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
      final int mVersionCode;
      final Set<Integer> zzbeN;
      int zzbgj;
      int zzbgk;
      
      static
      {
        zzbeM.put("leftImageOffset", FastJsonResponse.Field.zzi("leftImageOffset", 2));
        zzbeM.put("topImageOffset", FastJsonResponse.Field.zzi("topImageOffset", 3));
      }
      
      public CoverInfoEntity()
      {
        this.mVersionCode = 1;
        this.zzbeN = new HashSet();
      }
      
      CoverInfoEntity(Set<Integer> paramSet, int paramInt1, int paramInt2, int paramInt3)
      {
        this.zzbeN = paramSet;
        this.mVersionCode = paramInt1;
        this.zzbgj = paramInt2;
        this.zzbgk = paramInt3;
      }
      
      public int describeContents()
      {
        zzd localzzd = CREATOR;
        return 0;
      }
      
      public boolean equals(Object paramObject)
      {
        if (!(paramObject instanceof CoverInfoEntity)) {
          return false;
        }
        if (this == paramObject) {
          return true;
        }
        paramObject = (CoverInfoEntity)paramObject;
        Iterator localIterator = zzbeM.values().iterator();
        while (localIterator.hasNext())
        {
          FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
          if (zza(localField))
          {
            if (((CoverInfoEntity)paramObject).zza(localField))
            {
              if (!zzb(localField).equals(((CoverInfoEntity)paramObject).zzb(localField))) {
                return false;
              }
            }
            else {
              return false;
            }
          }
          else if (((CoverInfoEntity)paramObject).zza(localField)) {
            return false;
          }
        }
        return true;
      }
      
      public int getLeftImageOffset()
      {
        return this.zzbgj;
      }
      
      public int getTopImageOffset()
      {
        return this.zzbgk;
      }
      
      public boolean hasLeftImageOffset()
      {
        return this.zzbeN.contains(Integer.valueOf(2));
      }
      
      public boolean hasTopImageOffset()
      {
        return this.zzbeN.contains(Integer.valueOf(3));
      }
      
      public int hashCode()
      {
        Iterator localIterator = zzbeM.values().iterator();
        int i = 0;
        if (localIterator.hasNext())
        {
          FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
          if (!zza(localField)) {
            break label68;
          }
          int j = localField.zzrs();
          i = zzb(localField).hashCode() + (i + j);
        }
        label68:
        for (;;)
        {
          break;
          return i;
        }
      }
      
      public boolean isDataValid()
      {
        return true;
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        zzd localzzd = CREATOR;
        zzd.zza(this, paramParcel, paramInt);
      }
      
      public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
      {
        return zzbeM;
      }
      
      public CoverInfoEntity zzFs()
      {
        return this;
      }
      
      protected boolean zza(FastJsonResponse.Field paramField)
      {
        return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
      }
      
      protected Object zzb(FastJsonResponse.Field paramField)
      {
        switch (paramField.zzrs())
        {
        default: 
          throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
        case 2: 
          return Integer.valueOf(this.zzbgj);
        }
        return Integer.valueOf(this.zzbgk);
      }
    }
    
    public static final class CoverPhotoEntity
      extends FastSafeParcelableJsonResponse
      implements Person.Cover.CoverPhoto
    {
      public static final zze CREATOR = new zze();
      private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
      final int mVersionCode;
      String zzF;
      final Set<Integer> zzbeN;
      int zzoG;
      int zzoH;
      
      static
      {
        zzbeM.put("height", FastJsonResponse.Field.zzi("height", 2));
        zzbeM.put("url", FastJsonResponse.Field.zzl("url", 3));
        zzbeM.put("width", FastJsonResponse.Field.zzi("width", 4));
      }
      
      public CoverPhotoEntity()
      {
        this.mVersionCode = 1;
        this.zzbeN = new HashSet();
      }
      
      CoverPhotoEntity(Set<Integer> paramSet, int paramInt1, int paramInt2, String paramString, int paramInt3)
      {
        this.zzbeN = paramSet;
        this.mVersionCode = paramInt1;
        this.zzoH = paramInt2;
        this.zzF = paramString;
        this.zzoG = paramInt3;
      }
      
      public int describeContents()
      {
        zze localzze = CREATOR;
        return 0;
      }
      
      public boolean equals(Object paramObject)
      {
        if (!(paramObject instanceof CoverPhotoEntity)) {
          return false;
        }
        if (this == paramObject) {
          return true;
        }
        paramObject = (CoverPhotoEntity)paramObject;
        Iterator localIterator = zzbeM.values().iterator();
        while (localIterator.hasNext())
        {
          FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
          if (zza(localField))
          {
            if (((CoverPhotoEntity)paramObject).zza(localField))
            {
              if (!zzb(localField).equals(((CoverPhotoEntity)paramObject).zzb(localField))) {
                return false;
              }
            }
            else {
              return false;
            }
          }
          else if (((CoverPhotoEntity)paramObject).zza(localField)) {
            return false;
          }
        }
        return true;
      }
      
      public int getHeight()
      {
        return this.zzoH;
      }
      
      public String getUrl()
      {
        return this.zzF;
      }
      
      public int getWidth()
      {
        return this.zzoG;
      }
      
      public boolean hasHeight()
      {
        return this.zzbeN.contains(Integer.valueOf(2));
      }
      
      public boolean hasUrl()
      {
        return this.zzbeN.contains(Integer.valueOf(3));
      }
      
      public boolean hasWidth()
      {
        return this.zzbeN.contains(Integer.valueOf(4));
      }
      
      public int hashCode()
      {
        Iterator localIterator = zzbeM.values().iterator();
        int i = 0;
        if (localIterator.hasNext())
        {
          FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
          if (!zza(localField)) {
            break label68;
          }
          int j = localField.zzrs();
          i = zzb(localField).hashCode() + (i + j);
        }
        label68:
        for (;;)
        {
          break;
          return i;
        }
      }
      
      public boolean isDataValid()
      {
        return true;
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        zze localzze = CREATOR;
        zze.zza(this, paramParcel, paramInt);
      }
      
      public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
      {
        return zzbeM;
      }
      
      public CoverPhotoEntity zzFt()
      {
        return this;
      }
      
      protected boolean zza(FastJsonResponse.Field paramField)
      {
        return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
      }
      
      protected Object zzb(FastJsonResponse.Field paramField)
      {
        switch (paramField.zzrs())
        {
        default: 
          throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
        case 2: 
          return Integer.valueOf(this.zzoH);
        case 3: 
          return this.zzF;
        }
        return Integer.valueOf(this.zzoG);
      }
    }
  }
  
  public static final class ImageEntity
    extends FastSafeParcelableJsonResponse
    implements Person.Image
  {
    public static final zzf CREATOR = new zzf();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
    final int mVersionCode;
    String zzF;
    final Set<Integer> zzbeN;
    
    static
    {
      zzbeM.put("url", FastJsonResponse.Field.zzl("url", 2));
    }
    
    public ImageEntity()
    {
      this.mVersionCode = 1;
      this.zzbeN = new HashSet();
    }
    
    public ImageEntity(String paramString)
    {
      this.zzbeN = new HashSet();
      this.mVersionCode = 1;
      this.zzF = paramString;
      this.zzbeN.add(Integer.valueOf(2));
    }
    
    ImageEntity(Set<Integer> paramSet, int paramInt, String paramString)
    {
      this.zzbeN = paramSet;
      this.mVersionCode = paramInt;
      this.zzF = paramString;
    }
    
    public int describeContents()
    {
      zzf localzzf = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof ImageEntity)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (ImageEntity)paramObject;
      Iterator localIterator = zzbeM.values().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (zza(localField))
        {
          if (((ImageEntity)paramObject).zza(localField))
          {
            if (!zzb(localField).equals(((ImageEntity)paramObject).zzb(localField))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((ImageEntity)paramObject).zza(localField)) {
          return false;
        }
      }
      return true;
    }
    
    public String getUrl()
    {
      return this.zzF;
    }
    
    public boolean hasUrl()
    {
      return this.zzbeN.contains(Integer.valueOf(2));
    }
    
    public int hashCode()
    {
      Iterator localIterator = zzbeM.values().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (!zza(localField)) {
          break label68;
        }
        int j = localField.zzrs();
        i = zzb(localField).hashCode() + (i + j);
      }
      label68:
      for (;;)
      {
        break;
        return i;
      }
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzf localzzf = CREATOR;
      zzf.zza(this, paramParcel, paramInt);
    }
    
    public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
    {
      return zzbeM;
    }
    
    public ImageEntity zzFu()
    {
      return this;
    }
    
    protected boolean zza(FastJsonResponse.Field paramField)
    {
      return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
    }
    
    protected Object zzb(FastJsonResponse.Field paramField)
    {
      switch (paramField.zzrs())
      {
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
      }
      return this.zzF;
    }
  }
  
  public static final class NameEntity
    extends FastSafeParcelableJsonResponse
    implements Person.Name
  {
    public static final zzg CREATOR = new zzg();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
    final int mVersionCode;
    final Set<Integer> zzbeN;
    String zzbfl;
    String zzbfo;
    String zzbgl;
    String zzbgm;
    String zzbgn;
    String zzbgo;
    
    static
    {
      zzbeM.put("familyName", FastJsonResponse.Field.zzl("familyName", 2));
      zzbeM.put("formatted", FastJsonResponse.Field.zzl("formatted", 3));
      zzbeM.put("givenName", FastJsonResponse.Field.zzl("givenName", 4));
      zzbeM.put("honorificPrefix", FastJsonResponse.Field.zzl("honorificPrefix", 5));
      zzbeM.put("honorificSuffix", FastJsonResponse.Field.zzl("honorificSuffix", 6));
      zzbeM.put("middleName", FastJsonResponse.Field.zzl("middleName", 7));
    }
    
    public NameEntity()
    {
      this.mVersionCode = 1;
      this.zzbeN = new HashSet();
    }
    
    NameEntity(Set<Integer> paramSet, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
    {
      this.zzbeN = paramSet;
      this.mVersionCode = paramInt;
      this.zzbfl = paramString1;
      this.zzbgl = paramString2;
      this.zzbfo = paramString3;
      this.zzbgm = paramString4;
      this.zzbgn = paramString5;
      this.zzbgo = paramString6;
    }
    
    public int describeContents()
    {
      zzg localzzg = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof NameEntity)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (NameEntity)paramObject;
      Iterator localIterator = zzbeM.values().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (zza(localField))
        {
          if (((NameEntity)paramObject).zza(localField))
          {
            if (!zzb(localField).equals(((NameEntity)paramObject).zzb(localField))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((NameEntity)paramObject).zza(localField)) {
          return false;
        }
      }
      return true;
    }
    
    public String getFamilyName()
    {
      return this.zzbfl;
    }
    
    public String getFormatted()
    {
      return this.zzbgl;
    }
    
    public String getGivenName()
    {
      return this.zzbfo;
    }
    
    public String getHonorificPrefix()
    {
      return this.zzbgm;
    }
    
    public String getHonorificSuffix()
    {
      return this.zzbgn;
    }
    
    public String getMiddleName()
    {
      return this.zzbgo;
    }
    
    public boolean hasFamilyName()
    {
      return this.zzbeN.contains(Integer.valueOf(2));
    }
    
    public boolean hasFormatted()
    {
      return this.zzbeN.contains(Integer.valueOf(3));
    }
    
    public boolean hasGivenName()
    {
      return this.zzbeN.contains(Integer.valueOf(4));
    }
    
    public boolean hasHonorificPrefix()
    {
      return this.zzbeN.contains(Integer.valueOf(5));
    }
    
    public boolean hasHonorificSuffix()
    {
      return this.zzbeN.contains(Integer.valueOf(6));
    }
    
    public boolean hasMiddleName()
    {
      return this.zzbeN.contains(Integer.valueOf(7));
    }
    
    public int hashCode()
    {
      Iterator localIterator = zzbeM.values().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (!zza(localField)) {
          break label68;
        }
        int j = localField.zzrs();
        i = zzb(localField).hashCode() + (i + j);
      }
      label68:
      for (;;)
      {
        break;
        return i;
      }
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzg localzzg = CREATOR;
      zzg.zza(this, paramParcel, paramInt);
    }
    
    public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
    {
      return zzbeM;
    }
    
    public NameEntity zzFv()
    {
      return this;
    }
    
    protected boolean zza(FastJsonResponse.Field paramField)
    {
      return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
    }
    
    protected Object zzb(FastJsonResponse.Field paramField)
    {
      switch (paramField.zzrs())
      {
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
      case 2: 
        return this.zzbfl;
      case 3: 
        return this.zzbgl;
      case 4: 
        return this.zzbfo;
      case 5: 
        return this.zzbgm;
      case 6: 
        return this.zzbgn;
      }
      return this.zzbgo;
    }
  }
  
  public static final class OrganizationsEntity
    extends FastSafeParcelableJsonResponse
    implements Person.Organizations
  {
    public static final zzh CREATOR = new zzh();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
    String mName;
    final int mVersionCode;
    int zzabB;
    String zzapg;
    String zzaxl;
    final Set<Integer> zzbeN;
    String zzbfA;
    String zzbfk;
    String zzbgp;
    String zzbgq;
    boolean zzbgr;
    
    static
    {
      zzbeM.put("department", FastJsonResponse.Field.zzl("department", 2));
      zzbeM.put("description", FastJsonResponse.Field.zzl("description", 3));
      zzbeM.put("endDate", FastJsonResponse.Field.zzl("endDate", 4));
      zzbeM.put("location", FastJsonResponse.Field.zzl("location", 5));
      zzbeM.put("name", FastJsonResponse.Field.zzl("name", 6));
      zzbeM.put("primary", FastJsonResponse.Field.zzk("primary", 7));
      zzbeM.put("startDate", FastJsonResponse.Field.zzl("startDate", 8));
      zzbeM.put("title", FastJsonResponse.Field.zzl("title", 9));
      zzbeM.put("type", FastJsonResponse.Field.zza("type", 10, new StringToIntConverter().zzh("work", 0).zzh("school", 1), false));
    }
    
    public OrganizationsEntity()
    {
      this.mVersionCode = 1;
      this.zzbeN = new HashSet();
    }
    
    OrganizationsEntity(Set<Integer> paramSet, int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, String paramString6, String paramString7, int paramInt2)
    {
      this.zzbeN = paramSet;
      this.mVersionCode = paramInt1;
      this.zzbgp = paramString1;
      this.zzaxl = paramString2;
      this.zzbfk = paramString3;
      this.zzbgq = paramString4;
      this.mName = paramString5;
      this.zzbgr = paramBoolean;
      this.zzbfA = paramString6;
      this.zzapg = paramString7;
      this.zzabB = paramInt2;
    }
    
    public int describeContents()
    {
      zzh localzzh = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof OrganizationsEntity)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (OrganizationsEntity)paramObject;
      Iterator localIterator = zzbeM.values().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (zza(localField))
        {
          if (((OrganizationsEntity)paramObject).zza(localField))
          {
            if (!zzb(localField).equals(((OrganizationsEntity)paramObject).zzb(localField))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((OrganizationsEntity)paramObject).zza(localField)) {
          return false;
        }
      }
      return true;
    }
    
    public String getDepartment()
    {
      return this.zzbgp;
    }
    
    public String getDescription()
    {
      return this.zzaxl;
    }
    
    public String getEndDate()
    {
      return this.zzbfk;
    }
    
    public String getLocation()
    {
      return this.zzbgq;
    }
    
    public String getName()
    {
      return this.mName;
    }
    
    public String getStartDate()
    {
      return this.zzbfA;
    }
    
    public String getTitle()
    {
      return this.zzapg;
    }
    
    public int getType()
    {
      return this.zzabB;
    }
    
    public boolean hasDepartment()
    {
      return this.zzbeN.contains(Integer.valueOf(2));
    }
    
    public boolean hasDescription()
    {
      return this.zzbeN.contains(Integer.valueOf(3));
    }
    
    public boolean hasEndDate()
    {
      return this.zzbeN.contains(Integer.valueOf(4));
    }
    
    public boolean hasLocation()
    {
      return this.zzbeN.contains(Integer.valueOf(5));
    }
    
    public boolean hasName()
    {
      return this.zzbeN.contains(Integer.valueOf(6));
    }
    
    public boolean hasPrimary()
    {
      return this.zzbeN.contains(Integer.valueOf(7));
    }
    
    public boolean hasStartDate()
    {
      return this.zzbeN.contains(Integer.valueOf(8));
    }
    
    public boolean hasTitle()
    {
      return this.zzbeN.contains(Integer.valueOf(9));
    }
    
    public boolean hasType()
    {
      return this.zzbeN.contains(Integer.valueOf(10));
    }
    
    public int hashCode()
    {
      Iterator localIterator = zzbeM.values().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (!zza(localField)) {
          break label68;
        }
        int j = localField.zzrs();
        i = zzb(localField).hashCode() + (i + j);
      }
      label68:
      for (;;)
      {
        break;
        return i;
      }
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public boolean isPrimary()
    {
      return this.zzbgr;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzh localzzh = CREATOR;
      zzh.zza(this, paramParcel, paramInt);
    }
    
    public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
    {
      return zzbeM;
    }
    
    public OrganizationsEntity zzFw()
    {
      return this;
    }
    
    protected boolean zza(FastJsonResponse.Field paramField)
    {
      return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
    }
    
    protected Object zzb(FastJsonResponse.Field paramField)
    {
      switch (paramField.zzrs())
      {
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
      case 2: 
        return this.zzbgp;
      case 3: 
        return this.zzaxl;
      case 4: 
        return this.zzbfk;
      case 5: 
        return this.zzbgq;
      case 6: 
        return this.mName;
      case 7: 
        return Boolean.valueOf(this.zzbgr);
      case 8: 
        return this.zzbfA;
      case 9: 
        return this.zzapg;
      }
      return Integer.valueOf(this.zzabB);
    }
  }
  
  public static final class PlacesLivedEntity
    extends FastSafeParcelableJsonResponse
    implements Person.PlacesLived
  {
    public static final zzi CREATOR = new zzi();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
    String mValue;
    final int mVersionCode;
    final Set<Integer> zzbeN;
    boolean zzbgr;
    
    static
    {
      zzbeM.put("primary", FastJsonResponse.Field.zzk("primary", 2));
      zzbeM.put("value", FastJsonResponse.Field.zzl("value", 3));
    }
    
    public PlacesLivedEntity()
    {
      this.mVersionCode = 1;
      this.zzbeN = new HashSet();
    }
    
    PlacesLivedEntity(Set<Integer> paramSet, int paramInt, boolean paramBoolean, String paramString)
    {
      this.zzbeN = paramSet;
      this.mVersionCode = paramInt;
      this.zzbgr = paramBoolean;
      this.mValue = paramString;
    }
    
    public int describeContents()
    {
      zzi localzzi = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof PlacesLivedEntity)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (PlacesLivedEntity)paramObject;
      Iterator localIterator = zzbeM.values().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (zza(localField))
        {
          if (((PlacesLivedEntity)paramObject).zza(localField))
          {
            if (!zzb(localField).equals(((PlacesLivedEntity)paramObject).zzb(localField))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((PlacesLivedEntity)paramObject).zza(localField)) {
          return false;
        }
      }
      return true;
    }
    
    public String getValue()
    {
      return this.mValue;
    }
    
    public boolean hasPrimary()
    {
      return this.zzbeN.contains(Integer.valueOf(2));
    }
    
    public boolean hasValue()
    {
      return this.zzbeN.contains(Integer.valueOf(3));
    }
    
    public int hashCode()
    {
      Iterator localIterator = zzbeM.values().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (!zza(localField)) {
          break label68;
        }
        int j = localField.zzrs();
        i = zzb(localField).hashCode() + (i + j);
      }
      label68:
      for (;;)
      {
        break;
        return i;
      }
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public boolean isPrimary()
    {
      return this.zzbgr;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzi localzzi = CREATOR;
      zzi.zza(this, paramParcel, paramInt);
    }
    
    public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
    {
      return zzbeM;
    }
    
    public PlacesLivedEntity zzFx()
    {
      return this;
    }
    
    protected boolean zza(FastJsonResponse.Field paramField)
    {
      return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
    }
    
    protected Object zzb(FastJsonResponse.Field paramField)
    {
      switch (paramField.zzrs())
      {
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
      case 2: 
        return Boolean.valueOf(this.zzbgr);
      }
      return this.mValue;
    }
  }
  
  public static final class UrlsEntity
    extends FastSafeParcelableJsonResponse
    implements Person.Urls
  {
    public static final zzj CREATOR = new zzj();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
    String mValue;
    final int mVersionCode;
    String zzaUO;
    int zzabB;
    final Set<Integer> zzbeN;
    private final int zzbgs = 4;
    
    static
    {
      zzbeM.put("label", FastJsonResponse.Field.zzl("label", 5));
      zzbeM.put("type", FastJsonResponse.Field.zza("type", 6, new StringToIntConverter().zzh("home", 0).zzh("work", 1).zzh("blog", 2).zzh("profile", 3).zzh("other", 4).zzh("otherProfile", 5).zzh("contributor", 6).zzh("website", 7), false));
      zzbeM.put("value", FastJsonResponse.Field.zzl("value", 4));
    }
    
    public UrlsEntity()
    {
      this.mVersionCode = 1;
      this.zzbeN = new HashSet();
    }
    
    UrlsEntity(Set<Integer> paramSet, int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3)
    {
      this.zzbeN = paramSet;
      this.mVersionCode = paramInt1;
      this.zzaUO = paramString1;
      this.zzabB = paramInt2;
      this.mValue = paramString2;
    }
    
    public int describeContents()
    {
      zzj localzzj = CREATOR;
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof UrlsEntity)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (UrlsEntity)paramObject;
      Iterator localIterator = zzbeM.values().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (zza(localField))
        {
          if (((UrlsEntity)paramObject).zza(localField))
          {
            if (!zzb(localField).equals(((UrlsEntity)paramObject).zzb(localField))) {
              return false;
            }
          }
          else {
            return false;
          }
        }
        else if (((UrlsEntity)paramObject).zza(localField)) {
          return false;
        }
      }
      return true;
    }
    
    public String getLabel()
    {
      return this.zzaUO;
    }
    
    public int getType()
    {
      return this.zzabB;
    }
    
    public String getValue()
    {
      return this.mValue;
    }
    
    public boolean hasLabel()
    {
      return this.zzbeN.contains(Integer.valueOf(5));
    }
    
    public boolean hasType()
    {
      return this.zzbeN.contains(Integer.valueOf(6));
    }
    
    public boolean hasValue()
    {
      return this.zzbeN.contains(Integer.valueOf(4));
    }
    
    public int hashCode()
    {
      Iterator localIterator = zzbeM.values().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
        if (!zza(localField)) {
          break label68;
        }
        int j = localField.zzrs();
        i = zzb(localField).hashCode() + (i + j);
      }
      label68:
      for (;;)
      {
        break;
        return i;
      }
    }
    
    public boolean isDataValid()
    {
      return true;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzj localzzj = CREATOR;
      zzj.zza(this, paramParcel, paramInt);
    }
    
    public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
    {
      return zzbeM;
    }
    
    @Deprecated
    public int zzFy()
    {
      return 4;
    }
    
    public UrlsEntity zzFz()
    {
      return this;
    }
    
    protected boolean zza(FastJsonResponse.Field paramField)
    {
      return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
    }
    
    protected Object zzb(FastJsonResponse.Field paramField)
    {
      switch (paramField.zzrs())
      {
      default: 
        throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
      case 5: 
        return this.zzaUO;
      case 6: 
        return Integer.valueOf(this.zzabB);
      }
      return this.mValue;
    }
  }
  
  public static class zza
  {
    public static int zzfH(String paramString)
    {
      if (paramString.equals("person")) {
        return 0;
      }
      if (paramString.equals("page")) {
        return 1;
      }
      throw new IllegalArgumentException("Unknown objectType string: " + paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\plus\internal\model\people\PersonEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */