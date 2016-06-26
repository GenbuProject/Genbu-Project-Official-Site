package com.microsoft.onlineid.interop.xbox.compat;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public abstract class BaseActivity
  extends Activity
{
  public void addFragment(int paramInt, BaseFragment paramBaseFragment)
  {
    getFragmentManager().beginTransaction().add(paramInt, paramBaseFragment).commit();
  }
  
  public boolean hasFragment(int paramInt)
  {
    return getFragmentManager().findFragmentById(paramInt) != null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\compat\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */