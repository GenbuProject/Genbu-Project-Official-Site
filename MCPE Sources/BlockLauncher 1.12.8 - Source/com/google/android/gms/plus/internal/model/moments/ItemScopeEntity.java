package com.google.android.gms.plus.internal.model.moments;

import android.os.Parcel;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.model.moments.ItemScope;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class ItemScopeEntity
  extends FastSafeParcelableJsonResponse
  implements ItemScope
{
  public static final zza CREATOR = new zza();
  private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
  String mName;
  final int mVersionCode;
  String zzF;
  String zzJN;
  double zzaNF;
  double zzaNG;
  String zzaxl;
  final Set<Integer> zzbeN;
  ItemScopeEntity zzbeO;
  List<String> zzbeP;
  ItemScopeEntity zzbeQ;
  String zzbeR;
  String zzbeS;
  String zzbeT;
  List<ItemScopeEntity> zzbeU;
  int zzbeV;
  List<ItemScopeEntity> zzbeW;
  ItemScopeEntity zzbeX;
  List<ItemScopeEntity> zzbeY;
  String zzbeZ;
  String zzbfA;
  String zzbfB;
  String zzbfC;
  ItemScopeEntity zzbfD;
  String zzbfE;
  String zzbfF;
  String zzbfG;
  String zzbfH;
  String zzbfa;
  ItemScopeEntity zzbfb;
  String zzbfc;
  String zzbfd;
  List<ItemScopeEntity> zzbfe;
  String zzbff;
  String zzbfg;
  String zzbfh;
  String zzbfi;
  String zzbfj;
  String zzbfk;
  String zzbfl;
  String zzbfm;
  ItemScopeEntity zzbfn;
  String zzbfo;
  String zzbfp;
  String zzbfq;
  ItemScopeEntity zzbfr;
  ItemScopeEntity zzbfs;
  ItemScopeEntity zzbft;
  List<ItemScopeEntity> zzbfu;
  String zzbfv;
  String zzbfw;
  String zzbfx;
  String zzbfy;
  ItemScopeEntity zzbfz;
  String zztZ;
  String zzyv;
  
  static
  {
    zzbeM.put("about", FastJsonResponse.Field.zza("about", 2, ItemScopeEntity.class));
    zzbeM.put("additionalName", FastJsonResponse.Field.zzm("additionalName", 3));
    zzbeM.put("address", FastJsonResponse.Field.zza("address", 4, ItemScopeEntity.class));
    zzbeM.put("addressCountry", FastJsonResponse.Field.zzl("addressCountry", 5));
    zzbeM.put("addressLocality", FastJsonResponse.Field.zzl("addressLocality", 6));
    zzbeM.put("addressRegion", FastJsonResponse.Field.zzl("addressRegion", 7));
    zzbeM.put("associated_media", FastJsonResponse.Field.zzb("associated_media", 8, ItemScopeEntity.class));
    zzbeM.put("attendeeCount", FastJsonResponse.Field.zzi("attendeeCount", 9));
    zzbeM.put("attendees", FastJsonResponse.Field.zzb("attendees", 10, ItemScopeEntity.class));
    zzbeM.put("audio", FastJsonResponse.Field.zza("audio", 11, ItemScopeEntity.class));
    zzbeM.put("author", FastJsonResponse.Field.zzb("author", 12, ItemScopeEntity.class));
    zzbeM.put("bestRating", FastJsonResponse.Field.zzl("bestRating", 13));
    zzbeM.put("birthDate", FastJsonResponse.Field.zzl("birthDate", 14));
    zzbeM.put("byArtist", FastJsonResponse.Field.zza("byArtist", 15, ItemScopeEntity.class));
    zzbeM.put("caption", FastJsonResponse.Field.zzl("caption", 16));
    zzbeM.put("contentSize", FastJsonResponse.Field.zzl("contentSize", 17));
    zzbeM.put("contentUrl", FastJsonResponse.Field.zzl("contentUrl", 18));
    zzbeM.put("contributor", FastJsonResponse.Field.zzb("contributor", 19, ItemScopeEntity.class));
    zzbeM.put("dateCreated", FastJsonResponse.Field.zzl("dateCreated", 20));
    zzbeM.put("dateModified", FastJsonResponse.Field.zzl("dateModified", 21));
    zzbeM.put("datePublished", FastJsonResponse.Field.zzl("datePublished", 22));
    zzbeM.put("description", FastJsonResponse.Field.zzl("description", 23));
    zzbeM.put("duration", FastJsonResponse.Field.zzl("duration", 24));
    zzbeM.put("embedUrl", FastJsonResponse.Field.zzl("embedUrl", 25));
    zzbeM.put("endDate", FastJsonResponse.Field.zzl("endDate", 26));
    zzbeM.put("familyName", FastJsonResponse.Field.zzl("familyName", 27));
    zzbeM.put("gender", FastJsonResponse.Field.zzl("gender", 28));
    zzbeM.put("geo", FastJsonResponse.Field.zza("geo", 29, ItemScopeEntity.class));
    zzbeM.put("givenName", FastJsonResponse.Field.zzl("givenName", 30));
    zzbeM.put("height", FastJsonResponse.Field.zzl("height", 31));
    zzbeM.put("id", FastJsonResponse.Field.zzl("id", 32));
    zzbeM.put("image", FastJsonResponse.Field.zzl("image", 33));
    zzbeM.put("inAlbum", FastJsonResponse.Field.zza("inAlbum", 34, ItemScopeEntity.class));
    zzbeM.put("latitude", FastJsonResponse.Field.zzj("latitude", 36));
    zzbeM.put("location", FastJsonResponse.Field.zza("location", 37, ItemScopeEntity.class));
    zzbeM.put("longitude", FastJsonResponse.Field.zzj("longitude", 38));
    zzbeM.put("name", FastJsonResponse.Field.zzl("name", 39));
    zzbeM.put("partOfTVSeries", FastJsonResponse.Field.zza("partOfTVSeries", 40, ItemScopeEntity.class));
    zzbeM.put("performers", FastJsonResponse.Field.zzb("performers", 41, ItemScopeEntity.class));
    zzbeM.put("playerType", FastJsonResponse.Field.zzl("playerType", 42));
    zzbeM.put("postOfficeBoxNumber", FastJsonResponse.Field.zzl("postOfficeBoxNumber", 43));
    zzbeM.put("postalCode", FastJsonResponse.Field.zzl("postalCode", 44));
    zzbeM.put("ratingValue", FastJsonResponse.Field.zzl("ratingValue", 45));
    zzbeM.put("reviewRating", FastJsonResponse.Field.zza("reviewRating", 46, ItemScopeEntity.class));
    zzbeM.put("startDate", FastJsonResponse.Field.zzl("startDate", 47));
    zzbeM.put("streetAddress", FastJsonResponse.Field.zzl("streetAddress", 48));
    zzbeM.put("text", FastJsonResponse.Field.zzl("text", 49));
    zzbeM.put("thumbnail", FastJsonResponse.Field.zza("thumbnail", 50, ItemScopeEntity.class));
    zzbeM.put("thumbnailUrl", FastJsonResponse.Field.zzl("thumbnailUrl", 51));
    zzbeM.put("tickerSymbol", FastJsonResponse.Field.zzl("tickerSymbol", 52));
    zzbeM.put("type", FastJsonResponse.Field.zzl("type", 53));
    zzbeM.put("url", FastJsonResponse.Field.zzl("url", 54));
    zzbeM.put("width", FastJsonResponse.Field.zzl("width", 55));
    zzbeM.put("worstRating", FastJsonResponse.Field.zzl("worstRating", 56));
  }
  
  public ItemScopeEntity()
  {
    this.mVersionCode = 1;
    this.zzbeN = new HashSet();
  }
  
  ItemScopeEntity(Set<Integer> paramSet, int paramInt1, ItemScopeEntity paramItemScopeEntity1, List<String> paramList, ItemScopeEntity paramItemScopeEntity2, String paramString1, String paramString2, String paramString3, List<ItemScopeEntity> paramList1, int paramInt2, List<ItemScopeEntity> paramList2, ItemScopeEntity paramItemScopeEntity3, List<ItemScopeEntity> paramList3, String paramString4, String paramString5, ItemScopeEntity paramItemScopeEntity4, String paramString6, String paramString7, String paramString8, List<ItemScopeEntity> paramList4, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, ItemScopeEntity paramItemScopeEntity5, String paramString18, String paramString19, String paramString20, String paramString21, ItemScopeEntity paramItemScopeEntity6, double paramDouble1, ItemScopeEntity paramItemScopeEntity7, double paramDouble2, String paramString22, ItemScopeEntity paramItemScopeEntity8, List<ItemScopeEntity> paramList5, String paramString23, String paramString24, String paramString25, String paramString26, ItemScopeEntity paramItemScopeEntity9, String paramString27, String paramString28, String paramString29, ItemScopeEntity paramItemScopeEntity10, String paramString30, String paramString31, String paramString32, String paramString33, String paramString34, String paramString35)
  {
    this.zzbeN = paramSet;
    this.mVersionCode = paramInt1;
    this.zzbeO = paramItemScopeEntity1;
    this.zzbeP = paramList;
    this.zzbeQ = paramItemScopeEntity2;
    this.zzbeR = paramString1;
    this.zzbeS = paramString2;
    this.zzbeT = paramString3;
    this.zzbeU = paramList1;
    this.zzbeV = paramInt2;
    this.zzbeW = paramList2;
    this.zzbeX = paramItemScopeEntity3;
    this.zzbeY = paramList3;
    this.zzbeZ = paramString4;
    this.zzbfa = paramString5;
    this.zzbfb = paramItemScopeEntity4;
    this.zzbfc = paramString6;
    this.zzbfd = paramString7;
    this.zztZ = paramString8;
    this.zzbfe = paramList4;
    this.zzbff = paramString9;
    this.zzbfg = paramString10;
    this.zzbfh = paramString11;
    this.zzaxl = paramString12;
    this.zzbfi = paramString13;
    this.zzbfj = paramString14;
    this.zzbfk = paramString15;
    this.zzbfl = paramString16;
    this.zzbfm = paramString17;
    this.zzbfn = paramItemScopeEntity5;
    this.zzbfo = paramString18;
    this.zzbfp = paramString19;
    this.zzyv = paramString20;
    this.zzbfq = paramString21;
    this.zzbfr = paramItemScopeEntity6;
    this.zzaNF = paramDouble1;
    this.zzbfs = paramItemScopeEntity7;
    this.zzaNG = paramDouble2;
    this.mName = paramString22;
    this.zzbft = paramItemScopeEntity8;
    this.zzbfu = paramList5;
    this.zzbfv = paramString23;
    this.zzbfw = paramString24;
    this.zzbfx = paramString25;
    this.zzbfy = paramString26;
    this.zzbfz = paramItemScopeEntity9;
    this.zzbfA = paramString27;
    this.zzbfB = paramString28;
    this.zzbfC = paramString29;
    this.zzbfD = paramItemScopeEntity10;
    this.zzbfE = paramString30;
    this.zzbfF = paramString31;
    this.zzJN = paramString32;
    this.zzF = paramString33;
    this.zzbfG = paramString34;
    this.zzbfH = paramString35;
  }
  
  public ItemScopeEntity(Set<Integer> paramSet, ItemScopeEntity paramItemScopeEntity1, List<String> paramList, ItemScopeEntity paramItemScopeEntity2, String paramString1, String paramString2, String paramString3, List<ItemScopeEntity> paramList1, int paramInt, List<ItemScopeEntity> paramList2, ItemScopeEntity paramItemScopeEntity3, List<ItemScopeEntity> paramList3, String paramString4, String paramString5, ItemScopeEntity paramItemScopeEntity4, String paramString6, String paramString7, String paramString8, List<ItemScopeEntity> paramList4, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, ItemScopeEntity paramItemScopeEntity5, String paramString18, String paramString19, String paramString20, String paramString21, ItemScopeEntity paramItemScopeEntity6, double paramDouble1, ItemScopeEntity paramItemScopeEntity7, double paramDouble2, String paramString22, ItemScopeEntity paramItemScopeEntity8, List<ItemScopeEntity> paramList5, String paramString23, String paramString24, String paramString25, String paramString26, ItemScopeEntity paramItemScopeEntity9, String paramString27, String paramString28, String paramString29, ItemScopeEntity paramItemScopeEntity10, String paramString30, String paramString31, String paramString32, String paramString33, String paramString34, String paramString35)
  {
    this.zzbeN = paramSet;
    this.mVersionCode = 1;
    this.zzbeO = paramItemScopeEntity1;
    this.zzbeP = paramList;
    this.zzbeQ = paramItemScopeEntity2;
    this.zzbeR = paramString1;
    this.zzbeS = paramString2;
    this.zzbeT = paramString3;
    this.zzbeU = paramList1;
    this.zzbeV = paramInt;
    this.zzbeW = paramList2;
    this.zzbeX = paramItemScopeEntity3;
    this.zzbeY = paramList3;
    this.zzbeZ = paramString4;
    this.zzbfa = paramString5;
    this.zzbfb = paramItemScopeEntity4;
    this.zzbfc = paramString6;
    this.zzbfd = paramString7;
    this.zztZ = paramString8;
    this.zzbfe = paramList4;
    this.zzbff = paramString9;
    this.zzbfg = paramString10;
    this.zzbfh = paramString11;
    this.zzaxl = paramString12;
    this.zzbfi = paramString13;
    this.zzbfj = paramString14;
    this.zzbfk = paramString15;
    this.zzbfl = paramString16;
    this.zzbfm = paramString17;
    this.zzbfn = paramItemScopeEntity5;
    this.zzbfo = paramString18;
    this.zzbfp = paramString19;
    this.zzyv = paramString20;
    this.zzbfq = paramString21;
    this.zzbfr = paramItemScopeEntity6;
    this.zzaNF = paramDouble1;
    this.zzbfs = paramItemScopeEntity7;
    this.zzaNG = paramDouble2;
    this.mName = paramString22;
    this.zzbft = paramItemScopeEntity8;
    this.zzbfu = paramList5;
    this.zzbfv = paramString23;
    this.zzbfw = paramString24;
    this.zzbfx = paramString25;
    this.zzbfy = paramString26;
    this.zzbfz = paramItemScopeEntity9;
    this.zzbfA = paramString27;
    this.zzbfB = paramString28;
    this.zzbfC = paramString29;
    this.zzbfD = paramItemScopeEntity10;
    this.zzbfE = paramString30;
    this.zzbfF = paramString31;
    this.zzJN = paramString32;
    this.zzF = paramString33;
    this.zzbfG = paramString34;
    this.zzbfH = paramString35;
  }
  
  public int describeContents()
  {
    zza localzza = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ItemScopeEntity)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (ItemScopeEntity)paramObject;
    Iterator localIterator = zzbeM.values().iterator();
    while (localIterator.hasNext())
    {
      FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
      if (zza(localField))
      {
        if (((ItemScopeEntity)paramObject).zza(localField))
        {
          if (!zzb(localField).equals(((ItemScopeEntity)paramObject).zzb(localField))) {
            return false;
          }
        }
        else {
          return false;
        }
      }
      else if (((ItemScopeEntity)paramObject).zza(localField)) {
        return false;
      }
    }
    return true;
  }
  
  public ItemScope getAbout()
  {
    return this.zzbeO;
  }
  
  public List<String> getAdditionalName()
  {
    return this.zzbeP;
  }
  
  public ItemScope getAddress()
  {
    return this.zzbeQ;
  }
  
  public String getAddressCountry()
  {
    return this.zzbeR;
  }
  
  public String getAddressLocality()
  {
    return this.zzbeS;
  }
  
  public String getAddressRegion()
  {
    return this.zzbeT;
  }
  
  public List<ItemScope> getAssociated_media()
  {
    return (ArrayList)this.zzbeU;
  }
  
  public int getAttendeeCount()
  {
    return this.zzbeV;
  }
  
  public List<ItemScope> getAttendees()
  {
    return (ArrayList)this.zzbeW;
  }
  
  public ItemScope getAudio()
  {
    return this.zzbeX;
  }
  
  public List<ItemScope> getAuthor()
  {
    return (ArrayList)this.zzbeY;
  }
  
  public String getBestRating()
  {
    return this.zzbeZ;
  }
  
  public String getBirthDate()
  {
    return this.zzbfa;
  }
  
  public ItemScope getByArtist()
  {
    return this.zzbfb;
  }
  
  public String getCaption()
  {
    return this.zzbfc;
  }
  
  public String getContentSize()
  {
    return this.zzbfd;
  }
  
  public String getContentUrl()
  {
    return this.zztZ;
  }
  
  public List<ItemScope> getContributor()
  {
    return (ArrayList)this.zzbfe;
  }
  
  public String getDateCreated()
  {
    return this.zzbff;
  }
  
  public String getDateModified()
  {
    return this.zzbfg;
  }
  
  public String getDatePublished()
  {
    return this.zzbfh;
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public String getDuration()
  {
    return this.zzbfi;
  }
  
  public String getEmbedUrl()
  {
    return this.zzbfj;
  }
  
  public String getEndDate()
  {
    return this.zzbfk;
  }
  
  public String getFamilyName()
  {
    return this.zzbfl;
  }
  
  public String getGender()
  {
    return this.zzbfm;
  }
  
  public ItemScope getGeo()
  {
    return this.zzbfn;
  }
  
  public String getGivenName()
  {
    return this.zzbfo;
  }
  
  public String getHeight()
  {
    return this.zzbfp;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public String getImage()
  {
    return this.zzbfq;
  }
  
  public ItemScope getInAlbum()
  {
    return this.zzbfr;
  }
  
  public double getLatitude()
  {
    return this.zzaNF;
  }
  
  public ItemScope getLocation()
  {
    return this.zzbfs;
  }
  
  public double getLongitude()
  {
    return this.zzaNG;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public ItemScope getPartOfTVSeries()
  {
    return this.zzbft;
  }
  
  public List<ItemScope> getPerformers()
  {
    return (ArrayList)this.zzbfu;
  }
  
  public String getPlayerType()
  {
    return this.zzbfv;
  }
  
  public String getPostOfficeBoxNumber()
  {
    return this.zzbfw;
  }
  
  public String getPostalCode()
  {
    return this.zzbfx;
  }
  
  public String getRatingValue()
  {
    return this.zzbfy;
  }
  
  public ItemScope getReviewRating()
  {
    return this.zzbfz;
  }
  
  public String getStartDate()
  {
    return this.zzbfA;
  }
  
  public String getStreetAddress()
  {
    return this.zzbfB;
  }
  
  public String getText()
  {
    return this.zzbfC;
  }
  
  public ItemScope getThumbnail()
  {
    return this.zzbfD;
  }
  
  public String getThumbnailUrl()
  {
    return this.zzbfE;
  }
  
  public String getTickerSymbol()
  {
    return this.zzbfF;
  }
  
  public String getType()
  {
    return this.zzJN;
  }
  
  public String getUrl()
  {
    return this.zzF;
  }
  
  public String getWidth()
  {
    return this.zzbfG;
  }
  
  public String getWorstRating()
  {
    return this.zzbfH;
  }
  
  public boolean hasAbout()
  {
    return this.zzbeN.contains(Integer.valueOf(2));
  }
  
  public boolean hasAdditionalName()
  {
    return this.zzbeN.contains(Integer.valueOf(3));
  }
  
  public boolean hasAddress()
  {
    return this.zzbeN.contains(Integer.valueOf(4));
  }
  
  public boolean hasAddressCountry()
  {
    return this.zzbeN.contains(Integer.valueOf(5));
  }
  
  public boolean hasAddressLocality()
  {
    return this.zzbeN.contains(Integer.valueOf(6));
  }
  
  public boolean hasAddressRegion()
  {
    return this.zzbeN.contains(Integer.valueOf(7));
  }
  
  public boolean hasAssociated_media()
  {
    return this.zzbeN.contains(Integer.valueOf(8));
  }
  
  public boolean hasAttendeeCount()
  {
    return this.zzbeN.contains(Integer.valueOf(9));
  }
  
  public boolean hasAttendees()
  {
    return this.zzbeN.contains(Integer.valueOf(10));
  }
  
  public boolean hasAudio()
  {
    return this.zzbeN.contains(Integer.valueOf(11));
  }
  
  public boolean hasAuthor()
  {
    return this.zzbeN.contains(Integer.valueOf(12));
  }
  
  public boolean hasBestRating()
  {
    return this.zzbeN.contains(Integer.valueOf(13));
  }
  
  public boolean hasBirthDate()
  {
    return this.zzbeN.contains(Integer.valueOf(14));
  }
  
  public boolean hasByArtist()
  {
    return this.zzbeN.contains(Integer.valueOf(15));
  }
  
  public boolean hasCaption()
  {
    return this.zzbeN.contains(Integer.valueOf(16));
  }
  
  public boolean hasContentSize()
  {
    return this.zzbeN.contains(Integer.valueOf(17));
  }
  
  public boolean hasContentUrl()
  {
    return this.zzbeN.contains(Integer.valueOf(18));
  }
  
  public boolean hasContributor()
  {
    return this.zzbeN.contains(Integer.valueOf(19));
  }
  
  public boolean hasDateCreated()
  {
    return this.zzbeN.contains(Integer.valueOf(20));
  }
  
  public boolean hasDateModified()
  {
    return this.zzbeN.contains(Integer.valueOf(21));
  }
  
  public boolean hasDatePublished()
  {
    return this.zzbeN.contains(Integer.valueOf(22));
  }
  
  public boolean hasDescription()
  {
    return this.zzbeN.contains(Integer.valueOf(23));
  }
  
  public boolean hasDuration()
  {
    return this.zzbeN.contains(Integer.valueOf(24));
  }
  
  public boolean hasEmbedUrl()
  {
    return this.zzbeN.contains(Integer.valueOf(25));
  }
  
  public boolean hasEndDate()
  {
    return this.zzbeN.contains(Integer.valueOf(26));
  }
  
  public boolean hasFamilyName()
  {
    return this.zzbeN.contains(Integer.valueOf(27));
  }
  
  public boolean hasGender()
  {
    return this.zzbeN.contains(Integer.valueOf(28));
  }
  
  public boolean hasGeo()
  {
    return this.zzbeN.contains(Integer.valueOf(29));
  }
  
  public boolean hasGivenName()
  {
    return this.zzbeN.contains(Integer.valueOf(30));
  }
  
  public boolean hasHeight()
  {
    return this.zzbeN.contains(Integer.valueOf(31));
  }
  
  public boolean hasId()
  {
    return this.zzbeN.contains(Integer.valueOf(32));
  }
  
  public boolean hasImage()
  {
    return this.zzbeN.contains(Integer.valueOf(33));
  }
  
  public boolean hasInAlbum()
  {
    return this.zzbeN.contains(Integer.valueOf(34));
  }
  
  public boolean hasLatitude()
  {
    return this.zzbeN.contains(Integer.valueOf(36));
  }
  
  public boolean hasLocation()
  {
    return this.zzbeN.contains(Integer.valueOf(37));
  }
  
  public boolean hasLongitude()
  {
    return this.zzbeN.contains(Integer.valueOf(38));
  }
  
  public boolean hasName()
  {
    return this.zzbeN.contains(Integer.valueOf(39));
  }
  
  public boolean hasPartOfTVSeries()
  {
    return this.zzbeN.contains(Integer.valueOf(40));
  }
  
  public boolean hasPerformers()
  {
    return this.zzbeN.contains(Integer.valueOf(41));
  }
  
  public boolean hasPlayerType()
  {
    return this.zzbeN.contains(Integer.valueOf(42));
  }
  
  public boolean hasPostOfficeBoxNumber()
  {
    return this.zzbeN.contains(Integer.valueOf(43));
  }
  
  public boolean hasPostalCode()
  {
    return this.zzbeN.contains(Integer.valueOf(44));
  }
  
  public boolean hasRatingValue()
  {
    return this.zzbeN.contains(Integer.valueOf(45));
  }
  
  public boolean hasReviewRating()
  {
    return this.zzbeN.contains(Integer.valueOf(46));
  }
  
  public boolean hasStartDate()
  {
    return this.zzbeN.contains(Integer.valueOf(47));
  }
  
  public boolean hasStreetAddress()
  {
    return this.zzbeN.contains(Integer.valueOf(48));
  }
  
  public boolean hasText()
  {
    return this.zzbeN.contains(Integer.valueOf(49));
  }
  
  public boolean hasThumbnail()
  {
    return this.zzbeN.contains(Integer.valueOf(50));
  }
  
  public boolean hasThumbnailUrl()
  {
    return this.zzbeN.contains(Integer.valueOf(51));
  }
  
  public boolean hasTickerSymbol()
  {
    return this.zzbeN.contains(Integer.valueOf(52));
  }
  
  public boolean hasType()
  {
    return this.zzbeN.contains(Integer.valueOf(53));
  }
  
  public boolean hasUrl()
  {
    return this.zzbeN.contains(Integer.valueOf(54));
  }
  
  public boolean hasWidth()
  {
    return this.zzbeN.contains(Integer.valueOf(55));
  }
  
  public boolean hasWorstRating()
  {
    return this.zzbeN.contains(Integer.valueOf(56));
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
    zza localzza = CREATOR;
    zza.zza(this, paramParcel, paramInt);
  }
  
  public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
  {
    return zzbeM;
  }
  
  public ItemScopeEntity zzFm()
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
    case 35: 
    default: 
      throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
    case 2: 
      return this.zzbeO;
    case 3: 
      return this.zzbeP;
    case 4: 
      return this.zzbeQ;
    case 5: 
      return this.zzbeR;
    case 6: 
      return this.zzbeS;
    case 7: 
      return this.zzbeT;
    case 8: 
      return this.zzbeU;
    case 9: 
      return Integer.valueOf(this.zzbeV);
    case 10: 
      return this.zzbeW;
    case 11: 
      return this.zzbeX;
    case 12: 
      return this.zzbeY;
    case 13: 
      return this.zzbeZ;
    case 14: 
      return this.zzbfa;
    case 15: 
      return this.zzbfb;
    case 16: 
      return this.zzbfc;
    case 17: 
      return this.zzbfd;
    case 18: 
      return this.zztZ;
    case 19: 
      return this.zzbfe;
    case 20: 
      return this.zzbff;
    case 21: 
      return this.zzbfg;
    case 22: 
      return this.zzbfh;
    case 23: 
      return this.zzaxl;
    case 24: 
      return this.zzbfi;
    case 25: 
      return this.zzbfj;
    case 26: 
      return this.zzbfk;
    case 27: 
      return this.zzbfl;
    case 28: 
      return this.zzbfm;
    case 29: 
      return this.zzbfn;
    case 30: 
      return this.zzbfo;
    case 31: 
      return this.zzbfp;
    case 32: 
      return this.zzyv;
    case 33: 
      return this.zzbfq;
    case 34: 
      return this.zzbfr;
    case 36: 
      return Double.valueOf(this.zzaNF);
    case 37: 
      return this.zzbfs;
    case 38: 
      return Double.valueOf(this.zzaNG);
    case 39: 
      return this.mName;
    case 40: 
      return this.zzbft;
    case 41: 
      return this.zzbfu;
    case 42: 
      return this.zzbfv;
    case 43: 
      return this.zzbfw;
    case 44: 
      return this.zzbfx;
    case 45: 
      return this.zzbfy;
    case 46: 
      return this.zzbfz;
    case 47: 
      return this.zzbfA;
    case 48: 
      return this.zzbfB;
    case 49: 
      return this.zzbfC;
    case 50: 
      return this.zzbfD;
    case 51: 
      return this.zzbfE;
    case 52: 
      return this.zzbfF;
    case 53: 
      return this.zzJN;
    case 54: 
      return this.zzF;
    case 55: 
      return this.zzbfG;
    }
    return this.zzbfH;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\plus\internal\model\moments\ItemScopeEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */