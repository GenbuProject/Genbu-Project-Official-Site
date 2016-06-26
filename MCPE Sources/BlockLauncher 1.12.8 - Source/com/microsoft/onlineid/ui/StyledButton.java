package com.microsoft.onlineid.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import com.microsoft.onlineid.internal.ui.Fonts;

public class StyledButton
  extends Button
{
  public StyledButton(Context paramContext)
  {
    super(paramContext);
    initialize(paramContext);
  }
  
  public StyledButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initialize(paramContext);
  }
  
  public StyledButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initialize(paramContext);
  }
  
  private void initialize(Context paramContext)
  {
    if (!isInEditMode()) {
      setTypeface(Fonts.SegoeUISemiBold.getTypeface(paramContext));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\ui\StyledButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */