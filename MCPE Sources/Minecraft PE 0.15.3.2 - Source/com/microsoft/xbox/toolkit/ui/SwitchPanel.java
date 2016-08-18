package com.microsoft.xbox.toolkit.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.microsoft.xbox.toolkit.BackgroundThreadWaitor;
import com.microsoft.xbox.toolkit.BackgroundThreadWaitor.WaitType;
import com.microsoft.xbox.toolkit.XLERValueHelper;

public class SwitchPanel
  extends LinearLayout
{
  private static final int LAYOUT_BLOCK_TIMEOUT_MS = 150;
  private AnimatorListenerAdapter AnimateInListener = new AnimatorListenerAdapter()
  {
    public void onAnimationCancel(Animator paramAnonymousAnimator)
    {
      SwitchPanel.this.onAnimateInEnd();
    }
    
    public void onAnimationEnd(Animator paramAnonymousAnimator)
    {
      SwitchPanel.this.onAnimateInEnd();
    }
    
    public void onAnimationStart(Animator paramAnonymousAnimator)
    {
      SwitchPanel.this.onAnimateInStart();
    }
  };
  private AnimatorListenerAdapter AnimateOutListener = new AnimatorListenerAdapter()
  {
    public void onAnimationCancel(Animator paramAnonymousAnimator)
    {
      SwitchPanel.this.onAnimateOutEnd();
    }
    
    public void onAnimationEnd(Animator paramAnonymousAnimator)
    {
      SwitchPanel.this.onAnimateOutEnd();
    }
    
    public void onAnimationStart(Animator paramAnonymousAnimator)
    {
      SwitchPanel.this.onAnimateOutStart();
    }
  };
  private final int INVALID_STATE_ID = -1;
  private final int VALID_CONTENT_STATE = 0;
  private boolean active = false;
  private boolean blocking = false;
  private View newView = null;
  private View oldView = null;
  private int selectedState;
  private boolean shouldAnimate = true;
  
  public SwitchPanel(Context paramContext)
  {
    super(paramContext);
    throw new UnsupportedOperationException();
  }
  
  public SwitchPanel(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, XLERValueHelper.getStyleableRValueArray("SwitchPanel"));
    this.selectedState = paramContext.getInteger(XLERValueHelper.getStyleableRValue("SwitchPanel_selectedState"), -1);
    paramContext.recycle();
    if (this.selectedState < 0) {
      throw new IllegalArgumentException("You must specify the selectedState attribute in the xml, and the value must be positive.");
    }
    setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
  }
  
  private void onAnimateInEnd()
  {
    setBlocking(false);
    if (this.newView != null) {
      this.newView.setLayerType(0, null);
    }
  }
  
  private void onAnimateInStart()
  {
    if (this.newView != null)
    {
      this.newView.setLayerType(2, null);
      setBlocking(true);
    }
  }
  
  private void onAnimateOutEnd()
  {
    if (this.oldView != null)
    {
      this.oldView.setVisibility(8);
      this.oldView.setLayerType(0, null);
    }
  }
  
  private void onAnimateOutStart()
  {
    if (this.oldView != null)
    {
      this.oldView.setLayerType(2, null);
      setBlocking(true);
    }
  }
  
  private void updateVisibility(int paramInt1, int paramInt2)
  {
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      if (!(localView instanceof SwitchPanelChild)) {
        throw new UnsupportedOperationException("All children of SwitchPanel must implement the SwitchPanelChild interface. All other types are not supported and should be removed.");
      }
      int k = ((SwitchPanelChild)localView).getState();
      if (k == paramInt1) {
        this.oldView = localView;
      }
      for (;;)
      {
        i += 1;
        break;
        if (k == paramInt2) {
          this.newView = localView;
        } else {
          localView.setVisibility(8);
        }
      }
    }
    if ((this.shouldAnimate) && (paramInt2 == 0) && (this.newView != null))
    {
      this.newView.setAlpha(0.0F);
      this.newView.setVisibility(0);
      requestLayout();
      if (this.oldView != null) {
        this.oldView.animate().alpha(0.0F).setDuration(150L).setListener(this.AnimateOutListener);
      }
      this.newView.animate().alpha(1.0F).setDuration(150L).setListener(this.AnimateInListener);
      return;
    }
    if (this.oldView != null) {
      this.oldView.setVisibility(8);
    }
    if (this.newView != null)
    {
      this.newView.setAlpha(1.0F);
      this.newView.setVisibility(0);
    }
    requestLayout();
  }
  
  public int getState()
  {
    return this.selectedState;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    updateVisibility(-1, this.selectedState);
  }
  
  public void setActive(boolean paramBoolean)
  {
    this.active = paramBoolean;
  }
  
  public void setBlocking(boolean paramBoolean)
  {
    if (this.blocking != paramBoolean)
    {
      this.blocking = paramBoolean;
      if (this.blocking) {
        BackgroundThreadWaitor.getInstance().setBlocking(BackgroundThreadWaitor.WaitType.ListLayout, 150);
      }
    }
    else
    {
      return;
    }
    BackgroundThreadWaitor.getInstance().clearBlocking(BackgroundThreadWaitor.WaitType.ListLayout);
  }
  
  public void setShouldAnimate(boolean paramBoolean)
  {
    this.shouldAnimate = paramBoolean;
  }
  
  public void setState(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("New state must be a positive value.");
    }
    if (this.selectedState != paramInt)
    {
      int i = this.selectedState;
      this.selectedState = paramInt;
      updateVisibility(i, paramInt);
    }
  }
  
  public static abstract interface SwitchPanelChild
  {
    public abstract int getState();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\SwitchPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */