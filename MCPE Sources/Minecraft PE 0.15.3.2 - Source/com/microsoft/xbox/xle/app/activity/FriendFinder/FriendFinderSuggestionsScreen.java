package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.content.Context;
import android.util.AttributeSet;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xboxtcui.R.layout;

public class FriendFinderSuggestionsScreen
  extends ActivityBase
{
  public FriendFinderSuggestionsScreen() {}
  
  public FriendFinderSuggestionsScreen(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected String getActivityName()
  {
    return "Friend Finder Suggestions";
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    this.viewModel = new FriendFinderSuggestionsScreenViewModel(this);
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friendfinder_suggestions_screen);
  }
  
  public void onStart()
  {
    super.onStart();
    FriendFinderType localFriendFinderType = NavigationManager.getInstance().getActivityParameters().getFriendFinderType();
    switch (localFriendFinderType)
    {
    default: 
      return;
    case ???: 
      UTCFriendFinder.trackFacebookAddFriendView(getActivityName());
      return;
    }
    UTCFriendFinder.trackContactsFindFriendsView(getActivityName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderSuggestionsScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */