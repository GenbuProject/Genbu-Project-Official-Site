package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.content.Context;
import android.util.AttributeSet;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xboxtcui.R.layout;

public class FriendFinderLinkScreen
  extends ActivityBase
{
  private FriendFinderType linkType = FriendFinderType.UNKNOWN;
  
  public FriendFinderLinkScreen() {}
  
  public FriendFinderLinkScreen(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected String getActivityName()
  {
    return "Friend Finder Facebook Link";
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    ActivityParameters localActivityParameters = NavigationManager.getInstance().getActivityParameters();
    XLEAssert.assertNotNull(localActivityParameters);
    boolean bool;
    if (localActivityParameters != null)
    {
      this.linkType = localActivityParameters.getFriendFinderType();
      if (this.linkType == FriendFinderType.UNKNOWN)
      {
        bool = true;
        XLEAssert.assertFalse("Expected link type", bool);
      }
    }
    else
    {
      if (this.linkType != FriendFinderType.UNKNOWN) {
        break label71;
      }
    }
    for (;;)
    {
      label71:
      try
      {
        NavigationManager.getInstance().PopScreen();
        return;
      }
      catch (XLEException localXLEException) {}
      bool = false;
      break;
      if (this.linkType == FriendFinderType.FACEBOOK)
      {
        this.viewModel = new LinkFacebookAccountViewModel(this);
        return;
      }
    }
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friendfinder_link_screen);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderLinkScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */