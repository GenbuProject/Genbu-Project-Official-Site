package com.microsoft.onlineid.interop.xbox.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.microsoft.onlineid.interop.R.id;
import com.microsoft.onlineid.interop.R.layout;
import com.microsoft.onlineid.interop.R.string;
import com.microsoft.onlineid.interop.xbox.compat.BaseFragment;

public class BanErrorFragment
  extends BaseFragment
{
  public static final String ARG_GAMER_TAG = "ARG_GAMER_TAG";
  private static final String TAG = BanErrorFragment.class.getSimpleName();
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(R.layout.xbid_fragment_error_ban, paramViewGroup, false);
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    paramBundle = getArguments();
    if (paramBundle != null) {
      if (paramBundle.containsKey("ARG_GAMER_TAG")) {
        paramBundle = paramBundle.getString("ARG_GAMER_TAG");
      }
    }
    for (;;)
    {
      ((TextView)paramView.findViewById(R.id.xbid_greeting_text)).setText(getString(R.string.xbid_ban_error_header, new Object[] { paramBundle }));
      return;
      Log.e(TAG, "No ARG_GAMER_TAG provided");
      paramBundle = "";
      continue;
      Log.e(TAG, "No arguments provided");
      paramBundle = "";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\ui\BanErrorFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */