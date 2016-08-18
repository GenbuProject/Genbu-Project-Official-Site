package com.microsoft.xbox.xle.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.microsoft.xbox.service.network.managers.friendfinder.PhoneContactInfo.Contact;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xboxtcui.R.color;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;

public class FriendFinderPhoneInviteListAdapter
  extends ArrayAdapter<PhoneContactInfo.Contact>
{
  public FriendFinderPhoneInviteListAdapter(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    XLEAssert.fail("This isn't supported yet.");
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = 0;
    ViewHolder localViewHolder;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(getContext()).inflate(R.layout.friendfinder_phone_invite_list_item, paramViewGroup, false);
      localViewHolder = new ViewHolder(null);
      ViewHolder.access$102(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_phone_invite_name));
      ViewHolder.access$202(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_phone_invite_name_onxbox));
      ViewHolder.access$302(localViewHolder, (CustomTypefaceTextView)paramView.findViewById(R.id.friendfinder_phone_invite_checkbox));
      paramView.setTag(localViewHolder);
      PhoneContactInfo.Contact localContact = (PhoneContactInfo.Contact)getItem(paramInt);
      boolean bool = ((ListView)paramViewGroup).isItemChecked(paramInt);
      if (!bool) {
        break label194;
      }
      paramInt = R.color.XboxOneGreen;
      label116:
      paramView.setBackgroundResource(paramInt);
      if (localContact != null)
      {
        XLEUtil.updateTextAndVisibilityIfNotNull(localViewHolder.contactNameTextView, localContact.displayName, 0);
        paramViewGroup = localViewHolder.onXboxTextView;
        if (!localContact.isOnXbox) {
          break label200;
        }
        paramInt = 0;
        label156:
        paramViewGroup.setVisibility(paramInt);
        paramViewGroup = localViewHolder.checkTextView;
        if (!bool) {
          break label206;
        }
      }
    }
    label194:
    label200:
    label206:
    for (paramInt = i;; paramInt = 4)
    {
      paramViewGroup.setVisibility(paramInt);
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      paramInt = 17170445;
      break label116;
      paramInt = 8;
      break label156;
    }
  }
  
  private static class ViewHolder
  {
    private CustomTypefaceTextView checkTextView;
    private CustomTypefaceTextView contactNameTextView;
    private CustomTypefaceTextView onXboxTextView;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\adapter\FriendFinderPhoneInviteListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */