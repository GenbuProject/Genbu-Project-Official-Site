package com.microsoft.xbox.toolkit.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.microsoft.xbox.toolkit.ui.util.LibCompat;
import com.microsoft.xboxtcui.R.drawable;
import com.microsoft.xboxtcui.R.styleable;

public class XLECheckBox
  extends ViewGroup
{
  private final CheckBox checkBox;
  private final TextView subText;
  private final TextView text;
  
  public XLECheckBox(Context paramContext)
  {
    super(paramContext);
    this.checkBox = new CheckBox(paramContext);
    this.text = new TextView(paramContext);
    this.subText = new TextView(paramContext);
  }
  
  public XLECheckBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.checkBox = new CheckBox(paramContext, paramAttributeSet);
    this.text = new TextView(paramContext, paramAttributeSet);
    this.subText = new TextView(paramContext, paramAttributeSet);
    initialize(paramContext, paramAttributeSet);
  }
  
  public XLECheckBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.checkBox = new CheckBox(paramContext, paramAttributeSet);
    this.text = new TextView(paramContext, paramAttributeSet);
    this.subText = new TextView(paramContext, paramAttributeSet);
    initialize(paramContext, paramAttributeSet);
  }
  
  private void initialize(Context paramContext, AttributeSet paramAttributeSet)
  {
    this.checkBox.setButtonDrawable(R.drawable.apptheme_btn_check_holo_light);
    addView(this.checkBox, new ViewGroup.LayoutParams(-2, -2));
    this.text.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        XLECheckBox.this.checkBox.toggle();
      }
    });
    addView(this.text, new ViewGroup.LayoutParams(-2, -2));
    addView(this.subText, new ViewGroup.LayoutParams(-2, -2));
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.XLECheckBox);
    try
    {
      if (!isInEditMode())
      {
        int i = paramAttributeSet.getResourceId(R.styleable.XLECheckBox_textStyle, -1);
        LibCompat.setTextAppearance(this.text, i);
        Object localObject = paramAttributeSet.getString(R.styleable.XLECheckBox_textTypefaceSource);
        localObject = FontManager.Instance().getTypeface(paramContext, (String)localObject);
        this.text.setTypeface((Typeface)localObject);
        i = paramAttributeSet.getResourceId(R.styleable.XLECheckBox_subTextStyle, -1);
        LibCompat.setTextAppearance(this.subText, i);
        localObject = paramAttributeSet.getString(R.styleable.XLECheckBox_subTextTypefaceSource);
        paramContext = FontManager.Instance().getTypeface(paramContext, (String)localObject);
        this.subText.setTypeface(paramContext);
      }
      this.text.setText(paramAttributeSet.getString(R.styleable.XLECheckBox_text));
      this.subText.setText(paramAttributeSet.getString(R.styleable.XLECheckBox_subText));
      return;
    }
    finally
    {
      paramAttributeSet.recycle();
    }
  }
  
  public CharSequence getSubText()
  {
    return this.subText.getText();
  }
  
  public CharSequence getText()
  {
    return this.text.getText();
  }
  
  public boolean isChecked()
  {
    return this.checkBox.isChecked();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = getPaddingLeft();
    paramInt1 = getPaddingTop() + Math.max(this.checkBox.getMeasuredHeight() / 2, this.text.getMeasuredHeight() / 2);
    paramInt3 = paramInt1 - this.checkBox.getMeasuredWidth() / 2;
    this.checkBox.layout(paramInt2, paramInt3, this.checkBox.getMeasuredWidth() + paramInt2, this.checkBox.getMeasuredHeight() + paramInt3);
    paramInt2 += this.checkBox.getMeasuredWidth();
    paramInt1 -= this.text.getMeasuredHeight() / 2;
    this.text.layout(paramInt2, paramInt1, this.text.getMeasuredWidth() + paramInt2, this.text.getMeasuredHeight() + paramInt1);
    paramInt1 += this.text.getMeasuredHeight();
    this.subText.layout(paramInt2, paramInt1, this.subText.getMeasuredWidth() + paramInt2, this.subText.getMeasuredHeight() + paramInt1);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt1);
    int j;
    if (k == 0)
    {
      paramInt1 = 0;
      j = View.MeasureSpec.getSize(paramInt2);
      int m = View.MeasureSpec.getMode(paramInt2);
      if (m != 0) {
        break label302;
      }
      paramInt2 = 0;
      label37:
      int n = getPaddingLeft();
      int i1 = getPaddingTop();
      int i2 = Math.max(i - n - getPaddingRight(), 0);
      int i3 = Math.max(j - i1 - getPaddingBottom(), 0);
      this.checkBox.measure(View.MeasureSpec.makeMeasureSpec(i2, paramInt1), View.MeasureSpec.makeMeasureSpec(i3, paramInt2));
      n += this.checkBox.getMeasuredWidth();
      i2 = Math.max(i - n - getPaddingRight(), 0);
      i3 = Math.max(j - i1 - getPaddingBottom(), 0);
      this.text.measure(View.MeasureSpec.makeMeasureSpec(i2, paramInt1), View.MeasureSpec.makeMeasureSpec(i3, paramInt2));
      i1 += Math.max(this.checkBox.getMeasuredHeight(), this.text.getMeasuredHeight());
      i2 = Math.max(i - n - getPaddingRight(), 0);
      i3 = Math.max(j - i1 - getPaddingBottom(), 0);
      this.subText.measure(View.MeasureSpec.makeMeasureSpec(i2, paramInt1), View.MeasureSpec.makeMeasureSpec(i3, paramInt2));
      paramInt1 = Math.max(this.text.getMeasuredWidth(), this.subText.getMeasuredWidth());
      paramInt2 = this.subText.getMeasuredHeight();
      paramInt1 = n + paramInt1 + getPaddingRight();
      paramInt2 = i1 + paramInt2 + getPaddingBottom();
      if (k != 0) {
        break label308;
      }
      label284:
      if (m != 0) {
        break label317;
      }
    }
    for (;;)
    {
      setMeasuredDimension(paramInt1, paramInt2);
      return;
      paramInt1 = Integer.MIN_VALUE;
      break;
      label302:
      paramInt2 = Integer.MIN_VALUE;
      break label37;
      label308:
      paramInt1 = Math.min(paramInt1, i);
      break label284;
      label317:
      paramInt2 = Math.min(paramInt2, j);
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    this.checkBox.setChecked(paramBoolean);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.checkBox.setEnabled(paramBoolean);
    this.text.setEnabled(paramBoolean);
    this.subText.setEnabled(paramBoolean);
  }
  
  public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.checkBox.setOnCheckedChangeListener(paramOnCheckedChangeListener);
  }
  
  public void setSubText(CharSequence paramCharSequence)
  {
    this.subText.setText(paramCharSequence);
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    this.text.setText(paramCharSequence);
  }
  
  public void toggle()
  {
    this.checkBox.toggle();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\XLECheckBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */