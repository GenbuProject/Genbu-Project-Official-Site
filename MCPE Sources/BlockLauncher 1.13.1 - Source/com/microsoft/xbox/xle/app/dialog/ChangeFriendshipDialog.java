package com.microsoft.xbox.xle.app.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.microsoft.xbox.toolkit.DialogManager;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.XLEManagedDialog;
import com.microsoft.xbox.toolkit.network.ListState;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.FastProgressBar;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.SwitchPanel;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.toolkit.ui.XLECheckBox;
import com.microsoft.xbox.toolkit.ui.XLEClickableLayout;
import com.microsoft.xbox.toolkit.ui.XLEUniversalImageView;
import com.microsoft.xbox.xle.app.ImageUtil;
import com.microsoft.xbox.xle.app.SGProjectSpecificDialogManager;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xbox.xle.telemetry.helpers.UTCChangeRelationship;
import com.microsoft.xbox.xle.viewmodel.ChangeFriendshipDialogViewModel;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.R.color;
import com.microsoft.xboxtcui.R.drawable;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.R.style;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class ChangeFriendshipDialog
  extends XLEManagedDialog
{
  private RadioButton addFavorite;
  private RadioButton addFriend;
  private XLEButton cancelButton;
  private SwitchPanel changeFriendshipSwitchPanel;
  private XLEButton confirmButton;
  private CustomTypefaceTextView favoriteIconView;
  private CustomTypefaceTextView gamertag;
  private FastProgressBar overlayLoadingIndicator;
  private ViewModelBase previousVM;
  private TextView profileAccountTier;
  private CustomTypefaceTextView profileGamerScore;
  private XLEUniversalImageView profilePic;
  private CustomTypefaceTextView realName;
  private XLEClickableLayout removeFriendLayout;
  private XLECheckBox shareRealNameCheckbox;
  private ChangeFriendshipDialogViewModel vm;
  
  public ChangeFriendshipDialog(Context paramContext, ChangeFriendshipDialogViewModel paramChangeFriendshipDialogViewModel, ViewModelBase paramViewModelBase)
  {
    super(paramContext, R.style.TcuiDialog);
    this.previousVM = paramViewModelBase;
    this.vm = paramChangeFriendshipDialogViewModel;
  }
  
  private void dismissSelf()
  {
    ((SGProjectSpecificDialogManager)DialogManager.getInstance().getManager()).dismissChangeFriendshipDialog();
  }
  
  private void setDialogLoadingView()
  {
    XLEUtil.updateVisibilityIfNotNull(this.overlayLoadingIndicator, 0);
    if (this.confirmButton != null) {
      this.confirmButton.setEnabled(false);
    }
    if (this.cancelButton != null) {
      this.cancelButton.setEnabled(false);
    }
  }
  
  private void setDialogValidContentView()
  {
    XLEUtil.updateVisibilityIfNotNull(this.overlayLoadingIndicator, 8);
    if (this.confirmButton != null) {
      this.confirmButton.setEnabled(true);
    }
    if (this.cancelButton != null) {
      this.cancelButton.setEnabled(true);
    }
  }
  
  public void closeDialog()
  {
    dismissSelf();
    this.previousVM.load(true);
  }
  
  protected String getActivityName()
  {
    return "ChangeRelationship Info";
  }
  
  public void onBackPressed()
  {
    dismissSelf();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setLayout(-1, -1);
    getWindow().setFlags(1024, 1024);
    setContentView(R.layout.change_friendship_dialog);
    this.profilePic = ((XLEUniversalImageView)findViewById(R.id.change_friendship_profile_pic));
    this.gamertag = ((CustomTypefaceTextView)findViewById(R.id.gamertag_text));
    this.realName = ((CustomTypefaceTextView)findViewById(R.id.realname_text));
    this.profileAccountTier = ((TextView)findViewById(R.id.peoplehub_info_gamerscore_icon));
    this.profileGamerScore = ((CustomTypefaceTextView)findViewById(R.id.peoplehub_info_gamerscore));
    this.addFriend = ((RadioButton)findViewById(R.id.add_as_friend));
    this.addFavorite = ((RadioButton)findViewById(R.id.add_as_favorite));
    this.shareRealNameCheckbox = ((XLECheckBox)findViewById(R.id.share_real_name_checkbox));
    this.confirmButton = ((XLEButton)findViewById(R.id.submit_button));
    this.cancelButton = ((XLEButton)findViewById(R.id.cancel_button));
    this.changeFriendshipSwitchPanel = ((SwitchPanel)findViewById(R.id.change_friendship_switch_panel));
    this.removeFriendLayout = ((XLEClickableLayout)findViewById(R.id.remove_friend_btn_layout));
    this.favoriteIconView = ((CustomTypefaceTextView)findViewById(R.id.people_favorites_icon));
    this.overlayLoadingIndicator = ((FastProgressBar)findViewById(R.id.overlay_loading_indicator));
    paramBundle = new FrameLayout(getContext());
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 5;
    XLEButton localXLEButton = new XLEButton(getContext());
    localXLEButton.setPadding(60, 0, 0, 0);
    localXLEButton.setBackgroundResource(R.drawable.common_button_background);
    localXLEButton.setText(R.string.ic_Close);
    localXLEButton.setTextColor(-1);
    localXLEButton.setTextSize(2, 14.0F);
    localXLEButton.setTypeFace("fonts/SegXboxSymbol.ttf");
    localXLEButton.setContentDescription(getContext().getResources().getString(R.string.TextInput_Confirm));
    localXLEButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          ChangeFriendshipDialog.this.dismiss();
          NavigationManager.getInstance().PopAllScreens();
          return;
        }
        catch (XLEException paramAnonymousView) {}
      }
    });
    localXLEButton.setOnKeyListener(new View.OnKeyListener()
    {
      public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if ((paramAnonymousInt == 4) && (paramAnonymousKeyEvent.getAction() == 1))
        {
          ChangeFriendshipDialog.this.dismiss();
          return true;
        }
        return false;
      }
    });
    paramBundle.addView(localXLEButton);
    addContentView(paramBundle, localLayoutParams);
  }
  
  public void onStart()
  {
    this.vm.load();
    updateView();
    this.changeFriendshipSwitchPanel.setBackgroundColor(this.vm.getPreferredColor());
    UTCChangeRelationship.trackChangeRelationshipView(getActivityName(), this.vm.getXuid());
  }
  
  public void onStop() {}
  
  public void reportAsyncTaskCompleted()
  {
    if ((!this.vm.isBusy()) && (this.changeFriendshipSwitchPanel.getState() == 1)) {
      closeDialog();
    }
  }
  
  public void reportAsyncTaskFailed(String paramString)
  {
    if (this.changeFriendshipSwitchPanel.getState() == 1)
    {
      this.changeFriendshipSwitchPanel.setState(0);
      Toast.makeText(XboxTcuiSdk.getActivity(), paramString, 0).show();
    }
    updateView();
  }
  
  public void setVm(ChangeFriendshipDialogViewModel paramChangeFriendshipDialogViewModel)
  {
    this.vm = paramChangeFriendshipDialogViewModel;
  }
  
  public void updateShareIdentityCheckboxStatus()
  {
    String str = this.vm.getCallerShareRealNameStatus();
    int i;
    if (str != null)
    {
      boolean bool = str.equalsIgnoreCase("Blocked");
      XLECheckBox localXLECheckBox = this.shareRealNameCheckbox;
      if (!bool) {
        break label248;
      }
      i = 8;
      localXLECheckBox.setVisibility(i);
      if (!bool)
      {
        if (JavaUtil.isNullOrEmpty(this.vm.getRealName())) {
          break label253;
        }
        i = 1;
        label58:
        if (str.compareToIgnoreCase("Everyone") == 0)
        {
          this.shareRealNameCheckbox.setChecked(true);
          this.vm.setInitialRealNameSharingState(true);
          this.shareRealNameCheckbox.setEnabled(false);
          this.shareRealNameCheckbox.setSubText(XboxTcuiSdk.getResources().getString(R.string.ChangeRelationship_Checkbox_Subtext_ShareRealName_Everyone));
        }
        if (str.compareToIgnoreCase("PeopleOnMyList") == 0)
        {
          this.shareRealNameCheckbox.setChecked(true);
          this.vm.setInitialRealNameSharingState(true);
          this.shareRealNameCheckbox.setEnabled(false);
          this.shareRealNameCheckbox.setSubText(XboxTcuiSdk.getResources().getString(R.string.ChangeRelationship_Checkbox_Subtext_ShareRealName_Friends));
        }
        if (str.compareToIgnoreCase("FriendCategoryShareIdentity") == 0)
        {
          if (!this.vm.getIsFollowing()) {
            break label277;
          }
          if (!this.vm.getCallerMarkedTargetAsIdentityShared()) {
            break label258;
          }
          this.shareRealNameCheckbox.setChecked(true);
          this.vm.setInitialRealNameSharingState(true);
        }
      }
    }
    for (;;)
    {
      str = String.format(XboxTcuiSdk.getResources().getString(R.string.ChangeRelationship_Checkbox_Subtext_ShareRealName), new Object[] { this.vm.getGamerTag() });
      this.shareRealNameCheckbox.setSubText(str);
      this.shareRealNameCheckbox.setEnabled(true);
      return;
      label248:
      i = 0;
      break;
      label253:
      i = 0;
      break label58;
      label258:
      this.shareRealNameCheckbox.setChecked(false);
      this.vm.setInitialRealNameSharingState(false);
      continue;
      label277:
      if (i != 0)
      {
        this.shareRealNameCheckbox.setChecked(true);
        this.vm.setInitialRealNameSharingState(true);
        this.vm.setShouldAddUserToShareIdentityList(true);
      }
      else
      {
        this.shareRealNameCheckbox.setChecked(false);
        this.vm.setInitialRealNameSharingState(false);
      }
    }
  }
  
  public void updateView()
  {
    if (this.vm.getViewModelState() == ListState.ValidContentState)
    {
      setDialogValidContentView();
      XLEUtil.updateAndShowTextViewUnlessEmpty(this.gamertag, this.vm.getGamerTag());
      if (this.profilePic != null) {
        this.profilePic.setImageURI2(ImageUtil.getMedium(this.vm.getGamerPicUrl()), R.drawable.gamerpic_missing, R.drawable.gamerpic_missing);
      }
      XLEUtil.updateAndShowTextViewUnlessEmpty(this.realName, this.vm.getRealName());
      localObject = this.favoriteIconView;
      if (this.vm.getIsFavorite())
      {
        i = 0;
        XLEUtil.updateVisibilityIfNotNull((View)localObject, i);
        if (this.vm.getIsFavorite()) {
          this.favoriteIconView.setTextColor(getContext().getResources().getColor(R.color.XboxGreen));
        }
        localObject = this.vm.getGamerScore();
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("0")))
        {
          XLEUtil.updateAndShowTextViewUnlessEmpty(this.profileGamerScore, this.vm.getGamerScore());
          XLEUtil.updateVisibilityIfNotNull(this.profileAccountTier, 0);
        }
        if (this.addFriend != null)
        {
          if (!this.vm.getIsFollowing()) {
            break label399;
          }
          this.addFriend.setChecked(true);
          this.addFriend.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              if (!ChangeFriendshipDialog.this.vm.getIsFollowing()) {
                ChangeFriendshipDialog.this.vm.setShouldAddUserToFriendList(true);
              }
              if (ChangeFriendshipDialog.this.vm.getIsFavorite()) {
                ChangeFriendshipDialog.this.vm.setShouldRemoveUserFromFavoriteList(true);
              }
              ChangeFriendshipDialog.this.vm.setShouldAddUserToFavoriteList(false);
            }
          });
        }
        if (this.addFavorite != null)
        {
          if (this.vm.getIsFavorite()) {
            this.addFavorite.setChecked(true);
          }
          this.addFavorite.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              if (!ChangeFriendshipDialog.this.vm.getIsFavorite()) {
                ChangeFriendshipDialog.this.vm.setShouldAddUserToFavoriteList(true);
              }
              ChangeFriendshipDialog.this.vm.setShouldRemoveUserFromFavoriteList(false);
            }
          });
        }
        if (this.confirmButton != null) {
          this.confirmButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ChangeFriendshipDialog.this.changeFriendshipSwitchPanel.setState(1);
              ChangeFriendshipDialog.this.vm.onChangeRelationshipCompleted();
            }
          });
        }
        if (this.cancelButton != null) {
          this.cancelButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ChangeFriendshipDialog.this.dismissSelf();
              ChangeFriendshipDialog.this.vm.clearChangeFriendshipForm();
            }
          });
        }
        if (this.shareRealNameCheckbox != null)
        {
          this.shareRealNameCheckbox.setChecked(this.vm.getCallerMarkedTargetAsIdentityShared());
          this.shareRealNameCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
          {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
              ChangeFriendshipDialog.this.vm.setIsSharingRealNameEnd(paramAnonymousBoolean);
              if (paramAnonymousBoolean)
              {
                if (!ChangeFriendshipDialog.this.vm.getCallerMarkedTargetAsIdentityShared()) {
                  ChangeFriendshipDialog.this.vm.setShouldAddUserToShareIdentityList(true);
                }
                ChangeFriendshipDialog.this.vm.setShouldRemoveUserFroShareIdentityList(false);
                return;
              }
              if (ChangeFriendshipDialog.this.vm.getCallerMarkedTargetAsIdentityShared()) {
                ChangeFriendshipDialog.this.vm.setShouldRemoveUserFroShareIdentityList(true);
              }
              ChangeFriendshipDialog.this.vm.setShouldAddUserToShareIdentityList(false);
            }
          });
          updateShareIdentityCheckboxStatus();
        }
        if (this.removeFriendLayout != null)
        {
          if (!this.vm.getIsFollowing()) {
            break label418;
          }
          this.removeFriendLayout.setVisibility(0);
          this.removeFriendLayout.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              UTCChangeRelationship.trackChangeRelationshipRemoveFriend();
              ChangeFriendshipDialog.this.changeFriendshipSwitchPanel.setState(1);
              ChangeFriendshipDialog.this.vm.removeFollowingUser();
            }
          });
          this.confirmButton.setText(this.vm.getDialogButtonText());
        }
        updateShareIdentityCheckboxStatus();
      }
    }
    label399:
    label418:
    while (this.vm.getViewModelState() != ListState.LoadingState) {
      for (;;)
      {
        Object localObject;
        return;
        int i = 4;
        continue;
        this.vm.setShouldAddUserToFriendList(true);
        this.addFriend.setChecked(true);
        continue;
        this.removeFriendLayout.setEnabled(false);
        this.removeFriendLayout.setVisibility(8);
      }
    }
    setDialogLoadingView();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\dialog\ChangeFriendshipDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */