package com.microsoft.xbox.xle.app.adapter;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.xle.app.activity.FriendFinder.FriendFinderAddPhoneScreenViewModel;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.viewmodel.AdapterBase;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class FriendFinderAddPhoneScreenAdapter
  extends AdapterBase
{
  private FrameLayout loadingLayout;
  private XLEButton nextButton;
  private EditText phoneNumberEditText;
  private CustomTypefaceTextView subtitleTextView;
  private FriendFinderAddPhoneScreenViewModel viewModel;
  
  public FriendFinderAddPhoneScreenAdapter(FriendFinderAddPhoneScreenViewModel paramFriendFinderAddPhoneScreenViewModel)
  {
    super(paramFriendFinderAddPhoneScreenViewModel);
    this.viewModel = paramFriendFinderAddPhoneScreenViewModel;
    this.subtitleTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_add_phone_subtitle));
    this.phoneNumberEditText = ((EditText)findViewById(R.id.friendfinder_add_phone_edit_text));
    this.nextButton = ((XLEButton)findViewById(R.id.friendfinder_info_next));
    this.loadingLayout = ((FrameLayout)findViewById(R.id.friendfinder_add_phone_loading));
    XLEAssert.assertNotNull(this.subtitleTextView);
    XLEAssert.assertNotNull(this.phoneNumberEditText);
    XLEAssert.assertNotNull(this.nextButton);
    XLEAssert.assertNotNull(this.loadingLayout);
  }
  
  public void onStart()
  {
    super.onStart();
    String str = XboxTcuiSdk.getResources().getString(R.string.FriendFinder_AddPhoneNumber_Dialog_Text_LineOne) + "\n\n" + XboxTcuiSdk.getResources().getString(R.string.FriendFinder_AddPhoneNumber_Dialog_Text_LineTwo) + "\n\n" + XboxTcuiSdk.getResources().getString(R.string.FriendFinder_AddPhoneNumber_Dialog_Text_LineThree);
    this.subtitleTextView.setText(str);
    this.nextButton.setEnabled(false);
    this.nextButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UTCFriendFinder.trackPhoneContactsNext(FriendFinderAddPhoneScreenAdapter.this.viewModel.getScreen().getName());
        FriendFinderAddPhoneScreenAdapter.this.viewModel.addPhoneNumber(FriendFinderAddPhoneScreenAdapter.this.phoneNumberEditText.getText().toString());
      }
    });
    this.phoneNumberEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (paramAnonymousEditable.length() > 0) {}
        for (boolean bool = true;; bool = false)
        {
          FriendFinderAddPhoneScreenAdapter.this.nextButton.setEnabled(bool);
          return;
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
  }
  
  protected void updateViewOverride()
  {
    Object localObject = this.loadingLayout;
    if (this.viewModel.isBusy()) {}
    for (int i = 0;; i = 8)
    {
      ((FrameLayout)localObject).setVisibility(i);
      if (!JavaUtil.isNullOrEmpty(this.viewModel.getSimPhoneNumber()))
      {
        String str = this.viewModel.getCurrentCountryCode() + this.viewModel.getSimPhoneNumber();
        EditText localEditText = this.phoneNumberEditText;
        localObject = str;
        if (JavaUtil.isNullOrEmpty(str)) {
          localObject = "+";
        }
        localEditText.setText((CharSequence)localObject);
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\adapter\FriendFinderAddPhoneScreenAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */