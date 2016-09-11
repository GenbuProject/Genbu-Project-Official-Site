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
    String[] arrayOfString1 = { "zh_SG", "zh", "CN" };
    String[] arrayOfString2 = { "zh_CN", "zh", "CN" };
    String[] arrayOfString3 = { "zh_HK", "zh", "TW" };
    String[] arrayOfString4 = { "zh_TW", "zh", "TW" };
    String[] arrayOfString5 = { "da", "da", "DK" };
    String[] arrayOfString6 = { "nl", "nl", "NL" };
    String[] arrayOfString7 = { "en", "en", "GB" };
    String[] arrayOfString8 = { "en_US", "en", "US" };
    String[] arrayOfString9 = { "fi", "fi", "FI" };
    String[] arrayOfString10 = { "fr", "fr", "FR" };
    String[] arrayOfString11 = { "de", "de", "DE" };
    String[] arrayOfString12 = { "it", "it", "IT" };
    String[] arrayOfString13 = { "ja", "ja", "JP" };
    String[] arrayOfString14 = { "ko", "ko", "KR" };
    String[] arrayOfString15 = { "nb", "nb", "NO" };
    String[] arrayOfString16 = { "pl", "pl", "PL" };
    String[] arrayOfString17 = { "pt_PT", "pt", "PT" };
    String[] arrayOfString18 = { "pt", "pt", "BR" };
    String[] arrayOfString19 = { "es_ES", "es", "ES" };
    String[] arrayOfString20 = { "es", "es", "MX" };
    String[] arrayOfString21 = { "sv", "sv", "SE" };
    String[] arrayOfString22 = { "tr", "tr", "TR" };
    displayLocales = new String[][] { arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, arrayOfString7, arrayOfString8, arrayOfString9, arrayOfString10, arrayOfString11, arrayOfString12, arrayOfString13, arrayOfString14, arrayOfString15, arrayOfString16, arrayOfString17, arrayOfString18, { "ru", "ru", "RU" }, arrayOfString19, arrayOfString20, arrayOfString21, arrayOfString22 };
  }
  
  private XleProjectSpecificDataProvider()
  {
    String[] arrayOfString = { "JP", "ja-JP" };
    this.serviceLocales = new String[][] { { "es_AR", "es-AR" }, { "AR", "es-AR" }, { "en_AU", "en-AU" }, { "AU", "en-AU" }, { "de_AT", "de-AT" }, { "AT", "de-AT" }, { "fr_BE", "fr-BE" }, { "nl_BE", "nl-BE" }, { "BE", "fr-BE" }, { "pt_BR", "pt-BR" }, { "BR", "pt-BR" }, { "en_CA", "en-CA" }, { "fr_CA", "fr-CA" }, { "CA", "en-CA" }, { "en_CZ", "en-CZ" }, { "CZ", "en-CZ" }, { "da_DK", "da-DK" }, { "DK", "da-DK" }, { "fi_FI", "fi-FI" }, { "FI", "fi-FI" }, { "fr_FR", "fr-FR" }, { "FR", "fr-FR" }, { "de_DE", "de-DE" }, { "DE", "de-DE" }, { "en_GR", "en-GR" }, { "GR", "en-GR" }, { "en_HK", "en-HK" }, { "zh_HK", "zh-HK" }, { "HK", "en-HK" }, { "en_HU", "en-HU" }, { "HU", "en-HU" }, { "en_IN", "en-IN" }, { "IN", "en-IN" }, { "en_GB", "en-GB" }, { "GB", "en-GB" }, { "en_IL", "en-IL" }, { "IL", "en-IL" }, { "it_IT", "it-IT" }, { "IT", "it-IT" }, { "ja_JP", "ja-JP" }, arrayOfString, { "zh_CN", "zh-CN" }, { "CN", "zh-CN" }, { "es_MX", "es-MX" }, { "MX", "es-MX" }, { "es_CL", "es-CL" }, { "CL", "es-CL" }, { "es_CO", "es-CO" }, { "CO", "es-CO" }, { "nl_NL", "nl-NL" }, { "NL", "nl-NL" }, { "en_NZ", "en-NZ" }, { "NZ", "en-NZ" }, { "nb_NO", "nb-NO" }, { "NO", "nb-NO" }, { "pl_PL", "pl-PL" }, { "PL", "pl-PL" }, { "pt_PT", "pt-PT" }, { "PT", "pt-PT" }, { "ru_RU", "ru-RU" }, { "RU", "ru-RU" }, { "en_SA", "en-SA" }, { "SA", "en-SA" }, { "en_SG", "en-SG" }, { "zh_SG", "zh-SG" }, { "SG", "en-SG" }, { "en_SK", "en-SK" }, { "SK", "en-SK" }, { "en_ZA", "en-ZA" }, { "ZA", "en-ZA" }, { "ko_KR", "ko-KR" }, { "KR", "ko-KR" }, { "es_ES", "es-ES" }, { "es", "es-ES" }, { "de_CH", "de-CH" }, { "fr_CH", "fr-CH" }, { "CH", "fr-CH" }, { "zh_TW", "zh-TW" }, { "TW", "zh-TW" }, { "en_AE", "en-AE" }, { "AE", "en-AE" }, { "en_US", "en-US" }, { "US", "en-US" }, { "sv_SE", "sv-SE" }, { "SE", "sv-SE" }, { "tr_Tr", "tr-TR" }, { "Tr", "tr-TR" }, { "en_IE", "en-IE" }, { "IE", "en-IE" } };
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
    DisplayMetrics localDisplayMetrics = null;
    Object localObject1 = Locale.getDefault();
    String str1 = ((Locale)localObject1).toString();
    Object localObject2 = ((Locale)localObject1).getLanguage();
    String str2 = ((Locale)localObject1).getCountry();
    int i = 0;
    localObject1 = localDisplayMetrics;
    if (i < displayLocales.length)
    {
      if (!displayLocales[i][0].equals(str1)) {
        break label190;
      }
      if ((!displayLocales[i][1].equals(localObject2)) || (!displayLocales[i][2].equals(str2))) {}
    }
    label110:
    label190:
    label205:
    for (;;)
    {
      return;
      localObject1 = new Locale(displayLocales[i][1], displayLocales[i][2]);
      if (localObject1 == null)
      {
        i = 0;
        if (i < displayLocales.length) {
          if (displayLocales[i][0].equals(localObject2)) {
            localObject1 = new Locale(displayLocales[i][1], displayLocales[i][2]);
          }
        }
      }
      for (;;)
      {
        if (localObject1 == null) {
          break label205;
        }
        localDisplayMetrics = XboxTcuiSdk.getResources().getDisplayMetrics();
        localObject2 = XboxTcuiSdk.getResources().getConfiguration();
        ((Configuration)localObject2).locale = ((Locale)localObject1);
        XboxTcuiSdk.getResources().updateConfiguration((Configuration)localObject2, localDisplayMetrics);
        return;
        i += 1;
        break;
        i += 1;
        break label110;
      }
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\XleProjectSpecificDataProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */