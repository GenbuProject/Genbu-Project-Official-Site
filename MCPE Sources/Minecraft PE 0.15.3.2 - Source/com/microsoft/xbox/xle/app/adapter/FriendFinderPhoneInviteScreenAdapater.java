package com.microsoft.xbox.xle.app.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.xle.app.activity.FriendFinder.FriendFinderPhoneInviteScreenViewModel;
import com.microsoft.xbox.xle.viewmodel.AdapterBase;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class FriendFinderPhoneInviteScreenAdapater
  extends AdapterBase
{
  private FriendFinderPhoneInviteListAdapter contactsListAdapter;
  private ListView contactsListView;
  private FrameLayout loadingLayout;
  private CustomTypefaceTextView subtitleTextView;
  private CustomTypefaceTextView titleTextView;
  private FriendFinderPhoneInviteScreenViewModel viewModel;
  
  public FriendFinderPhoneInviteScreenAdapater(FriendFinderPhoneInviteScreenViewModel paramFriendFinderPhoneInviteScreenViewModel)
  {
    super(paramFriendFinderPhoneInviteScreenViewModel);
    XLEAssert.fail("This isn't supported yet.");
    this.viewModel = paramFriendFinderPhoneInviteScreenViewModel;
    this.titleTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_suggestions_title));
    this.subtitleTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_suggestions_subtitle));
    this.contactsListView = ((ListView)findViewById(R.id.friendfinder_suggestions_list));
    this.loadingLayout = ((FrameLayout)findViewById(R.id.friendfinder_suggestions_loading));
    XLEAssert.assertNotNull(this.titleTextView);
    XLEAssert.assertNotNull(this.subtitleTextView);
    XLEAssert.assertNotNull(this.contactsListView);
    XLEAssert.assertNotNull(this.loadingLayout);
    this.titleTextView.setText(R.string.FriendFinder_PhoneInviteFriends_Dialog_Title);
    paramFriendFinderPhoneInviteScreenViewModel = XboxTcuiSdk.getResources().getString(R.string.FriendFinder_PhoneInviteFriends_Dialog_Text);
    this.subtitleTextView.setText(paramFriendFinderPhoneInviteScreenViewModel.replace("-", "\n\n"));
    this.contactsListView.setChoiceMode(2);
  }
  
  public void onStart()
  {
    super.onStart();
    this.contactsListAdapter = new FriendFinderPhoneInviteListAdapter(XboxTcuiSdk.getActivity(), R.layout.friendfinder_phone_invite_list_item);
    this.contactsListView.setAdapter(this.contactsListAdapter);
    this.contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView.getAdapter().getView(paramAnonymousInt, paramAnonymousView, paramAnonymousAdapterView);
      }
    });
  }
  
  protected void updateViewOverride()
  {
    FrameLayout localFrameLayout = this.loadingLayout;
    if (this.viewModel.isBusy()) {}
    for (int i = 0;; i = 8)
    {
      localFrameLayout.setVisibility(i);
      this.contactsListAdapter.clear();
      this.contactsListAdapter.addAll(this.viewModel.getContacts());
      this.contactsListAdapter.notifyDataSetChanged();
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\adapter\FriendFinderPhoneInviteScreenAdapater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */