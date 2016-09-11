package com.microsoft.xbox.xle.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.helpers.UTCPageAction;
import com.microsoft.xbox.idp.telemetry.helpers.UTCPageView;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCCommonDataModel;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.toolkit.JavaUtil;

public class UTCFriendFinder
{
  private static String currentUserXuid;
  
  private static String[] formatXuids(String[] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      String str = paramArrayOfString[i];
      if (!str.startsWith("x:")) {
        paramArrayOfString[i] = ("x:" + str);
      }
      i += 1;
    }
    return paramArrayOfString;
  }
  
  private static void setCurrentUserXuid(String paramString)
  {
    currentUserXuid = paramString;
    setUserIdForCommonData();
  }
  
  private static void setUserIdForCommonData()
  {
    if (!JavaUtil.isNullOrEmpty(currentUserXuid)) {
      UTCCommonDataModel.setUserId(currentUserXuid);
    }
  }
  
  public static void trackAddFacebookFriend(final CharSequence paramCharSequence, String[] paramArrayOfString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
        localUTCAdditionalInfoModel.addValue("selectedXUIDs", UTCFriendFinder.formatXuids(this.val$targetXuids));
        UTCPageAction.track("Friend Finder Facebook - Add Facebook Friend", paramCharSequence, localUTCAdditionalInfoModel);
      }
    });
  }
  
  public static void trackAddFacebookFriendCancel(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Facebook - Add Facebook Friend Cancel", this.val$activityTitle);
      }
    });
  }
  
  public static void trackBackButtonPressed(CharSequence paramCharSequence, FriendFinderType paramFriendFinderType)
  {
    switch (paramFriendFinderType)
    {
    default: 
      return;
    case ???: 
      trackFacebookFriendFinderBack(paramCharSequence);
      return;
    }
    trackPhoneContactsBack(paramCharSequence);
  }
  
  public static void trackContactsAddPhoneView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Contacts - Add Phone View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackContactsFindFriendsView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Contacts - Find Friends View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackContactsInviteFriendsView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Contacts - Invite Friends View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackContactsOptInView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Contacts - Opt In View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackContactsSignUp(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Sign Up", this.val$activityTitle);
      }
    });
  }
  
  public static void trackContactsSuggestions(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Suggestions", this.val$activityTitle);
      }
    });
  }
  
  public static void trackContactsVerifyPhoneView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Contacts - Verify Phone View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackDone(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder - Done", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookAddFriendView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Facebook - Find Friends View", this.val$activityTitle);
      }
    });
  }
  
  private static void trackFacebookFriendFinderBack(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Facebook - Friend Finder Back", this.val$activityTitle);
        UTCPageView.removePage();
      }
    });
  }
  
  public static void trackFacebookLinkAccountView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Facebook -  Link View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookLoginCancel(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Facebook - Login Cancel", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookLoginSuccessful(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Facebook - Login", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookOptInNext(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Facebook - Opt In Next", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookOptInView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Facebook -  Opt In View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookShareView(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageView.track("Friend Finder Facebook - Share View", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookSignup(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder - Facebook Signup", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFacebookSuggestions(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder - Facebook Suggestions", this.val$activityTitle);
      }
    });
  }
  
  public static void trackFriendFinderView(final CharSequence paramCharSequence, String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.setCurrentUserXuid(this.val$currentUserXuid);
        UTCPageView.track("Friend Finder - View", paramCharSequence);
      }
    });
  }
  
  public static void trackGamertagSearch(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder - Gamertag Search", this.val$activityTitle);
      }
    });
  }
  
  public static void trackGamertagSearchSubmit(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder - Gamertag Search Submit", this.val$activityTitle);
      }
    });
  }
  
  public static void trackGamertagSearchSuccess(final CharSequence paramCharSequence, String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
        localUTCAdditionalInfoModel.addValue("targetXUID", "x:" + this.val$xuid);
        UTCPageAction.track("Friend Finder - Gamertag Search Success", paramCharSequence, localUTCAdditionalInfoModel);
      }
    });
  }
  
  public static void trackPhoneContactsAddFriends(final CharSequence paramCharSequence, String[] paramArrayOfString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
        localUTCAdditionalInfoModel.addValue("selectedXUIDs", UTCFriendFinder.formatXuids(this.val$targetXuids));
        UTCPageAction.track("Friend Finder Contacts - Add Friends", paramCharSequence, localUTCAdditionalInfoModel);
      }
    });
  }
  
  private static void trackPhoneContactsBack(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Close", this.val$activityTitle);
        UTCPageView.removePage();
      }
    });
  }
  
  public static void trackPhoneContactsCallMe(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Call Me", this.val$activityTitle);
      }
    });
  }
  
  public static void trackPhoneContactsChangeRegion(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Change Region", this.val$activityTitle);
      }
    });
  }
  
  public static void trackPhoneContactsLinkSuccess(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Link Success", this.val$activityTitle);
      }
    });
  }
  
  public static void trackPhoneContactsNext(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Next", this.val$activityTitle);
      }
    });
  }
  
  public static void trackPhoneContactsResendCode(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Resend Code", this.val$activityTitle);
      }
    });
  }
  
  public static void trackPhoneContactsSendInvite(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Send Invite", this.val$activityTitle);
      }
    });
  }
  
  public static void trackPhoneContactsSkipAddFriends(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Contacts - Skip", this.val$activityTitle);
      }
    });
  }
  
  public static void trackShareFacebookLinkToFeed(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Facebook - Upsell Success", this.val$activityTitle);
      }
    });
  }
  
  public static void trackSkipFacebookSharing(CharSequence paramCharSequence)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCFriendFinder.access$100();
        UTCPageAction.track("Friend Finder Facebook - Upsell Cancel", this.val$activityTitle);
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\telemetry\helpers\UTCFriendFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */