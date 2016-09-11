package com.microsoft.xbox.xle.app.activity.FriendFinder;

import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xboxtcui.R.layout;

public class FriendFinderAddPhoneScreen
  extends ActivityBase
{
  protected String getActivityName()
  {
    return "Friend Finder Add Phone";
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    this.viewModel = new FriendFinderAddPhoneScreenViewModel(this);
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friendfinder_add_phone_screen);
  }
  
  public void onStart()
  {
    super.onStart();
    UTCFriendFinder.trackContactsAddPhoneView(getName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderAddPhoneScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */