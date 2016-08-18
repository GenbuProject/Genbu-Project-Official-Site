package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.R.style;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.share.DeviceShareDialog;
import com.facebook.share.DeviceShareDialog.Result;
import com.facebook.share.model.ShareContent;

public final class DeviceShareButton
  extends FacebookButtonBase
{
  private DeviceShareDialog dialog = null;
  private boolean enabledExplicitlySet = false;
  private int requestCode = 0;
  private ShareContent shareContent;
  
  public DeviceShareButton(Context paramContext)
  {
    this(paramContext, null, 0);
  }
  
  public DeviceShareButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  private DeviceShareButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt, 0, "fb_device_share_button_create", "fb_device_share_button_did_tap");
    if (isInEditMode()) {}
    for (paramInt = 0;; paramInt = getDefaultRequestCode())
    {
      this.requestCode = paramInt;
      internalSetEnabled(false);
      return;
    }
  }
  
  private boolean canShare()
  {
    return new DeviceShareDialog(getActivity()).canShow(getShareContent());
  }
  
  private DeviceShareDialog getDialog()
  {
    if (this.dialog != null) {
      return this.dialog;
    }
    if (getFragment() != null) {
      this.dialog = new DeviceShareDialog(getFragment());
    }
    for (;;)
    {
      return this.dialog;
      if (getNativeFragment() != null) {
        this.dialog = new DeviceShareDialog(getNativeFragment());
      } else {
        this.dialog = new DeviceShareDialog(getActivity());
      }
    }
  }
  
  private void internalSetEnabled(boolean paramBoolean)
  {
    setEnabled(paramBoolean);
    this.enabledExplicitlySet = false;
  }
  
  private void setRequestCode(int paramInt)
  {
    if (FacebookSdk.isFacebookRequestCode(paramInt)) {
      throw new IllegalArgumentException("Request code " + paramInt + " cannot be within the range reserved by the Facebook SDK.");
    }
    this.requestCode = paramInt;
  }
  
  protected void configureButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super.configureButton(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setInternalOnClickListener(getShareOnClickListener());
  }
  
  protected int getDefaultRequestCode()
  {
    return CallbackManagerImpl.RequestCodeOffset.Share.toRequestCode();
  }
  
  protected int getDefaultStyleResource()
  {
    return R.style.com_facebook_button_share;
  }
  
  public int getRequestCode()
  {
    return this.requestCode;
  }
  
  public ShareContent getShareContent()
  {
    return this.shareContent;
  }
  
  protected View.OnClickListener getShareOnClickListener()
  {
    new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DeviceShareButton.this.callExternalOnClickListener(paramAnonymousView);
        DeviceShareButton.this.getDialog().show(DeviceShareButton.this.getShareContent());
      }
    };
  }
  
  public void registerCallback(CallbackManager paramCallbackManager, FacebookCallback<DeviceShareDialog.Result> paramFacebookCallback)
  {
    getDialog().registerCallback(paramCallbackManager, paramFacebookCallback);
  }
  
  public void registerCallback(CallbackManager paramCallbackManager, FacebookCallback<DeviceShareDialog.Result> paramFacebookCallback, int paramInt)
  {
    setRequestCode(paramInt);
    getDialog().registerCallback(paramCallbackManager, paramFacebookCallback, paramInt);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.enabledExplicitlySet = true;
  }
  
  public void setShareContent(ShareContent paramShareContent)
  {
    this.shareContent = paramShareContent;
    if (!this.enabledExplicitlySet) {
      internalSetEnabled(canShare());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\widget\DeviceShareButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */