package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;

public abstract interface TintableBackgroundView
{
  @Nullable
  public abstract ColorStateList getSupportBackgroundTintList();
  
  @Nullable
  public abstract PorterDuff.Mode getSupportBackgroundTintMode();
  
  public abstract void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList);
  
  public abstract void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\view\TintableBackgroundView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */