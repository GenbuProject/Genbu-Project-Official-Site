package com.microsoft.xbox.toolkit.ui;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import com.microsoft.xbox.toolkit.XLEAssert;

public class TouchUtil
{
  public static View.OnClickListener createOnClickListener(View.OnClickListener paramOnClickListener)
  {
    if (paramOnClickListener == null) {
      return null;
    }
    XLEAssert.assertNotNull("Original listener is null.", paramOnClickListener);
    return paramOnClickListener;
  }
  
  public static AdapterView.OnItemClickListener createOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    if (paramOnItemClickListener == null) {
      return null;
    }
    XLEAssert.assertNotNull("Original listener is null.", paramOnItemClickListener);
    return paramOnItemClickListener;
  }
  
  public static View.OnLongClickListener createOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    if (paramOnLongClickListener == null) {
      return null;
    }
    XLEAssert.assertNotNull("Original listener is null.", paramOnLongClickListener);
    return paramOnLongClickListener;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\TouchUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */