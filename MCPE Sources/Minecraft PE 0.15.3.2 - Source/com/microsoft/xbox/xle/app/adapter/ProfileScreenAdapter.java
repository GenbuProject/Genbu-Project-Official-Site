package com.microsoft.xbox.xle.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.FastProgressBar;
import com.microsoft.xbox.toolkit.ui.XLERoundedUniversalImageView;
import com.microsoft.xbox.xle.app.ImageUtil;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xbox.xle.app.activity.Profile.ProfileScreenViewModel;
import com.microsoft.xbox.xle.telemetry.helpers.UTCPeopleHub;
import com.microsoft.xbox.xle.ui.IconFontToggleButton;
import com.microsoft.xbox.xle.ui.XLERootView;
import com.microsoft.xbox.xle.viewmodel.AdapterBase;
import com.microsoft.xboxtcui.R.drawable;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxAppDeepLinker;

public class ProfileScreenAdapter
  extends AdapterBase
{
  private IconFontToggleButton blockButton;
  private ScrollView contentScrollView;
  private IconFontToggleButton followButton;
  private XLERoundedUniversalImageView gamerPicImageView;
  private CustomTypefaceTextView gamerscoreIconTextView;
  private CustomTypefaceTextView gamerscoreTextView;
  private CustomTypefaceTextView gamertagTextView;
  private FastProgressBar loadingProgressBar;
  private IconFontToggleButton muteButton;
  private CustomTypefaceTextView realNameTextView;
  private IconFontToggleButton reportButton;
  private XLERootView rootView;
  private IconFontToggleButton viewInXboxAppButton;
  private CustomTypefaceTextView viewInXboxAppSubTextView;
  private ProfileScreenViewModel viewModel;
  
  public ProfileScreenAdapter(ProfileScreenViewModel paramProfileScreenViewModel)
  {
    super(paramProfileScreenViewModel);
    this.viewModel = paramProfileScreenViewModel;
    this.rootView = ((XLERootView)findViewById(R.id.profile_root));
    this.gamerPicImageView = ((XLERoundedUniversalImageView)findViewById(R.id.profile_gamerpic));
    this.loadingProgressBar = ((FastProgressBar)findViewById(R.id.profile_screen_loading));
    this.contentScrollView = ((ScrollView)findViewById(R.id.profile_screen_content_list));
    this.realNameTextView = ((CustomTypefaceTextView)findViewById(R.id.profile_realname));
    this.gamerscoreIconTextView = ((CustomTypefaceTextView)findViewById(R.id.profile_gamerscore_icon));
    this.gamerscoreTextView = ((CustomTypefaceTextView)findViewById(R.id.profile_gamerscore));
    this.gamertagTextView = ((CustomTypefaceTextView)findViewById(R.id.profile_gamertag));
    this.followButton = ((IconFontToggleButton)findViewById(R.id.profile_follow));
    this.muteButton = ((IconFontToggleButton)findViewById(R.id.profile_mute));
    this.blockButton = ((IconFontToggleButton)findViewById(R.id.profile_block));
    this.reportButton = ((IconFontToggleButton)findViewById(R.id.profile_report));
    this.viewInXboxAppButton = ((IconFontToggleButton)findViewById(R.id.profile_view_in_xbox_app));
    this.viewInXboxAppSubTextView = ((CustomTypefaceTextView)findViewById(R.id.profile_view_in_xbox_app_subtext));
    this.viewInXboxAppButton.setVisibility(0);
    this.viewInXboxAppButton.setEnabled(true);
    this.viewInXboxAppButton.setChecked(true);
    if (this.viewModel.isMeProfile())
    {
      this.followButton.setVisibility(8);
      this.muteButton.setVisibility(8);
      this.blockButton.setVisibility(8);
      this.reportButton.setVisibility(8);
      this.viewInXboxAppSubTextView.setText(R.string.Profile_ViewInXboxApp_Details_MeProfile);
      return;
    }
    this.followButton.setVisibility(0);
    this.followButton.setEnabled(true);
    this.muteButton.setVisibility(0);
    this.muteButton.setEnabled(true);
    this.muteButton.setChecked(false);
    this.blockButton.setVisibility(0);
    this.blockButton.setEnabled(false);
    this.reportButton.setVisibility(0);
    this.reportButton.setEnabled(true);
    this.reportButton.setChecked(false);
    this.viewInXboxAppSubTextView.setText(R.string.Profile_ViewInXboxApp_Details_YouProfile);
  }
  
  public void onStart()
  {
    super.onStart();
    if (this.followButton != null) {
      this.followButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ProfileScreenAdapter.this.viewModel.navigateToChangeRelationship();
        }
      });
    }
    if (this.muteButton != null) {
      this.muteButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ProfileScreenAdapter.this.muteButton.toggle();
          ProfileScreenAdapter.this.muteButton.setEnabled(false);
          if (ProfileScreenAdapter.this.muteButton.isChecked())
          {
            UTCPeopleHub.trackMute(true);
            ProfileScreenAdapter.this.viewModel.muteUser();
            return;
          }
          UTCPeopleHub.trackMute(false);
          ProfileScreenAdapter.this.viewModel.unmuteUser();
        }
      });
    }
    if (this.blockButton != null) {
      this.blockButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ProfileScreenAdapter.this.blockButton.toggle();
          ProfileScreenAdapter.this.blockButton.setEnabled(false);
          if (ProfileScreenAdapter.this.blockButton.isChecked())
          {
            UTCPeopleHub.trackBlock();
            ProfileScreenAdapter.this.viewModel.blockUser();
            return;
          }
          UTCPeopleHub.trackUnblock();
          ProfileScreenAdapter.this.viewModel.unblockUser();
        }
      });
    }
    if (this.reportButton != null) {
      this.reportButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          UTCPeopleHub.trackReport();
          ProfileScreenAdapter.this.viewModel.showReportDialog();
        }
      });
    }
    if (this.viewInXboxAppButton != null)
    {
      if (XboxAppDeepLinker.appDeeplinkingSupported()) {
        this.viewInXboxAppButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            UTCPeopleHub.trackViewInXboxApp();
            ProfileScreenAdapter.this.viewModel.launchXboxApp();
          }
        });
      }
    }
    else {
      return;
    }
    this.viewInXboxAppButton.setVisibility(8);
    this.viewInXboxAppSubTextView.setVisibility(8);
  }
  
  protected void updateViewOverride()
  {
    boolean bool2 = true;
    if (this.rootView != null) {
      this.rootView.setBackgroundColor(this.viewModel.getPreferredColor());
    }
    Object localObject = this.loadingProgressBar;
    int i;
    if (this.viewModel.isBusy())
    {
      i = 0;
      ((FastProgressBar)localObject).setVisibility(i);
      localObject = this.contentScrollView;
      if (!this.viewModel.isBusy()) {
        break label387;
      }
      i = 8;
      label66:
      ((ScrollView)localObject).setVisibility(i);
      if (this.gamerPicImageView != null) {
        this.gamerPicImageView.setImageURI2(ImageUtil.getMedium(this.viewModel.getGamerPicUrl()), R.drawable.gamerpic_missing, R.drawable.gamerpic_missing);
      }
      if (this.realNameTextView != null)
      {
        localObject = this.viewModel.getRealName();
        if (JavaUtil.isNullOrEmpty((String)localObject)) {
          break label392;
        }
        this.realNameTextView.setText((CharSequence)localObject);
        this.realNameTextView.setVisibility(0);
      }
      label143:
      if ((this.gamerscoreTextView != null) && (this.gamerscoreIconTextView != null))
      {
        localObject = this.viewModel.getGamerScore();
        if (!JavaUtil.isNullOrEmpty((String)localObject))
        {
          XLEUtil.updateTextAndVisibilityIfNotNull(this.gamerscoreTextView, (CharSequence)localObject, 0);
          XLEUtil.updateVisibilityIfNotNull(this.gamerscoreIconTextView, 0);
        }
      }
      if (this.gamertagTextView != null)
      {
        localObject = this.viewModel.getGamerTag();
        if (!JavaUtil.isNullOrEmpty((String)localObject)) {
          XLEUtil.updateTextAndVisibilityIfNotNull(this.gamertagTextView, (CharSequence)localObject, 0);
        }
      }
      if (!this.viewModel.isMeProfile())
      {
        if ((!this.viewModel.getIsAddingUserToBlockList()) && (!this.viewModel.getIsRemovingUserFromBlockList())) {
          break label404;
        }
        i = 1;
        label258:
        this.followButton.setChecked(this.viewModel.isCallerFollowingTarget());
        localObject = this.followButton;
        if ((i != 0) || (this.viewModel.getIsBlocked())) {
          break label409;
        }
        bool1 = true;
        label294:
        ((IconFontToggleButton)localObject).setEnabled(bool1);
        this.muteButton.setChecked(this.viewModel.getIsMuted());
        localObject = this.muteButton;
        if ((this.viewModel.getIsAddingUserToMutedList()) || (this.viewModel.getIsRemovingUserFromMutedList())) {
          break label414;
        }
        bool1 = true;
        label342:
        ((IconFontToggleButton)localObject).setEnabled(bool1);
        this.blockButton.setChecked(this.viewModel.getIsBlocked());
        localObject = this.blockButton;
        if (i != 0) {
          break label419;
        }
      }
    }
    label387:
    label392:
    label404:
    label409:
    label414:
    label419:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      ((IconFontToggleButton)localObject).setEnabled(bool1);
      return;
      i = 8;
      break;
      i = 0;
      break label66;
      this.realNameTextView.setVisibility(8);
      break label143;
      i = 0;
      break label258;
      bool1 = false;
      break label294;
      bool1 = false;
      break label342;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\adapter\ProfileScreenAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */