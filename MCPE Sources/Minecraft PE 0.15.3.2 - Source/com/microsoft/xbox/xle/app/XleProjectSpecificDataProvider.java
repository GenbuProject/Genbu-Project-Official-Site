package com.microsoft.xbox.xle.app;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.toolkit.GsonUtil;
import com.microsoft.xbox.toolkit.IProjectSpecificDataProvider;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Set;

public class XleProjectSpecificDataProvider
  implements IProjectSpecificDataProvider
{
  private static final String[][] displayLocales;
  private static XleProjectSpecificDataProvider instance = new XleProjectSpecificDataProvider();
  private String androidId;
  private Set<String> blockFeaturedChild = new HashSet();
  private boolean gotSettings;
  private boolean isMeAdult;
  private String meXuid;
  private Set<String> musicBlocked = new HashSet();
  private String privileges;
  private Set<String> promotionalRestrictedRegions = new HashSet();
  private Set<String> purchaseBlocked = new HashSet();
  private String scdRpsTicket;
  private Hashtable<String, String> serviceLocaleMapTable = new Hashtable();
  private String[][] serviceLocales;
  private Set<String> videoBlocked = new HashSet();
  
  static
  {
    String[] arrayOfString1 = { "nl", "nl", "NL" };
    String[] arrayOfString2 = { "en", "en", "GB" };
    String[] arrayOfString3 = { "en_US", "en", "US" };
    String[] arrayOfString4 = { "it", "it", "IT" };
    displayLocales = new String[][] { { "zh_SG", "zh", "CN" }, { "zh_CN", "zh", "CN" }, { "zh_HK", "zh", "TW" }, { "zh_TW", "zh", "TW" }, { "da", "da", "DK" }, arrayOfString1, arrayOfString2, arrayOfString3, { "fi", "fi", "FI" }, { "fr", "fr", "FR" }, { "de", "de", "DE" }, arrayOfString4, { "ja", "ja", "JP" }, { "ko", "ko", "KR" }, { "nb", "nb", "NO" }, { "pl", "pl", "PL" }, { "pt_PT", "pt", "PT" }, { "pt", "pt", "BR" }, { "ru", "ru", "RU" }, { "es_ES", "es", "ES" }, { "es", "es", "MX" }, { "sv", "sv", "SE" }, { "tr", "tr", "TR" } };
  }
  
  private XleProjectSpecificDataProvider()
  {
    String[] arrayOfString1 = { "AR", "es-AR" };
    String[] arrayOfString2 = { "nl_BE", "nl-BE" };
    String[] arrayOfString3 = { "en_CA", "en-CA" };
    String[] arrayOfString4 = { "da_DK", "da-DK" };
    String[] arrayOfString5 = { "fr_FR", "fr-FR" };
    String[] arrayOfString6 = { "FR", "fr-FR" };
    String[] arrayOfString7 = { "en_GR", "en-GR" };
    String[] arrayOfString8 = { "GR", "en-GR" };
    String[] arrayOfString9 = { "en_HK", "en-HK" };
    String[] arrayOfString10 = { "HK", "en-HK" };
    String[] arrayOfString11 = { "HU", "en-HU" };
    String[] arrayOfString12 = { "en_IN", "en-IN" };
    String[] arrayOfString13 = { "en_GB", "en-GB" };
    String[] arrayOfString14 = { "GB", "en-GB" };
    String[] arrayOfString15 = { "en_IL", "en-IL" };
    String[] arrayOfString16 = { "IL", "en-IL" };
    String[] arrayOfString17 = { "it_IT", "it-IT" };
    String[] arrayOfString18 = { "IT", "it-IT" };
    String[] arrayOfString19 = { "zh_CN", "zh-CN" };
    String[] arrayOfString20 = { "CN", "zh-CN" };
    String[] arrayOfString21 = { "es_MX", "es-MX" };
    String[] arrayOfString22 = { "MX", "es-MX" };
    String[] arrayOfString23 = { "es_CO", "es-CO" };
    String[] arrayOfString24 = { "CO", "es-CO" };
    String[] arrayOfString25 = { "NL", "nl-NL" };
    String[] arrayOfString26 = { "en_NZ", "en-NZ" };
    String[] arrayOfString27 = { "pl_PL", "pl-PL" };
    String[] arrayOfString28 = { "en_SA", "en-SA" };
    String[] arrayOfString29 = { "en_SK", "en-SK" };
    String[] arrayOfString30 = { "SK", "en-SK" };
    String[] arrayOfString31 = { "ZA", "en-ZA" };
    String[] arrayOfString32 = { "ko_KR", "ko-KR" };
    String[] arrayOfString33 = { "KR", "ko-KR" };
    String[] arrayOfString34 = { "es", "es-ES" };
    String[] arrayOfString35 = { "de_CH", "de-CH" };
    String[] arrayOfString36 = { "fr_CH", "fr-CH" };
    String[] arrayOfString37 = { "zh_TW", "zh-TW" };
    String[] arrayOfString38 = { "TW", "zh-TW" };
    String[] arrayOfString39 = { "en_AE", "en-AE" };
    String[] arrayOfString40 = { "sv_SE", "sv-SE" };
    String[] arrayOfString41 = { "en_IE", "en-IE" };
    String[] arrayOfString42 = { "IE", "en-IE" };
    this.serviceLocales = new String[][] { { "es_AR", "es-AR" }, arrayOfString1, { "en_AU", "en-AU" }, { "AU", "en-AU" }, { "de_AT", "de-AT" }, { "AT", "de-AT" }, { "fr_BE", "fr-BE" }, arrayOfString2, { "BE", "fr-BE" }, { "pt_BR", "pt-BR" }, { "BR", "pt-BR" }, arrayOfString3, { "fr_CA", "fr-CA" }, { "CA", "en-CA" }, { "en_CZ", "en-CZ" }, { "CZ", "en-CZ" }, arrayOfString4, { "DK", "da-DK" }, { "fi_FI", "fi-FI" }, { "FI", "fi-FI" }, arrayOfString5, arrayOfString6, { "de_DE", "de-DE" }, { "DE", "de-DE" }, arrayOfString7, arrayOfString8, arrayOfString9, { "zh_HK", "zh-HK" }, arrayOfString10, { "en_HU", "en-HU" }, arrayOfString11, arrayOfString12, { "IN", "en-IN" }, arrayOfString13, arrayOfString14, arrayOfString15, arrayOfString16, arrayOfString17, arrayOfString18, { "ja_JP", "ja-JP" }, { "JP", "ja-JP" }, arrayOfString19, arrayOfString20, arrayOfString21, arrayOfString22, { "es_CL", "es-CL" }, { "CL", "es-CL" }, arrayOfString23, arrayOfString24, { "nl_NL", "nl-NL" }, arrayOfString25, arrayOfString26, { "NZ", "en-NZ" }, { "nb_NO", "nb-NO" }, { "NO", "nb-NO" }, arrayOfString27, { "PL", "pl-PL" }, { "pt_PT", "pt-PT" }, { "PT", "pt-PT" }, { "ru_RU", "ru-RU" }, { "RU", "ru-RU" }, arrayOfString28, { "SA", "en-SA" }, { "en_SG", "en-SG" }, { "zh_SG", "zh-SG" }, { "SG", "en-SG" }, arrayOfString29, arrayOfString30, { "en_ZA", "en-ZA" }, arrayOfString31, arrayOfString32, arrayOfString33, { "es_ES", "es-ES" }, arrayOfString34, arrayOfString35, arrayOfString36, { "CH", "fr-CH" }, arrayOfString37, arrayOfString38, arrayOfString39, { "AE", "en-AE" }, { "en_US", "en-US" }, { "US", "en-US" }, arrayOfString40, { "SE", "sv-SE" }, { "tr_Tr", "tr-TR" }, { "Tr", "tr-TR" }, arrayOfString41, arrayOfString42 };
    int i = 0;
    while (i < this.serviceLocales.length)
    {
      this.serviceLocaleMapTable.put(this.serviceLocales[i][0], this.serviceLocales[i][1]);
      i += 1;
    }
    this.serviceLocales = ((String[][])null);
  }
  
  private void addRegions(String paramString, Set<String> paramSet)
  {
    if (!JavaUtil.isNullOrEmpty(paramString))
    {
      paramString = paramString.split("[|]");
      if (!XLEUtil.isNullOrEmpty(paramString))
      {
        paramSet.clear();
        int j = paramString.length;
        int i = 0;
        while (i < j)
        {
          String str = paramString[i];
          if (!JavaUtil.isNullOrEmpty(str)) {
            paramSet.add(str);
          }
          i += 1;
        }
      }
    }
  }
  
  private String getDeviceLocale()
  {
    Object localObject = Locale.getDefault();
    String str = ((Locale)localObject).toString();
    if (this.serviceLocaleMapTable.containsKey(str)) {
      return (String)this.serviceLocaleMapTable.get(str);
    }
    localObject = ((Locale)localObject).getCountry();
    if ((!JavaUtil.isNullOrEmpty((String)localObject)) && (this.serviceLocaleMapTable.containsKey(localObject))) {
      return (String)this.serviceLocaleMapTable.get(localObject);
    }
    return "en-US";
  }
  
  public static XleProjectSpecificDataProvider getInstance()
  {
    return instance;
  }
  
  public void ensureDisplayLocale()
  {
    Object localObject2 = null;
    Object localObject1 = Locale.getDefault();
    String str1 = ((Locale)localObject1).toString();
    Object localObject3 = ((Locale)localObject1).getLanguage();
    String str2 = ((Locale)localObject1).getCountry();
    int i = 0;
    localObject1 = localObject2;
    if (i < displayLocales.length)
    {
      if (displayLocales[i][0].equals(str1))
      {
        if ((displayLocales[i][1].equals(localObject3)) && (displayLocales[i][2].equals(str2))) {
          label81:
          return;
        }
        localObject1 = new Locale(displayLocales[i][1], displayLocales[i][2]);
      }
    }
    else
    {
      localObject2 = localObject1;
      if (localObject1 == null) {
        i = 0;
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (i < displayLocales.length)
      {
        if (displayLocales[i][0].equals(localObject3)) {
          localObject2 = new Locale(displayLocales[i][1], displayLocales[i][2]);
        }
      }
      else
      {
        if (localObject2 == null) {
          break label81;
        }
        localObject1 = XboxTcuiSdk.getResources().getDisplayMetrics();
        localObject3 = XboxTcuiSdk.getResources().getConfiguration();
        ((Configuration)localObject3).locale = ((Locale)localObject2);
        XboxTcuiSdk.getResources().updateConfiguration((Configuration)localObject3, (DisplayMetrics)localObject1);
        return;
        i += 1;
        break;
      }
      i += 1;
    }
  }
  
  public boolean getAllowExplicitContent()
  {
    return true;
  }
  
  public String getAutoSuggestdDataSource()
  {
    return "bbxall2";
  }
  
  public String getCombinedContentRating()
  {
    return "";
  }
  
  public String getConnectedLocale()
  {
    return getDeviceLocale();
  }
  
  public String getConnectedLocale(boolean paramBoolean)
  {
    return getConnectedLocale();
  }
  
  public String getContentRestrictions()
  {
    String str = getRegion();
    int i = getMeMaturityLevel();
    if ((!JavaUtil.isNullOrEmpty(str)) && (i != 255))
    {
      str = GsonUtil.toJsonString(new ContentRestrictions(str, i, isPromotionalRestricted()));
      if (!JavaUtil.isNullOrEmpty(str)) {
        return Base64.encodeToString(str.getBytes(), 2);
      }
    }
    return null;
  }
  
  public String getCurrentSandboxID()
  {
    return "PROD";
  }
  
  public boolean getInitializeComplete()
  {
    return getXuidString() != null;
  }
  
  public boolean getIsForXboxOne()
  {
    return true;
  }
  
  public boolean getIsFreeAccount()
  {
    return false;
  }
  
  public boolean getIsXboxMusicSupported()
  {
    return true;
  }
  
  public String getLegalLocale()
  {
    return getConnectedLocale();
  }
  
  public int getMeMaturityLevel()
  {
    ProfileModel localProfileModel = ProfileModel.getMeProfileModel();
    if (localProfileModel != null) {
      return localProfileModel.getMaturityLevel();
    }
    return 0;
  }
  
  public String getMembershipLevel()
  {
    if (ProfileModel.getMeProfileModel().getAccountTier() == null) {
      return "Gold";
    }
    return ProfileModel.getMeProfileModel().getAccountTier();
  }
  
  public String getPrivileges()
  {
    return this.privileges;
  }
  
  public String getRegion()
  {
    return Locale.getDefault().getCountry();
  }
  
  public String getSCDRpsTicket()
  {
    return this.scdRpsTicket;
  }
  
  public String getVersionCheckUrl()
  {
    switch (com.microsoft.xbox.toolkit.network.XboxLiveEnvironment.Instance().getEnvironment())
    {
    default: 
      throw new UnsupportedOperationException();
    case ???: 
    case ???: 
      return "http://www.rtm.vint.xbox.com/en-US/Platform/Android/XboxLIVE/sgversion";
    }
    return "http://www.xbox.com/en-US/Platform/Android/XboxLIVE/sgversion";
  }
  
  public int getVersionCode()
  {
    return 1;
  }
  
  public String getWindowsLiveClientId()
  {
    switch (com.microsoft.xbox.toolkit.network.XboxLiveEnvironment.Instance().getEnvironment())
    {
    default: 
      throw new UnsupportedOperationException();
    case ???: 
      return "0000000048093EE3";
    }
    return "0000000068036303";
  }
  
  public String getXuidString()
  {
    return this.meXuid;
  }
  
  public boolean gotSettings()
  {
    return this.gotSettings;
  }
  
  public boolean isDeviceLocaleKnown()
  {
    Object localObject = Locale.getDefault();
    String str = ((Locale)localObject).toString();
    if (this.serviceLocaleMapTable.containsKey(str)) {}
    do
    {
      return true;
      localObject = ((Locale)localObject).getCountry();
    } while ((!JavaUtil.isNullOrEmpty((String)localObject)) && (this.serviceLocaleMapTable.containsKey(localObject)));
    return false;
  }
  
  public boolean isFeaturedBlocked()
  {
    return (!isMeAdult()) && (this.blockFeaturedChild.contains(getRegion()));
  }
  
  public boolean isMeAdult()
  {
    return this.isMeAdult;
  }
  
  public boolean isMusicBlocked()
  {
    return true;
  }
  
  public boolean isPromotionalRestricted()
  {
    return (!isMeAdult()) && (this.promotionalRestrictedRegions.contains(getRegion()));
  }
  
  public boolean isPurchaseBlocked()
  {
    return this.purchaseBlocked.contains(getRegion());
  }
  
  public boolean isVideoBlocked()
  {
    return true;
  }
  
  public void processContentBlockedList(SmartglassSettings paramSmartglassSettings)
  {
    addRegions(paramSmartglassSettings.VIDEO_BLOCKED, this.videoBlocked);
    addRegions(paramSmartglassSettings.MUSIC_BLOCKED, this.musicBlocked);
    addRegions(paramSmartglassSettings.PURCHASE_BLOCKED, this.purchaseBlocked);
    addRegions(paramSmartglassSettings.BLOCK_FEATURED_CHILD, this.blockFeaturedChild);
    addRegions(paramSmartglassSettings.PROMOTIONAL_CONTENT_RESTRICTED_REGIONS, this.promotionalRestrictedRegions);
    this.gotSettings = true;
  }
  
  public void resetModels(boolean paramBoolean) {}
  
  public void setIsMeAdult(boolean paramBoolean)
  {
    this.isMeAdult = paramBoolean;
  }
  
  public void setPrivileges(String paramString)
  {
    this.privileges = paramString;
  }
  
  public void setSCDRpsTicket(String paramString)
  {
    this.scdRpsTicket = paramString;
  }
  
  public void setXuidString(String paramString)
  {
    this.meXuid = paramString;
  }
  
  private class ContentRestrictions
  {
    public Data data = new Data();
    public int version = 2;
    
    public ContentRestrictions(String paramString, int paramInt, boolean paramBoolean)
    {
      this.data.geographicRegion = paramString;
      this$1 = this.data;
      this.data.preferredAgeRating = paramInt;
      XleProjectSpecificDataProvider.this.maxAgeRating = paramInt;
      this.data.restrictPromotionalContent = paramBoolean;
    }
    
    public class Data
    {
      public String geographicRegion;
      public int maxAgeRating;
      public int preferredAgeRating;
      public boolean restrictPromotionalContent;
      
      public Data() {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\XleProjectSpecificDataProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */