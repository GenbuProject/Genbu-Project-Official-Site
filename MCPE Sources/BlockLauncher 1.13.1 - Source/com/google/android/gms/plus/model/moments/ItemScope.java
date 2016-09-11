package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.plus.internal.model.moments.ItemScopeEntity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract interface ItemScope
  extends Freezable<ItemScope>
{
  public abstract ItemScope getAbout();
  
  public abstract List<String> getAdditionalName();
  
  public abstract ItemScope getAddress();
  
  public abstract String getAddressCountry();
  
  public abstract String getAddressLocality();
  
  public abstract String getAddressRegion();
  
  public abstract List<ItemScope> getAssociated_media();
  
  public abstract int getAttendeeCount();
  
  public abstract List<ItemScope> getAttendees();
  
  public abstract ItemScope getAudio();
  
  public abstract List<ItemScope> getAuthor();
  
  public abstract String getBestRating();
  
  public abstract String getBirthDate();
  
  public abstract ItemScope getByArtist();
  
  public abstract String getCaption();
  
  public abstract String getContentSize();
  
  public abstract String getContentUrl();
  
  public abstract List<ItemScope> getContributor();
  
  public abstract String getDateCreated();
  
  public abstract String getDateModified();
  
  public abstract String getDatePublished();
  
  public abstract String getDescription();
  
  public abstract String getDuration();
  
  public abstract String getEmbedUrl();
  
  public abstract String getEndDate();
  
  public abstract String getFamilyName();
  
  public abstract String getGender();
  
  public abstract ItemScope getGeo();
  
  public abstract String getGivenName();
  
  public abstract String getHeight();
  
  public abstract String getId();
  
  public abstract String getImage();
  
  public abstract ItemScope getInAlbum();
  
  public abstract double getLatitude();
  
  public abstract ItemScope getLocation();
  
  public abstract double getLongitude();
  
  public abstract String getName();
  
  public abstract ItemScope getPartOfTVSeries();
  
  public abstract List<ItemScope> getPerformers();
  
  public abstract String getPlayerType();
  
  public abstract String getPostOfficeBoxNumber();
  
  public abstract String getPostalCode();
  
  public abstract String getRatingValue();
  
  public abstract ItemScope getReviewRating();
  
  public abstract String getStartDate();
  
  public abstract String getStreetAddress();
  
  public abstract String getText();
  
  public abstract ItemScope getThumbnail();
  
  public abstract String getThumbnailUrl();
  
  public abstract String getTickerSymbol();
  
  public abstract String getType();
  
  public abstract String getUrl();
  
  public abstract String getWidth();
  
  public abstract String getWorstRating();
  
  public abstract boolean hasAbout();
  
  public abstract boolean hasAdditionalName();
  
  public abstract boolean hasAddress();
  
  public abstract boolean hasAddressCountry();
  
  public abstract boolean hasAddressLocality();
  
  public abstract boolean hasAddressRegion();
  
  public abstract boolean hasAssociated_media();
  
  public abstract boolean hasAttendeeCount();
  
  public abstract boolean hasAttendees();
  
  public abstract boolean hasAudio();
  
  public abstract boolean hasAuthor();
  
  public abstract boolean hasBestRating();
  
  public abstract boolean hasBirthDate();
  
  public abstract boolean hasByArtist();
  
  public abstract boolean hasCaption();
  
  public abstract boolean hasContentSize();
  
  public abstract boolean hasContentUrl();
  
  public abstract boolean hasContributor();
  
  public abstract boolean hasDateCreated();
  
  public abstract boolean hasDateModified();
  
  public abstract boolean hasDatePublished();
  
  public abstract boolean hasDescription();
  
  public abstract boolean hasDuration();
  
  public abstract boolean hasEmbedUrl();
  
  public abstract boolean hasEndDate();
  
  public abstract boolean hasFamilyName();
  
  public abstract boolean hasGender();
  
  public abstract boolean hasGeo();
  
  public abstract boolean hasGivenName();
  
  public abstract boolean hasHeight();
  
  public abstract boolean hasId();
  
  public abstract boolean hasImage();
  
  public abstract boolean hasInAlbum();
  
  public abstract boolean hasLatitude();
  
  public abstract boolean hasLocation();
  
  public abstract boolean hasLongitude();
  
  public abstract boolean hasName();
  
  public abstract boolean hasPartOfTVSeries();
  
  public abstract boolean hasPerformers();
  
  public abstract boolean hasPlayerType();
  
  public abstract boolean hasPostOfficeBoxNumber();
  
  public abstract boolean hasPostalCode();
  
  public abstract boolean hasRatingValue();
  
  public abstract boolean hasReviewRating();
  
  public abstract boolean hasStartDate();
  
  public abstract boolean hasStreetAddress();
  
  public abstract boolean hasText();
  
  public abstract boolean hasThumbnail();
  
  public abstract boolean hasThumbnailUrl();
  
  public abstract boolean hasTickerSymbol();
  
  public abstract boolean hasType();
  
  public abstract boolean hasUrl();
  
  public abstract boolean hasWidth();
  
  public abstract boolean hasWorstRating();
  
  public static class Builder
  {
    private String mName;
    private String zzF;
    private String zzJN;
    private double zzaNF;
    private double zzaNG;
    private String zzaxl;
    private final Set<Integer> zzbeN = new HashSet();
    private ItemScopeEntity zzbeO;
    private List<String> zzbeP;
    private ItemScopeEntity zzbeQ;
    private String zzbeR;
    private String zzbeS;
    private String zzbeT;
    private List<ItemScopeEntity> zzbeU;
    private int zzbeV;
    private List<ItemScopeEntity> zzbeW;
    private ItemScopeEntity zzbeX;
    private List<ItemScopeEntity> zzbeY;
    private String zzbeZ;
    private String zzbfA;
    private String zzbfB;
    private String zzbfC;
    private ItemScopeEntity zzbfD;
    private String zzbfE;
    private String zzbfF;
    private String zzbfG;
    private String zzbfH;
    private String zzbfa;
    private ItemScopeEntity zzbfb;
    private String zzbfc;
    private String zzbfd;
    private List<ItemScopeEntity> zzbfe;
    private String zzbff;
    private String zzbfg;
    private String zzbfh;
    private String zzbfi;
    private String zzbfj;
    private String zzbfk;
    private String zzbfl;
    private String zzbfm;
    private ItemScopeEntity zzbfn;
    private String zzbfo;
    private String zzbfp;
    private String zzbfq;
    private ItemScopeEntity zzbfr;
    private ItemScopeEntity zzbfs;
    private ItemScopeEntity zzbft;
    private List<ItemScopeEntity> zzbfu;
    private String zzbfv;
    private String zzbfw;
    private String zzbfx;
    private String zzbfy;
    private ItemScopeEntity zzbfz;
    private String zztZ;
    private String zzyv;
    
    public ItemScope build()
    {
      return new ItemScopeEntity(this.zzbeN, this.zzbeO, this.zzbeP, this.zzbeQ, this.zzbeR, this.zzbeS, this.zzbeT, this.zzbeU, this.zzbeV, this.zzbeW, this.zzbeX, this.zzbeY, this.zzbeZ, this.zzbfa, this.zzbfb, this.zzbfc, this.zzbfd, this.zztZ, this.zzbfe, this.zzbff, this.zzbfg, this.zzbfh, this.zzaxl, this.zzbfi, this.zzbfj, this.zzbfk, this.zzbfl, this.zzbfm, this.zzbfn, this.zzbfo, this.zzbfp, this.zzyv, this.zzbfq, this.zzbfr, this.zzaNF, this.zzbfs, this.zzaNG, this.mName, this.zzbft, this.zzbfu, this.zzbfv, this.zzbfw, this.zzbfx, this.zzbfy, this.zzbfz, this.zzbfA, this.zzbfB, this.zzbfC, this.zzbfD, this.zzbfE, this.zzbfF, this.zzJN, this.zzF, this.zzbfG, this.zzbfH);
    }
    
    public Builder setAbout(ItemScope paramItemScope)
    {
      this.zzbeO = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(2));
      return this;
    }
    
    public Builder setAdditionalName(List<String> paramList)
    {
      this.zzbeP = paramList;
      this.zzbeN.add(Integer.valueOf(3));
      return this;
    }
    
    public Builder setAddress(ItemScope paramItemScope)
    {
      this.zzbeQ = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(4));
      return this;
    }
    
    public Builder setAddressCountry(String paramString)
    {
      this.zzbeR = paramString;
      this.zzbeN.add(Integer.valueOf(5));
      return this;
    }
    
    public Builder setAddressLocality(String paramString)
    {
      this.zzbeS = paramString;
      this.zzbeN.add(Integer.valueOf(6));
      return this;
    }
    
    public Builder setAddressRegion(String paramString)
    {
      this.zzbeT = paramString;
      this.zzbeN.add(Integer.valueOf(7));
      return this;
    }
    
    public Builder setAssociated_media(List<ItemScope> paramList)
    {
      this.zzbeU = paramList;
      this.zzbeN.add(Integer.valueOf(8));
      return this;
    }
    
    public Builder setAttendeeCount(int paramInt)
    {
      this.zzbeV = paramInt;
      this.zzbeN.add(Integer.valueOf(9));
      return this;
    }
    
    public Builder setAttendees(List<ItemScope> paramList)
    {
      this.zzbeW = paramList;
      this.zzbeN.add(Integer.valueOf(10));
      return this;
    }
    
    public Builder setAudio(ItemScope paramItemScope)
    {
      this.zzbeX = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(11));
      return this;
    }
    
    public Builder setAuthor(List<ItemScope> paramList)
    {
      this.zzbeY = paramList;
      this.zzbeN.add(Integer.valueOf(12));
      return this;
    }
    
    public Builder setBestRating(String paramString)
    {
      this.zzbeZ = paramString;
      this.zzbeN.add(Integer.valueOf(13));
      return this;
    }
    
    public Builder setBirthDate(String paramString)
    {
      this.zzbfa = paramString;
      this.zzbeN.add(Integer.valueOf(14));
      return this;
    }
    
    public Builder setByArtist(ItemScope paramItemScope)
    {
      this.zzbfb = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(15));
      return this;
    }
    
    public Builder setCaption(String paramString)
    {
      this.zzbfc = paramString;
      this.zzbeN.add(Integer.valueOf(16));
      return this;
    }
    
    public Builder setContentSize(String paramString)
    {
      this.zzbfd = paramString;
      this.zzbeN.add(Integer.valueOf(17));
      return this;
    }
    
    public Builder setContentUrl(String paramString)
    {
      this.zztZ = paramString;
      this.zzbeN.add(Integer.valueOf(18));
      return this;
    }
    
    public Builder setContributor(List<ItemScope> paramList)
    {
      this.zzbfe = paramList;
      this.zzbeN.add(Integer.valueOf(19));
      return this;
    }
    
    public Builder setDateCreated(String paramString)
    {
      this.zzbff = paramString;
      this.zzbeN.add(Integer.valueOf(20));
      return this;
    }
    
    public Builder setDateModified(String paramString)
    {
      this.zzbfg = paramString;
      this.zzbeN.add(Integer.valueOf(21));
      return this;
    }
    
    public Builder setDatePublished(String paramString)
    {
      this.zzbfh = paramString;
      this.zzbeN.add(Integer.valueOf(22));
      return this;
    }
    
    public Builder setDescription(String paramString)
    {
      this.zzaxl = paramString;
      this.zzbeN.add(Integer.valueOf(23));
      return this;
    }
    
    public Builder setDuration(String paramString)
    {
      this.zzbfi = paramString;
      this.zzbeN.add(Integer.valueOf(24));
      return this;
    }
    
    public Builder setEmbedUrl(String paramString)
    {
      this.zzbfj = paramString;
      this.zzbeN.add(Integer.valueOf(25));
      return this;
    }
    
    public Builder setEndDate(String paramString)
    {
      this.zzbfk = paramString;
      this.zzbeN.add(Integer.valueOf(26));
      return this;
    }
    
    public Builder setFamilyName(String paramString)
    {
      this.zzbfl = paramString;
      this.zzbeN.add(Integer.valueOf(27));
      return this;
    }
    
    public Builder setGender(String paramString)
    {
      this.zzbfm = paramString;
      this.zzbeN.add(Integer.valueOf(28));
      return this;
    }
    
    public Builder setGeo(ItemScope paramItemScope)
    {
      this.zzbfn = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(29));
      return this;
    }
    
    public Builder setGivenName(String paramString)
    {
      this.zzbfo = paramString;
      this.zzbeN.add(Integer.valueOf(30));
      return this;
    }
    
    public Builder setHeight(String paramString)
    {
      this.zzbfp = paramString;
      this.zzbeN.add(Integer.valueOf(31));
      return this;
    }
    
    public Builder setId(String paramString)
    {
      this.zzyv = paramString;
      this.zzbeN.add(Integer.valueOf(32));
      return this;
    }
    
    public Builder setImage(String paramString)
    {
      this.zzbfq = paramString;
      this.zzbeN.add(Integer.valueOf(33));
      return this;
    }
    
    public Builder setInAlbum(ItemScope paramItemScope)
    {
      this.zzbfr = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(34));
      return this;
    }
    
    public Builder setLatitude(double paramDouble)
    {
      this.zzaNF = paramDouble;
      this.zzbeN.add(Integer.valueOf(36));
      return this;
    }
    
    public Builder setLocation(ItemScope paramItemScope)
    {
      this.zzbfs = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(37));
      return this;
    }
    
    public Builder setLongitude(double paramDouble)
    {
      this.zzaNG = paramDouble;
      this.zzbeN.add(Integer.valueOf(38));
      return this;
    }
    
    public Builder setName(String paramString)
    {
      this.mName = paramString;
      this.zzbeN.add(Integer.valueOf(39));
      return this;
    }
    
    public Builder setPartOfTVSeries(ItemScope paramItemScope)
    {
      this.zzbft = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(40));
      return this;
    }
    
    public Builder setPerformers(List<ItemScope> paramList)
    {
      this.zzbfu = paramList;
      this.zzbeN.add(Integer.valueOf(41));
      return this;
    }
    
    public Builder setPlayerType(String paramString)
    {
      this.zzbfv = paramString;
      this.zzbeN.add(Integer.valueOf(42));
      return this;
    }
    
    public Builder setPostOfficeBoxNumber(String paramString)
    {
      this.zzbfw = paramString;
      this.zzbeN.add(Integer.valueOf(43));
      return this;
    }
    
    public Builder setPostalCode(String paramString)
    {
      this.zzbfx = paramString;
      this.zzbeN.add(Integer.valueOf(44));
      return this;
    }
    
    public Builder setRatingValue(String paramString)
    {
      this.zzbfy = paramString;
      this.zzbeN.add(Integer.valueOf(45));
      return this;
    }
    
    public Builder setReviewRating(ItemScope paramItemScope)
    {
      this.zzbfz = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(46));
      return this;
    }
    
    public Builder setStartDate(String paramString)
    {
      this.zzbfA = paramString;
      this.zzbeN.add(Integer.valueOf(47));
      return this;
    }
    
    public Builder setStreetAddress(String paramString)
    {
      this.zzbfB = paramString;
      this.zzbeN.add(Integer.valueOf(48));
      return this;
    }
    
    public Builder setText(String paramString)
    {
      this.zzbfC = paramString;
      this.zzbeN.add(Integer.valueOf(49));
      return this;
    }
    
    public Builder setThumbnail(ItemScope paramItemScope)
    {
      this.zzbfD = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(50));
      return this;
    }
    
    public Builder setThumbnailUrl(String paramString)
    {
      this.zzbfE = paramString;
      this.zzbeN.add(Integer.valueOf(51));
      return this;
    }
    
    public Builder setTickerSymbol(String paramString)
    {
      this.zzbfF = paramString;
      this.zzbeN.add(Integer.valueOf(52));
      return this;
    }
    
    public Builder setType(String paramString)
    {
      this.zzJN = paramString;
      this.zzbeN.add(Integer.valueOf(53));
      return this;
    }
    
    public Builder setUrl(String paramString)
    {
      this.zzF = paramString;
      this.zzbeN.add(Integer.valueOf(54));
      return this;
    }
    
    public Builder setWidth(String paramString)
    {
      this.zzbfG = paramString;
      this.zzbeN.add(Integer.valueOf(55));
      return this;
    }
    
    public Builder setWorstRating(String paramString)
    {
      this.zzbfH = paramString;
      this.zzbeN.add(Integer.valueOf(56));
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\plus\model\moments\ItemScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */