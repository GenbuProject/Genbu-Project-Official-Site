package com.microsoft.xbox.idp.compat;

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


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\compat\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */