package com.microsoft.xbox.xle.app.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.xle.app.activity.FriendFinder.FriendFinderHomeScreenViewModel;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.ui.IconFontSubTextButton;
import com.microsoft.xbox.xle.ui.ImageTitleSubtitleButton;
import com.microsoft.xbox.xle.viewmodel.AdapterBase;
import com.microsoft.xboxtcui.R.id;
import java.util.ArrayList;
import java.util.Arrays;

public class FriendFinderHomeScreenAdapter
  extends AdapterBase
  implements View.OnClickListener
{
  private XLEButton doneButton;
  private ImageTitleSubtitleButton inviteFacebookButton;
  private CustomTypefaceTextView inviteFriendsTextView;
  private IconFontSubTextButton invitePhoneButton;
  private ImageTitleSubtitleButton linkFacebookButton;
  private IconFontSubTextButton linkPhoneButton;
  private FrameLayout loadingFrameLayout;
  private IconFontSubTextButton searchButton;
  private EditText searchEditText;
  private FrameLayout searchIconButton;
  private final ArrayList<Integer> searchImeActions = new ArrayList(Arrays.asList(new Integer[] { Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(4) }));
  private RelativeLayout searchLayout;
  private FriendFinderHomeScreenViewModel viewModel;
  
  public FriendFinderHomeScreenAdapter(FriendFinderHomeScreenViewModel paramFriendFinderHomeScreenViewModel)
  {
    super(paramFriendFinderHomeScreenViewModel);
    this.viewModel = paramFriendFinderHomeScreenViewModel;
    this.linkFacebookButton = ((ImageTitleSubtitleButton)findViewById(R.id.friendfinder_link_facebook));
    this.linkPhoneButton = ((IconFontSubTextButton)findViewById(R.id.friendfinder_link_phone));
    this.searchButton = ((IconFontSubTextButton)findViewById(R.id.friendfinder_link_search));
    this.searchIconButton = ((FrameLayout)findViewById(R.id.friendfinder_search_icon));
    this.searchLayout = ((RelativeLayout)findViewById(R.id.friendfinder_search_layout));
    this.searchEditText = ((EditText)findViewById(R.id.friendfinder_search_edittext));
    this.inviteFacebookButton = ((ImageTitleSubtitleButton)findViewById(R.id.friendfinder_home_invite_facebook));
    this.invitePhoneButton = ((IconFontSubTextButton)findViewById(R.id.friendfinder_home_invite_phone));
    this.inviteFriendsTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_home_invite_friends_text));
    this.loadingFrameLayout = ((FrameLayout)findViewById(R.id.friendfinder_home_loading));
    this.doneButton = ((XLEButton)findViewById(R.id.friendfinder_home_done));
    XLEAssert.assertNotNull(this.linkFacebookButton);
    XLEAssert.assertNotNull(this.linkPhoneButton);
    XLEAssert.assertNotNull(this.searchButton);
    XLEAssert.assertNotNull(this.searchIconButton);
    XLEAssert.assertNotNull(this.searchLayout);
    XLEAssert.assertNotNull(this.searchEditText);
    XLEAssert.assertNotNull(this.inviteFacebookButton);
    XLEAssert.assertNotNull(this.invitePhoneButton);
    XLEAssert.assertNotNull(this.inviteFriendsTextView);
    XLEAssert.assertNotNull(this.loadingFrameLayout);
    XLEAssert.assertNotNull(this.doneButton);
  }
  
  private void setViewVisibilities()
  {
    int j = 8;
    Object localObject = this.linkFacebookButton;
    if (this.viewModel.facebookLinked())
    {
      i = 8;
      ((ImageTitleSubtitleButton)localObject).setVisibility(i);
      localObject = this.linkPhoneButton;
      if (!this.viewModel.phoneLinked()) {
        break label177;
      }
      i = 8;
      label44:
      ((IconFontSubTextButton)localObject).setVisibility(i);
      localObject = this.inviteFacebookButton;
      if (!this.viewModel.facebookLinked()) {
        break label182;
      }
      i = 0;
      label66:
      ((ImageTitleSubtitleButton)localObject).setVisibility(i);
      localObject = this.invitePhoneButton;
      if (!this.viewModel.phoneLinked()) {
        break label188;
      }
      i = 0;
      label88:
      ((IconFontSubTextButton)localObject).setVisibility(i);
      localObject = this.loadingFrameLayout;
      if (!this.viewModel.isBusy()) {
        break label194;
      }
      i = 0;
      label110:
      ((FrameLayout)localObject).setVisibility(i);
      localObject = this.doneButton;
      if (!this.viewModel.shouldShowDone()) {
        break label200;
      }
    }
    label177:
    label182:
    label188:
    label194:
    label200:
    for (int i = 0;; i = 8)
    {
      ((XLEButton)localObject).setVisibility(i);
      localObject = this.inviteFriendsTextView;
      if (!this.viewModel.facebookLinked())
      {
        i = j;
        if (!this.viewModel.phoneLinked()) {}
      }
      else
      {
        i = 0;
      }
      ((CustomTypefaceTextView)localObject).setVisibility(i);
      return;
      i = 0;
      break;
      i = 0;
      break label44;
      i = 8;
      break label66;
      i = 8;
      break label88;
      i = 8;
      break label110;
    }
  }
  
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    paramView = this.viewModel.getScreen().getName();
    if (i == R.id.friendfinder_link_facebook)
    {
      UTCFriendFinder.trackFacebookSignup(paramView);
      this.viewModel.navigateToLinkFacebook();
    }
    do
    {
      return;
      if (i == R.id.friendfinder_link_phone)
      {
        UTCFriendFinder.trackContactsSignUp(paramView);
        this.viewModel.navigateToLinkPhone();
        return;
      }
      if (i == R.id.friendfinder_link_search)
      {
        UTCFriendFinder.trackGamertagSearch(paramView);
        this.searchLayout.setVisibility(0);
        this.searchButton.setVisibility(8);
        this.searchEditText.requestFocus();
        return;
      }
      if (i == R.id.friendfinder_home_invite_facebook)
      {
        UTCFriendFinder.trackFacebookSuggestions(paramView);
        this.viewModel.navigateToFacebookSuggestions();
        return;
      }
      if (i == R.id.friendfinder_home_invite_phone)
      {
        UTCFriendFinder.trackContactsSuggestions(paramView);
        this.viewModel.navigateToPhoneSuggestions();
        return;
      }
    } while (i != R.id.friendfinder_home_done);
    UTCFriendFinder.trackDone(paramView);
    this.viewModel.finishFriendFinder();
  }
  
  public void onStart()
  {
    super.onStart();
    this.linkFacebookButton.setOnClickListener(this);
    this.linkPhoneButton.setOnClickListener(this);
    this.searchButton.setOnClickListener(this);
    this.inviteFacebookButton.setOnClickListener(this);
    this.invitePhoneButton.setOnClickListener(this);
    this.doneButton.setOnClickListener(this);
    this.linkFacebookButton.setImageUri("");
    this.inviteFacebookButton.setImageUri("");
    this.searchLayout.setVisibility(8);
    final String str = this.viewModel.getScreen().getName();
    this.searchIconButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FriendFinderHomeScreenAdapter.this.viewModel.searchGamertag(FriendFinderHomeScreenAdapter.this.searchEditText.getText().toString());
      }
    });
    this.searchEditText.addTextChangedListener(new TextWatcher()
    {
      private boolean isEnterKey;
      
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (this.isEnterKey) {
          paramAnonymousEditable.delete(paramAnonymousEditable.length() - 1, paramAnonymousEditable.length());
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if ((paramAnonymousCharSequence.length() > 0) && (paramAnonymousCharSequence.subSequence(paramAnonymousCharSequence.length() - 1, paramAnonymousCharSequence.length()).toString().equalsIgnoreCase("\n"))) {}
        for (boolean bool = true;; bool = false)
        {
          this.isEnterKey = bool;
          if (this.isEnterKey)
          {
            UTCFriendFinder.trackGamertagSearchSubmit(str);
            FriendFinderHomeScreenAdapter.this.viewModel.searchGamertag(FriendFinderHomeScreenAdapter.this.searchEditText.getText().toString());
          }
          return;
        }
      }
    });
    this.searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (FriendFinderHomeScreenAdapter.this.searchImeActions.contains(Integer.valueOf(paramAnonymousInt)))
        {
          UTCFriendFinder.trackGamertagSearchSubmit(str);
          FriendFinderHomeScreenAdapter.this.viewModel.searchGamertag(FriendFinderHomeScreenAdapter.this.searchEditText.getText().toString());
          return true;
        }
        return false;
      }
    });
    this.searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        paramAnonymousView = (InputMethodManager)FriendFinderHomeScreenAdapter.this.searchEditText.getContext().getSystemService("input_method");
        if (paramAnonymousBoolean)
        {
          paramAnonymousView.toggleSoftInput(2, 0);
          return;
        }
        paramAnonymousView.toggleSoftInput(1, 0);
      }
    });
  }
  
  protected void updateViewOverride()
  {
    setViewVisibilities();
    String str = this.viewModel.getFacebookIconUri();
    if (!JavaUtil.isNullOrEmpty(str))
    {
      this.linkFacebookButton.setImageUri(str);
      this.inviteFacebookButton.setImageUri(str);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\adapter\FriendFinderHomeScreenAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */