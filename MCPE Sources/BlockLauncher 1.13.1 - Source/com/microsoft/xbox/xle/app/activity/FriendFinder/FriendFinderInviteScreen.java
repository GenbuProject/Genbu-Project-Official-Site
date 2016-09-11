package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.RecommendationType;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.toolkit.Ready;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.xle.app.FriendFinderSettings;
import com.microsoft.xbox.xle.app.FriendFinderSettings.IconImageSize;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.ui.IconFontSubTextButton;
import com.microsoft.xbox.xle.ui.ImageTitleSubtitleButton;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class FriendFinderInviteScreen
  extends ActivityBase
{
  private FriendFinderType inviteType = FriendFinderType.UNKNOWN;
  
  public FriendFinderInviteScreen() {}
  
  public FriendFinderInviteScreen(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private String getFacebookIconUri()
  {
    return FriendFinderSettings.getIconBySize(IPeopleHubResult.RecommendationType.FacebookFriend.name(), FriendFinderSettings.IconImageSize.MEDIUM);
  }
  
  protected String getActivityName()
  {
    return null;
  }
  
  public boolean onBackButtonPressed()
  {
    UTCFriendFinder.trackBackButtonPressed(getName(), this.inviteType);
    return super.onBackButtonPressed();
  }
  
  public void onCreate()
  {
    super.onCreate();
    XLEAssert.assertTrue(FacebookManager.getFacebookManagerReady().getIsReady());
    onCreateContentView();
    ActivityParameters localActivityParameters = NavigationManager.getInstance().getActivityParameters();
    XLEAssert.assertNotNull(localActivityParameters);
    if (localActivityParameters != null)
    {
      this.inviteType = localActivityParameters.getFriendFinderType();
      if (this.inviteType != FriendFinderType.UNKNOWN) {
        break label59;
      }
    }
    label59:
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertFalse("Expected invite type", bool);
      return;
    }
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friendfinder_invite_screen);
  }
  
  public void onStart()
  {
    super.onStart();
    Object localObject = (CustomTypefaceTextView)findViewById(R.id.friendfinder_invite_title);
    CustomTypefaceTextView localCustomTypefaceTextView = (CustomTypefaceTextView)findViewById(R.id.friendfinder_invite_subtitle);
    if (this.inviteType == FriendFinderType.FACEBOOK)
    {
      ((CustomTypefaceTextView)localObject).setText(R.string.FriendFinder_Invite_Facebook_Title);
      localCustomTypefaceTextView.setText(R.string.FriendFinder_Facebook_Upsell_Description_Default_LineTwo);
      localObject = (ImageTitleSubtitleButton)findViewById(R.id.friendfinder_invite_facebook);
      if (localObject != null)
      {
        ((ImageTitleSubtitleButton)localObject).setVisibility(0);
        ((ImageTitleSubtitleButton)localObject).setImageUri(getFacebookIconUri());
        ((ImageTitleSubtitleButton)localObject).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            UTCFriendFinder.trackShareFacebookLinkToFeed(FriendFinderInviteScreen.this.getName());
            FacebookManager.getInstance().shareToFacebook();
          }
        });
      }
      UTCFriendFinder.trackFacebookShareView(getActivityName());
    }
    for (;;)
    {
      localObject = (XLEButton)findViewById(R.id.friendfinder_invite_next);
      if (localObject != null) {
        ((XLEButton)localObject).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            try
            {
              switch (FriendFinderInviteScreen.4.$SwitchMap$com$microsoft$xbox$service$model$friendfinder$FriendFinderType[FriendFinderInviteScreen.this.inviteType.ordinal()])
              {
              }
              for (;;)
              {
                paramAnonymousView = new ActivityParameters();
                paramAnonymousView.putFriendFinderDone(true);
                NavigationManager.getInstance().PushScreen(FriendFinderHomeScreen.class, paramAnonymousView);
                return;
                UTCFriendFinder.trackSkipFacebookSharing(FriendFinderInviteScreen.this.getName());
                continue;
                UTCFriendFinder.trackPhoneContactsNext(FriendFinderInviteScreen.this.getName());
              }
              return;
            }
            catch (XLEException paramAnonymousView) {}
          }
        });
      }
      return;
      if (this.inviteType == FriendFinderType.PHONE)
      {
        ((CustomTypefaceTextView)localObject).setText(R.string.FriendFinder_PhoneInviteFriends_Dialog_Title);
        localCustomTypefaceTextView.setText(XboxTcuiSdk.getResources().getString(R.string.FriendFinder_PhoneInviteFriends_Dialog_Text).replace("-", "\n\n"));
        localObject = (IconFontSubTextButton)findViewById(R.id.friendfinder_invite_phone);
        if (localObject != null)
        {
          ((IconFontSubTextButton)localObject).setVisibility(0);
          ((IconFontSubTextButton)localObject).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              try
              {
                UTCFriendFinder.trackPhoneContactsSendInvite(FriendFinderInviteScreen.this.getName());
                NavigationManager.getInstance().PushScreen(FriendFinderPhoneInviteScreen.class);
                return;
              }
              catch (XLEException paramAnonymousView) {}
            }
          });
        }
        UTCFriendFinder.trackContactsInviteFriendsView(getActivityName());
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderInviteScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */