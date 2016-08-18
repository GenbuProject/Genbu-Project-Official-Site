package com.microsoft.xbox.idp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.microsoft.xbox.idp.R.id;
import com.microsoft.xbox.idp.R.layout;
import com.microsoft.xbox.idp.compat.BaseFragment;

public class ErrorButtonsFragment
  extends BaseFragment
  implements View.OnClickListener
{
  public static final String ARG_LEFT_ERROR_BUTTON_STRING_ID = "ARG_LEFT_ERROR_BUTTON_STRING_ID";
  private static final Callbacks NO_OP_CALLBACKS = new Callbacks()
  {
    public void onClickedLeftButton() {}
    
    public void onClickedRightButton() {}
  };
  private Callbacks callbacks = NO_OP_CALLBACKS;
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.callbacks = ((Callbacks)paramActivity);
  }
  
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i == R.id.xbid_error_left_button) {
      this.callbacks.onClickedLeftButton();
    }
    while (i != R.id.xbid_error_right_button) {
      return;
    }
    this.callbacks.onClickedRightButton();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(R.layout.xbid_fragment_error_buttons, paramViewGroup, false);
  }
  
  public void onDetach()
  {
    super.onDetach();
    this.callbacks = NO_OP_CALLBACKS;
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    paramBundle = (Button)paramView.findViewById(R.id.xbid_error_left_button);
    paramBundle.setOnClickListener(this);
    paramView.findViewById(R.id.xbid_error_right_button).setOnClickListener(this);
    paramView = getArguments();
    if ((paramView != null) && (paramView.containsKey("ARG_LEFT_ERROR_BUTTON_STRING_ID"))) {
      paramBundle.setText(paramView.getInt("ARG_LEFT_ERROR_BUTTON_STRING_ID"));
    }
  }
  
  public static abstract interface Callbacks
  {
    public abstract void onClickedLeftButton();
    
    public abstract void onClickedRightButton();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\ui\ErrorButtonsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */