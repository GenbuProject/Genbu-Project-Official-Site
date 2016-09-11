package com.microsoft.xbox.xle.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.microsoft.xbox.toolkit.system.SystemUtil;
import com.microsoft.xboxtcui.R.styleable;

public class XLERootView
  extends RelativeLayout
{
  private static final int UNASSIGNED_ACTIVITY_BODY_ID = -1;
  private View activityBody;
  private int activityBodyIndex;
  private String headerName;
  private boolean isTopLevel = false;
  private long lastFps = 0L;
  private long lastMs = 0L;
  private int origPaddingBottom;
  private boolean showTitleBar = true;
  
  public XLERootView(Context paramContext)
  {
    super(paramContext);
    throw new UnsupportedOperationException();
  }
  
  public XLERootView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.XLERootView);
    if (paramContext != null) {}
    try
    {
      this.activityBodyIndex = paramContext.getResourceId(R.styleable.XLERootView_activityBody, -1);
      this.isTopLevel = paramContext.getBoolean(R.styleable.XLERootView_isTopLevel, false);
      this.showTitleBar = paramContext.getBoolean(R.styleable.XLERootView_showTitleBar, true);
      int i = paramContext.getInt(R.styleable.XLERootView_minScreenPercent, Integer.MIN_VALUE);
      if (i != Integer.MIN_VALUE) {
        setMinimumWidth(Math.max(0, i) * SystemUtil.getScreenWidth() / 100);
      }
      this.headerName = paramContext.getString(R.styleable.XLERootView_headerName);
      return;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  private void initialize()
  {
    if (this.activityBodyIndex != -1) {}
    for (this.activityBody = findViewById(this.activityBodyIndex);; this.activityBody = this)
    {
      this.origPaddingBottom = getPaddingBottom();
      if ((this.activityBody != null) && (this.activityBody != this))
      {
        Object localObject = this.activityBody.getLayoutParams();
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams((ViewGroup.LayoutParams)localObject);
        localLayoutParams.width = -1;
        localLayoutParams.height = -1;
        localLayoutParams.addRule(10);
        if ((localObject instanceof ViewGroup.MarginLayoutParams))
        {
          localObject = (ViewGroup.MarginLayoutParams)localObject;
          int i = getPaddingLeft();
          int j = ((ViewGroup.MarginLayoutParams)localObject).leftMargin;
          int k = getPaddingTop();
          int m = ((ViewGroup.MarginLayoutParams)localObject).topMargin;
          int n = getPaddingRight();
          int i1 = ((ViewGroup.MarginLayoutParams)localObject).rightMargin;
          int i2 = this.origPaddingBottom;
          setPadding(i + j, k + m, n + i1, ((ViewGroup.MarginLayoutParams)localObject).bottomMargin + i2);
          localLayoutParams.setMargins(0, 0, 0, 0);
        }
        removeView(this.activityBody);
        addView(this.activityBody, localLayoutParams);
      }
      return;
    }
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
  }
  
  public String getHeaderName()
  {
    return this.headerName;
  }
  
  public boolean getIsTopLevel()
  {
    return this.isTopLevel;
  }
  
  public boolean getShowTitleBar()
  {
    return this.showTitleBar;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    initialize();
  }
  
  public void setBottomMargin(int paramInt)
  {
    setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), this.origPaddingBottom + paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\ui\XLERootView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */