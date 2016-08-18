package com.microsoft.onlineid.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import com.microsoft.onlineid.internal.configuration.Settings;
import com.microsoft.onlineid.internal.log.SendLogsHandler;
import com.microsoft.onlineid.internal.ui.AccountHeaderView;
import com.microsoft.onlineid.sdk.R.string;

public abstract class MsaSdkActivity
  extends Activity
{
  public static final String AuthenticatorIntentFlagTag = "com.microsoft.msa.authenticator.authenticatorFlags";
  public static final int IntentFlagNoFinishAnimation = 1;
  protected SendLogsHandler _logHandler;
  
  protected void onCreate(Bundle paramBundle)
  {
    AccountHeaderView.applyStyle(this, getResources().getString(R.string.webflow_header));
    super.onCreate(paramBundle);
    this._logHandler = new SendLogsHandler(this);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((Settings.isDebugBuild()) && (this._logHandler != null))
    {
      this._logHandler.setSendScreenshot(true);
      this._logHandler.trySendLogsOnKeyEvent(paramInt);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    if ((isFinishing()) && ((getIntent().getIntExtra("com.microsoft.msa.authenticator.authenticatorFlags", 0) & 0x1) == 1)) {
      overridePendingTransition(0, 0);
    }
    super.onPause();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\ui\MsaSdkActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */