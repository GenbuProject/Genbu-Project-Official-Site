package com.microsoft.onlineid.internal.ui;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ProgressView
  extends RelativeLayout
{
  public static final int NumberOfDots = 5;
  private static final int ProgressColor = Color.rgb(121, 121, 121);
  private static final float ProgressDotSizeDip = 2.6F;
  private static final float ProgressPaddingDip = 2.6F;
  private int _dotSize;
  private ProgressAnimation _progressAnimation;
  
  public ProgressView(Context paramContext)
  {
    super(paramContext);
    initialize();
  }
  
  public ProgressView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initialize();
  }
  
  public ProgressView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initialize();
  }
  
  private View addDot()
  {
    View localView = new View(getContext());
    localView.setLayoutParams(new RelativeLayout.LayoutParams(this._dotSize, this._dotSize));
    localView.setBackgroundColor(ProgressColor);
    localView.setX(this._dotSize * -1);
    addView(localView);
    return localView;
  }
  
  private void initialize()
  {
    int i = 0;
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    this._dotSize = Dimensions.convertDipToPixels(2.6F, localDisplayMetrics);
    int j = Dimensions.convertDipToPixels(2.6F, localDisplayMetrics);
    setPadding(0, j, 0, j);
    while (i < 5)
    {
      addDot();
      i += 1;
    }
    this._progressAnimation = new ProgressAnimation(getWidth());
  }
  
  public boolean isAnimating()
  {
    return (this._progressAnimation != null) && (this._progressAnimation.isAnimating());
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = stopAnimation();
    this._progressAnimation = new ProgressAnimation(paramInt1);
    if (bool) {
      startAnimation();
    }
  }
  
  public void overrideDefaultPadding(float paramFloat)
  {
    int i = Dimensions.convertDipToPixels(2.6F, getContext().getResources().getDisplayMetrics());
    setPadding(0, i, 0, i);
  }
  
  public boolean startAnimation()
  {
    return (this._progressAnimation != null) && (this._progressAnimation.startAnimation());
  }
  
  public boolean stopAnimation()
  {
    return (this._progressAnimation != null) && (this._progressAnimation.stopAnimation());
  }
  
  private class ProgressAnimation
  {
    private int _animationDuration;
    private int[] _dotDelays;
    private int[] _keyframeXTranslations;
    private AnimatorSet _progressAnimator;
    
    public ProgressAnimation(int paramInt)
    {
      setAnimationParams(paramInt);
      this._progressAnimator = generateAnimation();
    }
    
    private PropertyValuesHolder createKeyframes()
    {
      int i = 0;
      Keyframe[] arrayOfKeyframe = new Keyframe[this._keyframeXTranslations.length];
      while (i < this._keyframeXTranslations.length)
      {
        arrayOfKeyframe[i] = Keyframe.ofFloat(new float[] { 0.0F, 0.15F, 0.65F, 0.8F, 1.0F }[i], this._keyframeXTranslations[i]);
        i += 1;
      }
      arrayOfKeyframe[(this._keyframeXTranslations.length - 1)].setInterpolator(new OvershootInterpolator(1.0F));
      return PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, arrayOfKeyframe);
    }
    
    private AnimatorSet generateAnimation()
    {
      Object localObject = createKeyframes();
      ObjectAnimator[] arrayOfObjectAnimator = new ObjectAnimator[5];
      int i = 0;
      while (i < 5)
      {
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(ProgressView.this.getChildAt(i), new PropertyValuesHolder[] { localObject });
        localObjectAnimator.setDuration(this._animationDuration);
        localObjectAnimator.setRepeatCount(-1);
        localObjectAnimator.setStartDelay(this._dotDelays[i]);
        arrayOfObjectAnimator[i] = localObjectAnimator;
        i += 1;
      }
      localObject = new AnimatorSet();
      ((AnimatorSet)localObject).playTogether(arrayOfObjectAnimator);
      return (AnimatorSet)localObject;
    }
    
    private void setAnimationParams(int paramInt)
    {
      int i = Math.round(paramInt / 25.0F);
      float f1 = 30.0F + paramInt / 10.0F;
      int k = Math.round(paramInt * 0.416666F);
      int m = Math.round(paramInt * (1.0F - 0.416666F * 2.0F));
      float f2 = m * 1000 / f1;
      float f3 = (f2 / 0.3333F - f2) / 2.0F;
      int j = Math.round(i * 1000 / f1);
      i = j;
      if (j > 333) {
        i = 333;
      }
      this._dotDelays = new int[] { 0, i, i * 2, i * 3, i * 4 };
      this._animationDuration = Math.round(i * 4.0F + (f2 + f3 * 2.0F) + 250.0F);
      this._keyframeXTranslations = new int[] { ProgressView.this._dotSize * -1, k, k + m, ProgressView.this._dotSize + paramInt, ProgressView.this._dotSize * -1 };
    }
    
    public boolean isAnimating()
    {
      return this._progressAnimator.isRunning();
    }
    
    public boolean startAnimation()
    {
      if (!this._progressAnimator.isRunning())
      {
        this._progressAnimator.start();
        return true;
      }
      return false;
    }
    
    public boolean stopAnimation()
    {
      if (this._progressAnimator.isRunning())
      {
        this._progressAnimator.end();
        return true;
      }
      return false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\ui\ProgressView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */