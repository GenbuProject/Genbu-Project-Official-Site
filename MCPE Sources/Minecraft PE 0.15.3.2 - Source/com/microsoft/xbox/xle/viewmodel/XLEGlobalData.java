package com.microsoft.xbox.xle.viewmodel;

import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.MultiSelection;
import com.microsoft.xbox.toolkit.ProjectSpecificDataProvider;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;

public class XLEGlobalData
{
  private static final int MAX_SEARCH_TEXT_LENGTH = 120;
  private boolean autoLoginStarted;
  private Class<? extends ActivityBase> defaultScreenClass;
  private long errorCodeForLogin;
  private boolean forceRefreshProfile;
  private HashSet<Class<? extends ViewModelBase>> forceRefreshVMs;
  private boolean friendListUpdated = false;
  private boolean hideCollectionFilter = false;
  private boolean isLoggedIn;
  private boolean isOffline = true;
  private boolean launchTitleIsBrowser = false;
  private String pivotTitle;
  private Class<? extends ViewModelBase> searchFilterSetterClass;
  private String searchTag;
  private String selectedAchievementKey;
  private String selectedDataSource;
  private String selectedGamertag;
  private ArrayList<URI> selectedImages;
  private MultiSelection<FriendSelectorItem> selectedRecipients;
  private String selectedXuid;
  private boolean showLoginError;
  private long titleId;
  
  public static XLEGlobalData getInstance()
  {
    return XLEGlobalDataHolder.instance;
  }
  
  public void AddForceRefresh(Class<? extends ViewModelBase> paramClass)
  {
    
    if (this.forceRefreshVMs == null) {
      this.forceRefreshVMs = new HashSet();
    }
    this.forceRefreshVMs.add(paramClass);
  }
  
  public boolean CheckDrainShouldRefresh(Class<? extends ViewModelBase> paramClass)
  {
    return (this.forceRefreshVMs != null) && (this.forceRefreshVMs.remove(paramClass));
  }
  
  public boolean getAutoLoginStarted()
  {
    return this.autoLoginStarted;
  }
  
  public Class<? extends ActivityBase> getDefaultScreenClass()
  {
    return this.defaultScreenClass;
  }
  
  public boolean getForceRefreshProfile()
  {
    return this.forceRefreshProfile;
  }
  
  public boolean getFriendListUpdated()
  {
    return this.friendListUpdated;
  }
  
  public boolean getHideCollectionFilter()
  {
    return this.hideCollectionFilter;
  }
  
  public boolean getIsLoggedIn()
  {
    return this.isLoggedIn;
  }
  
  public boolean getIsOffline()
  {
    return this.isOffline;
  }
  
  public boolean getIsOnline()
  {
    return !this.isOffline;
  }
  
  public long getLastLoginError()
  {
    long l = this.errorCodeForLogin;
    this.errorCodeForLogin = 0L;
    return l;
  }
  
  public boolean getLaunchTitleIsBrowser()
  {
    return this.launchTitleIsBrowser;
  }
  
  public String getPivotTitle()
  {
    return this.pivotTitle;
  }
  
  public Class<? extends ViewModelBase> getSearchFilterSetterClass()
  {
    return this.searchFilterSetterClass;
  }
  
  public String getSearchTag()
  {
    return this.searchTag;
  }
  
  public String getSelectedAchievementKey()
  {
    return this.selectedAchievementKey;
  }
  
  public String getSelectedDataSource()
  {
    return this.selectedDataSource;
  }
  
  public String getSelectedGamertag()
  {
    return this.selectedGamertag;
  }
  
  public ArrayList<URI> getSelectedImages()
  {
    return this.selectedImages;
  }
  
  public MultiSelection<FriendSelectorItem> getSelectedRecipients()
  {
    if (this.selectedRecipients == null) {
      this.selectedRecipients = new MultiSelection();
    }
    return this.selectedRecipients;
  }
  
  public long getSelectedTitleId()
  {
    return this.titleId;
  }
  
  public String getSelectedXuid()
  {
    if (JavaUtil.isNullOrEmpty(this.selectedXuid)) {
      return ProjectSpecificDataProvider.getInstance().getXuidString();
    }
    return this.selectedXuid;
  }
  
  public boolean getShowLoginError()
  {
    boolean bool = this.showLoginError;
    this.showLoginError = false;
    return bool;
  }
  
  public void resetGlobalParameters()
  {
    this.selectedGamertag = null;
    this.selectedAchievementKey = null;
    this.selectedDataSource = null;
    this.isLoggedIn = false;
    this.showLoginError = false;
    this.isOffline = true;
    this.searchTag = null;
    this.selectedImages = null;
    this.titleId = 0L;
    this.forceRefreshVMs = null;
  }
  
  public void setAutoLoginStarted(boolean paramBoolean)
  {
    this.autoLoginStarted = paramBoolean;
  }
  
  public void setDefaultScreenClass(Class<? extends ActivityBase> paramClass)
  {
    this.defaultScreenClass = paramClass;
  }
  
  public void setForceRefreshProfile(boolean paramBoolean)
  {
    this.forceRefreshProfile = paramBoolean;
  }
  
  public void setFriendListUpdated(boolean paramBoolean)
  {
    this.friendListUpdated = paramBoolean;
  }
  
  public void setHideCollectionFilter(boolean paramBoolean)
  {
    this.hideCollectionFilter = paramBoolean;
  }
  
  public void setIsOffline(boolean paramBoolean)
  {
    this.isOffline = paramBoolean;
  }
  
  public void setLaunchTitleIsBrowser(boolean paramBoolean)
  {
    this.launchTitleIsBrowser = paramBoolean;
  }
  
  public void setLoggedIn(boolean paramBoolean)
  {
    this.isLoggedIn = paramBoolean;
  }
  
  public void setLoginErrorCode(long paramLong)
  {
    this.errorCodeForLogin = paramLong;
  }
  
  public void setPivotTitle(String paramString)
  {
    this.pivotTitle = paramString;
  }
  
  public void setSearchTag(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 120))
    {
      this.searchTag = paramString.substring(0, 120);
      return;
    }
    this.searchTag = paramString;
  }
  
  public void setSelectedAchievementKey(String paramString)
  {
    this.selectedAchievementKey = paramString;
  }
  
  public void setSelectedDataSource(String paramString)
  {
    this.selectedDataSource = paramString;
  }
  
  public void setSelectedGamertag(String paramString)
  {
    this.selectedGamertag = paramString;
  }
  
  public void setSelectedImages(ArrayList<URI> paramArrayList)
  {
    this.selectedImages = paramArrayList;
  }
  
  public void setSelectedTitleId(long paramLong)
  {
    this.titleId = paramLong;
  }
  
  public void setSelectedXuid(String paramString)
  {
    this.selectedXuid = paramString;
  }
  
  public void setShowLoginError(boolean paramBoolean)
  {
    this.showLoginError = paramBoolean;
  }
  
  private static class XLEGlobalDataHolder
  {
    public static final XLEGlobalData instance = new XLEGlobalData(null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\viewmodel\XLEGlobalData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */