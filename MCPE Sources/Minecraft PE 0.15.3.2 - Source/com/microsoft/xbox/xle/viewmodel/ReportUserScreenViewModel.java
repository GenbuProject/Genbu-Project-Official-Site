package com.microsoft.xbox.xle.viewmodel;

import android.content.res.Resources;
import com.microsoft.xbox.idp.telemetry.helpers.UTCPageView;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.model.sls.FeedbackType;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.DialogManager;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.app.adapter.ReportUserScreenAdapter;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;

public class ReportUserScreenViewModel
  extends ViewModelBase
{
  private FeedbackType[] feedbackReasons;
  private boolean isSubmittingReport;
  private ProfileModel model;
  private FeedbackType selectedReason;
  private SubmitReportAsyncTask submitReportAsyncTask;
  
  public ReportUserScreenViewModel(ScreenLayout paramScreenLayout)
  {
    super(paramScreenLayout);
    paramScreenLayout = NavigationManager.getInstance().getActivityParameters().getSelectedProfile();
    boolean bool;
    label62:
    FeedbackType localFeedbackType1;
    FeedbackType localFeedbackType2;
    if (!JavaUtil.isNullOrEmpty(paramScreenLayout))
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (JavaUtil.isNullOrEmpty(paramScreenLayout)) {
        popScreenWithXuidError();
      }
      this.model = ProfileModel.getProfileModel(paramScreenLayout);
      if (JavaUtil.isNullOrEmpty(this.model.getGamerTag())) {
        break label157;
      }
      bool = true;
      XLEAssert.assertTrue(bool);
      this.adapter = new ReportUserScreenAdapter(this);
      localFeedbackType1 = FeedbackType.UserContentPersonalInfo;
      localFeedbackType2 = FeedbackType.FairPlayCheater;
      if (!JavaUtil.isNullOrEmpty(this.model.getRealName())) {
        break label162;
      }
    }
    label157:
    label162:
    for (paramScreenLayout = FeedbackType.UserContentGamertag;; paramScreenLayout = FeedbackType.UserContentRealName)
    {
      this.feedbackReasons = new FeedbackType[] { localFeedbackType1, localFeedbackType2, paramScreenLayout, FeedbackType.UserContentGamerpic, FeedbackType.FairPlayQuitter, FeedbackType.FairplayUnsporting, FeedbackType.CommsAbusiveVoice };
      return;
      bool = false;
      break;
      bool = false;
      break label62;
    }
  }
  
  private void onSubmitReportCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      DialogManager.getInstance().showToast(R.string.ProfileCard_Report_SuccessSubtext);
      onBackButtonPressed();
      return;
    }
    showError(R.string.ProfileCard_Report_Error);
  }
  
  private void popScreenWithXuidError()
  {
    try
    {
      showError(R.string.Service_ErrorText);
      NavigationManager.getInstance().PopScreen();
      return;
    }
    catch (XLEException localXLEException) {}
  }
  
  public int getPreferredColor()
  {
    return this.model.getPreferedColor();
  }
  
  public FeedbackType getReason()
  {
    return this.selectedReason;
  }
  
  public ArrayList<String> getReasonTitles()
  {
    ArrayList localArrayList = new ArrayList(this.feedbackReasons.length);
    localArrayList.add(XboxTcuiSdk.getResources().getString(R.string.ProfileCard_Report_SelectReason));
    FeedbackType[] arrayOfFeedbackType = this.feedbackReasons;
    int j = arrayOfFeedbackType.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(arrayOfFeedbackType[i].getTitle());
      i += 1;
    }
    return localArrayList;
  }
  
  public String getTitle()
  {
    return String.format(XboxTcuiSdk.getResources().getString(R.string.ProfileCard_Report_InfoString_Android), new Object[] { this.model.getGamerTag() });
  }
  
  public String getXUID()
  {
    return this.model.getXuid();
  }
  
  public boolean isBusy()
  {
    return this.isSubmittingReport;
  }
  
  public void load(boolean paramBoolean) {}
  
  public boolean onBackButtonPressed()
  {
    
    try
    {
      NavigationManager.getInstance().PopScreensAndReplace(1, null, false, false, false, NavigationManager.getInstance().getActivityParameters());
      return true;
    }
    catch (XLEException localXLEException) {}
    return false;
  }
  
  public void onRehydrate() {}
  
  protected void onStartOverride() {}
  
  protected void onStopOverride()
  {
    if (this.submitReportAsyncTask != null) {
      this.submitReportAsyncTask.cancel();
    }
  }
  
  public void setReason(int paramInt)
  {
    int i;
    if ((paramInt != 0) && (paramInt - 1 < this.feedbackReasons.length))
    {
      i = 1;
      if (i == 0) {
        break label45;
      }
    }
    label45:
    for (FeedbackType localFeedbackType = this.feedbackReasons[(paramInt - 1)];; localFeedbackType = null)
    {
      this.selectedReason = localFeedbackType;
      updateAdapter();
      return;
      i = 0;
      break;
    }
  }
  
  public void submitReport(String paramString)
  {
    if (this.submitReportAsyncTask != null) {
      this.submitReportAsyncTask.cancel();
    }
    if (this.selectedReason != null)
    {
      this.submitReportAsyncTask = new SubmitReportAsyncTask(this.model, this.selectedReason, paramString, null);
      this.submitReportAsyncTask.load(true);
    }
  }
  
  public boolean validReasonSelected()
  {
    return this.selectedReason != null;
  }
  
  private class SubmitReportAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private FeedbackType feedbackType;
    private ProfileModel model;
    private String textReason;
    
    private SubmitReportAsyncTask(ProfileModel paramProfileModel, FeedbackType paramFeedbackType, String paramString)
    {
      this.model = paramProfileModel;
      this.feedbackType = paramFeedbackType;
      this.textReason = paramString;
    }
    
    protected boolean checkShouldExecute()
    {
      XLEAssert.assertIsUIThread();
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      XLEAssert.assertNotNull(this.model);
      return this.model.submitFeedbackForUser(this.forceLoad, this.feedbackType, this.textReason).getStatus();
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      XLEAssert.assertIsUIThread();
      ReportUserScreenViewModel.this.onSubmitReportCompleted(AsyncActionStatus.NO_CHANGE);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ReportUserScreenViewModel.this.onSubmitReportCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ReportUserScreenViewModel.access$202(ReportUserScreenViewModel.this, true);
      ReportUserScreenViewModel.this.updateAdapter();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\viewmodel\ReportUserScreenViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */