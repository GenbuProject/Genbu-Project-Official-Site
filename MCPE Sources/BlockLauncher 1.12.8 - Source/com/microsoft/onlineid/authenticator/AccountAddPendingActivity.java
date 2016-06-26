package com.microsoft.onlineid.authenticator;

import android.os.Bundle;
import android.view.View;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.ui.ProgressView;
import com.microsoft.onlineid.sdk.R.id;
import com.microsoft.onlineid.sdk.R.layout;
import com.microsoft.onlineid.ui.MsaSdkActivity;

public class AccountAddPendingActivity
  extends MsaSdkActivity
{
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    setContentView(R.layout.static_page);
    super.onCreate(paramBundle);
    paramBundle = (ProgressView)findViewById(R.id.progressView);
    paramBundle.setVisibility(0);
    paramBundle.startAnimation();
    findViewById(R.id.static_page_header).setVisibility(8);
    findViewById(R.id.static_page_body_first).setVisibility(8);
    findViewById(R.id.static_page_body_second).setVisibility(8);
  }
  
  protected void onStart()
  {
    super.onStart();
    ClientAnalytics.get().logScreenView("Account add pending screen");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\authenticator\AccountAddPendingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */