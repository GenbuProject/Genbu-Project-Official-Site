package android.support.v4.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

class PagerTitleStripIcs
{
  public static void setSingleLineAllCaps(TextView paramTextView)
  {
    paramTextView.setTransformationMethod(new SingleLineAllCapsTransform(paramTextView.getContext()));
  }
  
  private static class SingleLineAllCapsTransform
    extends SingleLineTransformationMethod
  {
    private static final String TAG = "SingleLineAllCapsTransform";
    private Locale mLocale;
    
    public SingleLineAllCapsTransform(Context paramContext)
    {
      this.mLocale = paramContext.getResources().getConfiguration().locale;
    }
    
    public CharSequence getTransformation(CharSequence paramCharSequence, View paramView)
    {
      paramCharSequence = super.getTransformation(paramCharSequence, paramView);
      if (paramCharSequence != null) {
        return paramCharSequence.toString().toUpperCase(this.mLocale);
      }
      return null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\android\support\v4\view\PagerTitleStripIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */