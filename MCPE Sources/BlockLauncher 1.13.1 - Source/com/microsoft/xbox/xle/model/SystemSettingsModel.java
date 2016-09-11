package com.microsoft.xbox.xle.model;

import android.os.Build.VERSION;
import com.microsoft.xbox.service.model.ModelBase;
import com.microsoft.xbox.service.model.serialization.Version;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.DataLoadUtil;
import com.microsoft.xbox.toolkit.ProjectSpecificDataProvider;
import com.microsoft.xbox.toolkit.SingleEntryLoadingStatus;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.network.IDataLoaderRunnable;
import com.microsoft.xbox.xle.app.FriendFinderSettings;
import com.microsoft.xbox.xle.app.SmartglassSettings;
import java.util.HashSet;

public class SystemSettingsModel
  extends ModelBase<Version>
{
  private FriendFinderSettings friendFinderSettings;
  private final SingleEntryLoadingStatus friendFinderSettingsLoadingStatus = new SingleEntryLoadingStatus();
  private final HashSet<String> hiddenMruItems = new HashSet();
  private int latestVersion = 0;
  private String marketUrl;
  private int minRequiredOSVersion = 0;
  private int minVersion = 0;
  private int[] remoteControlSpecialTitleIds;
  private SmartglassSettings smartglassSettings;
  private final SingleEntryLoadingStatus smartglassSettingsLoadingStatus = new SingleEntryLoadingStatus();
  private OnUpdateExistListener updateExistListener;
  
  public static SystemSettingsModel getInstance()
  {
    return SystemSettingsModelContainer.instance;
  }
  
  private int getMinimumVersion()
  {
    return this.minVersion;
  }
  
  private void onGetFriendFinderSettingsCompleted(AsyncResult<FriendFinderSettings> paramAsyncResult)
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
      {
        this.friendFinderSettings = ((FriendFinderSettings)paramAsyncResult.getResult());
        if (this.friendFinderSettings != null) {
          this.friendFinderSettings.getIconsFromJson(this.friendFinderSettings.ICONS);
        }
      }
      return;
    }
  }
  
  private void onGetSmartglassSettingsCompleted(AsyncResult<SmartglassSettings> paramAsyncResult)
  {
    boolean bool;
    if (Thread.currentThread() == ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
      {
        this.smartglassSettings = ((SmartglassSettings)paramAsyncResult.getResult());
        if (this.smartglassSettings != null)
        {
          this.minRequiredOSVersion = this.smartglassSettings.ANDROID_VERSIONMINOS;
          this.minVersion = this.smartglassSettings.ANDROID_VERSIONMIN;
          this.latestVersion = this.smartglassSettings.ANDROID_VERSIONLATEST;
          this.marketUrl = this.smartglassSettings.ANDROID_VERSIONURL;
          populateHiddenMruItems(this.smartglassSettings.HIDDEN_MRU_ITEMS);
          populateRemoteControlSpecialTitleIds(this.smartglassSettings.REMOTE_CONTROL_SPECIALS);
          if (this.updateExistListener != null)
          {
            if (!getMustUpdate(ProjectSpecificDataProvider.getInstance().getVersionCode())) {
              break label144;
            }
            this.updateExistListener.onMustUpdate();
          }
        }
      }
    }
    label144:
    while (!getHasUpdate(ProjectSpecificDataProvider.getInstance().getVersionCode()))
    {
      return;
      bool = false;
      break;
    }
    this.updateExistListener.onOptionalUpdate();
  }
  
  private void populateHiddenMruItems(String paramString)
  {
    this.hiddenMruItems.clear();
    if (paramString != null)
    {
      paramString = paramString.split(",");
      if (paramString != null)
      {
        int j = paramString.length;
        int i = 0;
        while (i < j)
        {
          Object localObject = paramString[i];
          this.hiddenMruItems.add(localObject);
          i += 1;
        }
      }
    }
  }
  
  private void populateRemoteControlSpecialTitleIds(String paramString)
  {
    if (paramString != null)
    {
      paramString = paramString.split(",");
      if (paramString != null)
      {
        this.remoteControlSpecialTitleIds = new int[paramString.length];
        int m = paramString.length;
        int j = 0;
        int i = 0;
        for (;;)
        {
          if (i < m)
          {
            String str = paramString[i];
            try
            {
              k = Integer.parseInt(str);
              this.remoteControlSpecialTitleIds[j] = k;
              j += 1;
              i += 1;
            }
            catch (NumberFormatException localNumberFormatException)
            {
              for (;;)
              {
                int k = 0;
              }
            }
          }
        }
      }
    }
  }
  
  public boolean getHasUpdate(int paramInt)
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      if ((Build.VERSION.SDK_INT < this.minRequiredOSVersion) || (getLatestVersion() <= paramInt)) {
        break;
      }
      return true;
    }
    return false;
  }
  
  public int getLatestVersion()
  {
    return this.latestVersion;
  }
  
  public String getMarketUrl()
  {
    return this.marketUrl;
  }
  
  public boolean getMustUpdate(int paramInt)
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      if ((Build.VERSION.SDK_INT < this.minRequiredOSVersion) || (getMinimumVersion() <= paramInt)) {
        break;
      }
      return true;
    }
    return false;
  }
  
  public int[] getRemoteControlSpecialTitleIds()
  {
    return this.remoteControlSpecialTitleIds;
  }
  
  public boolean isInHiddenMruItems(String paramString)
  {
    return this.hiddenMruItems.contains(paramString);
  }
  
  public void loadAsync(boolean paramBoolean)
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      DataLoadUtil.StartLoadFromUI(paramBoolean, this.lifetime, null, this.smartglassSettingsLoadingStatus, new GetSmartglassSettingsRunner(this));
      DataLoadUtil.StartLoadFromUI(paramBoolean, this.lifetime, null, this.friendFinderSettingsLoadingStatus, new GetFriendFinderSettingsRunner(this));
      return;
    }
  }
  
  public AsyncResult<FriendFinderSettings> loadFriendFinderSettings(boolean paramBoolean)
  {
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.friendFinderSettingsLoadingStatus, new GetFriendFinderSettingsRunner(this));
  }
  
  public AsyncResult<SmartglassSettings> loadSystemSettings(boolean paramBoolean)
  {
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.smartglassSettingsLoadingStatus, new GetSmartglassSettingsRunner(this));
  }
  
  public void setOnUpdateExistListener(OnUpdateExistListener paramOnUpdateExistListener)
  {
    this.updateExistListener = paramOnUpdateExistListener;
  }
  
  private class GetFriendFinderSettingsRunner
    extends IDataLoaderRunnable<FriendFinderSettings>
  {
    private final SystemSettingsModel caller;
    
    public GetFriendFinderSettingsRunner(SystemSettingsModel paramSystemSettingsModel)
    {
      this.caller = paramSystemSettingsModel;
    }
    
    public FriendFinderSettings buildData()
      throws XLEException
    {
      return null;
    }
    
    public long getDefaultErrorCode()
    {
      return 5001L;
    }
    
    public void onPostExcute(AsyncResult<FriendFinderSettings> paramAsyncResult)
    {
      this.caller.onGetFriendFinderSettingsCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
  
  private class GetSmartglassSettingsRunner
    extends IDataLoaderRunnable<SmartglassSettings>
  {
    private final SystemSettingsModel caller;
    
    public GetSmartglassSettingsRunner(SystemSettingsModel paramSystemSettingsModel)
    {
      this.caller = paramSystemSettingsModel;
    }
    
    public SmartglassSettings buildData()
      throws XLEException
    {
      return null;
    }
    
    public long getDefaultErrorCode()
    {
      return 5001L;
    }
    
    public void onPostExcute(AsyncResult<SmartglassSettings> paramAsyncResult)
    {
      this.caller.onGetSmartglassSettingsCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
  
  public static abstract interface OnUpdateExistListener
  {
    public abstract void onMustUpdate();
    
    public abstract void onOptionalUpdate();
  }
  
  private static class SystemSettingsModelContainer
  {
    private static SystemSettingsModel instance = new SystemSettingsModel(null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\model\SystemSettingsModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */