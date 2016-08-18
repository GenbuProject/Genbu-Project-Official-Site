package com.microsoft.xbox.toolkit;

public abstract interface IProjectSpecificDataProvider
{
  public abstract boolean getAllowExplicitContent();
  
  public abstract String getAutoSuggestdDataSource();
  
  public abstract String getCombinedContentRating();
  
  public abstract String getConnectedLocale();
  
  public abstract String getConnectedLocale(boolean paramBoolean);
  
  public abstract String getContentRestrictions();
  
  public abstract String getCurrentSandboxID();
  
  public abstract boolean getInitializeComplete();
  
  public abstract boolean getIsForXboxOne();
  
  public abstract boolean getIsFreeAccount();
  
  public abstract boolean getIsXboxMusicSupported();
  
  public abstract String getLegalLocale();
  
  public abstract String getMembershipLevel();
  
  public abstract String getPrivileges();
  
  public abstract String getRegion();
  
  public abstract String getSCDRpsTicket();
  
  public abstract String getVersionCheckUrl();
  
  public abstract int getVersionCode();
  
  public abstract String getWindowsLiveClientId();
  
  public abstract String getXuidString();
  
  public abstract boolean isDeviceLocaleKnown();
  
  public abstract void resetModels(boolean paramBoolean);
  
  public abstract void setPrivileges(String paramString);
  
  public abstract void setSCDRpsTicket(String paramString);
  
  public abstract void setXuidString(String paramString);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\IProjectSpecificDataProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */