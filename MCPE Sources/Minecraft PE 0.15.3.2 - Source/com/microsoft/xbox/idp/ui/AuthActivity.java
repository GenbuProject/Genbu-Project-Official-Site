package com.microsoft.xbox.idp.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.microsoft.xbox.idp.R.id;
import com.microsoft.xbox.idp.compat.BaseActivity;
import com.microsoft.xbox.idp.interop.Interop.AuthFlowScreenStatus;

public abstract class AuthActivity
  extends BaseActivity
{
  public static final int RESULT_PROVIDER_ERROR = 2;
  
  public static Interop.AuthFlowScreenStatus fromActivityResult(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Interop.AuthFlowScreenStatus.PROVIDER_ERROR;
    case -1: 
      return Interop.AuthFlowScreenStatus.NO_ERROR;
    }
    return Interop.AuthFlowScreenStatus.ERROR_USER_CANCEL;
  }
  
  public static int toActivityResult(Interop.AuthFlowScreenStatus paramAuthFlowScreenStatus)
  {
    switch (paramAuthFlowScreenStatus)
    {
    default: 
      return 2;
    case ???: 
      return -1;
    }
    return 0;
  }
  
  protected void finishCompat()
  {
    finish();
  }
  
  protected void showBodyFragment(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject = getFragmentManager();
    FragmentTransaction localFragmentTransaction = ((FragmentManager)localObject).beginTransaction();
    if (paramBoolean) {
      if (((FragmentManager)localObject).findFragmentById(R.id.xbid_header_fragment) == null)
      {
        localObject = new HeaderFragment();
        ((Fragment)localObject).setArguments(paramBundle);
        localFragmentTransaction.add(R.id.xbid_header_fragment, (Fragment)localObject);
      }
    }
    for (;;)
    {
      if (paramFragment != null) {
        paramFragment.setArguments(paramBundle);
      }
      localFragmentTransaction.replace(R.id.xbid_body_fragment, paramFragment);
      localFragmentTransaction.commit();
      return;
      localObject = ((FragmentManager)localObject).findFragmentById(R.id.xbid_header_fragment);
      if (localObject != null) {
        localFragmentTransaction.remove((Fragment)localObject);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\ui\AuthActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */