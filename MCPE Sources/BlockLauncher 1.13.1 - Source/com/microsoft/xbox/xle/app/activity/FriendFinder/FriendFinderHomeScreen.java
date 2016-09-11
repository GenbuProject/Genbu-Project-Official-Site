package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.content.Context;
import android.util.AttributeSet;
import com.microsoft.xbox.toolkit.ProjectSpecificDataProvider;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xboxtcui.R.layout;

public class FriendFinderHomeScreen
  extends ActivityBase
{
  public FriendFinderHomeScreen() {}
  
  public FriendFinderHomeScreen(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected String getActivityName()
  {
    return "FriendFinderHome";
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    this.viewModel = new FriendFinderHomeScreenViewModel(this);
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friend_finder_home_screen);
  }
  
  public void onStart()
  {
    super.onStart();
    UTCFriendFinder.trackFriendFinderView(getActivityName(), ProjectSpecificDataProvider.getInstance().getXuidString());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderHomeScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */