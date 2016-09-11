package com.microsoft.xbox.xle.app.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.xle.app.activity.FriendFinder.FriendFinderVerifyCodeScreenViewModel;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.ui.IconFontToggleButton;
import com.microsoft.xbox.xle.viewmodel.AdapterBase;
import com.microsoft.xboxtcui.R.id;

public class FriendFinderVerifyCodeScreenAdapter
  extends AdapterBase
{
  private IconFontToggleButton callMeButton;
  private EditText codeEditText;
  private FrameLayout loadingLayout;
  private IconFontToggleButton resendCodeButton;
  private XLEButton verifyButton;
  private FriendFinderVerifyCodeScreenViewModel viewModel;
  
  public FriendFinderVerifyCodeScreenAdapter(FriendFinderVerifyCodeScreenViewModel paramFriendFinderVerifyCodeScreenViewModel)
  {
    super(paramFriendFinderVerifyCodeScreenViewModel);
    this.viewModel = paramFriendFinderVerifyCodeScreenViewModel;
    this.codeEditText = ((EditText)findViewById(R.id.friendfinder_verify_code_edit_text));
    this.resendCodeButton = ((IconFontToggleButton)findViewById(R.id.friendfinder_verify_resend));
    this.callMeButton = ((IconFontToggleButton)findViewById(R.id.friendfinder_verify_call_me));
    this.verifyButton = ((XLEButton)findViewById(R.id.friendfinder_verify_verify_code));
    this.loadingLayout = ((FrameLayout)findViewById(R.id.friendfinder_verify_loading));
    XLEAssert.assertNotNull(this.codeEditText);
    XLEAssert.assertNotNull(this.resendCodeButton);
    XLEAssert.assertNotNull(this.callMeButton);
    XLEAssert.assertNotNull(this.verifyButton);
    XLEAssert.assertNotNull(this.loadingLayout);
    this.resendCodeButton.setChecked(false);
    this.resendCodeButton.setEnabled(true);
    this.callMeButton.setChecked(false);
    this.callMeButton.setEnabled(true);
    this.verifyButton.setEnabled(false);
  }
  
  public void onStart()
  {
    super.onStart();
    this.codeEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        XLEButton localXLEButton = FriendFinderVerifyCodeScreenAdapter.this.verifyButton;
        if (paramAnonymousEditable.length() > 0) {}
        for (boolean bool = true;; bool = false)
        {
          localXLEButton.setEnabled(bool);
          return;
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.resendCodeButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UTCFriendFinder.trackPhoneContactsResendCode(FriendFinderVerifyCodeScreenAdapter.this.viewModel.getScreen().getName());
        FriendFinderVerifyCodeScreenAdapter.this.viewModel.resendCode();
      }
    });
    this.callMeButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UTCFriendFinder.trackPhoneContactsCallMe(FriendFinderVerifyCodeScreenAdapter.this.viewModel.getScreen().getName());
        FriendFinderVerifyCodeScreenAdapter.this.viewModel.callMe();
      }
    });
    this.verifyButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UTCFriendFinder.trackPhoneContactsNext(FriendFinderVerifyCodeScreenAdapter.this.viewModel.getScreen().getName());
        FriendFinderVerifyCodeScreenAdapter.this.viewModel.verifyCode(FriendFinderVerifyCodeScreenAdapter.this.codeEditText.getText().toString());
      }
    });
  }
  
  protected void updateViewOverride()
  {
    boolean bool2 = false;
    Object localObject = this.loadingLayout;
    int i;
    if (this.viewModel.isBusy())
    {
      i = 0;
      ((FrameLayout)localObject).setVisibility(i);
      localObject = this.resendCodeButton;
      if (this.viewModel.isSendingCode()) {
        break label83;
      }
    }
    label83:
    for (boolean bool1 = true;; bool1 = false)
    {
      ((IconFontToggleButton)localObject).setEnabled(bool1);
      localObject = this.callMeButton;
      bool1 = bool2;
      if (!this.viewModel.isSendingCode()) {
        bool1 = true;
      }
      ((IconFontToggleButton)localObject).setEnabled(bool1);
      return;
      i = 8;
      break;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\adapter\FriendFinderVerifyCodeScreenAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */