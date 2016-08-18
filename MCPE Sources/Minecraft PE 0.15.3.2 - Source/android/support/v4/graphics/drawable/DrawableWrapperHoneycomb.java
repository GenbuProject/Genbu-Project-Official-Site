package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class DrawableWrapperHoneycomb
  extends DrawableWrapperDonut
{
  DrawableWrapperHoneycomb(Drawable paramDrawable)
  {
    super(paramDrawable);
  }
  
  DrawableWrapperHoneycomb(DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
  }
  
  public void jumpToCurrentState()
  {
    this.mDrawable.jumpToCurrentState();
  }
  
  @NonNull
  DrawableWrapperDonut.DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateHoneycomb(this.mState, null);
  }
  
  private static class DrawableWrapperStateHoneycomb
    extends DrawableWrapperDonut.DrawableWrapperState
  {
    DrawableWrapperStateHoneycomb(@Nullable DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
    {
      super(paramResources);
    }
    
    public Drawable newDrawable(@Nullable Resources paramResources)
    {
      return new DrawableWrapperHoneycomb(this, paramResources);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\graphics\drawable\DrawableWrapperHoneycomb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */