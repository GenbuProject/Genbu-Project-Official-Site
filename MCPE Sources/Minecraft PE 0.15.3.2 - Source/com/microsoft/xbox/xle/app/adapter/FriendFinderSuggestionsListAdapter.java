package com.microsoft.xbox.xle.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderSuggestionModel;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.RecommendationType;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.XLERoundedUniversalImageView;
import com.microsoft.xbox.toolkit.ui.XLEUniversalImageView;
import com.microsoft.xbox.xle.app.FriendFinderSettings;
import com.microsoft.xbox.xle.app.FriendFinderSettings.IconImageSize;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xboxtcui.R.color;
import com.microsoft.xboxtcui.R.drawable;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import java.net.URI;

public class FriendFinderSuggestionsListAdapter
  extends ArrayAdapter<FriendFinderSuggestionModel>
{
  private boolean containsHeader;
  private URI facebookImageUri;
  
  public FriendFinderSuggestionsListAdapter(Context paramContext, int paramInt, boolean paramBoolean)
  {
    super(paramContext, paramInt);
    this.containsHeader = paramBoolean;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int k = 17170445;
    int j = 0;
    ViewHolder localViewHolder;
    int i;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(getContext()).inflate(R.layout.friendfinder_suggestions_list_item, paramViewGroup, false);
      localViewHolder = new ViewHolder(null);
      ViewHolder.access$102(localViewHolder, (XLERoundedUniversalImageView)paramView.findViewById(R.id.friendfinder_suggestions_item_image));
      ViewHolder.access$202(localViewHolder, (XLEUniversalImageView)paramView.findViewById(R.id.friendfinder_suggestions_item_icon_image));
      ViewHolder.access$302(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_suggestions_item_gamertag));
      ViewHolder.access$402(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_suggestions_item_realname));
      ViewHolder.access$502(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_suggestions_item_icon_text));
      ViewHolder.access$602(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_suggestions_item_presence));
      ViewHolder.access$702(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_suggestions_item_check));
      paramView.setTag(localViewHolder);
      FriendFinderSuggestionModel localFriendFinderSuggestionModel = (FriendFinderSuggestionModel)getItem(paramInt);
      paramViewGroup = (ListView)paramViewGroup;
      if (!this.containsHeader) {
        break label428;
      }
      i = 1;
      label180:
      boolean bool = paramViewGroup.isItemChecked(i + paramInt);
      if (!bool) {
        break label434;
      }
      paramInt = R.color.white_15_percent;
      label199:
      paramView.setBackgroundResource(paramInt);
      paramViewGroup = localViewHolder.iconTextView;
      paramInt = k;
      if (!bool) {
        paramInt = R.color.white_15_percent;
      }
      paramViewGroup.setBackgroundResource(paramInt);
      if (localFriendFinderSuggestionModel != null)
      {
        localViewHolder.gamerpicImageView.setImageURI2(localFriendFinderSuggestionModel.imageUri, R.drawable.gamerpic_missing, R.drawable.gamerpic_missing);
        XLEUtil.updateTextAndVisibilityIfNotNull(localViewHolder.gamertagTextView, localFriendFinderSuggestionModel.gamerTag, 0);
        XLEUtil.updateTextAndVisibilityIfNotNull(localViewHolder.realNameTextView, localFriendFinderSuggestionModel.realName, 0);
        XLEUtil.updateTextAndVisibilityIfNotNull(localViewHolder.presenceTextView, localFriendFinderSuggestionModel.presence, 0);
        if (localFriendFinderSuggestionModel.recommendationType != IPeopleHubResult.RecommendationType.FacebookFriend) {
          break label440;
        }
        paramInt = 1;
        label306:
        paramViewGroup = localViewHolder.iconImageView;
        if (paramInt == 0) {
          break label445;
        }
        i = 0;
        label319:
        paramViewGroup.setVisibility(i);
        paramViewGroup = localViewHolder.iconTextView;
        if (paramInt == 0) {
          break label451;
        }
        i = 4;
        label338:
        paramViewGroup.setVisibility(i);
        if (paramInt != 0)
        {
          if (this.facebookImageUri == null)
          {
            paramViewGroup = FriendFinderSettings.getIconBySize(IPeopleHubResult.RecommendationType.FacebookFriend.name(), FriendFinderSettings.IconImageSize.MEDIUM);
            if (!JavaUtil.isNullOrEmpty(paramViewGroup)) {
              this.facebookImageUri = URI.create(paramViewGroup);
            }
          }
          localViewHolder.iconImageView.setImageURI2(this.facebookImageUri);
        }
        paramViewGroup = localViewHolder.checkTextView;
        if (!bool) {
          break label457;
        }
      }
    }
    label428:
    label434:
    label440:
    label445:
    label451:
    label457:
    for (paramInt = j;; paramInt = 4)
    {
      paramViewGroup.setVisibility(paramInt);
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      i = 0;
      break label180;
      paramInt = 17170445;
      break label199;
      paramInt = 0;
      break label306;
      i = 4;
      break label319;
      i = 0;
      break label338;
    }
  }
  
  private static class ViewHolder
  {
    private CustomTypefaceTextView checkTextView;
    private XLERoundedUniversalImageView gamerpicImageView;
    private CustomTypefaceTextView gamertagTextView;
    private XLEUniversalImageView iconImageView;
    private CustomTypefaceTextView iconTextView;
    private CustomTypefaceTextView presenceTextView;
    private CustomTypefaceTextView realNameTextView;
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\adapter\FriendFinderSuggestionsListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */