package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;

public class FragmentWrapper
{
  private android.app.Fragment nativeFragment;
  private android.support.v4.app.Fragment supportFragment;
  
  public FragmentWrapper(android.app.Fragment paramFragment)
  {
    Validate.notNull(paramFragment, "fragment");
    this.nativeFragment = paramFragment;
  }
  
  public FragmentWrapper(android.support.v4.app.Fragment paramFragment)
  {
    Validate.notNull(paramFragment, "fragment");
    this.supportFragment = paramFragment;
  }
  
  public final Activity getActivity()
  {
    if (this.supportFragment != null) {
      return this.supportFragment.getActivity();
    }
    return this.nativeFragment.getActivity();
  }
  
  public android.app.Fragment getNativeFragment()
  {
    return this.nativeFragment;
  }
  
  public android.support.v4.app.Fragment getSupportFragment()
  {
    return this.supportFragment;
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    if (this.supportFragment != null)
    {
      this.supportFragment.startActivityForResult(paramIntent, paramInt);
      return;
    }
    this.nativeFragment.startActivityForResult(paramIntent, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\internal\FragmentWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */