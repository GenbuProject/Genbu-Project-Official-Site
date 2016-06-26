package de.ankri.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.CompoundButton;
import de.ankri.text.method.AllCapsTransformationMethod;
import de.ankri.text.method.TransformationMethodCompat2;
import net.zhuoweizhang.mcpelauncher.R.attr;
import net.zhuoweizhang.mcpelauncher.R.styleable;

public class Switch
  extends CompoundButton
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int MONOSPACE = 3;
  private static final int SANS = 1;
  private static final int SERIF = 2;
  private static final int TOUCH_MODE_DOWN = 1;
  private static final int TOUCH_MODE_DRAGGING = 2;
  private static final int TOUCH_MODE_IDLE = 0;
  private int mMinFlingVelocity;
  private Layout mOffLayout;
  private Layout mOnLayout;
  private int mSwitchBottom;
  private int mSwitchHeight;
  private int mSwitchLeft;
  private int mSwitchMinWidth;
  private int mSwitchPadding;
  private int mSwitchRight;
  private int mSwitchTop;
  private TransformationMethodCompat2 mSwitchTransformationMethod;
  private int mSwitchWidth;
  private final Rect mTempRect = new Rect();
  private ColorStateList mTextColors;
  private CharSequence mTextOff;
  private CharSequence mTextOn;
  private TextPaint mTextPaint = new TextPaint(1);
  private Drawable mThumbDrawable;
  private float mThumbPosition;
  private int mThumbTextPadding;
  private int mThumbWidth;
  private int mTouchMode;
  private int mTouchSlop;
  private float mTouchX;
  private float mTouchY;
  private Drawable mTrackDrawable;
  private VelocityTracker mVelocityTracker = VelocityTracker.obtain();
  
  public Switch(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public Switch(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.switchStyle);
  }
  
  public Switch(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = getResources();
    this.mTextPaint.density = localResources.getDisplayMetrics().density;
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Switch, paramInt, 0);
    this.mThumbDrawable = paramAttributeSet.getDrawable(R.styleable.Switch_thumb);
    this.mTrackDrawable = paramAttributeSet.getDrawable(R.styleable.Switch_track);
    this.mTextOn = paramAttributeSet.getText(R.styleable.Switch_textOn);
    this.mTextOff = paramAttributeSet.getText(R.styleable.Switch_textOff);
    this.mThumbTextPadding = paramAttributeSet.getDimensionPixelSize(R.styleable.Switch_thumbTextPadding, 0);
    this.mSwitchMinWidth = paramAttributeSet.getDimensionPixelSize(R.styleable.Switch_switchMinWidth, 0);
    this.mSwitchPadding = paramAttributeSet.getDimensionPixelSize(R.styleable.Switch_switchPadding, 0);
    paramInt = paramAttributeSet.getResourceId(R.styleable.Switch_switchTextAppearance, 0);
    if (paramInt != 0) {
      setSwitchTextAppearance(paramContext, paramInt);
    }
    paramAttributeSet.recycle();
    paramContext = ViewConfiguration.get(paramContext);
    this.mTouchSlop = paramContext.getScaledTouchSlop();
    this.mMinFlingVelocity = paramContext.getScaledMinimumFlingVelocity();
    refreshDrawableState();
    setChecked(isChecked());
    setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView) {}
    });
  }
  
  private void animateThumbToCheckedState(boolean paramBoolean)
  {
    setChecked(paramBoolean);
  }
  
  private void cancelSuperTouch(MotionEvent paramMotionEvent)
  {
    paramMotionEvent = MotionEvent.obtain(paramMotionEvent);
    paramMotionEvent.setAction(3);
    super.onTouchEvent(paramMotionEvent);
    paramMotionEvent.recycle();
  }
  
  private boolean getTargetCheckedState()
  {
    return this.mThumbPosition >= getThumbScrollRange() / 2;
  }
  
  private int getThumbScrollRange()
  {
    if (this.mTrackDrawable == null) {
      return 0;
    }
    this.mTrackDrawable.getPadding(this.mTempRect);
    return this.mSwitchWidth - this.mThumbWidth - this.mTempRect.left - this.mTempRect.right;
  }
  
  private boolean hitThumb(float paramFloat1, float paramFloat2)
  {
    this.mThumbDrawable.getPadding(this.mTempRect);
    int i = this.mSwitchTop;
    int j = this.mTouchSlop;
    int k = this.mSwitchLeft + (int)(this.mThumbPosition + 0.5F) - this.mTouchSlop;
    int m = this.mThumbWidth;
    int n = this.mTempRect.left;
    int i1 = this.mTempRect.right;
    int i2 = this.mTouchSlop;
    int i3 = this.mSwitchBottom;
    int i4 = this.mTouchSlop;
    return (paramFloat1 > k) && (paramFloat1 < m + k + n + i1 + i2) && (paramFloat2 > i - j) && (paramFloat2 < i3 + i4);
  }
  
  private Layout makeLayout(CharSequence paramCharSequence)
  {
    if (this.mSwitchTransformationMethod != null) {
      paramCharSequence = this.mSwitchTransformationMethod.getTransformation(paramCharSequence, this);
    }
    for (;;)
    {
      return new StaticLayout(paramCharSequence, this.mTextPaint, (int)FloatMath.ceil(Layout.getDesiredWidth(paramCharSequence, this.mTextPaint)), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
    }
  }
  
  private void setSwitchTypefaceByIndex(int paramInt1, int paramInt2)
  {
    Typeface localTypeface = null;
    switch (paramInt1)
    {
    }
    for (;;)
    {
      setSwitchTypeface(localTypeface, paramInt2);
      return;
      localTypeface = Typeface.SANS_SERIF;
      continue;
      localTypeface = Typeface.SERIF;
      continue;
      localTypeface = Typeface.MONOSPACE;
    }
  }
  
  private void setThumbPosition(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (float f = getThumbScrollRange();; f = 0.0F)
    {
      this.mThumbPosition = f;
      return;
    }
  }
  
  private void stopDrag(MotionEvent paramMotionEvent)
  {
    this.mTouchMode = 0;
    int i;
    boolean bool;
    if ((paramMotionEvent.getAction() == 1) && (isEnabled()))
    {
      i = 1;
      cancelSuperTouch(paramMotionEvent);
      if (i == 0) {
        break label98;
      }
      this.mVelocityTracker.computeCurrentVelocity(1000);
      float f = this.mVelocityTracker.getXVelocity();
      if (Math.abs(f) <= this.mMinFlingVelocity) {
        break label89;
      }
      if (f <= 0.0F) {
        break label83;
      }
      bool = true;
    }
    for (;;)
    {
      animateThumbToCheckedState(bool);
      return;
      i = 0;
      break;
      label83:
      bool = false;
      continue;
      label89:
      bool = getTargetCheckedState();
    }
    label98:
    animateThumbToCheckedState(isChecked());
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    if (this.mThumbDrawable != null) {
      this.mThumbDrawable.setState(arrayOfInt);
    }
    if (this.mTrackDrawable != null) {
      this.mTrackDrawable.setState(arrayOfInt);
    }
    invalidate();
  }
  
  public int getCompoundPaddingRight()
  {
    int j = super.getCompoundPaddingRight() + this.mSwitchWidth;
    int i = j;
    if (!TextUtils.isEmpty(getText())) {
      i = j + this.mSwitchPadding;
    }
    return i;
  }
  
  public int getSwitchMinWidth()
  {
    return this.mSwitchMinWidth;
  }
  
  public int getSwitchPadding()
  {
    return this.mSwitchPadding;
  }
  
  public CharSequence getTextOff()
  {
    return this.mTextOff;
  }
  
  public CharSequence getTextOn()
  {
    return this.mTextOn;
  }
  
  public Drawable getThumbDrawable()
  {
    return this.mThumbDrawable;
  }
  
  public int getThumbTextPadding()
  {
    return this.mThumbTextPadding;
  }
  
  public Drawable getTrackDrawable()
  {
    return this.mTrackDrawable;
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (isChecked()) {
      mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    }
    return arrayOfInt;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int k = this.mSwitchLeft;
    int i = this.mSwitchTop;
    int i1 = this.mSwitchRight;
    int j = this.mSwitchBottom;
    this.mTrackDrawable.setBounds(k, i, i1, j);
    this.mTrackDrawable.draw(paramCanvas);
    paramCanvas.save();
    this.mTrackDrawable.getPadding(this.mTempRect);
    int n = k + this.mTempRect.left;
    k = this.mTempRect.top;
    int i2 = this.mTempRect.right;
    int m = this.mTempRect.bottom;
    paramCanvas.clipRect(n, i, i1 - i2, j);
    this.mThumbDrawable.getPadding(this.mTempRect);
    i2 = (int)(this.mThumbPosition + 0.5F);
    i1 = n - this.mTempRect.left + i2;
    n = n + i2 + this.mThumbWidth + this.mTempRect.right;
    this.mThumbDrawable.setBounds(i1, i, n, j);
    this.mThumbDrawable.draw(paramCanvas);
    if (this.mTextColors != null) {
      this.mTextPaint.setColor(this.mTextColors.getColorForState(getDrawableState(), this.mTextColors.getDefaultColor()));
    }
    this.mTextPaint.drawableState = getDrawableState();
    if (getTargetCheckedState()) {}
    for (Layout localLayout = this.mOnLayout;; localLayout = this.mOffLayout)
    {
      if (localLayout != null)
      {
        paramCanvas.translate((i1 + n) / 2 - localLayout.getWidth() / 2, (i + k + (j - m)) / 2 - localLayout.getHeight() / 2);
        localLayout.draw(paramCanvas);
      }
      paramCanvas.restore();
      return;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    setThumbPosition(isChecked());
    paramInt3 = getWidth() - getPaddingRight();
    paramInt4 = this.mSwitchWidth;
    switch (getGravity() & 0x70)
    {
    default: 
      paramInt2 = getPaddingTop();
      paramInt1 = paramInt2 + this.mSwitchHeight;
    }
    for (;;)
    {
      this.mSwitchLeft = (paramInt3 - paramInt4);
      this.mSwitchTop = paramInt2;
      this.mSwitchBottom = paramInt1;
      this.mSwitchRight = paramInt3;
      return;
      paramInt2 = (getPaddingTop() + getHeight() - getPaddingBottom()) / 2 - this.mSwitchHeight / 2;
      paramInt1 = paramInt2 + this.mSwitchHeight;
      continue;
      paramInt1 = getHeight() - getPaddingBottom();
      paramInt2 = paramInt1 - this.mSwitchHeight;
    }
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.mOnLayout == null) {
      this.mOnLayout = makeLayout(this.mTextOn);
    }
    if (this.mOffLayout == null) {
      this.mOffLayout = makeLayout(this.mTextOff);
    }
    this.mTrackDrawable.getPadding(this.mTempRect);
    int i = Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth());
    int j = Math.max(this.mSwitchMinWidth, i * 2 + this.mThumbTextPadding * 4 + this.mTempRect.left + this.mTempRect.right);
    int k = this.mTrackDrawable.getIntrinsicHeight();
    this.mThumbWidth = (this.mThumbTextPadding * 2 + i);
    this.mSwitchWidth = j;
    this.mSwitchHeight = k;
    super.onMeasure(paramInt1, paramInt2);
    if (getMeasuredHeight() < k) {
      setMeasuredDimension(getMeasuredWidth(), k);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (paramMotionEvent.getActionMasked())
    {
    }
    for (;;)
    {
      bool = super.onTouchEvent(paramMotionEvent);
      float f1;
      float f2;
      do
      {
        return bool;
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        if ((!isEnabled()) || (!hitThumb(f1, f2))) {
          break;
        }
        this.mTouchMode = 1;
        this.mTouchX = f1;
        this.mTouchY = f2;
        break;
        switch (this.mTouchMode)
        {
        case 0: 
        default: 
          break;
        case 1: 
          f1 = paramMotionEvent.getX();
          f2 = paramMotionEvent.getY();
          if ((Math.abs(f1 - this.mTouchX) <= this.mTouchSlop) && (Math.abs(f2 - this.mTouchY) <= this.mTouchSlop)) {
            break;
          }
          this.mTouchMode = 2;
          getParent().requestDisallowInterceptTouchEvent(true);
          this.mTouchX = f1;
          this.mTouchY = f2;
          return true;
        case 2: 
          f1 = paramMotionEvent.getX();
          f2 = this.mTouchX;
          f2 = Math.max(0.0F, Math.min(this.mThumbPosition + (f1 - f2), getThumbScrollRange()));
        }
      } while (f2 == this.mThumbPosition);
      this.mThumbPosition = f2;
      this.mTouchX = f1;
      invalidate();
      return true;
      if (this.mTouchMode == 2)
      {
        stopDrag(paramMotionEvent);
        return true;
      }
      this.mTouchMode = 0;
      this.mVelocityTracker.clear();
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    super.setChecked(paramBoolean);
    setThumbPosition(isChecked());
    invalidate();
  }
  
  public void setSwitchMinWidth(int paramInt)
  {
    this.mSwitchMinWidth = paramInt;
    requestLayout();
  }
  
  public void setSwitchPadding(int paramInt)
  {
    this.mSwitchPadding = paramInt;
    requestLayout();
  }
  
  public void setSwitchTextAppearance(Context paramContext, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramInt, R.styleable.TextAppearanceSwitch);
    ColorStateList localColorStateList = paramContext.getColorStateList(R.styleable.TextAppearanceSwitch_textColor);
    if (localColorStateList != null)
    {
      this.mTextColors = localColorStateList;
      paramInt = paramContext.getDimensionPixelSize(R.styleable.TextAppearanceSwitch_textSize, 0);
      if ((paramInt != 0) && (paramInt != this.mTextPaint.getTextSize()))
      {
        this.mTextPaint.setTextSize(paramInt);
        requestLayout();
      }
      setSwitchTypefaceByIndex(paramContext.getInt(R.styleable.TextAppearanceSwitch_typeface, -1), paramContext.getInt(R.styleable.TextAppearanceSwitch_textStyle, -1));
      if (!paramContext.getBoolean(R.styleable.TextAppearanceSwitch_textAllCaps, false)) {
        break label137;
      }
      this.mSwitchTransformationMethod = new AllCapsTransformationMethod(getContext());
      this.mSwitchTransformationMethod.setLengthChangesAllowed(true);
    }
    for (;;)
    {
      paramContext.recycle();
      return;
      this.mTextColors = getTextColors();
      break;
      label137:
      this.mSwitchTransformationMethod = null;
    }
  }
  
  public void setSwitchTypeface(Typeface paramTypeface)
  {
    if (this.mTextPaint.getTypeface() != paramTypeface)
    {
      this.mTextPaint.setTypeface(paramTypeface);
      requestLayout();
      invalidate();
    }
  }
  
  public void setSwitchTypeface(Typeface paramTypeface, int paramInt)
  {
    boolean bool = false;
    if (paramInt > 0)
    {
      int i;
      if (paramTypeface == null)
      {
        paramTypeface = Typeface.defaultFromStyle(paramInt);
        setSwitchTypeface(paramTypeface);
        if (paramTypeface == null) {
          break label88;
        }
        i = paramTypeface.getStyle();
        label31:
        paramInt &= (i ^ 0xFFFFFFFF);
        paramTypeface = this.mTextPaint;
        if ((paramInt & 0x1) != 0) {
          bool = true;
        }
        paramTypeface.setFakeBoldText(bool);
        paramTypeface = this.mTextPaint;
        if ((paramInt & 0x2) == 0) {
          break label94;
        }
      }
      label88:
      label94:
      for (float f = -0.25F;; f = 0.0F)
      {
        paramTypeface.setTextSkewX(f);
        return;
        paramTypeface = Typeface.create(paramTypeface, paramInt);
        break;
        i = 0;
        break label31;
      }
    }
    this.mTextPaint.setFakeBoldText(false);
    this.mTextPaint.setTextSkewX(0.0F);
    setSwitchTypeface(paramTypeface);
  }
  
  public void setTextOff(CharSequence paramCharSequence)
  {
    this.mTextOff = paramCharSequence;
    requestLayout();
  }
  
  public void setTextOn(CharSequence paramCharSequence)
  {
    this.mTextOn = paramCharSequence;
    requestLayout();
  }
  
  public void setThumbDrawable(Drawable paramDrawable)
  {
    this.mThumbDrawable = paramDrawable;
    requestLayout();
  }
  
  public void setThumbResource(int paramInt)
  {
    setThumbDrawable(getContext().getResources().getDrawable(paramInt));
  }
  
  public void setThumbTextPadding(int paramInt)
  {
    this.mThumbTextPadding = paramInt;
    requestLayout();
  }
  
  public void setTrackDrawable(Drawable paramDrawable)
  {
    this.mTrackDrawable = paramDrawable;
    requestLayout();
  }
  
  public void setTrackResource(int paramInt)
  {
    setTrackDrawable(getContext().getResources().getDrawable(paramInt));
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == this.mThumbDrawable) || (paramDrawable == this.mTrackDrawable);
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\de\ankri\views\Switch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */