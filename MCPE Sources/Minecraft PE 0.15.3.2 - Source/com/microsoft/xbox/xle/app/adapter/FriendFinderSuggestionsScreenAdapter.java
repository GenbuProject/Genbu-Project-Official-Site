package com.microsoft.xbox.xle.app.adapter;

import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.toolkit.ui.XLEListView;
import com.microsoft.xbox.xle.app.activity.FriendFinder.FriendFinderSuggestionsScreenViewModel;
import com.microsoft.xbox.xle.viewmodel.AdapterBase;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;

public class FriendFinderSuggestionsScreenAdapter
  extends AdapterBase
{
  private XLEButton advanceButton;
  private ViewGroup emptyListHeaderContainer;
  private FrameLayout listHeaderContainer;
  private ViewGroup listHeaderGroup;
  private FrameLayout loadingOverlay;
  private CustomTypefaceTextView subtitleTextView;
  private FriendFinderSuggestionsListAdapter suggestionsListAdapter;
  private XLEListView suggestionsListView;
  private CustomTypefaceTextView titleTextView;
  private FriendFinderSuggestionsScreenViewModel viewModel;
  
  public FriendFinderSuggestionsScreenAdapter(FriendFinderSuggestionsScreenViewModel paramFriendFinderSuggestionsScreenViewModel)
  {
    super(paramFriendFinderSuggestionsScreenViewModel);
    this.viewModel = paramFriendFinderSuggestionsScreenViewModel;
    this.titleTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_suggestions_title));
    this.subtitleTextView = ((CustomTypefaceTextView)findViewById(R.id.friendfinder_suggestions_subtitle));
    this.emptyListHeaderContainer = ((ViewGroup)findViewById(R.id.friendfinder_suggestions_empty_header_container));
    this.listHeaderGroup = ((ViewGroup)findViewById(R.id.friendfinder_suggestions_header));
    this.suggestionsListView = ((XLEListView)findViewById(R.id.friendfinder_suggestions_list));
    this.advanceButton = ((XLEButton)findViewById(R.id.friendfinder_suggestions_button));
    this.loadingOverlay = ((FrameLayout)findViewById(R.id.friendfinder_suggestions_loading));
    XLEAssert.assertNotNull(this.titleTextView);
    XLEAssert.assertNotNull(this.subtitleTextView);
    XLEAssert.assertNotNull(this.suggestionsListView);
    XLEAssert.assertNotNull(this.emptyListHeaderContainer);
    XLEAssert.assertNotNull(this.listHeaderGroup);
    XLEAssert.assertNotNull(this.advanceButton);
    XLEAssert.assertNotNull(this.loadingOverlay);
    this.listHeaderContainer = new FrameLayout(XboxTcuiSdk.getActivity());
    this.suggestionsListView.addHeaderView(this.listHeaderContainer, null, false);
    this.suggestionsListView.setChoiceMode(2);
  }
  
  public void onStart()
  {
    super.onStart();
    this.suggestionsListAdapter = new FriendFinderSuggestionsListAdapter(XboxTcuiSdk.getActivity(), R.layout.friendfinder_suggestions_list_item, true);
    this.suggestionsListView.setAdapter(this.suggestionsListAdapter);
    this.suggestionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView.getAdapter().getView(paramAnonymousInt, paramAnonymousView, paramAnonymousAdapterView);
        paramAnonymousInt = FriendFinderSuggestionsScreenAdapter.this.suggestionsListView.getCheckedItemCount();
        if (paramAnonymousInt == 0)
        {
          FriendFinderSuggestionsScreenAdapter.this.advanceButton.setText(R.string.FriendFinder_Phone_Next_ButtonText);
          return;
        }
        if (paramAnonymousInt == 1)
        {
          FriendFinderSuggestionsScreenAdapter.this.advanceButton.setText(R.string.Profile_Profile_AddFriend);
          return;
        }
        FriendFinderSuggestionsScreenAdapter.this.advanceButton.setText(R.string.FriendFinder_AddFriends);
      }
    });
    this.advanceButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = FriendFinderSuggestionsScreenAdapter.this.suggestionsListView.getCheckedItemPositions();
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < FriendFinderSuggestionsScreenAdapter.this.suggestionsListAdapter.getCount() + 1)
        {
          if (paramAnonymousView.get(i)) {
            localArrayList.add(Integer.valueOf(i - 1));
          }
          i += 1;
        }
        if (localArrayList.size() > 0)
        {
          FriendFinderSuggestionsScreenAdapter.this.viewModel.addSuggestions(localArrayList);
          return;
        }
        FriendFinderSuggestionsScreenAdapter.this.viewModel.navigateToSkip();
      }
    });
  }
  
  protected void updateViewOverride()
  {
    FrameLayout localFrameLayout = this.loadingOverlay;
    if (this.viewModel.isBusy()) {}
    for (int i = 0;; i = 8)
    {
      localFrameLayout.setVisibility(i);
      this.titleTextView.setText(this.viewModel.getTitle());
      this.subtitleTextView.setText(this.viewModel.getSubtitle());
      this.suggestionsListAdapter.clear();
      this.suggestionsListAdapter.addAll(this.viewModel.getSuggestions());
      this.suggestionsListAdapter.notifyDataSetChanged();
      this.emptyListHeaderContainer.removeAllViews();
      this.listHeaderContainer.removeAllViews();
      if (this.suggestionsListAdapter.getCount() <= 0) {
        break;
      }
      this.listHeaderContainer.addView(this.listHeaderGroup);
      return;
    }
    this.emptyListHeaderContainer.addView(this.listHeaderGroup);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\adapter\FriendFinderSuggestionsScreenAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */