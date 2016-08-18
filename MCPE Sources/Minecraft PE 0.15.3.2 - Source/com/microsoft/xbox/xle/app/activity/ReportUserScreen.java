package com.microsoft.xbox.xle.app.activity;

import com.microsoft.xbox.xle.telemetry.helpers.UTCReportUser;
import com.microsoft.xbox.xle.viewmodel.ReportUserScreenViewModel;
import com.microsoft.xboxtcui.R.layout;

public class ReportUserScreen
  extends ActivityBase
{
  private ReportUserScreenViewModel reportUserScreenViewModel;
  
  protected String getActivityName()
  {
    return "Report user";
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    this.viewModel = new ReportUserScreenViewModel(this);
    this.reportUserScreenViewModel = ((ReportUserScreenViewModel)this.viewModel);
    UTCReportUser.trackReportView(getName(), this.reportUserScreenViewModel.getXUID());
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.report_user_screen);
  }
  
  public void onStart()
  {
    super.onStart();
    setBackgroundColor(this.reportUserScreenViewModel.getPreferredColor());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\ReportUserScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */