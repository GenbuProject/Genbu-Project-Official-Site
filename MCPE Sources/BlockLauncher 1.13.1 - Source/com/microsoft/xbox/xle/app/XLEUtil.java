package com.microsoft.xbox.xle.app;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.microsoft.xbox.toolkit.DialogManager;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.Date;
import java.util.Iterator;

public class XLEUtil
{
  public static <T> boolean isNullOrEmpty(Iterable<T> paramIterable)
  {
    return (paramIterable == null) || (!paramIterable.iterator().hasNext());
  }
  
  public static <T> boolean isNullOrEmpty(T[] paramArrayOfT)
  {
    return (paramArrayOfT == null) || (paramArrayOfT.length == 0);
  }
  
  public static boolean shouldRefresh(Date paramDate, long paramLong)
  {
    if (paramDate == null) {}
    while (new Date().getTime() - paramDate.getTime() > paramLong) {
      return true;
    }
    return false;
  }
  
  public static void showKeyboard(View paramView, int paramInt)
  {
    ThreadManager.UIThreadPostDelayed(new Runnable()
    {
      public void run()
      {
        ((InputMethodManager)XboxTcuiSdk.getSystemService("input_method")).showSoftInput(this.val$view, 1);
      }
    }, paramInt);
  }
  
  public static void showOkCancelDialog(String paramString1, String paramString2, String paramString3, Runnable paramRunnable1, String paramString4, Runnable paramRunnable2)
  {
    XLEAssert.assertNotNull("You must supply cancel text if this is not a must-act dialog.", paramString4);
    DialogManager.getInstance().showOkCancelDialog(paramString1, paramString2, paramString3, paramRunnable1, paramString4, paramRunnable2);
  }
  
  public static void updateAndShowTextViewUnlessEmpty(TextView paramTextView, CharSequence paramCharSequence)
  {
    if (paramTextView != null)
    {
      if ((paramCharSequence != null) && (paramCharSequence.length() > 0))
      {
        paramTextView.setText(paramCharSequence);
        paramTextView.setVisibility(0);
      }
    }
    else {
      return;
    }
    paramTextView.setVisibility(8);
  }
  
  public static void updateTextAndVisibilityIfNotNull(TextView paramTextView, CharSequence paramCharSequence, int paramInt)
  {
    if (paramTextView != null)
    {
      paramTextView.setText(paramCharSequence);
      paramTextView.setVisibility(paramInt);
    }
  }
  
  public static void updateVisibilityIfNotNull(View paramView, int paramInt)
  {
    if (paramView != null) {
      paramView.setVisibility(paramInt);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\XLEUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */