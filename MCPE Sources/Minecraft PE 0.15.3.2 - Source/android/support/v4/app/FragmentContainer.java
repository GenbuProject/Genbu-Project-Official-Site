package android.support.v4.app;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class FragmentContainer
{
  @Nullable
  public abstract View onFindViewById(@IdRes int paramInt);
  
  public abstract boolean onHasView();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\app\FragmentContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */