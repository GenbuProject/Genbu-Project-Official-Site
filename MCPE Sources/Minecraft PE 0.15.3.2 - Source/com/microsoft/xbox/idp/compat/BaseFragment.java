package com.microsoft.xbox.idp.compat;

import android.app.Activity;
import android.app.Fragment;

public abstract class BaseFragment
  extends Fragment
{
  public static final String ARG_ALT_BUTTON_TEXT = "ARG_ALT_BUTTON_TEXT";
  public static final String ARG_USER_PTR = "ARG_USER_PTR";
  
  protected CharSequence getActivityTitle()
  {
    Activity localActivity = getActivity();
    if (localActivity == null) {
      return null;
    }
    return localActivity.getTitle();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\compat\BaseFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */