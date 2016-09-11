package com.microsoft.xbox.xle.app.activity.FriendFinder;

import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xboxtcui.R.layout;

public class FriendFinderVerifyCodeScreen
  extends ActivityBase
{
  protected String getActivityName()
  {
    return "Friend Finder Verify Code";
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    this.viewModel = new FriendFinderVerifyCodeScreenViewModel(this);
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friendfinder_verify_code_screen);
  }
  
  public void onStart()
  {
    super.onStart();
    UTCFriendFinder.trackContactsVerifyPhoneView(getName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderVerifyCodeScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */