package com.microsoft.onlineid.interop.xbox.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.microsoft.onlineid.interop.R.id;
import com.microsoft.onlineid.interop.R.layout;
import com.microsoft.onlineid.interop.R.string;
import com.microsoft.onlineid.interop.xbox.compat.BaseFragment;
import com.microsoft.onlineid.interop.xbox.model.Const;

public class CatchAllErrorFragment
  extends BaseFragment
{
  private static final String TAG = CatchAllErrorFragment.class.getSimpleName();
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(R.layout.xbid_fragment_error_catch_all, paramViewGroup, false);
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    UiUtil.ensureClickableSpanOnUnderlineSpan((TextView)paramView.findViewById(R.id.xbid_error_message), R.string.xbid_catchall_error_android, new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.d(CatchAllErrorFragment.TAG, "onClick");
        try
        {
          CatchAllErrorFragment.this.startActivity(new Intent("android.intent.action.VIEW", Const.URL_XBOX_COM));
          return;
        }
        catch (ActivityNotFoundException paramAnonymousView)
        {
          Log.e(CatchAllErrorFragment.TAG, paramAnonymousView.getMessage());
        }
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\ui\CatchAllErrorFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */