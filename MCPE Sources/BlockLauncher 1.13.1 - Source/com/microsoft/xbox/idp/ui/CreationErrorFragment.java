package com.microsoft.xbox.idp.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.microsoft.xbox.idp.R.id;
import com.microsoft.xbox.idp.R.layout;
import com.microsoft.xbox.idp.R.string;
import com.microsoft.xbox.idp.compat.BaseFragment;
import com.microsoft.xbox.idp.model.Const;

public class CreationErrorFragment
  extends BaseFragment
{
  public static final String TAG = CreationErrorFragment.class.getSimpleName();
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(R.layout.xbid_fragment_error_creation, paramViewGroup, false);
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    UiUtil.ensureClickableSpanOnUnderlineSpan((TextView)paramView.findViewById(R.id.xbid_error_message), R.string.xbid_creation_error_android, new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.d(CreationErrorFragment.TAG, "onClick");
        try
        {
          CreationErrorFragment.this.startActivity(new Intent("android.intent.action.VIEW", Const.URL_XBOX_COM));
          return;
        }
        catch (ActivityNotFoundException paramAnonymousView)
        {
          Log.e(CreationErrorFragment.TAG, paramAnonymousView.getMessage());
        }
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\ui\CreationErrorFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */