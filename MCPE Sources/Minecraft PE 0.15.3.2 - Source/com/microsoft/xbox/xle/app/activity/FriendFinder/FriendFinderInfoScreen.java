package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySettingValue;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.toolkit.Ready;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.xle.app.activity.ActivityBase;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class FriendFinderInfoScreen
  extends ActivityBase
{
  private FriendFinderType infoType = FriendFinderType.UNKNOWN;
  private XLEButton nextButton;
  private CustomTypefaceTextView subtitleTextView;
  private CustomTypefaceTextView titleTextView;
  
  public FriendFinderInfoScreen() {}
  
  public FriendFinderInfoScreen(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private String getFacebookText()
  {
    PrivacySettings.PrivacySettingValue localPrivacySettingValue = PrivacySettings.PrivacySettingValue.getPrivacySettingValue(ProfileModel.getMeProfileModel().getShareRealNameStatus());
    switch (localPrivacySettingValue)
    {
    default: 
      return XboxTcuiSdk.getResources().getString(R.string.FriendFinder_LinkFacebook_Dialog_Text_Default);
    case ???: 
      return multiLineText(new int[] { R.string.FriendFinder_LinkFacebook_Dialog_Text_Default, R.string.FriendFinder_LinkFacebook_Dialog_Text_NotSet_LineTwo, R.string.FriendFinder_LinkFacebook_Dialog_Text_NotSet_LineThree });
    case ???: 
      return multiLineText(new int[] { R.string.FriendFinder_LinkFacebook_Dialog_Text_Default, R.string.FriendFinder_LinkFacebook_Dialog_Text_Blocked_LineTwo, R.string.FriendFinder_LinkFacebook_Dialog_Text_Blocked_LineThree });
    }
    return multiLineText(new int[] { R.string.FriendFinder_LinkFacebook_Dialog_Text_Default, R.string.FriendFinder_LinkFacebook_Dialog_Text_PeopleIChoose_LineTwo });
  }
  
  private String getPhoneText()
  {
    PrivacySettings.PrivacySettingValue localPrivacySettingValue = PrivacySettings.PrivacySettingValue.getPrivacySettingValue(ProfileModel.getMeProfileModel().getShareRealNameStatus());
    switch (localPrivacySettingValue)
    {
    default: 
      return multiLineText(new int[] { R.string.FriendFinder_LinkPhone_Dialog_Text_LineOne, R.string.FriendFinder_LinkPhone_Dialog_Text_LineTwo });
    case ???: 
    case ???: 
      return multiLineText(new int[] { R.string.FriendFinder_LinkPhone_Dialog_Text_LineOne, R.string.FriendFinder_LinkPhone_Dialog_Text_LineTwo, R.string.FriendFinder_LinkPhone_Dialog_Text_LineThree });
    case ???: 
    case ???: 
      return multiLineText(new int[] { R.string.FriendFinder_LinkPhone_Dialog_Text_LineOne, R.string.FriendFinder_LinkPhone_Dialog_Text_LineTwo, R.string.FriendFinder_LinkPhone_Dialog_Text_RealNameSharedWithContacts, R.string.FriendFinder_LinkPhone_Dialog_Text_LineThree });
    }
    return multiLineText(new int[] { R.string.FriendFinder_LinkPhone_Dialog_Text_LineOne, R.string.FriendFinder_LinkPhone_Dialog_Text_LineTwo, R.string.FriendFinder_LinkPhone_Dialog_Text_RealNameSharedWithContacts, R.string.FriendFinder_LinkPhone_Dialog_Text_ACoupleNotes, R.string.FriendFinder_LinkPhone_Dialog_Text_LineThree });
  }
  
  private String multiLineText(int... paramVarArgs)
  {
    Object localObject;
    if (paramVarArgs.length == 0)
    {
      localObject = "";
      return (String)localObject;
    }
    String str = XboxTcuiSdk.getResources().getString(paramVarArgs[0]);
    int i = 1;
    for (;;)
    {
      localObject = str;
      if (i >= paramVarArgs.length) {
        break;
      }
      str = str + "\n\n" + XboxTcuiSdk.getResources().getString(paramVarArgs[i]);
      i += 1;
    }
  }
  
  protected String getActivityName()
  {
    switch (3.$SwitchMap$com$microsoft$xbox$service$model$friendfinder$FriendFinderType[this.infoType.ordinal()])
    {
    default: 
      return "Friend finder info";
    case 1: 
      return "Friend finder facebook info";
    }
    return "Friend finder phone info";
  }
  
  public boolean onBackButtonPressed()
  {
    UTCFriendFinder.trackBackButtonPressed(getName(), this.infoType);
    return super.onBackButtonPressed();
  }
  
  public void onCreate()
  {
    super.onCreate();
    onCreateContentView();
    ActivityParameters localActivityParameters = NavigationManager.getInstance().getActivityParameters();
    XLEAssert.assertNotNull(localActivityParameters);
    if (localActivityParameters != null)
    {
      this.infoType = localActivityParameters.getFriendFinderType();
      if (this.infoType != FriendFinderType.UNKNOWN) {
        break label50;
      }
    }
    label50:
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertFalse("Expected info type", bool);
      return;
    }
  }
  
  public void onCreateContentView()
  {
    setContentView(R.layout.friend_finder_info_screen);
    this.titleTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_info_title));
    this.subtitleTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_info_subtitle));
    this.nextButton = ((XLEButton)findViewById(R.id.friendfinder_info_next));
    XLEAssert.assertNotNull(this.titleTextView);
    XLEAssert.assertNotNull(this.subtitleTextView);
    XLEAssert.assertNotNull(this.nextButton);
  }
  
  public void onStart()
  {
    super.onStart();
    switch (3.$SwitchMap$com$microsoft$xbox$service$model$friendfinder$FriendFinderType[this.infoType.ordinal()])
    {
    default: 
      return;
    case 1: 
      this.titleTextView.setText(R.string.FriendFinder_LinkFacebook_Dialog_Title);
      this.subtitleTextView.setText(getFacebookText());
      this.nextButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          UTCFriendFinder.trackFacebookOptInNext(FriendFinderInfoScreen.this.getName());
          XLEAssert.assertTrue(FacebookManager.getFacebookManagerReady().getIsReady());
          FacebookManager.getInstance().login();
        }
      });
      UTCFriendFinder.trackFacebookOptInView(getName());
      return;
    }
    this.titleTextView.setText(R.string.FriendFinder_LinkPhone_Dialog_Title);
    this.subtitleTextView.setText(getPhoneText());
    this.nextButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UTCFriendFinder.trackPhoneContactsNext(FriendFinderInfoScreen.this.getName());
        try
        {
          NavigationManager.getInstance().PushScreen(FriendFinderAddPhoneScreen.class);
          return;
        }
        catch (XLEException paramAnonymousView) {}
      }
    });
    UTCFriendFinder.trackContactsOptInView(getName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderInfoScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */