package com.microsoft.xbox.toolkit;

public class ProjectSpecificDataProvider
  implements IProjectSpecificDataProvider
{
  private static ProjectSpecificDataProvider instance = new ProjectSpecificDataProvider();
  private IProjectSpecificDataProvider provider;
  
  private void checkProvider() {}
  
  public static ProjectSpecificDataProvider getInstance()
  {
    return instance;
  }
  
  public boolean getAllowExplicitContent()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getAllowExplicitContent();
    }
    return false;
  }
  
  public String getAutoSuggestdDataSource()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getAutoSuggestdDataSource();
    }
    return null;
  }
  
  public String getCombinedContentRating()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getCombinedContentRating();
    }
    return null;
  }
  
  public String getConnectedLocale()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getConnectedLocale();
    }
    return null;
  }
  
  public String getConnectedLocale(boolean paramBoolean)
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getConnectedLocale(paramBoolean);
    }
    return null;
  }
  
  public String getContentRestrictions()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getContentRestrictions();
    }
    return null;
  }
  
  public String getCurrentSandboxID()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getCurrentSandboxID();
    }
    return null;
  }
  
  public boolean getInitializeComplete()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getInitializeComplete();
    }
    return false;
  }
  
  public boolean getIsForXboxOne()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getIsForXboxOne();
    }
    return false;
  }
  
  public boolean getIsFreeAccount()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getIsFreeAccount();
    }
    return true;
  }
  
  public boolean getIsXboxMusicSupported()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getIsXboxMusicSupported();
    }
    return false;
  }
  
  public String getLegalLocale()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getLegalLocale();
    }
    return null;
  }
  
  public String getMembershipLevel()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getMembershipLevel();
    }
    return null;
  }
  
  public String getPrivileges()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getPrivileges();
    }
    return "";
  }
  
  public String getRegion()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getRegion();
    }
    return null;
  }
  
  public String getSCDRpsTicket()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getSCDRpsTicket();
    }
    return null;
  }
  
  public String getVersionCheckUrl()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getVersionCheckUrl();
    }
    return null;
  }
  
  public int getVersionCode()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getVersionCode();
    }
    return 0;
  }
  
  public String getWindowsLiveClientId()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getWindowsLiveClientId();
    }
    return null;
  }
  
  public String getXuidString()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.getXuidString();
    }
    return null;
  }
  
  public boolean isDeviceLocaleKnown()
  {
    checkProvider();
    if (this.provider != null) {
      return this.provider.isDeviceLocaleKnown();
    }
    return true;
  }
  
  public void resetModels(boolean paramBoolean)
  {
    checkProvider();
    if (this.provider != null) {
      this.provider.resetModels(paramBoolean);
    }
  }
  
  public void setPrivileges(String paramString)
  {
    checkProvider();
    if (this.provider != null) {
      this.provider.setPrivileges(paramString);
    }
  }
  
  public void setProvider(IProjectSpecificDataProvider paramIProjectSpecificDataProvider)
  {
    this.provider = paramIProjectSpecificDataProvider;
  }
  
  public void setSCDRpsTicket(String paramString)
  {
    checkProvider();
    if (this.provider != null) {
      this.provider.setSCDRpsTicket(paramString);
    }
  }
  
  public void setXuidString(String paramString)
  {
    checkProvider();
    if (this.provider != null) {
      this.provider.setXuidString(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ProjectSpecificDataProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */