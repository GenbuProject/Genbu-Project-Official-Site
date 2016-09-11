package com.microsoft.xbox.xle.app.activity.FriendFinder;

import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xboxtcui.R.layout;

public class FriendFinderPhoneInviteScreen
  extends ActivityBase
{
  protected String getActivityName()
  {
    return "Friend Finder Phone Invite";
  }
  
  public void onCreate()
  {
    super.onCreate();
    XLEAssert.fail("This isn't supported yet.");
    onCreateContentView();
    this.viewModel = new FriendFinderPhoneInviteScreenViewModel(this);
    UTCFriendFinder.trackContactsInviteFriendsView(getName());
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friendfinder_suggestions_screen);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderPhoneInviteScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */