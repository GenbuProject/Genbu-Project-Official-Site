package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import com.microsoft.xbox.toolkit.XLERValueHelper;
import java.net.URI;

public class XLEImageViewFast
  extends XLEImageView
{
  private TextureBindingOption option;
  protected int pendingBitmapResourceId = -1;
  private String pendingFilePath = null;
  protected URI pendingUri = null;
  private boolean useFileCache = true;
  
  public XLEImageViewFast(Context paramContext)
  {
    super(paramContext);
    setSoundEffectsEnabled(false);
  }
  
  public XLEImageViewFast(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (isInEditMode()) {
      return;
    }
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("XLEImageViewFast"));
    setImageResource(paramContext.getResourceId(XLERValueHelper.getStyleableRValue("XLEImageViewFast_src"), -1));
    paramContext.recycle();
    setSoundEffectsEnabled(false);
  }
  
  private void bindToFilePath(String paramString)
  {
    this.pendingFilePath = null;
    TextureManager.Instance().bindToViewFromFile(paramString, this, getWidth(), getHeight());
  }
  
  private void bindToResourceId(int paramInt)
  {
    this.pendingBitmapResourceId = -1;
    TextureManager.Instance().bindToView(paramInt, this, getWidth(), getHeight());
  }
  
  private void bindToUri(URI paramURI, TextureBindingOption paramTextureBindingOption)
  {
    this.pendingUri = null;
    this.option = null;
    TextureManager.Instance().bindToView(paramURI, this, paramTextureBindingOption);
  }
  
  protected void bindToUri(URI paramURI)
  {
    this.pendingUri = null;
    bindToUri(paramURI, new TextureBindingOption(getWidth(), getHeight(), this.useFileCache));
  }
  
  protected boolean hasSize()
  {
    return (getWidth() > 0) && (getHeight() > 0);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(resolveSize(0, paramInt1), resolveSize(0, paramInt2));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (hasSize())
    {
      if (this.pendingBitmapResourceId >= 0) {
        bindToResourceId(this.pendingBitmapResourceId);
      }
      if ((this.pendingUri != null) || ((this.pendingUri == null) && (this.option != null)))
      {
        if (this.option == null) {
          break label123;
        }
        TextureBindingOption localTextureBindingOption = new TextureBindingOption(getWidth(), getHeight(), this.option.resourceIdForLoading, this.option.resourceIdForError, this.option.useFileCache);
        bindToUri(this.pendingUri, localTextureBindingOption);
      }
    }
    for (;;)
    {
      if (this.pendingFilePath != null) {
        bindToFilePath(this.pendingFilePath);
      }
      return;
      label123:
      bindToUri(this.pendingUri);
    }
  }
  
  public void setImageFilePath(String paramString)
  {
    if (hasSize())
    {
      bindToFilePath(paramString);
      return;
    }
    this.pendingFilePath = paramString;
  }
  
  public void setImageResource(int paramInt)
  {
    if (hasSize())
    {
      bindToResourceId(paramInt);
      return;
    }
    this.pendingBitmapResourceId = paramInt;
  }
  
  public void setImageURI(Uri paramUri)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setImageURI2(URI paramURI)
  {
    if (hasSize())
    {
      bindToUri(paramURI);
      return;
    }
    this.pendingUri = paramURI;
  }
  
  public void setImageURI2(URI paramURI, int paramInt1, int paramInt2)
  {
    this.option = new TextureBindingOption(getWidth(), getHeight(), paramInt1, paramInt2, this.useFileCache);
    if (hasSize())
    {
      bindToUri(paramURI, this.option);
      return;
    }
    this.pendingUri = paramURI;
  }
  
  public void setImageURI2(URI paramURI, boolean paramBoolean)
  {
    this.useFileCache = paramBoolean;
    this.option = new TextureBindingOption(getWidth(), getHeight(), this.useFileCache);
    if (hasSize())
    {
      bindToUri(paramURI, this.option);
      return;
    }
    this.pendingUri = paramURI;
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    super.setOnClickListener(TouchUtil.createOnClickListener(paramOnClickListener));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\XLEImageViewFast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */