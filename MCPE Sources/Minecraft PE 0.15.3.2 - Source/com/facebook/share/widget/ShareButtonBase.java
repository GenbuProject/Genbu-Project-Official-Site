package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.model.ShareContent;

public abstract class ShareButtonBase
  extends FacebookButtonBase
{
  private boolean enabledExplicitlySet = false;
  private int requestCode = 0;
  private ShareContent shareContent;
  
  protected ShareButtonBase(Context paramContext, AttributeSet paramAttributeSet, int paramInt, String paramString1, String paramString2)
  {
    super(paramContext, paramAttributeSet, paramInt, 0, paramString1, paramString2);
    if (isInEditMode()) {}
    for (paramInt = 0;; paramInt = getDefaultRequestCode())
    {
      this.requestCode = paramInt;
      internalSetEnabled(false);
      return;
    }
  }
  
  private void internalSetEnabled(boolean paramBoolean)
  {
    setEnabled(paramBoolean);
    this.enabledExplicitlySet = false;
  }
  
  protected boolean canShare()
  {
    return getDialog().canShow(getShareContent());
  }
  
  protected void configureButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super.configureButton(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setInternalOnClickListener(getShareOnClickListener());
  }
  
  protected abstract FacebookDialogBase<ShareContent, Sharer.Result> getDialog();
  
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
        ShareButtonBase.this.callExternalOnClickListener(paramAnonymousView);
        ShareButtonBase.this.getDialog().show(ShareButtonBase.this.getShareContent());
      }
    };
  }
  
  public void registerCallback(CallbackManager paramCallbackManager, FacebookCallback<Sharer.Result> paramFacebookCallback)
  {
    ShareInternalUtility.registerSharerCallback(getRequestCode(), paramCallbackManager, paramFacebookCallback);
  }
  
  public void registerCallback(CallbackManager paramCallbackManager, FacebookCallback<Sharer.Result> paramFacebookCallback, int paramInt)
  {
    setRequestCode(paramInt);
    registerCallback(paramCallbackManager, paramFacebookCallback);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.enabledExplicitlySet = true;
  }
  
  protected void setRequestCode(int paramInt)
  {
    if (FacebookSdk.isFacebookRequestCode(paramInt)) {
      throw new IllegalArgumentException("Request code " + paramInt + " cannot be within the range reserved by the Facebook SDK.");
    }
    this.requestCode = paramInt;
  }
  
  public void setShareContent(ShareContent paramShareContent)
  {
    this.shareContent = paramShareContent;
    if (!this.enabledExplicitlySet) {
      internalSetEnabled(canShare());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\widget\ShareButtonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */